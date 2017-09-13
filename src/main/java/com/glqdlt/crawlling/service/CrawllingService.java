package com.glqdlt.crawlling.service;

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
import com.glqdlt.persistence.repository.CrawllingRawDataRepository;

@Service
public class CrawllingService {

	@Autowired
	CrawllingRawDataRepository cRepo;

	@Autowired
	JobSchuduleService jsService;

	private static final Logger log = LoggerFactory.getLogger(CrawllingService.class);

	public void jobRunner() {
		ExecutorService exePool = Executors.newCachedThreadPool();
		List<CrawllingTargetDomain> list = jsService.getAllCrawllingTargets();
		List<Future<List<CrawllingRawDataDomain>>> futurePool = new ArrayList<>();

		for (CrawllingTargetDomain crawllingTarget : list) {

			switch (crawllingTarget.getCraw_no()) {

			case 1:
				Future<List<CrawllingRawDataDomain>> f1 = exePool.submit(new RuriwebParser(crawllingTarget));
				futurePool.add(f1);
				break;

			case 2:
				Future<List<CrawllingRawDataDomain>> f2 = exePool.submit(new CoolenjoyNewsParser(crawllingTarget));
				futurePool.add(f2);
				break;
			case 3:
				Future<List<CrawllingRawDataDomain>> f3 = exePool.submit(new CoolenjoyTukgaParser(crawllingTarget));
				futurePool.add(f3);
				break;

			case 4:

				Future<List<CrawllingRawDataDomain>> f4 = exePool.submit(new PpompuParser(crawllingTarget));
				futurePool.add(f4);
				break;

			case 5:

				Future<List<CrawllingRawDataDomain>> f5 = exePool.submit(new PpompuParser(crawllingTarget));
				futurePool.add(f5);
				break;
			case 6:

				Future<List<CrawllingRawDataDomain>> f6 = exePool.submit(new YepannetParser(crawllingTarget));
				futurePool.add(f6);
				break;
			case 7:

				Future<List<CrawllingRawDataDomain>> f7 = exePool.submit(new YepannetParser(crawllingTarget));
				futurePool.add(f7);
				break;
			default:
				break;
			}

		}

		for (Future<List<CrawllingRawDataDomain>> future : futurePool) {
			try {
				cRepo.save(future.get(60, TimeUnit.SECONDS));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				log.error("timeout Exception", e);
				continue;
			}

		}

		log.info("done");

	}

}
