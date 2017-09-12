package glqdlt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.glqdlt.crawling.jsoup.parsers.PpompuParser;
import com.glqdlt.crawling.jsoup.parsers.RuriwebParser;
import com.glqdlt.crawling.target.PpompuCoupon;
import com.glqdlt.crawling.target.PpompuTukga;
import com.glqdlt.crawling.target.Ruriweb;
import com.glqdlt.persistence.data.CrawllingObject;

public class FunctionTest {
	
	
	@Test
	public void test_ruriweb(){
		new RuriwebParser().startJob(Ruriweb.target_url);
	}

//	@Test
	public void test_ppompu() {

		List<CrawllingObject> l = new PpompuParser().startJob(PpompuCoupon.target_url);
		List<CrawllingObject> l_2 = new PpompuParser().startJob(PpompuTukga.target_url);
		l.forEach(x -> System.out.println(x.toString()));
		System.out.println("=======");
		l_2.forEach(x -> System.out.println(x.toString()));

	}

}
