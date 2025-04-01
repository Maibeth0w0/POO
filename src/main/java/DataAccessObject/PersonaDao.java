package DataAccessObject;

import Interfaces.Repositorio;
import conexion.Conexion;
import modelos.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao implements Repositorio<Persona> {

    @Override
    public boolean agregar(Persona persona) {
        String sql = "INSERT INTO Persona (ID, nombres, apellidos, email) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, persona.getId());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getEmail());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar persona: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Persona consultar(Integer id) {
        String sql = "SELECT * FROM Persona WHERE ID = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return  Persona persona = personaFabrica.crearPersona( //new persona
                            rs.getInt("ID"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al consultar persona: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Persona persona) {
        String sql = "UPDATE Persona SET nombres = ?, apellidos = ?, email = ? WHERE ID = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getEmail());
            ps.setInt(4, persona.getId());  // ✅ Corregido: Usa setInt en lugar de setDouble

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer id) {  // ✅
        System.out.println("ELIMINANDO");
        String sql = "DELETE FROM Persona WHERE ID = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);  // ✅ Usa setInt porque id es Integer
            System.out.println("ELIMINADO");
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (Connection con = Conexion.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(Persona persona = personaFabrica.crearPersona(
                        rs.getInt("ID"),  // ✅ Asegurado que es Integer
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar personas: " + e.getMessage());
        }
        return lista;
    }
}
