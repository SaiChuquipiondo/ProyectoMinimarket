package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.categoria;

public interface CategoriaService {

	public abstract List<categoria> ListarCategoria();

	public abstract void agregarCategoria(categoria categoria);

	public abstract categoria buscarCategoria(int id);

	public abstract void editarCategoria(categoria categoria);

	public abstract void eliminarCategoria(int id);

	public abstract void actualizarEstadoCategoria(int id, boolean estado);
}
