package com.glqdlt.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.entity.CrawRawDataEntity;

public interface LastBoardDataRepo extends JpaRepository<CrawRawDataEntity, Integer> {

	List<CrawRawDataEntity> findByBoardNo(Integer boardNo);
}
