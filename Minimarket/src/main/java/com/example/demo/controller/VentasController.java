package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/Ventas")
public class VentasController {

	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;

	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;

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
}