$(function() {
  // Now that the DOM is fully loaded, create the dropzone, and setup the
  // event listeners
	
var xhrGlobal;	
Dropzone.autoDiscover = false;
var myDropzone = new Dropzone("#dzHorario",{
	dictDefaultMessage: "Arrastra el horario aqui",
	method: "POST",
	url: "./documentos?action=procesarArchivo",	
	paramName: 'horario',
	autoProcessQueue: true,
	uploadMultiple: false,
	parallelUploads: 1,
	maxFiles: 1,
	maxFilesize: 5,
	acceptedFiles: ".xlsx, .xls",
	addRemoveLinks: true,
	init: function () {		
		this.on("sending", function(file, xhr, data) {
			 swal({
				    type: 'info',
				    title: 'Espere...',
				    html: 'Procesando archivo...',
				    timer: 1000
				  });
		});
		this.on('success', function (file, xhr, data) {
			var popUpHandle = window.open("./documentos?action=documentoProcesado", "popup", "height = 700, width=800, left=500, titlebar=no");
			xhrGlobal = xhr;
			setTimeout(() => {
				popUpHandle.document.getElementById("previewExcel").innerHTML = xhr;
			}, 1000); 
			
//			$("#previewExcel", popUpHandle.document).append(xhr);
//			$("#previewExcel").append(xhr);//file.xhr.response
			/*$('#btnGuardarHorario').removeAttr( "hidden" );
			$('#btnBorrarHorario').removeAttr( "hidden" );
			$("#btnGuardarHorario").show();
			$("#btnBorrarHorario").show();*/

			
			
			
//			this.removeFile(file);
			$("#dropHorario").hide();
			swal({
			    type: 'success',
			    title: 'Correcto!',
			    html: 'Archivo procesando!',
			    timer: 1000
			  });
		});
	} 
});

/**-------
 * BOTONES|
 * -------
 */
$('#btnNuevaCargaHorario').on('click', inicializaCarga);
$('#btnGuardarHorario').on('click', guardarHorario);
$('#btnBorrarHorario').on('click', cancelFiles);

function inicializaCarga() {
	 $("#dropHorario").show();
}

window.Cancelar = function() {
	cancelFiles();
}

window.Guardar = function() {
	guardarHorario();
}

function cancelFiles(){
	  myDropzone.removeAllFiles( true );
}


  myDropzone.on("addedfile", function(file) {
    /* Maybe display some more file information on your page */
//	  alert("agregado!");
	  var ext = file.name.split('.').pop();

	    if (ext == "pdf") {
	        $(file.previewElement).find(".dz-image img").attr("src", "./img/icons/pdf.png");
	    } else if (ext.indexOf("doc") != -1) {
	        $(file.previewElement).find(".dz-image img").attr("src", "./img/icons/word.ico");
	    } else if (ext.indexOf("xls") != -1) {
	        $(file.previewElement).find(".dz-image img").attr("src", "./img/icons/excel.png");
	    } else if (ext.indexOf("xlsx") != -1) {
	        $(file.previewElement).find(".dz-image img").attr("src", "./img/icons/excel.png");
	    }
  });
  
  
  
  myDropzone.on("removedfile", function (file) {
	  swal({
		  title: 'Desea borrar el archivo de horario?',
		  text: "El horario sera borrado de la vista actual, esto no borra en la base de datos!",
		  type: 'question',
		  showCancelButton: true,		  
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Si, borrarlo!',
		  cancelButtonText: 'No, espera!',
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		  showLoaderOnConfirm: true,		 
		  preConfirm: function () {
		    return new Promise(function (resolve, reject) {
		    	$("#dropHorario").show();
		    	//$("#previewExcel").empty();
		    	//$("#btnGuardarHorario").hide();
		    	//$("#btnBorrarHorario").hide();
		    	resolve();
		    })
		  },
		  allowOutsideClick: false
		}).then(function () {
		  swal({
		    type: 'success',
		    title: 'Archivo borrado!',
		    timer: 1000,
		    html: 'Archivo borrado'
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
			    abrePopUpHorarioGlobal();
			   /* $("#btnGuardarHorario").show();
			    $("#btnBorrarHorario").show();*/
			  }
		}).catch(swal.noop);	 
  });
  
  
  
  function guardarHorario(){
	  swal({
		  title: 'Desea guardar el horario?',
		  text: "El horario sera guardado en la base de datos!",
		  type: 'question',
		  showCancelButton: true,		  
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Si, guardalo!',
		  cancelButtonText: 'No, espera!',
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		  showLoaderOnConfirm: true,		 
		  preConfirm: function () {
		    return new Promise(function (resolve, reject) {
		    	$.ajax({
		    	  method: "POST",
		  		  url: "./documentos",
		  		  data: {
		  			  action: "guardarHorarioArchivo"
		  		  },		  
		  		  success: function(result){	  
		  	        $("#initRow").html(result);
		  	        $("#dropHorario").show();
		  	        closeAlerts();
			    	/*$("#previewExcel").empty();
			    	$("#btnGuardarHorario").hide();
			    	$("#btnBorrarHorario").hide();*/
		  	        resolve();
		  		  },
		  		  complete: function(xhr, status){	  
		  		       console.log(xhr);
		  		  }
		  	  });
		    })
		  },
		  allowOutsideClick: false
		}).then(function () {
		  swal({
		    type: 'success',
		    title: 'Horario guardado!',
		    html: 'Archivo guardado'
		  })
		  /*setTimeout(() => {
			  cancelFiles();
		}, 1000);*/
		  
		}, function (dismiss) {
			  // dismiss can be 'cancel', 'overlay',
			  // 'close', and 'timer'
			  if (dismiss === 'cancel') {
			    swal(
			      'Detenido',
			      'Puede volver a revisar los datos',
			      'error'
			    )
			   abrePopUpHorarioGlobal();
			  }
		}).catch(swal.noop);	  
	  
  }  
  
  function abrePopUpHorarioGlobal() {
	  var popUpHandle = window.open("./documentos?action=documentoProcesado", "_parent", "height = 700, width=800, left=500, titlebar=no");	//popup en ves de _parent			
		setTimeout(() => {
			popUpHandle.document.getElementById("previewExcel").innerHTML = xhrGlobal;
		}, 1000); 
}
  
  var tabla = document.getElementById("documentosHorarios");
  var tablaExiste = document.getElementById("contenedorTabla").contains(tabla);
	if(tablaExiste){
		$('#documentosHorarios').DataTable({
				fixedHeader: true,
				 "language": {
		                "url": "./js/dataTables.spanish.lang.json"
		            },
		        "bFilter":true,
		        responsive: true
			});
		$("#documentosHorarios_filter").css("margin-right", "4em"); 
	}else{
		console.log('tabla no existe!');
	}

  
});





