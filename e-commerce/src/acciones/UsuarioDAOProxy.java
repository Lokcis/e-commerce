package acciones;

import java.util.List;

/**
 *
 * @author lokci
 */
public class UsuarioDAOProxy implements UsuariosDAOi {

    private UsuariosDAO usuariosDAO;
    private Long idUsuarioActual;

    public UsuarioDAOProxy(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    private void verificar(Usuario usuario) throws Exception {
        if (usuario.getId() == null || usuario.getNombre() == null || usuario.getContrasena() == null) {
            throw new Exception("Los campos del usuario no pueden ser nulos");
        }
    }

    private boolean autenticar(Usuario usuario) throws Exception {
        Usuario usuarioBD = usuariosDAO.obtener(usuario.getId());
        if (usuarioBD != null && usuarioBD.getContrasena().equals(usuario.getContrasena())) {
            return true;
        } else {
            return false;
        }
    }

    private Usuario obtenerUsuarioActual(Long id) throws Exception {
        return usuariosDAO.obtener(id);
    }

    @Override
    public void insertar(Usuario usuario) throws Exception {
        verificar(usuario);
        if (autenticar(usuario)) {
            usuariosDAO.insertar(usuario);
        } else {
            throw new Exception("Autenticación fallida");
        }
    }

    @Override
    public void modificar(Usuario usuario) throws Exception {
        verificar(usuario);
        if (autenticar(usuario)) {
            usuariosDAO.modificar(usuario);
        } else {
            throw new Exception("Autenticación fallida");
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Usuario usuario = obtener(id);
        if (usuario != null && usuario.esAdministrador()) {
            usuariosDAO.eliminar(id);
        } else {
            throw new Exception("Autenticación fallida: solo los administradores pueden eliminar usuarios");
        }
    }

    @Override
    public List<Usuario> obtenerTodos() throws Exception {
        Usuario usuario = obtenerUsuarioActual(idUsuarioActual);
        if (usuario != null && usuario.esAdministrador()) {
            return usuariosDAO.obtenerTodos();
        } else {
            throw new Exception("Autenticación fallida: solo los administradores pueden obtener todos los usuarios");
        }
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        Usuario usuario = obtenerUsuarioActual(idUsuarioActual);
        if (usuario != null && (usuario.esAdministrador() || usuario.getId().equals(id))) {
            return usuariosDAO.obtener(id);
        } else {
            throw new Exception("Autenticación fallida: solo los administradores o el propio usuario pueden obtener los detalles del usuario");
        }
    }
}
