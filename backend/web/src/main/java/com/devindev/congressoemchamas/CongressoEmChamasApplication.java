package com.devindev.congressoemchamas;

import com.gprindevelopment.cec.CoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(value = {CoreConfig.class})
@EnableConfigurationProperties(CoreConfig.class)
public class CongressoEmChamasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongressoEmChamasApplication.class, args);
	}

}
