package com.glqdlt.crawling.stack.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser_2;
import com.glqdlt.crawling.stack.jsoup.parsers.ppompu_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.ruriweb_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.yepannet_parser;
import com.glqdlt.crawling.target.stack.Coolenjoy_news;
import com.glqdlt.crawling.target.stack.Coolenjoy_tuckga;
import com.glqdlt.crawling.target.stack.Ppompu_Coupon;
import com.glqdlt.crawling.target.stack.Ppompu_Tukga;
import com.glqdlt.crawling.target.stack.Ruriweb;
import com.glqdlt.crawling.target.stack.Yepannet_tuckga;
import com.glqdlt.crawling.target.stack.Yepannet_yeyak;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;

public class JsoupHandler extends JsoupFunctions {

	Logger logger = LoggerFactory.getLogger(JsoupHandler.class);

	public List<List<CrawRAWDataVO>> CrawllingScheduleMaker() {
		List<List<CrawRAWDataVO>> list = new ArrayList<>();

		NewDataCheckVO ruriweb_NVO = CheckNewHash(new Ruriweb());
		NewDataCheckVO cool_NVO = CheckNewHash(new Coolenjoy_tuckga());

		NewDataCheckVO cool_2_NVO = CheckNewHash(new Coolenjoy_news());

		NewDataCheckVO yepan_1_NVO = CheckNewHash(new Yepannet_tuckga());
		NewDataCheckVO yepan_2_NVO = CheckNewHash(new Yepannet_yeyak());
		NewDataCheckVO ppompu_1_NVO = CheckNewHash(new Ppompu_Tukga());

		NewDataCheckVO ppompu_2_NVO = CheckNewHash(new Ppompu_Coupon());
		if (ruriweb_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new ruriweb_parser().Doc_Parser(ruriweb_NVO);
			if (l.size() != 0) {
				list.add(l);
			}
		}
		if (cool_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new coolenjoy_parser().Doc_Parser(cool_NVO);
			if (l.size() != 0) {
				list.add(l);
			}
		}
		if (cool_2_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new coolenjoy_parser_2().Doc_Parser(cool_2_NVO);
			if (l.size() != 0) {
				list.add(l);
			}
		}
		if (yepan_1_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new yepannet_parser().Doc_Parser(yepan_1_NVO);
			if (l.size() != 0) {
				list.add(l);
			}

		}

		if (yepan_2_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new yepannet_parser().Doc_Parser(yepan_2_NVO);
			if (l.size() != 0) {
				list.add(l);
			}
		}

		if (ppompu_1_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new ppompu_parser().Doc_Parser(ppompu_1_NVO);
			if (l.size() != 0) {
				list.add(l);

			}
		}
		if (ppompu_2_NVO.GetCheck_boolean()) {
			List<CrawRAWDataVO> l = new ppompu_parser().Doc_Parser(ppompu_2_NVO);
			if (l.size() != 0) {
				list.add(l);

			}
		}

		return list;

	}

}
