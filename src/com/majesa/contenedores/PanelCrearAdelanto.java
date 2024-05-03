package com.majesa.contenedores;

import com.majesa.componentes.*;
import com.majesa.programa.GuiaAdelanto;

import javax.swing.*;
import java.util.ArrayList;

import static com.majesa.programa.MetodosAyuda.*;
import static com.majesa.programa.MetodosTxt.actualizarGuiasAdelantos;

public class PanelCrearAdelanto extends JPanel {

    // Definiendo los componentes del panel crear adelanto

    private TextoCuadro crearClienteTexto;
    private TextoCuadro crearAdelantoTexto;
    private TextoCuadro crearObservacionTexto;
    private CuadroCliente crearClienteCuadro;
    private CuadroTextoGrande crearAdelantoCuadro;
    private CuadroObservacion crearObservacionCuadro;
    private Alerta crearAlerta;
    private BotonFuncion crearAñadir;
    private BotonFuncion crearResetear;
    private BotonFuncion crearGuardar;


    public PanelCrearAdelanto(ArrayList<GuiaAdelanto> guiasAdelantos) {

        // Creando el panel crear adelanto y organizando sus componentes

        this.setLayout(null);

        crearClienteTexto = new TextoCuadro("Cliente:", 415, 145);
        this.add(crearClienteTexto);

        crearAdelantoTexto = new TextoCuadro("Adelanto:", 155, 185);
        this.add(crearAdelantoTexto);

        crearObservacionTexto = new TextoCuadro("Observacion:", 155, 225);
        this.add(crearObservacionTexto);

        crearAlerta = new Alerta("", 420, 225);
        this.add(crearAlerta);

        crearClienteCuadro = new CuadroCliente("", 475, 145);
        this.add(crearClienteCuadro);

        crearAdelantoCuadro = new CuadroTextoGrande("", 255, 185, true);
        this.add(crearAdelantoCuadro);

        crearObservacionCuadro = new CuadroObservacion("", 155, 255, true);
        this.add(crearObservacionCuadro);

        crearResetear = new BotonFuncion("Resetear", 625, 225);
        crearResetear.addActionListener(e -> resetearGuia());
        this.add(crearResetear);

        crearGuardar = new BotonFuncion("Guardar", 625, 280);
        crearGuardar.addActionListener(e -> guardarGuia(guiasAdelantos));
        this.add(crearGuardar);

    }

    //--------------------------------------------------\\

    // Metodos para el panel crear adelanto

    public void resetearGuia() {

        crearAdelantoCuadro.setText("");
        crearObservacionCuadro.setText("");

        crearClienteCuadro.setText("");
        crearAlerta.setText("<html><center> Guia reseteada!!! </center></html>");
        resetearAlerta(crearAlerta);

    }

    public void guardarGuia(ArrayList<GuiaAdelanto> guiasAdelantos) {

        if (clienteValido(crearClienteCuadro) && textoValido(crearAdelantoCuadro)) {

            String cliente = crearClienteCuadro.getText();
            String observacion = crearObservacionCuadro.getText();
            String montoAdelanto = crearAdelantoCuadro.getText();

            GuiaAdelanto nuevaGuia = new GuiaAdelanto(cliente, observacion, montoAdelanto);
            guiasAdelantos.add(nuevaGuia);
            actualizarGuiasAdelantos(guiasAdelantos);

            crearAlerta.setText("<html><center> Guia guardada!!! </center></html>");
            resetearAlerta(crearAlerta);

        } else {
            crearAlerta.setText("<html><center> Revisa los valores <br> ingresados... </center></html>");
            resetearAlerta(crearAlerta);
        }

    }

}
