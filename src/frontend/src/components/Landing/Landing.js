import React from "react";
import styles from "./Landing.module.css";
import { LandingForm, LandingVideo, FeaturesShowcase, Footer } from "../index";

function Landing(props) {
  return (
    <div>
      <div className={styles.container}>
        <LandingForm />
        <LandingVideo />
      </div>
      <FeaturesShowcase />
      <Footer />
    </div>
  );
}

export default Landing;
