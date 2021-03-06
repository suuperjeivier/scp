
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
<meta name="description" content="Pagina para consulta de permisos" />
<meta name="author" content="Sistemas C4 Tabasco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery-ui.theme.min.css" />
<!-- <link rel="stylesheet" href="css/bootstrap_select/bootstrap-select.css" />-->
<title>Permisos | <c:out value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="/jsp/header/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row" id="initRow"></div>
		<div class="page-header">
			<h1>
				<i class="fa fa-search" aria-hidden="true"></i> Consulta Permisos <small>Permisos/Justificantes</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i class="fa fa-bed"
					aria-hidden="true"></i> Justificantes </a></li>
			<li class="breadcrumb-item active"><i class="fa fa-search"
				aria-hidden="true"></i> Consulta</li>
		</ol>
		<div class="row">
			<form class="form-inline" action="" method="post"
				id="formFiltrosPermisos">

				<h4>
					<i class="fa fa-filter"></i>Filtros
				</h4>
				<div class="form-group">
					<label for="claveJustificante"> CLAVE DEL JUSTIFICANTE:</label> <input
						type="text" value="<c:out value="${filtroClave}" />"
						class="form-control" id="claveJustificante"
						name="claveJustificante">
				</div>
				<div class="form-group">
					<label for="idEmpleado2">Empleado</label> <select
						class="form-control" data-width="auto" id="idEmpleado2"
						name="idEmpleado2">
						<option value="" selected>Seleccione...</option>
						<c:forEach var="row" items="${listaEmpleados}">
							<option value='<c:out value="${row.idEmpleado}"></c:out>'><c:out
									value="${row.nombreCompletoEmpleado}"></c:out></option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-default">Buscar</button>

			</form>
		</div>
		<div class="row">
			<button type="button" class="btn btn-success btn-margin"
				data-target="#modalPermisos" data-toggle="modal" data-new="1">
				<i class="fa fa-plus"></i> Nuevo
			</button>
			<!--  	<button type="button" class="btn btn-primary btn-margin" id="btnImprimirPermisos"> <i class="fa fa-print"></i> Imprimir Listado</button>  -->
			<!-- Modal -->
			<div class="modal fade" id="modalPermisos" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel"></h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form id="formPermisos" role="form" accept-charset="utf-8">
							<div class="modal-body">
								<input type="hidden" id="idPermisoHidden" name="idPermisoHidden"
									value="<c:out value="${nuevoSkuPermiso}"/>" /> <input
									type="hidden" id="lastSku" />
								<div class="row">
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="idEmpleado">Empleado</label> <select
												class="form-control" id="idEmpleado" name="idEmpleado"
												required>
												<option value="">Seleccione uno...</option>
												<c:forEach var="row" items="${listaEmpleados}">
													<option value="<c:out value='${row.idEmpleado}'/>"><c:out
															value="${row.nombreCompletoEmpleado}" /></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="justificacion">Justificación:</label>
											<textarea size="100" name="justificacion" id="justificacion"
												maxlength="100" required></textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div
										class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
										<label class="texto-blanco-bold">Fecha(s) a
											justificar:</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="fechaDePermiso">De:</label> <input type="text"
												class="form-control" name="fechaDePermiso"
												id="fechaDePermiso" placeholder="Ej. 01/03/2017" />
										</div>
									</div>
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="fechaAPermiso">A:</label> <input type="text"
												class="form-control" id="fechaAPermiso" name="fechaAPermiso"
												placeholder="Ej. 02/03/2017" />
										</div>
									</div>
								</div>
								<div class="row">
									<div
										class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
										<label class="texto-blanco-bold">Horas a justificar:</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="horaDe">De:</label> <input type="text"
												class="form-control horaF24" name="horaDe" id="horaDe"
												placeholder="Ej. 08:00:00" />
										</div>
									</div>
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="horaA">A:</label> <input type="text"
												class="form-control horaF24" id="horaA" name="horaA"
												placeholder="Ej. 10:00:00" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="tipoJustificante">Tipo de justificante:</label> <select
												class="form-control" data-width="auto" id="tipoJustificante"
												name="tipoJustificante" required>

											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 col-xs-12 col-lg-12">
										<div class="form-group row" id="divFormGroupArchivo">
											<label for="archivoTarjeta">Tarjeta informativa:</label> <input
												type="file" class="form-control" id="archivoTarjeta"
												name="archivoTarjeta" placeholder="Seleccione la tarjeta"
												accept="application/msword, .doc, .docx, application/pdf" />
										</div>
									</div>
								</div>
								<div class="row" id="divFormGroupArchivoRow">
									<div class="col-md-12 col-xs-12 col-lg-12">
										<div class="form-group row" id="divFormGroupArchivo2"></div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 col-xs-6 col-lg-6">
										<div class="form-group">
											<label for="statusJustificante">Estatus:</label> <select
												class="form-control" data-width="auto"
												id="statusJustificante" name="statusJustificante" required>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary">Guardar
									cambios</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:out value="${mensaje}" />
		<c:if test="${fn:length(permisos) gt 0}">
			<div class="table-responsive rowInicial2" id="tablaPrincipal">
				<table id="tb_listahorarios"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th width="12%">NOMBRE EMPLEADO:</th>
							<th>DE:</th>
							<th>A:</th>
							<th>MOTIVO:</th>
							<th>DESCRIPCIÓN:</th>
							<th>ESTATUS:</th>
							<th>EDITAR</th>
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
							<td></td>
							<td></td>
						</tr>
					</tfoot>
					<tbody>
						<c:set var="i" value="1"></c:set>
						<c:forEach var="row" items="${permisos}">
							<tr>
								<td><c:out value="${i}" /></td>
								<td><c:out value="${row.empledoDTO.nombreCompletoEmpleado}" /></td>
								<td><c:out value="${row.fechaDePermiso.fechaString}" /></td>
								<td><c:out value="${row.fechaAPermiso.fechaString}" /></td>
								<td><c:out value="${row.motivoDTO.estatusString}" /></td>
								<td><c:out value="${row.descripcionPermiso}" /></td>
								<td><img
									src="<c:out value='${row.estatusDTO.iconoStringBase64}' />"
									id="logo_sistema"
									width="<c:out value='${row.estatusDTO.widthIcono}' />"
									height="<c:out value='${row.estatusDTO.heightIcono}' />"
									alt="icono_status"></img><i
									title="<c:out value='${row.estatusDTO.descripcionEstatus}' />"><c:out
											value='${row.estatusDTO.indicadorEstatus}' /></i></td>
								<td><a role="button" data-target="#modalPermisos"
									data-toggle="modal" data-new="0"
									data-idpermiso="<c:out value='${row.idPermiso}' />"
									data-justificacionp="<c:out value='${row.descripcionPermiso}' />"
									data-idempleado="<c:out value='${row.empledoDTO.idEmpleado}' />"
									data-fechadepermiso="<c:out value='${row.fechaDePermiso.fechaString}' />"
									data-fechaapermiso="<c:out value='${row.fechaAPermiso.fechaString}' />"
									data-horadepermiso="<c:out value='${row.horaDePermiso.horaString}' />"
									data-horaapermiso="<c:out value='${row.horaAPermiso.horaString}' />"
									data-motivopermiso="<c:out value='${row.motivoDTO.idTipoEstatus}' />"
									data-statuspermiso="<c:out value='${row.estatusDTO.idTipoEstatus}' />"
									data-archivo-id="<c:out value='${row.archivoTarjeta.idDocumento}' />"
									data-archivo-nombre="<c:out value='${row.archivoTarjeta.nombreDocumento}' />">
										<i class="fa fa-pencil" title="Editar horario"></i>
								</a></td>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- Div tabla -->
		</c:if>
	</div>
	<!-- Container fluid -->
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/listados/permisosJS.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>

	<!-- <script src="js/bootstrap_select/bootstrap-select.js" type="text/javascript"></script>
		<script src="js/bootstrap_select/defaults-es_ES.min.js" type="text/javascript"></script>-->

</body>
</html>