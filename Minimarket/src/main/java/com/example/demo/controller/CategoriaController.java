package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.categoria;
import com.example.demo.service.CategoriaService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("categoriaService")
	private CategoriaService categoriaService;

	@GetMapping("/Listar")
	public String listarCategoria(Model model) {
		List<categoria> listarCategorias = categoriaService.ListarCategoria();
		model.addAttribute("categoria", listarCategorias);
		return "Admin/Categoria/ListarCategorias";
	}

	@GetMapping("/Agregar")
	public ModelAndView AgregarCategoria() {
		ModelAndView mav = new ModelAndView("Admin/Categoria/AgregarCategoria");
		mav.addObject("categoria", new categoria());
		return mav;
	}

	@PostMapping("/Guardar")
	public String guardarCategoria(@ModelAttribute("categoria") categoria categoria) {
		categoriaService.agregarCategoria(categoria);

		System.out.println("estado: " + categoria.getEstado());
		return "redirect:/Categoria/Listar";
	}

	@GetMapping("/Editar/{id}")
	public ModelAndView EditarCategoria(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("Admin/Categoria/EditarCategoria");
		categoria categoria = categoriaService.buscarCategoria(id);
		mav.addObject("categoria", categoria);
		return mav;
	}

	@PostMapping("/Editar/{id}")
	public String editarCategoria(@PathVariable("id") int id, @ModelAttribute("categoria") categoria categoria) {
		categoria.setIdCategoria(id);
		categoriaService.actualizarEstadoCategoria(id, categoria.getEstado());
		categoriaService.editarCategoria(categoria);
		return "redirect:/Categoria/Listar";
	}

	@GetMapping("/Eliminar/{id}")
	public String eliminarCategoria(@PathVariable("id") int id) {
		categoriaService.eliminarCategoria(id);
		return "redirect:/Categoria/Listar";
	}

}
