/**
 * 
 */
/**
 *Archivo que contiene los metodos de formularios en modelos.jsp 
 */
$(function(){
	var tabla = document.getElementById("tb_lista"+elementos);
    var tablaExiste = document.getElementById("contenedorTabla").contains(tabla);
	if(tablaExiste){
		$('#tb_lista'+elementos).DataTable({
				fixedHeader: true,
				 "language": {
		                "url": "./js/dataTables.spanish.lang.json"
		            }
			});
	}
	var nuevoGlobal = -1;
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
	 
	 $("#btnImprimir"+elementos).click(function(e) {
		 window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#divTabla'+elementos).html()));
		 e.preventDefault();
	 });
	
	$('#modal'+elementos).on('show.bs.modal', function (event) {
		resizeMap();
		var button = $(event.relatedTarget);
		var nuevo = button.data('new');	
		var modal = $(this);	
		nuevoGlobal = nuevo;
		if(nuevo == 0){			
			var field1 = button.data('field1');
			var field2 = button.data('field2');
			var field3 = button.data('field3');
			var field4 = button.data('field4');
			var field5 = button.data('field5');
			var field6 = button.data('field6');
			var field7 = button.data('field7');
			var field8 = button.data('field8');
			var field9 = button.data('field9');
			var field10 = button.data('field10');
			modal.find('.modal-title').text('EDITAR '+elemento+': ' + field2);	
			modal.find(".modal-body #field1").val(field1);
			modal.find(".modal-body #field2").val(field2);
			modal.find(".modal-body #field3").val(field3);
			modal.find(".modal-body #field4").val(field4);
			modal.find(".modal-body #field5").val(field5);
			modal.find(".modal-body #field6").val(field6);
			modal.find(".modal-body #field7").val(field7);
			if(field8 != "Sin licencia."){
				modal.find(".modal-body #field8").val(field8);
			}
			modal.find(".modal-body #field9").val(field9);
			modal.find(".modal-body #field10").val(field10);
		}else{		
			//--->nuevo<---
		}
	});	
	function resizeMap() {
		   if(typeof map =="undefined") return;
		   setTimeout( function(){resizingMap();} , 400);
		}

		function resizingMap() {
		   if(typeof map =="undefined") return;
		   var center = map.getCenter();
		   google.maps.event.trigger(map, "resize");
		   map.setCenter(center); 
		}
	$('#modal'+elementos).on('hidden.bs.modal', function (e) {
			
		
	});
	
	$('#modal'+elementos).on('hide.bs.modal', function(e){			
			swal({
				title: 'Desea descartar los datos del '+elemento+'?',
				text: "Se dispone a descartar los datos del "+elemento+"!",
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
						$('#form'+elemento).trigger("reset");
						
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
					$("#modal"+elementos).modal('toggle');					
				}
			}).catch(swal.noop);		

	});	
	
	
	$('#form' + elemento).on('submit',function(event){
		event.preventDefault() ;
		event.stopPropagation();
		var action0 = "";						
		if (nuevoGlobal == 0){
			action0 = "actualizar";
		}else{
			action0 = "guardar";							
		}	
		try{
			swal({							
				title: 'Desea '+action0+' los datos del ' + elemento + '?',
				text: "Se dispone a "+action0+" los datos del " + elemento + "!",
				type: 'question',
				showCancelButton: true,		  
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Si, '+action0+'!',
				cancelButtonText: 'No, espera!',
				confirmButtonClass: 'btn btn-success',
				cancelButtonClass: 'btn btn-danger',   			
				showLoaderOnConfirm: true,		 
				preConfirm: function () {
					return new Promise(function (resolve, reject) {	
						var action = "procesaFormItemOrdenes";
						var guardado = false;
						$.ajax({							
							type: 'POST',
							url: "./listados?action="+action,							
							data: $('#form' + elemento).serialize(),		  
							success: function(data){
								if(data == '1'){
									$('#form' + elemento)[0].reset();	
									$("#modal"+elementos).modal('toggle');
									swal({
										type: 'success',
										title: 'Correcto!',
										html: elemento + ' guardado'
									});
									guardado = true;
									setTimeout(function(){ 
										location.reload();
									}, 
									2000);

								}else{
									swal({
										type: 'error',
										title: 'Error!',
										html: elemento + ' no guardado'
									});
								}
							},
							error: function(data){
								var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
								$("#initRow").html(alertError);
								swal({
									type: 'error',
									title: elemento+ ' no guardado!',
									html: elemento + ' no guardado'
								})
							},
							complete: function(xhr, status){	
								var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> '+elemento+' guardado correctamente!.</div>';
								var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.text+'. </div>';
								setTimeout(function(){ 
									if(status != 'error' && guardado){
										$("#initRow").html(alertSuccess);	
									}else{
										$("#initRow").html(alertError);	
									}	
								}, 
								1000);														
								
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
	/**
	 * DESCOMENTAR PARA EL DATETIMEPICKER
	 */
	
	/*$.datepicker.regional['es'] = {
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
	$.datepicker.setDefaults($.datepicker.regional['es']);*/
	
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
	//DESCOMENTAR PARA LOS DATE PICKER
	
	/*$( "#inputFechaInicial" ).datepicker({
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
	   /* }
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
	  /* }
	});*/

	
	
});
function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}