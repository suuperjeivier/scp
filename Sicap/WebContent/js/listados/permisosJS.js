$(function(){		
	/**
	 * Formato para los inputs de tipo hora
	 * 
	 */
	$(".horaF24").inputmask({
		mask: "h:s:s",
		placeholder: "__:__:__",
		alias: "datetime",
		hourFormat: "24",
		showMaskOnHover: false
	});


	$(function(){
		$('#formFiltrosPermisos').on('submit',function(event){
			event.preventDefault() ;
			event.stopPropagation();
			selectPermisosFiltros();
		})
	});

	function selectPermisosFiltros(){	
		$('#tablaPrincipal').empty();
		var tabla = '<table id="tb_listahorarios" class="table table-striped table-bordered table-condensed">'
			+'<thead>'
			+	'<tr>'
			+		'<th>#</th>'
			+		'<th width="12%">NOMBRE EMPLEADO:</th>'
			+		'<th>DE:</th>'
			+		'<th>A:</th>'
			+		'<th>MOTIVO:</th>'
			+		'<th>DESCRIPCI&Oacute;N:</th>'						
			+		'<th>ESTATUS:</th>'
			+		'<th>EDITAR</th>'
			+	'</tr>'
			+'</thead>'
			+'<tfoot>'
			+	'<tr>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'
			+		'<td></td>'							
			+	'</tr>'
			+'</tfoot>'
			+'<tbody>'			
			+'</tbody>'
			+'</table>';

		$('#tablaPrincipal').html(tabla);

		$.ajax({ 		
			method: "POST",
			url: "./bitacora?action=buscarPermiso",
			data: $('#formFiltrosPermisos').serialize(),
			success: function(data){
				$.each(data, function(i,item){
					//introducimos los option del Json obtenido
					var rowTabla = '<tr>'
							+'<td>'+(i+1)+'</td>'
							+'<td>'+item.empledoDTO.nombreCompletoEmpleado+'</td>'
							+'<td>'+item.fechaDePermiso.fechaString+'</td>'
							+'<td>'+item.fechaAPermiso.fechaString+'</td>'	
							+'<td>'+item.motivoDTO.estatusString+'</td>'
							+'<td>'+item.descripcionPermiso+'</td>'			
							+'<td><img src="'+item.estatusDTO.iconoStringBase64+'" id="logo_sistema" width="'+item.estatusDTO.widthIcono+'"	height="'+item.estatusDTO.heightIcono+'" alt="icono_status"></img> '+item.estatusDTO.indicadorEstatus+'</td>'
							+'<td>'
								+'<a role="button" data-target="#modalPermisos" data-toggle="modal" data-new="0" data-idpermiso="'+item.idPermiso+'"'
									+ 'data-justificacionp="'+item.descripcionPermiso+'"'
									+ 'data-idempleado="'+item.empledoDTO.idEmpleado+'"'
									+ '	data-fechadepermiso="'+item.fechaDePermiso.fechaString+'"'
									+ '	data-fechaapermiso="'+item.fechaAPermiso.fechaString+'"'
									+ '	data-horadepermiso="'+item.horaDePermiso.horaString+'"'
									+ '	data-horaapermiso="'+item.horaAPermiso+'"'
									+ '	data-motivopermiso="'+item.motivoDTO.idTipoEstatus+'"'
									+ '	data-statuspermiso="'+item.estatusDTO.idTipoEstatus+'">'
										+ '<i class="fa fa-pencil" title="Editar horario."></i>'
								+'</a>'
							+'</td>'
						+'</tr>';
					$("#tb_listahorarios tbody").append(rowTabla);
				});
			}
		});	
	}

	function getDefaultDate(){
		var now = new Date();
		var day = ("0" + now.getDate()).slice(-2);
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var today = (day) + "/" + (month)+ "/" + now.getFullYear();
		return today;
	}

	$.datepicker.regional['es'] = {
			closeText: 'Cerrar',
			prevText: '<Ant',
			nextText: 'Sig>',
			currentText: 'Hoy',
			monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
			dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi�rcoles', 'Jueves', 'Viernes', 'S�bado'],
			dayNamesShort: ['Dom','Lun','Mar','Mi�','Juv','Vie','S�b'],
			dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
			weekHeader: 'Sm',
			dateFormat: 'dd/mm/yy',
			setDate : new Date(),
			firstDay: 1,
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''
	};

	$.datepicker.setDefaults($.datepicker.regional['es']);
	$( "#fechaDePermiso" ).datepicker({ 
		//defaultDate: -6570,
		changeMonth: true,
		changeYear: true,
		showAnim: "slideDown"
	});

	$( "#fechaAPermiso" ).datepicker({
		//defaultDate: -3650,	
		changeMonth: true,
		changeYear: true,
		showAnim: "slideDown",
		//showButtonPanel: true
	});

	var modalPublico = null;
	$('#modalPermisos').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var nuevo = button.data('new'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		selectTiposJustificantes();
		selectEstatusJustificantes();
		var modal = $(this);	
		modalPublico = modal;
		nuevoGlobal = nuevo;
		if(nuevo == 0){		
			/*var claveHorario= button.data('clave');
			modal.find('.modal-title').text('EDITAR HORARIO: ' + claveHorario);	*/		  
			var idPermiso = button.data('idpermiso');			  
			var justificacion = button.data('justificacionp');
			var idEmpleado = button.data('idempleado');
			var fechaDePermiso = button.data('fechadepermiso');
			var fechaAPermiso = button.data('fechaapermiso');
			var horaDePermiso = button.data('horadepermiso');
			var horaAPermiso = button.data('horaapermiso');
			var motivoPermiso = button.data('motivopermiso');
			var statusPermiso = button.data('statuspermiso');
			var idArchivoTarjeta = button.data('archivo-id');
			var nombreDocumento = button.data('archivo-nombre');
			
			if(idArchivoTarjeta > 0){
				modal.find('.modal-body #divFormGroupArchivo').empty();
				modal.find('.modal-body #divFormGroupArchivo').html(
						'<label class="col-sm-2 col-form-label">Archivo:</label>'
						+ '<div class="col-sm-8">'
							+ '<p class="form-control-static">'
								+ nombreDocumento
							+'</p>'
						+'</div>'
						+ '<div class="col-sm-1">'
							+'<i class="fa fa-download pointer" aria-hidden="true" onclick="descargaArchivo('+idArchivoTarjeta+');"></i>'
						+'</div>'
						+ '<div class="col-sm-1">'
							+ '<i class="fa fa-refresh pointer" id="cambiarArchivoToggle" aria-hidden="true"></i>'
						+'</div>'
						+'');
			}else{
				inicializarArchivoInput(modal);
			}
			modal.find('.modal-body #idPermisoHidden').val(idPermiso);			 
			modal.find('.modal-title').text('EDITAR PERMISO ' + idPermiso);
			modal.find('.modal-body #justificacion').val(justificacion);
			modal.find('.modal-body #idEmpleado').val(idEmpleado);
			modal.find('.modal-body #fechaDePermiso').val(fechaDePermiso);
			modal.find('.modal-body #fechaAPermiso').val(fechaAPermiso);
			modal.find('.modal-body #horaDe').val(horaDePermiso);
			modal.find('.modal-body #horaA').val(horaAPermiso);
			setTimeout(function(){ 
				modal.find('.modal-body #tipoJustificante').val(motivoPermiso);
				modal.find('.modal-body #statusJustificante').val(statusPermiso);
			}, 
			1000);
			
				mostrando = false;			
				
				modal.find('.modal-body #divFormGroupArchivoRow').slideUp();
			
			
			

		}else{
			//$('#statusJustificante').hide();
			consultaNuevoSkuPermiso();
			modal.find('.modal-title').text('NUEVO PERMISO ?');
			setTimeout(function(){ 
				modal.find('.modal-title').text('NUEVO PERMISO ' + $('#lastSku').val());
			}, 
			1000);			
			modal.find('.modal-body #idPermisoHidden').val('');	
			inicializarArchivoInput(modal);
		}

	});
	var mostrando = false;
	$("#modalPermisos").on('click', '#cambiarArchivoToggle', function () {
	 console.log(mostrando);
		if (mostrando == false){
			mostrando = true;
		modalPublico.find('.modal-body #divFormGroupArchivo2').empty();
		modalPublico.find('.modal-body #divFormGroupArchivo2').html(
				'<label for="archivoTarjeta">Tarjeta informativa:</label>'
				+'<input type="file" class="form-control" id="cambiarArchivo"  name="archivoTarjeta" placeholder="Seleccione la tarjeta" accept="application/msword, .doc, .docx, application/pdf"/>');
		modalPublico.find('.modal-body #cambiarArchivo').val('');
		
		
			modalPublico.find('.modal-body #divFormGroupArchivoRow').slideDown();
		
		}else{
			mostrando = false;
			modalPublico.find('.modal-body #divFormGroupArchivoRow').slideUp();
		}
		
	});
	
	
	function inicializarArchivoInput(modal){	
		
		modal.find('.modal-body #divFormGroupArchivo').empty();
		modal.find('.modal-body #divFormGroupArchivo').html(
				'<label for="archivoTarjeta">Tarjeta informativa:</label>'
				+'<input type="file" class="form-control" id="archivoTarjeta"  name="archivoTarjeta" placeholder="Seleccione la tarjeta" accept="application/msword, .doc, .docx, application/pdf"/>');
	}

	$('#modalPermisos').on('hide.bs.modal', function(e){		

		swal({
			title: 'Desea descartar los datos del horario?',
			text: "Se dispone a descartar los datos del horario!",
			type: 'question',
			showCancelButton: true,		  
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, descartarlos!',
			cancelButtonText: 'No, espera!',
			confirmButtonClass: 'btn btn-success',
			cancelButtonClass: 'btn btn-danger',   			
			showLoaderOnConfirm: true,		 
			preConfirm: function () {
				return new Promise(function (resolve, reject) {
					$('#formPermisos').trigger("reset");

					resolve();
				})
			},
			allowOutsideClick: false
		}).then(function () {
			console.log("Terminado!");

		}, function (dismiss) {
			// dismiss can be 'cancel', 'overlay',
			// 'close', and 'timer'
			if (dismiss === 'cancel') {
				swal(	'Detenido',
						'Puede volver a revisar los datos',
				'error');
				$("#modalPermisos").modal('toggle');					
			}
		}).catch(swal.noop);


	});	

	var nuevoGlobal = -1;
	$('#formPermisos').on('submit',function(event){
		event.preventDefault() ;
		event.stopPropagation();

		try{
			swal({
				title: 'Desea guardar los datos del permiso?',
				text: "Se dispone a guardar los datos del permiso!",
				type: 'question',
				showCancelButton: true,		  
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Si, guardarlo!',
				cancelButtonText: 'No, espera!',
				confirmButtonClass: 'btn btn-success',
				cancelButtonClass: 'btn btn-danger',   			
				showLoaderOnConfirm: true,		 
				preConfirm: function () {
					return new Promise(function (resolve, reject) {
						var action = "";						
						if (nuevoGlobal == 0){
							action = "actualizarPermiso";
						}else{
							action = "nuevoPermiso";							
						}
						var form = $('#formPermisos')[0]; // You need to use standart javascript object here
						var formData = new FormData(form);
						formData.append('action', action);
						$.ajax({
							url: "./bitacora",
							data: formData,
							type: 'POST',
							contentType: false,  
							processData: false,							  
							success: function(data){
								if(data == '1'){
									$('#formPermisos')[0].reset();	
									$("#modalPermisos").modal('toggle');
									swal({
										type: 'success',
										title: 'Correcto!',
										html: 'Permiso guardado'
									});
									setTimeout(function(){ 
										window.location = "./bitacora?action=consultarPermisos";
									}, 
									1000);

								}else{
									swal({
										type: 'error',
										title: 'Error!',
										html: 'Permiso no guardado'
									});
								}
							},
							error: function(data){
								var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
								$("#initRow").html(alertError);
								swal({
									type: 'error',
									title: 'Permiso no guardado!',
									html: 'Permiso no guardado'
								})
							},
							complete: function(xhr, status){	
								var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Permiso guardado correctamente!.</div>';
								var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.text+'. </div>';
								if(status != 'error'){
									$("#initRow").html(alertSuccess);	
								}else{
									$("#initRow").html(alertError);	
								}								
								console.log(xhr);
							}
						});
					})
				},
				allowOutsideClick: false
			}).then(function () {


			}, function (dismiss) {
				// dismiss can be 'cancel', 'overlay',
				// 'close', and 'timer'
				if (dismiss === 'cancel') {
					swal(	'Detenido',
							'Puede volver a revisar los datos',
					'error');
				}
			}).catch(swal.noop);
		} catch (e) {
			throw new Error(e.message);
		}

	});	

	function consultaNuevoSkuPermiso(){
		$.ajax({
			url: "./bitacora",
			data: {
				action: "consultaNuevoSkuPermiso",
			},
			success: function(data){
				$('#lastSku').val(data);
			},
			error: function(data){
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
				$("#initRow").html(alertError);
				swal({
					type: 'error',
					title: 'Permiso no guardado!',
					html: 'Permiso no guardado'
				})
			},
			complete: function(xhr, status){	
				var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong>Permiso guardado correctamente!.</div>';
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.text+'. </div>';
				if(status != 'error'){
					//$("#initRow").html(alertSuccess);	
				}else{
					$("#initRow").html(alertError);	
				}								
				console.log(xhr);
			}
		});
	}

	function selectTiposJustificantes(){

		$.ajax({

			method: "POST",
			url: "./listados",      
			data: {
				action: "selectTiposEstatus",
				app: "justificantes_tipo"
			},
			success: function(data){
				//console.log(data);	
				$('#tipoJustificante').empty();
				$("#tipoJustificante").append('<option value="" selected="true" >Seleccione un motivo...</option>');
				$.each(data, function(i,item){
					//introducimos los option del Json obtenido
					$("#tipoJustificante").append('<option value="'+item.idTipoEstatus+'">'+item.estatusString+'</option>');
				});

			},
			complete: function(xhr, status){	  
				console.log(xhr);
			}
		});
	}
	
	
	function selectEstatusJustificantes(){

		$.ajax({

			method: "POST",
			url: "./listados",      
			data: {
				action: "selectTiposEstatus",
				app: "justificantes_status"
			},
			success: function(data){
				//console.log(data);	
				$('#statusJustificante').empty();
				$("#statusJustificante").append('<option value="" selected="true" >Seleccione un status...</option>');
				$.each(data, function(i,item){
					//introducimos los option del Json obtenido
					$("#statusJustificante").append('<option value="'+item.idTipoEstatus+'">'+item.estatusString+'</option>');
				});

			},
			complete: function(xhr, status){	  
				console.log(xhr);
			}
		});
	}


})





function descargaArchivo(archivoId){
	window.location = './documentos?action=descargarArchivo&id='+archivoId;
	/*$.ajax({

		method: "POST",
		url: "./documentos",      
		data: {
			action: "descargarArchivo",
			id: archivoId
		},
		success: function(data){
			
		},
		complete: function(xhr, status){	  
			console.log(xhr);
		}
	});*/
	
}

