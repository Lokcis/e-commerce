package acciones;

/**
 *
 * @author lokci
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAO implements DAO<Item, Long> {

    private Connection conexion;

    public ItemsDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Item item) throws Exception {
        String sql = "INSERT INTO items (id, nombre, precio, descripcion, categoria, cantidad) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, item.getId());
        ps.setString(2, item.getNombre());
        ps.setDouble(3, item.getPrecio());
        ps.setString(4, item.getDescripcion());
        ps.setString(5, item.getCategoria());
        ps.setInt(6, item.getCantidad());
        ps.executeUpdate();
    }

    @Override
    public void modificar(Item item) throws Exception {
        String sql = "UPDATE items SET nombre = ?, precio = ?, descripcion = ?, categoria = ?, cantidad = ? WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, item.getNombre());
        ps.setDouble(2, item.getPrecio());
        ps.setString(3, item.getDescripcion());
        ps.setString(4, item.getCategoria());
        ps.setInt(5, item.getCantidad());
        ps.setLong(6, item.getId());
        ps.executeUpdate();
    }

    @Override
    public void eliminar(Long id) throws Exception {
        String sql = "DELETE FROM items WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Item> obtenerTodos() throws Exception {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Item item = new Item(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"), rs.getString("categoria"), rs.getInt("cantidad"));
            items.add(item);
        }
        return items;
    }

    @Override
    public Item obtener(Long id) throws Exception {
        String sql = "SELECT * FROM items WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Item(rs.getLong("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"), rs.getString("categoria"), rs.getInt("cantidad"));
        } else {
            return null;
        }
    }
}
