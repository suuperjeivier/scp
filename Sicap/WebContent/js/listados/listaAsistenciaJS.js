$(function() {
	$('#btnJasper').on('click', function(){
		window.location = "./bitacora?action=reporteJasper";
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

function exportaExcel2(){
	
	var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById('tb_asistencia')
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
}



function exportaPDF(){
	$('#tb_asistencia').tableExport({		
		type:'pdf',
		filename: 'reporte_c4tabasco.pdf',
		bootstrap: true,
		escape:'false',
		tableName:'tb_horarios',		
		pdfFontSize:8,
		pdfLeftMargin:8,		
		htmlContent:'true',
		consoleLog:'true',			
		jspdf: {
			orientation: 'l',			
            format: 'letter',
            margins: {
            	left:5,
            	right:5,
            	top:10,
            	bottom:10
            	},            
            autotable: {            	
            	theme: 'striped',
            	startY: 40,
            	headerStyles: {
            		fillColor: [52, 73, 94],            		
                    textColor: 255,
                    fontStyle: 'bold',
                    halign: 'center',
            	},
            	alternateRowStyles:  {
            		fillColor: 245
		    	},
            	styles: {
            		overflow: 'linebreak',            	
            		fillColor: 'inherit',
            		textColor: 'inherit',
            		columnWidth: 'auto'            			
            	},            	
            	showHeader: 'firstPage',
            	tableWidth: 'auto',
            	tableExport: {
            		onAfterAutotable: null,            	
                    onBeforeAutotable: null,
                    onAutotableText: null,
                    onTable: null,
                    outputImages: true
            	}
            }           
        }		
       
	});
}

function generate() {
	var pdf = new jsPDF('l', 'pt', 'letter');
	
	 var text = "Lista de asistencias";
     pdf.text(250, 20, text);
     pdf.setFont("arial", "narrow");
     pdf.addImage(imgSrc, 'PNG', 10, 0, 50, 30);
	  var res = pdf.autoTableHtmlToJson(document.getElementById("tb_asistencia"));
	  pdf.autoTable(res.columns, res.data, {
		  theme: 'striped',	  
	    startY: 40,
	    startX: 2,
	    styles: {
	      overflow: 'linebreak',
	      fontSize: 6,	      
	      columnWidth: 'auto',	      
	    },
	    showHeader: 'firstPage',
    	tableWidth: 'auto'
	   
	  });

	  
	

	  pdf.save("table.pdf");
}

function generate2() {
	 var pdf = new jsPDF('l', 'pt', 'letter');
	
     // source can be HTML-formatted string, or a reference
     // to an actual DOM element from which the text will be scraped.
	 
	 var text = "Lista de asistencias";
     pdf.text(200, 0, text);
     pdf.setFont("arial", "bold");
     pdf.addImage(imgSrc, 'PNG', 0, 0, 50, 50);
     source = $('#tablaRegistros')[0];
     
     // we support special element handlers. Register them with jQuery-style 
     // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
     // There is no support for any other type of selectors 
     // (class, of compound) at this time.
     specialElementHandlers = {
         // element with id of "bypass" - jQuery style selector
         '#bypassme': function(element, renderer) {
             // true = "handled elsewhere, bypass text extraction"
             return true
         }
     };
     margins = {
         top: 50,
         bottom: 10,
         left: 40,
         width: 700
     };
     
    
     // all coords and widths are in jsPDF instance's declared units
     // 'inches' in this case
    
                
    
     pdf.fromHTML(
             source, // HTML string or DOM elem ref.
             margins.left, // x coord
             margins.top, {// y coord
                 'width': margins.width, // max width of content on PDF
                 'elementHandlers': specialElementHandlers
             },
             
     
     function(dispose) {
            	 
         // dispose: object with X, Y of the last line add to the PDF 
         //          this allow the insertion of new lines after html
         pdf.save('Test.pdf');
     }
     , margins);
 
}
