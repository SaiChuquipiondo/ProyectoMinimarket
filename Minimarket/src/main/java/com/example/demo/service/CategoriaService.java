package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Categoria;

public interface CategoriaService {

    public abstract List<Categoria> listarCategorias();

    public abstract Categoria buscarCategoria(int idCategoria);

    public abstract Categoria guardarCategoria(Categoria categoria);

    public abstract Categoria actualizarCategoria(Categoria categoria);

    public abstract void eliminarCategoria(int idCategoria);
}
