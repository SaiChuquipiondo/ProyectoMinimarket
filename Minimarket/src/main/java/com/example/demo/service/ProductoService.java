package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;

public interface ProductoService {
    public abstract List<Producto> ListarProducto();

    public abstract Producto buscarProducto(int id);

    public abstract Producto guardarProducto(Producto producto);

    public abstract Producto editarProducto(Producto producto);

    public abstract void eliminarProducto(int id);

    public abstract List<Categoria> ListarCategoria(boolean estado);

    public abstract List<Producto> buscarProductoStock(int stock1, int stock2);

    
    public List<Producto> findByNombreProducto(String nombreProducto);

    public List<Producto> findByCategoriaId(int categoriaId) ;

    public List<Producto> findByCategoriaAndNombre(int categoriaId, String nombreProducto) ;
    
    public abstract Producto actualizarStock(int idProducto, int stock);
    
    
}
