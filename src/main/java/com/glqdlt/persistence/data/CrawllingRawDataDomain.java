package com.glqdlt.persistence.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "crawlling_raw_data_tbl")
public class CrawllingRawDataDomain {

	@Id
	@GeneratedValue
	private Integer no;

	private Integer craw_no;
	private String subject;
	private String link;
	private String board_write_date;
	private String board_no;
	private Integer data_tag;
	private Integer site_tag;
	private String data_name;
	private String site_name;

}
