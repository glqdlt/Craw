package com.glqdlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.crawlling.service.CrawllingMom;
import com.glqdlt.crawlling.service.JobStatus;

@RequestMapping(value = "/crawlling")
@RestController
public class MainController {

	@Autowired
	CrawllingMom cMom;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/start")
	public String start() {
		if (JobStatus.getInstance().getStatus() != 1) {
			cMom.start();
			return "Start Auto Crawlling Mode..";
		}

		return "Auto Crawlling Mode, Alive";
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/shutdown")
	public void shutdown() {

	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/restart")
	public void reStart() {

	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/status")
	public void status() {

	}

}
