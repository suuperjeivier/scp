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
				<i class="fa fa-upload" aria-hidden="true"></i> Carga de Horarios <small>Carga
					el archivo que contiene el horario</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-clock-o" aria-hidden="true"></i> Horarios </a></li>
			<li class="breadcrumb-item active"><i class="fa fa-upload"
				aria-hidden="true"></i> Carga de Horarios</li>
		</ol>
		<div class="row" id="filtrosRow">
			<div class="col-lg-2">
				<button id="btnNuevaCargaHorario" data-toggle="modal"
					data-target="#modalCargaHorario"
					class="btn btn-success btn-lg btn-nuevo">
					<i class="fa fa-upload" aria-hidden="true"></i> Carga horario
				</button>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="modalCargaHorario" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Carga de
								archivos</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<h6>Carga el archivo que contiene el horario de los
								empleados</h6>
							<hr>
							<div class="container-fluid" id="dzContainer">
								<form action="./documentos?action=procesarArchivo"
									class="dz-clickable form-inline" id="dzHorario" method="post"
									enctype="multipart/form-data">
									<div class="dz-default dz-message form-group row"
										id="dropHorario">
										<label class="col-form-label" id="lblInfo"><i
											class="fa fa-download-alt"></i> Arrastra el archivo .xlsx del
											horario para procesarlo/Has clic aquí para seleccionarlo.</label>

									</div>
								</form>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cerrar</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="row" align="center" id="dropzonePreview"
			class="dropzone-previews"></div>
		<div class="container-fluid">
			<c:choose>
				<c:when test="${fn:length(documentosHorarios) gt 0}">
					<div class="table-responsive" id="contenedorTabla">
						<table
							class="table table-bordered table-condensed table-striped table-hover"
							id="documentosHorarios">
							<thead>
								<tr>
									<th>#</th>
									<th>NOMBRE</th>
									<th>FECHA VALIDÉZ</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="row" items="${documentosHorarios}">
									<tr>
										<td><c:out value="${row.idDocumentoHorarioEmpleados}" /></td>
										<td><c:out value="${row.nombreDocumento}" /></td>
										<td><c:out value="${row.fechaValidezDocumento}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Sin datos!</strong> Su busqueda produjo ningún resultado.
					</div>
				</c:otherwise>
			</c:choose>
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