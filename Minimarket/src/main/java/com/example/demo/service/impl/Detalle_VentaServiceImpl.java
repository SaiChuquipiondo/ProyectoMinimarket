package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Detalle_Venta;

import com.example.demo.repository.Detalle_VentaRepository;

import com.example.demo.service.Detalle_VentaService;

@Service("detalleventaService")
public class Detalle_VentaServiceImpl implements Detalle_VentaService {

	@Autowired
	@Qualifier("detalleventaRepository")
	private Detalle_VentaRepository detalleventaRepository;

	@Override
	public Detalle_Venta guardarDetalle_Venta(Detalle_Venta detalleventa) {
		return detalleventaRepository.save(detalleventa);
	}

	@Override
	public List<Detalle_Venta> ListarDetalle() {
		return detalleventaRepository.findAll();
	}

}
