package com.glqdlt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.crawlling.service.JobManager;
import com.glqdlt.crawlling.service.JobStatus;
import com.glqdlt.persistence.data.CrawllingStatusObject;

@RequestMapping(value = "/crawlling/system")
@RestController
public class CrawllingController {

	@Autowired
	JobManager cMom;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/status")
	public CrawllingStatusObject getStatus() {
		CrawllingStatusObject cObj = new CrawllingStatusObject();
		Integer status = 0;
		if (JobStatus.getInstance().getStatus() != 1) {
			status = 1;
		}
		cObj.setStatus(status);
		return cObj;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/shutdown")
	public void shutdown() {

	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/restart")
	public void reStart() {

	}


}
