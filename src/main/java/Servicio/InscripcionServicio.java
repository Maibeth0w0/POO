package Servicio;

import DataAccessObject.CursoDao;
import DataAccessObject.EstudianteDao;
import DataAccessObject.InscripcionDao;
import modelos.Curso;
import modelos.Estudiante;
import modelos.Inscripcion;
import Fabrica.InscripcionFabrica;

import java.util.List;

public class InscripcionServicio {
    private InscripcionDao inscripcionDao;
    private EstudianteDao estudianteDao;
    private CursoDao cursoDao;
    private InscripcionFabrica inscripcionFabrica;

    public InscripcionServicio() {
        this.inscripcionDao = new InscripcionDao();
        this.estudianteDao = new EstudianteDao();
        this.cursoDao = new CursoDao();
        this.inscripcionFabrica = new InscripcionFabrica();
    }

    // Agregar una inscripción con validaciones
    public void agregarInscripcion(Integer idInscripcion, Integer id_curso,
                                   Integer codigo_estudiante, Integer anno, Integer semestre) {

        Estudiante estudiante = estudianteDao.consultar(codigo_estudiante);
        Curso curso = cursoDao.consultarCurso(id_curso);

        if (estudiante == null || curso == null) {
            System.out.println("Estudiante o curso no existe");
            return;
        }

        // Se crea la inscripción con el idInscripcion como PK
        Inscripcion inscripcion = inscripcionFabrica.crearInscripcion(idInscripcion, curso, anno, semestre, estudiante);

        if (inscripcion.getIdInscripcion() != null &&
                inscripcion.getCurso() != null &&
                inscripcion.getEstudiante() != null) {
            inscripcionDao.agregar(inscripcion);
        } else {
            System.err.println("❌ Error: Datos inválidos para la inscripción.");
        }
    }

    // Consultar la inscripción por su idInscripcion (entero)
    public Inscripcion consultarInscripcion(int idInscripcion) {
        if (idInscripcion > 0) {
            return inscripcionDao.consultar(idInscripcion);
        } else {
            System.err.println("❌ Error: Parámetro inválido para la consulta (idInscripcion).");
            return null;
        }
    }

    // Actualizar una inscripción
    public void actualizarInscripcion(Integer idInscripcion, Integer id_curso,
                                      Integer codigo_estudiante, Integer anno, Integer semestre) {
        Estudiante estudiante = estudianteDao.consultar(codigo_estudiante);
        Curso curso = cursoDao.consultarCurso(id_curso);

        if (estudiante == null || curso == null) {
            System.out.println("Estudiante o curso no existe");
            return;
        }

        Inscripcion inscripcion = inscripcionFabrica.crearInscripcion(idInscripcion, curso, anno, semestre, estudiante);

        if (inscripcion.getIdInscripcion() != null) {
            inscripcionDao.actualizar(inscripcion);
        } else {
            System.err.println("❌ Error: Datos inválidos para la actualización.");
        }
    }

    // Eliminar una inscripción por idInscripcion
    public void eliminarInscripcion(int idInscripcion) {
        if (idInscripcion > 0) {
            inscripcionDao.eliminar(idInscripcion);
        } else {
            System.err.println("❌ Error: Parámetro inválido (idInscripcion).");
        }
    }

    // Listar todas las inscripciones
    public List<Inscripcion> listarInscripciones() {
        return inscripcionDao.listar();
    }
}
