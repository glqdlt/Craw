package com.glqdlt.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.CrawllingObject;

public interface CrawllingRepository extends JpaRepository<CrawllingObject, Integer> {

}
