package com.gprindevelopment.cec.metrics;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com/gprindevelopment/cec/metrics"})
@ComponentScan(basePackages = {"com/gprindevelopment/cec/metrics"})
public class BatchConfig {
}
