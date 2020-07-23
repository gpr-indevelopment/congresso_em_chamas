import React, { useEffect } from "react";
import { Spin } from "antd";
import { Header, Footer, MainContent } from "../";

function Propositions(props) {
  const { handlePropositionsRequest } = props;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  useEffect(() => {
    handlePropositionsRequest(politicianId);
  }, [handlePropositionsRequest, politicianId]);

  return (
    <Spin spinning={false}>
      <Header politicianId={politicianId}/>
      <MainContent>
        <div>Propositions</div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Propositions;
