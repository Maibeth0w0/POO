package modelos;

public class Profesor extends Persona {

    private Integer id; // Clave primaria de Profesor (profesor_id)
    private String tipoContrato;

    public Profesor() {
    }

    // Constructor: 'id' es el id propio del profesor y 'idPersona' es el id para la tabla Persona.
    public Profesor(Integer id, String tipoContrato, Integer idPersona, String nombres, String apellidos, String email) {
        super(idPersona, nombres, apellidos, email);  // Se almacena en la parte Persona
        this.id = id;                                // id propio de Profesor
        this.tipoContrato = tipoContrato;
    }

    // Devuelve el id propio del profesor (clave primaria de la tabla Profesor)
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    // Método adicional para obtener el id de la persona (clave foránea)
    public Integer getIdPersona() {
        // Se asume que la clase Persona tiene un método getId() que devuelve su id.
        // Como Profesor extiende Persona y oculta el campo id, este método nos permitirá obtener el id
        // de la parte Persona.
        return super.getId();
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "profesor_id=" + id +
                ", persona_id=" + getIdPersona() +
                ", tipoContrato=" + tipoContrato +
                ", nombres=" + getNombres() +
                ", apellidos=" + getApellidos() +
                ", email=" + getEmail() +
                '}';
    }
}
