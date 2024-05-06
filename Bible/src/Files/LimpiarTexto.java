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
                linea = linea.toLowerCase();
                // Escribir la línea limpia en el archivo de salida
                escritor.write(linea);
            }

            System.out.println("Archivo limpiado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }

    public static int contarPalabras(String rutaArchivo) {
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en palabras usando espacios en blanco como delimitador
                String[] palabras = linea.split("\\s+");
                // Añadir la cantidad de palabras en esta línea al contador total
                contador += palabras.length;
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        return contador;
    }

}
