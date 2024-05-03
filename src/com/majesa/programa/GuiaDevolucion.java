package com.majesa.programa;

public class GuiaDevolucion {

    // Atributos de cada guia de devolucion

    private String cliente;
    private String observacion;
    private String[] totales;
    private String[][] contenido;

    // Constructor para cada guia de devolucion

    public GuiaDevolucion(String cliente, String observacion, String[] totales, String[][] contenido) {

        this.cliente = cliente;
        this.observacion = observacion;
        this.totales = totales;
        this.contenido = contenido;

    }

    // Metodos para cada guia de devolucion

    public String getCliente() {
        return cliente;
    }

    public String getObservacion() {
        return observacion;
    }

    public String[] getTotales() {
        return totales;
    }

    public String getImporteTotal() {
        return totales[0];
    }

    public String getPesoTotal() {
        return totales[1];
    }

    public String getRollosTotal() {
        return totales[2];
    }

    public String getBolsasTotal() {
        return totales[3];
    }

    public String[][] getContenido() {
        return contenido;
    }

}
