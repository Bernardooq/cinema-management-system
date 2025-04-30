import java.sql.Connection;
import java.sql.SQLException;

import util.DBConnection;

public class TestConnect {

    public static void main(String[] args) {

        Connection connection;

        try {
            connection = DBConnection.getConnection();
            System.out.println("Coneccion exitosa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
