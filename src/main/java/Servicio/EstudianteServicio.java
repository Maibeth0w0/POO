package Servicio;

import DataAccessObject.EstudianteDao;
import DataAccessObject.PersonaDao;
import DataAccessObject.ProgramaDao;
import modelos.Estudiante;
import modelos.Persona;
import modelos.Programa;

import java.util.List;

public class EstudianteServicio {

    private EstudianteDao estudianteDao;
    private PersonaDao personaDao;
    private ProgramaDao programaDao;

    public EstudianteServicio() {
        this.estudianteDao = new EstudianteDao();
        this.personaDao = new PersonaDao();
        this.programaDao = new ProgramaDao();
    }

    public boolean agregarEstudiante(Integer codigo, Integer idPersona, Integer idPrograma, double promedio, boolean activo) {

        Persona persona = personaDao.consultar(idPersona);

        Programa programa = programaDao.consultarPrograma(idPrograma);

        Estudiante estudiante = new Estudiante(codigo, programa, activo, promedio, persona.getId(), persona.getNombres(), persona.getApellidos(), persona.getEmail());
        try {
            estudianteDao.agregar(estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error en servicio al agregar estudiante: " + e.getMessage());
            return false;
        }
    }

    public Estudiante obtenerEstudiante(int codigo) {
        try {
            return estudianteDao.consultar(codigo);
        } catch (Exception e) {
            System.err.println("❌ Error en servicio al consultar estudiante: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarEstudiante(Integer codigo, Integer idPersona, Integer idPrograma, double promedio, boolean activo) {
        Persona persona = personaDao.consultar(idPersona);
        Programa programa = programaDao.consultarPrograma(idPrograma);

        Estudiante estudiante = new Estudiante(codigo, programa, activo, promedio, persona.getId(), persona.getNombres(), persona.getApellidos(), persona.getEmail());

        try {
            estudianteDao.actualizar(estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error en servicio al actualizar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarEstudiante(int codigo) {
        try {
            estudianteDao.eliminar(codigo);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error en servicio al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }

    public List<Estudiante> listarEstudiantes() {
        try {
            return estudianteDao.listar();
        } catch (Exception e) {
            System.err.println("❌ Error en servicio al listar estudiantes: " + e.getMessage());
            return null;
        }
    }
}

