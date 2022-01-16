$(function() {
    $('.accountLogin-content-toggleBtn').on('click', function () {
        var body = $("body");
        body.toggleClass('page-login-register');
        if (body.hasClass('page-login-register')) {
            $(".accountLogin-content").addClass('pt-45 mw-100')
			$(".accountLogin-content.pt-45 .panelTabs-content.container").addClass('w-100 mw-100');
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
        var lang = "en";
        if ($('input#loginSelectLanguageSelect:checked').length > 0 && $('input#loginSelectLanguageSelect:checked')[0].value !== "") {
            lang = "ar"
        }
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + lang + "/login";
        // window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login";
    });

    $("#registerQuickSelectLanguageDiv #registerQuickSelectLanguageSelect").on("change", function() {
        var lang = "en";
        if ($('input#registerQuickSelectLanguageSelect:checked').length > 0 && $('input#registerQuickSelectLanguageSelect:checked')[0].value !== "") {
            lang = "ar"
        }
        var encodedPath = ACC.config.encodedContextPath;
        window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + lang + "/login#register-quick";
        // window.location.href = encodedPath.substring(0, encodedPath.lastIndexOf("/")) + "/" + $(this).val() + "/login#register-quick";
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







//-----------------------



function recaptchaCallback(){
	$(".js-recaptcha-captchaaddon").siblings('span#lblErrorCaptcha').text('');			
}


$(document).ready(function(){

	if($(".accountLogin-content").is(':visible')){
		$(".accountLogin-content").addClass('pt-45 mw-100')
		$(".accountLogin-content.pt-45 .panelTabs-content.container").addClass('w-100 mw-100');
	}
})

$(document).ready(function(){
    var lang = window.location.pathname;
    
    if(lang.indexOf('ar') != -1){
        $('.register-form input').prop('checked',true);
    }
    else if(lang.indexOf('en') != -1){
       
    }
})


$(document).on('focus blur change click', '.formSelectBox', function (e) {
    var $currEl = $(this).find('.register-user-details');
    $currEl.parent().addClass('focus-on-change');
    if ($currEl.is('select')) {
        if($($currEl)[0].value !== undefined && $($currEl)[0].value.trim() !== ""){
            $currEl.parents('.focus-on-change').children('label').addClass('focused');
       }
       else if (e.type === "focusin") {
            $currEl.parents('.focus-on-change').children('label').addClass('focused');
        }
        else{ 
			if ($(".select2-results__options").is(':visible')) {
				$currEl.parents('.focus-on-change').children('label').addClass('focused');
			}
			else {
				$currEl.parents('.focus-on-change').children('label').removeClass('focused');
			}
        }
	}
})


$(document).on('focus blur change keyup', '.formInputBox .register-user-details', function (e) {
    console.log('e',e);
	var $currEl = $(this);
    $currEl.parent().addClass('focus-on-change');
	if (e.type === "keyup") {
		$(this).removeClass('required');
	}    
	if ($($currEl)[0].value !== undefined && $($currEl)[0].value.trim() !== "") {
		$currEl.parents('.focus-on-change').children('label').addClass('focused');
	}
	else if (e.type === "focusin") {
		$currEl.parents('.focus-on-change').children('label').addClass('focused');
	}
	else {
		$currEl.parents('.focus-on-change').children('label').toggleClass('focused');
	}


    if($currEl.val() !== ""){
        $(this).removeClass('hasError');
    }
    
}).trigger('blur');

$('.quickregistrationPwd').on('change paste keyup',function(e){
	// if($($currEl)[0].classList.contains("quickregistrationPwd")){
        var val = e.target.value;

        let regExpWeak = /(?=(.*[A-Za-z]){5,})/;
        let regExpMedium = /(?=(.*[0-9]){2,})/;
        let regExpStrong = /(?=(.*[*/}{~!@#$?%^&+=]))/;
        let no=0;

        if (val.length > 0  && val.length <=5 || (val.match(regExpWeak) || val.match(regExpMedium) || val.match(regExpStrong))) 
        no = 1;
        if (val.length >= 6 && ((val.match(regExpWeak) && val.match(regExpMedium)) || (val.match(regExpMedium) && val.match(regExpStrong)) || (val.match(regExpWeak) && val.match(regExpStrong)))) 
        no = 2;
        if (val.length >= 8 && val.match(regExpWeak) && val.match(regExpMedium) && val.match(regExpStrong))
        no = 3;

        $("#password-strength-meter").val(no);        
    // }
}).trigger('change');

$(".register-account-screen .login-btn-next").on('click',function(){
	if ($('.register-account-screen .role-investor.active').length > 0) {
		$('.register-account-screen').addClass('next-hide');
		// $('.register-account-investor-screen2').removeClass('next-hide');
		$('.register-account-investor-screen3').removeClass('next-hide');
	}
});

$(".register-account-screen .login-btn-cancel").on('click',function(){
	//toggle with register and login account
    window.location="/en/login";
});

$(".register-investor-screen2-btn-back").on('click',function(){
	$('.register-account-screen').removeClass('next-hide');
	$('.register-account-investor-screen2').addClass('next-hide');
})
$(".bussiness-sector .bussiness-sector-btn").on('click', function () {
	if($(this).attr("data-selected") == "no"){
		$(".bussiness-sector .bussiness-sector-btn").removeClass("selected");
		$(this).attr("data-selected","yes");
		$(this).addClass("selected");
		$(this).find(".selected-img").removeAttr("style");
		$(this).find(".deselected-img").attr("style", "display: none;");
	}
	else {
		$(this).attr("data-selected","no");
		$(this).removeClass("selected");
		$(this).find(".selected-img").attr("style", "display: none;");
		$(this).find(".deselected-img").removeAttr("style");
	}

	if ($('.selected').length > 0){
		$(".bussiness-sector-selected").attr('src', $(".bussiness-sector .bussiness-sector-btn.selected img").attr('src'));
		$('.register-investor-screen2-btn-next').addClass('active')
		$(".register-investor-screen2-btn-next svg").removeClass('next-hide');
	}
	else{
		$(".bussiness-sector-selected").attr('src', "");
		$('.register-investor-screen2-btn-next').removeClass('active')
		$(".register-investor-screen2-btn-next svg").addClass('next-hide');
	}
})
$(".register-investor-screen2-btn-next").on('click',function(){
	if ($('.bussiness-sector .bussiness-sector-btn.selected').length > 0) {
		$('.register-account-investor-screen2').addClass('next-hide');
		$('.register-account-investor-screen3').removeClass('next-hide');
        
	}
})
$(".register-investor-screen3-btn-back").on('click',function(){
	// $('.register-account-investor-screen2').removeClass('next-hide');
	$('.register-account-screen').removeClass('next-hide');
	$('.register-account-investor-screen3').addClass('next-hide');
})
$(".register-account-investor-screen3 .register-form input").on('change',function(){
	var $fName = $(".js-quickregister-firstname").val();
	var $lName = $(".js-quickregister-lastname").val();
	var $Company = $(".js-quickregister-company").val();
	var $EmailId = $("#quickregistrationEmail").val();
	var $country = $(".js-quickregister-countrycode").val();
	var $mobile = $(".js-quick-mobile-number").val();

	if($fName && $lName && $Company && $EmailId && $country && $mobile){
		$('.register-investor-screen3-btn-next').addClass('active')
		$(".register-investor-screen3-btn-next svg").removeClass('next-hide');
	}
	else{
		$('.register-investor-screen3-btn-next').removeClass('active')
		$(".register-investor-screen3-btn-next svg").addClass('next-hide');
	}
})


$(".register-investor-screen3-btn-next").on('click',function(){
	if ($(this).hasClass('active')) {
		$('.register-account-investor-screen3').addClass('next-hide');
		$('.register-account-investor-screen4').removeClass('next-hide');
	}
})

$(".register-investor-screen4-btn-back").on('click',function(){
	$('.register-account-investor-screen3').removeClass('next-hide');
	$('.register-account-investor-screen4').addClass('next-hide');
})

$(".register-form-terms-condition").on('click', function() {
	var checkbox = $(this).find('input[type="checkbox"]');
    checkbox.prop('checked', !checkbox.prop('checked'));
	if(checkbox.prop('checked')){
		$(".reg-terms-checkbox-span span").addClass('reg-terms-checked')
	}else{
		$(".reg-terms-checkbox-span span").removeClass('reg-terms-checked')
	}
	validateRegisterUserInfo();
});

$(".register-account-investor-screen4 .register-form input").on('change',function(){
	validateRegisterUserInfo();
})
function validateRegisterUserInfo(){
	var $username = $(".js-register-quick-user-name").val();
	var $password = $(".quickregistrationPwd").val();
	var $cpassword = $(".quickregistrationCheckPwd").val();
	var checkbox = $(".register-form-terms-condition").find('input[type="checkbox"]');

	if($username && $password && $cpassword && checkbox.prop('checked')){
		$('.register-account-investor-screen4 .register-investor-screen4-btn-next').addClass('active')
		$(".register-account-investor-screen4 .register-investor-screen4-btn-next svg").removeClass('next-hide');
		$($(".register-account-investor-screen4 .register-progress span")[3]).html('&#10003;')
	}
	else{
		$('.register-account-investor-screen4 .register-investor-screen4-btn-next').removeClass('active')
		$(".register-account-investor-screen4 .register-investor-screen4-btn-next svg").addClass('next-hide');
		$($(".register-account-investor-screen4 .register-progress span")[3]).html('4')
	}
}
$(".register-investor-screen5-btn-next").on('click',function(){
	if ($(this).hasClass('active')) {		
		$(".register-account-investor-screen5").addClass('next-hide');

		var body = $("body");
		body.toggleClass('page-login-register');
		$('.accountLogin').show();
	}
})


function validateLoginForm(){
    var username = $("#j_username");
    var password = $("#j_password");
    var recaptcha = document.forms["sagiaLoginForm"]["g-recaptcha-response"].value;
	
    username.removeClass('hasError');
    password.removeClass('hasError');
    var valid = true;

	if (username.val() === "" ){       
        username.addClass('hasError');
		valid = false;
	}
    if(password.val() === "" ){
        password.addClass('hasError');
        valid = false;
    }
    if(recaptcha === ""){
        $("#lblErrorCaptcha").text("Please fill reCAPTCHA");
        valid = false;
    }
    return valid;
}

$(".login-screen .login-entry-cancel").on('click',function(e){
    e.preventDefault();
    $(".login-screen input").val('').change();
    $(".login-screen input").removeClass('hasError');
    grecaptcha.reset(0);
})

$(".toggle-password1").on('click',function(){    	  
    $(this).toggleClass("fa-eye fa-eye-slash");      
      var input = $(".quickregistrationPwd");
      input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
       
});
$(".toggle-password2").on('click',function(){    	  
    $(this).toggleClass("fa-eye fa-eye-slash");      
      var input = $(".quickregistrationCheckPwd");
      input.attr('type') === 'password' ? input.attr('type','text') : input.attr('type','password')
       
});

$("#backtoLogin").on('click',function(){
    window.location="/en/login"
})