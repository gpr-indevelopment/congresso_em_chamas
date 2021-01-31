package com.gprindevelopment.cec.core.externalapi.google;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:google.properties")
public class GoogleNewsConfig {

    @Value("${google-news.api.key}")
    @Getter
    private String apiKey;

    @Value("${google-news.api.url}")
    @Getter
    private String baseUrl;
}
