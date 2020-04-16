package com.devindev.congressoemchamas.externalapi.google;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// TODO: 18-Mar-20 Check all url building on Google and Camara to use a builder for parameters instead.
@Configuration
@PropertySource("classpath:google.properties")
public class GoogleConfig {

    @Value("${googleapi.url}")
    private String url;

    @Value("${googleapi.key}")
    private String apiKey;

    @Value("${cse.id}")
    private String engineId;

    public String getUrl(){
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getEngineId() {
        return engineId;
    }
}
