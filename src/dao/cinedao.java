package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection; // No se especifica paquete, ya que está en la raíz de 'src'
import model.Cine;

public class cinedao {
    private Connection connection;

    public cinedao() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para agregar un nuevo cine
    public boolean agregarcine(Cine cine) {
        String sql = "INSERT INTO cine (estado, ciudad, nombre) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cine.getEstado());
            stmt.setString(2, cine.getCiudad());
            stmt.setString(3, cine.getNombre());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id autoincremental
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cine.setId(rs.getInt(1)); // Asignar el id generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener un cine por su id
    public Cine obtenercinePorId(int id) {
        String sql = "SELECT * FROM cine WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cine(
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodo para obtener todos los cines
    public List<Cine> obtenerTodoscines() {
        List<Cine> cines = new ArrayList<>();
        String sql = "SELECT * FROM cine";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cines.add(new Cine(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cines;
    }

    // Metodo para actualizar un cine
    public boolean actualizarcine(Cine cine) {
        String sql = "UPDATE cine SET estado = ?, ciudad = ?, nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cine.getEstado());
            stmt.setString(2, cine.getCiudad());
            stmt.setString(3, cine.getNombre());
            stmt.setInt(4, cine.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para eliminar un cine
    public boolean eliminarcine(int id) {
        String sql = "DELETE FROM cine WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cine> obtenerCinesPorEstado(String estado) {
        String sql = "SELECT * FROM cine WHERE estado = ?";
        List<Cine> cines = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cines.add(new Cine(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cines;
    }

    public List<Cine> obtenerCinesPorCiudad(String ciudad) {
        String sql = "SELECT * FROM cine WHERE ciudad = ?";
        List<Cine> cines = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ciudad);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cines.add(new Cine(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cines;
    }

    public List<Cine> obtenerCinesPorNombre(String nombre) {
        String sql = "SELECT * FROM cine WHERE nombre LIKE ?";
        List<Cine> cines = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cines.add(new Cine(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cines;
    }

    public List<String> obtenerEstados() {
        List<String> estados = new ArrayList<>();
        String sql = "SELECT DISTINCT estado FROM cine";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                estados.add(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }

    public List<String> obtenerCiudadesDeCines(String estado) {
        List<String> ciudades = new ArrayList<>();
        String sql = "SELECT DISTINCT ciudad FROM cine WHERE estado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ciudades.add(rs.getString("ciudad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ciudades;
    }

    public List<Cine> obtenerCinePorCiudadEstado(String estado, String ciudad) {
        List<Cine> cines = new ArrayList<>();
        String sql = "SELECT * FROM cine WHERE estado = ? AND ciudad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setString(2, ciudad);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cines.add(new Cine(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("ciudad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cines;
    }

}
