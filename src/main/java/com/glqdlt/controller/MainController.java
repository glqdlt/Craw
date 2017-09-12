package com.glqdlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.crawlling.service.CrawllingService;

@RequestMapping(value = "/crawlling")
@RestController
public class MainController {
	@Autowired
	CrawllingService cService;

	@RequestMapping(value = "/start")
	public void start() {
		cService.StartJob();
	}

	@RequestMapping(value = "/shutdown")
	public void shutdown() {

	}

	@RequestMapping(value = "/restart")
	public void reStart() {

	}

	@RequestMapping(value = "/status")
	public void status() {

	}

}
