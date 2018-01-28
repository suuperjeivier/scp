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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<base href="${pageContext.request.contextPath}/" />
<!-- pageContext.request.contextPath -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description"
	content="Pagina para consulta de lista de asistencia" />
<meta name="author" content="Sistemas C4 tabasco-Javier Brito Pacheco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link href="css/base/base.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="./css/asistencias/listaAsistenciasCSS.css" />
<link rel="stylesheet" type="text/css"
	href="./css/listados/empleados/empleadosHorariosCSS.css" />
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" />
<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />

<title>Horarios de empleados | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="../header/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-table"></i> Horarios de empleados <small>muestra
					los horarios de los empleados</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-list-alt" aria-hidden="true"></i>Horarios</a></li>
			<li class="breadcrumb-item active"><i class="fa fa-list-ol"></i>
				Consultar Horarios de empleados</li>
		</ol>
		<div class="row" id="rowFiltros">
			<form class="form-inline"
				action="./listados?action=filtrosHorariosEmpleados" method="post">
				<fieldset>
					<legend>
						<i class="fa fa-filter"></i> Filtros
					</legend>
					<div class="form-group">
						<div class="form-group">
							<label for="fechaDe"> <i class="fa fa-table"></i>Mes y
								año:
							</label> <input type="text" class="form-control" name="fechaDe"
								id="fechaDe" value='<c:out value="${filtroFechaDe}"></c:out>'
								placeholder="Ej Mar 1993" />
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
						<button type="submit" class="btn btn-info">
							<i class="fa fa-search"></i> Buscar
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

		<c:if test="${fn:length(empleadosHorarios) gt 0}">
			<button type="button" onclick="exportaExcel();"
				class="btn btn-success">
				<i class="fa fa-file-excel-o" aria-hidden="true"></i> Excel
			</button>
			<button type="button" onclick="exportaPDF();" class="btn btn-danger">
				<i class="fa fa-file-pdf-o" aria-hidden="true"></i> PDF
			</button>
			<div class="table-responsive">
				<table id="tb_asistencia" name="tb_horarios"
					class="table table-hover table-bordered table-condensed" sortable>
					<thead>
						<tr style="display: none;" data-tableexport-display="always">
							<th><img
								src="<c:out value='${configSistema.imagenSistemaBase64}' />"
								id="logo_sistema"
								width="<c:out value='${configSistema.widthLogoSistema}' />"
								height="<c:out value='${configSistema.heightLogoSistema}' />"
								class="d-inline-block align-top" alt="logo_sistema"></th>
						</tr>
						<tr>
							<th>SKU</th>
							<th colspan="4"><i class="fa fa-user" aria-hidden="true"></i>
								Nombre del empleado:</th>
							<c:forEach begin="1" end="31" varStatus="loop">
								<th>${loop.index}</th>
							</c:forEach>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td></td>
							<td colspan="4"></td>
							<c:forEach begin="1" end="31" varStatus="loop">
								<td></td>
							</c:forEach>
						</tr>
					</tfoot>
					<tbody>
						<c:set var="i" value="1"></c:set>
						<c:forEach var="row" items="${empleadosHorarios}">
							<tr>
								<td><c:out value="${row.empleado.skuEmpleado}" /></td>
								<td colspan="4"><c:out
										value="${row.empleado.nombreCompletoEmpleado}" /></td>
								<c:forEach var="item" items="${row.horarios}">
									<td bgcolor="<c:out value='${item.colorHexadecimal}'/>"
										style="white-space: nowrap;"><c:out
											value="${item.claveHorario}" /></td>
								</c:forEach>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- TERMINA DIV RESPONSIVE DE TABLA -->
		</c:if>
	</div>
	<jsp:include page="../footer/footer.jsp"></jsp:include>

	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/moment-with-locales.js"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>


	<script type="text/javascript" src="js/jquery.base64.js"></script>
	<script type="text/javascript" src="js/jspdf/libs/sprintf.js"></script>
	<script type="text/javascript" src="js/jspdf/jspdf.min.js"></script>
	<script type="text/javascript" src="js/jspdf/jspdf.plugin.autotable.js"></script>
	<script type="text/javascript" src="js/jspdf/libs/base64.js"></script>
	<script type="text/javascript" src="js/FileSaver.min.js"></script>
	<script type="text/javascript" src="js/xlsx.core.min.js"></script>
	<script type="text/javascript" src="js/html2canvas.min.js"></script>
	<script type="text/javascript" src="js/tableExport.min.js"></script>

	<script src="js/bitacora/empleadosHorariosJS.js" type="text/javascript"></script>

	<c:if test="${empty filtroFechaDe }">
		<script type="text/javascript">
			$(function() {
				//$( "#fechaDe" ).val(moment().month() + " " + moment().year());	
			});
		</script>
	</c:if>
	<c:if test="${empty filtroFechaA}">
		<script type="text/javascript">
			$(function() {
				//$( "#fechaA" ).val(getDefaultDate());
			});
		</script>
	</c:if>
</body>
</html>