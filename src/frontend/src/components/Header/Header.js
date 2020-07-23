import React, { useEffect } from "react";
import Profile from "./Profile";
import styles from "./Header.module.css";
import HeaderLogo from "./HeaderLogo";
import { Link } from "react-router-dom";

export default function Header(props) {
  let { handleProfileLoading, politicianId, loading } = props;
  useEffect(() => {
    politicianId && handleProfileLoading(politicianId);
  }, [handleProfileLoading, politicianId]);
  return (
    <div className={styles.container}>
      <Link to="/">
        <HeaderLogo />
      </Link>
      <div>{props.children}</div>
      {politicianId && (
        <Profile profile={props.profile} loading={politicianId && loading} />
      )}
    </div>
  );
}
