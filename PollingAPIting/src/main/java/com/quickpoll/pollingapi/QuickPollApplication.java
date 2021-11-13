package com.quickpoll.pollingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuickPollApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickPollApplication.class, args);
	}

}
