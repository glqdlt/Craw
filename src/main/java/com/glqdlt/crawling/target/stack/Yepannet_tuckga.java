package com.glqdlt.crawling.target.stack;

public class Yepannet_tuckga implements CrawllingTarget {

	private int site_tag;
	private int data_tag;
	private String site_name;
	private String data_name;
	private String target_url;

	public Yepannet_tuckga() {
		site_name = "예판넷";
		site_tag = 3;
		data_name = "특가 정보";
		data_tag = 1;
		target_url = "http://yepan.net/bbs/board.php?bo_table=comm_info&sca=&sca=%ED%8A%B9%ED%8C%90%EC%A0%95%EB%B3%B4";
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
