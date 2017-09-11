package com.glqdlt;

import java.util.List;

import org.junit.Test;

import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser;
import com.glqdlt.crawling.stack.jsoup.parsers.coolenjoy_parser_2;
import com.glqdlt.data.CrawRAWDataVO;

public class FunctionTest {

//	@Test
	public void test_cool_new() {

		List<CrawRAWDataVO> l = new coolenjoy_parser_2().Doc_Parser();
		l.forEach(x -> System.out.println(x.getSubject()));

	}
	@Test
	public void test_cool_tuck() {
		
		List<CrawRAWDataVO> l = new coolenjoy_parser().Doc_Parser();
		l.forEach(x -> System.out.println(x.getSubject()));
		
	}
}
