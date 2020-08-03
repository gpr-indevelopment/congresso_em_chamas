import React from "react";
import { Radio } from "antd";
import styles from "./ExpensesRadio.module.css";

function ExpensesRadio(props) {
  return (
    <div className={styles.container}>
      <Radio.Group
        defaultValue={props.active}
        buttonStyle="solid"
        onChange={(e) => props.onChange(e.target.value)}
        className={styles.radio}
      >
        <Radio.Button value={1}>Esse ano</Radio.Button>
        <Radio.Button value={2}>Últimos 6 meses</Radio.Button>
        <Radio.Button value={3}>Legislatura</Radio.Button>
      </Radio.Group>
    </div>
  );
}

export default ExpensesRadio;
