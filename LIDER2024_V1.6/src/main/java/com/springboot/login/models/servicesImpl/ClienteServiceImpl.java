package com.springboot.login.models.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.login.models.entitys.Cliente;
import com.springboot.login.models.repository.ClienteRepository;

@Service
public class ClienteServiceImpl {
	@Autowired
	private ClienteRepository repository; 
	
	public void guardarClient(Cliente cliente) {
		repository.save(cliente);
	}
	
	public Optional<Cliente>buscarPorId(int id){
		return repository.findById(id);
	}
	
	public List<Cliente> listaC() {
		return repository.findAll();
	}

}
