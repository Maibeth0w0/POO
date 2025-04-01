package Fabrica;

import modelos.Curso;
import modelos.Programa;

public class CursoFabrica {
    public Curso crearCurso(Integer id, String nombre, Programa programa, Boolean activo) {
        return new Curso(id, nombre, programa, activo);

    }
}