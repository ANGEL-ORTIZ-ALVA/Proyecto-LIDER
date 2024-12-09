package com.springboot.login.models.servicesImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.login.models.entitys.Usuario;
import com.springboot.login.models.repository.UsuarioRepository;
import com.springboot.login.models.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository repository;

	public void guardarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return repository.findById(id);
    }


	@Override
	public Usuario findByNombreUsuario(String nombreUsuario) {
		return repository.findByNombreUsuario(nombreUsuario).orElse(null);
	}

	public List<Usuario> listar(){
		return repository.findAll();
	}

}
