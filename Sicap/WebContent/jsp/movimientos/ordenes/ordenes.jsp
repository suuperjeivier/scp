<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${user == null}">
	<c:redirect url="./" />
</c:if>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>			
		<!-- pageContext.request.contextPath -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		<link rel="stylesheet" href="css/jquery-ui.min.css" />
		<link rel="stylesheet" href="css/jquery-ui.theme.min.css" />
		<link rel="stylesheet"
			href="fonts/fontawesome/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
		<link rel="stylesheet" href="css/ordenes/ordenesCSS.css" />
		<c:set var="compTitle" value="Ordenes"></c:set>
		<c:set var="compTitleSing" value="Orden"></c:set>
		<title>Consulta de <c:out value="${compTitle}"/> | <c:out value="${configSistema.tituloSistema}" /></title>
	</head>
	<body>
		<jsp:include page="/jsp/header/header.jsp"></jsp:include>	
		<div class="container-fluid">
			<div class="row" id="initRow"></div>
			<div class="page-header">
				<h1>
					<i class="fa fa-search" aria-hidden="true"></i> Consulta de <c:out value="${compTitle}"/>
					<small><c:out value="${compTitle}"/> del sistema</small>
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
				<button type="button" class="btn btn-success btn-margin" data-target="#modal<c:out value="${compTitle}"/>" data-toggle="modal" data-new="1"><i class="fa fa-plus"></i> Nueva <c:out value="${compTitleSing}"/></button>
				<c:if test="${fn:length(items) gt 0}">
					<button type="button" class="btn btn-primary btn-margin" id="btnImprimir<c:out value="${compTitle}"/>"> <i class="fa fa-print"></i> Imprimir Listado</button>
				</c:if>
				<!-- Modal -->
				<div class="modal fade" id="modal<c:out value="${compTitle}"/>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg" role="document">
				    <div class="modal-content">
				      <div class="modal-header">				      	
				        <h5 class="modal-title" id="modalLabel"></h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      
				      
							<div class="modal-body" id="myWizard">          
					         <div class="progress">
					           <div class="progress-bar bg-success" role="progressbar" aria-valuenow="1" aria-valuemin="1" aria-valuemax="5" style="width: 20%;">
					             paso 1 de 4
					           </div>
					         </div>					        
					         <div class="navbar">
					            <div class="navbar-inner">
					                  <ul class="nav nav-pills nav-fill setup-panel">
					                     <li class="nav-item"><a class="nav-link active"  href="#step1" data-toggle="tab" role="tab" aria-controls="Datos de la órden" data-step="1"><i class="fa fa-file" aria-hidden="true"></i>
                        Datos de la órden </a></li>
					                     <li class="nav-item"><a class="nav-link" href="#step2" data-toggle="tab" role="tab" aria-controls="Lugares" data-step="2">Lugares</a></li>
					                     <li class="nav-item"><a class="nav-link" href="#step3" data-toggle="tab" role="tab" aria-controls="Step 3" data-step="3">Técnicos</a></li>
					                     <li class="nav-item"><a class="nav-link" href="#step4" data-toggle="tab" role="tab" aria-controls="Step 4" data-step="4">Radios</a></li>
					                     <li class="nav-item"><a class="nav-link" href="#step5" data-toggle="tab" role="tab" aria-controls="Step 5" data-step="5">Documentos</a></li>
					                  </ul>
					            </div>
					         </div>
					         <form id="form<c:out value="${compTitleSing}"/>" method="post" enctype="multipart/form-data">
					         <div class="tab-content">					         	
					            <div class="tab-pane fade show active" id="step1" role="tabpanel">					               
					            	<div class="well">
					            		<fieldset>
					            			<legend>Datos de la órden de servicio</legend>          
							              	<div class="form-group row">
												<label for="field1" class="col-4 col-form-label"><c:out value="${compTitleSing}"/>Id.</label>
												<input type="text" class="col-4 form-control" name="field1" id="field1" readonly placeholder="Id <c:out value="${compTitleSing}"/> auto">
											</div>
											<div class="form-group row">
												<label for="field2" class="col-4 col-form-label">Fecha de elaboración
													<c:out value="${compTitleSing}"/></label> <input type="text" 
													required class="col-4 form-control date" name="field2" id="field2"
													placeholder="Fecha de la <c:out value="${compTitleSing}"/>">
											</div>
											<div class="form-group row">
												<label for="field3" class="col-4 col-form-label">Número de oficio</label>
												<input type="text" class="col-4 form-control"
													name="field3" id="field3"
													placeholder="Número de oficio de la <c:out value="${compTitleSing}"/>">
											</div>
											<div class="form-group row">
												<label for="field4" class="col-4 col-form-label">Fecha de Salida</label>
												<input type="text" class="col-4 form-control date"
													name="field4" id="field4"
													placeholder="Fecha de Salida">
											</div>
							              
							              <div class="form-group row">
												<label for="field5" class="col-4 col-form-label">Fecha de Retorno</label>
												<input type="text" class="col-4 form-control date"
													name="field5" id="field5"
													placeholder="Fecha de Retorno">
											</div>											
											<div class="form-group row">
												<label for="field7" class="col-4 col-form-label">Técnico responsable</label>
												<select type="text" class="col-8 form-control"
													name="field7" id="field7">
													<option value="">Seleccione un técnico...</option>
													<c:forEach var="tecnico" items="${tecnicos}">
														<option value='<c:out value="${tecnico.tecnicoId}"></c:out>'>
															<c:out value="${tecnico.nombre}"></c:out> | <c:out value="${tecnico.licencia}"></c:out> | <c:out value="${tecnico.titulo}"></c:out>
																				| <c:out value="${tecnico.departamentoDTO.siglas}"></c:out></option>	
													</c:forEach>
												</select>
											</div>
											<div class="form-group row">
												<label for="field6" class="col-4 col-form-label">Vehiculo asignado</label>
												<select type="text" class="col-8 form-control"
													name="field6" id="field6">
													<option value="">Seleccione un vehiculo...</option>
													<c:forEach var="vehiculo" items="${vehiculos}">
														<option value='<c:out value="${vehiculo.vehiculoId}"></c:out>'><c:out value="${vehiculo.nombre}"></c:out> | <c:out value="${vehiculo.numPolizaSeguro}"></c:out></option>	
													</c:forEach>
												</select>
											</div>
											<div class="form-group row" id="divHiddenPolizaExterna" hidden>																						
												<div class="form-group ">
												<label for="fieldNewV2" class="col-6 col-form-label" required>Nombre</label>
												<input type="text" required class="col-12 form-control"
													name="fieldNewV2" id="fieldNewV2"
													placeholder="Nombre del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV3" class="col-6 col-form-label">Tipo</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV3" id="fieldNewV3"
														placeholder="Tipo del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV4" class="col-6 col-form-label">Marca</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV4" id="fieldNewV4"
														placeholder="Marca del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV5" class="col-6 col-form-label">Linea</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV5" id="fieldNewV5"
														placeholder="Linea del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV6" class="col-6 col-form-label">Modelo</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV6" id="fieldNewV6"
														placeholder="Modelo del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV7" class="col-6 col-form-label" required>Placas</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV7" id="fieldNewV7"
														placeholder="Placas del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV8" class="col-6 col-form-label">Inventario</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV8" id="fieldNewV8"
														placeholder="Inventario del vehiculo externo">
												</div>
												<div class="form-group ">
													<label for="fieldNewV9" class="col-12 col-form-label" required>Núm. póliza</label>
													<input type="text" class="col-11 form-control"
														name="fieldNewV9" id="fieldNewV9"
														placeholder="Poliza del vehiculo externo">
												</div>
											</div>											
											
											<div class="form-group row">
												<label for="field8" class="col-4 col-form-label">Chofer responsable</label>
												<select type="text" class="col-8 form-control multiple"
													name="field8" id="field8" required>
													<option value="">Seleccione un tecnico chofer...</option>
													<c:forEach var="tecnicoChofer" items="${tecnicosChoferes}">
														<option value='<c:out value="${tecnicoChofer.tecnicoId}"></c:out>'>
															<c:out value="${tecnicoChofer.nombre}"></c:out> | <c:out value="${tecnicoChofer.licencia}"></c:out> | <c:out value="${tecnicoChofer.titulo}"></c:out>
																				| <c:out value="${tecnicoChofer.departamentoDTO.siglas}"></c:out></option>	
													</c:forEach>
												</select>
											</div>
										</fieldset>
									</div>
									<button id="activate-step-2" type="button" class="btn btn-primary btn-md pointer">Siguiente</button> 
					            </div>
					            <div class="tab-pane fade" id="step2" role="tabpanel">
					               <div class="well">
					               <h2>LUGARES</h2>
					               	<div class="container col-xs-12">
								        <div class="row clearfix">
										    <div class="col-md-12 column">
											    <table class="table table-bordered table-hover" id="tab_logic">
													<thead>
														<tr >
															<th>&times;</th>
															<th>#</th>
															<th class="text-center" width="270px">
																LUGAR
															</th>
															<th class="text-center">
																ACTIVIDAD(ES)
															</th>
														</tr>
													</thead>
													<tfoot>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</tfoot>
													<tbody>
														<tr id='addr0'>
															<td></td>
															<td>
																1
															</td>
															<td>
																<div class="form-group row">
																	
																	<select type="text" class="col-11 form-control"
																		name="fieldLugares[]" id="fieldPlaces0">
																		<option value="">Seleccione un lugar de trabajo...</option>
																		<c:forEach var="lugar" items="${lugares}">
																			<option value='<c:out value="${lugar.lugarId}"></c:out>'><c:out value="${lugar.nombre}"></c:out></option>	
																		</c:forEach>
																	</select>
																</div>
															</td>
															<td>
																<div class="form-group row">																	
																	<textarea type="text" class="col-11 form-control"
																		name="fieldCommentsLugares[]" id="commentPlace0"
																		placeholder="Actividad(es), escriba lo más relevante" rows="4" required></textarea>
																</div>															
															</td>
														</tr>									                    
													</tbody>
											    </table>
										    </div>
									    </div>
									    <div class="row">
									    	<div class="col-7">									   			
									   		</div>
									   		<div class="col-5">
									   			<a id="add_row" class="btn btn-success pull-left">Agregar linea</a>
									   			<a id='delete_row' class="btn btn-danger pull-right">Borrar linea</a>
									   		</div>
									   	</div>
								    </div>						    
					               </div><!-- well 2 -->
					               <a class="btn btn-default next" role="button" type="button" href="#">Continuar</a>
					            </div>
					            <div class="tab-pane fade" id="step3" role="tabpanel">
					              	<div class="well">
					              		<h2>TECNICOS</h2>
					              		<div class="container col-xs-12">
									        <div class="row clearfix">
											    <div class="col-md-12 column table-responsive">
												    <table class="table table-bordered table-hover table-condensed" id="tab_logic_tec">
														<thead>
															<tr >
																<th>&times;</th>
																<th>#</th>
																<th class="text-center">
																	TECNICOS(S)
																</th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<td></td>
																<td></td>															
																<td></td>
															</tr>
														</tfoot>
														<tbody>
															<tr id='addr0'>
																<td></td>
																<td>
																	1
																</td>
																<td>
																	<div class="form-group row">																	
																		<select type="text" class="col-11 form-control"
																			name="fieldTec[]" id="fieldTec0">
																			<option value="">Seleccione un tecnico auxiliar...</option>
																			<c:forEach var="tecnico" items="${tecnicos}">
																				<option value='<c:out value="${tecnico.tecnicoId}"></c:out>'>
																				<c:out value="${tecnico.nombre}"></c:out>
																				 | <c:out value="${tecnico.titulo}"></c:out>
																				  | <c:out value="${tecnico.departamentoDTO.siglas}"></c:out>
																				</option>	
																			</c:forEach>
																		</select>
																	</div>
																</td>															
															</tr>									                    
														</tbody>
												    </table>
											    </div>
										    </div>
										    <div class="row">
										    	<div class="col-7">										   			
										   		</div>
										   		<div class="col-5">
										   			<a id="add_row_tec" class="btn btn-success pull-left">Agregar linea</a>
										   			<a id='delete_row_tec' class="btn btn-danger pull-right">Borrar linea</a>
										   		</div>
										   	</div>
								    	</div>								    	
									</div><!-- well 2 -->	
									<a class="btn btn-default next" role="button" type="button" href="#">Continuar</a>				              	
					            </div>
					            <div class="tab-pane fade" id="step4" role="tabpanel">
									<div class="well">
										<h2>RADIOS</h2>									
										<div class="container col-xs-12">
											<div class="row clearfix">
												<div class="col-md-12 column table-responsive">
													<table class="table table-bordered table-hover table-condensed"	id="tab_logic_rad">
														<thead>
															<tr>
																<th></th>
																<th>#</th>
																<th class="text-center">RADIO(S)</th>
															</tr>
														</thead>
														<tfoot>
															<tr>
																<td></td>
																<td></td>
																<td></td>
															</tr>
														</tfoot>
														<tbody>
															<tr id='addr0'>
																<td></td>
																<td>1</td>
																<td>
																	<div class="form-group row">
																		<select type="text" class="col-11 form-control"
																			name="fieldRad[]" id="fieldRad0">
																			<option value="">Seleccione radio de
																				trabajo...</option>
																			<c:forEach var="radio" items="${radios}">
																				<option
																					value='<c:out value="${radio.radioId}"></c:out>'><c:out
																						value="${radio.tecnicoDTO.nombre}"></c:out> | <c:out
																						value="${radio.tecnicoDTO.titulo}"></c:out> | <c:out
																						value="${radio.tecnicoDTO.departamentoDTO.siglas}"></c:out> | <c:out
																						value="${radio.rfsi}"></c:out></option>
																			</c:forEach>
																		</select>
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<div class="row">
												<div class="col-7">													
												</div>
												<div class="col-5">
													<a id="add_row_rad" class="btn btn-success pull-left">Agregar
														linea</a> <a id='delete_row_rad'
														class="btn btn-danger pull-right">Borrar linea</a>
												</div>
											</div>
										</div>
									</div>	<!-- well -->	
									<a class="btn btn-default next" role="button" type="button" href="#">Continuar</a>
								</div>								
					             <div class="tab-pane fade" id="step5" role="tabpanel">
					              <div class="well"> <h2>DOCUMENTOS</h2> 
					              	<div class="container">
								      <div class="panel panel-default">
								        <div class="panel-heading"><strong>1. Sube los archivos:</strong> <small> Para subir los archivos de click en el botón debajo y seleccione los que desee cargar. Pueden ser multiples archivos</small></div>
								        <div class="panel-body">								
								          <!-- Standar Form -->	         
								            <div class="form-inline">
								            	<div class="form-group col-12">
													<div class="col-12">
														<h4>Selecciona los archivos</h4>
														<div class="input-group">
															<label class="input-group-btn"> 
																<span class="btn btn-primary"> Elegir archivos&hellip; 
																	<input type="file" class="form-control-file" style="display: none;" name="files" id="js-upload-files" multiple="multiple">
																</span>
															</label>
															<input type="text" class="form-control" readonly>
														</div>
														<span class="help-block">Puede seleccionar más de un solo archivo, recuerde que el nombre de cada archivo no debe de exceder de 250 caracteres! <i class="fa fa-warning"></i></span>
													</div>
												</div>
								              <!-- <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload files</button>-->
								            </div>							         
										
								          <!-- Drop Zone -->
								          <!--  <h4>Or drag and drop files below</h4>
								          <div class="upload-drop-zone" id="drop-zone">
								            Just drag and drop files here
								          </div>-->
								
								          <!-- Progress Bar -->
								          <!--  <div class="progress">
								            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
								              <span class="sr-only">60% Complete</span>
								            </div>
								          </div>-->
								
								          <!-- Upload Finished -->
								          <div class="js-upload-finished">
								            <h3>Archivos cargados <i class="fa fa-upload"></i></h3>
								            <div class="list-group" id="documentos">								              
								            </div>
								          </div>
								        </div>
								      </div>
								    </div> <!-- /container -->
					              </div>
					              <!-- <a class="btn btn-success first" href="#" role="button">Comenzar</a>-->
					              <button type="submit" class="btn btn-lg btn-success pointer" id="btnGuardar">Guardar Orden</button>
					            </div>					            
					         </div><!-- tab content -->
					         </form>
					        </div>								
							<!--<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary" id="btnGuardar<c:out value="${compTitleSing}"/>">Guardar <c:out value="${compTitleSing}"/></button>
							</div>-->
						
				    </div>
				  </div>
				</div>
			</div>
			<div id="contenedorTabla">
			<c:out value="${mensaje}" />	
			<c:choose>
				<c:when  test="${fn:length(ordenes) gt 0}">				
					<div class="table-responsive rowInicial2" id="divTabla<c:out value="${compTitle}"/>">
						<table id="tb_lista<c:out value="${compTitle}"/>" class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>No. Orden</th>
								<th>Fecha Elaboracion:</th>
								<th width="12%">No Oficio:</th>
								<th>Fecha Salida:</th>
								<th>Fecha Llegada:</th>
								<th>Vehiculo:</th>
								<th>Número de poliza de seguro</th>
								<th>Tecnico(s):</th>
								<th>Licencia</th>
								<td>Imprimir</td>
								<td>Editar</td>
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
								<td></td>
								<td></td>	
								<td></td>							
							</tr>
						</tfoot>
						<tbody>
							<c:set var="i" value="1"></c:set>
							<c:forEach var="orden" items="${ordenes}">
								<tr>
									<td><c:out value="${i}" /></td>
									<td><c:out value="${orden.orderId}" /></td>
									<td><c:out value="${orden.fecha.fechaString}" /></td>
									<td><c:out value="${orden.noOficio}" /></td>
									<td><c:out value="${orden.fechaSalida.fechaString}" /></td>									
									<td><c:out value="${orden.fechaLlegada.fechaString}" /></td>
									<td><c:out value="${orden.vehiculoDTO.nombre}" /></td>
									<td><c:out value="${orden.vehiculoDTO.numPolizaSeguro}" /></td>
									<td><c:out value="${orden.tecnicoDTO.nombre}" /></td>
									<td><c:out value="${orden.tecnicoChoferDTO.nombre}"></c:out> | <c:out value="${orden.tecnicoChoferDTO.licencia}"></c:out></td>
									<td><form action="./documentos?action=reportesJasper" method="post">
										<input type="hidden" class="form-control" name="numero_orden" id="numero_orden_<c:out value='${orden.orderId}' />" placeholder="Número de orden" value="<c:out value='${orden.orderId}' />"></input>
										  <button type="submit" name="tipo_doc" value="oficio" class="btn-link"><i class="fa fa-file"></i></button>
										&nbsp;<button type="submit" name="tipo_doc" value="orden_doble" class="btn-link"><i class="fa fa-file"></i><i class="fa fa-file"></i></button>										
										</form>
									</td>	
									<td><a role="button" data-target="#modal<c:out value="${compTitle}"/>" data-toggle="modal" data-new="0"
										data-field1="<c:out value='${orden.orderId}' />"
										data-field2="<c:out value='${orden.fecha.fechaString}' />"										
										data-field3="<c:out value='${orden.noOficio}' />"
										data-field4="<c:out value='${orden.fechaSalida.fechaString}' />"
										data-field5="<c:out value='${orden.fechaLlegada.fechaString}' />"
										data-field6="<c:out value='${orden.vehiculoDTO.vehiculoId}' />"
										data-field7="<c:out value='${orden.tecnicoDTO.tecnicoId}' />"
										data-field8="<c:out value='${orden.tecnicoChoferDTO.tecnicoId}' />"
									><i class="fa fa-pencil"
										title="Editar <c:out value="${compTitleSing}"/> | <c:out value='${orden.orderId}' />"></i></a></td>
								</tr>	
								
								<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:when>
				<c:otherwise>
					<div class="alert alert-info">
					  <strong>Sin registros!</strong> La consulta solicitada ha retornado ningun resultado.
					</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<jsp:include page="../../footer/footer.jsp"></jsp:include>
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
		<script src="js/jquery-ui.min.js" type="text/javascript"></script>
		<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="js/dataTables.bootstrap4.min.js" type="text/javascript"></script>		
		<script src="js/ordenes/ordenesJS.js" type="text/javascript"></script>
	
	</body>
</html>