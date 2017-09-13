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
public class RuriwebParser extends DefaultParser implements Callable<List<CrawllingRawDataDomain>> {

	private static final Logger log = LoggerFactory.getLogger(RuriwebParser.class);

	public RuriwebParser() {
		// TODO Auto-generated constructor stub
	}
	CrawllingTargetDomain cDomain;
	public RuriwebParser(CrawllingTargetDomain cDomain) {
		this.cDomain = cDomain;
	}
	
	@Override
	public List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain) {
		List<CrawllingRawDataDomain> list = new ArrayList<CrawllingRawDataDomain>();

		int lastBoardNo = 1;

		String subject = null;
		String boardWriteDate = null;
		String link = null;
		String boardNo = null;
		try {
			Document doc = Jsoup.connect(cDomain.getUrl()).get();
			Elements elemnts = doc.getElementsByClass("board_list_table");
			elemnts = elemnts.get(0).getElementsByClass("table_body");

			for (Element el : elemnts) {
				Elements elements2 = el.getElementsByClass("table_body");
				for (Element el2 : elements2) {
					if (el2.className().equals("table_body")) {
						CrawllingRawDataDomain CrawVO = new CrawllingRawDataDomain();
						link = el.getElementsByClass("subject").get(0).getElementsByClass("deco").attr("href");
						boardWriteDate = el.getElementsByClass("time").text();
						subject = el.getElementsByClass("subject").text();
						subject = FindReply(subject);
						boardNo = el.getElementsByClass("id").text();

						CrawVO.setBoard_write_date(boardWriteDate);
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

				}
			}

		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;
	}

	private String FindReply(String text) {

		text = text.trim();
		if (text.substring(text.length() - 1, text.length()).equals("N")) {
			text = text.substring(0, text.length() - 2);
		}
		text = text.trim();

		if (text.lastIndexOf(")") == (text.length()) - 1) {

			text = text.substring(0, text.lastIndexOf("(") - 1);

		}

		return text;
	}

	@Override
	public List<CrawllingRawDataDomain> call() throws Exception {
		return startJob(cDomain);
	}

}
