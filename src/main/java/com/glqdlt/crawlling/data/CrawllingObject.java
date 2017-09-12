package com.glqdlt.crawlling.data;

public class CrawllingObject {

	@Override
	public String toString() {
		return "CrawllingObject [subject=" + subject + ", link=" + link + ", write_date=" + board_write_date
				+ ", price=" + price + ", boardNo=" + board_no + ", sale_pariod=" + sale_pariod + ", data_tag="
				+ data_tag + ", site_tag=" + site_tag + ", data_name=" + data_name + ", site_name=" + site_name + "]";
	}

	private String subject;
	private String link;
	private String board_write_date;
	private String price;
	private String board_no;
	private String sale_pariod;
	private int data_tag;
	private int site_tag;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getboard_no() {
		return board_no;
	}

	public void setboard_no(String boardNo) {
		this.board_no = boardNo;
	}

	public String getSale_pariod() {
		return sale_pariod;
	}

	public void setSale_pariod(String sale_pariod) {
		this.sale_pariod = sale_pariod;
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
