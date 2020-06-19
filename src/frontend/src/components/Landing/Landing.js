import React from "react";
import styles from "./Landing.module.css";
import * as CongressoComponents from "../index";

function Landing(props) {
  return (
    <div>
      <div className={styles.container}>
        <CongressoComponents.LandingForm handleSearchSubmit={props.handleSearchSubmit}/>
        <CongressoComponents.LandingVideo />
      </div>
      <CongressoComponents.FeaturesShowcase/>
      <CongressoComponents.Footer />
    </div>
  );
}

export default Landing;
