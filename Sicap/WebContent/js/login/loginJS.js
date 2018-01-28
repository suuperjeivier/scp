$(function() {
    
    var $formLogin = $('#login-form');
    //var $formLost = $('#lost-form');
   // var $formRegister = $('#register-form');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    $("form").submit(function () {
    	document.getElementById("loginBtn").disabled = true;
    	switch(this.id) {
    	case "login-form":
    		var $lg_username=$('#login_username').val();
    		var $lg_password=$('#login_password').val();
    		$("#loginBtn").html("<img src='./img/icons/ellipsis.gif' width='36' />");
    		$.ajax({                	
    			method: "POST",
    			url: "./login",
    			data: {    		  			  
    				action: "doLogin",
    				user: $lg_username,
    				pass: $lg_password
    			},		  
    			success: function(result){
//    				console.log(result);    		
    				if (result == "1") {
    					msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Login OK"); 					
    					window.setTimeout(function() {
    						msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Iniciando"); 		
    						window.location.href = './login?action=init';
//    						document.getElementById("loginBtn").disabled = false;
    					}, 1000);
    					
    				}else if (result == "-2") {
    					msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "warning", "glyphicon-remove", "Sin configuracion para este sistema");					
    					/*window.setTimeout(function() {
    						msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Iniciando"); 		
    						window.location.href = './login?action=init';
    						document.getElementById("loginBtn").disabled = false;
    					}, 1000);*/
    				}else {
    					msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Login error");
    					setTimeout(function() {
    						document.getElementById("loginBtn").disabled = false;
    					}, 2000);
    						

    					
    				}
    			},
    			complete: function(xhr, status){	  
    				//console.log(xhr);
    				$("#loginBtn").html("Login");
    			}
    		});


    		return false;
    		break;

    	default:
    		return false;
    	}
    	return false;
    });
    
    $('#login_register_btn').click( function () { modalAnimate($formLogin, $formRegister) });
    $('#register_login_btn').click( function () { modalAnimate($formRegister, $formLogin); });
    $('#login_lost_btn').click( function () { modalAnimate($formLogin, $formLost); });
    $('#lost_login_btn').click( function () { modalAnimate($formLost, $formLogin); });
    $('#lost_register_btn').click( function () { modalAnimate($formLost, $formRegister); });
    $('#register_lost_btn').click( function () { modalAnimate($formRegister, $formLost); });
    
    function modalAnimate ($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height",$oldH);
        $oldForm.fadeToggle($modalAnimateTime, function(){
            $divForms.animate({height: $newH}, $modalAnimateTime, function(){
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }
    
    function msgFade ($msgId, $msgText) {
        $msgId.fadeOut($msgAnimateTime, function() {
            $(this).text($msgText).fadeIn($msgAnimateTime);
        });
    }
    
    function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
        var $msgOld = $divTag.text();
        msgFade($textTag, $msgText);
        $divTag.addClass($divClass);
        $iconTag.removeClass("glyphicon-chevron-right");
        $iconTag.addClass($iconClass + " " + $divClass);
        setTimeout(function() {
            msgFade($textTag, $msgOld);
            $divTag.removeClass($divClass);
            $iconTag.addClass("glyphicon-chevron-right");
            $iconTag.removeClass($iconClass + " " + $divClass);
  		}, $msgShowTime);
    }
    //PARA APP DE GOOGLE CHROME
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
    
});