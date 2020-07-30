import React from "react";
import { Card } from "antd";
import styles from "./NewsCard.module.css";

function NewsCard(props) {
  return (
    <Card
      cover={<img alt="" src={props.data.imageLink} />}
      className={styles.card}
      hoverable
      onClick={() => window.open(props.data.link, "_blank")}
    >
      <h4 className={styles.title}>{props.data.title}</h4>
      <small>
        {props.data.sourceName +
          " - " +
          new Date(props.data.publishedDate).toLocaleDateString()}
      </small>
      <p className={styles.description}>{props.data.description}</p>
    </Card>
  );
}

export default NewsCard;
