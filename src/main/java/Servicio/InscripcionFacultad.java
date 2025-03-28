/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import java.util.ArrayList;
import java.util.List;
import modelos.Facultad;

/**
 *
 * @author maibe
 */
public class InscripcionFacultad {
    private List<Facultad> listadoFacultad;

    public InscripcionFacultad() {
        this.listadoFacultad = new ArrayList<>();
    }

    public void inscribirFacultad(Facultad facultad) {

        if (existeFacultad(facultad)) {
            System.out.println("Error: La facultad con ID " + facultad.getId() + " ya está inscrita.");
            return;
        }
        listadoFacultad.add(facultad);
       // guardarInformacion();
        System.out.println("✅ Facultad inscrita y guardada: " + facultad);
    }

    private boolean existeFacultad(Facultad facultad) {
        for (Facultad facul : listadoFacultad) {
            if (facul.getId().equals(facultad.getId())) {
                return true;
            }
        }
        return false;
    }

     public List<Facultad> getListadoFacultad() {
        return listadoFacultad;
    }



}
