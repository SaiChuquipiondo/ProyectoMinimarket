package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Persona;

import com.example.demo.entity.TipoPersona;
import com.example.demo.entity.Venta;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Persona, Integer> {

    List<Persona> findByTipo(TipoPersona tipo);

    @Query(value = "SELECT v.* FROM venta v inner join persona p on v.id_cliente = p.id where p.id = :id", nativeQuery = true)
    List<Venta> listarVentasCliente(@Param("id") int id);

    @Query(value = "select * from persona p where (:dniCliente = '' or p.dni like concat('%', :dniCliente, '%'))", nativeQuery = true)
    List<Persona> findByDniCliente(@Param("dniCliente") String dniCliente);

}
