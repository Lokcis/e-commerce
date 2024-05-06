package Files;

/**
 *
 * @author espin
 */

import java.io.*;

public class LimpiarTexto {
    public static void limpiarArchivo(String rutaEntrada, String rutaSalida) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaEntrada));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaSalida));

            String linea;
            while ((linea = lector.readLine()) != null) {
                // Eliminar caracteres que no sean letras, números, espacios en blanco, apóstrofes o guiones
                linea = linea.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúÑñ\\s']", "");

                // Reemplazar múltiples espacios en blanco por un solo espacio
                linea = linea.replaceAll("\\s+", " ");

                // Escribir la línea limpia en el archivo de salida
                escritor.write(linea);
            }

            System.out.println("Archivo limpiado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
