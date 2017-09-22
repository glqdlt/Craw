package com.glqdlt.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glqdlt.persistence.entity.CrawRawDataEntity;
import com.glqdlt.persistence.service.CrawDataService;
import com.glqdlt.persistence.vo.CrawDataCountVo;

@RequestMapping(value = "/crawlling/get")
@RestController
public class DataController {

	@Autowired
	CrawDataService cJobService;

	@RequestMapping(value = "/data/all")
	public List<CrawRawDataEntity> getDataAll() {

		List<CrawRawDataEntity> result = cJobService.getAllRawData();
		Collections.reverse(result);

		return result;
	}

	@RequestMapping(value = "/count/all")
	public CrawDataCountVo getCountAll() {

		CrawDataCountVo result = new CrawDataCountVo();

		result.setType("all");
		result.setResult(cJobService.getAllRawData().size());

		return result;
	}

}
