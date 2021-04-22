package it.libreria.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.libreria.service.MyServiceFactory;

@WebServlet("/PrepareModificaLibroServlet")
public class PrepareModificaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaLibroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdLibroCheVoglioModificare = request.getParameter("idLibro");

		if (!NumberUtils.isCreatable(parametroIdLibroCheVoglioModificare)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			request.setAttribute("libroDaModificare", MyServiceFactory.getLibroServiceInstance()
					.caricaSingoloElemento(Long.parseLong(parametroIdLibroCheVoglioModificare)));

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/libro/edit.jsp").forward(request, response);
	}

}
