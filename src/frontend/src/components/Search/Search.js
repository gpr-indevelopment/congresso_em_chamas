import React, { useEffect } from "react";
import { Header, PoliticianSearch, MainContent, Footer } from "../";
import { Spin } from "antd";
import ProfileCard from "./ProfileCard";
import styles from "./Search.module.css";

function Search(props) {
  let { handleSearchRequest } = props;
  useEffect(() => {
    let politicianName = new URLSearchParams(window.location.search).get(
      "politicianName"
    );
    if (politicianName) {
      handleSearchRequest(politicianName);
    }
  }, [handleSearchRequest]);

  let buildDataCards = (profiles) => {
    let cards = [];
    profiles.forEach((profile) => {
      cards.push(<ProfileCard profile={profile} />);
    });
    return cards;
  };

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header>
        <PoliticianSearch
          handleSearchSubmit={(input) => props.handleSearchRequest(input)}
        />
      </Header>
      <MainContent>
        <div className={styles.container}>{buildDataCards(props.profiles)}</div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Search;
