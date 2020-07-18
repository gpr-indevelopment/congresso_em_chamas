import React, { useEffect } from "react";
import * as CongressoComponents from "../index";
import { Spin } from "antd";
import styles from "./Search.module.css";

function Search(props) {
  useEffect(() => {
    let politicianName = new URLSearchParams(window.location.search).get(
      "politicianName"
    );
    if (politicianName) {
      props.handleSearchRequest(politicianName);
    }
  }, []);

  let buildDataCards = (profiles) => {
    let cards = [];
    profiles.forEach((profile) => {
      cards.push(<CongressoComponents.ProfileCard profile={profile} />);
    });
    return cards;
  };

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <CongressoComponents.Header>
        <CongressoComponents.SearchInput
          handleSearchSubmit={(input) => props.handleSearchRequest(input)}
        />
      </CongressoComponents.Header>
      <CongressoComponents.MainContent>
        <div className={styles.container}>{buildDataCards(props.profiles)}</div>
      </CongressoComponents.MainContent>
      <CongressoComponents.Footer />
    </Spin>
  );
}

export default Search;
