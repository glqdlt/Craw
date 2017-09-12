package com.glqdlt.persistence.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crawlling_object_tbl")
public class CrawllingObject {

	@Override
	public String toString() {
		return "CrawllingObject [subject=" + subject + ", link=" + link + ", write_date=" + board_write_date
				+ ", boardNo=" + board_no  + ", data_tag=" + data_tag + ", site_tag="
				+ site_tag + ", data_name=" + data_name + ", site_name=" + site_name + "]";
	}

	@Id
	@GeneratedValue
	private Integer no;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	private String subject;
	private String link;
	private String board_write_date;
	private String board_no;
	private Integer data_tag;
	private Integer site_tag;
	private String data_name;
	private String site_name;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBoard_write_date() {
		return board_write_date;
	}

	public void setBoard_write_date(String board_write_date) {
		this.board_write_date = board_write_date;
	}

	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	public String getboard_no() {
		return board_no;
	}

	public void setboard_no(String boardNo) {
		this.board_no = boardNo;
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

	public String getData_name() {
		return data_name;
	}

	public void setData_name(String data_name) {
		this.data_name = data_name;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

}
