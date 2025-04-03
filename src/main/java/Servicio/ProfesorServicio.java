package Servicio;

import DataAccessObject.PersonaDao;
import DataAccessObject.ProfesorDao;
import modelos.Profesor;
import modelos.Persona;
import java.util.List;
import Fabrica.ProfesorFabrica;

public class ProfesorServicio {

    private final ProfesorDao profesorDao;
    private final PersonaDao personaDao;
    private final ProfesorFabrica profesorFabrica;

    public ProfesorServicio() {
        this.personaDao = new PersonaDao();
        this.profesorDao = new ProfesorDao();
        this.profesorFabrica = new ProfesorFabrica();
    }

    public boolean agregarProfesor(Integer id, Integer idPersona, String tipoContrato) {
        Persona persona = personaDao.consultar(idPersona);

        if(persona == null){
            System.out.println("Persona no encontrada");
            return false;
        }

        Profesor profesor = profesorFabrica.crearProfesor(id, tipoContrato, persona.getId(), persona.getNombres(), persona.getApellidos(), persona.getEmail());
        return profesorDao.agregarProfesor(profesor);
    }

    public Profesor consultarProfesor(Integer id) {
        return profesorDao.consultarProfesor(id);
    }

    public boolean actualizarProfesor(Integer id, Integer idPersona, String tipoContrato) {

        Persona persona = personaDao.consultar(idPersona);

        if(persona == null){
            System.out.println("Persona no encontrada");
            return false;
        }

        Profesor profesor = profesorFabrica.crearProfesor( id, tipoContrato, persona.getId(), persona.getNombres(), persona.getApellidos(), persona.getEmail());
        return profesorDao.actualizarProfesor(profesor);

    }

    public boolean eliminarProfesor(Integer id) {
        return profesorDao.eliminarProfesor(id);
    }

    public List<Profesor> listarProfesores() {
        return profesorDao.listarProfesores();
    }
}
