package com.majesa.programa;

public class GuiaVenta {

    // Atributos de cada guia de venta

    private String cliente;
    private String[] totales;
    private String[][] contenido;

    // Constructor para cada guia de venta

    public GuiaVenta(String cliente, String[] totales, String[][] contenido) {

        this.cliente = cliente;
        this.totales = totales;
        this.contenido = contenido;

    }

    // Metodos para cada guia de venta

    public String getCliente() {
        return cliente;
    }

    public String[] getTotales() {
        return totales;
    }

    public String getImporteTotal() {
        return totales[0];
    }

    public String getMontoDescuento() {
        return totales[1];
    }

    public String getMontoCancelado() {
        return totales[2];
    }

    public String getSaldoCliente() {
        return totales[3];
    }

    public String getPesoTotal() {
        return totales[4];
    }

    public String getRollosTotal() {
        return totales[5];
    }

    public String getBolsasTotal() {
        return totales[6];
    }

    public String[][] getContenido() {
        return contenido;
    }

}
