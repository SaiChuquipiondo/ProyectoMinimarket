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
	public boolean actualizarStock(int idProducto,int cantidadVendida) {
		// Verificar si el producto existe antes de intentar actualizar el stock
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        if (producto == null) {
            return false; // Producto no encontrado
        }

        // Verificar si hay suficiente stock
        if (producto.getStock() >= cantidadVendida) {
            // Calcular el nuevo stock
            int nuevoStock = producto.getStock() - cantidadVendida;
            
            // Actualizar el stock utilizando la consulta nativa
            productoRepository.actualizarStock(idProducto, String.valueOf(nuevoStock));
            return true; // Stock actualizado correctamente
        } else {
            return false; // Stock insuficiente
        }
    }
	
    
  
}
