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
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/base/base.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/sweetalert2.min.css" />
<link rel="stylesheet" type="text/css"
	href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/bitacora/registroAsistenciaCSS.css" />
<link rel="stylesheet" type="text/css" href="css/checador/style.css"
	media="screen" />
<c:set var="compTitle" value="Reloj checador"></c:set>
<c:set var="compTitleSing" value="Reloj checador"></c:set>
<title>Consulta de <c:out value="${compTitle}" /> | <c:out
		value="${configSistema.tituloSistema}" /></title>
</head>
<body>
	<jsp:include page="/jsp/header/header.jsp"></jsp:include>
	<div id="sky"></div>
	<div id="sun_yellow"></div>
	<div id="sun_red"></div>
	<div id="clouds"></div>
	<div id="ground"></div>
	<div id="night"></div>
	<div id="stars"></div>
	<div id="sstar"></div>
	<div id="moon"></div>
	<div id="title"></div>


	<div class="container-fluid" id="mainContainer">
		<div class="row" id="initRow"></div>
		<!-- <div class="page-header">
				<h1>
					<i class="fa fa-clock-o" aria-hidden="true"></i>
					<c:out value="${compTitle}" />
					<small><c:out value="${compTitle}" /> registra las
						asistencias de los empleados</small>
				</h1>
			</div>-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-clock-o" aria-hidden="true"></i> Control de Tiempos </a></li>
			<li class="breadcrumb-item"><a href="#"><i
					class="fa fa-list-alt" aria-hidden="true"></i> Bitacora </a></li>
			<li class="breadcrumb-item active"><i class="fa fa-clock-o"
				aria-hidden="true"></i> Reloj checador</li>
		</ol>
		<div class="container-fluid" align="center">
			<div id="contenedorTablaForm">
				<c:out value="${mensaje}" />
				<div id="clock" class="light">
					<div class="display">
						<div class="weekdays"></div>
						<div class="ampm"></div>
						<div class="alarm">
							<i class="fa fa-clock-o"></i>
						</div>
						<div class="digits"></div>
					</div>
				</div>
				<!-- Termina reloj -->
				<div id="rowForm" align="center" class="container">
					<form class="form-inline" id="formRegistros" method="post">
						<div class="form-group">
							<label class="sr-only" for="inputSku"
								class="col-sm-2 col-form-label col-form-label-lg">SKU</label>
							<div class="input-group mb-2 mr-sm-2 mb-sm-0">
								<div class="input-group-addon">
									<i class="fa fa-user" aria-hidden="true"></i>
								</div>
								<input type="number" id="inputSku" name="inputSku" max="9999"
									min="1" maxlength="4" placeholder="#SKU"
									class="form-control form-control-lg"
									aria-describedby="skuHelpInline" autofocus required> <small
									id="skuHelpInline" class="form-text text-muted"> N�mero
									de empleado. </small>
							</div>
						</div>
						<div class="form-group">
							<img src="./img/checador/operator_support_girl.png"
								class="img img-thumbnail" width="170" height="150"
								id="imgEmployee" alt="usr_img"></img>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-form-label" id="lblNombre">Nombre:</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="nombreEmpleado"></p>
							</div>
						</div>
					</form>
				</div>
				<c:choose>
					<c:when test="${fn:length(registros) gt 0}">
						<div id="rowTabla" class="table-responsive" align="center">

							<table class="table table-bordered table-hover"
								id="tablaAsistencia">
								<thead>
									<tr>
										<th><i class="fa fa-user" aria-hidden="true"></i> NOMBRE</th>
										<th><i class="fa fa-calendar" aria-hidden="true"></i>
											FECHA</th>
										<th><i class="fa fa-clock-o" aria-hidden="true"></i> HORA</th>
										<th><i class="fa fa-exchange" aria-hidden="true"></i>
											TIPO</th>
									</tr>

								</thead>
								<!-- <tfoot>
										<tr>
											<td><i class="fa fa-user" aria-hidden="true"></i> NOMBRE</td>
											<td><i class="fa fa-calendar" aria-hidden="true"></i> FECHA</td>
											<td><i class="fa fa-clock-o" aria-hidden="true"></i> HORA</td>
											<td><i class="fa fa-exchange" aria-hidden="true"></i> TIPO</td>	
										</tr>
									</tfoot>-->
								<tbody>
									<c:forEach var="row" items="${registros}">
										<tr>
											<td><c:out
													value="${row.empleado.nombreCompletoEmpleado}"></c:out></td>
											<td><c:out value="${row.fechaRegistro.fechaString}"></c:out></td>
											<td><c:out value="${row.horaRegistro.horaString}"></c:out></td>
											<td><c:out value="${row.tipoRegistro.estatusString}"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info">
							<strong>Aviso!</strong> SIN DATOS <i class="fa fa-file-o"></i>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- Cierra row contenedorTabla -->
		</div>
		<!-- Cierra row -->
	</div>
	<!-- Cierra container-fluid -->
	<jsp:include page="../footer/footer.jsp"></jsp:include>

	<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
	<script src="js/tether.min.js" type="text/javascript"></script>
	<script src="js/drop.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/base/base.js" type="text/javascript"></script>
	<script src="js/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
	<script src="js/moment-with-locales.js" type="text/javascript"></script>
	<script src="js/bitacora/registroAsistenciaJS.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.color.js"></script>
	<script type="text/javascript">
            $(function() {
                
            });
        </script>
</body>
</html>