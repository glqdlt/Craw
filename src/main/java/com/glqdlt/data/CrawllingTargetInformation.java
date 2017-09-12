package com.glqdlt.data;

public class CrawllingTargetInformation {

	@Override
	public String toString() {
		return "CrawllingTargetInformation [site_tag=" + site_tag + ", data_tag=" + data_tag + ", site_name="
				+ site_name + ", data_name=" + data_name + ", target_url=" + target_url + "]";
	}

	private int site_tag;
	private int data_tag;
	private String site_name;
	private String data_name;
	private String target_url;

	public int get_site_tag() {
		return site_tag;
	}

	public void set_site_tag(int site_tag) {
		this.site_tag = site_tag;
	}

	public int get_data_tag() {
		return data_tag;
	}

	public void set_data_tag(int data_tag) {
		this.data_tag = data_tag;
	}

	public String get_site_name() {
		return site_name;
	}

	public void set_site_name(String site_name) {
		this.site_name = site_name;

	}

	public String get_data_name() {
		return data_name;
	}

	public void set_data_name(String data_name) {
		this.data_name = data_name;

	}

	public String get_target_url() {
		return target_url;
	}

	public void set_target_url(String target_url) {
		this.target_url = target_url;

	}

}
