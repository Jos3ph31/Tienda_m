package com.tienda_m.service;

import com.tienda_m.domain.Producto;
import java.util.List;

public interface ProductoService {
    //Se define la firma del método para obtener los registro de producto, en forma de lista
    public List<Producto>getProductos(boolean activo);
    
    //Se recupers un registro tomando el idProducto como clave de busqueda
    public Producto getProducto(Producto producto);
    
    //Si idCategori tiene un valor, se modifica ese registro
    //Si idCategori NO tiene valor, se inserta un registro
    
    public void save (Producto producto);
    
    //Se elimina el registro que tenga el valor del idProducto pasado
    public void delete (Producto producto);
    
}