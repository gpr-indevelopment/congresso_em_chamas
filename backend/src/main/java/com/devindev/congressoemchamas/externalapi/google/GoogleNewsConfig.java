package com.devindev.congressoemchamas.externalapi.google;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleNewsConfig {

    @Value("${google-news.api.key}")
    @Getter
    private String apiKey;

    @Value("${google-news.api.url}")
    @Getter
    private String baseUrl;
}
