package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaAdelanto;
import com.majesa.programa.GuiaDevolucion;
import com.majesa.programa.GuiaVenta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.cambiarPaneles;
import static com.majesa.programa.MetodosPdf.descargarResumenDiaPdf;
import static com.majesa.programa.MetodosTxt.*;

public class Ventana extends JFrame {

    // Definiendo los arraylists que van a contener todas las guias de ventas, devoluciones y adelantos guardadas

    private ArrayList<GuiaVenta> guiasVentas;
    private ArrayList<GuiaDevolucion> guiasDevoluciones;
    private ArrayList<GuiaAdelanto> guiasAdelantos;

    // Definiendo el panel contenedor

    private JPanel panelContenedor;

    // Definiendo los componentes del panel home

    private JPanel homePanel;
    private TextoBienvenida homeBienvenida;
    private TextoMensaje homeMensaje;
    private LogoMajesa homeLogo;
    private BotonNavegacion homeSalir;
    private BotonOpcion homeCrearGuia;
    private BotonOpcion homeVerGuias;

    // Definiendo el arreglo que va a contener el encabezado de las tablas

    private String[] encabezadoTablas;

    // Definiendo el panel crear

    private JPanel crearPanel;
    private BotonNavegacion crearAtras;
    private PanelTabeado crearPanelTabeado;
    private PanelCrearVenta crearPanelVenta;
    private PanelCrearDevolucion crearPanelDevolucion;
    private PanelCrearAdelanto crearPanelAdelanto;

    // Definiendo los componentes del panel ver

    private JPanel verPanel;
    private PanelTabeado verPanelTabeado;
    private PanelVerVenta verPanelVenta;
    private PanelVerDevolucion verPanelDevolucion;
    private PanelVerAdelanto verPanelAdelanto;
    private BotonNavegacion verAtras;


    public Ventana() {

        // Creando los arraylists que va a contener todas las guias de ventas, devoluciones y adelantos guardadas

        guiasVentas = new ArrayList<>();
        guiasDevoluciones = new ArrayList<>();
        guiasAdelantos = new ArrayList<>();

        // Obteniendo todas las guias de ventas, devoluciones y adelantos guardadas

        obtenerGuiasVentas(guiasVentas);
        obtenerGuiasDevoluciones(guiasDevoluciones);
        obtenerGuiasAdelantos(guiasAdelantos);

        // Creando el panel contenedor

        panelContenedor = new JPanel();
        panelContenedor.setLayout(new CardLayout());

        //--------------------------------------------------\\

        // Creando el panel home y organizando sus componentes

        homePanel = new JPanel();
        homePanel.setLayout(null);

        homeBienvenida = new TextoBienvenida("Hola Lizeth!", 395, 120);
        homePanel.add(homeBienvenida);

        homeMensaje = new TextoMensaje("Selecciona la opcion que deseas para empezar", 225, 245);
        homePanel.add(homeMensaje);

        homeLogo = new LogoMajesa("./imagenes/Logo Majesa.png", 795, 0);
        homePanel.add(homeLogo);

        homeSalir = new BotonNavegacion("Salir", 20, 20);
        homeSalir.addActionListener(e -> this.dispose());
        homePanel.add(homeSalir);

        homeCrearGuia = new BotonOpcion("Crear Guia", 285, 385);
        homeCrearGuia.addActionListener(e -> cambiarPaneles(homePanel, crearPanel));
        homePanel.add(homeCrearGuia);

        homeVerGuias = new BotonOpcion("Ver Guias", 535, 385);
        homeVerGuias.addActionListener(e -> {
            cambiarPaneles(homePanel, verPanel);
            prepararVerPanel();
        });
        homePanel.add(homeVerGuias);

        //--------------------------------------------------\\

        // Creando el arreglo que va a contener el encabezado de las tablas

        encabezadoTablas = new String[]{"Item", "Peso", "Descripcion", "P/Kg", "Importe"};

        // Creando el panel crear y organizando sus componentes

        crearPanel = new JPanel();
        crearPanel.setLayout(null);

        crearPanelVenta = new PanelCrearVenta(guiasVentas, encabezadoTablas);
        crearPanelDevolucion = new PanelCrearDevolucion(guiasDevoluciones, encabezadoTablas);
        crearPanelAdelanto = new PanelCrearAdelanto(guiasAdelantos);

        crearPanelTabeado = new PanelTabeado(30, 100);
        crearPanelTabeado.add("Ventas", crearPanelVenta);
        crearPanelTabeado.add("Devoluciones", crearPanelDevolucion);
        crearPanelTabeado.add("Adelantos", crearPanelAdelanto);
        crearPanel.add(crearPanelTabeado);

        crearAtras = new BotonNavegacion("Atras", 20, 20);
        crearAtras.addActionListener(e -> cambiarPaneles(crearPanel, homePanel));
        crearPanel.add(crearAtras);

        //--------------------------------------------------\\

        // Creando el panel crear y organizando sus componentes

        verPanel = new JPanel();
        verPanel.setLayout(null);

        verPanelVenta = new PanelVerVenta(guiasVentas, encabezadoTablas, this);
        verPanelDevolucion = new PanelVerDevolucion(guiasDevoluciones, encabezadoTablas, this);
        verPanelAdelanto = new PanelVerAdelanto(guiasAdelantos, this);

        verPanelTabeado = new PanelTabeado(30, 100);
        verPanelTabeado.add("Ventas", verPanelVenta);
        verPanelTabeado.add("Devoluciones", verPanelDevolucion);
        verPanelTabeado.add("Adelantos", verPanelAdelanto);
        verPanel.add(verPanelTabeado);

        verAtras = new BotonNavegacion("Atras", 20, 20);
        verAtras.addActionListener(e -> cambiarPaneles(verPanel, homePanel));
        verPanel.add(verAtras);

        //--------------------------------------------------\\

        // Organizando todos los paneles y configurando la ventana

        this.add(panelContenedor);
        panelContenedor.add(homePanel);
        panelContenedor.add(crearPanel);
        panelContenedor.add(verPanel);
        crearPanel.setVisible(false);
        verPanel.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(970, 700);
        this.setResizable(false);
        this.setTitle("Programa Guias Majesa");
        this.setVisible(true);

    }

    //--------------------------------------------------\\

    // Metodos para la ventana

    public void prepararVerPanel() {

        verPanelVenta.editarGuiasCuadro(guiasVentas);
        verPanelVenta.actualizarTotalesDia(guiasVentas);

        verPanelDevolucion.editarGuiasCuadro(guiasDevoluciones);
        verPanelDevolucion.actualizarTotalesDia(guiasDevoluciones);

        verPanelAdelanto.editarGuiasCuadro(guiasAdelantos);
        verPanelAdelanto.actualizarAdelantosTotales(guiasAdelantos);

    }

    public void descargarResumenDia() {

        String[] totalesVentas = verPanelVenta.obtenerTotalesDia();
        String[] totalesDevoluciones = verPanelDevolucion.obtenerTotalesDia();
        String adelantosTotales = verPanelAdelanto.obtenerAdelantosTotales();

        descargarResumenDiaPdf(guiasVentas, totalesVentas, guiasDevoluciones, totalesDevoluciones, guiasAdelantos, adelantosTotales);

    }

}
