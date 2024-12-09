package com.springboot.sri.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.sri.models.entitys.Crsri;
import com.springboot.sri.models.entitys.DetalleSRIId;
import com.springboot.sri.models.entitys.DetalleSri;
import com.springboot.sri.models.entitys.Insumo;
import com.springboot.sri.models.entitys.Personal;
import com.springboot.sri.models.entitys.SolicitudIncidente;
import com.springboot.sri.models.entitys.Sri;
import com.springboot.sri.models.services.DetalleSRIService;
import com.springboot.sri.models.services.ICrsriService;
import com.springboot.sri.models.services.InsumoService;
import com.springboot.sri.models.services.PdfService;
import com.springboot.sri.models.services.PersonalService;
import com.springboot.sri.models.services.SolicitudIncidenteService;
import com.springboot.sri.models.services.SriService;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayInputStream;

@Controller
public class SriController {
	@Autowired
	 private SriService sriService;
	 
	 @Autowired
	 private SolicitudIncidenteService incidenteService; 
	 
	 @Autowired
	 private PersonalService personalService;
	 
	 //CRSRI
	 
	 @Autowired
	 private ICrsriService crsriService;
	
	 @GetMapping("/sriAprobadas")
	    public String listarAprobadas(Model model) { 
		 	List<Sri>listaSriAprobadas = sriService.findByEstado(Sri.Estado.aprobada);
		 	List<Crsri> listaCRSRI = crsriService.listarCrsri();
		    
		 // Crear un mapa para asociar Sri ID con Crsri ID
		    Map<Integer, Integer> sriToCrsriMap = new HashMap<>();
		    for (Crsri crsri : listaCRSRI) {
		        sriToCrsriMap.put(crsri.getIdSRI().getId(), crsri.getId());
		    }
		 	
		 // Añadir la propiedad transitoria hasCrsri
	        for (Sri sri : listaSriAprobadas) {
	            boolean hasCrsri = crsriService.existsBySri(sri);
	            sri.setHasCrsri(hasCrsri);
	        }
	        
	        model.addAttribute("listaSriAprobadas", listaSriAprobadas);
	        model.addAttribute("sriToCrsriMap", sriToCrsriMap);
	        return "sri/viewSriAprobadas.html";
	    }
	 
	 @GetMapping("/Crsri")
	    public String listarComprobantes(Model model) {
		    List<Crsri> listaCRSRI = crsriService.listarCrsri();
		    model.addAttribute("listaCRSRI", listaCRSRI);
		    return "sri/viewCrsri";
	    }
	 
	 @GetMapping("/nuevoCrsri/{idSRI}")
	    public String nuevoComprobanteSRI(@PathVariable("idSRI") int idSRI,  Model modelo) {
	        List<Personal> listaPersonal = personalService.listarPersonal();
	        modelo.addAttribute("listaPersonal", listaPersonal);
	        Crsri crsri = new Crsri();
	        //crsri.setId_SRI(new Sri(idSRI)); // Asociar el SRI con el CRSRI
	        modelo.addAttribute("crsri", crsri);
	        return "sri/nuevoCrsri.html";
	    }

		
		@PostMapping("/guardarCrsri")
		public String guardarComprobanteSRI(@ModelAttribute Crsri Crsri) {
		    // Lógica para guardar el nuevo detalle de solicitud en la base de datos
		    crsriService.save(Crsri);
		    return "redirect:/sriAprobadas";
		}
		
		@GetMapping("/editarCrsri/{id}")
	    public String editarComprobanteSRI(@PathVariable("id") int id, Model model) {
	        // Obtener la CRSRI existente por su ID
	        Optional<Crsri> filaCrsriOptional = crsriService.buscarId(id);
	        if (filaCrsriOptional.isPresent()) {
	            // Si se encontró la CRSRI, cargar los datos en el modelo
	            Crsri crsri = filaCrsriOptional.get();
	            // Obtener personal si es necesario
	            List<Personal> listaPersonal = personalService.listarPersonal();
	            // Agregar los objetos al modelo
	            model.addAttribute("listaPersonal", listaPersonal);
	            model.addAttribute("crsri", crsri); // Pasar la Sri existente al modelo
	            return "/sri/editarCrsri.html";
	        } else {
	            // Aquí puedes redirigir a una página de error o manejarlo de otra manera
	            return "redirect:/error"; // Por ejemplo
	        }
	    }

	    @PostMapping("/actualizarCrsri")
	    public String actualizarComprobanteSRI(@ModelAttribute Crsri crsri) {
	        crsriService.update(crsri);
	        return "redirect:/Crsri";
	    }

	    @GetMapping("/borrarCrsri/{id}")
	    public String borrarComprobanteSRI(@PathVariable("id") int id) {
	        crsriService.delete(id);
	        return "redirect:/Crsri";
	    }
	    
	    @GetMapping("/verCrsri/{id}")
	    public String verComprobanteSRI(@PathVariable("id") int id, Model modelo) {
	        Crsri crsri = crsriService.encontrarPorId(id);
	        List<DetalleSri> detalles = detalleService.listarPorSriId(crsri.getIdSRI().getId());
	        modelo.addAttribute("detalles", detalles);
	        modelo.addAttribute("crsri", crsri);
	        return "sri/verComprobanteSRI";
	    }
	    
	    //pdf
	    @Autowired
	    private PdfService pdfService;

	    @GetMapping("/crsri/{id}/pdf")
	    public ResponseEntity<InputStreamResource> exportCrsriToPdf(@PathVariable("id") int id) throws DocumentException {
	        Crsri crsri = crsriService.encontrarPorId(id);
	        List<DetalleSri> detalles = detalleService.listarPorSriId(crsri.getIdSRI().getId());
	        ByteArrayInputStream bis = pdfService.generatePdf(crsri, detalles);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=comprobante_sri.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }
	 
	 
	 
	 //CRSRI
	 
	   /* @GetMapping("/sri")
	    public String listarTodas(Model model) {
		    List<Sri> listaSRI = sriService.listarTodas();
		    model.addAttribute("listaSRI", listaSRI);
		    return "sri/viewSri";
	    }*/
	    @GetMapping("/sri")
	    public String listarTodas(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Sri> sriPage = sriService.listarSri(pageable);

	        model.addAttribute("listaSRI", sriPage.getContent());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", size);
	        model.addAttribute("totalPages", sriPage.getTotalPages());
	        model.addAttribute("totalElements", sriPage.getTotalElements());

	        return "sri/viewSri";
	    }

	    @GetMapping("/nuevoSri")
	    public String nuevo(Model model) {
	    	List<SolicitudIncidente>listaIncidente = incidenteService.listarIncidentes();
	    	List<Personal>listaPersonal = personalService.listarPersonal();
	    	model.addAttribute("listaIncidente", listaIncidente);
	    	model.addAttribute("listaPersonal", listaPersonal);
		    model.addAttribute("sri", new Sri());
		    return "/sri/guardarSri";
	    }

	    @PostMapping("/guardarSri")
	    public String guardar(@ModelAttribute Sri sri) {
		    sriService.guardar(sri);
		    return "redirect:/sri";
	    }

	    /*@GetMapping("/editarSri/{id}")
	    public String editar(@PathVariable int id, Model model) {
	    	List<SolicitudIncidente>listaIncidente = incidenteService.listarIncidentes();
	    	List<Personal>listaPersonal = personalService.listarPersonal();
	    	model.addAttribute("listaIncidente", listaIncidente);
	    	model.addAttribute("listaPersonal", listaPersonal);
	    	Optional<Sri>filaSri = sriService.buscarPorId(id);
		    model.addAttribute("sri", filaSri.orElse(null));
		    return "guardarSri.html";
	    }*/
	    
	    @GetMapping("/editarSri/{id}")
	    public String editar(@PathVariable("id") int id, Model model) {
	        // Obtener la Sri existente por su ID
	        Optional<Sri> filaSriOptional = sriService.buscarPorId(id);
	        
	        if (filaSriOptional.isPresent()) {
	            // Si se encontró la Sri, cargar los datos en el modelo
	            Sri sri = filaSriOptional.get();
	            
	            // Obtener listas de incidentes y personal si es necesario
	            List<SolicitudIncidente> listaIncidente = incidenteService.listarIncidentes();
	            List<Personal> listaPersonal = personalService.listarPersonal();
	            
	            // Agregar los objetos al modelo
	            model.addAttribute("listaIncidente", listaIncidente);
	            model.addAttribute("listaPersonal", listaPersonal);
	            model.addAttribute("sri", sri); // Pasar la Sri existente al modelo
	            return "/sri/editarSri.html";
	        } else {
	            // Manejar el caso en que no se encuentre la Sri
	            // Aquí puedes redirigir a una página de error o manejarlo de otra manera
	            return "redirect:/error"; // Por ejemplo
	        }
	    }

	    @PostMapping("/actualizarSri")
	    public String actualizar(@ModelAttribute Sri sri) {
		    sriService.guardar(sri);
		    return "redirect:/sri";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminar(@PathVariable("id") int id) {
	        sriService.eliminarPorId(id);
	        return "redirect:/sri";
	    }
	    
	    //DetalleSri
	    
	    @Autowired
	    private DetalleSRIService detalleService;
	    
	    @Autowired
	    private InsumoService insumoService;
	    
	    @GetMapping("/sri/verProductosSolicitud/{id_SRI}")
		public String verProductosSolicitud(@PathVariable("id_SRI") int id_SRI, Model modelo) {
		    // Obtener todos los productos asociados a la solicitud
		    List<DetalleSri> listaDetallesSri = detalleService.listarPorSriId(id_SRI);
		    // Pasar los productos a la vista
		    modelo.addAttribute("listaDetallesSri", listaDetallesSri);
		    return "/sri/verProductosSolicitudSRI"; // Nombre de la vista que mostrará los productos
		}
	    
	  
	    
	    @GetMapping("/nuevoDetalleSRI/{idSRI}/{idInsumo}")
	    public String nuevoDetalle(@PathVariable("idSRI") int idSRI, @PathVariable("idInsumo") int idInsumo, Model modelo) {
	        List<Insumo> listaInsumos = insumoService.listarInsumos();
	        modelo.addAttribute("listaInsumos", listaInsumos);
	        List<Sri> listaSRI = sriService.listarTodas();
	        modelo.addAttribute("listaSRI", listaSRI);
	        DetalleSRIId detalleSRIId = new DetalleSRIId(idSRI, idInsumo);
	        modelo.addAttribute("detalleSRIId", detalleSRIId);
	        modelo.addAttribute("detalleSRI", new DetalleSri());
	        return "sri/nuevoDetalleSRI";
	    }

		
		@PostMapping("/guardarDetalleSRI")
		public String guardarDetalle(@ModelAttribute DetalleSri DetalleSri) {
		    // Lógica para guardar el nuevo detalle de solicitud en la base de datos
		    detalleService.guardar(DetalleSri);
		    return "redirect:/sri";
		}
		
		
		
		@GetMapping("/editarDetalleSri/{idSRI}/{idInsumo}")
	    public String editarDetalle(@PathVariable("idSRI") int idSRI, @PathVariable("idInsumo") int idInsumo, Model model) {
	        List<Insumo> listaInsumos = insumoService.listarInsumos();
	        model.addAttribute("listaInsumos", listaInsumos);
	        List<Sri> listaSRI = sriService.listarTodas();
	        model.addAttribute("listaSRI", listaSRI);
	        DetalleSRIId detalleSRIId = new DetalleSRIId(idSRI, idInsumo);
	        Optional<DetalleSri> filaDetalleSri = detalleService.buscarPorId(detalleSRIId);
	        model.addAttribute("detalleSRI", filaDetalleSri.orElse(null));
	        return "/sri/editarDetalleSRI";
	    }
		
		@GetMapping("/eliminarDetalle/{idSRI}/{idInsumo}")
	    public String eliminarDetalle(@PathVariable("idSRI") int idSRI, @PathVariable("idInsumo") int idInsumo) {
	        DetalleSRIId detalleSRIId = new DetalleSRIId(idSRI, idInsumo);
	        detalleService.eliminarPorId(detalleSRIId);
	        return "redirect:/sri";
	    }
}
