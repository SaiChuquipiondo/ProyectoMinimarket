package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	@Qualifier("categoriaRepository")
	private CategoriaRepository categoriaRepository;

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productorRepository;

	@Override
	public List<Categoria> ListarCategoria() {
		return categoriaRepository.listar();
	}

	@Override
	public void agregarCategoria(Categoria categoria) {
		categoriaRepository.insertarCategoria(categoria.getNombre(), categoria.getEstado());
	}

	@Override
	public Categoria buscarCategoria(int id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	public void editarCategoria(Categoria categoria) {
		categoriaRepository.editarCategoria(categoria.getIdCategoria(), categoria.getNombre(), categoria.getEstado());
	}

	@Override
	public void eliminarCategoria(int id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	public void actualizarEstadoCategoria(int id, boolean estado) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
		categoria.setEstado(estado);
		categoriaRepository.save(categoria);

		List<Producto> productos = productorRepository.findByCategoriaId(id);
		productos.forEach(producto -> {
			producto.setEstado(estado);
			productorRepository.save(producto);
		});

	}

}
