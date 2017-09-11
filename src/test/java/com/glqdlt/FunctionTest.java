package com.glqdlt;

import java.util.List;

import org.junit.Test;

import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser_2;
import com.glqdlt.crawling.stack.jsoup.parsers.ppompu_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.ruriweb_parser;
import com.glqdlt.crawling.target.stack.Ppompu_Coupon_static;
import com.glqdlt.crawling.target.stack.Ppompu_Tukga_static;
import com.glqdlt.data.CrawRAWDataVO;

public class FunctionTest {

	@Test
	public void test_cool_new() {

		List<CrawRAWDataVO> l = new coolenjoy_parser_2().Doc_Parser();
		l.forEach(x -> System.out.println(x.getSubject() + "||" + x.getLink() + "||" + x.getColumn_no()));
	}

	// @Test
	public void test_cool_tuck() {

		List<CrawRAWDataVO> l = new coolenjoy_parser().Doc_Parser();
		l.forEach(x -> System.out.println(x.getSubject() + "||" + x.getLink() + "||" + x.getColumn_no()));

	}

	// @Test
	public void test_ruri() {

		List<CrawRAWDataVO> l = new ruriweb_parser().Doc_Parser();
		l.forEach(x -> System.out.println(x.getSubject() + "||" + x.getLink() + "||" + x.getColumn_no()));
	}

	// @Test
	public void test_ppompu() {

		List<CrawRAWDataVO> l = new ppompu_parser().Doc_Parser(Ppompu_Coupon_static.target_url);
		List<CrawRAWDataVO> l_2 = new ppompu_parser().Doc_Parser(Ppompu_Tukga_static.target_url);
		l.forEach(x -> System.out.println(x.getSubject() + "||" + x.getLink() + "||" + x.getColumn_no()));
		System.out.println("=======");
		l_2.forEach(x -> System.out.println(x.getSubject() + "||" + x.getLink() + "||" + x.getColumn_no()));

	}

}
