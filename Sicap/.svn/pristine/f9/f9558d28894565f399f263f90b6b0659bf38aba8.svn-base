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
			yearSuffix: ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
	
	$( "#fechaDe" ).datepicker({
			changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        viewMode: "months", 
	        minViewMode: "months",
	        dateFormat: 'mm/yy',
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
	
	
	
//	$( "#fechaA" ).datepicker();
	
	/* Defaults */
	/*$("#tb_asistencia").tableExport({
	    headings: true,                    // (Boolean), display table headings (th/td elements) in the <thead>
	    footers: true,                     // (Boolean), display table footers (th/td elements) in the <tfoot>
	    formats: ["xls", "csv", "txt", "pdf"],    // (String[]), filetypes for the export
	    fileName: "id",                    // (id, String), filename for the downloaded file
	    bootstrap: true,                   // (Boolean), style buttons using bootstrap
	    position: "bottom" ,                // (top, bottom), position of the caption element relative to table
	    ignoreRows: null,                  // (Number, Number[]), row indices to exclude from the exported file(s)
	    ignoreCols: null,                  // (Number, Number[]), column indices to exclude from the exported file(s)
	    ignoreCSS: ".tableexport-ignore",  // (selector, selector[]), selector(s) to exclude from the exported file(s)
	    emptyCSS: ".tableexport-empty",    // (selector, selector[]), selector(s) to replace cells with an empty string in the exported file(s)
	    trimWhitespace: false              // (Boolean), remove all leading/trailing newlines, spaces, and tabs from cell text in the exported file(s)
	});*/
});

function getDefaultDate(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = (day) + "/" + (month)+ "/" + now.getFullYear();
    return today;
}

function getDefaultMonthAndYear(){
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = month + " " + now.getFullYear();
    return today;
}

function exportaExcel(){
	$('#tb_asistencia').tableExport({
		type:'excel',
		bootstrap: true,
		escape:'false',
		tableName:'asistencias',		
		pdfFontSize:10,
		pdfLeftMargin:10,		
		htmlContent:'true',
		consoleLog:'true',
		worksheetName: 'reporte'
	}); 
}

function exportaPDF(){
	$('#tb_asistencia').tableExport({
		type:'pdf',		
		jspdf: {
			orientation: 'l',		
            format: 'a4',
            margins: {
            	left:5,
            	right:5,
            	top:10,
            	bottom:10
            	},            
            autotable: {            	
            	theme: 'striped',
            	styles: {
            		fillColor: 'inherit',
            		textColor: 'inherit',
            		columnWidth: 'auto'            			
            		},
            	showHeader: 'everyPage',
            	tableWidth: 'auto',
            	tableExport: {
            		onAfterAutotable: null,            	
                    onBeforeAutotable: null,
                    onAutotableText: null,
                    onTable: null,
                    outputImages: true
            	}
            }           
        },		
        bootstrap: true,
        filename: 'reporte_c4tabasco.pdf',
		escape:'false',
		tableName:'tb_horarios',		
		pdfFontSize:8,
		pdfLeftMargin:8,		
		htmlContent:'true',
		consoleLog:'true'
	});
}

function generate() {
	var doc = new jsPDF('l', 'pt');

	var elem = document.getElementById('tb_asistencia');
	var imgElements = document.querySelectorAll('#tb_asistencia thead img');
	var data = doc.autoTableHtmlToJson(elem);
	var images = [];
	var i = 0;
	doc.autoTable(data.columns, data.rows, {
		bodyStyles: {rowHeight: 30},
		drawCell: function(cell, opts) {
			if (opts.column.dataKey === 5) {
				images.push({
					url: imgElements[i].src,
					x: cell.textPos.x,
					y: cell.textPos.y
				});
				i++;
			}
		},
		addPageContent: function() {
			for (var i = 0; i < images.length; i++) {
				doc.addImage(images[i].url, images[i].x, images[i].y, 20, 20);
			}
		}
	});

	doc.save("table.pdf");
}
