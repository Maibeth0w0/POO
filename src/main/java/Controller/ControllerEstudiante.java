package Controller;

import modelos.Estudiante;
import Servicio.EstudianteServicio;

import java.util.List;

public class ControllerEstudiante {
    private EstudianteServicio estudianteService;

    public ControllerEstudiante() {
        this.estudianteService = new EstudianteServicio();
    }


    public void agregarEstudiante(Integer codigo, Integer idPersona, Integer idPrograma, Double promedio, boolean activo) {
        estudianteService.agregarEstudiante(codigo, idPersona, idPrograma, promedio, activo);

    }

    public void consultarEstudiante(int codigo) {
       estudianteService.obtenerEstudiante(codigo);

    }

    public void actualizarEstudiante(Integer codigo, Integer idPersona, Integer idPrograma, Double promedio, boolean activo) {
        estudianteService.actualizarEstudiante(codigo, idPersona, idPrograma, promedio, activo);

    }

    public void eliminarEstudiante(int codigo) {
       estudianteService.eliminarEstudiante(codigo);

    }

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
        return estudiantes;
    }
}

