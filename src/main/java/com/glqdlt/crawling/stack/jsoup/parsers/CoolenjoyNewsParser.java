package com.glqdlt.crawling.stack.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glqdlt.crawling.target.stack.CoolenjoyNews;
import com.glqdlt.data.CrawllingObject;

public class CoolenjoyNewsParser extends JsoupFunction {

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyNewsParser.class);

	public List<CrawllingObject> Doc_Parser(String url) {
		int data_tag = CoolenjoyNews.data_tag;
		int site_tag = CoolenjoyNews.site_tag;
		String site_name = CoolenjoyNews.site_name;
		String data_name = CoolenjoyNews.data_name;

		int last_column_no = 1;

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements trElements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");

			String subject = "";
			String link = "";
			String date = "";
			String column_no = "";

			for (Element tr : trElements) {
				CrawllingObject CrawVO = new CrawllingObject();
				Elements tds = tr.getElementsByTag("td");

				if (tds.hasClass("td_subject")) {

					for (Element element2 : tds) {

						if (element2.className().equals("td_subject")) {
							subject = element2.text();
							link = element2.getElementsByTag("a").get(0).attr("href");
							column_no = link.substring((link.lastIndexOf("/") + 1), link.length());

						}
						if (element2.className().equals("td_date")) {
							date = element2.text();

						}

					}

					CrawVO.setSubject(subject);
					CrawVO.setwrite_date(date);
					CrawVO.setLink(link);
					CrawVO.setBoardNo(column_no);
					CrawVO.setData_tag(data_tag);
					CrawVO.setSite_tag(site_tag);
					CrawVO.setSite_name(site_name);
					CrawVO.setData_name(data_name);

					if (last_column_no < Integer.parseInt(column_no)) {
						list.add(CrawVO);
					}
				}
				CrawVO = null;

			}

		} catch (IOException e) {
			log.error("Parser Error.."+e);
		}
		return list;
	}

}
