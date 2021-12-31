$(function() {
    $('.accountLogin-content-toggleBtn').on('click', function () {
        var body = $("body");
        body.toggleClass('page-login-register');
        if (body.hasClass('page-login-register')) {
            SAGIA.register.init();
        }
        $('.accountLogin').show();
    });
    
    $(".toggle-password").click(function(){
    	  
    	  $(this).toggleClass("fa-eye fa-eye-slash");
    	    
    	    var input = $("#j_password");
    	    input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
    	    		
    	  /*// Check the checkbox state
    	  if($(this).is(':checked')){
    	   // Changing type attribute
    	   $("#j_password").attr("type","text");
    	   
    	   // Change the Text
    	   $("#toggleText").text("Hide");
    	  }else{
    	   // Changing type attribute
    	   $("#j_password").attr("type","password");
    	  
    	   // Change the Text
    	   $("#toggleText").text("Show");
    	  }*/
    	 
    	 });
	
	$('#sagiaLoginForm').submit(function(e){
    	/*$("#sagiaLoginBtn").attr("disabled", true);
        return true;*/
		$('#j_username').val($('#j_username').val().toLowerCase());
        $("#sagiaLoginBtn", this)
          .attr('disabled', 'disabled');
        return true;
      });

    if (document.location.href.indexOf('/register') >= 0 || document.location.href.indexOf('#register') >= 0 &&
        document.location.href.indexOf('/login')) {
        $('body').addClass('page-login-register');
        SAGIA.register.init();
    }

    $('.panelTabs_nested .tabhead').each(function (i, elem) {
        var _trigger = $('.panelTabs_nested .panelTabs_nested-head').filter(function (j, el) {
            return $(el).attr('href') === ("#" + $(elem).attr('id'));
        });
        if ($(this).data('show') && $(this).data('show').indexOf('#') === 0) {
            var _targetShow = $(this).data('show');
            $(_trigger).click(function () {
                if ($(_targetShow).data('show-cls')) {
                    $(_targetShow).addClass($(_targetShow).data('show-cls')).removeClass($(_targetShow).data('hide-cls'));
                    $(_targetShow).parent().children('[data-onshow-cls]').each(function () {
                        $(this).addClass($(this).data('onshow-cls')).removeClass($(this).data('onhide-cls'));
                    });
                }
            });
        }
        if ($(this).data('hide') && $(this).data('hide').indexOf('#') === 0) {
            var _targetHide = $(this).data('hide');
            $(_trigger).click(function () {
                if ($(_targetHide).data('hide-cls')) {
                    $(_targetHide).addClass($(_targetHide).data('hide-cls')).removeClass($(_targetHide).data('show-cls'));
                    $(_targetHide).parent().children('[data-onhide-cls]').each(function () {
                        $(this).addClass($(this).data('onhide-cls')).removeClass($(this).data('onshow-cls'));
                    });
                }
            });
        }
    });

    $("#loginSelectLanguageDiv #loginSelectLanguageSelect").on("change", function() {
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login";
    });

    $("#registerQuickSelectLanguageDiv #registerQuickSelectLanguageSelect").on("change", function() {
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login#register-quick";
    });

    $("#registerApplySelectLanguageDiv #registerApplySelectLanguageSelect").on("change", function() {
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login#register-apply";
    });

    $("#registerNationalInvestorSelectLanguageDiv #registerNationalInvestorSelect").on("change", function() {
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login#register-national-investor";
    });
    
    $("#editCountryCode").on('click', function(){
		$('#countryCode').prop("disabled", false);
	});
	
	$("#editMobile").on('click', function(){
		$('#mobile').prop("disabled", false);
	});
	
	$("#editEmail").on('click', function(){
		$('#regEmail').prop("disabled", false);
	});
	
	$("#editQEmail").on('click', function(){
		$('#qeemahEmail').prop("disabled", false);
	});
	
	$("#mobileBtn").on('click', function(){
		$('#countryCode').prop("disabled", false);
		$('#mobile').prop("disabled", false);
		$('#countryCode').val($('#countryCode').val().replace(/^0+/, ''));
		$('#mobile').val($('#mobile').val().replace(/^0+/, ''));
	});
	
	$("#regEmailBtn").on('click', function(){
		$('#regEmail').prop("disabled", false);
	});
	
	$("#qeemahEmailBtn").on('click', function(){
		$('#qeemahEmail').prop("disabled", false);
	});
    
    $(".js-mobile-coutry-code").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
     $(".js-quick-mobile-number").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
   });
   		  
   $('#regEmail').focusout(function() {
   	var sEmail = $('#regEmail').val();
   	
   	if (!validateEmail(sEmail)) {
   	 	$("#emailR").text("Invalid Email ID");
   	 	$("#regEmailBtn").attr("disabled", true);
   	}
   	else {
   		$("#regEmailBtn").attr("disabled", false);
   		}
   });

   $('#regEmail').focusin(function() {
   	$("#emailR").text("");
   });

   $('#qeemahEmail').focusout(function() {
   	var sEmail = $('#qeemahEmail').val();

   	if (!validateEmail(sEmail)) {
   	 	$("#emailQ").text("Invalid Email ID");
   	 	$("#qeemahEmailBtn").attr("disabled", true);
   	}
   	else {
   		$("#qeemahEmailBtn").attr("disabled", false);	
   	}

   });

   $('#qeemahEmail').focusin(function() {
   	$("#emailQ").text("");
   });
   
   function validateEmail(sEmail) {
	 //var filter = /^[w-.+]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
	  //var filter = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	  var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	  return filter.test(sEmail);
	 }
   $(document).ready(function(e) {
	   $("#resendBtn").attr("disabled", true);
       setTimeout(function(){$("#resendBtn").attr("disabled", false)}, 420000);
   });
});
