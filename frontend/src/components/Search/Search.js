import React, { useEffect } from "react";
import { Header, PoliticianSearch, MainContent, Footer } from "../";
import { Spin, Result } from "antd";
import ProfileCard from "./ProfileCard";
import styles from "./Search.module.css";

function Search(props) {
  let { handleSearchRequest } = props;
  useEffect(() => {
    let nome = new URLSearchParams(window.location.search).get(
      "politicianName"
    );
    let uf = new URLSearchParams(window.location.search).get(
      "uf"
    );
      handleSearchRequest({ nome, uf });
  }, [handleSearchRequest]);

  let buildDataCards = (profiles) => {
    let cards = [];
    profiles.forEach((profile) => {
      cards.push(<ProfileCard profile={profile} key={profile.id}/>);
    });
    return cards;
  };

  let buildEmptyProfiles = () => {
    return(
      <Result status="404" title="Nenhum político foi encontrado. Tente novamente com outro nome."/>
    )
  }

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header>
        <PoliticianSearch
          handleSearchSubmit={(input) => props.handleSearchRequest(input)}
        />
      </Header>
      <MainContent>
        <div className={styles.container}>{props.profiles.length > 0 ? buildDataCards(props.profiles) : buildEmptyProfiles()}</div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Search;
