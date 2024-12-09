package com.springboot.sri.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.sri.models.entitys.SolicitudIncidente;

@Repository
public interface SolicitudIncidenteRepository extends JpaRepository<SolicitudIncidente, Integer>{

}