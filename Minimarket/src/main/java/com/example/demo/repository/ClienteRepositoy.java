package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;

@Repository("clienteRepositoy")
public interface ClienteRepositoy extends JpaRepository<Persona, Integer> {

}
