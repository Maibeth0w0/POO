package DataAccessObject;

import conexion.Conexion;
import modelos.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDao {

    // Sentencias SQL para insertar, actualizar, consultar, eliminar y listar
    private static final String INSERT_PERSONA = "INSERT INTO Persona (id, nombres, apellidos, email) VALUES (?, ?, ?, ?)";
    private static final String INSERT_PROFESOR = "INSERT INTO Profesor (id, persona_id, tipoContrato) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT p.id AS persona_id, p.nombres, p.apellidos, p.email, pr.id AS profesor_id, pr.tipoContrato " +
            "FROM Persona p INNER JOIN Profesor pr ON p.id = pr.persona_id WHERE pr.id = ?";
    private static final String UPDATE_PERSONA = "UPDATE Persona SET nombres = ?, apellidos = ?, email = ? WHERE id = ?";
    private static final String UPDATE_PROFESOR = "UPDATE Profesor SET tipoContrato = ? WHERE id = ?";
    private static final String DELETE_PROFESOR = "DELETE FROM Profesor WHERE id = ?";
    private static final String DELETE_PERSONA = "DELETE FROM Persona WHERE id = ?";
    private static final String SELECT_ALL = "SELECT p.id AS persona_id, p.nombres, p.apellidos, p.email, pr.id AS profesor_id, pr.tipoContrato " +
            "FROM Persona p INNER JOIN Profesor pr ON p.id = pr.persona_id";

    // Método para agregar un profesor:
    // Se inserta en Persona (solo si no existe) y luego en Profesor, utilizando transacciones.
    public boolean agregarProfesor(Profesor profesor) {
        try (Connection con = Conexion.getInstance().getConnection()) {
            con.setAutoCommit(false);

            // Verificar si la persona ya existe
            PersonaDao personaDao = new PersonaDao();
            if (personaDao.consultar(profesor.getIdPersona()) == null) {
                try (PreparedStatement psPersona = con.prepareStatement(INSERT_PERSONA)) {
                    psPersona.setDouble(1, profesor.getIdPersona());
                    psPersona.setString(2, profesor.getNombres());
                    psPersona.setString(3, profesor.getApellidos());
                    psPersona.setString(4, profesor.getEmail());
                    psPersona.executeUpdate();
                }
            }

            // Insertar en Profesor
            try (PreparedStatement psProfesor = con.prepareStatement(INSERT_PROFESOR)) {
                psProfesor.setInt(1, profesor.getId());             // id propio de Profesor
                psProfesor.setInt(2, profesor.getIdPersona());        // id de Persona (referencia)
                psProfesor.setString(3, profesor.getTipoContrato());
                psProfesor.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar profesor: " + e.getMessage());
            return false;
        }
    }

    // Método para consultar un profesor por su id (de la tabla Profesor)
    public Profesor consultarProfesor(int profesorId) {
        Profesor profesor = null;
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, profesorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profesor = new Profesor(
                            rs.getInt("profesor_id"),          // id propio de Profesor
                            rs.getString("tipoContrato"),
                            rs.getInt("persona_id"),           // id de Persona
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar profesor: " + e.getMessage());
        }
        return profesor;
    }

    // Método para actualizar un profesor
    public boolean actualizarProfesor(Profesor profesor) {
        try (Connection con = Conexion.getInstance().getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement psPersona = con.prepareStatement(UPDATE_PERSONA);
                 PreparedStatement psProfesor = con.prepareStatement(UPDATE_PROFESOR)) {

                // Actualizar en Persona
                psPersona.setString(1, profesor.getNombres());
                psPersona.setString(2, profesor.getApellidos());
                psPersona.setString(3, profesor.getEmail());
                psPersona.setDouble(4, profesor.getIdPersona());
                psPersona.executeUpdate();

                // Actualizar en Profesor
                psProfesor.setString(1, profesor.getTipoContrato());
                psProfesor.setDouble(2, profesor.getId());
                psProfesor.executeUpdate();

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                System.err.println("❌ Error al actualizar profesor: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error en conexión (actualizarProfesor): " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un profesor
    // Se elimina primero en Profesor y luego en Persona (usando la misma conexión para la consulta de persona_id)
    public boolean eliminarProfesor(int profesorId) {
        try (Connection con = Conexion.getInstance().getConnection()) {
            con.setAutoCommit(false);
            int personaId;
            try (PreparedStatement psConsultar = con.prepareStatement("SELECT persona_id FROM Profesor WHERE id = ?")) {
                psConsultar.setInt(1, profesorId);
                try (ResultSet rs = psConsultar.executeQuery()) {
                    if (rs.next()) {
                        personaId = rs.getInt("persona_id");
                    } else {
                        con.rollback();
                        return false;
                    }
                }
            }
            // Eliminar en Profesor
            try (PreparedStatement psProfesor = con.prepareStatement(DELETE_PROFESOR)) {
                psProfesor.setInt(1, profesorId);
                psProfesor.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los profesores
    public List<Profesor> listarProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Profesor profesor = new Profesor(
                        rs.getInt("profesor_id"),
                        rs.getString("tipoContrato"),
                        rs.getInt("persona_id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("email")
                );
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar profesores: " + e.getMessage());
        }
        return profesores;
    }
}
