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
import com.example.demo.service.PersonalService;

@Controller
@RequestMapping("/Personal")
public class PersonalController {

    @Autowired
    @Qualifier("personalService")
    private PersonalService personalService;

    @GetMapping("/Listar")
    public String ListarPersonal(Model model) {
        List<Persona> listarPersonal = personalService.listarPersonal();
        model.addAttribute("personal", listarPersonal);
        return "/Admin/Personal/ListarPersonal";
    }

    @GetMapping("/Agregar")
    public ModelAndView AgregarPersonal() {
        ModelAndView mav = new ModelAndView("Admin/Personal/AgregarPersonal");
        mav.addObject("personal", new Persona());
        return mav;
    }

    @PostMapping("/Guardar")
    public String guardarPersonal(@ModelAttribute("personal") Persona persona) {
        persona.setEstado(true);
        persona.setTipo(TipoPersona.PERSONAL);
        personalService.guardarPersonal(persona);
        return "redirect:/Personal/Listar";
    }

    @GetMapping("/Editar/{id}")
    public ModelAndView EditarPersonal(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("Admin/Personal/EditarPersonal");
        Persona persona = personalService.buscarPersonal(id);
        mav.addObject("personal", persona);
        return mav;
    }

    @PostMapping("/Editar/{id}")
    public String editarPersonal(@PathVariable("id") int id, @ModelAttribute("personal") Persona persona) {
        persona.setIdPersona(id);
        persona.setTipo(TipoPersona.PERSONAL);
        persona.setEstado(true);
        personalService.actualizarPersonal(persona);
        return "redirect:/Personal/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminarPersonal(@PathVariable("id") int id) {
        Persona personal = personalService.buscarPersonal(id);
        if (personal != null) {
            personal.setEstado(false);
            personalService.actualizarPersonal(personal);
        }
        return "redirect:/Personal/Listar";
    }
}
