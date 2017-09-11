package com.glqdlt.crawling.target.stack;

public class Coolenjoy_tuckga implements CrawllingTarget {

	private int site_tag;
	private int data_tag;
	private String site_name;
	private String data_name;
	private String target_url;
	
	public Coolenjoy_tuckga() {
		site_name = "쿨엔조이";
		site_tag = 2;
		data_name = "특가 정보";
		data_tag = 1;
		target_url = "http://www.coolenjoy.net/bbs/29";
		
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
