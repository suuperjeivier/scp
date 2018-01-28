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
<c:set var="compTitle" value="Lugares"></c:set>
<c:set var="compTitleSing" value="Lugar"></c:set>
<title>Consulta de <c:out value="${compTitle}" /> | <c:out
		value="${configSistema.tituloSistema}" /></title>
<style>
#map {
	width: 100%;
	height: 400px;
	background-color: grey;
}
</style>
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
			<c:if test="${fn:length(items) gt 0}">
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
								<input type="hidden" name="itemDesc" id="itemDesc"
									value="<c:out value="${compTitleSing}"/>"></input>
								<div class="form-group row">
									<label for="field1" class="col-2 col-form-label"><c:out
											value="${compTitleSing}" /> Id.</label> <input type="text"
										class="col-6 form-control" name="field1" id="field1" readonly
										placeholder="Id <c:out value="${compTitleSing}"/> auto">
								</div>
								<div class="form-group row">
									<label for="field2" class="col-2 col-form-label">Nombre
										<c:out value="${compTitleSing}" />
									</label> <input type="text" required class="col-10 form-control"
										name="field2" id="field2"
										placeholder="Nombre del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field3" class="col-2 col-form-label">Ubicacion</label>
									<input type="text" class="col-10 form-control" name="field3"
										id="field3"
										placeholder="Ubicacion del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<div id="current"></div>
									<div id="map"></div>
								</div>
								<div class="form-group row">
									<label for="field5" class="col-2 col-form-label">LAT:</label> <input
										type="text" class="col-4 form-control" name="field5"
										id="field5"
										placeholder="lAT. del <c:out value="${compTitleSing}"/>">
									<label for="field6" class="col-2 col-form-label">LONG:</label>
									<input type="text" class="col-4 form-control" name="field6"
										id="field6"
										placeholder="lONG. del <c:out value="${compTitleSing}"/>">
								</div>
								<div class="form-group row">
									<label for="field4" class="col-2 col-form-label">Tipo</label> <input
										type="text" class="col-6 form-control" name="field4"
										id="field4"
										placeholder="Tipo del <c:out value="${compTitleSing}"/>">
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
		<div id="contenedorTabla">
			<c:out value="${mensaje}" />
			<c:choose>
				<c:when test="${fn:length(items) gt 0}">
					<div class="table-responsive rowInicial2"
						id="divTabla<c:out value="${compTitle}"/>">
						<table id="tb_lista<c:out value="${compTitle}"/>"
							class="table table-striped table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th>#</th>
									<th>ID:</th>
									<th width="12%">NOMBRE:</th>
									<th>UBICACION:</th>
									<td>TIPO:</td>
									<td>EDITAR:</td>
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
								<c:forEach var="row" items="${items}">
									<tr>
										<td><c:out value="${i}" /></td>
										<td><c:out value="${row.lugarId}" /></td>
										<td><c:out value="${row.nombre}" /></td>
										<td><c:out value="${row.ubicacion}" /></td>
										<td><c:out value="${row.tipo}" /></td>
										<td><a role="button"
											data-target="#modal<c:out value="${compTitle}"></c:out>"
											data-toggle="modal" data-new="0"
											data-field1="<c:out value='${row.lugarId}' />"
											data-field2="<c:out value='${row.nombre}' />"
											data-field3="<c:out value='${row.ubicacion}' />"
											data-field4="<c:out value='${row.tipo}' />"><i
												class="fa fa-pencil"
												title="Editar <c:out value="${compTitleSing}"/> - <c:out value='${row.lugarId}' />"></i></a></td>
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
	<jsp:include page="../../../footer/footer.jsp"></jsp:include>
	<script type="text/javascript">
			var elemento = '<c:out value="${compTitleSing}"/>';
			var elementos = '<c:out value="${compTitle}"/>';
		</script>
	<script>
			var map;
			var currCenter;
	      function initMap() {
	        var uluru = {lat: 17.977536, lng: -92.936984};
	        map = new google.maps.Map(document.getElementById('map'), {
	          zoom: 8,
	          center: uluru,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        });
	        var imageA = { 
	        	url: 'img/icons/torre_principal.png',
	        	scaledSize: new google.maps.Size(50, 50), // scaled size
	            //origin: new google.maps.Point(0,0), // origin
	            //anchor: new google.maps.Point(0, 0) // anchor
	        };
	        var imageB = { 
		        url: 'img/icons/antena_b.png',
		        scaledSize: new google.maps.Size(50, 55), // scaled size
		        origin: new google.maps.Point(0,0), // origin
		        anchor: new google.maps.Point(0, 0) // anchor      		
		    };
	        var marker = new google.maps.Marker({
	          position: uluru,
	          map: map,
	          label: 'A',
	          icon: imageA,
	          title: 'C4 Tabasco'
	        });    
	        marker.addListener('click', function() {
	            infowindow.open(map, marker);
	          });
	        currCenter = map.getCenter();
	        
	        line = new google.maps.Polyline({
                path: [uluru, uluru],
                strokeOpacity: 1,
                strokeWeight: 2,
                strokeColor: '#f00',
                geodesic: true                    
            });
            line.setMap(map);
	        var contentString = '<div id="content">'+
	        '<div id="siteNotice">'+
	        '</div>'+
	        '<h1 id="firstHeading" class="firstHeading">C4</h1>'+
	        '<div id="bodyContent">'+
	        '<p><b>C4</b> Tabasco</b>, '+
	        'Sitio principal'+	       
	        '</div>'+
	        '</div>';
		    var infowindow = new google.maps.InfoWindow({
		      content: contentString
		    });
		    
		    marker2 = new google.maps.Marker({
		        map: map,
		        draggable: true,
		        label: 'B',
		        animation: google.maps.Animation.DROP,
		        position: uluru,
		        icon: imageB,
		    	title: 'Nuevo sitio'
		      });
		    
		    /*google.maps.event.addListener(myMarker, 'dragend', function(evt){
		        document.getElementById('current').innerHTML = '<p>Marker dropped: Current Lat: ' + evt.latLng.lat().toFixed(3) + ' Current Lng: ' + evt.latLng.lng().toFixed(3) + '</p>';
		    });*/

		    marker2.addListener('dragstart', function(evt){
		    	//document.getElementById('current').innerHTML = '<p>Currently dragging marker...</p>';
		    });
		    marker2.addListener('drag', function(evt){
		    	var title = this.title,                
                oldLatLng = uluru,
                newLatLng = new google.maps.LatLng(this.getPosition().lat(), this.getPosition().lng());
            	line.setPath([oldLatLng, newLatLng]);
            	 document.getElementById("field5").value = this.getPosition().lat();//event.latLng.lat().toFixed(3);
 				document.getElementById("field6").value = this.getPosition().lng();
		    });
			marker2.addListener('dragend', function(event) {				
			    document.getElementById("field5").value = this.getPosition().lat();//event.latLng.lat().toFixed(3);
				document.getElementById("field6").value = this.getPosition().lng();//event.latLng.lng().toFixed(3);
			});
			  
		    marker2.addListener('click', toggleBounce);
	        
	      }
	      function toggleBounce() {
	    	  if (marker2.getAnimation() !== null) {
	    	    marker2.setAnimation(null);
	    	  } else {
	    	    marker2.setAnimation(google.maps.Animation.BOUNCE);
	    	  }
	    	}
	      /*google.maps.event.addDomListener(window, 'load', initialize);
	      google.maps.event.addDomListener(window, "resize", resizingMap());*/
	    </script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbOGS3ow3e91WC9f8Locl-nk5RJDaHjJ4&callback=initMap">
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
	<script src="js/ordenes/items_ordenes.js" type="text/javascript"></script>

</body>
</html>