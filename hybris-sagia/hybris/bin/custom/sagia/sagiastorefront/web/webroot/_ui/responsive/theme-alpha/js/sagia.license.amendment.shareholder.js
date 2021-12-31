$(document).ready(function () {

    //prevents removal of modal-open class on body when two modals are open and one of them is closed.
    $(document).on('hidden.bs.modal', '#errorResponseModal', function () {
        if($('.modal.show').length)
        {
            $('body').addClass('modal-open');
        }
    });

    if ($('body').hasClass('page-license-amend')) {
        $('#shareholderPersonEntityTypeId').show();
        $('#bpNumberGroupId').hide();
        $('#entityShareholderId').hide();

        //$('#contentNewShareholderForm').hide();
        loadPersonForm();

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
        $('#shareholderAcademicTitleId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderMaritalStatusId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderPremiumResidentId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderGenderId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderNationalityCurrentId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderNationalityPreviousId').on('change', removeShareholderErrorsIfExists);
        $('#shareholderCountryId').on('change', removeShareholderErrorsIfExists);
        $('#existingShareholderId').on('click', removePreviousErrors);
        $('#newShareholderId').on('click', removePreviousErrors);
        $('#entityId').on('click', removePreviousErrors);

        $('.saveShareholderBtn').on('click', function() {
            if ($('#existingShareholderId')[0].checked) { // validate existing shareholder first
                validateExistingShareholder();
            }  
            else { // new shareholder - can be saved
                saveShareholder(false);
            }
        });

        $("input[name='shareholderNewExistingRadioBox']").click(function () {
            if ($(this).val() === "true") {
                $('#shareholderPersonEntityTypeId').show();
                $('#bpNumberGroupId').hide();
                clearShareholderForm();
                enableShareholderForm();
                $('#shareholderValidationDetails').show();
                enableDisableShareholderFormSection(false);
                $('#entityShareholderId').show();
                $('#companyVerificationSection').hide();
            } else {
                $('#shareholderPersonEntityTypeId').hide();
                clearShareholderForm();
                disableShareholderForm();
                $('#bpNumberGroupId').show();
                $('#shareholderInheritedPropertyId').attr("disabled", false);
                $('#shareholderValidationDetails').hide();
                $('#delegateDivSection').hide();
                enableDisableShareholderFormSection(true);
                $('#entityShareholderId').hide();
                $('#companyVerificationSection').show();
                $('#inheritsection').hide();
            }
        });

        $("input[name='shareholderPersonEntityRadioBox']").click(function () {
            if ($(this).val() === "true") {
                loadPersonForm();
            } else {
                loadOrganizationForm();
            }
        });

        // search shareholder
        $("#bpNumberId").on('keyup', function (e) {
            if (e.keyCode === 13) {
                validateExistingShareholder();
            }
        });

        $("#shareholderValidationDetails").find("#shareholderIdType").change(function(){
            var type = "Person";
            var element = $("#shareholderValidationDetails");
            onchangeOfShareholderIdType(element, type);
        });

        $("#shareholderValidationDetails").find("#verifyShareholderDetailsShow").on("click", function() {
            preNICVerificationShareholder($("#shareholderValidationDetails"), $(this));
        });

        $("#delegateDetails").find("#verifyDetailsShow").on("click", function() {
            preNICVerificationDelegate($("#delegateDetails"), $(this));
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

        $("#delegateDetails").find("#idType").change(function(){
            var type = "Person";
            var element = $("#delegate");
            onchangeOfIdType(element, type);
        });

        $("#entityShareholderId").find("#load-investor").on("click", function () {
            loadInvestorByCrEvent($("#inputCRNumber").val());
        });
        
        $('#inheritsection .verify-moj').on('keyup', function() {
           	var deceasedId = $('#deceasedId').val();
           	var deedNo = $('#deedNumber').val();

           	if (deceasedId.length != 0 && deedNo.length != 0) {
           	 	//$("#inputCRNumber-error").addClass("has-error").text(getI18nText("validation.crnumber"));
           	 	$("#verifyInherit").attr("disabled", false);
           	}
           	else {
           		//$("#inputCRNumber-error").removeClass("has-error").text("");
           		$("#verifyInherit").attr("disabled", true);	
           	}
           });
        
        $("#inheritsection").find("#verifyInherit").on("click", function () {
        	verifyInheritWithMOJ($("#deceasedId").val(), $("#deedNumber").val());
        });
        
        $("input[name='shareholderInheritedProperty']").click(function () {
        	var shType = $("#personId").is(':checked');
        	var companyCountry = $("#companyCountry").val();
        	var deceasedName = $("#deceasedName").val();
        	var inheritProperty = $('#shareholderInheritedPropertyId').is(':checked');
            if (inheritProperty && shType && companyCountry === "") {
            	$('#inheritsection').show();
            	if(deceasedName.length != 0){
            		$('#deceasedNameSection').show();
            	}else{
            		$('#deceasedNameSection').hide();
            	}
            } else {
            	$('#inheritsection').hide();
            	$('#deceasedNameSection').hide();
            	resetVerifyInherit();
            }
        });
        
        $("#entityShareholderId").find("#companyCountry").change(function(){
            var entityShareholderId = $("#entityShareholderId");
            /*var newFileSection = $("#companyCheckFileAttachment");
            if($(this).val()) {
                newFileSection.hide();
                SAGIA.licenseApplyShareholderCommons.loadNewFile(entityShareholderId.find("#companyCountry"), newFileSection);
                newFileSection.find('[name=companyMemoAssociationFile]').removeClass('validate__file');
            }*/
            if($(this).val() == "SA")
            {
                $('#shareholderValidationSection').show();
                $('#shareholderValidationDetails').hide();

                $('#contentNewShareholderForm').show();
                $('#individualShareholderId').hide();
                $('#entityShareholderId').show();
                $('#entityBasicInformation').hide();
                $('#entityBasicInformation2').hide();

                $('.contentModule-headline').hide();
                $('#shareholderAddressId').hide();

                $('#delegateDivSection').hide();
                $('#delegate').hide();

                $("#hasDelegateYES").attr('checked', true);

                $("#inputCRNumber").prop('disabled', false);

                $(this).parents('.modal').off('scroll');
            }
            else
            {
                $("#inputCRNumber").prop('disabled', true);
                loadOrganizationFormFields();
                
            }

            if($("#professionalLicenseCrVerified").val() == 'true'){
                $("#organizationNameEnglish").val("").attr("disabled", false);
                $("#organizationNameArabic").val("").attr("disabled", false);
                $("#companyCapital").val("").attr("disabled", false);
                $("#professionalLicenseCrVerified").val(false);

                $("#shareholderNameEnglishId").val('').attr("disabled", false);
                $("#shareholderNameId").val('').attr("disabled", false);
                $("#shareholderCapitalId").val('').attr("disabled", false);

                $("#inputCRNumber").val("");
            }
        });

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

        resetFormOnModalClose()
    }
});

var loadInvestorByCrEvent = function(crNumber){
    if(crNumber === "") {
        $(".valid-cr-fields").hide();
        $('.cr-validation').addClass('has-error');
        $('.cr-validation .help-block').html("<span>Please provide a valid CR Number</span>");
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
                    loadInvestorCrResponseEvent(jsonData, crNumber);
                } else {
                    $(".valid-cr-fields").hide();
                    $('.cr-validation').addClass('has-error');
                    $('.cr-validation .help-block').html("<span>" + jsonData.error + "</span>");
                    $("#professionalLicenseCrVerified").val(false);
                    loadOrganizationForm();
                    // TODO Thiago - Fix popUp.
                    var errorResponseModal = $('#errorResponseModal');
                    errorResponseModal.find('.modal-description').text(getI18nText("validation.shareholder.error.nic"));
                    errorResponseModal.modal('show');
                    $("#inputCRNumber").prop('disabled', false);
                    
                   
                }
            },
            error: function() {
                loadOrganizationForm();
                // TODO Thiago - Fix popUp.
                var errorResponseModal = $('#errorResponseModal');
                errorResponseModal.find('.modal-description').text(getI18nText("validation.shareholder.issue"));
                errorResponseModal.modal('show');
                $("#professionalLicenseCrVerified").val(false);
            }
         });
    }
}

var toggleDelegateDetails = function(element, flag){
    if(flag)
    {
        element.find("#delegate").show();
        element.find("#delegateDetails").show();
    }
    else
    {
        element.find("#delegate").hide();
        element.find("#delegateDetails").hide();
    }
};

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

var preNICVerificationShareholder = function(element, current){
    var idType = element.find("#shareholderIdType").val();
    
    if(idType == "4"){
    	return ;
    }
    var idNumber = element.find("#shareholderIdNumber").val();
    var shareholderDateofBirth = element.find("#shareholderDateofBirth").val();

    current.parents('form').find('.inputValidationError').hide();

    if(!validateShareholderNICInput(idType,idNumber,shareholderDateofBirth,element)) {
        return false
    }

    element.parents('.modal').off('scroll');

    verifyUserDataWithNIC(idType, idNumber, shareholderDateofBirth).done(function(data) {
        if(data)
        {
            var jsonData = JSON.parse(data);
            // jsonData.result = "Invalid Data";
            if( jsonData.result && jsonData.result == "Valid Data")
            {
                current.data('nicVerified', "true");
                current.siblings("#isShareholderNicVerified").prop('checked', true);
                var element = $("#individualShareholderId");
                setAndDisableFieldIfValueNotBlank(element.find("#shareholderFirstNameId"),jsonData.firstName_EN);
                setAndDisableFieldIfValueNotBlank(element.find("#shareholderLastNameId"),jsonData.secondName_EN);
                setAndDisableFieldIfValueNotBlank(element.find("#birthDateId"),shareholderDateofBirth);
                updateGenderDropdown(element.find("#shareholderGenderId"), jsonData.gender, true);
                updateShareholderPremiumResident(element.find("#shareholderPremiumResidentId"), jsonData.premium_Residency, true);
                $("#individualShareholderId").append(makeIdentityNumberCopy());
                // $("#shareholderValidationSection").hide();
                // $("#contentNewShareholderForm").show();
                // $("#individualShareholderId").show();
                // Code replaced to:
                enableDisableShareholderFormSection(true);
            }
            else
            {
                // toggleTheNICFieldsEditable(element, "", true);
                current.data('nicVerified', "false");
                current.siblings("#isShareholderNicVerified").prop('checked', false);

                var errorResponseModal = $('#errorResponseModal');
                errorResponseModal.find('.modal-description').text(getI18nText("validation.shareholder.error.nic"));
                errorResponseModal.modal('show');
                // TODO: Thiago S.: fix: this popup above on the code is showing the popup between the main page and shareholder form
            }
        }
        else
        {
            // toggleTheNICFieldsEditable(element, "", true);
            current.data('nicVerified', "false");
            current.siblings("#isNicVerified").prop('checked', false);

            var errorResponseModal = $('#errorResponseModal');
            errorResponseModal.find('.modal-description').text(getI18nText("validation.shareholder.issue"));
            errorResponseModal.modal('show');
        }
        // toggleIdCopeFileSection(element);
        // toggleFieldsOnValue(element);
        // element.find("#verifyDelegateDetails").show();
    }).fail(function(data){
        console.log(data);
    });
};

var makeIdentityNumberCopy = function () {
    var clonedIdentityType = $('#shareholderValidationDetails').find('#shareholderIdNumber').parents('.col-md-6').clone();
    var inputElement = clonedIdentityType.find('#shareholderIdNumber');
    inputElement.attr('id', 'identityNumberCopy');
    inputElement.attr('name', 'identityNumber');
    inputElement.prop('disabled', true);
    return clonedIdentityType;
}

var preNICVerificationDelegate = function(element, current){
    var idType = element.find("#idType").val();
    var idNumber = element.find("#idNumber").val();
    var delegateDateofBirth = element.find("#delegateDateofBirth").val();

    current.parents('form').find('.inputValidationError').hide();

    if(!validateDelegateNICInput(idType,idNumber,delegateDateofBirth,element)) {
        return false
    }

    verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data) {
        if(data)
        {
            var jsonData = JSON.parse(data);
            // jsonData.result = "Invalid Data";
            if( jsonData.result && jsonData.result == "Valid Data")
            {
                current.data('nicVerified', "true");
                current.siblings("#isShareholderNicVerified").prop('checked', true);
                var element = $("#delegate");

                setAndDisableFieldIfValueNotBlank(element.find("#delegateFirstNameArabic"),jsonData.firstName_AR);
                setAndDisableFieldIfValueNotBlank(element.find("#delegateLastNameArabic"),jsonData.lastName_AR);
                setAndDisableFieldIfValueNotBlank(element.find("#delegateFullNameEnglish"),jsonData.fullName_EN);
                setAndDisableFieldIfValueNotBlank(element.find("#delegateBirthDateId"),jsonData.dob);
                setAndDisableFieldIfValueNotBlank(element.find("#delegateIssueDate"),jsonData.issueDate);
                setAndDisableFieldIfValueNotBlank(element.find("#delegateExpiryDate"),jsonData.expiryDate);
                
                
                updateGenderDropdown(element.find("#delegateGenderId"), jsonData.gender, true);
                updateShareholderPremiumResident(element.find("#delegatePremiumResidentId"), jsonData.premium_Residency, true);
            }
            else
            {
                toggleTheNICFieldsEditableDelegate($("#delegate"), "", true);
                current.data('nicVerified', "false");
            }
        }
        else
        {
            toggleTheNICFieldsEditableDelegate($("#delegate"), "", true);
            current.data('nicVerified', "false");
            current.siblings("#isNicVerified").prop('checked', false);
        }
        // toggleIdCopeFileSection(element);
        // toggleFieldsOnValue(element);
        $("#verifyDelegateDetails").show();
    }).fail(function(data){
        console.log(data);
    });
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

var validateShareholderNICInput = function(idType,idNumber,shareholderValidationDateofBirth,element){
    if(!idType||!idNumber||!shareholderValidationDateofBirth){
        var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
        if(!idType){
            element.find("#shareholderIdType").parent().addClass("has-error");
            element.find("#shareholderIdType").parent().siblings(".help-block").text(errorMsg);
        }
        if(!idNumber){
            element.find("#shareholderIdNumber").parent().addClass("has-error");
            element.find("#shareholderIdNumber").parent().siblings(".help-block").text(errorMsg);
        }
        if(!shareholderValidationDateofBirth){
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
};

var validateDelegateNICInput = function(idType,idNumber,shareholderValidationDateofBirth,element){
    if(!idType||!idNumber||!shareholderValidationDateofBirth){
        var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
        if(!idType){
            element.find("#idType").parent().addClass("has-error");
            element.find("#idType").parent().siblings(".help-block").text(errorMsg);
        }
        if(!idNumber){
            element.find("#idNumber").parent().addClass("has-error");
            element.find("#idNumber").parent().siblings(".help-block").text(errorMsg);
        }
        if(!shareholderValidationDateofBirth){
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
};

var verifyUserDataWithNIC = function(idType, idNumber, shareholderValidationDateofBirth){
    return $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/nic", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
        },
        method: 'POST',
        data: {"idType": idType,
            "idNumber": idNumber,
            "dob":shareholderValidationDateofBirth},
        success: function (data) {
        },
        error: function (error) {
        }
    });
};


var verifyInheritWithMOJ = function (deceasedId, deedNumber) {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/verifyInherit/"+deceasedId+"/"+deedNumber, {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (mojInherit) {
            //$('#bpNumberId').parent('.form-group').removeClass('has-error').find('.help-block').empty();
            if (mojInherit) {
                var resDeceasedId = mojInherit.deceasedId;
                var resDeedNumber = mojInherit.deedNumber;
                var resDeceasedName = mojInherit.deceasedName;
                var resIsMojVerified = mojInherit.isMojVerified;
                if(resIsMojVerified === 'X' && resDeceasedName.length > 0){
                	$('#deceasedNameSection').show();
                	$('#deceasedId').val(resDeceasedId);
                	$('#deceasedName').val(resDeceasedName);
                	$('#deedNumber').val(resDeedNumber);
                	$('#isMojVerified').val(resIsMojVerified);
                	$("#verifyInherit").attr("disabled", true);
                }else{
                	$('#deceasedNameSection').hide();
                	$('#deceasedId').val(resDeceasedId);
                	$('#deceasedName').val("");
                	$('#deedNumber').val(resDeedNumber);
                	$('#isMojVerified').val("");
                	$("#verifyInherit").attr("disabled", true);
                }
            }
        },
        error: function () {
        	$('#deceasedNameSection').hide();
        	$('#deceasedId').val(resDeceasedId);
        	$('#deceasedName').val("");
        	$('#deedNumber').val(resDeedNumber);
        	$('#isMojVerified').val("");
        	$("#verifyInherit").attr("disabled", true);
        	resetVerifyInherit();
        }
    });
};

var validateNICInput = function(idType,idNumber,shareholderValidationDateofBirth,element){
    if(!idType||!idNumber||!shareholderValidationDateofBirth){
        var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
        if(!idType){
            element.find("#idType").parent().addClass("has-error");
            element.find("#idType").parent().siblings(".help-block").text(errorMsg);
        }
        if(!idNumber){
            element.find("#idNumber").parent().addClass("has-error");
            element.find("#idNumber").parent().siblings(".help-block").text(errorMsg);
        }
        if(!shareholderValidationDateofBirth){
            element.find("#shareholderValidationDateofBirth").parent().addClass("has-error");
            element.find("#shareholderValidationDateofBirth").parent().siblings(".help-block").text(errorMsg);
        }
        return false;
    }
    else{
        element.find("#idType").parent().removeClass("has-error");
        element.find("#idType").parent().siblings(".help-block").text("");
        element.find("#shareholderValidationDateofBirth").parent().removeClass("has-error");
        element.find("#shareholderValidationDateofBirth").parent().siblings(".help-block").text("");
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
};

var clearIdNumber = function(element) {
    element.find("#idNumber").parent().removeClass("has-error");
    element.find("#idNumber").parent().siblings(".help-block").text("");
    element.find("#idNumber").val('');
};

var onchangeOfShareholderIdType = function(element, type)
{
    var elementIdType = element.find("#shareholderIdType");
    var elementDateofBirth = element.find("#shareholderDateofBirth");
    toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, elementDateofBirth);
   
	var elementSharholderBirthDateId =   $("#contentNewShareholderForm").find("#birthDateId");  	    
	toggleUmmAlQuraOrNormalCalInDelegateSection(elementIdType, elementSharholderBirthDateId);
	    
	    
	    
	    
    element.find("#nicShareholderVerifyBtnSection").show();

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

    var isEditErrorShareholder = elementIdType.val()==="4" || element.find('#isShareholderNicVerified').prop('checked');

    var delegateAttr = element.find('[name=delegateInfo\\.delegate]').parents('.form-group');

    var validateInitialEvents = initialEditEvents && delegateAttr.attr('data-delegate') && delegateAttr.attr('data-delegate') !== 'false';

    if (validateInitialEvents) {
        if(element.find("#shareholderIdType").val()==="4"){
            //For passport
            element.find("#nicShareholderVerifyBtnSection").hide();
        }

        element.find("#verifyDelegateDetails").show();
        form.removeClass('edit-initial');

        if (element.find("#shareholderIdType").val()!=="4") {
    	    
            toggleTheNICFieldsEditable(element, type, false);
        }
        
      
	    
	    
	    
    } else {
    	
    
        if(element.find("#shareholderIdType").val()==="4"){
            //For passport
            element.find("#nicShareholderVerifyBtnSection").hide();
            element.find("#shareholderIdNumberSection").hide();
            element.find("#shareholderDateofBirthSection").hide();
            
            
            
            enableDisableShareholderFormSection(true);
            enableDisablePassportIdShareholderForm(true)
        } else {
            element.find("#nicShareholderVerifyBtnSection").show();
            element.find("#shareholderIdNumberSection").show();
            element.find("#shareholderDateofBirthSection").show();
            enableDisableShareholderFormSection(false);
            enableDisablePassportIdShareholderForm(false);
            
        }

        resetShareholderDetails(element, type);
    }

    //toggleIdCopeFileSection(element);
    //toggleFieldsOnValue(element);
    //toggleSectionsOnValue(element);
    //resetDataSectionFields(element,type);
};

var resetDelegate = function(element, type)
{
    resetDelegateSection(element, type);
    resetDelegateQuestion(element, type);
    resetDelegateInfo(element, type);
    resetDelegateDetails(element, type);
};


var resetDelegateSection = function(element, type)
{
    element.find("input[name='hasDelegateInfo']:checked").prop('checked', false);
    element.find("#delegateSection").hide();
};

var resetDelegateQuestion = function(element, type)
{
    element.find("input[name='hasDelegate']:checked").prop('checked', false);
    if(type === 'Person')
    {
        element.find('#showDelegateQuestionOrganization').hide();
        element.find('#showDelegateQuestion').show();
    }
    else if(type === 'Organization')
    {
    	 element.find('#showDelegateQuestionOrganization').hide();
         element.find('#showDelegateQuestion').hide();
    }
    element.find("#delegate").hide();
};

var resetDelegateInfo = function(element, type)
{
    element.find("#verifyDetailsShow").data("nicVerified", "false");
    element.find("#idType").val("").trigger("blur").trigger('change');
    element.find("#idNumber").val("");
    element.find("#delegateDateofBirth").val("");
    element.find("#delegateDetails").hide();
    element.find("#nicVerifyBtnSection").show();
};

var resetShareholderDetails = function(element, type)
{
    clearShareholderForm();
    toggleTheNICFieldsEditable(element, type, true);
};

var resetDelegateDetails = function(element, type)
{
    clearDelegateDetailsForm();
    toggleTheNICFieldsEditableDelegate(element, type, true);

    element.find("#idCopyFile").val(null); //mind the null
    element.find("#idCopyFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
    element.find("#idCopyFileName").val("");
    element.find("#authorizationLetterFile").val(null); //mind the null
    element.find("#authorizationLetterFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
    element.find("#authorizationLetterFileName").val("");

    element.find("#verifyDelegateDetails").hide();

    if(element.find("#idType").val()==="4"){
        //For passport
        element.find("#nicVerifyBtnSection").hide();
        element.find("#verifyDelegateDetails").show();
        element.find("#delegateDateofBirthSection").hide();
    }

};

var toggleTheNICFieldsEditable = function(element, type, editable)
{

    if(editable)
    {
        element.find("#shareholderFirstNameArabic").removeAttr("disabled");
        element.find("#shareholderLastNameArabic").removeAttr("disabled");
        element.find("#shareholderFullNameEnglish").removeAttr("disabled");
        element.find("#shareholderGender").removeAttr("disabled");
        element.find("#delegateIssueDate").removeAttr("disabled");
        element.find("#delegateExpiryDate").removeAttr("disabled");    
        element.find("#identityNumberCopy").removeAttr("disabled");
    }
    else
    {
        element.find("#shareholderFirstNameArabic").attr('disabled','disabled');
        element.find("#shareholderLastNameArabic").attr('disabled','disabled');
        element.find("#shareholderFullNameEnglish").attr('disabled','disabled');
        element.find("#shareholderGender").attr('disabled','disabled');
        element.find("#delegateIssueDate").attr('disabled','disabled');
        element.find("#delegateExpiryDate").attr('disabled','disabled');
        element.find("#identityNumberCopy").attr('disabled','disabled');
    }
};

var toggleTheNICFieldsEditableDelegate = function(element, type, editable)
{
    if(editable)
    {
        //element.find("#delegateFirstNameId").removeAttr("disabled");
        //element.find("#delegateLastNameId").removeAttr("disabled");
    	element.find("#delegateFirstNameArabic").removeAttr("disabled");
    	element.find("#delegateLastNameArabic").removeAttr("disabled");
    	element.find("#delegateFullNameEnglish").removeAttr("disabled");
        element.find("#delegateBirthDateId").removeAttr("disabled");
        element.find("#delegateGenderId").removeAttr("disabled");
        element.find("#delegatePremiumResidentId").removeAttr("disabled");
        element.find("#delegateExpiryDate").removeAttr("disabled");
        element.find("#delegateIssueDate").removeAttr("disabled");
        
    }
    else
    {
    	element.find("#delegateExpiryDate").attr('disabled','disabled');
        element.find("#delegateIssueDate").attr('disabled','disabled');
    	element.find("#delegateFirstNameArabic").attr('disabled','disabled');
    	element.find("#delegateLastNameArabic").attr('disabled','disabled');
    	element.find("#delegateFullNameEnglish").attr('disabled','disabled');
        element.find("#delegateBirthDateId").attr('disabled','disabled');
        element.find("#delegateGenderId").attr('disabled','disabled');
        element.find("#delegatePremiumResidentId").attr('disabled','disabled');
    }
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
        $('#shareholderAddressId').hide();
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

var loadOrganizationForm = function () {
    $('#shareholderValidationSection').show();
    $('#shareholderValidationDetails').hide();
    $('#inheritsection').hide();

    $('#contentNewShareholderForm').show();
    $('#individualShareholderId').hide();
    $('#entityShareholderId').show();
    $('#entityBasicInformation').hide();
    $('#entityBasicInformation2').hide();

    $('.contentModule-headline').hide();
    $('#shareholderAddressId').hide();

    // Delegate section
    $('#delegateDivSection').hide();
    $('#delegate').hide();
    
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

    // Delegate section
    $('#delegateDivSection').show();
    $('#delegate').show();
    loadShareholderIdTypeDropDown(false);
};

var loadPersonForm = function () {
    $('#shareholderValidationSection').show();
    $('#shareholderValidationDetails').show();
    $('#entityBasicInformation2').show();
    $('#contentNewShareholderForm').hide();
    $('#individualShareholderId').hide();
    $('#entityShareholderId').hide();
    $('#inheritsection').hide();

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

    // Delegate section
    $('#delegateDivSection').show();
    $('#delegate').show();
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
    licenseAmendment.dirtyAmendment = true;
    var $shareholderRow = $(this).closest('tr');
    $shareholderRow.remove();

    var shareholderId = parseInt($shareholderRow.attr('id'));
    var shareholders = licenseAmendment.shareholders;
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


var validateExistingShareholder = function () {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/shareholder/" + $("#bpNumberId").val(), {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (shareholder) {
            $('#bpNumberId').parent('.form-group').removeClass('has-error')
                .find('.help-block').empty();
            if (shareholder) {
                var bpId = shareholder.bpId;
                var existingShareholder = licenseAmendment.shareholders.find(function (s) {
                    return s.shBpId == bpId;
                });
                if (existingShareholder || bpId === licenseAmendment.bpId) {
                    addExistingShareholderError(getI18nText("validation.shareholder.alreadyexists"));
                    return;
                }

                shareholder.newItemId = newItemId++;
                shareholder.existingBp = true;

                var $shareholderPercentate = $('#shareholderPercentageId');
                shareholder.percentage = $shareholderPercentate.val();
                $shareholderPercentate.attr("disabled", false);
                $('#shareholderInheritedPropertyId').attr("disabled", false);
                fillShareholderForm(shareholder);
                saveShareholder(true, bpId);
                $('#companyVerificationSection').hide();
                $('#delegateDivSection').hide();
                $('#inheritsection').hide();
            }
        },
        error: function () {
            addExistingShareholderError(getI18nText("validation.bpnumber.invalid"));
            $('#errorResponseModal').remove();
        }
    });
};

function addExistingShareholderError(message) {
    $('#bpNumberId').parent('.form-group').addClass('has-error')
        .find('.help-block').text(message);
}

var newShareholder = function () {
	
	 $('#showDelegateQuestionOrganization').hide();
	 $('#showDelegateQuestion').show();
    clearShareholderValidation();
    $('#shareholderNewExistingTypeId').show();
    $('#shareholderPersonEntityTypeId').show();
    $('#bpNumberGroupId').hide();
    enableShareholderForm();
    clearShareholderForm();
    $('#personId').click();
    $('#shareholderIdType').removeAttr('disabled');
    $('#verifyShareholderDetailsShow').removeAttr('disabled');
    $('#shareholderDateofBirth').removeAttr('disabled');
    $('#shareholderIdNumber').removeAttr('disabled');
    $('#shareholderIdTypeSection').show();
};

function enableShareholderForm() {
    $("#entityShareholderId :input").attr("disabled", false);
    $("#individualShareholderId :input").attr("disabled", false);
    $('#shareholderInheritedPropertyId').attr("disabled", false);
    $("#shareholderAddressId :input").attr("disabled", false);
    resetVerifyInherit();
}

function enableDelegateDetailsForm() {
    $("#verifyDelegateDetails :input").attr("disabled", false);
}

function disableShareholderForm() {
    $("#entityShareholderId :input").attr("disabled", true);
    $("#individualShareholderId :input").attr("disabled", true);
    $('#shareholderInheritedPropertyId').attr("disabled", true);
    $("#shareholderAddressId :input").attr("disabled", true);
}

function disableNICFields() {
    $("#shareholderLastNameId").attr("disabled", true);
    $("#shareholderFirstNameId").attr("disabled", true);
    $("#birthDateId").attr("disabled", true);
    $("#shareholderGenderId").attr("disabled", true);
    $("#shareholderPremiumResidentId").attr("disabled", true);
    $("#identityNumberCopy").attr("disabled", true);
   
}

function enableNICFields() {
    $("#shareholderLastNameId").attr("disabled", false);
    $("#shareholderFirstNameId").attr("disabled", false);
    $("#birthDateId").attr("disabled", false);
    $("#shareholderGenderId").attr("disabled", false);
    $("#shareholderPremiumResidentId").attr("disabled", false);
    $("#identityNumberCopy").attr("disabled", false);
 
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
    var index = licenseAmendment.shareholders.findIndex(function (shareholder) {
        return shareholder.srId == selectedShareholderId || shareholder.newItemId == selectedShareholderId;
    });

    // determine index by screen position assuming array has the same config.
    index = $(this).parents('tr').index() - 1;

    var selectedShareholder = licenseAmendment.shareholders[index];

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
            licenseAmendment.shareholders[index].percentage = newPercentage;

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
        $(this).find('.percentage').html(licenseAmendment.shareholders[index].percentage + '%');
    })
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

var editShareholder = function () {
    clearShareholderValidation();
    var selectedShareholderId = $(this).closest('tr').attr('id');
    var selectedShareholder = licenseAmendment.shareholders.find(function (shareholder) {
        return shareholder.srId == selectedShareholderId || shareholder.newItemId == selectedShareholderId;
    });

    // Hide block new si type
    $('#shareholderNewExistingTypeId').hide();
    $('#shareholderPersonEntityTypeId').hide();
    $('#bpNumberGroupId').hide();

    disableShareholderForm();
    if (selectedShareholder.srId) { // Existing shareholder
        $('#shareholderPercentageId').attr("disabled", false);
        $('#shareholderNameId').attr("disabled", true);
        $('#shareholderNameEnglishId').attr("disabled", true);
        
        
        
    } else if (selectedShareholder.newItemId && selectedShareholder.existingBp) { // Existing crm shareholder
        $('#shareholderPercentageId').attr("disabled", false);
        $('#shareholderInheritedPropertyId').attr("disabled", false);
        $('#delegateDivSection').hide();
        
    } else { // New shareholder
    		 
        enableShareholderForm();
        
 
        if(selectedShareholder.companyCountry !== 'SA' ){
        	 $('#delegateDivSection').show();
        	
        }else {
        	 $('#delegateDivSection').hide();
        }
        
    }
   
    
    fillShareholderForm(selectedShareholder);
    $('.saveShareholderBtn').attr('id', selectedShareholder.srId || selectedShareholder.newItemId);
    SAGIA.formElements.placeholderPolyfill();
    prepareVisibleItemsEdit(selectedShareholder);
    if(selectedShareholder.idType !== '4' ){
    	disableNICFields();
    	
    }else {
    	enableNICFields();
    }
    
    $('#entityBasicInformation2').show();
    
};

function prepareVisibleItemsEdit(selectedShareholder) {
    $('#shareholderFormId').find('.contentModule-headline').show();

    $('#shareholderValidationDetails').hide();
    $('#contentNewShareholderForm').show();
    
    if (selectedShareholder.srId || (!selectedShareholder.srId && selectedShareholder.idType !== '4')) {
        // Delegate section
        $('#delegateDivSection').hide();
        $('#delegate').hide();
    } else {
        // Delegate section
        $('#delegateDivSection').show();
        $('#delegate').show();
    }
    
    
    
    //if an Entity
    if (selectedShareholder.bpType === '2') {
    	 $('#entityBasicInformation').show();
    	 $('#showDelegateQuestionOrganization').show();
    	 $('#showDelegateQuestion').hide();
    	 
    	 $('#shareholderAddressId').show();
    	 $('#companyCountry').attr("disabled", true);
    	 $('#inputCRNumber').attr("disabled", true);
    	 $('#load-investor').attr("disabled", true);
    	 
    	 $('#delegateDivSection').show();
         $('#delegate').show();
    	 
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
    if (selectedShareholder.bpType === '2') {
        $('#individualShareholderId').hide();
        $('#entityShareholderId').show();
        $('#shareholderNameId').val(selectedShareholder.firstName);
        $('#shareholderNameEnglishId').val(selectedShareholder.englishName);
        $('#inputCRNumber').val(selectedShareholder.inputCRNumber);
        
        updateDropDown('#companyCountry', selectedShareholder.companyCountry);
        updateDropDown('#shareholderSectorId', selectedShareholder.industry);
        updateDropDown('#shareholderMultinationalCompanyId', selectedShareholder.multinationalCompany);
        updateDropDown('#shareholderLegalStatusId', selectedShareholder.legalStatus);
        $('#shareholderSubsectorId').val(selectedShareholder.subsector);
        $('#shareholderCapitalId').val(selectedShareholder.capital);
        $('#shareholderLabourSizeId').val(selectedShareholder.labourSize);
    } else {
    	
    	 
          
    
        $('#entityShareholderId').hide();
        $('#individualShareholderId').show();
        $('#shareholderFirstNameId').val(selectedShareholder.firstName);
        
        
        $('#shareholderLastNameId').val(selectedShareholder.secondName);
        if (selectedShareholder.birthDateString) {
            $("#birthDateId").val(selectedShareholder.birthDateString);
        }      
        
        updateDropDown('#shareholderAcademicTitleId', selectedShareholder.academicTitle);
        updateDropDown('#shareholderGenderId', selectedShareholder.gender);
        updateDropDown('#shareholderMaritalStatusId', selectedShareholder.maritalStatus);
        updateDropDown('#shareholderPremiumResidentId', selectedShareholder.premiumResident);
        updateDropDown('#shareholderNationalityCurrentId', selectedShareholder.nationalityCurrent);
        updateDropDown('#shareholderNationalityPreviousId', selectedShareholder.nationalityPrevious);
        
        if (selectedShareholder.inheritedProperty && selectedShareholder.bpType === '1') {
        	$('#inheritsection').show();
        	$('#deceasedId').val(selectedShareholder.deceasedId);
            $('#deceasedName').val(selectedShareholder.deceasedName);
            $('#deedNumber').val(selectedShareholder.deedNumber);
            $('#isMojVerified').val(selectedShareholder.isMojVerified);
        }
    }

    if (selectedShareholder.inheritedProperty) {
        $('#shareholderInheritedPropertyId').prop('checked', true);
    }
    $('#shareholderPercentageId').val(selectedShareholder.percentage); // if not existing

    updateDropDown('#shareholderCountryId', selectedShareholder.address.country);
    $('#shareholderCityId').val(selectedShareholder.address.city);
    $('#shareholderStreetId').val(selectedShareholder.address.street);
    $('#shareholderNumberId').val(selectedShareholder.address.number);
    $('#shareholderZipCodeId').val(selectedShareholder.address.zipCode);
    $('#shareholderTelephoneId').val(selectedShareholder.address.telephone);
    $('#shareholderEmailId').val(selectedShareholder.address.email);
    $('#shareholderWebsiteId').val(selectedShareholder.address.website);

    if (selectedShareholder.delegate && selectedShareholder.delegate.delegateYourself !== "" && selectedShareholder.delegate.delegateYourself === "false") {
        $('#delegateDetails').show();
        $('#verifyDelegateDetails').show();
        
        $('[name=delegateInfo\\.delegateYourself][value='+selectedShareholder.delegate.delegateYourself+']').trigger('click');
        updateDropDown('#idType', selectedShareholder.delegate.idType);
        updateDropDown('#delegateAcademicTitleId', selectedShareholder.delegate.academicTitle);
        $('#idNumber').val(selectedShareholder.delegate.idNumber);
        $('#delegateDateofBirth').val(selectedShareholder.delegate.mainDateOfBirth);
        if (selectedShareholder.delegate.idType && selectedShareholder.delegate.idType != 4) {
            $('#verifyDetailsShow').trigger('click');
        }else {
        	$('#delegateFirstNameArabic').val(selectedShareholder.delegate.firstNameArabic);
            $('#delegateLastNameArabic').val(selectedShareholder.delegate.lastNameArabic);
            $('#delegateFullNameEnglish').val(selectedShareholder.delegate.fullNameEnglish);
            $('#delegateBirthDateId').val(selectedShareholder.delegate.dateofBirth);
            $('#delegateIssueDate').val(selectedShareholder.delegate.issueDate);
            $('#delegateExpiryDate').val(selectedShareholder.delegate.expiryDate); 
            $('#delegateNationality').val(selectedShareholder.delegate.nationality).trigger("blur").trigger('change');;
            $('#delegateCountry').val(selectedShareholder.delegate.country).trigger("blur").trigger('change');;
            $('#delegateGenderId').val(selectedShareholder.delegate.gender).trigger("blur").trigger('change');;
        }
        updateDropDown('#delegateMaritalStatusId', selectedShareholder.delegate.maritalStatus);
        updateDropDown('#delegateNationalityCurrentId', selectedShareholder.delegate.nationality);
        updateDropDown('#delegateNationalityPreviousId', selectedShareholder.delegate.nationality);
       
        
        $('#delegatePostalCode').val(selectedShareholder.delegate.postalCode);
        $('#delegatePOBox').val(selectedShareholder.delegate.poBox);
        $('#delegateCountryCodeForTelephone').val(selectedShareholder.delegate.countryCodeForTelephone);
        $('#delegateTelephone').val(selectedShareholder.delegate.telephone);      
        $('#delegateCountryCodeForMobile').val(selectedShareholder.delegate.countryCodeForTelephone);
        $('#delegateMobile').val(selectedShareholder.delegate.mobile);
        $('#delegateEmail').val(selectedShareholder.delegate.email);
        
        
    }
    
    
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

var getDelegateInfo = function () {
    var element = $('#delegateSection');
    
    
    var nationality  = element.find('#delegateNationality option:selected').val(); 
    var country = element.find('#delegateCountry option:selected').val();	
    var issueDate = $('#delegateIssueDate').val();
    var expiryDate = $('#delegateExpiryDate').val();
    
    
    var idType = element.find('#idType option:selected').val();
    var idNumber = element.find('#idNumber').val();
   
    var firstNameArabic = element.find('#delegateFirstNameArabic').val();
    var lastNameArabic = element.find('#delegateLastNameArabic').val();
    var fullNameEnglish = element.find('#delegateFullNameEnglish').val();
    
    var birthDate = element.find('#delegateBirthDateId').val();
    var mainBirthDate = element.find('#delegateDateofBirth').val();
   
    var $academicTitle = element.find('#delegateAcademicTitleId option:selected');
    var academicTitle = $academicTitle.val();
    var academicTitleDescription = $academicTitle.text();

    var $gender = element.find('#delegateGenderId option:selected');
    var gender = $gender.val();
    var genderDescription = $gender.text();

    var $maritalStatus = element.find('#delegateMaritalStatusId option:selected');
    var maritalStatus = $maritalStatus.val();
    var maritalStatusDescription = $maritalStatus.text();

    var $premiumResident = element.find('#delegatePremiumResidentId option:selected');
    var premiumResident = $premiumResident.val();
    var premiumResidentDescription = $premiumResident.text();
    
    
    var postalCode = element.find('#delegatePostalCode').val();
    var poBox = element.find('#delegatePOBox').val();
    var countryCodeForTelephone = element.find('#delegateCountryCodeForTelephone').val();
    var delegateTelephone = element.find('#delegateTelephone').val();
    var delegateCountryCodeForMobile = element.find('#delegateCountryCodeForMobile').val();
    var delegateMobile = element.find('#delegateMobile').val();
    var delegateEmail = element.find('#delegateEmail').val();
    

    var delegateYourself = $('input[name=delegateInfo\\.delegateYourself]:checked').val();

    // delegateAcademicTitleId       - academicTitle, academicTitleDescription
    // delegatePremiumResidentId     - premiumResident, premiumResidentDescription
    // delegateNationalityPreviousId - nationalityPrevious, nationalityPreviousDescription
    var delegateInfo = {
        delegateYourself: typeof(delegateYourself) === 'undefined' ? '' : delegateYourself,
        refID: '',
        EntityNo: '',
        
        nationality: nationality,
        country: country,
        issueDate: issueDate,
        expiryDate: expiryDate,
        
        idType: idType,
        idNumber: idNumber,
        dateofBirth: birthDate,
        mainDateOfBirth: mainBirthDate,
        academicTitle: academicTitle,
        firstNameArabic: firstNameArabic,
        lastNameArabic: lastNameArabic,
        fullNameEnglish: fullNameEnglish,
        gender: gender,
    
        countryText: country,
        maritalStatus: maritalStatus,
        postalCode: postalCode,
        poBox: poBox,
        countryCodeForTelephone: countryCodeForTelephone,
        telephone: delegateTelephone,
        countryCodeForMobile: delegateCountryCodeForMobile,
        mobile: delegateMobile,
        email: delegateEmail,
        nicVerified: '',
        // idCopyFileName: '',
        // idCopyFile: '',
        authorizationLetterFileName: '',
        authorizationLetterFile: ''
        /*
        delegateAttachments: []
            refId
            entityNumber
            fileType
            fileName
            fileContent
            fileMimeType
        */
    };

   

   

    return delegateInfo;
};

var saveShareholder = function (existingBp, bpId) {
	
	
    var validator = shareholderValidator();
    if (!validator.form()) {
        return;
    }
    
    var percentage = $('#shareholderPercentageId').val();
    if (percentage === '') {  
    if(!validateShareholderNICInput(idType,idNumber,shareholderDateofBirth,$('#shareholderValidationDetails'))) {
        return false
    }
    }

    licenseAmendment.dirtyAmendment = true;
    var saveShareholderBtn = $('.saveShareholderBtn');
    var shareholderSrId = saveShareholderBtn.attr('id');
    saveShareholderBtn.removeAttr('id');

    var $companyCountry = $('#companyCountry option:selected');
    var companyCountry = $companyCountry.val();
    var companyCountryDescription = $companyCountry.text();

    var inputCRNumber = $('#inputCRNumber').val();
    var isCrVerified =  $("#professionalLicenseCrVerified").val();

    var name = $('#shareholderNameId').val();
    var englishName = $('#shareholderNameEnglishId').val();
    var subsector = $('#shareholderSubsectorId').val();
    var capital = $('#shareholderCapitalId').val();
    var labourSize = $('#shareholderLabourSizeId').val();
    var firstName = $('#shareholderFirstNameId').val();
    var lastName = $('#shareholderLastNameId').val();
   
    var inheritedProperty = $('#shareholderInheritedPropertyId').is(':checked');
    
    var deceasedId = $('#deceasedId').val();
    var deceasedName = $('#deceasedName').val();
    var deedNumber = $('#deedNumber').val();
    var isMojVerified = $('#isMojVerified').val();
    var multinationalCompany = $('#shareholderMultinationalCompanyId option:selected').val();
    var birthDate = $('#birthDateId').val();
    
  
	
	

    var idNumber = $("#identityNumberCopy").length > 0 ? $("#identityNumberCopy").val() : $('#shareholderIdNumber').val();

    var $idType = $('#shareholderIdType option:selected');
    var idType = $idType.val();

    var delegateYourself = $('input[name=delegateInfo\\.delegateYourself]:checked').val();

    var $industry = $('#shareholderSectorId option:selected');
    var industry = $industry.val();
    var industryDescription = $industry.text();

    var $legalStatus = $('#shareholderLegalStatusId option:selected');
    var legalStatus = $legalStatus.val();
    var legalStatusDescription = $legalStatus.text();

    var $academicTitle = $('#shareholderAcademicTitleId option:selected');
    var academicTitle = $academicTitle.val();
    var academicTitleDescription = $academicTitle.text();

    var $gender = $('#shareholderGenderId option:selected');
    var gender = $gender.val();
    var genderDescription = $gender.text();

    var $maritalStatus = $('#shareholderMaritalStatusId option:selected');
    var maritalStatus = $maritalStatus.val();
    var maritalStatusDescription = $maritalStatus.text();

    var $premiumResident = $('#shareholderPremiumResidentId option:selected');
    var premiumResident = $premiumResident.val();
    var premiumResidentDescription = $premiumResident.text();

    var $nationalityCurrent = $('#shareholderNationalityCurrentId option:selected');
    var nationalityCurrent = $nationalityCurrent.val();
    var nationalityCurrentDescription = $nationalityCurrent.text();

    var $nationalityPrevious = $('#shareholderNationalityPreviousId option:selected');
    var nationalityPrevious = $nationalityPrevious.val();
    var nationalityPreviousDescription = $nationalityPrevious.text();

    // Shareholder address
    var country = $('#shareholderCountryId option:selected').val();
    var city = $('#shareholderCityId').val();
    var street = $('#shareholderStreetId').val();
    var number = $('#shareholderNumberId').val();
    var zipCode = $('#shareholderZipCodeId').val();
    var telephone = $('#shareholderTelephoneId').val();
    var email = $('#shareholderEmailId').val();
    var website = $('#shareholderWebsiteId').val();

    var shareholderIndex = licenseAmendment.shareholders.findIndex(function (shareholder) {
        return shareholderSrId && (shareholder.srId == shareholderSrId || shareholder.newItemId == parseInt(shareholderSrId));
    });
    var selectedShareholder = licenseAmendment.shareholders[shareholderIndex];

    var shareholderRow;
    var percentageRow = (percentage.length > 5 ? percentage.substring(0, 5) : percentage) + '%';
    if (selectedShareholder) { // edit shareholder
        shareholderRow = $('#' + shareholderSrId);
        if (firstName) { // edit individual shareholder
            shareholderRow.children().first().html(firstName + ' ' + lastName)
                .next().text(getI18nText("general.individual")).next().text(percentageRow).next().text(nationalityCurrentDescription).next().text('-');
        } else { // edit entity shareholder
            shareholderRow.children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(percentageRow).next().text('-').next().text(legalStatus);
        }

        selectedShareholder.percentage = percentage;
        if (selectedShareholder.newItemId) { // Edit added shareholder - save all fields
            if (firstName) { // edit individual shareholder
                // isPersonNICVerified
                // selfDelegate
                // isPerDelegateNICVerified
                // selectedShareholder.idType = idType;
                // selectedShareholder.idNumber = idNumber;

                selectedShareholder.firstName = firstName;
                selectedShareholder.secondName = lastName;
                selectedShareholder.birthDateString = birthDate;
                selectedShareholder.academicTitle = academicTitle;
                selectedShareholder.academicTitleDescription = academicTitleDescription;
                selectedShareholder.gender = gender;
                selectedShareholder.genderDescription = genderDescription;
                selectedShareholder.maritalStatus = maritalStatus;
                selectedShareholder.maritalStatusDescription = maritalStatusDescription;
                selectedShareholder.premiumResident = premiumResident;
                selectedShareholder.premiumResidentDescription = premiumResidentDescription;
                selectedShareholder.nationalityCurrent = nationalityCurrent;
                selectedShareholder.nationalityCurrentDescription = nationalityCurrentDescription;
                selectedShareholder.nationalityPrevious = nationalityPrevious;
                selectedShareholder.nationalityPreviousDescription = nationalityPreviousDescription;
                
                selectedShareholder.deceasedId = deceasedId;
                selectedShareholder.deceasedName = deceasedName;
                selectedShareholder.deedNumber = deedNumber;
                selectedShareholder.isMojVerified = isMojVerified;
            } else { // edit entity shareholder
                selectedShareholder.companyCountry = companyCountry;
                selectedShareholder.companyCountryDescription = companyCountryDescription;
                selectedShareholder.inputCRNumber = inputCRNumber;
                selectedShareholder.firstName = name;
                selectedShareholder.englishName = englishName;
                selectedShareholder.industry = industry;
                selectedShareholder.industryDescription = industryDescription;
                selectedShareholder.multinationalCompany = multinationalCompany;
                selectedShareholder.legalStatus = legalStatus;
                selectedShareholder.legalStatusDescription = legalStatusDescription;
                selectedShareholder.subsector = subsector;
                selectedShareholder.capital = capital;
                selectedShareholder.labourSize = labourSize;
                selectedShareholder.isCrVerified = isCrVerified ; 
                
            }
            selectedShareholder.inheritedProperty = inheritedProperty;
            selectedShareholder.address.country = country;
            selectedShareholder.address.street = street;
            selectedShareholder.address.number = number;
            selectedShareholder.address.city = city;
            selectedShareholder.address.zipCode = zipCode;
            selectedShareholder.address.telephone = telephone;
            selectedShareholder.address.email = email;
            selectedShareholder.address.website = website;
            selectedShareholder.delegate = getDelegateInfo();
        } else {  // Edit existing shareholder
            licenseAmendment.shareholders[shareholderIndex].action = '02';
            setColorForModifiedRow(shareholderRow);
        }
    } else { // new shareholder
        shareholderRow = $('.shareholderTemplate').first().clone();
        shareholderRow.show();

        var shareholder = {
            action: '01',
            existingBp : existingBp,
            shBpId : bpId,
            firstName: '',
            secondName: '',
            academicTitle: '',
            academicTitleDescription: '',
            birthDateString: '',
            birthDate: {},
            gender: '',
            genderDescription: '',
            maritalStatus: '',
            maritalStatusDescription: '',
            premiumResident: '',
            premiumResidentDescription: '',
            nationalityCurrent: '',
            nationalityCurrentDescription: '',
            nationalityPrevious: '',
            nationalityPreviousDescription: '',
            name: '',
            englishName: '',
            industry: '',
            industryDescription: '',
            multinationalCompany: '',
            legalStatus: '',
            legalStatusDescription: '',
            subsector: '',
            capital: '',
            labourSize: '',
            isCrVerified: '',
            percentage: percentage,
            inheritedProperty: inheritedProperty,
            address: {
                country: country,
                street: street,
                number: number,
                telephone: telephone,
                email: email,
                website: website,
                city: city,
                zipCode: zipCode
            }
        };

        if (firstName) { // individual shareholder
            shareholderRow.attr("id", newItemId).children().first().html(firstName + ' ' + lastName)
                .next().text(getI18nText("general.individual")).next().text(percentageRow).next().text(nationalityCurrentDescription).next().text('-');
            shareholder.idType = idType;
            shareholder.idNumber = idNumber;
            shareholder.bpType = '1';
            shareholder.firstName = firstName;
            shareholder.secondName = lastName;
            shareholder.birthDateString = birthDate;
            shareholder.academicTitle = academicTitle;
            shareholder.academicTitleDescription = academicTitleDescription;
            shareholder.gender = gender;
            shareholder.genderDescription = genderDescription;
            shareholder.maritalStatus = maritalStatus;
            shareholder.maritalStatusDescription = maritalStatusDescription;
            shareholder.premiumResident = premiumResident;
            shareholder.premiumResidentDescription = premiumResidentDescription;
            shareholder.nationalityCurrent = nationalityCurrent;
            shareholder.nationalityCurrentDescription = nationalityCurrentDescription;
            shareholder.nationalityPrevious = nationalityPrevious;
            shareholder.nationalityPreviousDescription = nationalityPreviousDescription;
            shareholder.delegate = getDelegateInfo();
            shareholder.deceasedId = deceasedId;
            shareholder.deceasedName = deceasedName;
            shareholder.deedNumber = deedNumber;
            shareholder.isMojVerified = isMojVerified;
            
        } else { // entity shareholder
            shareholderRow.attr("id", newItemId).children().first().html(name)
                .next().text(getI18nText("general.entity")).next().text(percentageRow).next().text('-').next().text(legalStatus);
            shareholder.bpType = '2';
            shareholder.firstName = name;
            shareholder.englishName = englishName;
            shareholder.industry = industry;
            shareholder.industryDescription = industryDescription;
            shareholder.multinationalCompany = multinationalCompany;
            shareholder.legalStatus = legalStatus;
            shareholder.legalStatusDescription = legalStatusDescription;
            shareholder.subsector = subsector;
            shareholder.capital = capital;
            shareholder.labourSize = labourSize;
            shareholder.isCrVerified = isCrVerified;
            shareholder.companyCountry = companyCountry;
            shareholder.companyCountryDescription = companyCountryDescription;
            shareholder.inputCRNumber = inputCRNumber;
            if(!shareholder.existingBp) { 
            	shareholder.delegate = getDelegateInfo();
            }
            
            resetVerifyInherit();            
        }

        

        setColorForNewRow(shareholderRow);
        $('#shareholdersId').append(shareholderRow);

        shareholder.newItemId = newItemId++;
        licenseAmendment.shareholders.push(shareholder);
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
        loadPersonForm();
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

    
    
    $('#delegateCountryCodeForMobile').val('');
    $('#delegateCountryCodeForTelephone').val('');
    $('#delegateFirstNameArabic').val('');
    $('#delegateLastNameArabic').val('');
    $('#delegateFullNameEnglish').val('');
    $('#delegateEmail').val('');
    $('#delegateTelephone').val('');
    $('#delegateMobile').val('');
    
    

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