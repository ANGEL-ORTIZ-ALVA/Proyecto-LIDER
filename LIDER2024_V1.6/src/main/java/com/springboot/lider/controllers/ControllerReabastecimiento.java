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

import com.springboot.lider.models.dao.ReabastecimientoDaoImpl;
import com.springboot.lider.models.entitys.Reabastecimiento;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerReabastecimiento {
	@Autowired
	private ReabastecimientoDaoImpl dao;
	
	@GetMapping("/liderv1/reabastecimiento")
	public String listarReabastecimiento(Model modelo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Reabastecimiento> reabastecimientoPage = dao.listar(pageable);
		List<Reabastecimiento> listaReabastecimiento = reabastecimientoPage.getContent();
		modelo.addAttribute("listaReabastecimiento", listaReabastecimiento);
		modelo.addAttribute("currentPage", page);
	    modelo.addAttribute("pageSize", size);
	    modelo.addAttribute("totalPages", reabastecimientoPage.getTotalPages());
	    int totalReabastecimiento = dao.contarRea();
	    modelo.addAttribute("totalRea", totalReabastecimiento);
		modelo.addAttribute("reabastecimiento", new Reabastecimiento());
	    return "/gestion-almacen/solicitud-reabastecimiento/viewReabastecimiento";
	}
	
	@GetMapping("/liderv1/nuevoReabastecimiento")
	public String nuevo(Model modelo) {
		modelo.addAttribute("reabastecimiento", new Reabastecimiento());
		return "/gestion-almacen/solicitud-reabastecimiento/nuevoReabastecimiento";
	}
	
	@PostMapping("/liderv1/guardarReabastecimiento")
	public String guardar(@ModelAttribute Reabastecimiento rea) {
		dao.guardar(rea);
		return "redirect:/reabastecimiento";
	}
	
	@GetMapping("/liderv1/editarReabastecimiento/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Reabastecimiento rea = dao.buscarID(id);
		modelo.addAttribute("reabastecimiento", rea);
		return "/gestion-almacen/solicitud-reabastecimiento/editarReabastecimiento";
	}
	
	@PostMapping("/liderv1/actualizarReabastecimiento")
	public String actualizar(@ModelAttribute Reabastecimiento rea) {
		dao.actualizar(rea);		
		return "redirect:/reabastecimiento";
	}
	
	@GetMapping("/liderv1/borrarReabastecimiento/{id}")
	public String borrar(@PathVariable int id) {
		dao.borrar(id);
		return "redirect:/reabastecimiento";
	}
}
