import React, { useEffect } from "react";
import * as CongressoComponents from "../index";
import styles from "./Expenses.module.css";
import { Spin } from "antd";

function Expenses(props) {
  useEffect(() => {
    let politician = new URLSearchParams(window.location.search).get(
      "politician"
    );
    props.handleExpensesRequest(politician);
  }, []);

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <CongressoComponents.Header />
      <CongressoComponents.MainContent>
        <div className={styles.container}>
          <div className={styles.chart}>
            <CongressoComponents.ExpensesGraph
              data={props.expenseData}
              onDataClick={(event, array) => array.length > 0 && props.handleDetailsClick(array[0]._index)}
            />
          </div>
          <CongressoComponents.ExpensesDetailsSection data={props.detailsData}/>
        </div>
      </CongressoComponents.MainContent>
      <CongressoComponents.Footer />
    </Spin>
  );
}

export default Expenses;
