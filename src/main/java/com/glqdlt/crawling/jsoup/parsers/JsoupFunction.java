package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glqdlt.crawlling.data.NewDataCheckVO;
import com.glqdlt.crawlling.service.CrawllingService;


@Component
public abstract class JsoupFunction {

	@Autowired
	CrawllingService cService;
	private static final Logger log = LoggerFactory.getLogger(JsoupFunction.class);

	protected NewDataCheckVO CheckNewHash() {

		NewDataCheckVO NCVO = new NewDataCheckVO();

		Document doc = null;
		String old_hash = null;
		String new_hash = null;

		try {
			doc = Jsoup.connect("url").get();
			new_hash = CheckMD5(doc.toString());
		} catch (IOException e) {
			log.error("checkMD5 Error." + e);
		}
		NCVO.setCheck_boolean(EqualsHash(new_hash, old_hash));
		NCVO.setDoc(doc);

		return NCVO;
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

}
