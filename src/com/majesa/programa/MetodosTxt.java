package com.majesa.programa;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.majesa.programa.MetodosAyuda.obtenerFecha;

public class MetodosTxt {

    public static void obtenerArticulosyColores(ArrayList<String> articulos, HashMap<String, String[]> colores) {

        articulos.add("Seleccionar");
        colores.put("Seleccionar", new String[]{"Seleccionar"});

        try {

            FileReader archivo = new FileReader("./informacion/Articulos y Colores.txt");
            BufferedReader lector = new BufferedReader(archivo);

            ArrayList<String> articulosHashMap = new ArrayList<>();
            String[] coloresHashMap = new String[0];
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.equals("")) {

                    int numeroArticulos = articulosHashMap.size();

                    for (int i=0; i<numeroArticulos; i++) {
                        colores.put(articulosHashMap.get(i), coloresHashMap);
                    }

                    articulosHashMap.clear();
                    coloresHashMap = new String[0];

                } else if (linea.endsWith(":")) {
                    String articulo = linea.substring(0, linea.length()-1);
                    articulosHashMap.add(articulo);
                    articulos.add(articulo);
                } else if (linea.contains(", ")) {
                    coloresHashMap = linea.split(", ");
                }
            }

            lector.close();

        } catch (Exception e) { }

    }

    public static void obtenerGuiasVentas(ArrayList<GuiaVenta> guiasVentas) {

        String ruta = "./informacion/Guias Ventas.txt";

        try {

            FileReader archivoLeer = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(archivoLeer);
            String fecha = obtenerFecha();

            lector.readLine();
            lector.readLine();
            String lineaFecha = lector.readLine();

            if (lineaFecha == null) {
                lineaFecha = "";
            }

            if (lineaFecha.equals(fecha)) {

                lector.readLine();

                String cliente = "";
                String[] totales = new String[0];
                String[][] contenido = new String[0][5];
                String linea;

                while ((linea = lector.readLine()) != null) {
                    if (linea.equals("")) {

                        GuiaVenta guiaTemporal = new GuiaVenta(cliente, totales, contenido);
                        guiasVentas.add(guiaTemporal);

                        cliente = "";
                        totales = new String[0];
                        contenido = new String[0][5];

                    } else if (linea.endsWith(":")) {
                        cliente = linea.substring(0, linea.length()-1);
                    } else if (linea.contains(" - ")) {
                        totales = linea.split(" - ");
                    } else if (linea.contains(", ")) {

                        String[] items = linea.split("; ");

                        int numeroItems = items.length;
                        contenido = new String[numeroItems][5];

                        for (int i=0; i<numeroItems; i++) {
                            contenido[i] = items[i].split(", ");
                        }

                    }
                }

                lector.close();

            } else {

                lector.close();

                reemplazarArchivo(ruta);

                FileWriter archivoEscribir = new FileWriter(ruta, true);
                BufferedWriter escritor = new BufferedWriter(archivoEscribir);

                escritor.write("-------------");
                escritor.newLine();
                escritor.newLine();
                escritor.write(fecha);
                escritor.newLine();
                escritor.newLine();
                escritor.write("-------------");

                escritor.close();

            }

        } catch (Exception e) { }

    }

    public static void obtenerGuiasDevoluciones(ArrayList<GuiaDevolucion> guiasDevoluciones) {

        String ruta = "./informacion/Guias Devoluciones.txt";

        try {

            FileReader archivoLeer = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(archivoLeer);
            String fecha = obtenerFecha();

            lector.readLine();
            lector.readLine();
            String lineaFecha = lector.readLine();

            if (lineaFecha == null) {
                lineaFecha = "";
            }

            if (lineaFecha.equals(fecha)) {

                lector.readLine();

                String cliente = "";
                String observacion = "";
                String[] totales = new String[0];
                String[][] contenido = new String[0][5];
                String linea;

                while ((linea = lector.readLine()) != null) {
                    if (linea.equals("")) {

                        GuiaDevolucion guiaTemporal = new GuiaDevolucion(cliente, observacion, totales, contenido);
                        guiasDevoluciones.add(guiaTemporal);

                        cliente = "";
                        observacion = "";
                        totales = new String[0];
                        contenido = new String[0][5];

                    } else if (linea.endsWith(":")) {
                        cliente = linea.substring(0, linea.length()-1);
                    } else if (linea.endsWith(".")) {
                        observacion = linea.substring(0, linea.length()-1);
                    } else if (linea.contains(" - ")) {
                        totales = linea.split(" - ");
                    } else if (linea.contains(", ")) {

                        String[] items = linea.split("; ");

                        int numeroItems = items.length;
                        contenido = new String[numeroItems][5];

                        for (int i=0; i<numeroItems; i++) {
                            contenido[i] = items[i].split(", ");
                        }

                    }
                }

                lector.close();

            } else {

                lector.close();

                reemplazarArchivo(ruta);

                FileWriter archivoEscribir = new FileWriter(ruta, true);
                BufferedWriter escritor = new BufferedWriter(archivoEscribir);

                escritor.write("-------------");
                escritor.newLine();
                escritor.newLine();
                escritor.write(fecha);
                escritor.newLine();
                escritor.newLine();
                escritor.write("-------------");

                escritor.close();

            }

        } catch (Exception e) { }

    }

    public static void obtenerGuiasAdelantos(ArrayList<GuiaAdelanto> guiasDevoluciones) {

        String ruta = "./informacion/Guias Adelantos.txt";

        try {

            FileReader archivoLeer = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(archivoLeer);
            String fecha = obtenerFecha();

            lector.readLine();
            lector.readLine();
            String lineaFecha = lector.readLine();

            if (lineaFecha == null) {
                lineaFecha = "";
            }

            if (lineaFecha.equals(fecha)) {

                lector.readLine();

                String cliente = "";
                String observacion = "";
                String montoAdelanto = "";
                String linea;

                while ((linea = lector.readLine()) != null) {
                    if (linea.equals("")) {

                        GuiaAdelanto guiaTemporal = new GuiaAdelanto(cliente, observacion, montoAdelanto);
                        guiasDevoluciones.add(guiaTemporal);

                        cliente = "";
                        observacion = "";
                        montoAdelanto = "";

                    } else if (linea.endsWith(":")) {
                        cliente = linea.substring(0, linea.length()-1);
                    } else if (linea.endsWith(".")) {
                        observacion = linea.substring(0, linea.length()-1);
                    } else if (linea.startsWith("• ")) {
                        montoAdelanto = linea.substring(2);
                    }
                }

                lector.close();

            } else {

                lector.close();

                reemplazarArchivo(ruta);

                FileWriter archivoEscribir = new FileWriter(ruta, true);
                BufferedWriter escritor = new BufferedWriter(archivoEscribir);

                escritor.write("-------------");
                escritor.newLine();
                escritor.newLine();
                escritor.write(fecha);
                escritor.newLine();
                escritor.newLine();
                escritor.write("-------------");

                escritor.close();

            }

        } catch (Exception e) { }

    }

    public static void actualizarGuiasVentas(ArrayList<GuiaVenta> guiasVentas) {

        String ruta = "./informacion/Guias Ventas.txt";

        try {

            reemplazarArchivo(ruta);

            FileWriter archivoEscribir = new FileWriter(ruta, true);
            BufferedWriter escritor = new BufferedWriter(archivoEscribir);
            String fecha = obtenerFecha();

            escritor.write("-------------");
            escritor.newLine();
            escritor.newLine();
            escritor.write(fecha);
            escritor.newLine();
            escritor.newLine();

            int numeroGuias = guiasVentas.size();

            for (int i=0; i<numeroGuias; i++) {

                GuiaVenta guia = guiasVentas.get(i);

                String cliente = guia.getCliente();
                String[] totales = guia.getTotales();
                String[][] contenido = guia.getContenido();

                escritor.write(cliente + ":");

                escritor.newLine();
                escritor.write(String.join(" - ", totales));

                int numeroItems = contenido.length;
                String[] items = new String[numeroItems];

                for (int j=0; j<numeroItems; j++) {
                    items[j] = String.join(", ", contenido[j]);
                }

                escritor.newLine();
                escritor.write(String.join("; ", items));

                escritor.newLine();
                escritor.newLine();

            }

            escritor.write("-------------");
            escritor.close();

        } catch (Exception e) { }

    }

    public static void actualizarGuiasDevoluciones(ArrayList<GuiaDevolucion> guiasDevoluciones) {

        String ruta = "./informacion/Guias Devoluciones.txt";

        try {

            reemplazarArchivo(ruta);

            FileWriter archivoEscribir = new FileWriter(ruta, true);
            BufferedWriter escritor = new BufferedWriter(archivoEscribir);
            String fecha = obtenerFecha();

            escritor.write("-------------");
            escritor.newLine();
            escritor.newLine();
            escritor.write(fecha);
            escritor.newLine();
            escritor.newLine();

            int numeroGuias = guiasDevoluciones.size();

            for (int i=0; i<numeroGuias; i++) {

                GuiaDevolucion guia = guiasDevoluciones.get(i);

                String cliente = guia.getCliente();
                String observacion = guia.getObservacion();
                String[] totales = guia.getTotales();
                String[][] contenido = guia.getContenido();

                escritor.write(cliente + ":");

                escritor.newLine();
                escritor.write(observacion + ".");

                escritor.newLine();
                escritor.write(String.join(" - ", totales));

                int numeroItems = contenido.length;
                String[] items = new String[numeroItems];

                for (int j=0; j<numeroItems; j++) {
                    items[j] = String.join(", ", contenido[j]);
                }

                escritor.newLine();
                escritor.write(String.join("; ", items));

                escritor.newLine();
                escritor.newLine();

            }

            escritor.write("-------------");
            escritor.close();

        } catch (Exception e) { }

    }

    public static void actualizarGuiasAdelantos(ArrayList<GuiaAdelanto> guiasAdelantos) {

        String ruta = "./informacion/Guias Adelantos.txt";

        try {

            reemplazarArchivo(ruta);

            FileWriter archivoEscribir = new FileWriter(ruta, true);
            BufferedWriter escritor = new BufferedWriter(archivoEscribir);
            String fecha = obtenerFecha();

            escritor.write("-------------");
            escritor.newLine();
            escritor.newLine();
            escritor.write(fecha);
            escritor.newLine();
            escritor.newLine();

            int numeroGuias = guiasAdelantos.size();

            for (int i=0; i<numeroGuias; i++) {

                GuiaAdelanto guia = guiasAdelantos.get(i);

                String cliente = guia.getCliente();
                String observacion = guia.getObservacion();
                String montoAdelanto = guia.getMontoAdelanto();

                escritor.write(cliente + ":");

                escritor.newLine();
                escritor.write(observacion + ".");

                escritor.newLine();
                escritor.write("• " + montoAdelanto);

                escritor.newLine();
                escritor.newLine();

            }

            escritor.write("-------------");
            escritor.close();

        } catch (Exception e) { }

    }

    public static void reemplazarArchivo(String ruta) {

        try {

            File archivoViejo = new File(ruta);
            archivoViejo.delete();

            File archivoNuevo = new File(ruta);
            archivoNuevo.createNewFile();

        } catch (Exception e) { }

    }

}
