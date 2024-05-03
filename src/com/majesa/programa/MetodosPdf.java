package com.majesa.programa;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.obtenerFecha;

public class MetodosPdf {

    public static void descargarResumenDiaPdf(ArrayList<GuiaVenta> guiasVentas, String[] totalesVentas,
                                              ArrayList<GuiaDevolucion> guiasDevoluciones, String[] totalesDevoluciones,
                                              ArrayList<GuiaAdelanto> guiasAdelantos, String adelantosTotales) {

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Documento PDF", "pdf");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            try {

                String ruta = fileChooser.getSelectedFile().getPath();
                PdfWriter escritorPdf = new PdfWriter(crearExtensionPdf(ruta));
                PdfDocument documentoPdf = new PdfDocument(escritorPdf);

                Document documento = new Document(documentoPdf, PageSize.A4);
                documento.setMargins(72, 72, 72, 72);

                Paragraph titulo = new Paragraph("Reporte del Dia: " + obtenerFecha());
                titulo.setTextAlignment(TextAlignment.CENTER);
                titulo.setUnderline(1, -1);
                titulo.setFontSize(16);
                documento.add(titulo);

                documento.add(new Paragraph(""));
                documento.add(new Paragraph(""));

                Paragraph resumenVentas = new Paragraph("Resumen Ventas:");
                resumenVentas.setUnderline(1, -1);
                resumenVentas.setFontSize(12);
                documento.add(resumenVentas);

                documento.add(new Paragraph(""));

                Table tablaResumenVentas = crearResumenVentas(totalesVentas, 12);
                documento.add(tablaResumenVentas);

                documento.add(new Paragraph(""));
                documento.add(new Paragraph(""));

                Paragraph resumenDevoluciones = new Paragraph("Resumen Devoluciones:");
                resumenDevoluciones.setUnderline(1, -1);
                resumenDevoluciones.setFontSize(12);
                documento.add(resumenDevoluciones);

                documento.add(new Paragraph(""));

                Table tablaResumenDevoluciones = crearResumenDevoluciones(totalesDevoluciones, 12);
                documento.add(tablaResumenDevoluciones);

                documento.add(new Paragraph(""));
                documento.add(new Paragraph(""));

                Paragraph resumenAdelantos = new Paragraph("Resumen Adelantos:");
                resumenAdelantos.setUnderline(1, -1);
                resumenAdelantos.setFontSize(12);
                documento.add(resumenAdelantos);

                documento.add(new Paragraph(""));

                Table tablaResumenAdelantos = crearResumenAdelantos(adelantosTotales, 12);
                documento.add(tablaResumenAdelantos);

                int numeroGuiasVentas = guiasVentas.size();

                for (int i=0; i<numeroGuiasVentas; i++) {
                    GuiaVenta guia = guiasVentas.get(i);
                    documento.add(new AreaBreak());
                    crearGuiaVenta(documento, guia, i+1, 12);
                }

                int numeroGuiasDevoluciones = guiasDevoluciones.size();

                for (int i=0; i<numeroGuiasDevoluciones; i++) {
                    GuiaDevolucion guia = guiasDevoluciones.get(i);
                    documento.add(new AreaBreak());
                    crearGuiaDevolucion(documento, guia, i+1, 12);
                }

                int numeroGuiasAdelantos = guiasAdelantos.size();

                for (int i=0; i<numeroGuiasAdelantos; i++) {
                    GuiaAdelanto guia = guiasAdelantos.get(i);
                    documento.add(new AreaBreak());
                    crearGuiaAdelanto(documento, guia, i+1, 12);
                }

                documento.close();

            } catch (Exception e) { }

        }

    }

    public static void imprimirGuiaVentaPdf(GuiaVenta guia, int numeroGuia) {

        try {

            ByteArrayOutputStream documentoOutputBytes = new ByteArrayOutputStream();
            PdfWriter escritorPdf = new PdfWriter(documentoOutputBytes);
            PdfDocument documentoPdf = new PdfDocument(escritorPdf);

            Document documento = new Document(documentoPdf, PageSize.A5);
            documento.setMargins(36, 36, 36, 36);

            crearGuiaVenta(documento, guia, numeroGuia, 8);

            documento.close();

            ByteArrayInputStream documentoInputBytes = new ByteArrayInputStream(documentoOutputBytes.toByteArray());
            PDDocument documentoPD = PDDocument.load(documentoInputBytes);

            PrinterJob trabajoImpresion = PrinterJob.getPrinterJob();
            trabajoImpresion.setPageable(new PDFPageable(documentoPD));

            if (trabajoImpresion.printDialog() == true) {
                trabajoImpresion.print();
            }

        } catch (Exception e) { }

    }

    public static void imprimirGuiaDevolucionPdf(GuiaDevolucion guia, int numeroGuia) {

        try {

            ByteArrayOutputStream documentoOutputBytes = new ByteArrayOutputStream();
            PdfWriter escritorPdf = new PdfWriter(documentoOutputBytes);
            PdfDocument documentoPdf = new PdfDocument(escritorPdf);

            Document documento = new Document(documentoPdf, PageSize.A5);
            documento.setMargins(36, 36, 36, 36);

            crearGuiaDevolucion(documento, guia, numeroGuia, 8);

            documento.close();

            ByteArrayInputStream documentoInputBytes = new ByteArrayInputStream(documentoOutputBytes.toByteArray());
            PDDocument documentoPD = PDDocument.load(documentoInputBytes);

            PrinterJob trabajoImpresion = PrinterJob.getPrinterJob();
            trabajoImpresion.setPageable(new PDFPageable(documentoPD));

            if (trabajoImpresion.printDialog() == true) {
                trabajoImpresion.print();
            }

        } catch (Exception e) { }

    }

    public static void imprimirGuiaAdelantoPdf(GuiaAdelanto guia, int numeroGuia) {

        try {

            ByteArrayOutputStream documentoOutputBytes = new ByteArrayOutputStream();
            PdfWriter escritorPdf = new PdfWriter(documentoOutputBytes);
            PdfDocument documentoPdf = new PdfDocument(escritorPdf);

            Document documento = new Document(documentoPdf, PageSize.A5);
            documento.setMargins(36, 36, 36, 36);

            crearGuiaAdelanto(documento, guia, numeroGuia, 8);

            documento.close();

            ByteArrayInputStream documentoInputBytes = new ByteArrayInputStream(documentoOutputBytes.toByteArray());
            PDDocument documentoPD = PDDocument.load(documentoInputBytes);

            PrinterJob trabajoImpresion = PrinterJob.getPrinterJob();
            trabajoImpresion.setPageable(new PDFPageable(documentoPD));

            if (trabajoImpresion.printDialog() == true) {
                trabajoImpresion.print();
            }

        } catch (Exception e) { }

    }

    public static String crearExtensionPdf(String ruta) {

        if (ruta.endsWith(".pdf")) {
            return ruta;
        } else {
            return ruta + ".pdf";
        }

    }

    public static void crearGuiaVenta(Document documento, GuiaVenta guia, int numeroGuia, int tamañoLetra) {

        Paragraph titulo = new Paragraph("Guia Nº" + numeroGuia + ":");
        titulo.setUnderline(1, -1);
        titulo.setFontSize(tamañoLetra);
        documento.add(titulo);

        documento.add(new Paragraph(""));

        Paragraph cliente = new Paragraph("Cliente: " + guia.getCliente());
        cliente.setFontSize(tamañoLetra);
        documento.add(cliente);

        Paragraph tipoGuia = new Paragraph("Tipo de Guia: Venta");
        tipoGuia.setFontSize(tamañoLetra);
        documento.add(tipoGuia);

        Paragraph fecha = new Paragraph("Fecha: " + obtenerFecha());
        fecha.setFontSize(tamañoLetra);
        documento.add(fecha);

        documento.add(new Paragraph(""));

        String[][] contenidoGuia = guia.getContenido();
        Table tablaGuia = crearTablaGuia(contenidoGuia, tamañoLetra);
        documento.add(tablaGuia);

        documento.add(new Paragraph(""));
        documento.add(new Paragraph(""));

        List totales = new List();
        totales.setListSymbol("•");
        totales.setSymbolIndent(tamañoLetra);
        totales.setFontSize(tamañoLetra);
        totales.add("Importe Total: S/ " + guia.getImporteTotal());
        totales.add("Descuento: S/ " + guia.getMontoDescuento());
        totales.add("A Cuenta: S/ " + guia.getMontoCancelado());
        totales.add("Saldo: S/ " + guia.getSaldoCliente());
        totales.add("Peso Total: " + guia.getPesoTotal() + " kg");
        totales.add("Rollos Total: " + guia.getRollosTotal() + " rollos");
        totales.add("Bolsas Total: " + guia.getBolsasTotal() + " bolsas");
        documento.add(totales);

    }

    public static void crearGuiaDevolucion(Document documento, GuiaDevolucion guia, int numeroGuia, int tamañoLetra) {

        Paragraph titulo = new Paragraph("Guia Nº" + numeroGuia + ":");
        titulo.setUnderline(1, -1);
        titulo.setFontSize(tamañoLetra);
        documento.add(titulo);

        documento.add(new Paragraph(""));

        Paragraph cliente = new Paragraph("Cliente: " + guia.getCliente());
        cliente.setFontSize(tamañoLetra);
        documento.add(cliente);

        Paragraph tipoGuia = new Paragraph("Tipo de Guia: Devolucion");
        tipoGuia.setFontSize(tamañoLetra);
        documento.add(tipoGuia);

        Paragraph fecha = new Paragraph("Fecha: " + obtenerFecha());
        fecha.setFontSize(tamañoLetra);
        documento.add(fecha);

        documento.add(new Paragraph(""));

        String[][] contenidoGuia = guia.getContenido();
        Table tablaGuia = crearTablaGuia(contenidoGuia, tamañoLetra);
        documento.add(tablaGuia);

        documento.add(new Paragraph(""));
        documento.add(new Paragraph(""));

        List totales = new List();
        totales.setListSymbol("•");
        totales.setSymbolIndent(tamañoLetra);
        totales.setFontSize(tamañoLetra);
        totales.add("Importe Total: S/ " + guia.getImporteTotal());
        totales.add("Peso Total: " + guia.getPesoTotal() + " kg");
        totales.add("Rollos Total: " + guia.getRollosTotal() + " rollos");
        totales.add("Bolsas Total: " + guia.getBolsasTotal() + " bolsas");
        totales.add("Observacion: " + guia.getObservacion());
        documento.add(totales);

    }

    public static void crearGuiaAdelanto(Document documento, GuiaAdelanto guia, int numeroGuia, int tamañoLetra) {

        Paragraph titulo = new Paragraph("Guia Nº" + numeroGuia + ":");
        titulo.setUnderline(1, -1);
        titulo.setFontSize(tamañoLetra);
        documento.add(titulo);

        documento.add(new Paragraph(""));

        Paragraph cliente = new Paragraph("Cliente: " + guia.getCliente());
        cliente.setFontSize(tamañoLetra);
        documento.add(cliente);

        Paragraph tipoGuia = new Paragraph("Tipo de Guia: Adelanto");
        tipoGuia.setFontSize(tamañoLetra);
        documento.add(tipoGuia);

        Paragraph fecha = new Paragraph("Fecha: " + obtenerFecha());
        fecha.setFontSize(tamañoLetra);
        documento.add(fecha);

        documento.add(new Paragraph(""));

        List totales = new List();
        totales.setListSymbol("•");
        totales.setSymbolIndent(tamañoLetra);
        totales.setFontSize(tamañoLetra);
        totales.add("Adelanto: S/ " + guia.getMontoAdelanto());
        totales.add("Observacion: " + guia.getObservacion());
        documento.add(totales);

    }

    public static Table crearResumenVentas(String[] totalesDia, int tamañoLetra) {

        float[] dimensionesTablaResumen = new float[]{125, 105};
        Table tablaResumen = new Table(dimensionesTablaResumen);

        Cell encabezadoVentasTotales = crearCelda("Ventas Totales:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoVentasTotales);
        Cell celdaVentasTotales = crearCelda("S/ " + totalesDia[0], tamañoLetra, false);
        tablaResumen.addCell(celdaVentasTotales);

        Cell encabezadoIngresosTotales = crearCelda("Cobranzas:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoIngresosTotales);
        Cell celdaIngresosTotales = crearCelda("S/ " + totalesDia[1], tamañoLetra, false);
        tablaResumen.addCell(celdaIngresosTotales);

        Cell encabezadoCreditoOtorgado = crearCelda("Credito Otorgado:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoCreditoOtorgado);
        Cell celdaCreditoOtorgado = crearCelda("S/ " + totalesDia[2], tamañoLetra, false);
        tablaResumen.addCell(celdaCreditoOtorgado);

        Cell encabezadoKilosVendidos = crearCelda("Kilos Vendidos:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoKilosVendidos);
        Cell celdaKilosVendidos = crearCelda(totalesDia[3] + " kilos", tamañoLetra, false);
        tablaResumen.addCell(celdaKilosVendidos);

        Cell encabezadoRollosVendidos = crearCelda("Rollos Vendidos:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoRollosVendidos);
        Cell celdaRollosVendidos = crearCelda(totalesDia[4] + " rollos", tamañoLetra, false);
        tablaResumen.addCell(celdaRollosVendidos);

        Cell encabezadoBolsasVendidas = crearCelda("Bolsas Vendidas:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoBolsasVendidas);
        Cell celdaBolsasVendidas = crearCelda(totalesDia[5] + " bolsas", tamañoLetra, false);
        tablaResumen.addCell(celdaBolsasVendidas);

        return tablaResumen;

    }

    public static Table crearResumenDevoluciones(String[] totalesDia, int tamañoLetra) {

        float[] dimensionesTablaResumen = new float[]{125, 105};
        Table tablaResumen = new Table(dimensionesTablaResumen);

        Cell encabezadoDevolucionesTotales = crearCelda("Devoluciones Totales:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoDevolucionesTotales);
        Cell celdaDevolucionesTotales = crearCelda("S/ " + totalesDia[0], tamañoLetra, false);
        tablaResumen.addCell(celdaDevolucionesTotales);

        Cell encabezadoKilosDevueltos = crearCelda("Kilos Devueltos:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoKilosDevueltos);
        Cell celdaKilosDevueltos = crearCelda(totalesDia[1] + " kilos", tamañoLetra, false);
        tablaResumen.addCell(celdaKilosDevueltos);

        Cell encabezadoRollosDevueltos = crearCelda("Rollos Devueltos:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoRollosDevueltos);
        Cell celdaRollosDevueltos = crearCelda(totalesDia[2] + " rollos", tamañoLetra, false);
        tablaResumen.addCell(celdaRollosDevueltos);

        Cell encabezadoBolsasDevueltas = crearCelda("Bolsas Devueltas:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoBolsasDevueltas);
        Cell celdaBolsasDevueltas = crearCelda(totalesDia[3] + " bolsas", tamañoLetra, false);
        tablaResumen.addCell(celdaBolsasDevueltas);

        return tablaResumen;

    }

    public static Table crearResumenAdelantos(String adelantosTotales, int tamañoLetra) {

        float[] dimensionesTablaResumen = new float[]{125, 105};
        Table tablaResumen = new Table(dimensionesTablaResumen);

        Cell encabezadoAdelantosTotales = crearCelda("Adelantos Totales:", tamañoLetra, true);
        tablaResumen.addCell(encabezadoAdelantosTotales);
        Cell celdaAdelantostotales = crearCelda(adelantosTotales, tamañoLetra, false);
        tablaResumen.addCell(celdaAdelantostotales);

        return tablaResumen;

    }

    public static Table crearTablaGuia(String[][] contenidoGuia, int tamañoLetra) {

        float[] dimensionesTablaGuia = new float[]{40, 60, 205, 55, 70};
        Table tablaGuia = new Table(dimensionesTablaGuia);

        Cell encabezadoItem = crearCelda("Item", tamañoLetra, true);
        tablaGuia.addHeaderCell(encabezadoItem);
        Cell encabezadoPeso = crearCelda("Peso", tamañoLetra, true);
        tablaGuia.addHeaderCell(encabezadoPeso);
        Cell encabezadoDescripcion = crearCelda("Descripcion", tamañoLetra, true);
        tablaGuia.addHeaderCell(encabezadoDescripcion);
        Cell encabezadoPrecio = crearCelda("P/Kg", tamañoLetra, true);
        tablaGuia.addHeaderCell(encabezadoPrecio);
        Cell encabezadoImporte = crearCelda("Importe", tamañoLetra, true);
        tablaGuia.addHeaderCell(encabezadoImporte);

        int numeroFilas = contenidoGuia.length;
        int numeroColumnas = tablaGuia.getNumberOfColumns();

        for (int j=0; j<numeroFilas; j++) {
            for (int k=0; k<numeroColumnas; k++) {
                if (k == 2) {
                    Cell celdaDescripcion = crearCeldaDescripcion(contenidoGuia[j][k], tamañoLetra);
                    tablaGuia.addCell(celdaDescripcion);
                } else {
                    Cell celda = crearCelda(contenidoGuia[j][k], tamañoLetra, false);
                    tablaGuia.addCell(celda);
                }
            }
        }

        return tablaGuia;

    }

    public static Cell crearCelda(String texto, int tamañoLetra, boolean encabezado) {

        Cell celda = new Cell();
        Paragraph textoCelda = new Paragraph(texto);
        textoCelda.setFontSize(tamañoLetra);
        celda.add(textoCelda);

        if (encabezado == true) {
            celda.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        }

        return celda;

    }

    public static Cell crearCeldaDescripcion(String texto, int tamañoLetra) {

        Cell celdaDescripcion = new Cell();

        String articulo = texto.substring(texto.indexOf("<html>")+7, texto.indexOf(" <br>"));
        Paragraph textoCeldaPrimeraParte = new Paragraph(articulo);
        textoCeldaPrimeraParte.setFontSize(tamañoLetra);
        celdaDescripcion.add(textoCeldaPrimeraParte);

        String color = texto.substring(texto.indexOf("<br>")+5, texto.indexOf(" </html>"));
        Paragraph textoCeldaSegundaParte = new Paragraph(color);
        textoCeldaSegundaParte.setFontSize(tamañoLetra);
        celdaDescripcion.add(textoCeldaSegundaParte);

        return celdaDescripcion;

    }

}
