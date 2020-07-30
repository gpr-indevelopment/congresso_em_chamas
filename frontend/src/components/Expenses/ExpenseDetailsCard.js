import React from "react";
import { Card, Tooltip } from "antd";
import styles from "./ExpenseDetailsCard.module.css";
import { WarningOutlined } from "@ant-design/icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMoneyBillWave } from "@fortawesome/free-solid-svg-icons";
import { faCalendarAlt, faFileAlt } from "@fortawesome/free-regular-svg-icons";

function ExpenseDetails(props) {
  return (
    <Card
      title={
        <Tooltip title={props.data.provider}>{props.data.provider}</Tooltip>
      }
      className={styles.card}
      hoverable={props.data.documentUrl}
      onClick={
        props.data.documentUrl &&
        (() => window.open(props.data.documentUrl, "_blank"))
      }
      actions={[
        props.data.documentUrl ? (
          <Tooltip title="Comprovante">
            <a
              href={props.data.documentUrl}
              target="_blank"
              rel="noopener noreferrer"
            >
              <FontAwesomeIcon icon={faFileAlt} key="document" size={"lg"} />
            </a>
          </Tooltip>
        ) : (
          <Tooltip title="Sem comprovante">
            <WarningOutlined />
          </Tooltip>
        ),
      ]}
    >
      <p>{props.data.type}</p>
      <div className={styles.money}>
        <FontAwesomeIcon
          icon={faMoneyBillWave}
          key="value"
          size={"lg"}
          className={styles.moneyIcon}
        />
        {"R$ " + props.data.value.toLocaleString()}
      </div>
      <div className={styles.date}>
        <FontAwesomeIcon
          icon={faCalendarAlt}
          key="date"
          size={"lg"}
          className={styles.icon}
        />
        {props.data.yearMonth.split("-")[1] +
          "/" +
          props.data.yearMonth.split("-")[0]}
      </div>
    </Card>
  );
}

export default ExpenseDetails;
