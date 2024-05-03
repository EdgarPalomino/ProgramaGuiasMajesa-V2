package com.majesa.programa;

public class GuiaAdelanto {

    // Atributos de cada guia de adelanto

    private String cliente;
    private String observacion;
    private String montoAdelanto;

    // Constructor de cada guia de adelanto

    public GuiaAdelanto(String cliente, String observacion, String montoAdelanto) {

        this.cliente = cliente;
        this.observacion = observacion;
        this.montoAdelanto = montoAdelanto;

    }

    // Metodos para cada guia de adelanto

    public String getCliente() {
        return cliente;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getMontoAdelanto() {
        return montoAdelanto;
    }

}
