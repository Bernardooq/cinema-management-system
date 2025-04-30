// userdao.java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import model.User;

public class userdao {
    private Connection connection;

    public userdao() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo usuario
    public boolean agregarUsuario(User usuario) {
        String sql = "INSERT INTO user (email, passwd, username) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getPasswd());
            stmt.setString(3, usuario.getUsername());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id autoincremental
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    usuario.setId(rs.getInt(1)); // Asignar el id generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener un usuario por email y psswd
    public User obtenerUsuarioPorEmailyPsswd(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? and passwd= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("email"),
                        rs.getString("passwd"),
                        rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean obtenerUsuarioPorEmailyPsswdBool(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? and passwd= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener un usuario por su id
    public boolean obtenerUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener todos los usuarios
    public List<User> obtenerTodosUsuarios() {
        List<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usuarios.add(new User(
                        rs.getString("email"),
                        rs.getString("passwd"),
                        rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Metodo para actualizar un usuario
    public boolean actualizarUsuario(User usuario) {
        String sql = "UPDATE user SET passwd = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getPasswd());
            stmt.setString(2, usuario.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para eliminar un usuario
    public boolean eliminarUsuario(String email) {
        String sql = "DELETE FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
