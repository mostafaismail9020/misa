var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = {
    data: {
        qeemah: undefined,
        qeemahChannel:SAGIA.config.regularQeemahChannel,
        qeemah1Data: {
            newShareholders: [],
            existingShareholders: [],
            contactPerson: {}
        },
        qeemah2Data: {
            shareholders: [],
            contactPerson: {}
        }
    }
};
$(function() {
    SAGIA.license.apply.tabs = $(".js-panelTabs");

    var licenceApplyDraftServiceId = 'ZS16';
    var entityInformationTabData = $("#entityInformationTabData");
    var shareholdersTabData = $("#shareholdersTabData");
    var contactPersonTabData = $("#contactPersonTabData");

    var errorResponseModal = $('#errorResponseModal');
    var infoResponseModal = $("#infoResponseModal");

    $("#saveDraftButton").on("click", function() {

        var pathNameURL = window.location.pathname;
        if (window.location.href.indexOf("?") >= 0) {
            var indexOfQuestion = window.location.href.indexOf("?");
            var parametersString = window.location.href.substr(indexOfQuestion);
            pathNameURL = pathNameURL + parametersString;
        }
       
        SAGIA.license.apply.data.businessActivities.businessType = SAGIA.license.businessActivities.businessType;
        $.ajax(ACC.config.encodedContextPath + "/draft/save-json", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            method: 'POST',
            data: JSON.stringify({
                serviceId: licenceApplyDraftServiceId,
                url: pathNameURL,
                json: JSON.stringify({
                    data: SAGIA.license.apply.data
                })
            }),
            success: function () {
                $('#loadDraftButton').show();
                infoResponseModal.find(".modal-description").text(getI18nText("general.draft.saved"));
                infoResponseModal.modal('show');
            },
            error: function (error) {
                errorResponseModal.find('.modal-description').text(getI18nText("general.couldnotsavedraft"));
                errorResponseModal.modal('show');
            }
        });

        });

    var reloadFormComponents = function() {
        $.each(SAGIA.formElements._autoload, function(key, value) {
            if($.isArray(value)) {
                if(eval(value[1]) === true) {
                    SAGIA.formElements[value[0]]();
                } else if(eval(value[2]) === true) {
                    SAGIA.formElements[value[2]]()
                }
            } else {
                SAGIA.formElements[value]();
            }
        });
    };
    $("#loadDraftButton").on("click", function () {
        $.ajax(ACC.config.encodedContextPath + "/draft/json?serviceId=" + licenceApplyDraftServiceId, {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (draft) {
                if (draft) {
                    handleLicenseDraftData2(JSON.parse(draft.data));
                }
            },
            error: function() {
                errorResponseModal.find('.modal-description').text(getI18nText("general.couldnot.load.draft"));
                errorResponseModal.modal('show');
            }
        });
    });

    var handleLicenseDraftData = function(draftData) {

        var temp = {arr : draftData.data};
        var draftDataCopy = $.extend(true, {}, temp);

        SAGIA.license.apply.data = draftData.data;
        SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
        SAGIA.license.apply.onChangeTabToEntityInformation();

        entityInformationTabData.empty().html(draftData.layoutEntityInformation);
        shareholdersTabData.empty().html(draftData.layoutShareholders);
        contactPersonTabData.empty().html(draftData.layoutContactPerson);

        SAGIA.license.apply.displayEntityInformationTab();

        $(".select2").remove();
        SAGIA.license.apply.tabs.find("select").each(function() {
            var value = $(this).attr("value");
            if(value) {
                $(this).val(value);
            }
        });
        reloadFormComponents();
        SAGIA.license.apply.tabs.find("select").each(function() {
            var value = $(this).attr("value");
            if(value) {
                $(this).parent().find(".select2").addClass("select2Container_selected");
            }
        });

        $(document).find(".help-block").text("");
        $(document).find(".has-error").removeClass("has-error");

        if(SAGIA.license.apply.data.businessActivities) {
            SAGIA.license.businessActivities.licenseType = SAGIA.license.apply.data.businessActivities.licenseType;
            SAGIA.license.businessActivities.selectedSection = SAGIA.license.apply.data.businessActivities.selectedSection;
            SAGIA.license.businessActivities.selectedSectionName = SAGIA.license.apply.data.businessActivities.selectedSectionName;
            SAGIA.license.businessActivities.selectedDivisions = SAGIA.license.apply.data.businessActivities.selectedDivisions;
            SAGIA.license.businessActivities.selectedGroups = SAGIA.license.apply.data.businessActivities.selectedGroups;
            SAGIA.license.businessActivities.selectedClasses = SAGIA.license.apply.data.businessActivities.selectedClasses;
            SAGIA.license.businessActivities.selectedBranches = SAGIA.license.apply.data.businessActivities.selectedBranches;
            SAGIA.license.businessActivities.selectedActivities = SAGIA.license.apply.data.businessActivities.selectedActivities;
        }

        if (SAGIA.license.apply.data.licenseType) {
            SAGIA.license.businessActivities.setLicenseType(SAGIA.license.apply.data.licenseType, SAGIA.license.apply.data.licenseTypeName);
        }

        SAGIA.license.apply.data.basicInformationExtended = draftDataCopy.arr.basicInformationExtended;
        SAGIA.license.apply.data.basicInformation = draftDataCopy.arr.basicInformation;
        SAGIA.license.apply.handleProductsSearch();
        SAGIA.formElements.placeholderPolyfill();
    };


    var handleLicenseDraftData2 = function(draftData) {
        
    	
        var temp = {arr : draftData};
        var draftDataCopy = $.extend(true, {}, temp);

        SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
        SAGIA.license.apply.onChangeTabToEntityInformation();

        SAGIA.license.apply.data = draftData.data;

        SAGIA.license.apply.draft = SAGIA.license.apply.draft || {};
        SAGIA.license.apply.draft.isPresent = true;
        SAGIA.license.apply.draft.technicalAndFinancialCriteriaQuestions = SAGIA.license.apply.data.technicalAndFinancialCriteriaQuestions;

        setEntityInformationTabData();

        setDelegateDefaultValueIfMissingInDraft();

        $(document).find(".help-block").text("");
        $(document).find(".has-error").removeClass("has-error");



        SAGIA.license.apply.handleProductsSearch();
        SAGIA.formElements.placeholderPolyfill();
    };

    var  setEntityInformationTabData = function () {

        var licenseInformationSection = $("#licenseInformationSection");
        var stockMarketSection = $("#stockMarketSection");
        var onStockMarketSection = $("#onStockMarketSection");
        var basicInformationExtendedSection = $("#basicInformationExtendedSection");
        var basicInformationSection = $("#basicInformationSection");

        var hasSagiaLicenseSection = $("#hasSagiaLicenseSection");
        //after load draft has GCCNationality will be always set as NO   --- to add a flag when nothing is selected
        hasSagiaLicenseSection.find("#hasGCCNationalityNO").trigger("click");
        SAGIA.license.apply.data.hasSagiaLicense ? hasSagiaLicenseSection.find("#hasSagiaLicenseYES").trigger("click")
            : hasSagiaLicenseSection.find("#hasSagiaLicenseNO").trigger("click");

        var hasShareholdersTypePerson = $("#hasShareholdersTypePerson");
        SAGIA.license.apply.data.hasShareholdersTypePerson ? hasShareholdersTypePerson.find("#hasShareholdersTypePersonYES").trigger("click")
            : hasShareholdersTypePerson.find("#hasShareholdersTypePersonNO").trigger("click");

        var advanceLicenseNumberSection = $("#advanceLicenseNumberSection");
        if (SAGIA.license.apply.data.hasAdvanceLicenseNr) {
            advanceLicenseNumberSection.find("#hasAdvanceLicenseNrYES").trigger("click");
            advanceLicenseNumberSection.find("#advanceLicenseNumberInput").val(SAGIA.license.apply.data.advanceLicenseNr);
        } else {
            advanceLicenseNumberSection.find("#hasAdvanceLicenseNrNO").trigger("click");
        }

        var temp = {arr : SAGIA.license.apply.data.activityAttachments};
        var activityAttachmentsCopy = $.extend(true, {}, temp);

        if(SAGIA.license.apply.data.businessActivities) { //SAGIA.license.businessActivities.businessType
            SAGIA.license.businessActivities.licenseType = SAGIA.license.apply.data.businessActivities.licenseType;
            SAGIA.license.businessActivities.businessType = SAGIA.license.apply.data.businessActivities.businessType;
            SAGIA.license.businessActivities.selectedSection = SAGIA.license.apply.data.businessActivities.selectedSection;
            SAGIA.license.businessActivities.selectedSectionName = SAGIA.license.apply.data.businessActivities.selectedSectionName;
            SAGIA.license.businessActivities.selectedDivisions = SAGIA.license.apply.data.businessActivities.selectedDivisions;
            SAGIA.license.businessActivities.selectedGroups = SAGIA.license.apply.data.businessActivities.selectedGroups;
            SAGIA.license.businessActivities.selectedClasses = SAGIA.license.apply.data.businessActivities.selectedClasses;
            SAGIA.license.businessActivities.selectedBranches = SAGIA.license.apply.data.businessActivities.selectedBranches;
            SAGIA.license.businessActivities.selectedActivities = SAGIA.license.apply.data.businessActivities.selectedActivities;
            SAGIA.license.apply.data.activityAttachments = activityAttachmentsCopy ;
            SAGIA.license.businessActivities.temporaryLicenseTextBox = SAGIA.license.apply.data.businessActivities.temporaryLicenseTextBox;
           if(SAGIA.license.apply.data.businessActivities.temporaryLicenseTextBox && SAGIA.license.apply.data.licenseType== SAGIA.config.temporaryLicenseConstant){

            	$("#temporaryLicenseTextBox").show();
            	$("#noBusinessActivitiesSelected").hide();
  			  	$("#businessActivitiesSection").hide(); 
            	$("#temporaryLicenseTextBox textarea").val(SAGIA.license.apply.data.businessActivities.temporaryLicenseTextBox);
            }
        }

        if(SAGIA.license.apply.data.licenseType){
            updateDropDown("#licenseTypes", SAGIA.license.apply.data.licenseType);
        }

        if (SAGIA.license.apply.data.isEntrepreneur) {
            licenseInformationSection.find("#isEntrepreneurYES").trigger("click");

        } else {
            licenseInformationSection.find("#isEntrepreneurNO").trigger("click");
        }

        if (!SAGIA.license.apply.data.hasSagiaLicense && !SAGIA.license.apply.data.hasShareholdersTypePerson) {
            if (SAGIA.license.apply.data.hasStockListing) {
                stockMarketSection.find("#hasStockListingYES").trigger("click");
            } else {
                stockMarketSection.find("#hasStockListingNO").trigger("click");
            }
        }

        if (SAGIA.license.apply.data.basicInformation.entityName){
            basicInformationSection.find("#basicInformationEntityName").val(SAGIA.license.apply.data.basicInformation.entityName);
        }

        if (SAGIA.license.apply.data.basicInformation.entityNameArabic) {
            basicInformationSection.find("#basicInformationEntityNameArabic").val(SAGIA.license.apply.data.basicInformation.entityNameArabic);
        }

        if (SAGIA.license.apply.data.basicInformation.labourSize) {
            basicInformationSection.find("#basicInformationLabourSize").val(SAGIA.license.apply.data.basicInformation.labourSize) ;
        }

        if (SAGIA.license.apply.data.basicInformation.capital) {
            basicInformationSection.find("#basicInformationCapital").val(SAGIA.license.apply.data.basicInformation.capital) ;
        }

        if(SAGIA.license.apply.data.basicInformationExtended.entityName){
            basicInformationExtendedSection.find("#basicInformationExtendedEntityName").val(SAGIA.license.apply.data.basicInformationExtended.entityName) ;
        }

        if(SAGIA.license.apply.data.basicInformationExtended.entityNameArabic) {
            basicInformationExtendedSection.find("#basicInformationExtendedEntityNameArabic").val(SAGIA.license.apply.data.basicInformationExtended.entityNameArabic) ;
        }

        if(SAGIA.license.apply.data.basicInformationExtended.capital) {
            basicInformationExtendedSection.find("#basicInformationExtendedCapital").val(SAGIA.license.apply.data.basicInformationExtended.capital);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.email){
            basicInformationExtendedSection.find("#basicInformationExtendedEmail").val(SAGIA.license.apply.data.basicInformationExtended.email);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.countryCodeForTelephone) {
            basicInformationExtendedSection.find("#basicInformationExtendedCountryCodeForTelephone").val(SAGIA.license.apply.data.basicInformationExtended.countryCodeForTelephone) ;
        }

        if(SAGIA.license.apply.data.basicInformationExtended.telephone) {
            basicInformationExtendedSection.find("#basicInformationExtendedTelephone").val(SAGIA.license.apply.data.basicInformationExtended.telephone);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.countryCodeForMobilePhone){
            basicInformationExtendedSection.find("#basicInformationExtendedCountryCodeForMobilePhone").val(SAGIA.license.apply.data.basicInformationExtended.countryCodeForMobilePhone);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.mobilePhone){
            basicInformationExtendedSection.find("#basicInformationExtendedMobilePhone").val(SAGIA.license.apply.data.basicInformationExtended.mobilePhone);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.website) {
            basicInformationExtendedSection.find("#basicInformationExtendedWebsite").val(SAGIA.license.apply.data.basicInformationExtended.website);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.address){
            basicInformationExtendedSection.find("#basicInformationExtendedAddress").val(SAGIA.license.apply.data.basicInformationExtended.address);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.postalCode){
            basicInformationExtendedSection.find("#basicInformationExtendedPostalCode").val(SAGIA.license.apply.data.basicInformationExtended.postalCode);
        }

        if(SAGIA.license.apply.data.basicInformationExtended.poBox){
            basicInformationExtendedSection.find("#basicInformationExtendedPOBox").val(SAGIA.license.apply.data.basicInformationExtended.poBox);
        }

        if(SAGIA.license.apply.data.notOnStockMarket.isicSection) {
            updateDropDown("#notOnStockMarketSection #isicSection", SAGIA.license.apply.data.notOnStockMarket.isicSection);
        }

        if(SAGIA.license.apply.data.notOnStockMarket.isicDivison){
            updateDropDown("#notOnStockMarketSection #isicDivision", SAGIA.license.apply.data.notOnStockMarket.isicDivison);
        }

       if(SAGIA.license.apply.data.notOnStockMarket.licenseCondition){
            updateDropDown("#notOnStockMarketSection #licenseCondition", SAGIA.license.apply.data.notOnStockMarket.licenseCondition);
       }

        if(SAGIA.license.apply.data.onStockMarket.isicSection) {
            updateDropDown("#onStockMarketSection #isicSection",  SAGIA.license.apply.data.onStockMarket.isicSection);
        }

        if(SAGIA.license.apply.data.onStockMarket.isicDivision) {
            updateDropDown("#onStockMarketSection #isicDivision",  SAGIA.license.apply.data.onStockMarket.isicDivision);
        }

        if(SAGIA.license.apply.data.onStockMarket.licenseCondition){
            onStockMarketSection.find("#licenseCondition").val(SAGIA.license.apply.data.onStockMarket.licenseCondition) ;
        }
        
        if(SAGIA.license.apply.data.licenseYear) {
            updateDropDown("#licenseYearSection #licenseYear",  SAGIA.license.apply.data.licenseYear);
        }
    };

    $(document).on("click","#entityInformationCancelButton, #shareholdersCancelButton, #contactPersonCancelButton, #reviewCancelButton", function() {
        window.location.href = ACC.config.encodedContextPath;
    });

    SAGIA.license.apply.displayEntityInformationTab();
    SAGIA.license.apply.onChangeTabToEntityInformation();
});

var licenseValidationParams = {
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    focusInvalid: true,
    ignore: "",
    errorElement: 'span',
    errorPlacement: function (error, element) {
        if (element.closest('.form-group').find('.help-block').length > 0) {
            error.appendTo(element.closest('.form-group').find('.help-block'));
        } else if (element.hasClass('js-select2-oneColumn')) {
            error.appendTo(element.closest('.formSelectBox').find('.help-block'));
        } else {
            error.appendTo(element.closest('.formInputBox').find('.help-block'));
        }
    },
    invalidHandler: function (event, validator) {},
    rules: {},
    messages: {}
};

/**
 *
 * @param scope required to isolate the jQuery selectors, can be class (.elementClass) or id (#elementId)
 * @param validatorUrl corresponding controller method request mapping
 * @param data JSON data expected by the controller for validation
 * @param _callback the method executed on success
 */
var validateLicenseData = function(scope, validatorUrl, data, _onSuccess, _onError, _onComplete) {
    return $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        url: ACC.config.encodedContextPath + "/my-sagia/license/validation/" + validatorUrl,
        data: JSON.stringify(data),
        beforeSend: function(xhr) {
            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            xhr.setRequestHeader('Accept', "application/json");
        },
        success: function() {
            if (! validatorUrl.includes('shareholder-percentage') && !validatorUrl.includes('shareholders-total-percentage'))
                $(scope + ' .help-block').each(function (index, errorMessageContainer) {
                    $(errorMessageContainer).text('');
                    $(errorMessageContainer).closest('.form-group').removeClass('has-error');
                    $(errorMessageContainer).siblings('.form-group').removeClass('has-error');
            });

            _onSuccess();
        },
        error: function(xhr, status, error) {
            var formErrors = xhr.responseJSON.formErrors;
            if(typeof _onError !== 'undefined') {
                _onError(xhr, status, error);
            } else {
                $(scope + ' .help-block').each(function(index, errorMessageContainer){
                    $(errorMessageContainer).text('');
                    $(errorMessageContainer).closest('.form-group').removeClass('has-error');
                    $(errorMessageContainer).siblings('.form-group').removeClass('has-error');
                });
                $.each(formErrors, function(key, errorMessage){
                    $(scope + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                    $(scope + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                });
            }
        },
        complete: function() {
            if(typeof _onComplete !== "undefined") {
                _onComplete();
            }
        }
    });
};

function setMobileCode(sourceElement, targetElement1, targetElement2) {
    $(sourceElement).on('change', function () {
        var code = $(this).val();
        
        if(code)
        {
	        $.ajax({
	            url : ACC.config.encodedContextPath + "/my-sagia/license/countries",
	            type : "GET",
	            success : function(data) {
	                var countries = JSON.parse(data);
	
	                var prefix = countries.filter(function (el) {
	                    return el.code === code;
	                });
	
	                $(sourceElement).valid();
	
	                if (prefix[0] != null) {
	                    var countryCodeInput1 = $(targetElement1);
	                    var countryCodeInput2 = $(targetElement2);
	                    if (prefix[0].phoneprefix != null) {
	                        countryCodeInput1.val(prefix[0].phoneprefix).trigger("blur").trigger("change");
	                        countryCodeInput2.val(prefix[0].phoneprefix).trigger("blur").trigger("change");
	                    } else {
	                        countryCodeInput1.val("").trigger("blur").trigger("change");
	                        countryCodeInput2.val("").trigger("blur").trigger("change");
	                    }
	                }
	            }
	        });
         }
        else
        {
        	$(targetElement1).val("").trigger("blur").trigger("change");
        	$(targetElement2).val("").trigger("blur").trigger("change");
        }
    });
}

function removeBase64prefix(mimeType, content) {
    var prefixStr = 'data:' + mimeType + ';base64,';
    var index = content.indexOf(prefixStr);
    if(index !== -1) {
        content = content.substring(index + prefixStr.length);
    }
    return content;
}

function updateDropDown(selectId, value) {
    var $select = $(selectId);
    $select.next().addClass("select2Container_selected");
    $select.val(value).trigger("change");
}


var setDelegateDefaultValueIfMissingInDraft = function() {
	for (var indexNewShareholder = 0; indexNewShareholder < SAGIA.license.apply.data.qeemah1Data.newShareholders.length; indexNewShareholder++) {
		var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[indexNewShareholder];
		if (newShareholder.hasDelegateInfo == null) {
			var delegateType = newShareholder.type;
			var person = "Person";
			var org = "Organization";
			if (person === delegateType) {
				if (!newShareholder.selfDelegate) {
					newShareholder.hasDelegateInfo = "false";
					newShareholder.selfDelegate = "false";
				} else if (newShareholder.selfDelegate = "true") {
					newShareholder.hasDelegateInfo = "true";
				} else if (newShareholder.selfDelegate = "false") {
					newShareholder.hasDelegateInfo = "true";
				}
			}
			if (org === delegateType) {
				if (!newShareholder.selfDelegate
						|| newShareholder.selfDelegate === "") {
					newShareholder.selfDelegate = "false"
				}
				if (newShareholder.delegate) {
					var delegate = newShareholder.delegate;
					if (delegate != null) {
						if (delegate.dateofBirth
								&& delegate.dateofBirth === "") {
							newShareholder.hasDelegateInfo = "false";
						} else if (delegate.dateofBirth
								&& delegate.dateofBirth != "") {
							newShareholder.hasDelegateInfo = "true";
						} else {
							newShareholder.hasDelegateInfo = "false";
						}

					} else {
						newShareholder.hasDelegateInfo = "false";
					}
				} else {
					newShareholder.hasDelegateInfo = "false";
				}
			}
		}
		if(newShareholder.delegate == null){
			newShareholder.delegate={};
		}
	}
};

