package com.springboot.sorea.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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

/*
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.lowagie.text.pdf.PdfPTable;
*/

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
//import java.time.format.DateTimeFormatter;

//import javax.servlet.http.HttpServletResponse;

import com.springboot.sorea.models.dao.CrsoreaDaoImpl;
import com.springboot.sorea.models.dao.DetalleSoreaDaoimpl;
//import com.springboot.sorea.models.dao.ICrsoreaDao;
import com.springboot.sorea.models.dao.IInsumoDaoImpl;
import com.springboot.sorea.models.dao.IPersonalDaoImpl;
import com.springboot.sorea.models.dao.ProveedorDaoImpl;
import com.springboot.sorea.models.dao.SoreaDaoImpl;
import com.springboot.sorea.models.entitys.Sorea;

import jakarta.servlet.http.HttpServletResponse;

import com.springboot.sorea.models.entitys.Crsorea;
import com.springboot.sorea.models.entitys.DetalleSorea;
import com.springboot.sorea.models.entitys.Insumo;
import com.springboot.sorea.models.entitys.Personal;
import com.springboot.sorea.models.entitys.Proveedor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerSorea {
	@Autowired
	private SoreaDaoImpl dao;
	
	@Autowired
    private IPersonalDaoImpl personalDao;
	
	@Autowired
    private ProveedorDaoImpl proveedorDao;
	
	/*@GetMapping("/sorea")
	public String listarReabastecimiento(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Sorea> reabastecimientoPage = dao.listar(pageable);
		List<Sorea> listaReabastecimiento = reabastecimientoPage.getContent();
		
		modelo.addAttribute("listaReabastecimiento", listaReabastecimiento);
		modelo.addAttribute("currentPage", page);
	    modelo.addAttribute("pageSize", size);
	    modelo.addAttribute("totalPages", reabastecimientoPage.getTotalPages());
	    
	    int totalReabastecimiento = dao.contarRea();
	    modelo.addAttribute("totalRea", totalReabastecimiento);
		modelo.addAttribute("sorea", new Sorea());
	    return "/sorea/viewSorea";
	}*/
	@GetMapping("/sorea")
    public String listarReabastecimiento(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Sorea> reabastecimientoPage = dao.listar(pageable);
        List<Sorea> listaReabastecimiento = reabastecimientoPage.getContent();

        Map<Integer, Integer> soreaToCrsoreaMap = new HashMap<>();
        for (Sorea sorea : listaReabastecimiento) {
            Integer crsoreaId = crsoreaService.findBySolicitudId(sorea.getId());
            soreaToCrsoreaMap.put(sorea.getId(), crsoreaId);
        }

        modelo.addAttribute("listaReabastecimiento", listaReabastecimiento);
        modelo.addAttribute("currentPage", page);
        modelo.addAttribute("pageSize", size);
        modelo.addAttribute("totalPages", reabastecimientoPage.getTotalPages());
        modelo.addAttribute("soreaToCrsoreaMap", soreaToCrsoreaMap);

        int totalReabastecimiento = dao.contarRea();
        modelo.addAttribute("totalRea", totalReabastecimiento);
        modelo.addAttribute("sorea", new Sorea());
        return "sorea/viewSorea";
    }
	
	@GetMapping("/nuevoReabastecimiento")
	public String nuevo(Model modelo) {
		List<Personal> recepcionistas = personalDao.listarRecepcionistas();
        modelo.addAttribute("recepcionistas", recepcionistas);
        List<Proveedor> listaProveedores = proveedorDao.listarProveedores();
        modelo.addAttribute("listaProveedores", listaProveedores);
		modelo.addAttribute("sorea", new Sorea());
		return "/sorea/nuevoSorea";
	}
	
	@PostMapping("/guardarReabastecimiento")
	public String guardar(@ModelAttribute Sorea rea) {
		dao.guardar(rea);
		return "redirect:/sorea";
	}
	
	@GetMapping("/editarReabastecimiento/{id}")
	public String editar(@PathVariable("id") int id, Model modelo) {
		Sorea rea = dao.buscarID(id);
		List<Personal> recepcionistas = personalDao.listarRecepcionistas();
	    modelo.addAttribute("recepcionistas", recepcionistas);
	    List<Proveedor> listaProveedores = proveedorDao.listarProveedores();
        modelo.addAttribute("listaProveedores", listaProveedores);
		modelo.addAttribute("sorea", rea);
		return "/sorea/editarSorea";
	}
	
	@PostMapping("/actualizarReabastecimiento")
	public String actualizar(@ModelAttribute Sorea rea) {
		dao.actualizar(rea);		
		return "redirect:/sorea";
	}
	
	@GetMapping("/borrarReabastecimiento/{id}")
	public String borrar(@PathVariable("id") int id) {
		dao.borrar(id);
		return "redirect:/sorea";
	}
	
	//DetalleSorea
	@Autowired
	private DetalleSoreaDaoimpl detalleSOREADao;
	
	
	@Autowired
	private IInsumoDaoImpl insumoDao;
	
	@GetMapping("/sorea/verProductosSolicitud/{id_SOREA}")
    public String verProductosSolicitud(@PathVariable("id_SOREA") int id_SOREA, Model modelo) {
        // Obtener todos los productos asociados a la solicitud
        List<DetalleSorea> listaDetallesSorea = detalleSOREADao.obtenerProductosPorSolicitud(id_SOREA);
        // Pasar los productos a la vista
        modelo.addAttribute("listaDetallesSorea", listaDetallesSorea);
        return "/sorea/verProductosSolicitud"; // Nombre de la vista que mostrará los productos
    }

    @GetMapping("/nuevoDetalleSOREA/{id_SOREA}")
    public String nuevoDetalle(@PathVariable("id_SOREA") int id_SOREA, Model modelo) {
    	List<Insumo> insumos = insumoDao.listarInsumos();
        modelo.addAttribute("id_SOREA", id_SOREA);
        modelo.addAttribute("insumos", insumos);
        modelo.addAttribute("detalleSorea", new DetalleSorea());
        return "/sorea/nuevoDetalleSorea"; // Nombre de la vista para crear un nuevo detalle
    }

    @PostMapping("/guardarDetalleSOREA")
    public String guardarDetalle(@ModelAttribute DetalleSorea detalleSorea) {
        // Lógica para guardar el nuevo detalle de solicitud en la base de datos
        detalleSOREADao.guardarDetalleSorea(detalleSorea);
        return "redirect:/sorea/verProductosSolicitud/" + detalleSorea.getId_SOREA();
    }

    @GetMapping("/editarDetalleSorea/{id_SOREA}/{id_insumo}")
    public String editarDetalleSorea(@PathVariable("id_SOREA") int idSorea, @PathVariable("id_insumo") int idInsumo, Model modelo) {
    	List<Insumo> insumos = insumoDao.listarInsumos();
    	modelo.addAttribute("insumos", insumos);
        DetalleSorea detalleSorea = detalleSOREADao.buscarDetalleSorea(idSorea, idInsumo);
        modelo.addAttribute("detalleSorea", detalleSorea);
        return "/sorea/editarDetalleSorea"; // Nombre de la vista para editar un detalle
    }

    @PostMapping("/actualizarDetalleSorea")
    public String actualizarDetalleSorea(@ModelAttribute DetalleSorea detalleSorea) {
        detalleSOREADao.actualizarDetalleSorea(detalleSorea);
        return "redirect:/sorea/verProductosSolicitud/" + detalleSorea.getId_SOREA();
    }

    @GetMapping("/borrarDetalleSorea/{id_SOREA}/{id_insumo}")
    public String borrarDetalle(@PathVariable("id_SOREA") int idSorea, @PathVariable("id_insumo") int idInsumo) {
        detalleSOREADao.borrarDetalleSorea(idSorea, idInsumo);
        return "redirect:/sorea/verProductosSolicitud/" + idSorea;
    }
    
    /*LOGICA PARA EL COMPROBANTE*/
    
    @Autowired CrsoreaDaoImpl crsoreaService;
    
    @GetMapping("/crsorea")
	public String listarComprobantes(Model modelo,  @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Crsorea> CreabastecimientoPage = crsoreaService.listarComprobantes(pageable);
		List<Crsorea> listaCReabastecimiento = CreabastecimientoPage.getContent();
		
		modelo.addAttribute("listaReabastecimiento", listaCReabastecimiento);
		modelo.addAttribute("currentPage", page);
	    modelo.addAttribute("pageSize", size);
	    modelo.addAttribute("totalPages", CreabastecimientoPage.getTotalPages());
	    
		modelo.addAttribute("crsorea", new Crsorea());
	    return "/crsorea/viewCrsorea";
	}

    @GetMapping("/nuevoCrsorea/{idSolicitud}")
    public String nuevoCrsorea(@PathVariable("idSolicitud") int idSolicitud, Model model) {
        Crsorea crsorea = new Crsorea();
        crsorea.setId_solicitud(idSolicitud);
        model.addAttribute("crsorea", crsorea);
        return "crsorea/nuevoCrsorea";
    }

    @PostMapping("/guardarCrsorea")
    public String guardarCrsorea(@ModelAttribute Crsorea crsorea) {
        crsoreaService.save(crsorea);
        return "redirect:/crsorea";
    }

    @GetMapping("/verCrsorea/{id}")
    public String verCrsorea(@PathVariable("id") int id, Model model) {
        Crsorea crsorea = crsoreaService.findById(id);
        
        /*datos para el reporte*/
        if (crsorea == null) {
            model.addAttribute("error", "Comprobante no encontrado");
            return "error"; // Asegúrate de tener una plantilla de error adecuada
        }
        
        Sorea sorea =  dao.buscarID(crsorea.getId_solicitud());
        List<DetalleSorea> detalles = detalleSOREADao.obtenerProductosPorSolicitud(crsorea.getId_solicitud());
        		
        
        
        model.addAttribute("crsorea", crsorea);
        model.addAttribute("sorea", sorea);
        model.addAttribute("detalles", detalles);
        return "crsorea/verCrsorea";
    }
    
    @GetMapping("/editarCrsorea/{id}")
	public String editarCrsorea(@PathVariable("id") int id, Model modelo) {
		Crsorea crsorea = crsoreaService.findById(id);
		modelo.addAttribute("crsorea", crsorea);
		return "crsorea/editarCrsorea";
	}
	
	@PostMapping("/actualizarCrsorea")
	public String actualizarCrsorea(@ModelAttribute Crsorea crsorea) {
		crsoreaService.actualizar(crsorea);		
		return "redirect:/crsorea";
	}
    

    @GetMapping("/eliminarCrsorea/{id}")
    public String eliminarCrsorea(@PathVariable("id") int id) {
        crsoreaService.deleteById(id);
        return "redirect:/crsorea";
    }
    
    /*Exportacion PDF del comprobante*/
    
    @GetMapping("/exportarPdfCrsorea/{id}")
    public void exportarPdfCrsorea(@PathVariable("id") int id, HttpServletResponse response) throws IOException, DocumentException {
        Crsorea crsorea = crsoreaService.findById(id);
        Sorea sorea = dao.buscarID(crsorea.getId_solicitud());
        List<DetalleSorea> detalles = detalleSOREADao.obtenerProductosPorSolicitud(crsorea.getId_solicitud());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=crsorea_" + id + ".pdf");

        try (OutputStream out = response.getOutputStream()) {
            crearPdfCrsorea(crsorea, sorea, detalles, out);
        }
    }

    /*private void crearPdfCrsorea(Crsorea crsorea, Sorea sorea, List<DetalleSorea> detalles, OutputStream out) throws DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();

        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        document.add(new Paragraph("Comprobante de Recepción de Solicitud de Reabastecimiento de Almacén (CRSOREA)", font));
        document.add(new Paragraph("ID Comprobante: " + crsorea.getId(), font));
        document.add(new Paragraph("Fecha de Recepción: " + crsorea.getFecha_recepcion(), font));
        document.add(new Paragraph("Monto Total: " + crsorea.getMonto_total(), font));
        document.add(new Paragraph("Estado: " + crsorea.getEstado(), font));
        document.add(new Paragraph("Descripción: " + crsorea.getDescripcion(), font));

        document.add(new Paragraph(" ")); // Espacio

        document.add(new Paragraph("Información de la Solicitud (SOREA)", font));
        document.add(new Paragraph("ID Solicitud: " + sorea.getId(), font));
        document.add(new Paragraph("ID Proveedor: " + sorea.getId_proveedor(), font));
        document.add(new Paragraph("ID Recepcionista: " + sorea.getRecepcionista(), font));
        document.add(new Paragraph("Fecha de Solicitud: " + sorea.getFecha_solicitud(), font));
        document.add(new Paragraph("Fecha de Recepción: " + sorea.getFecha_recepcion(), font));
        document.add(new Paragraph("Descripción: " + sorea.getDescripcion(), font));

        document.add(new Paragraph(" ")); // Espacio

        document.add(new Paragraph("Detalles de la Solicitud", font));
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell = new PdfPCell(new Phrase("ID Insumo", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cantidad", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unidad de Medida", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Precio Unitario", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descripción", font));
        table.addCell(cell);

        for (DetalleSorea detalle : detalles) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(detalle.getId_insumo()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(detalle.getCantidad()), font)));
            table.addCell(new PdfPCell(new Phrase(detalle.getUnidad_medida(), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(detalle.getPrecio_unitario()), font)));
            table.addCell(new PdfPCell(new Phrase(detalle.getDescripcion(), font)));
        }

        document.add(table);
        document.close();
    }*/
    private void crearPdfCrsorea(Crsorea crsorea, Sorea sorea, List<DetalleSorea> detalles, OutputStream out) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
        writer.setPageEvent(new FooterEvent());
        document.open();

        // Adding background color
        Rectangle pageSize = document.getPageSize();
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.setColorFill(BaseColor.GRAY.brighter());
        canvas.rectangle(pageSize.getLeft(), pageSize.getBottom(), pageSize.getWidth(), pageSize.getHeight());
        canvas.fill();

        // Adding a border
        PdfContentByte canvasBorder = writer.getDirectContent();
        canvasBorder.setColorStroke(BaseColor.BLACK);
        canvasBorder.setLineWidth(2);
        canvasBorder.rectangle(pageSize.getLeft() + 10, pageSize.getBottom() + 10, pageSize.getWidth() - 20, pageSize.getHeight() - 20);
        canvasBorder.stroke();

        // Adding a title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("Comprobante CRSOREA(" + crsorea.getId() + ")", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Adding a logo
        Image img = Image.getInstance("src/main/resources/static/img/logo-lider.png");
        img.scaleToFit(100, 100);
        img.setAlignment(Element.ALIGN_RIGHT);
        document.add(img);

        document.add(Chunk.NEWLINE);

        // Adding the details
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        PdfPTable detailsTable = new PdfPTable(2);
        detailsTable.setWidthPercentage(100);
        detailsTable.setSpacingBefore(10f);

        detailsTable.addCell(createCell("ID Comprobante:", boldFont));
        detailsTable.addCell(createCell(String.valueOf(crsorea.getId()), font));
        detailsTable.addCell(createCell("Fecha de Recepción:", boldFont));
        detailsTable.addCell(createCell(String.valueOf(crsorea.getFecha_recepcion()), font));
        detailsTable.addCell(createCell("Monto Total:", boldFont));
        detailsTable.addCell(createCell(String.valueOf(crsorea.getMonto_total()), font));
        detailsTable.addCell(createCell("Estado:", boldFont));
        detailsTable.addCell(createCell(String.valueOf(crsorea.getEstado()), font));
        detailsTable.addCell(createCell("Descripción:", boldFont));
        detailsTable.addCell(createCell(crsorea.getDescripcion(), font));

        document.add(detailsTable);
        document.add(new Paragraph(" ")); // Espacio

        document.add(new Paragraph("Información de la Solicitud (SOREA)", boldFont));
        PdfPTable soreaTable = new PdfPTable(2);
        soreaTable.setWidthPercentage(100);
        soreaTable.setSpacingBefore(10f);

        soreaTable.addCell(createCell("ID Solicitud:", boldFont));
        soreaTable.addCell(createCell(String.valueOf(sorea.getId()), font));
        soreaTable.addCell(createCell("ID Proveedor:", boldFont));
        soreaTable.addCell(createCell(String.valueOf(sorea.getId_proveedor()), font));
        soreaTable.addCell(createCell("ID Recepcionista:", boldFont));
        soreaTable.addCell(createCell(String.valueOf(sorea.getRecepcionista()), font));
        soreaTable.addCell(createCell("Fecha de Solicitud:", boldFont));
        soreaTable.addCell(createCell(String.valueOf(sorea.getFecha_solicitud()), font));
        soreaTable.addCell(createCell("Fecha de Recepción:", boldFont));
        soreaTable.addCell(createCell(String.valueOf(sorea.getFecha_recepcion()), font));
        soreaTable.addCell(createCell("Descripción:", boldFont));
        soreaTable.addCell(createCell(sorea.getDescripcion(), font));

        document.add(soreaTable);
        document.add(new Paragraph(" ")); // Espacio

        document.add(new Paragraph("Detalles de la Solicitud", boldFont));
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        table.addCell(createHeaderCell("ID Insumo", boldFont));
        table.addCell(createHeaderCell("Cantidad", boldFont));
        table.addCell(createHeaderCell("Unidad de Medida", boldFont));
        table.addCell(createHeaderCell("Precio Unitario", boldFont));
        table.addCell(createHeaderCell("Descripción", boldFont));

        for (DetalleSorea detalle : detalles) {
            table.addCell(createCell(String.valueOf(detalle.getId_insumo()), font));
            table.addCell(createCell(String.valueOf(detalle.getCantidad()), font));
            table.addCell(createCell(detalle.getUnidad_medida(), font));
            table.addCell(createCell(String.valueOf(detalle.getPrecio_unitario()), font));
            table.addCell(createCell(detalle.getDescripcion(), font));
        }

        document.add(table);
        document.close();
    }

    private PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell createHeaderCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        return cell;
    }

    class FooterEvent extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("Lider S.A", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }
}
