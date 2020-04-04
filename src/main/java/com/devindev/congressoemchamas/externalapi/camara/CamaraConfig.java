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

    public String getBaseUrl() {
        return baseUrl;
    }
}
