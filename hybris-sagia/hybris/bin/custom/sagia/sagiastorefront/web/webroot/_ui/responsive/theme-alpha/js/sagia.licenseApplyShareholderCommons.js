SAGIA.licenseApplyShareholderCommons = {
    _autoload: [
        "bindAll"
    ],

    bindAll: function () {

    },

    loadShareholderQeemah1Data: function(callback) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var shareholdersQM1 = $("#shareholdersQM1");
                var academicTitle = shareholdersQM1.find("#academicTitle"); //??? academicTitle - education
                var previousAcademicTitle = academicTitle.attr('data-value');
                academicTitle.find("option").remove();
                academicTitle.append(new Option("", "", false, false));
                jsonData.education.forEach(function (currentValue) {
                    academicTitle.append(new Option(currentValue.educationText, currentValue.education, false, false));
                });
                if(previousAcademicTitle) {
                    academicTitle.val(previousAcademicTitle).trigger("blur").trigger('change');
                } else {
                    academicTitle.val(null)
                }

                var premiumResident = shareholdersQM1.find("#premiumResident"); //??? academicTitle - education
                var previousPremiumResident = '';

                if (premiumResident.attr('data-value') && premiumResident.attr('data-value') !== undefined ) {
                    previousPremiumResident = premiumResident.attr('data-value') === 'true' ? 'yes' : 'no';
                }

                premiumResident.find("option").remove();
                premiumResident.append(new Option("", "", false, false));
                jsonData.premium.forEach(function (currentValue) {
                    premiumResident.append(new Option(currentValue.premiumText, currentValue.premiumStatus, false, false));
                });
                if(previousPremiumResident) {
                    premiumResident.val(previousPremiumResident).trigger("blur").trigger('change');
                } else {
                    premiumResident.val(null)
                }
                
                var professionalLicense = shareholdersQM1.find("#professionalLicense"); //??? professionalLicense
                var previousProfessionalLicense = '';                
                if (professionalLicense.attr('data-value') && professionalLicense.attr('data-value') !== undefined ) {
                	previousProfessionalLicense = professionalLicense.attr('data-value') === 'true' ? 'yes' : 'no';
                }                
                professionalLicense.find("option").remove();
                professionalLicense.append(new Option("", "", false, false));
                
                var companyProfessionalLicense = shareholdersQM1.find("#companyProfessionalLicense"); //??? professionalLicense
                var previousCompanyProfessionalLicense = '';                
                if (companyProfessionalLicense.attr('data-value') && companyProfessionalLicense.attr('data-value') !== undefined ) {
                	previousCompanyProfessionalLicense = companyProfessionalLicense.attr('data-value') === 'true' ? 'yes' : 'no';
                }                
                companyProfessionalLicense.find("option").remove();
                companyProfessionalLicense.append(new Option("", "", false, false));
                
                var existingProfessionalLicense = shareholdersQM1.find("#existingProfessionalLicense"); //??? professionalLicense
                var previousExistingProfessionalLicense = '';                
                if (existingProfessionalLicense.attr('data-value') && existingProfessionalLicense.attr('data-value') !== undefined ) {
                	previousExistingProfessionalLicense = existingProfessionalLicense.attr('data-value') === 'true' ? 'yes' : 'no';
                }                
                existingProfessionalLicense.find("option").remove();
                existingProfessionalLicense.append(new Option("", "", false, false));
                        
                jsonData.professionalLicense.forEach(function (currentValue) {
                	professionalLicense.append(new Option(currentValue.professionalLicenseText, currentValue.professionalLicenseStatus, false, false));
                	companyProfessionalLicense.append(new Option(currentValue.professionalLicenseText, currentValue.professionalLicenseStatus, false, false));
                	existingProfessionalLicense.append(new Option(currentValue.professionalLicenseText, currentValue.professionalLicenseStatus, false, false));
                });
                
                if(previousProfessionalLicense) {
                	professionalLicense.val(previousProfessionalLicense).trigger("blur").trigger('change');
                } else {
                	professionalLicense.val(null)
                }
                
                if(previousCompanyProfessionalLicense) {
                	companyProfessionalLicense.val(previousCompanyProfessionalLicense).trigger("blur").trigger('change');
                } else {
                	companyProfessionalLicense.val(null)
                }
                
                if(previousExistingProfessionalLicense) {
                	existingProfessionalLicense.val(previousExistingProfessionalLicense).trigger("blur").trigger('change');
                } else {
                	existingProfessionalLicense.val(null)
                }

                var currentNationality = shareholdersQM1.find("#currentNationality");
                var previousCurrentNationality = currentNationality.attr('data-value');
                currentNationality.find("option").remove();
                currentNationality.append(new Option("", "", false, false));

                var previousNationality = shareholdersQM1.find("#previousNationality");
                var previousPreviousNationality = previousNationality.attr('data-value');
                previousNationality.find("option").remove();
                previousNationality.append(new Option("", "", false, false));

                var country = shareholdersQM1.find("#country");
                var previousCountry = country.attr('data-value');
                country.find("option").remove();
                country.append(new Option("", "", false, false));

                var companyCountry = shareholdersQM1.find("#companyCountry");
                var previousCompanyCountry = companyCountry.attr('data-value');
                companyCountry.find("option").remove();
                companyCountry.append(new Option("", "", false, false));

                var companyCountryOfRegistration = shareholdersQM1.find("#companyCountryOfRegistration");
                var previousCompanyCountryOfRegistration = companyCountryOfRegistration.attr('data-value');
                companyCountryOfRegistration.find("option").remove();
                companyCountryOfRegistration.append(new Option("", "", false, false));

                var parentCompanyCountry = shareholdersQM1.find("#parentCompanyCountry");
                var previousParentCompanyCountry = parentCompanyCountry.attr('data-value');
                parentCompanyCountry.find("option").remove();
                parentCompanyCountry.append(new Option("", "", false, false));

                var existingShareholderParentCompanyCountry = shareholdersQM1.find("#existingShareholderParentCompanyCountry");
                var previousExistingShareholderParentCompanyCountry = existingShareholderParentCompanyCountry.val();
                existingShareholderParentCompanyCountry.find("option").remove();
                existingShareholderParentCompanyCountry.append(new Option("", "", false, false));
                
                var organizationDelegateCountry = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateCountry");
                var previousOrganizationDelegateCountry = organizationDelegateCountry.attr('data-value');
                organizationDelegateCountry.find("option").remove();
                organizationDelegateCountry.append(new Option("", "", false, false));

                var organizationDelegateNationality = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateNationality");
                var previousOrganizationDelegateNationality = organizationDelegateNationality.attr('data-value');
                organizationDelegateNationality.find("option").remove();
                organizationDelegateNationality.append(new Option("", "", false, false));

                var personDelegateCountry = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateCountry");
                var previousPersonDelegateCountry = personDelegateCountry.attr('data-value');
                personDelegateCountry.find("option").remove();
                personDelegateCountry.append(new Option("", "", false, false));

                var personDelegateNationality = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateNationality");
                var previousPersonDelegateNationality = personDelegateNationality.attr('data-value');
                personDelegateNationality.find("option").remove();
                personDelegateNationality.append(new Option("", "", false, false));
                var isicSectionsElement=$('#organizationShareholderForm #companySection')
                var licenseType = isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') ? isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') : 1;
                var isRHQ = false;
                if(licenseType === "11"){
                  isRHQ = true;
                }

                jsonData.countries.forEach(function (currentValue) {
                  if(isRHQ === true && currentValue.country === "SA"){

                  }else{
                       companyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                  }
                    currentNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    previousNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    country.append(new Option(currentValue.countryText, currentValue.country, false, false));

                    companyCountryOfRegistration.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    parentCompanyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    existingShareholderParentCompanyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    organizationDelegateNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    organizationDelegateCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    personDelegateNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    personDelegateCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                if(previousCurrentNationality) {
                    currentNationality.val(previousCurrentNationality).trigger("blur").trigger('change');
                } else {
                    currentNationality.val(null)
                }
                if(previousPreviousNationality) {
                    previousNationality.val(previousPreviousNationality).trigger("blur").trigger('change');
                } else {
                    previousNationality.val(null)
                }
                if(previousCountry) {
                    country.val(previousCountry).trigger("blur").trigger('change');
                } else {
                    country.val(null)
                }
                if(previousCompanyCountry) {
                    companyCountry.val(previousCompanyCountry).trigger("blur").trigger('change');
                } else {
                    companyCountry.val(null);
                }
                if(previousCompanyCountryOfRegistration) {
                    companyCountryOfRegistration.val(previousCompanyCountryOfRegistration).trigger("blur").trigger('change');
                } else {
                    companyCountryOfRegistration.val(null)
                }
                if(previousParentCompanyCountry) {
                    parentCompanyCountry.val(previousParentCompanyCountry).trigger("blur").trigger('change');
                } else {
                    parentCompanyCountry.val(null);
                }
                if(previousExistingShareholderParentCompanyCountry) {
                    existingShareholderParentCompanyCountry.val(previousExistingShareholderParentCompanyCountry).trigger("blur").trigger('change');
                } else {
                    existingShareholderParentCompanyCountry.val(null);
                }

                if(previousPersonDelegateCountry) {
                    personDelegateCountry.val(previousPersonDelegateCountry);
                    if (personDelegateCountry.parents('form').find('[name=delegateInfo\\.delegateIdentityType]').val() !== "1"){
                        personDelegateCountry.trigger("blur").trigger('change');
                    }
                } else {
                    personDelegateCountry.val(null);
                }
                if(previousPersonDelegateNationality) {
                    personDelegateNationality.val(previousPersonDelegateNationality).trigger("blur").trigger('change');
                } else {
                    personDelegateNationality.val(null);
                }

                if(previousOrganizationDelegateCountry) {
                    organizationDelegateCountry.val(previousOrganizationDelegateCountry);
                    if (organizationDelegateCountry.parents('form').find('[name=delegateInfo\\.delegateIdentityType]').val() !== "1"){
                        organizationDelegateCountry.trigger("blur").trigger('change');
                    }
                } else {
                    organizationDelegateCountry.val(null);
                }
                if(previousOrganizationDelegateNationality) {
                    organizationDelegateNationality.val(previousOrganizationDelegateNationality).trigger("blur").trigger('change');
                } else {
                    organizationDelegateNationality.val(null);
                }

                var organizationLegalStatus = shareholdersQM1.find("#organizationLegalStatus");
                var previousOrganizationLegalStatus = organizationLegalStatus.attr('data-value');
                organizationLegalStatus.find("option").remove();
                organizationLegalStatus.append(new Option("", "", false, false));

                jsonData.legalStatus.forEach(function (currentValue) {
                    organizationLegalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                });
                if(previousOrganizationLegalStatus) {
                    organizationLegalStatus.val(previousOrganizationLegalStatus).trigger("blur").trigger('change');
                } else {
                    organizationLegalStatus.val(null);
                }

                var multinationalCompany = shareholdersQM1.find("#multinationalCompany");
                var previousMultinationalCompany = multinationalCompany.attr('data-value');

                multinationalCompany.find("option").remove();
                multinationalCompany.append(new Option("", "", false, false));
                jsonData.multinationalCompany.forEach(function (currentValue) {
                    multinationalCompany.append(new Option(currentValue.multinationalCompanyText, currentValue.multinationalCompany, false, false));
                });
                if(previousMultinationalCompany) {
                    multinationalCompany.val(previousMultinationalCompany).trigger("blur").trigger('change');
                } else {
                    multinationalCompany.val(null);
                }

                try {
                    callback();
                } catch (exception) {

                }
            }
        });
    },

    getShareholderByEntityNumber: function () {
        var errorResponseModal = $('#errorResponseModal');
        $('#validateAddShareholder').attr('disabled', 'disabled');

        $(document).on("change", "#existingShareholderEntityNumber", function() {
            var shareholderEntityNumber = $(this).val();

            if(!$("#existingShareholderForm [name=code]").val()) {
                var listEntityNumbers = [];

                $('#shareholderQM1TableSection tr[data-entitynumber]').each(function () {
                    var entityNumberValue = $(this).attr('data-entitynumber');

                    if (entityNumberValue) {
                        listEntityNumbers.push({
                            entityNumber: $(this).attr('data-entitynumber')
                        });
                    }
                });

                if (listEntityNumbers.length > 0) {
                    var shareholderExists = listEntityNumbers.filter(function (shareholder) {
                        return shareholder.entityNumber === shareholderEntityNumber;
                    });

                    if (shareholderExists.length) {
                        $(this).val('');
                        errorResponseModal.find('.modal-description').text(getI18nText("license.existingShareholder.alreadyAdded"));
                        errorResponseModal.modal('show');
                        return;
                    }
                }
            }

            if(shareholderEntityNumber) {
                $.get({
                    url: ACC.config.encodedContextPath + "/my-sagia/license/" + shareholderEntityNumber
                }).done(function (response) {
                    var shareholderExists = JSON.parse(response);
                    if (shareholderExists) {
                        $('#validateAddShareholder').removeAttr('disabled');
                        $("#existingShareholderName").val(shareholderExists.bpName).attr("title", shareholderExists.bpName);
                        if(shareholderExists.bpName) {
                            $("#existingShareholderName").removeClass("placeholder-shown"); //fix for ie11
                        }
                        if (shareholderExists.companycountry) {
                            $("#existingShareholderParentCompanyCountry").val(shareholderExists.companycountry).trigger("blur").trigger("change").attr("title", shareholderExists.companycountry);
                        } else {
                            $("#existingShareholderParentCompanyCountry").val("SA").trigger("blur").trigger("change").attr("title", "");
                        }
                    } else {
                        $('#validateAddShareholder').attr('disabled', 'disabled');
                        errorResponseModal.find('.modal-description').text(getI18nText("license.existingShareholder.notFound"));
                        errorResponseModal.modal('show');
                    }
                }).fail(function (error) {
                    $('#validateAddShareholder').attr('disabled', 'disabled');
                    errorResponseModal.find('.modal-description').text(getI18nText("general.error"));
                    errorResponseModal.modal('show');
                });
            }
        });
    },
    
    bindMofaNumberValidation: function () {
    	var isMofaVerified = $('#isMofaVerified').val();
    	//$("#isMofaVerified").val(false);
    	/*$(document).on("keyup", "#mofaNumber", function() {
    		var mofaSpecialNumber = $(this).val();
    		if (mofaSpecialNumber.length != 12) {
           	 	$("#mofaNumber").closest('.formInputBox').find(".help-block").addClass("has-error").text(getI18nText("validation.mofanumber"));
           	}
           	else {
           		$("#mofaNumber").closest('.formInputBox').find(".help-block").removeClass("has-error").text("");
           	}
    	});*/
    	$(document).on("blur", "#mofaNumber", function(element) {
    		var mofaSpecialNumber = $(this).val();
    		var $element = $("#mofaNumber");
    		var messageElements = $element.closest('.formInputBox').find(".help-block");
    		messageElements.empty();
    		if (mofaSpecialNumber.length != 0) {
    			$("#isMofaVerified").val(true);
    			$.get({
                    url: ACC.config.encodedContextPath + "/my-sagia/license/verifyMofa/" + mofaSpecialNumber
                }).done(function (response) {
                    var validMofaNumber = JSON.parse(response);
                    if (validMofaNumber) {
                    	$("#mofaNumber").closest('.formInputBox').find(".help-block").removeClass("has-error").text("");
                    	if(validMofaNumber.isMofaVerified === "X"){
                    		$("#isMofaVerified").val(true);
                    		//$("#mofaNumber-error").addClass("has-error").text("Valid MOFA Number");d
                    		
                            messageElements.addClass("success-message-block").text(getI18nText('shareholder.mofaNumber.valid'));
                            messageElements.show();
                    		
                    		$("#delegateSection #loaFileDiv").hide();
                    		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
                    		//element.find('#loaFileDiv').hide();
                    		
                    		if($('#organizationType').val() === "Organization"){
                    			$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();                	
                            	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');                        	
                            	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
                             	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file');
                             	
                             	$("#addShareholderQM1NewOrganizationSection #orgAttachmentTitle").hide();
                    		}
                    		
                    	}else{
                    		$("#isMofaVerified").val(false);
                    		//$("#mofaNumber-error").addClass("has-error").text("Invalid MOFA Number");
                    		
                            messageElements.removeClass('success-message-block').addClass("has-error").text(getI18nText('shareholder.mofaNumber.invalid'));
                            messageElements.show();
                    		
                    		$("#delegateSection #loaFileDiv").show();
                    		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                    		
                    		if($('#organizationType').val() === "Organization"){
                    			if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val() !== "SA")
                                {
		                    		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
		                        	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');                        	
		                        	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
		                         	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                                }
                    		}
                    	}
                    	
                    } else {
                    	$("#isMofaVerified").val(false);
                    	//$("#mofaNumber-error").addClass("has-error").text("Invalid MOFA Number");
                    	messageElements.removeClass('success-message-block').addClass("has-error").text(getI18nText('shareholder.mofaNumber.invalid'));
                        messageElements.show();
                        
                    	$("#delegateSection #loaFileDiv").show();
                    	$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                    	
                    	if($('#organizationType').val() === "Organization"){
                    		if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val() !== "SA")
                            {
	                    		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
	                        	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');                        	
	                        	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
	                         	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                            }
                		}
                    }
                }).fail(function (error) {
                	$("#isMofaVerified").val(false);
                	//$("#mofaNumber-error").addClass("has-error").text("Invalid MOFA Number");
                	messageElements.removeClass('success-message-block').addClass("has-error").text(getI18nText('shareholder.mofaNumber.invalid'));
                    messageElements.show();
                    
                	$("#delegateSection #loaFileDiv").show();
                	$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                	
                	if($('#organizationType').val() === "Organization"){
                		if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val() !== "SA")
                        {
	                		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
	                    	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');                        	
	                    	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
	                     	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                        }
            		}
                });
           	 	
           	}
    		/*else{
    			$("#isMofaVerified").val(false);
    			//messageElements.removeClass('success-message-block').addClass("has-error").text(getI18nText('shareholder.mofaNumber.invalid'));
                //messageElements.show();
                
                $("#delegateSection #loaFileDiv").show();
                $("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
    			
    			if($('#organizationType').val() === "Organization"){
    				if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val() !== "SA")
                    {
	            		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
	                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');                        	
	                	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
	                 	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                    }
        		}
    		}*/
    		
    	});
    	
    },

    bindSelect2ShareholderForms: function (element) {
        var defaultMatcher = $.fn.select2.defaults.defaults.matcher;

        function matchStart(params, data) {
            params.term = params.term || '';
            if (data.text.toUpperCase().indexOf(params.term.toUpperCase()) == 0) {
                return data;
            }

            return null;
        }

        element.find(".js-select2-oneColumn").each(function () {
            var _minimumResultsforSearch;
            var _searchBegining;
            var _sortAlphabetically;

            //set this class on select to force searchBar
            $(this).hasClass('js-select2-forceSearch') ? _minimumResultsforSearch = 0 : _minimumResultsforSearch = 10;

            //set this class on select to change search behaviour to begining of word
            $(this).hasClass('js-select2-searchBegining') ? _searchBegining = true : _searchBegining = false;

            //set this class on select to sort alphabetically
            $(this).hasClass('js-select2-sortAlphabetically') ? _sortAlphabetically = true : _sortAlphabetically = false;

            $(this).select2({
                placeholder: "",
                minimumResultsForSearch: _minimumResultsforSearch,
                dropdownCssClass: 'select2-dropdown_oneColumn select2-dropdown_oneColumn_hasFilter',
                matcher: function(params, data) {
                    if (_searchBegining) {
                        return matchStart(params, data);
                    } else {
                        return defaultMatcher(params, data);
                    }
                },
                sorter: function(data) {
                    if (_sortAlphabetically) {
                        return data.sort(function (a, b) {
                            a = a.text.toLowerCase();
                            b = b.text.toLowerCase();
                            if (a > b) {
                                return 1;
                            } else if (a < b) {
                                return -1;
                            }
                            return 0;
                        });
                    } else {
                        return data;
                    }
                }
            });

            if ($(this).prop('disabled')) {
                SAGIA.licenseApplyShareholder.bindSelectEventToSelect2Disabled($(this));
            }

            $(this).on('select2:select', function (e) {
                if ($(this).val()) {
                    $(this).next().addClass("select2Container_selected");
                } else {
                    $(this).next().removeClass("select2Container_selected");
                }
            });

            $(this).on('change', function (e) {
                if ($(this).val()) {
                    $(this).next().addClass("select2Container_selected");
                } else {
                    $(this).next().removeClass("select2Container_selected");
                }
            });
        })
    },
    loadPersonShareholderOnUpdate: function() {
    	 
    	 $('#addShareholderQM1NewPersonSection #delegateSectionQuestion').hide();
    	 $('#addShareholderQM1NewPersonSection #shareholderIdTypeSection').show();
    	 //diable shareholder id type sectio
    	 $('#addShareholderQM1NewPersonSection #shareholderIdType').attr('disabled','disabled');
    	 $('#addShareholderQM1NewPersonSection #shareholderIdNumber').attr('disabled','disabled');
    	 $('#addShareholderQM1NewPersonSection #shareholderDateofBirth').attr('disabled','disabled');
    	 $('#addShareholderQM1NewPersonSection #nicShareholderVerifyBtnSection').hide();
    	 $('#addShareholderQM1NewPersonSection #shareholderDateofBirthSection').hide();
    	 $('#addShareholderQM1NewPersonSection #shareholderIdNumberSection').hide();
         
    	 // check whether there is a delegate:
    	 var isDelegate = $("#addShareholderQM1NewPersonSection #hasDelegateNO").attr('checked');
    	 if(isDelegate){
    		 $('#addShareholderQM1NewPersonSection #verifyDelegateDetails').show();
    		// $('#addShareholderQM1NewPersonSection #idType').attr('disabled','disabled');
    		// $('#addShareholderQM1NewPersonSection #idNumber').attr('disabled','disabled');
    		// $('#addShareholderQM1NewPersonSection #delegateDateofBirth').attr('disabled','disabled');
    	//	 $('#addShareholderQM1NewPersonSection #nicVerifyBtnSection').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateFullNameEnglish').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateLastNameArabic').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateFirstNameArabic').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateGender').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateIssueDate').attr('disabled','disabled');
    		 $('#addShareholderQM1NewPersonSection #delegateExpiryDate').attr('disabled','disabled');
    		// $("#addShareholderQM1NewPersonSection #hasDelegateYES").attr('disabled','disabled');
    		 $("#addShareholderQM1NewPersonSection #delegateCountry").attr('disabled','disabled');
    		 $("#addShareholderQM1NewPersonSection #delegateNationality").attr('disabled','disabled');
    		 $("#addShareholderQM1NewPersonSection #delegateCountry").trigger("blur").trigger('change');
    		 	 
    	 }
    	 
    	 
    	var itType = $("#addShareholderQM1NewPersonSection #shareholderIdType").attr('data-value');
    	var element = $("#addShareholderQM1NewPersonSection #dataSection");
    	
    	
    	
    	var toggleUmmAlQuraOrNormalCalInShareholderSection = function(itType,element){
            if(itType==="1"){
                bindUmmAlQuraCal($('#addShareholderQM1NewPersonSection #shareholderDateofBirth'));
                bindUmmAlQuraCal($('#addShareholderQM1NewPersonSection #dateOfBirth'));
            
            }
           
        };
        
    	toggleUmmAlQuraOrNormalCalInShareholderSection(itType,element);
    	
    	 
         
         
    	if(itType === '4' ){ //id type is  4
    		
    		 $('#addShareholderQM1NewPersonSection #delegateSection').show();
    		 $('#addShareholderQM1NewPersonSection #delegateDivSection').show();
        //     $('#verifyDelegateDetails').hide();
        //     $('#delegateDetails').hide();
             
             $('#addShareholderQM1NewPersonSection #attachmentSection').show();
             
             var passportFileAttachment = $("#addShareholderQM1NewPersonSection #passportFileAttachment");
         	$("#addShareholderQM1NewPersonSection #passportFileAttachment").show();
         	passportFileAttachment.find('[name=passportFile]').addClass('validate__file');
    		
         	$('#addShareholderQM1NewPersonSection #mofaNumberSection').show();
    	
    	}else {
    		 $('#addShareholderQM1NewPersonSection #dateOfBirth').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #passportExpiryDate').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #passportNumber').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #firstNameArabic').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #lastNameArabic').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #fullNameEnglish').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #delegateGender').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #delegateExpiryDate').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #premiumResident').attr('disabled','disabled');
        	 $('#addShareholderQM1NewPersonSection #attachmentSection').show();
        	 $('[name=shareHolderTitle]').filter(':not(:checked)').attr('disabled', 'disabled');
        	 
        	var passportFileAttachment = $("#addShareholderQM1NewPersonSection #passportFileAttachment");
           	$("#addShareholderQM1NewPersonSection #passportFileAttachment").hide();
           	passportFileAttachment.find('[name=passportFile]').removeClass('validate__file');
    		
           	$('#addShareholderQM1NewPersonSection #mofaNumberSection').hide();
    	}
    	
    },
    
    loadOrganizationShareholderOnUpdate: function(hascode) {
    	
   	 $('#addShareholderQM1NewOrganizationSection #shareholderIdTypeSection').show();
   	 var country = $("#companyCountry").attr('data-value');
     
   	 if(country == "SA")
     {
       
     	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').hide();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').show();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').show();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').show();
     	
     	$("#addShareholderQM1NewOrganizationSection #inputCRNumber").prop('disabled', false);
     	$("#addShareholderQM1NewOrganizationSection #companyRegistrationNumber").prop('disabled', true);
     	$("#addShareholderQM1NewOrganizationSection #organizationNameEnglish").prop('disabled', true);
     	$("#addShareholderQM1NewOrganizationSection #organizationNameArabic").prop('disabled', true);
     	$("#addShareholderQM1NewOrganizationSection #companyCapital").prop('disabled', true);
     	$("#addShareholderQM1NewOrganizationSection #companyCountryOfRegistration").prop('disabled', true);
 
     	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();
     	
     	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');
     	
     	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
     	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file');     	
     	
     	$('#addShareholderQM1NewOrganizationSection #mofaNumberSection').hide();
     	//$("#addShareholderQM1NewOrganizationSection #orgAttachmentTitle").hide();
     }
     else
     {
     	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').show();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').show();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').show();
     	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').show();
     	$("#addShareholderQM1NewOrganizationSection #load-investor").attr("disabled", true);
     	$("#addShareholderQM1NewOrganizationSection #inputCRNumber").attr("disabled", true);
     	
     	//$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
     	//$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');
     	$('#addShareholderQM1NewOrganizationSection #mofaNumberSection').show();
    	if($('#isMofaVerified').val() === "true"){
    		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();                	
        	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');
        	
        	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
         	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file');
    		
    	}else{
    		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
        	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');
        	
        	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
        	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
    	}
     	
     	 $('#addShareholderQM1NewOrganizationSection #verifyDelegateDetails').show();
     	 
     	// check whether there is a delegate:
    	 var isDelegate = $("#addShareholderQM1NewOrganizationSection #OrganizationDelegateYES").attr('checked');
    	 if(isDelegate){
    		 $('#addShareholderQM1NewOrganizationSection #verifyDelegateDetails').show();
    		 $('#addShareholderQM1NewOrganizationSection #showDelegateQuestion').hide();
    		 
    		 var idType = $('#addShareholderQM1NewOrganizationSection #idType').attr('data-value');
    		 
    		 if(idType !== "4"){
    		 $('#addShareholderQM1NewOrganizationSection #delegateFullNameEnglish').attr('disabled','disabled');
    		 $('#addShareholderQM1NewOrganizationSection #delegateLastNameArabic').attr('disabled','disabled');
    		 $('#addShareholderQM1NewOrganizationSection #delegateFirstNameArabic').attr('disabled','disabled');
    		 $('#addShareholderQM1NewOrganizationSection #delegateGender').attr('disabled','disabled');
    		 $('#addShareholderQM1NewOrganizationSection #delegateIssueDate').attr('disabled','disabled');
    		 $('#addShareholderQM1NewOrganizationSection #delegateExpiryDate').attr('disabled','disabled');
    		 }
    	
    		 
    	 }else {
    	
    		 $('#addShareholderQM1NewOrganizationSection #delegateSection').hide();
    		 
    	 }
     	       
     }
   	 
   	 
   },
   
    loadIdAndGenderDropDown: function() {
        var shareholdersQM1 = $("#shareholdersQM1");
        var personDelegateGender = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateGender");
        var previousPersonDelegateGender = personDelegateGender.attr('data-value');
        if (previousPersonDelegateGender) {
            previousPersonDelegateGender = previousPersonDelegateGender.toLowerCase();
            previousPersonDelegateGender = previousPersonDelegateGender.substr(0,1).toUpperCase()+previousPersonDelegateGender.substr(1);
        }
        personDelegateGender.find("option").remove();
        personDelegateGender.append(new Option("", "", false, false));

        var personDelegateIdType = shareholdersQM1.find("#addShareholderQM1NewPersonSection #idType");
        var previousPersonDelegateIdType = personDelegateIdType.attr('data-value');
        personDelegateIdType.find("option").remove();
        personDelegateIdType.append(new Option("", "", false, false));

        var organizationDelegateGender = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateGender");
        var previousOrganizationDelegateGender = organizationDelegateGender.attr('data-value');
        if (previousOrganizationDelegateGender) {
            previousOrganizationDelegateGender = previousOrganizationDelegateGender.toLowerCase();
            previousOrganizationDelegateGender = previousOrganizationDelegateGender.substr(0,1).toUpperCase()+previousOrganizationDelegateGender.substr(1);
        }
        organizationDelegateGender.find("option").remove();
        organizationDelegateGender.append(new Option("", "", false, false));

        var organizationDelegateIdType = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #idType");
        var previousOrganizationDelegateIdType  = organizationDelegateIdType.attr('data-value');
        organizationDelegateIdType.find("option").remove();
        organizationDelegateIdType.append(new Option("", "", false, false));
        
        var personShareholderIdType = shareholdersQM1.find("#addShareholderQM1NewPersonSection #shareholderIdType");
        var previousPersonShareholderIdType = personShareholderIdType.attr('data-value');
        personShareholderIdType.find("option").remove();
        personShareholderIdType.append(new Option("", "", false, false));
        
        var contactPersonQM1 = $("#contactPersonQM1");
        
        var contactGender = contactPersonQM1.find("#delegateGender");
        var previousContactGender = contactGender.attr('data-value');
        if (previousContactGender) {
        	previousContactGender = previousContactGender.toLowerCase();
        	previousContactGender = previousContactGender.substr(0,1).toUpperCase()+previousContactGender.substr(1);
        }
        contactGender.find("option").remove();
        contactGender.append(new Option("", "", false, false));
        
        var contactIdType = contactPersonQM1.find("#idType");
        var previousContactIdType = contactIdType.attr('data-value');
        contactIdType.find("option").remove();
        contactIdType.append(new Option("", "", false, false));
        

        personDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.male"), "Male", false, false));
        personDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.female"), "Female", false, false));
        organizationDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.male"), "Male", false, false));
        organizationDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.female"), "Female", false, false));
        
        personShareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        personShareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        personShareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
        personShareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));

        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
       // personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));
       
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));
        
        contactIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        contactIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        contactIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
        contactIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));

        if(previousOrganizationDelegateGender) {
            organizationDelegateGender.val(previousOrganizationDelegateGender).trigger("blur").trigger('change');
        } else {
            organizationDelegateGender.val(null);
        }

        if(previousPersonDelegateGender) {
            personDelegateGender.val(previousPersonDelegateGender).trigger("blur").trigger('change');
        } else {
            personDelegateGender.val(null);
        }

        if(previousOrganizationDelegateIdType) {
            organizationDelegateIdType.val(previousOrganizationDelegateIdType).trigger("blur").trigger('change');
        } else {
            organizationDelegateIdType.val(null);
        }

        if(previousPersonDelegateIdType) {
            personDelegateIdType.val(previousPersonDelegateIdType).trigger("blur").trigger('change');
        } else {
            personDelegateIdType.val(null);
        }
        
        if(previousPersonShareholderIdType) {
        	personShareholderIdType.val(previousPersonShareholderIdType).trigger("blur").trigger('change');
        } else {
        	personShareholderIdType.val(null);
        }
        
        if(previousContactIdType) {
        	contactIdType.val(previousContactIdType).trigger("blur").trigger('change');
        } else {
        	contactIdType.val(null);
        }
        
        
    },

    bindDelegateEvents: function (hascode) {
    	
        $("#addShareholderQM1NewPersonSection #delegateSection").find("#idType").change(function(){
            var type = "Person";
            var element = $("#addShareholderQM1NewPersonSection #delegateSection");
            onchangeOfIdType(element, type);
        });
        $("#addShareholderQM1NewOrganizationSection #delegateSection").find("#idType").change(function(){
            var type = "Organization";
            var element = $("#addShareholderQM1NewOrganizationSection");
            onchangeOfIdType(element, type);
        });
        $("#contactPersonQM1").find("#idType").change(function(){
            var type = "Organization";
            var element = $("#contactPersonQM1");
            onchangeOfContactIdType(element, type);
        });

        
        if(!hascode) {  // hide the id type section when editing an
            $("#addShareholderQM1NewPersonSection").find("#shareholderIdType").change(function(){
                var type = "Person";
                var element = $("#addShareholderQM1NewPersonSection");
                if(element.find("#shareholderIdType").val()==="4"){
                	element.find("#mofaNumberSection").show();
                    element.find("#isMofaVerified").val(false)
                }else{
                	element.find("#mofaNumberSection").hide();
                	element.find("#personAttachmentTitle").hide();
                    element.find("#isMofaVerified").val(false);
                }
                onchangeOfShareholderIdType(element, type);
            });
        }
        
        if(!hascode) {  // hide the id type section when editing an
        $("#addShareholderQM1NewOrganizationSection").find("#companyCountry").change(function(){
            var type = "Organization";
            var element = $("#addShareholderQM1NewOrganizationSection");
            if(element.find("#companyCountry").val() == "SA")
            {
            	element.find("#mofaNumberSection").hide();
            	element.find("#orgAttachmentTitle").hide();
            	element.find("#isMofaVerified").val(false);
            }else{
            	element.find("#mofaNumberSection").show();
            	//element.find("#mofaNumberSection").val(false);
            }
            onchangeOfCompanyCountry();
        });
        }
        
        
        var onchangeOfContactIdType = function(element, type)
        {
        	//resetContactEvent();
            toggleUmmAlQuraOrNormalCalInContactSection(element);

            element.find("#nicVerifyBtnSection").show();
            element.find("#delegateDateofBirthSection").show();
            element.find("#idNumberection").show();
            element.find("#contactDetails").hide();
            element.find("#contactSection").hide();
            
            
            
            var initialEditEvents = false;
            var form = element.find('form');

            if (type === 'Organization') {
                initialEditEvents = form.hasClass('edit-initial') &&
                    form.find('input[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true';
            } else {
                initialEditEvents = form.hasClass('edit-initial') &&
                    form.find('input[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true' &&
                    form.find('input[name=delegateInfo\\.delegateYourself]').filter(':checked').val() === 'false';
            }

            var isEditErrorShareholder = element.find("#idType").val()==="4" || element.find('#isNicVerified').prop('checked');

            var delegateAttr = element.find('[name=delegateInfo\\.delegate]').parents('.form-group');

            var validateInitialEvents = initialEditEvents && delegateAttr.attr('data-delegate') && delegateAttr.attr('data-delegate') !== 'false';

            if (validateInitialEvents) {
                if(element.find("#idType").val()==="4"){
                    //For passport
                    element.find("#nicVerifyBtnSection").hide();
                    element.find("#delegateDateofBirthSection").hide();
                    element.find("#idNumberection").hide();
                    element.find("#contactDetails").show();
                    element.find("#contactSection").show();
                    
                   // make fields Editable:   	
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
                    // 
                }

                element.find("#verifyDelegateDetails").show();
                form.removeClass('edit-initial');

                if (element.find("#idType").val()!=="4") {
                    toggleTheNICFieldsEditable(element, type, false);
                    element.find("#contactDetails").hide();
                    element.find("#contactSection").hide();
                    
                }
            } else {
                if(element.find("#idType").val()==="4"){
                    //For passport
                    element.find("#nicVerifyBtnSection").hide();
                    element.find("#delegateDateofBirthSection").hide();
                    element.find("#verifyDelegateDetails").show();
                    element.find("#idNumberection").hide();
                    element.find("#contactDetails").show();
                    element.find("#contactSection").show();

                    if($("#savedContactIdType").val() === "4")
                    {
                        // previously saved data is with passport ID, so this is edit request. Just enable edit. Don't reset.
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
                        $("#savedContactIdType").val("");
                    }
                    else
                    {
                            // On change of passport ID reset all value of contact details
                            SAGIA.licenseApplyContactPerson.resetContactDetails();
                    }
                }

               // resetDelegateDetails(element, type);
            }

            toggleIdCopeFileSection(element);
            toggleFieldsOnValue(element);
           // resetDataSectionFields(element,type);
        }
        
        
        var onchangeOfIdType = function(element, type)
        {
            toggleUmmAlQuraOrNormalCalInDelegateSection(element);

            element.find("#nicVerifyBtnSection").show();
            element.find("#delegateDateofBirthSection").show();
            element.find("#idNumberection").show();
            element.find("#contactDetails").hide();
            element.find("#contactSection").hide();
            
            
            
            var initialEditEvents = false;
            var form = element.find('form');

            if (type === 'Organization') {
                initialEditEvents = form.hasClass('edit-initial') &&
                    form.find('input[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true';
            } else {
                initialEditEvents = form.hasClass('edit-initial') &&                 
                    form.find('input[name=delegateInfo\\.delegateYourself]').filter(':checked').val() === 'false';
            }

            var isEditErrorShareholder = element.find("#idType").val()==="4" || element.find('#isNicVerified').prop('checked');

            var delegateAttr = element.find('[name=delegateInfo\\.delegate]').parents('.form-group');

            var validateInitialEvents = initialEditEvents && delegateAttr.attr('data-delegate') && delegateAttr.attr('data-delegate') !== 'false';

            if (validateInitialEvents) {
                if(element.find("#idType").val()==="4"){
                    //For passport
                    element.find("#nicVerifyBtnSection").hide();
                    element.find("#delegateDateofBirthSection").hide();
                    element.find("#idNumberection").hide();
                    element.find("#contactDetails").show();
                    element.find("#contactSection").show();
                    
                    var passportFileAttachment = $("#delegateSection #passportFileAttachment");
                   	$("#delegateSection #passportFileAttachment").show();
                   	passportFileAttachment.find('[name=passportFile]').addClass('validate__file');
                   	element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.passport"));
                    element.find('#idCopyFileDiv').show();
                    // 
                    if($('#isMofaVerified').val() === "true"){
                   		element.find('#loaFileDiv').hide();
                   		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
                   	}else{
                   		element.find('#loaFileDiv').show();
                   		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                   	}
                }

                element.find("#verifyDelegateDetails").show();
                form.removeClass('edit-initial');
                
                if($('#isMofaVerified').val() === "true"){
               		element.find('#loaFileDiv').hide();
               		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
               		if(element.find("#idType").val() !== "4"){
               			$("#delegateSection #delegateAttachments").hide();
               		}
               	}else{
               		element.find('#loaFileDiv').show();
               		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
               	}

                if (element.find("#idType").val()!=="4") {
                    toggleTheNICFieldsEditable(element, type, false);
                    element.find("#contactDetails").hide();
                    element.find("#contactSection").hide();
                    
                }
            } else {
                if(element.find("#idType").val()==="4"){
                    //For passport
                    element.find("#nicVerifyBtnSection").hide();
                    element.find("#delegateDateofBirthSection").hide();
                    element.find("#verifyDelegateDetails").show();
                    element.find("#idNumberection").hide();
                    element.find("#contactDetails").show();
                    element.find("#contactSection").show();
                    
                    element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.passport"));
                    element.find('#idCopyFileDiv').show();
                    
                    var passportFileAttachment = $("#delegateSection #passportFileAttachment");
                   	$("#delegateSection #passportFileAttachment").show();
                   	passportFileAttachment.find('[name=passportFile]').addClass('validate__file');
                   	
                   	if($('#isMofaVerified').val() === "true"){
                   		element.find('#loaFileDiv').hide();
                   		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
                   	}else{
                   		element.find('#loaFileDiv').show();
                   		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                   	}
                   	
                }else {
                    element.find('#idCopyFileDiv').hide();
                    element.find("#attachmentSection").show();
                    
                    if($('#isMofaVerified').val() === "true"){
                   		element.find('#loaFileDiv').hide();
                   		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
               			$("#delegateSection #delegateAttachments").hide();
                   	}else{
                   		element.find('#loaFileDiv').show();
                   		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
                   	}
                    
                    var passportFileAttachment = $("#delegateSection #passportFileAttachment");
                   	$("#delegateSection #passportFileAttachment").hide();
                   	passportFileAttachment.find('[name=passportFile]').removeClass('validate__file');
                }

                resetDelegateDetails(element, type);
            }

            toggleIdCopeFileSection(element);
            toggleFieldsOnValue(element);
           // resetDataSectionFields(element,type);
        }
        
        
        var  resetContactEvent =  function() {
         	
        	$("#qm1DateOfBirth").removeAttr('readOnly');
        	$("#qm1DateOfBirth").val("") ;
    		$("#qm1PassportNumber").val("").removeAttr('readOnly');
    		$("#qm1Email").val("").removeAttr('readOnly');
    		$("#qm1CountryCodeForMobileNumber").val("").removeAttr('readOnly');
    		$("#qm1MobileNumber").val("").removeAttr('readOnly');	
    		$("#qm1CountryCodeForTelephone").val("").removeAttr('readOnly');
    		$("#qm1Telephone").val("").removeAttr('readOnly');
    		$("#qm1City").val("").removeAttr('readOnly');
    		$("#qm1POBox").val("").removeAttr('readOnly');
    		$("#qm1PostalCode").val("").removeAttr('readOnly');
    		$("#qm1PassportExpiryDate").val("").removeAttr('readOnly');
    		$("#qm1PassportIssueDate").val("").removeAttr('readOnly');
    		$("#qm1Country").val("").removeAttr('readOnly');
        }
        
        
        var toggleUmmAlQuraOrNormalCalInDelegateSection = function(element){
            if(element.find("#idType").val()==="1"){
                bindUmmAlQuraCal(element.find('#delegateDateofBirth'));
                bindNormalCal(element.find('#delegateIssueDate'));
                bindNormalCal(element.find('#delegateExpiryDate'));
            }
            else if(element.find('#delegateDateofBirth').hasClass("ummAlQura"))
            {
                bindNormalCal(element.find('#delegateDateofBirth'));
                bindNormalCal(element.find('#delegateIssueDate'));
                bindNormalCal(element.find('#delegateExpiryDate'));
            }
        };
        
        var toggleUmmAlQuraOrNormalCalInShareholderSection = function(element){
            if(element.find("#shareholderIdType").val()==="1"){
                bindUmmAlQuraCal(element.find('#shareholderDateofBirth'));
                bindUmmAlQuraCal(element.find('#dateOfBirth'));
                bindNormalCal(element.find('#passportExpiryDate'));
            }
            else if(element.find('#shareholderDateofBirth').hasClass("ummAlQura"))
            {
                bindNormalCal(element.find('#shareholderDateofBirth'));
                bindNormalCal(element.find('#dateOfBirth'));
                bindNormalCal(element.find('#passportExpiryDate'));
            }
        };
        
        var toggleUmmAlQuraOrNormalCalInContactSection = function(element){
            if(element.find("#idType").val()==="1"){
                bindUmmAlQuraCal($('#delegateDateofBirth'));              
                bindUmmAlQuraCal($('#qm1DateOfBirth'));
                bindNormalCal($('#qm1PassportExpiryDate'));
                bindNormalCal($('#qm1PassportIssueDate'));
            }
            else if(element.find('#delegateDateofBirth').hasClass("ummAlQura"))
            {
                bindNormalCal($('#delegateDateofBirth'));
                bindNormalCal($('#qm1DateOfBirth'));
                bindNormalCal($('#qm1PassportExpiryDate'));
                bindNormalCal($('#qm1PassportIssueDate'));
            }
        };
        
        

        var bindCalendarsToInputs = function (element) {
            var isUmmAlQura = false;

            if(element.find("#idType").val()==="1"){
                isUmmAlQura = true;
            }

            if (isUmmAlQura) {
                bindCalendarsPickerToInput(element.find('#delegateDateofBirth'));
                bindCalendarsPickerToInput(element.find('#delegateIssueDate'));
                bindCalendarsPickerToInput(element.find('#delegateExpiryDate'));
            } else {
                bindCustomFlatpickr(element.find('#delegateDateofBirth'));
                bindCustomFlatpickr(element.find('#delegateIssueDate'));
                bindCustomFlatpickr(element.find('#delegateExpiryDate'));
            }
        };
        
        var bindShareholderCalendarsToInputs = function (element) {
            var isUmmAlQura = false;

            if(element.find("#shareholderIdType").val()==="1"){
                isUmmAlQura = true;
            }

            if (isUmmAlQura) {
                bindCalendarsPickerToInput(element.find('#shareholderDateofBirth'));
                bindCalendarsPickerToInput(element.find('#dateOfBirth'));
            //    bindCalendarsPickerToInput(element.find('#passportIssueDate'));
            //    bindCalendarsPickerToInput(element.find('#passportExpiryDate'));
            } else {
                if (!element.find('#dateOfBirth').hasClass('flatpickr-input')) {
                    bindCustomFlatpickr(element.find('#dateOfBirth'));
                }
                if (!element.find('#shareholderDateofBirth').hasClass('flatpickr-input')) {
                    bindCustomFlatpickr(element.find('#shareholderDateofBirth'));
                }
                if (!element.find('#passportIssueDate').hasClass('flatpickr-input')) {
                    bindCustomFlatpickr(element.find('#passportIssueDate'));
                }
                if (!element.find('#passportExpiryDate').hasClass('flatpickr-input')) {
                    bindCustomFlatpickr(element.find('#passportExpiryDate'));
                }
            }
        };
        
        
        
     // Start of onchangeOfShareholderIdType --> shareholderIdType
        var onchangeOfShareholderIdType = function(element, type)
        {
            
            toggleUmmAlQuraOrNormalCalInShareholderSection(element);

            element.find("#nicShareholderVerifyBtnSection").show();
            element.find("#shareholderDateofBirthSection").show();
            element.find("#shareholderIdNumberSection").show();

            var initialEditEvents = false;
            var form = element.find('form');

            if (type === 'Organization') {
                initialEditEvents = form.hasClass('edit-initial') &&
                    form.find('input[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true';
            } else {
                initialEditEvents = form.hasClass('edit-initial') &&
                    form.find('input[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true' &&
                    form.find('input[name=delegateInfo\\.delegateYourself]').filter(':checked').val() === 'false';
            }

            var isEditErrorShareholder = element.find("#shareholderIdType").val()==="4" || element.find('#isNicVerified').prop('checked');

            var delegateAttr = element.find('[name=delegateInfo\\.delegate]').parents('.form-group');

            var validateInitialEvents = initialEditEvents && delegateAttr.attr('data-delegate') && delegateAttr.attr('data-delegate') !== 'false';

            if (validateInitialEvents) {
                if(element.find("#shareholderIdType").val()==="4"){
                    //For passport
                    element.find("#nicShareholderVerifyBtnSection").hide();
                    element.find("#shareholderDateofBirthSection").hide();
                    element.find("#shareholderIdNumberSection").hide();
                    element.find("#delegateSectionQuestion").hide();
                    
                }

                element.find("#verifyDelegateDetails").show();
                form.removeClass('edit-initial');

                if (element.find("#shareholderIdType").val()!=="4") {
                    toggleTheNICFieldsEditable(element, type, false);
                    element.find("#shareholderDateofBirthSection").show();
                    element.find("#shareholderIdNumberSection").show();
                    element.find("#delegateSectionQuestion").hide();
                    element.find("#personAttachmentTitle").hide();
                    //element.find("#mofaNumberSection").hide();
                    //element.find("#isMofaVerified").val(false);
                }
            } else {
                if(element.find("#shareholderIdType").val()==="4"){
                    //For passport
                    element.find("#nicShareholderVerifyBtnSection").hide();
                    element.find("#verifyDelegateDetails").show();
                    element.find("#shareholderDateofBirthSection").hide();
                    element.find("#shareholderIdNumberSection").hide();
                    element.find("#delegateSectionQuestion").hide();
                    
                    
                }

                resetShareholderDetails(element, type);
            }

            toggleIdCopeFileSection(element);
            toggleFieldsOnValue(element);
            toggleSectionsOnValue(element);
            resetDataSectionFields(element,type);
        };
        
        var onchangeOfCompanyCountry = function() 
        
        {
        	

            var shareholdersQM1 = $("#shareholdersQM1");
            var newFileSection = $("#companyCheckFileAttachment");
            if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val()) {
                newFileSection.hide();
                SAGIA.licenseApplyShareholderCommons.loadNewFile(shareholdersQM1.find("#companyCountry"), newFileSection);
                newFileSection.find('[name=companyMemoAssociationFile]').removeClass('validate__file');
            }  
                if($("#addShareholderQM1NewOrganizationSection").find("#companyCountry").val() == "SA")
                {
                	$("#addShareholderQM1NewOrganizationSection #showDelegateQuestion").hide();
                	$("#addShareholderQM1NewOrganizationSection #delegateSectionQuestion").hide();
                	
                  
                	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').hide();
                	
                	
                		$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').hide();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').hide();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').hide();
                
                	
                	
                	$("#addShareholderQM1NewOrganizationSection #inputCRNumber").prop('disabled', false);
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();
                	
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');
                	
                	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
                 	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file'); 
                	
                 	$("#addShareholderQM1NewOrganizationSection #mofaNumberSection").hide();
                 	$("#addShareholderQM1NewOrganizationSection #orgAttachmentTitle").hide();
                 	$("#addShareholderQM1NewOrganizationSection #isMofaVerified").val(false);
                }
                else
                {
                 	//For passport
                	$("#addShareholderQM1NewOrganizationSection #delegateSectionQuestion").show();
                	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').show();
                 	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').show();
                	
                	if (!hascode) {
                		$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').show();
                		$('#addShareholderQM1NewOrganizationSection #showDelegateQuestion').hide();
                		$('#addShareholderQM1NewOrganizationSection #delegateSection').hide();
                		
                		
                	}
                	
                	
                 	$('#addShareholderQM1NewOrganizationSection #delegateDetails').hide();              
                 	$("#addShareholderQM1NewOrganizationSection #verifyDelegateDetails").hide();
                	$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').show();
                	$('#addShareholderQM1NewOrganizationSection #showDelegateQuestion').hide();
                	
                	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').show();
                	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').show();
                	$("#addShareholderQM1NewOrganizationSection #load-investor").attr("disabled", true);
                	$("#addShareholderQM1NewOrganizationSection #inputCRNumber").attr("disabled", true);
                
                	$("#addShareholderQM1NewOrganizationSection #mofaNumberSection").show();
                 	//$("#addShareholderQM1NewOrganizationSection #isMofaVerified").val(false);
                
//RHQ CHANGES START
			    var isicSectionsElement=$('#organizationShareholderForm #companySection')
				var licenseType = isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') ? isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') : 1;
					 if(licenseType === "11")
					{
						//var sharesPercentage = shareholdersQM1.find("#companySharesPercentage");
						var professionalLicense = shareholdersQM1.find("#companyProfessionalLicense");
						//sharesPercentage.attr("disabled", true);
	                	//sharesPercentage.val("100").trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded
	                	professionalLicense.attr("disabled", true);
	                	professionalLicense.val('no').trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded
					}
 //RHQ CHANGES END               	    
                	if($('#isMofaVerified').val() === "true"){
                		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();                	
                    	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');
                    	
                    	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
                     	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file');
                     	
                     	$("#addShareholderQM1NewOrganizationSection #orgAttachmentTitle").hide();
                		
                	}else{
                		$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
                    	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');
                    	
                    	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
                    	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                	}
                	//$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
                	//$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');
   
                }
                
                if($("#addShareholderQM1NewOrganizationSection #professionalLicenseCrVerified").val() == 'true'){
            		$("#addShareholderQM1NewOrganizationSection #organizationNameEnglish").val("").removeAttr('disabled');
            		$("#addShareholderQM1NewOrganizationSection #organizationNameArabic").val("").removeAttr('disabled');
            		$("#addShareholderQM1NewOrganizationSection #companyCapital").val("").removeAttr('disabled');	
            		$("#addShareholderQM1NewOrganizationSection #professionalLicenseCrVerified").val(false);
            		$("#addShareholderQM1NewOrganizationSection #inputCRNumber").val("");
            		$("#addShareholderQM1NewOrganizationSection#companyRegistrationNumber").val("").removeAttr('disabled');
            		$("#addShareholderQM1NewOrganizationSection #companyCountryOfRegistration").val("").removeAttr('disabled');
            		
            	}
            
        
        	
        	
        }
   // End of onchangeOfShareholderIdType --> shareholderIdType
        
        
        var toggleUmmAlQuraOrNormalCalInDelegateSection = function(element){
            if(element.find("#idType").val()==="1"){
                bindUmmAlQuraCal(element.find('#delegateDateofBirth'));
                bindNormalCal(element.find('#delegateIssueDate'));
                bindNormalCal(element.find('#delegateExpiryDate'));
            }
            else if(element.find('#delegateDateofBirth').hasClass("ummAlQura"))
            {
                bindNormalCal(element.find('#delegateDateofBirth'));
                bindNormalCal(element.find('#delegateIssueDate'));
                bindNormalCal(element.find('#delegateExpiryDate'));
            }
        };
         var bindUmmAlQuraCal = function(element){
            element.addClass("ummAlQura");
            var flatPickrInstance = element.get(0)._flatpickr;
            if(flatPickrInstance === undefined) {
             
            }else {
            	 flatPickrInstance.destroy();
            }
            

            element.prop('readonly',true);

            bindCalendarsPickerToInput(element);
        };
        var bindNormalCal = function(element){
            element.removeClass("ummAlQura");

            element.calendarsPicker('destroy');

            bindCustomFlatpickr(element);
        };

        var bindCalendarsToInputs = function (element) {
            var isUmmAlQura = false;

            if(element.find("#idType").val()==="1"){
                isUmmAlQura = true;
            }

            if (isUmmAlQura) {
                bindCalendarsPickerToInput(element.find('#delegateDateofBirth'));
               // bindCalendarsPickerToInput(element.find('#delegateIssueDate'));
               // bindCalendarsPickerToInput(element.find('#delegateExpiryDate'));
                bindCustomFlatpickr(element.find('#delegateIssueDate'));
                bindCustomFlatpickr(element.find('#delegateExpiryDate'));
            } else {
                bindCustomFlatpickr(element.find('#delegateDateofBirth'));
                bindCustomFlatpickr(element.find('#delegateIssueDate'));
                bindCustomFlatpickr(element.find('#delegateExpiryDate'));
            }
        };
        
        

        var bindCalendarsPickerToInput = function (element) {
            var calendar = $.calendars.instance('ummalqura', SAGIA.locale === 'en' ? '' : SAGIA.locale);

            element.prop('readonly',true);

            element.calendarsPicker($.extend({

                    showOtherMonths: true,
                    changeMonth: true,
                    selectOtherMonths: true,
                    showSpeed: "800",
                    useMouseWheel: false,
                    yearRange: 'any'
                }, $.calendarsPicker.regionalOptions[SAGIA.locale === 'en' ? '' : SAGIA.locale])
            ).calendarsPicker('option', {
                calendar: calendar,
                dateFormat: ACC.formatUIDateUAQ,
//            altField: $("#delegateDateofBirthUmm"),
//            altFormat: 'yyyy-mm-dd',
                prevText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M15.758.374c.365-.365.955-.365 1.319 0 .364.364.363.954-.001 1.319l-7.789 10.31 7.789 10.305c.364.365.365.955.001 1.319s-.954.364-1.319 0l-9.108-11.624 9.108-11.629z\"/></svg>",
                nextText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M8.242.374c-.365-.365-.956-.365-1.319 0-.364.364-.363.954 0 1.319l7.79 10.311-7.79 10.305c-.364.365-.364.955 0 1.319s.955.364 1.319 0l9.107-11.624-9.107-11.63z\"/></svg>",
            });

            if (element.attr('value')) {
                element.val(element.attr('value'));
            }
        };

        var bindCustomFlatpickr = function (element) {
            ACC.calendarcommons.bindFlatpickr(element);
        };

        var resetDelegateDetails = function(element, type)
        {
        	
        	  var isIntiaLoad = element.hasClass('load-delegate') ;
        	
        	   if (!isIntiaLoad) {
        	   element.find("#idCopyFile").val(null); //mind the null
               element.find("#idCopyFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
               element.find("#idCopyFileName").val("");
               element.find("#authorizationLetterFile").val(null); //mind the null
               element.find("#authorizationLetterFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
               element.find("#authorizationLetterFileName").val("");
               element.find("#delegateCountry").val("").trigger("blur").trigger('change');
               element.find("#delegateNationality").val("").trigger("blur").trigger('change');
               element.find("#delegatePostalCode").val("");
               element.find("#delegatePOBox").val("");
               element.find("#delegateCountryCodeForTelephone").val("");
               element.find("#delegateTelephone").val("");
               element.find("#delegateCountryCodeForMobile").val("");
               element.find("#delegateMobile").val("");
               element.find("#delegateEmail").val("");
        	   }
        	   
        	   element.removeClass('load-delegate');
        	
        	
            element.find("#delegateFirstNameArabic").val("");
            element.find("#delegateLastNameArabic").val("");
            element.find("#delegateFullNameEnglish").val("");
            element.find("#delegateGender").val("").trigger("blur").trigger('change').siblings('span.select2').removeClass('');
            element.find("#delegateExpiryDate").val("");
            element.find("#delegateIssueDate").val("");
           
     
            toggleTheNICFieldsEditable(element, type, true);

         
                   
            element.find("#verifyDelegateDetails").hide();

            if(element.find("#idType").val()==="4"){
                //For passport
                element.find("#nicVerifyBtnSection").hide();
                element.find("#verifyDelegateDetails").show();
                element.find("#idCopyFileDiv").show();
                
                if($('#isMofaVerified').val() === "true"){
               		element.find('#loaFileDiv').hide();
               		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
               	}else{
               		element.find('#loaFileDiv').show();
               		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
               	}
                
            }

            element.find("#isNicVerified").prop('checked', false);

        }
        
        // reset shareholder Details
        var resetShareholderDetails = function(element, type)
        {
            element.find("#delegateFirstNameArabic").val("");
            element.find("#delegateLastNameArabic").val("");
            element.find("#delegateFullNameEnglish").val("");
            
            
            element.find("#premiumResident").val("").trigger("blur").trigger('change');
            element.find("#currentNationality").val("").trigger("blur").trigger('change');
            element.find("#country").val("").trigger("blur").trigger('change');
            
            
            element.find("#delegateGender").val("").trigger("blur").trigger('change').siblings('span.select2').removeClass('');
            element.find("#delegateExpiryDate").val("");
            element.find("#delegateIssueDate").val("");
            element.find("#delegateCountry").val("").trigger("blur").trigger('change');
            element.find("#delegateNationality").val("").trigger("blur").trigger('change');
            element.find("#delegatePostalCode").val("");
            element.find("#delegatePOBox").val("");
            element.find("#delegateCountryCodeForTelephone").val("");
            element.find("#delegateTelephone").val("");
            element.find("#delegateCountryCodeForMobile").val("");
            element.find("#delegateMobile").val("");
            element.find("#delegateEmail").val("");
            toggleTheNICFieldsEditable(element, type, true);

            element.find("#idCopyFile").val(null); //mind the null
            element.find("#idCopyFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
            element.find("#idCopyFileName").val("");
            element.find("#authorizationLetterFile").val(null); //mind the null
            element.find("#authorizationLetterFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
            element.find("#authorizationLetterFileName").val("");

            element.find("#verifyDelegateDetails").hide();

            if(element.find("#shareholderIdType").val()==="4"){
                //For passport
                element.find("#nicShareholderVerifyBtnSection").hide();
                element.find("#verifyDelegateDetails").show();
            }

            element.find("#isNicVerified").prop('checked', false);

        }
        
     // End reset shareholder Details

        var toggleIdCopeFileSection = function(element){

        	 
        };
        
        var toggleShareholderIdCopeFileSection = function(element){

            
           if(element.find("#shareholderIdType").val() == "4")
            {
                element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.passport"));
                element.find('#idCopyFileDiv').show();
                
                var passportFileAttachment = $("#passportFileAttachment");
               	$("#passportFileAttachment").show();
               	passportFileAttachment.find('[name=passportFile]').addClass('validate__file');
               	
               	if($('#isMofaVerified').val() === "true"){
               		element.find('#loaFileDiv').hide();
               		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
              		$("#delegateSection #delegateAttachments").hide();
               	}else{
               		element.find('#loaFileDiv').show();
               		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
               	}
            }
            else {
                element.find('#idCopyFileDiv').hide();
                element.find("#attachmentSection").show();
                
                var passportFileAttachment = $("#passportFileAttachment");
               	$("#passportFileAttachment").hide();
               	passportFileAttachment.find('[name=passportFile]').removeClass('validate__file');
               	
               	if($('#isMofaVerified').val() === "true"){
               		element.find('#loaFileDiv').hide();
               		$("#delegateSection #authorizationLetterFile").removeClass('validate__delegate-file');
               		$("#delegateSection #delegateAttachments").hide();
               	}else{
               		element.find('#loaFileDiv').show();
               		$("#delegateSection #authorizationLetterFile").addClass('validate__delegate-file');
               	}
            }
        };
        
       
        var resetDataSectionFields = function(element, type)
        {
        	 element.find("#dateOfBirth").val("");
        	 element.find("#dateOfBirth").removeAttr('disabled');
        	 element.find("#passportExpiryDate").val("");
        	 element.find("#passportExpiryDate").removeAttr('disabled');
        	 element.find("#passportIssueDate").val("");
        	 element.find("#passportIssueDate").removeAttr('disabled');
        	 element.find("#passportNumber").val("");
        	 element.find("#passportNumber").removeAttr('disabled');
        	 element.find("#firstNameArabic").val("");
        	 element.find("#firstNameArabic").removeAttr('disabled');
        	 element.find("#lastNameArabic").val("");
        	 element.find("#lastNameArabic").removeAttr('disabled');
        	 element.find("#fullNameEnglish").val("");
        	 element.find("#fullNameEnglish").removeAttr('disabled');
        	 element.find("#delegateGender").val("");
        	 element.find("#delegateGender").removeAttr('disabled');
        	 element.find("#delegateExpiryDate").val("");
        	 element.find("#delegateIssueDate").removeAttr('disabled');
        	 element.find("#delegateIssueDate").val("");
        	 element.find("#delegateExpiryDate").removeAttr('disabled');
        	 element.find("#premiumResident").val("");
        	 element.find("#premiumResident").removeAttr('disabled');
        	 
        	 element.find("#organizationTitle").removeAttr('disabled');
             element.find("#personTitle").removeAttr('disabled');
             
             element.find("#currentNationality").removeAttr('disabled');
             element.find("#country").removeAttr('disabled');
           //  element.find("#premiumResident").find("option").remove();
             element.find("#premiumResident").append(new Option("", "", false, false));
             element.find("#premiumResident").val("");
             
             
        }

        var toggleSectionsOnValue = function (element) {
        	
        	element.find('#delegateDivSection').hide();
            element.find('#attachmentSection').hide();
            element.find('#dataSection').hide();
            
            
            if(element.find("#shareholderIdType").val() == "4")
            {
            	$('#hasDelegateYESLabel').trigger('click');
                element.find('#delegateDivSection').show();
                element.find('#verifyDelegateDetails').hide();
                element.find('#attachmentSection').show();
                element.find('#dataSection').show();
                
                var passportFileAttachment = $("#passportFileAttachment");
               	$("#passportFileAttachment").show();
               	passportFileAttachment.find('[name=passportFile]').addClass('validate__file');
                
                
            }
            
            
        };
        
        
        var toggleFieldsOnValue = function (element) {

            element.find('#postalCodeDiv').hide();
            element.find('#poBoxDiv').hide();
            element.find('#delegateCountryDiv').show();
            element.find('#delegateNationalityDiv').show();
            

            if(element.find("#idType").val() == "1")
            {
                element.find('#delegateCountry').val("SA").trigger("blur").trigger("change");
                element.find('#delegateNationality').val("SA").trigger("blur").trigger("change");
                element.find('#delegateCountryDiv').hide();
                element.find('#delegateNationalityDiv').hide();
            }
            else if(element.find("#idType").val() == "4")
            {
                element.find('#postalCodeDiv').show();
                element.find('#poBoxDiv').show();
                
                
            }
        };

        var toggleTheNICFieldsEditable = function(element, type, editable)
        {
            if(editable)
            {
                element.find("#delegateFirstNameArabic").removeAttr("disabled");
                element.find("#delegateLastNameArabic").removeAttr("disabled");
                element.find("#delegateFullNameEnglish").removeAttr("disabled");
                element.find("#delegateGender").removeAttr("disabled");
                element.find("#delegateExpiryDate").removeAttr("disabled");
                element.find("#delegateIssueDate").removeAttr("disabled");
            }
            else
            {
                element.find("#delegateFirstNameArabic").attr('disabled','disabled');
                element.find("#delegateLastNameArabic").attr('disabled','disabled');
                element.find("#delegateFullNameEnglish").attr('disabled','disabled');
                element.find("#delegateGender").attr('disabled','disabled');
                element.find("#delegateExpiryDate").attr('disabled','disabled');
                element.find("#delegateIssueDate").attr('disabled','disabled');
            }
        }
        
        
        bindCalendarsToInputs($("#addShareholderQM1NewPersonSection"));
        bindShareholderCalendarsToInputs($("#addShareholderQM1NewPersonSection"));
        bindCalendarsToInputs($("#addShareholderQM1NewOrganizationSection"));

        $("#addShareholderQM1NewPersonSection").find("#idNumber").change(function(){
            var element = $("#addShareholderQM1NewPersonSection");
            resetDelegateDetails(element, "Person");
        });
        
        $("#addShareholderQM1NewPersonSection").find("#shareholderIdNumber").change(function(){
            var element = $("#addShareholderQM1NewPersonSection");
            resetShareholderDetails(element, "Person");
        });
        
        $("#addShareholderQM1NewOrganizationSection").find("#idNumber").change(function(){
            var element = $("#addShareholderQM1NewOrganizationSection");
            resetDelegateDetails(element, "Organization");
        });

        $("#addShareholderQM1NewPersonSection").find("#idNumber").on('keypress', function (event) {
            validateIdNumber($("#addShareholderQM1NewPersonSection"));
        });
        
        $("#addShareholderQM1NewPersonSection").find("#shareholderIdNumber").on('keypress', function (event) {
        	validateShareholderIdNumber($("#addShareholderQM1NewPersonSection"));
        });

        $("#addShareholderQM1NewOrganizationSection").find("#idNumber").on('keypress', function (event) {
            validateIdNumber($("#addShareholderQM1NewOrganizationSection"));
        });

        var validateIdNumber  = function(element){
            var idType = element.find("#idType").val();
            if(idType && idType==="1" || idType==="2")
            {
                var regex = new RegExp("^[0-9]+$");
                var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
                if (!regex.test(key)) {
                    event.preventDefault();
                    return false;
                }
            }
        }
        
        var validateShareholderIdNumber  = function(element){
            var idType = element.find("#shareholderIdNumber").val();
            if(idType && idType==="1" || idType==="2")
            {
                var regex = new RegExp("^[0-9]+$");
                var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
                if (!regex.test(key)) {
                    event.preventDefault();
                    return false;
                }
            }
        }

        $("#addShareholderQM1NewPersonSection").find("#delegateDateofBirth").change(function(){
            var element = $("#addShareholderQM1NewPersonSection");
            if (!element.find('form').hasClass('edit-initial')) {
                resetDelegateDetails(element, "Person");
            }
        });
        $("#addShareholderQM1NewOrganizationSection").find("#delegateDateofBirth").change(function(){
            var element = $("#addShareholderQM1NewOrganizationSection");
            if (!element.find('form').hasClass('edit-initial')) {
                resetDelegateDetails(element, "Organization");
            }
        });

        $("#addShareholderQM1NewOrganizationSection").find("#verifyDetailsShow").on("click", function() {
            preNICVerfication($("#addShareholderQM1NewOrganizationSection"), $(this));
        });

        $("#addShareholderQM1NewPersonSection").find("#verifyDetailsShow").on("click", function() {
            preNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
        });
        
        $("#addShareholderQM1NewPersonSection").find("#verifyShareholderDetailsShow").on("click", function() {
        	preShareholderNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
        });
       
        $("#contactPersonQM1").find("#verifyDetailsShow").on("click", function() {
            preContactNICVerfication($("#contactPersonQM1"), $(this));
        });
        
        

        var preNICVerfication = function(element, current){
            var idType = element.find("#idType").val();
            if(idType==="4") {
            	return;
            }
            var idNumber = element.find("#idNumber").val();
            var delegateDateofBirth = element.find("#delegateDateofBirth").val();

            current.parents('form').find('.inputValidationError').hide();

            if(!validateNICInput(idType,idNumber,delegateDateofBirth,element))
            {
                return false
            }
            
            verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data){
                if(data)
                {
                    var jsonData = JSON.parse(data);
                    if( jsonData.result && jsonData.result == "Valid Data")
                    {
                        current.data('nicVerified', "true");
                        current.siblings("#isNicVerified").prop('checked', true);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateFirstNameArabic"),jsonData.firstName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateLastNameArabic"),jsonData.lastName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateFullNameEnglish"),jsonData.fullName_EN);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateGender"),jsonData.gender);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateExpiryDate"),jsonData.expiryDate);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateIssueDate"),jsonData.issueDate);
                        element.find("#verifyDelegateDetails").show();
                    }
                    else
                    {
                        toggleTheNICFieldsEditable(element, "", true);
                        current.data('nicVerified', "false");
                        current.siblings("#isNicVerified").prop('checked', false);
                        
                        var errorResponseModal = $('#errorResponseModal');
                        errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                        errorResponseModal.modal('show');
                        element.find("#verifyDelegateDetails").hide();
                    }
                }
                else
                {
                    toggleTheNICFieldsEditable(element, "", true);
                    current.data('nicVerified', "false");
                    current.siblings("#isNicVerified").prop('checked', false);
                    element.find("#verifyDelegateDetails").hide();
                    var errorResponseModal = $('#errorResponseModal');
                    errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                    errorResponseModal.modal('show');
                }
                toggleIdCopeFileSection(element);
                toggleFieldsOnValue(element);
                
                
            });
            
            
            
        }
        
        var preContactNICVerfication = function(element, current){
            var idType = element.find("#idType").val();
            var idNumber = element.find("#idNumber").val();
            var delegateDateofBirth = element.find("#delegateDateofBirth").val();

            current.parents('form').find('.inputValidationError').hide();

            if(!validateNICInput(idType,idNumber,delegateDateofBirth,element))
            {
                return false
            }
            
            verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data){
                if(data)
                {
                    var jsonData = JSON.parse(data);
                    if( jsonData.result && jsonData.result == "Valid Data")
                    {
                        current.data('nicVerified', "true");
                        current.siblings("#isNicVerified").prop('checked', true);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1FirstName"),jsonData.firstName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1LastName"),jsonData.lastName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateFullNameEnglish"),jsonData.fullName_EN);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateGender"),jsonData.gender);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1PassportNumber"),jsonData.idNumber);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1PassportExpiryDate"),jsonData.expiryDate);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1PassportIssueDate"),jsonData.issueDate);
                        setAndDisableFieldIfValueNotBlank(element.find("#qm1DateOfBirth"),delegateDateofBirth);
                        
                        element.find("#contactSection").show();
                        element.find("#contactDetails").show();
                                            
                    }
                    else
                    {
                        toggleTheNICFieldsEditable(element, "", true);
                        current.data('nicVerified', "false");
                        current.siblings("#isNicVerified").prop('checked', false);
                        
                        var errorResponseModal = $('#errorResponseModal');
                        errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                        errorResponseModal.modal('show');
                        element.find("#contactSection").hide();
                        element.find("#contactDetails").hide();
                    }
                }
                else
                {
                    toggleTheNICFieldsEditable(element, "", true);
                    current.data('nicVerified', "false");
                    current.siblings("#isNicVerified").prop('checked', false);
                    element.find("#verifyDelegateDetails").hide();
                    var errorResponseModal = $('#errorResponseModal');
                    errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                    errorResponseModal.modal('show');
                }
                toggleIdCopeFileSection(element);
                toggleFieldsOnValue(element);
                
                
            });
            
            
            
        }

        
        var preShareholderNICVerfication = function(element, current){
            var idType = element.find("#shareholderIdType").val();
            var idNumber = element.find("#shareholderIdNumber").val();
            var delegateDateofBirth = element.find("#shareholderDateofBirth").val();

            current.parents('form').find('.inputValidationError').hide();

            
            if(!validateShareholderNICInput(idType,idNumber,delegateDateofBirth,element))
            {
                return false
            }
            verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data){
                if(data)
                {
                    var jsonData = JSON.parse(data);
                    if( jsonData.result && jsonData.result == "Valid Data")
                    {
                        current.data('nicVerified', "true");
                        current.siblings("#isNicVerified").prop('checked', true);
                        element.find("#dataSection").show();
                        element.find("#attachmentSection").show();
                        
                     
                    	var passportFileAttachment = $("#passportFileAttachment");
                    	$("#passportFileAttachment").hide();
                    	passportFileAttachment.find('[name=passportFile]').removeClass('validate__file');
                    	
                    	
                    	
                        
                        setAndDisableFieldIfValueNotBlank(element.find("#dateOfBirth"),delegateDateofBirth);
                        setAndDisableFieldIfValueNotBlank(element.find("#passportExpiryDate"),jsonData.expiryDate);
                        setAndDisableFieldIfValueNotBlank(element.find("#passportIssueDate"),jsonData.issueDate);
                        setAndDisableFieldIfValueNotBlank(element.find("#passportNumber"),jsonData.idNumber);
                        setAndDisableFieldIfValueNotBlank(element.find("#firstNameArabic"),jsonData.firstName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#lastNameArabic"),jsonData.lastName_AR);
                        setAndDisableFieldIfValueNotBlank(element.find("#fullNameEnglish"),jsonData.fullName_EN);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateGender"),jsonData.gender);
                        setAndDisableFieldIfValueNotBlank(element.find("#delegateExpiryDate"),jsonData.expiryDate);
                        
                  
                        if(jsonData.idType == "1") {
                        	setAndDisableFieldIfValueNotBlank(element.find("#currentNationality"),"SA");
                        	setAndDisableFieldIfValueNotBlank(element.find("#country"),"SA");
                        }
                        
                      //select
                        if(jsonData.premium_Residency) {
                        	  setAndDisableFieldIfValueNotBlank(element.find("#premiumResident"),'yes');
                        }else {
                        	  setAndDisableFieldIfValueNotBlank(element.find("#premiumResident"),'no');
                        }
                      
                        if(jsonData.gender == "Male") {
                        	element.find("#personTitle").prop('checked', true);
                        	element.find("#organizationTitle").prop('checked', false);
                        	element.find("#organizationTitle").attr('disabled','disabled');
                        	element.find("#personTitle").removeAttr('disabled');
                        }else {
                        	element.find("#personTitle").prop('checked', false);
                        	element.find("#organizationTitle").prop('checked', true);
                        	element.find("#personTitle").attr('disabled','disabled');
                        	element.find("#organizationTitle").removeAttr('disabled');
                        	
                        	
                        }
                        
                        toggleShareholderIdCopeFileSection(element);
                    }
                    else
                    {
                    	
                        toggleTheNICFieldsEditable(element, "", true);
                        current.data('nicVerified', "false");
                        current.siblings("#isNicVerified").prop('checked', false);
                        
                        var errorResponseModal = $('#errorResponseModal');
                        errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                        errorResponseModal.modal('show');
                    }
                    
                    
                    
                }
                else
                {
                    toggleTheNICFieldsEditable(element, "", true);
                    current.data('nicVerified', "false");
                    current.siblings("#isNicVerified").prop('checked', false);
                    var errorResponseModal = $('#errorResponseModal');
                    errorResponseModal.find('.modal-description').text(getI18nText("general.error.nic"));
                    errorResponseModal.modal('show');
                    
                    
                }
           
              
                
            });
        }

        var setAndDisableFieldIfValueNotBlank = function(element, value){
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
                element.attr('disabled','disabled');
            }
        }

        var verifyUserDataWithNIC = function(idType, idNumber, delegateDateofBirth){
            return $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/nic", {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    //xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                method: 'POST',
                data: {"idType": idType,
                    "idNumber": idNumber,
                    "dob":delegateDateofBirth},
                success: function (data) {
                    //console.log("asd");
                },
                error: function (error) {
                    //console.log("erro");
                }
            });
        }

        var validateShareholderNICInput = function(idType,idNumber,delegateDateofBirth,element){
            if(!idType||!idNumber||!delegateDateofBirth){
                var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
                if(!idType){
                    element.find("#shareholderIdType").parent().addClass("has-error");
                    element.find("#shareholderIdType").parent().siblings(".help-block").text(errorMsg);
                }
                if(!idNumber){
                    element.find("#shareholderIdNumber").parent().addClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text(errorMsg);
                }
                if(!delegateDateofBirth){
                    element.find("#shareholderDateofBirth").parent().addClass("has-error");
                    element.find("#shareholderDateofBirth").parent().siblings(".help-block").text(errorMsg);
                }
                return false;
            }
            else{
                element.find("#shareholderIdType").parent().removeClass("has-error");
                element.find("#shareholderIdType").parent().siblings(".help-block").text("");
                element.find("#shareholderDateofBirth").parent().removeClass("has-error");
                element.find("#shareholderDateofBirth").parent().siblings(".help-block").text("");
                element.find("#shareholderIdNumber").parent().removeClass("has-error");
                element.find("#shareholderIdNumber").parent().siblings(".help-block").text("");
                var regex = new RegExp("^[12]{1}[0-9]{9}$");


                if((idType==="1" || idType==="2") && idNumber.length !== 10)
                {
                    element.find("#shareholderIdNumber").parent().addClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.10digit"));
                    return false;
                }
                else if(idType==="1" && idNumber[0] !=="1")
                {
                    element.find("#shareholderIdNumber").parent().addClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.startwith1"));
                    return false;
                }
                else if(idType==="2" && idNumber[0] !=="2")
                {
                    element.find("#shareholderIdNumber").parent().addClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.startwith2"));
                    return false;
                }
                else if((idType==="1" || idType==="2") && !regex.test(idNumber))
                {
                    element.find("#shareholderIdNumber").parent().addClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.digitonly"));
                    return false;
                }
                else
                {
                    element.find("#shareholderIdNumber").parent().removeClass("has-error");
                    element.find("#shareholderIdNumber").parent().siblings(".help-block").text("");
                }
            }
            return true;
        }  
       
       var validateNICInput = function(idType,idNumber,delegateDateofBirth,element){
            if(!idType||!idNumber||!delegateDateofBirth){
                var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
                if(!idType){
                    element.find("#idType").parent().addClass("has-error");
                    element.find("#idType").parent().siblings(".help-block").text(errorMsg);
                }
                if(!idNumber){
                    element.find("#idNumber").parent().addClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text(errorMsg);
                }
                if(!delegateDateofBirth){
                    element.find("#delegateDateofBirth").parent().addClass("has-error");
                    element.find("#delegateDateofBirth").parent().siblings(".help-block").text(errorMsg);
                }
                return false;
            }
            else{
                element.find("#idType").parent().removeClass("has-error");
                element.find("#idType").parent().siblings(".help-block").text("");
                element.find("#delegateDateofBirth").parent().removeClass("has-error");
                element.find("#delegateDateofBirth").parent().siblings(".help-block").text("");
                element.find("#idNumber").parent().removeClass("has-error");
                element.find("#idNumber").parent().siblings(".help-block").text("");
                var regex = new RegExp("^[12]{1}[0-9]{9}$");


                if((idType==="1" || idType==="2") && idNumber.length !== 10)
                {
                    element.find("#idNumber").parent().addClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.10digit"));
                    return false;
                }
                else if(idType==="1" && idNumber[0] !=="1")
                {
                    element.find("#idNumber").parent().addClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.startwith1"));
                    return false;
                }
                else if(idType==="2" && idNumber[0] !=="2")
                {
                    element.find("#idNumber").parent().addClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.startwith2"));
                    return false;
                }
                else if((idType==="1" || idType==="2") && !regex.test(idNumber))
                {
                    element.find("#idNumber").parent().addClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text(getI18nText("validation.shareholder.delegate.idNumber.digitonly"));
                    return false;
                }
                else
                {
                    element.find("#idNumber").parent().removeClass("has-error");
                    element.find("#idNumber").parent().siblings(".help-block").text("");
                }
            }
            return true;
        } 
    },

    loadIsicSections: function(isicSectionsElement) {
        var licenseType = isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') ? isicSectionsElement.parents('#shareholdersQM1').attr('data-licensetype') : 1;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicSectionsQDF/" + licenseType, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var previousIsicSectionsElement = isicSectionsElement.attr('data-value');
                isicSectionsElement.find("option").remove();
                isicSectionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicSectionsElement.append(new Option(currentValue.sectionDescription, currentValue.sectionNumber, false, false));
                });
                if(previousIsicSectionsElement) {
                    isicSectionsElement.val(previousIsicSectionsElement).trigger("blur").trigger('change');
                }  else {
                    isicSectionsElement.val(null);
                }
            }
        });
    },

    loadIsicDivisions: function(isicDivisionsElement, sectionId) {
        var licenseType = isicDivisionsElement.parents('#shareholdersQM1').attr('data-licensetype') ? isicDivisionsElement.parents('#shareholdersQM1').attr('data-licensetype') : 1;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicDivisionsQDF/" +
            licenseType + "/" + sectionId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var previousIsicDivisionsElement = isicDivisionsElement.attr('data-value');
                isicDivisionsElement.find("option").remove();
                isicDivisionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicDivisionsElement.append(new Option(currentValue.divisionDescription, currentValue.divisionNumber, false, false));
                });
                if(previousIsicDivisionsElement) {
                    isicDivisionsElement.val(previousIsicDivisionsElement).trigger("blur").trigger('change');
                } else {
                    isicDivisionsElement.val(null);
                }
            }
        });
    },

    bindTooltip: function () {
        if ($(".js-tip").length > 0) {
            this.tip();
        }
    },

    tip: function() {
        $('.js-tip').each(function(){
            var tiptrigger = $(this).data('tip-trigger') ? $(this).data('tip-trigger') : 'click click-outside';
            if(tiptrigger && tiptrigger.indexOf('click-outside') > 0) {
                $(this).data('trigger', 'click');
                $(this).data('hide-trigger','click-outside');
            } else {
                tiptrigger = false;
            }
            var htmlValue = $("#" + $(this).data('tip-id')).html();
            var tiptext = $(this).data('tip-title') !== undefined ?
                $(this).data('tip-title') : ($(this).data('tip-id') !== undefined && htmlValue ? htmlValue : false),
                tipPlace = $(this).data('tip-placement') ? $(this).data('tip-placement') : "top";

            var tipAutoSizeFuc = function (el) {
                var _this = $(el.target);
                if(el && !el.target && $(el).attr('aria-describedby')) {
                    _this = $(el);
                }

                if(_this.data('tip-width') === "auto") {
                    $("#" + _this.attr('aria-describedby')).addClass('tooltip_autosize');
                }
                if(_this.data('tip-class')) {
                    $("#" + _this.attr('aria-describedby')).find(".tooltip-inner").addClass(_this.data('tip-class'));

                }
                if(_this.data('hide-trigger') === 'click-outside') {
                    var _tipID = _this.attr('aria-describedby'),
                        _trigger = _this,
                        _shadowID = _tipID + '-shadow';
                    if($('.tooltip-clickshadow').length < 1){
                        $("#" + _tipID).before('<div class="tooltip-clickshadow"></div>');
                        $('.tooltip-clickshadow').click(function(){
                            $('.js-tip').tooltip('hide');
                            $('body > .tooltip-clickshadow').remove();
                        });
                    }
                    _this.on('hide.bs.tooltip', function () {
                        $('body > .tooltip-clickshadow').remove();
                    });
                    $("#" + _this.attr('aria-describedby') + '-shadow').click(function(){
                        _this.tooltip('hide');
                    });
                    $("#" + _this.attr('aria-describedby')).width('auto');
                }
                $("#" + _this.attr('aria-describedby') + ' .tooltip-list_collapsible .tooltip-listItem-header').click(function(){
                    if($(this).parent().hasClass('tooltip-listItem_expanded')) {
                        $(this).parent().removeClass('tooltip-listItem_expanded');
                        _this.tooltip('update');
                        return;
                    }
                    $(this).parents('.tooltip-list_collapsible').children('.tooltip-listItem_expanded').removeClass('tooltip-listItem_expanded');
                    $(this).parent().addClass('tooltip-listItem_expanded');
                    _this.tooltip('update');
                });
            };
            $(this).tooltip({
                html: true,
                title: tiptext,
                placement: tipPlace
            });

            $(this).on('inserted.bs.tooltip', tipAutoSizeFuc);
            $(this).on('hide.bs.tooltip', function () {
                $('body > .tooltip-clickshadow').remove();
            });
        });
    },

    loadNewFile: function(countryElement, newFileSection) {

        //var newFileSection = $("#newFileSection");
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/shareHolderCountryCheck", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var selectedCountry = countryElement.val();
                jsonData.forEach(function (currentValue) {
                    if(selectedCountry == currentValue.code){
                        newFileSection.show();
                        newFileSection.find('[name=companyMemoAssociationFile]').addClass('validate__file');
                    }
                });
            }
        });
    }
};
