package com.glqdlt.persistence.service;

import java.util.Comparator;
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
	CrawllingTargetRepository cTargetRepo;

	@Autowired
	CrawllingRawDataRepository cRawDataRepo;

	private static final Logger log = LoggerFactory.getLogger(CrawllingJobService.class);

	synchronized public Integer getLastBoardNo(Integer craw_no) {
		Integer resultLastBoardNo = 1;
		List<CrawllingRawDataDomain> list = cRawDataRepo.findByCrawNo(craw_no);
		if (list.size() == 0) {
			return resultLastBoardNo;
		}
		Integer lastBoardNo = 0;

		for (CrawllingRawDataDomain crawllingRawDataDomain : list) {

			if (lastBoardNo == 0) {
				lastBoardNo = crawllingRawDataDomain.getBoardNo();
			}

			if (crawllingRawDataDomain.getBoardNo() > lastBoardNo) {
				lastBoardNo = crawllingRawDataDomain.getBoardNo();
			}

		}
		if (lastBoardNo != 0) {
			resultLastBoardNo = lastBoardNo;
		}
		return resultLastBoardNo;
	}

	public List<CrawllingTargetDomain> getAllCrawllingTargets() {
		return cTargetRepo.findAll();
	}

	public void saveCrawllingRawDataList(Future<List<CrawllingRawDataDomain>> f) {
		try {
			cRawDataRepo.save(f.get(60, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException e) {
			log.error("saveCrawllingRawData error.", e);
		} catch (TimeoutException e) {
			log.error("timeout ex", e);
		}
	}

	public void saveCrawllingRawData(CrawllingRawDataDomain cRawData) {
		cRawDataRepo.save(cRawData);
	}

}
