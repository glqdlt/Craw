package com.glqdlt.persistence.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crawlling_target_tbl")
@Entity
public class CrawllingTargetDomain {

	@Id
	@GeneratedValue
	private int craw_no;

	private String url;
	private String data_name;
	private Integer data_tag;
	private String site_name;
	private Integer site_tag;
	private Integer craw_type;
	private Integer last_board_no;

}
