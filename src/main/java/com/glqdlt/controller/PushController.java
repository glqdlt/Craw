package com.glqdlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.glqdlt.persistence.service.CrawDataService;

@Component
public class PushController {

	@Autowired
	private SimpMessagingTemplate broker;
	@Autowired
	CrawDataService cDataService;

	@Scheduled(fixedDelay = 60000)
	public void greeting2() throws Exception {
		broker.convertAndSend("/push/newData", cDataService.getAllRawData());
	}
}
