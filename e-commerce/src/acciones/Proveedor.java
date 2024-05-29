package acciones;

/**
 *
 * @author lokci
 */
import java.util.ArrayList;
import java.util.List;

public class Proveedor extends Usuario implements Observador {

    private String empresa;
    private List<Item> productos;
    private int ventasTotales;

    public Proveedor(Long id, String nombre, String contrasena, String empresa) {
        super(id, nombre, contrasena);
        this.empresa = empresa;
        this.productos = new ArrayList<>();
        this.ventasTotales = 0;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<Item> getProductos() {
        return productos;
    }

    public int getVentasTotales() {
        return ventasTotales;
    }

    public void agregarProducto(Item item) {
        this.productos.add(item);
        item.agregarObservador(this); // El proveedor se suscribe a los cambios del item
    }

    public void venderProducto(Item item) {
        this.productos.remove(item);
        this.ventasTotales++;
        System.out.println("El proveedor " + getNombre() + " ha vendido el item " + item.getNombre());
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Proveedor: " + getNombre() + ", ID: " + getId() + ", Empresa: " + getEmpresa());
    }

    @Override
    public void actualizar() {
        System.out.println("El proveedor " + getNombre() + " ha sido notificado de un cambio en un item");
    }

    public void mostrarProductos() {
        System.out.println("Productos disponibles de " + getEmpresa() + ":");
        for (Item item : productos) {
            System.out.println(item.getNombre() + " - Precio: " + item.getPrecio());
        }
    }

}
