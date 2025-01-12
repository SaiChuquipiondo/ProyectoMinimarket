package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TipoUsuario;
import com.example.demo.repository.TipoUsuarioRepository;
import com.example.demo.service.TipoUsuarioService;

@Service("tipoUsuarioService")
public class TipoUsuarioServiceImpl implements TipoUsuarioService {
    @Autowired
    @Qualifier("tipoUsuarioRepository")
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public List<TipoUsuario> listarTipos() {
        return tipoUsuarioRepository.findAll();
    }

    @Override
    public TipoUsuario buscarTipo(int id) {
        return tipoUsuarioRepository.findById(id).get();
    }

    @Override
    public void insertarTipo(TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.save(tipoUsuario);
    }

    @Override
    public void actualizarTipo(TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.save(tipoUsuario);
    }

}
