import React from "react";
import LogoIcon from "../../assets/perfil-nobackground.png";
import styles from "./LandingForm.module.css";
import { Input } from "antd";

const {Search} = Input;

function LandingForm(props) {
  return (
    <div className={styles.container}>
      <img src={LogoIcon} className={styles.logo} />
      <h1 className={styles.title}>Congresso em chamas</h1>
      <Search
        placeholder="Insira o nome de um deputado"
        enterButton
        onSearch={(input) => props.handleSearchSubmit(input)}
      />
    </div>
  );
}

export default LandingForm;
