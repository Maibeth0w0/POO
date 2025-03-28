package Controller;

import Servicio.InscripcionServicio;
import modelos.Inscripcion;
import java.util.List;

public class ControllerInscripcion {
    private final InscripcionServicio inscripcionServicio;

    public ControllerInscripcion() {
        this.inscripcionServicio = new InscripcionServicio();
    }

    // Agregar inscripción con 5 parámetros
    public void agregarInscripcion(Integer idInscripcion, Integer id_curso,
                                   Integer codigo_estudiante, Integer anno, Integer semestre) {
        inscripcionServicio.agregarInscripcion(idInscripcion, id_curso, codigo_estudiante, anno, semestre);
    }

    // Consultar inscripción usando idInscripcion
    public Inscripcion consultarInscripcion(int idInscripcion) {
        return inscripcionServicio.consultarInscripcion(idInscripcion);
    }

    // Actualizar inscripción con 5 parámetros
    public void actualizarInscripcion(Integer idInscripcion, Integer id_curso,
                                      Integer codigo_estudiante, Integer anno, Integer semestre) {
        inscripcionServicio.actualizarInscripcion(idInscripcion, id_curso, codigo_estudiante, anno, semestre);
    }

    // Eliminar inscripción por idInscripcion
    public void eliminarInscripcion(int idInscripcion) {
        inscripcionServicio.eliminarInscripcion(idInscripcion);
    }

    public List<Inscripcion> listarInscripciones() {
        return inscripcionServicio.listarInscripciones();
    }
}
