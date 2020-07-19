import React, { useEffect } from "react";
import {
  Header,
  MainContent,
  ExpensesGraph,
  ExpensesDetailsSection,
  Footer,
} from "../index";
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
      <Header />
      <MainContent>
        <div className={styles.container}>
          <div className={styles.chart}>
            <ExpensesGraph
              data={props.expenseData}
              onDataClick={(event, array) =>
                array.length > 0 && props.handleDetailsClick(array[0]._index)
              }
            />
          </div>
          <ExpensesDetailsSection
            data={props.detailsData}
          />
        </div>
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Expenses;
