package com.glqdlt.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;

public interface LastBoardDataRepo extends JpaRepository<CrawllingRawDataDomain, Integer> {

	List<CrawllingRawDataDomain> findByBoardNo(Integer boardNo);
}
