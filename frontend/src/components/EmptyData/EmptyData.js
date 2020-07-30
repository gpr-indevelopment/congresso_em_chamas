import React from "react";
import styles from "./EmptyData.module.css";
import { Empty } from "antd";

function EmptyData() {
  return (
    <div className={styles.container}>
      <Empty description="Sem dados" />
    </div>
  );
}

export default EmptyData;
