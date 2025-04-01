package Servicio;

import DataAccessObject.CursoDao;
import DataAccessObject.ProgramaDao;
import modelos.Curso;
import modelos.Programa;
import java.util.List;
import Fabrica.CursoFabrica;

public class CursoServicio {

    private final CursoDao cursoDao;
    private final ProgramaDao programaDao;
    private final CursoFabrica cursoFabrica;

    public CursoServicio() {
        this.cursoDao = new CursoDao();
        this.programaDao = new ProgramaDao();
        this.cursoFabrica = new CursoFabrica();
    }

    public boolean crearCurso(Integer id, String nombre, Integer idPrograma, Boolean activo) {
        
        Programa programa = programaDao.consultarPrograma(idPrograma);
        
        if(programa == null){
            System.out.println("Programa no existe");
            return false;
        }
        Curso curso = cursoFabrica.crearCurso(id, nombre, programa, activo);
        //Curso curso = new Curso(id, nombre, programa, activo);
        return cursoDao.crearCurso(curso);
    }

    public Curso consultarCurso(Integer id) {
        if (id <= 0) {
            System.err.println("ID inválido para consulta.");
            return null;
        }
        return cursoDao.consultarCurso(id);
    }

    public boolean actualizarCurso(Integer id, String nombre, Integer idPrograma, Boolean activo) {
        
        Programa programa = programaDao.consultarPrograma(idPrograma);
        
        if(programa == null){
            System.out.println("Programa no existe");
            return false;
        }
        
        Curso curso = new Curso(id, nombre, programa, activo);
        return cursoDao.actualizarCurso(curso);
    }

    public boolean eliminarCurso(Integer id) {
        if (id <= 0) {
            System.err.println("ID inválido para eliminar curso.");
            return false;
        }
        return cursoDao.eliminarCurso(id);
    }

    public List<Curso> listarCursos() {
        return cursoDao.listarCursos();
    }
}
