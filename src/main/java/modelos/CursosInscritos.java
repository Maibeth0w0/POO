package modelos;

import Interfaces.Servicios;

import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios {

    private List<Inscripcion> listadoInscripciones;

    public CursosInscritos(){
        this.listadoInscripciones = new ArrayList<>();
    }

    public void inscribirCurso(Inscripcion inscripcion){
        if (existeInscripcion(inscripcion)) {
            System.out.println("Error: El curso ya está inscrito.");
            return;
        }
        listadoInscripciones.add(inscripcion);
        //guardarInformacion();
        System.out.println("Curso inscrito y guardado: " + inscripcion.getCurso().getNombre());
    }

    private boolean existeInscripcion(Inscripcion inscripcion) {
        for (Inscripcion i : listadoInscripciones) {
            if (i.getCurso().getId().equals(inscripcion.getCurso().getId())) {
                return true;
            }
        }
        return false;
    }

//    public void guardarInformacion() {
//        try {
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//            try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(FILE_PATH), cipher);
//                 ObjectOutputStream oos = new ObjectOutputStream(cos)) {
//
//                oos.writeObject(listadoInscripciones);
//                System.out.println("Datos guardados en archivo binario cifrado.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error al guardar la información: " + e.getMessage());
//        }
//

    public void eliminarInscripcion(Inscripcion inscripcion){
        if (listadoInscripciones.remove(inscripcion)){
            System.out.println("Curso eliminado: " + inscripcion.getCurso().getNombre());
        } else {
            System.out.println("Curso no encontrado en el listado.");
        }
    }

    public void actualizarCurso(Inscripcion cursoActualizado){
        for (int i = 0; i < listadoInscripciones.size(); i++){
            if (listadoInscripciones.get(i).getCurso().getId().equals(cursoActualizado.getCurso().getId())){
                listadoInscripciones.set(i, cursoActualizado);
                System.out.println("Datos actualizados para: " + cursoActualizado.getCurso().getNombre());
                return;
            }
        }
        System.out.println("Curso no encontrado para actualizar.");
    }

//    public void cargarDatos() {
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            System.out.println("No hay datos previos para cargar.");
//            return;
//        }
//
//        try {
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//            try (CipherInputStream cis = new CipherInputStream(new FileInputStream(FILE_PATH), cipher);
//                 ObjectInputStream ois = new ObjectInputStream(cis)) {
//
//                listadoInscripciones = (List<Inscripcion>) ois.readObject();
//                System.out.println("Datos cargados desde archivo binario cifrado.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error al cargar la información: " + e.getMessage());
//        }
//    }


    @Override
    public String imprimirPosicion(Integer posicion) {
        if (posicion >= 0 && posicion < listadoInscripciones.size()) {
            return listadoInscripciones.get(posicion).toString();
        }
        return "Posición fuera de rango.";
    }

    @Override
    public Integer cantidadActual() {
        return listadoInscripciones.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> nombres = new ArrayList<>();
        for (Inscripcion inscripcion : listadoInscripciones) {
            nombres.add(inscripcion.getCurso().getNombre());
        }
        return nombres;
    }

    @Override
    public String toString() {
        return "CursosInscritos{" +
                "listadoInscripciones=" + listadoInscripciones +
                '}';
    }
}
