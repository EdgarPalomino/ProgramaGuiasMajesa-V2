package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaVenta;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static com.majesa.programa.MetodosAyuda.*;
import static com.majesa.programa.MetodosTxt.actualizarGuiasVentas;
import static com.majesa.programa.MetodosTxt.obtenerArticulosyColores;

public class PanelCrearVenta extends JPanel {

    // Definiendo el arraylist que va a contener todos los articulos

    private ArrayList<String> articulos;

    // Definiendo el hashmap que va a contener todos los colores de cada articulo

    private HashMap<String, String[]> colores;

    // Definiendo los componentes del panel crear venta

    private TextoCuadro crearClienteTexto;
    private TextoCuadro crearPesoTexto;
    private TextoCuadro crearArticuloTexto;
    private TextoCuadro crearPrecioTexto;
    private TextoCuadro crearColorTexto;
    private TextoCuadro crearRollosTexto;
    private TextoCuadro crearImporteTotalTexto;
    private TextoCuadro crearDescuentoTexto;
    private TextoCuadro crearCanceladoTexto;
    private TextoCuadro crearSaldoClienteTexto;
    private TextoCuadro crearPesoTotalTexto;
    private TextoCuadro crearRollosTotalTexto;
    private TextoCuadro crearBolsasTotalTexto;
    private CuadroCliente crearClienteCuadro;
    private CuadroTextoGrande crearPesoCuadro;
    private CuadroTextoGrande crearPrecioCuadro;
    private CuadroCombinado crearArticuloCuadro;
    private CuadroCombinado crearColorCuadro;
    private CuadroSpinner crearRollosCuadro;
    private CuadroTextoGrande crearImporteTotalCuadro;
    private CuadroTextoGrande crearDescuentoCuadro;
    private CuadroTextoGrande crearCanceladoCuadro;
    private CuadroTextoGrande crearSaldoClienteCuadro;
    private CuadroTextoGrande crearPesoTotalCuadro;
    private CuadroTextoPequeño crearRollosTotalCuadro;
    private CuadroTextoPequeño crearBolsasTotalCuadro;
    private Alerta crearAlerta;
    private BotonFuncion crearAñadir;
    private BotonFuncion crearQuitar;
    private BotonFuncion crearResetear;
    private BotonFuncion crearGuardar;
    private Tabla crearTabla;
    private BarraTabla crearBarraTabla;


    public PanelCrearVenta(ArrayList<GuiaVenta> guiasVentas, String[] encabezadoTablas) {

        // Creando el arraylist que va a contener todos los articulos

        articulos = new ArrayList<>();

        // Creando el hashmap que va a contener los colores de cada articulo

        colores = new HashMap<>();

        // Obteniendo todos los articulos y los colores de cada articulo

        obtenerArticulosyColores(articulos, colores);

        //--------------------------------------------------\\

        // Creando el panel crear venta y organizando sus componentes

        this.setLayout(null);

        crearClienteTexto = new TextoCuadro("Cliente:", 540, 30);
        this.add(crearClienteTexto);

        crearPesoTexto = new TextoCuadro("Peso:", 540, 70);
        this.add(crearPesoTexto);

        crearPrecioTexto = new TextoCuadro("Precio:", 540, 140);
        this.add(crearPrecioTexto);

        crearArticuloTexto = new TextoCuadro("Articulo:", 670, 70);
        this.add(crearArticuloTexto);

        crearColorTexto = new TextoCuadro("Color:", 670, 140);
        this.add(crearColorTexto);

        crearRollosTexto = new TextoCuadro("Rollos:", 540, 210);
        this.add(crearRollosTexto);

        crearImporteTotalTexto = new TextoCuadro("Importe Total:", 280, 300);
        this.add(crearImporteTotalTexto);

        crearDescuentoTexto = new TextoCuadro("Descuento:", 280, 340);
        this.add(crearDescuentoTexto);

        crearCanceladoTexto = new TextoCuadro("A Cuenta:", 280, 380);
        this.add(crearCanceladoTexto);

        crearSaldoClienteTexto = new TextoCuadro("Saldo:", 280, 420);
        this.add(crearSaldoClienteTexto);

        crearPesoTotalTexto = new TextoCuadro("Peso Total:", 35, 300);
        this.add(crearPesoTotalTexto);

        crearRollosTotalTexto = new TextoCuadro("Rollos Total:", 35, 340);
        this.add(crearRollosTotalTexto);

        crearBolsasTotalTexto = new TextoCuadro("Bolsas Total:", 35, 380);
        this.add(crearBolsasTotalTexto);

        crearAlerta = new Alerta("", 545, 320);
        this.add(crearAlerta);

        crearClienteCuadro = new CuadroCliente("", 600, 30);
        this.add(crearClienteCuadro);

        crearPesoCuadro = new CuadroTextoGrande("", 540, 100, true);
        this.add(crearPesoCuadro);

        crearPrecioCuadro = new CuadroTextoGrande("", 540, 170, true);
        this.add(crearPrecioCuadro);

        crearArticuloCuadro = new CuadroCombinado(articulos.toArray(new String[0]), 670, 100);
        crearArticuloCuadro.addActionListener(e -> {
            editarColorCuadro();
            editarRollosCuadro();
        });
        this.add(crearArticuloCuadro);

        crearColorCuadro = new CuadroCombinado(new String[]{"Seleccionar"}, 670, 170);
        crearColorCuadro.addActionListener(e -> editarRollosCuadro());
        this.add(crearColorCuadro);

        crearRollosCuadro = new CuadroSpinner(540, 240);
        this.add(crearRollosCuadro);

        crearImporteTotalCuadro = new CuadroTextoGrande("0.00", 380, 300, false);
        this.add(crearImporteTotalCuadro);

        crearDescuentoCuadro = new CuadroTextoGrande("", 380,  340, true);
        crearDescuentoCuadro.addActionListener(e -> actualizarSaldo());
        this.add(crearDescuentoCuadro);

        crearCanceladoCuadro = new CuadroTextoGrande("", 380, 380, true);
        crearCanceladoCuadro.addActionListener(e -> actualizarSaldo());
        this.add(crearCanceladoCuadro);

        crearSaldoClienteCuadro = new CuadroTextoGrande("0.00", 380, 420, false);
        this.add(crearSaldoClienteCuadro);

        crearPesoTotalCuadro = new CuadroTextoGrande("0.00", 115, 300, false);
        this.add(crearPesoTotalCuadro);

        crearRollosTotalCuadro = new CuadroTextoPequeño("0", 150, 340, false);
        this.add(crearRollosTotalCuadro);

        crearBolsasTotalCuadro = new CuadroTextoPequeño("0", 150, 380, false);
        this.add(crearBolsasTotalCuadro);

        crearAñadir = new BotonFuncion("Añadir Item", 630, 220);
        crearAñadir.addActionListener(e -> añadirItem(encabezadoTablas));
        this.add(crearAñadir);

        crearQuitar = new BotonFuncion("Quitar Item", 750, 220);
        crearQuitar.addActionListener(e -> quitarItem(encabezadoTablas));
        this.add(crearQuitar);

        crearResetear = new BotonFuncion("Resetear", 750, 320);
        crearResetear.addActionListener(e -> resetearGuia());
        this.add(crearResetear);

        crearGuardar = new BotonFuncion("Guardar", 750, 375);
        crearGuardar.addActionListener(e -> guardarGuia(guiasVentas));
        this.add(crearGuardar);

        crearTabla = new Tabla(new String[0][5], encabezadoTablas);
        crearBarraTabla = new BarraTabla(crearTabla, 30, 30);
        this.add(crearBarraTabla);

    }

    //--------------------------------------------------\\

    // Metodos para el panel crear venta

    public void editarColorCuadro() {

        String articuloElegido = crearArticuloCuadro.getSelectedItem().toString();
        String[] nuevoContenidoColorCuadro = colores.get(articuloElegido);
        DefaultComboBoxModel nuevoModeloColorCuadro = new DefaultComboBoxModel(nuevoContenidoColorCuadro);
        crearColorCuadro.setModel(nuevoModeloColorCuadro);

    }

    public void editarRollosCuadro() {

        String articuloElegido = crearArticuloCuadro.getSelectedItem().toString();
        int colorElegido = crearColorCuadro.getSelectedIndex();

        if (articuloElegido.equals("Seleccionar") || (articuloElegido.equals("Rib Algodon") && colorElegido < 6) ||
                (articuloElegido.equals("Rib Jaspeado") && colorElegido < 3)) {
            crearRollosCuadro.setValue(0);
            crearRollosCuadro.setEnabled(false);
        } else {
            crearRollosCuadro.setEnabled(true);
        }

    }

    public void añadirItem(String[] encabezadoTablas) {

        if (textoValido(crearPesoCuadro) && textoValido(crearPrecioCuadro) && textoElegidoValido(crearArticuloCuadro) &&
                textoElegidoValido(crearColorCuadro) && valorElegidoValido(crearRollosCuadro)) {

            int numeroFilas = crearTabla.getRowCount();
            int numeroColumnas = crearTabla.getColumnCount();
            String[][] nuevoContenidoTabla = new String[numeroFilas+1][numeroColumnas];

            for (int i=0; i<numeroFilas; i++) {
                for (int j=0; j<numeroColumnas; j++) {
                    nuevoContenidoTabla[i][j] = crearTabla.getValueAt(i, j).toString();
                }
            }

            String peso = crearPesoCuadro.getText();
            String precio = crearPrecioCuadro.getText();
            String articulo = crearArticuloCuadro.getSelectedItem().toString();
            String color = crearColorCuadro.getSelectedItem().toString();
            String rollos = crearRollosCuadro.getValue().toString();

            nuevoContenidoTabla[numeroFilas][0] = Integer.toString(numeroFilas+1);
            nuevoContenidoTabla[numeroFilas][1] = peso;
            nuevoContenidoTabla[numeroFilas][2] = crearDescripcion(articulo, color, rollos);
            nuevoContenidoTabla[numeroFilas][3] = precio;
            nuevoContenidoTabla[numeroFilas][4] = calcularImporte(peso, precio);

            actualizarTabla(crearTabla, nuevoContenidoTabla, encabezadoTablas);
            actualizarTotales();
            actualizarSaldo();

            resetearCuadros(crearPesoCuadro, crearPrecioCuadro, crearArticuloCuadro);
            crearAlerta.setText("<html><center> Item añadido!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else {
            crearAlerta.setText("<html><center> Revisa los valores <br> ingresados... </center></html>");
            resetearAlerta(crearAlerta);
        }

    }

    public void quitarItem(String[] encabezadoTablas) {

        if (crearTabla.getSelectedRow() >= 0) {

            int numeroFilas = crearTabla.getRowCount();
            int numeroColumnas = crearTabla.getColumnCount();
            int filaSeleccionada = crearTabla.getSelectedRow();
            String[][] nuevoContenidoTabla = new String[numeroFilas-1][numeroColumnas];

            for (int i=0; i<filaSeleccionada; i++) {
                for (int j=0; j<numeroColumnas; j++) {
                    nuevoContenidoTabla[i][j] = crearTabla.getValueAt(i, j).toString();
                }
            }

            for (int i=filaSeleccionada; i<numeroFilas-1; i++) {
                for (int j=0; j<numeroColumnas; j++) {
                    if (j == 0) {
                        nuevoContenidoTabla[i][j] = Integer.toString(i+1);
                    } else {
                        nuevoContenidoTabla[i][j] = crearTabla.getValueAt(i+1, j).toString();
                    }
                }
            }

            actualizarTabla(crearTabla, nuevoContenidoTabla, encabezadoTablas);
            actualizarTotales();
            actualizarSaldo();

            crearTabla.getSelectionModel().clearSelection();
            crearAlerta.setText("<html><center> Item quitado!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else {
            crearAlerta.setText("<html><center> Selecciona un item <br> para quitarlo... </center></html>");
            resetearAlerta(crearAlerta);
        }

    }

    public void resetearGuia() {

        resetearTabla(crearTabla);
        actualizarTotales();
        crearDescuentoCuadro.setText("");
        crearCanceladoCuadro.setText("");
        crearSaldoClienteCuadro.setText("0.00");

        crearClienteCuadro.setText("");
        resetearCuadros(crearPesoCuadro, crearPrecioCuadro, crearArticuloCuadro);
        crearAlerta.setText("<html><center> Guia reseteada!!! </center></html>");
        resetearAlerta(crearAlerta);

    }

    public void guardarGuia(ArrayList<GuiaVenta> guiasVentas) {

        if (crearTabla.getRowCount() > 0 && clienteValido(crearClienteCuadro) && textoValido(crearDescuentoCuadro) &&
                textoValido(crearCanceladoCuadro)) {

            actualizarSaldo();

            int numeroFilas = crearTabla.getRowCount();
            int numeroColumnas = crearTabla.getColumnCount();
            String cliente = crearClienteCuadro.getText();
            String[] totales = {crearImporteTotalCuadro.getText(), crearDescuentoCuadro.getText(),
                                crearCanceladoCuadro.getText(), crearSaldoClienteCuadro.getText(),
                                crearPesoTotalCuadro.getText(), crearRollosTotalCuadro.getText(),
                                crearBolsasTotalCuadro.getText()};
            String[][] contenido = new String[numeroFilas][numeroColumnas];

            for (int i=0; i<numeroFilas; i++) {
                for (int j=0; j<numeroColumnas; j++) {
                    contenido[i][j] = crearTabla.getValueAt(i, j).toString();
                }
            }

            GuiaVenta nuevaGuia = new GuiaVenta(cliente, totales, contenido);
            guiasVentas.add(nuevaGuia);
            actualizarGuiasVentas(guiasVentas);

            crearAlerta.setText("<html><center> Guia guardada!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else {
            crearAlerta.setText("<html><center> Revisa los valores <br> ingresados... </center></html>");
            resetearAlerta(crearAlerta);
        }

    }

    public void actualizarTotales() {

        int numeroFilas = crearTabla.getRowCount();
        BigDecimal importeTotal = new BigDecimal("0.00");
        BigDecimal pesoTotal = new BigDecimal("0.00");
        int rollosTotal = 0;
        double bolsasPesoTotal = 0;
        int pesoPorBolsa = 20;

        for (int i=0; i<numeroFilas; i++) {

            BigDecimal importe = new BigDecimal(crearTabla.getValueAt(i, 4).toString());
            BigDecimal peso = new BigDecimal(crearTabla.getValueAt(i, 1).toString());
            importeTotal = importeTotal.add(importe);
            pesoTotal = pesoTotal.add(peso);

            String descripcion = crearTabla.getValueAt(i, 2).toString();

            if (descripcion.contains("rollo/s")) {
                String rollos = descripcion.substring(descripcion.indexOf("(")+1, descripcion.indexOf(" rollo/s)"));
                rollosTotal += Integer.parseInt(rollos);
            } else {
                String bolsasPeso = crearTabla.getValueAt(i, 1).toString();
                bolsasPesoTotal += Double.parseDouble(bolsasPeso);
            }

        }

        int bolsasTotal = (int) Math.ceil(bolsasPesoTotal/pesoPorBolsa);

        crearImporteTotalCuadro.setText(importeTotal.toString());
        crearPesoTotalCuadro.setText(pesoTotal.toString());
        crearRollosTotalCuadro.setText(Integer.toString(rollosTotal));
        crearBolsasTotalCuadro.setText(Integer.toString(bolsasTotal));

    }

    public void actualizarSaldo() {

        if (textoValido(crearDescuentoCuadro) && textoValido(crearCanceladoCuadro)) {

            BigDecimal importeTotal = new BigDecimal(crearImporteTotalCuadro.getText());
            BigDecimal montoDescuento = new BigDecimal(crearDescuentoCuadro.getText());
            BigDecimal montoCancelado = new BigDecimal(crearCanceladoCuadro.getText());

            BigDecimal saldoCliente = importeTotal.subtract(montoDescuento);
            saldoCliente = saldoCliente.subtract(montoCancelado);
            crearSaldoClienteCuadro.setText(saldoCliente.toString());

            crearAlerta.setText("<html><center> Saldo calculado!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else if (textoValido(crearDescuentoCuadro)) {

            BigDecimal importeTotal = new BigDecimal(crearImporteTotalCuadro.getText());
            BigDecimal montoDescuento = new BigDecimal(crearDescuentoCuadro.getText());

            BigDecimal saldoCliente = importeTotal.subtract(montoDescuento);
            crearSaldoClienteCuadro.setText(saldoCliente.toString());

            crearAlerta.setText("<html><center> Saldo calculado!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else if (textoValido(crearCanceladoCuadro)) {

            BigDecimal importeTotal = new BigDecimal(crearImporteTotalCuadro.getText());
            BigDecimal montoCancelado = new BigDecimal(crearCanceladoCuadro.getText());

            BigDecimal saldoCliente = importeTotal.subtract(montoCancelado);
            crearSaldoClienteCuadro.setText(saldoCliente.toString());

            crearAlerta.setText("<html><center> Saldo calculado!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else {
            crearAlerta.setText("<html><center> Revisa el monto <br> ingresado... </center></html>");
            resetearAlerta(crearAlerta);
        }

    }

}
