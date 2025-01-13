package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPersona;

@Repository("personalRepository")
public interface PersonalRepository extends JpaRepository<Persona, Integer> {

    List<Persona> findByTipo(TipoPersona tipo);
}
