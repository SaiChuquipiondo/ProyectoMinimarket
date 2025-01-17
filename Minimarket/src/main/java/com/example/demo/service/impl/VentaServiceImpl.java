package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Venta;
import com.example.demo.repository.VentaRepository;
import com.example.demo.service.VentaService;

@Service("ventaService")
public class VentaServiceImpl implements VentaService {

    @Autowired
    @Qualifier("ventaRepository")
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin) {
        return ventaRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);
    }

	@Override
	public Venta guardarVenta(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public List<Venta> ListarVenta() {
		return ventaRepository.findAll();
	}

}
