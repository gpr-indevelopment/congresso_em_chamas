import React from "react";
import styles from "./ExpensesDetailsSection.module.css";
import * as CongressoComponents from "../index";

function ExpensesDetails(props) {

  let detailsList = [];
  props.data.forEach(dataElement => {
    detailsList.push(<CongressoComponents.ExpenseDetailsCard data={dataElement} key={dataElement.documentNumber}/>)
  });
  return (
    <div className={styles.container}>
      {props.data.length > 0 ? (
        detailsList
      ) : (
        <p className={styles.click}>
          Clique em uma despesa no gráfico para visualizar os detalhes.
        </p>
      )}
    </div>
  );
}

export default ExpensesDetails;
