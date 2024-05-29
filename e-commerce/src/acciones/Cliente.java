package acciones;

/**
 *
 * @author lokci
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario implements Observador {

    private Queue<Item> colaDeItems;
    private List<Item> historialDeCompras;
    private double saldo;

    public Cliente(Long id, String nombre, String contrasena, double saldoInicial) {
        super(id, nombre, contrasena);
        this.colaDeItems = new LinkedList<>();
        this.historialDeCompras = new ArrayList<>();
        this.saldo = saldoInicial;
    }

    public Queue<Item> getColaDeItems() {
        return colaDeItems;
    }

    public List<Item> getHistorialDeCompras() {
        return historialDeCompras;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void agregarItemACola(Item item) {
        this.colaDeItems.add(item);
    }

    public void comprarItem() {
        Item item = this.colaDeItems.poll();
        if (item != null) {
            if (item.getPrecio() <= this.saldo) {
                this.historialDeCompras.add(item);
                this.saldo -= item.getPrecio();
                System.out.println("El cliente " + getNombre() + " ha comprado el item " + item.getNombre());
            } else {
                System.out.println("El cliente " + getNombre() + " no tiene suficiente saldo para comprar el item " + item.getNombre());
            }
        } else {
            System.out.println("No hay items en la cola de " + getNombre());
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Cliente: " + getNombre() + ", ID: " + getId());
    }

    @Override
    public void actualizar() {
        System.out.println("El cliente " + getNombre() + " ha sido notificado de un cambio en un item");
    }

    public void agregarProductoAlCarrito(Item item) {
        this.colaDeItems.add(item);
    }

    public void verCarrito() {
        System.out.println("Productos en el carrito de " + getNombre() + ":");
        for (Item item : colaDeItems) {
            System.out.println(item.getNombre() + " - Precio: " + item.getPrecio());
        }
    }

    public void realizarCompra() {
        System.out.println("Realizando compra para " + getNombre());
        while (!colaDeItems.isEmpty()) {
            Item item = colaDeItems.poll();
            if (item != null && item.getPrecio() <= this.saldo) {
                this.historialDeCompras.add(item);
                this.saldo -= item.getPrecio();
                System.out.println("Compra realizada: " + item.getNombre());
            } else {
                System.out.println("No se puede comprar el producto: " + item.getNombre());
            }
        }
    }
}
