package com.glqdlt.crawling.target.stack;

public class Ruriweb implements CrawllingTarget {

	private int site_tag;
	private int data_tag;
	private String site_name;
	private String data_name;
	private String target_url;

	public Ruriweb() {
		site_name = "루리웹";
		site_tag = 1;
		data_name = "특가 정보";
		data_tag = 1;
		target_url = "http://bbs.ruliweb.com/market/board/1020";
	}
	
	@Override
	public int get_site_tag() {
		return site_tag;
	}


	@Override
	public int get_data_tag() {
		return data_tag;
	}


	@Override
	public String get_site_name() {
		return site_name;
	}


	@Override
	public String get_data_name() {
		return data_name;
	}


	@Override
	public String get_target_url() {
		return target_url;
	}


}
