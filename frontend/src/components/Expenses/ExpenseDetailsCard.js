import React, { useEffect, useState } from "react";
import { Card, Tooltip } from "antd";
import styles from "./ExpenseDetailsCard.module.css";
import { WarningOutlined } from "@ant-design/icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMoneyBillWave } from "@fortawesome/free-solid-svg-icons";
import { faCalendarAlt, faFileAlt } from "@fortawesome/free-regular-svg-icons";
import JarbasReimbursementTag from "./JarbasReimbursementTag";
import { config } from "../../constants";

function ExpenseDetails(props) {
  const [reimbursement, setReimbursement] = useState({});
  const [loading, setLoading] = useState(false);

  let { documentCode } = props.data;
  useEffect(() => {
    if (documentCode && documentCode > 0) {
      let url = `${config.url}/expenses/${documentCode}`;
      setLoading(true);
      fetch(url)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            setLoading(false);
            setReimbursement(null);
          }
        })
        .then((body) => {
          setLoading(false);
          setReimbursement(body);
          if (body.suspicions) {
            props.onFindSuspicion(body.suspicions);
          }
        })
        .catch((error) => {
          setLoading(false);
          setReimbursement(null);
        });
      async function loadingTimeout() {
        await new Promise((resolve) => setTimeout(resolve, 60000));
        setLoading(false);
      }
      loadingTimeout();
    }
    // eslint-disable-next-line
  }, [documentCode]);

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
            isLoading={loading}
            reimbursement={reimbursement}
          />
        </div>
      </div>
    </Card>
  );
}

export default ExpenseDetails;
