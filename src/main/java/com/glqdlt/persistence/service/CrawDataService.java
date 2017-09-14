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

import com.glqdlt.persistence.entity.CrawRawDataEntity;
import com.glqdlt.persistence.entity.CrawDomainEntity;
import com.glqdlt.persistence.repository.CrawRawDataRepository;
import com.glqdlt.persistence.repository.CrawDomainRepository;

@Service
public class CrawDataService {

	@Autowired
	CrawDomainRepository cTargetRepo;

	@Autowired
	CrawRawDataRepository cRawDataRepo;

	private static final Logger log = LoggerFactory.getLogger(CrawDataService.class);

	synchronized public Integer getLastBoardNo(Integer craw_no) {
		Integer resultLastBoardNo = 1;
		List<CrawRawDataEntity> list = cRawDataRepo.findByCrawNo(craw_no);
		if (list.size() == 0) {
			return resultLastBoardNo;
		}
		Integer lastBoardNo = 0;

		for (CrawRawDataEntity crawllingRawDataDomain : list) {

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

	public List<CrawRawDataEntity> getAllRawData() {
		return cRawDataRepo.findAll();
	}

	public List<CrawDomainEntity> getAllCrawllingTargets() {
		return cTargetRepo.findAll();
	}

	public void saveCrawllingRawDataList(Future<List<CrawRawDataEntity>> f) {
		try {
			cRawDataRepo.save(f.get(3, TimeUnit.MINUTES));
		} catch (InterruptedException | ExecutionException e) {
			log.error("saveCrawllingRawData error.", e);
		} catch (TimeoutException e) {
			log.error("timeout ex", e);
		}
	}

	public void saveCrawllingRawData(CrawRawDataEntity cRawData) {
		cRawDataRepo.save(cRawData);
	}

	public List<CrawRawDataEntity> pullCrawNewRawData(List<CrawRawDataEntity> list) {

		return list;

	}

}
