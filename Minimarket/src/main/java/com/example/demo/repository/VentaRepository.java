package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Venta;

@Repository("ventaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findByFechaRegistroBetween(Date fechaInicio, Date fechaFin);
    
    

}
