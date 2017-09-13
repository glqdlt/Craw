package com.glqdlt.crawling.jsoup.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import com.glqdlt.persistence.data.CrawllingTargetDomain;
import com.glqdlt.persistence.service.CrawllingJobService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class PpompuParser extends ParserUtill implements Callable<List<CrawllingRawDataDomain>> {

	private static final Logger log = LoggerFactory.getLogger(PpompuParser.class);

	
	@Autowired
	CrawllingJobService cService;
	private CrawllingTargetDomain cDomain;

	public PpompuParser(CrawllingTargetDomain cDomain) {
		this.cDomain = cDomain;
	}

	@Override
	public List<CrawllingRawDataDomain> startJob(CrawllingTargetDomain cDomain) {
		List<CrawllingRawDataDomain> list = new ArrayList<CrawllingRawDataDomain>();

		int last_column_no = 1;

		String subject = null;
		String link = null;
		String boardNo = null;
		String href = null;
		Document doc = null;
		try {
			doc = Jsoup.connect(cDomain.getUrl()).get();
			Element table = doc.select("table[id=revolution_main_table]").get(0);
			Elements el = table.select("td[class=list_vspace]");
			for (Element element : el) {
				subject = (element.text());
				href = element.getElementsByTag("a").attr("href");
				if (href.equals("#")) {
					log.debug("find '#' break to continue.");
					continue;
				}
				CrawllingRawDataDomain crawObj = new CrawllingRawDataDomain();
				link = "http://www.ppomppu.co.kr/zboard/" + href;
				boardNo = boardNoRegex(link);

				crawObj.setLink(link);
				crawObj.setSubject(subject);
				crawObj.setBoard_no(parserBoardNo(boardNo));

				// crawObj.setData_name(cDomain.getData_name());
				// crawObj.setData_tag(cDomain.getData_tag());
				// crawObj.setSite_name(cDomain.getSite_name());
				// crawObj.setSite_tag(cDomain.getSite_tag());
				// crawObj.setCraw_no(cDomain.getCraw_no());
				setCommonData(crawObj, cDomain);

				if (last_column_no < Integer.parseInt(boardNo)) {
					list.add(crawObj);
				}
			}
		} catch (IOException e) {
			log.error("Parser Error.." + e);
		}
		return list;

	}

	private String boardNoRegex(String link) {
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
	public List<CrawllingRawDataDomain> call() throws Exception {
		// TODO Auto-generated method stub
		return startJob(cDomain);
	}

}
