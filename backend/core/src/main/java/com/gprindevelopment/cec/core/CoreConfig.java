package com.gprindevelopment.cec.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.gprindevelopment.cec.core"})
@EntityScan(basePackages = {"com.gprindevelopment.cec.core"})
@ComponentScan(basePackages = {"com.gprindevelopment.cec.core"})
public class CoreConfig {
}
