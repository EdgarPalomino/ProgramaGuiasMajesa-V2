package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaDevolucion;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.*;
import static com.majesa.programa.MetodosPdf.imprimirGuiaDevolucionPdf;
import static com.majesa.programa.MetodosTxt.actualizarGuiasDevoluciones;

public class PanelVerDevolucion extends JPanel {

    // Definiendo los componentes del panel ver devolucion

    private TextoCuadro verGuiaTexto;
    private TextoCuadro verImporteTotalTexto;
    private TextoCuadro verObservacionTexto;
    private TextoCuadro verPesoTotalTexto;
    private TextoCuadro verRollosTotalTexto;
    private TextoCuadro verBolsasTotalTexto;
    private TextoCuadro verDevolucionesTotalesTexto;
    private TextoCuadro verKilosDevueltosTexto;
    private TextoCuadro verRollosDevueltosTexto;
    private TextoCuadro verBolsasDevueltasTexto;
    private CuadroCombinado verGuiaCuadro;
    private CuadroTextoGrande verImporteTotalCuadro;
    private CuadroObservacion verObservacionCuadro;
    private CuadroTextoGrande verPesoTotalCuadro;
    private CuadroTextoPequeño verRollosTotalCuadro;
    private CuadroTextoPequeño verBolsasTotalCuadro;
    private CuadroTextoGrande verDevolucionesTotalesCuadro;
    private CuadroTextoGrande verKilosVendidosCuadro;
    private CuadroTextoPequeño verRollosVendidosCuadro;
    private CuadroTextoPequeño verBolsasVendidasCuadro;
    private Alerta verAlerta;
    private BotonFuncion verBorrar;
    private BotonFuncion verDescargar;
    private BotonFuncion verImprimir;
    private Tabla verTabla;
    private BarraTabla verBarraTabla;


    public PanelVerDevolucion(ArrayList<GuiaDevolucion> guiasDevoluciones, String[] encabezadoTablas, Ventana ventana) {

        // Creando el panel ver devolucion y organizando sus componentes

        this.setLayout(null);

        verGuiaTexto = new TextoCuadro("Guia:", 30, 30);
        this.add(verGuiaTexto);

        verImporteTotalTexto = new TextoCuadro("Importe Total:", 675, 300);
        this.add(verImporteTotalTexto);

        verObservacionTexto = new TextoCuadro("Observacion:", 675, 340);
        this.add(verObservacionTexto);

        verPesoTotalTexto = new TextoCuadro("Peso Total:", 430, 300);
        this.add(verPesoTotalTexto);

        verRollosTotalTexto = new TextoCuadro("Rollos Total:", 430, 340);
        this.add(verRollosTotalTexto);

        verBolsasTotalTexto = new TextoCuadro("Bolsas Total:", 430, 380);
        this.add(verBolsasTotalTexto);

        verDevolucionesTotalesTexto = new TextoCuadro("Devoluciones Totales:", 60, 100);
        this.add(verDevolucionesTotalesTexto);

        verKilosDevueltosTexto = new TextoCuadro("Kilos Devueltos:", 60, 170);
        this.add(verKilosDevueltosTexto);

        verRollosDevueltosTexto = new TextoCuadro("Rollos Devueltos:", 30, 240);
        this.add(verRollosDevueltosTexto);

        verBolsasDevueltasTexto = new TextoCuadro("Bolsas Devueltas:", 200, 240);
        this.add(verBolsasDevueltasTexto);

        verAlerta = new Alerta("", 35, 320);
        this.add(verAlerta);

        verGuiaCuadro = new CuadroCombinado(new String[]{"Seleccionar"}, 30, 60);
        verGuiaCuadro.addActionListener(e -> mostrarGuia(guiasDevoluciones, encabezadoTablas));
        this.add(verGuiaCuadro);

        verImporteTotalCuadro = new CuadroTextoGrande("0.00", 775, 300, false);
        this.add(verImporteTotalCuadro);

        verObservacionCuadro = new CuadroObservacion("", 675, 370, false);
        this.add(verObservacionCuadro);

        verPesoTotalCuadro = new CuadroTextoGrande("0.00", 510, 300, false);
        this.add(verPesoTotalCuadro);

        verRollosTotalCuadro = new CuadroTextoPequeño("0", 545, 340, false);
        this.add(verRollosTotalCuadro);

        verBolsasTotalCuadro = new CuadroTextoPequeño("0", 545, 380, false);
        this.add(verBolsasTotalCuadro);

        verDevolucionesTotalesCuadro = new CuadroTextoGrande("0.00", 60, 130, false);
        this.add(verDevolucionesTotalesCuadro);

        verKilosVendidosCuadro = new CuadroTextoGrande("0.00", 60, 200, false);
        this.add(verKilosVendidosCuadro);

        verRollosVendidosCuadro = new CuadroTextoPequeño("0", 140, 240, false);
        this.add(verRollosVendidosCuadro);

        verBolsasVendidasCuadro = new CuadroTextoPequeño("0", 310, 240, false);
        this.add(verBolsasVendidasCuadro);

        verBorrar = new BotonFuncion("Borrar Guia", 240, 40);
        verBorrar.addActionListener(e -> borrarGuia(guiasDevoluciones));
        this.add(verBorrar);

        verDescargar = new BotonFuncion("Descargar", 240, 320);
        verDescargar.addActionListener(e -> descargarResumenDia(ventana));
        this.add(verDescargar);

        verImprimir = new BotonFuncion("Imprimir", 240, 375);
        verImprimir.addActionListener(e -> imprimirGuia(guiasDevoluciones));
        this.add(verImprimir);

        verTabla = new Tabla(new String[0][5], encabezadoTablas);
        verBarraTabla = new BarraTabla(verTabla, 425, 30);
        this.add(verBarraTabla);

    }

    //--------------------------------------------------\\

    // Metodos para el panel ver devolucion

    public void editarGuiasCuadro(ArrayList<GuiaDevolucion> guiasDevoluciones) {

        int numeroGuias = guiasDevoluciones.size();
        String[] nuevoContenidoGuiaCuadro = new String[numeroGuias+1];
        nuevoContenidoGuiaCuadro[0] = "Seleccionar";

        for (int i=0; i<numeroGuias; i++) {
            String cliente = guiasDevoluciones.get(i).getCliente();
            nuevoContenidoGuiaCuadro[i+1] = "Nº" + (i+1) + " " + cliente;
        }

        DefaultComboBoxModel nuevoModeloGuiaCuadro = new DefaultComboBoxModel(nuevoContenidoGuiaCuadro);
        verGuiaCuadro.setModel(nuevoModeloGuiaCuadro);

    }

    public void mostrarGuia(ArrayList<GuiaDevolucion> guiasDevoluciones, String[] encabezadoTablas) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaDevolucion guia = guiasDevoluciones.get(guiaElegida-1);
            String[][] nuevoContenidoTabla = guia.getContenido();

            actualizarTabla(verTabla, nuevoContenidoTabla, encabezadoTablas);

            verObservacionCuadro.setText(guia.getObservacion());
            verImporteTotalCuadro.setText(guia.getImporteTotal());
            verPesoTotalCuadro.setText(guia.getPesoTotal());
            verRollosTotalCuadro.setText(guia.getRollosTotal());
            verBolsasTotalCuadro.setText(guia.getBolsasTotal());

        } else {

            resetearTabla(verTabla);

            verObservacionCuadro.setText("");
            verImporteTotalCuadro.setText("0.00");
            verPesoTotalCuadro.setText("0.00");
            verRollosTotalCuadro.setText("0");
            verBolsasTotalCuadro.setText("0");

        }

    }

    public void borrarGuia(ArrayList<GuiaDevolucion> guiasDevoluciones) {

        int guiaEscogida = verGuiaCuadro.getSelectedIndex();

        if (guiaEscogida > 0) {

            guiasDevoluciones.remove(guiaEscogida-1);
            actualizarGuiasDevoluciones(guiasDevoluciones);
            editarGuiasCuadro(guiasDevoluciones);
            verGuiaCuadro.setSelectedIndex(0);

            actualizarTotalesDia(guiasDevoluciones);
            verAlerta.setText("<html><center> Guia borrada!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para eliminarla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

    public void actualizarTotalesDia(ArrayList<GuiaDevolucion> guiasDevoluciones) {

        int numeroGuias = guiasDevoluciones.size();
        BigDecimal devolucionesTotales = new BigDecimal("0.00");
        BigDecimal kilosDevueltos = new BigDecimal("0.00");
        int rollosDevueltos = 0;
        int bolsasDevueltas = 0;

        for (int i=0; i<numeroGuias; i++) {

            GuiaDevolucion guia = guiasDevoluciones.get(i);

            BigDecimal importeTotal = new BigDecimal(guia.getImporteTotal());
            BigDecimal pesoTotal = new BigDecimal(guia.getPesoTotal());
            devolucionesTotales = devolucionesTotales.add(importeTotal);
            kilosDevueltos = kilosDevueltos.add(pesoTotal);

            String rollosTotal = guia.getRollosTotal();
            String bolsasTotal = guia.getBolsasTotal();
            rollosDevueltos += Integer.parseInt(rollosTotal);
            bolsasDevueltas += Integer.parseInt(bolsasTotal);

        }

        verDevolucionesTotalesCuadro.setText(devolucionesTotales.toString());
        verKilosVendidosCuadro.setText(kilosDevueltos.toString());
        verRollosVendidosCuadro.setText(Integer.toString(rollosDevueltos));
        verBolsasVendidasCuadro.setText(Integer.toString(bolsasDevueltas));

    }

    public String[] obtenerTotalesDia() {

        String[] totalesDia = {verDevolucionesTotalesCuadro.getText(), verKilosVendidosCuadro.getText(),
                                verRollosVendidosCuadro.getText(), verBolsasVendidasCuadro.getText()};

        return totalesDia;

    }

    public void descargarResumenDia(Ventana ventana) {

        ventana.descargarResumenDia();

        verAlerta.setText("<html><center> Resumen del dia <br> descargado!!! </center></html>");
        resetearAlerta(verAlerta);

    }

    public void imprimirGuia(ArrayList<GuiaDevolucion> guiaDevoluciones) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaDevolucion guia = guiaDevoluciones.get(guiaElegida-1);
            imprimirGuiaDevolucionPdf(guia, guiaElegida);

            verAlerta.setText("<html><center> Guia impresa!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para imprimirla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

}
