package com.springboot.sri.models.services;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.springboot.sorea.models.entitys.DetalleSorea;
import com.springboot.sri.models.entitys.Crsri;
import com.springboot.sri.models.entitys.DetalleSri;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
//import org.springframework.stereotype.Service;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//import java.util.List;

@Service
public class PdfService {
	public ByteArrayInputStream generatePdf(Crsri crsri, List<DetalleSri> detalles) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            // Define the box size with the name "art"
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
            writer.setPageEvent(new FooterEvent());
            document.open();

            // Adding background color
            Rectangle pageSize = document.getPageSize();
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(BaseColor.WHITE);
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
            Paragraph title = new Paragraph("Comprobante CRSRI(" + crsri.getId() + ")", titleFont);
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
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            PdfPTable detailsTable = new PdfPTable(2);
            detailsTable.setWidthPercentage(100);
            detailsTable.setSpacingBefore(10f);

            detailsTable.addCell(createCell("ID Solicitud:", boldFont));
            detailsTable.addCell(createCell(String.valueOf(crsri.getIdSRI().getId()), font));
            detailsTable.addCell(createCell("Fecha de Recepción:", boldFont));
            detailsTable.addCell(createCell(String.valueOf(crsri.getFecha_recepcion()), font));
            detailsTable.addCell(createCell("Hora de Recepción:", boldFont));
            detailsTable.addCell(createCell(crsri.getHoraRecepcion().format(timeFormatter), font));
            detailsTable.addCell(createCell("Personal de Recepción:", boldFont));
            detailsTable.addCell(createCell(crsri.getPersonal_recepcion().getNombre() + " " + crsri.getPersonal_recepcion().getApellido(), font));
            detailsTable.addCell(createCell("Estado de Recepción:", boldFont));
            detailsTable.addCell(createCell(String.valueOf(crsri.getEstado_recepcion()), font));
            detailsTable.addCell(createCell("Incidente:", boldFont));
            detailsTable.addCell(createCell(crsri.getIdSRI().getSolicitudIncidente().getIncidente(), font));
            detailsTable.addCell(createCell("Descripción:", boldFont));
            detailsTable.addCell(createCell(crsri.getDescripcion(), font));

            document.add(detailsTable);
            document.add(new Paragraph(" ")); // Espacio

            document.add(new Paragraph("Detalles del Comprobante", boldFont));
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            table.addCell(createHeaderCell("Insumo", boldFont));
            table.addCell(createHeaderCell("Cantidad", boldFont));
            table.addCell(createHeaderCell("Descripción", boldFont));

            for (DetalleSri detalle : detalles) {
                table.addCell(createCell(detalle.getInsumo().getNombre(), font));
                table.addCell(createCell(String.valueOf(detalle.getCantidad()), font));
                table.addCell(createCell(detalle.getDescripcion(), font));
            }

            document.add(table);
            document.close();
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
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
