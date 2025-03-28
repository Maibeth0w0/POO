/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import DataAccessObject.FacultadDao;
import DataAccessObject.ProgramaDao;
import java.util.List;
import modelos.Facultad;
import modelos.Programa;

/**
 *
 * @author maibe
 */
public class ProgramaServicio {
    
    private final ProgramaDao programaDao;
    private final InscribirPrograma inscribir;
    private final FacultadDao facultadDao;
    
    public ProgramaServicio(){
        this.programaDao = new ProgramaDao();
        this.inscribir = new InscribirPrograma();
        this.facultadDao = new FacultadDao();
        
    }
    
    public void inscribirPrograma(Integer id, String nombre, Double duracion, String registro, Facultad facultad){
        Programa programa = new Programa();
            programa.setId(id);
            programa.setNombre(nombre);
            programa.setDuracion(duracion);
            programa.setRegistro(registro);
            programa.setFacultad(facultad);
            
            inscribir.inscribirPrograma(programa);
    }
    
    public List<Programa>  listarProgramaLista(){
        List<Programa> programa = inscribir.getListadoPrograma();
        return programa;
    }
    
    public boolean agregarPrograma(Integer id, String nombre, Double duracion, String registro, Integer idFacultad) {
        
        Facultad facultad = facultadDao.consultar(idFacultad);

        if (facultad == null) {
            System.out.println("❌ Error: Facultad con ID " + idFacultad + " no encontrada.");
            return false;
        }

        // Validar que los campos no sean nulos o vacíos
        if (nombre == null || nombre.trim().isEmpty() || registro == null || registro.trim().isEmpty() || duracion == null) {
            System.err.println("❌ Datos inválidos para agregar programa.");
            return false;
        }

        Programa programa = new Programa(id, nombre, duracion, registro, facultad);
        return programaDao.agregarPrograma(programa);
    }

    
    public void editarPrograma(Integer id, String nombre, Double duracion, String registro, Integer idFacultad) {
              
        Facultad facultad = facultadDao.consultar(idFacultad);
            
        if(facultad == null){
            System.out.println("Facultad no encontrada");
            return;
        }
         
        Programa programa = new Programa();
        programa.setId(id);
        programa.setNombre(nombre);
        programa.setDuracion(duracion);
        programa.setRegistro(registro);
        programa.setFacultad(facultad);
            
        if (programa == null || programa.getId()==null||programa.getNombre().isEmpty() || programa.getDuracion()== null || programa.getRegistro()==null || programa.getFacultad()==null ) {
            System.err.println("❌ Datos inválidos para agregar programa.");               
        }
        
        programaDao.actualizarPrograma(programa);

    }
    
    public Programa consultarPrograma(Integer id) {
        if (id <= 0) {
            System.err.println("❌ ID inválido para consulta.");
            return null;
        }
        return programaDao.consultarPrograma(id);
    }
    
    public List<Programa> listarProgramasBD(){
        return programaDao.listarProgramas();
    }
    
    public void eliminarPrograma(Integer id){
        programaDao.eliminarPrograma(id);
    }
    
    
}
