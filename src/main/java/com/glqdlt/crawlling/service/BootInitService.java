package com.glqdlt.crawlling.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glqdlt.crawling.target.CoolenjoyNews;
import com.glqdlt.crawling.target.CoolenjoyTukga;
import com.glqdlt.crawling.target.PpompuCoupon;
import com.glqdlt.crawling.target.PpompuTukga;
import com.glqdlt.crawling.target.Ruriweb;
import com.glqdlt.crawling.target.YepannetTukga;
import com.glqdlt.crawling.target.YepannetYepan;
import com.glqdlt.persistence.data.CrawllingTargetDomain;
import com.glqdlt.persistence.repository.CrawllingTargetRepository;

@Service
public class BootInitService {

	@Autowired
	CrawllingTargetRepository ctRepo;

	private static final Logger log = LoggerFactory.getLogger(BootInitService.class);

	public void init() {

		setUp().forEach(x -> ctRepo.save(x));
		log.info("Boot Init complete to Crawlling Targets.");

	}

	private ArrayList<CrawllingTargetDomain> setUp() {

		log.info("Crawlling data setup for init..");

		ArrayList<CrawllingTargetDomain> list = new ArrayList<>();

		list.add(new CrawllingTargetDomain(1, Ruriweb.target_url, Ruriweb.data_name, Ruriweb.data_tag,
				Ruriweb.site_name, Ruriweb.site_tag, Ruriweb.craw_type));

		list.add(new CrawllingTargetDomain(2, CoolenjoyNews.target_url, CoolenjoyNews.data_name, CoolenjoyNews.data_tag,
				CoolenjoyNews.site_name, CoolenjoyNews.site_tag, CoolenjoyNews.craw_type));
		list.add(new CrawllingTargetDomain(3, CoolenjoyTukga.target_url, CoolenjoyTukga.data_name,
				CoolenjoyTukga.data_tag, CoolenjoyTukga.site_name, CoolenjoyTukga.site_tag, CoolenjoyTukga.craw_type));

		list.add(new CrawllingTargetDomain(4, PpompuCoupon.target_url, PpompuCoupon.data_name, PpompuCoupon.data_tag,
				PpompuCoupon.site_name, PpompuCoupon.site_tag, PpompuCoupon.craw_type));
		list.add(new CrawllingTargetDomain(5, PpompuTukga.target_url, PpompuTukga.data_name, PpompuTukga.data_tag,
				PpompuTukga.site_name, PpompuTukga.site_tag, PpompuTukga.craw_type));

		list.add(new CrawllingTargetDomain(6, YepannetTukga.target_url, YepannetTukga.data_name, YepannetTukga.data_tag,
				YepannetTukga.site_name, YepannetTukga.site_tag, YepannetTukga.craw_type));
		list.add(new CrawllingTargetDomain(7, YepannetYepan.target_url, YepannetYepan.data_name, YepannetYepan.data_tag,
				YepannetYepan.site_name, YepannetYepan.site_tag, YepannetYepan.craw_type));

		log.info("Ready to Crawlling data init.");
		return list;

	}

}
