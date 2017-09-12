package com.glqdlt.data;

import org.jsoup.nodes.Document;

public class NewDataCheckVO {

	@Override
	public String toString() {
		return "NewDataCheckVO [Check_boolean=" + Check_boolean + ", doc=" + doc + "]";
	}

	private boolean Check_boolean;
	private Document doc;

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

}
