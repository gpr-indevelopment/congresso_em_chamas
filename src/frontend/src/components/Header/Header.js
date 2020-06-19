import React from "react";
import styles from "./Header.module.css";
import * as CongressoComponents from "../index";

function Header(props) {
  return (
    <div className={styles.container}>
      <div>
        <CongressoComponents.HeaderLogo />
      </div>
      <div>{props.children}</div>
    </div>
  );
}

export default Header;
