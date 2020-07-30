import React, { useEffect } from "react";
import { Header, MainContent, Footer } from "../";
import ExpensesGraph from "./ExpensesGraph";
import ExpensesDetailsSection from "./ExpensesDetailsSection";
import styles from "./Expenses.module.css";
import { Spin, Radio, Space } from "antd";
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
            <Space direction="vertical" className={styles.left}>
              <div className={styles.radio}>
                <Radio.Group defaultValue="1" buttonStyle="solid">
                  <Radio.Button value="1">Esse ano</Radio.Button>
                  <Radio.Button value="2">Últimos 6 meses</Radio.Button>
                  <Radio.Button value="3">Legislatura</Radio.Button>
                </Radio.Group>
              </div>
              <div className={styles.chart}>
                <ExpensesGraph
                  data={props.expenseData}
                  onDataClick={(event, array) =>
                    array.length > 0 &&
                    props.handleDetailsClick(array[0]._index)
                  }
                />
              </div>
            </Space>
            <ExpensesDetailsSection data={props.detailsData} />
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
