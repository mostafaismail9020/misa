var financialSurvey, listItems, newItemId = 1000;
var attachments;

SAGIA = SAGIA || {};
SAGIA.financialSurvey = SAGIA.financialSurvey || {};
SAGIA.financialSurvey.data = SAGIA.financialSurvey.data || {};
SAGIA.financialSurvey.dirtyAmendment = false;
SAGIA.financialSurvey.isCompanyProfileSectionFilled = false;
SAGIA.financialSurvey.isBranchSectionFilled = false;
SAGIA.financialSurvey.isEquitySectionFilled = false;
SAGIA.financialSurvey.isShareholdersSectionFilled = false;
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

function caluculateTotalCapitalPreviousQuarter() {
    var sum = 0 ;
    sum = sum +  1*($('#paidUpCapitalPreviousQuarterId').val());
    sum = sum +  1*($('#retainedEarningsIncludePreviousQuarterId').val());
    sum = sum +  1*($('#additionalPaidUpCapitalPreviousQuarterId').val());
    // sum = sum +  1*($('#profitLossQuarterCurrentQuarterId').val());
    sum = sum +  1*($('#totalReservesPreviousQuarterId').val());
    sum = sum +  1*($('#treasurySharesPreviousQuarterId').val());
    sum = sum +  1*($('#headOfficeAccountInBranchPreviousQuarterId').val());
    sum = sum +  1*($('#shareholderEquityOthersPreviousQuarterId').val());
    sum = sum +  1*($('#minorityRightsPreviousQuarterId').val());
    $('#totalShareholderEquityPreviousQuarterId').val(sum);
}

function caluculateTotalTansactionDebit() {
    var sum = 0 ;
    sum = sum +  1*($('#tradeDebitCurrentQuarterId').val());
    sum = sum +  1*($('#loansAssetsCurrentQuarterId').val());
    sum = sum +  1*($('#interestReceivedCurrentQuarterId').val());
    sum = sum +  1*($('#dividendsReceivedCurrentQuarterId').val());
    sum = sum +  1*($('#expensesReceivedCurrentQuarterId').val());
    sum = sum +  1*($('#sellProductionSuppliesCurrentQuarterId').val());
    sum = sum +  1*($('#sellMachineryCurrentQuarterId').val());
    sum = sum +  1*($('#currentDebitAccountCurrentQuarterId').val());
    sum = sum +  1*($('#expensesReceivableCurrentQuarterId').val());
    sum = sum +  1*($('#insuranceCommissionReceivableCurrentQuarterId').val());
    sum = sum +  1*($('#otherDebitCurrentQuarterId').val());
    $('#totalDebitCurrentQuarterId').val(sum);
}

function calculateTotalTansactionCredit() {
    var sum = 0 ;

    sum = sum +  1*($('#tradeCreditCurrentQuarterId').val());
    sum = sum +  1*($('#loansLiabilitiesCurrentQuarterId').val());
    sum = sum +  1*($('#interestPayableCurrentQuarterId').val());
    sum = sum +  1*($('#dividendsPaidCurrentQuarterId').val());
    sum = sum +  1*($('#expensesPaidCurrentQuarterId').val());
    sum = sum +  1*($('#purchaseProductionSuppliesCurrentQuarterId').val());
    sum = sum +  1*($('#currentCreditAccountCurrentQuarterId').val());
    sum = sum +  1*($('#expensesPayableCurrentQuarterId').val());
    sum = sum +  1*($('#insuranceCommissionPayableCurrentQuarterId').val());
    sum = sum +  1*($('#otherCreditCurrentQuarterId').val());
    sum = sum +  1*($('#purchaseMachineryCurrentQuarterId').val());
    $('#totalCreditCurrentQuarterId').val(sum);
}

function calculateShareholderEquity() {

    var sharePercentage = $('#shareholderPercentageId').val();
    if (sharePercentage <= 0  || sharePercentage > 100) {
        return;
    }
    var retainedEarningsIncludeCurrentQuarter = (financialSurvey.shareholderEquity.retainedEarningsIncludeCurrentQuarter /100)  * sharePercentage ;
    var paidUpCapitalCurrentQuarter = (financialSurvey.paidUpCapitalCurrentQuarter /100)  * sharePercentage ;
    var additionalPaidUpCapitalCurrentQuarter = (financialSurvey.shareholderEquity.additionalPaidUpCapitalCurrentQuarter /100)  * sharePercentage ;
    var profitLossQuarterCurrentQuarter = (financialSurvey.shareholderEquity.profitLossQuarterCurrentQuarter /100)  * sharePercentage ;
    var totalReservesCurrentQuarter = (financialSurvey.shareholderEquity.totalReservesCurrentQuarter /100)  * sharePercentage ;
    var treasurySharesCurrentQuarter = (financialSurvey.shareholderEquity.treasurySharesCurrentQuarter /100)  * sharePercentage ;
    var headOfficeAccountInBranchCurrentQuarter = (financialSurvey.shareholderEquity.headOfficeAccountInBranchCurrentQuarter /100)  * sharePercentage ;
    var shareholderEquityOthersCurrentQuarter = (financialSurvey.shareholderEquity.shareholderEquityOthersCurrentQuarter /100)  * sharePercentage ;
    var minorityRightsCurrentQuarter = (financialSurvey.shareholderEquity.minorityRightsCurrentQuarter /100)  * sharePercentage ;
    var totalShareholderEquityCurrentQuarter = (financialSurvey.shareholderEquity.totalShareholderEquityCurrentQuarter /100)  * sharePercentage ;

    $('#shareholderRetainedEarningsIncludeCurrentQuarterId').val(Math.ceil(retainedEarningsIncludeCurrentQuarter));
    $('#shareholderAdditionalPaidUpCapitalCurrentQuarterId').val(Math.ceil(additionalPaidUpCapitalCurrentQuarter));
    $('#shareholderCapitalId').val(Math.ceil((paidUpCapitalCurrentQuarter)));
    $('#shareholderProfitLossQuarterCurrentQuarterId').val(Math.ceil(profitLossQuarterCurrentQuarter));
    $('#shareholderTotalReservesCurrentQuarterId').val(Math.ceil(totalReservesCurrentQuarter));
    $('#shareholderTreasurySharesCurrentQuarterId').val(Math.ceil(treasurySharesCurrentQuarter));
    $('#shareholderHeadOfficeAccountInBranchCurrentQuarterId').val(Math.ceil(headOfficeAccountInBranchCurrentQuarter));
    $('#shareholderShareholderEquityOthersCurrentQuarterId').val(Math.ceil(shareholderEquityOthersCurrentQuarter));
    $('#shareholderMinorityRightsCurrentQuarterId').val(Math.ceil(minorityRightsCurrentQuarter));
    $('#shareholderTotalShareholderEquityCurrentQuarterId').val(Math.ceil(totalShareholderEquityCurrentQuarter));


    var shareholderIsVotingPower = $('#shareholderIsVotingPowerId').is(':checked');
    if (!shareholderIsVotingPower ) {
        $('#shareholderVotingPowerId').val(sharePercentage);
    }
}


$(document).ready(function () {



    if ($('body').hasClass("page-financial-survey")) {

        /* NEEDS TO BE IMPROVED TEMP WORKAROUND TO DISABLE HEADS NAVIGATION*/
        document.getElementById("tab1").style["cursor"] = "default";
        document.getElementById("tab1").style["pointer-events"] = "none";
        document.getElementById("tab2").style["cursor"] = "default";
        document.getElementById("tab2").style["pointer-events"] = "none";
        document.getElementById("tab3").style["cursor"] = "default";
        document.getElementById("tab3").style["pointer-events"] = "none";
        document.getElementById("tab4").style["cursor"] = "default";
        document.getElementById("tab4").style["pointer-events"] = "none";
        document.getElementById("tab5").style["cursor"] = "default";
        document.getElementById("tab5").style["pointer-events"] = "none";




        $(document).on("click", "#cancelTabSubsidiariesBtnId", function () {
            $('a[href="#accessibletabscontent0-0"]').click();
        });
        $(document).on("click", "#cancelTabShareholdersEquityBtnId", function () {
            $('a[href="#accessibletabscontent0-1"]').click();
        });
        $(document).on("click", "#cancelTabShareholdersBtnId", function () {
            $('a[href="#accessibletabscontent0-2"]').click();
        });
        $(document).on("click", "#cancelSubmit", function () {
            $('a[href="#accessibletabscontent0-3"]').click();
        });






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



        validateNumberFloatInput ($("#comppanyPaidUpCapitalCurrentQuarterId"));
        validateNumberFloatInput ($("#paidUpCapitalCurrentQuarterId"));
        validateNumberFloatInput ($("#paidUpCapitalPreviousQuarterId"));
        validateNumberFloatInput ($("#additionalPaidUpCapitalCurrentQuarterId"));
        validateNumberFloatInput ($("#additionalPaidUpCapitalPreviousQuarterId"));

        $("#retainedEarningsIncludeCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#retainedEarningsIncludeCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#retainedEarningsIncludePreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#retainedEarningsIncludePreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#profitLossQuarterCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#profitLossQuarterCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#profitLossQuarterPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#profitLossQuarterPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#totalReservesCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#totalReservesCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#totalReservesPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#totalReservesPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        validateNumberFloatInput ($("#treasurySharesCurrentQuarterId"));
        validateNumberFloatInput ($("#treasurySharesPreviousQuarterId"));


        $("#headOfficeAccountInBranchCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#headOfficeAccountInBranchCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#headOfficeAccountInBranchPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#headOfficeAccountInBranchPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });


        $("#shareholderEquityOthersCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderEquityOthersCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderEquityOthersPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderEquityOthersPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#minorityRightsCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#minorityRightsCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#minorityRightsPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#minorityRightsPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });


        $("#totalShareholderEquityCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#totalShareholderEquityCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });

        $("#totalShareholderEquityPreviousQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#totalShareholderEquityPreviousQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });


        validateNumberFloatInput ($("#shareholderCapitalId"));
        validateNumberFloatInput ($("#shareholderAdditionalPaidUpCapitalCurrentQuarterId"));

        $("#shareholderRetainedEarningsIncludeCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderRetainedEarningsIncludeCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderProfitLossQuarterCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderProfitLossQuarterCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderTotalReservesCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderTotalReservesCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderHeadOfficeAccountInBranchCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderHeadOfficeAccountInBranchCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderShareholderEquityOthersCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderShareholderEquityOthersCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderMinorityRightsCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 45 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
            if ( $("#shareholderMinorityRightsCurrentQuarterId").val() != '' && e.which == 45 ) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderPercentageId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            //if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //    //display error message
            //    $("#errmsg").html("Digits Only").show().fadeOut("slow");
            //    return false;
            //}

            var regex = new RegExp("^[0-9\.]$");
            // var regexMulti = new RegExp("^[\d]+\.?[\d]*$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            } else {
                var hasDot = $(this).val().indexOf('.') >= 0;
                if (key === '.' && hasDot || key === '.' && $(this).val().length === 3) {
                    event.preventDefault();
                    return false;
                }

                var $self = $(this);
                var oldValue = $self.val();

                setTimeout(function () {
                    if (key !== '.' && hasDot) {
                        var value = $self.val().split('.');

                        if (value[0].length > 2 || value[1].length > 2) {
                            $self.val(oldValue);
                        }
                    }

                    if (key !== '.' && !hasDot) {
                        var value = $self.val();

                        if (value.length >= 3 && value > 100) {
                            $self.val(oldValue);
                        }
                    }
                }, 0);
            }

        });
        validateNumberFloatInput ($("#shareholderTreasurySharesCurrentQuarterId"));

        $("#shareholderTotalShareholderEquityCurrentQuarterId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderVotingPowerId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        $("#shareholderPreferredSharesId").keypress(function (e) {
            //if the letter is not digit then display error and don't type anything
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                //display error message
                $("#errmsg").html("Digits Only").show().fadeOut("slow");
                return false;
            }
        });
        validateNumberFloatInput ($("#shareholderValueOfReverseInvestmentId"));
        validateNumberFloatInput ($("#tradeDebitCurrentQuarterId"));
        validateNumberFloatInput ($("#loansAssetsCurrentQuarterId"));
        validateNumberFloatInput ($("#interestReceivedCurrentQuarterId"));
        validateNumberFloatInput ($("#dividendsReceivedCurrentQuarterId"));
        validateNumberFloatInput ($("#expensesReceivedCurrentQuarterId"));
        validateNumberFloatInput ($("#sellProductionSuppliesCurrentQuarterId"));
        validateNumberFloatInput ($("#currentDebitAccountCurrentQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionReceivableCurrentQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionReceivableCurrentQuarterId"));
        validateNumberFloatInput ($("#otherDebitCurrentQuarterId"));
        validateNumberFloatInput ($("#totalDebitCurrentQuarterId"));
        validateNumberFloatInput ($("#tradeCreditCurrentQuarterId"));
        validateNumberFloatInput ($("#loansLiabilitiesCurrentQuarterId"));
        validateNumberFloatInput ($("#interestPayableCurrentQuarterId"));
        validateNumberFloatInput ($("#dividendsPaidCurrentQuarterId"));
        validateNumberFloatInput ($("#expensesPaidCurrentQuarterId"));
        validateNumberFloatInput ($("#purchaseProductionSuppliesCurrentQuarterId"));
        validateNumberFloatInput ($("#purchaseMachineryCurrentQuarterId"));
        validateNumberFloatInput ($("#currentCreditAccountCurrentQuarterId"));
        validateNumberFloatInput ($("#expensesPayableCurrentQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionPayableCurrentQuarterId"));
        validateNumberFloatInput ($("#otherCreditCurrentQuarterId"));
        validateNumberFloatInput ($("#totalCreditCurrentQuarterId"));
        validateNumberFloatInput ($("#sellMachineryCurrentQuarterId"));
        validateNumberFloatInput ($("#expensesReceivableCurrentQuarterId"));


        validateNumberFloatInput ($("#tradeDebitPreviousQuarterId"));
        validateNumberFloatInput ($("#loansAssetsPreviousQuarterId"));
        validateNumberFloatInput ($("#interestReceivedPreviousQuarterId"));
        validateNumberFloatInput ($("#dividendsReceivedPreviousQuarterId"));
        validateNumberFloatInput ($("#expensesReceivedPreviousQuarterId"));
        validateNumberFloatInput ($("#sellProductionSuppliesPreviousQuarterId"));
        validateNumberFloatInput ($("#currentDebitAccountPreviousQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionReceivablePreviousQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionReceivablePreviousQuarterId"));
        validateNumberFloatInput ($("#otherDebitPreviousQuarterId"));
        validateNumberFloatInput ($("#totalDebitPreviousQuarterId"));
        validateNumberFloatInput ($("#tradeCreditPreviousQuarterId"));
        validateNumberFloatInput ($("#loansLiabilitiesPreviousQuarterId"));
        validateNumberFloatInput ($("#interestPayablePreviousQuarterId"));
        validateNumberFloatInput ($("#dividendsPaidPreviousQuarterId"));
        validateNumberFloatInput ($("#expensesPaidPreviousQuarterId"));
        validateNumberFloatInput ($("#purchaseProductionSuppliesPreviousQuarterId"));
        validateNumberFloatInput ($("#purchaseMachineryPreviousQuarterId"));
        validateNumberFloatInput ($("#currentCreditAccountPreviousQuarterId"));
        validateNumberFloatInput ($("#expensesPayablePreviousQuarterId"));
        validateNumberFloatInput ($("#insuranceCommissionPayablePreviousQuarterId"));
        validateNumberFloatInput ($("#otherCreditPreviousQuarterId"));
        validateNumberFloatInput ($("#totalCreditPreviousQuarterId"));
        validateNumberFloatInput ($("#sellMachineryPreviousQuarterId"));
        validateNumberFloatInput ($("#expensesReceivablePreviousQuarterId"));




        $("input[name='consolidatedStandloneRadioBox']").click(function () {
            if ($(this).val() === "true") {
                $('#subsidiarySection').hide();
                financialSurvey.isConsolidated = false;

            } else {
                $('#subsidiarySection').show();
                financialSurvey.isConsolidated = true;
            }
        });

        $("input[name='sacaleLevelRadioBox']").click(function () {
            if ($(this).val() === "true") {
                financialSurvey.isScaleLevelActualUnit = true;
            }  else {
                financialSurvey.isScaleLevelActualUnit = false;
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
        getDropDownsData();
        //getFinancialSurveyData();
        setupBranchesTable();
        setupSubsidiariesTable();
        SAGIA.formElements.placeholderPolyfill();

        $("#branchRegionId").change(updateCities);
        $("#subsidiaryRegionId").change(updateCities);
        $("#sectionId").change(updateDivisions);
        $("#divisionId").change(updateGroups);
        $("#groupId").change(updateClass);
        $("#classId").change(updateBranch);
        $("#branchId").change(updateActivity);



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

                $('#requestErrorDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                }).find('.globalMessage-msg').text(getI18nText("financial.survey.mandatory.fields.survey"));

                return;
            }
            submitFinancialSurveyCompanyProfile();
        });

        $(document).on("click", "#nextTabSubsidiariesBtnId", function () {
            /*var validator = entityValidator();
            if (!validator.form()) {
                $('a[href="#accessibletabscontent0-1"]').click();
                return;
            }*/
            submitFinancialSurveyBrnachesAndSubsidiaries();
        });

        $(document).on("click", "#nextTabShareholdersEquityBtnId", function () {
            var validator = equityValidator();
            if (!validator.form()) {

                $('#requestErrorDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                }).find('.globalMessage-msg').text(getI18nText("financial.survey.mandatory.fields.survey"));

              //  $('a[href="#accessibletabscontent0-2"]').click();
                return;
            }else {
                caluculateTotalCapital();
                caluculateTotalCapitalPreviousQuarter();
                submitFinancialSurveyEquity();
            }



        });

        $(document).on("click", "#nextTabShareholdersBtnId", function () {
            submitFinancialSurveyShareholders();
        });



        /*// Cancel amendment
        $(document).on("click", ".cancelAmendmentBtn", function () {

                window.location.href = ACC.config.encodedContextPath + "/dashboard";

        });
*/
        $(document).on("click", "#dismissChangesBtnId, .showHistoryBtn", function () {
           // window.location.href = ACC.config.encodedContextPath + "/dashboard";
            $('#requestErrorDialogId').modal('hide');
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
               // updateIsic(data);
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
                getFinancialSurveyData();

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


var validateBranchesSumPercentage = function () {
    var sum = 0;
    if (financialSurvey.branches.length == 0 ){
           return;
    }
    financialSurvey.branches.forEach(function(branch) {
        if(branch.volumeWeight != null && branch.action != null && branch.action !== '03') {
            sum = Math.round((sum + Number(branch.volumeWeight)) * 1e12) / 1e12 ;
        }
    });
    if (sum !== 100) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('validation.sharePerc.sum'));
       // $('a[href="#accessibletabscontent0-1"]').click();
        return;
    }

}


var validateNumberFloatInput = function (element)  {
    element.on('keypress', function (event) {
        var regex = new RegExp("^[0-9\.]$");
        // var regexMulti = new RegExp("^[\d]+\.?[\d]*$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

        if (!regex.test(key)) {

            event.preventDefault();
            //$("#errmsg").html("Digits Only").show().fadeOut("slow");
            return false;
        } else {
            var hasDot = $(this).val().indexOf('.') >= 0;

            if (key === '.' && hasDot) {
                event.preventDefault();
                return false;
            }
            else  if (hasDot) {
                var value = $(this).val().split('.');
                if ( value[1].length > 2) {
                    event.preventDefault();
                    return false;
                }

            }
        }
    });

    element.on('paste', function () {
        var $self = $(this)
        setTimeout(function () {
            $self.val($self.val().replace(/[^0-9.]+/g, ""));
            var dotCount = $self.val().replace(/[^.]/g, "").length;

            if (dotCount > 1) {
                while (dotCount > 1) {
                    $self.val($self.val().replace(/[.]/, ""));
                    dotCount--;
                }
            }
        }, 0);
    })

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
    $('#totalShareholderEquityCurrentQuarterId').val(shareholderEquity.totalShareholderEquityCurrentQuarter);
}

function fillShareholderEquityPrevQuarterData( shareholderEquity ) {
    $('#paidUpCapitalPreviousQuarterId').val(shareholderEquity.paidUpCapitalPreviousQuarter);
    $('#additionalPaidUpCapitalPreviousQuarterId').val(shareholderEquity.additionalPaidUpCapitalPreviousQuarter);
    $('#retainedEarningsIncludePreviousQuarterId').val(shareholderEquity.retainedEarningsIncludePreviousQuarter);
    $('#profitLossQuarterPreviousQuarterId').val(shareholderEquity.profitLossQuarterPreviousQuarter);
    $('#totalReservesPreviousQuarterId').val(shareholderEquity.totalReservesPreviousQuarter);
    $('#treasurySharesPreviousQuarterId').val(shareholderEquity.treasurySharesPreviousQuarter);
    $('#headOfficeAccountInBranchPreviousQuarterId').val(shareholderEquity.headOfficeAccountInBranchPreviousQuarter);
    $('#shareholderEquityOthersPreviousQuarterId').val(shareholderEquity.shareholderEquityOthersPreviousQuarter);
    $('#minorityRightsPreviousQuarterId').val(shareholderEquity.minorityRightsPreviousQuarter);
    $('#totalShareholderEquityPreviousQuarterId').val(shareholderEquity.totalShareholderEquityPreviousQuarter);
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
     financialSurvey.shareholderEquity.totalShareholderEquityCurrentQuarter = $('#totalShareholderEquityCurrentQuarterId').val();
    financialSurvey.shareholderEquity.totalShareholderEquityPreviousQuarter = $('#totalShareholderEquityPreviousQuarterId').val();
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
    financialSurvey.companyProfile.legalStatus = $('#legalStatusId').val();
    financialSurvey.companyProfile.financeManagerTelephone = $('#financeManagerTelephoneId').val();
    financialSurvey.companyStatus = $('#companyStatusId').val();
    financialSurvey.suspensionDate = $('#suspensionDateId').val();
    financialSurvey.disclosureCurrency = $('#disclosureCurrencyId').val();


    financialSurvey.economicActivitySection = $('#sectionId').val();
    financialSurvey.economicActivityDivision = $('#divisionId').val();
    financialSurvey.economicActivityGroup = $('#groupId').val();
    financialSurvey.economicActivityClass = $('#classId').val();
    financialSurvey.economicActivityBranch = $('#branchId').val();
    financialSurvey.economicActivity = $('#activityId').val();


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


var submitFinancialSurveyShareholders = function () {


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
    }

    //Voting Sum
    var sumVoting = 0;
    financialSurvey.shareholders.forEach(function(shareholder) {
        if(shareholder.shareholderPercentage != null && shareholder.action != null && shareholder.action !== '03') {
            sumVoting = Math.round((sumVoting + Number(shareholder.shareholderVotingPower)) * 1e12) / 1e12 ;
        }
    });
    if (sumVoting !== 100) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('validation.votingPerc.sum'));
        return;
    }

    // End Voting Sum

    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/saveShareholders", {
        method: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(financialSurvey),
        success: function () {
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
                $('a[href="#accessibletabscontent0-4"]').click();

                SAGIA.financialSurvey.isShareholdersSectionFilled = true;
                return
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

};
var submitFinancialSurveyBrnachesAndSubsidiaries = function () {

    var sum = 0;
    var numberOfBranches = 0;

    financialSurvey.branches.forEach(function(branch) {
        if(branch.volumeWeight != null  && branch.action !== '03') {
            sum = Math.round((sum + Number(branch.volumeWeight)) * 1e12) / 1e12 ;
            numberOfBranches ++;
        }
    });
    if (sum !== 100 && numberOfBranches !== 0 ) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('finance.survey.validation.branchPerc.sum'));
        return;
    }

    var numberOfSubsidiaries = 0;
    financialSurvey.subsidiaries.forEach(function(subsidiary) {
        if( subsidiary.action !== '03') {
            numberOfSubsidiaries ++;
        }
    });


    if (numberOfSubsidiaries == 0 &&  financialSurvey.isConsolidated ){
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('finance.survey.validation.subsidiaries'));
        return;
    }

    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/saveBrnachesAndSubsidiaries", {
        method: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(financialSurvey),
        success: function () {
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
                $('a[href="#accessibletabscontent0-2"]').click();
                SAGIA.financialSurvey.isBranchSectionFilled = true ;
                return
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
};

var submitFinancialSurveyCompanyProfile = function () {

   /* if (SAGIA.financialSurvey.businessActivities.selectedActivities.length == 0) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('financial.survey.validation.businessActivities'));
        return;
    }*/

    populateCompanyProfile();
    /*var selectedActivities = SAGIA.financialSurvey.businessActivities.selectedActivities;
    var selectedActivitiesIds = [];
    selectedActivities.forEach(function(activity) {
        selectedActivitiesIds.push(activity.activityId);
    });

    financialSurvey.businessActivities = [];
    selectedActivities.forEach(function (activity) {
        financialSurvey.businessActivities.push({
            id: activity.activityId,
            description: activity.description
        });
    });*/

   // var timerId = setInterval(function () {
        var token = $('input[name="CSRFToken"]').attr('value');
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/saveCompanyProfile", {
            method: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', token);
            },
            data: JSON.stringify(financialSurvey),
            success: function () {
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
                    $('a[href="#accessibletabscontent0-1"]').click();

                    SAGIA.financialSurvey.isCompanyProfileSectionFilled = true;
                    return
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
   // }, 1000);
};



var submitFinancialSurveyEquity = function () {

    populateShareholderEquity();

    // var timerId = setInterval(function () {
    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/saveShareholderEquity", {
        method: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(financialSurvey),
        success: function () {
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
                $('a[href="#accessibletabscontent0-3"]').click();
                SAGIA.financialSurvey.isEquitySectionFilled = true ;
                return
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
    // }, 1000);
};



function setLicenseData(data, history) {
    financialSurvey = data;


    SAGIA.financialSurvey.isCompanyProfileSectionFilled = financialSurvey.isCompanyProfileSectionFilled;
    SAGIA.financialSurvey.isBranchSectionFilled = financialSurvey.isBranchSectionFilled ;
    SAGIA.financialSurvey.isEquitySectionFilled = financialSurvey.isEquitySectionFilled ;
    SAGIA.financialSurvey.isShareholdersSectionFilled = financialSurvey.isShareholdersSectionFilled;

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
    $('#comppanyPaidUpCapitalCurrentQuarterId').val(financialSurvey.paidUpCapitalCurrentQuarter);

    fillShareholderEquityData(financialSurvey.shareholderEquity);
    fillShareholderEquityPrevQuarterData(financialSurvey.shareholderEquity);

    updateDropDown('#companyStatusId', financialSurvey.companyStatus);
    updateDropDown('#disclosureCurrencyId', financialSurvey.disclosureCurrency);

     updateDropDownwithoutTriggerChange('#sectionId', financialSurvey.economicActivitySection);
     loadIsicValues(financialSurvey.economicActivityDivision,financialSurvey.economicActivityGroup, financialSurvey.economicActivityClass, financialSurvey.economicActivityBranch, financialSurvey.economicActivity);


    $('#suspensionDateId').val(financialSurvey.suspensionDate);

    updateDropDown('#legalStatusId', companyProfile.legalStatus);
    if (financialSurvey.isConsolidated) {
        $("#standloneId").prop('checked',false);
        $("#consolidatedId").prop('checked',true);
        $('#subsidiarySection').show();
    }else {
        $("#standloneId").prop('checked',true);
        $("#consolidatedId").prop('checked',false);
        $('#subsidiarySection').hide();
    }

    if (financialSurvey.isScaleLevelActualUnit) {
        $("#actualUnitId").prop('checked',true);
        $("#thousandsId").prop('checked',false);
    }else {
        $("#actualUnitId").prop('checked',false);
        $("#thousandsId").prop('checked',true);
    }


    var DELETE_ACTION = '03';


    var $shareholderRowTemplate = $('.shareholderTemplate').first().clone(true);
    var $shareholdersTable = $('#shareholdersId').empty().append($shareholderRowTemplate.clone(true).hide());
    financialSurvey.shareholders.forEach(function (shareholder) {
        if (shareholder.action !== DELETE_ACTION) {
            var name = shareholder.shareholderNameEnglish;
            var type = shareholder.shareholderType === '1' ? getI18nText("general.individual") : getI18nText("general.entity");
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
            var type = affiliate.affiliateType === '1' ? getI18nText("general.individual") : getI18nText("general.entity");


            var $affiliateRow = $affiliateRowTemplate.clone(true).show();
            $affiliateRow.attr("id", affiliate.srId ? affiliate.srId : affiliate.newItemId).children().first().text(name).next().text(type);
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
            var volumeWeight = branch.volumeWeight;
            var city = branch.address.cityDescription;
            var addrNumber = branch.addrNumber;
            var $branchRow = $branchRowTemplate.clone(true).show();
            $branchRow.attr("id", branch.srId).children().first().html(typeDescription).next().text(name).next().text(city).next().text(volumeWeight);
            setColorForDraftRow($branchRow, branch.action);

          //  if (branch.main) { // Main branch, can edit, can't be removed
                    $branchRow.find('.viewBranchBtn').show();
                    $branchRow.find('.editBranchBtn').show();
                    $branchRow.find('.deleteDropdown').show();
          //      } else { // Can be removed
         //           $branchRow.find('.viewBranchBtn').show();
         //           $branchRow.find('.editBranchBtn').hide();
        //            $branchRow.find('.deleteDropdown').show();
        //    }


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
            var dataIncludedInHeadOffice = subsidiary.dataIncludedInHeadOffice;
            var dataIncludedInHeadOfficeDisplay = getI18nText("finance.survey.subsidiary.dataIncludedInHeadOffice.no");
            if(dataIncludedInHeadOffice == 'true'){
                dataIncludedInHeadOfficeDisplay = getI18nText("finance.survey.subsidiary.dataIncludedInHeadOffice.yes");
            }

            var $subsidiaryRow = $subsidiaryRowTemplate.clone(true).show();
            $subsidiaryRow.attr("id", subsidiary.srId).children().first().html(name).next().text(registrationNumber).next().text(dataIncludedInHeadOfficeDisplay);
            setColorForDraftRow($subsidiaryRow, subsidiary.action);
            $subsidiaryRow.find('.editSubsidiaryBtn').show();
            $subsidiaryRow.find('.deleteDropdown').show();

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
    var nationalityOfUCPId = $('#nationalityOfUCPId').append(new Option('', '', false, false));

    data.countries.forEach(function (country) {
        $currentNationalitySelect.append(new Option(country.name, country.id, false, false));
        affiliateCompanyCountry.append(new Option(country.name, country.id, false, false));
        $previousNationalitySelect.append(new Option(country.name, country.id, false, false));
        $shareholderCountrySelect.append(new Option(country.name, country.id, false, false));
        $affiliateCurrentNationalitySelect.append(new Option(country.name, country.id, false, false));
        $affiliatePreviousNationalitySelect.append(new Option(country.name, country.id, false, false));
        $affiliateCountrySelect.append(new Option(country.name, country.id, false, false));
        $companyCountrySelect.append(new Option(country.name, country.id, false, false));
        nationalityOfUCPId.append(new Option(country.name, country.id, false, false));
    });

    var $sectorSelect = $('#shareholderSectorId').append(new Option('', '', false, false));
    var $affiliateSectorSelect = $('#affiliateSectorId').append(new Option('', '', false, false));
    data.sectors.forEach(function (sector) {
        $sectorSelect.append(new Option(sector.name, sector.id, false, false));
        $affiliateSectorSelect.append(new Option(sector.name, sector.id, false, false));
    });

    //var $legalStatusSelect = $('#shareholderLegalStatusId').append(new Option('', '', false, false));
    var $affiliateLegalStatusSelect = $('#affiliateLegalStatusId').append(new Option('', '', false, false));
    var $legalStatusSelect = $('#legalStatusId').append(new Option('', '', false, false));

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


    var $sectionSelect = $('#sectionId').append(new Option('', '', false, false));
    data.sections.forEach(function (section) {
        $sectionSelect.append(new Option(section.id.concat(" : ", section.name), section.id, false, false));


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

var loadIsicValues = function (selectedDivision,selectedGroup,selectedClass, selectedBranch, selectedActivity) {



    var selectedSectionId = $('#sectionId option:selected').val();

    var $divisionIdSelect = $('#divisionId').empty();
    $divisionIdSelect.append(new Option('', '', false, false));

    if (selectedSectionId !== ""  ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicDivisionsQDF/na/" + selectedSectionId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                $divisionIdSelect.find("option").remove();
                $divisionIdSelect.append(new Option("", "", false, false));
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $divisionIdSelect.append(new Option(currentValue.divisionNumber.concat(" : ", currentValue.divisionDescription) , currentValue.divisionNumber, false, false));
                    });
                }

                if(selectedDivision !== null ){
                    //updateDrop('#divisionId', updateDropDown);
                    updateDropDownwithoutTriggerChange('#divisionId', selectedDivision);
                    loadGroups(selectedDivision,selectedGroup,selectedClass, selectedBranch, selectedActivity);

                }
            }
        });
    }


};

var loadGroups = function (selectedDivision, selectedGroup, selectedClass ,selectedBranch, selectedActivity) {


    var $groupIdSelect = $('#groupId').empty();
    $groupIdSelect.append(new Option('', '', false, false));

    if (selectedDivision !== ""  ) {
        $.ajax(ACC.config.encodedContextPath +"/my-sagia/license/isicGroup/" + selectedDivision, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                $groupIdSelect.find("option").remove();
                $groupIdSelect.append(new Option("", "", false, false));

                if(jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $groupIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }

                if(selectedGroup !== null ){

                    updateDropDownwithoutTriggerChange('#groupId', selectedGroup);

                    loadClass(selectedGroup, selectedClass, selectedBranch, selectedActivity);
                }
            }
        });
    }

};

var loadClass = function (selectedGroup, selectedClass, selectedBranch, selectedActivity) {



    var $classIdSelect = $('#classId').empty();
    $classIdSelect.append(new Option('', '', false, false));

    if (selectedGroup !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicClass/" + selectedGroup, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $classIdSelect.find("option").remove();
                $classIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $classIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }

                if(selectedClass !== null ){

                    updateDropDownwithoutTriggerChange('#classId', selectedClass);
                    loadBranch(selectedClass, selectedBranch, selectedActivity);
                }
            }
        });
    }

};



var loadBranch = function (selectedClass, selectedBranch, selectedActivity) {



    var $branchIdSelect = $('#branchId').empty();
    $branchIdSelect.append(new Option('', '', false, false));

    if (selectedClass !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicBranch/" + selectedClass, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $branchIdSelect.find("option").remove();
                $branchIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $branchIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }

                if(selectedBranch !== null ){

                    updateDropDownwithoutTriggerChange('#branchId', selectedBranch);
                    loadActivity(selectedBranch, selectedActivity);
                }
            }
        });
    }

};



var loadActivity = function (selectedBranch, selectedActivity) {


    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    if (selectedBranch !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicActivity/" + selectedBranch, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $activityIdSelect.find("option").remove();
                $activityIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $activityIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }

                if(selectedActivity !== null ){

                    updateDropDownwithoutTriggerChange('#activityId', selectedActivity);
                }
            }
        });
    }

};


var updateDivisions = function () {
    var selectedSectionId = $('#sectionId option:selected').val();


    var $divisionIdSelect = $('#divisionId').empty();
    $divisionIdSelect.append(new Option('', '', false, false));

    // Void next levels
    var $groupIdSelect = $('#groupId').empty();
    $groupIdSelect.append(new Option('', '', false, false));

    var $classIdSelect = $('#classId').empty();
    $classIdSelect.append(new Option('', '', false, false));

    var $branchIdSelect = $('#branchId').empty();
    $branchIdSelect.append(new Option('', '', false, false));

    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    // End void Group

    if (selectedSectionId !== ""  ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicDivisionsQDF/na/" + selectedSectionId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                $divisionIdSelect.find("option").remove();
                $divisionIdSelect.append(new Option("", "", false, false));
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $divisionIdSelect.append(new Option(currentValue.divisionNumber.concat(" : ", currentValue.divisionDescription), currentValue.divisionNumber, false, false));
                    });
                }


            }
        });
    }

};


var updateGroups = function () {
    var selectedDivisionId = $('#divisionId option:selected').val();

    var $groupIdSelect = $('#groupId').empty();
    $groupIdSelect.append(new Option('', '', false, false));


    // Void next levels

    var $classIdSelect = $('#classId').empty();
    $classIdSelect.append(new Option('', '', false, false));

    var $branchIdSelect = $('#branchId').empty();
    $branchIdSelect.append(new Option('', '', false, false));

    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    // End void Group

    if (selectedDivisionId !== ""  ) {
        $.ajax(ACC.config.encodedContextPath +"/my-sagia/license/isicGroup/" + selectedDivisionId, {
        type: "GET",
        responseType: "application/json;charset=utf-8",
        contentType: "application/json;charset=utf-8",
        cache: false,
        success: function (data) {
            var jsonData = JSON.parse(data);
            $groupIdSelect.find("option").remove();
            $groupIdSelect.append(new Option("", "", false, false));

            if(jsonData !== null) {
                   jsonData.forEach(function (currentValue) {
                    $groupIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                });
            }
        }
    });
    }

};


var updateClass = function () {
    var selectedGroupId = $('#groupId option:selected').val();

    var $classIdSelect = $('#classId').empty();
    $classIdSelect.append(new Option('', '', false, false));


    // Void next levels

    var $branchIdSelect = $('#branchId').empty();
    $branchIdSelect.append(new Option('', '', false, false));

    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    // End void Group

    if (selectedGroupId !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicClass/" + selectedGroupId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $classIdSelect.find("option").remove();
                $classIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $classIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }
            }
        });
    }

};


var updateBranch = function () {
    var selectedClassId = $('#classId option:selected').val();

    var $branchIdSelect = $('#branchId').empty();
    $branchIdSelect.append(new Option('', '', false, false));


    // Void next levels

    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    // End void Group

    if (selectedClassId !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicBranch/" + selectedClassId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $branchIdSelect.find("option").remove();
                $branchIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $branchIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }
            }
        });
    }

};



var updateActivity = function () {
    var selectedBranchId = $('#branchId option:selected').val();

    var $activityIdSelect = $('#activityId').empty();
    $activityIdSelect.append(new Option('', '', false, false));

    if (selectedBranchId !== "" ) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/isicActivity/" + selectedBranchId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {

                $activityIdSelect.find("option").remove();
                $activityIdSelect.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                if (jsonData !== null) {
                    jsonData.forEach(function (currentValue) {
                        $activityIdSelect.append(new Option(currentValue.code.concat(" : ", currentValue.description), currentValue.code, false, false));
                    });
                }
            }
        });
    }

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












