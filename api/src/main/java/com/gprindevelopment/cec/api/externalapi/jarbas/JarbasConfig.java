package com.gprindevelopment.cec.api.externalapi.jarbas;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jarbas.properties")
public class JarbasConfig {

    @Value("${jarbas.url}")
    @Getter
    private String jarbasUrl;
}
