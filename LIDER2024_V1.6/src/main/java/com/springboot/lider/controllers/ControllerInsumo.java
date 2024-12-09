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

import com.springboot.lider.models.dao.InsumosDaoImpl;
import com.springboot.lider.models.entitys.Insumo;

@Controller
public class ControllerInsumo {
	@Autowired
	private InsumosDaoImpl dao;
	
	@GetMapping("/insumo")
	public String Listarinsumo(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Insumo> insumosPage = dao.listar(pageable);
	    List<Insumo> listaInsumos = insumosPage.getContent();
	    modelo.addAttribute("listaInsumos", listaInsumos);
	    modelo.addAttribute("currentPage", page);
	    modelo.addAttribute("pageSize", size);
	    modelo.addAttribute("totalPages", insumosPage.getTotalPages());
	    int totalInsumos = dao.contarIns();
		modelo.addAttribute("totalIns", totalInsumos);
		modelo.addAttribute("insumo", new Insumo());
	    return "/gestion-almacen/insumos/viewInsumo";
	}
	
	@GetMapping("/nuevoInsumo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("insumo", new Insumo());
		return "/gestion-almacen/insumos/nuevoInsumo";
	}
	
	@PostMapping("/guardarInsumo")
	public String guardar(@ModelAttribute Insumo ins) {
		dao.guardar(ins);
		
		return "redirect:/insumo";
	}
	
	@GetMapping("/editarInsumo/{id}")
	public String editar(@PathVariable("id") int id, Model modelo) {
		Insumo ins = dao.buscarID(id);
		modelo.addAttribute("insumo",ins);
		return "/gestion-almacen/insumos/editarInsumo";
	}
	
	@PostMapping("/actualizarInsumo")
	public String actualizar(@ModelAttribute Insumo ins) {
		dao.actualizar(ins);
		return "redirect:/insumo";
	}
	
	@GetMapping("/borrarInsumo/{id}")
	public String borrar (@PathVariable("id") int id){
		dao.borrar(id);
		return "redirect:/insumo";
	}
}
