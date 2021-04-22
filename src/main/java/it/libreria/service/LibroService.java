package it.libreria.service;

import java.util.List;

import it.libreria.dao.LibroDao;
import it.libreria.model.Libro;

public interface LibroService {

	public void setLibroDao(LibroDao lIBRODAO_INSTANCE);

	public List<Libro> listAll() throws Exception;

	public Libro caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Libro input) throws Exception;

	public void inserisciNuovo(Libro input) throws Exception;

	public void rimuovi(Libro input) throws Exception;

}
