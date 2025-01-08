package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Persona;

public interface PersonalService {

    public abstract List<Persona> listarPersonal();

    public abstract void guardarPersonal(Persona persona);

    public abstract Persona buscarPersonal(int id);

    public abstract void actualizarPersonal(Persona persona);

    public abstract void eliminarPersonal(int id);
}
