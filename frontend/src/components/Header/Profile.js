import React from "react";
import { Avatar, Skeleton } from "antd";
import styles from "./Profile.module.css";
import { UserOutlined } from "@ant-design/icons";

export default function Profile(props) {
  return (
    <div className={styles.container}>
      <Skeleton avatar paragraph={{ rows: 2, width: [100, 55] }} title={false} loading={props.loading}>
        <Avatar
          size={54}
          src={props.profile.picture}
          className={styles.image}
          icon={<UserOutlined />}
        />
        <div>
          <h4 className={styles.name}>{props.profile.name}</h4>
          <h5 className={styles.party}>{props.profile.party}</h5>
        </div>
      </Skeleton>
    </div>
  );
}
