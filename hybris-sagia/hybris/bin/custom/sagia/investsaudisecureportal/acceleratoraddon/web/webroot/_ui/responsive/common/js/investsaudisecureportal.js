
ACC.secureportal = {

	_autoload : [ 	"bindRegister",
					"registerNavigationItem",
					"bindHeightEqualizer"],

	bindRegister : function() {

		var registerForm = $(".registerForm");
		
		for (var i = 0; i < registerForm.length; i++) {
			curr = registerForm[i];
			curr.id;
			myform = $("#"+curr.id) ;
			
			var entity = myform.find(".js-secureportal-userEntity").val();

			if(myform.find(".js-secureportal-userEntity option[value='9999']").length != 0)
			{
				myform.find(".js-secureportal-userEntity option[value='9999']").remove();
				myform.find('.js-secureportal-userEntity').append(new Option('OTHERS', '9999', false, false));
			}
			if(entity === "9999"){
				myform.find(".js-secureportal-userEntity").val('9999').trigger('change');
				myform.find(".js-secureportal-otherUserEntity").show();
	    	}

			if (!myform.find(".js-secureportal-confirm-register-email").val() && myform.find(".js-secureportal-orignal-register-email").val()) {
				myform.find(".js-secureportal-register-button").attr("disabled", "disabled");
			}

			var $termsCheckbox = myform.find(".js-secureportal-terms");
		    if (!$termsCheckbox.is(":checked")) {
		    	myform.find(".js-secureportal-register-button").attr("disabled", "disabled");
		    }else{
		        $termsCheckbox.parents('.form-item').find('.help-block').text('');
		        return true;
		    }
			
		}
		
	    var bsuRegValidationParams = {
	            highlight: function(element) {
	                $(element).closest('.form-group').addClass('has-error');
	            },
	            unhighlight: function(element) {
	                $(element).closest('.form-group').removeClass('has-error');
	            },
	            focusInvalid: true,
	            ignore: "",
	            errorElement: 'span',
	            errorPlacement: function(error, element) {
	                if (element.closest('.form-group').find('.help-block').length>0) {
	                    error.appendTo(element.closest('.form-group').find('.help-block'));
	                } else if (element.hasClass('js-select2-oneColumn')){
	                    error.appendTo(element.closest('.formSelectBox').find('.help-block'));
	                } else {
	                    error.appendTo(element.closest('.formInputBox').find('.help-block'));
	                }
	            },
	            rules: {
	            	email: {
	                    required: true,
	                    email: true
	                },
	                telephone: {
	                    required: true
	                }
	            },
	            messages: {
	            	email: {
	                    required: "This field cannot be empty",
	                    email: "Please enter a valid email address",
	                    remote: "Email address already exists or has an incorrect format, please enter another"
	                },
	                telephone: {
	                    required: "This field cannot be empty",
	                    digits: "The mobile number is invalid",
	                    remote: "Mobile number already exists or has an incorrect format, please enter another"
	                }
	            }
	        };

	    	bsuRegValidationParams.rules.email.remote = {
	            type: 'GET',
	            url: ACC.config.encodedContextPath + "/register/regvalidation",
	            data: {
	                email: function() { return $(".js-secureportal-orignal-register-email").val() }
	            },
	            cache: false,
	            dataFilter: function(data) {
	                var $element = $(".js-secureportal-orignal-register-email");
	                if (data === 'true') {
	                    var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
	                    messageElements.text('Email is OK');
	                    messageElements.show();
	                    return true;
	                } else {
	                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
	                    return false;
	                }
	            }
	        };
	    	bsuRegValidationParams.rules.telephone.remote = {
	    	        type: 'GET',
	    	        url: ACC.config.encodedContextPath + "/register/regvalidation",
	    	        data: {
	    	        	mobileNumber: function () {
	    	                return $(".js-secureportal-telephone").val();
	    	            },
	    	            mobileCountryCode: function () {
	    	            	var telephoneExtension = $(".js-secureportal-telephoneExtension").val();
	    	            	if(telephoneExtension){
	    	            		return telephoneExtension;
	    	            	}else{
	    	            		return "966";
	    	            	}
	    	            }
	    	        },
	    	        cache: false,
	    	        dataFilter: function(data) {
	    	            var $element = $(".js-secureportal-telephone");
	    	            if (data === 'true') {
	    	                var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
	    	                messageElements.text("Mobile number is OK");
	    	                messageElements.show();
	    	                return true;
	    	            }
	    	            else {
	    	                $element.closest('.formInputBox').find(".js-help-block-success").empty();
	    	                return false;
	    	            }
	    	        }
	    	    };

	    $(".js-secureportal-orignal-register-email").on("change", function(e) {
	    	$(this).parents(".registerForm").validate(bsuRegValidationParams);
	    	/*var emailAdd = $(".js-secureportal-orignal-register-email").val();
			$.ajax({
		            type: 'GET',
		            url: ACC.config.encodedContextPath + "/register/validation",
		            data: {
		                email: function() { return emailAdd },
		            },
		            cache: false,
		            dataFilter: function(data) {
		                var $element = $(".js-secureportal-orignal-register-email");
		                if (data === 'true') {
		                    var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
		                    messageElements.text('Email is OK');
		                    messageElements.show();
		                    return true;
		                } else {
		                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
		                    return false;
		                }
		            }
			});*/

	    });
	    $(".js-secureportal-telephone").keypress(function (e) {
	        //if the letter is not digit then display error and don't type anything
	        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	           //display error message
	           $("#errmsg").html("Digits Only").show().fadeOut("slow");
	                  return false;
	       }
	   });
	    $(".js-secureportal-telephoneExtension").keypress(function (e) {
	        //if the letter is not digit then display error and don't type anything
	        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	           //display error message
	           $("#errmsg").html("Digits Only").show().fadeOut("slow");
	                  return false;
	       }
	   });
	    $(".js-secureportal-telephone").on("change", function(e) {
	    	$(this).parents(".registerForm").validate(bsuRegValidationParams);
	    });
	    $(".js-secureportal-userEntity").on("change", function(e) {
	    	var form = $(this).parents('form');
	    	
	    	var userEntity = form.find(".js-secureportal-userEntity").val();
	    	if(userEntity === "9999"){
	    		form.find(".js-secureportal-otherUserEntity").show();
	    	}else{
	    		form.find(".js-secureportal-otherUserEntity").hide();
	    	}

	    });

	    var $termsCheckbox = $(".termsAndConditionsRegister");
		if (!$termsCheckbox.is(":checked")) {
			$termsCheckbox.parents('form').find(".js-secureportal-register-button").attr("disabled", "disabled");
		}
		$(document).on("change", ".js-secureportal-confirm-register-email, .js-secureportal-orignal-register-email, .js-secureportal-terms", function() {

			var form = $(this).parents('form');

			var originalEmail = form.find(".js-secureportal-orignal-register-email").val();
			var confirmationEmail = form.find(".js-secureportal-confirm-register-email").val();
			var $termsCheckbox = form.find(".termsAndConditionsRegister");

			if (!$termsCheckbox.is(":checked")) {
				$termsCheckbox.parents('.form-item').find('.help-block').text("Please accept terms and conditions");
			} else {
				$termsCheckbox.parents('.form-item').find('.help-block').text('');
			}

			if (originalEmail === confirmationEmail) {
				form.find(".js-secureportal-email-not-match-message").hide();
			} else {
				form.find(".js-secureportal-email-not-match-message").show();
			}

			if (originalEmail === confirmationEmail && $termsCheckbox.is(":checked")) {
				form.find(".js-secureportal-register-button").removeAttr("disabled");
			} else {
				form.find(".js-secureportal-register-button").attr("disabled", "disabled");
			}

		});

	},

	registerNavigationItem: function(){
		var registerLink = $(".js-register-navigation-item").clone();
		$('.js-userAccount-Links').append(registerLink);
		registerLink.wrap( "<li class='auto'></li>" );
	},

	bindHeightEqualizer: function () {
		$('.page-SecureCustomerPortalRegisterPage .register__container').each(function() {
			$(this).find('.register__section').css('min-height', $(this).outerHeight()+ 'px');
		})
	}
};
