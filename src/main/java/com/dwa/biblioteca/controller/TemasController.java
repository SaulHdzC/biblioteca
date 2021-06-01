package com.dwa.biblioteca.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dwa.biblioteca.modelos.Libros;
import com.dwa.biblioteca.modelos.Usuario;

import com.dwa.biblioteca.servicios.ServicioTema;
import com.dwa.biblioteca.servicios.ServicioUsuario;

@Controller
public class TemasController {
	
	@Autowired
	ServicioUsuario servicioUsuario;
	@Autowired
	ServicioTema servicioTema;
	
	
	@GetMapping("/libros/listar")
	public String listar(Model modelo) {
		Iterable<Libros> temas = servicioTema.verTodos();
		if(temas != null && temas.iterator().hasNext()) {
			modelo.addAttribute("temas", temas);
		}
		else {
			modelo.addAttribute("error", "Aún no hay temas registrados...");
		}
		modelo.addAttribute("tema", new Libros());
		if(!servicioTema.getMensaje().equals("")) {
			modelo.addAttribute("error", servicioTema.getMensaje());
		}
		return "libros/listar";
	}
	
	@PostMapping("/libros/agregar")
	public String agregar(Model modelo, @ModelAttribute Libros tema, HttpSession sesion) {
		int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
		Usuario autor = servicioUsuario.buscar(iduser);
		if(autor == null || autor.getId() == 0)
			modelo.addAttribute("error", servicioUsuario.getMensaje());
		else {
			tema.setUsuario(autor);
			if(!servicioTema.agregar(tema));
				modelo.addAttribute("error", servicioTema.getMensaje());
		}
		return "redirect:/libros/listar";
	}
	
}