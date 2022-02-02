SAGIA.licenseApplyShareholder = {
    _autoload: [
        "bindAll"
    ],

    //global variables
    addShareholderQM1NoShareholderSection: $('#addShareholderQM1NoShareholderSection'),
    sharehfolderQM1TableSection: $('#shareholderQM1TableSection'),
    addShareholderQM1ExistingSection: $('#addShareholderQM1ExistingSection'),
    addShareholderQM1NewSection: $('#addShareholderQM1NewSection'),
    addShareholderQM1NewPersonSection: $('#addShareholderQM1NewPersonSection'),
    addShareholderQM1NewOrganizationSection: $('#addShareholderQM1NewOrganizationSection'),
    shareholdersTableBody: $('#shareholderQM1TableSection .tableModule .tableModule-table .tableModule-body'),

    bindAll: function () {
        this.bindOpenExistingShareholderFormButton();
        SAGIA.licenseApplyShareholderCommons.getShareholderByEntityNumber();
        SAGIA.licenseApplyShareholderCommons.bindMofaNumberValidation();
        this.bindCancelButtonExistingShareholder();
        this.bindExistingShareholderFormSubmit();
        this.bindButtonAddExistingShareholder();
        this.bindOpenNewShareholderSectionButton();
        this.bindCancelButtonNewShareholder();
        this.enableValidateCr();
        this.bindNewShareholderFormButtonEvent();
        this.bindProfessionalLicenseValidateCrEvent();
        SAGIA.licenseApply.setMobileCode('#personShareholderForm #country',
            '#personShareholderForm #countryCodeForTelephone',
            '#personShareholderForm #countryCodeForMobile');
        SAGIA.licenseApply.setMobileCode('#organizationShareholderForm #companyCountry',
            '#organizationShareholderForm #companyCountryCodeForTelephone',
            '#organizationShareholderForm #companyCountryCodeForMobile');
        SAGIA.licenseApply.setMobileCode('#personShareholderForm #delegateCountry',
            '#personShareholderForm #delegateCountryCodeForTelephone',
            '#personShareholderForm #delegateCountryCodeForMobile');
        SAGIA.licenseApply.setMobileCode('#organizationShareholderForm #delegateCountry',
            '#organizationShareholderForm #delegateCountryCodeForTelephone',
            '#organizationShareholderForm #delegateCountryCodeForMobile');
        this.bindPersonIsDelegateEvent();
        this.bindPersonPremiumResidentChangeEvent();
        this.bindProfessionalLicenseChangeEvent();
        this.bindPersonDelegateYourselfEvent();
        this.bindButtonAddPersonShareholder();
        this.bindPersonShareholderFormSubmit();
        this.bindShareholderTableEditButtons();
        this.bindShareholderTableRemoveButtons();
        this.bindSectionChangeEvent();
        this.bindOrganizationIsDelegateEvent();
        this.bindButtonAddOrganizationShareholder();
        this.bindOrganizationShareholderFormSubmit();
       
        this.bindNextButtonLink();
        SAGIA.fileInput.init();
    },

    bindOpenExistingShareholderFormButton: function () {
        var self = this;
        var existingBtn = $("button.addExistingButton");

        existingBtn.on('click', function () {
            $(this).removeClass('btn-outline');
            self.getExistingShareholderForm();
        })
    },

    getExistingShareholderForm: function (code) {
        var self = this;
        var url = ACC.config.encodedContextPath + controllerUrl + '/existing-shareholder-form';

        if (code && code !== '' && code !== undefined) {
            url += "?code="+code;
        }

        $.ajax({
            url: url,
            method: "GET",
            success: function (data) {
                self.bindCallbackFunctionForImportedForm(data)
            },
            error: function (data) {
                console.error(data);
            }
        })
    },

    getPersonShareholderForm: function (code) {
        var self = this;
        var url = ACC.config.encodedContextPath + controllerUrl + '/person-shareholder-form';
        var hasCode = false;

        if (code && code !== '' && code !== undefined) {
            url += "?code="+code;
            hasCode = true;
        }

        $.ajax({
            url: url,
            method: "GET",
            success: function (data) {
                self.bindCallbackFunctionForImportedPersonForm(data,hasCode);
                if (hasCode) {
                    $('#addShareholderQM1NewSection [name=ShareholderRadioBox01][value=Person]').prop('checked', true);
                    $('#shareholderNewGlobalMessage').hide();
                    $('#dataSection').show();
                    self.addShareholderQM1NewSection.hide();
                    
                    //@todo call get data from NIC.
                }
            },
            error: function (data) {
                console.error('error');
            }
        })
    },

    getOrganizationShareholderForm: function (code) {
        var self = this;
        var url = ACC.config.encodedContextPath + controllerUrl + '/organization-shareholder-form';
        var hasCode = false;

        if (code && code !== '' && code !== undefined) {
            url += "?code="+code;
            hasCode = true;
        }

        $.ajax({
            url: url,
            method: "GET",
            success: function (data) {
                self.bindCallbackFunctionForImportedOrganizationForm(data,hasCode);
                if (hasCode) {
                    $('#addShareholderQM1NewSection [name=ShareholderRadioBox01][value=Organization]').prop('checked', true);
                    //$('#shareholderNewGlobalMessage').show();
                    self.addShareholderQM1NewSection.show();
                    $('#dataSectionCapital').show();
                    $('#dataSectionCompany').show();
                    
                    
                }
            },
            error: function (data) {
                console.error('error');
            }
        })
    },

    bindOpenNewShareholderSectionButton: function () {
        var self = this;
        var newButton = $('button.addNewButton');

        newButton.on('click', function () {
            $(this).removeClass('btn-outline');
            self.getNewShareholderSection();
        });
    },

    getNewShareholderSection: function () {
        this.addShareholderQM1NewSection.show();
        this.toggleShareholderTypeChoiceButtons(true);
    },

    bindCallbackFunctionForImportedForm: function (data) {
        $('.existingShareholdersForm-wrapper').html(data);
        SAGIA.licenseApplyShareholderCommons.bindSelect2ShareholderForms($('#existingShareholderForm'));
        SAGIA.licenseApplyShareholderCommons.loadShareholderQeemah1Data(function () {
            var entityNumber = $('#existingShareholderForm #existingShareholderEntityNumber');
            if (entityNumber.val() !== '') {
                entityNumber.trigger('change');
            }
        });

        this.addShareholderQM1NewSection.hide();
        this.addShareholderQM1NewPersonSection.hide();
        this.addShareholderQM1NewOrganizationSection.hide();
        this.addShareholderQM1ExistingSection.show();
        this.toggleShareholderTypeChoiceButtons(true);
        SAGIA.licenseApplyShareholderCommons.bindTooltip();
        SAGIA.licenseApply.bindInputValidation($('#existingShareholderForm'));
    },

    bindNextButtonLink: function () {
        $('#shareholdersNextButton').not(':disabled').on('click', function () {
            window.location.href = $('#shareholdersNextButton').attr('data-url');
        })
    },

    toggleShareholderTypeChoiceButtons: function (disable) {
        if (typeof disable === "boolean") {
            $('button.addExistingButton').prop('disabled', disable);
            $('button.addNewButton').prop('disabled', disable);
            $('button#shareholdersNextButton').prop('disabled', disable);
        }
    },

    bindSelectEventToSelect2Disabled: function (element) {
        element.on('change', function (e) {
            if ($(this).val() != '') {
                $(this).next().addClass("select2Container_selected");
            }
        });
    },

    bindCancelButtonExistingShareholder: function () {
        var self = this;
        $(document).on('click', "#addShareholderQM1ExistingSection .cancelButton", function () {
            $('button.addExistingButton').addClass('btn-outline');
            self.addShareholderQM1ExistingSection.hide();
            self.toggleShareholderTypeChoiceButtons(false);
        })
    },

    bindCancelButtonNewShareholder: function () {
        var self = this;
        $(document).on('click','#addShareholderQM1NewSection .cancelButton, ' +
            '#addShareholderQM1NewPersonSection .cancelButton, ' +
            '#addShareholderQM1NewOrganizationSection .cancelButton', function () {
            self.addShareholderQM1NewSection.hide();
            self.addShareholderQM1NewPersonSection.hide();
            self.addShareholderQM1NewOrganizationSection.hide();
            self.toggleShareholderTypeChoiceButtons(false);
            $('#addShareholderQM1NewSection [name=ShareholderRadioBox01]').prop('checked', false);
            $('#shareholderNewGlobalMessage').hide();
            $('button.addNewButton').addClass('btn-outline');
        })
    },

    bindNewShareholderFormButtonEvent: function () {
        var self = this;
        $('#addShareholderQM1NewSection input[name=ShareholderRadioBox01]').on('change', function () {
            $('#shareholderNewGlobalMessage').hide()
            if ($(this).filter(':checked').val() === "Person") {
                self.getPersonShareholderForm();
            } else if ($(this).filter(':checked').val() === "Organization") {
                self.getOrganizationShareholderForm();
            } else {
                console.error('Error binding event for new shareholder buttons.');
            }
        })
    },

    bindExistingShareholderFormSubmit: function () {
        var self = this;

        $(document).on('submit','#existingShareholderForm', function (e) {
            e.preventDefault();
            var csrfToken = "?CSRFToken="+$(this).find('[name=CSRFToken]').val();

            $(this).find(':input').prop('disabled', false);

            var formData = new FormData(this);

            $.ajax({
                url: ACC.config.encodedContextPath + controllerUrl + "/save-existing-shareholder"+csrfToken,
                data: formData,
                method: $(this).prop('method'),
                processData: false,
                contentType: false,
                success: function (rawData) {
                    var data = JSON.parse(rawData)
                    if (data.status) {
                        self.bindShowShareholdersTable(data);
                        self.addShareholderQM1ExistingSection.hide();
                        self.toggleShareholderTypeChoiceButtons(false);
                    } else {
                        if (data.hasOwnProperty("form")) {
                            self.bindCallbackFunctionForImportedForm(SAGIA.licenseApply.parseHtml(data.form));
                        }
                    }
                },
                error: function (data) {
                    console.error(data);
                }
            });
        });
    },

    bindButtonAddExistingShareholder: function () {
        var self = this;
        $("#addShareholderQM1ExistingSection #validateAddShareholder").on('click', function () {
            // if (SAGIA.licenseApply.checkSharePercentages($("#existingShareholderForm #existingShareholderSharesPercentage"))) {
                if (self.isValidExistingShareholderForm()) {
                    $("#existingShareholderForm").submit();
                }
            // }
        })
    },

    bindCallbackFunctionForImportedPersonForm: function (data,hascode) {
        $('#addShareholderQM1NewPersonSection').html(data);
        SAGIA.licenseApplyShareholderCommons.bindSelect2ShareholderForms($('#personShareholderForm'));
        SAGIA.licenseApplyShareholderCommons.loadShareholderQeemah1Data();
        this.addShareholderQM1NewOrganizationSection.hide();
        this.addShareholderQM1ExistingSection.hide();
        //this.addShareholderQM1NewSection.show();
        this.addShareholderQM1NewPersonSection.show();
        SAGIA.formElements.flatpickr($('.js-form-control_date'));
       
        SAGIA.licenseApplyShareholderCommons.bindDelegateEvents(hascode);
        if (hascode) {
        	$("#addShareholderQM1NewPersonSection #delegateSection").addClass('load-delegate');
            SAGIA.licenseApplyShareholderCommons.loadPersonShareholderOnUpdate();
        //    SAGIA.licenseApplyShareholderCommons.bindDelegateEvents(hascode);
        }//else {
             //SAGIA.licenseApplyShareholderCommons.bindDelegateEvents(hascode);
     //   }

        

        
        SAGIA.licenseApplyShareholderCommons.loadIdAndGenderDropDown();
        SAGIA.licenseApplyShareholderCommons.bindTooltip();
        $('#personShareholderForm [name=delegateInfo\\.delegateYourself]').trigger('change');
        SAGIA.licenseApply.bindInputValidation($('#personShareholderForm'));
        
        if (hascode) {
        	
           $("#addShareholderQM1NewPersonSection #verifyDetailsShow").trigger("blur").trigger('click');
        }
        
       
    },

    bindCallbackFunctionForImportedOrganizationForm: function (data,hascode) {
        $('#addShareholderQM1NewOrganizationSection').html(data);
        SAGIA.licenseApplyShareholderCommons.bindSelect2ShareholderForms($('#organizationShareholderForm'));
        SAGIA.licenseApplyShareholderCommons.loadShareholderQeemah1Data();
        //this.addShareholderQM1NewSection.show();
        this.addShareholderQM1NewPersonSection.hide();
        this.addShareholderQM1ExistingSection.hide();
        this.addShareholderQM1NewOrganizationSection.show();
        SAGIA.formElements.flatpickr($('.js-form-control_date'));
        
        
        SAGIA.licenseApplyShareholderCommons.bindDelegateEvents(hascode);
        if(hascode) {
        	$("#addShareholderQM1NewOrganizationSection #delegateSection").addClass('load-delegate');
            SAGIA.licenseApplyShareholderCommons.loadOrganizationShareholderOnUpdate(hascode);
     
         }else {
    
        	 
        	 
        	 $('#organizationShareholderForm [name=delegateInfo\\.delegate], ' +
             '#organizationShareholderForm [name=delegateInfo\\.delegateYourself]').trigger('change');
         }
        SAGIA.licenseApplyShareholderCommons.loadIdAndGenderDropDown();
        SAGIA.licenseApplyShareholderCommons.loadIsicSections($('#organizationShareholderForm #companySection'));
        SAGIA.licenseApplyShareholderCommons.bindTooltip();
        SAGIA.licenseApply.bindInputValidation($('#organizationShareholderForm'));
        
        if (hascode) {
        	
            $("#addShareholderQM1NewOrganizationSection #verifyDetailsShow").trigger("blur").trigger('click');
         }
       
    },

    bindSectionChangeEvent: function () {
        $(document).on('change', "#organizationShareholderForm #companySection", function() {
            var shareholdersQM1 = $("#shareholdersQM1");
            if($(this).val()) {
                SAGIA.licenseApplyShareholderCommons.loadIsicDivisions(shareholdersQM1.find("#companyDivision"), $(this).val());
            }
        });
    },

    bindPersonIsDelegateEvent: function () {
        this.bindPersonIsDelegateCleanUp();
        $(document).on('change', '#personShareholderForm [name=delegateInfo\\.delegate]', function () {
            if ($(this).filter(':checked').val() === 'true') {
                $('#personShareholderForm [id=delegateSection]').show();
            } else if ($(this).filter(':checked').val() === 'false') {
                $('#personShareholderForm [id=delegateSection]').hide();
                $('#personShareholderForm [id=delegateDetails]').hide();
            }
        })
    },
    
   

    bindPersonPremiumResidentChangeEvent: function () {
        var self = this;
        $(document).on('change', '#addShareholderQM1NewPersonSection #premiumResident', function () {
        	//var premiumResident = shareholdersQM1.find("#premiumResident"); //??? academicTitle - education
        	var premiumResident = $(this).val();
        	//var passportFileNameLabel = $("#addShareholderQM1NewPersonSection").find("#passportFileName").text();
        	if(premiumResident === "yes"){
        		$("#addShareholderQM1NewPersonSection label[for='passportFileName']").text(getI18nText("license.apply.premiumcopy"));
        	}else{
        		$("#addShareholderQM1NewPersonSection label[for='passportFileName']").text(getI18nText("licence.apply.passport"));
        	}
        });
    },
    
    bindProfessionalLicenseChangeEvent: function () {
        var self = this;
        $(document).on('change', '#personShareholderForm #professionalLicense', function () {
        	var professionalLicense = $(this).val();
        	var newProfessionalLicenseFileSection = $("#professionalLicenseFileAttachment");
        	if(professionalLicense === "yes"){
        		$("#professionalLicenseFileAttachment").show();
        		newProfessionalLicenseFileSection.find('[name=professionalLicenseFile]').addClass('validate__file');
        	}else{
        		$("#professionalLicenseFileAttachment").hide();
        		newProfessionalLicenseFileSection.find('[name=professionalLicenseFile]').removeClass('validate__file');
        	}
        });
        
        $(document).on('change', '#organizationShareholderForm #companyProfessionalLicense', function () {
        	var companyProfessionalLicense = $(this).val();
        	var newProfessionalLicenseFileSection = $("#companyProfessionalLicenseFileAttachment");
        	if(companyProfessionalLicense === "yes"){
        		$("#companyProfessionalLicenseFileAttachment").show();
        		newProfessionalLicenseFileSection.find('[name=companyProfessionalLicenseFile]').addClass('validate__file');
        	}else{
        		$("#companyProfessionalLicenseFileAttachment").hide();
        		newProfessionalLicenseFileSection.find('[name=companyProfessionalLicenseFile]').removeClass('validate__file');
        	}
        });
        
        $(document).on('change', '#existingShareholderForm #existingProfessionalLicense', function () {
        	var existingProfessionalLicense = $(this).val();
        	var newProfessionalLicenseFileSection = $("#existingProfessionalLicenseFileAttachment");
        	if(existingProfessionalLicense === "yes"){
        		$("#existingProfessionalLicenseFileAttachment").show();
        		newProfessionalLicenseFileSection.find('[name=existingProfessionalLicenseFile]').addClass('validate__file');
        	}else{
        		$("#existingProfessionalLicenseFileAttachment").hide();
        		newProfessionalLicenseFileSection.find('[name=existingProfessionalLicenseFile]').removeClass('validate__file');
        	}
        });
    },
    
    bindPersonDelegateYourselfEvent: function () {
        this.bindPersonDelegateYourselfCleanUp();

        $(document).on('change', '#personShareholderForm [name=delegateInfo\\.delegateYourself]', function () {
            if ($(this).filter(':checked').val() === 'false') {
                $('#personShareholderForm [id=delegateDetails]').show();
            } else if($(this).filter(':checked').val() === 'true') {
                $('#personShareholderForm [id=delegateDetails]').hide();
            }
        });
    },

    
    
    bindOrganizationIsDelegateEvent: function () {
        this.bindOrganizationIsDelegateCleanUp();

        $(document).on('change', '#organizationShareholderForm [name=delegateInfo\\.delegate]', function () {
            if ($(this).filter(':checked').val() === 'false') { 
            	 $('#organizationShareholderForm [id=delegateDetails]').hide();
            	 $('#organizationShareholderForm [id=verifyDelegateDetails]').hide();
            	 $('#organizationShareholderForm [id=verifyDelegateDetails]').hide();
            	 
            } else if($(this).filter(':checked').val() === 'true') {
               
                $('#organizationShareholderForm [id=delegateSection]').show();
                $('#organizationShareholderForm [id=showDelegateQuestion]').hide();
                $('#organizationShareholderForm [id=delegateDetails]').show();
                
                
            }
        });
    },

    bindOrganizationIsDelegateCleanUp: function () {
        var self = this;

        $(document).on('click', '#organizationShareholderForm [name=delegateInfo\\.delegateYourself]', function () {
            self.cleanUpDelegateInfo($('#organizationShareholderForm'));
        })
    },

    bindPersonDelegateYourselfCleanUp: function () {
        var self = this;

        $(document).on('click', '#personShareholderForm [name=delegateInfo\\.delegateYourself]', function () {
            self.cleanUpDelegateInfo($('#personShareholderForm'));
        })
    },

    bindPersonIsDelegateCleanUp: function () {
        var self = this;

        $(document).on('click', '#personShareholderForm [name=delegateInfo\\.delegate]', function () {
         //   self.cleanUpDelegateInfo($('#personShareholderForm'));
         //   $('#personShareholderForm [name=delegateInfo\\.delegateYourself]').prop('checked', false);
        })
    },

    cleanUpDelegateInfo: function (element) {
        element.find('[name=delegateInfo\\.delegateIdentityNumber]').val('');
        element.find('[name=delegateInfo\\.delegateIdentityType]').val('').trigger('change');
        element.find('[name=delegateInfo\\.delegateDateOfBirth]').val('');
        element.find('[name=delegateInfo\\.delegateDateOfBirth]').removeAttr('value');
        element.find('[name=delegateInfo\\.nicVerified]').prop('checked', false);
    },

    bindButtonAddPersonShareholder: function () {
        var self = this;
        $(document).on('click', '#addShareholderQM1NewPersonSection .addButton', function () {
            // if (SAGIA.licenseApply.checkSharePercentages($("#personShareholderForm #personSharesPercentage"))) {
                if (self.isValidPersonShareholderForm()) {
                    $('#personShareholderForm').submit();
                }
            // }
        })
    },

    bindPersonShareholderFormSubmit: function () {
        var self = this;

        $(document).on('submit','#personShareholderForm', function (e) {
            e.preventDefault();
            var csrfToken = "?CSRFToken="+$(this).find('[name=CSRFToken]').val();

            $(this).find(':input').prop('disabled', false);

            var formData = new FormData(this);

            $.ajax({
                url: ACC.config.encodedContextPath + controllerUrl + "/save-person-shareholder"+csrfToken,
                data: formData,
                method: $(this).prop('method'),
                processData: false,
                contentType: false,
                success: function (rawData) {
                    var data = JSON.parse(rawData)
                    if (data.status) {
                        self.bindShowShareholdersTable(data);
                        self.addShareholderQM1NewPersonSection.hide();
                        self.addShareholderQM1NewSection.hide();
                        self.toggleShareholderTypeChoiceButtons(false);
                        $('#addShareholderQM1NewSection [name=ShareholderRadioBox01]').prop('checked', false);
                        $('#shareholderNewGlobalMessage').hide()
                    } else {
                        if (data.hasOwnProperty("form")) {
                            self.bindCallbackFunctionForImportedPersonForm(SAGIA.licenseApply.parseHtml(data.form),false);
                        }
                    }
                },
                error: function (data) {
                    console.error(data);
                }
            });
        });
    },

    bindButtonAddOrganizationShareholder: function () {
        var self = this;
        $(document).on('click', '#addShareholderQM1NewOrganizationSection .addButton', function () {
            // if (SAGIA.licenseApply.checkSharePercentages($("#organizationShareholderForm"))) {
                if (self.isValidOrganizationShareholderForm()) {
                    $('#organizationShareholderForm').submit();
                }
            // }
        })
    },

    bindOrganizationShareholderFormSubmit: function () {
        var self = this;

        $(document).on('submit','#organizationShareholderForm', function (e) {
            e.preventDefault();
            var csrfToken = "?CSRFToken="+$(this).find('[name=CSRFToken]').val();

            $(this).find(':input').prop('disabled', false);

            var formData = new FormData(this);

            $.ajax({
                url: ACC.config.encodedContextPath + controllerUrl + "/save-organization-shareholder"+csrfToken,
                data: formData,
                method: $(this).prop('method'),
                processData: false,
                contentType: false,
                success: function (rawData) {
                    var data = JSON.parse(rawData);
                    if (data.status) {
                        self.bindShowShareholdersTable(data);
                        self.addShareholderQM1NewOrganizationSection.hide();
                        self.addShareholderQM1NewSection.hide();
                        self.toggleShareholderTypeChoiceButtons(false);
                        $('#addShareholderQM1NewSection [name=ShareholderRadioBox01]').prop('checked', false);
                        $('#shareholderNewGlobalMessage').hide()
                    } else {
                        if (data.hasOwnProperty("form")) {
                        	
                        	var idNumber = $('#addShareholderQM1NewOrganizationSection #companyRegistrationNumber').val();
                            self.bindCallbackFunctionForImportedOrganizationForm(SAGIA.licenseApply.parseHtml(data.form),idNumber !== '');
                            $('#addShareholderQM1NewOrganizationSection #dataSectionCapital').show();
                            $('#addShareholderQM1NewOrganizationSection #dataSectionCompany').show();
                        }
                    }
                },
                error: function (data) {
                    console.error(data);
                    $('#dataSectionCapital').show();
                    $('#dataSectionCompany').show();
                }
            });
        });
    },

    bindShareholderTableEditButtons: function () {
        var self = this;
        $(document).on('click', '#shareholderQM1TableSection .editButton', function () {
            var type = $(this).parents('tr').attr('data-type');
            var code = $(this).parents('tr').attr('data-id');
            $(this).parents('#shareholderQM1TableSection')
                .find('.addExistingButton, .addNewButton').prop('disabled', true);
            $('#shareholdersNextButton').prop('disabled', true);
            $(this).removeClass('btn-outline');

            if (code) {
                switch (type.toLowerCase()) {
                    case 'existing': {
                        self.getExistingShareholderForm(code);
                        break;
                    }
                    case 'person': {
                        self.getPersonShareholderForm(code);
                        break;
                    }
                    case 'organization': {
                        self.getOrganizationShareholderForm(code);
                        break;
                    }
                }
            }

        })
    },

    bindShareholderTableRemoveButtons: function () {
        var self = this;
        $(document).on('click', '#shareholderQM1TableSection .removeButton', function () {
            var code = $(this).parents('tr').attr('data-id');

            if (code) {
                self.removeShareholder(code)
            }
        });
    },

    removeShareholder: function (code) {
        var self = this;
        $.ajax({
            url: ACC.config.encodedContextPath + controllerUrl + "/remove-shareholder",
            method: 'GET',
            data: {
                "code": code
            },
            success: function (rawData) {
                var data = JSON.parse(rawData)
                if (data.status) {
                    self.bindShowShareholdersTable(data)
                }
            }
        });
    },

    bindShowShareholdersTable: function (data) {
        if (data.shareholdersCount && data.shareholdersCount > 0) {
            this.addShareholderQM1NoShareholderSection.hide();
            this.shareholdersTableBody.html(SAGIA.licenseApply.parseHtml(data.shareholdersTable));
           // this.shareholderQM1TableSection.show();
            $('#shareholderQM1TableSection').show();
        } else {
            this.addShareholderQM1NoShareholderSection.show();
            this.shareholdersTableBody.html(SAGIA.licenseApply.parseHtml(data.shareholdersTable));
           // this.shareholderQM1TableSection.hide();
            $('#shareholderQM1TableSection').hide();
        }
    },
    
    
    // CR Validation 
    
    

    
    professionalLicenseInfoYesEvent: function () {
        this.professionalLicenseCrSection.show();
        this.professionalLicenseCrSection.find("input").focus();
    },
    
    
    enableValidateCr: function (){
    	 $(document).on('keyup', "#inputCRNumber", function() {
            	var crValue = $('#inputCRNumber').val();

            	if (crValue.length != 10) {
            	 	$("#inputCRNumber-error").addClass("has-error").text(getI18nText("validation.crnumber"));
            	 	$("#load-investor").attr("disabled", true);
            	}
            	else {
            		$("#inputCRNumber-error").removeClass("has-error").text("");
            		$("#load-investor").attr("disabled", false);	
            	}
            });
    },

    
    
    bindProfessionalLicenseValidateCrEvent: function () {
       /* var self = this;
        if(self.licenseInformationSection.find("#professionalLicenseCrVerified").val() != undefined && self.licenseInformationSection.find("#professionalLicenseCrVerified").val() == 'true'){
        	self.basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").prop('readonly', true);
    		self.basicInformationExtendedSection.find("#basicInformationExtendedCapital").prop('readonly', true);	
        }
        
        $('#load-investor').on('click', function () {
        	self.loadInvestorByCrEvent($('#inputCRNumber').val());
        })*/
    	var self = this;
    	$(document).on('click', "#load-investor", function() {
    		self.loadInvestorByCrEvent($('#inputCRNumber').val());
    	})
    },
    
    loadInvestorByCrEvent: function(crNumber){
    	var self = this;
    	if(crNumber === "") {
            $(".valid-cr-fields").hide();
            $('.cr-validation').addClass('has-error');
            $('.cr-validation .help-block').html("<span>Please provide a valid CR Number</span>");
            self.resetInvestorEvent(crNumber);
        } else {
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/professional-license/" + crNumber, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    $("#inputCRNumber-error").removeClass("has-error").text("");
                    var jsonData = JSON.parse(data);
                    if(jsonData.error === "") {
                        $('.cr-validation').removeClass('has-error');
                        $('.cr-validation .help-block').empty();
                        self.loadInvestorCrResponseEvent(jsonData, crNumber);
                    } else {
                    	self.resetInvestorEvent(crNumber);
                        $(".valid-cr-fields").hide();
                        $('.cr-validation').addClass('has-error');
                        $('.cr-validation .help-block').html("<span>" + jsonData.error + "</span>");
                        $('#dataSectionAttachment').hide();
                    	$('#dataSectionCapital').hide();
                    	$('#dataSectionCompany').hide();
                    }
                },
                error: function() {
                	self.resetInvestorEvent(crNumber);
                    $("#inputCRNumber-error").addClass("has-error").text(getI18nText("general.error"));
                    $(".valid-cr-fields").hide();
                }
            });
        }
    },
    
    loadInvestorCrResponseEvent: function(data, crNumber) {
    	var self = this;
    	if(data.nameEnglish){
    		self.setAndDisableFieldIfValueNotBlank($("#organizationNameEnglish"),data.nameEnglish.replace(/[^a-zA-Z0-9 ]+/g, ""));
    	}
    	if(data.nameArabic){
    		self.setAndDisableFieldIfValueNotBlank($("#organizationNameArabic"),data.nameArabic.replace(/[^\u0621-\u064A\u0660-\u0669 ]+/g, ""));
    	}
    	if(data.capital){
    		self.setAndDisableFieldIfValueNotBlank($("#companyCapital"),data.capital.replace(/\D+/g, ""));
    	}
    	$("#professionalLicenseCrVerified").val(true);
    	self.setAndDisableFieldIfValueNotBlank($("#companyRegistrationNumber"),data.crNumber);
    	self.setAndDisableFieldIfValueNotBlank($("#companyCountryOfRegistration"),"SA");
    //	$('#dataSectionDelegate').show();
    	$('#dataSectionAttachment').show();
    	$('#dataSectionCapital').show();
    	$('#dataSectionCompany').show();
    	
    	
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
            element.attr('disabled','disabled');
        }
    },
    
    
    resetInvestorEvent: function(crNumber) {
    	
    	if($("#professionalLicenseCrVerified").val() == 'true'){
    		$("#organizationNameEnglish").val("").removeAttr('disabled');
    		$("#organizationNameArabic").val("").removeAttr('disabled');
    		$("#companyCapital").val("").prop('readonly', false);	
    		$("#professionalLicenseCrVerified").val(false);
    		$("#inputCRNumber").val(crNumber);
    		$("#companyRegistrationNumber").val("").removeAttr('disabled');
    		$("#companyRegistrationNumber").prop('readonly', false);
    		$("#companyCountryOfRegistration").val("").removeAttr('disabled');
    		$("#companyCapital").val("").removeAttr('disabled');
    		
    	}
    },
    
    // End CR Validation

    isValidPersonShareholderForm: function () {
        var valid = true;
        var form = $('#personShareholderForm');

        form.find('.form-group.has-error').removeClass('has-error');
        form.find('.help-block').html('');
        SAGIA.licenseApply.removeGlobalErrorMessage($('#shareholderNewGlobalMessage'));

        if (this.checkValidFormDelegateData(form) === false) {
            valid = false;
        }

        if (SAGIA.licenseApply.checkSharePercentages(form.find("#personSharesPercentage")) === false) {
            valid = false;
        }

        if (SAGIA.licenseApply.validateRadioMandatory(form.find('[name=shareHolderTitle]')) === false) {
            valid = false;
        }

        /*if (SAGIA.licenseApply.validateRadioMandatory(form.find('[name=delegateInfo\\.delegate]')) === false) {
            valid = false;
        }*/

        // delegateSection // skip this in case of CR validated.
        var isPassportIdShareholderType = form.find("#shareholderIdType").val() === '4'; 
        // delegateSection // skip this in case of CR validated.
        if (isPassportIdShareholderType &&  form.find('[name=delegateInfo\\.delegateYourself]').filter(':checked').val() === "false") {
            if (this.validateCommonFields(form, true) === false) {
                valid = false;
            }
        }
        
        var dob = form.find("#dateOfBirth").val();
        if(form.find("#shareholderIdType").val() != '1' && dob.length > 0){
        	var dobDate = Date.parse(dob);
             if (dobDate > Date.now()) {
            	form.find('[id=dateOfBirth]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.personShareholder.dateOfBirth.error"));
         		form.find('[id=dateOfBirth]').parents('.form-group').addClass('has-error');
         		valid = false;
             }
        }
        
        var passportIssue = form.find("#passportIssueDate").val();
        if(form.find("#shareholderIdType").val() != '1' && passportIssue.length > 0){
        	var passportIssueDate = Date.parse(passportIssue);
             if (passportIssueDate > Date.now()) {
            	form.find('[id=passportIssueDate]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.personShareholder.passportIssueDate.error"));
         		form.find('[id=passportIssueDate]').parents('.form-group').addClass('has-error');
         		valid = false;
             }
        }
        

        if (this.validateCommonFields(form, false) === false) {
            valid = false;
        }
        
        if(form.find('[id=postalCode]').val()){
        	if(form.find('[id=postalCode]').val().length != 5){
        		form.find('[id=postalCode]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		form.find('[id=postalCode]').parents('.form-group').addClass('has-error');
        		valid = false;
        	}
        }
        
        if(form.find('[id=poBox]').val()){
        	if(form.find('[id=poBox]').val().length != 5){
        		form.find('[id=poBox]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		form.find('[id=poBox]').parents('.form-group').addClass('has-error');
        		valid = false;
        	}
        }

        if (!valid) {
            SAGIA.licenseApply.globalErrorMessage($('#shareholderNewGlobalMessage'));
        }

        return valid;
    },

    validateCommonFields: function (form, isDelegate) {
        var delegateSelector = isDelegate === true ? "delegate-" : "";
        var valid = true;

        form.find('.validate__'+delegateSelector+'mandatory').each(function () {
            if (SAGIA.licenseApply.validateMandatoryAndGetMessage($(this)) === false) {
                valid = false;
            }
        });
        
        form.find('.validate__file').each(function () {
            if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                valid = false;
            }
        });

        var isPassportIdType = form.find("#shareholderIdType").val() === '4'; 
        
        if (isPassportIdType) {
        form.find('.validate__'+delegateSelector+'file').each(function () {
            if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                valid = false;
            }
        });
        }

        form.find('.validate__'+delegateSelector+'email').each(function () {
            if (SAGIA.licenseApply.validateEmailAddresses($(this)) === false) {
                valid = false;
            }
        });

        form.find('.validate__'+delegateSelector+'website').each(function () {
            if (SAGIA.licenseApply.validateWebsite($(this)) === false) {
                valid = false;
            }
        });
        
        if (isDelegate && form.find('[name=delegateInfo\\.delegateIdentityType]').val() === '4') {
           
            if (SAGIA.licenseApply.validateMandatoryAndGetMessage(form.find('[name=delegateInfo\\.delegatePostalCode]')) === false) {
                valid = false;
            }

            if (SAGIA.licenseApply.validateMandatoryAndGetMessage(form.find('[name=delegateInfo\\.delegatePoBox]')) === false) {
                valid = false;
            }
        }
       
         if (isDelegate && !form.find('[name=delegateInfo\\.nicVerified]').prop('checked')) {
        	 if (SAGIA.licenseApply.validateFileTypeAndSize(form.find('[name=saudiIdCopy]')) === false) {
                 valid = false;
             }
        }
       

        return valid;
    },

    isValidOrganizationShareholderForm: function () {
        var valid = true;
        var form = $('#organizationShareholderForm');

        form.find('.form-group.has-error').removeClass('has-error');
        form.find('.help-block').html('');
        SAGIA.licenseApply.removeGlobalErrorMessage($('#shareholderNewGlobalMessage'));

        if (this.checkValidFormDelegateData(form) === false) {
            valid = false;
        }

        if (SAGIA.licenseApply.checkSharePercentages(form.find("#companySharesPercentage")) === false) {
            valid = false;
        }
        
        
        // skip in case of CR validated.

       /* if (SAGIA.licenseApply.validateRadioMandatory(form.find('[name=delegateInfo\\.delegate]')) === false) {
            valid = false;
        }*/

        // delegateSection
        if (form.find('[name=delegateInfo\\.delegate]').filter(':checked').val() === "true") {
            if (this.validateCommonFields(form, true) === false) {
                valid = false;
            }
        }

        if (this.validateCommonFields(form, false) === false) {
            valid = false;
        }
        
        if(form.find('[id=companyPostalCode]').val()){
        	if(form.find('[id=companyPostalCode]').val().length != 5){
        		form.find('[id=companyPostalCode]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		form.find('[id=companyPostalCode]').parents('.form-group').addClass('has-error');
        		valid = false;
        	}
        }
        
        if(form.find('[id=companyPOBox]').val()){
        	if(form.find('[id=companyPOBox]').val().length != 5){
        		form.find('[id=companyPOBox]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		form.find('[id=companyPOBox]').parents('.form-group').addClass('has-error');
        		valid = false;
        	}
        }

        if (!valid) {
            SAGIA.licenseApply.globalErrorMessage($('#shareholderNewGlobalMessage'));
        }

        return valid;
    },

    isValidExistingShareholderForm: function () {
        var valid = true;
        var form = $('#existingShareholderForm');

        form.find('.form-group.has-error').removeClass('has-error');
        form.find('.help-block').html('');
        SAGIA.licenseApply.removeGlobalErrorMessage($('#shareholderExistingGlobalMessage'));

        if (SAGIA.licenseApply.checkSharePercentages(form.find("#existingShareholderSharesPercentage")) === false) {
            valid = false;
        }

        if (this.validateCommonFields(form, false) === false) {
            valid = false;
        }

        if (!valid) {
            SAGIA.licenseApply.globalErrorMessage($('#shareholderExistingGlobalMessage'));
        }

        return valid;
    },

    bindOrganizationCountryChangeEvent: function (hascode) {
        $(document).on('change', "#addShareholderQM1NewOrganizationSection #companyCountry", function() {
            var shareholdersQM1 = $("#shareholdersQM1");
            var newFileSection = $("#companyCheckFileAttachment");
            if($(this).val()) {
                newFileSection.hide();
                SAGIA.licenseApplyShareholderCommons.loadNewFile(shareholdersQM1.find("#companyCountry"), newFileSection);
                newFileSection.find('[name=companyMemoAssociationFile]').removeClass('validate__file');
            }  

                if($(this).val() == "SA")
                {
                	$("#addShareholderQM1NewOrganizationSection #showDelegateQuestion").hide();
                	$("#addShareholderQM1NewOrganizationSection #delegateSectionQuestion").hide();
                	
                  
                	$('#addShareholderQM1NewOrganizationSection #dataSectionDelegate').hide();
                	
                	if (!hascode) {
                		$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').hide();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').hide();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').hide();
                	}else {
                		$('#addShareholderQM1NewOrganizationSection #dataSectionAttachment').show();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCapital').show();
                    	$('#addShareholderQM1NewOrganizationSection #dataSectionCompany').show();
                	}
                	
                	
                	$("#addShareholderQM1NewOrganizationSection #inputCRNumber").prop('disabled', false);
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").hide();
                	
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").removeClass('validate__file');
                	
                	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").hide();
                 	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").removeClass('validate__file');                	
                 	
                 	$("#addShareholderQM1NewOrganizationSection #orgAttachmentTitle").hide();
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
                
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFileSection").show();
                	$("#addShareholderQM1NewOrganizationSection #companyRegistrationFile").addClass('validate__file');
                	
                	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFileSection").show();
                 	$("#addShareholderQM1NewOrganizationSection #companyFinancialStatementFile").addClass('validate__file');
                	       
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
            
        });
    },

    checkValidFormDelegateData: function (element) {
        var isDelegate = element.find('[name=delegateInfo\\.delegate]').filter(':checked').val() === 'true';
        if (element.attr('id') === 'personShareholderForm') {
            isDelegate = isDelegate && element.find('[name=delegateInfo\\.delegateYourself]').filter(':checked').val() === 'false';
        }
        var isNicVerified = element.find('#isNicVerified').prop('checked');
        var hiddenDelegateDetails = element.find("#verifyDelegateDetails").css('display') === 'none';
        var isNotPassportIdType = element.find("#idType").val() !== '4';
        var valid = true;

        if (isDelegate && !isNicVerified && hiddenDelegateDetails && isNotPassportIdType) {
            element.find('.inputValidationError').show();
            valid = false;
        } else {
            element.find('.inputValidationError').hide();
        }

        return valid;
    }
};



$(document).ready(function () {
	 $("#shareholderIdTypeSection").find("#shareholderIdType").change(function(){
         var type = "Person";
         var element = $("#delegate");
         if($("#shareholderIdType").val() === '4'){
        	 $('#addShareholderQM1NewPersonSection #mofaNumberSection').show();
         }else{
        	 $('#addShareholderQM1NewPersonSection #mofaNumberSection').hide();
         }
         onchangeOfIdType(element, type);
     });
});


var onchangeOfIdType = function(element, type) {

    var elementIdType = element.find("#idType");
    var elementDateofBirth = element.find("#passportExpiryDate");
    var delegateBirthDateId = element.find("#delegateBirthDateId");
    toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, elementDateofBirth);
    toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, delegateBirthDateId);
    

    resetDelegateDetails(element, type);
    element.find("#nicVerifyBtnSection").show();

};

var toggleUmmAlQuraOrNormalCalInDelegateSection = function(elementIdType, elementDateofBirth){
    if(elementIdType.val()==="1"){
        bindUmmAlQuraCal(elementDateofBirth);
    }
    else if(elementDateofBirth.hasClass("ummAlQura"))
    {
        bindNormalCal(elementDateofBirth);
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
//    altField: $("#delegateDateofBirthUmm"),
//    altFormat: 'yyyy-mm-dd',
        prevText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M15.758.374c.365-.365.955-.365 1.319 0 .364.364.363.954-.001 1.319l-7.789 10.31 7.789 10.305c.364.365.365.955.001 1.319s-.954.364-1.319 0l-9.108-11.624 9.108-11.629z\"/></svg>",
        nextText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M8.242.374c-.365-.365-.956-.365-1.319 0-.364.364-.363.954 0 1.319l7.79 10.311-7.79 10.305c-.364.365-.364.955 0 1.319s.955.364 1.319 0l9.107-11.624-9.107-11.63z\"/></svg>",
    });

    if (element.attr('value')) {
        element.val(element.attr('value'));
    }
};


var bindUmmAlQuraCal = function(element){
    element.addClass("ummAlQura");
  //  var flatPickrInstance = element.get(0)._flatpickr;
    var flatPickrInstance = element.get(0);
    
    if(flatPickrInstance === undefined) {
     
    }else {
    	 flatPickrInstance._flatpickr.destroy();
    }
    element.prop('readonly',true);

    bindCalendarsPickerToInput(element);
};
var bindCustomFlatpickr = function (element) {
    ACC.calendarcommons.bindFlatpickr(element);
};
var bindNormalCal = function(element){
    element.removeClass("ummAlQura");

    element.calendarsPicker('destroy');

    bindCustomFlatpickr(element);
};

