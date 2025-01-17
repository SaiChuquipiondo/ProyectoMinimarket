package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Detalle_Venta;
import com.example.demo.entity.Venta;

@Repository("detalleventaRepository")
public interface Detalle_VentaRepository extends JpaRepository<Detalle_Venta, Integer> {

    

}