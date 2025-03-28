package Controller;

import Servicio.ProfesorServicio;
import modelos.Profesor;
import java.util.List;

public class ControllerProfesor {

    private final ProfesorServicio profesorServicio;

    public ControllerProfesor() {
        this.profesorServicio = new ProfesorServicio();
    }

    public boolean agregarProfesor(Integer id, Integer idPersona, String tipoContrato) {
        return profesorServicio.agregarProfesor(id, idPersona, tipoContrato);
    }

    public Profesor consultarProfesor(Integer id) {
        return profesorServicio.consultarProfesor(id);
    }

    public boolean actualizarProfesor(Integer id, Integer idPersona, String tipoContrato) {
        return profesorServicio.actualizarProfesor(id, idPersona, tipoContrato);
    }

    public boolean eliminarProfesor(Integer id) {
        return profesorServicio.eliminarProfesor(id);
    }

    public List<Profesor> listarProfesores() {
        return profesorServicio.listarProfesores();
    }
}
