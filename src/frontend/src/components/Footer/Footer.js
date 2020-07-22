import React from "react";
import styles from "./Footer.module.css";
import GitHubIcon from "../../assets/github-icon.png";

function Footer() {
  return (
    <footer className={styles.container}>
      <div className={styles.left}>
        <div className={styles.title}>Sobre Congresso em Chamas</div>
        <p className={styles.text}>
          Congresso em Chamas é uma aplicação web que entrega ferramentas para o
          acompanhamento do trabalho dos deputados federais atualmente em
          mandato. Visamos contribuir com a transparência da atividade do
          legislativo.
        </p>
      </div>
      <div className={styles.right}>
        <div className={styles.title}>Contribua!</div>
        <div>
          <p className={styles.text}>
            Esse é um projeto de código aberto, sem fins lucrativos, que depende
            de doações para manter sua infraestrutura. Agradecemos contribuições
            financeiras ou de novas features.
          </p>
          <div className={styles.links}>
            <form
              action="https://www.paypal.com/cgi-bin/webscr"
              method="post"
              target="_top"
            >
              <input type="hidden" name="cmd" value="_donations" />
              <input type="hidden" name="business" value="DVUECCKXF39N6" />
              <input
                type="hidden"
                name="item_name"
                value="Arrecadar fundos para manter a infraestrutura do site Congresso Em Chamas."
              />
              <input type="hidden" name="currency_code" value="BRL" />
              <input
                type="image"
                src="https://www.paypalobjects.com/pt_BR/BR/i/btn/btn_donateCC_LG.gif"
                border="0"
                name="submit"
                title="PayPal - The safer, easier way to pay online!"
                alt="Faça doações com o botão do PayPal"
              />
              <img
                alt="Not available"
                border="0"
                src="https://www.paypal.com/pt_BR/i/scr/pixel.gif"
                width="1"
                height="1"
              />
            </form>
            <a
              href="https://github.com/gpr-indevelopment/congresso_em_chamas"
              target="_blank"
              rel="noopener noreferrer"
            >
              <img src={GitHubIcon} height={30} width={30} alt="Not available"/>
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
