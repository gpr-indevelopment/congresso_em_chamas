import styles from "./ProfileCard.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFileAlt } from "@fortawesome/free-regular-svg-icons";
import { faNewspaper, faDollarSign } from "@fortawesome/free-solid-svg-icons";
import { Card, Avatar, Tooltip } from "antd";
import { Link } from "react-router-dom";
import { UserOutlined } from "@ant-design/icons";

import React from "react";

function ProfileCard(props) {
  const { Meta } = Card;
  return (
    <Card
      hoverable={true}
      className={styles.card}
      actions={[
        <Tooltip title="Despesas">
          <Link to={`/expenses?politician=${props.profile.id}`}>
            <FontAwesomeIcon icon={faDollarSign} size={"lg"} />
          </Link>
        </Tooltip>,
        <Tooltip title="Notícias">
          <Link to={`/news?politician=${props.profile.id}`}>
            <FontAwesomeIcon icon={faNewspaper} size={"lg"} />
          </Link>
        </Tooltip>,
        <Tooltip title="Proposições">
          <Link to={`/propositions?politician=${props.profile.id}`}>
            <FontAwesomeIcon icon={faFileAlt} size={"lg"} />
          </Link>
        </Tooltip>,
      ]}
    >
      <Meta
        avatar={
          <Avatar
            size={96}
            src={props.profile.picture}
            icon={<UserOutlined />}
          />
        }
        title={
          <Tooltip title={props.profile.name}>{props.profile.name}</Tooltip>
        }
        description={props.profile.party}
      />
    </Card>
  );
}

export default ProfileCard;
