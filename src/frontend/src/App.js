import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import * as CongressoComponents from "./components/index";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <CongressoComponents.Landing />
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
