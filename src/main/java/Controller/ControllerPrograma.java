package Controller;

import Servicio.ProgramaServicio;
import modelos.Facultad;
import modelos.Programa;
import java.util.List;


public class ControllerPrograma {

    private final ProgramaServicio programaServicio;

    public ControllerPrograma() {
        this.programaServicio = new ProgramaServicio();
    }

    public void inscribirPrograma(Integer id, String nombre, Double duracion, String registro, Facultad facultad) {
        programaServicio.inscribirPrograma(id, nombre, duracion, registro, facultad);
    }

    public List<Programa> listarProgramasLista() {
        return programaServicio.listarProgramasBD();
    }

    public boolean agregarPrograma(Integer id, String nombre, Double duracion, String registro, Integer facultad) {
        return programaServicio.agregarPrograma(id, nombre, duracion, registro, facultad);
    }

    public Programa consultarPrograma(Integer id) {
        return programaServicio.consultarPrograma(id);
    }
    
    public void actualizarPrograma(Integer id, String nombre, Double duracion, String registro, Integer facultad){
        programaServicio.editarPrograma(id, nombre, duracion, registro, facultad);
    }
    
    public void eliminarPrograma(Integer id){
        programaServicio.eliminarPrograma(id);
    }
}
