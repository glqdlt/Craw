package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glqdlt.crawlling.data.CrawllingObject;
import com.glqdlt.crawlling.service.CrawllingService;

@Component
public class YepannetParser extends JsoupFunction {

	@Autowired
	CrawllingService cService;

	private static final Logger log = LoggerFactory.getLogger(YepannetParser.class);

	public List<CrawllingObject> Doc_Parser(String url) {

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();

		int lastBoardNo = 1;

		String subject = null;
		String boardWriteDate = null;
		String link = null;
		String boardNo = null;
		try {
			Document doc = Jsoup.connect(url).get();
			Elements el = doc.select("tr[align=center]");
			for (Element element : el) {
				CrawllingObject crawObj = new CrawllingObject();

				link = element.getElementsByClass("mw_basic_list_thumb").select("a[href]").attr("href");

				subject = element.getElementsByClass("mw_basic_list_subject").text();
				subject = subjectRegex(subject);
				boardNo = boardNoRegex(link);
				boardWriteDate = element.getElementsByClass("mw_basic_list_datetime").text();

				crawObj.setBoard_write_date(boardWriteDate);
				crawObj.setLink(link);
				crawObj.setSubject(subject);
				crawObj.setboard_no(boardNo);

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

	private String boardNoRegex(String link) {
		String regex = "(wr_id=)[0-9]*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(link);
		m.find();
		String result = m.group();
		try {
			result = result.replace("wr_id=", "");
		} catch (IllegalStateException igone) {
			result = "0";
		}
		return result;
	}

	private String subjectRegex(String subject) {
		String regex = "[+][0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(subject);
		if (m.find()) {
			String find = m.group();
			subject = subject.replace(find, "");
		}
		subject = subject.replace("[특판정보]", "");
		subject = subject.replace("[예판정보]", "");
		return subject;
	}
}
