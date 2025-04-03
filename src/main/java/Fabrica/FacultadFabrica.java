package Fabrica;

import modelos.Persona;
import modelos.Facultad;

public class FacultadFabrica {
    public Facultad crearFacultad(Integer id,  String nombre, Persona decano) {
        return new Facultad(id, nombre, decano);
    }
}
