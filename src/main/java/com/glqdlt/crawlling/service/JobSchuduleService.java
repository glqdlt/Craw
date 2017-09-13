package com.glqdlt.crawlling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glqdlt.persistence.data.CrawllingTargetDomain;
import com.glqdlt.persistence.repository.CrawllingTargetRepository;

@Service
public class JobSchuduleService {
	
	@Autowired
	CrawllingTargetRepository ctRepo;

	
	public List<CrawllingTargetDomain> getAllCrawllingTargets(){
		return ctRepo.findAll();
	}
}
