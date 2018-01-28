$(function(){

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


	$( "#fecha1" ).datepicker({
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

	$( "#fecha2" ).datepicker({
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