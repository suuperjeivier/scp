$(function() {  
	if(sesTimeOut >= 0){
		setTimeout(function(){ 
			window.location.href = "./login?action=try";
			console.log("sesion cerrada");
		}, 
		(sesTimeOut*1000));
	}else{
		//session infinita
	}
	
	$("#userAvatar").change(function(){
	    readURL(this);
	});
  $('#userInfoModal').on('show.bs.modal', function(e) {

	    //get data-id attribute of the clicked element
	    var nombreUsuario = $(e.relatedTarget).data('username');
	    var nombreEmpleado = $(e.relatedTarget).data('nombre');

	    //populate the textbox
	    $(e.currentTarget).find('input[name="usernameField"]').val(nombreUsuario);
	    $(e.currentTarget).find('#titleEdit').html(nombreEmpleado);
	    
	});
 // notifyMe();
  var stringPathNameSearchHash = window.location.pathname;
  stringPathNameSearchHash += window.location.search;
  stringPathNameSearchHash += window.location.hash;
  //console.log(stringPathNameSearchHash);
  var subMenuItem = $('a[href="'+stringPathNameSearchHash+'"]');
  subMenuItem.addClass('active');
  subMenuItem.parents('.nav-item').addClass('active');
  
  $('#userForm').on('submit',function(event){
	  event.preventDefault() ;
	  event.stopPropagation();
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
					  var actualizar = true;
					  action = "actualizarDatosUsuario";
					  var form = $('#userForm')[0]; // You need to use standart javascript object here
					  var formData = new FormData(form);	
					  $.ajax({								
						  method: 'POST',
						  url: './listados?action=' + action,
						  data: formData,
						  type: 'POST',
						  contentType: false,  
						  processData: false,
						  success: function(data){
							  console.log(data);
							  if (data == '1'){ 
								  $('#userForm')[0].reset();
								  var mensaje = '';
								  if(actualizar){
									  mensaje = 'actualizado';
								  }else{
									  mensaje = 'guardado';
								  }
								  swal({
									  type: 'success',
									  title: 'Correcto',
									  html: 'Usuario '+mensaje+'!'
								  });
								  $('#userInfoModal').modal('toggle');
								  var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Usuario guardado correctamente!.</div>';
								  $("#rowInit").html(alertSuccess);	
							  }else{										
								  var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data+'. </div>';
								  $("#rowInit").html(alertError);										
								  swal({
									  type: 'error',
									  title: 'Usuario no guardado!',
									  html: 'Usuario no guardado'
								  });
							  }
						  },
						  error: function(data){
							  var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.responseText+'. </div>';
							  $("#rowInit").html(alertError);
							  swal({
								  type: 'error',
								  title: 'Usuario no guardado!',
								  html: 'Usuario no guardado'
							  });
						  },
						  complete: function(xhr, status){	
							  console.log(xhr);
							  console.log(xhr.responseText);
							  console.log(status);
						  }
					  });
				  });
			  },
			  allowOutsideClick: false
		  }).then(function () {


		  }, function (dismiss) {					
			  if (dismiss === 'cancel') {
				  swal({
					  type: 'error',
					  title: 'Detenido!',
					  html: 'puede volver a revisar los datos'
				  });
			  }
		  }).catch(swal.noop);
	  } catch (e) {
		  throw new Error(e.message);
	  }

  });
  
  registraServiceWorker();
  
});
function registraServiceWorker() {
	if ('serviceWorker' in navigator) {
		  window.addEventListener('load', function() {
		    navigator.serviceWorker.register('./sw.js').then(function(registration) {
		      // Registration was successful
		      console.log('ServiceWorker registration successful with scope: ', registration.scope);
		    }).catch(function(err) {
		      // registration failed :(
		      console.log('ServiceWorker registration failed: ', err);
		    });
		  });
		}
}

function closeAlerts() {
	$(".alert").fadeTo(3000, 500).slideUp(500, function(){
		 $(".alert").slideUp(500);
	});
}

function reset() {
	var username = $( "#usernameField" ).val();
	$('#userForm')[0].reset();
	$( "#usernameField" ).val(username);
}
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imgAvatar').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

document.addEventListener('DOMContentLoaded', function () {
		var ua = window.navigator.userAgent;
	    var msie = ua.indexOf("MSIE ");	
	    if (msie > 0) // If Internet Explorer, return version number
	    {
	    	if (!Notification) {
	    	    alert('Desktop notifications not available in your browser. Try Chromium.'); 
	    	    return;
	    	  }
	
	    	  if (Notification.permission !== "granted")
	    	    Notification.requestPermission();
	        
	    }
	  
	});

	function notifyMe() {
	  if (Notification.permission !== "granted")
	    Notification.requestPermission();
	  else {
	    var notification = new Notification('Hola de nuevo ' +  $("#userNameSession").text(), {
	      icon: $("#logo_sistema").attr('src'),
	      body: "Bienvenido a SICAP",
	    });

	    notification.onclick = function () {
	      window.open("http://segob.tabasco.gob.mx/");      
	    };

	  }

	}
	
	
	





