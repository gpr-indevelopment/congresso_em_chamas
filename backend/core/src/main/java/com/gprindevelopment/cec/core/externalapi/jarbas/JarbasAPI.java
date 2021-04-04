package com.gprindevelopment.cec.core.externalapi.jarbas;

import com.gprindevelopment.cec.core.externalapi.jarbas.model.JarbasReimbursement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JarbasAPI {

    private final RestTemplate restTemplate;

    private final JarbasConfig jarbasConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(JarbasAPI.class);

    public Optional<JarbasReimbursement> requestReimbursement(Long documentCode) {
        Optional<JarbasReimbursement> jarbasReimbursementOpt = Optional.empty();
        if(!documentCode.equals(0L)) {
            String url = String.format("%s/chamber_of_deputies/reimbursement/%s", jarbasConfig.getJarbasUrl(), documentCode);
            try {
                jarbasReimbursementOpt = Optional.ofNullable(restTemplate.getForObject(url, JarbasReimbursement.class));
                LOGGER.info("Achei alguma coisa!");
            } catch (HttpClientErrorException.NotFound ignored) {
            } catch (Exception ex) {
                LOGGER.error("An error was found while requesting a reimbursement from Jarbas API for code {}. Returning empty.", documentCode);
                LOGGER.error(ex.getMessage());
            }
        }
        return jarbasReimbursementOpt;
    }
}
