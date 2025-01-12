package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TipoUsuario;

public interface TipoUsuarioService {

    public abstract List<TipoUsuario> listarTipos();

    public abstract TipoUsuario buscarTipo(int id);

    public abstract void insertarTipo(TipoUsuario tipoUsuario);

    public abstract void actualizarTipo(TipoUsuario tipoUsuario);

}
