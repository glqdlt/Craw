package com.glqdlt.crawling.stack.jsoup;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public abstract class JsoupFunctions {
	Logger logger = LoggerFactory.getLogger(JsoupFunctions.class);

	

	protected NewDataCheckVO CheckNewHash(CrawllingTarget target) {
		
		
		NewDataCheckVO NCVO = new NewDataCheckVO();

		int data_tag = target.get_data_tag();
		int site_tag = target.get_site_tag();
		Document doc = null;
		String old_hash = "";
		String new_hash = "";
		
		old_hash = LastCrawllingData.getIns().getLastHash(site_tag, data_tag);
		
		try {
			doc = Jsoup.connect(target.get_target_url()).get();
			new_hash = CheckMD5(doc.toString());
			

		}catch(SocketTimeoutException e){ 
			logger.warn("Timeout Exception.. Pass");
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		NCVO.setCheck_boolean(EqualsHash(new_hash, old_hash));
		NCVO.setDoc(doc);
		NCVO.setCrawllingTarget(target);

		return NCVO;
	}

	private boolean EqualsHash(String new_md5, String old_md5) {

		/**
		 * NewSite라고 판단되면 true를 반환.
		 */
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

		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		return fm1.format(new Date());
	}

	protected String FindReply(String text) {

		text = text.trim();
		if (text.substring(text.length() - 1, text.length()).equals("N")) {
			text = text.substring(0, text.length() - 2);
		}
		text = text.trim();
		return text;
	}
}
