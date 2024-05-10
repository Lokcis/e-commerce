package main;

import Files.LimpiarTexto;
import dataStructures.BinarySearchST;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lokci
 */
public class Interfaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opc;
        String key;
        String rutaEntrada = "C:\\Users\\lokci\\OneDrive\\Documentos\\Piloto\\Cuarto Semestre\\Estructuras\\Biblia\\Bible\\src\\Files\\Biblia.txt";
        String rutaSalida = "C:\\Users\\lokci\\OneDrive\\Documentos\\Piloto\\Cuarto Semestre\\Estructuras\\Biblia\\Bible\\src\\Files\\biblia_limpiada.txt;";

        try {
            LimpiarTexto.limpiarArchivo(rutaEntrada, rutaSalida);
        } catch (IOException e) {
            System.err.println("Error al limpiar el archivo: " + e.getMessage());
            return;
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

        System.out.println("¡Bienvenido!");
        do {

            System.out.println("""
                               Selecciones la acción a realizar: 
                               1. Verificar el tamaño de la tabla.
                               2. Traer el valor de la clave.
                               3. Comprobar si está vacío.
                               4. Agregar una repetición.
                               5. Eliminar una repetición.
                               6. Elimina una clave.
                               6. Verifica si existe la clave.
                               7. Llamar el valor menor.
                               8. Llamar el valor mayor.
                               9. Llamar el valor en la posición indicada.
                               10. Valor más alto antes del incidado.
                               11. Valor más bajo después del incidado.
                               12. Eliminar el valor mínimo.
                               13. Eliminar el valor máximo.
                               14. Retorna los valores entre dos llaves.
                               
                               """);
            opc = in.nextInt();

            switch (opc) {
                case 1 -> {
                    System.out.println("\nEl tamaño de la tabla es de: " + tabla.size() + "\n");
                }
                case 2 -> {
                    System.out.println("\nIngrese la clave a buscar\n");
                    key = in.next();

                    System.out.println("\nLa palabra " + key + " se repite " + tabla.get(key) + " veces.\n");
                }
                case 3 -> {
                    if (tabla.isEmpty()) {
                        System.out.println("\nLa tabla de símbolos está vacía.\n");
                    } else {
                        System.out.println("\nLa tabla de símbolos no está vacía.\n");
                    }
                }
                case 4 -> {
                    System.out.println("\nDigite la clave que desea agregar.\n");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.\n");
                    if (tabla.get(key) != null) {
                        tabla.put(key, tabla.get(key) + 1);
                    } else {
                        tabla.put(key, 1);
                    }
                    System.out.println("\nSe ha agregado la clave " + key + " sumando " + (tabla.get(key)) + " repeticiones.\n");
                }
                case 5 -> {
                    System.out.println("\nDigite la clave que desea eliminar.\n");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.\n");
                    if (tabla.get(key) != null) {
                        tabla.put(key, tabla.get(key) - 1);
                    }
                }
                case 6 -> {
                    System.out.println("\nDigite la clave que desea eliminar.\n");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.\n");
                    if (tabla.get(key) != null) {
                        tabla.delete(key);
                    }
                }
                case 7 -> {

                }
                case 8 -> {

                }
                case 9 -> {

                }
                case 10 -> {

                }
                case 11 -> {

                }
                case 12 -> {

                }
                case 13 -> {

                }
                case 14 -> {

                }
                case 15 -> {

                }

                default -> {

                }
            }

        } while (true);

    }
}
