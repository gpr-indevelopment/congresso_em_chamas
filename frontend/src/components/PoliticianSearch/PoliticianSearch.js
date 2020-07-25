import React from "react";
import { Input } from "antd";

function PoliticianSearch(props) {
  let { Search } = Input;
  return (
    <Search
      placeholder="Deputado federal"
      enterButton
      onSearch={(input) => props.handleSearchSubmit(input)}
    />
  );
}

export default PoliticianSearch;
