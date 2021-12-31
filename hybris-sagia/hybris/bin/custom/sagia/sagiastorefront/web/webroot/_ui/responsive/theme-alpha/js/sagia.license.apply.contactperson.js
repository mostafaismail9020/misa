var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};
SAGIA.license.apply.contactPerson = SAGIA.license.apply.contactPerson || {};

$(function() {
    SAGIA.license.apply.onChangeTabToContactPersonTab = function() {
        $(document).scrollTop(0);
        var globalMessage = $('#contactPersonTabData .globalMessage');
        globalMessage.hide();
        if(SAGIA.license.apply.data.qeemah === 1) {
            loadQeemah1Data();
            loadQeemah1Cities();
            $("#contactPersonQM1").show();
            $("#contactPersonQM2").hide();
        } else if(SAGIA.license.apply.data.qeemah === 2) {
            loadQeemah2Data();
            $("#contactPersonQM1").hide();
            $("#contactPersonQM2").show();
        } else {
            $("#contactPersonQM1").hide();
            $("#contactPersonQM2").hide();
            globalMessage.show();
        }
    };

    var handleContactPersonQeemah1DraftLoad = function() {
        var licenseData = SAGIA.license.apply.data;
        var draft = SAGIA.license.apply.draft;
        if (draft && draft.isPresent && !draft.handledForContactPerson) {
            var contactPersonQeemah1Data = licenseData.qeemah1Data.contactPerson;

            var $contactPersonQeemah1Div = $("#contactPersonQM1");
            $contactPersonQeemah1Div.find("#qm1Title").find("input[value=" + contactPersonQeemah1Data.title + "]").click();
            $contactPersonQeemah1Div.find("#qm1FirstName").val(contactPersonQeemah1Data.firstName);
            $contactPersonQeemah1Div.find("#qm1LastName").val(contactPersonQeemah1Data.lastName);
            $contactPersonQeemah1Div.find("#qm1Role").val(contactPersonQeemah1Data.role).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah1Div.find("#qm1Education").val(contactPersonQeemah1Data.education).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah1Div.find("#qm1PassportNumber").val(contactPersonQeemah1Data.passportNumber);
            $contactPersonQeemah1Div.find("#qm1Country").val(contactPersonQeemah1Data.country).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah1Div.find("#qm1Address").val(contactPersonQeemah1Data.address);
            $contactPersonQeemah1Div.find("#qm1POBox").val(contactPersonQeemah1Data.poBox);
            $contactPersonQeemah1Div.find("#qm1PostalCode").val(contactPersonQeemah1Data.postalCode);
            $contactPersonQeemah1Div.find("#qm1Email").val(contactPersonQeemah1Data.email);
            $contactPersonQeemah1Div.find("#qm1CountryCodeForTelephone").val(contactPersonQeemah1Data.countryCodeForTelephone);
            $contactPersonQeemah1Div.find("#qm1Telephone").val(contactPersonQeemah1Data.telephone);
            $contactPersonQeemah1Div.find("#qm1CountryCodeForMobileNumber").val(contactPersonQeemah1Data.countryCodeForMobileNumber);
            $contactPersonQeemah1Div.find("#qm1MobileNumber").val(contactPersonQeemah1Data.mobileNumber);

            var dateOfBirthFP = document.querySelector("#qm1DateOfBirth")._flatpickr; // this flatpickr is used for all dates parsing in contact person
            dateOfBirthFP.setDate(dateOfBirthFP.parseDate(contactPersonQeemah1Data.dateOfBirth, ACC.formatUIDate));
            document.querySelector("#qm1PassportIssueDate")._flatpickr.setDate(dateOfBirthFP.parseDate(contactPersonQeemah1Data.passportIssueDate, ACC.formatUIDate));
            document.querySelector("#qm1PassportExpiryDate")._flatpickr.setDate(dateOfBirthFP.parseDate(contactPersonQeemah1Data.passportExpiryDate, ACC.formatUIDate));

            draft.handledForContactPerson = true;
        }
    };

    var handleContactPersonQeemah2DraftLoad = function() {
        var licenseData = SAGIA.license.apply.data;
        var draft = SAGIA.license.apply.draft;
        if (draft && draft.isPresent && !draft.handledForContactPerson) {
            var contactPersonQeemah2Data = licenseData.qeemah2Data.contactPerson;

            var $contactPersonQeemah2Div = $("#contactPersonQM2");
            $contactPersonQeemah2Div.find("#qm2Title").find("input[value=" + contactPersonQeemah2Data.gender + "]").click();
            $contactPersonQeemah2Div.find("#qm2FirstName").val(contactPersonQeemah2Data.firstName);
            $contactPersonQeemah2Div.find("#qm2LastName").val(contactPersonQeemah2Data.lastName);
            $contactPersonQeemah2Div.find("#qm2Nationality").val(contactPersonQeemah2Data.nationality).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah2Div.find("#qm2Country").val(contactPersonQeemah2Data.country).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah2Div.find("#qm2Role").val(contactPersonQeemah2Data.role).trigger("change").next().addClass("select2Container_selected");
            $contactPersonQeemah2Div.find("#qm2Email").val(contactPersonQeemah2Data.email);
            $contactPersonQeemah2Div.find("#qm2CountryCodeForTelephone").val(contactPersonQeemah2Data.countryCodeForTelephone);
            $contactPersonQeemah2Div.find("#qm2Telephone").val(contactPersonQeemah2Data.telephone);
            $contactPersonQeemah2Div.find("#qm2CountryCodeForMobileNumber").val(contactPersonQeemah2Data.countryCodeForMobileNumber);
            $contactPersonQeemah2Div.find("#qm2MobileNumber").val(contactPersonQeemah2Data.mobileNumber);

            draft.handledForContactPerson = true;
        }
    };

    var qeemah1DataLoaded = false;
    var loadQeemah1Data = function() {
        if(qeemah1DataLoaded) {
           return;
        }
        qeemah1DataLoaded = true;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var roles = $("#contactPersonQM1 #qm1Role");
                roles.find("option").remove();
                roles.append(new Option("", "", false, false));
                jsonData.roles.forEach(function (currentValue) {
                    roles.append(new Option(currentValue.roleText, currentValue.role, false, false));
                });
                if(SAGIA.license.apply.data.qeemah1Data.contactPerson.role) {
                    roles.val(SAGIA.license.apply.data.qeemah1Data.contactPerson.role).trigger("blur").trigger("change");
                } else {
                    roles.val(null);
                }

                var education = $("#contactPersonQM1 #qm1Education");
                education.find("option").remove();
                education.append(new Option("", "", false, false));
                jsonData.education.forEach(function (currentValue) {
                    education.append(new Option(currentValue.educationText, currentValue.education, false, false));
                });
                if(SAGIA.license.apply.data.qeemah1Data.contactPerson.education) {
                    education.val(SAGIA.license.apply.data.qeemah1Data.contactPerson.education).trigger("blur").trigger("change");
                } else {
                    education.val(null);
                }

                var countries = $("#contactPersonQM1 #qm1Country");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                if(SAGIA.license.apply.data.qeemah1Data.contactPerson.country) {
                    countries.val(SAGIA.license.apply.data.qeemah1Data.contactPerson.country).trigger("blur").trigger("change");
                } else {
                    countries.val(null);
                }
                handleContactPersonQeemah1DraftLoad();
            }
        });
    };

    var qeemah1CitiesDataLoaded = false;
    var loadQeemah1Cities = function(region) {
        if(qeemah1CitiesDataLoaded) {
            return;
        }
        qeemah1CitiesDataLoaded = true;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1/cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var city = $("#contactPersonQM1 #qm1City");
                city.find("option").remove();
                city.append(new Option("", "", false, false));
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    city.append(new Option(currentValue.cityText, currentValue.city, false, false));
                });
                if(SAGIA.license.apply.data.qeemah1Data.contactPerson.city) {
                    city.val(SAGIA.license.apply.data.qeemah1Data.contactPerson.city).trigger("blur").trigger("change");
                } else {
                    city.val(null);
                }
            }
        });
    };

    $(document).on('change', '#qm2Country', function () {
        var countryCode = $(this).val().substr(0, 2);
        if (countryCode) {
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/bidding-code/" + countryCode, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function (countryPrefix) {
                    if (countryPrefix && countryPrefix.length) {
                        $('#qm2CountryCodeForMobileNumber').val(countryPrefix).trigger("blur").trigger("change");
                        $('#qm2CountryCodeForTelephone').val(countryPrefix).trigger("blur").trigger("change");
                    }
                }
            });
        }
    });
    $(document).on('change', '#qm1Country', function () {
        var countryCode = $(this).val().substr(0, 2);
        if (countryCode) {
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/bidding-code/" + countryCode, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function (countryPrefix) {
                    if (countryPrefix && countryPrefix.length) {
                        $('#qm1CountryCodeForMobileNumber').val(countryPrefix).trigger("blur").trigger("change");
                        $('#qm1CountryCodeForTelephone').val(countryPrefix).trigger("blur").trigger("change");
                    }
                }
            });
        }
    });

    var qeemah2DataLoaded = false;
    var loadQeemah2Data = function() {
        if(qeemah2DataLoaded) {
            return;
        }
        qeemah2DataLoaded = true;
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah2", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var nationality = $("#contactPersonQM2 #qm2Nationality");
                nationality.find("option").remove();
                nationality.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    nationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false)); //retrieved via countries array
                });
                if(SAGIA.license.apply.data.qeemah2Data.contactPerson.nationality) {
                    nationality.val(SAGIA.license.apply.data.qeemah2Data.contactPerson.nationality).trigger("blur").trigger("change");
                } else {
                    nationality.val(null);
                }

                var countries = $("#contactPersonQM2 #qm2Country");
                countries.find("option").remove();
                countries.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    countries.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                if(SAGIA.license.apply.data.qeemah2Data.contactPerson.country) {
                    countries.val(SAGIA.license.apply.data.qeemah2Data.contactPerson.country).trigger("blur").trigger("change");
                } else {
                    countries.val(null);
                }

                var roles = $("#contactPersonQM2 #qm2Role");
                roles.find("option").remove();
                roles.append(new Option("", "", false, false));
                jsonData.roles.forEach(function (currentValue) {
                    roles.append(new Option(currentValue.roleText, currentValue.role, false, false));
                });
                if(SAGIA.license.apply.data.qeemah2Data.contactPerson.role) {
                    roles.val(SAGIA.license.apply.data.qeemah2Data.contactPerson.role).trigger("blur").trigger("change");
                } else {
                    roles.val(null);
                }
                handleContactPersonQeemah2DraftLoad();
            }
        });
    };

    var qeemah2CitiesDataLoaded = false;
    var loadQeemah2Cities = function(region) {
        if(qeemah2CitiesDataLoaded) {
            return;
        }
        qeemah2CitiesDataLoaded = true;
        // $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah2/cities/" + region, {
        //     type: "GET",
        //     responseType: "application/json;charset=utf-8",
        //     contentType: "application/json;charset=utf-8",
        //     cache: false,
        //     success: function (data) {
        //         // var basicInformationCity = basicInformationSection.find("#basicInformationCity");
        //         // basicInformationCity.find("option").remove();
        //         // basicInformationCity.append(new Option("", "", false, false));
        //         // jsonData.forEach(function (currentValue) {
        //         //     basicInformationCity.append(new Option(currentValue.cityText, currentValue.city, false, false));
        //         // });
        //         //basicInformationCity.val(null).trigger('change');
        //     }
        // });
    };

    $(document).on("click", "#contactPersonNextButton", function () {
        if (SAGIA.license.apply.data.qeemah === 2) {
            validateLicenseData('#contactPersonTabData', 'contact-person-q2-validation', SAGIA.license.apply.data.qeemah2Data.contactPerson, function () {
                SAGIA.license.apply.tabs.showAccessibleTabSelector("#reviewTab");
                SAGIA.license.apply.onChangeTabToReviewTab();
            });
        } else if (SAGIA.license.apply.data.qeemah === 1) {
            validateLicenseData('#contactPersonTabData', 'contact-person-q1-validation', SAGIA.license.apply.data.qeemah1Data.contactPerson, function () {
                SAGIA.license.apply.tabs.showAccessibleTabSelector("#reviewTab");
                SAGIA.license.apply.onChangeTabToReviewTab();
            });
        }
    });

    $(document).on("click", "#contactPersonBackButton", function() {
        SAGIA.license.apply.tabs.showAccessibleTabSelector("#shareholdersTab");
        SAGIA.license.apply.onChangeTabToShareholdersTab();
    });
    $("#contactPersonTab").on("click", function() {
        SAGIA.license.apply.onChangeTabToContactPersonTab();


        $('#contactPersonQM1 .form-group').each(function () {
            if (! $(this).hasClass('has-error') && $(this).find('.help-block').length) {
                $(this).find('.help-block').text('');
            }
        });
    });

   /* $("#qm2FirstName, #qm2LastName, #qm2Email").keypress(function() {
        if ($(this).closest('.form-group').find('.help-block').length > 0) {
            $(this).closest('.form-group').find('.help-block').text("");
            $(this).closest('.form-group').removeClass('has-error');
        }
    });
    $("#qm2Role,#qm2Nationality, #qm2Country, #qm2Telephone,#qm2MobileNumber").change(function() {
        if ($(this).closest('.form-group').siblings('.help-block').length > 0) {
            $(this).closest('.form-group').siblings('.help-block').text("");
            $(this).closest('.form-group').removeClass('has-error');
        }
    });
    

    $("#qm1FirstName, #qm1LastName, #qm1Email, #qm1PassportNumber, #qm1City, #qm1Address, #qm1POBox, #qm1PostalCode, #qm1Telephone, #qm1MobileNumber").keypress(function() {
        if ($(this).closest('.form-group').find('.help-block').length > 0) {
            $(this).closest('.form-group').find('.help-block').text("");
            $(this).closest('.form-group').removeClass('has-error');
        }
    });
    $("#qm1Role, #qm1Education, #qm2Country, #qm1DateOfBirth, #qm1PassportIssueDate, #qm1PassportExpiryDate, #qm1Country").change(function() {
        if ($(this).closest('.form-group').siblings('.help-block').length > 0) {
            $(this).closest('.form-group').siblings('.help-block').text("");
            $(this).closest('.form-group').removeClass('has-error');
        }
    });*/

    $(document).on("change", "#contactPersonQM1 #qm1Title input", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.title = $(this).val();
        SAGIA.license.apply.data.qeemah1Data.contactPerson.titleText = $(this).find(":selected").text();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1FirstName", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.firstName = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1LastName", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.lastName = $(this).val();
    });
    $(document).on("change", "#contactPersonQM1 #qm1Role", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.role = $(this).val();
        SAGIA.license.apply.data.qeemah1Data.contactPerson.roleText = $(this).find(":selected").text();
    });
    $(document).on("change", "#contactPersonQM1 #qm1Education", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.education = $(this).val();
        SAGIA.license.apply.data.qeemah1Data.contactPerson.educationText = $(this).find(":selected").text();
    });
    $(document).on("change", "#contactPersonQM1 #qm1DateOfBirth", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.dateOfBirth = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1PassportNumber", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.passportNumber = $(this).val();
    });
    $(document).on("change", "#contactPersonQM1 #qm1PassportIssueDate", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.passportIssueDate = $(this).val();
    });
    $(document).on("change", "#contactPersonQM1 #qm1PassportExpiryDate", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.passportExpiryDate = $(this).val();
    });
    $(document).on("change", "#contactPersonQM1 #qm1Country", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.country = $(this).val();
        SAGIA.license.apply.data.qeemah1Data.contactPerson.countryText = $(this).find(":selected").text();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1City", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.city = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1Address", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.address = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1POBox", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.poBox = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1PostalCode", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.postalCode = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1CountryCodeForTelephone", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.countryCodeForTelephone = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1Telephone", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.telephone = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1CountryCodeForMobileNumber", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.countryCodeForMobileNumber = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1MobileNumber", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.mobileNumber = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM1 #qm1Email", function() {
        SAGIA.license.apply.data.qeemah1Data.contactPerson.email = $(this).val();
    });

    $(document).on("change", "#contactPersonQM2 #qm2Title input", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.gender = $(this).val();
        SAGIA.license.apply.data.qeemah2Data.contactPerson.genderText = $(this).find(":selected").text();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2FirstName", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.firstName = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2LastName", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.lastName = $(this).val();
    });
    $(document).on("change", "#contactPersonQM2 #qm2Nationality", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.nationality = $(this).val();
        SAGIA.license.apply.data.qeemah2Data.contactPerson.nationalityText = $(this).find(":selected").text();
    });
    $(document).on("change", "#contactPersonQM2 #qm2Country", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.country = $(this).val();
        SAGIA.license.apply.data.qeemah2Data.contactPerson.countryText = $(this).find(":selected").text();
    });
    $(document).on("change", "#contactPersonQM2 #qm2Role", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.role = $(this).val();
        SAGIA.license.apply.data.qeemah2Data.contactPerson.roleText = $(this).find(":selected").text();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2Email", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.email = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2CountryCodeForTelephone", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.countryCodeForTelephone = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2Telephone", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.telephone = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2CountryCodeForMobileNumber", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.countryCodeForMobileNumber = $(this).val();
    });
    $(document).on("blur", "#contactPersonQM2 #qm2MobileNumber", function() {
        SAGIA.license.apply.data.qeemah2Data.contactPerson.mobileNumber = $(this).val();
    });
    
    $("#qm1CountryCodeForTelephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#qm1Telephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#qm1CountryCodeForMobileNumber").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#qm1MobileNumber").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#qm1PostalCode").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#qm1POBox").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
});

$("#qm1Email").focusout(function() {
   	var emailValue = $('#qm1Email').val();

   	if (!validateContactEmail(emailValue)) {
   	 	$("#contactEmailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$("#contactPersonNextButton").attr("disabled", true);
   	}
   	else {
   		$("#contactPersonNextButton").attr("disabled", false);	
   	}
});

$("#qm1Email").focusin(function() {
	$("#contactEmailValidation").text("");
});

function validateContactEmail(emailValue) {
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 return filter.test(emailValue);
}