import React, { useEffect } from "react";
import { Card, Tooltip } from "antd";
import styles from "./ExpenseDetailsCard.module.css";
import { WarningOutlined } from "@ant-design/icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMoneyBillWave } from "@fortawesome/free-solid-svg-icons";
import { faCalendarAlt, faFileAlt } from "@fortawesome/free-regular-svg-icons";
import JarbasReimbursementTag from "./JarbasReimbursementTag";

function ExpenseDetails(props) {
  let { documentCode } = props.data;
  let { onLoad } = props;
  useEffect(() => {
    if(documentCode && documentCode > 0) {
      onLoad(documentCode);
    }
  }, [documentCode, onLoad]);

  let resolveReimbursement = () => {
    let documentCode = props.data.documentCode;
    if(documentCode && documentCode > 0 && props.jarbasReimbursements.get(documentCode)) {
      return props.jarbasReimbursements.get(documentCode).reimbursement
    } else {
      return null;
    }
  }

  return (
    <Card
      title={
        <Tooltip title={props.data.provider}>{props.data.provider}</Tooltip>
      }
      className={styles.card}
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
      <div className={styles.detailsContainer}>
        <div>
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
        </div>
        <div className={styles.jarbasReimbursementContainer}>
          <JarbasReimbursementTag
            isLoading={
              props.jarbasReimbursements.get(props.data.documentCode)
                ? props.jarbasReimbursements.get(props.data.documentCode)
                    .loading
                : true
            }
            reimbursement={resolveReimbursement()}
          />
        </div>
      </div>
    </Card>
  );
}

export default ExpenseDetails;
