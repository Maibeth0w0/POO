package DataAccessObject;

import conexion.Conexion;
import modelos.Estudiante;
import modelos.Programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Fabrica.EstudianteFabrica;

public class EstudianteDao {
    
    private final EstudianteFabrica estudianteFabrica;

    public EstudianteDao() {
        this.estudianteFabrica = new EstudianteFabrica();
    }

    public void agregar(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (codigo, id_persona, programa_id, activo, promedio) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, estudiante.getCodigo());
            stmt.setDouble(2, estudiante.getId()); // id de Persona en la superclase
            stmt.setInt(3, estudiante.getPrograma().getId());
            stmt.setBoolean(4, estudiante.getActivo());
            stmt.setDouble(5, estudiante.getPromedio());
            stmt.executeUpdate();

            System.out.println("✅ Estudiante agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar estudiante: " + e.getMessage());
        }
    }

    public Estudiante consultar(int codigo) {
        String sql = "SELECT * FROM Estudiante WHERE codigo = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return estudianteFabrica.crearEstudiante(
                            rs.getInt("codigo"),
                            new Programa(rs.getInt("programa_id"), "", 0.0, "", null),
                            rs.getBoolean("activo"),
                            rs.getDouble("promedio"),
                            rs.getInt("id_persona"),
                            "", // nombres
                            "", // apellidos
                            ""  // email
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar estudiante: " + e.getMessage());
        }
        return null;
    }

    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE Estudiante SET programa_id = ?, activo = ?, promedio = ? WHERE codigo = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, estudiante.getPrograma().getId());
            stmt.setBoolean(2, estudiante.getActivo());
            stmt.setDouble(3, estudiante.getPromedio());
            stmt.setInt(4, estudiante.getCodigo());
            stmt.executeUpdate();

            System.out.println("✅ Estudiante actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estudiante: " + e.getMessage());
        }
    }

    public void eliminar(int codigo) {
        String sql = "DELETE FROM Estudiante WHERE codigo = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("✅ Estudiante eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar estudiante: " + e.getMessage());
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Estudiante";
        try (Connection con = Conexion.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                estudiantes.add(estudianteFabrica.crearEstudiante(
                        rs.getInt("codigo"),
                        new Programa(rs.getInt("programa_id"), "", 0.0, "", null),
                        rs.getBoolean("activo"),
                        rs.getDouble("promedio"),
                        rs.getInt("id_persona"),
                        "", // nombres
                        "", // apellidos
                        ""  // email
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar estudiantes: " + e.getMessage());
        }
        return estudiantes;
    }
}
