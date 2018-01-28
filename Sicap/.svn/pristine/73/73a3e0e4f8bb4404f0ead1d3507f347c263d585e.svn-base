/**
 * 
 */
$(function(){
	var tabla = document.getElementById("tb_listado");
    var tablaExiste = document.getElementById("contenedorTabla").contains(tabla);
	if(tablaExiste){
		$('#tb_listado').DataTable({
				"fixedHeader": true,
				 "language": {
					 "url": "./js/dataTables.spanish.lang.json"
		         }
		         /*"processing": true,
	             "serverSide": true,	                
		         "ajax": {
		        	"url": "./listados?action=consultaUsuarios",
		        	"type": "POST",
		        	"dataSrc": "",
		        	    
		        },
		        "columns": [
		            { "data": "idUser" },
		            { "data": "login" },
		            { "data": "email" },
		            { "data": "sessionTimeOut" },
		            { "data": "active" }
		          ]*/
			});
	}
	
	
	
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
	 
	 $(".fechaDDMMYYYY").inputmask({
		 mask: "dd/mm/yyyy",
		 placeholder: "dd/mm/aaaa",
		 alias: "fecha",		
		 showMaskOnHover: false
	});
	 
	 $("#btnImprimirHorarios").click(function(e) {
		 window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#divTablaHorarios').html()));
		 e.preventDefault();
	 });
	
	$('#registroUsuario').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var nuevo = button.data('new'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this);	
		nuevoGlobal = nuevo;
		if(nuevo == 0){		
			var idUser= button.data('userid');
			var userName = button.data('username');
			modal.find('.modal-title').text('Editar usuario: ' + userName);			  
			var idPerfil = button.data('idperfil');
			var idUserConfig = button.data('iduserconfig');
			var provName = button.data('name');
			var email = button.data('email');
			var timeOut = button.data('timeout');
			var active = button.data('active');
			var image = button.data('image');
			var employeeId = button.data('employee');
			var employeeName = button.data('employee-name');
			
			modal.find('.modal-body #idUserHidden').val(idUser);
			modal.find('.modal-body #login').val(userName);
			modal.find('.modal-body #perfil').val(idPerfil);			
			modal.find('.modal-body #initService').val(idUserConfig);
			modal.find('.modal-body #name').val(provName);
			modal.find('.modal-body #email').val(email);
			modal.find('.modal-body #timeOut').val(timeOut);	
			modal.find('.modal-body #activo').val(active);
			if(image.length > 0){
				$("#imgPerfil").attr("src", image);
			}else{
				$("#imgPerfil").attr("src", "img/users/default_image.png");
			}
			if(active == 'Y'){
				modal.find('#activo').prop('checked', true);
			}else{
				modal.find('#activo').prop('checked', false);
			}
			setTimeout(function() {
				selectEmpleadosConSinUsario("si", true, employeeId, employeeName);
			}, 2000);
				
			
			setTimeout(function() {
				modal.find('.modal-body #idEmpleado').val(employeeId);
			}, 1200);
			
		
		}else{					
			modal.find('.modal-title').text('Nuevo usuario');
			setTimeout(function() {
				selectEmpleadosConSinUsario("si", false, 0, "");
			}, 2000);
			
		}

	});
	function selectEmpleadosConSinUsario(con_usuario, editar, id, nombre){//si, sin usuario, no todos los empleados
		$('#idEmpleado')
	    .empty()
	    .append('<option selected="selected" value="">Seleccione...</option>');
		$.ajax({
			url: "./listados",
			data: {
				action: "selectEmpleadosConSinUsario",
				usuario: con_usuario
			},
			success: function(data){
				var toAppend = '';
				if(editar){
					toAppend += '<option value="'+id+'">'+nombre+'</option>';
					$.each(data,function(i,o){
						toAppend += '<option value="'+o.idEmpleado+'">'+o.nombreCompletoEmpleado+'</option>';
					});
					$('#idEmpleado').append(toAppend);
				}else{
					$.each(data,function(i,o){
						toAppend += '<option value="'+o.idEmpleado+'">'+o.nombreCompletoEmpleado+'</option>';
				    });
				    $('#idEmpleado').append(toAppend);
				}
			},
			error: function(data){
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
				$("#initRow").html(alertError);
				swal({
					type: 'error',
					title: 'Error en consulta de existencia de login!',
					html: 'Error en consulta de existencia de login'
				})
			},
			complete: function(xhr, status){	
				var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Usuario guardado correctamente!.</div>';
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.text+'. </div>';
				if(status != 'error'){
					//$("#initRow").html(alertSuccess);	
				}else{
					$("#initRow").html(alertError);	
				}								
//				console.log(xhr);
			}
		});
	;
	}
		
	function consultaLogin(){
		$.ajax({
			url: "./listados",
			data: {
				action: "consultaLogin",
				login: ""
			},
			success: function(data){
				
				

			},
			error: function(data){
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
				$("#initRow").html(alertError);
				swal({
					type: 'error',
					title: 'Error en consulta de existencia de login!',
					html: 'Error en consulta de existencia de login'
				})
			},
			complete: function(xhr, status){	
				var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Usuario guardado correctamente!.</div>';
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
	
	$('#registroUsuario').on('hidden.bs.modal', function (e) {
			
		
	})
	
	
	$('#registroUsuario').on('hide.bs.modal', function(e){		
			
			swal({
				title: 'Desea descartar los datos del usuario?',
				text: "Se dispone a descartar los datos del usuario!",
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
						$('#formUsuario').trigger("reset");
						
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
					$("#registroUsuario").modal('toggle');					
				}
			}).catch(swal.noop);
		

	});
	
	$("#nl").on( 'change', function() {	
	    if( $(this).is(':checked') ) {
	    	 $('#e1Horario').prop('disabled', 'disabled');
	    	 $('#s1Horario').prop('disabled', 'disabled');
	    	 $('#hr1Horario').prop('disabled', 'disabled');
	    	 $('#horarioQuebrado').prop('disabled', 'disabled');
	    } else {
	    	 $('#e1Horario').prop('disabled', false);
	    	 $('#s1Horario').prop('disabled', false);
	    	 $('#hr1Horario').prop('disabled', false);
	    	 $('#horarioQuebrado').prop('disabled', false);
	    }
	});
	
	
	$("#horarioQuebrado").on( 'change', function() {
		if( $(this).is(':checked') ) {
			$('#e2Horario').prop('disabled', false);
			$('#s2Horario').prop('disabled', false);
			$('#hr2Horario').prop('disabled', false);
		} else {
			$('#e2Horario').prop('disabled', 'disabled');
			$('#s2Horario').prop('disabled', 'disabled');
			$('#hr2Horario').prop('disabled', 'disabled');
		}
	});
	
	var nuevoGlobal = -1;
	$('#formUsuario').on('submit',function(event){
		event.preventDefault() ;
		event.stopPropagation();

		try{
			swal({
				title: 'Desea guardar los datos del usuario?',
				text: "Se dispone a guardar los datos del usuario!",
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
							action = "actualizarUsuario";
						}else{
							action = "nuevoUsuario";							
						}
						var form = $('#formUsuario')[0]; // You need to use standart javascript object here
						var formData = new FormData(form);
						formData.append('action', action);
						$.ajax({
							url: "./listados?action="+action,
							data: formData,
							type: 'POST',
							contentType: false,  
							processData: false,							
							success: function(data){
								if(data == '1'){
									$('#formUsuario')[0].reset();	
									$("#registroUsuario").modal('toggle');
									swal({
										type: 'success',
										title: 'Correcto!',
										html: 'Horario guardado'
									});
									setTimeout(function(){ 
										window.location = "./listados?action=usuarios";
									}, 
									1000);
								}else{
									swal({
										type: 'error',
										title: 'Error!',
										html: 'Usuario no guardado'
									});
								}
							},
							error: function(data){
								var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
								$("#initRow").html(alertError);
								swal({
									type: 'error',
									title: 'Horario no guardado!',
									html: 'Horario no guardado'
								})
							},
							complete: function(xhr, status){	
								var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Usuario guardado correctamente!.</div>';
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
			yearSuffix: ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);

	
	$( "#inputFechaInicial" ).datepicker({
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        viewMode: "months", 
        minViewMode: "days",
        dateFormat: 'dd/mm/yy',
		setDate : new Date(),
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		onClose: function(dateText, inst) { 
	       
	    }
	});
	$( "#inputFechaFinal" ).datepicker({
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        viewMode: "months", 
        minViewMode: "days",
        dateFormat: 'dd/mm/yy',
		setDate : new Date(),
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		onClose: function(dateText, inst) { 
	       
	    }
	});

	
	
});
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imgPerfil')
                .attr('src', e.target.result)
                .width(60)
                .height(70);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}