package com.glqdlt.crawlling.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glqdlt.crawling.jsoup.parsers.CoolenjoyNewsParser;
import com.glqdlt.crawling.jsoup.parsers.CoolenjoyTukgaParser;
import com.glqdlt.crawling.jsoup.parsers.PpompuParser;
import com.glqdlt.crawling.jsoup.parsers.RuriwebParser;
import com.glqdlt.crawling.jsoup.parsers.YepannetParser;
import com.glqdlt.crawling.target.CoolenjoyNews;
import com.glqdlt.crawling.target.CoolenjoyTukga;
import com.glqdlt.crawling.target.PpompuCoupon;
import com.glqdlt.crawling.target.Ruriweb;
import com.glqdlt.crawling.target.YepannetTukga;
import com.glqdlt.crawling.target.YepannetYepan;
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

			if (crawllingTarget.getNo() == 1) {
				Future<List<CrawllingRawDataDomain>> f1 = exePool.submit(new RuriwebParser(crawllingTarget));
				futurePool.add(f1);
			}

			if (crawllingTarget.getNo() == 2) {
				Future<List<CrawllingRawDataDomain>> f2 = exePool.submit(new CoolenjoyNewsParser(crawllingTarget));
				futurePool.add(f2);

			}

			if (crawllingTarget.getNo() == 3) {
				Future<List<CrawllingRawDataDomain>> f3 = exePool.submit(new CoolenjoyTukgaParser(crawllingTarget));
				futurePool.add(f3);

			}

			if (crawllingTarget.getNo() == 4) {

			}

			if (crawllingTarget.getNo() == 5) {

			}

			if (crawllingTarget.getNo() == 6) {

			}

			if (crawllingTarget.getNo() == 7) {

			}

		}

		for (Future<List<CrawllingRawDataDomain>> future : futurePool) {
			try {
				cRepo.save(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}

		log.info("done");

	}

}
