package com.springboot.lider.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.lider.models.dao.SolicitudIncidenteDaoImpl;
import com.springboot.lider.models.entitys.SolicitudIncidente;

@Controller
public class ControllerSolicitudIncidente {
	 @Autowired
	    private SolicitudIncidenteDaoImpl dao;

	    @GetMapping("/solicitudes")
	    public String listarSolicitudes(Model modelo, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<SolicitudIncidente> solinPage = dao.listar(pageable);
	        List<SolicitudIncidente> listaSolicitudes = solinPage.getContent();

	        modelo.addAttribute("listaSolicitudes", listaSolicitudes);
	        modelo.addAttribute("currentPage", page);
	        modelo.addAttribute("pageSize", size);
	        modelo.addAttribute("totalPages", solinPage.getTotalPages());

	        int totalSI = dao.contarSI();
	        modelo.addAttribute("totalSI", totalSI);
	        modelo.addAttribute("solicitud", new SolicitudIncidente());

	        return "gestion-incidentes/solicitud-incidente/listaSolicitudes";
	    }

	    @GetMapping("/solicitud/nueva")
	    public String nuevaSolicitud(Model model) {
	        model.addAttribute("solicitudIncidente", new SolicitudIncidente());
	        return "gestion-incidentes/solicitud-incidente/nuevaSolicitud";
	    }

	    @PostMapping("/solicitud/guardar")
	    public String guardarSolicitud(@ModelAttribute SolicitudIncidente solicitudIncidente) {
	        solicitudIncidente.setFechaAprobacion(null);
	        solicitudIncidente.setFechaRechazo(null);
	        dao.guardar(solicitudIncidente);
	        
	        return "redirect:/solicitudes";
	    }

	    @GetMapping("/solicitud/editar/{id}")
	    public String editarSolicitud(@PathVariable("id") int id, Model model) {
	        SolicitudIncidente solicitudIncidente = dao.buscarPorId(id);
	        model.addAttribute("solicitudIncidente", solicitudIncidente);
	        
	        return "gestion-incidentes/solicitud-incidente/editarSolicitud";
	    }

	    @PostMapping("/solicitud/actualizar")
	    public String actualizarSolicitud(@ModelAttribute SolicitudIncidente solicitudIncidente) {
	        // Verificar si el estado ha cambiado
	        SolicitudIncidente solicitudAnterior = dao.buscarPorId(solicitudIncidente.getId());
	        String estadoAnterior = solicitudAnterior.getEstado();
	        String nuevoEstado = solicitudIncidente.getEstado();
	        
	        if (!estadoAnterior.equals(nuevoEstado)) {
	            // Actualizar las fechas de acuerdo al nuevo estado
	            if (nuevoEstado.equals("aprobada")) {
	                solicitudIncidente.setFechaAprobacion(new Date(System.currentTimeMillis())); // Establecer la fecha actual xd
	            } else if (nuevoEstado.equals("rechazada")) {
	                solicitudIncidente.setFechaRechazo(new Date(System.currentTimeMillis()));
	            }
	        }
	        
	        // Actualizar la solicitud en la base de datos
	        dao.actualizar(solicitudIncidente);
	        
	        return "redirect:/solicitudes";
	    }


	    @GetMapping("/solicitud/borrar/{id}")
	    public String borrarSolicitud(@PathVariable("id") int id) {
	        dao.eliminar(id);
	        return "redirect:/solicitudes";
	    }
	    /*filtrado por fechas*/
	    @GetMapping("/solicitudes/filtrar")
	    public String filtrarSolicitudes(Model modelo, @RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        List<SolicitudIncidente> listaSolicitudes = dao.filtrarPorFecha(fechaInicio, fechaFin);

	        int start = Math.min((int) pageable.getOffset(), listaSolicitudes.size());
	        int end = Math.min((start + pageable.getPageSize()), listaSolicitudes.size());
	        Page<SolicitudIncidente> solinPage = new PageImpl<>(listaSolicitudes.subList(start, end), pageable, listaSolicitudes.size());

	        modelo.addAttribute("listaSolicitudes", solinPage.getContent());
	        modelo.addAttribute("currentPage", page);
	        modelo.addAttribute("pageSize", size);
	        modelo.addAttribute("totalPages", solinPage.getTotalPages());

	        int totalSI = listaSolicitudes.size();
	        modelo.addAttribute("totalSI", totalSI);
	        modelo.addAttribute("solicitud", new SolicitudIncidente());

	        return "gestion-incidentes/solicitud-incidente/listaSolicitudes";
	    }
}
