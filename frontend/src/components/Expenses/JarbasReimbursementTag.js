import React from "react";
import { Spin, Popover, List, Typography, Button } from "antd";
import styles from "./JarbasReimbursementTag.module.css";
import { config } from "../../constants";

function JarbasReimbursementTag(props) {
  const { Text } = Typography;
  const politicianId = new URLSearchParams(window.location.search).get(
    "politician"
  );

  let openWindow = (politicianName) => {
    if (politicianName) {
      window.open(
        `https://jarbas.serenata.ai/dashboard/chamber_of_deputies/reimbursement/?q=${politicianName}&is_suspicions=yes`,
        "_blank"
      );
    } else {
      window.open(
        "https://jarbas.serenata.ai/dashboard/chamber_of_deputies/reimbursement/?is_suspicions=yes",
        "_blank"
      );
    }
  };

  let handleDetailsClick = () => {
    fetch(`${config.url}/politicians/${politicianId}`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          openWindow();
        }
      })
      .then((body) => openWindow(body.name))
      .catch((error) => openWindow());
  };
  let handleSuspicions = (suspicions) => {
    if (suspicions) {
      return (
        <Popover
          title="Suspeitas"
          content={() => {
            let listData = [];
            for (var key in suspicions) {
              if (suspicions[key].value) {
                listData.push(suspicions[key]);
              }
            }
            return (
              <List
                size="small"
                dataSource={listData}
                footer={
                  <div className={styles.footerContainer}>
                    <Text type="secondary">Powered by Jarbas</Text>
                    <Button
                      type="primary"
                      size="small"
                      onClick={handleDetailsClick}
                      danger
                    >
                      Detalhes
                    </Button>
                  </div>
                }
                renderItem={(item) => {
                  return <List.Item>{item.description}</List.Item>;
                }}
              />
            );
          }}
        >
          <Button type="primary" danger>Suspeito</Button>
        </Popover>
      );
    } else {
      return null;
    }
  };
  return (
    <Spin spinning={props.isLoading}>
      <div>
        {props.reimbursement
          ? handleSuspicions(props.reimbursement.suspicions)
          : null}
      </div>
    </Spin>
  );
}

export default JarbasReimbursementTag;
