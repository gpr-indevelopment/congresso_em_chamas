package com.devindev.congressoemchamas.data.updater;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dataupdate.properties")
public class DataUpdaterConfig {

    @Value("${data.update.politician-expiration-time-days}")
    private Integer politicianExpirationTimeDays;

    public Integer getPoliticianExpirationTimeDays() {
        return politicianExpirationTimeDays;
    }
}
