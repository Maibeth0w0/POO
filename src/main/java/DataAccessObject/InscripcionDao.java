package DataAccessObject;

import conexion.Conexion;
import modelos.Inscripcion;
import modelos.Curso;
import modelos.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDao {

    private static final String INSERT = "INSERT INTO Inscripcion (id_inscripcion, id_curso, codigo_estudiante, anno, semestre) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Inscripcion WHERE id_inscripcion = ?";
    private static final String UPDATE = "UPDATE Inscripcion SET anno = ?, semestre = ? WHERE id_inscripcion = ?";
    private static final String DELETE = "DELETE FROM Inscripcion WHERE id_inscripcion = ?";
    private static final String SELECT_ALL = "SELECT * FROM Inscripcion";

    public void agregar(Inscripcion inscripcion) {
        String sql = INSERT;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, inscripcion.getIdInscripcion());
            ps.setInt(2, inscripcion.getCurso().getId());
            ps.setInt(3, inscripcion.getEstudiante().getCodigo());
            ps.setInt(4, inscripcion.getAnno());
            ps.setInt(5, inscripcion.getSemestre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                // Si hay autoincrement, se puede leer. Si no, no hace falta.
                if (rs.next()) {
                    inscripcion.setIdInscripcion(rs.getInt(1));
                }
            }
            System.out.println("✅ Inscripción agregada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar inscripción: " + e.getMessage());
        }
    }

    public Inscripcion consultar(int idInscripcion) {
        String sql = SELECT_BY_ID;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idInscripcion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Consultar curso y estudiante en la BD
                    int idCurso = rs.getInt("id_curso");
                    int codigoEst = rs.getInt("codigo_estudiante");

                    Curso cursoReal = new CursoDao().consultarCurso(idCurso);
                    Estudiante estudianteReal = new EstudianteDao().consultar(codigoEst);

                    return new Inscripcion(
                            rs.getInt("id_inscripcion"),
                            cursoReal,
                            rs.getInt("anno"),
                            rs.getInt("semestre"),
                            estudianteReal
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar inscripción: " + e.getMessage());
        }
        return null;
    }

    public void actualizar(Inscripcion inscripcion) {
        String sql = UPDATE;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, inscripcion.getAnno());
            ps.setInt(2, inscripcion.getSemestre());
            ps.setInt(3, inscripcion.getIdInscripcion());
            ps.executeUpdate();

            System.out.println("✅ Inscripción actualizada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar inscripción: " + e.getMessage());
        }
    }

    public void eliminar(int idInscripcion) {
        String sql = DELETE;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idInscripcion);
            ps.executeUpdate();

            System.out.println("✅ Inscripción eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar inscripción: " + e.getMessage());
        }
    }

    public List<Inscripcion> listar() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = SELECT_ALL;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idIns = rs.getInt("id_inscripcion");
                int idCurso = rs.getInt("id_curso");
                int codigoEst = rs.getInt("codigo_estudiante");
                int anno = rs.getInt("anno");
                int sem = rs.getInt("semestre");

                // Se consulta la info real en la BD
                Curso cursoReal = new CursoDao().consultarCurso(idCurso);
                Estudiante estudianteReal = new EstudianteDao().consultar(codigoEst);

                inscripciones.add(new Inscripcion(
                        idIns,
                        cursoReal,
                        anno,
                        sem,
                        estudianteReal
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar inscripciones: " + e.getMessage());
        }
        return inscripciones;
    }
}
