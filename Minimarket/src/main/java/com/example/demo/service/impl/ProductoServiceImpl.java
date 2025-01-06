package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.categoria;
import com.example.demo.entity.producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    @Qualifier("productoRepository")
    private ProductoRepository productoRepository;

    @Override
    public List<producto> ListarProducto() {
        return productoRepository.findAll();
    }

    @Override
    public producto buscarProducto(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public producto guardarProducto(producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public producto editarProducto(producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<categoria> ListarCategoria(boolean estado) {
        return productoRepository.ListarActivo(estado);
    }

}
