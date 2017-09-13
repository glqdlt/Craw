package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.CrawllingTargetDomain;

@Component
public class CoolenjoyTukgaParser extends DefaultParser implements Callable<List<CrawllingRawDataDomain>> {

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyTukgaParser.class);


	private CrawllingTargetDomain cDomain;

	public CoolenjoyTukgaParser(CrawllingTargetDomain cDomain) {
		this.cDomain = cDomain;
	}

	@Override
	public List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain) {

		List<CrawllingRawDataDomain> list = new ArrayList<CrawllingRawDataDomain>();
		int lastBoardNo = 1;
		String subject = null;
		String fullBody = null;
		// String salePariod = null;
		String link = null;
		// String price = null;
		String boardNo = null;
		try {
			Document doc = Jsoup.connect(cDomain.getUrl()).get();
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawllingRawDataDomain crawObj = new CrawllingRawDataDomain();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
				// price = element.getElementsByClass("td_won").text();

				fullBody = element.getElementsByClass("td_subject").text();
				fullBody = fullBody.replace("마감 |", "");
				fullBody = fullBody.replace("예정 |", "");
				fullBody = fullBody.replace("진행 |", "").trim();

				subject = fullBody.substring(0, (fullBody.lastIndexOf("댓글")));
				// salePariod = fullBody.substring(fullBody.indexOf("일정:") + 3,
				// (fullBody.length()));
				boardNo = link.substring((link.lastIndexOf("/") + 1), link.length());
				// CrawVO.setSale_pariod(salePariod);
				crawObj.setLink(link);
				crawObj.setSubject(subject);
				crawObj.setBoard_no(boardNo);
				crawObj.setData_name(cDomain.getData_name());
				crawObj.setData_tag(cDomain.getData_tag());
				crawObj.setSite_name(cDomain.getSite_name());
				crawObj.setSite_tag(cDomain.getSite_tag());
				crawObj.setCraw_no(cDomain.getCraw_no());

				if (lastBoardNo < Integer.parseInt(boardNo)) {
					list.add(crawObj);
				}

				crawObj = null;
			}

		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;
	}

	@Override
	public List<CrawllingRawDataDomain> call() throws Exception {
		return startJob(cDomain);
	}

}
