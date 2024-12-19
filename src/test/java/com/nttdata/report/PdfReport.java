package com.nttdata.report;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileNotFoundException;

public class PdfReport {
    public void generarReporte(String filePath, String contenido) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(contenido));

            document.close();

            System.out.println("Reporte PDF generado exitosamente en: " + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
