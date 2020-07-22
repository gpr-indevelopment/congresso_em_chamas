import React from "react";
import LogoIcon from "../../assets/perfil-nobackground.png";
import styles from "./LandingForm.module.css";
import { PoliticianSearch } from "../";
import history from "../../history";

function LandingForm(props) {
  return (
    <div className={styles.container}>
      <img src={LogoIcon} className={styles.logo} />
      <h1 className={styles.title}>Congresso em chamas</h1>
      <PoliticianSearch
        handleSearchSubmit={(input) =>
          history.push(`/search?politicianName=${input}`)
        }
      />
    </div>
  );
}

export default LandingForm;
