import React from "react";
import styles from "./MainContent.module.css";

function MainContent(props) {
  return <main className={styles.container}>
      {props.children}
  </main>;
}

export default MainContent;
