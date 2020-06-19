import React from "react";
import * as CongressoComponents from "../index";

function Search(props) {
  return (
    <div>
      <CongressoComponents.Header>
        <CongressoComponents.SearchInput handleSearchSubmit={(input) => props.handleSearchSubmit(input)}/>
      </CongressoComponents.Header>
      <CongressoComponents.MainContent>
        {props.politicianName ? props.politicianName : "nao tem politico"}
      </CongressoComponents.MainContent>
      <CongressoComponents.Footer />
    </div>
  );
}

export default Search;
