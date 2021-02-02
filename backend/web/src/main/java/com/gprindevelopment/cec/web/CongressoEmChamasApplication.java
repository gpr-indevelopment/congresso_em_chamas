package com.gprindevelopment.cec.web;

import com.gprindevelopment.cec.core.CoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(value = {CoreConfig.class})
@EnableConfigurationProperties(CoreConfig.class)
@EnableScheduling
public class CongressoEmChamasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongressoEmChamasApplication.class, args);
	}

}
