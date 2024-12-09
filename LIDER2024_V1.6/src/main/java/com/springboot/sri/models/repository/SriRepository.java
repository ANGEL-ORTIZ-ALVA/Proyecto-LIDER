package com.springboot.sri.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sri.models.entitys.Sri;

@Repository
public interface SriRepository extends JpaRepository<Sri, Integer>{
	
	List<Sri> findByEstado(Sri.Estado estado);
	
}
