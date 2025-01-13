package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPersona;
import com.example.demo.entity.Venta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service("clienteService")

public class ClienteServiceImpl implements ClienteService {
    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Override
    public List<Persona> findByTipo(TipoPersona tipo) {
        return clienteRepository.findByTipo(tipo);
    }

    @Override
    public void guardarCliente(Persona persona) {
        clienteRepository.save(persona);
    }

    @Override
    public void actualizarCliente(Persona persona) {
        clienteRepository.save(persona);
    }

    @Override
    public Persona buscarCliente(int id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Venta> listarVentasCliente(int id) {
        return clienteRepository.listarVentasCliente(id);
    }

}
