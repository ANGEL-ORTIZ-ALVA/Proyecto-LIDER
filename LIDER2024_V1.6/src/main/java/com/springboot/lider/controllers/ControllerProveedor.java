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
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.lider.models.dao.ProveedoresDaoImpl;
import com.springboot.lider.models.entitys.Proveedor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerProveedor {
	@Autowired
	private ProveedoresDaoImpl dao;
	
	@GetMapping("/proveedores")
	public String listarProveedor(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Proveedor> proveedorPage = dao.listar(pageable);
		List<Proveedor> listaProveedores = proveedorPage.getContent();
		modelo.addAttribute("listaProveedores", listaProveedores);
		modelo.addAttribute("currentPage", page);
		modelo.addAttribute("pageSize", size);
		modelo.addAttribute("totalPages", proveedorPage.getTotalPages());
		int totalProveedores = dao.contarProv();
		modelo.addAttribute("totalProv", totalProveedores);
		modelo.addAttribute("proveedor", new Proveedor());
		return "/gestion-almacen/proveedores/viewProveedor";
	}
	
	@GetMapping("/nuevoProveedor")
	public String nuevo(Model modelo) {
		modelo.addAttribute("proveedor", new Proveedor());
		return "/gestion-almacen/proveedores/nuevoProveedor";
	}
	
	@PostMapping("/guardarProveedor")
	public String guardar(@ModelAttribute Proveedor prov) {
		dao.guardar(prov);		
		return "redirect:/proveedores";
	}
	
	@GetMapping("/editarProveedor/{id}")
	public String editar(@PathVariable("id") int id, Model modelo) {
		Proveedor prov = dao.buscarID(id);
		modelo.addAttribute("proveedor", prov);
		return "/gestion-almacen/proveedores/editarProveedor";
	}
	
	@PostMapping("/actualizarProveedor")
	public String actualizar(@ModelAttribute Proveedor prov) {
		dao.actualizar(prov);		
		return "redirect:/proveedores";
	}
	
	@GetMapping("/borrarProveedor/{id}")
	public String eliminar(@PathVariable("id") int id) {
		dao.eliminar(id);
		return "redirect:/proveedores";
	}
}
