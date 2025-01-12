package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoUsuario;
import com.example.demo.entity.usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    @Override
    public void guardarUsuario(usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public usuario obtenerUsuario(int id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public void actualizarUsuario(usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Persona> listarPersonal() {
        return usuarioRepository.listarPersonal();
    }

    @Override
    public List<TipoUsuario> listarTipos() {
        return usuarioRepository.ListarTipos();
    }

}
