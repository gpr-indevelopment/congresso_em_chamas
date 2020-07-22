import React from "react";
import LogoIcon from "../../assets/perfil-nobackground.png";
import styles from "./HeaderLogo.module.css";

function HeaderLogo(props) {
  return (
    <div className={styles.container}>
      <img src={LogoIcon} className={styles.logo} />
      <h5 className={styles.title}>Congresso em chamas</h5>
    </div>
  );
}

export default HeaderLogo;
