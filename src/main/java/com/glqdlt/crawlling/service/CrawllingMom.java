package com.glqdlt.crawlling.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glqdlt.crawling.jsoup.parsers.CoolenjoyNewsParser;
import com.glqdlt.crawling.jsoup.parsers.CoolenjoyTukgaParser;
import com.glqdlt.crawling.jsoup.parsers.PpompuParser;
import com.glqdlt.crawling.jsoup.parsers.RuriwebParser;
import com.glqdlt.crawling.jsoup.parsers.YepannetParser;
import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.CrawllingTargetDomain;
import com.glqdlt.persistence.data.FutureData;
import com.glqdlt.persistence.repository.CrawllingRawDataRepository;
import com.glqdlt.persistence.service.CrawllingJobService;

@Service
public class CrawllingMom {

	@Autowired
	CrawllingJobService cJobService;

	private static final Logger log = LoggerFactory.getLogger(CrawllingMom.class);

	public void jobRunner() {
		ExecutorService exePool = Executors.newCachedThreadPool();
		List<CrawllingTargetDomain> crawllingTargets = cJobService.getAllCrawllingTargets();
		List<FutureData> fPool = new ArrayList<>();

		for (CrawllingTargetDomain cTarget : crawllingTargets) {

			switch (cTarget.getCraw_no()) {

			case 1:
				Future<List<CrawllingRawDataDomain>> f1 = exePool.submit(new RuriwebParser(cTarget));
				fPool.add(new FutureData(f1, 1));
				break;

			case 2:
				Future<List<CrawllingRawDataDomain>> f2 = exePool.submit(new CoolenjoyNewsParser(cTarget));
				fPool.add(new FutureData(f2, 2));
				break;
			case 3:
				Future<List<CrawllingRawDataDomain>> f3 = exePool.submit(new CoolenjoyTukgaParser(cTarget));
				fPool.add(new FutureData(f3, 3));
				break;

			case 4:

				Future<List<CrawllingRawDataDomain>> f4 = exePool.submit(new PpompuParser(cTarget));
				fPool.add(new FutureData(f4, 4));
				break;

			case 5:

				Future<List<CrawllingRawDataDomain>> f5 = exePool.submit(new PpompuParser(cTarget));
				fPool.add(new FutureData(f5, 5));
				break;
			case 6:

				Future<List<CrawllingRawDataDomain>> f6 = exePool.submit(new YepannetParser(cTarget));
				fPool.add(new FutureData(f6, 6));
				break;
			case 7:

				Future<List<CrawllingRawDataDomain>> f7 = exePool.submit(new YepannetParser(cTarget));
				fPool.add(new FutureData(f7, 7));
				break;
			default:
				break;
			}

		}

		for (FutureData f : fPool) {
			cJobService.saveCrawllingRawData(f.getFuture());
		}

		log.info("done");

	}

	private boolean checkLastBoardNo(FutureData f) {

		Integer crawNo = f.getCraw_no();

		Integer lastBoardNo = cJobService.getLastBoardNo(crawNo);

		try {
			List<CrawllingRawDataDomain> l = f.getFuture().get(60, TimeUnit.SECONDS);
			for (CrawllingRawDataDomain crawllingRawDataDomain : l) {
				
				if(crawllingRawDataDomain.getBoard_no() > lastBoardNo){
					
				}
				
			}
			
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		return false;
	}

}
