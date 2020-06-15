import React from "react";
import styles from "./FeaturesShowcase.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFileAlt } from "@fortawesome/free-regular-svg-icons";
import { faNewspaper, faDollarSign } from "@fortawesome/free-solid-svg-icons";

function FeaturesShowcase() {
  let iconColor = "var(--theme-bg-color)";
  return (
    <div className={styles.container}>
      <div className={styles.card}>
        <div className={styles.icon}>
          <FontAwesomeIcon icon={faDollarSign} size={"7x"} color={iconColor} />
        </div>
        <h3 className={styles.title}>Despesas</h3>
        <p className={styles.description}>
          Acompanhe todas as despesas lançadas na cota parlamentar dos deputados
          federais.
        </p>
      </div>
      <div className={styles.card}>
        <div className={styles.icon}>
          <FontAwesomeIcon icon={faNewspaper} size={"7x"} color={iconColor} />
        </div>
        <h3 className={styles.title}>Notícias</h3>
        <p className={styles.description}>
          Os últimos artigos e notícias dos deputados federais com a curadoria
          do Google News API.
        </p>
      </div>
      <div className={styles.card}>
        <div className={styles.icon}>
          <FontAwesomeIcon icon={faFileAlt} size={"7x"} color={iconColor} />
        </div>
        <h3 className={styles.title}>Proposições</h3>
        <p className={styles.description}>
          Projetos de lei, resoluções, medidas provisórias e mais. Acompanhe a
          atividade dos deputados federais na câmara.
        </p>
      </div>
    </div>
  );
}

export default FeaturesShowcase;
