var financialSurvey, listItems, newItemId = 1000;
var attachments;

SAGIA = SAGIA || {};
SAGIA.financialSurvey = SAGIA.financialSurvey || {};
SAGIA.financialSurvey.data = SAGIA.financialSurvey.data || {};
SAGIA.financialSurvey.dirtyAmendment = false;
SAGIA.financialSurvey.data.letterOfSupportFile = {};


function caluculateTotalCapital() {
    var sum = 0 ;
    sum = sum +  1*($('#paidUpCapitalCurrentQuarterId').val());
    sum = sum +  1*($('#retainedEarningsIncludeCurrentQuarterId').val());
    sum = sum +  1*($('#additionalPaidUpCapitalCurrentQuarterId').val());
   // sum = sum +  1*($('#profitLossQuarterCurrentQuarterId').val());
    sum = sum +  1*($('#totalReservesCurrentQuarterId').val());
    sum = sum +  1*($('#treasurySharesCurrentQuarterId').val());
    sum = sum +  1*($('#headOfficeAccountInBranchCurrentQuarterId').val());
    sum = sum +  1*($('#shareholderEquityOthersCurrentQuarterId').val());
    sum = sum +  1*($('#minorityRightsCurrentQuarterId').val());
    $('#totalShareholderEquityCurrentQuarterId').val(sum);
}

$(document).ready(function () {



    //show submit window when closing T&C
    $("#termsAndConditionsResponseModal").on("hidden.bs.modal", function () {
        $('#docsmodalId').modal('show');
        $('.modal:visible').length && $(document.body).addClass('modal-open');
    });

    //hide submit modal when opening T&C
    $("#termsAndConditionsResponseModal").on("show.bs.modal", function () {
        $('#docsmodalId').modal('hide');
        $('.modal:visible').length && $(document.body).addClass('modal-open');
    });

    if ($('body').hasClass("page-financial-survey")) {



     /*   var attachmentSection = $("#attachmentSection");
        attachmentSection.find("#letterOfSupportFile").on("change", function () {
            SAGIA.financialSurvey.data.letterOfSupportFile = $(this).val();
            var fileReader = new FileReader();
            fileReader.onload = function (event) {
                SAGIA.financialSurvey.data.letterOfSupportFileMimeType = event.target._TYPE;
                SAGIA.financialSurvey.data.letterOfSupportFileName = SAGIA.financialSurvey.data.letterOfSupportFile;
               // SAGIA.financialSurvey.data.letterOfSupportFile = removeBase64prefix(event.target._TYPE, event.target.result);
            };
            fileReader._TYPE = $(this)[0].files[0].type;
            fileReader.readAsDataURL($(this)[0].files[0]);
        });
*/
        var attachmentSection = $("#attachmentSection");
        var letterOfSupportFile = attachmentSection.find("#letterOfSupportFile").val();
        if(letterOfSupportFile) {
            var fileReaderLetterOfSupportFile = new FileReader();
            fileReaderLetterOfSupportFile.onload = function (event) {
                SAGIA.financialSurvey.data.letterOfSupportFileMimeType = event.target._TYPE;
                SAGIA.financialSurvey.data.letterOfSupportFile = removeBase64prefix(event.target._TYPE, event.target.result);
                SAGIA.financialSurvey.data.letterOfSupportFileName = attachmentSection.find("#letterOfSupportFileName").val();
              //  newIdCopyFileReady = true;
            };
            fileReaderLetterOfSupportFile._TYPE = attachmentSection.find("#letterOfSupportFile")[0].files[0].type;
            fileReaderLetterOfSupportFile.readAsDataURL(attachmentSection.find("#letterOfSupportFile")[0].files[0]);
        }


        $("#commercialRegistrationNoID").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#unifiedNo700ID").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#comppanyPaidUpCapitalCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#paidUpCapitalCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#additionalPaidUpCapitalCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#retainedEarningsIncludeCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#profitLossQuarterCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#totalReservesCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        /*$("#treasurySharesCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });*/
        $("#headOfficeAccountInBranchCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderEquityOthersCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#minorityRightsCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#totalShareholderEquityCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("input[name='consolidatedStandloneRadioBox']").click(function () {
            if ($(this).val() === "true") {
                $('#subsidiarySection').hide();
            } else {
                $('#subsidiarySection').show();
            }
        });

        var elementsuspensionDateId = $("#suspensionDateId");
        bindNormalCal(elementsuspensionDateId);

        $("#companyStatusId").change(function(){
            var element = $("#entityAmendTabId");
            onchangeOfCompanyStatus(element);
        });

        $('.shareholderTemplate').hide();
        $('.branchTemplate').hide();
        $('.subsidiaryTemplate').hide();
        $('.productTemplate').hide();
        $('.docTemplate').hide();
        $('#entityHistoryTabId').hide();

        getFinancialSurveyData();
        getDropDownsData();
        setupBranchesTable();
        setupSubsidiariesTable();
        SAGIA.formElements.placeholderPolyfill();

        $("#branchRegionId").change(updateCities);
        $("#subsidiaryRegionId").change(updateCities);

        $(".validateLicenseBtn").on('click', submitLicense);

        var elementIncorporationDate = $("#incorporationDate");
        bindNormalCal(elementIncorporationDate);

        var elementCrIssueDate = $("#crIssueDate");
        bindNormalCal(elementCrIssueDate);



        // Tabs navigation
        $(document).on("click", "#nextTabEntityBtnId", function () {
            var validator = entityValidator();
            if (!validator.form()) {
                $('a[href="#accessibletabscontent0-0"]').click();
                return;
            }
            $('a[href="#accessibletabscontent0-1"]').click();
        });

        $(document).on("click", "#nextTabSubsidiariesBtnId", function () {
            $('a[href="#accessibletabscontent0-2"]').click();
        });

        $(document).on("click", "#nextTabShareholdersEquityBtnId", function () {
            var validator = equityValidator();
            if (!validator.form()) {
                $('a[href="#accessibletabscontent0-2"]').click();
                return;
            }else {
                caluculateTotalCapital();
            }

            $('a[href="#accessibletabscontent0-3"]').click();

        });

        $(document).on("click", "#nextTabShareholdersBtnId", function () {

            validateShareHoldersSumPercentage();

        });


        // Cancel amendment
        $(document).on("click", ".cancelAmendmentBtn", function () {
            var dirty = SAGIA.financialSurvey.dirtyAmendment;
            if (!dirty) {
                var labour = financialSurvey.entity.labour;
                var labourOld = financialSurvey.entity.labourOld;
                dirty = $('#labourId').val() !== labour || labour !== labourOld;

                var capital = financialSurvey.entity.capital;
                var capitalOld = financialSurvey.entity.capitalOld;
                dirty = dirty || $('#capitalId').val() !== capital || capital !== capitalOld;

                var name = financialSurvey.entity.name;
                var nameOld = financialSurvey.entity.nameOld;
                dirty = dirty || $('#entityNameId').val() !== name || name !== nameOld;

                var legalStatus = financialSurvey.entity.legalStatus;
                var legalStatusOld = financialSurvey.entity.legalStatusOld;
                dirty = dirty || $('#legalStatusId option:selected').val() !== legalStatus || legalStatus !== legalStatusOld;
            }

            if (dirty) {
                $('#unsavedChangesModalId').modal({backdrop: "static", keyboard: false});
            } else {
              //  $('#expandAmendmentHistoryBtnId').click();
                // Go back to dashboard
                window.location.href = ACC.config.encodedContextPath + "/dashboard";
            }
        });

        $(document).on("click", "#dismissChangesBtnId, .showHistoryBtn", function () {
            $('#expandAmendmentHistoryBtnId').click();
        });

        $(document).on("click", "#submitChangesBtnId", function () {
            validateLicense();
        });

        var serviceId = "FSURVEY_"+quarterCode;
        $("#saveDraftBtnId").click(function () {

            financialSurvey.companyProfile = {};
            financialSurvey.companyProfile.companyName = $('#companyNameId').val();
            financialSurvey.companyProfile.commercialRegistrationNo =  $('#commercialRegistrationNoID').val();
            financialSurvey.companyProfile.unifiedNo700 =  $('#unifiedNo700ID').val();
            financialSurvey.companyProfile.legalStatus = $('#legalStatusId').val();
            financialSurvey.companyProfile.financeManagerName = $('#financeManagerNameId').val();
            financialSurvey.companyProfile.financeManagerEmail = $('#financeManagerEmailId').val();
            financialSurvey.companyProfile.financeManagerTelephone = $('#financeManagerTelephoneId').val();
            financialSurvey.companyProfile.crIssueDate = $('#crIssueDate').val();
            financialSurvey.companyProfile.incorporationDate = $('#incorporationDate').val();

            var data = {};
            data.serviceId = serviceId;
            data.json = JSON.stringify(financialSurvey);
            var pathNameURL = window.location.pathname;
            if (window.location.href.indexOf("?") > 0) {
                var indexOfQuestion = window.location.href.indexOf("?");
                var parametersString = window.location.href.substr(indexOfQuestion);
                pathNameURL = pathNameURL + parametersString;
            }
            data.url = pathNameURL;
            $.ajax(ACC.config.encodedContextPath + "/draft/save-json", {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', $('input[name="CSRFToken"]').attr('value'));
                },
                method: 'POST',
                data: JSON.stringify(data),
                success: function () {
                    $('#loadDraftBtnId').show();
                },
                error: function (error) {
                }
            });
        });

        $("#loadDraftBtnId").click(function () {
            $.ajax(ACC.config.encodedContextPath + "/draft/json?serviceId=" + "FSURVEY_"+quarterCode, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (draft) {
                    if (draft) {
                        SAGIA.financialSurvey.branch.dataTable.destroy();
                        SAGIA.financialSurvey.subsidiary.dataTable.destroy();
                        setLicenseData(JSON.parse(draft.data));
                        setupBranchesTable();
                        setupSubsidiariesTable();

                    }
                }
            });
        });

        var $submitAmendmentBtn = $('#submitAmendmentBtnId');
        $("#termsAndConditionsId").click(function () {
            if ($(this).is(':checked')) {
                $submitAmendmentBtn.attr("disabled", false);
            } else {
                $submitAmendmentBtn.attr("disabled", true);
            }
        });

        $submitAmendmentBtn.click(function () {
            $(this).attr('disabled', true);
        });

        $(".cancelAmendmentDialogBtn").on('click', cancelLicense);
        $(".submitAmendmentBtn").on('click', submitLicense);
    }
});

function getFinancialSurveyData() {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/"+quarterCode, {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            if (data) {
                setLicenseData(data);
                updateIsic(data);
            }
        },
        async: false
    });
}


function updateIsic(data) {
    SAGIA.financialSurvey.businessActivities.setBusinessTypeSectionVisible(false);

    var licenseActivities = [];
    if (data.businessActivities) {
        data.businessActivities.forEach(function(activity) {
                    var currentActivityId = activity.id;
                    var currentActivityDescription = activity.description;
                    if (currentActivityId && /^([0-9]+)$/.test(currentActivityId)) {
                        licenseActivities.push({id: currentActivityId, description: currentActivityDescription});
                    }
            })
    }


    if (licenseActivities && licenseActivities.length) {

        data.licenseType = 2;
        data.licenseTypeText = 'Services Investment License' ;
        SAGIA.financialSurvey.businessActivities.setLicenseType(data.licenseType, data.licenseTypeText, licenseActivities);
        SAGIA.financialSurvey.businessActivities.show(true);

    } else {
        data.licenseType = 2;
        data.licenseTypeText = 'Services Investment License' ;
        SAGIA.financialSurvey.businessActivities.setLicenseType(data.licenseType, data.licenseTypeText);
        SAGIA.financialSurvey.businessActivities.show(false);
    }
}

function getDropDownsData() {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/listItems", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            if (data) {
                setupDropDowns(data);
            }
        }
    });
}



function hideNewAmendmentElements() {
    $('.newAmendmentBtn, #saveDraftBtnId, #loadDraftBtnId').hide();
    $('#entityAmendTabId').hide();
}



var validateShareHoldersSumPercentage = function () {
    var sum = 0;
    financialSurvey.shareholders.forEach(function(shareholder) {
        if(shareholder.shareholderPercentage != null && shareholder.action != null && shareholder.action !== '03') {
            sum = Math.round((sum + Number(shareholder.shareholderPercentage)) * 1e12) / 1e12 ;
        }
    });
    if (sum !== 100) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('validation.sharePerc.sum'));
        return;
    }else {
        $('a[href="#accessibletabscontent0-4"]').click();
    }

}

var validateLicense = function () {


    // validate attechament

       var letterOfSupportFile = $("#letterOfSupportFile");
        letterOfSupportFile.closest('.form-group').removeClass("has-error").parent().find(".help-block").text('');
        if(!letterOfSupportFile.val()) {
            letterOfSupportFile.closest('.form-group').addClass('has-error').parent().find(".help-block").text(getI18nText("validation.attachment"));
            isValid = false;
        }



    var validator = entityValidator();
    if (!validator.form()) {
        $('a[href="#accessibletabscontent0-0"]').click();
        return;
    }

  //  financialSurvey.entity.activity = financialSurvey.entity.activity || '';
 //   var currentActivitiesIds = financialSurvey.entity.activity.match(/\d+/g) || [];
     var selectedActivities = SAGIA.financialSurvey.businessActivities.selectedActivities;
    var selectedActivitiesIds = [];
    selectedActivities.forEach(function(activity) {
        selectedActivitiesIds.push(activity.activityId);
    });

 //   var equalActivities = currentActivitiesIds.length === selectedActivitiesIds.length
 //       && currentActivitiesIds.sort().every(function(value, index) { return value == selectedActivitiesIds.sort()[index]});

  //  if (!equalActivities) {
        financialSurvey.businessActivities = [];
        selectedActivities.forEach(function (activity) {
            financialSurvey.businessActivities.push({
                id: activity.activityId,
                description: activity.description
            });
        });
 //   }

    if (financialSurvey.businessActivities.length < 1) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('finance.survey.validation.businessActivities'));
        return;
    }


    /*var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/amendmentTypes", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(financialSurvey),
        method: "POST",
        success: function (data) {
            if (data) {
                financialSurvey = data;
                if (financialSurvey.noChanges) {
                    $('#licenseAmendmentNoChangesDialogId').modal({
                        backdrop: "static",
                        keyboard: false
                    });
                }else if (financialSurvey.errors.length) {
                    var $modalDescription = $('#licenseAmendmentValidationDialogId').modal({
                        backdrop: "static",
                        keyboard: false
                    }).find('.modal-description');
                    $modalDescription.empty();
                    financialSurvey.errors.forEach(function (error) {
                        $modalDescription.append('<p>' + error + '</p>');
                    });
                } else {
                    var $docsModal = $('#docsmodalId');

                    $('#submitAmendmentBtnId').attr('disabled', true);
                    $('#termsAndConditionsId')[0].checked = false;

                    var $amendmentTypeDiv = $docsModal.find('#amendmentTypeId');
                    var $regularAmendmentDocs = $docsModal.find('#regularAmendmentDocsId');
                    if (financialSurvey.instantAmendment) {
                        $amendmentTypeDiv.text(getI18nText('license.amendment.type.instant'));
                        $regularAmendmentDocs.hide();
                    } else {
                        $amendmentTypeDiv.text(getI18nText('license.amendment.type.regular'));
                        $regularAmendmentDocs.show();
                    }

                    var $amendmentTypes = $docsModal.find('#amendmentTypesId');
                    var $docTemplate = $docsModal.find('.docTemplate').clone();
                    var $documents = $docsModal.find('#documentsId');
                    SAGIA.financialSurvey.generatedDocuments = [];
                    data.amendmentTypesView.forEach(function (amendmentType) {
                        $amendmentTypes.append('<div class="col-sm-6"><ul class="dottedList dottedList_green dottedList_big">' +
                            '<li class="dottedList-item">' + amendmentType.name + '</li>' +
                            '</ul></div>');
                        if (!data.instantAmendment) {
                            attachments.forEach(function (attachment) {
                                if (attachment.longDescription.indexOf(amendmentType.code) !== -1 && $docsModal.find('#' + attachment.dockeyId).length === 0) {
                                    var $documentRow = $docTemplate.clone();
                                    $documentRow.find('#fileId').attr('id', attachment.dockeyId).attr('name', attachment.dockeyId);
                                    $documentRow.find('.control-label').text(attachment.name).attr('for', attachment.dockeyId);
                                    SAGIA.financialSurvey.generatedDocuments.push(attachment.dockeyId);
                                    $documentRow.show();
                                    $documents.append($documentRow);
                                }
                            });
                        }

                    });
                    $docsModal.modal({
                        backdrop: "static",
                        keyboard: false
                    });
                }
            }
        }
    });*/
};

var cancelLicense = function () {
    $('#amendmentTypesId').empty();
    $('#documentsId').empty();
    $('#docsmodalId').modal('hide');
};

function fillShareholderEquityData( shareholderEquity ) {
    $('#paidUpCapitalCurrentQuarterId').val(shareholderEquity.paidUpCapitalCurrentQuarter);
    $('#additionalPaidUpCapitalCurrentQuarterId').val(shareholderEquity.additionalPaidUpCapitalCurrentQuarter);
    $('#retainedEarningsIncludeCurrentQuarterId').val(shareholderEquity.retainedEarningsIncludeCurrentQuarter);
    $('#profitLossQuarterCurrentQuarterId').val(shareholderEquity.profitLossQuarterCurrentQuarter);
    $('#totalReservesCurrentQuarterId').val(shareholderEquity.totalReservesCurrentQuarter);
    $('#treasurySharesCurrentQuarterId').val(shareholderEquity.treasurySharesCurrentQuarter);
    $('#headOfficeAccountInBranchCurrentQuarterId').val(shareholderEquity.headOfficeAccountInBranchCurrentQuarter);
    $('#shareholderEquityOthersCurrentQuarterId').val(shareholderEquity.shareholderEquityOthersCurrentQuarter);
    $('#minorityRightsCurrentQuarterId').val(shareholderEquity.minorityRightsCurrentQuarter);
}


function populateShareholderEquity() {
    financialSurvey.shareholderEquity = {};
    financialSurvey.shareholderEquity.paidUpCapitalCurrentQuarter = $('#paidUpCapitalCurrentQuarterId').val();
    financialSurvey.shareholderEquity.paidUpCapitalPreviousQuarter = $('#paidUpCapitalPreviousQuarterId').val();
    financialSurvey.shareholderEquity.additionalPaidUpCapitalCurrentQuarter = $('#additionalPaidUpCapitalCurrentQuarterId').val();
    financialSurvey.shareholderEquity.additionalPaidUpCapitalPreviousQuarter = $('#additionalPaidUpCapitalPreviousQuarterId').val();
    financialSurvey.shareholderEquity.retainedEarningsIncludeCurrentQuarter = $('#retainedEarningsIncludeCurrentQuarterId').val();
    financialSurvey.shareholderEquity.retainedEarningsIncludePreviousQuarter = $('#retainedEarningsIncludePreviousQuarterId').val();
    financialSurvey.shareholderEquity.profitLossQuarterCurrentQuarter = $('#profitLossQuarterCurrentQuarterId').val();
    financialSurvey.shareholderEquity.profitLossQuarterPreviousQuarter = $('#profitLossQuarterPreviousQuarterId').val();
    financialSurvey.shareholderEquity.totalReservesCurrentQuarter = $('#totalReservesCurrentQuarterId').val();
    financialSurvey.shareholderEquity.totalReservesPreviousQuarter = $('#totalReservesPreviousQuarterId').val();
    financialSurvey.shareholderEquity.treasurySharesCurrentQuarter = $('#treasurySharesCurrentQuarterId').val();
    financialSurvey.shareholderEquity.treasurySharesPreviousQuarter = $('#treasurySharesPreviousQuarterId').val();
    financialSurvey.shareholderEquity.headOfficeAccountInBranchCurrentQuarter = $('#headOfficeAccountInBranchCurrentQuarterId').val();
    financialSurvey.shareholderEquity.headOfficeAccountInBranchPreviousQuarter = $('#headOfficeAccountInBranchPreviousQuarterId').val();
    financialSurvey.shareholderEquity.shareholderEquityOthersCurrentQuarter = $('#shareholderEquityOthersCurrentQuarterId').val();
    financialSurvey.shareholderEquity.shareholderEquityOthersPreviousQuarter = $('#shareholderEquityOthersPreviousQuarterId').val();
    financialSurvey.shareholderEquity.minorityRightsCurrentQuarter = $('#minorityRightsCurrentQuarterId').val();
    financialSurvey.shareholderEquity.minorityRightsPreviousQuarter = $('#minorityRightsPreviousQuarterId').val();
    // financialSurvey.shareholderEquity.totalShareholderEquityCurrentQuarter = $('#totalShareholderEquityCurrentQuarterId').val();
    //  financialSurvey.shareholderEquity.totalShareholderEquityPreviousQuarter = $('#totalShareholderEquityPreviousQuarterId').val();
    /*financialSurvey.shareholderEquity.cashTransactionIncrease = $('#cashTransactionIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.profitsIncrease = $('#profitsIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.reservesIncrease = $('#reservesIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.shareholdersCreditIncrease = $('#shareholdersCreditIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.mergersIncrease = $('#mergersIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.acquisitionsIncrease = $('#acquisitionsIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.assetsInKindIncrease = $('#assetsInKindIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.othersIncrease = $('#othersIncreaseId').is(':checked');
    financialSurvey.shareholderEquity.lossesDecrease = $('#lossesDecreaseId').is(':checked');
    financialSurvey.shareholderEquity.treasurySharesDecrease = $('#treasurySharesDecreaseId').is(':checked');
    financialSurvey.shareholderEquity.othersDecrease = $('#othersDecreaseId').is(':checked');*/
}
function populateCompanyProfile() {
    financialSurvey.companyProfile = {};
    financialSurvey.companyProfile.companyName = $('#companyNameId').val();
    financialSurvey.companyProfile.commercialRegistrationNo = $('#commercialRegistrationNoID').val();
    financialSurvey.companyProfile.unifiedNo700 = $('#unifiedNo700ID').val();
    financialSurvey.companyProfile.crIssueDate = $('#crIssueDate').val();
    financialSurvey.companyProfile.incorporationDate = $('#incorporationDate').val();
    financialSurvey.companyProfile.financeManagerName = $('#financeManagerNameId').val();
    financialSurvey.companyProfile.financeManagerEmail = $('#financeManagerEmailId').val();
    financialSurvey.companyProfile.financeManagerTelephone = $('#financeManagerTelephoneId').val();
    financialSurvey.companyStatus = $('#companyStatusId').val();
    financialSurvey.suspensionDate = $('#suspensionDateId').val();
    financialSurvey.disclosureCurrency = $('#disclosureCurrencyId').val();
    financialSurvey.paidUpCapitalCurrentQuarter = $('#comppanyPaidUpCapitalCurrentQuarterId').val();
}

var submitLicense = function () {

    financialSurvey.attachments = [];
    financialSurvey.quarterCode = quarterCode;
    populateCompanyProfile();
    //validateLicense();
    populateShareholderEquity();


    // licenseAmendment.entity.activity = licenseAmendment.entity.activity || '';
   // var currentActivitiesIds = licenseAmendment.entity.activity.match(/\d+/g) || [];
    var selectedActivities = SAGIA.financialSurvey.businessActivities.selectedActivities;
    var selectedActivitiesIds = [];
    selectedActivities.forEach(function(activity) {
        selectedActivitiesIds.push(activity.activityId);
    });

  //  var equalActivities = currentActivitiesIds.length === selectedActivitiesIds.length
  //      && currentActivitiesIds.sort().every(function(value, index) { return value == selectedActivitiesIds.sort()[index]});

   /// if (!equalActivities) {
        financialSurvey.businessActivities = [];
        selectedActivities.forEach(function (activity) {
            financialSurvey.businessActivities.push({
                id: activity.activityId,
                description: activity.description
            });
        });
  //  }


    var generatedDocuments = SAGIA.financialSurvey.generatedDocuments;
    if (generatedDocuments && generatedDocuments.length && !documentsValidator(generatedDocuments).form()) {
        return;
    }

    var $attachments = $('#documentsId :file');
    var attachmentsNotLoaded = $attachments.length;
    $attachments.each(function (index, file) {
        var attachment = {};
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            attachment.content = removeBase64prefix(event.target._TYPE, event.target.result);
            attachment.name = event.target._NAME;
            attachment.dockeyId = event.target._DOCKEYID;
            financialSurvey.attachments.push(attachment);
            attachmentsNotLoaded--;
        };
        fileReader._NAME = $('#documentsId label[for=' + $(file).attr('id') + ']').text();
        fileReader._DOCKEYID = $(file).attr('id');
        fileReader._TYPE = file.files[0].type;
        fileReader.readAsDataURL(file.files[0]);
    });


    var timerId = setInterval(function () {
        if (attachmentsNotLoaded === 0) {
            submitLicenseWithAttachments();
            clearInterval(timerId);
        }
    }, 1000);
};

function submitLicenseWithAttachments() {
    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete", {
        method: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(financialSurvey),
        success: function () {
            $('#docsmodalId').modal('hide');
            if (financialSurvey.errors && financialSurvey.errors.length) {
                var $modalDescription = $('#licenseAmendmentValidationDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                }).find('.modal-description');
                $modalDescription.empty();
                financialSurvey.errors.forEach(function (error) {
                    $modalDescription.append('<p>' + error + '</p>');
                });
            } else {
                $('#requestSubmittedDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                });
            }
        },
        error: function () {
            $('#docsmodalId').modal('hide');
            $('#requestErrorDialogId').modal({
                backdrop: "static",
                keyboard: false
            }).find('.globalMessage-msg').text(getI18nText("finance.survey.submit.error"));
        }
    });
}

function setLicenseData(data, history) {
    financialSurvey = data;

    // companyProfile
    var companyProfile = financialSurvey.companyProfile;

    $('#companyNameId').val(companyProfile.companyName);
    $('#commercialRegistrationNoID').val(companyProfile.commercialRegistrationNo);
    $('#unifiedNo700ID').val(companyProfile.unifiedNo700);
    $('#financeManagerNameId').val(companyProfile.financeManagerName);
    $('#financeManagerEmailId').val(companyProfile.financeManagerEmail);
    $('#financeManagerTelephoneId').val(companyProfile.financeManagerTelephone);
    $('#crIssueDate').val(companyProfile.crIssueDate);
    $('#incorporationDate').val(companyProfile.incorporationDate);
    fillShareholderEquityData(financialSurvey.shareholderEquity)

    var companyStatus = financialSurvey.companyStatus ;
    if (!companyStatus){
        companyStatus = 'ACTIVE';
    }
    updateDropDown('#companyStatusId', companyStatus);

  //  updateDropDown('#legalStatusId', companyProfile.legalStatus);

    var DELETE_ACTION = '03';


    var $shareholderRowTemplate = $('.shareholderTemplate').first().clone(true);
    var $shareholdersTable = $('#shareholdersId').empty().append($shareholderRowTemplate.clone(true).hide());
    financialSurvey.shareholders.forEach(function (shareholder) {
        if (shareholder.action !== DELETE_ACTION) {
            var name = shareholder.name;
            var type = shareholder.bpType === '1' ? getI18nText("general.individual") : getI18nText("general.entity");
            var percentage = 0;
            if(shareholder.shareholderPercentage != null) {
                percentage = (shareholder.shareholderPercentage.length > 5 ? shareholder.shareholderPercentage.substring(0, 5) : shareholder.shareholderPercentage) + '%';
            }
            var shareValue = shareholder.shareholderCapital ;

            var shareholderVotingPower = 0;
            if(shareholder.shareholderVotingPower != null) {
                shareholderVotingPower = (shareholder.shareholderVotingPower.length > 5 ? shareholder.shareholderVotingPower.substring(0, 5) : shareholder.shareholderVotingPower) + '%';
            }

            var $shareholderRow = $shareholderRowTemplate.clone(true).show();
            $shareholderRow.attr("id", shareholder.srId ? shareholder.srId : shareholder.newItemId).children().first().text(name).next().text(type)
                .next().text(percentage).next().text(shareValue).next().text(shareholderVotingPower);
            setColorForDraftRow($shareholderRow, shareholder.action);
            $shareholdersTable.append($shareholderRow);
        }
    });

    // Start Affiliate

    var $affiliateRowTemplate = $('.affiliateTemplate').first().clone(true);
    var $affiliatesTable = $('#affiliatesId').empty().append($affiliateRowTemplate.clone(true).hide());

    financialSurvey.affiliates.forEach(function (affiliate) {
        if (affiliate.action !== DELETE_ACTION) {
            var name = affiliate.name;
            var type = affiliate.bpType === '1' ? getI18nText("general.individual") : getI18nText("general.entity");

            var shareValue = affiliate.affiliateCapital ;

            var affiliateVotingPower = 0;
            if(affiliate.affiliateVotingPower != null) {
                affiliateVotingPower = (affiliate.affiliateVotingPower.length > 5 ? affiliate.affiliateVotingPower.substring(0, 5) : affiliate.affiliateVotingPower) + '%';
            }

            var $affiliateRow = $affiliateRowTemplate.clone(true).show();
            $affiliateRow.attr("id", affiliate.srId ? affiliate.srId : affiliate.newItemId).children().first().text(name).next().text(type)
                .next().text(percentage).next().text(shareValue).next().text(affiliateVotingPower);
            setColorForDraftRow($affiliateRow, affiliate.action);
            $affiliatesTable.append($affiliateRow);
        }
    });


    // End Affiliate

    var $branchRowTemplate = $('.branchTemplate').first().clone(true);
    var $branchesTable = $('#branchesId').empty();//.append($branchRowTemplate.clone(true).hide());
    if (history) {
        $('#branchBtnColumnId').hide();
        $branchRowTemplate.find('.tableModule-bodyItem-action').hide();
        $('.newBranchBtn').hide();
    }
    financialSurvey.branches.forEach(function (branch) {
        if (branch.action !== DELETE_ACTION) {
            var typeDescription = branch.typeDescription;
            var name = branch.name;
            var city = branch.address.cityDescription;
            var addrNumber = branch.addrNumber;
            var $branchRow = $branchRowTemplate.clone(true).show();
            $branchRow.attr("id", branch.srId).children().first().html(typeDescription).next().text(name).next().text(city).next().text(addrNumber);
            setColorForDraftRow($branchRow, branch.action);
            if (!history) {
                if (branch.main) { // Main branch, can edit, can't be removed
                    $branchRow.find('.viewBranchBtn').hide();
                    $branchRow.find('.editBranchBtn').show();
                    $branchRow.find('.deleteDropdown').hide();
                } else { // Can be removed
                    $branchRow.find('.viewBranchBtn').show();
                    $branchRow.find('.editBranchBtn').hide();
                    $branchRow.find('.deleteDropdown').show();
                }
            }

            $branchesTable.append($branchRow);
        }
    });



    var $subsidiaryRowTemplate = $('.subsidiaryTemplate').first().clone(true);
    var $subsidiariesTable = $('#subsidiariesId').empty();//.append($branchRowTemplate.clone(true).hide());
    if (history) {
        $('#subsidiaryBtnColumnId').hide();
        $subsidiaryRowTemplate.find('.tableModule-bodyItem-action').hide();
        $('.newSubsidiaryBtn').hide();
    }

    financialSurvey.subsidiaries.forEach(function (subsidiary) {
        if (subsidiary.action !== DELETE_ACTION) {
            var name = subsidiary.subsidiaryName;
            var registrationNumber = subsidiary.registrationName;
            var contribution = subsidiary.contribution;
            var unifiedNo = subsidiary.unifiedNo;
            var $subsidiaryRow = $subsidiaryRowTemplate.clone(true).show();
            $subsidiaryRow.attr("id", subsidiary.srId).children().first().html(name).next().text(registrationNumber).next().text(unifiedNo);
            setColorForDraftRow($subsidiaryRow, subsidiary.action);
            if (!history) {
                $subsidiaryRow.find('.viewSubsidiaryBtn').show();
                $subsidiaryRow.find('.editSubsidiaryBtn').hide();
                $subsidiaryRow.find('.deleteDropdown').show();
                }

        $subsidiariesTable.append($subsidiaryRow);
        }
    });



    adjustNewItemIdVariable();
}

function setupDropDowns(data) {
    attachments = data.attachments;
    listItems = data;

    var $branchTypeSelect = $('#branchTypeId').append(new Option('', '', false, false));
    data.branchTypes.forEach(function (branchType) {
        $branchTypeSelect.append(new Option(branchType.name, branchType.id, false, false));
    });

    var $branchRegionSelect = $('#branchRegionId').append(new Option('', '', false, false));
    data.regions.forEach(function (region) {
        $branchRegionSelect.append(new Option(region.name, region.id, false, false));
    });


    var $shareholderGenderSelect = $('#shareholderGenderId').append(new Option('', '', false, false));
    var $affiliateGenderSelect = $('#affiliateGenderId').append(new Option('', '', false, false));

    data.gender.forEach(function (gender) {
        $shareholderGenderSelect.append(new Option(gender.name, gender.id, false, false));
        $affiliateGenderSelect.append(new Option(gender.name, gender.id, false, false));
    });

    var $shareholderStatusSelect = $('#shareholderMaritalStatusId').append(new Option('', '', false, false));
    var $affiliateStatusSelect = $('#shareholderMaritalStatusId').append(new Option('', '', false, false));
    data.status.forEach(function (status) {
        $shareholderStatusSelect.append(new Option(status.name, status.id, false, false));
        $affiliateStatusSelect.append(new Option(status.name, status.id, false, false));
    });



    var $currentNationalitySelect = $('#shareholderNationalityCurrentId').append(new Option('', '', false, false));
    var $previousNationalitySelect = $('#shareholderNationalityPreviousId').append(new Option('', '', false, false));
    var $shareholderCountrySelect = $('#shareholderCountryId').append(new Option('', '', false, false));
    var $affiliateCurrentNationalitySelect = $('#affiliateNationalityCurrentId').append(new Option('', '', false, false));
    var $affiliatePreviousNationalitySelect = $('#affiliateNationalityPreviousId').append(new Option('', '', false, false));
    var $affiliateCountrySelect = $('#affiliateCountryId').append(new Option('', '', false, false));
    var affiliateCompanyCountry = $('#affiliateCompanyCountry').append(new Option('', '', false, false));
    var $companyCountrySelect = $('#companyCountry').append(new Option('', '', false, false));

    data.countries.forEach(function (country) {
        $currentNationalitySelect.append(new Option(country.name, country.id, false, false));
        affiliateCompanyCountry.append(new Option(country.name, country.id, false, false));
        $previousNationalitySelect.append(new Option(country.name, country.id, false, false));
        $shareholderCountrySelect.append(new Option(country.name, country.id, false, false));
        $affiliateCurrentNationalitySelect.append(new Option(country.name, country.id, false, false));
        $affiliatePreviousNationalitySelect.append(new Option(country.name, country.id, false, false));
        $affiliateCountrySelect.append(new Option(country.name, country.id, false, false));
        $companyCountrySelect.append(new Option(country.name, country.id, false, false));
    });

    var $sectorSelect = $('#shareholderSectorId').append(new Option('', '', false, false));
    var $affiliateSectorSelect = $('#affiliateSectorId').append(new Option('', '', false, false));
    data.sectors.forEach(function (sector) {
        $sectorSelect.append(new Option(sector.name, sector.id, false, false));
        $affiliateSectorSelect.append(new Option(sector.name, sector.id, false, false));
    });

    var $legalStatusSelect = $('#shareholderLegalStatusId').append(new Option('', '', false, false));
    var $affiliateLegalStatusSelect = $('#affiliateLegalStatusId').append(new Option('', '', false, false));
    data.legalStatus.forEach(function (legalStatus) {
        $legalStatusSelect.append(new Option(legalStatus.name, legalStatus.id, false, false));
        $affiliateLegalStatusSelect.append(new Option(legalStatus.name, legalStatus.id, false, false));
    });

    var $multinationalCompanySelect = $('#shareholderMultinationalCompanyId').append(new Option('', '', false, false));
    var $affiliateMultinationalCompanySelect = $('#affiliateMultinationalCompanyId').append(new Option('', '', false, false));
    data.multinationalCompany.forEach(function (multinationalCompany) {
        $multinationalCompanySelect.append(new Option(multinationalCompany.name, multinationalCompany.id, false, false));
        $affiliateMultinationalCompanySelect.append(new Option(multinationalCompany.name, multinationalCompany.id, false, false));
    });

    var $unitSelect = $('#productUnitId').append(new Option('', '', false, false));
    data.unit.forEach(function (unit) {
        $unitSelect.append(new Option(unit.name, unit.id, false, false));
    });

    var selectedRegionId = data.regions[0].id;
    var $branchCitySelect = $('#branchCityId').append(new Option('', '', false, false));
    data.cities.forEach(function (city) {
        if (city.parentId === selectedRegionId) {
            $branchCitySelect.append(new Option(city.name, city.id, false, false));
        }
    });
}

var updateCities = function () {
    var selectedRegionId = $('#branchRegionId option:selected').val();

    var $branchCitySelect = $('#branchCityId').empty();
    $branchCitySelect.append(new Option('', '', false, false));
    listItems.cities.forEach(function (city) {
        if (city.parentId === selectedRegionId) {
            $branchCitySelect.append(new Option(city.name, city.id, false, false));
        }
    });
};


var adjustNewItemIdVariable = function () {
    newItemId = parseInt($('.shareholderTemplate:last-child').attr('id')) + 1;
    newItemId = 1;

    newItemId = parseInt($('.affiliateTemplate:last-child').attr('id')) + 1;
    newItemId = 1;
}



var bindNormalCal = function(element){
    element.removeClass("ummAlQura");

    element.calendarsPicker('destroy');

    bindCustomFlatpickr(element);
};


var onchangeOfCompanyStatus = function(element) {

    var elementCompanyStatusId = element.find("#companyStatusId");
    var elementsuspensionDateId = element.find("#suspensionDateId");

    bindNormalCal(elementsuspensionDateId);

    // show and hide the special fields
    if(elementCompanyStatusId.val()!=="ACTIVE"){
        $("#suspensionDateSection").show();
    }
    else
    {
        $("#suspensionDateSection").hide();
    }


};












