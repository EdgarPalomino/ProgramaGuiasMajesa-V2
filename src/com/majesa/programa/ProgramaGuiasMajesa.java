package com.majesa.programa;

import com.majesa.contenedores.Ventana;

import javax.swing.*;

public class ProgramaGuiasMajesa {

    public static void main(String[] args) {

        // Configurando el look and feel del programa

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        // Iniciando el programa

        new Ventana();

    }

}
