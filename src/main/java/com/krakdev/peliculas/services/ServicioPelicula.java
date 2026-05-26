package com.krakdev.peliculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.repository.PeliculaRepository;

@Service
public class ServicioPelicula {

	private final PeliculaRepository repository;

	public ServicioPelicula(PeliculaRepository repository) {
		this.repository = repository;
	}

	public Pelicula crear(Pelicula pelicula) {
		return repository.save(pelicula);
	}

	public List<Pelicula> listar() {
		return repository.findAll();
	}

	public Pelicula buscarPorId(Long id) {
		Optional<Pelicula> resultado = repository.findById(id);
		return resultado.orElse(null);
	}

	public Pelicula actualizar(Long id, Pelicula peliculaActualizada) {

		Pelicula peliculaConsultada = buscarPorId(id);

		if (peliculaConsultada == null) {
			return null;
		}

		peliculaConsultada.setNombre(peliculaActualizada.getNombre());
		peliculaConsultada.setDirector(peliculaActualizada.getDirector());
		peliculaConsultada.setGenero(peliculaActualizada.getGenero());
		peliculaConsultada.setDuracion(peliculaActualizada.getDuracion());
		peliculaConsultada.setDisponible(peliculaActualizada.isDisponible());

		return repository.save(peliculaConsultada);
	}

	public boolean eliminar(Long id) {

		Pelicula peliculaConsultada = buscarPorId(id);

		if (peliculaConsultada == null) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public List<Pelicula> buscarPorGenero(String genero) {
		return repository.findByGenero(genero);
	}

	public List<Pelicula> buscarPorDisponible(Boolean disponible) {
		return repository.findByDisponible(disponible);
	}
}