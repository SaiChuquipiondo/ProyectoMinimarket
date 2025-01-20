package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/JyK")
public class AdministradorController {

	@GetMapping({ "/Inicio", "/Home", "/" })
	public String ver() {
		return "index";
	}
}
