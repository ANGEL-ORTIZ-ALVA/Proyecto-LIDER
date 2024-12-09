package com.springboot.reporte.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springboot.lider.models.entitys.SolicitudIncidente;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("gestion-incidentes/solicitud-incidente/listaSolicitudes")
public class ListarSolicitudes extends AbstractPdfView{
	@Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<SolicitudIncidente> listaSolicitudes = (List<SolicitudIncidente>) model.get("listaSolicitudes");

        document.setPageSize(PageSize.LETTER.rotate());//para ponerlo horizontal el pdf
        document.open();

        // Establecer estilos para el título
        PdfPCell celdaTitulo = new PdfPCell(new Phrase("LISTADO DE SOLICITUDES DE INCIDENTES LIDER S.A.C"));
        celdaTitulo.setBorder(0);
        celdaTitulo.setBackgroundColor(new Color(58, 141, 193));
        celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celdaTitulo.setPadding(10);
        celdaTitulo.setPhrase(new Phrase("LISTADO DE SOLICITUDES DE INCIDENTES LIDER S.A.C", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.WHITE)));

        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.setWidthPercentage(100);
        tablaTitulo.addCell(celdaTitulo);
        tablaTitulo.setSpacingAfter(30);

        // Estilizar las celdas de la tabla
        PdfPTable tablaSolicitudes = new PdfPTable(8);
        tablaSolicitudes.setWidthPercentage(100);

        // Añadir encabezados de columna
        String[] encabezados = {"ID", "ID Cliente", "Fecha Solicitud", "Incidente", "Estado", "Fecha Aprobación", "Fecha Rechazo", "Observaciones"};
        for (String encabezado : encabezados) {
            PdfPCell celdaEncabezado = new PdfPCell(new Phrase(encabezado, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.WHITE)));
            celdaEncabezado.setBackgroundColor(new Color(58, 141, 193));
            celdaEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaEncabezado.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaEncabezado.setBorderWidth(1);
            celdaEncabezado.setBorderColor(Color.BLACK);
            tablaSolicitudes.addCell(celdaEncabezado);
        }

        // Alternar los colores de fondo de las filas
        boolean colorearFila = false;
        for (SolicitudIncidente solicitud : listaSolicitudes) {
            if (colorearFila) {
                tablaSolicitudes.getDefaultCell().setBackgroundColor(new Color(235, 235, 235));
            } else {
                tablaSolicitudes.getDefaultCell().setBackgroundColor(Color.WHITE);
            }
            colorearFila = !colorearFila;

            // Agregar contenido a la tabla
            tablaSolicitudes.addCell(new Phrase(String.valueOf(solicitud.getId())));
            tablaSolicitudes.addCell(new Phrase(String.valueOf(solicitud.getIdCliente())));
            tablaSolicitudes.addCell(new Phrase(solicitud.getFechaSolicitud() != null ? solicitud.getFechaSolicitud().toString() : ""));
            tablaSolicitudes.addCell(new Phrase(solicitud.getIncidente()));
            tablaSolicitudes.addCell(new Phrase(solicitud.getEstado()));
            tablaSolicitudes.addCell(new Phrase(solicitud.getFechaAprobacion() != null ? solicitud.getFechaAprobacion().toString() : ""));
            tablaSolicitudes.addCell(new Phrase(solicitud.getFechaRechazo() != null ? solicitud.getFechaRechazo().toString() : ""));
            tablaSolicitudes.addCell(new Phrase(solicitud.getObservaciones()));
        }

        document.add(tablaTitulo);
        document.add(tablaSolicitudes);
    }
}
