package Fabrica;

import modelos.Facultad;
import modelos.Programa;

public class ProgramaFabrica {
    
    public Programa crearprograma(Integer id, String nombre, Double duracion, String registro, Facultad facultad){
    
        return new Programa(id, nombre, duracion, registro, facultad);
    }    
}
