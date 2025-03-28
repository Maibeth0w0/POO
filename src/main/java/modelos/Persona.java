/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;

/**
 *
 * @author maibe
 */
public class Persona implements Serializable{
    private static final long serialVersionUID = 1L;

    Integer Id;
    String nombres;
    String apellidos;
    String email;

    public Persona(Integer Id, String nombres, String apellidos, String email) {
        this.Id = Id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Persona() {
    }
    

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" + "ID=" + Id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", email=" + email + '}';
    }
    
}
