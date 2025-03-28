/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import java.util.ArrayList;
import java.util.List;
import modelos.Programa;

/**
 *
 * @author maibe
 */
public class InscribirPrograma {
    private List<Programa> listadoPrograma;
    
    public InscribirPrograma(){
        this.listadoPrograma = new ArrayList<>();
    }
    
    public void inscribirPrograma(Programa programa) {
                   
        if (existePrograma(programa)) {
            System.out.println("Error: La persona con ID " + programa.getId() + " ya está inscrita.");
            return;
        }
        listadoPrograma.add(programa);
       // guardarInformacion();
        System.out.println("✅ Persona inscrita y guardada: " + programa);
    }
    
    private boolean existePrograma(Programa programa) {
        for (Programa program : listadoPrograma) {
            if (program.getId().equals(programa.getId())) {
                return true;
            }
        }
        return false;
    }
    
    public void actualizarPrograma(Programa programaActualizado) {
        for (int i = 0; i < listadoPrograma.size(); i++) {
            Programa programaExistente = listadoPrograma.get(i);
            if (programaExistente.getId().equals(programaActualizado.getId())) {

                if (programaActualizado.getNombre() != null) {
                    programaExistente.setNombre(programaActualizado.getNombre());
                }
                if (programaActualizado.getDuracion() != null) {
                    programaExistente.setDuracion(programaActualizado.getDuracion());
                }
                if (programaActualizado.getRegistro() != null) {
                    programaExistente.setRegistro(programaActualizado.getRegistro());
                }
                if (programaActualizado.getFacultad() != null) {
                    programaExistente.setFacultad(programaActualizado.getFacultad());
                }
    
                //guardarInformacion(); // Guarda los cambios
                System.out.println("Programa actualizada: " + programaExistente.getNombre() + " " + programaExistente.getDuracion()+ " " + programaExistente.getRegistro()+ " " + programaExistente.getFacultad());
                return;
            }
        }
        System.out.println("Programa no encontrado para actualizar.");
    }
    
    public void eliminarProgramaPorId(Integer ID) {
        listadoPrograma.removeIf(programa -> programa.getId().equals(ID));
        //guardarInformacion();
        System.out.println("programa con ID " + ID + " eliminado.");
    }
    public List<Programa> getListadoPrograma() {
        return listadoPrograma;
    }

    public void setListadoPersonas(List<Programa> listadoPersonas) {
        this.listadoPrograma = listadoPrograma;
    }   
    
}
