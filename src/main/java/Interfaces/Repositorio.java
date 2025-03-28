package Interfaces;

import java.util.List;

public interface Repositorio<T> {
    boolean agregar(T entidad);
    T consultar(Integer id);
    boolean actualizar(T entidad);
    boolean eliminar(Integer id);
    List<T> listar();
}
