import React from "react";
import { Layout } from "antd";
import * as CongressoComponents from "../index";
const { Header, Content, Footer } = Layout;

function Expenses() {
  return (
    <Layout>
      <Header>
        <CongressoComponents.Header />
      </Header>
      <Content>
        <div>Expenses</div>
      </Content>
      <Footer>
        <CongressoComponents.Footer />
      </Footer>
    </Layout>
  );
}

export default Expenses;
