$(function(){
	var tabla = document.getElementById("tb_listahorarios");
    var tablaExiste = document.getElementById("contenedorTabla").contains(tabla);
	if(tablaExiste){
		$('#tb_listahorarios').DataTable({
				fixedHeader: true,
				 "language": {
		                "url": "./js/dataTables.spanish.lang.json"
		            }
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
	
	$('#modalHorarios').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var nuevo = button.data('new'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this);	
		nuevoGlobal = nuevo;
		if(nuevo == 0){		
			var claveHorario= button.data('clave');
			modal.find('.modal-title').text('EDITAR HORARIO: ' + claveHorario);			  
			var idHorario = button.data('idhorario');			  
			var nombreHorario = button.data('name');
			var he1 = button.data('he1');
			var hs1 = button.data('hs1');
			var hr1 = button.data('hr1');
			var he2 = button.data('he2');
			var hs2 = button.data('hs2');
			var hr2 = button.data('hr2');
			var hQuebrado = button.data('quebrado');
			var horarioNoLaboral = button.data('nolaboral');
			var hStatus = button.data('status');

			modal.find('.modal-body #idHorarioHidden').val(idHorario);
			modal.find('.modal-body #cveHorarioHidden').val(claveHorario);
			modal.find('.modal-body #cveHorario').val(claveHorario);
			modal.find('.modal-body #nombreHorario').val(nombreHorario);
			modal.find('.modal-body #e1Horario').val(he1);
			modal.find('.modal-body #s1Horario').val(hs1);
			modal.find('.modal-body #hr1Horario').val(hr1);			  

			if(horarioNoLaboral){
				modal.find('#nl').prop('checked', true);
				$('#e1Horario').prop('disabled', 'disabled');
				$('#s1Horario').prop('disabled', 'disabled');
				$('#hr1Horario').prop('disabled', 'disabled');
				$('#horarioQuebrado').prop('disabled', 'disabled');				
				$('#e2Horario').prop('disabled', 'disabled');
				$('#s2Horario').prop('disabled', 'disabled');
				$('#hr2Horario').prop('disabled', 'disabled');

			}else{
				modal.find('#nl').prop('checked', false);
				$('#e1Horario').prop('disabled', '');
				$('#s1Horario').prop('disabled', '');
				$('#hr1Horario').prop('disabled', '');
				$('#horarioQuebrado').prop('disabled', '');
			}
			if(hQuebrado){
				modal.find('#horarioQuebrado').prop('checked', true);
				$('#e2Horario').prop('disabled', false);
				$('#s2Horario').prop('disabled', false);
				$('#hr2Horario').prop('disabled', false);

			}else{
				modal.find('#horarioQuebrado').prop('checked', false);
				$('#e2Horario').prop('disabled', 'disabled');
				$('#s2Horario').prop('disabled', 'disabled');
				$('#hr2Horario').prop('disabled', 'disabled');
			}	
			modal.find('.modal-body #e2Horario').val(he2);
			modal.find('.modal-body #s2Horario').val(hs2);
			modal.find('.modal-body #hr2Horario').val(hr2);

			modal.find('.modal-body #statusHorario').val(hStatus);


//			$("#cveHorario").prop("disabled", false);
			$('#divStatusHorario').show();
		}else{
			$('#divStatusHorario').hide();			
			modal.find('.modal-body #idHorarioHidden').val('');
			actualizaSkuNuevoHorario();
			modal.find('.modal-title').text('NUEVO HORARIO' + $("#cveHorario").val());
			$('#cveHorario').prop('disabled', 'disabled');
			
		}

	})
		
	function actualizaSkuNuevoHorario(){
		$.ajax({
			url: "./listados",
			data: {
				action: "consultarSkuHorario",
				prefix: "T"
			},
			success: function(data){
				console.log(data);
				$("#cveHorario").val(data);
				$("#cveHorarioHidden").val(data);
//				$("#cveHorario").prop("disabled", true);

			},
			error: function(data){
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
				$("#initRow").html(alertError);
				swal({
					type: 'error',
					title: 'Empleado no guardado!',
					html: 'Empleado no guardado'
				})
			},
			complete: function(xhr, status){	
				var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Empleado guardado correctamente!.</div>';
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
	
	$('#modalHorarios').on('hidden.bs.modal', function (e) {
			
		
	})
	
	
	$('#modalHorarios').on('hide.bs.modal', function(e){		
			
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
						$('#formHorarios').trigger("reset");
						
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
					$("#modalHorarios").modal('toggle');					
				}
			}).catch(swal.noop);
		

	});
	
	$("#nl").on( 'change', function() {	
	    if( $(this).is(':checked') ) {
	    	 $('#e1Horario').prop('disabled', 'disabled');
	    	 $('#s1Horario').prop('disabled', 'disabled');
	    	 $('#hr1Horario').prop('disabled', 'disabled');
	    	 $('#horarioQuebrado').prop('disabled', 'disabled');
	    	 $('#cveHorario').prop('disabled', false);	    	 
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
	$('#formHorarios').on('submit',function(event){
		event.preventDefault() ;
		event.stopPropagation();

		try{
			swal({
				title: 'Desea guardar los datos del horario?',
				text: "Se dispone a guardar los datos del horario!",
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
							action = "actualizarHorario";
						}else{
							action = "nuevoHorario";							
						}
						$.ajax({
							url: "./listados?action=" + action,
							data: $('#formHorarios').serialize(),		  
							success: function(data){
								if(data == '1'){
									$('#formHorarios')[0].reset();	
									$("#modalHorarios").modal('toggle');
									swal({
										type: 'success',
										title: 'Correcto!',
										html: 'Horario guardado'
									});
									setTimeout(function(){ 
										window.location = "./listados?action=consultarHorarios";
									}, 
									1000);

								}else{
									swal({
										type: 'error',
										title: 'Error!',
										html: 'Horario no guardado'
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
								var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Horario guardado correctamente!.</div>';
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
	
	/*var dateFormat = "dd/mm/yy",
    from = $( "#inputFechaInicial" )
      .datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        changeYear: true,
        viewMode: "months", 
        minViewMode: "days",
        numberOfMonths: 1,
        firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false
      })
      .on( "change", function() {
        to.datepicker( "option", "minDate", getDate( this ) );
      }),
    to = $( "#inputFechaFinal" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      changeYear: true,
      viewMode: "months", 
      minViewMode: "days",
      numberOfMonths: 1,
      firstDay: 1,
      isRTL: false,
      showMonthAfterYear: false      
    })
    .on( "change", function() {
      from.datepicker( "option", "maxDate", getDate( this ) );
    });

  function getDate( element ) {
    var date;
    try {
      date = $.datepicker.parseDate( dateFormat, element.value );
    } catch( error ) {
      date = null;
    }
    return date;
  }*/
	
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
	        /*var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(year, month, 1));*/
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
	        /*var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(year, month, 1));*/
	    }
	});

	
	
});
function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}