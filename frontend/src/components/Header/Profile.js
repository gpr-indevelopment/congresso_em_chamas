import React from "react";
import { Avatar, Skeleton, Space } from "antd";
import styles from "./Profile.module.css";

export default function Profile(props) {
  return (
    <div>
      {props.loading ? (
        <div className={styles.container}>
          <Space size="middle">
            <Skeleton.Avatar active={true} shape="circle" size="large"/>
            <Skeleton.Input active={true} style={{ width: 140 }} size="small"/>
          </Space>
        </div>
      ) : (
        <div className={styles.container}>
          <Avatar
            size={54}
            src={props.profile.picture}
            className={styles.image}
          />
          <div>
            <h4 className={styles.name}>{props.profile.name}</h4>
            <h5 className={styles.party}>{props.profile.party}</h5>
          </div>
        </div>
      )}
    </div>
  );
}
