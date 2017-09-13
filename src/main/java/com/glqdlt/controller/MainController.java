package com.glqdlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.crawlling.service.CrawllingMom;

@RequestMapping(value = "/crawlling")
@RestController
public class MainController {
	@Autowired
	CrawllingMom cService;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/start")
	public void start() {
		cService.jobRunner();
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
