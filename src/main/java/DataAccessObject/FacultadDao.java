package DataAccessObject;

import Interfaces.Repositorio;
import conexion.Conexion;
import modelos.Facultad;
import modelos.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDao implements Repositorio<Facultad> {

    @Override
    public boolean agregar(Facultad facultad) {
        String sql = "INSERT INTO Facultad (id, nombre, decano_id) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, facultad.getId());
            stmt.setString(2, facultad.getNombre());

            if (facultad.getDecano() != null) {
                stmt.setDouble(3, facultad.getDecano().getId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar facultad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Facultad consultar(Integer id) {
        Facultad facultad = null;
        String sql = "SELECT * FROM Facultad WHERE id = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    facultad = new Facultad(rs.getInt("id"), rs.getString("nombre"), null);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar facultad: " + e.getMessage());
        }
        return facultad;
    }

    @Override
    public boolean actualizar(Facultad facultad) {
        String sql = "UPDATE Facultad SET nombre = ?, decano_id = ? WHERE id = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, facultad.getNombre());
            if (facultad.getDecano() != null) {
                stmt.setDouble(2, facultad.getDecano().getId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, facultad.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar facultad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer id) {
        String sql = "DELETE FROM Facultad WHERE id = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar facultad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Facultad> listar() {
        List<Facultad> facultades = new ArrayList<>();
        String sql = "SELECT * FROM Facultad";
        try (Connection con = Conexion.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            PersonaDao personaDao = new PersonaDao();

            while (rs.next()) {
                int decanoId = rs.getInt("DECANO_ID");
                Persona decano = null;
                if (!rs.wasNull()) {  // Si DECANO_ID no es nulo
                    decano = personaDao.consultar(decanoId);
                }
                facultades.add(new Facultad(rs.getInt("id"), rs.getString("nombre"), decano));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar facultades: " + e.getMessage());
        }
        return facultades;
    }

    public String obtenerFacultadesConProgramas() {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT f.id AS facultad_id, f.nombre AS facultad_nombre, " +
                "p.id AS programa_id, p.nombre AS programa_nombre " +
                "FROM Facultad f " +
                "LEFT JOIN Programa p ON f.id = p.facultad_id " +
                "ORDER BY f.id, p.id";


        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int facultadIdActual = -1;

            while (rs.next()) {
                int facultadId = rs.getInt("facultad_id");
                String facultadNombre = rs.getString("facultad_nombre");
                int programaId = rs.getInt("programa_id");
                String programaNombre = rs.getString("programa_nombre");

                if (facultadId != facultadIdActual) {
                    facultadIdActual = facultadId;
                    resultado.append("\n🔹 Facultad: ").append(facultadNombre).append(" (ID: ").append(facultadId).append(")\n");
                    resultado.append("   Programas:\n");
                }

                if (programaId != 0) {
                    resultado.append("   - ").append(programaNombre).append(" (ID: ").append(programaId).append(")\n");
                } else {
                    resultado.append("   - No tiene programas registrados.\n");
                }
            }
        } catch (SQLException e) {
            resultado.append("❌ Error al obtener facultades con programas: ").append(e.getMessage()).append("\n");
        }
        System.out.println(resultado.toString());
        return resultado.toString();
    }

}
