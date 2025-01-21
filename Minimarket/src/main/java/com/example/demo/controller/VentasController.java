package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.*;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VentaService;

@Controller
@RequestMapping("/Ventas")
public class VentasController {

	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;

	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	@Autowired
	@Qualifier("ventaService")
	private VentaService ventaService;

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@GetMapping("/Listar")
	public String ver(@RequestParam(name = "categoriaId", defaultValue = "0") int categoriaId,
			@RequestParam(name = "nombreProducto", defaultValue = "") String nombreProducto, Model model) {
		// Obtener la lista de categorías activas
		List<Categoria> listarCategorias = categoriaService.ListarCategoria();
		String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		// Filtrar productos según la categoría seleccionada y el nombre del producto
		List<Producto> listarProductos;
		if (categoriaId == 0 && nombreProducto.isEmpty()) {
			// Si no hay filtro, traer todos los productos
			listarProductos = productoService.ListarProducto();
		} else if (categoriaId == 0) {
			// Si no hay filtro de categoría, buscar por nombre
			listarProductos = productoService.findByNombreProducto(nombreProducto);
		} else if (nombreProducto.isEmpty()) {
			// Si no hay filtro de nombre, buscar solo por categoría
			listarProductos = productoService.findByCategoriaId(categoriaId);
		} else {
			// Si hay filtros tanto para categoría como para nombre
			listarProductos = productoService.findByCategoriaAndNombre(categoriaId, nombreProducto);
		}
		model.addAttribute("producto", listarProductos);
		model.addAttribute("categoria", listarCategorias);
		model.addAttribute("categoriaId", categoriaId);
		model.addAttribute("nombreProducto", nombreProducto);
		model.addAttribute("today", today);
		return "Admin/Ventas/ListarVentas";
	}

	@GetMapping("/BuscarClientePorDni")
	@ResponseBody
	public ResponseEntity<Persona> buscarClientePorDni(@RequestParam("dni") String dni) {
		List<Persona> clientes = clienteService.buscarDniCliente(dni);
		if (clientes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(clientes.get(0)); // Retorna el primer cliente encontrado
	}

	@PostMapping("/Guardar")
	@ResponseBody
	public ResponseEntity<String> guardarVenta(@RequestBody Venta venta) {
		try {
			// Validar usuario
			usuario usuario = usuarioService.obtenerUsuario(venta.getUsuario().getIdUsuario());
			if (usuario == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado.");
			}

			// Validar cliente
			Persona cliente = clienteService.buscarCliente(venta.getPersona().getIdPersona());
			if (cliente == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no encontrado.");
			}

			// Asignar las entidades relacionadas
			venta.setUsuario(usuario);
			venta.setPersona(cliente);
			venta.setEstado(true); // Asignar estado activo por defecto

			// Guardar la venta
			ventaService.guardarVenta(venta);

			// Retornar el ID de la venta para usarlo en los detalles
			return ResponseEntity.ok("" + venta.getIdVenta());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la venta.");
		}
	}
}