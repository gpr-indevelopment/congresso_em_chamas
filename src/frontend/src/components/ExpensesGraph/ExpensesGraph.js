import { Line } from "react-chartjs-2";
import React from "react";

function ExpensesGraph(props) {
  return (
    <Line
      data={{
        labels: props.data.dates,
        datasets: [
          {
            fill: false,
            label: "Despesas",
            backgroundColor: "#0078ff",
            borderColor: "#0078ff",
            data: props.data.values,
          },
        ],
      }}
      options={{
        responsive: true,
        maintainAspectRatio: false,
        onClick: props.onDataClick,
        scales: {
          yAxes: [
            {
              ticks: {
                callback: function (value) {
                  return "R$ " + value;
                },
              },
            },
          ],
        },
      }}
    ></Line>
  );
}

export default ExpensesGraph;
