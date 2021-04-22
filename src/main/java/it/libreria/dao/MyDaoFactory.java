package it.libreria.dao;

public class MyDaoFactory {

	private static LibroDao libroDaoInstance = null;

	public static LibroDao getLibroDaoInstance() {
		if (libroDaoInstance == null)
			libroDaoInstance = new LibroDaoImpl();

		return libroDaoInstance;
	}

}
