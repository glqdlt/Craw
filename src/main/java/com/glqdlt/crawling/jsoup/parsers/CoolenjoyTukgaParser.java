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
public class CoolenjoyTukgaParser extends JsoupFunction {

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyTukgaParser.class);

	public List<CrawllingObject> Doc_Parser(String url) {

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();
		int lastBoardNo = 1;
		String subject = null;
		String fullBody = null;
		String salePariod = null;
		String link = null;
		String price = null;
		String boardNo = null;
		try {
			Document doc = Jsoup.connect(url).get();
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawllingObject CrawVO = new CrawllingObject();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
				price = element.getElementsByClass("td_won").text();

				fullBody = element.getElementsByClass("td_subject").text();
				fullBody = fullBody.replace("마감 |", "");
				fullBody = fullBody.replace("예정 |", "");
				fullBody = fullBody.replace("진행 |", "").trim();

				subject = fullBody.substring(0, (fullBody.lastIndexOf("댓글")));
				salePariod = fullBody.substring(fullBody.indexOf("일정:") + 3, (fullBody.length()));
				boardNo = link.substring((link.lastIndexOf("/") + 1), link.length());
				CrawVO.setSale_pariod(salePariod);
				CrawVO.setLink(link);
				CrawVO.setPrice(price);
				CrawVO.setSubject(subject);
				CrawVO.setboard_no(boardNo);

				if (lastBoardNo < Integer.parseInt(boardNo)) {
					list.add(CrawVO);
				}

				CrawVO = null;
			}

		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;
	}

}
