package com.krakdev.peliculas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	private final ServicioPelicula servicio;

	public PeliculaController(ServicioPelicula servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Pelicula pelicula) {
		try {
			Pelicula creada = servicio.crear(pelicula);
			return ResponseEntity.status(HttpStatus.CREATED).body(creada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear pelicula");
		}
	}

	@GetMapping
	public ResponseEntity<?> listar() {
		try {
			List<Pelicula> peliculas = servicio.listar();
			return ResponseEntity.ok(peliculas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar peliculas");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Pelicula pelicula = servicio.buscarPorId(id);
			if (pelicula == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula con id " + id + " no encontrada");
			}
			return ResponseEntity.ok(pelicula);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar pelicula");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pelicula peliculaActualizada) {
		try {
			Pelicula pelicula = servicio.actualizar(id, peliculaActualizada);

			if (pelicula == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula con id " + id + " no encontrada");
			}
			return ResponseEntity.ok(pelicula);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar pelicula");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		try {
			boolean eliminado = servicio.eliminar(id);
			if (!eliminado) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula con id " + id + " no encontrada");
			}
			return ResponseEntity.ok("Pelicula eliminada exitosamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar pelicula");
		}
	}

	// SEARCH BY GENERO
	@GetMapping("/genero")
	public ResponseEntity<?> buscarPorGenero(@RequestParam String genero) {
		try {
			List<Pelicula> peliculas = servicio.buscarPorGenero(genero);
			return ResponseEntity.ok(peliculas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar por genero");
		}
	}

	// SEARCH BY DISPONIBLE
	@GetMapping("/disponible")
	public ResponseEntity<?> buscarPorDisponible(@RequestParam boolean disponible) {
		try {
			List<Pelicula> peliculas = servicio.buscarPorDisponible(disponible);
			return ResponseEntity.ok(peliculas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar por disponibilidad");
		}
	}
}