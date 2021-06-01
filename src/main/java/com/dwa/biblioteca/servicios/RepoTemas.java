package com.dwa.biblioteca.servicios;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.dwa.biblioteca.modelos.Libros;

public interface RepoTemas extends CrudRepository<Libros, Integer> {
	@Query(value = "SELECT * FROM libros", nativeQuery = true)
	ArrayList<Libros> todos();
}
