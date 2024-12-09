package com.springboot.login.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.login.models.entitys.Personales;

@Repository
public interface PersonalesRepository extends JpaRepository<Personales, Integer>{

}
