package com.gprindevelopment.cec.core.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:batch.properties")
public class BatchConfig {

    @Value("${batch.politician.expiration-time-days}")
    private Long politicianExpirationDays;

    public Long getPoliticianExpirationDays() {
        return politicianExpirationDays;
    }
}
