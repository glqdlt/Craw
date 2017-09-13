package com.glqdlt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.glqdlt.configration.BootInitService;

@ComponentScan(basePackages = "com.glqdlt.*")
@SpringBootApplication
public class CrawApplication implements CommandLineRunner {

	@Autowired
	BootInitService biService;

	private static final Logger log = LoggerFactory.getLogger(CrawApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrawApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		log.info("init crawlling targets settup..");

		biService.init();

	}

}
