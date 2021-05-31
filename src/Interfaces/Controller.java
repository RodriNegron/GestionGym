package Interfaces;

public interface Controller {
    //funciones de agregar, buscar, eliminar y consultar o recorrer de las colecciones
    void add();
    void filterById(int id);
    void delete(Object obj);
    void consultList();

}
