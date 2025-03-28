package Binarios;

import modelos.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinariosPersona {
    
    private static final String FILE_NAME = "personas.bin";

    public void agregarPersonaBin(Persona persona) {
        List<Persona> personas = new ArrayList<>();

      
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                personas = (List<Persona>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }

       
        personas.add(persona);

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            System.out.println(personas);
            oos.writeObject(personas);
            System.out.println("Persona agregada exitosamente al archivo binario.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void eliminarPersonaBin(Persona persona) {
        List<Persona> personas = new ArrayList<>();
        File file = new File(FILE_NAME);

        // Leer la lista actual de personas
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                personas = (List<Persona>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return;
            }
        }

        // Filtrar la lista para eliminar la persona con el ID coincidente
        boolean removed = personas.removeIf(p -> p.getId().equals(persona.getId()));

        if (!removed) {
            System.out.println("No se encontró la persona con ID: " + persona.getId());
            return;
        }

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(personas);
            System.out.println("Persona eliminada exitosamente del archivo binario.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void actualizarPersonaBin(Persona personaActualizada) {
    List<Persona> personas = new ArrayList<>();
    File file = new File(FILE_NAME);
    boolean updated = false;

    // Leer la lista actual de personas
    if (file.exists()) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            personas = (List<Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
    }

    // Buscar y actualizar la persona con el mismo ID
    for (int i = 0; i < personas.size(); i++) {
        if (personas.get(i).getId().equals(personaActualizada.getId())) {
            personas.set(i, personaActualizada);
            updated = true;
            break;
        }
    }

    if (!updated) {
        System.out.println("No se encontró la persona con ID: " + personaActualizada.getId());
        return;
    }

    // Guardar la lista actualizada en el archivo binario
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(personas);
        System.out.println("Persona actualizada exitosamente en el archivo binario.");
    } catch (IOException e) {
        System.out.println("Error al escribir en el archivo: " + e.getMessage());
    }
}


}
