package com.springboot.login.models.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.login.models.entitys.Personales;
import com.springboot.login.models.repository.PersonalesRepository;

@Service
public class PersonalServiceImpl{
	@Autowired
	private PersonalesRepository repository;

	public void guardarPersonal(Personales personales) {
		repository.save(personales);
	}

	public List<Personales> listarP() {
		return repository.findAll();
	}
	
	public Optional<Personales> buscarID(int id){
		return repository.findById(id);
	}

}
