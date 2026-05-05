package services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import models.User;

public class PDFExporter {

    public void exportUsers(List<User> users, File file) throws IOException {

   
        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
             Document doc = new Document(pdfDoc, PageSize.LETTER.rotate())) {

            // Titulo del documentk
            doc.add(new Paragraph("Reporte del Sistema - NuestraBCS")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER));

            doc.add(new Paragraph("").setMarginTop(20)); // Espaciado

            // Definimos el ancho de las 5 columnas
            float[] columnsWidth = { 1, 4, 3, 4, 3 };
            Table table = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // Header de la tabla
            Cell headerTitle = new Cell(1, 5)
                    .add(new Paragraph("Usuarios Registrados"))
                    .setFont(font).setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(44, 62, 80)) //Color
                    .setTextAlignment(TextAlignment.CENTER);
            table.addHeaderCell(headerTitle);

            // Titulos de las columnas
            String[] cabeceras = {"#", "Nombre", "Usuario", "Email", "Género"};
            for (String textoCabecera : cabeceras) {
                Cell celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBorderTop(new SolidBorder(1f))
                        .setBackgroundColor(new DeviceGray(0.80f))
                        .add(new Paragraph(textoCabecera).setBold());
                table.addHeaderCell(celda);
            }
            
            // Llenar con informacion las tablas
            int indice = 1;
            for (User u : users) {
                // indice
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(String.valueOf(indice))));

                // Nombre 
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(u.getName() != null ? u.getName() : "")));

                // Username 
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(u.getUsername() != null ? u.getUsername() : "")));

                // Email
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT)
                        .add(new Paragraph(u.getEmail() != null ? u.getEmail() : "")));

                // Genero
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getGender() != null ? u.getGender() : "")));

                indice++;
            }

            // Trae la tabla al documento
            doc.add(table);
        }
    }
}
