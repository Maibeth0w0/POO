/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Servicio.FacultadServicio;
import java.util.List;
import modelos.Facultad;
import modelos.Persona;

/**
 *
 * @author maibe
 */
public class ControllerFacultad {
    private final FacultadServicio facultadServicio;

    public ControllerFacultad() {
        this.facultadServicio = new FacultadServicio();
    }
    
    //public void agregarFacultadLista(Integer id, String nombre, Persona decano){
        //facultadServicio.agregarFacultad(id, nombre, decano);
    //}
    
    public void agregarFacultad(Integer id, String nombre, Integer idDecano){
        facultadServicio.agregarFacultad(id, nombre, idDecano);
    }
    
    public void consultarFacultad(int id) {
        facultadServicio.consultarFacultad(id);
    }
    
    public void actualizarFacultad(Integer id, String nombre, Integer idDecano) {
        facultadServicio.actualizarFacultad(id, nombre, idDecano);
    }
    
    public void eliminarFacultad(Integer id){
        facultadServicio.eliminarFacultad(id);
    }
    
    public List<Facultad> listarFacultadBD() {
        List<Facultad> lista = facultadServicio.listarFacultad();
        return lista;
    }
    
    public List<Facultad> listarFacultaLista() {
        List<Facultad> lista = facultadServicio.listarFacultadLista();
        return lista;
    }

    public void mostrarFacultadConProgramas(){
        facultadServicio.mostrarFacultadConProgramas();
    }
    
}
