package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.glqdlt.crawlling.data.CrawllingObject;


@Component
public class CoolenjoyNewsParser extends JsoupFunction {

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyNewsParser.class);

	public List<CrawllingObject> Doc_Parser(String url) {

		int lastBoardNo = 1;
		String subject = null;
		String link = null;
		String date = null;
		String boardNo = null;

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements trElements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");

			for (Element tr : trElements) {
				CrawllingObject CrawVO = new CrawllingObject();
				Elements tds = tr.getElementsByTag("td");

				if (tds.hasClass("td_subject")) {

					for (Element element2 : tds) {

						if (element2.className().equals("td_subject")) {
							subject = element2.text();
							link = element2.getElementsByTag("a").get(0).attr("href");
							boardNo = link.substring((link.lastIndexOf("/") + 1), link.length());

						}
						if (element2.className().equals("td_date")) {
							date = element2.text();

						}

					}

					CrawVO.setSubject(subject);
					CrawVO.setBoard_write_date(date);
					CrawVO.setLink(link);

					if (lastBoardNo < Integer.parseInt(boardNo)) {
						list.add(CrawVO);
					}
				}
				CrawVO = null;

			}

		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;
	}

}
