<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${user == null}">
	<c:redirect url="./" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<!-- pageContext.request.contextPath -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Javier Brito">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap.fd.css" rel="stylesheet">
<link href="css/tether.min.css" rel="stylesheet">
<link href="css/tether-theme-arrows.min.css" rel="stylesheet">
<link href="css/cargaHorarioCSS.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link href="css/sweetalert2.min.css" rel="stylesheet">
<link href="css/dropzone.css" rel="stylesheet">
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<title>Carga de Horarios | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>

	<jsp:include page="../header/header.jsp"></jsp:include>
	<%--<c:import url="../header/header.jsp"/> --%>

	<div class="container-fluid">

		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-download" aria-hidden="true"></i> Reportes de
				ordenes de servicio <small>Genera los reportes de las
					ordenes de servicio</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><i class="fa fa-cog"
				aria-hidden="true"></i> Ordenes de Servicio</li>
			<li class="breadcrumb-item"><i class="fa fa-file"
				aria-hidden="true"></i> Reportes</li>
			<li class="breadcrumb-item active"><i class="fa fa-download"
				aria-hidden="true"></i> Generar</li>
		</ol>
		<div class="container">
			<div class="jumbotron">
				<form class="form" action="./documentos?action=reportesJasper"
					method="POST">
					<fieldset>
						<legend>
							<i class="file" aria-hidden="true"></i>Generar reporte
						</legend>
						<div class="form-group form-group-lg">
							<label class="form-label" for="numero_orden"></label> <input
								type="number" class="form-control" name="numero_orden"
								id="numero_orden" placeholder="Número de orden"></input>
						</div>
						<div class="form-group form-group-lg">
							<label class="form-label" for="tipo_doc"></label> <select
								class="form-control" name="tipo_doc" id="tipo_doc" required>
								<option value="" selected>Seleccione el tipo de
									reporte...</option>
								<option value="oficio">Oficio</option>
								<option value="orden_doble">Orden de doble</option>
							</select>
						</div>
						<button type="submit"
							class="btn btn-success btn-lg btn-block pointer" required>
							<i class="fa fa-download" aria-hidden="true"></i>Generar reporte
						</button>
					</fieldset>
				</form>
			</div>

		</div>

	</div>
	<!-- /.container -->
	<jsp:include page="../footer/footer.jsp"></jsp:include>


	<!-- Bootstrap core JavaScript
	    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<!--     <script src="./js/bootstrap.fd.js" type="text/javascript"></script>-->
	<script src="js/dropzone.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
	<script src="js/documentos/cargaHorarioJS.js" type="text/javascript"></script>

</body>
</html>