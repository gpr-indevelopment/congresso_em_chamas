package com.devindev.congressoemchamas.externalapi.camara;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:camara.properties")
public class CamaraConfig {

    @Value("${camara.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
