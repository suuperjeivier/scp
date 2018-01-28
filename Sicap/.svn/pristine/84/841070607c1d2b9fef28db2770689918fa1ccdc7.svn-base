var originalWindow;
$(function(){
	originalWindow = window.opener;
	/*originalWindow = window.opener;
	$('#btnGuardarHorario').on('click', opener.guardarHorario);
	$('#btnBorrarHorario').on('click', opener.cancelFiles);*/
	
	
});

function guardar() {	
	originalWindow.Guardar();
	window.close();
}

function cancelar(){
	var res = originalWindow.Cancelar();
	window.close();
}