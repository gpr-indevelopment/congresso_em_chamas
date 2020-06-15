import React from "react";
import { Layout } from "antd";
import * as CongressoComponents from "../index";
const { Header, Content, Footer } = Layout;

function News() {
  return (
    <Layout>
      <Header>
        <CongressoComponents.Header />
      </Header>
      <Content>
        <div>News</div>
      </Content>
      <Footer>
        <CongressoComponents.Footer />
      </Footer>
    </Layout>
  );
}

export default News;
