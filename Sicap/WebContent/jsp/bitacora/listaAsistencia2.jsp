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
<link rel="stylesheet" href="css/sweetalert2.min.css" />
<link rel="stylesheet"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" />
<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />

<title>Lista de Asistencia | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="../header/header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row" id="initRow"></div>
		<div class="page-header" id="text1">
			<h1>
				<i class="fa fa-list-ol"></i> Lista de asistencia <small>asitencia
					de los empleados</small>
			</h1>
		</div>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-list-alt" aria-hidden="true"></i> Bitacora </a></li>
			<li class="breadcrumb-item active"><i class="fa fa-list-ol"></i>
				Lista de asistencia</li>
		</ol>
		<div class="row">
			<form class="form-inline"
				action="./bitacora?action=listaAsistenciaFiltros2" method="post">
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
						<div class="form-group">
							<label for="tipoReg">Tipo:</label> <select class="form-control"
								name="tipoReg" id="tipoReg">
								<option <c:if test='${tipoReg == 0}'>selected</c:if> value="">Seleccione...</option>
								<c:forEach var="row" items="${tiposRegs}">
									<option value="<c:out value='${row.idTipoEstatus}'/>"
										<c:if test='${tipoReg == row.idTipoEstatus}'>selected</c:if>><c:out
											value="${row.estatusString}" /></option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-info">Buscar</button>
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
		<c:if test="${fn:length(registros) gt 99999}">
			<button type="button" onclick="exportaExcel();">Excel</button>
			<button type="button" onclick="exportaPDF();">PDF</button>
			<div class="table-responsive">
				<table id="tb_asistencia"
					class="table table-striped table-hover table-bordered table-condensed">
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
							<th>Nombre del empleado:</th>
							<th>Registros:</th>
							<!--  <th>Estadistica</th>-->
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<!-- <td></td>-->
						</tr>
					</tfoot>
					<tbody>
						<c:set var="i" value="1"></c:set>
						<c:set var="faltas" value="0"></c:set>
						<c:set var="retardos" value="0"></c:set>
						<c:forEach var="row" items="${registros}">
							<c:set var="skuAnt" value="${row.empleado.skuEmpleado}"></c:set>
							<tr>
								<td><c:out value="${row.empleado.skuEmpleado}" /></td>
								<td><c:out value="${row.empleado.nombreCompletoEmpleado}" /></td>
								<td>
									<table>
										<thead>
											<tr>
												<th>Fecha:</th>
												<th>Registros:</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td></td>
												<td></td>
											</tr>
										</tfoot>
										<tbody>
											<c:if test="${row.empleado.skuEmpleado != skuAnt}">
												<c:set var="retardos" value="0"></c:set>
												<c:set var="faltas" value="0"></c:set>
											</c:if>
											<c:forEach var="item" items="${row.registrosAsistencia}">
												<tr>
													<td><c:out value="${item.fecha.nombreDiaDeLaSemana}" />
														<c:out value="${item.fecha.fechaString}" /></td>
													<td>
														<table>
															<thead>
																<tr>
																	<c:forEach var="reg" items="${item.registrosEnFecha}">
																		<th>Hora:</th>
																		<th>Tipo:</th>
																		<!-- <th>Status:</th>-->
																	</c:forEach>
																</tr>
															</thead>
															<tfoot>
																<tr>
																	<c:forEach var="reg" items="${item.registrosEnFecha}">
																		<td></td>
																		<td></td>
																		<!-- <td></td>-->
																	</c:forEach>
																</tr>
															</tfoot>
															<tbody>
																<tr>
																	<c:forEach var="reg" items="${item.registrosEnFecha}">
																		<td><c:out value="${reg.horaRegistro.horaString}" /></td>
																		<td><c:out
																				value="${reg.tipoRegistro.estatusString}"></c:out></td>
																		<!-- <td><c:out value="${reg.statusRegistro.estatusString}"></c:out></td>-->
																		<c:if test="${reg.tipoRegistro.estatusInt == 10}">
																			<c:set var="retardos" value="${retardos+1}"></c:set>
																		</c:if>
																		<c:if test="${reg.tipoRegistro.estatusInt == 40}">
																			<c:set var="faltas" value="${faltas+1}"></c:set>
																		</c:if>
																	</c:forEach>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<table id="tb_resumen"
										class="table table-striped table-hover table-bordered table-condensed">
										<thead>
											<tr>
												<th>Retardos</th>
												<th>Faltas</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td></td>
												<td></td>
											</tr>
										</tfoot>
										<tbody>
											<tr>
												<td><c:out value="${retardos}" /></td>
												<td><c:out value="${faltas}" /></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- TERMINA DIV RESPONSIVE DE TABLA -->
		</c:if>
		<c:if test="${fn:length(registros) gt 0}">
			<button type="button" class="btn btn-success pointer"
				onclick="exportaExcel();">
				<i class="fa fa-file-excel-o"></i> Excel
			</button>
			<button type="button" class="btn btn-success pointer"
				onclick="exportaPDF();">
				<i class="fa fa-file-pdf-o"></i> PDF
			</button>
			<button type="button" class="btn btn-info pointer" id="btnJasper">
				<i class="fa fa-file-pdf-o"></i> Generar reporte de jasper
			</button>
			<div id="tablaRegistros">
				<table id="tb_asistencia"
					class="table table-striped table-hover table-bordered table-condensed"
					style="font-size: 9px;">
					<%--<colgroup>
			            <col width="5%">
			            	<col width="20%">
			            	<c:forEach var="row7" items="${dias}">								
								<col width="<c:out value="${65/fn:length(dias)}"/>%"/>								
							</c:forEach>			            				           		
			            			<col width="5%">
			           					<col width="5%">
			        </colgroup> --%>
					<thead>
						<tr>
							<th>SKU</th>
							<th>Nombre del empleado:</th>
							<c:forEach var="row0" items="${dias}">
								<th><c:out value="${row0.fecha.nombreDiaDeLaSemana}" /> <c:out
										value="${row0.fecha.fechaString}" /></th>
							</c:forEach>
							<th>Retardos</th>
							<th>Faltas</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td></td>
							<td></td>
							<c:forEach var="row1" items="${dias}">
								<td></td>
							</c:forEach>
							<td></td>
							<td></td>
						</tr>
					</tfoot>
					<tbody>
						<c:set var="i" value="1"></c:set>
						<c:set var="faltas" value="0"></c:set>
						<c:set var="retardos" value="0"></c:set>
						<c:forEach var="row" items="${registros}">
							<c:set var="skuAnt" value="${row.empleado.skuEmpleado}"></c:set>
							<tr>
								<td><c:out value="${row.empleado.skuEmpleado}" /></td>
								<td><c:out value="${row.empleado.nombreCompletoEmpleado}" /></td>
								<c:set var="retardos" value="0"></c:set>
								<c:set var="faltas" value="0"></c:set>
								<c:forEach var="item" items="${row.registrosAsistencia}">
									<c:set var="claseTdRegistros" value=""></c:set>
									<c:set var="colorBg" value=""></c:set>
									<c:forEach var="reg" items="${item.registrosEnFecha}">
										<c:if test="${reg.tipoRegistro.estatusInt != 38}">
											<c:choose>
												<c:when test="${reg.tipoRegistro.estatusInt == 40}">
													<c:set var="claseTdRegistros" value="bg-danger"></c:set>
													<c:set var="colorBg" value="#d9534f"></c:set>
													<c:set var="faltas" value="${faltas+1}"></c:set>
												</c:when>
												<c:when test="${reg.tipoRegistro.estatusInt == 10}">
													<c:set var="claseTdRegistros" value="bg-warning"></c:set>
													<c:set var="colorBg" value="#f0ad4e"></c:set>
													<c:set var="retardos" value="${retardos+1}"></c:set>
												</c:when>
												<c:when test="${reg.faltaJustificada}">
													<c:set var="claseTdRegistros" value="bg-info"></c:set>
													<c:set var="colorBg" value="#5bc0de"></c:set>
												</c:when>
												<c:otherwise>

												</c:otherwise>
											</c:choose>
										</c:if>

									</c:forEach>
									<td
										style="font-size: 8pt; color:black; background-color:<c:out value='${colorBg}'></c:out>;">
										<c:forEach var="reg2" items="${item.registrosEnFecha}">
											<c:out value="${reg2.horaRegistro.horaString}" />-<c:out
												value="${reg2.tipoRegistro.indicadorEstatus}"></c:out>

										</c:forEach>
								</c:forEach>

								<td><c:out value="${retardos}" /></td>
								<td><c:out value="${faltas}" /></td>
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
	<script type="text/javascript" src="js/jspdf/jspdf.debug.js"></script>
	<script type="text/javascript" src="js/jspdf/jspdf.plugin.autotable.js"></script>
	<script type="text/javascript" src="js/jspdf/libs/base64.js"></script>
	<script type="text/javascript" src="js/FileSaver.min.js"></script>
	<script type="text/javascript" src="js/xlsx.core.min.js"></script>
	<script type="text/javascript" src="js/html2canvas.min.js"></script>
	<script type="text/javascript" src="js/tableExport.min.js"></script>

	<script src="js/listados/listaAsistenciaJS.js" type="text/javascript"></script>
	<c:if test="${empty filtroFechaDe }">
		<script type="text/javascript">
			$(function() {
				$( "#fechaDe" ).val(getDefaultDate());	
			});
		</script>
	</c:if>
	<c:if test="${empty filtroFechaA}">
		<script type="text/javascript">
			$(function() {
				$( "#fechaA" ).val(getDefaultDate());
			});
		</script>
	</c:if>
	<script type="text/javascript">
		var imgSrc = "<c:out value='${configSistema.imagenSistemaBase64}' />";
	</script>
</body>
</html>