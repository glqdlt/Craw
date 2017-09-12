package com.glqdlt.crawlling.service;

import java.util.List;

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
import com.glqdlt.persistence.data.CrawllingObject;
import com.glqdlt.persistence.repository.CrawllingRepository;

@Service
public class CrawllingService {

	@Autowired
	CoolenjoyNewsParser cNewsParser;

	@Autowired
	CoolenjoyTukgaParser ctukgaParser;

	@Autowired
	RuriwebParser rParser;

	@Autowired
	PpompuParser pParser;

	@Autowired
	YepannetParser yParser;

	@Autowired
	CrawllingRepository cRepo;

	private static final Logger log = LoggerFactory.getLogger(CrawllingService.class);

	public void StartJob() {

		cNewsParser.startJob(CoolenjoyNews.target_url).forEach(x -> cRepo.save(x));

		ctukgaParser.startJob(CoolenjoyTukga.target_url).forEach(x -> cRepo.save(x));

		rParser.startJob(Ruriweb.target_url).forEach(x -> cRepo.save(x));

		pParser.startJob(PpompuCoupon.target_url).forEach(x -> cRepo.save(x));

		yParser.startJob(YepannetTukga.target_url).forEach(x -> cRepo.save(x));

		yParser.startJob(YepannetYepan.target_url).forEach(x -> cRepo.save(x));

		log.info("done");

	}

}
