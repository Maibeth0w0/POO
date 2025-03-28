package DataAccessObject;

import conexion.Conexion;
import modelos.Programa;
import modelos.Facultad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDao {

    private static final String INSERT = "INSERT INTO Programa (id, nombre, duracion, registro, facultad_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Programa WHERE id = ?";
    private static final String UPDATE = "UPDATE Programa SET nombre = ?, duracion = ?, registro = ?, facultad_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Programa WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Programa";

    public boolean agregarPrograma(Programa programa) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setInt(1, programa.getId());
            ps.setString(2, programa.getNombre());
            ps.setDouble(3, programa.getDuracion());
            ps.setString(4, programa.getRegistro());
            ps.setInt(5, programa.getFacultad().getId());

            int resultado = ps.executeUpdate();
            System.out.println("✅ Programa agregado correctamente.");
            return resultado > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar el programa: " + e.getMessage());
            return false;
        }
    }

    public Programa consultarPrograma(int id) {
        Programa programa = null;
        String sql = SELECT_BY_ID;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Facultad fac = new FacultadDao().consultar(rs.getInt("facultad_id"));
                    programa = new Programa(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("duracion"),
                            rs.getString("registro"),
                            fac
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar el programa: " + e.getMessage());
        }
        return programa;
    }

    public void actualizarPrograma(Programa programa) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, programa.getNombre());
            ps.setDouble(2, programa.getDuracion());
            ps.setString(3, programa.getRegistro());
            ps.setInt(4, programa.getFacultad().getId());
            ps.setInt(5, programa.getId());

            ps.executeUpdate();
            System.out.println("✅ Programa actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el programa: " + e.getMessage());
        }
    }

    public void eliminarPrograma(int id) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Programa eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el programa: " + e.getMessage());
        }
    }

    public List<Programa> listarProgramas() {
        List<Programa> programas = new ArrayList<>();
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Facultad facultad = new FacultadDao().consultar(rs.getInt("facultad_id"));
                Programa prog = new Programa(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("duracion"),
                        rs.getString("registro"),
                        facultad
                );
                programas.add(prog);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar programas: " + e.getMessage());
        }
        return programas;
    }
}
