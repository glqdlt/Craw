package com.glqdlt.crawling.target.stack;

public class Ppompu_Coupon implements CrawllingTarget {

	private int site_tag;
	private int data_tag;
	private String site_name;
	private String data_name;
	private String target_url;

	public Ppompu_Coupon() {
		site_name = "뽐뿌";
		site_tag = 4;
		data_name = "쿠폰 정보";
		data_tag = 6;
		target_url = "http://www.ppomppu.co.kr/zboard/zboard.php?id=coupon";
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
