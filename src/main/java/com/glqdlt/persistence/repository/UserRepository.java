package com.glqdlt.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glqdlt.persistence.data.UserDomain;

public interface UserRepository extends JpaRepository<UserDomain, Integer> {
	
	

}
