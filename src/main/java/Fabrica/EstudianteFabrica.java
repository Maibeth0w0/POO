package Fabrica;

import modelos.Estudiante;

public class EstudianteFabrica {
    public Estudiante crearEstudiante(Integer codigo, Programa programa, Boolean activo, Double promedio, Integer id, String nombres, String apellidos, String email) {
        return new Estudiante(codigo, programa, activo, promedio, id, nombres, apellidos, email);
    }
}