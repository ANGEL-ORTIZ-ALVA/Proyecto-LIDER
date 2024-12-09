package com.springboot.lider.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.lider.models.dao.UsuariosDaoImpl;
import com.springboot.lider.models.entitys.Usuario;

@Controller
public class ControllerUsuario {
	 @Autowired
	    private UsuariosDaoImpl dao;

	    @GetMapping("/usuarios")
	    public String listarUsuarios(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Usuario> usuariosPage = dao.listar(pageable);
	        List<Usuario> listaUsuarios = usuariosPage.getContent();
	        modelo.addAttribute("listaUsuarios", listaUsuarios);
	        modelo.addAttribute("currentPage", page);
	        modelo.addAttribute("pageSize", size);
	        modelo.addAttribute("totalPages", usuariosPage.getTotalPages());
	        int totalUsuarios = dao.contarUsu();
			modelo.addAttribute("totalUsu", totalUsuarios);
			modelo.addAttribute("usuario", new Usuario());
	        return "/gestion-almacen/usuarios/lista-usuarios"; 
	    }

	    @GetMapping("/usuario/nuevo")
	    public String nuevoUsuario(Model modelo) {
	        modelo.addAttribute("usuario", new Usuario());
	        return "/gestion-almacen/usuarios/formulario-usuario";
	    }

	    @PostMapping("/usuarios/guardar")
	    public String guardarUsuario(@ModelAttribute Usuario usuario) {
	        dao.guardar(usuario);
	        return "redirect:/usuarios";
	    }

	    @GetMapping("/usuarios/editar/{id}")
	    public String editarUsuario(@PathVariable("id") int id, Model modelo) {
	        Usuario usuario = dao.buscarPorId(id);
	        modelo.addAttribute("usuario", usuario);
	        return "/gestion-almacen/usuarios/editar-usuario";
	    }

	    @PostMapping("/usuarios/actualizar")
	    public String actualizarUsuario(@ModelAttribute Usuario usuario) {
	        // Recuperar el usuario existente de la base de datos
	        Usuario usuarioExistente = dao.buscarPorId(usuario.getId());

	        // Actualizar solo los campos necesarios
	        usuarioExistente.setCorreoElectronico(usuario.getCorreoElectronico());
	        usuarioExistente.setTelefono(usuario.getTelefono());
	        usuarioExistente.setEstado(usuario.getEstado());

	        // Guardar el usuario actualizado en la base de datos
	        dao.actualizar(usuarioExistente);
	        
	        return "redirect:/usuarios";
	    }


	    @GetMapping("/usuarios/borrar/{id}")
	    public String borrarUsuario(@PathVariable("id") int id) {
	        dao.eliminar(id);
	        return "redirect:/usuarios";
	    }
}
