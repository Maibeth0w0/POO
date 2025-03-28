package Controller;

import Servicio.InscripcionesPersonas;
import Servicio.PersonaServicio;
import modelos.Persona;
import java.util.List;

/**
 * Controlador para manejar operaciones sobre Persona.
 */
public class ControllerPersona {
    
    private final PersonaServicio personaServicio;
    

    public ControllerPersona() {
        this.personaServicio = new PersonaServicio();
        
    }
    
    public void agragarPersonaLista(Integer id, String nombres, String apellidos, String email){
        personaServicio.agregarPersona(id, nombres, apellidos, email);
    }
    
    public void agregarPersona(Integer id, String nombres, String apellidos, String email) {
       
        personaServicio.agregarPersona(id, nombres, apellidos, email);
    }

    public void consultarPersona(Integer id) {
        personaServicio.consultarPersona(id);
    }

    public void actualizarPersona(Integer id, String nombres, String apellidos, String email) {
        personaServicio.actualizarPersona(id, nombres, apellidos, email);
    }

    public void eliminarPersona(Integer id) {
        personaServicio.eliminarPersona(id);
    }

    public List<Persona> listarPersonasBD() {
        List<Persona> lista = personaServicio.listarPersonas();
        return lista;
    }
    
    public List<Persona>  listarPersonasLista(){
        List<Persona> personas = personaServicio.listarPersonasLista();
        return personas;
    }
}
