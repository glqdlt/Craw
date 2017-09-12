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

import com.glqdlt.crawling.target.stack.CoolenjoyTukga;
import com.glqdlt.data.CrawllingObject;

public class CoolenjoyTukgaParser extends JsoupFunction {

	
	private static final Logger log = LoggerFactory.getLogger(CoolenjoyTukgaParser.class);


	public List<CrawllingObject> Doc_Parser(String url) {

		int data_tag = CoolenjoyTukga.data_tag;
		int site_tag = CoolenjoyTukga.site_tag;
		String site_name = CoolenjoyTukga.site_name;
		String data_name = CoolenjoyTukga.data_name;

		int last_column_no = 1;

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();
		try {
			Document doc = Jsoup.connect(url).get();
			String subject;
			String full_text;
			String sale_pariod;
			String link;
			String price;
			String column_no;
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawllingObject CrawVO = new CrawllingObject();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
				price = element.getElementsByClass("td_won").text();

				full_text = element.getElementsByClass("td_subject").text();
				full_text = full_text.replace("마감 |", "");
				full_text = full_text.replace("예정 |", "");
				full_text = full_text.replace("진행 |", "").trim();

				subject = full_text.substring(0, (full_text.lastIndexOf("댓글")));
				sale_pariod = full_text.substring(full_text.indexOf("일정:") + 3, (full_text.length()));
				column_no = link.substring((link.lastIndexOf("/") + 1), link.length());
				CrawVO.setSale_pariod(sale_pariod);
				CrawVO.setLink(link);
				CrawVO.setPrice(price);
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

		} catch (IOException e) {
			log.error("Parser Error.."+e);
		}
		return list;
	}

}
