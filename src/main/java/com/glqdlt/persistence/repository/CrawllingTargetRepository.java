package com.glqdlt.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.CrawllingTargetDomain;

public interface CrawllingTargetRepository extends JpaRepository<CrawllingTargetDomain, Integer> {

}
