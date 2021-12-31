SAGIA.licenseApplyEntityInfo = {
    _autoload: [
        "bindAll"
    ],

    licenseInformationSection: $("#licenseInformationSection"),
	branchInformationSection: $("#branchInformationSection"),
    hasSagiaLicenseSection: $("#hasSagiaLicenseSection"),
    advanceLicenseNumberSection: $("#advanceLicenseNumberSection"),
    licenseTypeSection: $("#licenseTypeSection"),
    businessActivitiesSection: $("#businessActivitiesSection"),
    basicInformationExtendedSection: $("#basicInformationExtendedSection"),
    basicInformationSection: $("#basicInformationSection"),
    attachmentSection: $("#attachmentSection"),
    entrepreneurAttachment: $("#entrepreneurAttachment"),
	branchAttachment: $("#branchAttachment"),
	entityListedInStockMarketAttachment: $("#entityListedInStockMarketAttachment"),
	entityAssetAttachment: $("#entityAssetAttachment"),
	entityRevenueAttachment: $("#entityRevenueAttachment"),
	entityBranchAttachment: $("#entityBranchAttachment"),
    preApprovalNrAttachment: $("#preApprovalNrAttachment"),
    yearSection: $("#licenseYearSection"),
    hasAdvanceLicenseNumberSection: $("#hasAdvanceLicenseNumberSection"),
    isPreApprovalNumberSection: $("#isPreApprovalNumberSection"),
    entityForm: $("#entityForm"),
    licenseYearSection: $("#licenseYearSection"),
    hasProfessionalLicenseSection: $("#hasProfessionalLicenseSection"),
    professionalLicenseCrSection: $("#professionalLicenseCrSection"),
    pageloaded:false,
    activities:[],

    bindAll: function () {
    	this.bindGCCNationalityEvents();
        this.bindAdvanceLicenseNrEvents();
        this.bindPreApprovalNumberEvents();
        this.bindLicenseTypeEvents();
        this.bindLicenseInformationEvents();
		this.bindBranchInformationEvents();
		this.bindEntityListedInStockMarketEvents();
		this.bindEntityAssetEvents();
		this.bindEntityRevenueEvents();
		this.bindEntityBranchesEvents();
        this.enableValidateCr();
        //this.professionalLicenseInfoYesEvent();
        this.bindBasicInformationExtendedEvents();
        this.bindProfessionalLicenseValidateCrEvent();
        this.bindFormSubmitEvent();
		this.bindRhqRegionInformationEvents();
		this.bindRhqCountryInformationEvents();
        SAGIA.licenseApply.setMobileCode('#basicInformationExtendedSection #basicInformationExtendedCountry',
            "#basicInformationExtendedCountryCodeForTelephone",
            "#basicInformationExtendedCountryCodeForMobilePhone");
        SAGIA.licenseApply.bindInputValidation($('#entityForm'));

        if ($('[name=hasGCCNationality]').length > 0) {
        	 //after load page GCCNationality will be always set as NO, to avoid multiple redirection when user click on No and click on the back button  
        	this.entityForm.find("#hasGCCNationalityNO").trigger("click");
            $('[name=hasGCCNationality]').trigger('change');
        }

        if ($('[name=hasAdvanceLicenseNr]').filter(':checked').val()) {
            $('#spinnerMainDiv').removeClass('hidden');
            setTimeout(function () {
                $('[name=hasAdvanceLicenseNr]').filter(':checked').trigger('change');
                $('#spinnerMainDiv').addClass('hidden');
            }, 300)
        }
        
        if ($('[name=isPreApprovalNumber]').filter(':checked').val() == 'yes') {
            $('#spinnerMainDiv').removeClass('hidden');
            setTimeout(function () {
                $('[name=isPreApprovalNumber]').filter(':checked').trigger('change');
                $('#spinnerMainDiv').addClass('hidden');
            }, 300)
        }
        
        if ($('[name=hasProfessionalLicenseCr]').filter(':checked').val() == 'yes') {
            $('#spinnerMainDiv').removeClass('hidden');
            setTimeout(function () {
                $('[name=hasProfessionalLicenseCr]').filter(':checked').trigger('change');
                $('#spinnerMainDiv').addClass('hidden');
            }, 300)
        }
    },
    
    bindGCCNationalityEvents: function () {
        var self = this;

        this.entityForm.find('input[name=hasGCCNationality]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.gCCNationalityNoEvent($(this));
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.gCCNationalityYesEvent();
            }
        });
    },

    gCCNationalityYesEvent: function () {
        window.location.href = "https://mci.gov.sa/";
    },

    gCCNationalityNoEvent: function (element) {
        this.removeBorderColorFromRadioButtons(element);
        this.advanceLicenseNumberSection.show();
        // this.advanceLicenseNumberSection.find("input").prop("checked", null);
    },

    bindAdvanceLicenseNrEvents: function () {
        var self = this;
        this.entityForm.find('input[name=hasAdvanceLicenseNr]').on('change', function (){

        	if(!self.pageloaded)
    		{
        		self.switchToQeemah1();
        		self.pageloaded = true;
    		}
        	
            if ($(this).filter(':checked').val() === 'no') {
                self.advanceLicenseNrNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.advanceLicenseNrYesEvent();
            }
        });
    },

    advanceLicenseNrNoEvent: function () {
        this.hasAdvanceLicenseNumberSection.hide();
        this.hasAdvanceLicenseNumberSection.find("input").val("");
    },

    advanceLicenseNrYesEvent: function () {
        this.hasAdvanceLicenseNumberSection.show();
        this.hasAdvanceLicenseNumberSection.find("input").focus();
        // this.licenseTypeSection.find('select[name=activity]').val('').trigger('change');
    },
    
    bindPreApprovalNumberEvents: function () {
        var self = this;
        this.entityForm.find('input[name=isPreApprovalNumber]').on('change', function (){

        	if(!self.pageloaded)
    		{
        		self.switchToQeemah1();
        		self.pageloaded = true;
    		}
        	
            if ($(this).filter(':checked').val() === 'no') {
                self.preApprovalNumberNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.preApprovalNumberYesEvent();
            }
        });
    },

    preApprovalNumberNoEvent: function () {
        this.isPreApprovalNumberSection.hide();
        this.preApprovalNrAttachment.hide();
        this.isPreApprovalNumberSection.find("input").val("");
        if ($('[name=isEntrepreneur]').filter(':checked').val() == 'yes'){
        	this.attachmentSection.show();
        	this.entrepreneurAttachment.show();
        }else{
        	this.attachmentSection.hide();
			this.branchAttachment.hide();
			this.entityListedInStockMarketAttachment.hide();
			this.entityAssetAttachment.hide();
			this.entityRevenueAttachment.hide();
			this.entityBranchAttachment.hide();
        }
        //this.preApprovalNrAttachmentSection.hide();
    },

    preApprovalNumberYesEvent: function () {
    	this.attachmentSection.show();
        this.isPreApprovalNumberSection.show();
        this.isPreApprovalNumberSection.find("input").focus();
        this.preApprovalNrAttachment.show();
    },

	bindRhqRegionInformationEvents: function () {
        var self = this;

        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/rhqRegions", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var branchInformationRhqRegionsSection = self.branchInformationSection.find("#branchInformationRhqRegionsSection");
                branchInformationRhqRegionsSection.find("option").remove();
                //branchInformationRhqRegionsSection.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    branchInformationRhqRegionsSection.append(new Option(currentValue.regionText, currentValue.region, false, false));
                });

                self.updateSelectValues(self.branchInformationSection.find("#branchInformationRhqRegionsSection"));
            }
        });
    },
bindRhqCountryInformationEvents: function () {
        var self = this;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var countries = self.branchInformationSection.find("#branchInformationRhqCountry");
                countries.find("option").remove();
               // countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                //countries.attr("disabled", true);
                //countries.val("SA").trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded
			}
		});
	},

    bindLicenseTypeEvents: function () {
        var self = this;

        self.licenseTypeSection.find('#licenseTypes').on('change', function () {
            // Clean activity arrays when changing licenseTypes. On the first print, they'll be imported on functions ahead.
        	if($(this).val() == "9"){
        		self.licenseInformationSection.show();
				self.branchInformationSection.hide();
				self.branchAttachment.hide();
				self.entityListedInStockMarketAttachment.hide();
                self.entityAssetAttachment.hide();
                self.entityRevenueAttachment.hide();
                self.entityBranchAttachment.hide();
				self.attachmentSection.hide();
        		self.hasProfessionalLicenseSection.show();
        		if ($('[name=hasProfessionalLicenseCr]').filter(':checked').val() == 'yes') {
                    $('#spinnerMainDiv').removeClass('hidden');
                    setTimeout(function () {
                        $('[name=hasProfessionalLicenseCr]').filter(':checked').trigger('change');
                        $('#spinnerMainDiv').addClass('hidden');
                    }, 300)
                }
        		self.loadProfessionalLicenseLegalStatus();
        		if (self.licenseInformationSection.find('input[name=isEntrepreneur]').val() === 'no') {
                    //self.licenseInformationNoEvent();
                } else if (self.licenseInformationSection.find('input[name=isEntrepreneur]').val() === 'yes') {
                    //self.licenseInformationYesEvent();
                }
        		//self.loadYearsDropDown();
        		self.resetRHQActivities();
 				var legalStatus = self.basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
 				legalStatus.attr("disabled", false);
				self.licenseInformationSection.find('input[name=isEntrepreneur]').prop("disabled", false);
				self.licenseInformationSection.find('input[name=isPreApprovalNumber]').prop("disabled", false);
        	}else if($(this).val() == "11"){
        		//self.licenseInformationSection.find('input[name=isEntrepreneur]').val("no").trigger('change');
        		//self.licenseInformationSection.find('input[name=isPreApprovalNumber]').val("no").trigger('change');
				//self.attachmentSection.show();
       			 //self.branchAttachment.show();
				//self.bindRHQCountriesLimitEvent();
				self.licenseInformationSection.show();
        		self.preApprovalNumberNoEvent();
        		self.hasProfessionalLicenseSection.hide();
        		self.professionalLicenseCrSection.hide();
        		self.professionalLicenseInfoNoEvent();
        		//self.loadYearsDropDownForRHQ();
				//$("#licenseInformationSection").find("#isPreApprovalNumber").prop("disabled", true);
				//$("#licenseInformationSection").find("#isEntrepreneur").prop("disabled", true);
				//self.licenseInformationSection.find("#isEntrepreneur").val("Yes").prop("disabled", true).trigger('change');
				self.licenseInformationSection.find('input[name=isEntrepreneur]').prop("disabled", true);
				self.licenseInformationSection.find('input[name=isPreApprovalNumber]').prop("disabled", true);
				$('#isMoreThan2BranchYES').prop('checked',true);
				//$("#attachmentSection").show();
				//$("#branchAttachment").show();
				self.branchInformationSection.show();
				self.branchInformationYesEvent();
				self.licenseInformationNoEvent();
        		self.setRHQActivities();
				self.setRHQLegalStatus();
				self.entrepreneurAttachment.hide();
        	}else{
        		//self.licenseInformationSection.find('input[name=isEntrepreneur]').prop("disabled", false);
        		//self.licenseInformationSection.find('input[name=isPreApprovalNumber]').prop("disabled", false);

        		self.licenseInformationSection.show();
				self.branchInformationSection.hide();
				self.attachmentSection.hide();
				self.branchAttachment.hide();
				self.entityListedInStockMarketAttachment.hide();
                self.entityAssetAttachment.hide();
                self.entityRevenueAttachment.hide();
                self.entityBranchAttachment.hide();
        		self.hasProfessionalLicenseSection.hide();
        		self.professionalLicenseCrSection.hide();
        		self.resetInvestorEvent("");
        		//self.professionalLicenseCrSection.find("#inputCRNumber").val("");
        		//self.professionalLicenseCrSection.find("#professionalLicenseCrVerified").val(false);
        		self.loadProfessionalLicenseLegalStatus();
        		//self.loadYearsDropDown();
        		if (self.licenseInformationSection.find('input[name=isEntrepreneur]').val() === 'no') {
                    //self.licenseInformationNoEvent();
//					self.entrepreneurAttachment.hide();
//					self.attachmentSection.hide();
                } else if (self.licenseInformationSection.find('input[name=isEntrepreneur]').val() === 'yes') {
                    //self.licenseInformationYesEvent();
//					self.entrepreneurAttachment.show();
                }
        		self.resetRHQActivities();
				var legalStatus = self.basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
 				legalStatus.attr("disabled", false);
				self.licenseInformationSection.find('input[name=isEntrepreneur]').prop("disabled", false);
				self.licenseInformationSection.find('input[name=isPreApprovalNumber]').prop("disabled", false);
        	}
        	
            self.activities = [];
            SAGIA.license.businessActivities.newActivities = [];

            $(this).parent().removeClass('has-error');
            self.businessActivitiesSection.show();
            SAGIA.license.businessActivities.clearAll();
            SAGIA.license.businessActivities.setShowAttachments(false);
            if(!self.checkBusinessActivitiesTemporaryContract($(this).val()))
            {
            	self.updateActivities();
                var draftActivities = self.activities;
                if(draftActivities){
	                draftActivities.forEach(function (draftActivity) {
	                    draftActivity.id = draftActivity.activityId;
	                });
                }

                SAGIA.license.businessActivities.setLicenseType(self.licenseTypeSection.find('#licenseTypes').val(),
                        $(this).find(":selected").text(),draftActivities);
            }
        })
    },
	

    setRHQActivities: function () {
    	//var isEntrepreneur = $('[name=isEntrepreneur]').filter(':checked').val();
    	//var isPreApprovalNumber = $('[name=isPreApprovalNumber]').filter(':checked').val();
    	var self = this;
		this.setAttachmentTypeShow();
    	self.loadYearsDropDownForRHQ();
	//	this.attachmentsOnLoad();
		
		if(listOfRhqCountriesInJS != ""){
			var selectedCountriesSplit1 = listOfRhqCountriesInJS.split("[");
			var selectedCountriesSplit2 = selectedCountriesSplit1[1].split("]");
			var selectedCountries = selectedCountriesSplit2[0];
			$.each(selectedCountries.split(","), function(i ,e){
				$("#branchInformationRhqCountry option[value='" + e.trimLeft() + "']").prop("selected", true);
			});
		}
    },

	attachmentsOnLoad: function(){

	},

 setAttachmentTypeShow: function () {
//		let showAttachment=false;
//       if ($('[name=isMoreThan2Branch]').filter(':checked').val() == 'yes'){
//			showAttachment=true;
//        	this.branchAttachment.show();
//	
//        }else{
//			this.entrepreneurAttachment.hide();
//			this.entityListedInStockMarketAttachment.hide();
//			this.entityAssetAttachment.hide();
//			this.entityRevenueAttachment.hide();
//			this.entityBranchAttachment.hide();
//        }
//
//if ($('[name=isEntityListedInStockMarket]').filter(':checked').val() == 'yes'){
//		showAttachment=true;
//       	this.entityListedInStockMarketAttachment.show();
//      }else{
//			this.branchAttachment.hide();
//			this.entrepreneurAttachment.hide();
//			this.entityAssetAttachment.hide();
//			this.entityRevenueAttachment.hide();
//			this.entityBranchAttachment.hide();
//			
//        }
//
//if ($('[name=isEntityRevenueMoreThanThreshold]').filter(':checked').val() == 'yes'){
//			showAttachment=true;
//        	this.entityRevenueAttachment.show();
//        }else{
//			this.branchAttachment.hide();
//			this.entityListedInStockMarketAttachment.hide();
//			this.entityAssetAttachment.hide();
//			this.entrepreneurAttachment.hide();
//			this.entityBranchAttachment.hide();
//        }
//
//if ($('[name=isEntityAssetMoreThanThreshold]').filter(':checked').val() == 'yes'){
//	showAttachment=true;
//        	this.entityAssetAttachment.show();
//        }else{
//			this.branchAttachment.hide();
//			this.entityListedInStockMarketAttachment.hide();
//			this.entrepreneurAttachment.hide();
//			this.entityRevenueAttachment.hide();
//			this.entityBranchAttachment.hide();
//        }
//
//if ($('[name=isMoreThan6Branch]').filter(':checked').val() == 'yes'){
//			showAttachment=true;
//        	this.entityBranchAttachment.show();
//        }else{
//			this.branchAttachment.hide();
//			this.entityListedInStockMarketAttachment.hide();
//			this.entityAssetAttachment.hide();
//			this.entityRevenueAttachment.hide();
//			this.entrepreneurAttachment.hide();
//        }
//
//		if(showAttachment=true)
//		{
//			console.log("Attachment true")
//			this.attachmentSection.show()
//		}
//		else
//		{
//			this.attachmentSection.hide()
//		}
    },


 setRHQLegalStatus: function () 
 {
	 var self = this;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1",
		{
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
				 var legalStatus = self.basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                jsonData.legalStatus.forEach(function (currentValue) {
                    legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                });
                legalStatus.attr("disabled", true);
                legalStatus.val("BRFC").trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded

		}});
	},
    resetRHQActivities: function () {
    	var isEntrepreneur = $('[name=isEntrepreneur]').filter(':checked').val();
    	var isPreApprovalNumber = $('[name=isPreApprovalNumber]').filter(':checked').val();
    	var self = this;
    	
    	if(isEntrepreneur === 'yes'){
    		self.loadYearsDropDownForEntrepreneur();
    		self.licenseYearSection.find("#licenseYear").val("1").prop("disabled", true).trigger('change');
    	}else{
    		self.loadYearsDropDown();
    	}
		self.branchInformationSection.find('input[name=isMoreThan2Branch]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityListedInStockMarket]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityRevenueMoreThanThreshold]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityAssetMoreThanThreshold]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isMoreThan6Branch]').prop("disabled", true);
		self.branchInformationSection.find('select[id=branchInformationRhqRegionsSection]').clearInputs();
		self.branchInformationSection.find('select[id=branchInformationRhqCountry]').clearInputs();
    },
    updateActivities: function () {
        var element = $('.businessActivitiesJsonInputs');
        var businessActivities = element.find('input[name=businessActivities]');
        var self = this;

        if (!element.hasClass('imported') && businessActivities.length > 0) {
            businessActivities.each(function () {
                var json = $(this).val().replace(/'/g, '\"');
                if (self.isValidJsonString(json)) {
                    //SAGIA.license.businessActivities.selectedActivities.push(JSON.parse(json));
                    //SAGIA.license.businessActivities.existingActivities.push(JSON.parse(json));
                    //SAGIA.license.businessActivities.newActivities.push(JSON.parse(json));
                	self.activities.push(JSON.parse(json));
                }
            });

            element.addClass('imported');
            element.html('');

           // SAGIA.license.apply.updateIsicTable();
           // $('#noBusinessActivitiesSelected').hide();
        } else {
            var isicErrorMessageInput = $('#entityForm input[name=isicErrorMessage]');

            if (isicErrorMessageInput.length) {
                $('#noBusinessActivitiesSelected .help-block').text(getI18nText("business.activities.mandatory")).css({"color": "red"});
                //$('#noBusinessActivitiesSelected .help-block').html(isicErrorMessageInput.val());
            }
        }
    },

    isValidJsonString: function (str) {
        try {
            JSON.parse(str);
        } catch (e) {
            return false;
        }
        return true;
    },

    bindLicenseInformationEvents: function () {
        var self = this;

        self.licenseInformationSection.find('input[name=isEntrepreneur]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.licenseInformationNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.licenseInformationYesEvent();
            }
        });
        
        self.licenseInformationSection.find('input[name=hasProfessionalLicenseCr]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.professionalLicenseInfoNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.professionalLicenseInfoYesEvent();
            }
        });
    },
    professionalLicenseInfoNoEvent: function () {
        this.professionalLicenseCrSection.hide();
        this.resetInvestorEvent("");
    },
    
    professionalLicenseInfoYesEvent: function () {
        this.professionalLicenseCrSection.show();
        this.professionalLicenseCrSection.find("input").focus();
    },
    
    enableValidateCr: function (){
    	$('#inputCRNumber').on('keyup', function() {
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

    licenseInformationNoEvent: function () {
        this.attachmentSection.hide();
		this.branchAttachment.hide();
		this.entityListedInStockMarketAttachment.hide();
        this.entityAssetAttachment.hide();
        this.entityRevenueAttachment.hide();
        this.entityBranchAttachment.hide();
        this.entrepreneurAttachment.hide();
        if ($('[name=isPreApprovalNumber]').filter(':checked').val() == 'yes'){
        	this.attachmentSection.show();
        	this.preApprovalNrAttachment.show();
        }else{
        	this.attachmentSection.hide();
			this.branchAttachment.hide();
			this.entityListedInStockMarketAttachment.hide();
            this.entityAssetAttachment.hide();
            this.entityRevenueAttachment.hide();
            this.entityBranchAttachment.hide();
        }
//bqureshi
if($("#licenseTypes").val() === "11"){
		this.branchInformationSection.find('input[name=isMoreThan2Branch]').prop("disabled", false);
		this.branchInformationSection.find('input[name=isEntityListedInStockMarket]').prop("disabled", false);
		this.branchInformationSection.find('input[name=isEntityRevenueMoreThanThreshold]').prop("disabled", false);
		this.branchInformationSection.find('input[name=isEntityAssetMoreThanThreshold]').prop("disabled", false);
		this.branchInformationSection.find('input[name=isMoreThan6Branch]').prop("disabled", false);
		if ($('[name=isMoreThan2Branch]').filter(':checked').val() == 'yes'){
        	this.branchAttachment.show();
        }
		if ($('[name=isEntityListedInStockMarket]').filter(':checked').val() == 'yes'){
       		this.entityListedInStockMarketAttachment.show();
      	}
		if ($('[name=isEntityRevenueMoreThanThreshold]').filter(':checked').val() == 'yes'){
        	this.entityRevenueAttachment.show();
        }

		if ($('[name=isEntityAssetMoreThanThreshold]').filter(':checked').val() == 'yes'){
        	this.entityAssetAttachment.show();
        }

		if ($('[name=isMoreThan6Branch]').filter(':checked').val() == 'yes'){
        	this.entityBranchAttachment.show();
        }
		this.attachmentSection.show();
       	this.loadYearsDropDownForRHQ();
		
	}else{
        	this.loadYearsDropDown();
			this.attachmentSection.hide();
	}
//bqureshi		
        $("#licenseYearSection").find("#licenseYear").prop("disabled", false);
    },

    licenseInformationYesEvent: function () {
        this.attachmentSection.show();
        this.entrepreneurAttachment.show();
        this.loadYearsDropDownForEntrepreneur();
        this.licenseYearSection.find("#licenseYear").val("1").prop("disabled", true).trigger('change');
    },
    
    bindProfessionalLicenseValidateCrEvent: function () {
        var self = this;
        if(self.licenseInformationSection.find("#professionalLicenseCrVerified").val() != undefined && self.licenseInformationSection.find("#professionalLicenseCrVerified").val() == 'true'){
        	self.basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").prop('readonly', true);
    		self.basicInformationExtendedSection.find("#basicInformationExtendedCapital").prop('readonly', true);	
        }
        
        $('#load-investor').on('click', function () {
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
    		self.basicInformationExtendedSection.find("#basicInformationExtendedEntityName").val(data.nameEnglish.replace(/[^a-zA-Z0-9 ]+/g, "")).prop('readonly', true);
    	}
    	if(data.nameArabic){
    		self.basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").val(data.nameArabic.replace(/[^\u0621-\u064A\u0660-\u0669 ]+/g, "")).prop('readonly', true);
    	}
    	if(data.capital){
    		self.basicInformationExtendedSection.find("#basicInformationExtendedCapital").val(data.capital.replace(/\D+/g, "")).prop('readonly', true);	
    	}
    	self.licenseInformationSection.find("#professionalLicenseCrVerified").val(true);
    	self.licenseInformationSection.find("#inputCRNumber").val(crNumber);
    },
    
    resetInvestorEvent: function(crNumber) {
    	var self = this;
    	if(self.licenseInformationSection.find("#professionalLicenseCrVerified").val() == 'true'){
    		self.basicInformationExtendedSection.find("#basicInformationExtendedEntityName").val("").prop('readonly', false);
    		self.basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").val("").prop('readonly', false);
    		self.basicInformationExtendedSection.find("#basicInformationExtendedCapital").val("").prop('readonly', false);	
    		self.licenseInformationSection.find("#professionalLicenseCrVerified").val(false);
    		self.licenseInformationSection.find("#inputCRNumber").val(crNumber);
    	}
    },

    bindBasicInformationExtendedEvents: function () {
        var self = this;

        this.basicInformationExtendedSection.find("#basicInformationExtendedRegion").on("change", function () {
            self.loadBasicInformationQeemah1Cities($(this).val());
        });
    },

    bindFormSubmitEvent: function () {
        var self = this;

        $('#entityForm .entity-info-submit').on('click', function () {
            $(this).prop('disabled', true);

            if (!self.isValidEntityForm()) {
                $(this).prop('disabled', false);
                return false;
            }

            $('.businessActivitiesJsonInputs').html('');

            if ($('#entityForm #licenseTypes').val() !== "6") {
                if (SAGIA.license.businessActivities.selectedActivities.length > 0) {
                    $.each(SAGIA.license.businessActivities.selectedActivities, function (index, value) {
                        var element = $('<input/>').prop('type', 'hidden').prop('name', 'businessActivities').val(JSON.stringify(value).replace(/"/g, "'"));
                        $('#entityForm .businessActivitiesJsonInputs').append(element);
                    });

                    var isicCounter = $('<input/>').prop('type', 'hidden').prop('name', 'businessActivitiesCounter').val(SAGIA.license.businessActivities.selectedActivities.length);
                    $('#entityForm .businessActivitiesJsonInputs').append(isicCounter);

                } else {
                    $('#noBusinessActivitiesSelected .help-block').text(getI18nText("business.activities.mandatory")).css({"color": "red"});
                    $(this).prop('disabled', false);
                    return false;
                }
            } else {
                if (!$('#entityForm #temporaryLicenseTextBoxContent').val()) {
                    $('#temporaryLicenseTextBox .help-block').text(getI18nText("validation.purpose.temporary.certificate"));
                    $(this).prop('disabled', false);
                    return false;
                }
            }

            $(this).parents('form').find('input[name=country]').val($(this).parents('form').find('select[name=country]').val());

            var action =  $(this).parents('form').prop('action');
            action += "?CSRFToken="+$('input[name="CSRFToken"]').val();
            $(this).parents('form').prop('action', action);

            $(this).parents('form').submit();

            //if on firefox 1.0+
            var isFirefox = typeof InstallTrigger !== 'undefined';
            if (isFirefox) {
                $(this).parents('form').append('<input type="submit" id="submitFirefox" style="display: none">');
                $('#submitFirefox').trigger('click');
            }
        })
    },

    removeBorderColorFromRadioButtons: function (element) {
        element.each(function () {
            $(this).css("border-color", "");
        });
    },

    switchToQeemah1: function () {
        this.loadBasicInformationQeemah1Data();
        this.basicInformationExtendedSection.show();
        this.licenseInformationSection.show();
        this.licenseTypeSection.show();
        this.licenseYearSection.show();
        if (this.licenseTypeSection.find('select').val()) {
            this.licenseTypeSection.find('select').trigger('change');
        }
        this.loadYearsDropDown();

        if ($('[name=isEntrepreneur]').filter(':checked').val()) {
            $('[name=isEntrepreneur]').trigger('change');

            if ($('[name=isEntrepreneur]').filter(':checked').val() === 'no') {
                this.updateSelectValues($('select[name=licenseDuration]'));
            }
        }
    },

    loadYearsDropDown: function () {
        var entityLicenseYear = this.licenseYearSection.find("#licenseYear");
        var previousEntityLicenseYear = entityLicenseYear.val();

        entityLicenseYear.find("option").remove();
        entityLicenseYear.append(new Option("", "", false, false));

        entityLicenseYear.append(new Option(getI18nText("license.entity.year.1"), "1", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.2"), "2", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.3"), "3", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.4"), "4", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.year.5"), "5", false, false));

        if(previousEntityLicenseYear) {
            entityLicenseYear.val(previousEntityLicenseYear).trigger("blur").trigger('change');
        } else {
            entityLicenseYear.val(null);
        }
    },
    loadYearsDropDownForRHQ: function () {
        var entityLicenseYear = this.licenseYearSection.find("#licenseYear");
        var previousEntityLicenseYear = entityLicenseYear.val();

        entityLicenseYear.find("option").remove();
        entityLicenseYear.append(new Option("", "", false, false));

        entityLicenseYear.append(new Option(getI18nText("license.entity.rhq.year.1"), "1", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.rhq.year.2"), "2", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.rhq.year.3"), "3", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.rhq.year.4"), "4", false, false));
        entityLicenseYear.append(new Option(getI18nText("license.entity.rhq.year.5"), "5", false, false));

        if(previousEntityLicenseYear) {
            entityLicenseYear.val(previousEntityLicenseYear).trigger("blur").trigger('change');
        } else {
            entityLicenseYear.val(null);
        }
    },

    loadYearsDropDownForEntrepreneur: function(){
        var entityLicenseYear = this.licenseYearSection.find("#licenseYear");
        entityLicenseYear.find("option").remove();
        entityLicenseYear.append(new Option("", "", false, false));

        entityLicenseYear.append(new Option(getI18nText("license.entity.year.entrepreneur.1"), "1", false, false));
    },

    loadBasicInformationQeemah1Data: function () {
        var self = this;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var countries = self.basicInformationExtendedSection.find("#basicInformationExtendedCountry");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                countries.attr("disabled", true);
                countries.val("SA").trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded

               /* var legalStatus = self.basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                if($('#entityForm #licenseTypes').val() == "9"){
                	jsonData.legalStatus.forEach(function (currentValue) {
                		if(currentValue.legalStatus == "LLC" || currentValue.legalStatus == "ILLC" || currentValue.legalStatus == "CJOI" || currentValue.legalStatus == "JOIN")
	                	legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
	                });
                	
	            }else{
	            	jsonData.legalStatus.forEach(function (currentValue) {
	            		alert("1currentValue: "+currentValue.legalStatusText+" ***1currentValue.legalStatus: "+currentValue.legalStatus);
	                	legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
	                });
	            }
            
                legalStatus.val(null);*/

                var multinationalCompany = self.basicInformationExtendedSection.find("#basicInformationExtendedMultinationalCompany");
                multinationalCompany.find("option").remove();
                multinationalCompany.append(new Option("", "", false, false));
                jsonData.multinationalCompany.forEach(function (currentValue) {
                    multinationalCompany.append(new Option(currentValue.multinationalCompanyText, currentValue.multinationalCompany, false, false));
                });
                multinationalCompany.val(null);

                var regions = self.basicInformationExtendedSection.find("#basicInformationExtendedRegion");
                regions.find("option").remove();
                regions.append(new Option("", "", false, false));
                jsonData.regions.forEach(function (currentValue) {
                    regions.append(new Option(currentValue.regionText, currentValue.region, false, false));
                });
                regions.val(null);

                var investments = self.basicInformationExtendedSection.find("#basicInformationExtendedInvestment");
                investments.find("option").remove();
                investments.append(new Option("", "", false, false));
                jsonData.expectedInvestments.forEach(function (currentValue) {
                    investments.append(new Option(currentValue.value, currentValue.key, false, false));
                });
                investments.val(null);

                self.basicInformationExtendedSection.find("select").not('#basicInformationExtendedCountry').each(function () {
                    self.updateSelectValues($(this));
                })
            }
        });
    },
    
    loadProfessionalLicenseLegalStatus: function () {
    	var self = this;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                
                var legalStatus = self.basicInformationExtendedSection.find("#basicInformationExtendedLegalStatus");
                var previousLegalStatus = legalStatus.attr('data-value');
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                if($('#entityForm #licenseTypes').val() == "9"){
                	jsonData.legalStatus.forEach(function (currentValue) {
                		if(currentValue.legalStatus == "LLC" || currentValue.legalStatus == "ILLC" || currentValue.legalStatus == "CJOI" || currentValue.legalStatus == "JOIN")
	                	legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
	                });
                	
	            }else{
	            	jsonData.legalStatus.forEach(function (currentValue) {
	                	legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
	                });
	            }
                
                if(previousLegalStatus) {
                	legalStatus.val(previousLegalStatus).trigger("blur").trigger('change');
                } else {
                	legalStatus.val(null);
                }
                //legalStatus.val(null);
            }
        });
	},
    updateSelectValues: function (element) {
        if (element.data('value') != "" && element.data('value') != undefined && element.data('value')) {
            // element.find('option[value='+element.data('value')+']').prop('selected', true).trigger('change');
            // if (element.prop('name') === "country") {
            //     element.siblings('.select2.select2-container').addClass("select2Container_selected");
            // }
            element.val(element.data('value')).trigger('change');
        }
    },

    checkBusinessActivities: function () {
        if(this.businessActivitiesSection.find('#businessActivitiesJsonInputs input'). length > 0) {
            this.businessActivitiesSection.find("#noBusinessActivitiesSelected").hide();
            this.businessActivitiesSection.find("#businessActivitiesTable").show();
        }
        else {
            this.businessActivitiesSection.find("#noBusinessActivitiesSelected").show();
            this.businessActivitiesSection.find("#businessActivitiesTable").hide();
        }
    },

    checkBusinessActivitiesTemporaryContract: function (licenseTypeValue) {
        if (licenseTypeValue === SAGIA.config.temporaryLicenseConstant) {
            $("#noBusinessActivitiesSelected").hide();
            this.businessActivitiesSection.hide();
            $("#temporaryLicenseTextBox").show();
        } else {
            this.checkBusinessActivities();
            $("#temporaryLicenseTextBox").hide();
        }
        return licenseTypeValue === SAGIA.config.temporaryLicenseConstant;
    },

    loadBasicInformationQeemah1Cities: function (region) {
        var self = this;

        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var basicInformationExtendedCity = self.basicInformationExtendedSection.find("#basicInformationExtendedCity");
                basicInformationExtendedCity.find("option").remove();
                basicInformationExtendedCity.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    basicInformationExtendedCity.append(new Option(currentValue.cityText, currentValue.city, false, false));
                });

                self.updateSelectValues(self.basicInformationExtendedSection.find("#basicInformationExtendedCity"));
            }
        });
    },

    isValidEntityForm: function () {
        var self = this;
        var hasErrors = false;
        var entityForm = $('#entityForm');
		console.log("ENTITY FORM",entityForm.value);
		console.log("Selected ",$('#branchInformationRhqRegionsSection').val());
        entityForm.find('.form-group.has-error').removeClass('has-error');
        entityForm.find('.help-block').html('');
        SAGIA.licenseApply.removeGlobalErrorMessage($('#entityInformationGlobalMessage'));

        if (!entityForm.find('[name=hasGCCNationality]').filter(':checked').val()) {

            hasErrors = true;
        }
       
        if (entityForm.find('[name=hasAdvanceLicenseNr]').filter(':checked').val() === 'yes' && !entityForm.find('[name=advanceLicenseNr]').val()) {
            hasErrors = true;
            entityForm.find('[name=advanceLicenseNr]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.entityInfo.advanceLicenseNumber.error"));
            entityForm.find('[name=advanceLicenseNr]').parents('.form-group').addClass('has-error');
        }
        
        if (entityForm.find('[name=isPreApprovalNumber]').filter(':checked').val() === 'yes' && !entityForm.find('[name=preApprovalNumber]').val()) {
		hasErrors = true;
            entityForm.find('[name=preApprovalNumber]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.entityInfo.advanceLicenseNumber.error"));
            entityForm.find('[name=preApprovalNumber]').parents('.form-group').addClass('has-error');
        }

        if (!entityForm.find('[name=licenseType]').val()) {
            entityForm.find('[name=licenseType]').parents('.form-group').addClass('has-error');
            hasErrors = true;
        }
        if((typeof $("#attachmentSection").attr("style") === 'undefined' || $("#attachmentSection").attr("style").indexOf("display: none") === -1)){
            if((typeof $("#entrepreneurAttachment").attr("style") === 'undefined' || $("#entrepreneurAttachment").attr("style").indexOf("display: none") === -1)) {
//              var boardResolutionFile = $("#boardResolutionFile");
//              boardResolutionFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
 // //            if(!boardResolutionFile.val()) {
 // //                boardResolutionFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
 // //                hasErrors = true;
 // //            }
 //
//              var letterOfSupportFile = $("#letterOfSupportFile");
//              letterOfSupportFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
 // //            if(!letterOfSupportFile.val()) {
 // //                letterOfSupportFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
 // //                hasErrors = true;
 // //            }
             entityForm.find('#boardResolutionFile, #letterOfSupportFile').each(function () {
            	// if(!$("#boardResolutionFile").val() || !$("#letterOfSupportFile").val()){
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
            	// }
             })
         }

 if((typeof $("#entityListedInStockMarketAttachment").attr("style") === 'undefined' || $("#entityListedInStockMarketAttachment").attr("style").indexOf("display: none") === -1)) {
             entityForm.find('#entityListedInStockMarketFile').each(function () {
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
             })
         }
 if((typeof $("#entityAssetAttachment").attr("style") === 'undefined' || $("#entityAssetAttachment").attr("style").indexOf("display: none") === -1)) {
             entityForm.find('#entityAssetFile').each(function () {
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
             })
         }
 if((typeof $("#entityRevenueAttachment").attr("style") === 'undefined' || $("#entityRevenueAttachment").attr("style").indexOf("display: none") === -1)) {
             entityForm.find('#entityRevenueFile').each(function () {
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
             })
         }
 if((typeof $("#entityBranchAttachment").attr("style") === 'undefined' || $("#entityBranchAttachment").attr("style").indexOf("display: none") === -1)) {
             entityForm.find('#branchCR1File, #branchCR2File, #branchCR3File, #branchCR4File').each(function () {
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
             })
         }
if((typeof $("#branchAttachment").attr("style") === 'undefined' || $("#branchAttachment").attr("style").indexOf("display: none") === -1)) {
             entityForm.find('#mainBranchCRFile, #otherBranchCR1File, #otherBranchCR2File').each(function () {
            		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                         hasErrors = true;
                     }
             })
         }

         if((typeof $("#preApprovalNrAttachment").attr("style") === 'undefined' || $("#preApprovalNrAttachment").attr("style").indexOf("display: none") === -1)) {
         	entityForm.find('.validate__preApproval').each(function () {
         		//if(!$("#financialStatementFile").val() || !$("#iqamaFile").val() || !$("#crCertificateFile").val() || !$("#gosiCertificateFile").val() || !$("#noObjectionCertificateFile").val()){
                 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                     hasErrors = true;
                 }
         		//}
             })
         }
        }
//--Start---bqureshi changes 17-06-2021 skip Entreprenuer check if lic type is 11 (RHQ)
	if(self.licenseTypeSection.find('#licenseTypes').val() != "11"){
//--End---bqureshi changes 17-06-2021 skip Entreprenuer check if lic type is 11 (RHQ)		
        if (!entityForm.find('[name=isEntrepreneur]').filter(':checked').val()) {
			console.log("Ent error")
            hasErrors = true;
        }
}else{

//bqureshi ===== RHQ Validations
//      if (entityForm.find('[name=isMoreThan2Branch]').filter(':checked').val() ) {
//			console.log("2 error")
//            hasErrors = true;
//        }
		if (entityForm.find('[name=isEntityListedInStockMarket]').filter(':checked').val() === "no" &&
			entityForm.find('[name=isEntityRevenueMoreThanThreshold]').filter(':checked').val() === "no" &&
			entityForm.find('[name=isEntityAssetMoreThanThreshold]').filter(':checked').val() === "no" &&
			entityForm.find('[name=isMoreThan6Branch]').filter(':checked').val() === "no"
		) {
			entityForm.find('[name=isEntityListedInStockMarket]').parents('.form-group').addClass('has-error');
			entityForm.find('[name=isEntityRevenueMoreThanThreshold]').parents('.form-group').addClass('has-error');
			entityForm.find('[name=isEntityAssetMoreThanThreshold]').parents('.form-group').addClass('has-error');
			entityForm.find('[name=isMoreThan6Branch]').parents('.form-group').addClass('has-error');
            self.errorForBranchSectionMandatory();
		    hasErrors = true;
			}
		if(entityForm.find('#branchInformationRhqCountry').val().length < 8){
        	entityForm.find('[id=branchInformationRhqCountry]').parents('.formSelectBox').find('.help-block').text(getI18nText("validation.rhq.countries.minimum"));
        	entityForm.find('[id=branchInformationRhqCountry]').parents('.form-group').addClass('has-error');			
			hasErrors = true;
			}

        if(entityForm.find('#branchInformationRhqRegionsSection').val().length < 1){
         entityForm.find('[id=branchInformationRhqRegionsSection]').parents('.form-group').addClass('has-error');
          hasErrors = true;
		}
		if(entityForm.find('[name=isMoreThan2Branch]').filter(':checked').val() === "no"){
			self.errorForMoreThan2Branches();
			 hasErrors = true;
		}
		  
		
		}

//bqureshi
//        if (!entityForm.find('[name=isEntityListedInStockMarket]').val()||!entityForm.find('[name=isEntityRevenueMoreThanThreshold]').val()||!entityForm.find('[name=isEntityAssetMoreThanThreshold]').val()||!entityForm.find('[name=isMoreThan6Branch]').val()) {
//           /* entityForm.find('[name=isEntityListedInStockMarket]').parents('.form-group').addClass('has-error');
//            entityForm.find('[name=isEntityRevenueMoreThanThreshold]').parents('.form-group').addClass('has-error');
//            entityForm.find('[name=isEntityAssetMoreThanThreshold]').parents('.form-group').addClass('has-error');
//            entityForm.find('[name=isMoreThan6Branch]').parents('.form-group').addClass('has-error');*/
//			//this.errorForBranchSectionMandatory();
//           // hasErrors = true;
//        }
        if (!entityForm.find('[name=licenseDuration]').val()) {
            entityForm.find('[name=licenseDuration]').parents('.form-group').addClass('has-error');
            hasErrors = true;
        }

        entityForm.find('#basicInformationExtendedSection :input').not('.validate__email, .validate__website').each(function () {
            if ($(this).siblings('label.control-label_mandatory').length > 0 && !$(this).val()) {
                $(this).closest('.form-group').addClass('has-error');
                $(this).parents('.formInputBox, .formSelectBox').find('.help-block').text(self.getErrorMessageInfo($(this).attr('name')));
                hasErrors = true
            }
        });

        if (SAGIA.licenseApply.validateEmailAddresses(entityForm.find('#basicInformationExtendedSection .validate__email')) === false) {
            hasErrors = true;
        }

        if (SAGIA.licenseApply.validateWebsite(entityForm.find('#basicInformationExtendedSection .validate__website')) === false) {
            hasErrors = true;
        }
        
        if (entityForm.find('#basicInformationExtendedPostalCode').val()) {
        	if(entityForm.find('#basicInformationExtendedPostalCode').val().length != 5){
        		entityForm.find('[id=basicInformationExtendedPostalCode]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		entityForm.find('[id=basicInformationExtendedPostalCode]').parents('.form-group').addClass('has-error');
                hasErrors = true;
        	}
        }
        
        if (entityForm.find('#basicInformationExtendedPOBox').val()) {
        	if(entityForm.find('#basicInformationExtendedPOBox').val().length != 5){
        		entityForm.find('[id=basicInformationExtendedPOBox]').parents('.formInputBox').find('.help-block').text(getI18nText("validation.basicinformation.poBoxAndPostalCode"));
        		entityForm.find('[id=basicInformationExtendedPOBox]').parents('.form-group').addClass('has-error');
                hasErrors = true;
        	}
        }



        if (hasErrors) {
        	//SAGIA.licenseApply.scrollToErrorMessage(element);
            //SAGIA.licenseApply.globalErrorMessage($('#entityInformationGlobalMessage'));
        }
        return !hasErrors;
    },

		branchInformationYesEvent: function () {
		this.attachmentSection.show();
 		this.branchAttachment.show();
		//$("#licenseInformationSection").prop("disabled", true);
		
    },
	branchInformationNoEvent: function () {
        //this.attachmentSection.hide();
	//console.log(this.attachmentSection);
 		this.branchAttachment.hide();
		//$("#licenseInformationSection").prop("disabled", true);

    },
    bindBranchInformationEvents: function () {
        var self = this;

        self.branchInformationSection.find('input[name=isMoreThan2Branch]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
				self.errorForMoreThan2Branches();
                self.branchInformationNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.branchInformationYesEvent();
            }
        });

	//Bqureshi --start
        self.branchInformationSection.find('input[name=isEntityListedInStockMarket]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
            } else if ($(this).filter(':checked').val() === 'yes') {
			self.removeErrorStateFromBranchInformation();}
        });

        self.branchInformationSection.find('input[name=isEntityRevenueMoreThanThreshold]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
            } else if ($(this).filter(':checked').val() === 'yes') {
			self.removeErrorStateFromBranchInformation();}
        });

        self.branchInformationSection.find('input[name=isEntityAssetMoreThanThreshold]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
            } else if ($(this).filter(':checked').val() === 'yes') {
			self.removeErrorStateFromBranchInformation();}
        });

        self.branchInformationSection.find('input[name=isMoreThan6Branch]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
				
            } else if ($(this).filter(':checked').val() === 'yes') {
				
			self.removeErrorStateFromBranchInformation();}
        });

        self.branchInformationSection.find('[id=branchInformationRhqCountry]').on('change', function () {
            if ($(this).val().length >= 8 ) {
				self.branchInformationSection.find('[id=branchInformationRhqCountry]').parents('.formSelectBox').find('.help-block').hide(); //text(getI18nText("validation.rhq.countries.minimum"));
        		self.branchInformationSection.find('[id=branchInformationRhqCountry]').parents('.form-group').removeClass('has-error');	
            } else {
				self.branchInformationSection.find('[id=branchInformationRhqCountry]').parents('.formSelectBox').find('.help-block').show();
				self.branchInformationSection.find('[id=branchInformationRhqCountry]').parents('.formSelectBox').find('.help-block').text(getI18nText("validation.rhq.countries.minimum"));
        		self.branchInformationSection.find('[id=branchInformationRhqCountry]').parents('.form-group').addClass('has-error');	
			}
        });


    },

		removeErrorStateFromBranchInformation: function(){
				$('[name=isEntityListedInStockMarket]').parents('.form-group').removeClass("has-error");
				$('[name=isEntityRevenueMoreThanThreshold]').parents('.form-group').removeClass("has-error");
				$('[name=isEntityAssetMoreThanThreshold]').parents('.form-group').removeClass("has-error");
				$('[name=isMoreThan6Branch]').parents('.form-group').removeClass("has-error");	
		},
		
//bqureshi --End		
		errorForMoreThan2Branches: function(data) {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText('rhq.morethan2branches.error.message.name'));
            errorModal.modal('show');
        },
		errorForBranchSectionMandatory: function(data) {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText('rhq.branch.section.error.message.name'));
            errorModal.modal('show');
        },


		entityListedInStockMarketYesEvent: function () {
        this.attachmentSection.show();
       // this.branchAttachment.show();
		this.entityListedInStockMarketAttachment.show();
    },
	entityListedInStockMarketNoEvent: function () {
        //this.attachmentSection.hide();
 		//this.branchAttachment.hide();
		this.entityListedInStockMarketAttachment.hide();
    },

		entityAssetAttachmentYesEvent: function () {
        this.attachmentSection.show();
        //this.branchAttachment.show();
		this.entityAssetAttachment.show();
    },
	entityAssetAttachmentNoEvent: function () {
        //this.attachmentSection.hide();
 		//this.branchAttachment.hide();
		this.entityAssetAttachment.hide();
    },
	
		entityRevenueAttachmentYesEvent: function () {
        this.attachmentSection.show();
       // this.branchAttachment.show();
		this.entityRevenueAttachment.show();
    },
	entityRevenueAttachmentNoEvent: function () {
        //this.attachmentSection.hide();
 		//this.branchAttachment.hide();
		this.entityRevenueAttachment.hide();
    },
	
		entityBranchAttachmentYesEvent: function () {
        this.attachmentSection.show();
       // this.branchAttachment.show();
		this.entityBranchAttachment.show();
    },
	entityBranchAttachmentNoEvent: function () {
       // this.attachmentSection.hide();
 		//this.branchAttachment.hide();
		this.entityBranchAttachment.hide();
    },
    bindEntityListedInStockMarketEvents: function () {
        var self = this;

        self.branchInformationSection.find('input[name=isEntityListedInStockMarket]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.entityListedInStockMarketNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.entityListedInStockMarketYesEvent();
            }
        });
    },
    bindEntityAssetEvents: function () {
        var self = this;

        self.branchInformationSection.find('input[name=isEntityAssetMoreThanThreshold]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.entityAssetAttachmentNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.entityAssetAttachmentYesEvent();
            }
        });
    },
    bindEntityRevenueEvents: function () {
        var self = this;

        self.branchInformationSection.find('input[name=isEntityRevenueMoreThanThreshold]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.entityRevenueAttachmentNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.entityRevenueAttachmentYesEvent();
            }
        });
    },
    bindEntityBranchesEvents: function () {
        var self = this;

        self.branchInformationSection.find('input[name=isMoreThan6Branch]').on('change', function () {
            if ($(this).filter(':checked').val() === 'no') {
                self.entityBranchAttachmentNoEvent();
            } else if ($(this).filter(':checked').val() === 'yes') {
                self.entityBranchAttachmentYesEvent();
            }
        });
    },
    getErrorMessageInfo: function (fieldName) {
        return getI18nText("validate.licenseApply.entityInfo."+fieldName+".error");
    }, 

//	bindRHQCountriesLimitEvent: function(){
//		var eightCountries = null;
//		this.countriesCount = 0;
//		 $('[name=listOfRhqCountries]').on('change', function () {
////         if($(this).val().length<8){
////			$(this).val(eightCountries)
////			alert("max selection");
////		 }else{
////			eightCountries = $(this).val();
////		}
//			this.countriesCount = $(this).val().length;
//			console.log("change 111");
//        });
//	},
};
