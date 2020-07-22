import React from "react";
import { Router, Switch, Route } from "react-router-dom";
import { Landing, Propositions, SearchContainer, NewsContainer, ExpensesContainer } from "../";
import history from "../../history";

function App() {
  return (
    <Router history={history}>
      <Switch>
        <Route exact path="/">
          <Landing />
        </Route>
        <Route path="/search">
          <SearchContainer />
        </Route>
        <Route exact path="/news">
          <NewsContainer />
        </Route>
        <Route exact path="/propositions">
          <Propositions />
        </Route>
        <Route exact path="/expenses">
          <ExpensesContainer />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
