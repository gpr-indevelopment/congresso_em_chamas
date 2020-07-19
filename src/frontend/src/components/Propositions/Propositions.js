import React from "react";
import { Spin } from "antd";
import { Header, Footer, MainContent } from "../index";

function Propositions() {
  return (
    <Spin spinning={false}>
      <Header />
      <MainContent>
        <div>Propositions</div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Propositions;
