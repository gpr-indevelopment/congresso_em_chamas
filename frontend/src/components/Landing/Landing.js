import React from "react";
import styles from "./Landing.module.css";
import { Footer } from "../";
import LandingForm from "./LandingForm";
import LandingVideo from "./LandingVideo";
import FeaturesShowcase from "./FeaturesShowcase";
import NewFeatureModal from "./NewFeatureModal";

function Landing(props) {
  return (
    <div>
      <NewFeatureModal/>
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
