package it.libreria.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.libreria.service.MyServiceFactory;

@WebServlet("/PrepareDeleteLibroServlet")
public class PrepareDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteLibroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdLibroCheVoglioEliminare = request.getParameter("idLibro");

		if (!NumberUtils.isCreatable(parametroIdLibroCheVoglioEliminare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
			return;
		}
		try {

			request.setAttribute("libroDaEliminare", MyServiceFactory.getLibroServiceInstance()
					.caricaSingoloElemento(Long.parseLong(parametroIdLibroCheVoglioEliminare)));

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/libro/results.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/libro/delete.jsp").forward(request, response);
	}

}
