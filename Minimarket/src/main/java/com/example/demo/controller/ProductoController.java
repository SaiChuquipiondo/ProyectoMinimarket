package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        productoService.editarProducto(producto);
        return "redirect:/Producto/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id) {
        productoService.eliminarProducto(id);
        return "redirect:/Producto/Listar";
    }

    @GetMapping("/ControldeStock")
    public String ControldeStock(Model model) {
        List<Producto> listarProductos = productoService.buscarProductoStock(0, 10);
        model.addAttribute("producto", listarProductos);
        return "Admin/Producto/ControldeStock";
    }

}
