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

	@GetMapping({ "/Listar" })
	public String ver(@RequestParam(name = "id", defaultValue = "0") int categoriaId, Model model) {
		// Obtener todas las categorías
        List<Categoria> listarCategorias = categoriaService.ListarCategoria();
        
        // Obtener la fecha actual
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Obtener productos, filtrados por categoría si se selecciona una
        List<Producto> listarProductos;
        if (categoriaId == 0) {
            // Si se selecciona "todas las categorías", mostrar todos los productos
            listarProductos = productoService.ListarProducto();
        } else {
            // Si se selecciona una categoría, filtrar los productos por categoría
            Categoria categoriaSeleccionada = categoriaService.buscarCategoriaPorId(categoriaId);
            listarProductos = productoService.ListarProductosPorCategoria(categoriaSeleccionada);
        }

        // Agregar los atributos al modelo
        model.addAttribute("producto", listarProductos);
        model.addAttribute("categoria", listarCategorias);
        model.addAttribute("today", today);

        return "Admin/Ventas/ListarVentas";
	}
}