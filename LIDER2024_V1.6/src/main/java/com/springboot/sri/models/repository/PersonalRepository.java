package com.springboot.sri.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sri.models.entitys.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

}
