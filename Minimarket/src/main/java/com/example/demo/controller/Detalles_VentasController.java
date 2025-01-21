package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Detalle_Venta;

import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;

import com.example.demo.service.Detalle_VentaService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.VentaService;

@Controller
@RequestMapping("/Detalle_Ventas")
public class Detalles_VentasController {

	@Autowired
	@Qualifier("ventaService")
	private VentaService ventaService;

	@Autowired
	@Qualifier("detalleventaService")
	private Detalle_VentaService detalleventaService;

	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;

	@PostMapping("/Guardar")
	@ResponseBody
	public ResponseEntity<String> guardarDetalleVenta(@RequestBody Detalle_Venta detalleventa) {
		try {
			// Asegurarse de que el idVenta no sea null
			if (detalleventa.getVenta() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El idVenta no puede ser nulo.");
			}

			// Validar que la venta existe
			Venta venta = ventaService.buscarVenta(detalleventa.getVenta().getIdVenta());
			if (venta == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venta no encontrada.");
			}

			// Validar producto
			Producto producto = productoService.buscarProducto(detalleventa.getProducto().getIdProducto());
			if (producto == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no encontrado.");
			}

			// Asignar el detalle de venta
			detalleventa.setCantProd(detalleventa.getCantProd());
			detalleventa.setVenta(venta);
			detalleventa.setProducto(producto);
			detalleventa.setImporte(producto.getPrecio() * detalleventa.getCantProd());
			detalleventa.setEstado(true); // Establecer el estado como TRUE

			// Guardar el detalle de venta
			detalleventaService.guardarDetalle_Venta(detalleventa);

			return ResponseEntity.ok("Detalle de venta guardado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el detalle venta.");
		}
	}
}
