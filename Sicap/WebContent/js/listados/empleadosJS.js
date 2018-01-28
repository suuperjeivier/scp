function buscarEmpleado(){
	  		    	$.ajax({
		  		  url: "./listados",
		  		  data: {
		  			  action: "buscarEmpleado"
		  		  },		  
		  		  success: function(result){	  
		  	        $("#rowInit").html(result);
		  	        resolve();
		  		  },
		  		  complete: function(xhr, status){	  
		  		       console.log(xhr);
		  		  }
		  	  });
	
}

$("#btnImprimirEmpleados").click(function(e) {
	 window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#divTablaEmpleados').html()));
	 e.preventDefault();
});

function getDefaultDate(){
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = (day) + "/" + (month)+ "/" + now.getFullYear();
	return today;
}

function actualizaSkuEmpleado(){
  	$.ajax({
	  url: "./listados",
	  data: {
		  action: "nuevoSkuEmpleado"
	  },		  
	  success: function(result){
		  if (result != '-1'){
			  $("#ultimoSkuEmp").html(result);
			  $("#skuEmpleadoHidden").val(result);
		  }else{
			  var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+result+'. </div>';
			  $("#alertSku").html(alertError);
			  $("#skuEmpleadoHidden").val('');
		  }
	  },
	  complete: function(xhr, status){	  
	      //console.log(xhr);
	  }
});

}

function selectEntidadesFederativas(){
	
 	$.ajax({
 		
 	  method: "POST",
	  url: "./listados",      
	  data: {
		  action: "selectEntidadesFederativas"		  
	  },
	  success: function(data){
		  //console.log(data);	
		  $('#estadoDomicilio').empty();
		  $("#estadoDomicilio").append('<option value="" selected="true" >Seleccione un Estado...</option>');
		  $.each(data, function(i,item){
			//introducimos los option del Json obtenido
			$("#estadoDomicilio").append('<option value="'+item.idEntidadFederativa+'">'+item.nombreEntidadFederativa+'</option>');
		});
		  
	 },
	  complete: function(xhr, status){	  
	       //console.log(xhr);
	  }
  	});
 	
}



function selectMunicipios(){
	
 	$.ajax({
 		
 	  method: "POST",
	  url: "./listados",      
	  data: {
		  action: "selectMunicipios",
		  idEstado: $("#estadoDomicilio").val()
	  },
	  
	  success: function(data){		  
		  $('#municipioDomicilio').empty();
		  $.each(data, function(i,item){
			//introducimos los option del Json obtenido
			$("#municipioDomicilio").append('<option value="'+item.idMunicipio+'">'+item.nombreMunicipio+'</option>');
		});
		  
	 },
	  complete: function(xhr, status){	  
	       //console.log(xhr);
	  }
  	});
 	
}

function selectColonias(){	
 	$.ajax({ 		
 	  method: "POST",
	  url: "./listados",      
	  data: {
		  action: "selectColonias",
		  idMunicipio: $("#municipioDomicilio").val()
	  },
	  
	  success: function(data){
		 // console.log(data);	
		  $('#coloniaDomicilio').empty();
		  $.each(data, function(i,item){
			//introducimos los option del Json obtenido
			$("#coloniaDomicilio").append('<option value="'+item.idColonia+'">'+item.nombreColonia+'</option>');
		});
		  
	 },
	  complete: function(xhr, status){	  
	      // console.log(xhr);
	  }
  	});	
}


function selectGeneros(){
	$.ajax({
		method: "POST",
		url: "./listados",
		data:{
			action: "selectGeneros"
		},				
		success: function (data){	
//			console.log(data);
			$('#divContenedorRadioGeneros').empty();
			$.each(data, function(i,item){
				//jQuery("#radio_1").attr('checked', true);
				var inputGenero = '<div class="form-check">'
					+' <label class="form-check-label font-size11">'
					+'	<input type="radio" onClick="selectEstadosCiviles();" name="inputGenero" id="inputGenero'+item.idGenero+'" value="'+item.idGenero + '" />' + item.nombreGenero + '</label>'
					+'</div>';
				$('#divContenedorRadioGeneros').append(inputGenero);
				//$('#divContenedorRadioGeneros').checked('<input type=radio value="'+item.idGenero+'">'+item.nombreGenero);
			});
		},
		complete: function(xhr, status){
//			console.log(xhr);
		}
	});
}


function selectEstadosCiviles(){
		var generoId = $("input[name='inputGenero']:checked").val();		
		if(generoId > 0){
			//console.log(generoId);
			$.ajax({
				method: "POST",
				url: "./listados",
				data:{
					action: "selectEstadosCiviles",
					idGenero: generoId
				},				
				success: function (data){					
					$('#estadoCivil').empty();
					$.each(data, function(i,item){
						$('#estadoCivil').append('<option value="'+item.idEstadoCivil+'">'+item.nombreEstadoCivil+'</option>');
					});
				},
				complete: function(xhr, status){
				//	console.log(xhr);
				}
			});
		}else{
			console.log("sin genero! " + generoId);
		}
	}

$("#carreraTrunca").on( 'change', function() {
    if( $(this).is(':checked') ) {
    	$('#tipoPeriodoEscolar').prop('disabled', false);
    	$('#ultimoGradoEmpleado').prop('disabled', false);
    } else {
    	 $('#tipoPeriodoEscolar').prop('disabled', 'disabled');
    	 $('#ultimoGradoEmpleado').prop('disabled', 'disabled');
    }
});

function selectGradosEscolares(){
	$.ajax({
		method: "POST",
		url: "./listados",
		data:{
			action: "selectGradosEscolares",
			idPeriodoEscolar: $('#tipoPeriodoEscolar').val()
		},
		
		success: function (data){
			console.log(data);
			$('#ultimoGradoEmpleado').empty();
			$.each(data, function(i,item){
				$('#ultimoGradoEmpleado').append('<option value="'+item.idGradoEscolar+'">'+item.nombreGradoAcademico+'</option>');
			});
		},
		complete: function(xhr, status){
			// console.log(xhr);
		}
	});	
}

function selectEspecialidadesAcademicas(){
	$.ajax({
		method: "POST",
		url: "./listados",
		data:{
			action: "selectEspecialidadesAcademicas",
			idNivelAcademico: $('#nivelAcademicoEmpleado').val()
		},
		
		success: function (data){
			//console.log(data);
			$('#especialidadEmpleado').empty();
			$.each(data, function(i,item){
				$('#especialidadEmpleado').append('<option value="'+item.idEspecialidad+'">'+item.nombreEspecialidad+'</option>');
			});
		},
		complete: function(xhr, status){
			// console.log(xhr);
		}
	});	
}
	
$(function(){
	
	
	var errores = false;
	var vErrores = [];
	$( "#curp" ).keyup(function() {
		var curp = $('#curp').val();
		if(curp != "" && curp.length > 0){							
			 var regexCurp =
				 "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
				 "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
				 "[HM]{1}" +
				 "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
				 "[B-DF-HJ-NP-TV-Z]{3}" +
				 "[0-9A-Z]{1}[0-9]{1}$";
			 if (!curp.match(regexCurp)) {
				 
				 $('#errorCurp').removeAttr( "hidden" );
				 if($('#iconoCurpError').hasClass('glyphicon-ok')){
					 $('#iconoCurpError').removeClass('glyphicon-ok');
				 }
				 if(!$('#iconoCurpError').hasClass('glyphicon-remove')){
					 $('#iconoCurpError').addClass('glyphicon-remove');
				 }
				 $('#curp').removeClass('form-control-success');
				 $('#curp').addClass('form-control-danger');	
				 $('#errorCurp').slideDown();				 
				 $('#divCurp').addClass('has-danger');
				 errores = true;
				 if(vErrores.length > 0){
					 var curpExiste = false;
					 for(var i = vErrores.length - 1; i >= 0; i--) {
						    if(vErrores[i] == 'Curp') {
						    	curpExiste = true;
						    }
						}
					 if(!curpExiste){
						 vErrores.push("Curp");
					 }
				 }else{
					 var curpExiste = false;
					 for(var i = vErrores.length - 1; i >= 0; i--) {
						    if(vErrores[i] == 'Curp') {
						    	curpExiste = true;
						    }
						}
					 if(!curpExiste){
						 vErrores.push("Curp");
					 }
				 }
				 
			 }else{
				 $('#errorCurp').slideUp();
				 $('#curp').addClass('form-control-success');
				 $('#curp').removeClass('form-control-danger');			 
				 $('#divCurp').removeClass('has-danger');
				 $('#iconoCurpError').removeClass('glyphicon-remove');
				 $('#iconoCurpError').addClass('glyphicon-ok');
				 $('#divCurp').addClass('has-success');
				 errores = false;
				 if(vErrores.length > 0){
					 for(var i = vErrores.length - 1; i >= 0; i--) {
						    if(vErrores[i] == 'Curp') {
						    	vErrores.splice(i, 1);
						    }						    
						}
				 }
				 if(curp.length > 14){
					 $.ajax({
						 	method: "POST",
							url: "./listados",
							data: {
								action: "buscaCurpEmpleado",
								curpEmpleado : curp
							},
							success: function(data){
								console.log('resp: ' + data)
								if(data == '0'){
									if(vErrores.length > 0){
										 for(var i = vErrores.length - 1; i >= 0; i--) {
											 if (vErrores[i].indexOf("asignada") >= 0) {
											    	vErrores.splice(i, 1);
//											    	console.log('borrado!');
											    }
											}
									 }else{
//										 console.log('no error!');
										 var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Sin errores :).</div>';
										 $("#rowInitModal").html('');
										 $("#rowInitModal").html(alertSuccess);
									 }
									
								}else{
									$.each(data, function(i,item){
										 var curpExiste = false;
										 for(var i = vErrores.length - 1; i >= 0; i--) {
											    if(vErrores[i].indexOf("asignada") >= 0) {
											    	curpExiste = true;
											    }
											}
										 if(!curpExiste){
											 vErrores.push("Curp asignada " + item.nombreCompletoEmpleado);
										 }
										
									});	
									
									if(vErrores.length > 0){
										 var stringErrores = '';
										 for(var i = 0 ; i < vErrores.length; i++) {
											 if(i != vErrores.length){
												 stringErrores += vErrores[i] + ",";
											 }else{
												 stringErrores += vErrores[i];
											 }
										 }
										 var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+stringErrores+'. </div>';
										 $("#rowInitModal").html(alertError);
									 }else{
//										 console.log('no error!');
										 var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Sin errores :).</div>';
										 $("#rowInitModal").html('');
										 $("#rowInitModal").html(alertSuccess);
									 }
								}

							},
							complete: function(xhr, status){	
															
								
							}
						});
				 }
			 }
			 if(vErrores.length > 0){
				 var stringErrores = '';
				 for(var i = 0 ; i < vErrores.length; i++) {
					 if(i != vErrores.length){
						 stringErrores += vErrores[i] + ",";
					 }else{
						 stringErrores += vErrores[i];
					 }
				 }
				 var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+stringErrores+'. </div>';
				 $("#rowInitModal").html(alertError);
			 }else{
//				 console.log('no error!');
				 var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Sin errores :).</div>';
				 $("#rowInitModal").html('');
				 $("#rowInitModal").html(alertSuccess);
			 }
			 
		}
	});
	
	var close = false;
	
	
	$('#formEmpleado').on('submit',function(event){
		event.preventDefault() ;
		event.stopPropagation();
		if(!errores){		
			try{
				swal({
					title: 'Desea guardar los datos del empleado?',
					text: "Se dispone a guardar los datos del empleado!",
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
							var actualizar = false;
							if (editarGlobal == 1){
								action = "actualizarEmpleado";
								actualizar = true;
								editarGlobal = 0;
							}else{
								action = "nuevoEmpleado";
								actualizaSkuEmpleado();
								actualizar = false;
								editarGlobal = 0;
							}
							
							$.ajax({
								url: "./listados?action=" + action,
								data: $('#formEmpleado').serialize(),		  
								success: function(data){
									console.log(data);
									if (data == '1'){ 
											$('#formEmpleado')[0].reset();	
											close = true;
											var mensaje = '';
												if(actualizar){
													mensaje = 'actualizado';
												}else{
													mensaje = 'guardado';
												}
												
											swal({
												type: 'success',
												title: 'Correcto',
												html: 'Empleado '+mensaje+'!'
											})
											 $('#registroEmpleado').modal('toggle');
											
									}else{ 
										var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data+'. </div>';
										$("#rowInit").html(alertError);	
										swal({
											type: 'error',
											title: 'Empleado no guardado!',
											html: 'Empleado no guardado'
										})
									}           									  

								},
								error: function(data){
									var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.responseText+'. </div>';
									$("#rowInit").html(alertError);
									swal({
										type: 'error',
										title: 'Empleado no guardado!',
										html: 'Empleado no guardado'
									})
								},
								complete: function(xhr, status){	
									var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Empleado guardado correctamente!.</div>';
									var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.responseText+'. </div>';
									if(status != 'error'){
										$("#rowInit").html(alertSuccess);	
									}else{
										$("#rowInit").html(alertError);	
									}								
									console.log(xhr);
									console.log(xhr.responseText);
									console.log(status);
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
		}else{
			
		}
	});


	$.datepicker.regional['es'] = {
			closeText: 'Cerrar',
			prevText: '<Ant',
			nextText: 'Sig>',
			currentText: 'Hoy',
			monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
			dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
			dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
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
	$( "#fechaNac" ).datepicker({ 
		defaultDate: -6570,
		changeMonth: true,
	    changeYear: true,
	    showAnim: "slideDown"
	 });
	
	$( "#fechaAltaEmpleado" ).datepicker({
		defaultDate: -3650,	
		changeMonth: true,
		changeYear: true,
		showAnim: "slideDown",
		numberOfMonths: 2,
		showButtonPanel: true
	 });
	
	
	/**
	 * CARGA LA INFO DEL EMPLEADO EN EL MODAL
	 */	
	var editarGlobal = -1;
	$('#registroEmpleado').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var editar = button.data('editar'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		selectGeneros();
		selectEntidadesFederativas();
		
		editarGlobal = editar;
		if(editar == 1){
			var modal = $(this);
			modal.find('#btnActualizarSKU').hide();				  
			var idEmpleado = button.data('id');		
			var skuEmpleado = button.data('sku');
			var nombreEmpleado = button.data('nombre');
			var apPaternoEmpleado = button.data('appaterno');
			var apMaternoEmpleado = button.data('apmaterno');
			var cuipEmpleado = button.data('cuip');
			var curpEmpleado = button.data('curp');
			var rfcEmpleado = button.data('rfc');
			var fechaNacimiento = button.data('fechanac');
			var generoEmpleado = button.data('genero');
			var edoCivilEmpleado = button.data('edocivil');
			var gpoSanguineo = button.data('gposanguineo');
			var edoDireccionEmpleado = button.data('entidadfed');
			var munDireccionEmpleado = button.data('municipio');
			var colDireccionEmpleado = button.data('colonia');
			var cpDireccionEmpleado = button.data('cp');
			var calleDireccionEmpleado = button.data('calle');
			var numExtDireccionEmpleado = button.data('numext');
			var numIntDireccionEmpleado = button.data('numint');
			var numTelFijoEmpleado = button.data('numtelfijo');
			var numTelMovilEmpleado = button.data('numtelmovil');
			var correoEmpleado = button.data('correo');
			var nvelAcademicoEmpleado = button.data('nvelacad');
			var espAcademicaEmpleado = button.data('especialidad');
			var cTruncaEmpleado = button.data('ctrunca');
			var perEscolarEmpleado = button.data('perescolar');
			var ultGradoEscolarEmpleado = button.data('ultgradesc');
			var fechaAltaEmpleado = button.data('fechaalta');

//			modal.find('.modal-title').text('New message to ' + recipient)
			modal.find('#idEmpleadoHidden').val(idEmpleado);
			modal.find('#ultimoSkuEmp').html(skuEmpleado);
			modal.find('#skuEmpleadoHidden').val(skuEmpleado);
			modal.find('input[name="nombreEmpleado"]').val(nombreEmpleado);
			modal.find('input[name="apPaterno"]').val(apPaternoEmpleado);
			modal.find('input[name="apMaterno"]').val(apMaternoEmpleado);
			modal.find('#cuip').val(cuipEmpleado);
			modal.find('#curp').val(curpEmpleado);
			modal.find('#rfcEmpleado').val(rfcEmpleado);
			modal.find('#fechaNac').val(fechaNacimiento);		
			
			
			setTimeout(function(){ 
				var idHtmlGenero = 'inputGenero'+ generoEmpleado;
				modal.find('#'+idHtmlGenero).prop('checked', true).change();
				selectEstadosCiviles();
			}, 
			1000);
			
			setTimeout(function(){ 
				modal.find('#estadoCivil').val(edoCivilEmpleado); 
			}, 
			1500);
			
			
			
			/*var idHtmlGenero = 'inputGenero'+ generoEmpleado;
			modal.find('#'+idHtmlGenero).prop('checked', true).change();*/
			
			
			
			modal.find('#grupoSanguineo').val(gpoSanguineo);
			// console.log(munDireccionEmpleado);
			setTimeout(function(){ 
				modal.find('#estadoDomicilio').val(edoDireccionEmpleado).change();
			}, 
			500);

			setTimeout(function(){ 
				modal.find('#municipioDomicilio').val(munDireccionEmpleado).change();
			}, 
			900);


			setTimeout(function(){ 
				modal.find('#coloniaDomicilio').val(colDireccionEmpleado).change();
			}, 
			1500);


			modal.find('#cpDomicilio').val(cpDireccionEmpleado);
			modal.find('#calleDomicilio').val(calleDireccionEmpleado);
			modal.find('#numExtDomicilio').val(numExtDireccionEmpleado);
			modal.find('#numIntDomicilio').val(numIntDireccionEmpleado);
			modal.find('#numTelFijo').val(numTelFijoEmpleado);
			modal.find('#numTelMovil').val(numTelMovilEmpleado);
			modal.find('#correoe').val(correoEmpleado);
			modal.find('#nivelAcademicoEmpleado').val(nvelAcademicoEmpleado).change();
			setTimeout(function(){ 
				modal.find('#especialidadEmpleado').val(espAcademicaEmpleado);
			}, 
			1000);

			if(cTruncaEmpleado == 1 ){
				modal.find('#carreraTrunca').prop('checked', true).change();
				modal.find('#tipoPeriodoEscolar').val(perEscolarEmpleado).change();
				setTimeout(function(){ 
					modal.find('#ultimoGradoEmpleado').val(ultGradoEscolarEmpleado);
				}, 
				1000);


			}			  
			modal.find('#fechaAltaEmpleado').val(fechaAltaEmpleado);


		}else{
			actualizaSkuEmpleado();

		}
		close = false;
	})
	
	$('#registroEmpleado').on('hidden.bs.modal', function (e) {
		$('#btnActualizarSKU').show();			
		
	})
	
	
	$('#registroEmpleado').on('hide.bs.modal', function(e){
		if(!close){

			swal({
				title: 'Desea descartar los datos del empleado?',
				text: "Se dispone a descartar los datos del empleado!",
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
						$('#formEmpleado').trigger("reset");
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
					$("#registroEmpleado").modal('show');
//					$("#registroEmpleado").modal('hide');
				}
			}).catch(swal.noop);
		}


	});
	
	
	
	
});
	

