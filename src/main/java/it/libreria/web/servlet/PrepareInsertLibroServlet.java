package it.libreria.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.libreria.model.Libro;

@WebServlet("/PrepareInsertLibroServlet")
public class PrepareInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertLibroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("libroDaInserire", new Libro());

		request.getRequestDispatcher("/libro/insert.jsp").forward(request, response);
	}
}
