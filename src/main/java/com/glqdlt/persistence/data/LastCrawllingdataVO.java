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

	private int siteTag;
	private int dataTag;
	private int lastCrawNo;
	private String lastCrawHash;
	private String url;
	private String value;

}
