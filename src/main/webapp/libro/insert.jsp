<!doctype html>
<%@page import="it.libreria.model.Libro"%>
<%@page import="java.text.SimpleDateFormat" %>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci nuovo</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
		<%Libro libroDaInsertServlet = (Libro) request.getAttribute("libroDaInserire"); %>
		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Inserisci nuovo elemento</h5>
			</div>

			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ExecuteInsertLibroServlet"
					novalidate="novalidate">

					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Titolo <span class="text-danger">*</span></label> 
							<input type="text" name="titolo" id="titolo" class="form-control" value="<%= libroDaInsertServlet.getTitolo()!= null? libroDaInsertServlet.getTitolo():""%>"
								placeholder="Inserire il titolo" required >
						</div>

						<div class="form-group col-md-6">
							<label>Genere <span class="text-danger">*</span></label> 
							<input	type="text" name="genere" id="genere" class="form-control" value="<%= libroDaInsertServlet.getGenere()!= null?libroDaInsertServlet.getGenere():"" %>"
								placeholder="Inserire il genere" required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Pagine <span class="text-danger">*</span></label> <input
								type="number" class="form-control" name="pagine" id="pagine" value="<%= libroDaInsertServlet.getPagine()!= null?libroDaInsertServlet.getPagine():"" %>"
								placeholder="Inserire numero pagine" required>
						</div>
						
						<div class="form-group col-md-3">
							<label>Data di Pubblicazione<span class="text-danger">*</span></label>
							<input class="form-control" id="dataPubblicazione" type="date" value="<%=libroDaInsertServlet.getDataPubblicazione()!=null? new SimpleDateFormat("yyyy-MM-dd").format(libroDaInsertServlet.getDataPubblicazione()):""%>"
								placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"
								name="dataPubblicazione" required>
						</div>

					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>

				</form>
				<!-- end card-body -->
			</div>
		</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>