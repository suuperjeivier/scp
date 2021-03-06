<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <c:if test="${user == null}">
	<c:redirect url="./" />
</c:if>	-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<c:set var="compTitle" value="Menus"></c:set>
<c:set var="compTitleSing" value="Menu"></c:set>
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
			<li class="breadcrumb-item"><i class="fa fa-search"
				aria-hidden="true"></i> Consulta</li>
			<li class="breadcrumb-item active"><i class="fa fa-search"
				aria-hidden="true"></i> <c:out value="${compTitle}" /></li>
		</ol>
		<div class="row">
			<!-- <form class="form-inline"
					action="./listados?action=buscarHorario" method="post">
					
						<h4><i class="fa fa-filter"></i>Filtros</h4>
						<div class="form-group">
							<label for="claveHorario">CLAVE DEL HORARIO:</label> <input
								type="text" value="<c:out value="filtroClave" />"
								class="form-control" id="claveHorario" name="claveHorario">
						</div>
						<button type="submit" class="btn btn-default"><i class="fa fa-search"></i> Buscar</button>
					
				</form>-->
		</div>
		<div class="row">
			<button type="button" class="btn btn-success btn-margin"
				data-target="#modal<c:out value="${compTitle}"/>"
				data-toggle="modal" data-new="1"
				id="btnNuevo<c:out value="${compTitle}"/>">
				<i class="fa fa-plus"></i> Nuevo
				<c:out value="${compTitleSing}" />
			</button>
			<c:if test="${fn:length(menus) gt 0}">
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
						<form id="form<c:out value="${compTitleSing}"/>">
							<div class="modal-body">
								<input type="hidden" name="idMenuHidden" id="idMenuHidden"
									value=""></input>
								<div class="form-group row">
									<label for="field1" class="col-2 col-form-label"><c:out
											value="${compTitleSing}" /> ID</label> <input type="text"
										class="col-8 form-control" name="field1" id="field1" readonly
										placeholder="Id <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field2" class="col-2 col-form-label">MENU</label> <input
										type="text" class="col-8 form-control" name="field2"
										id="field2"
										placeholder="Nombre del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field3" class="col-2 col-form-label">DESCRIPCI�N</label>
									<input type="text" class="col-8 form-control" name="field3"
										id="field3"
										placeholder="Descripci�n del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field4" class="col-2 col-form-label">URL</label> <input
										type="text" class="col-8 form-control" name="field4"
										id="field4"
										placeholder="URL del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field5" class="col-2 col-form-label">�CONO</label>
									<input type="text" class="col-8 form-control" name="field5"
										id="field5"
										placeholder="�cono del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field6" class="col-2 col-form-label">INDEX</label>
									<input type="number" step="0.0" min="0"
										class="col-8 form-control" name="field6" id="field6"
										placeholder="Ej. 1.0">
								</div>
								<div class="form-group row">
									<label for="field7" class="col-2 col-form-label">ESTATUS</label>
									<input type="checkbox" class="col-1 form-control" name="field7"
										id="field7" value="1" /><label for="field7"
										class="col-1 col-form-label"> Activo</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary"
									id="btnGuardar<c:out value="${compTitleSing}"/>">
									Guardar
									<c:out value="${compTitleSing}" />
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="contenedorTabla<c:out value="${compTitleSing}"/>">
			<c:out value="${mensaje}" />
			<c:choose>
				<c:when test="${fn:length(menus) gt 0}">
					<div class="table-responsive rowInicial2"
						id="divTabla<c:out value="${compTitle}"/>">
						<table id="tb_lista<c:out value="${compTitle}"/>"
							class="table table-striped table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th><i class="fa fa-list" aria-hidden="true"></i> ID:</th>
									<th width="12%"><i class="fa fa-list" aria-hidden="true"></i>
										NOMBRE:</th>
									<th><i class="fa fa-list" aria-hidden="true"></i>
										DESCRIPCI�N:</th>
									<td><i class="fa fa-list" aria-hidden="true"></i> URL:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> �CONO:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> INDEX:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> ESTATUS:
									</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> EDITAR:
									</td>
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
								<c:forEach var="row" items="${menus}">
									<tr>
										<td><c:out value="${row.idApp}" /></td>
										<td><c:out value="${row.nombreApp}" /></td>
										<td><c:out value="${row.descApp}" /></td>
										<td><c:out value="${row.urlApp}" /></td>
										<td><i class="fa <c:out value="${row.iconoApp}" />"></i>
											(<c:out value="${row.iconoApp}" />)</td>
										<td><c:out value="${row.indexApp}" /></td>
										<td><img
											src="<c:out value='${row.estatusMenuDTO.iconoStringBase64}' />"
											id="logo_sistema"
											width="<c:out value='${row.estatusMenuDTO.widthIcono}' />"
											height="<c:out value='${row.estatusMenuDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><a role="button"
											data-target="#modal<c:out value="${compTitle}"></c:out>"
											data-toggle="modal" data-new="0"
											data-field1="<c:out value='${row.idApp}' />"
											data-field2="<c:out value='${row.nombreApp}' />"
											data-field3="<c:out value='${row.descApp}' />"
											data-field4="<c:out value='${row.urlApp}' />"
											data-field5="<c:out value='${row.iconoApp}' />"
											data-field6="<c:out value='${row.indexApp}' />"
											data-field7="<c:out value='${row.statusApp}' escapeXml="false" />"><i
												class="fa fa-pencil"
												title="Editar <c:out value="${compTitleSing}"/> - <c:out value='${row.idApp}' />"></i></a></td>

									</tr>
									<c:set var="i" value="${i+1}"></c:set>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<strong>Sin registros!</strong> La consulta solicitada ha
						retornado ningun resultado.
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="../footer/footer.jsp"></jsp:include>
	<script type="text/javascript">
		var elemento = '<c:out value="${compTitleSing}"/>';
		var elementos = '<c:out value="${compTitle}"/>';
	</script>
	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
	<script src="js/config/configJS.js" type="text/javascript"></script>

</body>
</html>