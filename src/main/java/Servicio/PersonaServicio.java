package Servicio;

import Binarios.BinariosPersona;
import DataAccessObject.PersonaDao;
import java.util.ArrayList;

import Fabrica.PersonaFabrica;
import modelos.Persona;
import java.util.List;

/**
 *
 * Servicio para manejar la lógica de negocio de Persona.
 */
public class PersonaServicio {
    
    private final PersonaDao personaDao;
    private final InscripcionesPersonas inscribir;
    private final BinariosPersona personaBin;
    private final PersonaFabrica personaFabrica;

    public PersonaServicio() {
        this.personaDao = new PersonaDao();
        this.inscribir = new InscripcionesPersonas();
        this.personaBin = new BinariosPersona();
        this.personaFabrica = new PersonaFabrica();
    }    
        
    public void inscribirPersona(Integer id, String nombres, String apellidos, String email){

        Persona persona = personaFabrica.crearPersona(id, nombres, apellidos, email);
        inscribir.inscribirPersona(persona);
    }

    public List<Persona>  listarPersonasLista(){
        List<Persona> personas = inscribir.getListadoPersonas();
        return personas;
    }
    

    public boolean agregarPersona(Integer id, String nombres, String apellidos, String email) {
        Persona persona = personaFabrica.crearPersona(id, nombres, apellidos, email);
        if (persona == null || persona.getId()==null ||persona.getNombres().isEmpty() || persona.getApellidos().isEmpty() || persona.getEmail().isEmpty()){
            System.err.println("❌ Datos inválidos para agregar persona.");
                      
            return false;
        }
        //personaBin.agregarPersonaBin(persona);

        return personaDao.agregar(persona);
    }

    // Método para consultar una persona por ID
    public Persona consultarPersona(Integer id) {
        if (id <= 0) {
            System.err.println("❌ ID inválido para consulta.");
            return null;
        }
        return personaDao.consultar(id);
    }

    public boolean actualizarPersona(Integer id, String nombres, String apellidos, String email) {

        Persona persona = personaFabrica.crearPersona(id, nombres, apellidos, email);
        if (persona == null || persona.getId() <= 0) {
            System.err.println("❌ Datos inválidos para actualizar persona.");
            return false;
        }
        
        //personaBin.actualizarPersonaBin(persona);
        
        return personaDao.actualizar(persona);
    }

    // Método para eliminar una persona por ID
    public boolean eliminarPersona(Integer id) {
        if (id <= 0) {
            System.err.println("❌ ID inválido para eliminación.");
            return false;
        }
        Persona persona = personaDao.consultar(id);

        //personaBin.eliminarPersonaBin(persona);

        return personaDao.eliminar(id);
    }

    public List<Persona> listarPersonas() {
        return personaDao.listar();
    }
    
    
}
