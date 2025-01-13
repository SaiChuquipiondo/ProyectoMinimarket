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

import com.example.demo.entity.TipoUsuario;

import com.example.demo.service.TipoUsuarioService;

;

@Controller
@RequestMapping("/Tipo")
public class TipoUsuarioController {
    @Autowired
    @Qualifier("tipoUsuarioService")
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping("/Listar")
    public String Listar(Model model) {
        List<TipoUsuario> lista = tipoUsuarioService.listarTipos();
        model.addAttribute("tipoUsuario", lista);
        return "Admin/TipoUsuario/ListarTipos";
    }

    @GetMapping("/Agregar")
    public ModelAndView Agregar() {
        ModelAndView mav = new ModelAndView("Admin/TipoUsuario/AgregarTipoUsuario");
        mav.addObject("tipoUsuario", new TipoUsuario());
        return mav;
    }

    @PostMapping("/Guardar")
    public String Guardar(@ModelAttribute("tipoUsuario") TipoUsuario tipoUsuario) {
        tipoUsuario.setEstado(true);
        tipoUsuarioService.insertarTipo(tipoUsuario);
        return "redirect:/Tipo/Listar";
    }

    @GetMapping("/Editar/{id}")
    public ModelAndView Editar(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("Admin/TipoUsuario/EditarTipoUsuario");
        mav.addObject("tipoUsuario", tipoUsuarioService.buscarTipo(id));
        return mav;
    }

    @PostMapping("/Editar/{id}")
    public String Editar(@PathVariable("id") int id, @ModelAttribute("tipoUsuario") TipoUsuario tipoUsuario) {
        tipoUsuario.setIdTipoUsuario(id);
        tipoUsuario.setEstado(true);
        tipoUsuarioService.actualizarTipo(tipoUsuario);
        return "redirect:/Tipo/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminarTipo(@PathVariable("id") int id) {
        TipoUsuario tipo = tipoUsuarioService.buscarTipo(id);
        if (tipo != null) {
            tipo.setEstado(false);
            tipoUsuarioService.actualizarTipo(tipo);
        }
        return "redirect:/Tipo/Listar";
    }

}
