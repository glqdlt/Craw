package com.glqdlt.crawling.stack.jsoup;

import java.util.List;

import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.data.NewDataCheckVO;

public interface JsoupParser {

	public List<CrawRAWDataVO> Doc_Parser(NewDataCheckVO NDVO);
	
}
