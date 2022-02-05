function entityValidator() {

    return $("#entityFormId").validate({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: ":hidden",
        errorPlacement: function (error, element) {
            error.appendTo(element.closest('.formInputBox').find('.help-block'));
        },
        rules: {
            commercialRegistrationNo: {
                required: true,
                number: true,
                minlength: 10,
                maxlength: 10

            },
            unifiedNo700: {
                number: true,
                minlength: 10,
                maxlength: 10
            },
            companyName: {
                required: true,
            },
            disclosureCurrency: {
                required: true,
            },
            crIssueDate: {
                required: true,
            },
            suspensionDate: {
                required: true,
            },
            incorporationDate: {
                required: true,
            },
            paidUpCapitalCurrentQuarter: {
                required: true,
                number: true,
            },
            financeManagerName: {
                required: true,
            },
            financeManagerEmail: {
                required: true,
            },
            financeManagerTelephone: {
                required: true,
            }
        },

        messages: {
            commercialRegistrationNo: {
                minlength: getI18nText("validation.shareholder.delegate.idNumber.10digit"),
                required: getI18nText("validation.empty"),
                number: getI18nText("validation.shareholder.delegate.idNumber.10digit")
            },
            unifiedNo700: {
                number: getI18nText("validation.shareholder.delegate.idNumber.10digit"),
                exactlength: getI18nText("validation.shareholder.delegate.idNumber.10digit")
            },
            companyName: {
                required: getI18nText("validation.empty"),
            },
            disclosureCurrency: {
                required: getI18nText("validation.empty"),
            },
            crIssueDate: {
                required: getI18nText("validation.empty"),
            },
            companyName: {
                required: getI18nText("validation.empty"),
            },
            suspensionDate: {
                required: getI18nText("validation.empty"),
            },
            paidUpCapitalCurrentQuarter: {
                number: getI18nText("validation.number"),
                required: getI18nText("validation.empty"),
            },
            incorporationDate: {
                required: getI18nText("validation.empty"),
            }

        },
        success: function (label, element) {
            $('#' + element.getAttribute('id')).parent().removeClass('has-error');
        }
    });
}

function documentsValidator(documents) {
    var rules = {};
    var messages = {};

    documents.forEach( function(document) {
        rules[document] = "required";
        messages[document] = getI18nText("validation.attachment");
    });

    return $("#docsFormId").validate({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: [],
        errorPlacement: function errorPlacement(error, element) {
            error.insertAfter(element.nextAll('.js-inputFile-reset')).wrap( "<div class='help-block'></div>" );
        },
        errorElement: 'span',
        errorClass: 'help-block',
        rules: rules,
        messages: messages,
        success: function (label, element) {
            $('#' + element.getAttribute('id')).parent().removeClass('has-error');
        }
    });
}


function shareholderValidator() {


    return $("#shareholderFormId").validate({

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: ":hidden",
        errorElement: 'span',
        errorPlacement: function (error, element) {
            if (element.closest('.form-group').find('.help-block').length > 0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn') || element.hasClass('js-select2-search')) {
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },

        rules: {
            // bpNumber: "required",delegateCountry delegateNationality
            shareholderNameEnglish: "required",
            companyCountry: "required",
            shareholderMultinationalCompany: "required",
            shareholderGender: "required",
            shareholderNationalityCurrent: "required",
            shareholderCountry: "required",
            shareholderCapital: "required",

            shareholderPercentage: {
                required: true,
                number: true,
                range: [0, 100]
            },
        },

        messages: {
        	delegateIdentityNumberName: {
                required: getI18nText("validation.name")
            },

        	delegateDateOfBirthName: {
                required: getI18nText("validation.birthDate")
            },
        	delegatePostalCodeName: {
                required: getI18nText("validation.zipcode")
            },
        	delegatePoBoxName: {
                  required: getI18nText("validation.zipcode")
            },
            delegateCountryCodeForTelephoneName: {
                required: getI18nText("validation.telephone")
            },
            delegateTelephoneName: {
                required: getI18nText("validation.telephone")
            },
            delegateCountryCodeForMobileName: {
                required: getI18nText("validation.telephone")
            },
            delegateMobileName: {
                required: getI18nText("validation.telephone")
            },
        	delegateEmailName: {
                required: getI18nText("validation.email")
            },

        	delegateEmailName: {
                required: getI18nText("validation.email")
            },
            shareholderName: {
                required: getI18nText("validation.name")
            },
            shareholderNameEnglish: {
                required: getI18nText("validation.nameEnglish")
            },
            shareholderMultinationalCompany: {
                required: getI18nText("validation.multiNatCompany")
            },
            shareholderLegalStatus: {
                required: getI18nText("validation.legalStatus")
            },
            shareholderCapital: {
                required: getI18nText("validation.capital")
            },
            shareholderLabourSize: {
                required: getI18nText("validation.labor")
            },
            shareholderFirstName: {
                required: getI18nText("validation.firstName")
            },
            shareholderLastName: {
                required: getI18nText("validation.lastName")
            },
            shareholderAcademicTitle: {
                required: getI18nText("validation.academicTitle")
            },
            enquiryType: {
                required: getI18nText("validation.birthDate")
            },
            shareholderGender: {
                required: getI18nText("validation.gender")
            },
            shareholderMaritalStatus: {
                required: getI18nText("validation.marital")
            },
            shareholderPremiumResident: {
                required: getI18nText("validation.premium")
            },
            shareholderNationalityCurrent: {
                required: getI18nText("validation.nationality")
            },
            shareholderNationalityPrevious: {
                required: getI18nText("validation.previousNationality")
            },
            shareholderPercentage: {
                required: getI18nText("validation.sharePerc"),
                number: getI18nText("validation.sharePerc"),
                range: getI18nText("validation.sharePerc")
            },
            companyCountry: {
                required: getI18nText("validation.country")
            },
            delegateNationality: {
                required: getI18nText("validation.country")
            },
            delegateCountry: {
                required: getI18nText("validation.country")
            },
            shareholderStreet: {
                required: getI18nText("validation.street")
            },
            shareholderNumber: {
                required: getI18nText("validation.streetNumber")
            },
            shareholderCity: {
                required: getI18nText("validation.city")
            },
            shareholderZipCode: {
                required: getI18nText("validation.zipcode")
            },
            shareholderTelephone: {
                required: getI18nText("validation.telephone")
            },
            shareholderEmail: {
                required: getI18nText("validation.email")
            },
            shareholderWebsite: {
                required: getI18nText("validation.url")
            }
        }
    });
}



function affiliateValidator() {


    return $("#affiliateFormId").validate({

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: ":hidden",
        errorElement: 'span',
        errorPlacement: function (error, element) {
            if (element.closest('.form-group').find('.help-block').length > 0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn') || element.hasClass('js-select2-search')) {
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },

        rules: {
            // bpNumber: "required",delegateCountry delegateNationality
            affiliateNameEnglish: "required",
            companyCountry: "required",
            affiliateMultinationalCompany: "required",
            affiliateGender: "required",
            affiliateNationalityCurrent: "required",
            affiliateCountry: "required",
            affiliateCapital: "required",

            affiliatePercentage: {
                required: true,
                number: true,
                range: [0, 100]
            },
        },

        messages: {
            delegateIdentityNumberName: {
                required: getI18nText("validation.name")
            },

            delegateDateOfBirthName: {
                required: getI18nText("validation.birthDate")
            },
            delegatePostalCodeName: {
                required: getI18nText("validation.zipcode")
            },
            delegatePoBoxName: {
                required: getI18nText("validation.zipcode")
            },
            delegateCountryCodeForTelephoneName: {
                required: getI18nText("validation.telephone")
            },
            delegateTelephoneName: {
                required: getI18nText("validation.telephone")
            },
            delegateCountryCodeForMobileName: {
                required: getI18nText("validation.telephone")
            },
            delegateMobileName: {
                required: getI18nText("validation.telephone")
            },
            delegateEmailName: {
                required: getI18nText("validation.email")
            },

            delegateEmailName: {
                required: getI18nText("validation.email")
            },
            affiliateName: {
                required: getI18nText("validation.name")
            },
            affiliateNameEnglish: {
                required: getI18nText("validation.nameEnglish")
            },
            affiliateMultinationalCompany: {
                required: getI18nText("validation.multiNatCompany")
            },
            affiliateLegalStatus: {
                required: getI18nText("validation.legalStatus")
            },
            affiliateCapital: {
                required: getI18nText("validation.capital")
            },
            affiliateLabourSize: {
                required: getI18nText("validation.labor")
            },
            affiliateFirstName: {
                required: getI18nText("validation.firstName")
            },
            affiliateLastName: {
                required: getI18nText("validation.lastName")
            },
            affiliateAcademicTitle: {
                required: getI18nText("validation.academicTitle")
            },
            enquiryType: {
                required: getI18nText("validation.birthDate")
            },
            affiliateGender: {
                required: getI18nText("validation.gender")
            },
            affiliateMaritalStatus: {
                required: getI18nText("validation.marital")
            },
            affiliatePremiumResident: {
                required: getI18nText("validation.premium")
            },
            affiliateNationalityCurrent: {
                required: getI18nText("validation.nationality")
            },
            affiliateNationalityPrevious: {
                required: getI18nText("validation.previousNationality")
            },
            affiliatePercentage: {
                required: getI18nText("validation.sharePerc"),
                number: getI18nText("validation.sharePerc"),
                range: getI18nText("validation.sharePerc")
            },
            companyCountry: {
                required: getI18nText("validation.country")
            },
            delegateNationality: {
                required: getI18nText("validation.country")
            },
            delegateCountry: {
                required: getI18nText("validation.country")
            },
            affiliateStreet: {
                required: getI18nText("validation.street")
            },
            affiliateNumber: {
                required: getI18nText("validation.streetNumber")
            },
            affiliateCity: {
                required: getI18nText("validation.city")
            },
            affiliateZipCode: {
                required: getI18nText("validation.zipcode")
            },
            affiliateTelephone: {
                required: getI18nText("validation.telephone")
            },
            affiliateEmail: {
                required: getI18nText("validation.email")
            },
            affiliateWebsite: {
                required: getI18nText("validation.url")
            }
        }
    });
}


function subsidiaryValidator() {
    return $("#subsidiaryFormId").validate({

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: "",
        errorElement: 'span',
        errorPlacement: function (error, element) {
            if (element.closest('.form-group').find('.help-block').length > 0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn') || element.hasClass('js-select2-search')) {
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },

        rules: {
            subsidiaryType: "required",
            subsidiaryName: "required",
            subsidiaryStreet: "required",
            subsidiaryNumber: "required",
            subsidiaryRegion: "required",
            subsidiaryCity: "required",
            subsidiaryTelephone: "required",
            subsidiaryEmail: "required",
            subsidiaryWebsite: "required"
        },

        messages: {
            subsidiaryType: {
                required: getI18nText("validation.subsidiary")
            },
            subsidiaryName: {
                required: getI18nText("validation.subsidiaryName")
            },
            subsidiaryStreet: {
                required: getI18nText("validation.streetName")
            },
            subsidiaryNumber: {
                required: getI18nText("validation.streetNumber")
            },
            subsidiaryRegion: {
                required: getI18nText("validation.region")
            },
            subsidiaryCity: {
                required: getI18nText("validation.city")
            },
            subsidiaryTelephone: {
                required: getI18nText("validation.telephoneNumber")
            },
            subsidiaryEmail: {
                required: getI18nText("validation.email")
            },
            subsidiaryWebsite: {
                required: getI18nText("validation.url")
            }
        }
    });
}



function branchValidator() {
    return $("#branchFormId").validate({

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: "",
        errorElement: 'span',
        errorPlacement: function (error, element) {
            if (element.closest('.form-group').find('.help-block').length > 0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn') || element.hasClass('js-select2-search')) {
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },

        rules: {
            branchType: "required",
            branchName: "required",
            branchRegion: "required",
            branchCity: "required",
            branchTelephone: "required",
            branchEmail: "required",
            branchWebsite: "required",
            volumeWeight: {
                required: true,
                number: true,
                range: [0, 100]
            },
        },

        messages: {
            branchType: {
                required: getI18nText("validation.branch")
            },
            branchName: {
                required: getI18nText("validation.branchName")
            },
            branchRegion: {
                required: getI18nText("validation.region")
            },
            branchCity: {
                required: getI18nText("validation.city")
            },
            branchTelephone: {
                required: getI18nText("validation.telephoneNumber")
            },
            branchEmail: {
                required: getI18nText("validation.email")
            },
            branchWebsite: {
                required: getI18nText("validation.url")
            },
            volumeWeight: {
                required: getI18nText("validation.sharePerc"),
                number: getI18nText("validation.sharePerc"),
                range: getI18nText("validation.sharePerc")
            },

        }
    });
}


function equityValidator() {

    return $("#equityFormId").validate({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: ":hidden",
        errorPlacement: function (error, element) {
            error.appendTo(element.closest('.formInputBox').find('.help-block'));
        },
        rules: {
            paidUpCapitalCurrentQuarter: {
                number: true
            },
            additionalPaidUpCapitalCurrentQuarter: {
                number: true,
            },
            retainedEarningsIncludeCurrentQuarter: {
                number: true,
            },
            profitLossQuarterCurrentQuarter: {
                number: true,
            },
            totalReservesCurrentQuarter: {
                number: true,
            },
            treasurySharesCurrentQuarter: {
                number: true,
                max: 0
            },
            headOfficeAccountInBranchCurrentQuarter: {
                number: true,
            },
            shareholderEquityOthersCurrentQuarter: {
                number: true,
            },
            minorityRightsCurrentQuarter: {
                number: true,
            },
            totalShareholderEquityCurrentQuarter: {
                number: true,
            },
        },

        messages: {
            paidUpCapitalCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            additionalPaidUpCapitalCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            retainedEarningsIncludeCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            profitLossQuarterCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            totalReservesCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            treasurySharesCurrentQuarter: {
                number: getI18nText("validation.number"),
                max: getI18nText("finance.survey.validation.number.negative")
            },
            headOfficeAccountInBranchCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            shareholderEquityOthersCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            minorityRightsCurrentQuarter: {
                number: getI18nText("validation.number")
            },
            totalShareholderEquityCurrentQuarter: {
                number: getI18nText("validation.number")
            }

        },
        success: function (label, element) {
            $('#' + element.getAttribute('id')).parent().removeClass('has-error');
        }
    });
}


function clearShareholderValidation() {
    var validator = shareholderValidator();
    $('[name]', '#shareholderFormId').each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}

function clearAffiliateValidation() {
    var validator = affiliateValidator();
    $('[name]', '#affiliateFormId').each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}

function clearBranchValidation() {
    var validator = branchValidator();
    $('[name]', '#branchFormId').each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}

function clearSubsidiaryValidation() {
    var validator = subsidiaryValidator();
    $('[name]', '#subsidiaryFormId').each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}




function clearProductValidation() {
    var validator = productValidator();
    $('[name]', '#productFormId').each(function () {
        validator.successList.push(this);
        validator.showErrors();
    });
    validator.resetForm();
    validator.reset();
}