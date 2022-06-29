SAGIA.financialSurvey.affiliates  = SAGIA.financialSurvey.affiliates || {};

$(document).ready(function () {

    //prevents removal of modal-open class on body when two modals are open and one of them is closed.
    $(document).on('hidden.bs.modal', '#errorResponseModal', function () {
        if($('.modal.show').length)
        {
            $('body').addClass('modal-open');
        }
    });

    if ($('body').hasClass('page-financial-survey')) {
        $('#affiliatePersonEntityTypeId').show();
        $('#bpNumberGroupId').hide();
        $('#entityAffiliateId').hide();


        //$('#contentNewAffiliateForm').hide();
        loadAffiliatePersonForm();

        $(".removeAffiliateBtn").on('click', removeAffiliate);
        $(".newAffiliateBtn, #newAffiliateId").on('click', newAffiliate);
        $(document).on('click', ".editAffiliateBtn", editAffiliate);
        // $(document).on('click', ".editAffiliateBtn", newEditAffiliate);
        if ($(".editAffiliateBtn").length > 0) {
            // bindSaveAffiliateClick();
            // bindAffiliateEditModalClose();
        }
        $('.cancelAffiliateBtn').on('click', function () {
            enableAffiliateForm();
            clearAffiliateForm();
        });
        $('#affiliatePersonId').on('click', clickAffiliatePersonID);
       //$('#affiliateAcademicTitleId').on('change', removeAffiliateErrorsIfExists);
        //$('#affiliateMaritalStatusId').on('change', removeAffiliateErrorsIfExists);
        //$('#affiliatePremiumResidentId').on('change', removeAffiliateErrorsIfExists);
        $('#affiliateGenderId').on('change', removeAffiliateErrorsIfExists);
        $('#affiliateNationalityCurrentId').on('change', removeAffiliateErrorsIfExists);
       // $('#affiliateNationalityPreviousId').on('change', removeAffiliateErrorsIfExists);
        $('#affiliateCountryId').on('change', removeAffiliateErrorsIfExists);
        //$('#existingAffiliateId').on('click', removePreviousAffiliateErrors);
        //$('#newAffiliateId').on('click', removePreviousAffiliateErrors);
        $('#affiliateEntityId').on('click', removePreviousAffiliateErrors);

        $('.saveAffiliateBtn').on('click', function() {
                saveAffiliate(false);
        });

        $('#affiliatePersonEntityTypeId').show();
       // $('#bpNumberGroupId').hide();
        clearAffiliateForm();
        enableAffiliateForm();
        $('#affiliateValidationDetails').show();
        enableDisableAffiliateFormSection(false);
        $('#entityAffiliateId').show();
     //   $('#companyVerificationSection').hide();


        $("input[name='affiliatePersonEntityRadioBox']").click(function () {
            if ($(this).val() === "true") {
                loadAffiliatePersonForm();

                //For passport
                var type = "Person";
                var element = $("#affiliateValidationDetails");
                enableDisableAffiliateFormSection(true);
                enableDisablePassportIdAffiliateForm(true);
                resetAffiliateDetails(element, type);
            } else {
                loadAffiliateOrganizationForm();
                loadAffiliateOrganizationFormFields();
            }
        });


        $("#contentNewAffiliateForm").find("#hasDelegateYES").on("click", function () {
            $("#contentNewAffiliateForm").find("#hasDelegateYESLabel").css("border-color", "");

            var type = "Person";
            var element = $("#delegate");
            resetDelegateDetails(element, type);
            toggleDelegateDetails($("#contentNewAffiliateForm"), false);
        });

        $("#contentNewAffiliateForm").find("#hasDelegateNO").on("click", function () {
            $("#contentNewAffiliateForm").find("#hasDelegateNOLabel").css("border-color", "");
            toggleDelegateDetails($("#contentNewAffiliateForm"), true);
        });


        resetAffiliateFormOnModalClose()
    }
});


var loadInvestorCrResponseEvent = function(data, crNumber){
    if(data.nameEnglish){
        $("#affiliateNameEnglishId").val(data.nameEnglish.replace(/[^a-zA-Z0-9 ]+/g, "")).attr("disabled", true);
    }
    if(data.nameArabic){
        $("#affiliateNameId").val(data.nameArabic.replace(/[^\u0621-\u064A\u0660-\u0669 ]+/g, "")).attr("disabled", true);
    }
    if(data.capital){
        $("#affiliateCapitalId").val(data.capital.replace(/\D+/g, "")).attr("disabled", true);
    }
    $("#professionalLicenseCrVerified").val(true);
    $("#inputCRNumber").val(crNumber);

    loadAffiliateOrganizationFormFields();
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

var resetAffiliateDetails = function(element, type)
{
    clearAffiliateForm();
   // toggleTheNICFieldsEditable(element, type, true);
};




var enableDisableAffiliateFormSection = function (enable) {
    if(enable) {
        $('#contentNewAffiliateForm').show();
        $('#individualAffiliateId').show();
        $('#affiliateAddressId').show();

    } else {
        $('#contentNewAffiliateForm').hide();
        $('#individualAffiliateId').hide();
        $('#affiliateAddressId').hide();
    }
};

var enableDisablePassportIdAffiliateForm = function (enable) {
    if(enable) {
        $('#delegateDivSection').show();
    } else {
        $('#delegateDivSection').hide();
    }
};

var resetCompanyCountriesSection = function () {
    var entitySection = $('#entityAffiliateId');

    entitySection.find("#load-investor").attr("disabled", true);

};

var loadAffiliateOrganizationForm = function () {
    $('#affiliateValidationSection').show();
    $('#affiliateValidationDetails').hide();

    $('#contentNewAffiliateForm').show();
    $('#individualAffiliateId').hide();
    $('#entityAffiliateId').show();
    $('#affiliateEntityBasicInformation').hide();
    $('#affiliateEntityBasicInformation2').hide();

    $('.contentModule-headline').hide();
    $('#affiliateAddressId').hide();

    // Delegate section
    $('#delegateDivSection').hide();
    $('#delegate').hide();

    $('#companyVerificationSection').show();

    resetCompanyCountriesSection();

};

var loadAffiliateOrganizationFormFields = function () {
    $('#affiliateValidationSection').show();
    $('#affiliateValidationDetails').hide();

    $('#contentNewAffiliateForm').show();
    $('#individualAffiliateId').hide();
    $('#entityAffiliateId').show();
    $('#affiliateEntityBasicInformation').show();
    $('#affiliateEntityBasicInformation2').show();

    $('.contentModule-headline').show();
    $('#affiliateAddressId').show();

    // Delegate section
    $('#delegateDivSection').show();
    $('#delegate').show();

};

var loadAffiliatePersonForm = function () {
    $('#affiliateValidationSection').show();
    $('#affiliateValidationDetails').show();
    $('#affiliateEntityBasicInformation2').show();
    $('#contentNewAffiliateForm').hide();
    $('#individualAffiliateId').hide();
    $('#entityAffiliateId').hide();
    $('#inheritsection').hide();


};




var removeAffiliate = function () {
    financialSurvey.dirtyAmendment = true;
    var $affiliateRow = $(this).closest('tr');
    $affiliateRow.remove();

    var affiliateId = parseInt($affiliateRow.attr('id'));
    var affiliates = financialSurvey.affiliates;
    for (var i = 0; i < affiliates.length; i++) {
        if (affiliateId == affiliates[i].srId) {
            affiliates[i].action = '03';
            break;
        } else if (affiliateId == affiliates[i].newItemId) {
            affiliates.splice(i, 1);
            break;
        }
    }
};


var newAffiliate = function () {

	 $('#showDelegateQuestionOrganization').hide();
	 $('#showDelegateQuestion').show();
    clearAffiliateValidation();
    $('#affiliateNewExistingTypeId').show();
    $('#affiliatePersonEntityTypeId').show();
    $('#bpNumberGroupId').hide();
    enableAffiliateForm();
    clearAffiliateForm();
    $('#affiliatePersonId').click();
    $('#affiliateIdType').removeAttr('disabled');
    $('#verifyAffiliateDetailsShow').removeAttr('disabled');
    $('#affiliateDateofBirth').removeAttr('disabled');
    $('#affiliateIdNumber').removeAttr('disabled');
    $('#affiliateIdTypeSection').show();
};

function enableAffiliateForm() {
    $("#entityAffiliateId :input").attr("disabled", false);
    $("#individualAffiliateId :input").attr("disabled", false);
    $('#affiliateInheritedPropertyId').attr("disabled", false);
    $("#affiliateAddressId :input").attr("disabled", false);
    resetVerifyInherit();
}

function disableAffiliateForm() {
    $("#entityAffiliateId :input").attr("disabled", true);
    $("#individualAffiliateId :input").attr("disabled", true);
    $('#affiliateInheritedPropertyId').attr("disabled", true);
    $("#affiliateAddressId :input").attr("disabled", true);
}


function disableMCFields() {
	$("#affiliateNameEnglishId").attr("disabled", true);
    $("#affiliateNameId").attr("disabled", true);
    $("#affiliateCapitalId").attr("disabled", true);


}

function enableMCFields() {
	$("#affiliateNameEnglishId").attr("disabled", false);
    $("#affiliateNameId").attr("disabled", false);
    $("#affiliateCapitalId").attr("disabled", false);

}



var newEditAffiliate = function () {
    var selectedAffiliateId = $(this).closest('tr').attr('id');
    var index = financialSurvey.affiliates.findIndex(function (affiliate) {
        return affiliate.srId == selectedAffiliateId || affiliate.newItemId == selectedAffiliateId;
    });

    // determine index by screen position assuming array has the same config.
    index = $(this).parents('tr').index() - 1;

    var selectedAffiliate = financialSurvey.affiliates[index];

}

var bindSaveAffiliateClick = function () {
    $(document).on('click', '.saveEditAffiliateBtn', function () {


    });
}

var bindAffiliateEditModalClose = function () {
    var form = $('.editAffiliate');
    var input = form.find('input');
    if (input.attr('data-old-value') != input.val()) {
        form.parents('.modal').on('hide.bs.modal', function () {

        });
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

var editAffiliate = function () {
    clearAffiliateValidation();
    var selectedAffiliateId = $(this).closest('tr').attr('id');
    var selectedAffiliate = financialSurvey.affiliates.find(function (affiliate) {
        return affiliate.srId == selectedAffiliateId || affiliate.newItemId == selectedAffiliateId;
    });

    // Hide block new si type
    $('#affiliateNewExistingTypeId').hide();
    $('#affiliatePersonEntityTypeId').hide();
    $('#bpNumberGroupId').hide();

     enableAffiliateForm();
    fillAffiliateForm(selectedAffiliate);
    $('.saveAffiliateBtn').attr('id', selectedAffiliate.srId || selectedAffiliate.newItemId);
    SAGIA.formElements.placeholderPolyfill();
    prepareVisibleItemsEdit(selectedAffiliate);
    /*if(selectedAffiliate.idType !== '4' ){
    	disableNICFields();

    }else {
    	enableNICFields();
    }*/

    $('#affiliateEntityBasicInformation2').show();

};

function prepareVisibleItemsEdit(selectedAffiliate) {
    $('#affiliateFormId').find('.contentModule-headline').show();

    $('#affiliateValidationDetails').hide();
    $('#contentNewAffiliateForm').show();

    if (selectedAffiliate.srId || (!selectedAffiliate.srId && selectedAffiliate.idType !== '4')) {
        // Delegate section
        $('#delegateDivSection').hide();
        $('#delegate').hide();
    } else {
        // Delegate section
        $('#delegateDivSection').show();
        $('#delegate').show();
    }



    //if an Entity
    if (selectedAffiliate.affiliateType === '2') {
    	 $('#affiliateEntityBasicInformation').show();
    	 $('#showDelegateQuestionOrganization').show();
    	 $('#showDelegateQuestion').hide();

    	 $('#affiliateAddressId').show();
    	 $('#affiliateCompanyCountry').attr("disabled", true);
    	 $('#inputCRNumber').attr("disabled", true);
    	 $('#load-investor').attr("disabled", true);

    	 $('#delegateDivSection').show();
         $('#delegate').show();

    	if (selectedAffiliate.isCrVerified ) { // verified Entity
            // Delegate section
        //    $('#affiliateEntityBasicInformation').show();
           // $('#delegate').hide();

            disableMCFields();
        } else{     // Not Verified Entity
            // Delegate section


            //enableMCFields() ;
        }

    	if (selectedAffiliate.srId){  // hide delegate section for existing enity
    		     $('#delegateDivSection').hide();
    	         $('#delegate').hide();
    	}else {


    		if(selectedAffiliate.companyCountry !== 'SA' ){
    		   	 $('#delegateDivSection').show();

    		   }else {
    		   	 $('#delegateDivSection').hide();
    		   }

    	}

    }else {


    	if(!selectedAffiliate.srId && selectedAffiliate.idNumber  !== '' ) {
   		 $('#affiliateValidationDetails').show();
      	     $('#affiliateIdTypeSection').hide();

            $('#affiliateIdNumber').val(selectedAffiliate.idNumber);
            if (selectedAffiliate.birthDateString) {
                $("#affiliateDateofBirth").val(selectedAffiliate.birthDateString);
            } else if (selectedAffiliate.birthDate && selectedAffiliate.birthDate.millis) {
                $("#affiliateDateofBirth")._flatpickr.setDate(new Date(selectedAffiliate.birthDate.millis));
            }
            $('#affiliateIdType').attr('disabled','disabled');
            $('#verifyAffiliateDetailsShow').attr('disabled','disabled');
            $('#affiliateDateofBirth').attr('disabled','disabled');
            $('#affiliateIdNumber').attr('disabled','disabled');

      	  }


    }

}

function fillAffiliateForm(selectedAffiliate) {
    if (selectedAffiliate.affiliateType === '2') {
        $('#individualAffiliateId').hide();
        $('#entityAffiliateId').show();
        $('#affiliateNameEnglishId').val(selectedAffiliate.affiliateNameEnglish);
        $('#inputCRNumber').val(selectedAffiliate.inputCRNumber);

        updateDropDown('#affiliateCompanyCountry', selectedAffiliate.affiliateCountry);
        updateDropDown('#affiliateSectorId', selectedAffiliate.affiliateSector);
        updateDropDown('#affiliateMultinationalCompanyId', selectedAffiliate.affiliateMultinationalCompany);
        updateDropDown('#affiliateLegalStatusId', selectedAffiliate.legalStatus);
        $('#affiliateSubsectorId').val(selectedAffiliate.affiliateSubsector);
    } else {

        $('#entityAffiliateId').hide();
        $('#individualAffiliateId').show();
        $('#individualAffiliateNameEnglishId').val(selectedAffiliate.affiliateNameEnglish);
        updateDropDown('#affiliateGenderId', selectedAffiliate.affiliateGender);
        updateDropDown('#affiliateMaritalStatusId', selectedAffiliate.maritalStatus);
        updateDropDown('#affiliateNationalityCurrentId', selectedAffiliate.affiliateNationalityCurrent);
        updateDropDown('#affiliateCountryId', selectedAffiliate.affiliateCountry);

    }


    $('#accordionTransactionAffeliate #tradeDebitCurrentQuarterId').val(selectedAffiliate.transaction.tradeDebitCurrentQuarter);
    $('#accordionTransactionAffeliate #tradeCreditCurrentQuarterId').val(selectedAffiliate.transaction.tradeCreditCurrentQuarter);
    $('#accordionTransactionAffeliate #loansAssetsCurrentQuarterId').val(selectedAffiliate.transaction.loansAssetsCurrentQuarter);
    $('#accordionTransactionAffeliate #loansLiabilitiesCurrentQuarterId').val(selectedAffiliate.transaction.loansLiabilitiesCurrentQuarter);
    $('#accordionTransactionAffeliate #interestReceivedCurrentQuarterId').val(selectedAffiliate.transaction.interestReceivedCurrentQuarter);
    $('#accordionTransactionAffeliate #interestPayableCurrentQuarterId').val(selectedAffiliate.transaction.interestPayableCurrentQuarter);
    $('#accordionTransactionAffeliate #dividendsReceivedCurrentQuarterId').val(selectedAffiliate.transaction.dividendsReceivedCurrentQuarter);
    $('#accordionTransactionAffeliate #dividendsPaidCurrentQuarterId').val(selectedAffiliate.transaction.dividendsPaidCurrentQuarter);
    $('#accordionTransactionAffeliate #expensesReceivedCurrentQuarterId').val(selectedAffiliate.transaction.expensesReceivedCurrentQuarter);
    $('#accordionTransactionAffeliate #expensesPaidCurrentQuarterId').val(selectedAffiliate.transaction.expensesPaidCurrentQuarter);
    $('#accordionTransactionAffeliate #sellProductionSuppliesCurrentQuarterId').val(selectedAffiliate.transaction.sellProductionSuppliesCurrentQuarter);
    $('#accordionTransactionAffeliate #purchaseProductionSuppliesCurrentQuarterId').val(selectedAffiliate.transaction.purchaseProductionSuppliesCurrentQuarter);
    $('#accordionTransactionAffeliate #sellMachineryCurrentQuarterId').val(selectedAffiliate.transaction.sellMachineryCurrentQuarter);
    $('#accordionTransactionAffeliate #purchaseMachineryCurrentQuarterId').val(selectedAffiliate.transaction.purchaseMachineryCurrentQuarter);
    $('#accordionTransactionAffeliate #currentDebitAccountCurrentQuarterId').val(selectedAffiliate.transaction.currentDebitAccountCurrentQuarter);
    $('#accordionTransactionAffeliate #currentCreditAccountCurrentQuarterId').val(selectedAffiliate.transaction.currentCreditAccountCurrentQuarter);
    $('#accordionTransactionAffeliate #expensesReceivableCurrentQuarterId').val(selectedAffiliate.transaction.expensesReceivableCurrentQuarter);
    $('#accordionTransactionAffeliate #expensesPayableCurrentQuarterId').val(selectedAffiliate.transaction.expensesPayableCurrentQuarter);
    $('#accordionTransactionAffeliate #insuranceCommissionReceivableCurrentQuarterId').val(selectedAffiliate.transaction.insuranceCommissionReceivableCurrentQuarter);
    $('#accordionTransactionAffeliate #insuranceCommissionPayableCurrentQuarterId').val(selectedAffiliate.transaction.insuranceCommissionPayableCurrentQuarter);
    $('#accordionTransactionAffeliate #otherDebitCurrentQuarterId').val(selectedAffiliate.transaction.otherDebitCurrentQuarter);
    $('#accordionTransactionAffeliate #otherCreditCurrentQuarterId').val(selectedAffiliate.transaction.otherCreditCurrentQuarter);
    $('#accordionTransactionAffeliate #totalDebitCurrentQuarterId').val(selectedAffiliate.transaction.totalDebitCurrentQuarter);
    $('#accordionTransactionAffeliate #totalCreditCurrentQuarterId').val(selectedAffiliate.transaction.totalCreditCurrentQuarter);

    updateDropDown('#affiliateCountryId', selectedAffiliate.affiliateCountry);




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
function updateAffiliatePremiumResident(select, value, disableFieldIfValueNotBlank) {
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


var saveAffiliate = function (existingBp, bpId) {


    var validator = affiliateValidator();
    if (!validator.form()) {
        return;
    }
    financialSurvey.dirtyAmendment = true;
    var saveAffiliateBtn = $('.saveAffiliateBtn');
    var affiliateSrId = saveAffiliateBtn.attr('id');
    saveAffiliateBtn.removeAttr('id');
    var affiliateCompanyCountry = $('#affiliateCompanyCountry option:selected');
    var companyCountry = affiliateCompanyCountry.val();
    var companyCountryDescription = affiliateCompanyCountry.text();
    var name = $('#affiliateNameEnglishId').val();
    var individualAffiliateName = $('#individualAffiliateNameEnglishId').val();
    var subsector = $('#affiliateSubsectorId').val();
    var multinationalCompany = $('#affiliateMultinationalCompanyId option:selected').val();
    var $industry = $('#affiliateSectorId option:selected');
    var industry = $industry.val();
    var industryDescription = $industry.text();
    var $gender = $('#affiliateGenderId option:selected');
    var gender = $gender.val();
    var genderDescription = $gender.text();
    var $maritalStatus = $('#affiliateMaritalStatusId option:selected');
    var maritalStatus = $maritalStatus.val();
    var maritalStatusDescription = $maritalStatus.text();
    var $nationalityCurrent = $('#affiliateNationalityCurrentId option:selected');
    var nationalityCurrent = $nationalityCurrent.val();
    var nationalityCurrentDescription = $nationalityCurrent.text();
    var $affiliateCountry = $('#affiliateCountryId option:selected');
    var affiliateCountry = $affiliateCountry.val();
    var affiliateCountryDescription = $affiliateCountry.text();
    var affiliateIndex = financialSurvey.affiliates.findIndex(function (affiliate) {
        return affiliateSrId && (affiliate.srId == affiliateSrId || affiliate.newItemId == parseInt(affiliateSrId));
    });
    var selectedAffiliate = financialSurvey.affiliates[affiliateIndex];
    var affiliateRow;


    // transaction
    var transaction = {};
     transaction.tradeDebitCurrentQuarter = $('#accordionTransactionAffeliate #tradeDebitCurrentQuarterId').val() ;
     transaction.tradeCreditCurrentQuarter = $('#accordionTransactionAffeliate #tradeCreditCurrentQuarterId').val() ;
     transaction.loansAssetsCurrentQuarter =  $('#accordionTransactionAffeliate #loansAssetsCurrentQuarterId').val() ;
     transaction.loansLiabilitiesCurrentQuarter =  $('#accordionTransactionAffeliate #loansLiabilitiesCurrentQuarterId').val() ;
     transaction.interestReceivedCurrentQuarter = $('#accordionTransactionAffeliate #interestReceivedCurrentQuarterId').val() ;
     transaction.interestPayableCurrentQuarter = $('#accordionTransactionAffeliate #interestPayableCurrentQuarterId').val() ;
     transaction.dividendsReceivedCurrentQuarter = $('#accordionTransactionAffeliate #dividendsReceivedCurrentQuarterId').val() ;
     transaction.dividendsPaidCurrentQuarter = $('#accordionTransactionAffeliate #dividendsPaidCurrentQuarterId').val() ;
     transaction.expensesReceivedCurrentQuarter = $('#accordionTransactionAffeliate #expensesReceivedCurrentQuarterId').val() ;
     transaction.expensesPaidCurrentQuarter = $('#accordionTransactionAffeliate #expensesPaidCurrentQuarterId').val() ;
     transaction.sellProductionSuppliesCurrentQuarter = $('#accordionTransactionAffeliate #sellProductionSuppliesCurrentQuarterId').val() ;
     transaction.purchaseProductionSuppliesCurrentQuarter = $('#accordionTransactionAffeliate #purchaseProductionSuppliesCurrentQuarterId').val() ;
     transaction.sellMachineryCurrentQuarter = $('#accordionTransactionAffeliate #sellMachineryCurrentQuarterId').val() ;
     transaction.purchaseMachineryCurrentQuarter = $('#accordionTransactionAffeliate #purchaseMachineryCurrentQuarterId').val() ;
     transaction.currentDebitAccountCurrentQuarter = $('#accordionTransactionAffeliate #currentDebitAccountCurrentQuarterId').val() ;
     transaction.currentCreditAccountCurrentQuarter = $('#accordionTransactionAffeliate #currentCreditAccountCurrentQuarterId').val() ;
     transaction.expensesReceivableCurrentQuarter = $('#accordionTransactionAffeliate #expensesReceivableCurrentQuarterId').val() ;
     transaction.expensesPayableCurrentQuarter = $('#accordionTransactionAffeliate #expensesPayableCurrentQuarterId').val() ;
     transaction.insuranceCommissionReceivableCurrentQuarter = $('#accordionTransactionAffeliate #insuranceCommissionReceivableCurrentQuarterId').val() ;
     transaction.insuranceCommissionPayableCurrentQuarter = $('#accordionTransactionAffeliate #insuranceCommissionPayableCurrentQuarterId').val() ;
     transaction.otherDebitCurrentQuarter = $('#accordionTransactionAffeliate #otherDebitCurrentQuarterId').val() ;
     transaction.otherCreditCurrentQuarter = $('#accordionTransactionAffeliate #otherCreditCurrentQuarterId').val() ;
     transaction.totalDebitCurrentQuarter = $('#accordionTransactionAffeliate #totalDebitCurrentQuarterId').val() ;
     transaction.totalCreditCurrentQuarter = $('#accordionTransactionAffeliate #totalCreditCurrentQuarterId').val() ;




    if (selectedAffiliate) { // edit affiliate
        affiliateRow = $('#' + affiliateSrId);
        if (individualAffiliateName) { // edit individual affiliate
            affiliateRow.children().first().html(individualAffiliateName)
                .next().text(getI18nText("general.individual")).next().text(affiliateCountryDescription);
        } else { // edit entity affiliate
            affiliateRow.children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(companyCountryDescription);
        }


        // transaction
        selectedAffiliate.transaction = {}
        selectedAffiliate.transaction.tradeDebitCurrentQuarter = transaction.tradeDebitCurrentQuarter ;
        selectedAffiliate.transaction.tradeCreditCurrentQuarter =transaction.tradeCreditCurrentQuarter ;
        selectedAffiliate.transaction.loansAssetsCurrentQuarter =transaction.loansAssetsCurrentQuarter ;
        selectedAffiliate.transaction.loansLiabilitiesCurrentQuarter =transaction.loansLiabilitiesCurrentQuarter  ;
        selectedAffiliate.transaction.interestReceivedCurrentQuarter =transaction.interestReceivedCurrentQuarter ;
        selectedAffiliate.transaction.interestPayableCurrentQuarter = transaction.interestPayableCurrentQuarter ;
        selectedAffiliate.transaction.dividendsReceivedCurrentQuarter = transaction.dividendsReceivedCurrentQuarter ;
        selectedAffiliate.transaction.dividendsPaidCurrentQuarter = transaction.dividendsPaidCurrentQuarter ;
        selectedAffiliate.transaction.expensesReceivedCurrentQuarter =transaction.expensesReceivedCurrentQuarter ;
        selectedAffiliate.transaction.expensesPaidCurrentQuarter =transaction.expensesPaidCurrentQuarter ;
        selectedAffiliate.transaction.sellProductionSuppliesCurrentQuarter =transaction.sellProductionSuppliesCurrentQuarter ;
        selectedAffiliate.transaction.purchaseProductionSuppliesCurrentQuarter = transaction.purchaseProductionSuppliesCurrentQuarter ;
        selectedAffiliate.transaction.sellMachineryCurrentQuarter =transaction.sellMachineryCurrentQuarter ;
        selectedAffiliate.transaction.purchaseMachineryCurrentQuarter =transaction.purchaseMachineryCurrentQuarter ;
        selectedAffiliate.transaction.currentDebitAccountCurrentQuarter =transaction.currentDebitAccountCurrentQuarter ;
        selectedAffiliate.transaction.currentCreditAccountCurrentQuarter =transaction.currentCreditAccountCurrentQuarter ;
        selectedAffiliate.transaction.expensesReceivableCurrentQuarter =transaction.expensesReceivableCurrentQuarter ;
        selectedAffiliate.transaction.expensesPayableCurrentQuarter =transaction.expensesPayableCurrentQuarter ;
        selectedAffiliate.transaction.insuranceCommissionReceivableCurrentQuarter =transaction.insuranceCommissionReceivableCurrentQuarter ;
        selectedAffiliate.transaction.insuranceCommissionPayableCurrentQuarter =transaction.insuranceCommissionPayableCurrentQuarter ;
        selectedAffiliate.transaction.otherDebitCurrentQuarter =transaction.otherDebitCurrentQuarter ;
        selectedAffiliate.transaction.otherCreditCurrentQuarter =transaction.otherCreditCurrentQuarter ;
        selectedAffiliate.transaction.totalDebitCurrentQuarter =transaction.totalDebitCurrentQuarter ;
        selectedAffiliate.transaction.totalCreditCurrentQuarter =transaction.totalCreditCurrentQuarter ;


        if (gender) { // edit individual affiliate

            selectedAffiliate.affiliateNameEnglish = individualAffiliateName;
            selectedAffiliate.affiliateGender = gender;
            selectedAffiliate.genderDescription = genderDescription;
            selectedAffiliate.maritalStatus = maritalStatus;
            selectedAffiliate.maritalStatusDescription = maritalStatusDescription;
            selectedAffiliate.affiliateNationalityCurrent = nationalityCurrent;
            selectedAffiliate.nationalityCurrentDescription = nationalityCurrentDescription;
            selectedAffiliate.affiliateCountry = affiliateCountry;
            selectedAffiliate.affiliateCountryDescription = affiliateCountryDescription;
        } else { // edit entity affiliate
            selectedAffiliate.companyCountry = companyCountry;
            selectedAffiliate.companyCountryDescription = companyCountryDescription;
            selectedAffiliate.affiliateNameEnglish = name;
            selectedAffiliate.industry = industry;
            selectedAffiliate.industryDescription = industryDescription;
            selectedAffiliate.affiliateMultinationalCompany = multinationalCompany;
            selectedAffiliate.affiliateSubsector = subsector;
        }



        if (selectedAffiliate.newItemId) { // Edit added affiliate - save all fields
            if (gender) { // edit individual affiliate

                selectedAffiliate.affiliateNameEnglish = individualAffiliateName;
                selectedAffiliate.affiliateGender = gender;
                selectedAffiliate.genderDescription = genderDescription;
                selectedAffiliate.maritalStatus = maritalStatus;
                selectedAffiliate.maritalStatusDescription = maritalStatusDescription;
                selectedAffiliate.nationalityCurrent = nationalityCurrent;
                selectedAffiliate.nationalityCurrentDescription = nationalityCurrentDescription;
                selectedAffiliate.affiliateCountry = affiliateCountry;
                selectedAffiliate.affiliateCountryDescription = affiliateCountryDescription;
            } else { // edit entity affiliate
                selectedAffiliate.companyCountry = companyCountry;
                selectedAffiliate.companyCountryDescription = companyCountryDescription;
                selectedAffiliate.affiliateNameEnglish = name;
                selectedAffiliate.industry = industry;
                selectedAffiliate.industryDescription = industryDescription;
                selectedAffiliate.multinationalCompany = multinationalCompany;
                selectedAffiliate.subsector = subsector;
            }

        } else {  // Edit existing affiliate
            financialSurvey.affiliates[affiliateIndex].action = '02';
            setColorForModifiedRow(affiliateRow);
        }
    } else { // new affiliate
        affiliateRow = $('.affiliateTemplate').first().clone();
        affiliateRow.show();

        var affiliate = {
            action: '01',
            firstName: '',
            secondName: '',
            companyCountry: '',
            affiliateNameEnglish: '',
            affiliateSector: '',
            affiliateSubsector: '',
            affiliateGender: '',
            affiliateGenderDescription: '',
            affiliateNationalityCurrent: '',
            affiliateNationalityCurrentDescription: '',
            affiliateCountry: '',
            affiliateCountryDescription: '',
            affiliatePercentage: '',
            affiliateCapital: '',
            affiliateIsVotingPower: '',
            affiliateVotingPower: '',
            affiliateHasPreferredShares: '',
            affiliateHaveReverseInvestment: '',
            valueOfReverseInvestment: '',
            affiliateMultinationalCompany: '',
            affiliatePreferredShares: '',

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
                 totalCreditCurrentQuarter : ''
            }
        };

        if (gender) { // individual affiliate

            affiliateRow.attr("id", newItemId).children().first().html(individualAffiliateName)
                .next().text(getI18nText("general.individual")).next().text(affiliateCountryDescription);

            affiliate.affiliateType = '1';
            affiliate.affiliateNameEnglish = individualAffiliateName;
            affiliate.affiliateGender = gender;
            affiliate.genderDescription = genderDescription;
            affiliate.maritalStatus = maritalStatus;
            affiliate.maritalStatusDescription = maritalStatusDescription;
            affiliate.affiliateNationalityCurrent = nationalityCurrent;
            affiliate.nationalityCurrentDescription = nationalityCurrentDescription;
            affiliate.affiliateCountry = affiliateCountry;
            affiliate.affiliateCountryDescription = affiliateCountryDescription;


        } else { // entity affiliate
            affiliateRow.attr("id", newItemId).children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(companyCountryDescription);
            affiliate.affiliateType = '2';
            affiliate.affiliateNameEnglish = name;
            affiliate.affiliateSector = industry;
            affiliate.industryDescription = industryDescription;
            affiliate.affiliateMultinationalCompany = multinationalCompany;
            affiliate.affiliateSubsector = subsector;
            affiliate.affiliateCountry = companyCountry;
            affiliate.companyCountryDescription = companyCountryDescription;
        }


        //transaction
            affiliate.transaction.currentDebitAccountCurrentQuarter = transaction.currentDebitAccountCurrentQuarter;
            affiliate.transaction.tradeDebitCurrentQuarter= transaction.tradeDebitCurrentQuarter;
            affiliate.transaction.tradeCreditCurrentQuarter = transaction.tradeCreditCurrentQuarter;
            affiliate.transaction.loansAssetsCurrentQuarter = transaction.loansAssetsCurrentQuarter;
            affiliate.transaction.loansLiabilitiesCurrentQuarter = transaction.loansLiabilitiesCurrentQuarter;
            affiliate.transaction.interestReceivedCurrentQuarter = transaction.interestReceivedCurrentQuarter;
            affiliate.transaction.interestPayableCurrentQuarter  = transaction.interestPayableCurrentQuarter;
            affiliate.transaction.dividendsReceivedCurrentQuarter  = transaction.dividendsReceivedCurrentQuarter;
            affiliate.transaction.dividendsPaidCurrentQuarter  = transaction.dividendsPaidCurrentQuarter;
            affiliate.transaction.expensesReceivedCurrentQuarter   = transaction.expensesReceivedCurrentQuarter;
            affiliate.transaction.expensesPaidCurrentQuarter  = transaction.expensesPaidCurrentQuarter;
            affiliate.transaction.sellProductionSuppliesCurrentQuarter  = transaction.sellProductionSuppliesCurrentQuarter;
            affiliate.transaction.purchaseProductionSuppliesCurrentQuarter  = transaction.purchaseProductionSuppliesCurrentQuarter;
            affiliate.transaction.sellMachineryCurrentQuarter  = transaction.sellMachineryCurrentQuarter;
            affiliate.transaction.purchaseMachineryCurrentQuarter  = transaction.purchaseMachineryCurrentQuarter;
            affiliate.transaction.currentDebitAccountCurrentQuarter  = transaction.currentDebitAccountCurrentQuarter;
            affiliate.transaction.currentCreditAccountCurrentQuarter  = transaction.currentCreditAccountCurrentQuarter;
            affiliate.transaction.expensesReceivableCurrentQuarter  = transaction.expensesReceivableCurrentQuarter;
            affiliate.transaction.expensesPayableCurrentQuarter  = transaction.expensesPayableCurrentQuarter;
            affiliate.transaction.insuranceCommissionReceivableCurrentQuarter  = transaction.insuranceCommissionReceivableCurrentQuarter;
            affiliate.transaction.insuranceCommissionPayableCurrentQuarter  = transaction.insuranceCommissionPayableCurrentQuarter;
            affiliate.transaction.otherDebitCurrentQuarter  = transaction.otherDebitCurrentQuarter;
            affiliate.transaction.otherCreditCurrentQuarter  = transaction.otherCreditCurrentQuarter ;
            affiliate.transaction.totalDebitCurrentQuarter   = transaction.totalDebitCurrentQuarter;
            affiliate.transaction.totalCreditCurrentQuarter =   transaction.totalCreditCurrentQuarter;




        setColorForNewRow(affiliateRow);
        $('#affiliatesId').append(affiliateRow);

        affiliate.newItemId = newItemId++;
        financialSurvey.affiliates.push(affiliate);
    }

    affiliateRow.find('.removeAffiliateBtn').on('click', removeAffiliate);
    affiliateRow.find('.editAffiliateBtn').on('click', editAffiliate);

    $("#affiliateModalId").modal('hide');
    enableAffiliateForm();
    clearAffiliateForm();
};

function resetAffiliateFormOnModalClose() {
    $('#affiliateModalId').on('hidden.bs.modal', function () {
        $("#identityNumberCopy").parents('.col-md-6').remove();
        loadAffiliatePersonForm();
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

function clearAffiliateForm() {
    var $sector = $('#affiliateSectorId');
    $sector.val(null).trigger('change');
    $sector.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $multinationalCompany = $('#affiliateMultinationalCompanyId');
    $multinationalCompany.val(null).trigger('change');
    $multinationalCompany.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $legalStatus = $('#affiliateLegalStatusId');
    $legalStatus.val(null).trigger('change');
    $legalStatus.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $academicTitle = $('#affiliateAcademicTitleId');
    $academicTitle.val(null).trigger('change');
    $academicTitle.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $gender = $('#affiliateGenderId');
    $gender.val(null).trigger('change');
    $gender.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $maritalStatus = $('#affiliateMaritalStatusId');
    $maritalStatus.val(null).trigger('change');
    $maritalStatus.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $premiumResident = $('#affiliatePremiumResidentId');
    $premiumResident.val(null).trigger('change');
    $premiumResident.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityCurrent = $('#affiliateNationalityCurrentId');
    $nationalityCurrent.val(null).trigger('change');
    $nationalityCurrent.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $nationalityPrevious = $('#affiliateNationalityPreviousId');
    $nationalityPrevious.val(null).trigger('change');
    $nationalityPrevious.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $country = $('#affiliateCountryId');
    $country.val(null).trigger('change');
    $country.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    $('#affiliateNameId').val('');
    $('#affiliateNameEnglishId').val('');
    $('#affiliateFirstNameId').val('');
    $('#affiliateLastNameId').val('');
    $('#birthDateId').val('');
    $('#affiliatePercentageId').val('');
    $('#affiliateInheritedPropertyId').prop('checked', false);
    $('#affiliateCityId').val('');
    $('#affiliateStreetId').val('');
    $('#affiliateNumberId').val('');
    $('#affiliateZipCodeId').val('');
    $('#affiliateTelephoneId').val('');
    $('#affiliateEmailId').val('');
    $('#affiliateWebsiteId').val('');
    $('.saveAffiliateBtn').removeAttr('id');

    $('#affiliateFirstNameId').removeAttr('disabled');
    $("#affiliateLastNameId").removeAttr('disabled');
    $("#birthDateId").removeAttr('disabled');
    $("#affiliateGenderId").removeAttr('disabled');
    $("#affiliatePremiumResidentId").removeAttr('disabled');
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



    $('#delegateCountryCodeForMobile').val('');
    $('#delegateCountryCodeForTelephone').val('');
    $('#delegateFirstNameArabic').val('');
    $('#delegateLastNameArabic').val('');
    $('#delegateFullNameEnglish').val('');
    $('#delegateEmail').val('');
    $('#delegateTelephone').val('');
    $('#delegateMobile').val('');



}

var removeAffiliateErrorsIfExists = function () {
    if ($(this).closest('.form-group').hasClass('has-error')) {
        $(this.closest('.form-group')).removeClass('has-error');
    }

    if ($(this).closest('.formSelectBox').length > 0) {
        $(this).closest('.formSelectBox').find('.help-block').text('');
    }
};

var clickAffiliatePersonID = function () {
//	 $('#showDelegateQuestionOrganization').show();
	$('#showDelegateQuestionOrganization').hide();
	 $('#showDelegateQuestion').show();
};



var removePreviousAffiliateErrors = function () {
	 $('#showDelegateQuestionOrganization').show();
	 $('#showDelegateQuestion').hide();
	clearAffiliateForm();

	 $("#contentNewAffiliateForm").find("#hasDelegateYES").click();

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

$("#affiliateTelephoneId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#affiliateZipCodeId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#affiliateNumberId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});