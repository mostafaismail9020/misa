var SAGIA = SAGIA || {};
SAGIA.register = {};

$(function () {
    var nationalInvestorForm = $("#nationalInvestorForm");

    $('.accountLogin').show();
    var loadInvestorTimeSlots = function(selectedDate, branch) {
        var date = selectedDate.getFullYear() + '-' + ('0' + (selectedDate.getMonth() + 1)).slice(-2) + '-' + ('0' + selectedDate.getDate()).slice(-2);
        $.ajax(ACC.config.encodedContextPath + "/register/get-calendar-slots", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            data: {
                selectedDate: date,
                branch: branch
            },
            success: function (data) {
                var jsonData = JSON.parse(data);
                var timeSlots = [];
                jsonData.forEach(function (currentValue) {
                    if (currentValue.date.date.year === selectedDate.getFullYear() &&
                        currentValue.date.date.month === selectedDate.getMonth() + 1 &&
                        currentValue.date.date.day === selectedDate.getDate()) {
                        var minute = currentValue.dateTimeEnd.minute;
                        if(currentValue.dateTimeEnd.minute === 0) {
                            minute = '00';
                        }
                        timeSlots.push(currentValue.dateTimeStart.hour + ':' + minute);
                    }
                });
                _updateSlot($('.page-login #timeFromString'), timeSlots);
            }
        });
    };

    var loadInvestorByCr = function(crNumber) {
        if(crNumber === "") {
            $(".valid-cr-fields").hide();
            $('.cr-validation').addClass('has-error');
            $('.cr-validation .help-block').html("<span>Please provide a valid CR Number</span>");
        } else {
            $.ajax(ACC.config.encodedContextPath + "/register/national-investor/" + crNumber, {
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
                        initInvestorCrForm(jsonData);
                        loadLegalStatuses();
                        $(".enumList-item_active").removeClass("enumList-item_active");
                        $(".organization-info").addClass("enumList-item_active");
                        $(".valid-cr-fields").show();
                        $('html, body').animate({
                            scrollTop: $("#organizationInfo").offset().top
                        }, 1000);
                    } else {
                        $(".valid-cr-fields").hide();
                        $('.cr-validation').addClass('has-error');
                        $('.cr-validation .help-block').html("<span>" + jsonData.error + "</span>");
                    }
                },
                error: function() {
                    $("#inputCRNumber-error").addClass("has-error").text(getI18nText("general.error"));
                    $(".valid-cr-fields").hide();
                }
            });
        }
    };

    var initInvestorCrForm = function(data) {
        // TODO: select dropdown values - currently empty values are returned
        $("#crNumber").val(data.crNumber);

        $("#nameArabic").val(data.nameArabic);
        $("#nameEnglish").val(data.nameEnglish);
        $("#capital").val(data.capital);
        nationalInvestorForm.find("#email").val(data.email);

        $("#number700").val(data.number700);
        $("#zakatNumber").val(data.zakatNumber);
        $("#molNumber").val(data.molNumber);
        $("#gosiNumber").val(data.gosiNumber);

        $("#contactName").val(data.contactName);
        $("#contactMobile").val(data.contactMobile);
        $("#contactEmail").val(data.contactEmail);
    };

    var loadLegalStatuses = function(selectedStatus) {
        $.ajax(ACC.config.encodedContextPath + "/register/get-legal-statuses", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                $('#legalStatus option').remove();
                jsonData.forEach(function (currentValue) {
                    $('#legalStatus').append(new Option(currentValue.name, currentValue.code, false, false));
                });
                $('#legalStatus').val(null).trigger('change');
            }
        });
    };

    var loadCompanyNationality = function(nationalityType) {
        var nationalityElement = $('#compNationality');
        nationalityElement.find("option").remove();
        var emptyOption = new Option("", "", false, false);
        nationalityElement.append(emptyOption);
        if (nationalityType === 'SAUDI') {
            nationalityElement.append(new Option("Saudi Arabia", "SA", false, true));
            nationalityElement.trigger('change');
            nationalityElement.next().addClass("select2Container_selected");
        } else {
            jQuery.ajax(ACC.config.encodedContextPath + "/register/get-nationalities/" + nationalityType, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    jsonData.forEach(function (currentValue) {
                        nationalityElement.append(new Option(currentValue.nationalityText, currentValue.nationalityText, false, false));
                    });
                    nationalityElement.val(null).trigger('change');
                }
            });
        }
    };

    var loadRegions = function(country) {
        getCountryPhonePrefix(country, $("#mobilePrefix"));
        var regionElement = $("#region");
        regionElement.empty();
        var emptyOption = new Option("", "", false, false);
        regionElement.append(emptyOption);
        $.ajax(ACC.config.encodedContextPath + "/register/get-regions/" + country, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var jqRegionElement = $(".jqRegion");
                var jqTxtRegionElement = $(".jqTxtRegion");
                var jqSelRegionElement = $('.jqSelRegion');
                if (jsonData.length > 0) {
                    jqRegionElement.find(".select2").show();
                    jqRegionElement.removeClass("formInputBox");
                    jqRegionElement.addClass("formSelectBox");
                    jqTxtRegionElement.hide();
                    jsonData.forEach(function (currentValue) {
                        jqSelRegionElement.append(new Option(currentValue.name, currentValue.code, false, false));
                    });
                    jqSelRegionElement.val(null).trigger('change');

                    jqSelRegionElement.attr("id", "region");
                    jqSelRegionElement.attr("name", "region");
                    jqTxtRegionElement.removeAttr("id");
                    jqTxtRegionElement.removeAttr("name");
                } else {
                    jqRegionElement.find(".select2").hide();
                    jqRegionElement.removeClass("formSelectBox");
                    jqRegionElement.addClass("formInputBox");
                    jqTxtRegionElement.show();
                    jqTxtRegionElement.focus();

                    jqTxtRegionElement.attr("id", "region");
                    jqTxtRegionElement.attr("name", "region");
                    jqSelRegionElement.removeAttr("id");
                    jqSelRegionElement.removeAttr("name");
                }
                loadCities("");
            }
        });
    };

    var loadCities = function(region) {

        var jqSelCityElement = $('.jqSelCity');
        jqSelCityElement.find("option").remove();
        var emptyOption = new Option("", "", false, false);
        jqSelCityElement.append(emptyOption);
        var jqCityElement = $(".jqCity");
        var jqTxtCityElement = $(".jqTxtCity");
        if(region == ""){
            jqCityElement.find(".select2").hide();
            jqCityElement.removeClass("formSelectBox");
            jqCityElement.addClass("formInputBox");
            jqTxtCityElement.show();
            // jqTxtCityElement.focus();

            jqTxtCityElement.attr("id", "city");
            jqTxtCityElement.attr("name", "city");
            jqSelCityElement.removeAttr("id");
            jqSelCityElement.removeAttr("name");
            return;
        }
        $.ajax(ACC.config.encodedContextPath + "/register/get-cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                if (jsonData.length > 0) {
                    jqCityElement.find(".select2").show();
                    jqCityElement.removeClass("formInputBox");
                    jqCityElement.addClass("formSelectBox");
                    jqTxtCityElement.hide();
                    jsonData.forEach(function (currentValue) {
                        jqSelCityElement.append(new Option(currentValue.name, currentValue.code, false, false));
                    });
                    jqSelCityElement.val(null).trigger('change');

                    jqSelCityElement.attr("id", "city");
                    jqSelCityElement.attr("name", "city");
                    jqTxtCityElement.removeAttr("id");
                    jqTxtCityElement.removeAttr("name");
                } else {
                    jqCityElement.find(".select2").hide();
                    jqCityElement.removeClass("formSelectBox");
                    jqCityElement.addClass("formInputBox");
                    jqTxtCityElement.show();
                    jqTxtCityElement.focus();

                    jqTxtCityElement.attr("id", "city");
                    jqTxtCityElement.attr("name", "city");
                    jqSelCityElement.removeAttr("id");
                    jqSelCityElement.removeAttr("name");
                }
            }
        });
    };

    var loadISICSections = function() {
        var isicSection = $('#isicSection');
        isicSection.find("option").remove();
        isicSection.append(new Option("", "", false, false));
        $.ajax(ACC.config.encodedContextPath + "/register/get-isic-sections/", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                jsonData.forEach(function (currentValue) {
                    isicSection.append(new Option(currentValue.name, currentValue.code, false, false));
                });
                isicSection.val(null).trigger('change');
            }
        });
    };

    var loadISICDivisions = function(sectionCode) {
        var isicDivision = $('#isicDivision');
        if(sectionCode !== "") {
            isicDivision.find("option").remove();
            isicDivision.append(new Option("", "", false, false));
            $.ajax(ACC.config.encodedContextPath + "/register/get-isic-divisions/" + sectionCode, {
                type: "GET",
                responseType: "application/json;charset=utf-8",
                contentType: "application/json;charset=utf-8",
                cache: false,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    jsonData.forEach(function (currentValue) {
                        isicDivision.append(new Option(currentValue.name, currentValue.code, false, false));
                    });
                    isicDivision.val(null).trigger('change');
                }
            });
        }
    };

    var loadContactRegions = function(country) {
        var jqSelContactRegion = $(".jqSelContactRegion");
        var jqContactRegion = $(".jqContactRegion");
        var jqTxtContactRegion = $(".jqTxtContactRegion");
        getCountryPhonePrefix(country, $("#contactMobilePrefix"));
        jqSelContactRegion.find("option").remove();
        jqSelContactRegion.append(new Option("", "", false, false)).trigger('change');
        $.ajax(ACC.config.encodedContextPath + "/register/get-regions/" + country, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                if (jsonData.length > 0) {
                    jqContactRegion.find(".select2").show();
                    jqContactRegion.removeClass("formInputBox");
                    jqContactRegion.addClass("formSelectBox");
                    jqTxtContactRegion.hide();
                    jsonData.forEach(function (currentValue) {
                        jqSelContactRegion.append(new Option(currentValue.name, currentValue.code, false, false));
                    });
                    jqSelContactRegion.val(null).trigger('change');
                    jqSelContactRegion.attr("id", "contactRegion");
                    jqSelContactRegion.attr("name", "contactRegion");
                    jqTxtContactRegion.removeAttr("id");
                    jqTxtContactRegion.removeAttr("name");
                } else {
                    jqContactRegion.find(".select2").hide();
                    jqContactRegion.removeClass("formSelectBox");
                    jqContactRegion.addClass("formInputBox");
                    jqTxtContactRegion.show();
                    //jqTxtContactRegion.focus();

                    jqTxtContactRegion.attr("id", "contactRegion");
                    jqTxtContactRegion.attr("name", "contactRegion");
                    jqSelContactRegion.removeAttr("id");
                    jqSelContactRegion.removeAttr("name");
                }
                loadContactCities("");
            }
        });
    };

    var loadContactCities = function(region) {
        var jqSelContactCity = $(".jqSelContactCity");
        var jqContactCity = $(".jqContactCity");
        var jqTxtContactCity = $(".jqTxtContactCity");
        jqSelContactCity.find("option").remove();
        var emptyOption = new Option("", "", false, false);
        jqSelContactCity.append(emptyOption);
        if(region == "") {
            jqContactCity.find(".select2").hide();
            jqContactCity.removeClass("formSelectBox");
            jqContactCity.addClass("formInputBox");
            jqTxtContactCity.show();
            // jqTxtContactCity.focus();

            jqTxtContactCity.attr("id", "contactCity");
            jqTxtContactCity.attr("name", "contactCity");
            jqSelContactCity.removeAttr("id");
            jqSelContactCity.removeAttr("name");
            return;
        }
        $.ajax(ACC.config.encodedContextPath + "/register/get-cities/" + region, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                jqTxtContactCity.hide();
                jqContactCity.find(".select2").hide();
                if (jsonData.length > 0) {
                    jqContactCity.find(".select2").show();
                    jqContactCity.removeClass("formInputBox");
                    jqContactCity.addClass("formSelectBox");
                    jsonData.forEach(function (currentValue) {
                        jqSelContactCity.append(new Option(currentValue.name, currentValue.code, false, false));
                    });

                    jqTxtContactCity.removeAttr("id");
                    jqTxtContactCity.removeAttr("name");

                    jqSelContactCity.val(null).trigger('change');
                    jqSelContactCity.attr("id", "contactCity");
                    jqSelContactCity.attr("name", "contactCity");
                    jqSelContactCity.show();
                } else {
                    jqContactCityElement.find(".select2").hide();
                    jqContactCityElement.removeClass("formSelectBox");
                    jqContactCityElement.addClass("formInputBox");
                    jqContactCityElement.show();
                    // jqTxtCityElement.focus();

                    jqTxtContactCity.attr("id", "contactCity");
                    jqTxtContactCity.attr("name", "contactCity");
                    jqSelContactCity.removeAttr("id");
                    jqSelContactCity.removeAttr("name");
                }
            }
        });
    };

    var loadCountries = function() {
        $.get(ACC.config.encodedContextPath + "/register/countries", function(data) {
            var jsonData = JSON.parse(data);
            SAGIA.register.countries = jsonData;
            var quickRegistrationRegisterCountry = $('#quickregistration\\.register\\.country');
            quickRegistrationRegisterCountry.empty();
            quickRegistrationRegisterCountry.append(new Option('', '', false, false));
            var applyRegisterCountry = $('#applylicense\\.register\\.country');
            applyRegisterCountry.empty();
            applyRegisterCountry.append(new Option('', '', false, false));
            jsonData.forEach(function (currentValue) {
                quickRegistrationRegisterCountry.append(new Option(currentValue.name, currentValue.code, false, false));
                applyRegisterCountry.append(new Option(currentValue.name, currentValue.code, false, false)); //cannot reuse
            });
        });
    };
    var loadSectors = function() {
        $.get(ACC.config.encodedContextPath + "/register/sectors", function(data) {
            var jsonData = JSON.parse(data);
            SAGIA.register.sectors = jsonData;
            var quickRegistrationRegisterSector = $('#quickregistration\\.register\\.sector');
            quickRegistrationRegisterSector.empty();
            quickRegistrationRegisterSector.append(new Option('', '', false, false));
            var applyRegisterSector = $('#applylicense\\.register\\.sector');
            applyRegisterSector.empty();
            applyRegisterSector.append(new Option('', '', false, false));
            jsonData.forEach(function (currentValue) {
                quickRegistrationRegisterSector.append(new Option(currentValue.name, currentValue.code, false, false)); //cannot reuse
                applyRegisterSector.append(new Option(currentValue.name, currentValue.code, false, false)); //cannot reuse
            });
        });
    };
    var loadUnifiedLicenseUrl = function() {
        $.get(ACC.config.encodedContextPath + "/register/unifiedLicenseUrl", function(data) {
            $("#unifiedLicenseUrl").attr("href", data);
        });
    };
    var loadNationalInvestorData = function() {
        $.get(ACC.config.encodedContextPath + "/register/national-investor/data", function(data) {
            var jsonData = JSON.parse(data);
            var branch = $('#branch');
            var legalStatus = $('#legalStatus');
            var countries = $('#country, #contactCountry');
            var contactNationality = $('#contactNationality');
            var isicSection = $('#isicSection');
            branch.empty();
            branch.append(new Option('', '', false, false));
            jsonData.branches.forEach(function (currentValue) {
                branch.append(new Option(currentValue.description, currentValue.fieldKey, false, false));
            });
            legalStatus.empty();
            legalStatus.append(new Option('', '', false, false));
            jsonData.nipLegalStatuses.forEach(function(currentValue) {
                legalStatus.append(new Option(currentValue.name, currentValue.code, false, false));
            });
            countries.empty();
            countries.append(new Option('', '', false, false));
            jsonData.nipGCCCountries.forEach(function(currentValue) {
                countries.append(new Option(currentValue.name, currentValue.code, false, false));
            });

            contactNationality.empty();
            contactNationality.append(new Option('', '', false, false));
            jsonData.nipGCCCountries.forEach(function(currentValue) {
                contactNationality.append(new Option(currentValue.nationalityText, currentValue.code, false, false));
            });

            isicSection.empty();
            isicSection.append(new Option('', '', false, false));
            jsonData.nipISICSections.forEach(function(currentValue) {
                isicSection.append(new Option(currentValue.name, currentValue.code, false, false));
            });

            if(jsonData.serviceType != null && jsonData.serviceType.description != null) {
                $("#serviceType1Description").val(jsonData.serviceType.description);
            }
            if(jsonData.service != null && jsonData.service.description != null) {
                $("#service1Description").val(jsonData.service.description);
            }
            if(jsonData.departments != null && typeof jsonData.departments == 'array' && jsonData.departments.length > 0) {
                if(jsonData.departments[0].description != null) {
                    $("#departmentDescription").val(jsonData.departments[0].description);
                }
                if(jsonData.departments[0].fieldKey != null) {
                    $("#department").val(jsonData.departments[0].fieldKey);
                }
            }
            if(jsonData.serviceType != null && jsonData.serviceType.fieldKey != null) {
                $("#serviceType1").val(jsonData.serviceType.fieldKey);
            }

            SAGIA.register.crmCountries = JSON.parse(jsonData.crmCountriesJson);
        });
    };

    var getCountryPhonePrefix = function(selectedCountry, inputField) {
        var country = SAGIA.register.crmCountries.find(function(x) {
            return x.code === selectedCountry;
        });
        inputField.removeAttr('readonly');
        inputField.val(country.mobileCode);
        inputField.attr('readonly', 'readonly');
    };

    var objectifyForm = function(formArray) {//serialize data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    };

    var investorNoCrValidationParams = {
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: true,
        ignore: "",
        errorElement: 'span',
        errorPlacement: function(error, element) {
            if (element.closest('.form-group').find('.help-block').length>0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn')){
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            branch: {
                required: true
            },
            investorId: {
                required: true
            },
            email: {
                required: true
            },
            dateString: {
                required: true
            },
            timeFromString: {
                required: true
            },
            termsAndConditionsChecked: {
                required: true
            }
        },
        messages: {
            branch: {
                required: getI18nText("validation.empty")
            },
            investorId: {
                required: getI18nText("validation.empty")
            },
            email: {
                required: getI18nText("validation.empty")
            },
            dateString: {
                required: getI18nText("validation.empty")
            },
            timeFromString: {
                required: getI18nText("validation.empty")
            }
        },
        invalidHandler: function(event, validator) {}
    };

    var nationalInvestorAppointment = $('#nationalInvestorAppointment');
    nationalInvestorAppointment.validate(investorNoCrValidationParams);
    nationalInvestorAppointment.ajaxForm({
        beforeSubmit: function(arr, $form, options){
            var valid = $form.valid();
            if (!valid) {
                return false;
            }
            options.headers = {
                'Accept': 'application/json',
                'CSRFToken': ACC.config.CSRFToken
            };
        },
        success: function (data) {
            var response = JSON.parse(data);
            if (response && response.responseData && +response.responseData) {
                $('#nipSuccessModal #requestNumberPlaceholder').text(response.responseData);
                $('#nipSuccessModal').modal('toggle');
            } else {
                $('#nipFailModal').modal('toggle');
            }

        }
    });

    var investorCrValidationParams = {
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: true,
        ignore: "",
        errorElement: 'span',
        errorPlacement: function(error, element) {
            if (element.closest('.form-group').find('.help-block').length>0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn')){
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            nameArabic: {
                required: true
            },
            nameEnglish: {
                required: true
            },
            capital: {
                required: true
            },
            legalStatus: {
                required: true
            },
            compNatType: {
                required: true
            },
            compNationality: {
                required: true
            },
            country: {
                required: true
            },
            region: {
                required: true
            },
            city: {
                required: true
            },
            mobile: {
                required: true,
                digits: true
            },
            email: {
                required : true,
                email: true
            },
            isicSection: {
                required: true
            },
            isicDivision: {
                required: true
            },
            contactName: {
                required: true
            },
            contactCountry: {
                required: true
            },
            contactNationality: {
                required: true
            },
            contactRegion: {
                required: true
            },
            contactCity: {
                required: true
            },
            contactMobile: {
                required: true
            },
            contactEmail: {
                required: true,
                email: true
            },
            termsAndConditionsChecked: {
                required: true
            }
        },
        messages: {
            nameArabic: {
                required: getI18nText("validation.empty")
            },
            nameEnglish: {
                required: getI18nText("validation.empty")
            },
            capital: {
                required: getI18nText("validation.empty")
            },
            legalStatus: {
                required: getI18nText("validation.empty")
            },
            compNatType: {
                required: getI18nText("validation.empty")
            },
            compNationality: {
                required: getI18nText("validation.empty")
            },
            country: {
                required: getI18nText("validation.empty")
            },
            region: {
                required: getI18nText("validation.empty")
            },
            city: {
                required: getI18nText("validation.empty")
            },
            mobile: {
                required: getI18nText("validation.empty"),
            },
            email: {
                required: getI18nText("validation.empty"),
            },
            isicSection: {
                required: getI18nText("validation.empty")
            },
            isicDivision: {
                required: getI18nText("validation.empty")
            },
            contactName: {
                required: getI18nText("validation.empty")
            },
            contactCountry: {
                required: getI18nText("validation.empty")
            },
            contactNationality: {
                required: getI18nText("validation.empty")
            },
            contactRegion: {
                required: getI18nText("validation.empty")
            },
            contactCity: {
                required: getI18nText("validation.empty")
            },
            contactMobile: {
                required: getI18nText("validation.empty"),
                digits: getI18nText("register.mobileNumber.invalid"),
                remote: getI18nText("register.validation.mobileNumber")
            },
            contactEmail: {
                required: getI18nText("validation.empty"),
                email: getI18nText("register.email.invalid"),
                remote: getI18nText("register.validation.email")
            },
            termsAndConditionsChecked : {
                required: getI18nText("terms.accept.text")
            }
        },
        invalidHandler: function(event, validator) {
            // if error in Organization Information - expand section
            $('.enumList-item').removeClass('enumList-item_active');
            if(validator.errorMap.hasOwnProperty("nameArabic")
                    || validator.errorMap.hasOwnProperty("nameEnglish")
                    || validator.errorMap.hasOwnProperty("capital")
                    || validator.errorMap.hasOwnProperty("legalStatus")
                    || validator.errorMap.hasOwnProperty("compNatType")
                    || validator.errorMap.hasOwnProperty("compNationality")
                    || validator.errorMap.hasOwnProperty("country")
                    || validator.errorMap.hasOwnProperty("region")
                    || validator.errorMap.hasOwnProperty("city")
                    || validator.errorMap.hasOwnProperty("mobile")
                    || validator.errorMap.hasOwnProperty("email")) {
                $('.organization-info').addClass('enumList-item_active');
                return;
            }
            if(validator.errorMap.hasOwnProperty("isicDivision") || validator.errorMap.hasOwnProperty("isicSection")){
                $('.organization-details').addClass('enumList-item_active');
                return;
            }
            if(validator.errorMap.hasOwnProperty("contactName") ||
                    validator.errorMap.hasOwnProperty("contactCountry") ||
                    validator.errorMap.hasOwnProperty("contactNationality") ||
                    validator.errorMap.hasOwnProperty("contactRegion") ||
                    validator.errorMap.hasOwnProperty("contactCity") ||
                    validator.errorMap.hasOwnProperty("contactMobile") ||
                    validator.errorMap.hasOwnProperty("contactEmail")) {
                $('.contact-details').addClass('enumList-item_active');
            }
        }
    };

    investorCrValidationParams.rules.contactEmail.remote = {
        type: 'GET',
        url: ACC.config.encodedContextPath + "/register/validation",
        data: {
            email: function() { return $("#contactEmail").val() }
        },
        cache: false,
        dataFilter: function(data) {
            var $element = $("#contactEmail");
            if (data === 'true') {
                var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                messageElements.text(getI18nText('register.validation.email.ok'));
                messageElements.show();
                return true;
            } else {
                $element.closest('.formInputBox').find(".js-help-block-success").empty();
                return false;
            }
        }
    };

    investorCrValidationParams.rules.contactMobile.remote = {
        type: 'GET',
        url: ACC.config.encodedContextPath + "/register/validation",
        data: {
            mobileNumber: function () {
                return $("#contactMobile").val();
            },
            mobileCountryCode: function () {
                return $('#contactMobilePrefix').val();
            }
        },
        cache: false,
        dataFilter: function(data) {
            var $element = $("#contactMobile");
            if (data === 'true') {
                var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                messageElements.text(getI18nText('register.validation.mobileNumber.ok'));
                messageElements.show();
                return true;
            }
            else {
                $element.closest('.formInputBox').find(".js-help-block-success").empty();
                return false;
            }
        }
    };

    nationalInvestorForm.validate(investorCrValidationParams);
    nationalInvestorForm.ajaxForm({
        beforeSubmit: function(arr, $form, options){
            var valid = $form.valid();
            if (!valid) {
                return false;
            }
            var removeFromIndex = [];
            $.each(arr, function(index, value) {
                if (value.type === "file" && !value.value) {
                    removeFromIndex.push(index);
                }
            });
            for (var i = removeFromIndex.length -1; i >= 0; i--) {
                arr.splice(removeFromIndex[i],1);
            }
            options.headers = {
                'Accept': 'application/json',
                'CSRFToken': ACC.config.CSRFToken
            };
        },
        success: function (data) {
            $('#nipSuccessModal #requestNumberPlaceholder').text(data.responseData.srqId);
            $('#nipSuccessModal').modal('toggle');
        }
    });

    $('.load-investor').on('click', function () {
        loadInvestorByCr($('#inputCRNumber').val());
    });
    $('#inputCRNumber').on("keyup", function(e) {
        if(e.keyCode === 13) {
            loadInvestorByCr($(this).val());
        }
    });

    $('#compNatType').on('change', function () {
        loadCompanyNationality($(this).val());
    });

    $('#country').on('change', function () {
        loadRegions($(this).val());
    });

    $('#contactCountry').on('change', function () {
        loadContactRegions($(this).val());
    });

    nationalInvestorForm.on('change', '.jqSelRegion', function () {
        loadCities($(this).val());
    });

    nationalInvestorForm.on('change', '.jqSelContactRegion', function () {
        loadContactCities($(this).val());
    });

    nationalInvestorForm.on('change', '#isicSection', function () {
        loadISICDivisions($(this).val());
    });

    $("#applylicense\\.register\\.company, #quickregistration\\.register\\.company").on("keydown", function(e) {
        var keyCode = (e.keyCode ? e.keyCode : e.which);
        if (keyCode > 47 && keyCode < 58 || keyCode > 95 && keyCode < 107 ){
            return false;
        }
    });

    $("#quickregistrationUsername, #applyForLicenseUsername").on("keydown", function(e) {
        var keyCode = (e.keyCode ? e.keyCode : e.which);
        if(keyCode === 32) { //space
            return false;
        }
    });
    $("#quickregistrationUsername, #applyForLicenseUsername").on("change", function(e) {
        $(this).val($(this).val().replace(/\s/g, "")); //for copy paste, remove space
    });
    $("#quickregistrationUsername").keypress(function() {
        if ($(this).closest('.form-group').find('.help-block').length > 1) {
            $(this).closest('.form-group').find('.help-block')[1].remove();
            $(this).closest('.form-group').removeClass('has-error');
        }
    });
    $("#quickregistrationEmail").keypress(function() {
        if ($(this).closest('.form-group').find('.help-block').length>1) {
            $(this).closest('.form-group').find('.help-block')[1].remove();
            $(this).closest('.form-group').removeClass('has-error');
        }
    });

// populates phone field with country prefix
    setMobileCode(".countriesselect", "quickregistration.register.mobileCountryCode", "#sagiaRegisterFormApplyLicense", ".js-quick-mobile-number");
    setMobileCode(".countriesselect\\.apply", "applylicense.register.mobileCountryCode", "#sagiaRegisterFormQuickRegistration", ".js-apply-mobile-number");

    function setMobileCode(sourceElement, targetElement, form, validateElement) {
        $(sourceElement).on('change', function () {
            var code = $(this).val();
            var prefix = SAGIA.register.countries.filter(function (el) {
                return el.code === code;
            });

            $(this).valid();

            if (prefix[0] != null) {
                var countryCodeInput = document.getElementById(targetElement);
                if(prefix[0].phoneprefix) {
                    countryCodeInput.value = prefix[0].phoneprefix;
                } else {
                    countryCodeInput.value = '';
                }
                countryCodeInput.classList.remove('placeholder-shown');
                var $form = $(form);
                var nextHelpBlock = $(validateElement).next(".help-block");
                nextHelpBlock.empty();
                /* Trigger manual remote validation from jQuery Validation for mobileNumber */
                $(validateElement).removeData("previousValue");
                //$form.data('validator').element(validateElement);
            }
        });
    }

    SAGIA.register.countries = {};
    SAGIA.register.init = function() {
        loadCountries();
        loadSectors();
        loadUnifiedLicenseUrl();
        loadNationalInvestorData();
        clearSuccessValidations();

        var now = new Date();
        var yesterday = new Date(now.setDate(now.getDate() - 1));

        if($('.page-login .js-form-control_date#dateString').length > 0) {
            $('.js-form-control_date#dateString').get(0).flatpickr({
                disable: [
                    function (date) {
                        return date < yesterday;
                    },
                    function (date) {
                        // return true to disable
                        return (date.getDay() === 5 || date.getDay() === 6);
                    }
                ],
                dateFormat: ACC.formatUIDate,
                onChange: function(selectedDates, dateStr, instance){
                    instance.set('dateFormat', ACC.formatUIDate);
                    var date = selectedDates[0].getFullYear() + '-' + ('0' + (selectedDates[0].getMonth()+1)).slice(-2) + '-' + ('0' + selectedDates[0].getDate()).slice(-2);
                    loadAvailableTimeSlots(date);
                    var branchVal = $('#branch').val();
                    if(branchVal !== "") {
                        loadInvestorTimeSlots(selectedDates[0], branchVal);
                    }
                }
            });
        }

        $("#myInput").on('input', function () {
            var value = $(this).val();
            if ($(this).val().length > 3) {
                $.ajax({
                    type: "GET",
                    url: ACC.config.encodedContextPath + "/service-search/search/" + value,
                    cache: false,
                    success: function (response) {
                        var resultList = $("#resultlist");
                        resultList.empty();
                        var obj = JSON.parse(response);
                        for (var key in obj) {
                            if(obj.hasOwnProperty(key)) {
                                resultList.append(
                                    "<h1>" + "<img src='" + obj[key][0].category.icon.url + "' height=\"42\" width=\"42\" >" + key + " (" + obj[key].length + " services)" + "</h1>");
                                for (var item in obj[key]) {
                                    if(obj[key].hasOwnProperty(item)) {
                                        resultList.append(
                                            "<li>" + "<div class='servicedetails' data-code='" + obj[key][item].code + "'>" + obj[key][item].code + "</div>"
                                            + obj[key][item].name + "</li>" + "<p style='font-size: 100%'>" + obj[key][item].description + "</p>");
                                    }
                                }
                            }
                        }
                    },
                    error: function () {
                        var errorModal = $('#errorResponseModal');
                        errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
                        errorModal.modal('show');
                    }
                });
            }
        });

        $(document).on('click', '.servicedetails', function () {
            var value = $(this).attr('data-code');
            $.ajax({
                type: "GET",
                url: ACC.config.encodedContextPath + "/service-search/serviceDetails/" + value,
                cache: false,
                success: function (response) {
                    var tabsList = $("#tabsList");
                    tabsList.empty();
                    var obj = JSON.parse(response);
                    for (var key in obj) {
                        if(obj.hasOwnProperty(key)) {
                            tabsList.append("<li>" + obj[key].title + "</li>" + "<p style='font-size: 100%'>" + obj[key].content + "</p>");
                        }
                    }
                }
            });
        });

        $(".accountLogin-content-formSubmitSection-checkbox.formCheckBox").on("click", function () {
            $('#termsAndConditionsChecked-error').remove();
        });

        $.validator.setDefaults({
            highlight: function(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            errorElement: 'span',
            //errorClass: 'help-block',
            errorPlacement: function(error, element) {
                if(element.closest('.formInputBox_big').length>0){
                    error.appendTo(element.closest('.formInputBox_big').find('.help-block'));
                } else if (element.closest('.formInputFile_group').length>0){
                    error.appendTo(element.closest('.formInputFile_group').find('.help-block'));
                } else if(element.closest('.form-group').find('.help-block').length>0) {
                    error.appendTo(element.closest('.form-group').find('.help-block'));
                } else if (element.hasClass('js-select2-oneColumn')) {
                    error.appendTo(element.closest('.col-md-6').find('.help-block'));
                } else if (element.hasClass('form-control')) {
                    error.appendTo(element.parent().find('.help-block'));
                } else {
                    error.appendTo(element.closest('.formInputBox').find('.help-block'));
                }
            },
            onkeyup: false
        });

        $.validator.addMethod("regex", function (value, element, regexp) {
            return this.optional(element) || new RegExp(regexp).test(value);
        }, "");

        $.validator.addMethod("sum255", function (value, element, parameter) {
            var relatedElement = $("." + parameter);
            return (value + relatedElement.val()).length < 255;
        }, getI18nText("register.name.invalid"));

        jQuery.extend(jQuery.validator.messages, {
            minlength: jQuery.validator.format(getI18nText("validation.minlength"))
        });

        var validationParams = {
            rules: {
                country: "required",
                company: {
                    required: true,
                    minlength: 2,
                    maxlength: 64
                },
                userName: {
                    required: true
                },
                email: {
                    required : true,
                    email: true
                },
                mobileNumber: {
                    required: true,
                    digits: true,
                    maxlength: 255
                },
                pwd: {
                    required: true,
                    regex: backendRegex
                },
                checkPwd: {
                    required: true,
                    equalTo: "#quickregistration\\.password"
                },
                termsAndConditionsChecked: {
                    required: true
                },
                sector: {
                    required: true
                },
                titleCode: {
                    required: true
                },
                countryCode: {
                    required: true
                }
            },

            messages: {
                firstName: {
                    required: getI18nText("validation.empty"),
                    maxlength: getI18nText("register.name.invalid")
                },
                lastName: {
                    required: getI18nText("validation.empty"),
                    maxlength: getI18nText("register.name.invalid")
                },
                country: {
                    required: getI18nText("validation.empty")
                },
                company: {
                    required: getI18nText("validation.empty"),
                    minlength: getI18nText("register.company.invalid"),
                    maxlength: getI18nText("register.company.invalid")
                },
                userName: {
                    required: getI18nText("validation.empty"),
                    remote: getI18nText("register.validation.userName")
                },
                email: {
                    required: getI18nText("validation.empty"),
                    email: getI18nText("register.email.invalid"),
                    remote: getI18nText("register.validation.email")
                },
                mobileNumber: {
                    required: getI18nText("validation.empty"),
                    digits: getI18nText("register.mobileNumber.invalid"),
                    remote: getI18nText("register.validation.mobileNumber")
                },
                pwd: {
                    required: getI18nText("profile.password.provide"),
                    regex: backendRegexErrorMessage
                },
                checkPwd: {
                    required: getI18nText("profile.password.provide"),
                    equalTo: getI18nText("profile.password.equals")
                },
                termsAndConditionsChecked: {
                    required: getI18nText("acceptTerms.text")
                },
                sector: {
                    required: getI18nText("validation.empty")
                },
                titleCode: {
                    required: getI18nText("validation.empty")
                },
                countryCode: {
                    required: getI18nText("validation.empty")
                }
            },
            invalidHandler: function(event, validator) {
            }
        };

        validationParams.rules.firstName = {
            required: true,
            maxlength: 255,
            sum255: "js-quickregister-lastname"
        };
        validationParams.rules.lastName = {
            required: true,
            maxlength: 255,
            sum255: "js-quickregister-firstname"
        };
        validationParams.rules.checkPwd = {
            required: true,
            equalTo: "#quickregistration\\.password"
        };

        validationParams.rules.email.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                email: function() { return $(".js-register-quick-email").val() }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-register-quick-email");

                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {
                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.email.ok'));
                    // messageElements.show();

                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));

                    return true;
                } else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        validationParams.rules.mobileNumber.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                mobileNumber: function () {
                    return $(".js-quick-mobile-number").val();
                },
                mobileCountryCode: function () {
                    return $(".js-quick-mobile-number").closest('.formInputBox-split').find('.js-mobile-coutry-code').val();
                }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-quick-mobile-number");

                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {
                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.mobileNumber.ok'));
                    // messageElements.show();

                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));

                    return true;
                }
                else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        validationParams.rules.userName.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                userName: function() { return $(".js-register-quick-user-name").val() }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-register-quick-user-name");

                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {

                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.userName.ok'));
                    // messageElements.show();

                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));
                    
                    return true;
                }
                else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        $("#sagiaRegisterFormQuickRegistration").validate(validationParams);

        validationParams.rules.firstName = {
            required: true,
            maxlength: 255,
            sum255: "js-applylicense-lastname"
        };
        validationParams.rules.lastName = {
            required: true,
            maxlength: 255,
            sum255: "js-applylicense-firstname"
        };
        validationParams.rules.checkPwd = {
            required: true,
            equalTo: "#applylicense\\.password"
        };
        validationParams.rules.email.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                email: function() { return $(".js-apply-email").val() }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-apply-email");
                
                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {
                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.email.ok'));
                    // messageElements.show();

                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));

                    return true;
                }
                else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        validationParams.rules.mobileNumber.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                mobileNumber: function () {
                    return $(".js-apply-mobile-number").val();
                },
                mobileCountryCode: function () {
                    return $(".js-apply-mobile-number").closest('.formInputBox-split').find('.js-mobile-coutry-code').val();
                }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-apply-mobile-number");

                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {
                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.mobileNumber.ok'));
                    // messageElements.show();

                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));

                    return true;
                }
                else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        validationParams.rules.userName.remote = {
            type: 'GET',
            url: ACC.config.encodedContextPath + "/register/validation",
            data: {
                userName: function() { return $(".js-apply-user-name").val() }
            },
            cache: false,
            dataFilter: function(data) {
                var $element = $(".js-apply-user-name");
                
                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (data === 'true') {
                    // var messageElements = $element.closest('.formInputBox').find(".js-help-block-success");
                    // messageElements.text(getI18nText('register.validation.userName.ok'));
                    // messageElements.show();
                    
                    $element.closest('.formInputBox').find('input').css('width','90%');
                    $element.closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($element.closest('.formInputBox').find('input'));

                    return true;
                }
                else {
                    $element.closest('.formInputBox').find(".js-help-block-success").empty();
                    return false;
                }
            }
        };
        $("#sagiaRegisterFormApplyLicense").validate(validationParams);

        $(".js-quick-mobile-number, .js-apply-mobile-number, " +
            ".js-register-quick-user-name, .js-apply-user-name, " +
            ".js-register-quick-email, .js-apply-email").on("keyup focus", function() {
            var $element = $(this);
            clearSuccessValidation($element);
        });

        function clearSuccessValidation($element) {
            $element.closest('.formInputBox').find(".js-help-block-success").empty();
        }

        $(".js-select-required").on("change", function() {
            $(this).valid();
        });

        $(".js-apply-license-btn").click(function(event) {
            event.preventDefault();

            var $form = $(this).closest('form');
            var valid = $form.valid();
            if (!valid) {
                return false;
            }

            $form.submit();
        });

        $("#sagiaRegisterFormQuickRegistration").ajaxForm({
            beforeSubmit: function (arr, $form, options) {
                var valid = $form.valid();
                var recaptcha = $("#sagiaRegisterFormQuickRegistration .g-recaptcha-response").val();	
                var lblErrorCaptcha = document.getElementById("lblErrorCaptcha");
                lblErrorCaptcha.innerHTML = "";
                if (recaptcha == "") {
                    lblErrorCaptcha.innerHTML = "Please fill reCAPTCHA";
                    valid = false;
                }	
                if (!valid) {
                    return false;
                }
            },
            success: function (data, status, xhr, form) {
                var validator = form.validate();
                validator.resetForm();
                $(".register-account-investor-screen5").removeClass('next-hide');
                $(".register-account-investor-screen4").addClass('next-hide');

                // var body = $("body");
                // body.toggleClass('page-login-register');
                // $('.accountLogin').show();

                // var $submitModal = $("#requestSubmittedApply");
                // $submitModal.appendTo('body');
                // $submitModal.modal('show');
            }
        });

        $(".quickregistrationPwd").on('focus blur keyup change', function () {
            var passwordSuccessDiv = $(this).closest('.formInputBox').find(".success-message-block");
            setTimeout(function() {
                var formValidation = $("#sagiaRegisterFormQuickRegistration, #sagiaRegisterFormApplyLicense").validate();
                var hasNoError = (formValidation.errorMap === undefined) || (formValidation.errorMap.pwd === undefined && !formValidation.invalid.pwd);
                hasNoError = hasNoError && $("#sagiaRegisterFormQuickRegistration").validate().element(".quickregistrationPwd");

                var $element = $(".quickregistrationPwd");
                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').siblings('.toggle-password1').removeClass('reg-password-success');
                $($element).closest('.formInputBox').find('.verified').remove();
                $(".quickregistrationCheckPwd").attr('disabled',true);

                if(e.type === "change"){
                    var $element = $(".quickregistrationCheckPwd");
                    $element.closest('.formInputBox').find('input').css('width','100%');
                    $element.closest('.formInputBox').find('.form-group').css('display','block');
                    $($element).closest('.formInputBox').siblings('.toggle-password2').removeClass('reg-password-success');
                    $($element).closest('.formInputBox').find('.verified').remove();
                }

                if (hasNoError) {
                    // passwordSuccessDiv.text(getI18nText('register.validation.psw.ok'));
                    
                    $($element).closest('.formInputBox').find("input").css('width','90%');
                    $($element).closest('.formInputBox').siblings('.toggle-password1').addClass('reg-password-success');
                    $($element).closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($($element).closest('.formInputBox').find('input'));
                    
                    $(".quickregistrationCheckPwd").removeAttr('disabled');

                } else {
                    passwordSuccessDiv.empty();
                }
            }, 100);
        });

        //used to remove the success message when having an error message because of using backspace key
        $(".quickregistrationPwd, .quickregistrationCheckPwd").on('blur').keyup(function(e){
            if(e.keyCode === 8) {
                $(this).closest('.formInputBox').find(".success-message-block").empty();
            }
        });

        $(".quickregistrationCheckPwd").on('focus blur keyup', function () {
            var passwordSuccessDiv = $(this).closest('.formInputBox').find(".success-message-block");
                var formValidation = $("#sagiaRegisterFormQuickRegistration, #sagiaRegisterFormApplyLicense").validate();
                var hasNoError = (formValidation.errorMap === undefined) || (formValidation.errorMap.pwd === undefined && !formValidation.invalid.checkPwd);
                hasNoError = hasNoError && $("#sagiaRegisterFormQuickRegistration").validate().element(".quickregistrationCheckPwd");

                var $element = $(".quickregistrationCheckPwd");
                $element.closest('.formInputBox').find('input').css('width','100%');
                $element.closest('.formInputBox').find('.form-group').css('display','block');
                $($element).closest('.formInputBox').siblings('.toggle-password2').removeClass('reg-password-success');
                $($element).closest('.formInputBox').find('.verified').remove();

                if (hasNoError) {
                    // passwordSuccessDiv.text(getI18nText('register.validation.psw.ok2'));
                    $($element).closest('.formInputBox').find("input").css('width','90%');
                    $($element).closest('.formInputBox').siblings('.toggle-password2').addClass('reg-password-success');
                    $($element).closest('.formInputBox').find('.form-group').css('display','flex');
                    $('<div class="verified" id="mobile-verified" style="margin-top: 18px;"></div>').insertAfter($($element).closest('.formInputBox').find('input'));
                } else {
                    passwordSuccessDiv.empty();
                }
        });

        $("#noCrNumberPanel").on("click", function() {
            $.ajax({
                type: "GET",
                url: ACC.config.encodedContextPath + "/realtime/connectivity",
                success: function () {
                    //var availabilityByDayOfWeek = response.availabilityByDayOfWeek;
                    var now = new Date();
                    var yesterday = new Date(now.setDate(now.getDate() - 1));
                    var dateString = $('#dateString');
                    var timeFromString = $('#timeFromString');
                    if(dateString.length > 0) {
                        dateString.flatpickr({
                            disable: [
                                function (date) {
                                    return date < yesterday;
                                },
                                function (date) {
                                    // return true to disable
                                    return (date.getDay() === 5 || date.getDay() === 6);
                                }
                            ],
                            dateFormat: ACC.formatUIDate,
                            onChange: function (selectedDates) {
                                //var now = new Date();
                                //var date = selectedDates[0].getFullYear() + '-' + ('0' + (selectedDates[0].getMonth() + 1)).slice(-2) + '-' + ('0' + selectedDates[0].getDate()).slice(-2);
                                var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
                                var dayName = days[selectedDates[0].getDay()];
                                for (var i = 0; i < SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek.length; i++) {
                                    var n = SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek[i].indexOf(dayName.concat("-"));
                                    if (n >= 0) {
                                        var availability = SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek[i].split('-', 2);
                                        var timeSlot = availability[1];
                                        timeFromString.data("dates-enabled",  timeSlot);
                                        timeFromString.get(0)._resetTimeslots();
                                    }
                                }
                            }
                        });
                    }
                }
            });
        });

        $("#sagia-cms-help-quick-registration-helper").load(ACC.config.encodedContextPath + '/cms/sagia-cms-help-quick-registration main');
        $("#sagia-cms-help-apply-sagia-license").load(ACC.config.encodedContextPath + '/cms/sagia-cms-help-apply-sagia-license main');
        $("#sagia-cms-help-apply-unified-license").load(ACC.config.encodedContextPath + '/cms/sagia-cms-help-apply-unified-license main');
        $("#sagia-cms-help-national-investor-cr").load(ACC.config.encodedContextPath + '/cms/sagia-cms-help-national-investor-cr main');
        $("#sagia-cms-help-national-investor-no-cr").load(ACC.config.encodedContextPath + '/cms/sagia-cms-help-national-investor-no-cr main');
    };

    function clearSuccessValidations() {
        $(".success-message-block").empty();
    }
});

// -- BEGINN Remove-Content --
//helper-method to update data-Attribute given picker
var _updateSlot = function (picker, data) {
    var _dataString = false;
    if (typeof(data) === "string" && data.indexOf(":") >= 0) {
        _dataString = data;
    } else if (data instanceof Array) {
        _dataString = data.join(";");
    }
    if (!_dataString) {
        return;
    }
    picker.data('dates-enabled', _dataString);
    picker.get(0)._resetTimeslots();
};

// command-line test Method
// tested using command:
// _testit($('#test-appointment-slot-picker').parents(".contentModule-section").find('.js-form-control_timeslot'));
// var _testit = function (picker) {
//     var responseFields = $("#checkboxes .my-checkbox:checked");
//     var dataAttr = [];
//     responseFields.each(function () {
//         dataAttr.push($(this).attr('value'));
//     });
//     _updateSlot(picker, dataAttr);
// };
// -- END Remove-Content --


function recaptchaCallback(){
	$(".js-recaptcha-captchaaddon").siblings('span#lblErrorCaptcha').text('');	
}