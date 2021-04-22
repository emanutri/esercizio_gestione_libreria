package it.libreria.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.libreria.model.Libro;
import it.libreria.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteLibroServlet")
public class ExecuteDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteLibroServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String IdLibroDaEliminare = request.getParameter("inputId");

		if (!NumberUtils.isCreatable(IdLibroDaEliminare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		try {

			Libro libroDaEliminare = MyServiceFactory.getLibroServiceInstance()
					.caricaSingoloElemento(Long.parseLong(IdLibroDaEliminare));

			MyServiceFactory.getLibroServiceInstance().rimuovi(libroDaEliminare);

			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
	}

}
