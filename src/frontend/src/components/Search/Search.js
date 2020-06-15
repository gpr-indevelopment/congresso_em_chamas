import React from "react";
import { Layout } from "antd";
import * as CongressoComponents from "../index";
const { Content, Footer } = Layout;

function Search() {
  return (
    <Layout>
      <Content>
        <div>Search</div>
      </Content>
      <Footer>
        <CongressoComponents.Footer />
      </Footer>
    </Layout>
  );
}

export default Search;
