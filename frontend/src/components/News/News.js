import React, { useEffect } from "react";
import { Spin } from "antd";
import { Header, MainContent, Footer } from "../";
import NewsCard from "./NewsCard";
import styles from "./News.module.css";
import EmptyData from "../EmptyData";

function News(props) {
  let { handleExpensesRequest } = props;
  let politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  useEffect(() => {
    handleExpensesRequest(politicianId);
  }, [handleExpensesRequest, politicianId]);

  let buildNewsCards = (news) => {
    let cards = [];
    news.forEach((newsElement) => {
      cards.push(<NewsCard data={newsElement} key={newsElement.link}/>);
    });
    return cards;
  };

  return (
    <Spin spinning={props.loading} tip="Carregando...">
      <Header politicianId={politicianId} />
      <MainContent>
        {props.data.length > 0 ? (
          <div className={styles.container}>{buildNewsCards(props.data)}</div>
        ) : (
          <EmptyData/>
        )}
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default News;
