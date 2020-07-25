import React, { useEffect } from "react";
import { Spin } from "antd";
import { Header, Footer, MainContent } from "../";
import PropositionsCard from "./PropositionsCard";
import styles from "./Propositions.module.css";

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
        <div className={styles.container}>{buildPropositionCards(props.propositions)}</div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Propositions;
