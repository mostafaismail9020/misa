function entityValidator() {

    return $("#entityFormId").validate({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: [],
        errorPlacement: function (error, element) {
            error.appendTo(element.closest('.formInputBox').find('.help-block'));
        },
        rules: {
            capital: "digits",
            labour: "digits",
            entityName: {
                "required":true,
                arabic : true
            }
        },

        messages: {
            capital: {
                digits: getI18nText("validation.number")
            },
            labour: {
                digits: getI18nText("validation.number")
            },
            entityName: {
                required: getI18nText("validation.empty"),
                arabic: getI18nText("validation.company.name.arabic")
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

    //custom validation rule
    $.validator.addMethod("shareholderEmailAddress",
        function(value, element) {
            return /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(value);
        }, getI18nText("validation.basicinformation.email")
    );

    $.validator.addMethod("shareholderWebsiteUrl",
        function(value, element) {
            return  /^(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]{0,}\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]{0,}\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9]\.[^\s]{2,})$/i.test(value);
        }, getI18nText("register.website.invalid")
    );

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
        	delegateNationality: "required",
        	delegateCountry: "required",      	
        	delegateIssueDate: "required",
        	delegateExpiryDate: "required",
        	delegateDateOfBirthName: "required",
        	delegateIdentityNumberName: "required",
        	delegatePostalCodeName: "required",
        	delegatePoBoxName: "required",
        	delegateCountryCodeForTelephoneName: "required",
        	delegateTelephoneName: "required",
        	delegateCountryCodeForMobileName: "required",
        	delegateMobileName: "required",
        	delegateEmailName: {
                 "required":true,
                 shareholderEmailAddress : true
             },
            shareholderName: "required",
            shareholderNameEnglish: "required",
            shareholderSector: "required",
            shareholderSubsector: "required",
            shareholderMultinationalCompany: "required",
            shareholderLegalStatus: "required",
            shareholderCapital: "required",
            shareholderLabourSize: "required",
            shareholderFirstName: "required",
            shareholderLastName: "required",
            shareholderAcademicTitle: "required",
            enquiryType: "required",
            shareholderGender: "required",
            shareholderMaritalStatus: "required",
            shareholderPremiumResident: "required",
            shareholderNationalityCurrent: "required",
            shareholderNationalityPrevious: "required",
            shareholderPercentage: {
                required: true,
                number: true,
                range: [0, 100]
            },
            shareholderCountry: "required",
            shareholderStreet: "required",
            shareholderNumber: "required",
            shareholderCity: "required",
            shareholderZipCode: "required",
            shareholderTelephone: "required",
            shareholderEmail: {
                "required":true,
                shareholderEmailAddress : true
            },
            shareholderWebsite: {
                "required": true,
                shareholderWebsiteUrl : true
            }
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
            shareholderSector: {
                required: getI18nText("validation.sector")
            },
            shareholderSubsector: {
                required: getI18nText("validation.subsector")
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
            shareholderCountry: {
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
            branchStreet: "required",
            branchNumber: "required",
            branchRegion: "required",
            branchCity: "required",
            branchTelephone: "required",
            branchEmail: "required",
            branchWebsite: "required"
        },

        messages: {
            branchType: {
                required: getI18nText("validation.branch")
            },
            branchName: {
                required: getI18nText("validation.branchName")
            },
            branchStreet: {
                required: getI18nText("validation.streetName")
            },
            branchNumber: {
                required: getI18nText("validation.streetNumber")
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
            }
        }
    });
}

function productValidator() {
    $.validator.addMethod('minStrict', function (value, el, param) {
        return value > param;
    });
    return $("#productFormId").validate({

        highlight: function(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
        unhighlight: function(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            focusInvalid: false,
            ignore: "",
            errorElement: 'span',
            errorPlacement: function(error, element) {
               if (element.closest('.form-group').find('.help-block').length>0) {
                   error.appendTo(element.closest('.form-group').find('.help-block'));
               } else if (element.hasClass('js-select2-oneColumn')  || element.hasClass('js-select2-search')){
                   error.appendTo(element.closest('.formSelectBox').find('.help-block'));
               } else {
                   error.appendTo(element.closest('.formInputBox').find('.help-block'));
               }
            },
        rules: {
            productId: "required",
            productDescription: "required",
            productQuantity: {
                required: true,
                number: true,
                minStrict: 0
            },
            productUnit: "required"
        },
        messages: {
            productId: {
                required: getI18nText("validation.product")
            },
            productDescription: {
                required: getI18nText("validation.productDesc")
            },
            productQuantity: {
                required: getI18nText("validation.quantity"),
                minStrict: getI18nText("validation.quantity"),
                number:getI18nText("validation.quantity")
            },
            productUnit: {
                required: getI18nText("validation.productUnit")
            }
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

function clearBranchValidation() {
    var validator = branchValidator();
    $('[name]', '#branchFormId').each(function () {
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