<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.libreria.model.Libro"%>
<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="../header.jsp" />
		<title>Elimina Libro</title>
		 <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
		<main role="main" class="container">
		
			<div class='card'>
			    <div class='card-header'>
			        Sei sicuro di voler eliminare il seguente libro?
			    </div>
	   		   	<% Libro libroInPagina = (Libro)request.getAttribute("libroDaEliminare"); %>
			    
			    <div class='card-body'>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Titolo:</dt>
					  <dd class="col-sm-9"><%=libroInPagina.getTitolo() %></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Genere:</dt>
					  <dd class="col-sm-9"><%=libroInPagina.getGenere() %></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Pagine:</dt>
					  <dd class="col-sm-9"><%=libroInPagina.getPagine()%></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di Pubblicazione:</dt>
					  <dd class="col-sm-9"><%=libroInPagina.getDataPubblicazione()!=null? new SimpleDateFormat("dd/MM/yyyy").format(libroInPagina.getDataPubblicazione()):"N.D."  %></dd>
			    	</dl>
			    	
			    </div>
			    
			    <div class='card-footer'>
			    	<form method="post" action="ExecuteDeleteLibroServlet">
				    	
				    	<a href="ListLibriServlet" class="btn  btn-sm btn-outline-secondary" style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
				        </a>
					   
					    <button type="submit" name="elimina" value="elimina" id="elimina" class="btn btn-outline-danger btn-sm">Elimina</button>
					    <input type="hidden" name ="inputId" value=<%=libroInPagina.getId() %>>
			    
			    	</form>
			    </div>
			</div>	
		</main>
		<jsp:include page="../footer.jsp" />
	</body>
</html>