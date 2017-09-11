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

import com.glqdlt.crawling.stack.jsoup.JsoupFunctions;
import com.glqdlt.crawling.stack.jsoup.JsoupParser;
import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public class yepannet_parser extends JsoupFunctions implements JsoupParser {

	Logger logger = LoggerFactory.getLogger(yepannet_parser.class);

	@Override
	public List<CrawRAWDataVO> Doc_Parser(NewDataCheckVO NDVO) {

		CrawllingTarget target = NDVO.getCrawllingTarget();
		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();

		int data_tag = target.get_data_tag();
		int site_tag = target.get_site_tag();

		int last_column_no = LastCrawllingData.getIns().getLastColumn_no(site_tag, data_tag);

		String subject;
		String write_date;
		String link;
		String column_no;
		try {
			Document doc = Jsoup.connect(target.get_target_url()).get();
			Elements el = doc.select("tr[align=center]");
			for (Element element : el) {
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();

				link = element.getElementsByClass("mw_basic_list_thumb").select("a[href]").attr("href");

				subject = element.getElementsByClass("mw_basic_list_subject").text();
				subject = subject_regex(subject);
				column_no = column_no_regex(link);
				write_date = element.getElementsByClass("mw_basic_list_datetime").text();

				CrawVO.setwrite_date(write_date);
				CrawVO.setLink(link);
				CrawVO.setSubject(subject);
				CrawVO.setColumn_no(column_no);

				CrawVO.setSite_tag(target.get_site_tag());
				CrawVO.setData_tag(target.get_data_tag());
				CrawVO.setSite_name(target.get_site_name());
				CrawVO.setData_name(target.get_data_name());
				
				if (last_column_no < Integer.parseInt(column_no)) {
					list.add(CrawVO);
				}
				CrawVO = null;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private String column_no_regex(String link) {
		String regex = "(wr_id=)[0-9]*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(link);
		m.find();
		String find = m.group();
		try{
		find = find.replace("wr_id=", "");
		}catch(IllegalStateException igone){
			find = "0";
		}
		return find;
	}

	private String subject_regex(String subject) {
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
