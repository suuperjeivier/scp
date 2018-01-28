function ProcesarBitacora(){
	  swal({
		  title: 'Desea procesar las asistencias?',
		  text: "Se dispone a procesar las asistencias!",
		  type: 'question',
		  showCancelButton: true,		  
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Si, procesarlas!',
		  cancelButtonText: 'No, espera!',
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: true,
		  showLoaderOnConfirm: true,		 
		  preConfirm: function () {
		    return new Promise(function (resolve, reject) {
		    	$.ajax({
		  		  url: "./bitacora",
		  		  data: {
		  			  action: "procesarBitacora"
		  		  },		  
		  		  success: function(result){	  
		  	        
		  	        resolve();
		  		  },
		  		  complete: function(xhr, status){	  
		  			$("#initRow").html(xhr);
		  		       console.log(xhr);
		  		  }
		  	  });
		    })
		  },
		  allowOutsideClick: false
		}).then(function () {
		  swal({
		    type: 'success',
		    title: 'Asistencias procesadas!',
		    html: 'Asistencias procesadas'
		  })
		  
		}, function (dismiss) {
			  // dismiss can be 'cancel', 'overlay',
			  // 'close', and 'timer'
			  if (dismiss === 'cancel') {
			    swal(
			      'Detenido',
			      'Puede volver a revisar los datos',
			      'error'
			    )
			  }
		}).catch(swal.noop);	
	
}

$("#btnImprimirAsistencias").click(function(e) {
	 window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#divTablaAsistencias').html()));
	 e.preventDefault();
});

$(function() {
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
	
	$( "#fechaDe" ).datepicker();
	$( "#fechaA" ).datepicker();
});

function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}