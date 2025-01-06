package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.categoria;
import com.example.demo.entity.producto;

public interface ProductoService {
    public abstract List<producto> ListarProducto();

    public abstract producto buscarProducto(int id);

    public abstract producto guardarProducto(producto producto);

    public abstract producto editarProducto(producto producto);

    public abstract void eliminarProducto(int id);

    public abstract List<categoria> ListarCategoria(boolean estado);
}
