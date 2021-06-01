package com.dwa.biblioteca.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwa.biblioteca.modelos.Libros;

@Service
public class ServicioTema {
	
	@Autowired
	private RepoTemas repoTema;
	
	private String Mensaje;
	
	public String getMensaje() {
		return Mensaje;
	}
	
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	
	public boolean agregar(Libros tema) {
		try {
			repoTema.save(tema);
			this.Mensaje = "Tema registrado con éxito.";
			return true;
		}
		catch (Exception e) {
			this.Mensaje = "Erros al registrar el tema.";
			return false;
		}
	}
	
	public Iterable<Libros> verTodos(){
		try	{
			this.Mensaje = "";
			return repoTema.findAll();
		}
		catch (Exception e) {
			this.Mensaje = e.getMessage();
			return null;
		}
	}
	
	public Libros buscar(int id) {
		try {
			this.Mensaje = "";
			Libros tema = repoTema.findById(id).get();
			return tema == null ? new Libros() : tema;
		}
		catch (Exception e) {
			this.Mensaje = e.getMessage();
			return null;
		}
	}
}
