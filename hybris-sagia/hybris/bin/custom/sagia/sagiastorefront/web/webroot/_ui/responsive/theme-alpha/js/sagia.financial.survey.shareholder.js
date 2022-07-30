$(document).ready(function () {

    //prevents removal of modal-open class on body when two modals are open and one of them is closed.
    $(document).on('hidden.bs.modal', '#errorResponseModal', function () {
        if($('.modal.show').length)
        {
            $('body').addClass('modal-open');
        }
    });

    if ($('body').hasClass('page-financial-survey')) {
        $('#shareholderPersonEntityTypeId').show();
        $("#entityId").prop('disabled', false);
        $("#personId").prop('disabled', false);

        //hide optionals
        $('#shareholderPreferredSharesSectionId').hide();
        $('#shareholderValueOfReverseInvestmentSectionId').hide();
      // $('#shareholderVotingPowerSectionId').hide();
     //   $('#shareholderVotingPowerId').prop("disabled", true);

        //$('#contentNewShareholderForm').hide();
        loadPersonShareholderForm();

        $(".removeShareholderBtn").on('click', removeShareholder);
        $(".newShareholderBtn, #newShareholderId").on('click', newShareholder);
        $(document).on('click', ".editShareholderBtn", editShareholder);
        // $(document).on('click', ".editShareholderBtn", newEditShareholder);
        if ($(".editShareholderBtn").length > 0) {
            // bindSaveShareholderClick();
            // bindShareholderEditModalClose();
        }
        $('.cancelShareholderBtn').on('click', function () {
            enableShareholderForm();
            clearShareholderForm();
        });
        $('#personId').on('click', clickPersonID);
       //$('#shareholderAcademicTitleId').on('change', removeShareholderErrorsIfExists);
        //$('#shareholderMaritalStatusId').on('change', removeShareholderErrorsIfExists);
        //$('#shareholderPremiumResidentId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderGenderId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderNationalityCurrentId').on('change', removeShareholderErrorsIfExists);
       // $('#shareholderNationalityPreviousId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderCountryId').on('change', removeShareholderErrorsIfExists);
        //$('#existingShareholderId').on('click', removePreviousErrors);
        //$('#newShareholderId').on('click', removePreviousErrors);
        $('#entityId').on('click', removePreviousErrors);

        $('.saveShareholderBtn').on('click', function() {
                saveShareholder(false);
        });

        $('#shareholderPersonEntityTypeId').show();
        $("#entityId").prop('disabled', false);
        $("#personId").prop('disabled', false);
       // $('#bpNumberGroupId').hide();
        clearShareholderForm();
        enableShareholderForm();
        $('#shareholderValidationDetails').show();
        enableDisableShareholderFormSection(false);
        $('#entityShareholderId').show();
     //   $('#companyVerificationSection').hide();


        $("input[name='shareholderPersonEntityRadioBox']").click(function () {
            if ($(this).val() === "true") {
                loadPersonShareholderForm();
                //For passport
                var type = "Person";
                var element = $("#shareholderValidationDetails");
                enableDisableShareholderFormSection(true);
                enableDisablePassportIdShareholderForm(true);
                resetShareholderDetails(element, type);
            } else {
                loadShareholderOrganizationForm();
                loadOrganizationFormFields();
            }
        });


        $("#contentNewShareholderForm").find("#hasDelegateYES").on("click", function () {
            $("#contentNewShareholderForm").find("#hasDelegateYESLabel").css("border-color", "");

            var type = "Person";
            var element = $("#delegate");
            resetDelegateDetails(element, type);
            toggleDelegateDetails($("#contentNewShareholderForm"), false);
        });

        $("#contentNewShareholderForm").find("#hasDelegateNO").on("click", function () {
            $("#contentNewShareholderForm").find("#hasDelegateNOLabel").css("border-color", "");
            toggleDelegateDetails($("#contentNewShareholderForm"), true);
        });



        $("input[name='shareholderIsVotingPower']").click(function () {

        	var shareholderIsVotingPower = $('#shareholderIsVotingPowerId').is(':checked');
            if (shareholderIsVotingPower ) {
            //	$('#shareholderVotingPowerSectionId').show();
                $('#shareholderVotingPowerId').prop("disabled", false);


            } else {
            //	$('#shareholderVotingPowerSectionId').hide();
             //   $('#shareholderVotingPowerId').val(null);

                // make shareholderVotingPower same as shares percentage and disable it.
                $('#shareholderVotingPowerId').prop("disabled", true);
                sharePercentage = $('#shareholderPercentageId').val();
                $('#shareholderVotingPowerId').val(sharePercentage);
            	//resetVerifyInherit();
            }
        });

        $("input[name='shareholderHasPreferredShares']").click(function () {

            var shareholderHasPreferredShares = $('#shareholderHasPreferredSharesId').is(':checked');
            if (shareholderHasPreferredShares ) {
                $('#shareholderPreferredSharesSectionId').show();

            } else {
                $('#shareholderPreferredSharesSectionId').hide();
                $('#shareholderPreferredSharesId').val(null);
                //resetVerifyInherit();
            }
        });

        $("input[name='shareholderHaveReverseInvestment']").click(function () {

            var shareholderHaveReverseInvestment = $('#shareholderHaveReverseInvestmentId').is(':checked');
            if (shareholderHaveReverseInvestment ) {
                $('#shareholderValueOfReverseInvestmentSectionId').show();

            } else {
                $('#shareholderValueOfReverseInvestmentSectionId').hide();
                $('#shareholderValueOfReverseInvestmentId').val(null);
                //resetVerifyInherit();
            }
        });


        resetFormOnModalClose()
    }
});


var loadInvestorCrResponseEvent = function(data, crNumber){
    if(data.nameEnglish){
        $("#shareholderNameEnglishId").val(data.nameEnglish.replace(/[^a-zA-Z0-9 ]+/g, "")).attr("disabled", true);
    }
    if(data.nameArabic){
        $("#shareholderNameId").val(data.nameArabic.replace(/[^\u0621-\u064A\u0660-\u0669 ]+/g, "")).attr("disabled", true);
    }
    if(data.capital){
        $("#shareholderCapitalId").val(data.capital.replace(/\D+/g, "")).attr("disabled", true);
    }
    $("#professionalLicenseCrVerified").val(true);
    $("#inputCRNumber").val(crNumber);

    loadOrganizationFormFields();
    $('#delegateDivSection').hide();
};

var setAndDisableFieldIfValueNotBlank = function(element, value){
    if(value)
    {
        element.val(value);
        if(element.is( "select" ))
        {
            element.trigger("blur").trigger('change');
        }

        if(element.hasClass("flatpickr-input"))
        {
            var dateParser = document.querySelector("#"+element.attr('id'))._flatpickr; // this flatpickr is used for parsing the date
            dateParser.setDate(dateParser.parseDate(value, ACC.formatUIDate));
        }
        element.attr('disabled','disabled');
    } else {
        element.removeAttr('disabled');
    }
};

var resetShareholderDetails = function(element, type)
{
    clearShareholderForm();
   // toggleTheNICFieldsEditable(element, type, true);
};



var onchangeOfIdType = function(element, type) {

    var elementIdType = element.find("#idType");
    var elementDateofBirth = element.find("#delegateDateofBirth");
    var delegateBirthDateId = element.find("#delegateBirthDateId");

    var elementDelegateExpiryDate = element.find("#delegateExpiryDate");
    var elementDelegateIssueDate = element.find("#delegateIssueDate");

    bindNormalCal(elementDelegateExpiryDate);
    bindNormalCal(elementDelegateIssueDate);

    toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, elementDateofBirth);
    toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, delegateBirthDateId);

    element.find("#nicVerifyBtnSection").show();
    element.find("#delegateDateofBirthSection").show();
    resetDelegateDetails(element, type);


    // show and hide the special fields
    if(elementIdType.val()==="1"){
    	$("#delegateCountryDiv").hide();
    	$("#delegateNationalityDiv").hide();

    }
    else
    {

    	$("#delegateCountryDiv").show();
    	$("#delegateNationalityDiv").show();

    }


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
var bindUmmAlQuraCal = function(element){
    element.addClass("ummAlQura");
    var flatPickrInstance = element.get(0)._flatpickr;

    flatPickrInstance.destroy();

    element.prop('readonly',true);

    bindCalendarsPickerToInput(element);
};
var bindNormalCal = function(element){
    element.removeClass("ummAlQura");

    element.calendarsPicker('destroy');

    bindCustomFlatpickr(element);
};

var enableDisableShareholderFormSection = function (enable) {
    if(enable) {
        $('#contentNewShareholderForm').show();
        $('#individualShareholderId').show();
        $('#shareholderAddressId').show();

    } else {
        $('#contentNewShareholderForm').hide();
        $('#individualShareholderId').hide();
        $('#shareholderAddressId').show();
    }
};

var enableDisablePassportIdShareholderForm = function (enable) {
    if(enable) {
        $('#delegateDivSection').show();
    } else {
        $('#delegateDivSection').hide();
    }
};

var resetCompanyCountriesSection = function () {
    var entitySection = $('#entityShareholderId');

    entitySection.find("#inputCRNumber").attr("disabled", true);
    entitySection.find("#inputCRNumber").val("");

    entitySection.find("#load-investor").attr("disabled", true);

};

var loadShareholderOrganizationForm = function () {
    $('#shareholderValidationSection').show();
    $('#shareholderValidationDetails').hide();

    $('#contentNewShareholderForm').show();
    $('#individualShareholderId').hide();
    $('#entityShareholderId').show();
    $('#entityBasicInformation').hide();
    $('#entityBasicInformation2').hide();

    $('.contentModule-headline').hide();
    $('#shareholderAddressId').show();

    $('#companyVerificationSection').show();

    resetCompanyCountriesSection();

};

var loadOrganizationFormFields = function () {
    $('#shareholderValidationSection').show();
    $('#shareholderValidationDetails').hide();

    $('#contentNewShareholderForm').show();
    $('#individualShareholderId').hide();
    $('#entityShareholderId').show();
    $('#entityBasicInformation').show();
    $('#entityBasicInformation2').show();

    $('.contentModule-headline').show();
    $('#shareholderAddressId').show();

    loadShareholderIdTypeDropDown(false);
};

var loadPersonShareholderForm = function () {
    $('#shareholderValidationSection').show();
    $('#shareholderValidationDetails').show();
    $('#entityBasicInformation2').show();
    $('#contentNewShareholderForm').hide();
    $('#individualShareholderId').hide();
    $('#entityShareholderId').hide();

    loadShareholderIdTypeDropDown(true);
    bindShareholderCalendarsToInput(
        $("#shareholderValidationDetails").find("#shareholderIdType"),
        $("#shareholderValidationDetails").find("#shareholderDateofBirth")
    );

    bindShareholderCalendarsToInput(
            $("#shareholderValidationDetails").find("#shareholderIdType"),
            $("#contentNewShareholderForm").find("#birthDateId")
        );

    bindShareholderCalendarsToInput(
        $("#delegate").find("#idType"),
        $("#delegate").find("#delegateDateofBirth")
    );

};

var bindShareholderCalendarsToInput = function (elementIdType, elementDateofBirth) {
    var isUmmAlQura = false;

    if(elementIdType.val() === "1"){
        isUmmAlQura = true;
    }

    if (isUmmAlQura) {
        bindCalendarsPickerToInput(elementDateofBirth);
    } else {
        bindCustomFlatpickr(elementDateofBirth);
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
        prevText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M15.758.374c.365-.365.955-.365 1.319 0 .364.364.363.954-.001 1.319l-7.789 10.31 7.789 10.305c.364.365.365.955.001 1.319s-.954.364-1.319 0l-9.108-11.624 9.108-11.629z\"/></svg>",
        nextText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M8.242.374c-.365-.365-.956-.365-1.319 0-.364.364-.363.954 0 1.319l7.79 10.311-7.79 10.305c-.364.365-.364.955 0 1.319s.955.364 1.319 0l9.107-11.624-9.107-11.63z\"/></svg>",
    });
};

var bindCustomFlatpickr = function (element) {
    ACC.calendarcommons.bindFlatpickr(element);
};

var loadShareholderIdTypeDropDown = function (isPerson) {
    var shareholderIdType = $("#shareholderValidationDetails #shareholderIdType");
    shareholderIdType.find("option").remove();
    shareholderIdType.append(new Option("", "", false, false));
    shareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
    shareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
    shareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
    shareholderIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));

    var personDelegateIdType = $("#delegateSection").find("#delegateDetails #idType");
    personDelegateIdType.find("option").remove();
    personDelegateIdType.append(new Option("", "", false, false));
    personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
    personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
    personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
    if(!isPerson){
    	personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));
    }
  //


};

var removeShareholder = function () {
    financialSurvey.dirtyAmendment = true;
    var $shareholderRow = $(this).closest('tr');
    $shareholderRow.remove();

    var shareholderId = parseInt($shareholderRow.attr('id'));
    var shareholders = financialSurvey.shareholders;
    for (var i = 0; i < shareholders.length; i++) {
        if (shareholderId == shareholders[i].srId) {
            shareholders[i].action = '03';
            break;
        } else if (shareholderId == shareholders[i].newItemId) {
            shareholders.splice(i, 1);
            break;
        }
    }
};


var newShareholder = function () {

	 $('#showDelegateQuestionOrganization').hide();
	 $('#showDelegateQuestion').show();
    clearShareholderValidation();
    $('#shareholderPersonEntityTypeId').show();
    $("#entityId").prop('disabled', false);
    $("#personId").prop('disabled', false);

    enableShareholderForm();
    clearShareholderForm();
    $('#personId').click();
    $('#shareholderIdType').removeAttr('disabled');
    $('#verifyShareholderDetailsShow').removeAttr('disabled');
    $('#shareholderDateofBirth').removeAttr('disabled');
    $('#shareholderIdNumber').removeAttr('disabled');

    $('#shareholderVotingPowerId').prop("disabled", true);
    $('#shareholderIdTypeSection').show();
};

function enableShareholderForm() {
    $("#entityShareholderId :input").attr("disabled", false);
    $("#individualShareholderId :input").attr("disabled", false);
    $('#shareholderInheritedPropertyId').attr("disabled", false);
    $("#shareholderAddressId :input").attr("disabled", false);
    resetVerifyInherit();
}

function disableShareholderForm() {
    $("#entityShareholderId :input").attr("disabled", true);
    $("#individualShareholderId :input").attr("disabled", true);
    $('#shareholderInheritedPropertyId').attr("disabled", true);
    $("#shareholderAddressId :input").attr("disabled", true);
}


function disableMCFields() {
	$("#shareholderNameEnglishId").attr("disabled", true);
    $("#shareholderNameId").attr("disabled", true);
    $("#shareholderCapitalId").attr("disabled", true);


}

function enableMCFields() {
	$("#shareholderNameEnglishId").attr("disabled", false);
    $("#shareholderNameId").attr("disabled", false);
    $("#shareholderCapitalId").attr("disabled", false);

}



var newEditShareholder = function () {
    var selectedShareholderId = $(this).closest('tr').attr('id');
    var index = financialSurvey.shareholders.findIndex(function (shareholder) {
        return shareholder.srId == selectedShareholderId || shareholder.newItemId == selectedShareholderId;
    });

    // determine index by screen position assuming array has the same config.
    index = $(this).parents('tr').index() - 1;

    var selectedShareholder = financialSurvey.shareholders[index];

    var $percentageInput = $('.editShareholder input[name=shareholderPercentage]');
    var oldPercentageValue = $percentageInput.val();
    $percentageInput.val(selectedShareholder.percentage);
    $percentageInput.attr('data-old-value', oldPercentageValue);
    $percentageInput.attr('data-index', index);
}

var bindSaveShareholderClick = function () {
    $(document).on('click', '.saveEditShareholderBtn', function () {
        var $percentageInput = $('#shareholderPercentage');
        var index = $percentageInput.attr('data-index');
        var newPercentage = $percentageInput.val();

        if (newPercentage && isNumeric(newPercentage)) {
            financialSurvey.shareholders[index].percentage = newPercentage;

            $(this).parents('.modal').modal('hide');
        } else {
            $percentageInput.parent().append('<div class="help-block">Invalid Percentage</div>');
            $percentageInput.parent().addClass('has-error');
        }
    });
}

var bindShareholderEditModalClose = function () {
    var form = $('.editShareholder');
    var input = form.find('input');
    if (input.attr('data-old-value') != input.val()) {
        form.parents('.modal').on('hide.bs.modal', function () {
            refreshShareholderPercentage();
        });
    }
}

var refreshShareholderPercentage = function () {
    $('.shareholderTemplate').filter(':visible').each(function (index) {
        $(this).find('.percentage').html(financialSurvey.shareholders[index].percentage + '%');
    })
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

var editShareholder = function () {
    clearShareholderValidation();
    var selectedShareholderId = $(this).closest('tr').attr('id');
    var selectedShareholder = financialSurvey.shareholders.find(function (shareholder) {
        return shareholder.srId == selectedShareholderId || shareholder.newItemId == selectedShareholderId;
    });

    // Disable shareholderPersonEntityTypeId block instead of hiding it.
  //  $('#shareholderPersonEntityTypeId').hide();
    $("#entityId").prop('disabled', true);
    $("#personId").prop('disabled', true);

     enableShareholderForm();
    fillShareholderForm(selectedShareholder);
    $('.saveShareholderBtn').attr('id', selectedShareholder.srId || selectedShareholder.newItemId);
    SAGIA.formElements.placeholderPolyfill();
    prepareShareholderVisibleItemsEdit(selectedShareholder);
    /*if(selectedShareholder.idType !== '4' ){
    	disableNICFields();

    }else {
    	enableNICFields();
    }*/

    var shareholderIsVotingPower = $('#shareholderIsVotingPowerId').is(':checked');
    if (shareholderIsVotingPower ) {
        $('#shareholderVotingPowerId').prop("disabled", false);

    } else {
        $('#shareholderVotingPowerId').prop("disabled", true);
    }

    $('#entityBasicInformation2').show();

};

function prepareShareholderVisibleItemsEdit(selectedShareholder) {
    $('#shareholderFormId').find('.contentModule-headline').show();

    $('#shareholderValidationDetails').hide();
    $('#contentNewShareholderForm').show();
    $('#shareholderAddressId').show();



    //if an Entity
    if (selectedShareholder.shareholderType === '2') {
    	 $('#entityBasicInformation').show();
    	 $('#showDelegateQuestionOrganization').show();
    	 $('#showDelegateQuestion').hide();
    	// $('#companyCountry').attr("disabled", true);
    	 $('#inputCRNumber').attr("disabled", true);
    	 $('#load-investor').attr("disabled", true);


    	if (selectedShareholder.isCrVerified ) { // verified Entity
            // Delegate section
        //    $('#entityBasicInformation').show();
           // $('#delegate').hide();

            disableMCFields();
        } else{     // Not Verified Entity
            // Delegate section


            //enableMCFields() ;
        }

    	if (selectedShareholder.srId){  // hide delegate section for existing enity
    		     $('#delegateDivSection').hide();
    	         $('#delegate').hide();
    	}else {


    		if(selectedShareholder.companyCountry !== 'SA' ){
    		   	 $('#delegateDivSection').show();

    		   }else {
    		   	 $('#delegateDivSection').hide();
    		   }

    	}

    }else {


    	if(!selectedShareholder.srId && selectedShareholder.idNumber  !== '' ) {
   		 $('#shareholderValidationDetails').show();
      	     $('#shareholderIdTypeSection').hide();

            $('#shareholderIdNumber').val(selectedShareholder.idNumber);
            if (selectedShareholder.birthDateString) {
                $("#shareholderDateofBirth").val(selectedShareholder.birthDateString);
            } else if (selectedShareholder.birthDate && selectedShareholder.birthDate.millis) {
                $("#shareholderDateofBirth")._flatpickr.setDate(new Date(selectedShareholder.birthDate.millis));
            }
            $('#shareholderIdType').attr('disabled','disabled');
            $('#verifyShareholderDetailsShow').attr('disabled','disabled');
            $('#shareholderDateofBirth').attr('disabled','disabled');
            $('#shareholderIdNumber').attr('disabled','disabled');

      	  }


    }

}

function fillShareholderForm(selectedShareholder) {
    if (selectedShareholder.shareholderType === '2') {


        $("#personId").prop('checked', false);
        $("#entityId").prop('checked', true);
        $('#individualShareholderId').hide();
        $('#entityShareholderId').show();
        $('#shareholderNameEnglishId').val(selectedShareholder.shareholderNameEnglish);
        $('#inputCRNumber').val(selectedShareholder.inputCRNumber);

        updateDropDown('#companyCountry', selectedShareholder.companyCountry);
        updateDropDown('#shareholderSectorId', selectedShareholder.industry);
        updateDropDown('#shareholderMultinationalCompanyId', selectedShareholder.shareholderMultinationalCompany);
        updateDropDown('#shareholderLegalStatusId', selectedShareholder.legalStatus);
        updateDropDown('#nationalityOfUCPId', selectedShareholder.nationalityOfUCP);
        $('#shareholderSubsectorId').val(selectedShareholder.subsector);
    } else {


        $("#personId").prop('checked', true);
        $("#entityId").prop('checked', false);
        $('#entityShareholderId').hide();
        $('#individualShareholderId').show();
        $('#individualShareholderNameEnglishId').val(selectedShareholder.shareholderNameEnglish);
        updateDropDown('#shareholderGenderId', selectedShareholder.shareholderGender);
        updateDropDown('#shareholderMaritalStatusId', selectedShareholder.maritalStatus);
        updateDropDown('#shareholderNationalityCurrentId', selectedShareholder.shareholderNationalityCurrent);
        updateDropDown('#shareholderCountryId', selectedShareholder.country);

    }

    if (selectedShareholder.shareholderIsVotingPower) {
        $('#shareholderVotingPowerSectionId').show();
        $('#shareholderIsVotingPowerId').prop('checked', true);
    }
    if (selectedShareholder.shareholderHasPreferredShares) {
        $('#shareholderPreferredSharesSectionId').show();
        $('#shareholderHasPreferredSharesId').prop('checked', true);
    }
    if (selectedShareholder.shareholderHaveReverseInvestment) {
        $('#shareholderValueOfReverseInvestmentSectionId').show();
        $('#shareholderHaveReverseInvestmentId').prop('checked', true);
    }

    $('#shareholderPercentageId').val(selectedShareholder.shareholderPercentage); // if not existing
    $('#shareholderCapitalId').val(selectedShareholder.shareholderCapital);
    $('#shareholderSubsectorId').val(selectedShareholder.shareholderSubsector);
    //shareholderSubsectorId  shareholderSubsector

    $('#shareholderVotingPowerId').val(selectedShareholder.shareholderVotingPower);
    $('#shareholderPreferredSharesId').val(selectedShareholder.shareholderPreferredShares);
    $('#shareholderValueOfReverseInvestmentId').val(selectedShareholder.valueOfReverseInvestment);

    $('#shareholderRetainedEarningsIncludeCurrentQuarterId').val(selectedShareholder.retainedEarningsIncludeCurrentQuarter);
    $('#shareholderAdditionalPaidUpCapitalCurrentQuarterId').val(selectedShareholder.additionalPaidUpCapitalCurrentQuarter);

    $('#shareholderPaidUpCapitalCurrentQuarterId').val(selectedShareholder.paidUpCapitalCurrentQuarter);
    $('#shareholderPaidUpCapitalPreviousQuarterId').val(selectedShareholder.paidUpCapitalPreviousQuarter);

    $('#shareholderProfitLossQuarterCurrentQuarterId').val(selectedShareholder.profitLossQuarterCurrentQuarter);
    $('#shareholderTotalReservesCurrentQuarterId').val(selectedShareholder.totalReservesCurrentQuarter);
    $('#shareholderTreasurySharesCurrentQuarterId').val(selectedShareholder.treasurySharesCurrentQuarter);
    $('#shareholderHeadOfficeAccountInBranchCurrentQuarterId').val(selectedShareholder.headOfficeAccountInBranchCurrentQuarter);
    $('#shareholderShareholderEquityOthersCurrentQuarterId').val(selectedShareholder.shareholderEquityOthersCurrentQuarter);
    $('#shareholderMinorityRightsCurrentQuarterId').val(selectedShareholder.minorityRightsCurrentQuarter);
    $('#shareholderTotalShareholderEquityCurrentQuarterId').val(selectedShareholder.totalShareholderEquityCurrentQuarter);

    $('#shareholderRetainedEarningsIncludePreviousQuarterId').val(selectedShareholder.retainedEarningsIncludePreviousQuarter);
    $('#shareholderAdditionalPaidUpCapitalPreviousQuarterId').val(selectedShareholder.additionalPaidUpCapitalPreviousQuarter);
    $('#shareholderProfitLossQuarterPreviousQuarterId').val(selectedShareholder.profitLossQuarterPreviousQuarter);
    $('#shareholderTotalReservesPreviousQuarterId').val(selectedShareholder.totalReservesPreviousQuarter);
    $('#shareholderTreasurySharesPreviousQuarterId').val(selectedShareholder.treasurySharesPreviousQuarter);
    $('#shareholderHeadOfficeAccountInBranchPreviousQuarterId').val(selectedShareholder.headOfficeAccountInBranchPreviousQuarter);
    $('#shareholderShareholderEquityOthersPreviousQuarterId').val(selectedShareholder.shareholderEquityOthersPreviousQuarter);
    $('#shareholderMinorityRightsPreviousQuarterId').val(selectedShareholder.minorityRightsPreviousQuarter);
    $('#shareholderTotalShareholderEquityPreviousQuarterId').val(selectedShareholder.totalShareholderEquityPreviousQuarter);

    $('#tradeDebitCurrentQuarterId').val(selectedShareholder.transaction.tradeDebitCurrentQuarter);
    $('#tradeCreditCurrentQuarterId').val(selectedShareholder.transaction.tradeCreditCurrentQuarter);
    $('#loansAssetsCurrentQuarterId').val(selectedShareholder.transaction.loansAssetsCurrentQuarter);
    $('#loansLiabilitiesCurrentQuarterId').val(selectedShareholder.transaction.loansLiabilitiesCurrentQuarter);
    $('#interestReceivedCurrentQuarterId').val(selectedShareholder.transaction.interestReceivedCurrentQuarter);
    $('#interestPayableCurrentQuarterId').val(selectedShareholder.transaction.interestPayableCurrentQuarter);
    $('#dividendsReceivedCurrentQuarterId').val(selectedShareholder.transaction.dividendsReceivedCurrentQuarter);
    $('#dividendsPaidCurrentQuarterId').val(selectedShareholder.transaction.dividendsPaidCurrentQuarter);
    $('#expensesReceivedCurrentQuarterId').val(selectedShareholder.transaction.expensesReceivedCurrentQuarter);
    $('#expensesPaidCurrentQuarterId').val(selectedShareholder.transaction.expensesPaidCurrentQuarter);
    $('#sellProductionSuppliesCurrentQuarterId').val(selectedShareholder.transaction.sellProductionSuppliesCurrentQuarter);
    $('#purchaseProductionSuppliesCurrentQuarterId').val(selectedShareholder.transaction.purchaseProductionSuppliesCurrentQuarter);
    $('#sellMachineryCurrentQuarterId').val(selectedShareholder.transaction.sellMachineryCurrentQuarter);
    $('#purchaseMachineryCurrentQuarterId').val(selectedShareholder.transaction.purchaseMachineryCurrentQuarter);
    $('#currentDebitAccountCurrentQuarterId').val(selectedShareholder.transaction.currentDebitAccountCurrentQuarter);
    $('#currentCreditAccountCurrentQuarterId').val(selectedShareholder.transaction.currentCreditAccountCurrentQuarter);
    $('#expensesReceivableCurrentQuarterId').val(selectedShareholder.transaction.expensesReceivableCurrentQuarter);
    $('#expensesPayableCurrentQuarterId').val(selectedShareholder.transaction.expensesPayableCurrentQuarter);
    $('#insuranceCommissionReceivableCurrentQuarterId').val(selectedShareholder.transaction.insuranceCommissionReceivableCurrentQuarter);
    $('#insuranceCommissionPayableCurrentQuarterId').val(selectedShareholder.transaction.insuranceCommissionPayableCurrentQuarter);
    $('#otherDebitCurrentQuarterId').val(selectedShareholder.transaction.otherDebitCurrentQuarter);
    $('#otherCreditCurrentQuarterId').val(selectedShareholder.transaction.otherCreditCurrentQuarter);
    $('#totalDebitCurrentQuarterId').val(selectedShareholder.transaction.totalDebitCurrentQuarter);
    $('#totalCreditCurrentQuarterId').val(selectedShareholder.transaction.totalCreditCurrentQuarter);




    $('#tradeDebitPreviousQuarterId').val(selectedShareholder.transaction.tradeDebitPreviousQuarter);
    $('#tradeCreditPreviousQuarterId').val(selectedShareholder.transaction.tradeCreditPreviousQuarter);
    $('#loansAssetsPreviousQuarterId').val(selectedShareholder.transaction.loansAssetsPreviousQuarter);
    $('#loansLiabilitiesPreviousQuarterId').val(selectedShareholder.transaction.loansLiabilitiesPreviousQuarter);
    $('#interestReceivedPreviousQuarterId').val(selectedShareholder.transaction.interestReceivedPreviousQuarter);
    $('#interestPayablePreviousQuarterId').val(selectedShareholder.transaction.interestPayablePreviousQuarter);
    $('#dividendsReceivedPreviousQuarterId').val(selectedShareholder.transaction.dividendsReceivedPreviousQuarter);
    $('#dividendsPaidPreviousQuarterId').val(selectedShareholder.transaction.dividendsPaidPreviousQuarter);
    $('#expensesReceivedPreviousQuarterId').val(selectedShareholder.transaction.expensesReceivedPreviousQuarter);
    $('#expensesPaidPreviousQuarterId').val(selectedShareholder.transaction.expensesPaidPreviousQuarter);
    $('#sellProductionSuppliesPreviousQuarterId').val(selectedShareholder.transaction.sellProductionSuppliesPreviousQuarter);
    $('#purchaseProductionSuppliesPreviousQuarterId').val(selectedShareholder.transaction.purchaseProductionSuppliesPreviousQuarter);
    $('#sellMachineryPreviousQuarterId').val(selectedShareholder.transaction.sellMachineryPreviousQuarter);
    $('#purchaseMachineryPreviousQuarterId').val(selectedShareholder.transaction.purchaseMachineryPreviousQuarter);
    $('#currentDebitAccountPreviousQuarterId').val(selectedShareholder.transaction.currentDebitAccountPreviousQuarter);
    $('#currentCreditAccountPreviousQuarterId').val(selectedShareholder.transaction.currentCreditAccountPreviousQuarter);
    $('#expensesReceivablePreviousQuarterId').val(selectedShareholder.transaction.expensesReceivablePreviousQuarter);
    $('#expensesPayablePreviousQuarterId').val(selectedShareholder.transaction.expensesPayablePreviousQuarter);
    $('#insuranceCommissionReceivablePreviousQuarterId').val(selectedShareholder.transaction.insuranceCommissionReceivablePreviousQuarter);
    $('#insuranceCommissionPayablePreviousQuarterId').val(selectedShareholder.transaction.insuranceCommissionPayablePreviousQuarter);
    $('#otherDebitPreviousQuarterId').val(selectedShareholder.transaction.otherDebitPreviousQuarter);
    $('#otherCreditPreviousQuarterId').val(selectedShareholder.transaction.otherCreditPreviousQuarter);
    $('#totalDebitPreviousQuarterId').val(selectedShareholder.transaction.totalDebitPreviousQuarter);
    $('#totalCreditPreviousQuarterId').val(selectedShareholder.transaction.totalCreditPreviousQuarter);

    updateDropDown('#shareholderCountryId', selectedShareholder.shareholderCountry);

    updateDropDown('#shareholderSectorId', selectedShareholder.shareholderSector);







}

function updateDropDown(selectId, value) {
    var $select = $(selectId);
    $select.next().addClass("select2Container_selected");
    $select.val(value).trigger('change');
}
function updateDropDownwithoutTriggerChange(selectId, value) {
    var $select = $(selectId);
    $select.next().addClass("select2Container_selected");
    $select.val(value).trigger("blur").trigger('change');;
}
function updateShareholderPremiumResident(select, value, disableFieldIfValueNotBlank) {
    var $selectPremiumResident = $(select);
    var optionValue = (value == true) ? 'Y' : (value == false ? 'N' : null);
    if(optionValue) {
        $selectPremiumResident.next().addClass("select2Container_selected");
        $selectPremiumResident.val(optionValue).trigger('change');
        if(disableFieldIfValueNotBlank) {
            $selectPremiumResident.attr('disabled','disabled');
        }
    }
}

function updateGenderDropdown(select, value, disableFieldIfValueNotBlank) {
    var $selectGender = $(select);
    if(optionValue = value.charAt(0)) {
        $selectGender.next().addClass("select2Container_selected");
        $selectGender.val(optionValue).trigger('change');
        if(disableFieldIfValueNotBlank) {
            $selectGender.attr('disabled','disabled');
        }
    }
}


var saveShareholder = function (existingBp, bpId) {


    var validator = shareholderValidator();
    if (!validator.form()) {
        return;
    }
    financialSurvey.dirtyAmendment = true;
    var saveShareholderBtn = $('.saveShareholderBtn');
    var shareholderSrId = saveShareholderBtn.attr('id');
    saveShareholderBtn.removeAttr('id');
    var $companyCountry = $('#companyCountry option:selected');
    var companyCountry = $companyCountry.val();

    var $nationalityOfUCP = $('#nationalityOfUCPId option:selected');
    var nationalityOfUCP = $nationalityOfUCP.val();

    var companyCountryDescription = $companyCountry.text();
    var name = $('#shareholderNameEnglishId').val();
    var individualShareholderName = $('#individualShareholderNameEnglishId').val();
    var subsector = $('#shareholderSubsectorId').val();
    var capital = $('#shareholderCapitalId').val();
    var percentage = $('#shareholderPercentageId').val();
    var shareholderVotingPower = $('#shareholderVotingPowerId').val();
    if($('#shareholderVotingPowerId').val() == ''){
        shareholderVotingPower = percentage;
    }
    var shareholderPreferredShares = $('#shareholderPreferredSharesId').val();
    var shareholderValueOfReverseInvestment = $('#shareholderValueOfReverseInvestmentId').val();
    var shareholderIsVotingPower = $('#shareholderIsVotingPowerId').is(':checked');
    var shareholderHasPreferredShares = $('#shareholderHasPreferredSharesId').is(':checked');
    var shareholderHaveReverseInvestment = $('#shareholderHaveReverseInvestmentId').is(':checked');
    var shareholderMultinationalCompany = $('#shareholderMultinationalCompanyId option:selected').val();

    var $industry = $('#shareholderSectorId option:selected');
    var industry = $industry.val();
    var industryDescription = $industry.text();

    //shareholderSubsectorId  shareholderSubsector
    var shareholderSubsector = $('#shareholderSubsectorId').val();

    var $gender = $('#shareholderGenderId option:selected');
    var gender = $gender.val();
    var genderDescription = $gender.text();
    var $maritalStatus = $('#shareholderMaritalStatusId option:selected');
    var maritalStatus = $maritalStatus.val();
    var maritalStatusDescription = $maritalStatus.text();
    var $nationalityCurrent = $('#shareholderNationalityCurrentId option:selected');
    var nationalityCurrent = $nationalityCurrent.val();
    var nationalityCurrentDescription = $nationalityCurrent.text();
    var $shareholderCountry = $('#shareholderCountryId option:selected');
    var shareholderCountry = $shareholderCountry.val();
    var shareholderCountryDescription = $shareholderCountry.text();

    var retainedEarningsIncludeCurrentQuarter = $('#shareholderRetainedEarningsIncludeCurrentQuarterId').val();
    var additionalPaidUpCapitalCurrentQuarter = $('#shareholderAdditionalPaidUpCapitalCurrentQuarterId').val();

    var paidUpCapitalCurrentQuarter = $('#shareholderPaidUpCapitalCurrentQuarterId').val();
    var paidUpCapitalPreviousQuarter = $('#shareholderPaidUpCapitalPreviousQuarterId').val();



    var profitLossQuarterCurrentQuarter = $('#shareholderProfitLossQuarterCurrentQuarterId').val();
    var totalReservesCurrentQuarter = $('#shareholderTotalReservesCurrentQuarterId').val();
    var treasurySharesCurrentQuarter = $('#shareholderTreasurySharesCurrentQuarterId').val();
    var headOfficeAccountInBranchCurrentQuarter = $('#shareholderHeadOfficeAccountInBranchCurrentQuarterId').val();
    var shareholderEquityOthersCurrentQuarter = $('#shareholderShareholderEquityOthersCurrentQuarterId').val();
    var minorityRightsCurrentQuarter =  $('#shareholderMinorityRightsCurrentQuarterId').val();
    var totalShareholderEquityCurrentQuarter =  $('#shareholderTotalShareholderEquityCurrentQuarterId').val();

    var retainedEarningsIncludePreviousQuarter = $('#shareholderRetainedEarningsIncludePreviousQuarterId').val();
    var additionalPaidUpCapitalPreviousQuarter = $('#shareholderAdditionalPaidUpCapitalPreviousQuarterId').val();
    var profitLossQuarterPreviousQuarter = $('#shareholderProfitLossQuarterPreviousQuarterId').val();
    var totalReservesPreviousQuarter = $('#shareholderTotalReservesPreviousQuarterId').val();
    var treasurySharesPreviousQuarter = $('#shareholderTreasurySharesPreviousQuarterId').val();
    var headOfficeAccountInBranchPreviousQuarter = $('#shareholderHeadOfficeAccountInBranchPreviousQuarterId').val();
    var shareholderEquityOthersPreviousQuarter = $('#shareholderShareholderEquityOthersPreviousQuarterId').val();
    var minorityRightsPreviousQuarter =  $('#shareholderMinorityRightsPreviousQuarterId').val();
    var totalShareholderEquityPreviousQuarter =  $('#shareholderTotalShareholderEquityPreviousQuarterId').val();



    var shareholderIndex = financialSurvey.shareholders.findIndex(function (shareholder) {
        return shareholderSrId && (shareholder.srId == shareholderSrId || shareholder.newItemId == parseInt(shareholderSrId));
    });
    var selectedShareholder = financialSurvey.shareholders[shareholderIndex];
    var shareholderRow;
    var percentageRow = (percentage.length > 6 ? percentage.substring(0, 6) : percentage) + '%';
    var shareholderVotingPowerRow = (shareholderVotingPower.length > 6 ? shareholderVotingPower.substring(0, 6) : shareholderVotingPower) + '%';
    var capitalRow = capital;






    // transaction
    var transaction = {};
     transaction.tradeDebitCurrentQuarter = $('#tradeDebitCurrentQuarterId').val() ;
     transaction.tradeCreditCurrentQuarter = $('#tradeCreditCurrentQuarterId').val() ;
     transaction.loansAssetsCurrentQuarter =  $('#loansAssetsCurrentQuarterId').val() ;
     transaction.loansLiabilitiesCurrentQuarter =  $('#loansLiabilitiesCurrentQuarterId').val() ;
     transaction.interestReceivedCurrentQuarter = $('#interestReceivedCurrentQuarterId').val() ;
     transaction.interestPayableCurrentQuarter = $('#interestPayableCurrentQuarterId').val() ;
     transaction.dividendsReceivedCurrentQuarter = $('#dividendsReceivedCurrentQuarterId').val() ;
     transaction.dividendsPaidCurrentQuarter = $('#dividendsPaidCurrentQuarterId').val() ;
     transaction.expensesReceivedCurrentQuarter = $('#expensesReceivedCurrentQuarterId').val() ;
     transaction.expensesPaidCurrentQuarter = $('#expensesPaidCurrentQuarterId').val() ;
     transaction.sellProductionSuppliesCurrentQuarter = $('#sellProductionSuppliesCurrentQuarterId').val() ;
     transaction.purchaseProductionSuppliesCurrentQuarter = $('#purchaseProductionSuppliesCurrentQuarterId').val() ;
     transaction.sellMachineryCurrentQuarter = $('#sellMachineryCurrentQuarterId').val() ;
     transaction.purchaseMachineryCurrentQuarter = $('#purchaseMachineryCurrentQuarterId').val() ;
     transaction.currentDebitAccountCurrentQuarter = $('#currentDebitAccountCurrentQuarterId').val() ;
     transaction.currentCreditAccountCurrentQuarter = $('#currentCreditAccountCurrentQuarterId').val() ;
     transaction.expensesReceivableCurrentQuarter = $('#expensesReceivableCurrentQuarterId').val() ;
     transaction.expensesPayableCurrentQuarter = $('#expensesPayableCurrentQuarterId').val() ;
     transaction.insuranceCommissionReceivableCurrentQuarter = $('#insuranceCommissionReceivableCurrentQuarterId').val() ;
     transaction.insuranceCommissionPayableCurrentQuarter = $('#insuranceCommissionPayableCurrentQuarterId').val() ;
     transaction.otherDebitCurrentQuarter = $('#otherDebitCurrentQuarterId').val() ;
     transaction.otherCreditCurrentQuarter = $('#otherCreditCurrentQuarterId').val() ;
     transaction.totalDebitCurrentQuarter = $('#totalDebitCurrentQuarterId').val() ;
     transaction.totalCreditCurrentQuarter = $('#totalCreditCurrentQuarterId').val() ;


    transaction.tradeDebitPreviousQuarter = $('#tradeDebitPreviousQuarterId').val() ;
    transaction.tradeCreditPreviousQuarter = $('#tradeCreditPreviousQuarterId').val() ;
    transaction.loansAssetsPreviousQuarter =  $('#loansAssetsPreviousQuarterId').val() ;
    transaction.loansLiabilitiesPreviousQuarter =  $('#loansLiabilitiesPreviousQuarterId').val() ;
    transaction.interestReceivedPreviousQuarter = $('#interestReceivedPreviousQuarterId').val() ;
    transaction.interestPayablePreviousQuarter = $('#interestPayablePreviousQuarterId').val() ;
    transaction.dividendsReceivedPreviousQuarter = $('#dividendsReceivedPreviousQuarterId').val() ;
    transaction.dividendsPaidPreviousQuarter = $('#dividendsPaidPreviousQuarterId').val() ;
    transaction.expensesReceivedPreviousQuarter = $('#expensesReceivedPreviousQuarterId').val() ;
    transaction.expensesPaidPreviousQuarter = $('#expensesPaidPreviousQuarterId').val() ;
    transaction.sellProductionSuppliesPreviousQuarter = $('#sellProductionSuppliesPreviousQuarterId').val() ;
    transaction.purchaseProductionSuppliesPreviousQuarter = $('#purchaseProductionSuppliesPreviousQuarterId').val() ;
    transaction.sellMachineryPreviousQuarter = $('#sellMachineryPreviousQuarterId').val() ;
    transaction.purchaseMachineryPreviousQuarter = $('#purchaseMachineryPreviousQuarterId').val() ;
    transaction.currentDebitAccountPreviousQuarter = $('#currentDebitAccountPreviousQuarterId').val() ;
    transaction.currentCreditAccountPreviousQuarter = $('#currentCreditAccountPreviousQuarterId').val() ;
    transaction.expensesReceivablePreviousQuarter = $('#expensesReceivablePreviousQuarterId').val() ;
    transaction.expensesPayablePreviousQuarter = $('#expensesPayablePreviousQuarterId').val() ;
    transaction.insuranceCommissionReceivablePreviousQuarter = $('#insuranceCommissionReceivablePreviousQuarterId').val() ;
    transaction.insuranceCommissionPayablePreviousQuarter = $('#insuranceCommissionPayablePreviousQuarterId').val() ;
    transaction.otherDebitPreviousQuarter = $('#otherDebitPreviousQuarterId').val() ;
    transaction.otherCreditPreviousQuarter = $('#otherCreditPreviousQuarterId').val() ;
    transaction.totalDebitPreviousQuarter = $('#totalDebitPreviousQuarterId').val() ;
    transaction.totalCreditPreviousQuarter = $('#totalCreditPreviousQuarterId').val() ;




    if (selectedShareholder) { // edit shareholder
        shareholderRow = $('#' + shareholderSrId);
        if (individualShareholderName) { // edit individual shareholder
            shareholderRow.children().first().html(individualShareholderName)
                .next().text(getI18nText("general.individual")).next().text(percentageRow).next().text(capitalRow).next().text(shareholderVotingPowerRow);
        } else { // edit entity shareholder
            shareholderRow.children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(percentageRow).next().text(capitalRow).next().text(shareholderVotingPowerRow);
        }
        selectedShareholder.shareholderPercentage = percentage;
        selectedShareholder.shareholderCapital = capital;
        selectedShareholder.shareholderVotingPower = shareholderVotingPower;
        selectedShareholder.valueOfReverseInvestment = shareholderValueOfReverseInvestment;
        selectedShareholder.shareholderPreferredShares = shareholderPreferredShares;
        selectedShareholder.shareholderIsVotingPower = shareholderIsVotingPower;
        selectedShareholder.shareholderHasPreferredShares = shareholderHasPreferredShares;
        selectedShareholder.shareholderHaveReverseInvestment = shareholderHaveReverseInvestment;

        // transaction
        selectedShareholder.transaction = {}
        selectedShareholder.transaction.tradeDebitCurrentQuarter = transaction.tradeDebitCurrentQuarter ;
        selectedShareholder.transaction.tradeCreditCurrentQuarter =transaction.tradeCreditCurrentQuarter ;
        selectedShareholder.transaction.loansAssetsCurrentQuarter =transaction.loansAssetsCurrentQuarter ;
        selectedShareholder.transaction.loansLiabilitiesCurrentQuarter =transaction.loansLiabilitiesCurrentQuarter  ;
        selectedShareholder.transaction.interestReceivedCurrentQuarter =transaction.interestReceivedCurrentQuarter ;
        selectedShareholder.transaction.interestPayableCurrentQuarter = transaction.interestPayableCurrentQuarter ;
        selectedShareholder.transaction.dividendsReceivedCurrentQuarter = transaction.dividendsReceivedCurrentQuarter ;
        selectedShareholder.transaction.dividendsPaidCurrentQuarter = transaction.dividendsPaidCurrentQuarter ;
        selectedShareholder.transaction.expensesReceivedCurrentQuarter =transaction.expensesReceivedCurrentQuarter ;
        selectedShareholder.transaction.expensesPaidCurrentQuarter =transaction.expensesPaidCurrentQuarter ;
        selectedShareholder.transaction.sellProductionSuppliesCurrentQuarter =transaction.sellProductionSuppliesCurrentQuarter ;
        selectedShareholder.transaction.purchaseProductionSuppliesCurrentQuarter = transaction.purchaseProductionSuppliesCurrentQuarter ;
        selectedShareholder.transaction.sellMachineryCurrentQuarter =transaction.sellMachineryCurrentQuarter ;
        selectedShareholder.transaction.purchaseMachineryCurrentQuarter =transaction.purchaseMachineryCurrentQuarter ;
        selectedShareholder.transaction.currentDebitAccountCurrentQuarter =transaction.currentDebitAccountCurrentQuarter ;
        selectedShareholder.transaction.currentCreditAccountCurrentQuarter =transaction.currentCreditAccountCurrentQuarter ;
        selectedShareholder.transaction.expensesReceivableCurrentQuarter =transaction.expensesReceivableCurrentQuarter ;
        selectedShareholder.transaction.expensesPayableCurrentQuarter =transaction.expensesPayableCurrentQuarter ;
        selectedShareholder.transaction.insuranceCommissionReceivableCurrentQuarter =transaction.insuranceCommissionReceivableCurrentQuarter ;
        selectedShareholder.transaction.insuranceCommissionPayableCurrentQuarter =transaction.insuranceCommissionPayableCurrentQuarter ;
        selectedShareholder.transaction.otherDebitCurrentQuarter =transaction.otherDebitCurrentQuarter ;
        selectedShareholder.transaction.otherCreditCurrentQuarter =transaction.otherCreditCurrentQuarter ;
        selectedShareholder.transaction.totalDebitCurrentQuarter =transaction.totalDebitCurrentQuarter ;
        selectedShareholder.transaction.totalCreditCurrentQuarter =transaction.totalCreditCurrentQuarter ;


        selectedShareholder.transaction.tradeDebitPreviousQuarter = transaction.tradeDebitPreviousQuarter ;
        selectedShareholder.transaction.tradeCreditPreviousQuarter =transaction.tradeCreditPreviousQuarter ;
        selectedShareholder.transaction.loansAssetsPreviousQuarter =transaction.loansAssetsPreviousQuarter ;
        selectedShareholder.transaction.loansLiabilitiesPreviousQuarter =transaction.loansLiabilitiesPreviousQuarter  ;
        selectedShareholder.transaction.interestReceivedPreviousQuarter =transaction.interestReceivedPreviousQuarter ;
        selectedShareholder.transaction.interestPayablePreviousQuarter = transaction.interestPayablePreviousQuarter ;
        selectedShareholder.transaction.dividendsReceivedPreviousQuarter = transaction.dividendsReceivedPreviousQuarter ;
        selectedShareholder.transaction.dividendsPaidPreviousQuarter = transaction.dividendsPaidPreviousQuarter ;
        selectedShareholder.transaction.expensesReceivedPreviousQuarter =transaction.expensesReceivedPreviousQuarter ;
        selectedShareholder.transaction.expensesPaidPreviousQuarter =transaction.expensesPaidPreviousQuarter ;
        selectedShareholder.transaction.sellProductionSuppliesPreviousQuarter =transaction.sellProductionSuppliesPreviousQuarter ;
        selectedShareholder.transaction.purchaseProductionSuppliesPreviousQuarter = transaction.purchaseProductionSuppliesPreviousQuarter ;
        selectedShareholder.transaction.sellMachineryPreviousQuarter =transaction.sellMachineryPreviousQuarter ;
        selectedShareholder.transaction.purchaseMachineryPreviousQuarter =transaction.purchaseMachineryPreviousQuarter ;
        selectedShareholder.transaction.currentDebitAccountPreviousQuarter =transaction.currentDebitAccountPreviousQuarter ;
        selectedShareholder.transaction.currentCreditAccountPreviousQuarter =transaction.currentCreditAccountPreviousQuarter ;
        selectedShareholder.transaction.expensesReceivablePreviousQuarter =transaction.expensesReceivablePreviousQuarter ;
        selectedShareholder.transaction.expensesPayablePreviousQuarter =transaction.expensesPayablePreviousQuarter ;
        selectedShareholder.transaction.insuranceCommissionReceivablePreviousQuarter =transaction.insuranceCommissionReceivablePreviousQuarter ;
        selectedShareholder.transaction.insuranceCommissionPayablePreviousQuarter =transaction.insuranceCommissionPayablePreviousQuarter ;
        selectedShareholder.transaction.otherDebitPreviousQuarter =transaction.otherDebitPreviousQuarter ;
        selectedShareholder.transaction.otherCreditPreviousQuarter =transaction.otherCreditPreviousQuarter ;
        selectedShareholder.transaction.totalDebitPreviousQuarter =transaction.totalDebitPreviousQuarter ;
        selectedShareholder.transaction.totalCreditPreviousQuarter =transaction.totalCreditPreviousQuarter ;



        selectedShareholder.retainedEarningsIncludeCurrentQuarter =  retainedEarningsIncludeCurrentQuarter ;
        selectedShareholder.additionalPaidUpCapitalCurrentQuarter  = additionalPaidUpCapitalCurrentQuarter ;
        selectedShareholder.paidUpCapitalCurrentQuarter  = paidUpCapitalCurrentQuarter ;
        selectedShareholder.paidUpCapitalPreviousQuarter  = paidUpCapitalPreviousQuarter ;

        selectedShareholder.profitLossQuarterCurrentQuarter  = profitLossQuarterCurrentQuarter  ;
        selectedShareholder.totalReservesCurrentQuarter  = totalReservesCurrentQuarter  ;
        selectedShareholder.treasurySharesCurrentQuarter  = treasurySharesCurrentQuarter  ;
        selectedShareholder.headOfficeAccountInBranchCurrentQuarter  = headOfficeAccountInBranchCurrentQuarter  ;
        selectedShareholder.shareholderEquityOthersCurrentQuarter  = shareholderEquityOthersCurrentQuarter  ;
        selectedShareholder.minorityRightsCurrentQuarter  = minorityRightsCurrentQuarter  ;
        selectedShareholder.totalShareholderEquityCurrentQuarter  = totalShareholderEquityCurrentQuarter  ;


        selectedShareholder.retainedEarningsIncludePreviousQuarter =  retainedEarningsIncludePreviousQuarter ;
        selectedShareholder.additionalPaidUpCapitalPreviousQuarter  = additionalPaidUpCapitalPreviousQuarter ;
        selectedShareholder.profitLossQuarterPreviousQuarter  = profitLossQuarterPreviousQuarter  ;
        selectedShareholder.totalReservesPreviousQuarter  = totalReservesPreviousQuarter  ;
        selectedShareholder.treasurySharesPreviousQuarter  = treasurySharesPreviousQuarter  ;
        selectedShareholder.headOfficeAccountInBranchPreviousQuarter  = headOfficeAccountInBranchPreviousQuarter  ;
        selectedShareholder.shareholderEquityOthersPreviousQuarter  = shareholderEquityOthersPreviousQuarter  ;
        selectedShareholder.minorityRightsPreviousQuarter  = minorityRightsPreviousQuarter  ;
        selectedShareholder.totalShareholderEquityPreviousQuarter  = totalShareholderEquityPreviousQuarter  ;




        if (gender) { // edit individual shareholder

            selectedShareholder.shareholderNameEnglish = individualShareholderName;
            selectedShareholder.shareholderGender = gender;
            selectedShareholder.shareholderType = '1' ; // person
            selectedShareholder.genderDescription = genderDescription;
            selectedShareholder.maritalStatus = maritalStatus;
            selectedShareholder.maritalStatusDescription = maritalStatusDescription;
            selectedShareholder.shareholderNationalityCurrent = nationalityCurrent;
            selectedShareholder.nationalityCurrentDescription = nationalityCurrentDescription;
            selectedShareholder.shareholderCountry = shareholderCountry;
            selectedShareholder.shareholderCountryDescription = shareholderCountryDescription;
        } else { // edit entity shareholder
            selectedShareholder.companyCountry = companyCountry;
            selectedShareholder.nationalityOfUCP = nationalityOfUCP;

            selectedShareholder.shareholderType = '2' ;
            selectedShareholder.companyCountryDescription = companyCountryDescription;
            selectedShareholder.shareholderNameEnglish = name;
            selectedShareholder.shareholderSector = industry;

            selectedShareholder.industryDescription = industryDescription;
            selectedShareholder.shareholderMultinationalCompany = shareholderMultinationalCompany;
            selectedShareholder.shareholderSubsector = shareholderSubsector;

        }


        if (selectedShareholder.newItemId) { // Edit added shareholder - save all fields
            if (gender) { // edit individual shareholder

                selectedShareholder.shareholderNameEnglish = individualShareholderName;
                selectedShareholder.shareholderGender = gender;
                selectedShareholder.shareholderType = '1' ; // person
                selectedShareholder.genderDescription = genderDescription;
                selectedShareholder.maritalStatus = maritalStatus;
                selectedShareholder.maritalStatusDescription = maritalStatusDescription;
                selectedShareholder.shareholderNationalityCurrent = nationalityCurrent;
                selectedShareholder.nationalityCurrentDescription = nationalityCurrentDescription;
                selectedShareholder.shareholderCountry = shareholderCountry;
                selectedShareholder.shareholderCountryDescription = shareholderCountryDescription;
            } else { // edit entity shareholder
                selectedShareholder.companyCountry = companyCountry;
                selectedShareholder.nationalityOfUCP = nationalityOfUCP;
                selectedShareholder.shareholderType = '2' ;
                selectedShareholder.companyCountryDescription = companyCountryDescription;
                selectedShareholder.shareholderNameEnglish = name;
                selectedShareholder.shareholderSector = industry;
                selectedShareholder.industryDescription = industryDescription;
                selectedShareholder.shareholderMultinationalCompany = shareholderMultinationalCompany;
                selectedShareholder.shareholderSubsector = shareholderSubsector;

            }

        } else {  // Edit existing shareholder
            financialSurvey.shareholders[shareholderIndex].action = '02';
            setColorForModifiedRow(shareholderRow);
        }
    } else { // new shareholder
        shareholderRow = $('.shareholderTemplate').first().clone();
        shareholderRow.show();

        var shareholder = {
            action: '01',
            firstName: '',
            secondName: '',
            companyCountry: '',
            nationalityOfUCP: '',
            shareholderNameEnglish: '',
            shareholderSector: '',
            shareholderSubsector: '',
            shareholderGender: '',
            shareholderGenderDescription: '',
            shareholderNationalityCurrent: '',
            shareholderNationalityCurrentDescription: '',
            shareholderCountry: '',
            shareholderCountryDescription: '',
            shareholderPercentage: '',
            shareholderCapital: '',
            shareholderIsVotingPower: '',
            shareholderVotingPower: '',
            shareholderHasPreferredShares: '',
            shareholderHaveReverseInvestment: '',
            valueOfReverseInvestment: '',
            shareholderMultinationalCompany: '',
            shareholderPreferredShares: '',

            retainedEarningsIncludeCurrentQuarter : '',
            additionalPaidUpCapitalCurrentQuarter  : '',
            paidUpCapitalCurrentQuarter : '',
            paidUpCapitalPreviousQuarter : '',

            profitLossQuarterCurrentQuarter  : '',
            totalReservesCurrentQuarter  : '',
            treasurySharesCurrentQuarter : '',
            headOfficeAccountInBranchCurrentQuarter  : '',
            shareholderEquityOthersCurrentQuarter  : '',
            minorityRightsCurrentQuarter  : '',
            totalShareholderEquityCurrentQuarter  : '',

            retainedEarningsIncludePreviousQuarter : '',
            additionalPaidUpCapitalPreviousQuarter  : '',
            profitLossQuarterPreviousQuarter  : '',
            totalReservesPreviousQuarter  : '',
            treasurySharesPreviousQuarter : '',
            headOfficeAccountInBranchPreviousQuarter  : '',
            shareholderEquityOthersPreviousQuarter  : '',
            minorityRightsPreviousQuarter  : '',
            totalShareholderEquityPreviousQuarter  : '',

            transaction: {
                 tradeDebitCurrentQuarter: '',
                 tradeCreditCurrentQuarter: '',
                 loansAssetsCurrentQuarter: '',
                 loansLiabilitiesCurrentQuarter: '',
                 interestReceivedCurrentQuarter: '',
                 interestPayableCurrentQuarter : '',
                 dividendsReceivedCurrentQuarter : '',
                 dividendsPaidCurrentQuarter : '',
                 expensesReceivedCurrentQuarter  : '',
                 expensesPaidCurrentQuarter : '',
                 sellProductionSuppliesCurrentQuarter : '',
                 purchaseProductionSuppliesCurrentQuarter : '',
                 sellMachineryCurrentQuarter : '',
                 purchaseMachineryCurrentQuarter : '',
                 currentDebitAccountCurrentQuarter : '',
                 currentCreditAccountCurrentQuarter : '',
                 expensesReceivableCurrentQuarter : '',
                 expensesPayableCurrentQuarter : '',
                 insuranceCommissionReceivableCurrentQuarter : '',
                 insuranceCommissionPayableCurrentQuarter : '',
                 otherDebitCurrentQuarter : '',
                 otherCreditCurrentQuarter : '',
                 totalDebitCurrentQuarter  : '',
                 totalCreditCurrentQuarter : '',


                tradeDebitPreviousQuarter: '',
                tradeCreditPreviousQuarter: '',
                loansAssetsPreviousQuarter: '',
                loansLiabilitiesPreviousQuarter: '',
                interestReceivedPreviousQuarter: '',
                interestPayablePreviousQuarter : '',
                dividendsReceivedPreviousQuarter : '',
                dividendsPaidPreviousQuarter : '',
                expensesReceivedPreviousQuarter  : '',
                expensesPaidPreviousQuarter : '',
                sellProductionSuppliesPreviousQuarter : '',
                purchaseProductionSuppliesPreviousQuarter : '',
                sellMachineryPreviousQuarter : '',
                purchaseMachineryPreviousQuarter : '',
                currentDebitAccountPreviousQuarter : '',
                currentCreditAccountPreviousQuarter : '',
                expensesReceivablePreviousQuarter : '',
                expensesPayablePreviousQuarter : '',
                insuranceCommissionReceivablePreviousQuarter : '',
                insuranceCommissionPayablePreviousQuarter : '',
                otherDebitPreviousQuarter : '',
                otherCreditPreviousQuarter : '',
                totalDebitPreviousQuarter  : '',
                totalCreditPreviousQuarter : ''
            }
        };



        shareholder.retainedEarningsIncludeCurrentQuarter =  retainedEarningsIncludeCurrentQuarter ;
        shareholder.additionalPaidUpCapitalCurrentQuarter  = additionalPaidUpCapitalCurrentQuarter ;
        shareholder.paidUpCapitalCurrentQuarter  = paidUpCapitalCurrentQuarter ;
        shareholder.paidUpCapitalPreviousQuarter  = paidUpCapitalPreviousQuarter ;
        shareholder.profitLossQuarterCurrentQuarter  = profitLossQuarterCurrentQuarter  ;
        shareholder.totalReservesCurrentQuarter  = totalReservesCurrentQuarter  ;
        shareholder.treasurySharesCurrentQuarter  = treasurySharesCurrentQuarter  ;
        shareholder.headOfficeAccountInBranchCurrentQuarter  = headOfficeAccountInBranchCurrentQuarter  ;
        shareholder.shareholderEquityOthersCurrentQuarter  = shareholderEquityOthersCurrentQuarter  ;
        shareholder.minorityRightsCurrentQuarter  = minorityRightsCurrentQuarter  ;
        shareholder.totalShareholderEquityCurrentQuarter  = totalShareholderEquityCurrentQuarter  ;
        shareholder.retainedEarningsIncludePreviousQuarter =  retainedEarningsIncludePreviousQuarter ;
        shareholder.additionalPaidUpCapitalPreviousQuarter  = additionalPaidUpCapitalPreviousQuarter ;
        shareholder.profitLossQuarterPreviousQuarter  = profitLossQuarterPreviousQuarter  ;
        shareholder.totalReservesPreviousQuarter  = totalReservesPreviousQuarter  ;
        shareholder.treasurySharesPreviousQuarter  = treasurySharesPreviousQuarter  ;
        shareholder.headOfficeAccountInBranchPreviousQuarter  = headOfficeAccountInBranchPreviousQuarter  ;
        shareholder.shareholderEquityOthersPreviousQuarter  = shareholderEquityOthersPreviousQuarter  ;
        shareholder.minorityRightsPreviousQuarter  = minorityRightsPreviousQuarter  ;
        shareholder.totalShareholderEquityPreviousQuarter  = totalShareholderEquityPreviousQuarter  ;

        if (gender) { // individual shareholder
            shareholderRow.attr("id", newItemId).children().first().html(individualShareholderName)
                .next().text(getI18nText("general.individual")).next().text(percentageRow).next().text(capitalRow).next().text(shareholderVotingPowerRow);;

            shareholder.shareholderType = '1';
            shareholder.shareholderNameEnglish = individualShareholderName;
            shareholder.shareholderGender = gender;
            shareholder.genderDescription = genderDescription;
            shareholder.maritalStatus = maritalStatus;
            shareholder.maritalStatusDescription = maritalStatusDescription;
            shareholder.shareholderNationalityCurrent = nationalityCurrent;
            shareholder.nationalityCurrentDescription = nationalityCurrentDescription;
            shareholder.shareholderCountry = shareholderCountry;
            shareholder.shareholderCountryDescription = shareholderCountryDescription;

        } else { // entity shareholder
            shareholderRow.attr("id", newItemId).children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(percentageRow).next().text(capitalRow).next().text(shareholderVotingPowerRow);;
            shareholder.shareholderType = '2';
            shareholder.shareholderNameEnglish = name;
            shareholder.shareholderSector = industry;
            shareholder.industryDescription = industryDescription;
            shareholder.shareholderMultinationalCompany = shareholderMultinationalCompany;
            shareholder.shareholderSubsector = shareholderSubsector;
            shareholder.companyCountry = companyCountry;
            shareholder.nationalityOfUCP = nationalityOfUCP;
            shareholder.companyCountryDescription = companyCountryDescription;
        }

        shareholder.shareholderPercentage  =  percentage ;
        shareholder.shareholderCapital =   capital;
        shareholder.shareholderPreferredShares = shareholderPreferredShares;
        shareholder.valueOfReverseInvestment = shareholderValueOfReverseInvestment;
        shareholder.shareholderVotingPower = shareholderVotingPower;
        shareholder.shareholderIsVotingPower = shareholderIsVotingPower;
        shareholder.shareholderHasPreferredShares = shareholderHasPreferredShares;
        shareholder.shareholderHaveReverseInvestment = shareholderHaveReverseInvestment;




        //transaction
            shareholder.transaction.currentDebitAccountCurrentQuarter = transaction.currentDebitAccountCurrentQuarter;
            shareholder.transaction.tradeDebitCurrentQuarter= transaction.tradeDebitCurrentQuarter;
            shareholder.transaction.tradeCreditCurrentQuarter = transaction.tradeCreditCurrentQuarter;
            shareholder.transaction.loansAssetsCurrentQuarter = transaction.loansAssetsCurrentQuarter;
            shareholder.transaction.loansLiabilitiesCurrentQuarter = transaction.loansLiabilitiesCurrentQuarter;
            shareholder.transaction.interestReceivedCurrentQuarter = transaction.interestReceivedCurrentQuarter;
            shareholder.transaction.interestPayableCurrentQuarter  = transaction.interestPayableCurrentQuarter;
            shareholder.transaction.dividendsReceivedCurrentQuarter  = transaction.dividendsReceivedCurrentQuarter;
            shareholder.transaction.dividendsPaidCurrentQuarter  = transaction.dividendsPaidCurrentQuarter;
            shareholder.transaction.expensesReceivedCurrentQuarter   = transaction.expensesReceivedCurrentQuarter;
            shareholder.transaction.expensesPaidCurrentQuarter  = transaction.expensesPaidCurrentQuarter;
            shareholder.transaction.sellProductionSuppliesCurrentQuarter  = transaction.sellProductionSuppliesCurrentQuarter;
            shareholder.transaction.purchaseProductionSuppliesCurrentQuarter  = transaction.purchaseProductionSuppliesCurrentQuarter;
            shareholder.transaction.sellMachineryCurrentQuarter  = transaction.sellMachineryCurrentQuarter;
            shareholder.transaction.purchaseMachineryCurrentQuarter  = transaction.purchaseMachineryCurrentQuarter;
            shareholder.transaction.currentDebitAccountCurrentQuarter  = transaction.currentDebitAccountCurrentQuarter;
            shareholder.transaction.currentCreditAccountCurrentQuarter  = transaction.currentCreditAccountCurrentQuarter;
            shareholder.transaction.expensesReceivableCurrentQuarter  = transaction.expensesReceivableCurrentQuarter;
            shareholder.transaction.expensesPayableCurrentQuarter  = transaction.expensesPayableCurrentQuarter;
            shareholder.transaction.insuranceCommissionReceivableCurrentQuarter  = transaction.insuranceCommissionReceivableCurrentQuarter;
            shareholder.transaction.insuranceCommissionPayableCurrentQuarter  = transaction.insuranceCommissionPayableCurrentQuarter;
            shareholder.transaction.otherDebitCurrentQuarter  = transaction.otherDebitCurrentQuarter;
            shareholder.transaction.otherCreditCurrentQuarter  = transaction.otherCreditCurrentQuarter ;
            shareholder.transaction.totalDebitCurrentQuarter   = transaction.totalDebitCurrentQuarter;
            shareholder.transaction.totalCreditCurrentQuarter =   transaction.totalCreditCurrentQuarter;



        shareholder.transaction.currentDebitAccountPreviousQuarter = transaction.currentDebitAccountPreviousQuarter;
        shareholder.transaction.tradeDebitPreviousQuarter= transaction.tradeDebitPreviousQuarter;
        shareholder.transaction.tradeCreditPreviousQuarter = transaction.tradeCreditPreviousQuarter;
        shareholder.transaction.loansAssetsPreviousQuarter = transaction.loansAssetsPreviousQuarter;
        shareholder.transaction.loansLiabilitiesPreviousQuarter = transaction.loansLiabilitiesPreviousQuarter;
        shareholder.transaction.interestReceivedPreviousQuarter = transaction.interestReceivedPreviousQuarter;
        shareholder.transaction.interestPayablePreviousQuarter  = transaction.interestPayablePreviousQuarter;
        shareholder.transaction.dividendsReceivedPreviousQuarter  = transaction.dividendsReceivedPreviousQuarter;
        shareholder.transaction.dividendsPaidPreviousQuarter  = transaction.dividendsPaidPreviousQuarter;
        shareholder.transaction.expensesReceivedPreviousQuarter   = transaction.expensesReceivedPreviousQuarter;
        shareholder.transaction.expensesPaidPreviousQuarter  = transaction.expensesPaidPreviousQuarter;
        shareholder.transaction.sellProductionSuppliesPreviousQuarter  = transaction.sellProductionSuppliesPreviousQuarter;
        shareholder.transaction.purchaseProductionSuppliesPreviousQuarter  = transaction.purchaseProductionSuppliesPreviousQuarter;
        shareholder.transaction.sellMachineryPreviousQuarter  = transaction.sellMachineryPreviousQuarter;
        shareholder.transaction.purchaseMachineryPreviousQuarter  = transaction.purchaseMachineryPreviousQuarter;
        shareholder.transaction.currentDebitAccountPreviousQuarter  = transaction.currentDebitAccountPreviousQuarter;
        shareholder.transaction.currentCreditAccountPreviousQuarter  = transaction.currentCreditAccountPreviousQuarter;
        shareholder.transaction.expensesReceivablePreviousQuarter  = transaction.expensesReceivablePreviousQuarter;
        shareholder.transaction.expensesPayablePreviousQuarter  = transaction.expensesPayablePreviousQuarter;
        shareholder.transaction.insuranceCommissionReceivablePreviousQuarter  = transaction.insuranceCommissionReceivablePreviousQuarter;
        shareholder.transaction.insuranceCommissionPayablePreviousQuarter  = transaction.insuranceCommissionPayablePreviousQuarter;
        shareholder.transaction.otherDebitPreviousQuarter  = transaction.otherDebitPreviousQuarter;
        shareholder.transaction.otherCreditPreviousQuarter  = transaction.otherCreditPreviousQuarter ;
        shareholder.transaction.totalDebitPreviousQuarter   = transaction.totalDebitPreviousQuarter;
        shareholder.transaction.totalCreditPreviousQuarter =   transaction.totalCreditPreviousQuarter;




        setColorForNewRow(shareholderRow);
        $('#shareholdersId').append(shareholderRow);

        shareholder.newItemId = newItemId++;
        financialSurvey.shareholders.push(shareholder);
    }

    shareholderRow.find('.removeShareholderBtn').on('click', removeShareholder);
    shareholderRow.find('.editShareholderBtn').on('click', editShareholder);

    $("#shareholderModalId").modal('hide');
    enableShareholderForm();
    clearShareholderForm();
};

function resetFormOnModalClose() {
    $('#shareholderModalId').on('hidden.bs.modal', function () {
        $("#identityNumberCopy").parents('.col-md-6').remove();
        loadPersonShareholderForm();
        $('#delegateDetails').hide();
        $('#verifyDelegateDetails').hide();
    });
}

function resetVerifyInherit() {
	$('#deedNumber').val(null);
    $('#deceasedId').val(null);
    $('#deceasedName').val(null);
    $('#isMojVerified').val(null);
}

function clearShareholderForm() {
    var $sector = $('#shareholderSectorId');
    $sector.val(null).trigger('change');
    $sector.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $multinationalCompany = $('#shareholderMultinationalCompanyId');
    $multinationalCompany.val(null).trigger('change');
    $multinationalCompany.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $legalStatus = $('#shareholderLegalStatusId');
    $legalStatus.val(null).trigger('change');
    $legalStatus.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $academicTitle = $('#shareholderAcademicTitleId');
    $academicTitle.val(null).trigger('change');
    $academicTitle.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $gender = $('#shareholderGenderId');
    $gender.val(null).trigger('change');
    $gender.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $maritalStatus = $('#shareholderMaritalStatusId');
    $maritalStatus.val(null).trigger('change');
    $maritalStatus.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $premiumResident = $('#shareholderPremiumResidentId');
    $premiumResident.val(null).trigger('change');
    $premiumResident.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityCurrent = $('#shareholderNationalityCurrentId');
    $nationalityCurrent.val(null).trigger('change');
    $nationalityCurrent.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityPrevious = $('#shareholderNationalityPreviousId');
    $nationalityPrevious.val(null).trigger('change');
    $nationalityPrevious.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $country = $('#shareholderCountryId');
    $country.val(null).trigger('change');
    $country.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    $('#shareholderNameId').val('');
    $('#shareholderNameEnglishId').val('');
    $('#shareholderFirstNameId').val('');
    $('#shareholderLastNameId').val('');
    $('#birthDateId').val('');
    $('#shareholderPercentageId').val('');
    $('#shareholderInheritedPropertyId').prop('checked', false);
    $('#shareholderCityId').val('');
    $('#shareholderStreetId').val('');
    $('#shareholderNumberId').val('');
    $('#shareholderZipCodeId').val('');
    $('#shareholderTelephoneId').val('');
    $('#shareholderEmailId').val('');
    $('#shareholderWebsiteId').val('');


    $('#shareholderRetainedEarningsIncludeCurrentQuarterId').val('');
    $('#shareholderAdditionalPaidUpCapitalCurrentQuarterId').val('');
    $('#shareholderPaidUpCapitalCurrentQuarterId').val('');
    $('#shareholderPaidUpCapitalPreviousQuarterId').val('');

    $('#shareholderProfitLossQuarterCurrentQuarterId').val('');
    $('#shareholderTotalReservesCurrentQuarterId').val('');
    $('#shareholderTreasurySharesCurrentQuarterId').val('');
    $('#shareholderHeadOfficeAccountInBranchCurrentQuarterId').val('');
    $('#shareholderShareholderEquityOthersCurrentQuarterId').val('');
    $('#shareholderMinorityRightsCurrentQuarterId').val('');
    $('#shareholderTotalShareholderEquityCurrentQuarterId').val('');

    $('#shareholderRetainedEarningsIncludePreviousQuarterId').val('');
    $('#shareholderAdditionalPaidUpCapitalPreviousQuarterId').val('');
    $('#shareholderProfitLossQuarterPreviousQuarterId').val('');
    $('#shareholderTotalReservesPreviousQuarterId').val('');
    $('#shareholderTreasurySharesPreviousQuarterId').val('');
    $('#shareholderHeadOfficeAccountInBranchPreviousQuarterId').val('');
    $('#shareholderShareholderEquityOthersPreviousQuarterId').val('');
    $('#shareholderMinorityRightsPreviousQuarterId').val('');
    $('#shareholderTotalShareholderEquityPreviousQuarterId').val('');


    $('.saveShareholderBtn').removeAttr('id');

    $('#shareholderFirstNameId').removeAttr('disabled');
    $("#shareholderLastNameId").removeAttr('disabled');
    $("#birthDateId").removeAttr('disabled');
    $("#shareholderGenderId").removeAttr('disabled');
    $("#shareholderPremiumResidentId").removeAttr('disabled');
    $("#identityNumberCopy").removeAttr('disabled');

    //resetVerifyInherit();
}

function clearDelegateDetailsForm() {


    var $academicTitle = $('#delegateAcademicTitleId');
    $academicTitle.val(null).trigger('change');
    $academicTitle.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $gender = $('#delegateGenderId');
    $gender.val(null).trigger('change');
    $gender.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $maritalStatus = $('#delegateMaritalStatusId');
    $maritalStatus.val(null).trigger('change');
    $maritalStatus.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $premiumResident = $('#delegatePremiumResidentId');
    $premiumResident.val(null).trigger('change');
    $premiumResident.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityCurrent = $('#delegateNationalityCurrentId');
    $nationalityCurrent.val(null).trigger('change');
    $nationalityCurrent.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityPrevious = $('#delegateNationalityPreviousId');
    $nationalityPrevious.val(null).trigger('change');
    $nationalityPrevious.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $country = $('#delegateCountry');
    $country.val(null).trigger('change');
    $country.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $country = $('#delegateNationality');
    $country.val(null).trigger('change');
    $country.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");




}

var removeShareholderErrorsIfExists = function () {
    if ($(this).closest('.form-group').hasClass('has-error')) {
        $(this.closest('.form-group')).removeClass('has-error');
    }

    if ($(this).closest('.formSelectBox').length > 0) {
        $(this).closest('.formSelectBox').find('.help-block').text('');
    }
};

var clickPersonID = function () {
//	 $('#showDelegateQuestionOrganization').show();
	$('#showDelegateQuestionOrganization').hide();
	 $('#showDelegateQuestion').show();
};



var removePreviousErrors = function () {
	 $('#showDelegateQuestionOrganization').show();
	 $('#showDelegateQuestion').hide();
	clearShareholderForm();

	 $("#contentNewShareholderForm").find("#hasDelegateYES").click();

    $('.form-group').each(function (i, obj) {
        if ($(this).hasClass('has-error')) {
            $(this).removeClass('has-error');
        }

        if ($(this).find('.help-block:first').length > 0) {
            $(this).find('.help-block:first').text('');
        }

        if ($(this).closest('.formSelectBox').length > 0) {
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }

        if ($(this).closest('.formInputBox').length > 0) {
            $(this).closest('.formInputBox').find('.help-block').text('');
        }

    });

};

$("#shareholderTelephoneId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#shareholderZipCodeId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#shareholderNumberId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});