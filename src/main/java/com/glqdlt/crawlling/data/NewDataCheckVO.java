package com.glqdlt.crawlling.data;

import org.jsoup.nodes.Document;

public class NewDataCheckVO {

	@Override
	public String toString() {
		return "NewDataCheckVO [Check_boolean=" + check_boolean + ", doc=" + doc + "]";
	}

	private boolean check_boolean;
	private Document doc;

	public boolean isCheck_boolean() {
		return check_boolean;
	}

	public void setCheck_boolean(boolean check_boolean) {
		this.check_boolean = check_boolean;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
