/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author maibe
 */
public class Inscripcion {
    private Integer idInscripcion;
    private Curso curso;
    private Integer anno;
    private Integer semestre;
    private Estudiante estudiante;

    public Inscripcion() {
    }

    public Inscripcion(Integer idInscripcion,Curso curso, Integer anno, Integer semestre, Estudiante estudiante) {
        this.idInscripcion = idInscripcion;
        this.curso = curso;
        this.anno = anno;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }



    @Override
    public String toString() {
        return "Inscripcion{" + "curso=" + curso + ", anno=" + anno + ", semestre=" + semestre + ", estudiante=" + estudiante + '}';
    }
    
    
}
