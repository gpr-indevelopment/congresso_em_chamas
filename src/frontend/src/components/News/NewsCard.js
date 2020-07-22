import React from "react";
import { Card } from "antd";
import styles from "./NewsCard.module.css";
const { Meta } = Card;

function NewsCard(props) {
  return (
    <Card
      cover={<img alt="" src={props.data.imageLink} />}
      className={styles.card}
      hoverable
      onClick={() => window.open(props.data.link, "_blank")}
    >
      <Meta title={props.data.title} description={props.data.sourceName + " - " + new Date(props.data.publishedDate).toLocaleDateString()} className={styles.meta}/>
      <p>{props.data.description}</p>
    </Card>
  );
}

export default NewsCard;
