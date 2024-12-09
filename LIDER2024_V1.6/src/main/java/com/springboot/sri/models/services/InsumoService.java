package com.springboot.sri.models.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sri.models.entitys.Insumo;
import com.springboot.sri.models.repository.InsumosRepository;

@Service
public class InsumoService {
	@Autowired
	private InsumosRepository insumosRepository;
	
	 public List<Insumo> listarInsumos() {
	        return insumosRepository.findAll();
	    }
}