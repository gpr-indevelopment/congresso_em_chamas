package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.externalapi.camara.functions.GetCurrentLegislature;
import lombok.Getter;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@PropertySource("classpath:camara.properties")
public class CamaraConfig {

    @Value("${camara.url}")
    private String baseUrl;

    @Getter
    private Long currentLegislatureId;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraConfig.class);

    @PostConstruct
    private void checkCurrentLegislature() throws IOException{
        try {
            String path = String.format("%s/legislaturas?ordem=DESC&ordenarPor=id", this.getBaseUrl());
            GetCurrentLegislature apiFunctionHandler = new GetCurrentLegislature();
            this.currentLegislatureId = Request.Get(path).execute().handleResponse(apiFunctionHandler);
        } catch (IOException e) {
            LOGGER.error("Unable to retrieve the current legislature ID from CamaraAPI.");
            throw e;
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
