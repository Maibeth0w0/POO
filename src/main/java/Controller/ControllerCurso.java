package Controller;

import Servicio.CursoServicio;
import modelos.Curso;
import modelos.Programa;
import java.util.List;

public class ControllerCurso {

    private final CursoServicio cursoServicio;

    public ControllerCurso() {
        this.cursoServicio = new CursoServicio();
    }

    public boolean crearCurso(Integer id, String nombre, Integer idPrograma, Boolean activo) {

        return cursoServicio.crearCurso(id, nombre, idPrograma, activo);
    }

    public Curso consultarCurso(Integer id) {
        return cursoServicio.consultarCurso(id);
    }

    public boolean actualizarCurso(Integer id, String nombre, Integer idPrograma, Boolean activo) {

        return cursoServicio.actualizarCurso(id, nombre, idPrograma, activo);
    }

    public boolean eliminarCurso(Integer id) {
        return cursoServicio.eliminarCurso(id);
    }

    public List<Curso> listarCursos() {
        return cursoServicio.listarCursos();
    }
}
