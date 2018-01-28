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
	content="Pagina para Configuraci�n de men�s por perfil" />
<meta name="author" content="Sistemas C4 Tabasco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/base/base.css" rel="stylesheet">
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" />
<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />
<style type="text/css">
.no-list {
	list-style-type: none;
	margin: 0;
	padding: 0;
	margin-bottom: 10px;
}
</style>
<c:set var="compTitle" value="Menus_Perfiles"></c:set>
<c:set var="compTitleSing" value="Menu_Perfil"></c:set>
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
				data-toggle="modal" data-new="1">
				<i class="fa fa-plus"></i> Nuevo
				<c:out value="${compTitleSing}" />
			</button>
			<c:if test="${fn:length(menusPerfiles) gt 0}">
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
								<input type="hidden" name="idMenuPerfilHidden"
									id="idMenuPerfilHidden" value=""></input>
								<div class="form-group row">
									<label for="field1" class="col-3 col-form-label"><c:out
											value="${compTitleSing}" /> ID</label> <input type="text"
										class="col-7 form-control" name="field1" id="field1" readonly
										placeholder="Id <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field2" class="col-3 col-form-label">PERFIL</label>
									<select type="text" class="col-7 form-control" name="field2"
										id="field2">
										<option value="">Seleccione un
											<c:out value="${compTitleSing}" />...
										</option>
										<c:forEach var="row" items="${perfiles}">
											<option value='<c:out value="${row.idPerfil}"></c:out>'>
												<c:out value="${row.description}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group row">
									<label for="field8" class="col-3 col-form-label">MEN�</label> <select
										type="text" class="col-7 form-control" name="field9"
										id="field9">
										<option value="">Seleccione un men�...</option>
										<c:forEach var="menu" items="${menus}">
											<option value='<c:out value="${menu.idApp}"></c:out>'>
												<c:out value="${menu.nombreApp}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group row">
									<label for="field3" class="col-3 col-form-label">SUBMEN�</label>
									<select type="text" class="col-7 form-control" name="field3"
										id="field3">
										<option value="">Seleccione un
											<c:out value="${compTitleSing}" />...
										</option>
										<c:forEach var="sub" items="${submenus}">
											<option value='<c:out value="${sub.idAction}"></c:out>'>
												<c:out value="${sub.nombreAction}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group row">
									<label for="field4" class="col-3 col-form-label">CREAR</label>
									<input type="checkbox" class="col-7 form-control" name="field4"
										id="field4" value="1" /><label for="field4"
										class="col-1 col-form-label">Si</label>
								</div>
								<div class="form-group row">
									<label for="field5" class="col-3 col-form-label">ACTUALIZAR</label>
									<input type="checkbox" class="col-7 form-control" name="field5"
										id="field5" value="1" /><label for="field5"
										class="col-1 col-form-label">Si</label>
								</div>
								<div class="form-group row">
									<label for="field6" class="col-3 col-form-label">BORRAR</label>
									<input type="checkbox" class="col-7 form-control" name="field6"
										id="field6" value="1" /><label for="field6"
										class="col-1 col-form-label">Si</label>
								</div>
								<div class="form-group row">
									<label for="field7" class="col-3 col-form-label">INDEX</label>
									<input type="number" step="any" min="0"
										class="col-7 form-control" name="field7" id="field7" />
								</div>
								<div class="form-group row">
									<ul class="no-list">
										<li id="draggable" class="ui-state-highlight">Drag me
											down</li>
									</ul>

									<ul id="sortable" class="no-list">
										<li class="ui-state-default">Item 1</li>
										<li class="ui-state-default">Item 2</li>
										<li class="ui-state-default">Item 3</li>
										<li class="ui-state-default">Item 4</li>
										<li class="ui-state-default">Item 5</li>
									</ul>
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
				<c:when test="${fn:length(menusPerfiles) gt 0}">
					<div class="table-responsive rowInicial2"
						id="divTabla<c:out value="${compTitle}"/>">
						<table id="tb_lista<c:out value="${compTitle}"/>"
							class="table table-striped table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th><i class="fa fa-list" aria-hidden="true"></i> ID:</th>
									<th><i class="fa fa-list" aria-hidden="true"></i> PERFIL:
									</th>
									<th><i class="fa fa-list" aria-hidden="true"></i> MEN�:</th>
									<th><i class="fa fa-list" aria-hidden="true"></i> SUBMEN�:
									</th>
									<td><i class="fa fa-list" aria-hidden="true"></i> CREAR:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i>
										ACTUALIZAR:</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> BORRAR:
									</td>
									<td><i class="fa fa-list" aria-hidden="true"></i> INDEX:</td>
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
									<td></td>
								</tr>
							</tfoot>
							<tbody>
								<c:set var="i" value="1"></c:set>
								<c:forEach var="row" items="${menusPerfiles}">
									<tr
										<c:if test="${row.fkSubmenuDTO.isHeader == 1 }">class="table-success"</c:if>>
										<td><c:out value="${row.idMenuPerfil}" /></td>
										<td><c:out value="${row.fkPerfilDTO.description}" /></td>
										<td><c:out value="${row.fkSubmenuDTO.nombreMenu}" /></td>
										<td><c:out value="${row.fkSubmenuDTO.nombreAction}" /></td>
										<td><img
											src="<c:out value='${row.createMenuPerfilDTO.iconoStringBase64}' />"
											id="logo_sistema"
											width="<c:out value='${row.createMenuPerfilDTO.widthIcono}' />"
											height="<c:out value='${row.createMenuPerfilDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><img
											src="<c:out value='${row.updateMenuPerfilDTO.iconoStringBase64}' />"
											id="logo_sistema2"
											width="<c:out value='${row.updateMenuPerfilDTO.widthIcono}' />"
											height="<c:out value='${row.updateMenuPerfilDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><img
											src="<c:out value='${row.deleteMenuPerfilDTO.iconoStringBase64}' />"
											id="logo_sistema3"
											width="<c:out value='${row.deleteMenuPerfilDTO.widthIcono}' />"
											height="<c:out value='${row.deleteMenuPerfilDTO.heightIcono}' />"
											alt="icono_status"></img></td>
										<td><c:out value="${row.indexMenuPerfil}" /></td>
										<td><a role="button"
											data-target="#modal<c:out value="${compTitle}"></c:out>"
											data-toggle="modal" data-new="0"
											data-field1="<c:out value='${row.idMenuPerfil}' />"
											data-field2="<c:out value='${row.fkPerfil}' />"
											data-field3="<c:out value='${row.fkSubmenu}' />"
											data-field4="<c:out value='${row.createMenuPerfil}' />"
											data-field5="<c:out value='${row.updateMenuPerfil}' />"
											data-field6="<c:out value='${row.deleteMenuPerfil}' />"
											data-field7="<c:out value='${row.indexMenuPerfil}' escapeXml="false" />"
											data-field8="<c:out value='${row.fkSubmenuDTO.fkApp}' />"><i
												class="fa fa-pencil"
												title="Editar <c:out value="${compTitleSing}"/> - <c:out value='${row.idMenuPerfil}' />"></i></a></td>

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
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>
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