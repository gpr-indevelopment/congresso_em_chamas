import React, { useEffect } from "react";
import { Spin } from "antd";
import { Header, Footer, MainContent } from "../";
import PropositionsCard from "./PropositionsCard";
import styles from "./Propositions.module.css";
import EmptyData from "../EmptyData";

function Propositions(props) {
  const { handlePropositionsRequest } = props;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );

  useEffect(() => {
    handlePropositionsRequest(politicianId);
  }, [handlePropositionsRequest, politicianId]);

  let buildPropositionCards = (propositions) => {
    return propositions.map((proposition) => (
      <PropositionsCard proposition={proposition} />
    ));
  };

  return (
    <Spin spinning={props.loading} tip="Carregando...">
      <Header politicianId={politicianId} />
      <MainContent>
        {(props.propositions.length > 0) ? (
          <div className={styles.container}>
            {buildPropositionCards(props.propositions)}
          </div>
        ) : (
          <EmptyData/>
        )}
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Propositions;
