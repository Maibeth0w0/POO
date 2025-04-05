package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion{
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static Conexion instancia;

    // Constructor privado: carga configuración y el driver
    private Conexion() {
        try {
            // Cargar archivo de propiedades
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ No se encontró el archivo config.properties");
            }

            props.load(input);

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            // Cargar driver
            Class.forName("org.h2.Driver");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error en la configuración: " + e.getMessage());
            throw new RuntimeException("Error en la configuración", e);
        }
    }

    public static synchronized Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener la conexión: " + e.getMessage());
            throw new RuntimeException("Error al obtener la conexión", e);
        }
    }
}
