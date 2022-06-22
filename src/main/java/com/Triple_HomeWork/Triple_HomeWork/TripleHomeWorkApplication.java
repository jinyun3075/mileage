package com.Triple_HomeWork.Triple_HomeWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripleHomeWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripleHomeWorkApplication.class, args);
	}

}
