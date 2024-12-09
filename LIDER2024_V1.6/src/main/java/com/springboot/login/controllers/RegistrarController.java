package com.springboot.login.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springboot.login.models.entitys.Cliente;
import com.springboot.login.models.entitys.Personales;
import com.springboot.login.models.entitys.Roles;
import com.springboot.login.models.entitys.Usuario;
import com.springboot.login.models.servicesImpl.ClienteServiceImpl;
import com.springboot.login.models.servicesImpl.PersonalServiceImpl;
import com.springboot.login.models.servicesImpl.RolServiceImpl;
import com.springboot.login.models.servicesImpl.UsuarioServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrarController {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	@Autowired
	private PersonalServiceImpl personalService;
	@Autowired
	private RolServiceImpl rolService;
	@Autowired
	private ClienteServiceImpl clienteService;

	private List<Usuario> filtrarUsuariosDisponibles(List<Usuario> usuarios, List<Cliente> clientes,
			List<Personales> personales) {
		List<Integer> idsClientes = clientes.stream().map(c -> c.getUsuario().getId()).collect(Collectors.toList());
		List<Integer> idsPersonal = personales.stream().map(p -> p.getUsuario().getId()).collect(Collectors.toList());

		return usuarios.stream().filter(u -> !idsClientes.contains(u.getId()) && !idsPersonal.contains(u.getId()))
				.collect(Collectors.toList());
	}

	/* REGISTRAR USUARIO */
	@GetMapping("/registrarse-usuario")
	public String guardarUsuario(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "/pages-login/registrarse-usuario";
	}

	@PostMapping("/registro")
	public String registro(@ModelAttribute Usuario usuario) {
		 // Log del objeto usuario
	    //System.out.println("Registrando usuario: " + usuario);
		usuario.setEstado(Usuario.Estado.activo);
		usuario.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		usuarioService.guardarUsuario(usuario);

		// REDIRIGIR SEGUN EL TIPO DE USUARIO
		if (usuario.getTipoUsuario() == Usuario.TipoUsuario.cliente) {
			return "redirect:/registrarse-cliente";
		} else if (usuario.getTipoUsuario() == Usuario.TipoUsuario.trabajador
				|| usuario.getTipoUsuario() == Usuario.TipoUsuario.jefe_de_almacen
				|| usuario.getTipoUsuario() == Usuario.TipoUsuario.administrador) {
			return "redirect:/registrarse-personal";
		} else {
			return "redirect:/registrarse-usuario";
		}
	}

	/* REGISTRAR PERSONAL */
	@GetMapping("/registrarse-personal")
	public String registrarsePer(Model modelo) {
		List<Roles> listaR = rolService.listarRoles();
		List<Usuario> listaUs = filtrarUsuariosDisponibles(usuarioService.listar(), clienteService.listaC(),
				personalService.listarP())
				.stream()
				.filter(u -> u.getTipoUsuario() == Usuario.TipoUsuario.trabajador
						|| u.getTipoUsuario() == Usuario.TipoUsuario.jefe_de_almacen
						|| u.getTipoUsuario() == Usuario.TipoUsuario.administrador)
				.collect(Collectors.toList());
		modelo.addAttribute("personal", new Personales());
		modelo.addAttribute("listaR", listaR);
		modelo.addAttribute("listaUs", listaUs);
		return "/pages-login/registrarse-personal";
	}

	@PostMapping("/registro-personal")
	public String registroPersonal(@ModelAttribute Personales personales) {
		personalService.guardarPersonal(personales);
		return "redirect:/registrarse-personal";
	}

	/* REGISTRAR CLIENTE */
	@GetMapping("/registrarse-cliente")
	public String registroCli(Model modelo) {
		List<Usuario> listaUs = filtrarUsuariosDisponibles(usuarioService.listar(), clienteService.listaC(),
				personalService.listarP()).stream().filter(u -> u.getTipoUsuario() == Usuario.TipoUsuario.cliente)
				.collect(Collectors.toList());
		modelo.addAttribute("cliente", new Cliente());
		modelo.addAttribute("listaUsuario", listaUs);
		return "/pages-login/registrarse-cliente";
	}

	@PostMapping("/registro-cliente")
	public String registrarCli(@ModelAttribute Cliente cliente) {
		clienteService.guardarClient(cliente);
		return "redirect:/registrarse-cliente";
	}
	
}
