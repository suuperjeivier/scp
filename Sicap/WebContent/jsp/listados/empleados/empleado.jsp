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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="Sistemas C4 Tabasco">
<link rel="manifest" href="./web_app_manifest.json">
<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="css/base/base.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="./css/asistencias/asistenciasCSS.css">
<link rel="stylesheet" type="text/css"
	href="./css/listados/empleados/empleadosCSS.css">
<link href="css/sweetalert2.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery-ui.theme.min.css" />
<title>Empleados | <c:out value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="/jsp/header/header.jsp"></jsp:include>

	<div class="container-fluid">

		<div class="row" id="initRow">
			<div class="container">
				<div class="row" id="rowInit"></div>
				<div class="page-header">
					<h1>
						<i class="fa fa-search" aria-hidden="true"></i> Consulta de
						Empleados <small>Empleados registrados</small>
					</h1>
				</div>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#"><i
							class="fa fa-list" aria-hidden="true"></i> Listados </a></li>
					<li class="breadcrumb-item"><a href="#"><i
							class="fa fa-users" aria-hidden="true"></i> Empleados </a></li>
					<li class="breadcrumb-item active"><i class="fa fa-search"
						aria-hidden="true"></i> Consulta de empleados</li>
				</ol>
				<div class="row">
					<form class="form-inline" action="./listados?action=buscarEmpleado"
						method="post">

						<div class="form-group">
							<label for="skuEmpleado">SKU Empleado</label> <input
								type="number" value="<c:out value="${filtroId}" />"
								class="form-control" id="skuEmpleado" name="skuEmpleado">
						</div>
						<div class="form-group">
							<label for="nombre">Nombre</label> <input type="text"
								value="<c:out value="${filtroNombre}" />" class="form-control"
								id="nombre" name="nombre">
						</div>
						<div class="form-group">
							<label for="numLimit">Cantidad:</label> <select name="numLimit"
								class="form-control">
								<option value="0"
									<c:if test="${filtroLimit == 0 }">selected="true"</c:if>>
									Todos</option>
								<option value="15"
									<c:if test="${filtroLimit == 15 }">selected="true"</c:if>>
									15</option>
								<option value="30"
									<c:if test="${filtroLimit == 30 }">selected="true"</c:if>>
									30</option>
								<option value="100"
									<c:if test="${empty filtroLimit }"> selected="true"</c:if>>
									100</option>
							</select>
						</div>
						<button type="submit" class="btn btn-default">
							<i class="fa fa-search"></i> Buscar
						</button>
					</form>
					<c:out value="${mensaje}" />
				</div>
				<button type="button"
					class="btn btn-success btn-margin nuevoEmpleado"
					data-toggle="modal" data-editar="0" data-target="#registroEmpleado">
					<i class="fa fa-plus"></i> Nuevo
				</button>
				<button type="button" class="btn btn-primary btn-margin"
					id="btnImprimirEmpleados">
					<i class="fa fa-print"></i> Imprimir Listado
				</button>
			</div>
		</div>
		<div class="container-fluid">
			<c:if test="${fn:length(empleados) gt 0}">
				<div class="table-responsive rowInicial2" id="divTablaEmpleados">
					<table id="tb_listaempleado"
						class="table table-striped table-bordered table-inverse table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>NOMBRE:</th>
								<th>CURP:</th>
								<th>RFC:</th>
								<th>ESCOLARIDAD:</th>
								<th>ESPECIALIDAD:</th>
								<th>FECHA DE ALTA:</th>
								<th>ESTATUS:</th>
								<th>EDITAR:</th>
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
							<c:forEach var="row" items="${empleados}">
								<tr>
									<td><c:out value="${row.skuEmpleado}" /></td>
									<td><c:out
											value="${row.nombreEmpleado} ${row.apPaternoEmpleado} ${row.apMaternoEmpleado}" /></td>
									<td><c:out value="${row.curpEmpleado}" /></td>
									<td><c:out value="${row.rfcEmpleado}" /></td>
									<td><c:out value="${row.escolaridadDTO.gradoEscolaridad}" /></td>
									<td><c:out
											value="${row.especialidadDTO.nombreEspecialidad}" /></td>
									<td><c:out value="${row.fechaAltaEmpleadoString}" /></td>
									<!-- <td><c:out value="${row.estatusDTO.estatusString}" /></td>-->
									<td><img
										src="<c:out value='${row.estatusDTO.iconoStringBase64}' />"
										id="logo_sistema"
										width="<c:out value='${row.estatusDTO.widthIcono}' />"
										height="<c:out value='${row.estatusDTO.heightIcono}' />"
										alt="icono_status"></img></td>
									<td><a data-toggle="modal" data-target="#registroEmpleado"
										data-editar="1" data-id="<c:out value="${row.idEmpleado}" />"
										data-sku="<c:out value='${row.skuEmpleado}' />"
										data-nombre="<c:out value='${row.nombreEmpleado}' />"
										data-appaterno="<c:out value='${row.apPaternoEmpleado}' />"
										data-apmaterno="<c:out value='${row.apMaternoEmpleado}' />"
										data-cuip="<c:out value='${row.cuipEmpleado }' />"
										data-curp="<c:out value='${row.curpEmpleado }' />"
										data-rfc="<c:out value='${row.rfcEmpleado }' />"
										data-fechanac="<c:out value='${row.fechaNacimientoEmpleadoString }' />"
										data-genero="<c:out value='${row.generoEmpleadoDTO.idGenero }' />"
										data-edocivil="<c:out value='${row.edoCivilEmpladoDTO.idEstadoCivil }' />"
										data-gposanguineo="<c:out value='${row.grupoSanguineoEmpleadoDTO.idGrupoSanguineo }' />"
										data-entidadfed="<c:out value='${row.entidadFederativaDomicilioEmpleadoDTO.idEntidadFederativa }' />"
										data-municipio="<c:out value='${row.municipioDomicilioEmpleadoDTO.idMunicipio }' />"
										data-colonia="<c:out value='${row.coloniaDomicilioEmpleadoDTO.idColonia }' />"
										data-cp="<c:out value='${row.codigoPostalDomicilioEmpleado }' />"
										data-calle="<c:out value='${row.calleDomicilioEmpleadoDTO.nombreCalle }' />"
										data-numext="<c:out value='${row.noExtDomicilioEmpleado }' />"
										data-numint="<c:out value='${row.noIntDomicilioEmpleado }' />"
										data-numtelfijo="<c:out value='${row.telFijoEmpleado }' />"
										data-numtelmovil="<c:out value='${row.telMovilEmpleado }' />"
										data-correo="<c:out value='${row.correoElectronicoEmpleado }' />"
										data-nvelacad="<c:out value='${row.nivelAcademicoEmpleadoDTO.idNivelAcademico }' />"
										data-especialidad="<c:out value='${row.especialidadDTO.idEspecialidad }' />"
										data-ctrunca="<c:out value='${row.carreraTruncaEmpleado.estatusInt }' />"
										data-perescolar="<c:out value='${row.peridoEscolarEmpleadoDTO.idPeriodoEscolar }' />"
										data-ultgradesc="<c:out value='${row.gradoPeridoEscolarEmpleadoDTO.gradoDTO.idGradoEscolar }' />"
										data-fechaalta="<c:out value='${row.fechaAltaEmpleadoString }' />"><i
											class="fa fa-pencil pointer" title="Editar empleado"></i></a></td>
								</tr>
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<!--   <nav aria-label="...">
  <ul class="pagination">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1">Previous</a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active">
      <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
    </li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="/Sicap/listados?action=paginador&ultimoE=<c:out value="${ultEmpleado}"/>">Next</a>
    </li>
  </ul>
</nav>  -->

		</div>
		<!-- cierra div clase row -->
	</div>
	<!-- cierra div clase container -->

	<div class="modal fade" id="registroEmpleado" tabindex="-1"
		role="dialog" data-backdrop="static"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg" role="document">

			<div class="modal-content">
				<form class="form-horizontal" id="formEmpleado">
					<div class="modal-header">
						<h5 class="modal-title texto-gris" id="formularioModalLabel">FORMULARIO
							DE REGISTRO DEL EMPLEADO</h5>
						<div class="col-md-3 col-xs-3 col-lg-3">
							<button type="button"
								class="btn btn-success boton-alineadoDerecha"
								id="btnActualizarSKU" onclick="actualizaSkuEmpleado();">Actualizar
								ID</button>
						</div>
						<div class="col-md-2 col-xs-2 col-lg-2">
							<div id="alertSku"></div>
							<h5 class="texto-alineadoDerecha-rojoBold">
								<label for="sku" class="modal-title" id="ultimoSkuEmp"><c:out
										value="${ultimoSkuEmpleado}" /></label>
							</h5>
							<input type="text" value="<c:out value="${ultimoSkuEmpleado}"/>"
								id="skuEmpleadoHidden" name="skuEmpleadoHidden" size="5"
								maxlength="11" /> <input type="hidden" id="idEmpleadoHidden"
								name="idEmpleadoHidden" />
						</div>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row" id="rowInitModal"></div>
						<div class="form-group">
							<div class="row">
								<div
									class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
									<label class="texto-blanco-bold">DATOS PERSONALES:</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="nombre" class="texto-azul-bold">NOMBRE(S):</label>
									<input type="text" class="form-control" id="nombreEmpleado"
										name="nombreEmpleado" required>
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="apPaterno" class="texto-azul-bold">APELLIDO
										PATERNO:</label> <input type="text" class="form-control"
										id="apPaterno" name="apPaterno" required>
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="apMaterno" class="texto-azul-bold">APELLIDO
										MATERNO:</label> <input type="text" class="form-control"
										id="apMaterno" name="apMaterno" required>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="cuip" class="texto-azul-bold">CUIP:</label> <input
										type="text" class="form-control text-uppercase	" id="cuip"
										name="cuip">
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<div id="divCurp" class="form-group ">
										<label for="curp" class="texto-azul-bold form-control-label">
											CURP:</label> <input type="text" class="form-control " id="curp"
											name="curp" required>
										<div id="errorCurp" class="form-control-feedback" hidden>
											<span id="iconoCurpError"
												class="glyphicon glyphicon-remove form-control-feedback"
												aria-hidden="true"></span> <span id="inputError2Status">El
												formato de la curp no corresponde al esperado</span>
										</div>
										<small class="form-text text-muted">Este es un campo
											de curp validado contra el formato.</small>
									</div>
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="rfcEmpleado" class="texto-azul-bold"> RFC:</label>
									<input type="text" class="form-control text-uppercase"
										id="rfcEmpleado" name="rfcEmpleado">
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="fechaNac" class="texto-azul-bold">FECHA DE
										NACIMIENTO:</label> <input type="text" class="form-control"
										id="fechaNac" name="fechaNac" required>
								</div>
								<div class="col-md-2 col-xs-2 col-lg-2">
									<fieldset>
										<label class="texto-azul-bold">G�NERO:</label>
										<div id="divContenedorRadioGeneros">
											<c:forEach var="row" items="${listadoGeneros}">
												<div class="form-check">
													<label class="form-check-label font-size11"> <input
														type="radio" class="form-check-input"
														onClick="selectEstadosCiviles();" name="inputGenero"
														id="inputGenero<c:out value="${row.idGenero}" />"
														value="<c:out value="${row.idGenero}" />"> <c:out
															value="${row.nombreGenero}" />
													</label>
												</div>
											</c:forEach>
										</div>
									</fieldset>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="estadoCivil" class="texto-azul-bold">ESTADO
										CIVIL:</label> <select class="form-control" id="estadoCivil"
										name="estadoCivil">
										<option value="" selected="true">Seleccione un
											g�nero...</option>
										<c:out value=""></c:out>
									</select>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="grupoSanguineo" class="texto-azul-bold">GRUPO
										SANGU�NEO:</label> <select class="form-control" id="grupoSanguineo"
										name="grupoSanguineo">
										<option value="" selected="true">Seleccione...</option>
										<c:forEach var="row" items="${listadoGruposSanguineos}">
											<option value="<c:out value="${row.idGrupoSanguineo }"/>"><c:out
													value="${row.grupoSanguineo }" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div
									class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
									<label class="texto-blanco-bold">DOMICILIO:</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="estadoDomicilio" class="texto-azul-bold">ESTADO:</label>
									<select class="form-control" onchange="selectMunicipios();"
										id="estadoDomicilio" name="estadoDomicilio" required>
										<option value="" selected="true">Seleccione un
											Estado...</option>
										<c:forEach var="row" items="${listadoEntidades}">
											<option value="<c:out value="${row.idEntidadFederativa}" />"><c:out
													value="${row.nombreEntidadFederativa}" /></option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="municipioDomicilio" class="texto-azul-bold">MUNICIPIO:</label>
									<select class="form-control" onchange="selectColonias();"
										id="municipioDomicilio" name="municipioDomicilio" required>
										<option value="">Seleccione un Estado...</option>
										<c:out value=""></c:out>

									</select>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="coloniaDomicilio" class="texto-azul-bold">COLONIA:</label>
									<select class="form-control" id="coloniaDomicilio"
										name="coloniaDomicilio" required>
										<option value="">Seleccione un Municipio...</option>
									</select>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="cpDomicilio" class="texto-azul-bold">CP:</label> <input
										name="cpDomicilio" id="cpDomicilio" type="number"
										class="form-control" required>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 col-xs-6 col-lg-6">
									<label for="calleDomicilio" class="texto-azul-bold">CALLE:
									</label> <input type="text" id="calleDomicilio" name="calleDomicilio"
										class="form-control" required />
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="numExtDomicilio" class="texto-azul-bold">NUM.
										EXT.</label> <input type="text" id="numExtDomicilio"
										name="numExtDomicilio" placeholder="S/N" class="form-control" />
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="numIntDomicilio" class="texto-azul-bold">NUM.
										INT.</label> <input type="text" id="numIntDomicilio"
										name="numIntDomicilio" class="form-control" placeholder="S/N" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="numTelFijo" class="texto-azul-bold">NUM.
										TEL. FIJO:</label> <input type="tel" id="numTelFijo" name="numTelFijo"
										class="form-control" />
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="numTelMovil" class="texto-azul-bold">NUM.
										TEL. MOVIL:</label> <input type="tel" id="numTelMovil"
										name="numTelMovil" class="form-control" />
								</div>

								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="correoe" class="texto-azul-bold">CORREO
										ELECTR�NICO:</label> <input type="email" id="correoe" name="correoe"
										class="form-control" placeholder="ejemplo@hotmail.com" />
								</div>
							</div>
							<div class="row">
								<div
									class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
									<label class="texto-blanco-bold">ESCOLARIDAD:</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="nivelAcademicoEmpleado" class="texto-azul-bold">NIVEL
										ACAD�MICO:</label> <select class="form-control"
										id="nivelAcademicoEmpleado" name="nivelAcademicoEmpleado"
										onchange="selectEspecialidadesAcademicas();" required>
										<option value="">Seleccione uno...</option>
										<c:forEach var="row" items="${listadoNivelesAcademicos}">
											<option value="<c:out value="${row.idNivelAcademico}" />"><c:out
													value="${row.nombreNivelAcademico}" /></option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="especialidadEmpleado" class="texto-azul-bold">ESPECIALIDAD:</label>
									<select class="form-control" id="especialidadEmpleado"
										name="especialidadEmpleado" required>
										<option value="">Seleccione uno...</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-2 col-lg-2">
									<label for="carreraTruncaEmpleado" class="texto-azul-bold">CARRERA
										TRUNCA:</label> <input type="checkbox" id="carreraTrunca"
										name="carreraTrunca" class="form-control" />
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="tipoPeriodoEscolar" class="texto-azul-bold">TIPO
										DE PERIODO ESCOLAR:</label> <select class="form-control"
										id="tipoPeriodoEscolar" name="tipoPeriodoEscolar"
										onchange="selectGradosEscolares();" disabled>
										<option value="">Seleccione uno...</option>
										<c:forEach var="row" items="${listadoPeriodosEscolares}">
											<option value="<c:out value="${row.idPeriodoEscolar}" />"><c:out
													value="${row.nombrePeriodoEscolar}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="ultimoGradoEmpleado" class="texto-azul-bold">�LTIMO
										GRADO:</label> <select class="form-control" id="ultimoGradoEmpleado"
										name="ultimoGradoEmpleado" disabled>
										<option value="">Seleccione tipo periodo...</option>
									</select>
								</div>
								<div class="col-md-4 col-xs-4 col-lg-4">
									<label for="tipoComprobanteEmpleado" class="texto-azul-bold">TIPO
										DE COMPROBANTE:</label> <input type="text"
										id="tipoComprobanteEmpleado" name="tipoComprobanteEmpleado"
										class="form-control" />
								</div>
								<div class="col-md-5 col-xs-5 col-lg-5">
									<label for="documentoComprobanteEmpleado"
										class="texto-azul-bold">DOCUMENTO COMPROBANTE:</label> <input
										type="file" id="documentoComprobanteEmpleado"
										name="documentoComprobanteEmpleado" class="form-control" />
								</div>
							</div>
							<div class="row">
								<div
									class="col-md-12 col-xs-12 col-lg-12 text-center div-background-black-degradado">
									<label class="texto-blanco-bold">DATOS GENERALES:</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-xs-3 col-lg-3">
									<label for="fechaAltaEmpleado" class="texto-azul-bold">FECHA
										DE ALTA:</label> <input type="text" class="form-control"
										id="fechaAltaEmpleado" name="fechaAltaEmpleado" required />
								</div>
								<div class="col-md-6 col-xs-6 col-lg-6">
									<label for="fotoEmpleado" class="texto-azul-bold">FOTOGRAF�A:</label>
									<input type="file" class="form-control" id="fotoEmpleado"
										name="fotoEmpleado" />
								</div>
							</div>
							<!--  	<div class="row">
						<div class="col-md-6 col-xs-6 col-lg-6">
							<label for="huellaEmpleado" class="texto-azul-bold">HUELLA:</label>
							<input type="file" class="form-control" id="huellaEmpleado" name="huellaEmpleado" /> 
						</div>
					</div>   -->

						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
						<button type="submit" class="btn btn-danger">Guardar
							registro</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../footer/footer.jsp"></jsp:include>

	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js" type="text/javascript"></script>

	<script src="js/listados/empleadosJS.js" type="text/javascript"></script>

</body>
</html>