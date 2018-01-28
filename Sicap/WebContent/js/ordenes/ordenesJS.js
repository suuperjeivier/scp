/**
 *Archivo que contiene los metodos de formularios en ordenes 
 */
$(function(){
	$("#divHiddenPolizaExterna").hide().removeAttr('hidden');
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
	 $('#field6').on('change', function() {
		  if(this.value == 4){//4 es el id de vehiculo extern
			  //$('#divHiddenPolizaExterna').removeAttr('hidden');			 
			 // $('#divHiddenPolizaExterna').slideDown('slow');
			  $('#divHiddenPolizaExterna :input').prop('disabled', false);
			  $('#divHiddenPolizaExterna').slideToggle();
			  $('#fieldNewV2').focus();
		  }else{
			  if($('#divHiddenPolizaExterna').is(":visible")){
				 /* $('#divHiddenPolizaExterna').slideUp('slow');*/
				  $('#divHiddenPolizaExterna :input').prop('disabled', true);
				  $('#divHiddenPolizaExterna').slideToggle();
			  }else{
				  
			  }
		  }
	})
	$('#modal'+elementos).on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget);
		var nuevo = button.data('new');	
		var modal = $(this);	
		nuevoGlobal = nuevo;
		$('#divHiddenPolizaExterna :input').prop('disabled', true);
		$('#divHiddenPolizaExterna').hide();
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
			modal.find('.modal-title').text('EDITAR '+elemento+': ' + field1);	
			modal.find(".modal-body #field1").val(field1);
			modal.find(".modal-body #field2").val(field2);
			modal.find(".modal-body #field3").val(field3);
			modal.find(".modal-body #field4").val(field4);
			modal.find(".modal-body #field5").val(field5);
			modal.find(".modal-body #field6").val(field6);
			if(field6 != '4'){
				$('#divHiddenPolizaExterna :input').prop('disabled', true);
				$('#divHiddenPolizaExterna').hide();
			}else{
				$('#divHiddenPolizaExterna :input').prop('disabled', false);
				$('#divHiddenPolizaExterna').slideDown('slow');
				
			}			
			modal.find(".modal-body #field7").val(field7);	
			modal.find(".modal-body #field8").val(field8);
			modal.find(".modal-body #field9").val(field9);
			setTimeout(function(){ 
				selectCatalogs(field1, modal);
			}, 
			1000);
			
		}else{		
			//--->nuevo<---
			modal.find('.modal-title').text('NUEVA '+ elemento);
			$('#documentos a').remove();
			var $rowDocs = "<a href='#' class='list-group-item list-group-item-info'><span class='badge alert-info pull-right'>&times;</span>Sin archivos cargados</a>";	
			$('#documentos').append($rowDocs);
		}
	});	
	
	function selectCatalogs(orderId, modal){
		$.ajax({
			url: "./movimientos",			
			data: {
				action: "selectCatalogs",
				orderId: orderId				
			},			
			success: function(data){
				var arr_from_json = $.parseJSON(data);
				var arr_lugares_json = new Array();	
				var arr_tecnicos_json = new Array();
				var arr_radios_json = new Array();
				var arr_documentos_json = new Array();
				console.log(arr_from_json.length + ', tamano de array principal');
				if(arr_from_json.length > 0){
					arr_lugares_json = arr_from_json[0];
					if(arr_from_json.length > 1){
						arr_tecnicos_json = arr_from_json[1];
					}
					if(arr_from_json.length > 2){
						arr_radios_json =arr_from_json[2];
					}
					if(arr_from_json.length > 3){
						arr_documentos_json = arr_from_json[3];
					}
				}
				if(arr_lugares_json.length > 0){
					for (var i = 0; i < arr_lugares_json.length; i++){
						if(i == 0){
							console.log(arr_lugares_json[i]);
							modal.find(".modal-body #fieldPlaces0").val(arr_lugares_json[i].lugarDTO.lugarId);
							modal.find(".modal-body #commentPlace0").val(arr_lugares_json[i].actividad);
						}else{
							 var $row = "<tr id='addr"+(i+1)+"'>" +
							 	" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
					  			"	<td>"+ (i+1) +"</td>" +
					  			"	<td>" +
					  			"		<div class='form-group row'>" +
								"			<select type='text' class='col-11 form-control' name='fieldLugares[]' required id='fieldPlaces"+(i+1)+"'>" +						
								"			</select>" +
								"		</div>" +
							  	"	</td>" +
							  	"	<td>" +
					  			"		<div class='form-group row'>" +		  		
					  			"			<label for='comment'>Comentarios:</label>"+
					  		  	"			<textarea class='form-control' name='fieldCommentsLugares[]' id='commentPlace"+(i+1)+"' required rows='4' id='comment'></textarea>"+
					  			"		</div>" +
					  			"	</td>"+
						  		"</tr>";
							 $('#tab_logic tr:last').after($row);
							 $('#fieldPlaces0').find('option').clone().appendTo('#fieldPlaces'+(i+1));
							 modal.find(".modal-body #fieldPlaces"+(i+1)).val(arr_lugares_json[i].lugarDTO.lugarId);
							 modal.find(".modal-body #commentPlace"+(i+1)).val(arr_lugares_json[i].actividad);
						}
						 	
			        }
				}else{
					//sin lugares seleccionados ant
				}
				if(arr_tecnicos_json.length > 0){
					for (var i = 0; i < arr_tecnicos_json.length; i++){
						if(i == 0){
							console.log(arr_tecnicos_json[i]);
							modal.find(".modal-body #fieldTec0").val(arr_tecnicos_json[i].tecnicoDTO.tecnicoId);						
						}else{
							var $rowTecnicos = "<tr id='addTecRow"+(i+1)+"'>" +
							" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
				  			"	<td>"+ (i+1) +"</td>" +
				  			"	<td>" +
				  			"		<div class='form-group row'>" +
							"			<select type='text' class='col-11 form-control' name='fieldTec[]' required id='fieldTec"+(i+1)+"'>" +						
							"			</select>" +
							"		</div>" +
						  	"	</td>" +					  	
					  		"</tr>";				  
							$('#tab_logic_tec tr:last').after($rowTecnicos);
							$('#fieldTec0').find('option').clone().appendTo('#fieldTec'+(i+1));
							modal.find(".modal-body #fieldTec"+(i+1)).val(arr_tecnicos_json[i].tecnicoDTO.tecnicoId);	
							
						}
						 	
			        }
				}else{
					//sin tecnicos seleccionados ant
				}
				if(arr_radios_json.length > 0){
					for (var i = 0; i < arr_radios_json.length; i++){
						if(i == 0){
							console.log(arr_radios_json[i]);
							modal.find(".modal-body #fieldRad0").val(arr_radios_json[i].radioDTO.radioId);						
						}else{
							 var $rowRadios = "<tr id='addRadRow"+(i+1)+"'>" +
							 	" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
					  			"	<td>"+ (i+1) +"</td>" +
					  			"	<td>" +
					  			"		<div class='form-group row'>" +
								"			<select type='text' class='col-11 form-control' required name='fieldRad[]' id='fieldRad"+(i+1)+"'>" +						
								"			</select>" +
								"		</div>" +
							  	"	</td>" +					  	
						  		"</tr>";
							 $('#tab_logic_rad tr:last').after($rowRadios);
							 $('#fieldRad0').find('option').clone().appendTo('#fieldRad'+(i+1));
							 modal.find(".modal-body #fieldRad"+(i+1)).val(arr_radios_json[i].radioDTO.radioId);	
						}
						 	
			        }
				}else{
					//sin radios seleccionados ant
				}
				$('#documentos a').remove();
				if(arr_documentos_json.length > 0){
					for (var i = 0; i < arr_documentos_json.length; i++){
						console.log(arr_documentos_json[i]);
						var $rowDocs = "<a href='#' class='list-group-item list-group-item-success'><span class='badge alert-success pull-right' onclick='descargarArchivo("+arr_documentos_json[i].documentoId+")'>Descargar</span>"+arr_documentos_json[i].nombreArchivo+"</a>";
						$('#documentos').append($rowDocs);
			        }
				}else{
					var $rowDocs = "<a href='#' class='list-group-item list-group-item-info'><span class='badge alert-info pull-right'>&times;</span>Sin archivos cargados</a>";
					$('#documentos').append($rowDocs);
					//sin docs seleccionados ant
				}
				

			},
			error: function(data){
				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
				$("#initRow").html(alertError);
				swal({
					type: 'error',
					title: 'Catalogos no obtenidos!',
					html: 'No fue posible obtener los catalogos con el id de' +elemento + 'proporcionado'
				})
			},
			complete: function(xhr, status){
				console.log(xhr);
			}
		});
	}
	
	$('#modal'+elementos).on('hidden.bs.modal', function (e) {			
		
	});
	
	$('#modal'+elementos).on('hide.bs.modal', function(e){			
			swal({
				title: 'Desea descartar los datos de la '+elemento+'?',
				text: "Se dispone a descartar los datos de la "+elemento+"!",
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
						var firstLugar = $('#tab_logic tbody tr').eq(0); // Take the first row
						$('#tab_logic tbody tr').remove(); //remove all rows
						$('#tab_logic tbody').html(firstLugar); //put first row again
						var firstTec = $('#tab_logic_tec tbody tr').eq(0); // Take the first row
						$('#tab_logic_tec tbody tr').remove(); //remove all rows
						$('#tab_logic_tec tbody').html(firstTec); //put first row again
						var firstRad = $('#tab_logic_rad tbody tr').eq(0); // Take the first row
						$('#tab_logic_rad tbody tr').remove(); //remove all rows
						$('#tab_logic_rad tbody').html(firstRad); //put first row again
						/*var firstDoc = $('#tab_logic_rad tbody tr').eq(0); // Take the first row DOCUMENTOS
						$('#tab_logic_rad tbody tr').remove(); //remove all rows
						$('#tab_logic_rad tbody').html(first); //put first row again*/
						$('#documentos a').remove();						
						$('#form'+elemento).trigger("reset");
						$('.nav a[href="#step1"]').tab('show');						 							 
						$('#divHiddenPolizaExterna :input').prop('disabled', true);
						$('#divHiddenPolizaExterna').hide();
						 
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
						var action = "procesaFormOrdenes";
						var guardado = false;
						var formData = new FormData($('#form' + elemento)[0]);
						$.ajax({							
							type: 'POST',
							url: "./movimientos?action="+action,							
							data: formData,		
							async: false,
				            cache: false,
				            contentType: false,
				            processData: false,
							success: function(data){
								if(data > 0){
									$('#form' + elemento)[0].reset();	
									$("#modal"+elementos).modal('toggle');
									swal({
										type: 'success',
										title: 'Correcto!',
										html: elemento + ' guardada con id: #' + data + '.'
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
										html: elemento + ' no guardada'
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
	//DESCOMENTAR PARA LOS DATE PICKER
	//$("#field2").datepicker().datepicker("setDate", new Date());
	$( "#field2" ).datepicker({
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
	//$("#field4").datepicker().datepicker("setDate", new Date());
	$( "#field4" ).datepicker({		
        changeYear: true,
        changeMonth: true,
        showButtonPanel: true,
        viewMode: "days", 
        minViewMode: "minutes",
        dateFormat: 'dd/mm/yy',
        minDate: 0,
        maxDate: +3,
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
	//$("#field5").datepicker().datepicker("setDate", new Date());
	$( "#field5" ).datepicker({
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
	$('#btnGuardar').click(function () {
	    $('input:invalid').each(function () {
	        // Find the tab-pane that this element is inside, and get the id
	        var $closest = $(this).closest('.tab-pane');
	        var id = $closest.attr('id');
	        console.log("elemento invalido" + $(this).attr("name"));
	        // Find the link that corresponds to the pane and have it show
	        $('.nav a[href="#' + id + '"]').tab('show');
	        $(this).focus();

	        // Only want to do it once
	        return false;
	    });
	    $('select:invalid').each(function () {
	        // Find the tab-pane that this element is inside, and get the id
	        var $closest = $(this).closest('.tab-pane');
	        var id = $closest.attr('id');
	        var eleId = $(this).attr("id");
	        console.log("elemento invalido" + eleId);
	        // Find the link that corresponds to the pane and have it show
	        $('.nav a[href="#' + id + '"]').tab('show');
	        $('#'+eleId).focus();

	        // Only want to do it once
	        return false;
	    });
	});

		$('.next').click(function(){
			
		  var nextId = $(this).parents('.tab-pane').next().attr("id");
		  $('[href="#'+nextId+'"]').tab('show');
		  return false;
		  
		})
		  
		  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		    
		    //update progress
		    var step = $(e.target).data('step');
		    var percent = (parseInt(step) / 5) * 100;
		    
		    $('.progress-bar').css({width: percent + '%'});
		    $('.progress-bar').text("paso " + step + " de 5");
		    
		    //e.relatedTarget // previous tab
		    
		  })
		  
		  $('.first').click(function(){
		  
		    $('#myWizard a:first').tab('show')
		  
		  });
		  
		  /**
		   * siguiente
		   */
		  $('#activate-step-2').on('click', function(e) {
		        //$('ul.setup-panel li:eq(1)').removeClass('disabled');
		        $('ul.setup-panel li a[href="#step2"]').trigger('click');
		        //$(this).remove();
		    })
		    
		  /**
		  /**
		   * tabla
		   * @returns
		   */
		  //LUGARES
		  var i=0;
		  $("#add_row").click(function(){
			  var $row = "<tr id='addr"+(i+1)+"'>" +
			  			" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
			  			"	<td>"+ (i+2) +"</td>" +
			  			"	<td>" +
			  			"		<div class='form-group row'>" +
						"			<select type='text' class='col-11 form-control' name='fieldLugares[]' id='field"+((i+1)+10)+"'>" +						
						"			</select>" +
						"		</div>" +
					  	"	</td>" +
					  	"	<td>" +
			  			"		<div class='form-group row'>" +		  		
			  			"			<label for='comment'>Comentarios:</label>"+
			  		  	"			<textarea class='form-control' name='fieldCommentsLugares[]' id='commentPlace"+(i+1)+"' required rows='4' id='comment'></textarea>"+
			  			"		</div>" +
			  			"	</td>"+
				  		"</tr>";
		      $('#tab_logic tr:last').after($row);
		      $('#fieldPlaces0').find('option').clone().appendTo('#field'+((i+1)+10));			  
		      i++;  
		  });
		     $("#delete_row").click(function(){
		    	 if(i>0){	    		 
		    		 $("#addr"+(i)).html('');
		    		 $("#addr"+(i)).remove();
		    		 i--;
				 }
			 });
		     //TECNICOS
		     var t=0;
			  $("#add_row_tec").click(function(){
				  var $rowTecnicos = "<tr id='addTecRow"+(t+1)+"'>" +
				  			" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
				  			"	<td>"+ ((t+1)+1) +"</td>" +
				  			"	<td>" +
				  			"		<div class='form-group row'>" +
							"			<select type='text' class='col-11 form-control' name='fieldTec[]' required id='fieldTec"+(t+1)+"'>" +						
							"			</select>" +
							"		</div>" +
						  	"	</td>" +					  	
					  		"</tr>";				  
			      $('#tab_logic_tec tr:last').after($rowTecnicos);
			      $('#fieldTec0').find('option').clone().appendTo('#fieldTec'+(t+1)); 
			      t++;  
			  });
			     $("#delete_row_tec").click(function(){
			    	 if(t>0){	    		 
			    		 $("#addTecRow"+(t)).html('');
			    		 $("#addTecRow"+(t)).remove();
			    		 t--;
					 }
				 });
			     
			     var r=0;
				  $("#add_row_rad").click(function(){					 
					  var $rowRadios = "<tr id='addRadRow"+(r+1)+"'>" +
					  			" 	<td><button type='button'  class='removebutton btn btn-danger btn-xs pointer' title='Borrar esta linea'>&times;</button></td>"+
					  			"	<td>"+ (r+2) +"</td>" +
					  			"	<td>" +
					  			"		<div class='form-group row'>" +
								"			<select type='text' class='col-11 form-control' name='fieldRad[]' required id='fieldRad"+(r+1)+"'>" +						
								"			</select>" +
								"		</div>" +
							  	"	</td>" +					  	
						  		"</tr>";
				      $('#tab_logic_rad tr:last').after($rowRadios);
				      $('#fieldRad0').find('option').clone().appendTo('#fieldRad'+(r+1));				      
				      r++;  
				  });
				     $("#delete_row_rad").click(function(){
				    	 if(r>0){	    		 
				    		 $("#addRadRow"+(r)).html('');
				    		 $("#addRadRow"+(r)).remove();
				    		 r--;
						 }
					 });
				     
				     
				  // UPLOAD CLASS DEFINITION
				     // ======================

				    /* var dropZone = document.getElementById('drop-zone');
				     var uploadForm = document.getElementById('form' + elemento);

				     var startUpload = function(files) {
				         console.log(files)
				     }

				     uploadForm.addEventListener('submit', function(e) {
				         var uploadFiles = document.getElementById('js-upload-files').files;
				         e.preventDefault()

				         startUpload(uploadFiles)
				     })

				     dropZone.ondrop = function(e) {
				         e.preventDefault();
				         this.className = 'upload-drop-zone';

				         startUpload(e.dataTransfer.files)
				     }

				     dropZone.ondragover = function() {
				         this.className = 'upload-drop-zone drop';
				         return false;
				     }

				     dropZone.ondragleave = function() {
				         this.className = 'upload-drop-zone';
				         return false;
				     }*/
				 
				     // We can attach the `fileselect` event to all file inputs on the page
				     $(document).on('change', ':file', function() {
				       var input = $(this),
				           numFiles = input.get(0).files ? input.get(0).files.length : 1,
				           label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				       input.trigger('fileselect', [numFiles, label]);
				     });

				     // We can watch for our custom `fileselect` event like this
				     $(document).ready( function() {
				         $('#js-upload-files').on('fileselect', function(event, numFiles, label) {
				             var input = $(this).parents('.input-group').find(':text'),
				                 log = numFiles > 1 ? numFiles + ' archivos seleccionados' : label;
				                 console.log(log);
				             if( input.length ) {
				                 input.val(log);
				             } else {
				                 if( log ) alert(log);
				             }

				         });
				     });
		

});
function descargarArchivo(idArchivo){
	$.ajax({
	    url: './documentos',
	    type: 'POST',
	    data:{
	    	action: "descargarArchivoOrdenes",
	    	id: idArchivo,
	    	download: "0"
	    },
	    success: function(data) {
	    	if(data == 1){
	    		window.location = './documentos?action=descargarArchivoOrdenes&id='+idArchivo+'&download=true';
	    	}else{
	    		console.log("no existe el archivo!");
	    	}
	    }
	});
}
$(document).on('click','button.removebutton', function() {  
  $(this).closest('tr').remove();
  return false;
});
function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}