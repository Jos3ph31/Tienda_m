package com.tienda_m.service;

import com.tienda_m.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    //Se define la firma del método para obtener los registro de categoria, en forma de lista
    public List<Categoria>getCategorias(boolean activo);
    
    //Se recupers un registro tomando el idCategoria como clave de busqueda
    public Categoria getCategoria(Categoria categoria);
    
    //Si idCategori tiene un valor, se modifica ese registro
    //Si idCategori NO tiene valor, se inserta un registro
    
    public void save (Categoria categoria);
    
    //Se elimina el registro que tenga el valor del idCategoria pasado
    public void delete (Categoria categoria);
    
}