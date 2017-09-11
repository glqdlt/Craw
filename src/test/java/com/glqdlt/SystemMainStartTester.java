package com.glqdlt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.glqdlt.crawling.stack.jsoup.JsoupHandler;
import com.glqdlt.data.CrawRAWDataVO;
import com.glqdlt.mail.MailTimer;
import com.glqdlt.mail.gmail.GMailSender;
import com.glqdlt.system.LastCrawllingData;
import com.glqdlt.user.MailUserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class SystemMainStartTester {
	Logger logger = LoggerFactory.getLogger(SystemMainStartTester.class);

	@Test
	public void start_jsoup_crawlling_test() throws InterruptedException {
		while (true) {
			logger.info(".. .. ..");
			LastCrawllingData.getIns().setList(null);
			JsoupHandler j = new JsoupHandler();
			List<List<CrawRAWDataVO>> list = j.CrawllingScheduleMaker();
			if (list.size() == 0) {
				j = null;
				list = null;
				/**
				 * 1 minute sleep...
				 */
				Thread.sleep(MailTimer.GetMillsecond(0, 1, 0));
				continue;
			}
//			List<MailUserVO> mail_user_list = dao.get_mailuser_list();
			List<MailUserVO> mail_user_list = null;
			logger.info("New_Crawlling data! Database Insert && Send Email...");
			for (List<CrawRAWDataVO> CRDVO_l : list) {
//				dao.insertcrawlingdata(CRDVO_l);
			}
			GMailSender.MailSend(list, mail_user_list);
			logger.info("Done!");
			Thread.sleep(MailTimer.GetMillsecond(0, 30, 0));
		}
	}
}
