package com.glqdlt.system;

import java.util.List;

import com.glqdlt.persistence.data.LastCrawllingdataVO;

public class LastCrawllingData {

	private List<LastCrawllingdataVO> list;
	private static LastCrawllingData ins;

	private LastCrawllingData() {

	}

	public static LastCrawllingData getIns() {
		if (ins == null) {
			ins = new LastCrawllingData();
		}
		return ins;
	}

	public List<LastCrawllingdataVO> getList() {
		return list;
	}

	public synchronized void setList(List<LastCrawllingdataVO> list) {
		this.list = list;
	}

	public String getLastHash(int site_tag, int data_tag) {
		String old_hash = "";
		for (LastCrawllingdataVO lvo : list) {
			if (lvo.getSite_tag() == site_tag) {
				if (lvo.getData_tag() == data_tag) {
					old_hash = lvo.getLast_craw_hash();
				}
			}

		}
		return old_hash;
	}

	public int getLastColumn_no(int site_tag, int data_tag) {
		int old_column_no = 0;
		for (LastCrawllingdataVO lvo : list) {
			if (lvo.getSite_tag() == site_tag) {
				if (lvo.getData_tag() == data_tag) {
					old_column_no = lvo.getLast_craw_no();
				}
			}

		}
		return old_column_no;
	}

}
