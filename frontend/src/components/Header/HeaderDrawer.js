import React from "react";
import { Drawer, Menu, Row, Col } from "antd";
import logo from "../../assets/perfil-nobackground.png";
import styles from "./HeaderDrawer.module.css";
import { Link } from "react-router-dom";
import { faFileAlt } from "@fortawesome/free-regular-svg-icons";
import { faNewspaper, faDollarSign } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PoliticianSearch from "../PoliticianSearch";
import history from "../../history";

function HeaderDrawer(props) {
  let pathname = window.location.pathname;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  return (
    <Drawer
      visible={props.visible}
      onClose={props.onClose}
      placement="left"
      title={
        <Link to="/">
          <div className={styles.title}>
            <img src={logo} height={70} alt="No content available" />
          </div>
        </Link>
      }
    >
      <Menu>
        <div className={styles.search}>
          <PoliticianSearch
            handleSearchSubmit={(input) =>
              history.push(`/search?politicianName=${input.nome}&uf=${input.uf}`)
            }
          />
        </div>

        <Menu.Item disabled={pathname === "/expenses"}>
          <Link to={`/expenses?politician=${politicianId}`}>
            <Row>
              <Col span={6} className={styles.icon}>
                <FontAwesomeIcon icon={faDollarSign} size="lg" />
              </Col>
              <Col span={18}>Despesas</Col>
            </Row>
          </Link>
        </Menu.Item>
        <Menu.Item disabled={pathname === "/news"}>
          <Link to={`/news?politician=${politicianId}`}>
            <Row>
              <Col span={6} className={styles.icon}>
                <FontAwesomeIcon icon={faNewspaper} size="lg" />
              </Col>
              <Col span={18}>Notícias</Col>
            </Row>
          </Link>
        </Menu.Item>
        <Menu.Item disabled={pathname === "/propositions"}>
          <Link to={`/propositions?politician=${politicianId}`}>
            <Row>
              <Col span={6} className={styles.icon}>
                <FontAwesomeIcon icon={faFileAlt} size="lg" />
              </Col>
              <Col span={18}>Proposições</Col>
            </Row>
          </Link>
        </Menu.Item>
      </Menu>
    </Drawer>
  );
}

export default HeaderDrawer;
