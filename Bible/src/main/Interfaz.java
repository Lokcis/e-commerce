package main;

import Files.LimpiarTexto;
import dataStructures.BinarySearchST;
import java.io.IOException;

/**
 *
 * @author lokci
 */
public class Interfaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rutaEntrada = "C:\\Users\\lokci\\OneDrive\\Documentos\\Piloto\\Cuarto Semestre\\Estructuras\\Biblia\\Bible\\src\\Files\\Biblia.txt";
        String rutaSalida = "C:\\Users\\lokci\\OneDrive\\Documentos\\Piloto\\Cuarto Semestre\\Estructuras\\Biblia\\Bible\\src\\Files\\biblia_limpiada.txt;";
        try {
            LimpiarTexto.limpiarArchivo(rutaEntrada, rutaSalida);
        } catch (IOException e) {
            System.err.println("Error al limpiar el archivo: " + e.getMessage());
            return;  // Detener la ejecución del programa
        }
        int cantidadPalabras = LimpiarTexto.contarPalabras(rutaSalida);
        System.out.println("La Biblia limpiada contiene " + cantidadPalabras + " palabras.");

        String[] palabras = LimpiarTexto.obtenerPalabras(rutaSalida);

        // Crear una nueva instancia de BinarySearchST
        BinarySearchST<String, Integer> tabla = new BinarySearchST<>(palabras.length);

        // Llenar la tabla con las palabras y sus conteos
        for (String palabra : palabras) {
            if (!tabla.contains(palabra)) {
                tabla.put(palabra, 1);
            } else {
                tabla.put(palabra, tabla.get(palabra) + 1);
            }
        }

        // Imprimir el total de palabras no repetidas
        System.out.println("Total de palabras no repetidas: " + tabla.size());

        // Imprimir cuántas veces se repite cada palabra
        for (String palabra : tabla.keys()) {
            System.out.println("Palabra: " + palabra + ", Repeticiones: " + tabla.get(palabra));
        }

    }
}
