package com.majesa.componentes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CuadroObservacion extends JTextArea {

    // Constructor para cada cuadro de observacion

    public CuadroObservacion(String texto, int x, int y, boolean habilitar) {

        this.setText(texto);
        this.setBounds(x, y, 170, 75);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Border bordeCuadroObservacion = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        Border margenCuadroObservacion = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        this.setBorder(BorderFactory.createCompoundBorder(bordeCuadroObservacion, margenCuadroObservacion));

        if (habilitar == false) {

            this.setEditable(false);
            this.setBackground(Color.WHITE);
            this.setOpaque(true);

        }

    }

}
