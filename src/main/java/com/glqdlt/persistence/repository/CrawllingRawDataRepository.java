package com.glqdlt.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;
import java.lang.Integer;
import java.util.List;

public interface CrawllingRawDataRepository extends JpaRepository<CrawllingRawDataDomain, Integer> {
	List<CrawllingRawDataDomain> findByCrawNo(Integer CrawNo);
}
