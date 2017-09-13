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
public class CoolenjoyTukgaParser extends DefaultParser  implements Callable<List<CrawllingRawDataDomain>>{

	private static final Logger log = LoggerFactory.getLogger(CoolenjoyTukgaParser.class);

	public CoolenjoyTukgaParser() {
		// TODO Auto-generated constructor stub
	}
	CrawllingTargetDomain cDomain;
	public CoolenjoyTukgaParser(CrawllingTargetDomain cDomain) {
		this.cDomain = cDomain;
	}
	
	@Override
	public List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain) {

		List<CrawllingRawDataDomain> list = new ArrayList<CrawllingRawDataDomain>();
		int lastBoardNo = 1;
		String subject = null;
		String fullBody = null;
//		String salePariod = null;
		String link = null;
//		String price = null;
		String boardNo = null;
		try {
			Document doc = Jsoup.connect(cDomain.getUrl()).get();
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawllingRawDataDomain CrawVO = new CrawllingRawDataDomain();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
//				price = element.getElementsByClass("td_won").text();

				fullBody = element.getElementsByClass("td_subject").text();
				fullBody = fullBody.replace("마감 |", "");
				fullBody = fullBody.replace("예정 |", "");
				fullBody = fullBody.replace("진행 |", "").trim();

				subject = fullBody.substring(0, (fullBody.lastIndexOf("댓글")));
//				salePariod = fullBody.substring(fullBody.indexOf("일정:") + 3, (fullBody.length()));
				boardNo = link.substring((link.lastIndexOf("/") + 1), link.length());
//				CrawVO.setSale_pariod(salePariod);
				CrawVO.setLink(link);
				CrawVO.setSubject(subject);
				CrawVO.setBoard_no(boardNo);
				CrawVO.setData_name(cDomain.getData_name());
				CrawVO.setData_tag(cDomain.getData_tag());
				CrawVO.setSite_name(cDomain.getSite_name());
				CrawVO.setSite_tag(cDomain.getSite_tag());


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

	@Override
	public List<CrawllingRawDataDomain> call() throws Exception {
		return startJob(cDomain);
	}

}
