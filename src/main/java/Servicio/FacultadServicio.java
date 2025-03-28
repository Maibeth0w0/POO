/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import DataAccessObject.FacultadDao;
import DataAccessObject.PersonaDao;

import java.sql.SQLOutput;
import java.util.List;
import modelos.Facultad;
import modelos.Persona;

/**
 *
 * @author maibe
 */

public class FacultadServicio {
    private final FacultadDao facultadDao;
    private final InscripcionFacultad inscribir;
    private final PersonaDao personaDao;

    public FacultadServicio() {
        this.personaDao = new PersonaDao();
        this.facultadDao = new FacultadDao();
        this.inscribir = new InscripcionFacultad();
    }
    
    public void inscribirFacultad(Integer id, String nombre, Integer idDecano){
        
            Persona personaDecano = personaDao.consultar(idDecano);
            
            if(personaDecano != null){
                Facultad facultad = new Facultad();
                facultad.setId(id);
                facultad.setNombre(nombre);
                facultad.setDecano(personaDecano);

                inscribir.inscribirFacultad(facultad);
                return;
            }

            System.out.println("Persona no existe");
                           
    }
    
    public List<Facultad>  listarFacultadLista(){
        List<Facultad> facultad = inscribir.getListadoFacultad();
        return facultad;
    }
   
    public boolean agregarFacultad(Integer id, String nombre, Integer idDecano) {
        
        Persona personaDecano = personaDao.consultar(idDecano);
                
        if(personaDecano != null){
        
            Facultad facultad = new Facultad();
            facultad.setId(id);
            facultad.setNombre(nombre);
            facultad.setDecano(personaDecano);
            
            if (facultad == null || facultad.getNombre().isEmpty()) {
                System.err.println("❌ Datos inválidos para agregar facultad.");                    
                return false;
            }
        
            //personaBin.agregarPersonaBin(persona);
            return facultadDao.agregar(facultad);
        }
        
        System.out.println("Persona no existe");
        return false;
                           
           
    }
    
    public Facultad consultarFacultad(int id) {
        if (id <= 0) {
            System.err.println("❌ ID inválido para consulta.");
            return null;
        }
        return facultadDao.consultar(id);
    }
     public boolean actualizarFacultad(Integer id, String nombre, Integer idDecano) {
         
         
            Persona personaDecano = personaDao.consultar(idDecano);
            
            if(personaDecano != null){
                
                Facultad facultad = new Facultad();
                facultad.setId(id);
                facultad.setNombre(nombre);
                facultad.setDecano(personaDecano);

                if (facultad == null || facultad.getId() <= 0) {
                    System.err.println("❌ Datos inválidos para actualizar persona.");
                    return false;
                }
                
                //personaBin.actualizarPersonaBin(persona);       
                return facultadDao.actualizar(facultad);
                
            }else{
                System.out.println("El decano no existe");
                return false; 
            }
                             
    }
     
     public boolean eliminarFacultad(int id) {
        if (id <= 0) {
            System.err.println("❌ ID inválido para eliminación.");
            return false;
        }
        Facultad facultad = facultadDao.consultar(id);
        //personaBin.eliminarPersonaBin(persona);
        return facultadDao.eliminar(facultad.getId());
    }
     public List<Facultad> listarFacultad() {
        return facultadDao.listar();

    }

    public void mostrarFacultadConProgramas() {

        facultadDao.obtenerFacultadesConProgramas();
    }
    
    
}
