import React, { useEffect } from "react";
import {
  ProfileCard,
  Header,
  SearchInput,
  MainContent,
  Footer,
} from "../index";
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
      cards.push(<ProfileCard profile={profile} />);
    });
    return cards;
  };

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header>
        <SearchInput
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
