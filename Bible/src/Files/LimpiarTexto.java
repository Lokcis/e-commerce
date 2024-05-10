package Files;

/**
 *
 * @author espin
 */
import java.io.*;
import java.util.*;

/**
 * Clase que proporciona métodos para limpiar un archivo de texto, contar
 * palabras y obtener un arreglo de palabras a partir de un archivo. Está
 * diseñada para eliminar caracteres no deseados, contar palabras y obtener un
 * arreglo de palabras para su posterior procesamiento.
 *
 * @author espin
 */
public class LimpiarTexto {

    /**
     * Limpia un archivo de texto eliminando caracteres no deseados como
     * símbolos de puntuación y convirtiendo el texto a minúsculas.
     *
     * @param rutaEntrada La ruta del archivo de entrada que se desea limpiar.
     * @param rutaSalida La ruta del archivo de salida donde se guardará el
     * texto limpio.
     * @throws IOException Si ocurre un error de entrada/salida al procesar los
     * archivos.
     */
    public static void limpiarArchivo(String rutaEntrada, String rutaSalida) throws IOException {
        BufferedReader lector = null;
        BufferedWriter escritor = null;

        try {
            lector = new BufferedReader(new FileReader(rutaEntrada));
            escritor = new BufferedWriter(new FileWriter(rutaSalida));

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
        } finally {
            // Asegurarse de cerrar los flujos de entrada/salida
            if (lector != null) {
                lector.close();
            }
            if (escritor != null) {
                escritor.close();
            }
        }
    }

    /**
     * Cuenta el número total de palabras en un archivo de texto.
     *   
     * @param rutaSalida La ruta del archivo del cual se contarán las palabras.
     * @return El número total de palabras en el archivo.
     */
    public static int contarPalabras(String rutaSalida) {
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaSalida));
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

    /**
     * Obtiene un arreglo de palabras a partir de un archivo de texto.
     *
     * @param rutaArchivo La ruta del archivo del cual se obtendrán las
     * palabras.
     * @return Un arreglo de tipo String que contiene todas las palabras del
     * archivo.
     */
    public static String[] obtenerPalabras(String rutaArchivo) {
        List<String> listaPalabras = new ArrayList<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea en palabras usando espacios en blanco como delimitador
                String[] palabras = linea.split("\\s+");
                listaPalabras.addAll(Arrays.asList(palabras));
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        return listaPalabras.toArray(new String[0]);
    }
}
