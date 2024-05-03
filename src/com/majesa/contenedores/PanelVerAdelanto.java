package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaAdelanto;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.resetearAlerta;
import static com.majesa.programa.MetodosPdf.imprimirGuiaAdelantoPdf;
import static com.majesa.programa.MetodosTxt.actualizarGuiasAdelantos;

public class PanelVerAdelanto extends JPanel {

    // Definiendo los componentes del panel ver adelanto

    private TextoCuadro verGuiaTexto;
    private TextoCuadro verAdelantoTexto;
    private TextoCuadro verObservacionTexto;
    private TextoCuadro verAdelantosTotalesTexto;
    private CuadroCombinado verGuiaCuadro;
    private CuadroTextoGrande verAdelantoCuadro;
    private CuadroObservacion verObservacionCuadro;
    private CuadroTextoGrande verAdelantosTotalesCuadro;
    private Alerta verAlerta;
    private BotonFuncion verBorrar;
    private BotonFuncion verDescargar;
    private BotonFuncion verImprimir;


    public PanelVerAdelanto(ArrayList<GuiaAdelanto> guiasAdelantos, Ventana ventana) {

        // Creando el panel ver adelanto y organizando sus componentes

        this.setLayout(null);

        verGuiaTexto = new TextoCuadro("Guia:", 155, 95);
        this.add(verGuiaTexto);

        verAdelantoTexto = new TextoCuadro("Adelanto:", 555, 235);
        this.add(verAdelantoTexto);

        verObservacionTexto = new TextoCuadro("Observacion:", 555, 275);
        this.add(verObservacionTexto);

        verAdelantosTotalesTexto = new TextoCuadro("Adelantos Totales:", 185, 165);
        this.add(verAdelantosTotalesTexto);

        verAlerta = new Alerta("", 160, 275);
        this.add(verAlerta);

        verGuiaCuadro = new CuadroCombinado(new String[]{"Seleccionar"}, 155, 125);
        verGuiaCuadro.addActionListener(e -> mostrarGuia(guiasAdelantos));
        this.add(verGuiaCuadro);

        verAdelantoCuadro = new CuadroTextoGrande("0.00", 655, 235, false);
        this.add(verAdelantoCuadro);

        verObservacionCuadro = new CuadroObservacion("", 555, 305, false);
        this.add(verObservacionCuadro);

        verAdelantosTotalesCuadro = new CuadroTextoGrande("0.00", 185, 195, false);
        this.add(verAdelantosTotalesCuadro);

        verBorrar = new BotonFuncion("Borrar Guia", 365, 105);
        verBorrar.addActionListener(e -> borrarGuia(guiasAdelantos));
        this.add(verBorrar);

        verDescargar = new BotonFuncion("Descargar", 365, 275);
        verDescargar.addActionListener(e -> descargarResumenDia(ventana));
        this.add(verDescargar);

        verImprimir = new BotonFuncion("Imprimir", 365, 330);
        verImprimir.addActionListener(e -> imprimirGuia(guiasAdelantos));
        this.add(verImprimir);

    }

    //--------------------------------------------------\\

    // Metodos para el panel ver adelanto

    public void editarGuiasCuadro(ArrayList<GuiaAdelanto> guiasAdelantos) {

        int numeroGuias = guiasAdelantos.size();
        String[] nuevoContenidoGuiaCuadro = new String[numeroGuias+1];
        nuevoContenidoGuiaCuadro[0] = "Seleccionar";

        for (int i=0; i<numeroGuias; i++) {
            String cliente = guiasAdelantos.get(i).getCliente();
            nuevoContenidoGuiaCuadro[i+1] = "Nº" + (i+1) + " " + cliente;
        }

        DefaultComboBoxModel nuevoModeloGuiaCuadro = new DefaultComboBoxModel(nuevoContenidoGuiaCuadro);
        verGuiaCuadro.setModel(nuevoModeloGuiaCuadro);

    }

    public void mostrarGuia(ArrayList<GuiaAdelanto> guiasAdelantos) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaAdelanto guia = guiasAdelantos.get(guiaElegida-1);

            verObservacionCuadro.setText(guia.getObservacion());
            verAdelantoCuadro.setText(guia.getMontoAdelanto());

        } else {
            verObservacionCuadro.setText("");
            verAdelantoCuadro.setText("");
        }

    }

    public void borrarGuia(ArrayList<GuiaAdelanto> guiasAdelantos) {

        int guiaEscogida = verGuiaCuadro.getSelectedIndex();

        if (guiaEscogida > 0) {

            guiasAdelantos.remove(guiaEscogida-1);
            actualizarGuiasAdelantos(guiasAdelantos);
            editarGuiasCuadro(guiasAdelantos);
            verGuiaCuadro.setSelectedIndex(0);

            actualizarAdelantosTotales(guiasAdelantos);
            verAlerta.setText("<html><center> Guia borrada!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para eliminarla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

    public void actualizarAdelantosTotales(ArrayList<GuiaAdelanto> guiaAdelantos) {

        int numeroGuias = guiaAdelantos.size();
        BigDecimal adelantosTotales = new BigDecimal("0.00");

        for (int i=0; i<numeroGuias; i++) {

            GuiaAdelanto guia = guiaAdelantos.get(i);

            BigDecimal montoAdelanto = new BigDecimal(guia.getMontoAdelanto());
            adelantosTotales = adelantosTotales.add(montoAdelanto);

        }

        verAdelantosTotalesCuadro.setText(adelantosTotales.toString());

    }

    public String obtenerAdelantosTotales() {

        String adelantosTotales = verAdelantosTotalesCuadro.getText();

        return adelantosTotales;

    }

    public void descargarResumenDia(Ventana ventana) {

        ventana.descargarResumenDia();

        verAlerta.setText("<html><center> Resumen del dia <br> descargado!!! </center></html>");
        resetearAlerta(verAlerta);

    }

    public void imprimirGuia(ArrayList<GuiaAdelanto> guiasAdelantos) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaAdelanto guia = guiasAdelantos.get(guiaElegida-1);
            imprimirGuiaAdelantoPdf(guia, guiaElegida);

            verAlerta.setText("<html><center> Guia impresa!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para imprimirla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

}
