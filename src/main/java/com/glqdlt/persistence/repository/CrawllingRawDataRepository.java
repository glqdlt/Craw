package com.glqdlt.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.CrawllingRawDataDomain;

public interface CrawllingRawDataRepository extends JpaRepository<CrawllingRawDataDomain, Integer> {

}
