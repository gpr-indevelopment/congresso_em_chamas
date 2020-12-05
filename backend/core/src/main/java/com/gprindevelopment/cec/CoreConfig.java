package com.gprindevelopment.cec;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.gprindevelopment.cec"})
@EntityScan(basePackages = {"com.gprindevelopment.cec"})
@ComponentScan(basePackages = {"com.gprindevelopment.cec"})
public class CoreConfig {
}
