package Servicio;

import DataAccessObject.CursoDao;
import DataAccessObject.ProgramaDao;
import modelos.Curso;
import modelos.Programa;
import java.util.List;

public class CursoServicio {

    private final CursoDao cursoDao;
    private final ProgramaDao programaDao;

    public CursoServicio() {
        this.cursoDao = new CursoDao();
        this.programaDao = new ProgramaDao();
    }

    public boolean crearCurso(Integer id, String nombre, Integer idPrograma, Boolean activo) {
        
        Programa programa = programaDao.consultarPrograma(idPrograma);
        
        if(programa == null){
            System.out.println("Programa no existe");
            return false;
        }
        
        Curso curso = new Curso(id, nombre, programa, activo);
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
