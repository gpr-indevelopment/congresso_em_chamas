import React from "react";
import styles from "./Header.module.css";
import { HeaderLogo } from "../index";
import { Link } from "react-router-dom";

function Header(props) {
  return (
    <div className={styles.container}>
      <Link to="/">
        <HeaderLogo />
      </Link>
      <div>{props.children}</div>
    </div>
  );
}

export default Header;
