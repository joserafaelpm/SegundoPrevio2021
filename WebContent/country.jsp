<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aplicacion Gestion de Paises</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand">
					Gestion de Paises</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Pais</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${country != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${country == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${country != null}">
                             
                                </c:if>
						<c:if test="${country == null}">
                                
                                </c:if>
					</h2>
				</caption>

				<c:if test="${country != null}">
					<input type="hidden" name="id"
						value="<c:out value='${country.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>country Nombre</label> <input type="text"
						value="<c:out value='${usuario.nombre}' />" class="form-control"
						name="nombre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>country id</label> <input type="text"
						value="<c:out value='${country.id}' />" class="form-control"
						name="email">
				</fieldset>


				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>