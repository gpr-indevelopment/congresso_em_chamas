import React from "react";
import { Router, Switch, Route } from "react-router-dom";
import * as CongressoComponents from "./components/index";
import * as Containers from "./redux/containers/index";
import history from './history';

function App() {
  return (
    <Router history={history}>
      <Switch>
        <Route exact path="/">
          <CongressoComponents.Landing />
        </Route>
        <Route path="/search">
          <Containers.SearchContainer />
        </Route>
        <Route exact path="/news">
          <Containers.NewsContainer />
        </Route>
        <Route exact path="/propositions">
          <CongressoComponents.Propositions />
        </Route>
        <Route exact path="/expenses">
          <Containers.ExpensesContainer />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
