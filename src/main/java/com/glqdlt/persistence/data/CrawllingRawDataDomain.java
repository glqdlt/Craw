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

	private Integer crawNo;
	private String subject;
	private String link;
	private String boardWriteDate;
	private Integer boardNo;
	private Integer dataTag;
	private Integer siteTag;
	private String dataName;
	private String siteName;

}
