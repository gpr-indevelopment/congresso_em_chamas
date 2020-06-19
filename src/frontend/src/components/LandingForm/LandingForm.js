import React from "react";
import LogoIcon from "../../assets/perfil-nobackground.png";
import styles from "./LandingForm.module.css";
import * as Containers from "../../redux/containers/index";

function LandingForm(props) {
  return (
    <div className={styles.container}>
      <img src={LogoIcon} className={styles.logo} />
      <h1 className={styles.title}>Congresso em chamas</h1>
      <Containers.SearchInputContainer />
    </div>
  );
}

export default LandingForm;
