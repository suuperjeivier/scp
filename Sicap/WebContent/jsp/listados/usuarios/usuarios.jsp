
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${user == null}">
	<c:redirect url="./" />
</c:if>
<c:set var="title" value="usuarios"></c:set>
<c:set var="titleSingular" value="usuario"></c:set>
<c:set var="icon1" value="fa-user"></c:set>
<c:set var="icon2" value="fa-search"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />		
		<!-- pageContext.request.contextPath -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<meta name="description"
			content="Pagina para consulta de lista de <c:out value='${title}'/>" />
		<meta name="author" content="Sistemas C4 Tabasco">
		<link rel="manifest" href="./web_app_manifest.json">
		<link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/base/base.css" rel="stylesheet">
		<link rel="stylesheet" href="css/sweetalert2.min.css" />
		<link rel="stylesheet"
			href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/jquery-ui.min.css" />
		<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />
		<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
		<link rel="stylesheet" href="css/listados/usuarios/usuariosCSS.css" />
		
		<title>Consulta de <c:out value='${title}'/> | <c:out value="${configSistema.tituloSistema}" /></title>
	</head>
	<body>
		<jsp:include page="/jsp/header/header.jsp"></jsp:include>	
		<div class="container-fluid">
			<div class="row" id="initRow"></div>
			<div class="page-header">
				<h1>
					<i class="fa <c:out value='${icon1}'/>" aria-hidden="true"></i> Consulta de <c:out value='${title}'/>
					 <small><c:out value='${title}'/></small>
				</h1>
			</div>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"><i
						class="fa <c:out value='${icon1}'/>" aria-hidden="true"></i> <c:out value='${title}'/> </a></li>
				<li class="breadcrumb-item active"><i class="fa <c:out value='${icon2}'/>"
					aria-hidden="true"></i> Consulta</li>
			</ol>
			
			<div class="row mb-4">				
				<button type="button"
						class="btn btn-success pointer ml-3"
						data-toggle="modal" data-new="1"
						data-target="#registroUsuario">
						<i class="fa fa-plus"></i> Nuevo <c:out value='${titleSingular}'/>
					</button>
				<!-- Modal -->
				<div class="modal fade" id="registroUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Nuevo usuario</h5>
				        <img id="imgPerfil" class="rounded float-right img-thumbnail" src="#" alt="imagen de perfil" />
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <form id="formUsuario" role="form" accept-charset="utf-8"><!-- enctype="multipart/form-data" -->				      	
					      <div class="modal-body">				
					      <input type="hidden" val="" name="idUserHidden" id="idUserHidden">	        
							  <div class="form-group">
							    <label for="login" class="control-label">Usuario:</label>
							    <div class="input-group">							  	
	 								<span class="input-group-addon" id="sizing-addon00"><i class="fa fa-user-o" aria-hidden="true"></i></span>
							    	<input type="text" class="form-control" id="login" required name="login" placeholder="Usuario">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="config" class="control-label">Perfil:</label>
							    <div class="input-group">							  	
	 								<span class="input-group-addon" id="sizing-addon01"><i class="fa fa-group" aria-hidden="true"></i></span>
								    <select class="form-control form-control" required name="perfil" id="perfil">
									  <option value="" selected>Seleccione...</option>
									  <c:forEach var="perfil" items="${perfiles}">
									  	<option value="<c:out value='${perfil.idPerfil}'/>" ><c:out value="${perfil.description}"/></option>
									  </c:forEach>
									</select>
								</div>
							  </div>
							  <div class="form-group">
							    <label for="config" class="control-label">Servicio inicial:</label>
							    <div class="input-group">							  	
	 								<span class="input-group-addon" id="sizing-addon1"><i class="fa fa-cog fa-spin" aria-hidden="true"></i></span>
								    <select class="form-control form-control" required name="initService" id="initService">
									  <option value="" selected>Seleccione...</option>
									  <c:forEach var="initService" items="${initServices}">
									  	<option value="<c:out value='${initService.idUserConfig}'/>" ><c:out value="${initService.nombre}"/></option>
									  </c:forEach>
									</select>
								</div>
							  </div>
							  <div class="form-group">
							  	<label for="name" class="control-label">Nombre provisional:</label>
							  	<div class="input-group">
							  		<span class="input-group-addon" id="sizing-addon2"><i class="fa fa-hand-peace-o" aria-hidden="true"></i></span>								    
								    <input type="text" class="form-control" id="name" name="name" placeholder="Nombre prov.">
								 </div>
							  </div>
							  <div class="form-group">							  	
							  	<label for="email" class="control-label">Email:</label>
							  	<div class="input-group">							  	
	 								<span class="input-group-addon" id="sizing-addon3"><i class="fa fa-envelope" aria-hidden="true"></i></span>							    
								    <input type="email" class="form-control" id="email" name="email" required placeholder="ejemplo@c4tabasco.gob.mx">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="timeOut" class="control-label">Session TimeOut:</label>
							    <div class="input-group">
							  		<span class="input-group-addon" id="sizing-addon2"><i class="fa fa-clock-o" aria-hidden="true"></i></span>
							    	<input type="number" defaultValue="1299" val="1299" min="-1" max="99999" step="100" required class="form-control" id="timeOut" name="timeOut" >
							    </div>
							  </div>
							  <div class="form-group">
							  	<div class="input-group">
							  		<span class="input-group-addon" id="sizing-addon2">
								  		<i class="fa fa-question-circle-o" aria-hidden="true"></i> 
								  		<label class="custom-control custom-checkbox">								  			
										  <span class="custom-control-description">Activo:</span>
										  <input type="checkbox" class="custom-control-input" id="activo" name="activo" value="Y">
										  <span class="custom-control-indicator"></span>									  
										</label>
									</span>								   
								</div>
							  </div>
							  <div class="form-group">
							    <label for="avatar" class="control-label">Imagen de perfil:</label>
							    <div class="input-group">
							  		<span class="input-group-addon" id="sizing-addon2"><i class="fa fa-picture-o" aria-hidden="true"></i></span>
							    	<input type="file" class="form-control" accept="image/*" onchange="readURL(this);" id="userAvatar0" name="userAvatar" >
							    </div>							   
							  </div>
							  <div class="form-group">
							    <label for="config" class="control-label" required>Empleado:</label>
							     <div class="input-group">
							  		<span class="input-group-addon" id="sizing-addon2"><i class="fa fa-user-circle-o" aria-hidden="true"></i></span>
								    <select class="form-control form-control" name="idEmpleado" id="idEmpleado">
									  <option value="" selected>Seleccione...</option>
									  <c:forEach var="empleado" items="${empleados}">
									  	<option value="<c:out value='${empleado.idEmpleado}'/>" ><c:out value="${empleado.nombreCompletoEmpleado}"/></option>
									  </c:forEach>
									</select>
								</div>
							  </div>							
					      </div>
					      <div class="modal-footer">
					        <button type="reset" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
					        <button type="submit" class="btn btn-primary">Guardar cambios</button>
					      </div>
				      </form>
				    </div>
				  </div>
				</div>
				<c:if test="${fn:length(elementos) gt 0}">
					<button type="button" class="btn btn-primary pointer ml-3" id="btnImprimirHorarios"> <i class="fa fa-file-excel-o"></i> Exportar tabla</button>
				</c:if>
			</div><!-- TERMINA ROW -->
			<div id="contenedorTabla" class="row">			
			<c:choose>
				<c:when test="${fn:length(elementos) gt 0}"> 
					<div class="col-lg-12">
						<div class="table-responsive rowInicial2" id="divTablaHorarios">
							<table id="tb_listado" class="table table-striped table-bordered table-hover table-condensed">
								<thead>
									<tr>
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> #</th>										
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> id</th>
										<th><i class="fa fa-user" aria-hidden="true"></i> Login</th>
										<th><i class="fa fa-user" aria-hidden="true"></i> Empleado</th>
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> pwd</th>
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> email</th>
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> perfil</th>
										<th><i class="fa fa-phone-square" aria-hidden="true"></i> imagen</th>
										<th><i class="fa fa-edit" aria-hidden="true"></i> editar</th>
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
									<c:forEach var="row" items="${elementos}">
										<tr>
											<td><c:out value="${i}" /></td>										
											<td><c:out value="${row.userId}" /></td>
											<td><c:out value="${row.userName}" /></td>
											<td><c:out value="${row.nombreCompletoEmpleado}" /></td>
											<td><c:out value="${row.password}" /></td>
											<td><c:out value="${row.userEmail}" /></td>
											<td><c:out value="${row.perfil.description}" /></td>
											<td>
												<c:choose>
												    <c:when test="${empty row.imgAvatarDTO.imgStringBase64}">
												        <img alt="img_avatar" src="img/users/default_image.png" class="rounded float-right img-thumbnail" width="60px" height="45px">
												    </c:when>
												    <c:otherwise>
												        <img alt="img_avatar" class="rounded float-right img-thumbnail" src="<c:out value="${row.imgAvatarDTO.imgStringBase64}" />" width="70px" height="57px">
												    </c:otherwise>
												</c:choose>
												
											</td>
											<td>
												<a href="#registroUsuario" data-toggle="modal" data-new="0" data-target="#registroUsuario"
													data-userid="<c:out value='${row.userId}' />"
													data-username="<c:out value='${row.userName}' />"
													data-idperfil="<c:out value='${row.perfil.idPerfil}' />"
													data-iduserconfig="<c:out value='${row.userConfigDTO.idUserConfig}' />"
													data-name="<c:out value='${row.provName}' />"
													data-email="<c:out value='${row.userEmail}' />"
													data-timeout="<c:out value='${row.sessionTimeOut}' />"
													data-active="<c:out value='${row.active}' />"
													data-image="<c:out value='${row.imgAvatarDTO.imgStringBase64}' />"
													data-employee="<c:out value='${row.idEmpleado}' />"
													data-employee-name="<c:out value='${row.nombreCompletoEmpleado}' />"													
												><i class="fa fa-edit pointer" aria-hidden="true" ></i></a>
											</td>
										</tr>	
										<c:set var="i" value="${i+1}"></c:set>
									</c:forEach>
								</tbody>
								
							</table>
						</div>
					</div>					
				</c:when>
				<c:otherwise>
					 <div class="alert alert-info">
						<strong>Aviso!</strong> SIN DATOS <c:out value="${mensaje}" /><i class="fa fa-file-o"></i>
					</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<jsp:include page="../../footer/footer.jsp"></jsp:include>		
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
		<script src="js/jquery-ui.min.js" type="text/javascript"></script>
		<script src="js/chartJS.js" type="text/javascript"></script>
		<script src="js/listados/usuariosJS.js" type="text/javascript"></script>		
	</body>
</html>