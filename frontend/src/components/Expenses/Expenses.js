import React, { useEffect } from "react";
import { Header, MainContent, Footer } from "../";
import ExpensesGraph from "./ExpensesGraph";
import ExpensesDetailsSection from "./ExpensesDetailsSection";
import styles from "./Expenses.module.css";
import { Spin, Space } from "antd";
import EmptyData from "../EmptyData";
import ExpensesRadio from "./ExpensesRadio";

function Expenses(props) {
  const {
    handleExpensesRequest,
    activeRadio,
    resetJarbasReimbursement,
  } = props;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  useEffect(() => {
    resetJarbasReimbursement();
    switch (activeRadio) {
      case 2:
        handleExpensesRequest(politicianId, {
          lastMonths: 6,
        });
        break;
      case 3:
        handleExpensesRequest(politicianId);
        break;
      default:
        handleExpensesRequest(politicianId, {
          years: new Date().getFullYear(),
        });
        break;
    }
  }, [
    handleExpensesRequest,
    politicianId,
    activeRadio,
    resetJarbasReimbursement,
  ]);

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header politicianId={politicianId} />
      <MainContent>
        {props.expenseData.monthlyExpenses &&
        props.expenseData.monthlyExpenses.length > 0 ? (
          <div className={styles.container}>
            <Space direction="vertical" className={styles.left} size="middle">
              <ExpensesRadio
                active={props.activeRadio}
                onChange={props.handleRadioChanged}
              />
              <div className={styles.chart}>
                <ExpensesGraph
                  data={props.expenseData}
                  onDataClick={(event, array) => {
                    if(array.length > 0) {
                      resetJarbasReimbursement();
                      return props.handleDetailsClick(array[0]._index);
                    }
                  }}
                />
              </div>
            </Space>
            <ExpensesDetailsSection
              data={props.detailsData}
              onCardLoad={props.handleJarbasReimbursementRequest}
              jarbasReimbursements={props.jarbasReimbursements}
            />
          </div>
        ) : (
          <EmptyData />
        )}
      </MainContent>
      <Footer />
    </Spin>
  );
}

export default Expenses;
