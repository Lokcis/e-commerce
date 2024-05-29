/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lokci
 */
public class UsuariosDAO implements DAO<Usuario, Long> {

    private Connection conexion;

    public UsuariosDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuarios (id, nombre, contrasena) VALUES (?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, usuario.getId());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getContrasena());
        ps.executeUpdate();
    }

    @Override
    public void modificar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuarios SET nombre = ?, contrasena = ? WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getContrasena());
        ps.setLong(3, usuario.getId());
        ps.executeUpdate();
    }

    @Override
    public void eliminar(Long id) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Usuario> obtenerTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Usuario usuario = new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("contrasena")) {
                @Override
                public void mostrarDetalles() {
                }
            };
            usuarios.add(usuario);
        }
        return usuarios;
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("contrasena")) {
                @Override
                public void mostrarDetalles() {
                }
            };
        } else {
            return null;
        }
    }
}
