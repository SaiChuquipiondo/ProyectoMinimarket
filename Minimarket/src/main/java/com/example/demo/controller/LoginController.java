package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        if (usuarioService.authenticateUser(username, password)) {
            System.out.println("Autenticación exitosa");
            return "redirect:/JyK/Inicio";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "redirect:/login";
    }
}
