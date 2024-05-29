package acciones;

/**
 *
 * @author lokci
 */
import java.util.List;

public interface DAO<T, K> {

    void insertar(T t) throws Exception;

    void modificar(T t) throws Exception;

    void eliminar(K id) throws Exception;

    List<T> obtenerTodos() throws Exception;

    T obtener(K id) throws Exception;
}
