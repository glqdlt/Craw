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

import com.glqdlt.crawling.target.stack.Ruriweb;
import com.glqdlt.data.CrawllingObject;

public class RuriwebParser extends JsoupFunction {



	private static final Logger log = LoggerFactory.getLogger(RuriwebParser.class);

	
	public List<CrawllingObject> Doc_Parser(String url) {
		List<CrawllingObject> list = new ArrayList<CrawllingObject>();

		int data_tag = Ruriweb.data_tag;
		int site_tag = Ruriweb.site_tag;
		String data_name = Ruriweb.data_name;
		String site_name = Ruriweb.site_name;

		int last_column_no = 1;

		String subject;
		String write_date;
		String link;
		String column_no;
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elemnts = doc.getElementsByClass("board_list_table");
			elemnts = elemnts.get(0).getElementsByClass("table_body");

			for (Element el : elemnts) {
				Elements elements_2 = el.getElementsByClass("table_body");
				for (Element element2 : elements_2) {
					if (element2.className().equals("table_body")) {
						CrawllingObject CrawVO = new CrawllingObject();
						link = el.getElementsByClass("subject").get(0).getElementsByClass("deco").attr("href");
						write_date = el.getElementsByClass("time").text();
						subject = el.getElementsByClass("subject").text();
						subject = FindReply(subject);
						column_no = el.getElementsByClass("id").text();

						CrawVO.setwrite_date(write_date);
						CrawVO.setLink(link);
						CrawVO.setSubject(subject);
						CrawVO.setBoardNo(column_no);
						CrawVO.setSite_tag(site_tag);
						CrawVO.setData_tag(data_tag);
						CrawVO.setSite_name(site_name);
						CrawVO.setData_name(data_name);

						if (last_column_no < Integer.parseInt(column_no)) {
							list.add(CrawVO);
						}
						CrawVO = null;
					}

				}
			}

		} catch (IOException e) {
			log.error("Parser Error.."+e);
		}
		return list;
	}

	private String FindReply(String text) {

		text = text.trim();
		if (text.substring(text.length() - 1, text.length()).equals("N")) {
			text = text.substring(0, text.length() - 2);
		}
		text = text.trim();
		return text;
	}

}
