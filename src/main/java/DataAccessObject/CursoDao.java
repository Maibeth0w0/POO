package DataAccessObject;

import conexion.Conexion;
import modelos.Curso;
import modelos.Programa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    private static final String INSERT = "INSERT INTO Curso (id, nombre, programa_id, activo) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Curso WHERE id = ?";
    private static final String UPDATE = "UPDATE Curso SET nombre = ?, programa_id = ?, activo = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Curso WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Curso";

    public boolean crearCurso(Curso curso) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setInt(1, curso.getId());
            ps.setString(2, curso.getNombre());
            if (curso.getPrograma() != null) {
                ps.setInt(3, curso.getPrograma().getId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setBoolean(4, curso.getActivo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear curso: " + e.getMessage());
            return false;
        }
    }

    public Curso consultarCurso(int id) {
        Curso curso = null;
        String sql = SELECT_BY_ID;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Programa prog = new ProgramaDao().consultarPrograma(rs.getInt("programa_id"));
                    curso = new Curso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            prog,
                            rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar curso: " + e.getMessage());
        }
        return curso;
    }

    public boolean actualizarCurso(Curso curso) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, curso.getNombre());
            if (curso.getPrograma() != null) {
                ps.setInt(2, curso.getPrograma().getId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            ps.setBoolean(3, curso.getActivo());
            ps.setInt(4, curso.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCurso(int id) {
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Programa prog = new ProgramaDao().consultarPrograma(rs.getInt("programa_id"));
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        prog,
                        rs.getBoolean("activo")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cursos: " + e.getMessage());
        }
        return cursos;
    }
}
