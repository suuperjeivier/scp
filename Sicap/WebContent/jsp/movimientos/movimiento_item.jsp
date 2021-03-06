
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
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<meta name="description" content="Pagina para consulta de lista de asistencia" />
		<meta name="author" content="Sistemas C4 Tabasco">
		<link rel="manifest" href="./web_app_manifest.json">
		<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/base/base.css" rel="stylesheet">
		<link rel="stylesheet" href="css/sweetalert2.min.css" />
		<link rel="stylesheet" href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
		
		<title>Asignación RFSI | <c:out value="${configSistema.tituloSistema}" /></title>
	</head>
	<body>
		<jsp:include page="/jsp/header/header.jsp"></jsp:include>	
		<div class="container-fluid">
			<div class="row" id="initRow"></div>
			<div class="page-header">
				<h1>
					<i class="fa fa-search" aria-hidden="true"></i> Asignación RFSI
					<small>Asignación de RFSI a un radio</small>
				</h1>
			</div>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"><i
						class="fa fa-clock-o" aria-hidden="true"></i> Asignación </a></li>
				<li class="breadcrumb-item active"><i class="fa fa-search"
					aria-hidden="true"></i> Asignación</li>
			</ol>			
			<div class="row">
				<button type="button" class="btn btn-success btn-margin" data-target="#modalMovItem" data-toggle="modal" data-editar="0" data-nombreitemconf ='<c:out value="${tipoItem.nombreTipoItemSingular}"/>' data-tipoitemconf = '<c:out value="${tipoItem.idTipoItem}" />'>
					<i class="fa fa-plus"></i> Nuevo
				</button>
				<c:if test="${fn:length(movimientoItem) gt 0}">
					<button type="button" class="btn btn-primary btn-margin" id="btnImprimirMovItem"> <i class="fa fa-print"></i> Imprimir Listado</button>
				</c:if>
				<!-- Modal -->
				 <div class="modal fade" id="modalMovItem" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="modalLabel"></h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form id="formMovItems">
							<div class="modal-body">
								<div class="row">
									<div class="col-6">
										<div class="form-group">
											<label for="rfsi">RFSI</label> 
											<input type="number" class="form-control" name="rfsi" id="rfsi" placeholder="Ej. 270000000" required>
										</div>
									</div>
									<div class="col-6">
										<div class="form-group" id="divRadios">
											<label for="itemRadio">Radio:</label> 
											<select class="form-control" id="itemRadio" name="itemRadio" required>
												<option value="" selected>SELECCIONE...</option>
												<c:forEach var="itemRadio" items="${listado_itemRadio}">
													<option value='<c:out value="${itemRadio.idItem}"></c:out>'><c:out value="${itemRadio.noSerieItem}"></c:out></option>
												</c:forEach>
											</select>
										</div>									
									</div>
								</div>
								<div class="row">
									<div class="form-group col-6">
										<label for="itemRfsi">Cargador:</label> <select
											class="form-control" id="itemRfsi" name="item">
											<option value="" selected>SELECCIONE...</option>
											<c:forEach var="cargador" items="${listado_cargadores}">
												<option
													value='<c:out value="${cargador.idItem}"></c:out>'><c:out
														value="${cargador.noSerieItem}"></c:out></option>
											</c:forEach>
										</select>
										<!--  <input type="hidden" id="idHorarioHidden" name="idHorarioHidden" />
											<input type="hidden" id="cveHorarioHidden" name="cveHorarioHidden"> -->
									</div>
									<div class="form-group col-6">
										<label for="itemRfsi">Unidad:</label> <select
											class="form-control" id="itemRfsi" name="item">
											<option value="" selected>SELECCIONE...</option>
											<c:forEach var="unidad" items="${listado_vehiculos}">
												<option
													value='<c:out value="${unidad.idItem}"></c:out>'><c:out
														value="${unidad.placasItem}"></c:out></option>
											</c:forEach>
										</select>
										<label for="itemRfsi">GPS:</label> <select
											class="form-control" id="itemRfsi" name="item">
											<option value="" selected>SELECCIONE...</option>
											<c:forEach var="gps" items="${listado_gpss}">
												<option
													value='<c:out value="${gps.idItem}"></c:out>'><c:out
														value="${gps.noSerieItem}"></c:out></option>
											</c:forEach>
										</select>
										<!--  <input type="hidden" id="idHorarioHidden" name="idHorarioHidden" />
											<input type="hidden" id="cveHorarioHidden" name="cveHorarioHidden"> -->
									</div>
								</div>								
								<div class="row">
								<c:forEach var="accesorio" items="${accesorios}">
									<label class="custom-control custom-checkbox">
									  <input type="checkbox" class="custom-control-input" name="accesorios[]" value="<c:out value="${accesorio.id}"></c:out>">
									  <span class="custom-control-indicator"></span>
									  <span class="custom-control-description"><c:out value="${accesorio.nombre}"></c:out></span>
									</label>
								</c:forEach>	
								</div>							
								<div class="row">
									<div class="form-group" id="divRadios">
										<label for="textAreaComment">Comentarios:</label> 
										<textarea class="form-control" id="textAreaComment" name="textAreaComment" rows="3" cols="44"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary" id="">Guardar cambios</button>
							</div>
						</form>
				    </div>
				  </div>
				</div> 
			</div> 
			<div id="contenedorTablaMovItem">
			<c:out value="${mensaje}" />	
			<c:if test="${fn:length(movimientoItem) gt 0}">
				<div class="table-responsive rowInicial2" id="divTablaMovientosItems">
					<table id="tb_listamovitem" class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>RFSI:</th>
								<th>Radio:</th>
								<th>Comentarios:</th>
								<th>Estatus:</th>
								<th>Editar:</th>
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
						<!-- 	<c:set var="i" value="1"></c:set> -->
							<c:forEach var="row" items="${movimientoItem}">
								<tr>
								<!--  	<td><c:out value="${i}" /></td>-->
									<td><c:out value="${row.idMovimientoItem}" /></td>
									<td><c:out value="${row.fkIdMovimiento.skuMovimiento}" /></td>
									<td><c:out value="${row.fkIdItem.noSerieItem}" /></td>
									<td><c:out value="${row.fkIdMovimiento.comments}" /></td>
									<td><c:out value="${row.statusMovimientoItem}" /></td>
									<td><a><i class="fa fa-pencil"></i></a></td>
								</tr>	
								<!-- <c:set var="i" value="${i+1}"></c:set> -->
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			</div>
		</div>
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
		<script src="js/movimientos/movItemsJS.js" type="text/javascript"></script>
	
	</body>
</html>