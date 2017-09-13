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
public class CoolenjoyNewsParser extends DefaultParser implements Callable<List<CrawllingRawDataDomain>> {

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyNewsParser.class);

	CrawllingTargetDomain cDomain;

	public CoolenjoyNewsParser() {
	}

	public CoolenjoyNewsParser(CrawllingTargetDomain cDomain) {
		this.cDomain = cDomain;

	}

	@Override
	public List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain) {

		int lastBoardNo = 1;
		String subject = null;
		String link = null;
		String date = null;
		String boardNo = null;

		List<CrawllingRawDataDomain> list = new ArrayList<CrawllingRawDataDomain>();
		try {
			Document doc = Jsoup.connect(cDomain.getUrl()).get();
			Elements trElements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");

			for (Element tr : trElements) {
				CrawllingRawDataDomain crawObj = new CrawllingRawDataDomain();
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

					subject = subjectRegex(subject);
					crawObj.setSubject(subject);
					crawObj.setBoard_write_date(date);
					crawObj.setLink(link);
					crawObj.setBoard_no(boardNo);
					crawObj.setData_name(cDomain.getData_name());
					crawObj.setData_tag(cDomain.getData_tag());
					crawObj.setSite_name(cDomain.getSite_name());
					crawObj.setSite_tag(cDomain.getSite_tag());

					if (lastBoardNo < Integer.parseInt(boardNo)) {
						list.add(crawObj);
					}
				}
				crawObj = null;

			}

		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;
	}

	private String subjectRegex(String text) {

		if (text.lastIndexOf("개") == (text.length()) - 1) {
			text = text.substring(0, text.lastIndexOf("댓글"));
		}
		;

		return text;
	}

	@Override
	public List<CrawllingRawDataDomain> call() throws Exception {

		return startJob(cDomain);
	}

}
