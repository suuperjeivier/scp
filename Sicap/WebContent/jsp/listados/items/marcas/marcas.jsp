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
<!-- pageContext.request.contextPath -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description"
	content="Pagina para consulta de lista de asistencia" />
<meta name="author" content="Sistemas C4 Tabasco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<c:set var="compTitle" value="Marcas"></c:set>
<c:set var="compTitleSing" value="marca"></c:set>
<title>Consulta de <c:out value="${compTitle}" /> | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="/jsp/header/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-search" aria-hidden="true"></i> Consulta de
				<c:out value="${compTitle}" />
				<small><c:out value="${compTitle}" /> del sistema</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-clock-o" aria-hidden="true"></i> Inventario </a></li>
			<li class="breadcrumb-item active"><i class="fa fa-search"
				aria-hidden="true"></i> Consulta</li>
		</ol>
		<div class="row">
			<!-- <form class="form-inline"
					action="./listados?action=buscarHorario" method="post">
					
						<h4><i class="fa fa-filter"></i>Filtros</h4>
						<div class="form-group">
							<label for="claveHorario">CLAVE DEL HORARIO:</label> <input
								type="text" value="<c:out value="${filtroClave}" />"
								class="form-control" id="claveHorario" name="claveHorario">
						</div>
						<button type="submit" class="btn btn-default"><i class="fa fa-search"></i> Buscar</button>
					
				</form>-->
		</div>
		<div class="row">
			<button type="button" class="btn btn-success btn-margin"
				data-target="#modal<c:out value="${compTitle}"/>"
				data-toggle="modal" data-new="1">
				<i class="fa fa-plus"></i> Nuevo
			</button>
			<c:if test="${fn:length(modelosItems) gt 0}">
				<button type="button" class="btn btn-primary btn-margin"
					id="btnImprimir<c:out value="${compTitle}"/>">
					<i class="fa fa-print"></i> Imprimir Listado
				</button>
			</c:if>
			<!-- Modal -->
			<div class="modal fade" id="modal<c:out value="${compTitle}"/>"
				tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form id="formHorarios">
							<div class="modal-body">
								<div class="row">
									<div class="col-4">
										<div class="form-group">
											<input type="hidden" id="idHorarioHidden"
												name="idHorarioHidden" /> <input type="hidden"
												id="cveHorarioHidden" name="cveHorarioHidden"> <label
												for="cveHorario">Clave</label> <input type="text" disabled
												class="form-control" name="cveHorario" id="cveHorario"
												required placeholder="Ej. T1">
										</div>
									</div>
									<div class="col-5">
										<div class="form-group">
											<label for="nombreHorario">Descripción</label> <input
												type="text" class="form-control" name="nombreHorario"
												id="nombreHorario" placeholder="Ej. Oficina 1">
										</div>
									</div>
									<div class="col-3">
										<div class="form-group">
											<label for="nl">N/L(NO LABORAL)</label> <input
												name="noLaboral" type="checkbox" class="form-control"
												value="1" id="nl">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<div class="form-group">
											<label for="e1Horario">E1</label> <input type="text"
												class="form-control horaF24" id="e1Horario" name="e1Horario"
												placeholder="Ej. 08:00:00">
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="s1Horario">S1</label> <input type="text"
												class="form-control horaF24" id="s1Horario" name="s1Horario"
												placeholder="Ej. 17:00:00">
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="hr1Horario">HR1</label> <input type="text"
												class="form-control horaF24" id="hr1Horario"
												name="hr1Horario" placeholder="Ej. 00:14:59">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="horarioQuebrado">QDO(QUEBRADO)</label> <input
											type="checkbox" value="3" class="form-control"
											id="horarioQuebrado" name="horarioQuebrado">
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<div class="form-group">
											<label for="e2Horario">E2</label> <input type="text"
												class="form-control horaF24" required id="e2Horario"
												name="e2Horario" placeholder="Ej. 08:00:00" disabled>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="s2Horario">S2</label> <input type="text"
												class="form-control horaF24" required id="s2Horario"
												name="s2Horario" placeholder="Ej. 17:00:00" disabled>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="hr2Horario">HR2</label> <input type="text"
												class="form-control horaF24" required id="hr2Horario"
												name="hr2Horario" placeholder="Ej. 00:14:59" disabled>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group" id="divStatusHorario">
										<label for="statusHorario">ESTATUS</label> <select
											class="form-control" id="statusHorario"
											name="status<c:out value="${compTitle}"/>">
											<option value="" selected>SELECCIONE...</option>
											<option value="5">ACTIVO</option>
											<option value="6">BAJA</option>
										</select>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary" id="">Guardar
									cambios</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="contenedorTabla">
			<c:out value="${mensaje}" />
			<c:if test="${fn:length(marcasItems) gt 0}">
				<div class="table-responsive rowInicial2" id="divTablaHorarios">
					<table id="tb_listahorarios"
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>CVE:</th>
								<th width="12%">NOMBRE:</th>
								<th>E1:</th>
								<th>S1:</th>
								<th>S1:</th>

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
							<c:forEach var="row" items="${marcasItems}">
								<tr>
									<td><c:out value="${i}" /></td>
									<td><c:out value="${row.idMarcaItem}" /></td>
									<td><c:out value="${row.skuMarcaItem}" /></td>
									<td><c:out value="${row.nombreMarcaItem}" /></td>
									<td><c:out value="${row.tipoItem.nombreTipoItem}" /></td>


									<td><a role="button"
										data-target="#modal<c:out value="${compTitle}"/>"
										data-toggle="modal" data-new="0"
										data-idhorario="<c:out value='${row.idMarcaItem}' />"
										data-name="<c:out value='${row.nombreMarcaItem}' />"
										data-clave="<c:out value='${row.skuMarcaItem}' />"><i
											class="fa fa-pencil"
											title="Editar <c:out value="${compTitleSing}"/> -<c:out value='${row.idMarcaItem}' />"></i></a></td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</div>
	<jsp:include page="../../../footer/footer.jsp"></jsp:include>

	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
	<script src="js/listados/horariosJS.js" type="text/javascript"></script>

</body>
</html>