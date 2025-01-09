package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPersona;

public interface ClienteService {

    public abstract List<Persona> findByTipo(TipoPersona tipo);

    public abstract void guardarCliente(Persona persona);

    public abstract void actualizarCliente(Persona persona);

    public abstract Persona buscarCliente(int id);

    public abstract void eliminarCliente(int id);

}
