package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Persona;
import com.example.demo.entity.TipoUsuario;
import com.example.demo.entity.usuario;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;

    @GetMapping("/Listar")
    public String Listar(Model model) {
        List<usuario> lista = usuarioService.listarUsuarios();
        model.addAttribute("usuario", lista);
        return "Admin/Usuario/ListarUsuarios";
    }

    @GetMapping("/Agregar")
    public ModelAndView Agregar() {
        ModelAndView mav = new ModelAndView("Admin/Usuario/AgregarUsuario");
        List<Persona> Personal = usuarioService.listarPersonal();
        List<TipoUsuario> Tipos = usuarioService.listarTipos();
        mav.addObject("tipos", Tipos);
        mav.addObject("personal", Personal);
        mav.addObject("usuario", new usuario());
        return mav;
    }

    @PostMapping("/Guardar")
    public String Guardar(usuario usuario) {
        usuario.setEstado(true);
        usuarioService.guardarUsuario(usuario);
        return "redirect:/Usuario/Listar";
    }

    @GetMapping("/Editar/{id}")
    public ModelAndView Editar(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("Admin/Usuario/EditarUsuario");
        List<TipoUsuario> Tipos = usuarioService.listarTipos();
        List<Persona> Personal = usuarioService.listarPersonal();
        mav.addObject("personal", Personal);
        mav.addObject("tipos", Tipos);
        mav.addObject("usuario", usuarioService.obtenerUsuario(id));
        return mav;
    }

    @PostMapping("/Editar/{id}")
    public String Editar(@PathVariable("id") int id, usuario usuario) {
        usuario.setIdUsuario(id);
        usuario.setEstado(true);
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/Usuario/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String Eliminar(@PathVariable("id") int id) {
        usuario user = usuarioService.obtenerUsuario(id);
        if (user != null) {
            user.setEstado(false);
            usuarioService.actualizarUsuario(user);
        }

        return "redirect:/Usuario/Listar";
    }
}
