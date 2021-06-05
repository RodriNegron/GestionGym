package Interfaces;

public interface Controller <E> {
    //funciones de agregar, buscar, eliminar y consultar o recorrer de las colecciones
    void add(E name );
    E findById(int id);
    void delete(int id);  //borrado logico
    void consultList();
}
