package com.glqdlt.persistence.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class LastCrawllingdataVO {

	private int site_tag;
	private int data_tag;
	private int last_craw_no;
	private String last_craw_hash;
	private String url;
	private String value;

}
