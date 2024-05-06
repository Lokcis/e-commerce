
package main;

import Files.LimpiarTexto;
/**
 *
 * @author lokci
 */
public class Interfaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rutaEntrada = "C:\\Users\\espin\\Documents\\NetBeansProjects\\Biblia\\Bible\\src\\Files\\Biblia.txt";
        String rutaSalida = "C:\\Users\\espin\\Documents\\NetBeansProjects\\Biblia\\Bible\\src\\Files\\biblia_limpiada.txt;";

        LimpiarTexto.limpiarArchivo(rutaEntrada, rutaSalida);
        int cantidadPalabras = LimpiarTexto.contarPalabras(rutaSalida);
        System.out.println("La Biblia limpiada contiene " + cantidadPalabras + " palabras.");
                
    }
    
}
