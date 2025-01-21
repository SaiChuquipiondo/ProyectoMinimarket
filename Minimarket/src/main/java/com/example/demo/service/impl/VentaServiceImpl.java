package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Detalle_Venta;

import com.example.demo.entity.Venta;
import com.example.demo.repository.Detalle_VentaRepository;
import com.example.demo.repository.VentaRepository;
import com.example.demo.service.VentaService;

@Service("ventaService")
public class VentaServiceImpl implements VentaService {

	@Autowired
	@Qualifier("ventaRepository")
	private VentaRepository ventaRepository;

	@Autowired
	@Qualifier("detalleventaRepository")
	private Detalle_VentaRepository detalleventaRepository;

	@Override
	public List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin) {
		return ventaRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);
	}

	@Override
	public Venta guardarVenta(Venta venta) {
		// Primero, guardamos los Detalles de Venta, ya que necesitan la ID de la Venta
		for (Detalle_Venta detalle : venta.getDetalleVentas()) {
			detalle.setVenta(venta); // Aseguramos que cada detalle tenga la venta asociada
		}

		// Luego, guardamos la Venta (esto establecerá su ID)
		Venta ventaGuardada = ventaRepository.save(venta);

		// Finalmente, guardamos los Detalles de Venta
		detalleventaRepository.saveAll(venta.getDetalleVentas());

		return ventaGuardada; // Devolvemos la venta guardada
	}

	@Override
	public List<Venta> ListarVenta() {
		return ventaRepository.findAll();
	}

	@Override
	public Venta buscarVenta(int id) {
		return ventaRepository.findById(id).orElse(null);
	}

}
