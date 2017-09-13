package com.glqdlt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.glqdlt.configration.BootInit;
import com.glqdlt.persistence.service.CrawDataService;

@ComponentScan(basePackages = "com.glqdlt.*")
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

	@Autowired
	BootInit biService;


	@Autowired
	CrawDataService cJobService;

	
	private static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		log.info("init crawlling targets settup..");

		biService.initCrawDomainDatas();
		biService.startAutoCrawlling();
		
	}

}
