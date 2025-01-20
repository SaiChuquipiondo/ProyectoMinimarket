package com.example.demo.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.AESPasswordEncoder;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usuario user = usuarioRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Usuario no encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(user.getUsername(), user.getPassword(), mapearAutoridadesRoles(user.getTipoUsuario()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(TipoUsuario rol) {
        return Collections.singleton(new SimpleGrantedAuthority(rol.getNombre()));
    }

    @Override
    public boolean authenticateUser(String username, String rawPassword) {
        usuario user = usuarioRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Usuario falido");
            return false;
        }
        return new AESPasswordEncoder().matches(rawPassword, user.getPassword());
    }

}
