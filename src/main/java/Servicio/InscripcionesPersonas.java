/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import java.util.ArrayList;
import java.util.List;
import modelos.Persona;

/**
 *
 * @author maibe
 */
public class InscripcionesPersonas {
    private List<Persona> listadoPersonas;

    public InscripcionesPersonas() {
        this.listadoPersonas = new ArrayList<>();
    }
    
    public void inscribirPersona(Persona persona) {
                   
        if (existePersona(persona)) {
            System.out.println("Error: La persona con ID " + persona.getId() + " ya está inscrita.");
            return;
        }
        listadoPersonas.add(persona);
       // guardarInformacion();
        System.out.println("✅ Persona inscrita y guardada: " + persona);
    }

    private boolean existePersona(Persona persona) {
        for (Persona person : listadoPersonas) {
            if (person.getId().equals(persona.getId())) {
                return true;
            }
        }
        return false;
    }
    
    public void actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < listadoPersonas.size(); i++) {
            Persona personaExistente = listadoPersonas.get(i);
            if (personaExistente.getId().equals(personaActualizada.getId())) {

                if (personaActualizada.getNombres() != null) {
                    personaExistente.setNombres(personaActualizada.getNombres());
                }
                if (personaActualizada.getApellidos() != null) {
                    personaExistente.setApellidos(personaActualizada.getApellidos());
                }
    
                //guardarInformacion(); // Guarda los cambios
                System.out.println("Persona actualizada: " + personaExistente.getNombres() + " " + personaExistente.getApellidos());
                return;
            }
        }
        System.out.println("Persona no encontrada para actualizar.");
    }
    public void eliminarPersonaPorId(Integer ID) {
        listadoPersonas.removeIf(persona -> persona.getId().equals(ID));
        //guardarInformacion();
        System.out.println("Persona con ID " + ID + " eliminada.");
    }
    public List<Persona> getListadoPersonas() {
        return listadoPersonas;
    }

    public void setListadoPersonas(List<Persona> listadoPersonas) {
        this.listadoPersonas = listadoPersonas;
    }   
    
    
}
