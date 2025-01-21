package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/Producto")
public class ProductoController {

	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;

	@GetMapping("/Listar")
	public String ListarProducto(Model model) {
		List<Producto> listarProductos = productoService.ListarProducto();
		model.addAttribute("producto", listarProductos);
		return "Admin/Producto/ListarProductos";
	}

	@GetMapping("/Agregar")
	public ModelAndView AgregarProducto() {
		ModelAndView mav = new ModelAndView("Admin/Producto/AgregarProducto");
		mav.addObject("producto", new Producto());
		mav.addObject("categorias", productoService.ListarCategoria(true));
		return mav;
	}

	@PostMapping("/Guardar")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		producto.setEstado(true);
		productoService.guardarProducto(producto);
		return "redirect:/Producto/Listar";
	}

	@GetMapping("/Editar/{id}")
	public ModelAndView EditarProducto(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("Admin/Producto/EditarProducto");
		Producto producto = productoService.buscarProducto(id);
		mav.addObject("producto", producto);
		mav.addObject("categorias", productoService.ListarCategoria(true));
		return mav;
	}

	@PostMapping("/Editar/{id}")
	public String editarProducto(@PathVariable("id") int id, @ModelAttribute("producto") Producto producto) {
		producto.setIdProducto(id);
		producto.setEstado(true);
		productoService.editarProducto(producto);
		return "redirect:/Producto/Listar";
	}

	@GetMapping("/Eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") int id) {
		Producto producto = productoService.buscarProducto(id);
		if (producto != null) {
			producto.setEstado(false);
			productoService.editarProducto(producto);
		}
		return "redirect:/Producto/Listar";
	}

	@GetMapping("/ControldeStock")
	public String ControldeStock(Model model) {
		List<Producto> listarProductos = productoService.buscarProductoStock(0, 10);
		model.addAttribute("producto", listarProductos);
		return "Admin/Producto/ControldeStock";
	}

	// Método para obtener el stock actual de un producto
	@GetMapping("/ObtenerStock")
	@ResponseBody
	public ResponseEntity<?> obtenerStock(@RequestParam("idProducto") int idProducto) {
		Producto producto = productoService.buscarProducto(idProducto);
		if (producto != null) {
			return ResponseEntity.ok(Map.of("stock", producto.getStock()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
		}
	}

	// Método para actualizar el stock de un producto
	@PostMapping("/ActualizarStock")
	@ResponseBody
	public ResponseEntity<?> actualizarStock(@RequestBody Map<String, Object> request) {
		int idProducto = (Integer) request.get("idProducto");
		int stock = (Integer) request.get("stock");

		Producto producto = productoService.buscarProducto(idProducto);
		if (producto != null) {
			producto.setStock(stock);
			productoService.editarProducto(producto); // Suponiendo que este método guarda el producto con el nuevo
														// stock
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
		}
	}

}
