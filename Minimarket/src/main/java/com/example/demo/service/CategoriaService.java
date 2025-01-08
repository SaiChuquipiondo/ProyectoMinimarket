package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Categoria;

public interface CategoriaService {

	public abstract List<Categoria> ListarCategoria();

	public abstract void agregarCategoria(Categoria categoria);

	public abstract Categoria buscarCategoria(int id);

	public abstract void editarCategoria(Categoria categoria);

	public abstract void eliminarCategoria(int id);

	public abstract void actualizarEstadoCategoria(int id, boolean estado);
}
