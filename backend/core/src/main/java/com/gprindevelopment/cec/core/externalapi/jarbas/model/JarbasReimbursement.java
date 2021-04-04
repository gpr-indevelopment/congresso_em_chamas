package com.gprindevelopment.cec.core.externalapi.jarbas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JarbasReimbursement {

    @JsonProperty("document_id")
    private Long documentCode;

    @JsonProperty("suspicions")
    private JarbasSuspicions suspicions;
}
