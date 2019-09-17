package com.snowcatsystems.nasascraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NasascraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasascraperApplication.class, args);
	}

}
