package com.devindev.congressoemchamas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@EnableScheduling
public class CongressoEmChamasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongressoEmChamasApplication.class, args);
	}

}
