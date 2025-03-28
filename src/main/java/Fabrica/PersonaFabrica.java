package Fabrica;

import modelos.Persona;

public class PersonaFabrica {
    public Persona crearPersona(Integer Id, String nombres, String apellidos, String email) {
        return new Persona(Id, nombres, apellidos,email);

    }
}
