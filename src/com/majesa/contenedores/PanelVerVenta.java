package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaVenta;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.*;
import static com.majesa.programa.MetodosPdf.imprimirGuiaVentaPdf;
import static com.majesa.programa.MetodosTxt.actualizarGuiasVentas;

public class PanelVerVenta extends JPanel {

    // Definiendo los componentes del panel ver venta

    private TextoCuadro verGuiaTexto;
    private TextoCuadro verImporteTotalTexto;
    private TextoCuadro verDescuentoTexto;
    private TextoCuadro verCanceladoTexto;
    private TextoCuadro verSaldoClienteTexto;
    private TextoCuadro verPesoTotalTexto;
    private TextoCuadro verRollosTotalTexto;
    private TextoCuadro verBolsasTotalTexto;
    private TextoCuadro verVentasTotalesTexto;
    private TextoCuadro verIngresosTotalesTexto;
    private TextoCuadro verCreditoOtorgadoTexto;
    private TextoCuadro verKilosVendidosTexto;
    private TextoCuadro verRollosVendidosTexto;
    private TextoCuadro verBolsasVendidasTexto;
    private CuadroCombinado verGuiaCuadro;
    private CuadroTextoGrande verImporteTotalCuadro;
    private CuadroTextoGrande verDescuentoCuadro;
    private CuadroTextoGrande verCanceladoCuadro;
    private CuadroTextoGrande verSaldoClienteCuadro;
    private CuadroTextoGrande verPesoTotalCuadro;
    private CuadroTextoPequeño verRollosTotalCuadro;
    private CuadroTextoPequeño verBolsasTotalCuadro;
    private CuadroTextoGrande verVentasTotalesCuadro;
    private CuadroTextoGrande verIngresosTotalesCuadro;
    private CuadroTextoGrande verCreditoOtorgadoCuadro;
    private CuadroTextoGrande verKilosVendidosCuadro;
    private CuadroTextoPequeño verRollosVendidosCuadro;
    private CuadroTextoPequeño verBolsasVendidasCuadro;
    private Alerta verAlerta;
    private BotonFuncion verBorrar;
    private BotonFuncion verDescargar;
    private BotonFuncion verImprimir;
    private Tabla verTabla;
    private BarraTabla verBarraTabla;


    public PanelVerVenta(ArrayList<GuiaVenta> guiasVentas, String[] encabezadoTablas, Ventana ventana) {

        // Creando el panel ver venta y organizando sus componentes

        this.setLayout(null);

        verGuiaTexto = new TextoCuadro("Guia:", 30, 30);
        this.add(verGuiaTexto);

        verImporteTotalTexto = new TextoCuadro("Importe Total:", 675, 300);
        this.add(verImporteTotalTexto);

        verDescuentoTexto = new TextoCuadro("Descuento:", 675, 340);
        this.add(verDescuentoTexto);

        verCanceladoTexto = new TextoCuadro("Cancelado:", 675, 380);
        this.add(verCanceladoTexto);

        verSaldoClienteTexto = new TextoCuadro("Saldo Cliente:", 675, 420);
        this.add(verSaldoClienteTexto);

        verPesoTotalTexto = new TextoCuadro("Peso Total:", 430, 300);
        this.add(verPesoTotalTexto);

        verRollosTotalTexto = new TextoCuadro("Rollos Total:", 430, 340);
        this.add(verRollosTotalTexto);

        verBolsasTotalTexto = new TextoCuadro("Bolsas Total:", 430, 380);
        this.add(verBolsasTotalTexto);

        verVentasTotalesTexto = new TextoCuadro("Ventas Totales:", 60, 100);
        this.add(verVentasTotalesTexto);

        verIngresosTotalesTexto = new TextoCuadro("Cobranzas:", 210, 100);
        this.add(verIngresosTotalesTexto);

        verCreditoOtorgadoTexto = new TextoCuadro("Credito Otorgado:", 210, 170);
        this.add(verCreditoOtorgadoTexto);

        verKilosVendidosTexto = new TextoCuadro("Kilos Vendidos:", 60, 170);
        this.add(verKilosVendidosTexto);

        verRollosVendidosTexto = new TextoCuadro("Rollos Vendidos:", 30, 240);
        this.add(verRollosVendidosTexto);

        verBolsasVendidasTexto = new TextoCuadro("Bolsas Vendidas:", 200, 240);
        this.add(verBolsasVendidasTexto);

        verAlerta = new Alerta("", 35, 320);
        this.add(verAlerta);

        verGuiaCuadro = new CuadroCombinado(new String[]{"Seleccionar"}, 30, 60);
        verGuiaCuadro.addActionListener(e -> mostrarGuia(guiasVentas, encabezadoTablas));
        this.add(verGuiaCuadro);

        verImporteTotalCuadro = new CuadroTextoGrande("0.00", 775, 300, false);
        this.add(verImporteTotalCuadro);

        verDescuentoCuadro = new CuadroTextoGrande("0.00", 775, 340, false);
        this.add(verDescuentoCuadro);

        verCanceladoCuadro = new CuadroTextoGrande("0.00", 775, 380, false);
        this.add(verCanceladoCuadro);

        verSaldoClienteCuadro = new CuadroTextoGrande("0.00", 775, 420, false);
        this.add(verSaldoClienteCuadro);

        verPesoTotalCuadro = new CuadroTextoGrande("0.00", 510, 300, false);
        this.add(verPesoTotalCuadro);

        verRollosTotalCuadro = new CuadroTextoPequeño("0", 545, 340, false);
        this.add(verRollosTotalCuadro);

        verBolsasTotalCuadro = new CuadroTextoPequeño("0", 545, 380, false);
        this.add(verBolsasTotalCuadro);

        verVentasTotalesCuadro = new CuadroTextoGrande("0.00", 60, 130, false);
        this.add(verVentasTotalesCuadro);

        verIngresosTotalesCuadro = new CuadroTextoGrande("0.00", 210, 130, false);
        this.add(verIngresosTotalesCuadro);

        verCreditoOtorgadoCuadro = new CuadroTextoGrande("0.00", 210, 200, false);
        this.add(verCreditoOtorgadoCuadro);

        verKilosVendidosCuadro = new CuadroTextoGrande("0.00", 60, 200, false);
        this.add(verKilosVendidosCuadro);

        verRollosVendidosCuadro = new CuadroTextoPequeño("0", 140, 240, false);
        this.add(verRollosVendidosCuadro);

        verBolsasVendidasCuadro = new CuadroTextoPequeño("0", 310, 240, false);
        this.add(verBolsasVendidasCuadro);

        verBorrar = new BotonFuncion("Borrar Guia", 240, 40);
        verBorrar.addActionListener(e -> borrarGuia(guiasVentas));
        this.add(verBorrar);

        verDescargar = new BotonFuncion("Descargar", 240, 320);
        verDescargar.addActionListener(e -> descargarResumenDia(ventana));
        this.add(verDescargar);

        verImprimir = new BotonFuncion("Imprimir", 240, 375);
        verImprimir.addActionListener(e -> imprimirGuia(guiasVentas));
        this.add(verImprimir);

        verTabla = new Tabla(new String[0][5], encabezadoTablas);
        verBarraTabla = new BarraTabla(verTabla, 425, 30);
        this.add(verBarraTabla);

    }

    //--------------------------------------------------\\

    // Metodos para el panel ver venta

    public void editarGuiasCuadro(ArrayList<GuiaVenta> guiasVentas) {

        int numeroGuias = guiasVentas.size();
        String[] nuevoContenidoGuiaCuadro = new String[numeroGuias+1];
        nuevoContenidoGuiaCuadro[0] = "Seleccionar";

        for (int i=0; i<numeroGuias; i++) {
            String cliente = guiasVentas.get(i).getCliente();
            nuevoContenidoGuiaCuadro[i+1] = "Nº" + (i+1) + " " + cliente;
        }

        DefaultComboBoxModel nuevoModeloGuiaCuadro = new DefaultComboBoxModel(nuevoContenidoGuiaCuadro);
        verGuiaCuadro.setModel(nuevoModeloGuiaCuadro);

    }

    public void mostrarGuia(ArrayList<GuiaVenta> guiasVentas, String[] encabezadoTablas) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaVenta guia = guiasVentas.get(guiaElegida-1);
            String[][] nuevoContenidoTabla = guia.getContenido();

            actualizarTabla(verTabla, nuevoContenidoTabla, encabezadoTablas);

            verImporteTotalCuadro.setText(guia.getImporteTotal());
            verDescuentoCuadro.setText(guia.getMontoDescuento());
            verCanceladoCuadro.setText(guia.getMontoCancelado());
            verSaldoClienteCuadro.setText(guia.getSaldoCliente());
            verPesoTotalCuadro.setText(guia.getPesoTotal());
            verRollosTotalCuadro.setText(guia.getRollosTotal());
            verBolsasTotalCuadro.setText(guia.getBolsasTotal());

        } else {

            resetearTabla(verTabla);

            verImporteTotalCuadro.setText("0.00");
            verDescuentoCuadro.setText("0.00");
            verCanceladoCuadro.setText("0.00");
            verSaldoClienteCuadro.setText("0.00");
            verPesoTotalCuadro.setText("0.00");
            verRollosTotalCuadro.setText("0");
            verBolsasTotalCuadro.setText("0");

        }

    }

    public void borrarGuia(ArrayList<GuiaVenta> guiasVentas) {

        int guiaEscogida = verGuiaCuadro.getSelectedIndex();

        if (guiaEscogida > 0) {

            guiasVentas.remove(guiaEscogida-1);
            actualizarGuiasVentas(guiasVentas);
            editarGuiasCuadro(guiasVentas);
            verGuiaCuadro.setSelectedIndex(0);

            actualizarTotalesDia(guiasVentas);
            verAlerta.setText("<html><center> Guia borrada!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para eliminarla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

    public void actualizarTotalesDia(ArrayList<GuiaVenta> guiasVentas) {

        int numeroGuias = guiasVentas.size();
        BigDecimal ventasTotales = new BigDecimal("0.00");
        BigDecimal ingresosTotales = new BigDecimal("0.00");
        BigDecimal creditoOtorgado = new BigDecimal("0.00");
        BigDecimal kilosVendidos = new BigDecimal("0.00");
        int rollosVendidos = 0;
        int bolsasVendidas = 0;

        for (int i=0; i<numeroGuias; i++) {

            GuiaVenta guia = guiasVentas.get(i);

            BigDecimal importeTotal = new BigDecimal(guia.getImporteTotal());
            BigDecimal montoCancelado = new BigDecimal(guia.getMontoCancelado());
            BigDecimal saldoCliente = new BigDecimal(guia.getSaldoCliente());
            BigDecimal pesoTotal = new BigDecimal(guia.getPesoTotal());
            ventasTotales = ventasTotales.add(importeTotal);
            ingresosTotales = ingresosTotales.add(montoCancelado);
            creditoOtorgado = creditoOtorgado.add(saldoCliente);
            kilosVendidos = kilosVendidos.add(pesoTotal);

            String rollosTotal = guia.getRollosTotal();
            String bolsasTotal = guia.getBolsasTotal();
            rollosVendidos += Integer.parseInt(rollosTotal);
            bolsasVendidas += Integer.parseInt(bolsasTotal);

        }

        verVentasTotalesCuadro.setText(ventasTotales.toString());
        verIngresosTotalesCuadro.setText(ingresosTotales.toString());
        verCreditoOtorgadoCuadro.setText(creditoOtorgado.toString());
        verKilosVendidosCuadro.setText(kilosVendidos.toString());
        verRollosVendidosCuadro.setText(Integer.toString(rollosVendidos));
        verBolsasVendidasCuadro.setText(Integer.toString(bolsasVendidas));

    }

    public String[] obtenerTotalesDia() {

        String[] totalesDia = {verVentasTotalesCuadro.getText(), verIngresosTotalesCuadro.getText(),
                                verCreditoOtorgadoCuadro.getText(), verKilosVendidosCuadro.getText(),
                                verRollosVendidosCuadro.getText(), verBolsasVendidasCuadro.getText()};

        return totalesDia;

    }

    public void descargarResumenDia(Ventana ventana) {

        ventana.descargarResumenDia();

        verAlerta.setText("<html><center> Resumen del dia <br> descargado!!! </center></html>");
        resetearAlerta(verAlerta);

    }

    public void imprimirGuia(ArrayList<GuiaVenta> guiasVentas) {

        int guiaElegida = verGuiaCuadro.getSelectedIndex();

        if (guiaElegida > 0) {

            GuiaVenta guia = guiasVentas.get(guiaElegida-1);
            imprimirGuiaVentaPdf(guia, guiaElegida);

            verAlerta.setText("<html><center> Guia impresa!!! </center></html>");
            resetearAlerta(verAlerta);

        } else {
            verAlerta.setText("<html><center> Selecciona una guia <br> para imprimirla... </center></html>");
            resetearAlerta(verAlerta);
        }

    }

}
