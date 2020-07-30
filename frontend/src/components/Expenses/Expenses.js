import React, { useEffect } from "react";
import { Header, MainContent, Footer } from "../";
import ExpensesGraph from "./ExpensesGraph";
import ExpensesDetailsSection from "./ExpensesDetailsSection";
import styles from "./Expenses.module.css";
import { Spin, Radio, Space } from "antd";
import EmptyData from "../EmptyData";

function Expenses(props) {
  const { handleExpensesRequest, activeRadio } = props;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );
  useEffect(() => {
    switch (activeRadio) {
      case 2:
        handleExpensesRequest(politicianId, {
          lastMonths: 6
        });
        break;
      case 3:
        handleExpensesRequest(politicianId);
        break;
      default:
        handleExpensesRequest(politicianId, {
          years: new Date().getFullYear()
        });
        break;
    }
  }, [handleExpensesRequest, politicianId, activeRadio]);

  return (
    <Spin tip="Carregando..." spinning={props.loading}>
      <Header politicianId={politicianId} />
      <MainContent>
        {props.expenseData.monthlyExpenses &&
        props.expenseData.monthlyExpenses.length > 0 ? (
          <div className={styles.container}>
            <Space direction="vertical" className={styles.left}>
              <div className={styles.radio}>
                <Radio.Group
                  defaultValue={props.activeRadio}
                  buttonStyle="solid"
                  onChange={(e) => props.handleRadioChanged(e.target.value)}
                >
                  <Radio.Button value={1}>Esse ano</Radio.Button>
                  <Radio.Button value={2}>Últimos 6 meses</Radio.Button>
                  <Radio.Button value={3}>Legislatura</Radio.Button>
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
