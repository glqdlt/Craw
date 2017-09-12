package com.glqdlt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.glqdlt.*")
@SpringBootApplication
public class CrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawApplication.class, args);
	}

}
