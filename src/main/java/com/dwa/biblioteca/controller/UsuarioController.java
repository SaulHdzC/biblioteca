package com.dwa.biblioteca.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dwa.biblioteca.modelos.Usuario;
import com.dwa.biblioteca.servicios.ServicioUsuario;

@Controller
public class UsuarioController {
	
	@Autowired
	ServicioUsuario servicioUsuario;
	
	@GetMapping(path = {"/usuario/validar", "/"})
	public String validar() {
		return "usuario/validar";
	}
	
	@PostMapping("/usuario/validar")
	public String agregar(@RequestParam("username") String correoe,
			@RequestParam("password") String clave,
			Model modelo) {
			return "redirect:/libros/listar";
	}
	
	@GetMapping("/usuario/agregar")
	public String agregar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/agregar";
	}
	
	@PostMapping("/usuario/agregar")
	public String agregar(Model modelo, @ModelAttribute Usuario user, 
			@RequestParam("clave") String clave, HttpSession sesion) {
		boolean res = servicioUsuario.agregar(user, clave, sesion);
		if (res) {
			return "redirect:/libros/listar";
		}
		
		modelo.addAttribute("error", servicioUsuario.getMensaje());
		return "usuario/agregar";
	}
}
