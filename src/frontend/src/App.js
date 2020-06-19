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
          <Containers.LandingContainer />
        </Route>
        <Route exact path="/search">
          <CongressoComponents.Search />
        </Route>
        <Route exact path="/news">
          <CongressoComponents.News />
        </Route>
        <Route exact path="/propositions">
          <CongressoComponents.Propositions />
        </Route>
        <Route exact path="/expenses">
          <CongressoComponents.Expenses />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
