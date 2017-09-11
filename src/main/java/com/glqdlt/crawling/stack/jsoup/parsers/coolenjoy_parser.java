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
import com.glqdlt.crawling.target.stack.Coolenjoy_tuckga_static;
import com.glqdlt.crawling.target.stack.CrawllingTarget;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;
import com.glqdlt.system.LastCrawllingData;

public class coolenjoy_parser extends JsoupFunctions implements JsoupParser {

	Logger logger = LoggerFactory.getLogger(coolenjoy_parser.class);

	@Override
	public List<CrawRAWDataVO> Doc_Parser(NewDataCheckVO NDVO) {
		CrawllingTarget target = NDVO.getCrawllingTarget();
		
		int data_tag = target.get_data_tag();
		int site_tag = target.get_site_tag();
		
		int last_column_no = LastCrawllingData.getIns().getLastColumn_no(site_tag, data_tag);

		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();
		try {
			Document doc = Jsoup.connect(target.get_target_url()).get();
			String subject;
			String full_text;
			String sale_pariod;
			String link;
			String price;
			String column_no;
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
				price = element.getElementsByClass("td_won").text();

				full_text = element.getElementsByClass("td_subject").text();
				full_text = full_text.replace("마감 |", "");
				full_text = full_text.replace("예정 |", "");
				full_text = full_text.replace("진행 |", "").trim();

				subject = full_text.substring(0, (full_text.lastIndexOf("댓글")));
				sale_pariod = full_text.substring(full_text.indexOf("일정:") + 3, (full_text.length()));
				column_no = link.substring((link.lastIndexOf("/") + 1), link.length());
				CrawVO.setSale_pariod(sale_pariod);
				CrawVO.setLink(link);
				CrawVO.setPrice(price);
				CrawVO.setSubject(subject);
				CrawVO.setColumn_no(column_no);
				CrawVO.setSite_tag(target.get_site_tag());
				CrawVO.setData_tag(target.get_data_tag());
				CrawVO.setSite_name(target.get_site_name());
				CrawVO.setData_name(target.get_data_name());

				if(last_column_no < Integer.parseInt(column_no)){
					list.add(CrawVO);
				}

				CrawVO = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public List<CrawRAWDataVO> Doc_Parser() {
		
		int data_tag = Coolenjoy_tuckga_static.data_tag;
		int site_tag = Coolenjoy_tuckga_static.site_tag;
		String site_name = Coolenjoy_tuckga_static.site_name;
		String data_name = Coolenjoy_tuckga_static.data_name;
		
		
		// 나중에 a href= http:~~~/bbs/123412 이런식으로.. table no 로 보이는 숫자 필드가 있는 데, 이 정보를 기억해서 신규 게시글인지 확인이 가능할 것으로 보여짐.
		int last_column_no = 1;
		
		List<CrawRAWDataVO> list = new ArrayList<CrawRAWDataVO>();
		try {
			Document doc = Jsoup.connect(Coolenjoy_tuckga_static.target_url).get();
			String subject;
			String full_text;
			String sale_pariod;
			String link;
			String price;
			String column_no;
			Elements tbodys = doc.getElementsByTag("tbody");
			Element tbody = tbodys.get(0);
			Elements trs = tbody.getElementsByTag("tr");
			for (Element element : trs) {
				CrawRAWDataVO CrawVO = new CrawRAWDataVO();
				link = element.getElementsByClass("td_num").get(0).getElementsByTag("a").get(0).attr("href").toString();
				price = element.getElementsByClass("td_won").text();
				
				full_text = element.getElementsByClass("td_subject").text();
				full_text = full_text.replace("마감 |", "");
				full_text = full_text.replace("예정 |", "");
				full_text = full_text.replace("진행 |", "").trim();
				
				subject = full_text.substring(0, (full_text.lastIndexOf("댓글")));
				sale_pariod = full_text.substring(full_text.indexOf("일정:") + 3, (full_text.length()));
				column_no = link.substring((link.lastIndexOf("/") + 1), link.length());
				CrawVO.setSale_pariod(sale_pariod);
				CrawVO.setLink(link);
				CrawVO.setPrice(price);
				CrawVO.setSubject(subject);
				CrawVO.setColumn_no(column_no);
				CrawVO.setSite_tag(site_tag);
				CrawVO.setData_tag(data_tag);
				CrawVO.setSite_name(site_name);
				CrawVO.setData_name(data_name);
				
				if(last_column_no < Integer.parseInt(column_no)){
					list.add(CrawVO);
				}
				
				CrawVO = null;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
