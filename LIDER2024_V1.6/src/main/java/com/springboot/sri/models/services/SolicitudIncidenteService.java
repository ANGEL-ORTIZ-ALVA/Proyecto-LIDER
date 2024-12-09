package com.springboot.sri.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sri.models.entitys.SolicitudIncidente;
import com.springboot.sri.models.repository.SolicitudIncidenteRepository;

@Service
public class SolicitudIncidenteService {
	@Autowired
	private SolicitudIncidenteRepository solicitudIncidenteRepository;
	
	 public List<SolicitudIncidente> listarIncidentes() {
	        return solicitudIncidenteRepository.findAll();
	    }
}