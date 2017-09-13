package com.glqdlt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.ResultDataCount;
import com.glqdlt.persistence.service.CrawllingJobService;

@RequestMapping(value = "/crawlling/get")
@RestController
public class DataController {

	@Autowired
	CrawllingJobService cJobService;

	@RequestMapping(value = "/data/all")
	public List<CrawllingRawDataDomain> getDataAll() {

		return cJobService.getAllRawData();
	}

	@RequestMapping(value = "/count/all")
	public ResultDataCount getCountAll() {

		ResultDataCount result = new ResultDataCount();

		result.setType("all");
		result.setResult(cJobService.getAllRawData().size());

		return result;
	}

}
