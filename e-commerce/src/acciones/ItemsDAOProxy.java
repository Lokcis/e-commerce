package acciones;

import java.util.List;

/**
 *
 * @author lokci
 */
public class ItemsDAOProxy implements ItemsDAOi {
    private ItemsDAO itemsDAO;

    public ItemsDAOProxy(ItemsDAO itemsDAO) {
        this.itemsDAO = itemsDAO;
    }

    private void verificar(Item item) throws Exception {
        if (item.getId() == null || item.getNombre() == null || item.getDescripcion() == null || item.getCategoria() == null) {
            throw new Exception("Los campos del item no pueden ser nulos");
        }
    }

    @Override
    public void insertar(Item item) throws Exception {
        verificar(item);
        itemsDAO.insertar(item);
    }

    @Override
    public void modificar(Item item) throws Exception {
        verificar(item);
        itemsDAO.modificar(item);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        itemsDAO.eliminar(id);
    }

    @Override
    public List<Item> obtenerTodos() throws Exception {
        return itemsDAO.obtenerTodos();
    }

    @Override
    public Item obtener(Long id) throws Exception {
        return itemsDAO.obtener(id);
    }
}