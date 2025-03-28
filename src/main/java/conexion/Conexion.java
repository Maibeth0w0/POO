package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:h2:./db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Conexion instancia;

    // Constructor privado, se carga el driver
    private Conexion() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error al cargar el driver: " + e.getMessage());
            throw new RuntimeException("Error al cargar el driver", e);
        }
    }

    // Mantener el singleton para administrar la configuración, pero...
    public static synchronized Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Cada llamada devuelve una nueva conexión
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener la conexión: " + e.getMessage());
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }
}
