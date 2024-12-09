package com.springboot.sri.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sri.models.entitys.Personal;
import com.springboot.sri.models.repository.PersonalRepository;

@Service
public class PersonalService {
	@Autowired
	private PersonalRepository personalRepository;
	
	 public List<Personal> listarPersonal() {
	        return personalRepository.findAll();
	    }
}
