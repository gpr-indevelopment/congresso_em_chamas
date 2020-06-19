import React from "react";
import { Input } from "antd";

function SearchInput(props) {
  let { Search } = Input;
  return (
    <Search
      placeholder="Insira o nome de um deputado"
      enterButton
      onSearch={(input) => props.handleSearchSubmit(input)}
    />
  );
}

export default SearchInput;
