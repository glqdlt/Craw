package com.glqdlt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.glqdlt.configration.AppInitializer;

@ComponentScan(basePackages = "com.glqdlt.*")
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

	@Autowired
	AppInitializer biService;
	
//	private static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		biService.initCrawDomainDatas();
		biService.startAutoCrawlling();
		
	}

}
