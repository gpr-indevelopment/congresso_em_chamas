import React, { useEffect, useState } from "react";
import Profile from "./Profile";
import styles from "./Header.module.css";
import { Affix, Button } from "antd";
import HeaderDrawer from "./HeaderDrawer";
import { MenuOutlined } from "@ant-design/icons";
import HeaderLogo from "./HeaderLogo";
import { Link } from "react-router-dom";

export default function Header(props) {
  const [drawerVisible, setDrawerVisible] = useState(false);

  let { handleProfileLoading, politicianId, loading } = props;
  useEffect(() => {
    politicianId && handleProfileLoading(politicianId);
  }, [handleProfileLoading, politicianId]);
  return (
    <div className={styles.container}>
      {politicianId ? (
        <Affix offsetTop={25}>
          <Button
            type="primary"
            icon={<MenuOutlined />}
            onClick={() => setDrawerVisible(!drawerVisible)}
          />
        </Affix>
      ) : (
        <Link to="/">
          <HeaderLogo />
        </Link>
      )}

      <div>{props.children}</div>
      {politicianId && (
        <Profile profile={props.profile} loading={politicianId && loading} />
      )}
      <HeaderDrawer
        visible={drawerVisible}
        onClose={() => setDrawerVisible(false)}
      />
    </div>
  );
}
