package main;

import Files.LimpiarTexto;
import dataStructures.*;
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
        int opc, pos;
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

        // Crear una nueva instancia de las estructuras de datos
        BinarySearchST<String, Integer> tabla2 = new BinarySearchST<>(palabras.length);
        BST<String, Integer> tabla = new BST<>();
        

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

        System.out.println("\n¡Bienvenido!");
        do {

            System.out.println("""
                               \nSelecciones la acción a realizar: 
                               1. Verificar el tamaño de la tabla.
                               2. Traer el valor de la clave.
                               3. Comprobar si está vacío.
                               4. Agregar una repetición.
                               5. Eliminar una repetición.
                               6. Elimina una clave.
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
                    System.out.println("\nEl tamaño de la tabla es de: " + tabla.size());
                }
                case 2 -> {
                    System.out.println("\nIngrese la clave a buscar\n");
                    key = in.next();

                    System.out.println("\nLa palabra " + key + " se repite " + tabla.get(key) + " veces.");
                }
                case 3 -> {
                    if (tabla.isEmpty()) {
                        System.out.println("\nLa tabla de símbolos está vacía.");
                    } else {
                        System.out.println("\nLa tabla de símbolos no está vacía.");
                    }
                }
                case 4 -> {
                    System.out.println("\nDigite la clave que desea agregar.");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.");
                    if (tabla.get(key) != null) {
                        tabla.put(key, tabla.get(key) + 1);
                    } else {
                        tabla.put(key, 1);
                    }
                    System.out.println("\nSe ha agregado la clave " + key + " sumando " + (tabla.get(key)) + " repeticiones.");
                }
                case 5 -> {
                    System.out.println("\nDigite la clave que desea eliminar.");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.");
                    if (tabla.get(key) != null) {
                        tabla.put(key, tabla.get(key) - 1);
                    }
                }
                case 6 -> {
                    System.out.println("\nDigite la clave que desea eliminar.");
                    key = in.next();
                    System.out.println("\nLa clave tenía " + tabla.get(key) + " repeticiones.");
                    if (tabla.get(key) != null) {
                        tabla.delete(key);
                        System.out.println("Se ha eliminado la key " + key);
                    }
                }
                case 7 -> {
                    System.out.println("\nEl valor menor de la tabla es " + tabla.min());
                }
                case 8 -> {
                    System.out.println("\nEl valor mayor de la tabla es " + tabla.max());
                }
                case 9 -> {
                    System.out.println("\nIndique la posición que desea saber el valor: ");
                    pos = in.nextInt();
                    System.out.println("\nEl valor de la posición " + pos + " es " + tabla.select(pos));
                }
                case 10 -> {
                    System.out.println("\nIndique la clave que desea saber el piso: ");
                    key = in.next();
                    System.out.println("\nEl piso de la llave " + key + " es " + tabla.floor(key));
                }
                case 11 -> {
                    System.out.println("\nIndique la clave que desea saber el piso: ");
                    key = in.next();
                    System.out.println("\nEl piso de la llave " + key + " es " + tabla.ceiling(key));
                }
                case 12 -> {
                    System.out.println("Se ha borrado la primera posición " + tabla.min());
                    tabla.deleteMin();
                }
                case 13 -> {
                    System.out.println("Se ha borrado la última posición " + tabla.max());
                    tabla.deleteMax();
                }
                case 14 -> {
                    System.out.println("\nIngrese la primera posición del rango");
                    int lo = in.nextInt();
                    System.out.println("\nIngrese la segunda posición del rango");
                    int hi = in.nextInt();
                    System.out.println("\nLas claves en el rango [" + lo + ", " + hi + "] son:");
                    int rankLo = tabla.rank(tabla.select(lo));
                    int rankHi = tabla.rank(tabla.select(hi));
                    for (int i = rankLo; i <= rankHi; i++) {
                        String palabra = tabla.select(i);
                        System.out.println("Palabra: " + palabra + ", Repeticiones: " + tabla.get(palabra));
                    }
                }

                default -> {
                    System.out.println("Ingresa un número válido por favor");
                }
            }

        } while (true);

    }
}
