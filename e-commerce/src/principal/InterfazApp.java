package principal;

/**
 *
 * @author lokci
 */
import acciones.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class InterfazApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Connection conexion = null;
        Class.forName("org.apache.derby.jdbc.ClientDriver");

        try {
            String url = "jdbc:derby://localhost:1527/shopmedb;create=true";
            conexion = DriverManager.getConnection(url);

            // Crear instancias de DAO
            UsuariosDAO usuariosDAO = new UsuariosDAO(conexion);
            ItemsDAO itemsDAO = new ItemsDAO(conexion);

            // Crear un nuevo cliente
            Cliente cliente1 = new Cliente(1L, "Juan Perez", "1234", 1000.0);

            // Crear un nuevo proveedor
            Proveedor proveedor1 = new Proveedor(1L, "ProveedorXYZ", "password", "EmpresaXYZ");

            // Crear algunos productos y agregarlos al proveedor
            Item laptop = new Item(1L, "Laptop", 900.0, "Laptop Dell", "Electrónica", 5);
            Item mouse = new Item(2L, "Mouse", 50.0, "Mouse Logitech", "Electrónica", 10);
            proveedor1.agregarProducto(laptop);
            proveedor1.agregarProducto(mouse);

            // Mostrar productos disponibles del proveedor
            proveedor1.mostrarProductos();

            // Cliente agrega productos al carrito
            cliente1.agregarProductoAlCarrito(laptop);
            cliente1.agregarProductoAlCarrito(mouse);

            // Ver el carrito del cliente
            cliente1.verCarrito();

            // Cliente realiza la compra
            cliente1.realizarCompra();

            // Mostrar saldo restante del cliente
            System.out.println("Saldo restante de " + cliente1.getNombre() + ": " + cliente1.getSaldo());

            // Insertar el cliente en la base de datos
            usuariosDAO.insertar(cliente1);

            // Insertar productos en la base de datos
            itemsDAO.insertar(laptop);
            itemsDAO.insertar(mouse);

            // Obtener todos los usuarios para verificar
            List<Usuario> usuarios = usuariosDAO.obtenerTodos();
            for (Usuario usuario : usuarios) {
                System.out.println("Usuario: " + usuario.getNombre());
            }

            // Obtener todos los productos para verificar
            List<Item> productos = itemsDAO.obtenerTodos();
            for (Item producto : productos) {
                System.out.println("Producto: " + producto.getNombre() + " - Precio: " + producto.getPrecio());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
