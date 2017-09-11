package com.glqdlt.data;

public class LastCrawllingdataVO {

	private int site_tag;
	private int data_tag;
	private int last_craw_no;
	private String last_craw_hash;
	private String url;
	private String value;

	public String getLast_craw_hash() {
		return last_craw_hash;
	}

	public void setLast_craw_hash(String last_craw_hash) {
		this.last_craw_hash = last_craw_hash;
	}

	public int getLast_craw_no() {
		return last_craw_no;
	}

	public void setLast_craw_no(int last_craw_no) {
		this.last_craw_no = last_craw_no;
	}

	public int getData_tag() {
		return data_tag;
	}

	public void setData_tag(int data_tag) {
		this.data_tag = data_tag;
	}

	public int getSite_tag() {
		return site_tag;
	}

	public void setSite_tag(int site_tag) {
		this.site_tag = site_tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
