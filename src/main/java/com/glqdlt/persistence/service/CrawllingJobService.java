package com.glqdlt.persistence.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.CrawllingTargetDomain;
import com.glqdlt.persistence.repository.CrawllingRawDataRepository;
import com.glqdlt.persistence.repository.CrawllingTargetRepository;

@Service
public class CrawllingJobService {

	@Autowired
	CrawllingTargetRepository ctRepo;

	@Autowired
	CrawllingRawDataRepository cRepo;

	private static final Logger log = LoggerFactory.getLogger(CrawllingJobService.class);

	public Integer getLastBoardNo(Integer craw_no) {
		return 1;
	}

	public List<CrawllingTargetDomain> getAllCrawllingTargets() {
		return ctRepo.findAll();
	}

	public void saveCrawllingRawData(Future<List<CrawllingRawDataDomain>> f) {
		try {
			cRepo.save(f.get(60, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException e) {
			log.error("saveCrawllingRawData error.", e);
		} catch (TimeoutException e) {
			log.error("timeout ex", e);
		}
	}
}
