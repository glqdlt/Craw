package com.glqdlt.persistence.data;

import java.util.List;
import java.util.concurrent.Future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FutureData {

	private Future<List<CrawllingRawDataDomain>> future; 
	private CrawllingTargetDomain cDomain;

}
