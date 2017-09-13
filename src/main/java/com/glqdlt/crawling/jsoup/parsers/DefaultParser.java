package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glqdlt.crawlling.service.DataService;
import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.CrawllingTargetDomain;

@Component
public abstract class DefaultParser {

	@Autowired
	DataService cService;
	private static final Logger log = LoggerFactory.getLogger(DefaultParser.class);

	public abstract List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain);

	protected void CheckNewHash() {

		Document doc = null;
		String old_hash = null;
		String new_hash = null;

		try {
			doc = Jsoup.connect("url").get();
			new_hash = CheckMD5(doc.toString());
		} catch (IOException e) {
			log.error("checkMD5 Error." + e);
		}

	}

	private boolean EqualsHash(String new_md5, String old_md5) {

		if (!(old_md5.equals(new_md5))) {

			return true;
		}
		return false;

	}

	private String CheckMD5(String text) {
		String MD5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : byteData) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}

			MD5 = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MD5 = null;
		}
		return MD5;
	}

	protected String GetToday() {

		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy_MM_dd");
		return fm1.format(new Date());
	}

	protected void setCommonData(CrawllingRawDataDomain crawObj, CrawllingTargetDomain cDomain) {
		crawObj.setData_name(cDomain.getData_name());
		crawObj.setData_tag(cDomain.getData_tag());
		crawObj.setSite_name(cDomain.getSite_name());
		crawObj.setSite_tag(cDomain.getSite_tag());
		crawObj.setCraw_no(cDomain.getCraw_no());

	}

}
