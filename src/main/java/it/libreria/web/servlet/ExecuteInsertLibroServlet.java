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

@WebServlet("/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertLibroServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String pagineInputStringParam = request.getParameter("pagine");
		String dataPubblicazioneStringParam = request.getParameter("dataPubblicazione");

		// questa variabile mi serve in quanto sfrutto in un colpo la validazione
		// della data ed il suo parsing che non posso fare senza un try catch
		// a questo punto lo incapsulo in un metodo apposito
		Date dataPubblicazioneParsed = UtilityLibroForm.parseDatePubblicazioneFromString(dataPubblicazioneStringParam);

		Libro libroInstance = new Libro();

		// valido input tramite apposito metodo e se la validazione fallisce torno in
		// pagina
		if (!UtilityLibroForm.validateInput(titoloInputParam, genereInputParam, pagineInputStringParam,
				dataPubblicazioneStringParam) || dataPubblicazioneParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");

			libroInstance.setTitolo(titoloInputParam);
			libroInstance.setGenere(genereInputParam);
			if (!pagineInputStringParam.isEmpty())
				libroInstance.setPagine(Integer.parseInt(pagineInputStringParam));
			libroInstance.setDataPubblicazione(dataPubblicazioneParsed);

			request.setAttribute("libroDaInserire", libroInstance);
			
			request.getRequestDispatcher("/libro/insert.jsp").forward(request, response);
			return;
		}

		libroInstance.setTitolo(titoloInputParam);
		libroInstance.setGenere(genereInputParam);
		libroInstance.setPagine(Integer.parseInt(pagineInputStringParam));
		libroInstance.setDataPubblicazione(dataPubblicazioneParsed);

		// occupiamoci delle operazioni di business
		try {
			libroInstance.setTitolo(titoloInputParam);
			libroInstance.setGenere(genereInputParam);
			libroInstance.setPagine(Integer.parseInt(pagineInputStringParam));
			libroInstance.setDataPubblicazione(dataPubblicazioneParsed);

			MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libroInstance);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione si ?? verificato un errore.");
			request.getRequestDispatcher("/libro/insert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/libro/results.jsp").forward(request, response);

	}
}
