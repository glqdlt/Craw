package com.glqdlt.data;

import org.jsoup.nodes.Document;

import com.glqdlt.crawling.target.stack.CrawllingTarget;

public class NewDataCheckVO {

	private boolean Check_boolean;
	private Document doc;
	private CrawllingTarget CrawllingTarget;
	


	public boolean GetCheck_boolean() {
		return Check_boolean;
	}

	public void setCheck_boolean(boolean check) {
		Check_boolean = check;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public CrawllingTarget getCrawllingTarget() {
		return CrawllingTarget;
	}

	public void setCrawllingTarget(CrawllingTarget cto) {
		this.CrawllingTarget = cto;
	}

}
