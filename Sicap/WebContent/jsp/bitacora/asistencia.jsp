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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<base href="${pageContext.request.contextPath}/" />
<!-- pageContext.request.contextPath -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Sistemas C4 tabasco-Javier Brito Pacheco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="./css/asistencias/asistenciasCSS.css">
<link href="css/sweetalert2.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/jquery-ui.min.css" />
<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />
<title>Asistencias | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="../header/header.jsp"></jsp:include>
	<div class="container">
		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-search" aria-hidden="true"></i> Consultar Bitácora<small>Registros
					de asistencia de los empleados</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-list-alt" aria-hidden="true"></i> Bitacora </a></li>
			<li class="breadcrumb-item"><i class="fa fa-search"
				aria-hidden="true"></i> Consultar Bitácora</li>
		</ol>
		<div class="row">
			<form class="form-inline"
				action="./bitacora?action=consultarBitacoraFiltros" method="post">
				<fieldset>
					<legend>Filtros</legend>
					<div class="form-group">
						<div class="form-group">
							<label for="fechaDe">Fecha de:</label> <input type="text"
								class="form-control" name="fechaDe" id="fechaDe"
								value='<c:out value="${filtroFechaDe}"></c:out>'
								placeholder="dd/MM/aaaa" />
						</div>
						<div class="form-group ">
							<label for="fechaA">Fecha a:</label> <input type="text"
								class="form-control" name="fechaA" id="fechaA"
								value='<c:out value="${filtroFechaA}"></c:out>'
								placeholder="dd/MM/aaaa" />
						</div>
						<div class="form-group ">
							<label for="idEmpleado">ID:</label> <input type="number"
								value="<c:out value="${filtroId}" />" class="form-control"
								id="idEmpleado" name="idEmpleado" placeholder="ID del empleado">
						</div>
						<div class="form-group">
							<label for="nombreEmpleado">Nombre:</label> <input type="text"
								class="form-control" name="nombreEmpleado" id="nombreEmpleado"
								value='<c:out value="${filtroNombreEmpleado}"></c:out>'
								placeholder="Nombre o apellido parcial o completo" />
						</div>
						<button type="submit" class="btn btn-default">
							<i class="fa fa-search"></i> Buscar
						</button>
						<button type="button" class="btn btn-primary btn-margin"
							id="btnImprimirAsistencias">
							<i class="fa fa-print"></i> Imprimir Listado
						</button>
					</div>
				</fieldset>
			</form>
			<c:if test="${fn:length(errores) gt 0}">
				<c:forEach var="error" items="${errores}">
					<div class="alert alert-danger alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><c:out value="${error.tituloError}" /></strong>
						<c:out value="${error.mensajeError}" />
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="row">
			<form class="form-inline" onsubmit="return false;">
				<div class="form-group">
					<button type="button" class="btn btn-success form-control"
						onclick="ProcesarBitacora();">
						<i class="fa fa-cog fa-spin fa-fw"></i> <span class="sr-only">Loading...</span>
						Procesar registros
					</button>
				</div>
			</form>
		</div>
		<c:if test="${fn:length(registros) gt 0}">
			<div class="table-responsive" id="divTablaAsistencias">
				<table id="tb_asistencia"
					class="table table-striped table-bordered table-inverse table-condensed">
					<thead>
						<tr>
							<th>ID Asistencia</th>
							<th>Empleado:</th>
							<th>Fecha:</th>
							<th>Hora:</th>
							<th>Tipo:</th>
							<th>Status:</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tfoot>
					<tbody>
						<c:set var="i" value="1"></c:set>
						<c:forEach var="row" items="${registros}">
							<tr>
								<td><c:out value="${row.id}" /></td>
								<td><c:out value="${row.empleado.nombreCompletoEmpleado}" /></td>
								<td><c:out value="${row.fechaRegistro.fechaString}" /></td>
								<td><c:out value="${row.horaRegistro.horaString}" /></td>
								<td><c:out value="${row.tipoRegistro.estatusString}"></c:out></td>
								<td><c:out value="${row.statusRegistro.estatusString}"></c:out></td>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>

	<script src="js/asistencias/asistenciasJS.js" type="text/javascript"></script>
	<c:if test="${(empty filtroFechaDe) || (empty filtroFechaA)}">
		<script type="text/javascript">	
			<c:if test="${empty filtroFechaDe}">
				$(function() {
					$( "#fechaDe" ).val(getDefaultDate());	
				});				
			</c:if>
			<c:if test="${empty filtroFechaA}">				
				$(function() {
					$( "#fechaA" ).val(getDefaultDate());
				});				
			</c:if>
		</script>
	</c:if>
</body>
</html>