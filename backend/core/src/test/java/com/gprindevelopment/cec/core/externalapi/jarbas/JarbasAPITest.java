package com.gprindevelopment.cec.core.externalapi.jarbas;

import com.gprindevelopment.cec.core.externalapi.jarbas.model.JarbasReimbursement;
import com.gprindevelopment.cec.core.restclient.RestTemplateConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {JarbasConfig.class, JarbasAPI.class, RestTemplateConfig.class})
public class JarbasAPITest {

    @Autowired
    private JarbasAPI jarbasAPI;

    @Test
    public void requestReimbursement_validDocumentCode_returnsSuspicions() {
        Long documentCode = 7303763L;
        Optional<JarbasReimbursement> reimbursementOpt = jarbasAPI.requestReimbursement(documentCode);
        assertThat(reimbursementOpt.isPresent());
        JarbasReimbursement reimbursement = reimbursementOpt.get();
        assertThat(reimbursement.getDocumentCode()).isEqualTo(documentCode);
        assertThat(reimbursement.getSuspicions().getMealPriceOutlier().getValue()).isTrue();
    }

    @Test
    public void requestReimbursement_nonexistentDocumentCode_returnsNothing() {
        Long documentCode = 7123961L;
        Optional<JarbasReimbursement> reimbursementOpt = jarbasAPI.requestReimbursement(documentCode);
        assertThat(reimbursementOpt.isEmpty());
    }

}