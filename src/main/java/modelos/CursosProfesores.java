package modelos;

import Interfaces.Servicios;

import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios {

    private List<CursoProfesor> listadoCursoProfesores;

    public CursosProfesores(){
        this.listadoCursoProfesores = new ArrayList<>();
    }

    public void inscribirCursoProfesores(CursoProfesor cursoProfesor) {
        listadoCursoProfesores.add(cursoProfesor);
        System.out.println("Curso " + cursoProfesor.getCurso().getNombre() + " asignado exitosamente al profesor " + cursoProfesor.getProfesor().getNombres() + ".");
    }

//    public void guardarInformacion() {
//        try {
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//            try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(FILE_PATH), cipher);
//                 ObjectOutputStream oos = new ObjectOutputStream(cos)) {
//
//                oos.writeObject(listadoCursoProfesores);
//                System.out.println("Datos guardados correctamente en archivo binario cifrado.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error al guardar la información: " + e.getMessage());
//        }
//    }

//    public void cargarDatos() {
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            System.out.println("No hay datos previos guardados.");
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
//                listadoCursoProfesores = (List<CursoProfesor>) ois.readObject();
//                System.out.println("Datos cargados correctamente desde el archivo binario cifrado.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error al cargar la información: " + e.getMessage());
//        }
//    }

    @Override
    public String imprimirPosicion(Integer posicion) {
        if (posicion >= 0 && posicion < listadoCursoProfesores.size()) {
            return listadoCursoProfesores.get(posicion).toString();
        }
        return "Posición fuera de rango.";
    }

    @Override
    public Integer cantidadActual() {
        return listadoCursoProfesores.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> nombres = new ArrayList<>();
        for (CursoProfesor cursoProfesor : listadoCursoProfesores) {
            nombres.add(cursoProfesor.getProfesor().getNombres());
        }
        return nombres;
    }

    @Override
    public String toString() {
        return "CursosProfesores{" +
                "listadoCursoProfesores=" + listadoCursoProfesores +
                '}';
    }
}
