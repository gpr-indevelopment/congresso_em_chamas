import React from "react";
import styles from "./Landing.module.css";
import { Footer } from "../";
import LandingForm from "./LandingForm";
import FeaturesShowcase from "./FeaturesShowcase";

function Landing(props) {
  return (
    <div>
      <div className={styles.container}>
        <LandingForm />
        {/* <LandingVideo /> */}
      </div>
      <FeaturesShowcase />
      <Footer />
    </div>
  );
}

export default Landing;
