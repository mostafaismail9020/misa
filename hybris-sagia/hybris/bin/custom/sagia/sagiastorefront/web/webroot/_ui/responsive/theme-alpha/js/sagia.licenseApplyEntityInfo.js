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
    	//this.attachmentSection.hide();
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

                // self.updateSelectValues($("#branchInformationRhqRegionsSection"));
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
              //  countries.find("option").remove();
               // countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });


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
                    if (typeof objectBranches == "undefined") {
                         objectBranches = [];
                    }

                    if (typeof objectBrands == "undefined") {
                        objectBrands = [];
                    }

                    if (typeof objectCost == "undefined") {
                        objectCost = [];
                    }
                //22-Jan-22 - Start

                // let objectActivityOptions = new Array("","Sales and Marketing Support","Human Resources,and Personnel Management","Training Services","Financial Management, Foreign Exchange, and Treasury Centre Services","Compliance and Internal Control","Accounting","Legal","Auditing","Research and Analysis","Advisory Services","Operations Control","Logistics and Supply Chain Management","International Trading","Technical Support or Engineering Assistance","Network Operations for IT System","Research and Development","Intellectual Property Rights Management","Production Management","Sourcing of Raw Materials and Parts");
                // for(var i = 1; i < objectActivityOptions.length; i++) {
                //     $('#rhqCheckbox').append('<option value='+i+'>'+objectActivityOptions[i]+'</option>');
                //  }

                // let objectRhqStrategicOptions = new Array("","Formulate and monitor the regional strategy","Coordinate strategic alignment","Embed products and/or services in the region","Support acquisitions, mergers and divestments","Review financial performance");
                // for(var i = 1; i < objectRhqStrategicOptions.length; i++) {
                //     $('#rhqStrategicCheckbox').append('<option value='+i+'>'+objectRhqStrategicOptions[i]+'</option>');
                //  }

                // let objectRhqManagementFunOptions = new Array("","Business planning","Budgeting","Business coordination","Identification of new market opportunities","Monitoring of the regional market, competitors, and operations","Marketing plan for the region","Operational and financial reporting");
                // for(var i = 1; i < objectRhqManagementFunOptions.length; i++) {
                //     $('#rhqManagementFunCheckbox').append('<option value='+i+'>'+objectRhqManagementFunOptions[i]+'</option>');

                //  }


                $('#rhqCenterAdmin').on('change', function () {
                   $('#rhqCountryRegion').show();
                });






                // let objectCenterAdminRhqRegionsOptions = new Array("","North America","Central America","South America","Europe","North Africa","Central & South Africa","Middle East","Indian Subcontinent","CIS","East Asia","Southeast Asia","Oceania");
                // for(var i = 1; i < objectCenterAdminRhqRegionsOptions.length; i++) {
                //     $('#centerAdminRhqRegionsSection').append('<option value='+i+'>'+objectCenterAdminRhqRegionsOptions[i]+'</option>');
                // }

                //  let objectRhqSubsidiaryOptions = new Array("Only one country","2 to 5 countries","6 to 10 countries","over 10 countries");
                 let objectRhqSubsidiaryOptions = [{"value":"only_one_country","text":"Only one country"}, {"value":"2_to_5_countries","text":"2 to 5 countries"}, {"value":"6_to_10_countries","text":"6 to 10 countries"},{"value":"over_10_countries","text":"over 10 countries"}]
                 $('#rhqSubsidiaryPresence').html('');
                 for(var i = 0; i < objectRhqSubsidiaryOptions.length; i++) {
                   // $('#rhqSubsidiaryPresence').append('<div class="form-item"><input type="radio" name="rhqSubsidiaryPresence" value='+i+'>'+objectRhqSubsidiaryOptions[i]+'</input></div>');

                    $('#rhqSubsidiaryPresence').append('<div class="form-item"><input name="rhqSubsidiaryPresence" class="form-control" type="radio" id=' + (objectRhqSubsidiaryOptions[i]['value'])  + ' value=' + (objectRhqSubsidiaryOptions[i]['value'])  + '><label  class="control-label" for=' + (objectRhqSubsidiaryOptions[i]['value'])  + '><span></span> ' + (objectRhqSubsidiaryOptions[i]['text'])  + '</label></div>');

                 }

                //22-Jan-22 - End

                $('.rqh-tables').css("display", "block");
                $('#branchAttachmentNewRhq').css("display", "block")


                $('.rhqSelectBoxes').css("display", "block")
                // styles for the z-index existing sagia issue fix

                $('#addBranchTable').css({ 'z-index': '99999' });
                $('#EditBranchTable').css({ 'z-index': '99999' });
                $('#addBrandTable').css({ 'z-index': '99999' });
                $('#EditBrandTable').css({ 'z-index': '99999' });
                $('#addrhqCostTable',).css({ 'z-index': '99999' });
                $('#EditrhqCostTable').css({ 'z-index': '99999' });
                $('.page-new-license-apply.modal-open .select2-container--open.select2-container').css("z-index", "999999");

                // MNC Branches - Start
                 // objectBranches = [];
                 $("#mncBranchTable tbody").html('');
                 try{
                    if (objectBranches.length > 0) {
                        for (var idx = 0; idx < objectBranches.length; idx++) {
                            $("#mncBranchTable tbody").append('<tr><td>' + objectBranches[idx]['companyName'] + '</td><td>' + objectBranches[idx]['country'] + '</td><td>' + objectBranches[idx]['businessRelationshipType'] + '</td><td>' + objectBranches[idx]['industry'] + '</td><td>' + objectBranches[idx]['operations'] + '</td><td>' + objectBranches[idx]['RhqActivityProvided'] + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBranchBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');
                        }

                    }
                 }catch(e){
                     console.log(e)
                    }

                editBranchIndex = 0;
                BranchCurrentRow = null;

                $('#entityAddBranch').click(function () {
                    //alert('MNC Branches');
                    var addBranchName = $('#addBranchName').val();
                    var addBranchCountry = $('#addBranchCountry').val();
                    var addBranchBuz = $('#addBranchBuz').val();
                    var addBranchIndustry = $('#addBranchIndustry').val();
                    var addBranchOperation = $('#addBranchOperation').val();
                    var addBranchRhqActivity = $('#addBranchRhqActivity').val();

                    //add branch validation
                    hasBranchErrors = false;

                    if (addBranchName.length == 0) {
                        $('#addBranchName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchName').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#addBranchName').parents('.form-group').removeClass('has-error');
                    }

                    if (addBranchCountry.length == 0) {
                        $('#addBranchCountry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchCountry').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchCountry-error').text(getI18nText(""));
                        $('#addBranchCountry').parents('.form-group').removeClass('has-error');
                    }

                    if (addBranchBuz.length == 0) {
                        $('#addBranchBuz-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchBuz').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchBuz-error').text(getI18nText(""));
                        $('#addBranchBuz').parents('.form-group').removeClass('has-error');
                    }

                    if (addBranchIndustry.length == 0) {
                        $('#addBranchIndustry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchIndustry').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchIndustry-error').text(getI18nText(""));
                        $('#addBranchIndustry').parents('.form-group').removeClass('has-error');
                    }

                    if (addBranchOperation.length == 0) {
                        $('#addBranchOperation-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchOperation').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchOperation-error').text(getI18nText(""));
                        $('#addBranchOperation').parents('.form-group').removeClass('has-error');
                    }

                    if (addBranchRhqActivity.length == 0) {
                        $('#addBranchRhqActivity-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBranchRhqActivity').parents('.form-group').addClass('has-error');
                        hasBranchErrors = true;
                    } else {
                        $('#addBranchRhqActivity-error').text(getI18nText(""));
                        $('#addBranchRhqActivity').parents('.form-group').removeClass('has-error');
                    }
                    if (hasBranchErrors) {
                        return false;
                    }
                    else {
                        //pusing the new branch into Javascript array for backend db submission
                        objectBranches.push({
                            "companyName": $('#addBranchName').val(),
                            'country': $('#addBranchCountry').val(),
                            'businessRelationshipType': $('#addBranchBuz').val(),
                            "industry": $('#addBranchIndustry').val(),
                            'operations': $('#addBranchOperation').val(),
                            'RhqActivityProvided': $('#addBranchRhqActivity').val()
                        });
                        console.log(objectBranches.length);
                        console.log(JSON.stringify(objectBranches).replace(/"/g, "'"));


                        if(objectBranches.length <1){
                            $('#mncBranchTable-error').text(getI18nText("rhq.entities.managed.by.rhq.validation"));
                            $('#mncBranchTable-error').parents('.form-group').addClass('has-error');
                        } else {
                            $('#mncBranchTable-error').text(getI18nText(""));
                            $('#mncBranchTable-error').removeClass('has-error');
                        }


                        $("#mncBranchTable tbody").append('<tr><td>' + addBranchName + '</td><td>' + addBranchCountry + '</td><td>' + addBranchBuz + '</td><td>' + addBranchIndustry + '</td><td>' + addBranchOperation + '</td><td>' + addBranchRhqActivity + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBranchBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');
                        $('#addBranchForm')[0].reset();
                        $("#addBranchCountry").val(null).trigger("change");
                        $("#addBranchBuz").val(null).trigger("change");
                        $("#addBranchOperation").val(null).trigger("change");
                    }

                });
                $('#mncBranchTable').on('click', '#EditBranchBtn', function () {
                    //  var objectBranches = [{"name": "My  Branch", 'country': "India", 'type': "Joint Venture","industry": "Chemical", 'operation': "manufacturing", 'activity': "DFG-1222"}]
                    $('#EditBranchTable').modal('show');
                    editBranchIndex = $(this).closest('tr').index();
                    BranchCurrentRow = $(this).parents('tr');
                    let idx = $(this).closest('tr').index();
                    $('#editBranchName').val(objectBranches[idx]['companyName']);
                    $('#editBranchCountry').val(objectBranches[idx]['country']).trigger('change');
                    $('#editBranchBuz').val(objectBranches[idx]['businessRelationshipType']).trigger('change');
                    $('#editBranchIndustry').val(objectBranches[idx]['industry']);
                    $('#editBranchOperation').val(objectBranches[idx]['operations']).trigger('change');
                    $('#editBranchRhqActivity').val(objectBranches[idx]['RhqActivityProvided']);
                });


                $('#entityEditBranch').click(function () {


                    //edit branch validation
                    var editBranchName = $('#editBranchName').val();
                    var editBranchCountry = $('#editBranchCountry').val();
                    var editBranchBuz = $('#editBranchBuz').val();
                    var editBranchIndustry = $('#editBranchIndustry').val();
                    var editBranchOperation = $('#editBranchOperation').val();
                    var editBranchRhqActivity = $('#editBranchRhqActivity').val();

                    hasEditBranchErrors = false;

                    if (editBranchName.length == 0) {
                        $('#editBranchName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchName').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#editBranchName').parents('.form-group').removeClass('has-error');
                    }

                    if (editBranchCountry.length == 0) {
                        $('#editBranchCountry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchCountry').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchCountry-error').text(getI18nText(""));
                        $('#editBranchCountry').parents('.form-group').removeClass('has-error');
                    }

                    if (editBranchBuz.length == 0) {
                        $('#editBranchBuz-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchBuz').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchBuz-error').text(getI18nText(""));
                        $('#editBranchBuz').parents('.form-group').removeClass('has-error');
                    }

                    if (editBranchIndustry.length == 0) {
                        $('#editBranchIndustry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchIndustry').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchIndustry-error').text(getI18nText(""));
                        $('#editBranchIndustry').parents('.form-group').removeClass('has-error');
                    }

                    if (editBranchOperation.length == 0) {
                        $('#editBranchOperation-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchOperation').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchOperation-error').text(getI18nText(""));
                        $('#editBranchOperation').parents('.form-group').removeClass('has-error');
                    }

                    if (editBranchRhqActivity.length == 0) {
                        $('#editBranchRhqActivity-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBranchRhqActivity').parents('.form-group').addClass('has-error');
                        hasEditBranchErrors = true;
                    } else {
                        $('#editBranchRhqActivity-error').text(getI18nText(""));
                        $('#editBranchRhqActivity').parents('.form-group').removeClass('has-error');
                    }
                    if (hasEditBranchErrors) {
                        return false;
                    }
                    else {
                        //pusing the new branch into Javascript array for backend db submission
                        let updateditem = { "companyName": $('#editBranchName').val(), 'country': $('#editBranchCountry').val(), 'businessRelationshipType': $('#editBranchBuz').val(), "industry": $('#editBranchIndustry').val(), 'operations': $('#editBranchOperation').val(), 'RhqActivityProvided': $('#editBranchRhqActivity').val() };
                        objectBranches[editBranchIndex] = updateditem;

                        $("#mncBranchTable  > tbody").find($(BranchCurrentRow)).replaceWith('<tr><td>' + editBranchName + '</td><td>' + editBranchCountry + '</td><td>' + editBranchBuz + '</td><td>' + editBranchIndustry + '</td><td>' + editBranchOperation + '</td><td>' + editBranchRhqActivity + '</td><td><span type="" class="btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBranchBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"> <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');

                        console.log(objectBranches.length);

                    }

                });

                // Array Remove - By John Resig (MIT Licensed)
                Array.prototype.remove = function (from, to) {
                    var rest = this.slice((to || from) + 1 || this.length);
                    this.length = from < 0 ? this.length + from : from;
                    return this.push.apply(this, rest);
                };

                $('#mncBranchTable').on('click', '.delete_btn_click', function () {
                    objectBranches.remove($(this).closest('tr').index());
                    $(this).parents("tr").remove();
                    console.log("after removed" + objectBranches.length);

                    if(objectBranches.length <1){
                        $('#mncBranchTable-error').text(getI18nText("rhq.entities.managed.by.rhq.validation"));
                        $('#mncBranchTable-error').addClass('has-error');
                    } else {
                        $('#mncBranchTable-error').text(getI18nText(""));
                        $('#mncBranchTable-error').removeClass('has-error');
                    }

                });

                // MNC Branches - End

                // MNC Brand - Start
               //  objectBrands = [];
                $("#mncBrandTable tbody").html('');
                try {
                    if (typeof objectBrands !== "undefined") {
                        if (objectBrands.length > 0) {
                            for (var idx = 0; idx < objectBrands.length; idx++) {
                                $("#mncBrandTable tbody").append('<tr><td>' + objectBrands[idx]['brandName'] + '</td><td>' + objectBrands[idx]['country'] + '</td><td>' + objectBrands[idx]['industry'] + '</td><td>' + objectBrands[idx]['companyOwningBrandInMENA'] + '</td><td>' + objectBrands[idx]['RhqActivityProvided'] + '</td><td><span type="" class="btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBrandBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');

                            }
                        }
                    }
                }
                catch (err) {
                    console.log(err);
                }
                editBrandIndex = 0;
                BrandCurrentRow = null;

                $('#entityAddBrand').click(function () {
                    //alert('MNC Branches');
                    var addBrandName = $('#addBrandName').val();
                    var addBrandCountry = $('#addBrandCountry').val();
                    var addBrandIndustry = $('#addBrandIndustry').val();
                    var addBrandMena = $('#addBrandMena').val();
                    var addBrandProvider = $('#addBrandProvider').val();

                    //add branch validation
                    hasBrandErrors = false;

                    if (addBrandName.length == 0) {
                        $('#addBrandName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#addBrandName').parents('.form-group').addClass('has-error');
                        hasBrandErrors = true;
                    } else {
                        $('#addBrandName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#addBrandName').parents('.form-group').removeClass('has-error');
                    }

                    if (addBrandCountry.length == 0) {
                        $('#addBrandCountry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBrandCountry').parents('.form-group').addClass('has-error');
                        hasBrandErrors = true;
                    } else {
                        $('#addBrandCountry-error').text(getI18nText(""));
                        $('#addBrandCountry').parents('.form-group').removeClass('has-error');
                    }

                    if (addBrandIndustry.length == 0) {
                        $('#addBrandIndustry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBrandIndustry').parents('.form-group').addClass('has-error');
                        hasBrandErrors = true;
                    } else {
                        $('#addBrandIndustry-error').text(getI18nText(""));
                        $('#addBrandIndustry').parents('.form-group').removeClass('has-error');
                    }

                    if (addBrandMena.length == 0) {
                        $('#addBrandMena-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBrandMena').parents('.form-group').addClass('has-error');
                        hasBrandErrors = true;
                    } else {
                        $('#addBrandMena-error').text(getI18nText(""));
                        $('#addBrandMena').parents('.form-group').removeClass('has-error');
                    }

                    if (addBrandProvider.length == 0) {
                        $('#addBrandProvider-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addBrandProvider').parents('.form-group').addClass('has-error');
                        hasBrandErrors = true;
                    } else {
                        $('#addBrandProvider-error').text(getI18nText(""));
                        $('#addBrandProvider').parents('.form-group').removeClass('has-error');
                    }


                    if (hasBrandErrors) {
                        return false;
                    }
                    else {
                        try {
                        			objectBrands.push({
                                                    "brandName": $('#addBrandName').val(),
                                                    'country': $('#addBrandCountry').val(),
                                                    'industry': $('#addBrandIndustry').val(),
                                                     "companyOwningBrandInMENA": $('#addBrandMena').val(),
                                                    'RhqActivityProvided': $('#addBrandProvider').val()
                                                });

                                                if(objectBrands.length <1){
                                                    $('#mncBrandTable-error').text(getI18nText("rhq.brand.presence.validation"));
                                                    $('#mncBrandTable-error').addClass('has-error');
                                                } else {
                                                    $('#mncBrandTable-error').text(getI18nText(""));
                                                    $('#mncBrandTable-error').removeClass('has-error');
                                                }
                                                console.log(objectBrands.length);
                                            }
                                       catch (err) {
                                           console.log(err);
                                       }
                        console.log(JSON.stringify(objectBrands).replace(/"/g, "'"));
                        $("#mncBrandTable tbody").append('<tr><td>' + addBrandName + '</td><td>' + addBrandCountry + '</td><td>' + addBrandIndustry + '</td><td>' + addBrandMena + '</td><td>' + addBrandProvider + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBrandBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');
                        $('#addBrandForm')[0].reset();
                        $("#addBrandCountry").val(null).trigger("change");

                    }

                });
                $('#mncBrandTable').on('click', '#EditBrandBtn', function () {
                    $('#EditBrandTable').modal('show');
                    BrandCurrentRow = $(this).parents('tr');
                    editBrandIndex = $(this).closest('tr').index();
                    let idx = $(this).closest('tr').index();
                    $('#editBrandName').val(objectBrands[idx]['brandName']);
                    $('#editBrandCountry').val(objectBrands[idx]['country']).trigger('change');
                    $('#editBrandIndustry').val(objectBrands[idx]['industry'])
                    $('#editBrandMena').val(objectBrands[idx]['companyOwningBrandInMENA']);
                    $('#editBrandProvider').val(objectBrands[idx]['RhqActivityProvided'])
                });

                $('#entityEditBrand').click(function () {


                    //edit branch validation
                    var editBrandName = $('#editBrandName').val();
                    var editBrandCountry = $('#editBrandCountry').val();
                    var editBrandIndustry = $('#editBrandIndustry').val();
                    var editBrandMena = $('#editBrandMena').val();
                    var editBrandProvider = $('#editBrandProvider').val();

                    hasEditBrandErrors = false;

                    if (editBrandName.length == 0) {
                        $('#editBrandName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#editBrandName').parents('.form-group').addClass('has-error');
                        hasEditBrandErrors = true;
                    } else {
                        $('#editBrandName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#editBrandName').parents('.form-group').removeClass('has-error');
                    }

                    if (editBrandCountry.length == 0) {
                        $('#editBrandCountry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBrandCountry').parents('.form-group').addClass('has-error');
                        hasEditBrandErrors = true;
                    } else {
                        $('#editBrandCountry-error').text(getI18nText(""));
                        $('#editBrandCountry').parents('.form-group').removeClass('has-error');
                    }

                    if (editBrandIndustry.length == 0) {
                        $('#editBrandIndustry-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBrandIndustry').parents('.form-group').addClass('has-error');
                        hasEditBrandErrors = true;
                    } else {
                        $('#editBrandIndustry-error').text(getI18nText(""));
                        $('#editBrandIndustry').parents('.form-group').removeClass('has-error');
                    }

                    if (editBrandMena.length == 0) {
                        $('#editBrandMena-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBrandMena').parents('.form-group').addClass('has-error');
                        hasEditBrandErrors = true;
                    } else {
                        $('#editBrandMena-error').text(getI18nText(""));
                        $('#editBrandMena').parents('.form-group').removeClass('has-error');
                    }

                    if (editBrandProvider.length == 0) {
                        $('#editBrandProvider-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editBrandProvider').parents('.form-group').addClass('has-error');
                        hasEditBrandErrors = true;
                    } else {
                        $('#editBrandProvider-error').text(getI18nText(""));
                        $('#editBrandProvider').parents('.form-group').removeClass('has-error');
                    }


                    if (hasEditBrandErrors) {
                        return false;
                    }
                    else {
                        //pusing the new branch into Javascript array for backend db submission
                        //  var objectBranches = [{"name": "My  Branch", 'country': "India", 'industry': "Joint Venture","mena": "Chemical", 'provider': "manufacturing"}]
                        let updateditem = {
                            "brandName": $('#editBrandName').val(),
                            'country': $('#editBrandCountry').val(),
                            'industry': $('#editBrandIndustry').val(),
                            "companyOwningBrandInMENA": $('#editBrandMena').val(),
                            'RhqActivityProvided': $('#editBrandProvider').val()
                        };
                        $("#mncBrandTable  > tbody").find($(BrandCurrentRow)).replaceWith('<tr><td>' + editBrandName + '</td><td>' + editBrandCountry + '</td><td>' + editBrandIndustry + '</td><td>' + editBrandMena + '</td><td>' + editBrandProvider + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditBrandBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');

                        objectBrands[editBrandIndex] = updateditem;
                        console.log(objectBrands.length);

                    }

                });


                // Array Remove - By John Resig (MIT Licensed)
                Array.prototype.remove = function (from, to) {
                    var rest = this.slice((to || from) + 1 || this.length);
                    this.length = from < 0 ? this.length + from : from;
                    return this.push.apply(this, rest);
                };

                $('#mncBrandTable').on('click', '.delete_btn_click', function () {
                    console.log("before removed" + objectBrands.length);
                    console.log($(this).html());
                    console.log($(this).index());
                    console.log('going to delete the index..' + $(this).closest('tr').index());
                    objectBrands.remove($(this).closest('tr').index());
                    $(this).parents("tr").remove();
                    console.log("after removed" + objectBrands.length);

                    if(objectBrands.length <1){
                        $('#mncBrandTable-error').text(getI18nText("rhq.brand.presence.validation"));
                        $('#mncBrandTable-error').addClass('has-error');
                    } else {
                        $('#mncBrandTable-error').text(getI18nText(""));
                        $('#mncBrandTable-error').removeClass('has-error');
                    }

                });


                // MNC Brand - End

                // Operating Costs - Start
                // objectCost = [];
                $("#rhqCostTable tbody").html('');
                try {
                    if (typeof objectCost !== "undefined") {
                        if (objectCost.length > 0) {
                            for (var idx = 0; idx < objectCost.length; idx++) {
                                $("#rhqCostTable tbody").append('<tr><td>' + objectCost[idx]['item'] + '</td><td>' + objectCost[idx]['unitCost'] + '</td><td>' + objectCost[idx]['noOfUnits'] + '</td><td>' + objectCost[idx]['costFrequency'] + '</td><td>' + objectCost[idx]['year2022'] + '</td><td>' + objectCost[idx]['year2023'] + '</td><td>' + objectCost[idx]['year2024'] + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditCostBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');

                            }
                            Array.prototype.sum = function (prop) {
                                var total = 0
                                for (var i = 0, _len = this.length; i < _len; i++) {
                                    total += parseInt(this[i][prop]);
                                }
                                return total
                            }
                            $('#rhqCostTable-totalText').text('Total');
                            $('#rhqCostTable-sum1').text(objectCost.sum("year2022"));
                            $('#rhqCostTable-sum2').text(objectCost.sum("year2023"));
                            $('#rhqCostTable-sum3').text(objectCost.sum("year2024"));
                        }
                    }
                }
                catch (err) {
                    console.log(err);
                }
                editCostIndex = 0;
                CostCurrentRow = null;

                $('#entityAddItem').click(function () {
                    //alert('MNC Branches');
                    var addItemName = $('#addItemName').val();
                    var addUnitCost = $('#addUnitCost').val();
                    var addNoUnits = $('#addNoUnits').val();
                    var addCostFreq = $('#addCostFreq').val();
                    var addYear1 = $('#addYear1').val();
                    var addYear2 = $('#addYear2').val();
                    var addYear3 = $('#addYear3').val();

                    //add branch validation
                    hasCostErrors = false;

                    if (addItemName.length == 0) {
                        $('#addItemName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#addItemName').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addItemName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#addItemName').parents('.form-group').removeClass('has-error');
                    }

                    if (addUnitCost.length == 0) {
                        $('#addUnitCost-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addUnitCost').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addUnitCost-error').text(getI18nText(""));
                        $('#addUnitCost').parents('.form-group').removeClass('has-error');
                    }

                    if (addNoUnits.length == 0) {
                        $('#addNoUnits-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addNoUnits').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addNoUnits-error').text(getI18nText(""));
                        $('#addNoUnits').parents('.form-group').removeClass('has-error');
                    }

                    if (addCostFreq.length == 0) {
                        $('#addCostFreq-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addCostFreq').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addCostFreq-error').text(getI18nText(""));
                        $('#addCostFreq').parents('.form-group').removeClass('has-error');
                    }

                    if (addYear1.length == 0) {
                        $('#addYear1-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addYear1').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addYear1-error').text(getI18nText(""));
                        $('#addYear1').parents('.form-group').removeClass('has-error');
                    }

                    if (addYear2.length == 0) {
                        $('#addYear2-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addYear2').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addYear2-error').text(getI18nText(""));
                        $('#addYear2').parents('.form-group').removeClass('has-error');
                    }

                    if (addYear3.length == 0) {
                        $('#addYear3-error').text(getI18nText("rhq.mandatory.field"));
                        $('#addYear3').parents('.form-group').addClass('has-error');
                        hasCostErrors = true;
                    } else {
                        $('#addYear3-error').text(getI18nText(""));
                        $('#addYear3').parents('.form-group').removeClass('has-error');
                    }


                    if (hasCostErrors) {
                        return false;
                    }
                    else {
                        try {
                        			objectCost.push({
                                                    "item": $('#addItemName').val(),
                                                    'unitCost': $('#addUnitCost').val(),
                                                    'noOfUnits': $('#addNoUnits').val(),
                                                    "costFrequency": $('#addCostFreq').val(),
                                                    'year2022': $('#addYear1').val(),
                                                    'year2023': $('#addYear2').val(),
                                                    'year2024': $('#addYear3').val()
                                                });

                                                if(objectCost.length <1){
                                                    $('#rhqCostTable-error').text(getI18nText("rhq.estimatied.operating.cost.validation"));
                                                    $('#rhqCostTable-error').addClass('has-error');
                                                } else {
                                                    $('#rhqCostTable-error').text(getI18nText(""));
                                                    $('#rhqCostTable-error').removeClass('has-error');
                                                }

                                                console.log(objectCost.length);

                                            }
                                       catch (err) {
                                           console.log(err);
                                       }
                        console.log(JSON.stringify(objectCost).replace(/"/g, "'"));
                        $("#rhqCostTable tbody").append('<tr><td>' + addItemName + '</td><td>' + addUnitCost + '</td><td>' + addNoUnits + '</td><td>' + addCostFreq + '</td><td>' + addYear1 + '</td><td>' + addYear2 + '</td><td>' + addYear3 + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditCostBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');
                        $('#addItemForm')[0].reset();
                        calculateCostTotal();


                    }

                });




                $('#rhqCostTable').on('click', '#EditCostBtn', function () {
                    $('#EditrhqCostTable').modal('show');
                    CostCurrentRow = $(this).parents('tr');
                    editCostIndex = $(this).closest('tr').index();
                    let idx = $(this).closest('tr').index();
                    $('#editItemName').val(objectCost[idx]['item']);
                    $('#editUnitCost').val(objectCost[idx]['unitCost']);
                    $('#editNoUnits').val(objectCost[idx]['noOfUnits'])
                    $('#editCostFreq').val(objectCost[idx]['costFrequency']);
                    $('#editYear1').val(objectCost[idx]['year2022'])
                    $('#editYear2').val(objectCost[idx]['year2023'])
                    $('#editYear3').val(objectCost[idx]['year2024'])
                });



                $('#entityEditItem').click(function () {


                    //edit cost validation
                    var editItemName = $('#editItemName').val();
                    var editUnitCost = $('#editUnitCost').val();
                    var editNoUnits = $('#editNoUnits').val();
                    var editCostFreq = $('#editCostFreq').val();
                    var editYear1 = $('#editYear1').val();
                    var editYear2 = $('#editYear2').val();
                    var editYear3 = $('#editYear3').val();

                    hasEditCostErrors = false;

                    if (editItemName.length == 0) {
                        $('#editItemName').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.mandatory.field"));
                        $('#editItemName').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editItemName').parents('.formInputBox').find('.help-block').text(getI18nText(""));
                        $('#editItemName').parents('.form-group').removeClass('has-error');
                    }

                    if (editUnitCost.length == 0) {
                        $('#editUnitCost-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editUnitCost').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editUnitCost-error').text(getI18nText(""));
                        $('#editUnitCost').parents('.form-group').removeClass('has-error');
                    }

                    if (editNoUnits.length == 0) {
                        $('#editNoUnits-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editNoUnits').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editNoUnits-error').text(getI18nText(""));
                        $('#editNoUnits').parents('.form-group').removeClass('has-error');
                    }

                    if (editCostFreq.length == 0) {
                        $('#editCostFreq-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editCostFreq').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editCostFreq-error').text(getI18nText(""));
                        $('#editCostFreq').parents('.form-group').removeClass('has-error');
                    }

                    if (editYear1.length == 0) {
                        $('#editYear1-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editYear1').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editYear1-error').text(getI18nText(""));
                        $('#editYear1').parents('.form-group').removeClass('has-error');
                    }

                    if (editYear2.length == 0) {
                        $('#editYear2-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editYear2').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editYear2-error').text(getI18nText(""));
                        $('#editYear2').parents('.form-group').removeClass('has-error');
                    }


                    if (editYear3.length == 0) {
                        $('#editYear3-error').text(getI18nText("rhq.mandatory.field"));
                        $('#editYear3').parents('.form-group').addClass('has-error');
                        hasEditCostErrors = true;
                    } else {
                        $('#editYear3-error').text(getI18nText(""));
                        $('#editYear3').parents('.form-group').removeClass('has-error');
                    }



                    if (hasEditCostErrors) {
                        return false;
                    }
                    else {
                        let updateditem = {
                            "item": $('#editItemName').val(),
                            'unitCost': $('#editUnitCost').val(),
                            'noOfUnits': $('#editNoUnits').val(),
                            "costFrequency": $('#editCostFreq').val(),
                            'year2022': $('#editYear1').val(),
                            'year2023': $('#editYear2').val(),
                            'year2024': $('#editYear3').val()
                        };
                        $("#rhqCostTable  > tbody").find($(CostCurrentRow)).replaceWith('<tr><td>' + editItemName + '</td><td>' + editUnitCost + '</td><td>' + editNoUnits + '</td><td>' + editCostFreq + '</td><td>' + editYear1 + '</td><td>' + editYear2 + '</td><td>' + editYear3 + '</td><td><span type="" class=" btn_link iconElement iconElement_edit02 edit_btn_click"  id="EditCostBtn"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089">  <g id="edite-icon" transform="translate(-709.429 -1490.767)">    <g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)">      <g id="Group_1111" data-name="Group 1111">        <path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>        <line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>        <line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>      </g>      <line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line>    </g>  </g></svg></span><span  class=" btn_link iconElement iconElement_edit02 delete_btn_click"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">               <path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path>           </svg></span></td></tr>');

                        objectCost[editCostIndex] = updateditem;
                        console.log(objectCost.length);
                        calculateCostTotal();


                    }

                });


                // Array Remove - By John Resig (MIT Licensed)
                Array.prototype.remove = function (from, to) {
                    var rest = this.slice((to || from) + 1 || this.length);
                    this.length = from < 0 ? this.length + from : from;
                    return this.push.apply(this, rest);
                };

                $('#rhqCostTable').on('click', '.delete_btn_click', function () {
                    console.log("before removed" + objectCost.length);
                    console.log($(this).html());
                    console.log($(this).index());
                    console.log('going to delete the index..' + $(this).closest('tr').index());
                    objectCost.remove($(this).closest('tr').index());
                    $(this).parents("tr").remove();
                    console.log("after removed" + objectCost.length);
                    calculateCostTotal();

                    if(objectCost.length <1){
                        $('#rhqCostTable-error').text(getI18nText("rhq.estimatied.operating.cost.validation"));
                        $('#rhqCostTable-error').addClass('has-error');
                    } else {
                        $('#rhqCostTable-error').text(getI18nText(""));
                        $('#rhqCostTable-error').removeClass('has-error');
                    }

                });

                Array.prototype.sum = function (prop) {
                    var total = 0
                    for (var i = 0, _len = this.length; i < _len; i++) {
                        total += parseInt(this[i][prop]);
                    }
                    return total
                }

                calculateCostTotal = function () {
                    if (objectCost.length < 1) {
                        $('#rhqCostTable-totalText').text('');
                        $('#rhqCostTable-sum1').text('');
                        $('#rhqCostTable-sum2').text('');
                        $('#rhqCostTable-sum3').text('');
                    }
                    else {
                        $('#rhqCostTable-totalText').text('Total');
                        $('#rhqCostTable-sum1').text(objectCost.sum("year2022"));
                        $('#rhqCostTable-sum2').text(objectCost.sum("year2023"));
                        $('#rhqCostTable-sum3').text(objectCost.sum("year2024"));
                    }

                };

                // Operating costs - End





        		//self.licenseInformationSection.find('input[name=isEntrepreneur]').val("no").trigger('change');
        		//self.licenseInformationSection.find('input[name=isPreApprovalNumber]').val("no").trigger('change');
				//self.attachmentSection.show();
       			 //self.branchAttachment.show();
				//self.bindRHQCountriesLimitEvent();
				self.licenseInformationSection.hide();
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
        	//	self.setRHQActivities();
				self.setRHQLegalStatus("setRHQLegalStatusValues");
                self.setCorporateActivities();
                self.setRegionRHQ();
                self.setStrategicActivities();
                self.setManagementActivities();
                self.setrhqCenterAdmin();
                self.setSelectedManagementActivities();
                self.setRhqSubsidiaryPresence();
                self.setSelectedRhqCountries();
				self.entrepreneurAttachment.hide();
        	}
            else{
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


       //   self.updateSelectValues($("#branchInformationRhqCountry"));
        //self.CountryUpdateSelectValues($("#branchInformationRhqCountry"));
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


 setRHQLegalStatus: function (status)
 {
     countyList =[];
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
                 var previousLegalStatus = legalStatus.attr('data-value');
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                countyList = jsonData.countries;
                jsonData.legalStatus.forEach(function (currentValue) {
                    if (status == "setRHQLegalStatusValues") {
                        if (currentValue.legalStatus == "LLC" || currentValue.legalStatus == "ILLC" || currentValue.legalStatus == "BRFC") { legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false)); }
                        legalStatus.attr("disabled", false);
                      //  legalStatus.val($('#basicInformationExtendedLegalStatus').select2('data')).trigger("blur").trigger("change").next().addClass('select2Container_selected'); //hardcoded
                    }
                    if (status == "resetRHQLegalStatusValues") {
                        legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                    }
                });

                legalStatus.attr("disabled", false);
               if(previousLegalStatus) {
                	legalStatus.val(previousLegalStatus).trigger("blur").trigger('change');
                } else {
                	legalStatus.val(null);
                }
               //console.log(previousLegalStatus);
              // console.log($('#basicInformationExtendedLegalStatus').select2('data'));

              //set country values
              var addBrandCountry = $("#addBrandCountry");
              addBrandCountry.find("option").remove();
              addBrandCountry.append(new Option("", "", false, false));

              var editBrandCountry = $("#editBrandCountry");
              editBrandCountry.find("option").remove();
              editBrandCountry.append(new Option("", "", false, false));

              var addBranchCountry = $("#addBranchCountry");
              addBranchCountry.find("option").remove();
              addBranchCountry.append(new Option("", "", false, false));

              var editBranchCountry = $("#editBranchCountry");
              editBranchCountry.find("option").remove();
              editBranchCountry.append(new Option("", "", false, false));

              var branchInformationRhqCountry = $("#branchInformationRhqCountry");
              //branchInformationRhqCountry.find("option").remove();



              jsonData.countries.forEach(function (currentValue) {
                branchInformationRhqCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                addBrandCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                editBrandCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                addBranchCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                editBranchCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));


            });

            $("#branchInformationRhqCountry").multiselect({
                title: "Select Options",
                modalSize: "md"
            });
          //  self.updateSelectValues($("#branchInformationRhqCountry"));
          //  self.CountryUpdateSelectValues($("#branchInformationRhqCountry"));
             addBranchCountry.val(null);


		}});
	},
     // /dropdownsQeemah1/corporateActivities
    setCorporateActivities: function () {
        var self = this;


        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/corporateActivities",
            {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    $('rhqCheckbox').append(new Option("", "", false, false));
                    jsonData.forEach(function (currentValue) {
                        $('#rhqCheckbox').append('<option value=' + currentValue.id + '>' + currentValue.details + '</option>');
                    });

                    if ($("#rhqCheckbox").data('value') != "" && $("#rhqCheckbox").data('value') != undefined && $("#rhqCheckbox").data('value')) {
                     //   console.log("received data.." + $("#rhqCheckbox").data('value'));
                        var selectedDataString = String($("#rhqCheckbox").data('value'));
                        var selectedData = selectedDataString.split(',');
                        selectedData.forEach(function (currentValue) {
                            $("#rhqCheckbox").multiselect("selectOption", currentValue);
                        });
                    }
                    setTimeout(function () {
                    $("#rhqCheckbox").multiselect({
                        title: "Select Options",
                        modalSize: "md"
                    });},500);

                }
            });
    },

    setRegionRHQ: function () {
        var self = this;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/rhqRegions",
            {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    var RhqRegionsSection  = self.basicInformationExtendedSection.find("#branchInformationRhqRegionsSection");
                    RhqRegionsSection .find("option").remove();
                    RhqRegionsSection .append(new Option("", "", false, false));
                    jsonData.forEach(function (currentValue) {
                        $('#branchInformationRhqRegionsSection').append('<option value='+currentValue.region+'>'+currentValue.regionText+'</option>');
                    });

                    if ($("#branchInformationRhqRegionsSection").data('value') != "" && $("#branchInformationRhqRegionsSection").data('value') != undefined && $("#branchInformationRhqRegionsSection").data('value')) {
                        //   console.log("received data.." + $("#rhqCheckbox").data('value'));
                           var selectedDataString = String($("#branchInformationRhqRegionsSection").data('value'));
                           var selectedData = selectedDataString.split(',');
                           selectedData.forEach(function (currentValue) {
                               $("#branchInformationRhqRegionsSection").multiselect("selectOption", currentValue);
                           });
                       }
                       $("#branchInformationRhqRegionsSection").multiselect({
                           title: "Select Options",
                           modalSize: "md"
                       });

                }
            });
    },



        // /dropdownsQeemah1/strategicActivities
        setStrategicActivities: function () {
            var self = this;
            $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/strategicActivities",
                {
                    type: "GET",
                    responseType: "application/json;charset=utf-8",
                    contentType: "application/json;charset=utf-8",
                    cache: false,
                    success: function (data) {
                        var jsonData = JSON.parse(data);
                        var strategicActivities = self.basicInformationExtendedSection.find("#rhqStrategicCheckbox");
                        strategicActivities.find("option").remove();
                        strategicActivities.append(new Option("", "", false, false));
                        jsonData.forEach(function (currentValue) {
                            $('#rhqStrategicCheckbox').append('<option value='+currentValue.id+'>'+currentValue.details+'</option>');
                        });

                        if ($("#rhqStrategicCheckbox").data('value') != "" && $("#rhqStrategicCheckbox").data('value') != undefined && $("#rhqStrategicCheckbox").data('value')) {
                            //   console.log("received data.." + $("#rhqCheckbox").data('value'));
                               var selectedDataString = String($("#rhqStrategicCheckbox").data('value'));
                               var selectedData = selectedDataString.split(',');
                               selectedData.forEach(function (currentValue) {
                               //    console.log('inside rhqStrategicCheckbox loop..' + currentValue);
                                   $("#rhqStrategicCheckbox").multiselect("selectOption", currentValue);
                               });
                           }

                        $("#rhqStrategicCheckbox").multiselect({
                            title: "Select Options",
                            modalSize: "md"
                        });


                    }
                });
        },

              // /dropdownsQeemah1/managementActivities
              setManagementActivities: function () {
                var self = this;
                $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/managementActivities",
                    {
                        type: "GET",
                        responseType: "application/json;charset=utf-8",
                        contentType: "application/json;charset=utf-8",
                        cache: false,
                        success: function (data) {
                            var jsonData = JSON.parse(data);
                            var managementActivities = self.basicInformationExtendedSection.find("#rhqManagementFunCheckbox");
                            managementActivities.find("option").remove();
                            managementActivities.append(new Option("", "", false, false));
                            jsonData.forEach(function (currentValue) {
                                $('#rhqManagementFunCheckbox').append('<option value='+currentValue.id+'>'+currentValue.details+'</option>');
                            });
                         //   self.updateSelectValues($("#rhqManagementFunCheckbox"));
                        }
                    });
    }, setSelectedManagementActivities: function () {
        var self = this;
        try {
            managementActivitiesParsedList = []
            if(typeof listOfManagementActivitiesInJS !== "undefined" && listOfManagementActivitiesInJS !== "")
             {
                var selectedManagementListSplit1 = listOfManagementActivitiesInJS.split("[");
                var selectedManagementListSplit2 = selectedManagementListSplit1[1].split("]");
                var selectedManagementList = selectedManagementListSplit2[0];
                $.each(selectedManagementList.split(","), function (i, e) {
                    managementActivitiesParsedList.push(e.trimLeft())

                });
            }


            setTimeout(function () {
                managementActivitiesParsedList.forEach(function (currentValue) {
                    $("#rhqManagementFunCheckbox").multiselect("selectOption", currentValue);
                });


                $("#rhqManagementFunCheckbox").multiselect({
                    title: "Select Options",
                    modalSize: "md"
                });
            },1500);

        }
        catch (err) {
            console.log(err);
        }
    },setrhqCenterAdmin: function () {
        var self = this;
        try {
            RHQCenterfinalParsedList = []
            if(typeof rhqCenterAdminInJS !== "undefined" && rhqCenterAdminInJS !== "")
            {
                        var selectedrhqCenterAdminSplit1 = rhqCenterAdminInJS.split("[");
                        var selectedrhqCenterAdminSplit2 = selectedrhqCenterAdminSplit1[1].split("]");
                        var selectedRhqCenterAdmin = selectedrhqCenterAdminSplit2[0];
                        $.each(selectedRhqCenterAdmin.split(","), function (i, e) {
                            RHQCenterfinalParsedList.push(e.trimLeft())

                        });
            }


            setTimeout(function () {
                try{
                    RHQCenterfinalParsedList.forEach(function (currentValue) {
                        console.log("Middle_East_ME");
                        if(currentValue == "Middle_East_ME" || currentValue == "Middle East (ME)"){
                            $("#rhqCenterAdmin").multiselect("selectOption", "Middle_East_ME");
                        }
                        else{
                            console.log("currentValue");
                            $("#rhqCenterAdmin").multiselect("selectOption", currentValue);
                        }

                    });
                }
                catch(e){console.log(e);}

                $("#rhqCenterAdmin").multiselect({
                    title: "Select Options",
                    modalSize: "md"
                });
            },1500);

        }
        catch (err) {
            console.log(err);
        }

      },setRhqSubsidiaryPresence: function () {
        var self = this;
        try {
            if(rhqSubsidiaryPresenceInJS != "" &&  rhqSubsidiaryPresenceInJS != undefined){
                $("input[type=radio][name=rhqSubsidiaryPresence][value=" + rhqSubsidiaryPresenceInJS + "]").prop('checked', true);
            }
        }
        catch (err) {
            console.log(err);
        }
    },setSelectedRhqCountries: function () {

                  setTimeout(function () {
                      var self = this;
                      try {
                          selectedRhqCountrieseList = []
                          var listOfSelectedRhqCountriesInJSSplit1 = listOfRhqCountriesInJS.split("[");
                          var listOfSelectedRhqCountriesInJSSplit2 = listOfSelectedRhqCountriesInJSSplit1[1].split("]");
                          var selectedRhqCountriesInJSSplit = listOfSelectedRhqCountriesInJSSplit2[0];
                          $.each(selectedRhqCountriesInJSSplit.split(","), function (i, e) {
                              selectedRhqCountrieseList.push(e.trimLeft())

                          });
                          console.log(selectedRhqCountrieseList);
                          if (typeof selectedRhqCountrieseList !== "undefined") {
                              if (selectedRhqCountrieseList.length > 0) {
                                  selectedRhqCountrieseList.forEach(function (currentValue) {
                                      $("#branchInformationRhqCountry").multiselect("selectOption", currentValue);
                                  });

                              }
                          }


                      }
                      catch (err) {
                          console.log(err);
                      }
                  }, 2900);


    },

    resetRHQActivities: function () {

    	var isEntrepreneur = $('[name=isEntrepreneur]').filter(':checked').val();
    	var isPreApprovalNumber = $('[name=isPreApprovalNumber]').filter(':checked').val();
    	var self = this;
        $('#branchAttachmentNewRhq').css("display", "none");

    	if(isEntrepreneur === 'yes'){
    		self.loadYearsDropDownForEntrepreneur();
    		self.licenseYearSection.find("#licenseYear").val("1").prop("disabled", true).trigger('change');
            this.attachmentSection.show();
        	this.entrepreneurAttachment.show();
    	}else{
    		self.loadYearsDropDown();
    	}

        if(isPreApprovalNumber === 'yes'){
    	         self.preApprovalNumberYesEvent();
    	}else{
            self.preApprovalNumberNoEvent();
    	}


		self.branchInformationSection.find('input[name=isMoreThan2Branch]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityListedInStockMarket]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityRevenueMoreThanThreshold]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isEntityAssetMoreThanThreshold]').prop("disabled", true);
		self.branchInformationSection.find('input[name=isMoreThan6Branch]').prop("disabled", true);
		self.branchInformationSection.find('select[id=branchInformationRhqRegionsSection]').clearInputs();
		self.branchInformationSection.find('select[id=branchInformationRhqCountry]').clearInputs();
        $('.rqh-tables').css("display", "none");
        $('.rhqSelectBoxes').css("display", "none");
        self.setRHQLegalStatus("resetRHQLegalStatusValues");
        // $('#rhqCheckbox').html('');
        // $('#rhqStrategicCheckbox').html('');
        // $('#rhqManagementFunCheckbox').html('');
        // $('#branchInformationRhqRegionsSection').html('');
        try{
           var existingItems_rhqCheckbox =  $("#rhqCheckbox").val();
           existingItems_rhqCheckbox.forEach(function (currentValue) {
            $("#rhqCheckbox").multiselect("deselectOption", currentValue);
           });

        }catch(e){console.log(e)}

        try{
            var existingItems_rhqStrategicCheckbox =  $("#rhqStrategicCheckbox").val();
            existingItems_rhqStrategicCheckbox.forEach(function (currentValue) {
             $("#rhqStrategicCheckbox").multiselect("deselectOption", currentValue);
            });

         }catch(e){console.log(e)}

         try{
            var existingItems_rhqManagementFunCheckbox =  $("#rhqManagementFunCheckbox").val();
            existingItems_rhqManagementFunCheckbox.forEach(function (currentValue) {
             $("#rhqManagementFunCheckbox").multiselect("deselectOption", currentValue);
            });

         }catch(e){console.log(e)}

         try{
            var existingItems_branchInformationRhqRegionsSection =  $("#branchInformationRhqRegionsSection").val();
            existingItems_branchInformationRhqRegionsSection.forEach(function (currentValue) {
             $("#branchInformationRhqRegionsSection").multiselect("deselectOption", currentValue);
            });

         }catch(e){console.log(e)}

         try{
            var existingItems_branchInformationRhqCountry =  $("#branchInformationRhqCountry").val();
            existingItems_branchInformationRhqCountry.forEach(function (currentValue) {
             $("#branchInformationRhqCountry").multiselect("deselectOption", currentValue);
            });

         }catch(e){console.log(e)}

         $('input[name="rhqSubsidiaryPresence"]').prop('checked', false);



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
        this.preApprovalNrAttachment.hide();
		this.attachmentSection.show();
       	this.loadYearsDropDownForRHQ();


	}else{
        	this.loadYearsDropDown();
			//this.attachmentSection.hide();
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
            if ($('#entityForm #licenseTypes').val() == "11") {

                console.log(JSON.stringify(objectBranches).replace(/"/g, "'"));
                $('.mncBranchTableJsonInputs').html('');
                var element = $('<input/>').prop('type', 'hidden').prop('name', 'entitiesManagedByRhq').val(JSON.stringify(objectBranches).replace(/"/g, "'"));
                $('#entityForm .mncBranchTableJsonInputs').append(element);

                console.log(JSON.stringify(objectBrands).replace(/"/g, "'"));
                $('.mncBrandTableJsonInputs').html('');
                var element = $('<input/>').prop('type', 'hidden').prop('name', 'brandPresenceInMENARegion').val(JSON.stringify(objectBrands).replace(/"/g, "'"));
                $('#entityForm .mncBrandTableJsonInputs').append(element);

                console.log(JSON.stringify(objectCost).replace(/"/g, "'"));
                $('.mncCostTableJsonInputs').html('');
                var element = $('<input/>').prop('type', 'hidden').prop('name', 'estimatedOperatingCostForRhq').val(JSON.stringify(objectCost).replace(/"/g, "'"));
                $('#entityForm .mncCostTableJsonInputs').append(element);
                //print form values

                data_array = $("#entityForm").serialize();
                console.log(data_array);



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
            element.siblings('.select2.select2-container').addClass("select2Container_selected");
            element.val(element.data('value')).trigger('change');
        }
    },
    updateSelectValuesnewUI: function (element) {
        if (element.data('value') != "" && element.data('value') != undefined && element.data('value')) {

            element.siblings('.select2.select2-container').addClass("select2Container_selected");
            element.val(element.data('value')).trigger('change');
        }
    },
    CountryUpdateSelectValues: function (element) {
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

        //Deenz RHQ- JS Validation - Start
        if(self.licenseTypeSection.find('#licenseTypes').val() == "11"){
        if ($('#rhqCheckbox').val().length < 3) {
            hasErrors = true;
            $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.corporate.activity.validation"));
            $('#rhqCheckbox').parents('.form-group').addClass('has-error');
        } else {
            $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#rhqCheckbox').parents('.form-group').removeClass('has-error');
        }

        if($('#rhqStrategicCheckbox').val().length <5){
            hasErrors = true;
            $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.strategic.activity.validation"));
            $('#rhqStrategicCheckbox').parents('.form-group').addClass('has-error');
        } else {
            $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#rhqStrategicCheckbox').parents('.form-group').removeClass('has-error');
        }

        if($('#rhqManagementFunCheckbox').val().length <7){
            hasErrors = true;
            $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.management.activity.validation"));
            $('#rhqManagementFunCheckbox').parents('.form-group').addClass('has-error');
        } else {
            $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#rhqManagementFunCheckbox').parents('.form-group').removeClass('has-error');
        }

        if($('#branchInformationRhqRegionsSection').val().length <1){
            hasErrors = true;
            $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.region.validation"));
            $('#branchInformationRhqRegionsSection').parents('.form-group').addClass('has-error');
        } else {
            $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#branchInformationRhqRegionsSection').parents('.form-group').removeClass('has-error');
        }




        if (typeof ($('input[type=radio][name=rhqSubsidiaryPresence]:checked').val()) == "undefined") {
            hasErrors = true;
                $('#rhqSubsidiaryPresence').parents('.formRadioButton').find('.help-block').text(getI18nText("rhq.subsidiary.presence.validation"));
                $('#rhqSubsidiaryPresence').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqSubsidiaryPresence').parents('.formRadioButton').find('.help-block').text(getI18nText(""));
                $('#rhqSubsidiaryPresence').parents('.form-group').removeClass('has-error');
            }


        if($('#rhqCenterAdmin').val().length <1){
            hasErrors = true;
            $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.center.of.administration.validation"));
            $('#rhqCenterAdmin').parents('.form-group').addClass('has-error');
        } else {
            $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#rhqCenterAdmin').parents('.form-group').removeClass('has-error');
        }


        if(objectBranches.length <1){
            hasErrors = true;
            $('#mncBranchTable-error').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.entities.managed.by.rhq.validation"));
            $('#mncBranchTable-error').parents('.form-group').addClass('has-error');
        } else {
            $('#mncBranchTable-error').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#mncBranchTable-error').parents('.form-group').removeClass('has-error');
        }

        if(objectBrands.length <1){
             hasErrors = true;
             $('#mncBrandTable-error').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.brand.presence.validation"));
             $('#mncBrandTable-error').parents('.form-group').addClass('has-error');
         } else {
             $('#mncBrandTable-error').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
             $('#mncBrandTable-error').parents('.form-group').removeClass('has-error');
         }

         if(objectCost.length <1){
             hasErrors = true;
             $('#rhqCostTable-error').parents('.formInputBox').find('.help-block').text(getI18nText("rhq.estimatied.operating.cost.validation"));
             $('#rhqCostTable-error').parents('.form-group').addClass('has-error');
         } else {
             $('#rhqCostTable-error').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
             $('#rhqCostTable-error').parents('.form-group').removeClass('has-error');
         }

            entityForm.find('#entityFinancialStatementFile').each(function () {
                if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                    hasErrors = true;
                    $('#entityFinancialStatementFile-helper').text(getI18nText("rhq.attachment.validation"));
                    $('#entityFinancialStatementFile').parents('.form-group').addClass('has-error');
                }
            })
            entityForm.find('#commercialRegMainEntryFile').each(function () {
                if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                    hasErrors = true;
                    $('#commercialRegMainEntryFile-helper').text(getI18nText("rhq.attachment.validation"));
                    $('#commercialRegMainEntryFile').parents('.form-group').addClass('has-error');
                }
            })
            entityForm.find('#commercialRegBranch1File').each(function () {
                if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                    hasErrors = true;
                    $('#commercialRegBranch1File-helper').text(getI18nText("rhq.attachment.validation"));
                    $('#commercialRegBranch1File').parents('.form-group').addClass('has-error');
                }
            })
            entityForm.find('#commercialRegBranch2File').each(function () {
                if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
                    hasErrors = true;
                    $('#commercialRegBranch2File-helper').text(getI18nText("rhq.attachment.validation"));
                    $('#commercialRegBranch2File').parents('.form-group').addClass('has-error');
                }
            })




        }


        //onchanges validation start on document select , multi select and multi delsect event


        $(document.body).on('change', '#rhqCheckbox', function () {
            console.log('changes detected...');
            if ($('#rhqCheckbox').val().length < 3) {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.corporate.activity.validation"));
                $('#rhqCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCheckbox').parents('.form-group').removeClass('has-error');
            }

        });

        $("#rhqCheckbox").on("deselectiondone.bs.multiselect", function(event) {
            console.log('changes detected...');
            if ($('#rhqCheckbox').val().length < 3) {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.corporate.activity.validation"));
                $('#rhqCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCheckbox').parents('.form-group').removeClass('has-error');
            }
          });

          $("#rhqCheckbox").on("selectiondone.bs.multiselect", function(event) {
            console.log('changes detected...');
            if ($('#rhqCheckbox').val().length < 3) {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.corporate.activity.validation"));
                $('#rhqCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCheckbox').parents('.form-group').removeClass('has-error');
            }
          });



        $(document.body).on('change', '#rhqStrategicCheckbox', function () {
            console.log('changes detected...');
            if ($('#rhqStrategicCheckbox').val().length < 5) {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.strategic.activity.validation"));
                $('#rhqStrategicCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqStrategicCheckbox').parents('.form-group').removeClass('has-error');
            }

        });

        $("#rhqStrategicCheckbox").on("deselectiondone.bs.multiselect", function(event) {
            console.log('changes detected...');
            if ($('#rhqStrategicCheckbox').val().length < 5) {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.strategic.activity.validation"));
                $('#rhqStrategicCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqStrategicCheckbox').parents('.form-group').removeClass('has-error');
            }
          });

          $("#rhqStrategicCheckbox").on("selectiondone.bs.multiselect", function(event) {
            console.log('changes detected...');
            if ($('#rhqStrategicCheckbox').val().length < 5) {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.strategic.activity.validation"));
                $('#rhqStrategicCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqStrategicCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqStrategicCheckbox').parents('.form-group').removeClass('has-error');
            }
          });


        $(document.body).on('change', '#rhqManagementFunCheckbox', function () {

        if($('#rhqManagementFunCheckbox').val().length <7){
            $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.management.activity.validation"));
            $('#rhqManagementFunCheckbox').parents('.form-group').addClass('has-error');
        } else {
            $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
            $('#rhqManagementFunCheckbox').parents('.form-group').removeClass('has-error');
        }

        });

        $("#rhqManagementFunCheckbox").on("deselectiondone.bs.multiselect", function(event) {
            if($('#rhqManagementFunCheckbox').val().length <7){
                $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.management.activity.validation"));
                $('#rhqManagementFunCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqManagementFunCheckbox').parents('.form-group').removeClass('has-error');
            }
          });

          $("#rhqManagementFunCheckbox").on("selectiondone.bs.multiselect", function(event) {
            if($('#rhqManagementFunCheckbox').val().length <7){
                $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.management.activity.validation"));
                $('#rhqManagementFunCheckbox').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqManagementFunCheckbox').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqManagementFunCheckbox').parents('.form-group').removeClass('has-error');
            }
          });



        $(document.body).on('change', '#branchInformationRhqRegionsSection', function () {
            if($('#branchInformationRhqRegionsSection').val().length <1){
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.global.presence.validation"));
                $('#branchInformationRhqRegionsSection').parents('.form-group').addClass('has-error');
            } else {
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#branchInformationRhqRegionsSection').parents('.form-group').removeClass('has-error');
            }


        });

        $("#branchInformationRhqRegionsSection").on("deselectiondone.bs.multiselect", function(event) {
            if($('#branchInformationRhqRegionsSection').val().length <1){
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.global.presence.validation"));
                $('#branchInformationRhqRegionsSection').parents('.form-group').addClass('has-error');
            } else {
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#branchInformationRhqRegionsSection').parents('.form-group').removeClass('has-error');
            }
          });

          $("#branchInformationRhqRegionsSection").on("selectiondone.bs.multiselect", function(event) {
            if($('#branchInformationRhqRegionsSection').val().length <1){
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.global.presence.validation"));
                $('#branchInformationRhqRegionsSection').parents('.form-group').addClass('has-error');
            } else {
                $('#branchInformationRhqRegionsSection').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#branchInformationRhqRegionsSection').parents('.form-group').removeClass('has-error');
            }
          });




        $('input[type=radio][name=rhqSubsidiaryPresence]').change(function() {
            if (typeof ($('input[type=radio][name=rhqSubsidiaryPresence]:checked').val()) == "undefined") {
                $('#rhqSubsidiaryPresence').parents('.formRadioButton').find('.help-block').text(getI18nText("rhq.subsidiary.presence.validation"));
                $('#rhqSubsidiaryPresence').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqSubsidiaryPresence').parents('.formRadioButton').find('.help-block').text(getI18nText(""));
                $('#rhqSubsidiaryPresence').parents('.form-group').removeClass('has-error');
            }
        });

        $(document.body).on('change', '#rhqCenterAdmin', function () {
            if($('#rhqCenterAdmin').val().length <1){
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.center.of.administration.validation"));
                $('#rhqCenterAdmin').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCenterAdmin').parents('.form-group').removeClass('has-error');
            }

        });

        $("#rhqCenterAdmin").on("deselectiondone.bs.multiselect", function(event) {
            if($('#rhqCenterAdmin').val().length <1){
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.center.of.administration.validation"));
                $('#rhqCenterAdmin').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCenterAdmin').parents('.form-group').removeClass('has-error');
            }
          });

          $("#rhqCenterAdmin").on("selectiondone.bs.multiselect", function(event) {
            if($('#rhqCenterAdmin').val().length <1){
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText("rhq.center.of.administration.validation"));
                $('#rhqCenterAdmin').parents('.form-group').addClass('has-error');
            } else {
                $('#rhqCenterAdmin').parents('.formSelectBox').find('.help-block').text(getI18nText(""));
                $('#rhqCenterAdmin').parents('.form-group').removeClass('has-error');
            }
          });


        //onchanges validation End
        //Deenz- JS Validation - End

        if (!entityForm.find('[name=hasGCCNationality]').filter(':checked').val()) {

            hasErrors = true;
        }

        if (entityForm.find('[name=hasAdvanceLicenseNr]').filter(':checked').val() === 'yes' && !entityForm.find('[name=advanceLicenseNr]').val()) {
            hasErrors = true;
            entityForm.find('[name=advanceLicenseNr]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.entityInfo.advanceLicenseNumber.error"));
            entityForm.find('[name=advanceLicenseNr]').parents('.form-group').addClass('has-error');
        }

        if($("#licenseTypes").val() != "11"){
            if (entityForm.find('[name=isPreApprovalNumber]').filter(':checked').val() === 'yes' && !entityForm.find('[name=preApprovalNumber]').val()) {
            hasErrors = true;
                entityForm.find('[name=preApprovalNumber]').parents('.formInputBox').find('.help-block').text(getI18nText("validate.licenseApply.entityInfo.advanceLicenseNumber.error"));
                entityForm.find('[name=preApprovalNumber]').parents('.form-group').addClass('has-error');
            }
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
            //  entityForm.find('#mainBranchCRFile, #otherBranchCR1File, #otherBranchCR2File').each(function () {
            // 		 if (SAGIA.licenseApply.validateFileTypeAndSize($(this)) === false) {
            //              hasErrors = true;
            //          }
            //  })
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
		// if (entityForm.find('[name=isEntityListedInStockMarket]').filter(':checked').val() === "no" &&
		// 	entityForm.find('[name=isEntityRevenueMoreThanThreshold]').filter(':checked').val() === "no" &&
		// 	entityForm.find('[name=isEntityAssetMoreThanThreshold]').filter(':checked').val() === "no" &&
		// 	entityForm.find('[name=isMoreThan6Branch]').filter(':checked').val() === "no"
		// ) {
		// 	entityForm.find('[name=isEntityListedInStockMarket]').parents('.form-group').addClass('has-error');
		// 	entityForm.find('[name=isEntityRevenueMoreThanThreshold]').parents('.form-group').addClass('has-error');
		// 	entityForm.find('[name=isEntityAssetMoreThanThreshold]').parents('.form-group').addClass('has-error');
		// 	entityForm.find('[name=isMoreThan6Branch]').parents('.form-group').addClass('has-error');
        //     self.errorForBranchSectionMandatory();
		//     hasErrors = true;
		// 	}
		// if(entityForm.find('#branchInformationRhqCountry').val().length < 8){
        // 	entityForm.find('[id=branchInformationRhqCountry]').parents('.formSelectBox').find('.help-block').text(getI18nText("validation.rhq.countries.minimum"));
        // 	entityForm.find('[id=branchInformationRhqCountry]').parents('.form-group').addClass('has-error');
		// 	hasErrors = true;
		// 	}

        // if(entityForm.find('#branchInformationRhqRegionsSection').val().length < 1){
        //  entityForm.find('[id=branchInformationRhqRegionsSection]').parents('.form-group').addClass('has-error');
        //   hasErrors = true;
		// }
		// if(entityForm.find('[name=isMoreThan2Branch]').filter(':checked').val() === "no"){
		// 	self.errorForMoreThan2Branches();
		// 	 hasErrors = true;
		// }


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
