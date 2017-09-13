package com.glqdlt.persistence.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "crawlling_target_tbl")
@Entity
public class CrawllingTargetDomain {
	
	public CrawllingTargetDomain() {
	}

	public CrawllingTargetDomain(String url, String data_name, Integer data_tag, String site_name,
			Integer site_tag, Integer craw_type) {
		this.url = url;
		this.data_name = data_name;
		this.data_tag = data_tag;
		this.site_name = site_name;
		this.site_tag = site_tag;
		this.craw_type = craw_type;
	}

	@Id
	@GeneratedValue
	private int no;

	private String url;
	private String data_name;
	private Integer data_tag;
	private String site_name;
	private Integer site_tag;
	private Integer craw_type;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData_name() {
		return data_name;
	}

	public void setData_name(String data_name) {
		this.data_name = data_name;
	}

	public Integer getData_tag() {
		return data_tag;
	}

	public void setData_tag(Integer data_tag) {
		this.data_tag = data_tag;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public Integer getSite_tag() {
		return site_tag;
	}

	public void setSite_tag(Integer site_tag) {
		this.site_tag = site_tag;
	}

	public Integer getCraw_type() {
		return craw_type;
	}

	public void setCraw_type(Integer craw_type) {
		this.craw_type = craw_type;
	}

}
