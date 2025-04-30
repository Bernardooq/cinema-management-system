package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import model.Pelicula;

public class peliculadao {
    private Connection connection;

    public peliculadao() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean agregarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO pelicula (nombre, director, productor, clasificacion, duracion, genero) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pelicula.getNombre());
            stmt.setString(2, pelicula.getDirector());
            stmt.setString(3, pelicula.getProductor());
            stmt.setString(4, pelicula.getClasificacion());
            stmt.setFloat(5, pelicula.getDuracion());
            stmt.setString(6, pelicula.getGenero());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id autoincremental
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pelicula.setId(rs.getInt(1)); // Asignar el id generado
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Pelicula obtenerPeliculaPorId(int id) {
        String sql = "SELECT * FROM pelicula WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                        rs.getInt("id"), // Agregar el id
                        rs.getString("nombre"),
                        rs.getString("director"),
                        rs.getString("productor"),
                        rs.getString("clasificacion"),
                        rs.getFloat("duracion"),
                        rs.getString("genero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pelicula> obtenerTodasLasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM pelicula";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                peliculas.add(new Pelicula(
                        rs.getInt("id"), // Agregar el id
                        rs.getString("nombre"),
                        rs.getString("director"),
                        rs.getString("productor"),
                        rs.getString("clasificacion"),
                        rs.getFloat("duracion"),
                        rs.getString("genero")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    public boolean actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE pelicula SET nombre = ?, director = ?, productor = ?, clasificacion = ?, duracion = ?, genero = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getNombre());
            stmt.setString(2, pelicula.getDirector());
            stmt.setString(3, pelicula.getProductor());
            stmt.setString(4, pelicula.getClasificacion());
            stmt.setFloat(5, pelicula.getDuracion());
            stmt.setString(6, pelicula.getGenero());
            stmt.setInt(7, pelicula.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPelicula(int id) {
        String sql = "DELETE FROM pelicula WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
