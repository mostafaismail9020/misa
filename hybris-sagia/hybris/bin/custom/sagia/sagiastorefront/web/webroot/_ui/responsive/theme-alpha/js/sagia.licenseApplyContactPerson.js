SAGIA.licenseApplyContactPerson = {
    _autoload: [
        "bindAll"
    ],
    
    bindAll: function () {
        this.loadCountry();
        this.loadContactPersonNationality();
        this.bindCountryEvent();
        this.setDateObjects();
        this.bindNextButton();
        this.bindBackButton();
        this.bindOriginContactChangeEvent();
        this.loadIDType();
        SAGIA.licenseApply.bindInputValidation($('#contactPersonForm'));
        SAGIA.licenseApplyShareholderCommons.bindDelegateEvents(false);
        SAGIA.licenseApplyShareholderCommons.loadIdAndGenderDropDown();
        this.loadContactForm();
       
        
    },
    
    loadCountry: function () {
        var self = this;

        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var countries = $("#contactPersonQM1 #qm1Country");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                
                if(countries.data("value")) {
                	countries.val(countries.data("value")).trigger("change").next().addClass("select2Container_selected");
                }
            }
        });
        
        
    },
     loadContactPersonNationality: function () {
            var self = this;

            $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);

                    var countries = $("#contactPersonQM1 #qm1Nationality");
                    countries.find("option").remove();
                    countries.append(new Option("", "", false, false));
                    jsonData.countries.forEach(function (currentValue) {
                        countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    });

                    if(countries.data("value")) {
                    	countries.val(countries.data("value")).trigger("change").next().addClass("select2Container_selected");
                    }
                }
            });


        },
    
    loadIDType: function () {

    	  var shareholdersQM1 = $("#contactPersonQM1");
    	  var personDelegateIdType = shareholdersQM1.find("#idType");
    	  personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
          personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
          personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
          personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));        
    },
    
       bindCountryEvent: function () {
    	var self = this;
    	SAGIA.licenseApply.setMobileCode('#qm1Country',
                "#qm1CountryCodeForTelephone",
                "#qm1CountryCodeForMobileNumber");
    },
    
    

    setDateObjects: function () {
    	
        var dateOfBirthFP = document.querySelector("#qm1DateOfBirth")._flatpickr; // this flatpickr is used for all dates parsing in contact person
        
        if($("#qm1DateOfBirth").data("date"))
          {
        	dateOfBirthFP.setDate(dateOfBirthFP.parseDate($("#qm1DateOfBirth").data("date"), ACC.formatUIDate));
          }
       /* if($("#qm1PassportIssueDate").data("date"))
          {
    	    document.querySelector("#qm1PassportIssueDate")._flatpickr.setDate(dateOfBirthFP.parseDate($("#qm1PassportIssueDate").data("date"), ACC.formatUIDate));
          }
        */
        if($("#qm1PassportExpiryDate").data("date"))
          {
    	    document.querySelector("#qm1PassportExpiryDate")._flatpickr.setDate(dateOfBirthFP.parseDate($("#qm1PassportExpiryDate").data("date"), ACC.formatUIDate));
          }

    },
    
    isValidForm: function () {
        var self = this;
        var hasErrors = false;
        var contactPersonForm = $('#contactPersonForm');
        
        SAGIA.licenseApply.removeGlobalErrorMessage($('#contactPersonGlobalMessage'));
        contactPersonForm.find('.help-block').html("");
        contactPersonForm.find('.form-group.has-error').removeClass('has-error');
        
        var shareholdersQM1 = $("#contactPersonQM1");
  	    var personDelegateIdType = shareholdersQM1.find("#idType");
  	    var contactType = shareholdersQM1.find("#qm1Contacts");
  	    
        // remove mandatory fields
        if(contactType.val() === 'OTHER' && personDelegateIdType.val()!=='4'){
        	$("#delegateDateofBirth").addClass('label.control-label_mandatory');
        	$("#idType").addClass('label.control-label_mandatory');
        	$("#idNumber").addClass('label.control-label_mandatory');   
        	
        	if(!$("#idType").val()||!$("#idNumber").val()||!$("#delegateDateofBirth").val()){
                var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
                if(!$("#idType").val()){
                	$("#idType").parent().addClass("has-error");
                	$("#idType").parent().siblings(".help-block").text(errorMsg);
                }
                if(!$("#idNumber").val()){
                	$("#idNumber").parent().addClass("has-error");
                	$("#idNumber").parent().siblings(".help-block").text(errorMsg);
                }
                if(!$("#delegateDateofBirth").val()){
                	$("#delegateDateofBirth").parent().addClass("has-error");
                	$("#delegateDateofBirth").parent().siblings(".help-block").text(errorMsg);
                }
                hasErrors = true;
            }
        	
  	    }else {
  	    	$("#delegateDateofBirth").removeClass('label.control-label_mandatory');
        	$("#idType").removeClass('label.control-label_mandatory');
        	$("#idNumber").removeClass('label.control-label_mandatory'); 	    	
  	    }
       


        

        if (!contactPersonForm.find('[name=title]').filter(':checked').val()) {
        	contactPersonForm.find('[name=title]').closest('.form-group').addClass('has-error');
            hasErrors = true;
        }

        contactPersonForm.find('select').each(function () {
            if ($(this).siblings('label.control-label_mandatory').length > 0 && !$(this).val()) {
                $(this).closest('.form-group').addClass('has-error');
                $(this).parents('.formInputBox, .formSelectBox').find('.help-block').text(self.getErrorMessageInfo($(this).attr('name')));

                hasErrors = true
            }
        });
        
        contactPersonForm.find('input').not('[name=email]').each(function () {
            if ($(this).siblings('label.control-label_mandatory').length > 0 && !$(this).val()) {
                $(this).closest('.form-group').addClass('has-error');
                $(this).parents('.formInputBox, .formSelectBox').find('.help-block').text(self.getErrorMessageInfo($(this).attr('name')));

                hasErrors = true
            }
        });

        contactPersonForm.find('.validate__email').each(function () {
            if (SAGIA.licenseApply.validateEmailAddresses($(this)) === false) {
                hasErrors = true;
            }
        });
        
        if (contactPersonForm.find('#qm1POBox').val()) {
        	if(contactPersonForm.find('#qm1POBox').val().length != 5){
        		contactPersonForm.find('[id=qm1POBox]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		contactPersonForm.find('[id=qm1POBox]').parents('.form-group').addClass('has-error');
                hasErrors = true;
        	}
        }
        
        if (contactPersonForm.find('#qm1PostalCode').val()) {
        	if(contactPersonForm.find('#qm1PostalCode').val().length != 5){
        		contactPersonForm.find('[id=qm1PostalCode]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		contactPersonForm.find('[id=qm1PostalCode]').parents('.form-group').addClass('has-error');
                hasErrors = true;
        	}
        }
        
        if (hasErrors) {
            SAGIA.licenseApply.globalErrorMessage($('#contactPersonGlobalMessage'));
        }

        return !hasErrors;
    },
    
    bindNextButton: function () {
    	var self = this;
    	 $(document).on("click", "#contactPersonNextButton", function () {
             
    		 if (!self.isValidForm()) {
                 return false;
             }
    		 $("#contactPersonForm").submit();

    	 });

        $("#contactPersonForm").submit(function(e) {
            $(this).find(':input').prop('disabled', false);
         });
    },
    
    bindBackButton: function () {

    },

    getErrorMessageInfo: function (fieldName) {
        return getI18nText("validate.licenseApply.contactPerson."+fieldName+".error");
    },
    
   
    
    loadContactByOriginEvent: function (contactID) {
    	var self = this;
    	if(contactID === "") {
            $(".valid-cr-fields").hide();
            $('.cr-validation').addClass('has-error');
            $('.cr-validation .help-block').html("<span>Please provide a valid Contact</span>");
            self.resetInvestorEvent(crNumber);
        } else {
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/contact-details/" + contactID, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    $("#inputCRNumber-error").removeClass("has-error").text("");
                    var jsonData = JSON.parse(data);
                    if(true) {
                        $('.cr-validation').removeClass('has-error');
                        $('.cr-validation .help-block').empty();
                        self.loadContactResponseEvent(jsonData, contactID);
                    } else {
                    	self.resetContactEvent();
                        $(".valid-cr-fields").hide();
                        $('.cr-validation').addClass('has-error');
                        $('.cr-validation .help-block').html("<span>" + jsonData.error + "</span>");
                    }
                },
                error: function() {
                	self.resetContactEvent();
                    $("#inputCRNumber-error").addClass("has-error").text(getI18nText("general.error"));
                    $(".valid-cr-fields").hide();
                }
            });
        }
    },
    
    loadContactForm: function() {
    	var self = this;
    	var shareholdersQM1 = $("#contactPersonQM1");
  	    var personDelegateIdType = shareholdersQM1.find("#idType");
  	    var contactType = shareholdersQM1.find("#qm1Contacts");
  	    
  	    if(contactType.val() === 'OTHER'){
  	    	
  	    	if ( personDelegateIdType.val()==='') {
  	    		 self.setFieldIfValueNotBlank($("#idType"),'4');
  	    		$("#contactDetails").show();
            	$("#contactSection").show();  
            	
            	//fields must not be read only when passport is selected
            	self.revertmMkeFieldsReadOnlyWhenLoadedFromDelegatesAndShareHolders();
  	    	}else {
  	    		$("#delegateDetails").show();
            	$("#contactDetails").show();
            	$("#contactSection").show();
            	if(personDelegateIdType.val()!=='4')
                  {
                      $("#verifyDetailsShow").trigger('click');
                  }

            //	self.resetContactDetialsSectionEvent();
  	    	}
  	    }else {
  	    	
  	    	self.makeFieldsReadOnlyWhenLoadedFromDelegatesAndShareHolders();
  	    }
  	   
  	    
    	
    },
   
    makeFieldsReadOnlyWhenLoadedFromDelegatesAndShareHolders: function() {
    	var shareholdersQM1 = $("#contactPersonQM1");
    	var contactType = shareholdersQM1.find("#qm1Contacts");
    	$("#delegateDetails").hide();  
    	if(contactType.val() === 'OTHER')
    	{
    		  $("#delegateDetails").show(); 
        }
  	    $("#qm1PassportNumber").prop('disabled', true);
  		$("#qm1Email").prop('disabled', true);
  		$("#qm1CountryCodeForMobileNumber").prop('disabled', true);
  		$("#qm1MobileNumber").prop('disabled', true);
  		$("#qm1CountryCodeForTelephone").prop('disabled', true);
  		$("#qm1Telephone").prop('disabled', true);
  		$("#qm1City").prop('disabled', true);
  		$("#qm1POBox").prop('disabled', true);
  		$("#qm1PostalCode").prop('disabled', true);
  		$("#qm1Country").prop('disabled', true);
  		$("#qm1FirstName").prop('disabled', true);
  		$("#qm1LastName").prop('disabled', true);
  		$("#qm1FullName").prop('disabled', false);
  		$("#qm1DateOfBirth").prop('disabled', true);
  		$("#qm1PassportExpiryDate").prop('disabled', true);
  		$("#qm1PassportIssueDate").prop('disabled', true);
    },
    
    revertmMkeFieldsReadOnlyWhenLoadedFromDelegatesAndShareHolders: function() {
        var shareholdersQM1 = $("#contactPersonQM1");
    	var contactType = shareholdersQM1.find("#qm1Contacts");
    	$("#delegateDetails").hide();  
    	if(contactType.val() === 'OTHER')
    	{
    		  $("#delegateDetails").show(); 
        }   	
  	    $("#qm1PassportNumber").removeAttr('disabled');
  		$("#qm1Email").removeAttr('disabled');
  		$("#qm1CountryCodeForMobileNumber").removeAttr('disabled');
  		$("#qm1MobileNumber").removeAttr('disabled');
  		$("#qm1CountryCodeForTelephone").removeAttr('disabled');
  		$("#qm1Telephone").removeAttr('disabled');
  		$("#qm1City").removeAttr('disabled');
  		$("#qm1POBox").removeAttr('disabled');
  		$("#qm1PostalCode").removeAttr('disabled');
  		$("#qm1Country").removeAttr('disabled');
  		$("#qm1FirstName").removeAttr('disabled');
  		$("#qm1LastName").removeAttr('disabled');
  		$("#qm1DateOfBirth").removeAttr('disabled');
  		$("#qm1PassportExpiryDate").removeAttr('disabled');
  		$("#qm1PassportIssueDate").removeAttr('disabled');

    },
    
    
    
    
    loadContactResponseEvent: function(data, contactID) {
    	var self = this;
    	 
    	 var contactPersonForm = $('#contactPersonForm');          
         SAGIA.licenseApply.removeGlobalErrorMessage($('#contactPersonGlobalMessage'));
         contactPersonForm.find('.help-block').html("");
         contactPersonForm.find('.form-group.has-error').removeClass('has-error');
         
    	if(contactID.startsWith("SHR-") ) {
    		
    		self.setAndDisableFieldIfValueNotBlank($("#qm1PassportNumber"), data.passportNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Email"), data.email);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1CountryCodeForMobileNumber"), data.countryCodeMobile);	
    		self.setAndDisableFieldIfValueNotBlank($("#qm1MobileNumber"), data.mobileNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1CountryCodeForTelephone"), data.countryCodeTelephone);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Telephone"), data.telephoneNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1City"), data.city);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1POBox"), data.poBox);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1PostalCode"), data.postalCode);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Country"), data.country);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1FirstName"), data.firstNameArabic);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1LastName"), data.lastNameArabic);
    		self.setFieldIfValueNotBlank($("#qm1FullName"), data.fullName);
    		self.setFieldIfValueNotBlank($("#qm1Nationality"), data.currentNationality);
    		self.setFieldIfValueNotBlank($("#qm1DateOfBirth"), data.dateOfBirth);
    		self.setFieldIfValueNotBlank($("#qm1PassportExpiryDate"), data.passportExpiryDate);
    		self.setFieldIfValueNotBlank($("#qm1PassportIssueDate"), data.passportIssueDate);
    	} else if (contactID.startsWith("DEL-") ) {
    		 	
    		self.setAndDisableFieldIfValueNotBlank($("#qm1PassportNumber"), data.delegateIdentityNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Email"), data.delegateEmail);
    		self.setFieldIfValueNotBlank($("#qm1FullName"), data.delegateFullName);
    		self.setFieldIfValueNotBlank($("#qm1Nationality"), data.delegateNationality);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1CountryCodeForMobileNumber"), data.delegateCountryCodeMobile);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1MobileNumber"), data.delegateMobileNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1CountryCodeForTelephone"), data.delegateCountryCodeTel);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Telephone"), data.delegateTelephoneNumber);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1POBox"), data.delegatePoBox);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1PostalCode"), data.delegatePostalCode);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1Country"), data.delegateCountry);    		
    		self.setAndDisableFieldIfValueNotBlank($("#qm1FirstName"), data.delegateFirstNameArabic);
    		self.setAndDisableFieldIfValueNotBlank($("#qm1LastName"), data.delegateLastNameArabic);
    		self.setFieldIfValueNotBlank($("#qm1DateOfBirth"), data.delegateDateOfBirth);
    		self.setFieldIfValueNotBlank($("#qm1PassportExpiryDate"), data.idExpiryDate);
    		self.setFieldIfValueNotBlank($("#qm1PassportIssueDate"), data.idIssueDate);
    		
    		
    		
    	}
    		
    	
    },
    
    setAndDisableFieldIfValueNotBlank : function(element, value){
        if(value)
        {
            element.val(value);
            if(element.is( "select" ))
            {
                element.val(value).trigger("blur").trigger('change');
            }

            if (element.is('[type=radio]')) {
                element.filter('[value='+value+']').trigger('click');
            }

            if(element.hasClass("flatpickr-input"))
            {
                var dateParser = document.querySelector("#"+element.attr('id'))._flatpickr; // this flatpickr is used for parsing the date
                dateParser.setDate(dateParser.parseDate(value, ACC.formatUIDate));
            }
            element.prop('disabled', true);
        }
    },
        setFieldIfValueNotBlank : function(element, value){
            if(value)
            {
                element.val(value);
                if(element.is( "select" ))
                {
                    element.val(value).trigger("blur").trigger('change');
                }

                if (element.is('[type=radio]')) {
                    element.filter('[value='+value+']').trigger('click');
                }

                if(element.hasClass("flatpickr-input"))
                {
                    var dateParser = document.querySelector("#"+element.attr('id'))._flatpickr; // this flatpickr is used for parsing the date
                    dateParser.setDate(dateParser.parseDate(value, ACC.formatUIDate));
                }
                //element.prop('disabled', true);
            }
        },

     resetContactDetails: function() {

    		$("#qm1FirstName").val("").removeAttr('disabled');
    		$("#qm1LastName").val("").removeAttr('disabled');
    		$("#qm1FullName").val("").removeAttr('disabled');
    		$("#qm1Nationality").val("").trigger("blur").trigger('change');
    		$("#qm1Role").val("").trigger("blur").trigger('change');
    		$("#qm1Education").val("").trigger("blur").trigger('change');
        	$("#qm1DateOfBirth").val("").removeAttr('disabled');
    		$("#qm1PassportNumber").val("").removeAttr('disabled');
            $("#qm1PassportExpiryDate").val("").removeAttr('disabled');
            $("#qm1PassportIssueDate").val("").removeAttr('disabled');
        },

    resetContactEvent: function() {

    	$("#qm1DateOfBirth").removeAttr('disabled');
    	$("#qm1DateOfBirth").val("") ;
		$("#qm1PassportNumber").val("").removeAttr('disabled');
		$("#qm1Email").val("").removeAttr('disabled');
		$("#qm1CountryCodeForMobileNumber").val("").removeAttr('disabled');
		$("#qm1MobileNumber").val("").removeAttr('disabled');
		$("#qm1CountryCodeForTelephone").val("").removeAttr('disabled');
		$("#qm1Telephone").val("").removeAttr('disabled');
		$("#qm1City").val("").removeAttr('disabled');
		$("#qm1POBox").val("").removeAttr('disabled');
		$("#qm1PostalCode").val("").removeAttr('disabled');
		$("#qm1PassportExpiryDate").val("").removeAttr('disabled');
		$("#qm1PassportIssueDate").val("").removeAttr('disabled');
		$("#qm1PassportNumber").val("").removeAttr('disabled');
		$("#qm1Country").val("").removeAttr('disabled');
		$("#qm1FirstName").val("").removeAttr('disabled');
		$("#qm1LastName").val("").removeAttr('disabled');
    },
    
    resetContactDetialsSectionEvent: function() {
     	
    	$("#qm1DateOfBirth").removeAttr('disabled');
    	$("#qm1DateOfBirth").val("") ;
	    $("#qm1PassportNumber").val("").removeAttr('disabled');
		$("#qm1Email").val("").removeAttr('disabled');
		$("#qm1CountryCodeForMobileNumber").val("").removeAttr('disabled');
		$("#qm1MobileNumber").val("").removeAttr('disabled');
		$("#qm1CountryCodeForTelephone").val("").removeAttr('disabled');
		$("#qm1Telephone").val("").removeAttr('disabled');
		$("#qm1City").val("").removeAttr('disabled');
		$("#qm1POBox").val("").removeAttr('disabled');
		$("#qm1PostalCode").val("").removeAttr('disabled');
		$("#qm1PassportExpiryDate").val("").removeAttr('disabled');
		$("#qm1PassportIssueDate").val("").removeAttr('disabled');
		$("#qm1Country").val("").removeAttr('disabled');
        $("#qm1FirstName").val("").removeAttr('disabled');
        $("#qm1LastName").val("").removeAttr('disabled');
		$("#qm1PassportNumber").val("");
		$("#qm1Address").val("");
		
		
		
    },
    
    setFieldIfValueNotBlank : function(element, value){
        if(value)
        {
            element.val(value);
            if(element.is( "select" ))
            {
                element.val(value).trigger("blur").trigger('change');
            }

            if (element.is('[type=radio]')) {
                element.filter('[value='+value+']').trigger('click');
            }

            if(element.hasClass("flatpickr-input"))
            {
                var dateParser = document.querySelector("#"+element.attr('id'))._flatpickr; // this flatpickr is used for parsing the date
                dateParser.setDate(dateParser.parseDate(value, ACC.formatUIDate));
            }
           
        }
    },
    
    bindOriginContactChangeEvent: function () {
    	var self = this;
    	//self.resetContactEvent();
        $(document).on('change', "#qm1Contacts", function() {
        	val = $(this).val() ;
                if(val == "OTHER")
                {
                	
                	
                	$("#delegateDetails").show();
                	$("#contactDetails").hide();
                	$("#contactSection").hide();
                	//$("#idType").val('4');
                	
                    self.setFieldIfValueNotBlank($("#idType"),'4');

                
                	
                	$("#delegateDateofBirth").addClass('label.control-label_mandatory');
                	$("#idNumber").addClass('label.control-label_mandatory');
                	self.resetContactDetialsSectionEvent();
                }
                else
                {
                	//self.bindNormalCal($("#qm1PassportIssueDate"));
                	self.bindNormalCal($("#qm1PassportExpiryDate"));
                	self.bindNormalCal($("#qm1DateOfBirth"));
                	self.loadContactByOriginEvent(val);	
                	$("#delegateDetails").hide();
                	$("#contactDetails").show();
                	$("#contactSection").show();
                	
                	$("#delegateDateofBirth").removeClass('label.control-label_mandatory');
           
                	$("#idNumber").removeClass('label.control-label_mandatory');
                	
                	
                	       
                }
               
            
        });
    },
    
   

    bindNormalCal : function(element){
        element.removeClass("ummAlQura");
        element.calendarsPicker('destroy');
        ACC.calendarcommons.bindFlatpickr(element);
      
    },
    
    
    
    
    
    
    
};