package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoUsuario;
import com.example.demo.entity.usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<usuario, Integer> {

    @Query(value = "Select * from persona where id not in( select id_persona from usuario) and tipo='PERSONAL'", nativeQuery = true)
    List<Persona> listarPersonal();

    @Query(value = "select * from tipo_usuario where estado = 1", nativeQuery = true)
    List<TipoUsuario> ListarTipos();
}
