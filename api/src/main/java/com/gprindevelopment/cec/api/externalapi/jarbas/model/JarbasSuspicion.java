package com.gprindevelopment.cec.api.externalapi.jarbas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class JarbasSuspicion {

    private String key;

    @Setter
    private Boolean value = false;

    private String description;

    public JarbasSuspicion(String key, String description) {
        this.key = key;
        this.description = description;
    }
}
