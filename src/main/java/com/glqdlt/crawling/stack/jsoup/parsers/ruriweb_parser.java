package com.glqdlt.crawling.stack.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glqdlt.crawling.stack.jsoup.JsoupFunctions;
import com.glqdlt.crawling.stack.jsoup.JsoupParser;
import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.crawling.target.stack.Ruriweb_static;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public class ruriweb_parser extends JsoupFunctions implements JsoupParser {

	Logger logger = LoggerFactory.getLogger(ruriweb_parser.class);

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
			Elements elemnts = doc.getElementsByClass("board_list_table");
			elemnts = elemnts.get(0).getElementsByClass("table_body");

			for (Element el : elemnts) {
				Elements elements_2 = el.getElementsByClass("table_body");
				for (Element element2 : elements_2) {
					if (element2.className().equals("table_body")) {
						CrawRAWDataVO CrawVO = new CrawRAWDataVO();
						link = el.getElementsByClass("subject").get(0).getElementsByClass("deco").attr("href");
						write_date = el.getElementsByClass("time").text();
						subject = el.getElementsByClass("subject").text();
						subject = FindReply(subject);
						column_no = el.getElementsByClass("id").text();

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

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<CrawRAWDataVO> Doc_Parser() {
		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();

		int data_tag = Ruriweb_static.data_tag;
		int site_tag = Ruriweb_static.site_tag;
		String data_name = Ruriweb_static.data_name;
		String site_name = Ruriweb_static.site_name;

		
		// 이놈도 확실히  링크 주소 끝에 보면 no 로 보이는 숫자 필드가 있음.
		int last_column_no = 1;

		String subject;
		String write_date;
		String link;
		String column_no;
		try {
			Document doc = Jsoup.connect(Ruriweb_static.target_url).get();
			Elements elemnts = doc.getElementsByClass("board_list_table");
			elemnts = elemnts.get(0).getElementsByClass("table_body");

			for (Element el : elemnts) {
				Elements elements_2 = el.getElementsByClass("table_body");
				for (Element element2 : elements_2) {
					if (element2.className().equals("table_body")) {
						CrawRAWDataVO CrawVO = new CrawRAWDataVO();
						link = el.getElementsByClass("subject").get(0).getElementsByClass("deco").attr("href");
						write_date = el.getElementsByClass("time").text();
						subject = el.getElementsByClass("subject").text();
						subject = FindReply(subject);
						column_no = el.getElementsByClass("id").text();

						CrawVO.setwrite_date(write_date);
						CrawVO.setLink(link);
						CrawVO.setSubject(subject);
						CrawVO.setColumn_no(column_no);
						CrawVO.setSite_tag(site_tag);
						CrawVO.setData_tag(data_tag);
						CrawVO.setSite_name(site_name);
						CrawVO.setData_name(data_name);

						if (last_column_no < Integer.parseInt(column_no)) {
							list.add(CrawVO);
						}
						CrawVO = null;
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
