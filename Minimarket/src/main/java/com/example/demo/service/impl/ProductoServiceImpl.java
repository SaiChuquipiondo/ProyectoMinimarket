package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    @Qualifier("productoRepository")
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> ListarProducto() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto buscarProducto(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto editarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Categoria> ListarCategoria(boolean estado) {
        return productoRepository.ListarActivo(estado);
    }

    @Override
    public List<Producto> buscarProductoStock(int stock1, int stock2) {
        return productoRepository.findByStockBetween(stock1, stock2);
    }

    @Override
    public List<Producto> findByNombreProducto(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto); // Buscar por nombre
    }

    @Override
    public List<Producto> findByCategoriaId(int categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId); // Buscar solo por categoría
    }

    @Override
    public List<Producto> findByCategoriaAndNombre(int categoriaId, String nombreProducto) {
        return productoRepository.findByCategoriaIdAndNombre(categoriaId, "%" + nombreProducto + "%"); // Buscar por categoría y nombre
    }

	@Override
	public Producto actualizarStock(int idProducto,int stock) {
		int filasAfectadas = productoRepository.actualizarStock(idProducto, stock);
	    if (filasAfectadas > 0) {
	        // Recuperar el producto actualizado
	        return productoRepository.findById(idProducto).orElse(null);
	    }
	    return null; // O manejar un error según el caso
    }
	
    
  
}
