package com.springboot.login.models.services;


import com.springboot.login.models.entitys.Usuario;

public interface UsuarioService {
	public Usuario findByNombreUsuario(String nombreUsuario);
	
}
