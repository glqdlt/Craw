package com.glqdlt.crawling.stack.jsoup.parsers;

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

import com.glqdlt.data.CrawllingObject;

public class YepannetParser extends JsoupFunction {

	private static final Logger log = LoggerFactory.getLogger(YepannetParser.class);

	public List<CrawllingObject> Doc_Parser(String url) {

		List<CrawllingObject> list = new ArrayList<CrawllingObject>();

		int lastBoardNo = 1;

		String subject;
		String boardWriteDate;
		String link;
		String boardNo;
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

				crawObj.setwrite_date(boardWriteDate);
				crawObj.setLink(link);
				crawObj.setSubject(subject);
				crawObj.setBoardNo(boardNo);

				if (lastBoardNo < Integer.parseInt(boardNo)) {
					list.add(crawObj);
				}
				crawObj = null;

			}

		} catch (IOException e) {
			log.error("Parser Error.."+e);
		}
		return list;
	}

	private String boardNoRegex(String link) {
		String regex = "(wr_id=)[0-9]*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(link);
		m.find();
		String find = m.group();
		try {
			find = find.replace("wr_id=", "");
		} catch (IllegalStateException igone) {
			find = "0";
		}
		return find;
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
