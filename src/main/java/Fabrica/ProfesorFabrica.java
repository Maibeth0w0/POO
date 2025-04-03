
package Fabrica;

import modelos.Profesor;

public class ProfesorFabrica {
    
    public Profesor crearProfesor(Integer id, String tipoContrato, Integer idPersona, String nombres, String apellidos, String email){
     return new Profesor(id, tipoContrato, idPersona, nombres, apellidos, email);
    }
}
