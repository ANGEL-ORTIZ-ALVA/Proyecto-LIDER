package com.springboot.login.models.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.login.models.entitys.Roles;
import com.springboot.login.models.repository.RolesRepository;

@Service
public class RolServiceImpl {
	@Autowired
	private RolesRepository repository;
	
	public List<Roles>listarRoles(){
		return repository.findAll();
	}
	
	public Optional<Roles> buscarID(int id) {
		return repository.findById(id);
	}

}
