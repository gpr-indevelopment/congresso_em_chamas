package com.gprindevelopment.cec.core.externalapi.jarbas;

import com.gprindevelopment.cec.core.externalapi.jarbas.model.JarbasReimbursement;
import com.gprindevelopment.cec.core.restclient.RestTemplateConfig;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest(classes = {JarbasConfig.class, JarbasAPI.class, RestTemplateConfig.class})
@RunWith(SpringRunner.class)
public class JarbasAPITest {

    @Autowired
    private JarbasAPI jarbasAPI;

    @Test
    public void requestReimbursement_validDocumentCode_returnsSuspicions() {
        Long documentCode = 7121351L;
        Optional<JarbasReimbursement> reimbursementOpt = jarbasAPI.requestReimbursement(documentCode);
        assertThat(reimbursementOpt.isPresent());
        JarbasReimbursement reimbursement = reimbursementOpt.get();
        assertThat(reimbursement.getDocumentCode()).isEqualTo(documentCode);
        assertThat(reimbursement.getSuspicions().getInvalidCnpjOrCpf().getValue()).isTrue();
    }

    @Test
    public void requestReimbursement_nonexistentDocumentCode_returnsNothing() {
        Long documentCode = 7123961L;
        Optional<JarbasReimbursement> reimbursementOpt = jarbasAPI.requestReimbursement(documentCode);
        assertThat(reimbursementOpt.isEmpty());
    }

}