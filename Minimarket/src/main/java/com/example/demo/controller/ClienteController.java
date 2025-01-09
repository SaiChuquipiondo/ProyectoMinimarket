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

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoPersona;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteService")
    private ClienteService clienteService;

    @GetMapping("/Listar")
    public String Listar(Model model) {
        List<Persona> listarClientes = clienteService.findByTipo(TipoPersona.CLIENTE);
        model.addAttribute("cliente", listarClientes);
        return "Clientes/ListarClientes";
    }

    @GetMapping("/Agregar")
    public ModelAndView agregarCliente() {
        ModelAndView mav = new ModelAndView("Clientes/AgregarCliente");
        mav.addObject("cliente", new Persona());
        return mav;
    }

    @PostMapping("/Guardar")
    public String Guardar(@ModelAttribute("cliente") Persona persona) {
        persona.setTipo(TipoPersona.CLIENTE);
        clienteService.guardarCliente(persona);
        return "redirect:/Cliente/Listar";
    }

    @GetMapping("/Editar/{id}")
    public ModelAndView EditarCliente(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("Clientes/EditarCliente");
        mav.addObject("cliente", clienteService.buscarCliente(id));
        return mav;
    }

    @PostMapping("/Editar/{id}")
    public String editarCliente(@PathVariable("id") int id, @ModelAttribute("cliente") Persona persona) {
        persona.setIdPersona(id);
        persona.setTipo(TipoPersona.CLIENTE);
        clienteService.actualizarCliente(persona);
        return "redirect:/Cliente/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") int id) {
        clienteService.eliminarCliente(id);
        return "redirect:/Cliente/Listar";
    }
}
