package com.glqdlt.crawling.stack.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.glqdlt.crawling.stack.jsoup.JsoupFunctions;
import com.glqdlt.crawling.target.stack.Coolenjoy_news_static;
import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public class coolenjoy_parser_2 extends JsoupFunctions {

	public List<CrawRAWDataVO> Doc_Parser() {
		int data_tag = Coolenjoy_news_static.data_tag;
		int site_tag = Coolenjoy_news_static.site_tag;
		String site_name = Coolenjoy_news_static.site_name;
		String data_name = Coolenjoy_news_static.data_name;
		
//		나중에 a href= http:~~~/bbs/123412 이런식으로.. table no 로 보이는 숫자 필드가 있는 데, 이 정보를 기억해서 신규 게시글인지 확인이 가능할 것으로 보여짐.
		int last_column_no = 1;

		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();
		try {
			Document doc = Jsoup.connect(Coolenjoy_news_static.target_url).get();
			Elements trElements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");

			String subject = "";
			String link = "";
			String date = "";
			String column_no = "";

			for (Element tr : trElements) {
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				Elements tds = tr.getElementsByTag("td");

				if (tds.hasClass("td_subject")) {

					for (Element element2 : tds) {

						if (element2.className().equals("td_subject")) {
							subject = element2.text();
							link = element2.getElementsByTag("a").get(0).attr("href");
							column_no = link.substring((link.lastIndexOf("/") + 1), link.length());

						}
						if (element2.className().equals("td_date")) {
							date = element2.text();

						}

					}

					CrawVO.setSubject(subject);
					CrawVO.setwrite_date(date);
					CrawVO.setLink(link);
					CrawVO.setColumn_no(column_no);
					CrawVO.setData_tag(data_tag);
					CrawVO.setSite_tag(site_tag);
					CrawVO.setSite_name(site_name);
					CrawVO.setData_name(data_name);

					if (last_column_no < Integer.parseInt(column_no)) {
						list.add(CrawVO);
					}
				}
				CrawVO = null;

			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<CrawRAWDataVO> Doc_Parser(NewDataCheckVO NDVO) {
		CrawllingTarget target = NDVO.getCrawllingTarget();

		int data_tag = target.get_data_tag();
		int site_tag = target.get_site_tag();

		int last_column_no = LastCrawllingData.getIns().getLastColumn_no(site_tag, data_tag);

		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();
		try {
			Document doc = Jsoup.connect(target.get_target_url()).get();
			Elements trElements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");

			String subject = "";
			String link = "";
			String date = "";
			String column_no = "";

			for (Element tr : trElements) {
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				Elements tds = tr.getElementsByTag("td");

				if (tds.hasClass("td_subject")) {

					for (Element element2 : tds) {

						if (element2.className().equals("td_subject")) {
							subject = element2.text();
							link = element2.getElementsByTag("a").get(0).attr("href");
							column_no = link.substring((link.lastIndexOf("/") + 1), link.length());

						}
						if (element2.className().equals("td_date")) {
							date = element2.text();

						}

					}

					CrawVO.setSubject(subject);
					CrawVO.setwrite_date(date);
					CrawVO.setLink(link);
					CrawVO.setColumn_no(column_no);
					CrawVO.setData_tag(data_tag);
					CrawVO.setSite_tag(site_tag);
					CrawVO.setSite_tag(target.get_site_tag());
					CrawVO.setData_tag(target.get_data_tag());
					CrawVO.setSite_name(target.get_site_name());
					CrawVO.setData_name(target.get_data_name());

					if (last_column_no < Integer.parseInt(column_no)) {
						list.add(CrawVO);
					}
				}
				CrawVO = null;

			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
