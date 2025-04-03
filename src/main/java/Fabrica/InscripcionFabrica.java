/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fabrica;

import modelos.Curso;
import modelos.Estudiante;
import modelos.Inscripcion;

/**
 *
 * @author maibe
 */
public class InscripcionFabrica {
    
    public Inscripcion crearInscripcion(Integer idInscripcion,Curso curso, Integer anno, Integer semestre, Estudiante estudiante){
    
        return new Inscripcion(idInscripcion, curso, anno, semestre, estudiante);
    }
    
}
