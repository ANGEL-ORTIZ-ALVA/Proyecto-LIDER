package com.springboot.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.springboot.login.models.entitys.Usuario;
import com.springboot.login.models.repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombreUsuario(username)
				.orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado: "+username));
		return User.withUsername(usuario.getNombreUsuario())
				.password(usuario.getContrasena())
				.roles(usuario.getTipoUsuario().name())
				.build();
	}

}
