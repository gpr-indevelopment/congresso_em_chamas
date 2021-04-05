import React from "react";
import styles from "./ExpensesDetailsSection.module.css";
import ExpenseDetailsCard from "./ExpenseDetailsCard";
import JarbasSuspicionsCounter from "./JarbasSuspicionsCounter";

function ExpensesDetails(props) {
  let detailsList = [];
  props.data.forEach((dataElement) => {
    detailsList.push(
      <ExpenseDetailsCard
        onFindSuspicion={props.incrementSuspicions}
        data={dataElement}
        key={dataElement.documentNumber + "-" + dataElement.documentCode}
      />
    );
  });

  return (
    <div className={styles.container}>
      <div className={styles.counterContainer}>
        <JarbasSuspicionsCounter count={props.suspicionsCount} />
      </div>
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
