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

import com.glqdlt.crawling.stack.jsoup.JsoupFunctions;
import com.glqdlt.crawling.stack.jsoup.JsoupParser;
import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public class ppompu_parser extends JsoupFunctions implements JsoupParser {

	private String column_no_regex(String link) {
		String regex = "no(=)[0-9]*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(link);
		m.find();
		String find = m.group();
		try {
			find = find.replace("no=", "");
		} catch (IllegalStateException igone) {
			find = "0";
		}
		return find;
	}

	@Override
	public List<CrawRAWDataVO> Doc_Parser(NewDataCheckVO NDVO) {
		CrawllingTarget target = NDVO.getCrawllingTarget();
		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();

		int data_tag = target.get_data_tag();
		int site_tag = target.get_site_tag();

		int last_column_no = LastCrawllingData.getIns().getLastColumn_no(site_tag, data_tag);

		String subject;
		String link;
		String column_no;
		String href;
		Document doc;
		try {
			doc = Jsoup.connect(target.get_target_url()).get();
			Element table = doc.select("table[id=revolution_main_table]").get(0);
			Elements el = table.select("td[class=list_vspace]");
			for (Element element : el) {
				subject = (element.text());
				href = element.getElementsByTag("a").attr("href");
				if (href.equals("#")) {
					continue;
				}
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				link = "http://www.ppomppu.co.kr/zboard/" + href;
				column_no = column_no_regex(link);

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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<CrawRAWDataVO> Doc_Parser(String url) {
		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();

		int last_column_no = 1;

		String subject;
		String link;
		String column_no;
		String href;
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Element table = doc.select("table[id=revolution_main_table]").get(0);
			Elements el = table.select("td[class=list_vspace]");
			for (Element element : el) {
				subject = (element.text());
				href = element.getElementsByTag("a").attr("href");
				if (href.equals("#")) {
					continue;
				}
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				link = "http://www.ppomppu.co.kr/zboard/" + href;
				column_no = column_no_regex(link);

				CrawVO.setLink(link);
				CrawVO.setSubject(subject);
				CrawVO.setColumn_no(column_no);

				if (last_column_no < Integer.parseInt(column_no)) {
					list.add(CrawVO);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

}
