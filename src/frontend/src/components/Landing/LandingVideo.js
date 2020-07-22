import React from "react";
import styles from "./LandingVideo.module.css";
import ProtestVideo240 from "../../assets/protests_240.mp4";
import ProtestVideo144 from "../../assets/protests_144.mp4";

function LandingVideo() {
  return (
    <video autoPlay="autoplay" loop="loop" muted className={styles.video}>
      <source src={ProtestVideo240} type="video/mp4" />
      <source src={ProtestVideo144} type="video/mp4" />
      This video is not available.
    </video>
  );
}

export default LandingVideo;
