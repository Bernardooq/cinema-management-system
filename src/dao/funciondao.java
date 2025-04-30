package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import model.Funcion;

public class funciondao {
    private Connection connection;

    public funciondao() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para agregar una nueva función
    public boolean agregarfuncion(Funcion funcion) {
        String sql = "INSERT INTO funciones (id_cine, id_pelicula, nombre, sala, fecha, hora, precio, capacidad, capacidad_actual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, funcion.getIdCine());
            stmt.setInt(2, funcion.getIdPelicula());
            stmt.setString(3, funcion.getNombre()); // Asignar nombre
            stmt.setInt(4, funcion.getSala());
            stmt.setDate(5, funcion.getFecha());
            stmt.setTime(6, funcion.getHora());
            stmt.setDouble(7, funcion.getPrecio());
            stmt.setInt(8, funcion.getCapacidad());
            stmt.setInt(9, funcion.getCapacidadActual());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id autoincremental
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    funcion.setId(rs.getInt(1)); // Asignar el id generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener una función por su id
    public Funcion obtenerfuncionPorId(int id) {
        String sql = "SELECT * FROM funciones WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcion(
                        rs.getInt("id"),
                        rs.getInt("id_cine"),
                        rs.getInt("id_pelicula"),
                        rs.getString("nombre"), // Obtener nombre de la película
                        rs.getInt("sala"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getInt("capacidad_actual"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodo para obtener todas las funciones
    public List<Funcion> obtenerTodasfunciones() {
        List<Funcion> funciones = new ArrayList<>();
        String sql = "SELECT * FROM funciones";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                funciones.add(new Funcion(
                        rs.getInt("id"),
                        rs.getInt("id_cine"),
                        rs.getInt("id_pelicula"),
                        rs.getString("nombre"), // Obtener nombre de la película
                        rs.getInt("sala"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getInt("capacidad_actual")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funciones;
    }

    public List<Funcion> obtenerTodasfuncionesPorCine(int id) {
        List<Funcion> funciones = new ArrayList<>();
        String sql = "SELECT * FROM funciones WHERE id_cine = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                funciones.add(new Funcion(
                        rs.getInt("id"),
                        rs.getInt("id_cine"),
                        rs.getInt("id_pelicula"),
                        rs.getString("nombre"), // Obtener nombre de la película
                        rs.getInt("sala"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getInt("capacidad_actual")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funciones;
    }

    public List<Funcion> obtenerFuncionesPorFiltros(int idCine, Integer idPelicula, String nombre, Integer sala,
            Date fecha) {
        List<Funcion> funciones = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM funciones WHERE id_cine = ?");

        if (idPelicula != null) {
            sql.append(" AND id_pelicula = ?");
        }
        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND nombre = ?");
        }
        if (sala != null) {
            sql.append(" AND sala = ?");
        }
        if (fecha != null) {
            sql.append(" AND fecha = ?");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int index = 1;
            stmt.setInt(index++, idCine);

            if (idPelicula != null) {
                stmt.setInt(index++, idPelicula);
            }
            if (nombre != null && !nombre.isEmpty()) {
                stmt.setString(index++, nombre);
            }
            if (sala != null) {
                stmt.setInt(index++, sala);
            }
            if (fecha != null) {
                stmt.setDate(index++, fecha);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                funciones.add(new Funcion(
                        rs.getInt("id"),
                        rs.getInt("id_cine"),
                        rs.getInt("id_pelicula"),
                        rs.getString("nombre"), // Obtener nombre de la película
                        rs.getInt("sala"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getInt("capacidad_actual")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funciones;
    }

    // Metodo para actualizar una función
    public boolean actualizarfuncion(Funcion funcion) {
        String sql = "UPDATE funciones SET id_cine = ?, id_pelicula = ?, nombre = ?, sala = ?, fecha = ?, hora = ?, precio = ?, capacidad = ?, capacidad_actual = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, funcion.getIdCine());
            stmt.setInt(2, funcion.getIdPelicula());
            stmt.setString(3, funcion.getNombre()); // Asignar nombre
            stmt.setInt(4, funcion.getSala());
            stmt.setDate(5, funcion.getFecha());
            stmt.setTime(6, funcion.getHora());
            stmt.setDouble(7, funcion.getPrecio());
            stmt.setInt(8, funcion.getCapacidad());
            stmt.setInt(9, funcion.getCapacidadActual());
            stmt.setInt(10, funcion.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarcapacidad(int id, int suma) {
        String sql = "UPDATE funciones SET capacidad_actual = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, suma);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para eliminar una función
    public boolean eliminarfuncion(int id) {
        String sql = "DELETE FROM funciones WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
