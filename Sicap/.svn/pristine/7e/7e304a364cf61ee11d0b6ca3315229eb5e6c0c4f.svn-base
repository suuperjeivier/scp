$(function(){
	var empleadoDTO = null;
    // Cache some selectors

    var clock = $('#clock'),
        alarm = clock.find('.alarm'),
        ampm = clock.find('.ampm');
    var now2 = '';
    var existeEmpleado = false;
    //inicializa la animacion diaNoche
    animacionDiaNoche(); 
    
    // Map digits to their names (this will be an array)
    var digit_to_name = 'zero one two three four five six seven eight nine'.split(' ');

    // This object will hold the digit elements
    var digits = {};

    // Positions for the hours, minutes, and seconds
    var positions = [
        'h1', 'h2', ':', 'm1', 'm2', ':', 's1', 's2'
    ];

    // Generate the digits with the needed markup,
    // and add them to the clock

    var digit_holder = clock.find('.digits');

    $.each(positions, function(){

        if(this == ':'){
            digit_holder.append('<div class="dots">');
        }
        else{

            var pos = $('<div>');

            for(var i=1; i<8; i++){
                pos.append('<span class="d' + i + '">');
            }

            // Set the digits as key:value pairs in the digits object
            digits[this] = pos;

            // Add the digit elements to the page
            digit_holder.append(pos);
        }

    });

    // Add the weekday names

    var weekday_names = 'LUN MAR MIE JUE VIE SAB DOM'.split(' '),
        weekday_holder = clock.find('.weekdays');

    $.each(weekday_names, function(){
        weekday_holder.append('<span>' + this + '</span>');
    });

    var weekdays = clock.find('.weekdays span');

    // Run a timer every second and update the clock

    (function update_time(){

        // Use moment.js to output the current time as a string
        // hh is for the hours in 12-hour format,
        // mm - minutes, ss-seconds (all with leading zeroes),
        // d is for day of week and A is for AM/PM
    	var n = moment();
        var now = n.format("hhmmssdA");
        now2 = n.format('HH:mm:ss');
        
        digits.h1.attr('class', digit_to_name[now[0]]);
        digits.h2.attr('class', digit_to_name[now[1]]);
        digits.m1.attr('class', digit_to_name[now[2]]);
        digits.m2.attr('class', digit_to_name[now[3]]);
        digits.s1.attr('class', digit_to_name[now[4]]);
        digits.s2.attr('class', digit_to_name[now[5]]);

        // The library returns Sunday as the first day of the week.
        // Stupid, I know. Lets shift all the days one position down, 
        // and make Sunday last

        var dow = now[6];
        dow--;

        // Sunday!
        if(dow < 0){
            // Make it last
            dow = 6;
        }

        // Mark the active day of the week
        weekdays.removeClass('active').eq(dow).addClass('active');

        // Set the am/pm text:
        ampm.text(now[7]+now[8]);

        // Schedule this function to be run again in 1 sec
        setTimeout(update_time, 1000);

    })();

    // Switch the theme

    $('a.button').click(function(){
        clock.toggleClass('light dark');
    });
    
    //TERMINA CODIGO RELOJ
    
    $('#formRegistros').on('submit',function(event){
    	event.preventDefault() ;
    	event.stopPropagation();
    	try{
    		var sku = $("#inputSku").val();
    		var hora = now2;
	    		buscaEmpleado(sku, hora).done(function(){
	    			if(existeEmpleado){
			    		var action ="guardarRegistroDeAsistencia";
			    		$.ajax({
			    			url: "./bitacora",
			    			method: "POST",
			    			data: {
			    				action: action,			    			
			    				idEmpleado: empleadoDTO.idEmpleado,
			    				inputSku: empleadoDTO.skuEmpleado,
			    				horaRegistro: hora
			    			},		  
			    			success: function(data){
			    				console.log(empleadoDTO.idEmpleado);
			    				if(data != '0'){
			    					$('#formRegistros')[0].reset();
			    					setTimeout(() => {
			    						location.reload();
									}, 3000);
			    					swal({
			    						type: 'success',
			    						title: 'Correcto, registro guardado!',
			    						timer: 1500,
			    						html: data.mensaje
			    					});
			    					
			    				}else{
			    					swal({
			    						type: 'error',
			    						title: 'Error!',
			    						timer: 2000,
			    						html: 'Registro no guardado 444'
			    					});
			    				}
			    			},
			    			error: function(data){
			    				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
			    				$("#initRow").html(alertError);
			    				swal({
			    					type: 'error',
			    					title: 'Error!',
			    					timer: 2000,
			    					html: 'Registro no guardado'
			    				})
			    			},
			    			complete: function(xhr, status){	
			    				/*var alertSuccess = '<div class="alert alert-success"><strong>Correcto!</strong> Registro guardado correctamente!.</div>';
			    				var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+xhr.text+'. </div>';
			    				if(status != 'error'){
			    					$("#initRow").html(alertSuccess);	
			    				}else{
			    					$("#initRow").html(alertError);	
			    				}								
			    				console.log(xhr);*/
			    			}
			    		});	    			
	    			}else{
	    	    		swal({
	    					type: 'warning',
	    					title: 'No encontrado!',
	    					timer: 2000,
	    					html: 'Empleado no encontrado'
	    				});
	    	    		//CLEAR FOCUS SKU INPUT
	    	    	}
	    		}).fail(function(){
	    			swal({
    					type: 'error',
    					title: 'Error!',
    					timer: 2000,
    					html: 'No se puede ralizar la busqueda del empleado!'
    				})
	    	    });
	    			
	    		
    	} catch (e) {
    		throw new Error(e.message);
    	}

	});	
    
    
    function buscaEmpleado(sku, hora) {
    	existeEmpleado = false; 
    	var action ="selectEmpleado";
    	return $.ajax({
    		   	
    		url: "./listados",
    		data: {
    			action: action,
    			skuEmpleado: sku			
    		},		  
    		success: function(data){
    			if(data != '-1' && data != "-2"){
    				empleadoDTO = data;
    				$("#imgEmployee").attr("src", empleadoDTO.fotoEmpleadoBase64);
    				$("#nombreEmpleado").text(empleadoDTO.nombreCompletoEmpleado);
    				existeEmpleado = true;
    			}else{
    				existeEmpleado = false;
    			}
    		},
    		error: function(data){
    			var alertError = '<div class="alert alert-danger"><strong>Error!</strong> '+data.text+'. </div>';
    			$("#initRow").html(alertError);
    		},
    		complete: function(xhr, status){	
    			
    		}
    	});
    	
    }
    
    function animacionDiaNoche(){
    	 var now = new Date().getHours();
    	    var text, color;
    	    var duracionBase, duracion1, duracion2, duracion3, duracion4, duracion5, duracion6;
    	    var f = 24 - now;
    	    var mls = 3600000;
    	    var def1 = mlsToHours(12000);
    	    
    	    var def2 = mlsToHours(5000);
    	    var def3 = mlsToHours(300);
    	    var def4 = mlsToHours(29000);
    	    var def5 = mlsToHours(35000);
    	    var def6 = mlsToHours(40000);
    	    console.log(def1);
    	    console.log(def2);
    	    console.log(def3);
    	    console.log(def4);
    	    console.log(def5);
    	    console.log(def6);
    	    
    	    if (now >= 6 && now < 12){
    	    	duracion1= ((f-12)*mls);
    	    	duracion2 = ((f-12)*mls);
    	    	duracion3 = ((f-12)*mls);
    	    	duracion4 = ((f-12)*mls);
    	    	duracion5 = ((f-12)*mls);
    	    	duracion6 = ((f-12)*mls);
    	    	
    	    }
    	    else if (now >= 12 && now < 17){
    	    	text = 'Hello,';
    	    	color = '#CC7A29';  
    	    	duracionBase = (17-f)*mls;
    	    	duracion1= duracionBase;
    	    	duracion2 = duracionBase;
    	    	duracion3 = duracionBase;
    	    	duracion4 = duracionBase;
    	    	duracion5 = duracionBase;
    	    	duracion6 = duracionBase;
    	    }
    	    else if (now >= 17 && now < 19 ){
      	      text = 'Good Evening,';
      	      color = '#005CE6';
      	      	duracionBase = (19-f)*mls;
	      	    duracion1= duracionBase/12000;
		    	duracion2 = duracionBase/5000;
		    	duracion3 = duracionBase/300;
		    	duracion4 = duracionBase/29000;
		    	duracion5 = duracionBase/35000;
		    	duracion6 = duracionBase/40000;
      	    }
    	    else if (now >= 19 && now < 22 ){
    	      text = 'Good night,';
    	      color = '#005CE6';
    	      duracion1= (22-f)*mls/12000;
  	    	duracion2 = (22-f)*mls/5000;
  	    	duracion3 = (22-f)*mls/300;
  	    	duracion4 = (22-f)*mls/29000;
  	    	duracion5 = (22-f)*mls/35000;
  	    	duracion6 = (22-f)*mls/40000;
    	    }
    	    else{
    	      text = 'Good Night,';
    	      color = '#001C53';
    	      duracion1= (24-now)*mls/12000;
  	    	duracion2 = (24-now)*mls/5000;
  	    	duracion3 = (24-now)*mls/300;
  	    	duracion4 = (24-now)*mls/29000;
  	    	duracion5 = (24-now)*mls/35000;
  	    	duracion6 = (24-now)*mls/40000;
    	    }
    	    
    	    console.log(f);
	    	console.log(duracion1 + " " + duracion2 + " " + duracion3 + " " + duracion4 + " " + duracion5 + " " + duracion6);
    	    
    	    /*$('#sun_yellow').animate({'top':'96%','opacity':0.4}, duracion1,function(){
	            $('#stars').animate({'opacity':1}, duracion2,function(){
	                $('#moon').animate({'top':'30%','opacity':1}, duracion2, function(){
	                    $('#sstar').animate({'opacity':1}, 300);
	                    $('#sstar').animate({
	                        'backgroundPosition':'0px 0px','top':'15%', 'opacity':0
	                    }, 500,function(){
	                    	animacionDiaNoche();                    	
	                    });
	                });
	            });
	        });
	        $('#sun_red').animate({'top':'96%','opacity':0.8}, duracion1);
	        $('#sky').animate({'backgroundColor':'#4F0030'}, duracion1);
	        $('#clouds').animate({'backgroundPosition':'1000px 0px','opacity':0}, duracion1);
	        $('#night').animate({'opacity':0.8}, duracion2);*/
	        
	        $('#sun_yellow').animate({'top':'96%','opacity':0.4}, duracion1,function(){
	            $('#stars').animate({'opacity':1}, 5000,function(){
	                $('#moon').animate({'top':'30%','opacity':1}, duracion2, function(){
	                    $('#sstar').animate({'opacity':1}, duracion3);
	                    $('#sstar').animate({
	                        'backgroundPosition':'0px 0px','top':'15%', 'opacity':0
	                    }, 500,function(){
//	                    	animacionDiaNoche();      
	                    	
	                    	animacionNocheDia(duracion4, duracion5, duracion1);
	            	        $('#night').animate({'opacity':0.1}, duracion5-200);
	            	        $('#clouds').animate({'backgroundPosition':'0px 0px','opacity':0.9}, duracion6);
	            	        $('#sky').animate({'backgroundColor':'#fff'}, duracion5);
	                    });
	                });
	            });
	        });
	        $('#sun_red').animate({'top':'96%','opacity':0.8}, duracion1);
	        $('#sky').animate({'backgroundColor':'#4F0030'}, duracion1+6000);
	        $('#clouds').animate({'backgroundPosition':'1000px 0px','opacity':0}, duracion5-5000);
	        $('#night').animate({'opacity':0.8}, duracion4-9000);    	
    	
    	
    }
    
    function mlsToHours(mls){
    	var mlsInHour = mls/3600000;
    	return mlsInHour;
    }
    
    function animacionNocheDia(duracion4, duracion5, duracion1){
    	$('#moon').animate({'top':'96%','opacity':0.1}, duracion4);
        
        $('#stars').animate({'opacity':0.1}, duracion4,function(){
    		$('#sun_yellow').animate({'top':'30%','opacity':0.8}, duracion5+100,function(){  
    			
    		});
    		$('#sun_red').animate({'top':'30%','opacity':0.0}, duracion5, function(){
    			$('#sun_red').animate({'opacity':0.1}, duracion1);
        		$('#sun_yellow').animate({'opacity':0.8}, duracion1,function(){  
        			animacionDiaNoche(); 
        		});
    		});
        });
    }

});


