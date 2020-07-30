import React, { useEffect } from "react";
import { Header, MainContent, Footer } from "../";
import ExpensesGraph from "./ExpensesGraph";
import ExpensesDetailsSection from "./ExpensesDetailsSection";
import styles from "./Expenses.module.css";
import { Spin } from "antd";
import EmptyData from "../EmptyData";

function Expenses(props) {
  const { handleExpensesRequest } = props;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  useEffect(() => {
    handleExpensesRequest(politicianId);
  }, [handleExpensesRequest, politicianId]);

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header politicianId={politicianId} />
      <MainContent>
        {props.expenseData.monthlyExpenses &&
        props.expenseData.monthlyExpenses.length > 0 ? (
          <div className={styles.container}>
            <div className={styles.chart}>
              <ExpensesGraph
                data={props.expenseData}
                onDataClick={(event, array) =>
                  array.length > 0 && props.handleDetailsClick(array[0]._index)
                }
              />
            </div>
            <ExpensesDetailsSection data={props.detailsData} />
          </div>
        ) : (
          <EmptyData/>
        )}
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Expenses;
