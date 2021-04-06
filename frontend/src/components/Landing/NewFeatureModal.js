import React, { useState } from "react";
import { Modal, Button } from "antd";
import SuspicionCardDetails from "../../assets/Suspicion-card-details.png";
import SuspicionCard from "../../assets/Suspicion-card.png";
import styles from "./NewFeatureModal.module.css";

function NewFeatureModal(props) {
  const [isModalVisible, setIsModalVisible] = useState(true);
  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };
  return (
    <Modal
      visible={isModalVisible}
      centered
      closable={true}
      onCancel={handleCancel}
      onOk={handleOk}
      footer={null}
      title="Atualização 05/04/2021"
    >
      <div>
        <div className={styles.imagesContainer}>
          <img src={SuspicionCard} alt="Not available" />
          <img src={SuspicionCardDetails} alt="Not available" />
        </div>
        <p className={styles.paragraph}>
          Agora, as despesas podem ser marcadas como{" "}
          <Button type="primary" size="small" danger>
            Suspeitas
          </Button>
          . Dentre as classificações de suspeitas podemos citar: <span className={styles.bold}>Preços de
          refeição incomuns, despesas em cidades diferentes no mesmo dia, gastos
          com campanha eleitoral, entre outras</span>. Clique no botão vermelho para
          visualizar as suspeitas associadas a despesa. Ainda, clicar no botão
          {" "}<Button type="primary" size="small" danger>
            Detalhes
          </Button>{" "}
          leva ao <a href="https://jarbas.serenata.ai/dashboard/chamber_of_deputies/reimbursement/" target="_blank" rel="noopener noreferrer">dashboard do Jarbas</a>, que dispõe de mais informações da
          despesa, além de listar outras suspeitas do mesmo deputado.
        </p>
      </div>
    </Modal>
  );
}

export default NewFeatureModal;
