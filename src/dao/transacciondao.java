package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import model.Transaccion;

public class transacciondao {
    private Connection connection;

    public transacciondao() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para agregar una nueva transacción
    public boolean agregartransaccion(Transaccion transaccion) {
        String sql = "INSERT INTO transaccion (id_user, id_funcion, id_cine, boletos, precio_boleto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, transaccion.getIdUser());
            stmt.setInt(2, transaccion.getIdFuncion());
            stmt.setInt(3, transaccion.getIdCine());
            stmt.setInt(4, transaccion.getBoletos());
            stmt.setDouble(5, transaccion.getPrecioBoleto());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id autoincremental
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    transaccion.setId(rs.getInt(1)); // Asignar el id generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener una transacción por su id
    public Transaccion obtenertransaccionPorId(int id) {
        String sql = "SELECT * FROM transaccion WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transaccion(
                        rs.getInt("id_user"),
                        rs.getInt("id_funcion"),
                        rs.getInt("id_cine"),
                        rs.getInt("boletos"),
                        rs.getDouble("precio_boleto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodo para obtener todas las transacciones
    public List<Transaccion> obtenerTodastransacciones() {
        List<Transaccion> transacciones = new ArrayList<>();
        String sql = "SELECT * FROM transaccion";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                transacciones.add(new Transaccion(
                        rs.getInt("id_user"),
                        rs.getInt("id_funcion"),
                        rs.getInt("id_cine"),
                        rs.getInt("boletos"),
                        rs.getDouble("precio_boleto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    // Metodo para eliminar una transacción
    public boolean eliminartransaccion(int id) {
        String sql = "DELETE FROM transaccion WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
