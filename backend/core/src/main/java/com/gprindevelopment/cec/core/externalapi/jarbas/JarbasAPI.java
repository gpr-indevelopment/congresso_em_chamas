package com.gprindevelopment.cec.core.externalapi.jarbas;

import com.gprindevelopment.cec.core.externalapi.jarbas.model.JarbasReimbursement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JarbasAPI {

    private final RestTemplate restTemplate;

    private final JarbasConfig jarbasConfig;

    public Optional<JarbasReimbursement> requestReimbursement(Long documentCode) {
        String url = String.format("%s/chamber_of_deputies/reimbursement/%s", jarbasConfig.getJarbasUrl(), documentCode);
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, JarbasReimbursement.class));
        } catch (HttpClientErrorException.NotFound ex) {
            return Optional.empty();
        }
    }
}
