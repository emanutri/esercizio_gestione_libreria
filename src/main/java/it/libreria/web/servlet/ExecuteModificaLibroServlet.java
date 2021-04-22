package it.libreria.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.libreria.model.Libro;
import it.libreria.service.MyServiceFactory;
import it.libreria.utility.UtilityLibroForm;

@WebServlet("/ExecuteModificaLibroServlet")
public class ExecuteModificaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaLibroServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idLibroDaModificare = request.getParameter("inputId");

		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String pagineInputStringParam = request.getParameter("pagine");
		String dataPubblicazioneStringParam = request.getParameter("dataPubblicazione");

		Date dataPubblicazioneParsed = UtilityLibroForm.parseDatePubblicazioneFromString(dataPubblicazioneStringParam);

		Libro libroInstance = new Libro();
		libroInstance.setId(Long.parseLong(idLibroDaModificare));

		if (!UtilityLibroForm.validateInput(titoloInputParam, genereInputParam, pagineInputStringParam,
				dataPubblicazioneStringParam) || dataPubblicazioneParsed == null) {

			libroInstance.setTitolo(titoloInputParam);
			libroInstance.setGenere(genereInputParam);
			if (!pagineInputStringParam.isEmpty()) {
				libroInstance.setPagine(Integer.parseInt(pagineInputStringParam));
			}
			libroInstance.setDataPubblicazione(dataPubblicazioneParsed);

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("libroDaModificare", libroInstance);
			request.getRequestDispatcher("/libro/edit.jsp").forward(request, response);
			return;
		}
		try {

			libroInstance.setTitolo(titoloInputParam);
			libroInstance.setGenere(genereInputParam);
			libroInstance.setPagine(Integer.parseInt(pagineInputStringParam));
			libroInstance.setDataPubblicazione(dataPubblicazioneParsed);

			MyServiceFactory.getLibroServiceInstance().aggiorna(libroInstance);

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
