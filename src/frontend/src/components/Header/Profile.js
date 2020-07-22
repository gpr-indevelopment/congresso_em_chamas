import React from "react";
import { Avatar } from "antd";
import styles from "./Profile.module.css";

export default function Profile(props) {
  return (
    <div className={styles.container}>
      <Avatar size={54} src={props.profile.picture} className={styles.image}/>
      <div className={styles.description}>
        <h4>{props.profile.name}</h4>
        <h5>{props.profile.party}</h5>
      </div>
    </div>
  );
}
