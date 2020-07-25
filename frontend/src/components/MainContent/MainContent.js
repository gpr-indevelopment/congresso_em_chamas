import React from "react";
import styles from "./MainContent.module.css";

function MainContent(props) {
  return (
    <main className={styles.main}>
      {props.children}
    </main>
  );
}

export default MainContent;
