package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoUsuario;
import com.example.demo.entity.usuario;

public interface UsuarioService {
    public abstract void guardarUsuario(usuario usuario);

    public abstract List<Persona> listarPersonal();

    public abstract List<TipoUsuario> listarTipos();

    public abstract List<usuario> listarUsuarios();

    public abstract usuario obtenerUsuario(int id);

    public abstract void actualizarUsuario(usuario usuario);

}
