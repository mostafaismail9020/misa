var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};

$(function() {
    var shareholdersQM1 = $("#shareholdersQM1");

    SAGIA.license.apply.handleShareholdersQM1 = function() {
        var shareholdersQM1 = $("#shareholdersQM1");
        loadIdAndGenderDropDown();
        loadQeemah1Data();
        loadIsicSections(shareholdersQM1.find("#companySection"));
        var shareholderQM1TableSection = $("#shareholderQM1TableSection");
        var addShareholderQM1NoShareholderSection = $("#addShareholderQM1NoShareholderSection");
        
        $("#addShareholderQM1NewPersonSection").find("#verifyDetailsShow").data("nicVerified","false");
        $("#addShareholderQM1NewOrganizationSection").find("#verifyDetailsShow").data("nicVerified","false");

        if(SAGIA.license.apply.data.qeemah1Data.existingShareholders.length ||
            SAGIA.license.apply.data.qeemah1Data.newShareholders.length) {
            addShareholderQM1NoShareholderSection.hide();
            shareholderQM1TableSection.show();
            shareholderQM1TableSection.find(".addExistingButton").prop("disabled", false);
            shareholderQM1TableSection.find(".addNewButton").prop("disabled", false);
            $("#shareholdersNextButton").prop("disabled", false);

            var draft = SAGIA.license.apply.draft;
            if (draft && SAGIA.license.apply.draft.isPresent && !draft.handledForShareholders) {
                redrawShareholderQM1TableSection();
                draft.handledForShareholders = true;
            }

        } else {
            addShareholderQM1NoShareholderSection.show();
            addShareholderQM1NoShareholderSection.find(".addExistingButton").prop("disabled", false);
            addShareholderQM1NoShareholderSection.find(".addNewButton").prop("disabled", false);
            $("#shareholdersNextButton").prop("disabled", false);
            shareholderQM1TableSection.hide();
        }
    };

    $(document).on('change', "#companySection", function() {
        var shareholdersQM1 = $("#shareholdersQM1");
        if($(this).val()) {
            loadIsicDivisions(shareholdersQM1.find("#companyDivision"), $(this).val());
        }
    });
    
    $(document).on('change', "#companyCountry", function() {
    	var shareholdersQM1 = $("#shareholdersQM1");
    	var newFileSection = $("#companyCheckFileAttachment");
    	if($(this).val()) {
			newFileSection.hide();
            loadNewFile(shareholdersQM1.find("#companyCountry"), newFileSection);
        }
    });
    
    /*$(document).on('change', "#country", function() {
    	var shareholdersQM1 = $("#shareholdersQM1");
    	var newFileSection = $("#personCheckFileAttachment");
    	if($(this).val()) {
			newFileSection.hide();
            loadNewFile(shareholdersQM1.find("#country"), newFileSection);
        }
    });*/
    
    var loadNewFile = function(countryElement, newFileSection) {
    	
    	//var newFileSection = $("#newFileSection");
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/shareHolderCountryCheck", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var selectedCountry = countryElement.val();
                jsonData.forEach(function (currentValue) {
                	if(selectedCountry == currentValue.code){
                		newFileSection.show();
                    }
                });
            }
        });
    };

    var loadQeemah1Data = function() {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah1", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var shareholdersQM1 = $("#shareholdersQM1");
                var academicTitle = shareholdersQM1.find("#academicTitle"); //??? academicTitle - education
                var previousAcademicTitle = academicTitle.val();
                academicTitle.find("option").remove();
                academicTitle.append(new Option("", "", false, false));
                jsonData.education.forEach(function (currentValue) {
                    academicTitle.append(new Option(currentValue.educationText, currentValue.education, false, false));
                });
                if(previousAcademicTitle) {
                    academicTitle.val(previousAcademicTitle).trigger("blur").trigger('change');
                } else {
                    academicTitle.val(null)
                }
                
                var premiumResident = shareholdersQM1.find("#premiumResident"); //??? academicTitle - education
                var previousPremiumResident = premiumResident.val();
                premiumResident.find("option").remove();
                premiumResident.append(new Option("", "", false, false));
                jsonData.premium.forEach(function (currentValue) {
                	premiumResident.append(new Option(currentValue.premiumText, currentValue.premiumStatus, false, false));
                });
                if(previousPremiumResident) {
                	premiumResident.val(previousPremiumResident).trigger("blur").trigger('change');
                } else {
                	premiumResident.val(null)
                }
                
                var professionalLicense = shareholdersQM1.find("#professionalLicense"); //??? professionalLicense
                var previousProfessionalLicense = '';                
                if (professionalLicense.attr('data-value') && professionalLicense.attr('data-value') !== undefined ) {
                	previousProfessionalLicense = professionalLicense.attr('data-value') === 'true' ? 'yes' : 'no';
                }                
                professionalLicense.find("option").remove();
                professionalLicense.append(new Option("", "", false, false));
                
                var companyProfessionalLicense = shareholdersQM1.find("#companyProfessionalLicense"); //??? professionalLicense
                var previousCompanyProfessionalLicense = '';                
                if (companyProfessionalLicense.attr('data-value') && companyProfessionalLicense.attr('data-value') !== undefined ) {
                	previousCompanyProfessionalLicense = companyProfessionalLicense.attr('data-value') === 'true' ? 'yes' : 'no';
                }                
                companyProfessionalLicense.find("option").remove();
                companyProfessionalLicense.append(new Option("", "", false, false));                
                        
                jsonData.professionalLicense.forEach(function (currentValue) {
                	professionalLicense.append(new Option(currentValue.professionalLicenseText, currentValue.professionalLicenseStatus, false, false));
                	companyProfessionalLicense.append(new Option(currentValue.professionalLicenseText, currentValue.professionalLicenseStatus, false, false));
                });
                
                if(previousProfessionalLicense) {
                	professionalLicense.val(previousProfessionalLicense).trigger("blur").trigger('change');
                } else {
                	professionalLicense.val(null)
                }
                
                if(previousCompanyProfessionalLicense) {
                	companyProfessionalLicense.val(previousCompanyProfessionalLicense).trigger("blur").trigger('change');
                } else {
                	companyProfessionalLicense.val(null)
                }                

                var currentNationality = shareholdersQM1.find("#currentNationality");
                var previousCurrentNationality = currentNationality.val();
                currentNationality.find("option").remove();
                currentNationality.append(new Option("", "", false, false));

                var previousNationality = shareholdersQM1.find("#previousNationality");
                var previousPreviousNationality = previousNationality.val();
                previousNationality.find("option").remove();
                previousNationality.append(new Option("", "", false, false));

                var country = shareholdersQM1.find("#country");
                var previousCountry = country.val();
                country.find("option").remove();
                country.append(new Option("", "", false, false));

                var companyCountry = shareholdersQM1.find("#companyCountry");
                var previousCompanyCountry = companyCountry.val();
                companyCountry.find("option").remove();
                companyCountry.append(new Option("", "", false, false));

                var companyCountryOfRegistration = shareholdersQM1.find("#companyCountryOfRegistration");
                var previousCompanyCountryOfRegistration = companyCountryOfRegistration.val();
                companyCountryOfRegistration.find("option").remove();
                companyCountryOfRegistration.append(new Option("", "", false, false));

                var parentCompanyCountry = shareholdersQM1.find("#parentCompanyCountry");
                var previousParentCompanyCountry = parentCompanyCountry.val();
                parentCompanyCountry.find("option").remove();
                parentCompanyCountry.append(new Option("", "", false, false));

                var existingShareholderParentCompanyCountry = shareholdersQM1.find("#existingShareholderParentCompanyCountry");
                var previousExistingShareholderParentCompanyCountry = existingShareholderParentCompanyCountry.val();
                existingShareholderParentCompanyCountry.find("option").remove();
                existingShareholderParentCompanyCountry.append(new Option("", "", false, false));
                
                var organizationDelegateCountry = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateCountry");
                var previousOrganizationDelegateCountry = organizationDelegateCountry.val();
                organizationDelegateCountry.find("option").remove();
                organizationDelegateCountry.append(new Option("", "", false, false));
                
                var organizationDelegateNationality = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateNationality");
                var previousOrganizationDelegateNationality = organizationDelegateNationality.val();
                organizationDelegateNationality.find("option").remove();
                organizationDelegateNationality.append(new Option("", "", false, false));

                var personDelegateCountry = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateCountry");
                var previousPersonDelegateCountry = personDelegateCountry.val();
                personDelegateCountry.find("option").remove();
                personDelegateCountry.append(new Option("", "", false, false));
                
                var personDelegateNationality = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateNationality");
                var previousPersonDelegateNationality = personDelegateNationality.val();
                personDelegateNationality.find("option").remove();
                personDelegateNationality.append(new Option("", "", false, false));
                
                jsonData.countries.forEach(function (currentValue) {
                    currentNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    previousNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    country.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    companyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    companyCountryOfRegistration.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    parentCompanyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    existingShareholderParentCompanyCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    organizationDelegateNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    organizationDelegateCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                    personDelegateNationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false));
                    personDelegateCountry.append(new Option(currentValue.countryText, currentValue.country, false, false));
                });
                if(previousCurrentNationality) {
                    currentNationality.val(previousCurrentNationality).trigger("blur").trigger('change');
                } else {
                    currentNationality.val(null)
                }
                if(previousPreviousNationality) {
                    previousNationality.val(previousPreviousNationality).trigger("blur").trigger('change');
                } else {
                    previousNationality.val(null)
                }
                if(previousCountry) {
                    country.val(previousCountry).trigger("blur").trigger('change');
                } else {
                    country.val(null)
                }
                if(previousCompanyCountry) {
                    companyCountry.val(previousCompanyCountry).trigger("blur").trigger('change');
                } else {
                    companyCountry.val(null);
                }
                if(previousCompanyCountryOfRegistration) {
                    companyCountryOfRegistration.val(previousCompanyCountryOfRegistration).trigger("blur").trigger('change');
                } else {
                    companyCountryOfRegistration.val(null)
                }
                if(previousParentCompanyCountry) {
                    parentCompanyCountry.val(previousParentCompanyCountry).trigger("blur").trigger('change');
                } else {
                    parentCompanyCountry.val(null);
                }
                if(previousExistingShareholderParentCompanyCountry) {
                    existingShareholderParentCompanyCountry.val(previousExistingShareholderParentCompanyCountry).trigger("blur").trigger('change');
                } else {
                    existingShareholderParentCompanyCountry.val(null);
                }
                
                if(previousPersonDelegateCountry) {
                	personDelegateCountry.val(previousPersonDelegateCountry).trigger("blur").trigger('change');
                } else {
                	personDelegateCountry.val(null);
                }
                if(previousPersonDelegateNationality) {
                	personDelegateNationality.val(previousPersonDelegateNationality).trigger("blur").trigger('change');
                } else {
                	personDelegateNationality.val(null);
                }
                
                if(previousOrganizationDelegateCountry) {
                	organizationDelegateCountry.val(previousOrganizationDelegateCountry).trigger("blur").trigger('change');
                } else {
                	organizationDelegateCountry.val(null);
                }
                if(previousOrganizationDelegateNationality) {
                	organizationDelegateNationality.val(previousOrganizationDelegateNationality).trigger("blur").trigger('change');
                } else {
                	organizationDelegateNationality.val(null);
                }

                var organizationLegalStatus = shareholdersQM1.find("#organizationLegalStatus");
                var previousOrganizationLegalStatus = organizationLegalStatus.val();
                organizationLegalStatus.find("option").remove();
                organizationLegalStatus.append(new Option("", "", false, false));

                jsonData.legalStatus.forEach(function (currentValue) {
                    organizationLegalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false));
                });
                if(previousOrganizationLegalStatus) {
                    organizationLegalStatus.val(previousOrganizationLegalStatus).trigger("blur").trigger('change');
                } else {
                    organizationLegalStatus.val(null);
                }

                var multinationalCompany = shareholdersQM1.find("#multinationalCompany");
                var previousMultinationalCompany = multinationalCompany.val();
                multinationalCompany.find("option").remove();
                multinationalCompany.append(new Option("", "", false, false));
                jsonData.multinationalCompany.forEach(function (currentValue) {
                    multinationalCompany.append(new Option(currentValue.multinationalCompanyText, currentValue.multinationalCompany, false, false));
                });
                if(previousMultinationalCompany) {
                    multinationalCompany.val(previousMultinationalCompany).trigger("blur").trigger('change');
                } else {
                    multinationalCompany.val(null);
                }
            }
        });
    };

    var loadIsicSections = function(isicSectionsElement) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicSectionsQDF/" +
            (SAGIA.license.apply.data.licenseType ? SAGIA.license.apply.data.licenseType : 1), {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var previousIsicSectionsElement = isicSectionsElement.val();
                isicSectionsElement.find("option").remove();
                isicSectionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicSectionsElement.append(new Option(currentValue.sectionDescription, currentValue.sectionNumber, false, false));
                });
                if(previousIsicSectionsElement) {
                    isicSectionsElement.val(previousIsicSectionsElement).trigger("blur").trigger('change');
                }  else {
                    isicSectionsElement.val(null);
                }
            }
        });
    };
    var loadIsicDivisions = function(isicDivisionsElement, sectionId) {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/isicDivisionsQDF/" +
                (SAGIA.license.apply.data.licenseType ? SAGIA.license.apply.data.licenseType : 1) + "/" + sectionId, {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);
                var previousIsicDivisionsElement = isicDivisionsElement.val();
                isicDivisionsElement.find("option").remove();
                isicDivisionsElement.append(new Option("", "", false, false));
                jsonData.forEach(function (currentValue) {
                    isicDivisionsElement.append(new Option(currentValue.divisionDescription, currentValue.divisionNumber, false, false));
                });
                if(previousIsicDivisionsElement) {
                    isicDivisionsElement.val(previousIsicDivisionsElement).trigger("blur").trigger('change');
                } else {
                    isicDivisionsElement.val(null);
                }
            }
        });
    };

    var errorResponseModal = $('#errorResponseModal');
    $('#validateAddShareholder').attr('disabled', 'disabled');

    $(document).on("blur", "#existingShareholderEntityNumber", function() {
        var shareholderEntityNumber = $('#existingShareholderEntityNumber').val();

        if(!$("#addShareholderQM1ExistingSection").data("id")) {
            var shareholderExists = SAGIA.license.apply.data.qeemah1Data.existingShareholders.filter(function (shareholder) {
                return shareholder.entityNumber === shareholderEntityNumber;
            });

            if (shareholderExists.length) {
                $('#existingShareholderEntityNumber').val('');
                errorResponseModal.find('.modal-description').text(getI18nText("license.existingShareholder.alreadyAdded"));
                errorResponseModal.modal('show');
                return;
            }
        }

        if(shareholderEntityNumber) {
            $.get({
                url: ACC.config.encodedContextPath + controllerUrl + "/" + shareholderEntityNumber
            }).done(function (response) {
                var shareholderExists = JSON.parse(response);
                if (shareholderExists) {
                    $('#validateAddShareholder').removeAttr('disabled');
                    $("#existingShareholderName").val(shareholderExists.bpName).attr("title", shareholderExists.bpName);
                    if(shareholderExists.bpName) {
                        $("#existingShareholderName").removeClass("placeholder-shown"); //fix for ie11
                    }
                    if (shareholderExists.companycountry) {
                        $("#existingShareholderParentCompanyCountry").val(shareholderExists.companycountry).trigger("blur").trigger("change").attr("title", shareholderExists.companycountry);
                    } else {
                        $("#existingShareholderParentCompanyCountry").val("SA").trigger("blur").trigger("change").attr("title", "");
                    }
                } else {
                    $('#validateAddShareholder').attr('disabled', 'disabled');
                    errorResponseModal.find('.modal-description').text(getI18nText("license.existingShareholder.notFound"));
                    errorResponseModal.modal('show');
                }
            }).fail(function (error) {
                $('#validateAddShareholder').attr('disabled', 'disabled');
                errorResponseModal.find('.modal-description').text(getI18nText("general.error"));
                errorResponseModal.modal('show');
            });
        }
    });

/*    $(document).on("change", "#addShareholderQM1NewOrganizationSection #organizationLegalStatus", function () {
        var sharesPercentage = shareholdersQM1.find("#companySharesPercentage");
        if($(this).find(":selected").val() === 'BRFC'){
            sharesPercentage.val(100);
            sharesPercentage.attr('disabled', 'disabled');
            sharesPercentage.removeClass("placeholder-shown"); //fix for ie11
        } else {
            sharesPercentage.val("");
            sharesPercentage.removeAttr('disabled');
        }
    });*/

    $(document).on("click", "#shareholdersQM1 .addExistingButton", function (e) {
        var shareholdersQM1 = $("#shareholdersQM1");
        var addShareholderQM1ExistingSection = $("#addShareholderQM1ExistingSection");
        shareholdersQM1.find(".addExistingButton").prop("disabled", true);
        shareholdersQM1.find(".addNewButton").prop("disabled", true);
        $("#shareholdersNextButton").prop("disabled", true);
        $('#validateAddShareholder').prop('disabled', true);
        addShareholderQM1ExistingSection.show();
    });

    $(document).on("click", "#shareholdersQM1 .addNewButton", function(e){
        var shareholdersQM1 = $("#shareholdersQM1");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        shareholdersQM1.find(".addExistingButton").prop("disabled", true);
        shareholdersQM1.find(".addNewButton").prop("disabled", true);
        $("#shareholdersNextButton").prop("disabled", true);
        addShareholderQM1NewSection.show();
        if(SAGIA.license.apply.data.basicInformationExtended.legalStatus === "BRFC") {
            $("#shareholdersQM1 #shareholderType #personType").parent().hide();
        } else {
            $("#shareholdersQM1 #shareholderType #personType").parent().show();
        }
        setMobileCode("#addShareholderQM1NewPersonSection #country", "#countryCodeForTelephone", "#countryCodeForMobile" );
        setMobileCode("#addShareholderQM1NewPersonSection #delegateCountry", "#addShareholderQM1NewPersonSection #delegateCountryCodeForTelephone", "#addShareholderQM1NewPersonSection #delegateCountryCodeForMobile");
        
    });

    $(document).on("click", "#addShareholderQM1ExistingSection .addButton", function(e) {
        var addShareholderQM1ExistingSection = $("#addShareholderQM1ExistingSection");
        var addShareholderQM1NoShareholderSection = $("#addShareholderQM1NoShareholderSection");
        var shareholderQM1TableSection = $("#shareholderQM1TableSection");

        var id = addShareholderQM1ExistingSection.data("id");
        var type = addShareholderQM1ExistingSection.data("type");

        var commercialRegistrationFileName = addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFileName").val().replace("C:\\fakepath\\", "");
        if (commercialRegistrationFileName === "") {
            commercialRegistrationFileName = addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFileName").attr('placeholder');
        }
        var lastBudgetFileName = addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFileName").val().replace("C:\\fakepath\\", "");
        if (lastBudgetFileName === "") {
            lastBudgetFileName = addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFileName").attr('placeholder');
        }

        var existingNationality = addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").find(":selected").text();
        var shareholder = {
            id: new Date().getTime(),
            entityNumber: addShareholderQM1ExistingSection.find("#existingShareholderEntityNumber").val(),
            name: addShareholderQM1ExistingSection.find("#existingShareholderName").val(),
            parentCompanyCountry: addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").val(),
            parentCompanyCountryText: existingNationality ? existingNationality : "-",
            sharesPercentage: addShareholderQM1ExistingSection.find("#existingShareholderSharesPercentage").val(),
            commercialRegistrationFile: addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFile").val(),
            commercialRegistrationFileName: commercialRegistrationFileName,
            lastBudgetFile: addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFile").val(),
            lastBudgetFileName: lastBudgetFileName
        };


        var temp = {arr: SAGIA.license.apply.data.qeemah1Data};
        var qeemah1Data = $.extend(true, {}, temp);
        var existingShareholders = qeemah1Data.arr.existingShareholders;

        if (id) {
            var editedShareholder = getShareholderDataByIdFromArray(type, id, existingShareholders);
            editedShareholder.sharesPercentage = 0;

            shareholder.commercialRegistrationFileMimeType = editedShareholder.commercialRegistrationFileMimeType;
            shareholder.commercialRegistrationFile = editedShareholder.commercialRegistrationFile

        }


        var commercialRegistrationFileReady = false;
        commercialRegistrationFile = addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFile").val();
        //handle attachments
        if (commercialRegistrationFile) {
            var fileReaderCommercialRegistrationFile = new FileReader();
            fileReaderCommercialRegistrationFile.onload = function (event) {
                shareholder.commercialRegistrationFileMimeType = event.target._TYPE;
                shareholder.commercialRegistrationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                commercialRegistrationFileReady = true;
            };
            fileReaderCommercialRegistrationFile._TYPE = $("#existingShareholderCommercialRegistrationFile")[0].files[0].type;
            fileReaderCommercialRegistrationFile.readAsDataURL($("#existingShareholderCommercialRegistrationFile")[0].files[0]);
        }

        var lastBudgetFileReady = false;
        lastBudgetFile = addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFile").val();

        if (lastBudgetFile) {
            var fileReaderLastBudgetFile = new FileReader();
            fileReaderLastBudgetFile.onload = function (event) {
                shareholder.lastBudgetFileMimeType = event.target._TYPE;
                shareholder.lastBudgetFile = removeBase64prefix(event.target._TYPE, event.target.result);
                lastBudgetFileReady = true;
            };
            fileReaderLastBudgetFile._TYPE = $("#existingShareholderLastBudgetFile")[0].files[0].type;
            fileReaderLastBudgetFile.readAsDataURL($("#existingShareholderLastBudgetFile")[0].files[0]);
        }


        existingShareholders.push(shareholder);

        var qeemah1InfoTemp = { arr : SAGIA.license.apply.data};
        var qeemah1InfoData = $.extend(true, {}, qeemah1InfoTemp);
        var shareholdersExistingQm1Info = qeemah1InfoData.arr.qeemah1Data.existingShareholders;
        shareholdersExistingQm1Info.push(shareholder);

        var validationAndSaving = function () {
            if ((!commercialRegistrationFile || (commercialRegistrationFile && commercialRegistrationFileReady)) && (!lastBudgetFile || (lastBudgetFile && lastBudgetFileReady))){
                var promise = [];
            promise.push(validateLicenseData('#addShareholderQM1ExistingSection', 'existing-shareholder-qeemah1', qeemah1InfoData.arr, function () {
            }));

            promise.push(validateLicenseData('#addShareholderQM1ExistingSection', 'existing-shareholder-percentage-QM1', qeemah1Data.arr, function () {
                },
                function (xhr, status, error) {
                    var formErrors = xhr.responseJSON.formErrors;
                    $.each(formErrors, function (key, errorMessage) {
                        $("#addShareholderQM1ExistingSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                        $("#addShareholderQM1ExistingSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                    });
                }
            ));

            $.when.apply($, promise).done(function (results) {
                if (id) {
                    var existingNationality = addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").find(":selected").text();
                    shareholder = getShareholderDataById(type, id);
                    shareholder.entityNumber = addShareholderQM1ExistingSection.find("#existingShareholderEntityNumber").val();
                    shareholder.name = addShareholderQM1ExistingSection.find("#existingShareholderName").val();
                    shareholder.parentCompanyCountry = addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").val();
                    shareholder.parentCompanyCountryText = existingNationality ? existingNationality : "-";
                    shareholder.sharesPercentage = addShareholderQM1ExistingSection.find("#existingShareholderSharesPercentage").val();
                    shareholder.commercialRegistrationFileName = commercialRegistrationFileName;
                    shareholder.lastBudgetFileName = lastBudgetFileName;

                }

                shareholder.hasDelegateInfo = "false";
                if (!id) {
                    SAGIA.license.apply.data.qeemah1Data.existingShareholders.push(shareholder);
                }

                clearFormFieldsExisting();

                addShareholderQM1ExistingSection.hide();
                addShareholderQM1NoShareholderSection.hide();
                shareholderQM1TableSection.show();
                shareholderQM1TableSection.find(".addExistingButton").prop("disabled", false);
                shareholderQM1TableSection.find(".addNewButton").prop("disabled", false);
                $("#shareholdersNextButton").prop("disabled", false);
                redrawShareholderQM1TableSection();
            });

            return;

        }

            setTimeout(validationAndSaving, 1000);

        }


    validationAndSaving();

        });
    var removeShareholder = function(shareholderId) {
        for(var indexExistingShareholder = 0; indexExistingShareholder < SAGIA.license.apply.data.qeemah1Data.existingShareholders.length; indexExistingShareholder++) {
            var existingShareholder = SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholder];
            if(existingShareholder.id === shareholderId) {
                SAGIA.license.apply.data.qeemah1Data.existingShareholders.splice(indexExistingShareholder, 1);
                return;
            }
        }
        for(var indexNewShareholder = 0; indexNewShareholder < SAGIA.license.apply.data.qeemah1Data.newShareholders.length; indexNewShareholder++) {
            var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[indexNewShareholder];
            if(newShareholder.id === shareholderId) {
                SAGIA.license.apply.data.qeemah1Data.newShareholders.splice(indexNewShareholder, 1);
                return;
            }
        }
    };
    var getShareholderDataById = function(type, shareholderId) {
        if(type === "existing") {
            for(var indexExistingShareholder = 0; indexExistingShareholder < SAGIA.license.apply.data.qeemah1Data.existingShareholders.length; indexExistingShareholder++) {
                var existingShareholder = SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholder];
                if(existingShareholder.id === shareholderId) {
                    return existingShareholder;
                }
            }
        } else if(type === 'Person' || type === 'Organization') {
            for(var indexNewShareHolder = 0; indexNewShareHolder < SAGIA.license.apply.data.qeemah1Data.newShareholders.length; indexNewShareHolder++) {
                var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[indexNewShareHolder];
                if(newShareholder.id === shareholderId) {
                    return newShareholder;
                }
            }
        }
        return null;
    };

    var getShareholderDataByIdFromArray = function(type, shareholderId, array) {
        for(var indexShareholder = 0; indexShareholder < array.length; indexShareholder++) {
            var shareholder = array[indexShareholder];
            if (shareholder.id === shareholderId) {
                return shareholder;
            }
        }
        return null;
    };

    $(document).on("click", "#shareholderQM1TableSection .removeButton", function() {
        var trElement = $(this).parents("tr");
        removeShareholder(trElement.data("id"));
        trElement.remove();
        SAGIA.license.apply.handleShareholdersQM1();
    });
    $(document).on("click", "#shareholderQM1TableSection .editButton", function() {
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1ExistingSection = $("#addShareholderQM1ExistingSection");
        
        addShareholderQM1NewOrganizationSection.hide();
        addShareholderQM1NewPersonSection.hide();
        addShareholderQM1ExistingSection.hide();
        
        var trElement = $(this).parents("tr");
        var id = trElement.data("id");
        var type = trElement.data("type");
        var shareholder = getShareholderDataById(type, id);
        if(type === "existing") {
            addShareholderQM1ExistingSection.data("id", id);
            addShareholderQM1ExistingSection.data("type", type);
            $('#validateAddShareholder').removeAttr('disabled');
            addShareholderQM1ExistingSection.find("#existingShareholderEntityNumber").val(shareholder.entityNumber);
            addShareholderQM1ExistingSection.find("#existingShareholderName").val(shareholder.name);
            addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").val(shareholder.parentCompanyCountry);
            addShareholderQM1ExistingSection.find("#existingShareholderSharesPercentage").val(shareholder.sharesPercentage);

            if(shareholder.commercialRegistrationFileName) {
                addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFileName").attr("placeholder", shareholder.commercialRegistrationFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFile").closest(".formInputFile").addClass("active");
            }

            if(shareholder.lastBudgetFileName) {
                addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFileName").attr("placeholder", shareholder.lastBudgetFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFile").closest(".formInputFile").addClass("active");
            }
            addShareholderQM1ExistingSection.show();
        } else if(type === 'Person') {
            addShareholderQM1NewPersonSection.data("id", id);
            addShareholderQM1NewPersonSection.data("type", type);
            addShareholderQM1NewSection.find("#shareholderType input[value='Person']").prop("checked", true);
            addShareholderQM1NewPersonSection.find("#shareholderTitle input[value='" + shareholder.title + "']").prop("checked", true);
            addShareholderQM1NewPersonSection.find("#academicTitle").val(shareholder.academicTitle).trigger("blur").trigger("change");
            addShareholderQM1NewPersonSection.find("#premiumResident").val(shareholder.premiumResident).trigger("blur").trigger("change");
            addShareholderQM1NewPersonSection.find("#firstNameArabic").val(shareholder.firstNameArabic);
            addShareholderQM1NewPersonSection.find("#lastNameArabic").val(shareholder.lastNameArabic);
            addShareholderQM1NewPersonSection.find("#fullNameEnglish").val(shareholder.fullNameEnglish);
            addShareholderQM1NewPersonSection.find("#personSharesPercentage").val(shareholder.sharesPercentage);
            addShareholderQM1NewPersonSection.find("#dateOfBirth").val(shareholder.dateOfBirth);
            addShareholderQM1NewPersonSection.find("#passportNumber").val(shareholder.passportNumber);
            addShareholderQM1NewPersonSection.find("#passportIssueDate").val(shareholder.passportIssueDate);
            addShareholderQM1NewPersonSection.find("#passportExpiryDate").val(shareholder.passportExpiryDate);
            addShareholderQM1NewPersonSection.find("#currentNationality").val(shareholder.currentNationality.val).trigger("blur").trigger("change");
            addShareholderQM1NewPersonSection.find("#previousNationality").val(shareholder.previousNationality).trigger("blur").trigger("change");
            addShareholderQM1NewPersonSection.find("#country").val(shareholder.country).trigger("blur").trigger("change");
            addShareholderQM1NewPersonSection.find("#city").val(shareholder.city);
            addShareholderQM1NewPersonSection.find("#postalCode").val(shareholder.postalCode);
            addShareholderQM1NewPersonSection.find("#poBox").val(shareholder.poBox);
            addShareholderQM1NewPersonSection.find("#countryCodeForTelephone").val(shareholder.countryCodeForTelephone);
            addShareholderQM1NewPersonSection.find("#telephone").val(shareholder.telephone);
            addShareholderQM1NewPersonSection.find("#countryCodeForMobile").val(shareholder.countryCodeForMobile);
            addShareholderQM1NewPersonSection.find("#mobile").val(shareholder.mobile);
            addShareholderQM1NewPersonSection.find("#email").val(shareholder.email);
            //addShareholderQM1NewPersonSection.find("#passportFile").val(shareholder.passportFile); //it's a file input, cannot be set
            if(shareholder.passportFileName) {
                addShareholderQM1NewPersonSection.find("#passportFileName").attr("placeholder", shareholder.passportFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewPersonSection.find("#passportFile").closest(".formInputFile").addClass("active");
            }
            //addShareholderQM1NewPersonSection.find("#otherFile").val(shareholder.otherFile); //it's a file input, cannot be set
            if(shareholder.otherFileName) {
                addShareholderQM1NewPersonSection.find("#otherFileName").attr("placeholder", shareholder.otherFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewPersonSection.find("#otherFile").closest(".formInputFile").addClass("active");
            }
            /*if(shareholder.personMemoAssociationFileName) {
                addShareholderQM1NewPersonSection.find("#personMemoAssociationFileName").attr("placeholder", shareholder.personMemoAssociationFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").closest(".formInputFile").addClass("active");
            }*/
            
            if(shareholder.hasDelegateInfo==="true")
            {
            	addShareholderQM1NewPersonSection.find("#PersonDelegateYES").click();
            }
            else
            {
            	addShareholderQM1NewPersonSection.find("#PersonDelegateNO").click();
            }
            
            if(shareholder.hasDelegateInfo==="true" && shareholder.selfDelegate == "false"){
            	addShareholderQM1NewPersonSection.find("#hasDelegateNO").click();
            	addShareholderQM1NewPersonSection.find("#verifyDetailsShow").data('nicVerified', shareholder.delegate.nicVerified);
            	addShareholderQM1NewPersonSection.find("#idType").val(shareholder.delegate.idType).trigger("change");
            	addShareholderQM1NewPersonSection.find("#idNumber").val(shareholder.delegate.idNumber);
            	addShareholderQM1NewPersonSection.find("#delegateDateofBirth").val(shareholder.delegate.dateofBirth);
            	addShareholderQM1NewPersonSection.find("#delegateFirstNameArabic").val(shareholder.delegate.firstNameArabic);
            	addShareholderQM1NewPersonSection.find("#delegateLastNameArabic").val(shareholder.delegate.lastNameArabic);
            	addShareholderQM1NewPersonSection.find("#delegateFullNameEnglish").val(shareholder.delegate.fullNameEnglish);
            	addShareholderQM1NewPersonSection.find("#delegateGender").val(shareholder.delegate.gender).trigger("blur").trigger("change");
            	addShareholderQM1NewPersonSection.find("#delegateExpiryDate").val(shareholder.delegate.expiryDate);
            	addShareholderQM1NewPersonSection.find("#delegateIssueDate").val(shareholder.delegate.issueDate);
            	addShareholderQM1NewPersonSection.find("#delegateCountry").val(shareholder.delegate.country).trigger("blur").trigger("change");
            	addShareholderQM1NewPersonSection.find("#delegateNationality").val(shareholder.delegate.nationality).trigger("blur").trigger("change");
            	addShareholderQM1NewPersonSection.find("#delegatePostalCode").val(shareholder.delegate.postalCode);
            	addShareholderQM1NewPersonSection.find("#delegatePOBox").val(shareholder.delegate.poBox);
            	addShareholderQM1NewPersonSection.find("#delegateCountryCodeForTelephone").val(shareholder.delegate.countryCodeForTelephone);
            	addShareholderQM1NewPersonSection.find("#delegateTelephone").val(shareholder.delegate.telephone);
            	addShareholderQM1NewPersonSection.find("#delegateCountryCodeForMobile").val(shareholder.delegate.countryCodeForMobile);
            	addShareholderQM1NewPersonSection.find("#delegateMobile").val(shareholder.delegate.mobile);
            	addShareholderQM1NewPersonSection.find("#delegateEmail").val(shareholder.delegate.email);
            	addShareholderQM1NewPersonSection.find("#verifyDetailsShow").click();
      
            	if(shareholder.delegate.authorizationLetterFile) {
                    addShareholderQM1NewPersonSection.find("#authorizationLetterFileName").attr("placeholder", shareholder.delegate.authorizationLetterFileName.replace("C:\\fakepath\\", ""));
                    addShareholderQM1NewPersonSection.find("#authorizationLetterFile").closest(".formInputFile").addClass("active");
                }
            	
            	if(shareholder.delegate.idCopyFileName) {
                    addShareholderQM1NewPersonSection.find("#idCopyFileName").attr("placeholder", shareholder.delegate.idCopyFileName.replace("C:\\fakepath\\", ""));
                    addShareholderQM1NewPersonSection.find("#idCopyFile").closest(".formInputFile").addClass("active");
                }
            }
            else{
            	addShareholderQM1NewPersonSection.find("#hasDelegateYES").click();
            }
            setMobileCode("#addShareholderQM1NewPersonSection #country", "#countryCodeForTelephone", "#countryCodeForMobile" );
            setMobileCode("#addShareholderQM1NewPersonSection #delegateCountry", "#addShareholderQM1NewPersonSection #delegateCountryCodeForTelephone", "#addShareholderQM1NewPersonSection #delegateCountryCodeForMobile");
            
            addShareholderQM1NewPersonSection.show();
        } else if(type === 'Organization') {
        	shareholder.selfDelegate = "false";
            addShareholderQM1NewOrganizationSection.data("id", id);
            addShareholderQM1NewOrganizationSection.data("type", type);
            addShareholderQM1NewSection.find("#shareholderType input[value='Organization']").prop("checked", true);
            addShareholderQM1NewOrganizationSection.find("#organizationNameEnglish").val(shareholder.organizationNameEnglish);
            addShareholderQM1NewOrganizationSection.find("#organizationNameArabic").val(shareholder.organizationNameArabic);
            addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").val(shareholder.organizationLegalStatus).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#multinationalCompany").val(shareholder.multinationalCompany).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#companyRegistrationNumber").val(shareholder.companyRegistrationNumber);
            addShareholderQM1NewOrganizationSection.find("#companyCapital").val(shareholder.companyCapital);
            addShareholderQM1NewOrganizationSection.find("#companySharesPercentage").val(shareholder.companySharesPercentage);
            addShareholderQM1NewOrganizationSection.find("#companySection").val(shareholder.companySection).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#companyDivision").val(shareholder.companyDivision).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#parentCompanyName").val(shareholder.parentCompanyName);
            addShareholderQM1NewOrganizationSection.find("#parentCompanyCountry").val(shareholder.parentCompanyCountry).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#companyCountry").val(shareholder.companyCountry).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#companyCountryOfRegistration").val(shareholder.companyCountryOfRegistration).trigger("blur").trigger("change");
            addShareholderQM1NewOrganizationSection.find("#companyCity").val(shareholder.companyCity);
            addShareholderQM1NewOrganizationSection.find("#companyAddress").val(shareholder.companyAddress);
            addShareholderQM1NewOrganizationSection.find("#companyPostalCode").val(shareholder.companyPostalCode);
            addShareholderQM1NewOrganizationSection.find("#companyPOBox").val(shareholder.companyPOBox);
            addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForTelephone").val(shareholder.companyCountryCodeForTelephone);
            addShareholderQM1NewOrganizationSection.find("#companyTelephone").val(shareholder.companyTelephone);
            addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForMobile").val(shareholder.companyCountryCodeForMobile);
            addShareholderQM1NewOrganizationSection.find("#companyMobile").val(shareholder.companyMobile);
            addShareholderQM1NewOrganizationSection.find("#companyEmail").val(shareholder.companyEmail);
            addShareholderQM1NewOrganizationSection.find("#companyWebsite").val(shareholder.companyWebsite);
            //addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").val(shareholder.companyRegistrationFile); //it's a file input, cannot be set
            if(shareholder.companyRegistrationFileName) {
                addShareholderQM1NewOrganizationSection.find("#companyRegistrationFileName").attr("placeholder", shareholder.companyRegistrationFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").closest(".formInputFile").addClass("active");
            }
            //addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").val(shareholder.companyFinancialStatementFile); //it's a file input, cannot be set
            if(shareholder.companyFinancialStatementFileName) {
                addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFileName").attr("placeholder", shareholder.companyFinancialStatementFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").closest(".formInputFile").addClass("active");
            }
            if(shareholder.companyMemoAssociationFileName) {
                addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFileName").attr("placeholder", shareholder.companyMemoAssociationFileName.replace("C:\\fakepath\\", ""));
                addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").closest(".formInputFile").addClass("active");
            }
            
            addShareholderQM1NewOrganizationSection.find("#hasDelegateNO").click();
            addShareholderQM1NewOrganizationSection.find("#showDelegateQuestion").hide();
            if(shareholder.hasDelegateInfo==="true")
            {
            	addShareholderQM1NewOrganizationSection.find("#OrganizationDelegateYES").click();
            	addShareholderQM1NewOrganizationSection.find("#verifyDetailsShow").data('nicVerified', shareholder.delegate.nicVerified);
            	addShareholderQM1NewOrganizationSection.find("#idType").val(shareholder.delegate.idType).trigger("change");
            	addShareholderQM1NewOrganizationSection.find("#idNumber").val(shareholder.delegate.idNumber);
            	addShareholderQM1NewOrganizationSection.find("#delegateDateofBirth").val(shareholder.delegate.dateofBirth);
            	addShareholderQM1NewOrganizationSection.find("#delegateFirstNameArabic").val(shareholder.delegate.firstNameArabic);
            	addShareholderQM1NewOrganizationSection.find("#delegateLastNameArabic").val(shareholder.delegate.lastNameArabic);
            	addShareholderQM1NewOrganizationSection.find("#delegateFullNameEnglish").val(shareholder.delegate.fullNameEnglish);
            	addShareholderQM1NewOrganizationSection.find("#delegateGender").val(shareholder.delegate.gender).trigger("blur").trigger("change");
            	addShareholderQM1NewOrganizationSection.find("#delegateExpiryDate").val(shareholder.delegate.expiryDate);
            	addShareholderQM1NewOrganizationSection.find("#delegateIssueDate").val(shareholder.delegate.issueDate);
            	addShareholderQM1NewOrganizationSection.find("#delegateCountry").val(shareholder.delegate.country).trigger("blur").trigger("change");
            	addShareholderQM1NewOrganizationSection.find("#delegateNationality").val(shareholder.delegate.nationality).trigger("blur").trigger("change");
            	addShareholderQM1NewOrganizationSection.find("#delegatePostalCode").val(shareholder.delegate.postalCode);
            	addShareholderQM1NewOrganizationSection.find("#delegatePOBox").val(shareholder.delegate.poBox);
            	addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForTelephone").val(shareholder.delegate.countryCodeForTelephone);
            	addShareholderQM1NewOrganizationSection.find("#delegateTelephone").val(shareholder.delegate.telephone);
            	addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForMobile").val(shareholder.delegate.countryCodeForMobile);
            	addShareholderQM1NewOrganizationSection.find("#delegateMobile").val(shareholder.delegate.mobile);
            	addShareholderQM1NewOrganizationSection.find("#delegateEmail").val(shareholder.delegate.email);
            	addShareholderQM1NewOrganizationSection.find("#verifyDetailsShow").click();
            	
            	if(shareholder.delegate.authorizationLetterFile) {
            		addShareholderQM1NewOrganizationSection.find("#authorizationLetterFileName").attr("placeholder", shareholder.delegate.authorizationLetterFileName.replace("C:\\fakepath\\", ""));
            		addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile").closest(".formInputFile").addClass("active");
                }
            	
            	if(shareholder.delegate.authorizationLetterFile) {
            		addShareholderQM1NewOrganizationSection.find("#idCopyFileName").attr("placeholder", shareholder.delegate.idCopyFileName.replace("C:\\fakepath\\", ""));
            		addShareholderQM1NewOrganizationSection.find("#idCopyFile").closest(".formInputFile").addClass("active");
                }
            }
            else
            {
            	addShareholderQM1NewOrganizationSection.find("#OrganizationDelegateNO").click();
            }
        	
            setMobileCode("#addShareholderQM1NewOrganizationSection #country", "#countryCodeForTelephone", "#countryCodeForMobile" );
            setMobileCode("#addShareholderQM1NewOrganizationSection #delegateCountry", "#addShareholderQM1NewOrganizationSection #delegateCountryCodeForTelephone", "#addShareholderQM1NewOrganizationSection #delegateCountryCodeForMobile");

            addShareholderQM1NewOrganizationSection.show();
        }
        shareholdersQM1.find(".addNewButton").prop("disabled", true);
        shareholdersQM1.find(".addExistingButton").prop("disabled", true);
        $("#shareholdersNextButton").prop("disabled", true);
    });
    var redrawShareholderQM1TableSection = function() {
    	
    	if($("#existingShareholderParentCompanyCountry").children("option").length < 1)
    	{
    		//to check all drop down is updated with local lang, as we need to draw table using local translation
    		setTimeout(redrawShareholderQM1TableSection,1500);
    		return;
    	}
    	
        for(var indexExistingShareholders = 0; indexExistingShareholders < SAGIA.license.apply.data.qeemah1Data.existingShareholders.length; indexExistingShareholders++) {
        	SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholders].hasDelegateInfo = "false"; //to avoid null pointer for existing draft data in production
        	var existingShareholder = SAGIA.license.apply.data.qeemah1Data.existingShareholders[indexExistingShareholders];
            var tableTrExistingShareholder = $("#shareholderQM1TableSection table tbody tr[data-id='" + existingShareholder.id + "']");
            var parentCompanyCountryText = $("#existingShareholderParentCompanyCountry option[value='"+existingShareholder.parentCompanyCountry+"']").text();
            
            if(tableTrExistingShareholder.length) {
                tableTrExistingShareholder.empty().append("" +
                    "<td title='" + existingShareholder.name + "'>" + existingShareholder.name + "</td>" +
                    "<td>"+getI18nText("shareholder.label.existing")+"</td>" +
                    "<td>" + existingShareholder.sharesPercentage + "%</td>" +
                    "<td title='" + parentCompanyCountryText + "'>" + parentCompanyCountryText + "</td>" +
                    "<td>-</td>" +
                    "<td>-</td>" + 
                    "<td>-</td>" +
                    "<td class='tableModule-bodyItem-action'>" +
                    '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                    '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                    "</td>");
            } else {
                $("#shareholderQM1TableSection table tbody").append("" +
                    "<tr data-id='" + existingShareholder.id + "' data-type='existing'>" +
                    "   <td title='" + existingShareholder.name + "'>" + existingShareholder.name + "</td>" +
                    "   <td>"+getI18nText("shareholder.label.existing")+"</td>" +
                    "   <td>" + existingShareholder.sharesPercentage + "%</td>" +
                    "   <td title='" + parentCompanyCountryText + "'>" + parentCompanyCountryText + "</td>" +
                    "   <td>-</td>" +
                    "	<td>-</td>" +
                    "	<td>-</td>" + 
                    "   <td class='tableModule-bodyItem-action'>" +
                    '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                    '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                    "   </td>" +
                    "</tr>");
            }
        }
        for(var indexNewShareholder = 0; indexNewShareholder < SAGIA.license.apply.data.qeemah1Data.newShareholders.length; indexNewShareholder++) {
            var newShareholder = SAGIA.license.apply.data.qeemah1Data.newShareholders[indexNewShareholder];
            var tableTrNewShareholder = $("#shareholderQM1TableSection table tbody tr[data-id='" + newShareholder.id + "']");
            if(tableTrNewShareholder.length) {
                if(newShareholder.type === "Person") {
                	var currentNationalityText = $("#currentNationality option[value='"+newShareholder.currentNationality.val+"']").text();
                    var nameEnglishAndArabicPerson = newShareholder.firstNameArabic + " " + newShareholder.lastNameArabic + " (" + newShareholder.fullNameEnglish + ")";
                    var delegateNamePerson = newShareholder.delegate.fullNameEnglish || '-';
                    var delegateIdPerson = newShareholder.delegate.idNumber || '-';
                    tableTrNewShareholder.empty().append("" +
                        "<td title='" + nameEnglishAndArabicPerson + "'>" + nameEnglishAndArabicPerson + "</td>" +
                        "<td>"+getI18nText("shareholder.label.person")+"</td>" +
                        "<td>" + newShareholder.sharesPercentage + "%</td>" +
                        "<td>" + currentNationalityText + "</td>" +
                        "<td>-</td>" +
                        "<td>"+delegateNamePerson+"</td>" +
                        "<td>"+delegateIdPerson+"</td>" +
                        "<td class='tableModule-bodyItem-action'>" +
                        '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                        '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                        "</td>");
                } else if(newShareholder.type === "Organization") {
                	var companyCountryText = $("#companyCountry option[value='"+newShareholder.companyCountry+"']").text();
                	var organizationLegalStatusText = $("#organizationLegalStatus option[value='"+newShareholder.organizationLegalStatus+"']").text();
                    var nameEnglishAndArabicOrganization = newShareholder.organizationNameArabic + " (" + newShareholder.organizationNameEnglish + ")";
                    var delegateNameOrganization = newShareholder.delegate.fullNameEnglish || '-';
                    var delegateIdOrganization = newShareholder.delegate.idNumber || '-';
                    tableTrNewShareholder.empty().append("" +
                        "<td title='" + nameEnglishAndArabicOrganization + "'>" + nameEnglishAndArabicOrganization + "</td>" +
                        "<td>"+getI18nText("shareholder.label.organization")+"</td>" +
                        "<td>" + newShareholder.companySharesPercentage + "%</td>" +
                        "<td title='" + companyCountryText + "'>" + companyCountryText + "</td>" +
                        "<td>" + organizationLegalStatusText + "</td>" +
                        "<td>"+delegateNameOrganization+"</td>" +
                        "<td>"+delegateIdOrganization+"</td>" +
                        "<td class='tableModule-bodyItem-action'>" +
                        '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                        '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                        "</td>");
                } else {
                    SAGIA.showError("unknown shareholder type");
                }
            } else {
                if (newShareholder.type === "Person") {
                	var currentNationalityText = $("#currentNationality option[value='"+newShareholder.currentNationality.val+"']").text();
                	 var delegateNamePersonNew = newShareholder.delegate.fullNameEnglish || '-';
                     var delegateIdPersonNew = newShareholder.delegate.idNumber || '-';
                    var nameEnglishAndArabicPersonNew = newShareholder.firstNameArabic + " " + newShareholder.lastNameArabic + " (" + newShareholder.fullNameEnglish + ")";
                    $("#shareholderQM1TableSection table tbody").append("" +
                        "<tr data-id='" + newShareholder.id + "' data-type='" + newShareholder.type + "'>" +
                        "   <td title='" + nameEnglishAndArabicPersonNew + "'>" + nameEnglishAndArabicPersonNew + "</td>" +
                        "   <td>"+getI18nText("shareholder.label.person")+"</td>" +
                        "   <td>" + newShareholder.sharesPercentage + "%</td>" +
                        "   <td>" + currentNationalityText + "</td>" +
                        "   <td>-</td>" +
                        "<td>"+delegateNamePersonNew+"</td>" +
                        "<td>"+delegateIdPersonNew+"</td>" +
                        "   <td class='tableModule-bodyItem-action'>" +
                        '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                        '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                        "   </td>" +
                        "</tr>");
                } else if (newShareholder.type === "Organization") {
                	var companyCountryText = $("#companyCountry option[value='"+newShareholder.companyCountry+"']").text();
                	var organizationLegalStatusText = $("#organizationLegalStatus option[value='"+newShareholder.organizationLegalStatus+"']").text();
                	var delegateNameOrganizationNew = newShareholder.delegate.fullNameEnglish || '-';
                    var delegateIdOrganizationNew = newShareholder.delegate.idNumber || '-';
                	var nameEnglishAndArabicOrganizationNew = newShareholder.organizationNameArabic + " (" + newShareholder.organizationNameEnglish + ")";
                    $("#shareholderQM1TableSection table tbody").append("" +
                        "<tr data-id='" + newShareholder.id + "' data-type='" + newShareholder.type + "'>" +
                        "   <td title='" + nameEnglishAndArabicOrganizationNew + "'>" + nameEnglishAndArabicOrganizationNew + "</td>" +
                        "   <td>"+getI18nText("shareholder.label.organization")+"</td>" +
                        "   <td>" + newShareholder.companySharesPercentage + "%</td>" +
                        "   <td title='" + companyCountryText + "'>" + companyCountryText + "</td>" +
                        "   <td>" + organizationLegalStatusText + "</td>" +
                        "<td>"+delegateNameOrganizationNew+"</td>" +
                        "<td>"+delegateIdOrganizationNew+"</td>" +
                        "   <td class='tableModule-bodyItem-action'>" +
                        '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                        '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
                        "   </td>" +
                        "</tr>");
                } else {
                    SAGIA.showError("unknown shareholder type");
                }
            }
        }
    };

    var clearFormFieldsExisting = function() {
        var addShareholderQM1ExistingSection = $("#addShareholderQM1ExistingSection");
        addShareholderQM1ExistingSection.find("#existingShareholderEntityNumber").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderName").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderParentCompanyCountry").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderSharesPercentage").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFile").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
        addShareholderQM1ExistingSection.find("#existingShareholderCommercialRegistrationFileName").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFile").val("");
        addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
        addShareholderQM1ExistingSection.find("#existingShareholderLastBudgetFileName").val("");
        addShareholderQM1ExistingSection.data("id", null);
    };

    $(document).on("click", "#addShareholderQM1ExistingSection .cancelButton", function(e) {
        var addShareholderQM1ExistingSection = $("#addShareholderQM1ExistingSection");
        var shareholdersQM1 = $("#shareholdersQM1");
        addShareholderQM1ExistingSection.hide();
        removeErrorIfExists();
        clearFormFieldsExisting();
        shareholdersQM1.find(".addExistingButton").prop("disabled", false);
        shareholdersQM1.find(".addNewButton").prop("disabled", false);
        $("#shareholdersNextButton").prop("disabled", false);
    });

    $(document).on("click", "#addShareholderQM1NewSection .cancelButton", function(e) {
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var shareholdersQM1 = $("#shareholdersQM1");
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        addShareholderQM1NewSection.hide();
        removeErrorIfExists();
        resetShareholderQM1NewOrganizationForm();
        resetShareholderQM1NewPersonForm();
        shareholdersQM1.find(".addExistingButton").prop("disabled", false);
        shareholdersQM1.find(".addNewButton").prop("disabled", false);
        $("#shareholdersNextButton").prop("disabled", false);
        addShareholderQM1NewSection.find("input").prop("checked", null);
        addShareholderQM1NewPersonSection.hide();
        addShareholderQM1NewOrganizationSection.hide();
    });

    var removeErrorIfExists = function () {
        $('#shareholdersQM1 .form-group').each(function () {
            if ($(this).hasClass('has-error')) {
                $(this).removeClass('has-error');
            }

            if ($(this).find('.help-block').length) {
                $(this).find('.help-block').text('');
            }

            if ($(this).closest('.formSelectBox').length) {
                $(this).closest('.formSelectBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputBox').length) {
                $(this).closest('.formInputBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputFile').length) {
                $(this).closest('.formInputFile').find('.help-block').text('');
            }

            if ($(this).closest('.formRadioBox').length) {
                $(this).closest('.formRadioBox').find('.help-block').text('');
            }
        });
    };

    $(document).on("click", "#addShareholderQM1NewSection #personType", function(e) {
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        addShareholderQM1NewPersonSection.show();
        addShareholderQM1NewOrganizationSection.hide();
        $("#shareholdersQM1 #shareholderType #organizationType").parent().hide();
    });
    $(document).on("click", "#addShareholderQM1NewSection #organizationType", function(e){
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        addShareholderQM1NewPersonSection.hide();
        addShareholderQM1NewOrganizationSection.show();
        addShareholderQM1NewOrganizationSection.find("#showDelegateQuestion").hide();
        toggleDelegateDetails(addShareholderQM1NewOrganizationSection,true);
        setMobileCode("#addShareholderQM1NewOrganizationSection #companyCountry", "#companyCountryCodeForTelephone", "#companyCountryCodeForMobile");
        setMobileCode("#addShareholderQM1NewOrganizationSection #delegateCountry", "#addShareholderQM1NewOrganizationSection #delegateCountryCodeForTelephone", "#addShareholderQM1NewOrganizationSection #delegateCountryCodeForMobile");
        $("#shareholdersQM1 #shareholderType #personType").parent().hide();
    });

    $(document).on("click","#addShareholderQM1NewPersonSection .cancelButton", function(e) {
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        addShareholderQM1NewSection.find("input").prop("checked", null);
        addShareholderQM1NewPersonSection.hide();
        removeErrorIfExists();
        resetShareholderQM1NewPersonForm();
    });

    $(document).on("click", "#addShareholderQM1NewPersonSection .addButton", function(){
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1NoShareholderSection = $("#addShareholderQM1NoShareholderSection");
        var id = addShareholderQM1NewPersonSection.data("id");
        var type = addShareholderQM1NewPersonSection.data("type");
        
        var validate = validateRadioButtons(addShareholderQM1NewPersonSection,"Person");
        if(!validate){
        	return false;
        }
        var shareholder = undefined;
        var newPassportFile = undefined;
        var newOtherFile = undefined;
        //var newpersonMemoAssociationFile = undefined;
        var selectedNationality = addShareholderQM1NewPersonSection.find("#currentNationality option:selected").text();
       	var currentNationality = { 
  			val: addShareholderQM1NewPersonSection.find("#currentNationality").val(),
  			text: selectedNationality ? selectedNationality : "-"
       	};
       	var newAuthorizationLetterFile=undefined;
       	var newIdCopyFile= undefined;
       	
//       	if(!addShareholderQM1NewPersonSection.find("#verifyDetailsShow").data('clicked'))
//       	{
//       		addShareholderQM1NewPersonSection.find("verifyDelegateDetails").show();
//       		/*$("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
//            $("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);*/
//       	}
       	
        if(!id) {
            shareholder = extractNewPersonShareholder(0);
            shareholder.id = new Date().getTime();


            //handle attachments
            var newPassportFileReady = false;
            newPassportFile = addShareholderQM1NewPersonSection.find("#passportFile").val();
            if(newPassportFile) {
                var fileReaderPasswordFile = new FileReader();
                fileReaderPasswordFile.onload = function (event) {

                    shareholder.passportFile = event.target._TYPE;
                    shareholder.passportFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newPassportFileReady = true;
                };
                fileReaderPasswordFile._TYPE = $("#passportFile")[0].files[0].type;
                fileReaderPasswordFile.readAsDataURL($("#passportFile")[0].files[0]);
            }

            var newOtherFileReady = false;
            newOtherFile = addShareholderQM1NewPersonSection.find("#otherFile").val();

            if(newOtherFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {

                    shareholder.otherFileMimeType = event.target._TYPE;
                    shareholder.otherFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newOtherFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#otherFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#otherFile")[0].files[0]);
            }
            
           /* var newPersonMemoAssociationFileReady = false;
            newPersonMemoAssociationFile = addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").val();

            if(newPersonMemoAssociationFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {

                    shareholder.personMemoAssociationFileMimeType = event.target._TYPE;
                    shareholder.personMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newPersonMemoAssociationFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#personMemoAssociationFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#personMemoAssociationFile")[0].files[0]);
            }*/
            
            var newIdCopyFileReady = false;

            newIdCopyFile = addShareholderQM1NewPersonSection.find("#idCopyFile").val();

            if(newIdCopyFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholder.delegate.idCopyFileMimeType = event.target._TYPE;
                    shareholder.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newIdCopyFileReady = true;
                };
                fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0]);
            }
            
            var newAuthorizationLetterFileReady = false;

            newAuthorizationLetterFile = addShareholderQM1NewPersonSection.find("#authorizationLetterFile").val();

            if(newAuthorizationLetterFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholder.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                    shareholder.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newAuthorizationLetterFileReady = true;
                };
                fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0]);
            }


            var temp = { arr : SAGIA.license.apply.data.qeemah1Data };
            var qeemah1Data = $.extend(true, {}, temp);
            var shareholders = qeemah1Data.arr.newShareholders;
            shareholders.push(shareholder);


            var validationAndSaving = function() {
                if((!newPassportFile || (newPassportFile && newPassportFileReady)) && (!newOtherFile || (newOtherFile && newOtherFileReady)) && (!newIdCopyFile || (newIdCopyFile && newIdCopyFileReady)) && (!newAuthorizationLetterFile || (newAuthorizationLetterFile && newAuthorizationLetterFileReady))) {

                    var promise = [];
                    promise.push(validateLicenseData('#addShareholderQM1NewPersonSection', 'new-shareholder-qeemah1-person', shareholder, function(){}));

                    promise.push(validateLicenseData('#addShareholderQM1NewPersonSection', 'new-shareholder-percentage-QM1',qeemah1Data.arr , function () {}, function(xhr, status, error) {
                    var formErrors = xhr.responseJSON.formErrors;
                    $.each(formErrors, function(key, errorMessage){
                        $("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                        $("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                    });
                    }));

                     $.when.apply($, promise).done(function (results) {

                    SAGIA.license.apply.data.qeemah1Data.newShareholders.push(shareholder);
                    resetShareholderQM1NewPersonForm();
                    removeErrorIfExists();
                     });
            return;

            }

            setTimeout(validationAndSaving, 1000);

        }


        validationAndSaving();

    } else {
            var temp = { arr : SAGIA.license.apply.data.qeemah1Data };
            var qeemah1Data = $.extend(true, {}, temp);
            var shareholders = qeemah1Data.arr.newShareholders;

            shareholder = getShareholderDataByIdFromArray(type, id, shareholders);
            shareholder.sharesPercentage = 0;


           var shareholderModified = extractNewPersonShareholder(id);

           //keep the previous attachments in the case the attachments are not modified
            shareholderModified.otherFileMimeType = shareholder.otherFileMimeType;
            shareholderModified.otherFile = shareholder.otherFile;

            shareholderModified.passportFileMimeType = shareholder.passportFileMimeType;
            shareholderModified.passportFile = shareholder.passportFile;
            
            /*shareholderModified.personMemoAssociationFileMimeType = shareholder.personMemoAssociationFileMimeType;
            shareholderModified.personMemoAssociationFile = shareholder.personMemoAssociationFile;*/
            
            shareholderModified.delegate.idCopyFileMimeType = shareholder.delegate.idCopyFileMimeType;
            shareholderModified.delegate.idCopyFile = shareholder.delegate.idCopyFile;
            
            shareholderModified.delegate.authorizationLetterFileMimeType = shareholder.delegate.authorizationLetterFileMimeType;
            shareholderModified.delegate.authorizationLetterFile = shareholder.delegate.authorizationLetterFile;

            //handle new attachments

            var newPassportFileReady = false;

            newPassportFile = addShareholderQM1NewPersonSection.find("#passportFile").val();

            if(newPassportFile) {
                var fileReaderPasswordFile = new FileReader();
                fileReaderPasswordFile.onload = function (event) {
                    shareholderModified.passportFileMimeType = event.target._TYPE;
                    shareholderModified.passportFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newPassportFileReady = true;
                };
                fileReaderPasswordFile._TYPE = $("#passportFile")[0].files[0].type;
                fileReaderPasswordFile.readAsDataURL($("#passportFile")[0].files[0]);
            };

            var newOtherFileReady = false;

            newOtherFile = addShareholderQM1NewPersonSection.find("#otherFile").val();

            if(newOtherFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholderModified.otherFileMimeType = event.target._TYPE;
                    shareholderModified.otherFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newOtherFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#otherFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#otherFile")[0].files[0]);
            }
            
           /* var newPersonMemoAssociationFileReady = false;

            newPersonMemoAssociationFile = addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").val();

            if(newPersonMemoAssociationFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholderModified.personMemoAssociationFileMimeType = event.target._TYPE;
                    shareholderModified.personMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newPersonMemoAssociationFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#personMemoAssociationFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#personMemoAssociationFile")[0].files[0]);
            }*/
            
            var newIdCopyFileReady = false;

            newIdCopyFile = addShareholderQM1NewPersonSection.find("#idCopyFile").val();

            if(newIdCopyFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                	shareholderModified.delegate.idCopyFileMimeType = event.target._TYPE;
                	shareholderModified.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newIdCopyFileReady = true;
                };
                fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0]);
            }
            
            var newAuthorizationLetterFileReady = false;

            newAuthorizationLetterFile = addShareholderQM1NewPersonSection.find("#authorizationLetterFile").val();

            if(newAuthorizationLetterFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                	shareholderModified.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                	shareholderModified.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newAuthorizationLetterFileReady = true;
                };
                fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0]);
            }

            shareholders.push(shareholderModified);



      var validationAndSaving = function() {
         if((!newPassportFile || (newPassportFile && newPassportFileReady)) && (!newOtherFile || (newOtherFile && newOtherFileReady)) && (!newIdCopyFile || (newIdCopyFile && newIdCopyFileReady)) && (!newAuthorizationLetterFile || (newAuthorizationLetterFile && newAuthorizationLetterFileReady))) {
            var promise = [];
            promise.push(validateLicenseData('#addShareholderQM1NewPersonSection', 'new-shareholder-qeemah1-person', shareholderModified, function () {
            }));
            promise.push(validateLicenseData('#addShareholderQM1NewPersonSection', 'new-shareholder-percentage-QM1', qeemah1Data.arr, function () {
             },
             function (xhr, status, error) {
                 var formErrors = xhr.responseJSON.formErrors;
                 $.each(formErrors, function (key, errorMessage) {
                     $("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                     $("#addShareholderQM1NewPersonSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                 });
             }
            ));

            $.when.apply($, promise).done(function (results) {
            	var existingDelegateCountry = addShareholderQM1NewPersonSection.find("#delegateCountry").find(":selected").text();
                var existingDelegateNationality = addShareholderQM1NewPersonSection.find("#delegateNationality").find(":selected").text();
             shareholder = getShareholderDataById(type, id);
             shareholder.type = addShareholderQM1NewSection.find("#shareholderType input:checked").val();
             shareholder.title = addShareholderQM1NewPersonSection.find("#shareholderTitle input:checked").val();
             shareholder.academicTitle = addShareholderQM1NewPersonSection.find("#academicTitle").val();
             shareholder.premiumResident = addShareholderQM1NewPersonSection.find("#premiumResident").val();
             shareholder.firstNameArabic = addShareholderQM1NewPersonSection.find("#firstNameArabic").val();
             shareholder.lastNameArabic = addShareholderQM1NewPersonSection.find("#lastNameArabic").val();
             shareholder.fullNameEnglish = addShareholderQM1NewPersonSection.find("#fullNameEnglish").val();
             shareholder.sharesPercentage = addShareholderQM1NewPersonSection.find("#personSharesPercentage").val();
             shareholder.dateOfBirth = addShareholderQM1NewPersonSection.find("#dateOfBirth").val();
             shareholder.passportNumber = addShareholderQM1NewPersonSection.find("#passportNumber").val();
             shareholder.passportIssueDate = addShareholderQM1NewPersonSection.find("#passportIssueDate").val();
             shareholder.passportExpiryDate = addShareholderQM1NewPersonSection.find("#passportExpiryDate").val();
             shareholder.currentNationality = currentNationality;
             shareholder.previousNationality = addShareholderQM1NewPersonSection.find("#previousNationality").val();
             shareholder.country = addShareholderQM1NewPersonSection.find("#country").val();
             shareholder.city = addShareholderQM1NewPersonSection.find("#city").val();
             shareholder.postalCode = addShareholderQM1NewPersonSection.find("#postalCode").val();
             shareholder.poBox = addShareholderQM1NewPersonSection.find("#poBox").val();
             shareholder.countryCodeForTelephone = addShareholderQM1NewPersonSection.find("#countryCodeForTelephone").val();
             shareholder.telephone = addShareholderQM1NewPersonSection.find("#telephone").val();
             shareholder.countryCodeForMobile = addShareholderQM1NewPersonSection.find("#countryCodeForMobile").val();
             shareholder.mobile = addShareholderQM1NewPersonSection.find("#mobile").val();
             shareholder.email = addShareholderQM1NewPersonSection.find("#email").val();
             shareholder.hasDelegateInfo = addShareholderQM1NewPersonSection.find("input[name='hasDelegateInfo']:checked").val();
             shareholder.selfDelegate = addShareholderQM1NewPersonSection.find("input[name='hasDelegate']:checked").val();
             //delegate information
             
             shareholder.delegate.nicVerified = addShareholderQM1NewPersonSection.find("#verifyDetailsShow").data("nicVerified");
             shareholder.delegate.idType = addShareholderQM1NewPersonSection.find("#idType").val();
             shareholder.delegate.idNumber = addShareholderQM1NewPersonSection.find("#idNumber").val();
             shareholder.delegate.dateofBirth = addShareholderQM1NewPersonSection.find("#delegateDateofBirth").val();
             shareholder.delegate.firstNameArabic = addShareholderQM1NewPersonSection.find("#delegateFirstNameArabic").val();
             shareholder.delegate.lastNameArabic = addShareholderQM1NewPersonSection.find("#delegateLastNameArabic").val();
             shareholder.delegate.fullNameEnglish = addShareholderQM1NewPersonSection.find("#delegateFullNameEnglish").val();
             shareholder.delegate.gender = addShareholderQM1NewPersonSection.find("#delegateGender").val();
             shareholder.delegate.expiryDate = addShareholderQM1NewPersonSection.find("#delegateExpiryDate").val();
             shareholder.delegate.issueDate = addShareholderQM1NewPersonSection.find("#delegateIssueDate").val();
             shareholder.delegate.country = addShareholderQM1NewPersonSection.find("#delegateCountry").val();
             shareholder.delegate.countryText =  existingDelegateCountry ?existingDelegateCountry : "-";
             shareholder.delegate.nationality = addShareholderQM1NewPersonSection.find("#delegateNationality").val();
             shareholder.delegate.nationalityText =  existingDelegateNationality ?existingDelegateNationality : "-";
             shareholder.delegate.postalCode = addShareholderQM1NewPersonSection.find("#delegatePostalCode").val();
             shareholder.delegate.poBox = addShareholderQM1NewPersonSection.find("#delegatePOBox").val();
             shareholder.delegate.countryCodeForTelephone = addShareholderQM1NewPersonSection.find("#delegateCountryCodeForTelephone").val();
             shareholder.delegate.telephone = addShareholderQM1NewPersonSection.find("#delegateTelephone").val();
             shareholder.delegate.countryCodeForMobile = addShareholderQM1NewPersonSection.find("#delegateCountryCodeForMobile").val();
             shareholder.delegate.mobile = addShareholderQM1NewPersonSection.find("#delegateMobile").val();
             shareholder.delegate.email = addShareholderQM1NewPersonSection.find("#delegateEmail").val();
             
             newPassportFile = addShareholderQM1NewPersonSection.find("#passportFile").val();
             if (newPassportFile) {
                 shareholder.passportFile = newPassportFile;
                 shareholder.passportFileName = addShareholderQM1NewPersonSection.find("#passportFileName").val();
             }
             newOtherFile = addShareholderQM1NewPersonSection.find("#otherFile").val();
             if (newOtherFile) {
                 shareholder.otherFile = newOtherFile;
                 shareholder.otherFileName = addShareholderQM1NewPersonSection.find("#otherFileName").val();
             }
             
            /* newPersonMemoAssociationFile = addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").val();
             if (newPersonMemoAssociationFile) {
                 shareholder.personMemoAssociationFile = newPersonMemoAssociationFile;
                 shareholder.personMemoAssociationFileName = addShareholderQM1NewPersonSection.find("#personMemoAssociationFileName").val();
             }*/
             
             newAuthorizationLetterFile = addShareholderQM1NewPersonSection.find("#authorizationLetterFile").val();
             if (newAuthorizationLetterFile) {
                 shareholder.delegate.authorizationLetterFile = newAuthorizationLetterFile;
                 shareholder.delegate.authorizationLetterFileName = addShareholderQM1NewPersonSection.find("#authorizationLetterFile").val();
             }
             newIdCopyFile = addShareholderQM1NewPersonSection.find("#idCopyFile").val();
             if (newIdCopyFile) {
                 shareholder.delegate.idCopyFile = idCopyFile;
                 shareholder.delegate.idCopyFileName = addShareholderQM1NewPersonSection.find("#idCopyFile").val();
             }
             //handle attachments
             if (newPassportFile) {
                 var fileReaderPasswordFile = new FileReader();
                 fileReaderPasswordFile.onload = function (event) {
                     shareholder.passportFileName = shareholder.passportFile;
                     shareholder.passportFileMimeType = event.target._TYPE;
                     shareholder.passportFile = removeBase64prefix(event.target._TYPE, event.target.result);
                 };
                 fileReaderPasswordFile._TYPE = $("#passportFile")[0].files[0].type;
                 fileReaderPasswordFile.readAsDataURL($("#passportFile")[0].files[0]);
             }
             if (newOtherFile) {
                 var fileReaderOtherFile = new FileReader();
                 fileReaderOtherFile.onload = function (event) {
                     shareholder.otherFileName = shareholder.otherFile;
                     shareholder.otherFileMimeType = event.target._TYPE;
                     shareholder.otherFile = removeBase64prefix(event.target._TYPE, event.target.result);
                 };
                 fileReaderOtherFile._TYPE = $("#otherFile")[0].files[0].type;
                 fileReaderOtherFile.readAsDataURL($("#otherFile")[0].files[0]);
             }
             /*if (newPersonMemoAssociationFile) {
                 var fileReaderOtherFile = new FileReader();
                 fileReaderOtherFile.onload = function (event) {
                     shareholder.personMemoAssociationFileName = shareholder.personMemoAssociationFile;
                     shareholder.personMemoAssociationFileMimeType = event.target._TYPE;
                     shareholder.personMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                 };
                 fileReaderOtherFile._TYPE = $("#personMemoAssociationFile")[0].files[0].type;
                 fileReaderOtherFile.readAsDataURL($("#personMemoAssociationFile")[0].files[0]);
             }*/
             
             if(newAuthorizationLetterFile) {
                 var fileReaderOtherFile = new FileReader();
                 fileReaderOtherFile.onload = function (event) {
                     shareholder.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                     shareholder.delegate.authorizationLetterFileName = shareholder.delegate.authorizationLetterFileName;
                     shareholder.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                 };
                 fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0].type;
                 fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#authorizationLetterFile")[0].files[0]);
             }
             if(newIdCopyFile) {
                 var fileReaderOtherFile = new FileReader();
                 fileReaderOtherFile.onload = function (event) {
                 	shareholder.delegate.idCopyFileMimeType = event.target._TYPE;
                 	shareholder.delegate.idCopyFileName = shareholder.delegate.idCopyFileName;
                 	shareholder.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                 };
                 fileReaderOtherFile._TYPE = addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0].type;
                 fileReaderOtherFile.readAsDataURL(addShareholderQM1NewPersonSection.find("#idCopyFile")[0].files[0]);
             }
             addShareholderQM1NewPersonSection.data("id", 0);
             resetShareholderQM1NewPersonForm();
             removeErrorIfExists();
         });

         return;

         }

          setTimeout(validationAndSaving, 1000);

    }


    validationAndSaving();


        }

    });

    function extractNewPersonShareholder(id) {
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var selectedNationality = addShareholderQM1NewPersonSection.find("#currentNationality option:selected").text();
        var currentNationality = {
            val: addShareholderQM1NewPersonSection.find("#currentNationality").val(),
            text: selectedNationality ? selectedNationality : "-"
        };
        var passportFileName = addShareholderQM1NewPersonSection.find("#passportFileName").val().replace("C:\\fakepath\\", "");
        if(passportFileName === "") {
            passportFileName = addShareholderQM1NewPersonSection.find("#passportFileName").attr('placeholder');
        }
        var otherFileName = addShareholderQM1NewPersonSection.find("#otherFileName").val().replace("C:\\fakepath\\", "");
        if(otherFileName === "") {
            otherFileName = addShareholderQM1NewPersonSection.find("#otherFileName").attr('placeholder');
        }
        
        /*var personMemoAssociationFileName = addShareholderQM1NewPersonSection.find("#personMemoAssociationFileName").val().replace("C:\\fakepath\\", "");
        if(personMemoAssociationFileName === "") {
        	personMemoAssociationFileName = addShareholderQM1NewPersonSection.find("#personMemoAssociationFileName").attr('placeholder');
        }*/
        
        var idCopyFileName = addShareholderQM1NewPersonSection.find("#idCopyFileName").val().replace("C:\\fakepath\\", "");
        if(idCopyFileName === ""){
        	idCopyFileName = addShareholderQM1NewPersonSection.find("#idCopyFileName").attr('placeholder');
        }
        
        var authorizationLetterFileName = addShareholderQM1NewPersonSection.find("#authorizationLetterFileName").val().replace("C:\\fakepath\\", "");
        if(authorizationLetterFileName === ""){
        	authorizationLetterFileName = addShareholderQM1NewPersonSection.find("#authorizationLetterFileName").attr('placeholder');
        }
        
        var delegate = {

        		nicVerified: addShareholderQM1NewPersonSection.find("#verifyDetailsShow").data("nicVerified"),
        		idType: addShareholderQM1NewPersonSection.find("#idType").val(),
            	idNumber: addShareholderQM1NewPersonSection.find("#idNumber").val(),
        		dateofBirth: addShareholderQM1NewPersonSection.find("#delegateDateofBirth").val() ,
                firstNameArabic: addShareholderQM1NewPersonSection.find("#delegateFirstNameArabic").val(),
                lastNameArabic: addShareholderQM1NewPersonSection.find("#delegateLastNameArabic").val(),
                fullNameEnglish: addShareholderQM1NewPersonSection.find("#delegateFullNameEnglish").val(),
                gender: addShareholderQM1NewPersonSection.find("#delegateGender").val(),
                expiryDate: addShareholderQM1NewPersonSection.find("#delegateExpiryDate").val(),
                issueDate: addShareholderQM1NewPersonSection.find("#delegateIssueDate").val(),
                country: addShareholderQM1NewPersonSection.find("#delegateCountry").val(),
                nationality: addShareholderQM1NewPersonSection.find("#delegateNationality").val(),
                postalCode: addShareholderQM1NewPersonSection.find("#delegatePostalCode").val(),
                poBox: addShareholderQM1NewPersonSection.find("#delegatePOBox").val(),
                countryCodeForTelephone: addShareholderQM1NewPersonSection.find("#delegateCountryCodeForTelephone").val(),
                telephone: addShareholderQM1NewPersonSection.find("#delegateTelephone").val(),
                countryCodeForMobile: addShareholderQM1NewPersonSection.find("#delegateCountryCodeForMobile").val(),
                mobile: addShareholderQM1NewPersonSection.find("#delegateMobile").val(),
                email: addShareholderQM1NewPersonSection.find("#delegateEmail").val(),
                idCopyFileName: idCopyFileName,
                authorizationLetterFileName: authorizationLetterFileName
            };
        
        
        return {
            id: id,
            type: addShareholderQM1NewSection.find("#shareholderType input:checked").val(),
            title: addShareholderQM1NewPersonSection.find("#shareholderTitle input:checked").val(),
            academicTitle: addShareholderQM1NewPersonSection.find("#academicTitle").val(),
            premiumResident: addShareholderQM1NewPersonSection.find("#premiumResident").val(),
            firstNameArabic: addShareholderQM1NewPersonSection.find("#firstNameArabic").val(),
            lastNameArabic: addShareholderQM1NewPersonSection.find("#lastNameArabic").val(),
            fullNameEnglish: addShareholderQM1NewPersonSection.find("#fullNameEnglish").val(),
            sharesPercentage: addShareholderQM1NewPersonSection.find("#personSharesPercentage").val(),
            dateOfBirth: addShareholderQM1NewPersonSection.find("#dateOfBirth").val(),
            passportNumber: addShareholderQM1NewPersonSection.find("#passportNumber").val(),
            passportIssueDate: addShareholderQM1NewPersonSection.find("#passportIssueDate").val(),
            passportExpiryDate: addShareholderQM1NewPersonSection.find("#passportExpiryDate").val(),
            currentNationality: currentNationality,
            previousNationality: addShareholderQM1NewPersonSection.find("#previousNationality").val(),
            country: addShareholderQM1NewPersonSection.find("#country").val(),
            city: addShareholderQM1NewPersonSection.find("#city").val(),
            postalCode: addShareholderQM1NewPersonSection.find("#postalCode").val(),
            poBox: addShareholderQM1NewPersonSection.find("#poBox").val(),
            countryCodeForTelephone: addShareholderQM1NewPersonSection.find("#countryCodeForTelephone").val(),
            telephone: addShareholderQM1NewPersonSection.find("#telephone").val(),
            countryCodeForMobile: addShareholderQM1NewPersonSection.find("#countryCodeForMobile").val(),
            mobile: addShareholderQM1NewPersonSection.find("#mobile").val(),
            email: addShareholderQM1NewPersonSection.find("#email").val(),
            passportFileName: passportFileName,
            otherFileName: otherFileName,
            //personMemoAssociationFileName: personMemoAssociationFileName,
            hasDelegateInfo : addShareholderQM1NewPersonSection.find("input[name='hasDelegateInfo']:checked").val(),
            selfDelegate : addShareholderQM1NewPersonSection.find("input[name='hasDelegate']:checked").val(),
            //delegateDetails
            delegate: delegate,
        };
    }

    $(document).on("click", "#addShareholderQM1NewOrganizationSection .cancelButton", function() {
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        addShareholderQM1NewSection.find("input").prop("checked", null);
        addShareholderQM1NewOrganizationSection.hide();
        resetShareholderQM1NewOrganizationForm();
        removeErrorIfExists();
    });

    $(document).on("click", "#addShareholderQM1NewOrganizationSection .addButton", function() {
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var id = addShareholderQM1NewOrganizationSection.data("id");
        var type = addShareholderQM1NewOrganizationSection.data("type");
        var validate = validateRadioButtons(addShareholderQM1NewOrganizationSection,"Organization");
        if(!validate){
        	return false;
        }
        var shareholder = undefined;
        var newCompanyRegistrationFile = undefined;
        var newCompanyFinancialStatementFile = undefined;
        var newCompanyMemoAssociationFile = undefined;
        var newIdCopyFile = undefined;
        var newAuthorizationLetterFile = undefined;
        if(!id) {
            shareholder = extractNewOrganizationShareholder(0);
            shareholder.id = new Date().getTime();
		    shareholder.selfDelegate = "false";
            //handle attachments
            var newCompanyRegistrationFileReady = false;

            newCompanyRegistrationFile = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").val();

            if(newCompanyRegistrationFile) {
                var fileReaderPasswordFile = new FileReader();
                fileReaderPasswordFile.onload = function (event) {
                    shareholder.companyRegistrationFileMimeType = event.target._TYPE;
                    shareholder.companyRegistrationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyRegistrationFileReady = true;
                };
                fileReaderPasswordFile._TYPE = $("#companyRegistrationFile")[0].files[0].type;
                fileReaderPasswordFile.readAsDataURL($("#companyRegistrationFile")[0].files[0]);
            }


            newCompanyFinancialStatementFileReady = false;

            newCompanyFinancialStatementFile = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").val();

            if(newCompanyFinancialStatementFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholder.companyFinancialStatementFileMimeType = event.target._TYPE;
                    shareholder.companyFinancialStatementFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyFinancialStatementFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#companyFinancialStatementFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#companyFinancialStatementFile")[0].files[0]);
            }
            
            newCompanyMemoAssociationFileReady = false;

            newCompanyMemoAssociationFile = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").val();

            if(newCompanyMemoAssociationFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholder.companyMemoAssociationFileMimeType = event.target._TYPE;
                    shareholder.companyMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyMemoAssociationFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#companyMemoAssociationFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#companyMemoAssociationFile")[0].files[0]);
            }
            newIdCopyFileReady = false;

            newIdCopyFile = addShareholderQM1NewOrganizationSection.find("#idCopyFile").val();

            if(newIdCopyFile) {
                var fileReaderIdCopyFile = new FileReader();
                fileReaderIdCopyFile.onload = function (event) {
                    shareholder.delegate.idCopyFileMimeType = event.target._TYPE;
                    shareholder.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newIdCopyFileReady = true;
                };
                fileReaderIdCopyFile._TYPE = addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0].type;
                fileReaderIdCopyFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0]);
            }
            
            newAuthorizationLetterFileReady = false;

            newAuthorizationLetterFile = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile").val();

            if(newAuthorizationLetterFile) {
                var fileReaderAuthorizationFile = new FileReader();
                fileReaderAuthorizationFile.onload = function (event) {
                    shareholder.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                    shareholder.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newAuthorizationLetterFileReady = true;
                };
                fileReaderAuthorizationFile._TYPE = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0].type;
                fileReaderAuthorizationFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0]);
            }

            var temp = { arr : SAGIA.license.apply.data.qeemah1Data };
            var qeemah1Data = $.extend(true, {}, temp);
            var shareholders = qeemah1Data.arr.newShareholders;
            shareholders.push(shareholder);

            var qeemah1InfoTemp = { arr : SAGIA.license.apply.data};
            var qeemah1InfoData = $.extend(true, {}, qeemah1InfoTemp);
            var shareholdersQm1Info = qeemah1InfoData.arr.qeemah1Data.newShareholders;
            shareholdersQm1Info.push(shareholder);

            var validatingAndSaving = function() {
                if((!newCompanyRegistrationFile || (newCompanyRegistrationFile && newCompanyRegistrationFileReady)) && (!newCompanyFinancialStatementFile || (newCompanyFinancialStatementFile && newCompanyFinancialStatementFileReady)) && (!newIdCopyFile || (newIdCopyFile && newIdCopyFileReady)) && (!newAuthorizationLetterFile || (newAuthorizationLetterFile && newAuthorizationLetterFileReady)) ) {
                    var promise = [];
                    promise.push(validateLicenseData('#addShareholderQM1NewOrganizationSection', 'new-shareholder-qeemah1-entity', qeemah1InfoData.arr, function () {
                    }));
                        promise.push(validateLicenseData('#addShareholderQM1NewOrganizationSection', 'new-shareholder-percentage-QM1', qeemah1Data.arr, function () {
                            },
                            function (xhr, status, error) {
                                var formErrors = xhr.responseJSON.formErrors;
                                $.each(formErrors, function (key, errorMessage) {
                                    $("#addShareholderQM1NewOrganizationSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                                    $("#addShareholderQM1NewOrganizationSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                                });
                            }
                        ));
                    $.when.apply($, promise).done(function (results) {
                        SAGIA.license.apply.data.qeemah1Data.newShareholders.push(shareholder);
                        resetShareholderQM1NewOrganizationForm();
                        removeErrorIfExists();
                    });

                    return;
                }

                setTimeout(validatingAndSaving, 1000);
            }

            validatingAndSaving();
        } else {
            var temp = { arr : SAGIA.license.apply.data.qeemah1Data };
            var qeemah1Data = $.extend(true, {}, temp);
            var shareholders = qeemah1Data.arr.newShareholders;

            shareholder = getShareholderDataByIdFromArray(type, id, shareholders);
            shareholder.companySharesPercentage = 0;

            var shareholderModified = extractNewOrganizationShareholder(id);

            //keep the previous attachments in the case the attachments are not modified
            shareholderModified.companyRegistrationFileMimeType = shareholder.companyRegistrationFileMimeType;
            shareholderModified.companyRegistrationFile = shareholder.companyRegistrationFile;

            shareholderModified.companyFinancialStatementFileMimeType = shareholder.companyFinancialStatementFileMimeType;
            shareholderModified.companyFinancialStatementFile = shareholder.companyFinancialStatementFile;
            
            shareholderModified.companyMemoAssociationFileMimeType = shareholder.companyMemoAssociationFileMimeType;
            shareholderModified.companyMemoAssociationFile = shareholder.companyMemoAssociationFile;
            
            if(shareholder.delegate)
            {
	            shareholderModified.delegate.authorizationLetterFileMimeType = shareholder.delegate.authorizationLetterFileMimeType;
	            shareholderModified.delegate.authorizationLetterFile = shareholder.delegate.authorizationLetterFile;
	            
	            shareholderModified.delegate.idCopyFileMimeType = shareholder.delegate.idCopyFileMimeType;
	            shareholderModified.delegate.idCopyFile = shareholder.delegate.idCopyFile;
            }
            
            // handle new attachments
            var newCompanyRegistrationFileReady = false;

            newCompanyRegistrationFile = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").val();

            if(newCompanyRegistrationFile) {
                var fileReaderPasswordFile = new FileReader();
                fileReaderPasswordFile.onload = function (event) {
                    shareholderModified.companyRegistrationFileMimeType = event.target._TYPE;
                    shareholderModified.companyRegistrationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyRegistrationFileReady = true;
                };
                fileReaderPasswordFile._TYPE = $("#companyRegistrationFile")[0].files[0].type;
                fileReaderPasswordFile.readAsDataURL($("#companyRegistrationFile")[0].files[0]);
            }

            var newCompanyFinancialStatementFileReady = false;

            newCompanyFinancialStatementFile = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").val();

            if(newCompanyFinancialStatementFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholderModified.companyFinancialStatementFileMimeType = event.target._TYPE;
                    shareholderModified.companyFinancialStatementFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyFinancialStatementFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#companyFinancialStatementFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#companyFinancialStatementFile")[0].files[0]);
            }
            
            var newCompanyMemoAssociationFileReady = false;

            newCompanyMemoAssociationFile = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").val();

            if(newCompanyMemoAssociationFile) {
                var fileReaderOtherFile = new FileReader();
                fileReaderOtherFile.onload = function (event) {
                    shareholderModified.companyMemoAssociationFileMimeType = event.target._TYPE;
                    shareholderModified.companyMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newCompanyMemoAssociationFileReady = true;
                };
                fileReaderOtherFile._TYPE = $("#companyMemoAssociationFile")[0].files[0].type;
                fileReaderOtherFile.readAsDataURL($("#companyMemoAssociationFile")[0].files[0]);
            }

           var newIdCopyFileReady = false;

            newIdCopyFile = addShareholderQM1NewOrganizationSection.find("#idCopyFile").val();

            if(newIdCopyFile) {
                var fileReaderIdCopyFile = new FileReader();
                fileReaderIdCopyFile.onload = function (event) {
                	shareholderModified.delegate.idCopyFileMimeType = event.target._TYPE;
                	shareholderModified.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newIdCopyFileReady = true;
                };
                fileReaderIdCopyFile._TYPE = addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0].type;
                fileReaderIdCopyFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0]);
            }
            
           var newAuthorizationLetterFileReady = false;

            newAuthorizationLetterFile = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile").val();

            if(newAuthorizationLetterFile) {
                var fileReaderAuthorizationFile = new FileReader();
                fileReaderAuthorizationFile.onload = function (event) {
                	shareholderModified.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                	shareholderModified.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                    newAuthorizationLetterFileReady = true;
                };
                fileReaderAuthorizationFile._TYPE = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0].type;
                fileReaderAuthorizationFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0]);
            }

            shareholders.push(shareholderModified);

            var qeemah1InfoTemp = { arr : SAGIA.license.apply.data};
            var qeemah1InfoData = $.extend(true, {}, qeemah1InfoTemp);
            var shareholdersQm1Info = qeemah1InfoData.arr.qeemah1Data.newShareholders;
            shareholdersQm1Info.push(shareholderModified);

            var validatingAndSaving = function() {
            	 if((!newCompanyRegistrationFile || (newCompanyRegistrationFile && newCompanyRegistrationFileReady)) && (!newCompanyFinancialStatementFile || (newCompanyFinancialStatementFile && newCompanyFinancialStatementFileReady)) && (!newIdCopyFile || (newIdCopyFile && newIdCopyFileReady)) && (!newAuthorizationLetterFile || (newAuthorizationLetterFile && newAuthorizationLetterFileReady)) ) {
                    var promise = [];
                    promise.push(validateLicenseData('#addShareholderQM1NewOrganizationSection', 'new-shareholder-qeemah1-entity', qeemah1InfoData.arr, function () {
                    }));
                    promise.push(validateLicenseData('#addShareholderQM1NewOrganizationSection', 'new-shareholder-percentage-QM1', qeemah1Data.arr, function () {
                        },
                        function (xhr, status, error) {
                            var formErrors = xhr.responseJSON.formErrors;
                            $.each(formErrors, function (key, errorMessage) {
                                $("#addShareholderQM1NewOrganizationSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                                $("#addShareholderQM1NewOrganizationSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                            });
                        }
                    ));

                    $.when.apply($, promise).done(function (results) {
                        var existingParentCompany = addShareholderQM1NewOrganizationSection.find("#parentCompanyCountry").find(":selected").text();
                        var existingOrganizationSection = addShareholderQM1NewOrganizationSection.find("#companyCountry").find(":selected").text();
                        var existingDelegateCountry = addShareholderQM1NewOrganizationSection.find("#companyCountry").find(":selected").text();
                        var existingDelegateNationality = addShareholderQM1NewOrganizationSection.find("#companyCountry").find(":selected").text();
                        var existingOrganizationLegalStatus = addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").find(":selected").text();
                        var existingMultinationalCompany = addShareholderQM1NewOrganizationSection.find("#multinationalCompany").find(":selected").text();
                        shareholder = getShareholderDataById(type, id);
                        shareholder.type = addShareholderQM1NewSection.find("#shareholderType input:checked").val();
                        shareholder.title = addShareholderQM1NewPersonSection.find("#shareholderTitle input:checked").val();
                        shareholder.organizationNameEnglish = addShareholderQM1NewOrganizationSection.find("#organizationNameEnglish").val();
                        shareholder.organizationNameArabic = addShareholderQM1NewOrganizationSection.find("#organizationNameArabic").val();
                        shareholder.organizationLegalStatus = addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").val();
                        shareholder.organizationLegalStatusText = existingOrganizationLegalStatus ? existingOrganizationLegalStatus : "-";
                        shareholder.multinationalCompany = addShareholderQM1NewOrganizationSection.find("#multinationalCompany").val();
                        shareholder.multinationalCompanyText = existingMultinationalCompany ? existingMultinationalCompany : "-";
                        shareholder.companyRegistrationNumber = addShareholderQM1NewOrganizationSection.find("#companyRegistrationNumber").val();
                        shareholder.companyCapital = addShareholderQM1NewOrganizationSection.find("#companyCapital").val();
                        shareholder.companySharesPercentage = addShareholderQM1NewOrganizationSection.find("#companySharesPercentage").val();
                        shareholder.companySection = addShareholderQM1NewOrganizationSection.find("#companySection").val();
                        shareholder.companyDivision = addShareholderQM1NewOrganizationSection.find("#companyDivision").val();
                        shareholder.parentCompanyName = addShareholderQM1NewOrganizationSection.find("#parentCompanyName").val();
                        shareholder.parentCompanyCountry = addShareholderQM1NewOrganizationSection.find("#parentCompanyCountry").val();
                        shareholder.parentCompanyCountryText = existingParentCompany ? existingParentCompany : "-";
                        shareholder.companyCountry = addShareholderQM1NewOrganizationSection.find("#companyCountry").val();
                        shareholder.companyCountryText = existingOrganizationSection ? existingOrganizationSection : "-";
                        shareholder.companyCountryOfRegistration = addShareholderQM1NewOrganizationSection.find("#companyCountryOfRegistration").val();
                        shareholder.companyCity = addShareholderQM1NewOrganizationSection.find("#companyCity").val();
                        shareholder.companyAddress = addShareholderQM1NewOrganizationSection.find("#companyAddress").val();
                        shareholder.companyPostalCode = addShareholderQM1NewOrganizationSection.find("#companyPostalCode").val();
                        shareholder.companyPOBox = addShareholderQM1NewOrganizationSection.find("#companyPOBox").val();
                        shareholder.companyCountryCodeForTelephone = addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForTelephone").val();
                        shareholder.companyTelephone = addShareholderQM1NewOrganizationSection.find("#companyTelephone").val();
                        shareholder.companyCountryCodeForMobile = addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForMobile").val();
                        shareholder.companyMobile = addShareholderQM1NewOrganizationSection.find("#companyMobile").val();
                        shareholder.companyEmail = addShareholderQM1NewOrganizationSection.find("#companyEmail").val();
                        shareholder.companyWebsite = addShareholderQM1NewOrganizationSection.find("#companyWebsite").val();
                        shareholder.hasDelegateInfo = addShareholderQM1NewOrganizationSection.find("input[name='hasDelegateInfo']:checked").val();

                        shareholder.selfDelegate = "false";
                        //delegate
                        shareholder.delegate.nicVerified = addShareholderQM1NewOrganizationSection.find("#verifyDetailsShow").data("nicVerified");
                        shareholder.delegate.idType= addShareholderQM1NewOrganizationSection.find("#idType").val();
                        shareholder.delegate.idNumber= addShareholderQM1NewOrganizationSection.find("#idNumber").val();
                        shareholder.delegate.dateofBirth= addShareholderQM1NewOrganizationSection.find("#delegateDateofBirth").val();
                        shareholder.delegate.firstNameArabic=addShareholderQM1NewOrganizationSection.find("#delegateFirstNameArabic").val();
                        shareholder.delegate.lastNameArabic=addShareholderQM1NewOrganizationSection.find("#delegateLastNameArabic").val();
                        shareholder.delegate.fullNameEnglish=addShareholderQM1NewOrganizationSection.find("#delegateFullNameEnglish").val();
                        shareholder.delegate.gender=addShareholderQM1NewOrganizationSection.find("#delegateGender").val();
                        shareholder.delegate.expiryDate=addShareholderQM1NewOrganizationSection.find("#delegateExpiryDate").val();
                        shareholder.delegate.issueDate = addShareholderQM1NewOrganizationSection.find("#delegateIssueDate").val();
                        shareholder.delegate.country = addShareholderQM1NewOrganizationSection.find("#delegateCountry").val();
                        shareholder.delegate.countryText = existingDelegateCountry ? existingDelegateCountry : "-";
                        shareholder.delegate.nationality = addShareholderQM1NewOrganizationSection.find("#delegateNationality").val();
                        shareholder.delegate.nationalityText = existingDelegateNationality ? existingDelegateNationality : "-";
                        shareholder.delegate.postalCode = addShareholderQM1NewOrganizationSection.find("#delegatePostalCode").val();
                        shareholder.delegate.poBox = addShareholderQM1NewOrganizationSection.find("#delegatePOBox").val();
                        shareholder.delegate.countryCodeForTelephone = addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForTelephone").val();
                        shareholder.delegate.telephone = addShareholderQM1NewOrganizationSection.find("#delegateTelephone").val();
                        shareholder.delegate.countryCodeForMobile = addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForMobile").val();
                        shareholder.delegate.mobile = addShareholderQM1NewOrganizationSection.find("#delegateMobile").val();
                        shareholder.delegate.email = addShareholderQM1NewOrganizationSection.find("#delegateEmail").val();
                        
                        newCompanyRegistrationFile = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").val();
                        if (newCompanyRegistrationFile) {
                            shareholder.companyRegistrationFile = newCompanyRegistrationFile;
                            shareholder.companyRegistrationFileName = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFileName").val();
                        }
                        newCompanyFinancialStatementFile = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").val();
                        if (newCompanyFinancialStatementFile) {
                            shareholder.companyFinancialStatementFile = newCompanyFinancialStatementFile;
                            shareholder.companyFinancialStatementFileName = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFileName").val();
                        }
                        newCompanyMemoAssociationFile = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").val();
                        if (newCompanyMemoAssociationFile) {
                            shareholder.companyMemoAssociationFile = newCompanyMemoAssociationFile;
                            shareholder.companyMemoAssociationFileName = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFileName").val();
                        }
                        
                        newAuthorizationLetterFile = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile").val();
                        if (newAuthorizationLetterFile) {
                            shareholder.delegate.authorizationLetterFile = newAuthorizationLetterFile;
                            shareholder.delegate.authorizationLetterFileName = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile").val();
                        }
                        newIdCopyFile = addShareholderQM1NewOrganizationSection.find("#idCopyFile").val();
                        if (newIdCopyFile) {
                            shareholder.delegate.idCopyFile = newIdCopyFile;
                            shareholder.delegate.idCopyFileName = addShareholderQM1NewOrganizationSection.find("#idCopyFile").val();
                        }
                        //handle attachments
                        if (newCompanyRegistrationFile) {
                            var fileReaderPasswordFile = new FileReader();
                            fileReaderPasswordFile.onload = function (event) {
                                shareholder.companyRegistrationFileMimeType = event.target._TYPE;
                                shareholder.companyRegistrationFileName = shareholder.companyRegistrationFile;
                                shareholder.companyRegistrationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                            };
                            fileReaderPasswordFile._TYPE = $("#companyRegistrationFile")[0].files[0].type;
                            fileReaderPasswordFile.readAsDataURL($("#companyRegistrationFile")[0].files[0]);
                        }
                        if (newCompanyFinancialStatementFile) {
                            var fileReaderOtherFile = new FileReader();
                            fileReaderOtherFile.onload = function (event) {
                                shareholder.companyFinancialStatementFileMimeType = event.target._TYPE;
                                shareholder.companyFinancialStatementFileName = shareholder.companyFinancialStatementFile;
                                shareholder.companyFinancialStatementFile = removeBase64prefix(event.target._TYPE, event.target.result);
                            };
                            fileReaderOtherFile._TYPE = $("#companyFinancialStatementFile")[0].files[0].type;
                            fileReaderOtherFile.readAsDataURL($("#companyFinancialStatementFile")[0].files[0]);
                        }
                        if (newCompanyMemoAssociationFile) {
                            var fileReaderOtherFile = new FileReader();
                            fileReaderOtherFile.onload = function (event) {
                                shareholder.companyMemoAssociationFileMimeType = event.target._TYPE;
                                shareholder.companyMemoAssociationFileName = shareholder.companyMemoAssociationFile;
                                shareholder.companyMemoAssociationFile = removeBase64prefix(event.target._TYPE, event.target.result);
                            };
                            fileReaderOtherFile._TYPE = $("#companyMemoAssociationFile")[0].files[0].type;
                            fileReaderOtherFile.readAsDataURL($("#companyMemoAssociationFile")[0].files[0]);
                        }
                        if(newAuthorizationLetterFile) {
                            var fileReaderOtherFile = new FileReader();
                            fileReaderOtherFile.onload = function (event) {
                                shareholder.delegate.authorizationLetterFileMimeType = event.target._TYPE;
                                shareholder.delegate.authorizationLetterFileName = shareholder.delegate.authorizationLetterFileName;
                                shareholder.delegate.authorizationLetterFile = removeBase64prefix(event.target._TYPE, event.target.result);
                            };
                            fileReaderOtherFile._TYPE = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0].type;
                            fileReaderOtherFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#authorizationLetterFile")[0].files[0]);
                        }
                        if(newIdCopyFile) {
                            var fileReaderOtherFile = new FileReader();
                            fileReaderOtherFile.onload = function (event) {
                            	shareholder.delegate.idCopyFileMimeType = event.target._TYPE;
                            	shareholder.delegate.idCopyFileName = shareholder.delegate.idCopyFileName;
                            	shareholder.delegate.idCopyFile = removeBase64prefix(event.target._TYPE, event.target.result);
                            };
                            fileReaderOtherFile._TYPE = addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0].type;
                            fileReaderOtherFile.readAsDataURL(addShareholderQM1NewOrganizationSection.find("#idCopyFile")[0].files[0]);
                        }
                        
                        addShareholderQM1NewOrganizationSection.data("id", 0);
                        resetShareholderQM1NewOrganizationForm();
                        removeErrorIfExists();
                    });


                return;

                }

                setTimeout(validatingAndSaving, 1000);
            }

            validatingAndSaving();
        }
    });

    function extractNewOrganizationShareholder(id) {
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        
        var companyFinancialStatementFileName = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFileName").val().replace("C:\\fakepath\\", "");
        if(companyFinancialStatementFileName === ""){
            companyFinancialStatementFileName = addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFileName").attr('placeholder');
        }
        var authorizationLetterFileName = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFileName").val().replace("C:\\fakepath\\", "");
        if(authorizationLetterFileName === ""){
        	authorizationLetterFileName = addShareholderQM1NewOrganizationSection.find("#authorizationLetterFileName").attr('placeholder');
        }
        var idCopyFileName = addShareholderQM1NewOrganizationSection.find("#idCopyFileName").val().replace("C:\\fakepath\\", "");
        if(idCopyFileName === ""){
        	idCopyFileName = addShareholderQM1NewOrganizationSection.find("#idCopyFileName").attr('placeholder');
        }
        
        var companyRegistrationFileName = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFileName").val().replace("C:\\fakepath\\", "");
        if(companyRegistrationFileName === ""){
        	companyRegistrationFileName = addShareholderQM1NewOrganizationSection.find("#companyRegistrationFileName").attr('placeholder');
        }
        var companyMemoAssociationFileName = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFileName").val().replace("C:\\fakepath\\", "");
        if(companyMemoAssociationFileName === ""){
        	companyMemoAssociationFileName = addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFileName").attr('placeholder');
        }
        var existingLegalStatus = addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").find(":selected").text();
        var existingCountryName = addShareholderQM1NewOrganizationSection.find("#companyCountry").find(":selected").text();
        var existingDelegateCountryName = addShareholderQM1NewOrganizationSection.find("#delegateCountry").find(":selected").text();
        var existingDelegateNationalityName = addShareholderQM1NewOrganizationSection.find("#delegateNationality").find(":selected").text();
        var existingMultinationalCompany = addShareholderQM1NewOrganizationSection.find("#multinationalCompany").find(":selected").text();
        var delegate = {
        	
        	nicVerified: addShareholderQM1NewOrganizationSection.find("#verifyDetailsShow").data("nicVerified"),
        	idType: addShareholderQM1NewOrganizationSection.find("#idType").val(),
        	idNumber: addShareholderQM1NewOrganizationSection.find("#idNumber").val(),
        	dateofBirth: addShareholderQM1NewOrganizationSection.find("#delegateDateofBirth").val(),
        	firstNameArabic:addShareholderQM1NewOrganizationSection.find("#delegateFirstNameArabic").val(),
        	lastNameArabic:addShareholderQM1NewOrganizationSection.find("#delegateLastNameArabic").val(),
        	fullNameEnglish:addShareholderQM1NewOrganizationSection.find("#delegateFullNameEnglish").val(),
        	gender:addShareholderQM1NewOrganizationSection.find("#delegateGender").val(),
        	expiryDate:addShareholderQM1NewOrganizationSection.find("#delegateExpiryDate").val(),
        	issueDate : addShareholderQM1NewOrganizationSection.find("#delegateIssueDate").val(),
        	country : addShareholderQM1NewOrganizationSection.find("#delegateCountry").val(),
        	countryText: existingDelegateCountryName ? existingCountryName : "-",
        	nationality : addShareholderQM1NewOrganizationSection.find("#delegateNationality").val(),
        	nationalityText: existingDelegateNationalityName ? existingDelegateNationalityName : "-",
        	postalCode : addShareholderQM1NewOrganizationSection.find("#delegatePostalCode").val(),
        	poBox : addShareholderQM1NewOrganizationSection.find("#delegatePOBox").val(),
        	countryCodeForTelephone : addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForTelephone").val(),
        	telephone : addShareholderQM1NewOrganizationSection.find("#delegateTelephone").val(),
        	countryCodeForMobile : addShareholderQM1NewOrganizationSection.find("#delegateCountryCodeForMobile").val(),
        	mobile : addShareholderQM1NewOrganizationSection.find("#delegateMobile").val(),
        	email : addShareholderQM1NewOrganizationSection.find("#delegateEmail").val(),
        	idCopyFileName: idCopyFileName,
        	authorizationLetterFileName: authorizationLetterFileName
        }
    	
        return {
            id: id,
            type: addShareholderQM1NewSection.find("#shareholderType input:checked").val(),
            organizationNameEnglish: addShareholderQM1NewOrganizationSection.find("#organizationNameEnglish").val(),
            organizationNameArabic: addShareholderQM1NewOrganizationSection.find("#organizationNameArabic").val(),
            organizationLegalStatus: addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").val(),
            organizationLegalStatusText: existingLegalStatus ? existingLegalStatus : "-",
            multinationalCompany: addShareholderQM1NewOrganizationSection.find("#multinationalCompany").val(),
            multinationalCompanyText: existingMultinationalCompany ? existingMultinationalCompany : "-",
            companyRegistrationNumber: addShareholderQM1NewOrganizationSection.find("#companyRegistrationNumber").val(),
            companyCapital: addShareholderQM1NewOrganizationSection.find("#companyCapital").val(),
            companySharesPercentage: addShareholderQM1NewOrganizationSection.find("#companySharesPercentage").val(),
            companySection: addShareholderQM1NewOrganizationSection.find("#companySection").val(),
            companyDivision: addShareholderQM1NewOrganizationSection.find("#companyDivision").val(),
            parentCompanyName: addShareholderQM1NewOrganizationSection.find("#parentCompanyName").val(),
            parentCompanyCountry: addShareholderQM1NewOrganizationSection.find("#parentCompanyCountry").val(),
            companyCountry: addShareholderQM1NewOrganizationSection.find("#companyCountry").val(),
            companyCountryText: existingCountryName ? existingCountryName : "-",
            companyCountryOfRegistration: addShareholderQM1NewOrganizationSection.find("#companyCountryOfRegistration").val(),
            companyCity: addShareholderQM1NewOrganizationSection.find("#companyCity").val(),
            companyAddress: addShareholderQM1NewOrganizationSection.find("#companyAddress").val(),
            companyPostalCode: addShareholderQM1NewOrganizationSection.find("#companyPostalCode").val(),
            companyPOBox: addShareholderQM1NewOrganizationSection.find("#companyPOBox").val(),
            companyCountryCodeForTelephone: addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForTelephone").val(),
            companyTelephone: addShareholderQM1NewOrganizationSection.find("#companyTelephone").val(),
            companyCountryCodeForMobile: addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForMobile").val(),
            companyMobile: addShareholderQM1NewOrganizationSection.find("#companyMobile").val(),
            companyEmail: addShareholderQM1NewOrganizationSection.find("#companyEmail").val(),
            companyWebsite: addShareholderQM1NewOrganizationSection.find("#companyWebsite").val(),
            companyRegistrationFileName: companyRegistrationFileName,
            companyFinancialStatementFileName: companyFinancialStatementFileName,
            companyMemoAssociationFileName: companyMemoAssociationFileName,
            hasDelegateInfo : addShareholderQM1NewOrganizationSection.find("input[name='hasDelegateInfo']:checked").val(),
            selfDelegate : "false",   
            delegate:delegate
        };
    }

    function resetShareholderQM1NewOrganizationForm() {
        var addShareholderQM1NewOrganizationSection = $("#addShareholderQM1NewOrganizationSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1NoShareholderSection = $("#addShareholderQM1NoShareholderSection");
        var shareholderQM1TableSection = $("#shareholderQM1TableSection");

        addShareholderQM1NewSection.find("#shareholderType input").prop("checked", null);
        addShareholderQM1NewOrganizationSection.find("#organizationNameEnglish").val("");
        addShareholderQM1NewOrganizationSection.find("#organizationNameArabic").val("");
        addShareholderQM1NewOrganizationSection.find("#organizationLegalStatus").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#multinationalCompany").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#companyRegistrationNumber").val("");
        addShareholderQM1NewOrganizationSection.find("#companyCapital").val("");
        addShareholderQM1NewOrganizationSection.find("#companySharesPercentage").val("");
        addShareholderQM1NewOrganizationSection.find("#companySection").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#companyDivision").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#parentCompanyName").val("");
        addShareholderQM1NewOrganizationSection.find("#parentCompanyCountry").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#companyCountry").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#companyCountryOfRegistration").val("").trigger("blur").trigger("change");
        addShareholderQM1NewOrganizationSection.find("#companyCity").val("");
        addShareholderQM1NewOrganizationSection.find("#companyAddress").val("");
        addShareholderQM1NewOrganizationSection.find("#companyPostalCode").val("");
        addShareholderQM1NewOrganizationSection.find("#companyPOBox").val("");
        addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForTelephone").val("");
        addShareholderQM1NewOrganizationSection.find("#companyTelephone").val("");
        addShareholderQM1NewOrganizationSection.find("#companyCountryCodeForMobile").val("");
        addShareholderQM1NewOrganizationSection.find("#companyMobile").val("");
        addShareholderQM1NewOrganizationSection.find("#companyEmail").val("");
        addShareholderQM1NewOrganizationSection.find("#companyWebsite").val("");
        addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").val(null); //mind the null
        addShareholderQM1NewOrganizationSection.find("#companyRegistrationFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
        addShareholderQM1NewOrganizationSection.find("#companyRegistrationFileName").val("");
        addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").val(null); //mind the null
        addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
        addShareholderQM1NewOrganizationSection.find("#companyFinancialStatementFileName").val("");
        addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").val(null); //mind the null
        addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFile").parent().find(".form-icon_reset").trigger("blur").trigger("click");
        addShareholderQM1NewOrganizationSection.find("#companyMemoAssociationFileName").val("");
        addShareholderQM1NewOrganizationSection.data("id", null);

        //delegate
        resetDelegate(addShareholderQM1NewOrganizationSection, "Organization");
        
        addShareholderQM1NoShareholderSection.hide();
        addShareholderQM1NewSection.hide();
        addShareholderQM1NewOrganizationSection.hide();
        shareholderQM1TableSection.show();
        shareholderQM1TableSection.find(".addExistingButton").prop("disabled", false);
        shareholderQM1TableSection.find(".addNewButton").prop("disabled", false);
        $("#shareholdersNextButton").prop("disabled", false);

        $("#shareholdersQM1 #shareholderType .form-item").show();
        redrawShareholderQM1TableSection();
    }

    var resetShareholderQM1NewPersonForm = function(){
        var addShareholderQM1NewPersonSection = $("#addShareholderQM1NewPersonSection");
        var addShareholderQM1NewSection = $("#addShareholderQM1NewSection");
        var addShareholderQM1NoShareholderSection = $("#addShareholderQM1NoShareholderSection");
        var shareholderQM1TableSection = $("#shareholderQM1TableSection");

        addShareholderQM1NewSection.find("#shareholderType input").prop("checked", false);
        addShareholderQM1NewPersonSection.find("#shareholderTitle input").prop("checked", false);
        addShareholderQM1NewPersonSection.find("#academicTitle").val("").trigger("blur").trigger("change");
        addShareholderQM1NewPersonSection.find("#premiumResident").val("").trigger("blur").trigger("change");
        addShareholderQM1NewPersonSection.find("#firstNameArabic").val("");
        addShareholderQM1NewPersonSection.find("#lastNameArabic").val("");
        addShareholderQM1NewPersonSection.find("#fullNameEnglish").val("");
        addShareholderQM1NewPersonSection.find("#personSharesPercentage").val("");
        addShareholderQM1NewPersonSection.find("#dateOfBirth").val("");
        addShareholderQM1NewPersonSection.find("#passportNumber").val("");
        addShareholderQM1NewPersonSection.find("#passportIssueDate").val("");
        addShareholderQM1NewPersonSection.find("#passportExpiryDate").val("");
        addShareholderQM1NewPersonSection.find("#currentNationality").val("").trigger("blur").trigger("change");
        addShareholderQM1NewPersonSection.find("#previousNationality").val("").trigger("blur").trigger("change");
        addShareholderQM1NewPersonSection.find("#country").val("").trigger("blur").trigger("change");
        addShareholderQM1NewPersonSection.find("#city").val("");
        addShareholderQM1NewPersonSection.find("#postalCode").val("");
        addShareholderQM1NewPersonSection.find("#poBox").val("");
        addShareholderQM1NewPersonSection.find("#countryCodeForTelephone").val("");
        addShareholderQM1NewPersonSection.find("#telephone").val("");
        addShareholderQM1NewPersonSection.find("#countryCodeForMobile").val("");
        addShareholderQM1NewPersonSection.find("#mobile").val("");
        addShareholderQM1NewPersonSection.find("#email").val("");
        addShareholderQM1NewPersonSection.find("#passportFile").val(null); //mind the null
        addShareholderQM1NewPersonSection.find("#passportFile").parent().find(".form-icon_reset").trigger("click");
        addShareholderQM1NewPersonSection.find("#passportFileName").val("");
        addShareholderQM1NewPersonSection.find("#otherFile").val(null); //mind the null
        addShareholderQM1NewPersonSection.find("#otherFile").parent().find(".form-icon_reset").trigger("click");
        addShareholderQM1NewPersonSection.find("#otherFileName").val("");
        /*addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").val(null); //mind the null
        addShareholderQM1NewPersonSection.find("#personMemoAssociationFile").parent().find(".form-icon_reset").trigger("click");
        addShareholderQM1NewPersonSection.find("#personMemoAssociationFileName").val("");*/

        //delegate
        resetDelegate(addShareholderQM1NewPersonSection, "Person");
        
        addShareholderQM1NewPersonSection.data("id", null);

        addShareholderQM1NoShareholderSection.hide();
        addShareholderQM1NewSection.hide();
        addShareholderQM1NewPersonSection.hide();
        shareholderQM1TableSection.show();
        shareholderQM1TableSection.find(".addExistingButton").prop("disabled", false);
        shareholderQM1TableSection.find(".addNewButton").prop("disabled", false);
        $("#shareholdersNextButton").prop("disabled", false);

        $("#shareholdersQM1 #shareholderType .form-item").show();
        redrawShareholderQM1TableSection();
    }
    
    var resetDelegate = function(element, type)
    {
    	resetDelegateSection(element, type);
    	resetDelegateQuestion(element, type);
    	resetDelegateInfo(element, type);
    	resetDelegateDetails(element, type);
    }
    
    
    var resetDelegateSection = function(element, type)
    {
    	element.find("input[name='hasDelegateInfo']:checked").prop('checked', false);
    	element.find("#delegateSection").hide();
    }
    
    var resetDelegateQuestion = function(element, type)
    {
    	element.find("input[name='hasDelegate']:checked").prop('checked', false);
    	if(type === 'Person')
    	{
    		element.find("#showDelegateQuestion").show();
    	}
    	else if(type === 'Organization')
    	{
    		element.find("#showDelegateQuestion").hide();
    	}
    	element.find("#delegate").hide();
    }
    
    var resetDelegateInfo = function(element, type)
    {
        element.find("#verifyDetailsShow").data("nicVerified", "false");
        element.find("#idType").val("").trigger("blur").trigger('change');
        element.find("#idNumber").val("");
        element.find("#delegateDateofBirth").val("");
        element.find("#delegateDetails").hide();
        element.find("#nicVerifyBtnSection").show();
    }
    
    var resetDelegateDetails = function(element, type)
    {
        element.find("#delegateFirstNameArabic").val("");
        element.find("#delegateLastNameArabic").val("");
        element.find("#delegateFullNameEnglish").val("");
        element.find("#delegateGender").val("").trigger("blur").trigger('change');
        element.find("#delegateExpiryDate").val("");
        element.find("#delegateIssueDate").val("");
        element.find("#delegateCountry").val("").trigger("blur").trigger('change');
        element.find("#delegateNationality").val("").trigger("blur").trigger('change');
        element.find("#delegatePostalCode").val("");
        element.find("#delegatePOBox").val("");
        element.find("#delegateCountryCodeForTelephone").val("");
        element.find("#delegateTelephone").val("");
        element.find("#delegateCountryCodeForMobile").val("");
        element.find("#delegateMobile").val("");
        element.find("#delegateEmail").val("");
        toggleTheNICFieldsEditable(element, type, true);
        
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
    	}
		
    }
    
    var toggleTheNICFieldsEditable = function(element, type, editable)
    {
    	
    	if(editable)
    	{
    		element.find("#delegateFirstNameArabic").removeAttr("disabled");
    		element.find("#delegateLastNameArabic").removeAttr("disabled");
    		element.find("#delegateFullNameEnglish").removeAttr("disabled");
    		element.find("#delegateGender").removeAttr("disabled");
    		element.find("#delegateExpiryDate").removeAttr("disabled");
    	}
    	else
    	{
    		element.find("#delegateFirstNameArabic").attr('disabled','disabled');
    		element.find("#delegateLastNameArabic").attr('disabled','disabled');
    		element.find("#delegateFullNameEnglish").attr('disabled','disabled');
    		element.find("#delegateGender").attr('disabled','disabled');
    		element.find("#delegateExpiryDate").attr('disabled','disabled');
    	}
    }
    
    $("#addShareholderQM1NewPersonSection").find("#PersonDelegateYES").click(function() {
    	 $("#addShareholderQM1NewPersonSection").find("#PersonDelegateYESLable").css("border-color", "");
     	toggleDelegateSection($("#addShareholderQM1NewPersonSection"), true);
     });
    $("#addShareholderQM1NewOrganizationSection").find("#OrganizationDelegateYES").click(function() {
    	 $("#addShareholderQM1NewOrganizationSection").find("#OrganizationDelegateYESLable").css("border-color", "");
    	toggleDelegateSection($("#addShareholderQM1NewOrganizationSection"), true);
    });
    
    $("#addShareholderQM1NewPersonSection").find("#PersonDelegateNO").click(function() {
    	$("#addShareholderQM1NewPersonSection").find("#PersonDelegateNOLable").css("border-color", "");
    	toggleDelegateSection($("#addShareholderQM1NewPersonSection"), false);
    });
    
    $("#addShareholderQM1NewOrganizationSection").find("#OrganizationDelegateNO").click(function() {
    	 $("#addShareholderQM1NewOrganizationSection").find("#OrganizationDelegateNOLable").css("border-color", "");
    	toggleDelegateSection($("#addShareholderQM1NewOrganizationSection"), false);
    });
    
    var toggleDelegateSection = function(element, flag){
    	if(flag)
    	{
    		element.find("#delegateSection").show();
    	}
    	else
    	{
    		element.find("#delegateSection").hide();
    	}
    }
    
    $("#addShareholderQM1NewOrganizationSection").find("#hasDelegateYES").on("click", function () {
    	$("#addShareholderQM1NewOrganizationSection").find("#hasDelegateYESLabel").css("border-color", "");
    	toggleDelegateDetails($("#addShareholderQM1NewOrganizationSection"), false);
    });
    
    $("#addShareholderQM1NewPersonSection").find("#hasDelegateYES").on("click", function () {
		$("#addShareholderQM1NewPersonSection").find("#hasDelegateYESLabel").css("border-color", "");

    	toggleDelegateDetails($("#addShareholderQM1NewPersonSection"), false);
    });
    
    $("#addShareholderQM1NewOrganizationSection").find("#hasDelegateNO").on("click", function () {
		$("#addShareholderQM1NewOrganizationSection").find("#hasDelegateNOLabel").css("border-color", "");
    	toggleDelegateDetails($("#addShareholderQM1NewOrganizationSection"), true);
    });
    
    $("#addShareholderQM1NewPersonSection").find("#hasDelegateNO").on("click", function () {
    	debugger;
    	$("#addShareholderQM1NewPersonSection").find("#hasDelegateNOLabel").css("border-color", "");
		toggleDelegateDetails($("#addShareholderQM1NewPersonSection"), true);
    });
    
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
    }
    
    $("#addShareholderQM1NewOrganizationSection").find("#verifyDetailsShow").on("click", function() {
    	debugger;
    	preNICVerfication($("#addShareholderQM1NewOrganizationSection"), $(this));
    });
    
    $("#addShareholderQM1NewPersonSection").find("#verifyDetailsShow").on("click", function() {
    	debugger;
    	preNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
    	preShareholderNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
    });
    
    
    $("#nicVerificationSection").find("#verifyDetailsShow").on("click", function() {
    	
    	debugger;
    	preShareholderNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
    	preNICVerfication($("#addShareholderQM1NewPersonSection"), $(this));
    });
    
    
    
    var preNICVerfication = function(element, current){
    	var idType = element.find("#idType").val();
    	var idNumber = element.find("#idNumber").val();
    	var delegateDateofBirth = element.find("#delegateDateofBirth").val();
    	
    	if(!validateNICInput(idType,idNumber,delegateDateofBirth,element))
  		{
    		return false
    	}
    	verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data){
    		if(data)
    		{
    			var jsonData = JSON.parse(data);
    			if( jsonData.result && jsonData.result == "Valid Data")
    			{
    				current.data('nicVerified', "true");
    				setAndDisableFieldIfValueNotBlank(element.find("#delegateFirstNameArabic"),jsonData.firstName_AR);
    				setAndDisableFieldIfValueNotBlank(element.find("#delegateLastNameArabic"),jsonData.lastName_AR);
    				setAndDisableFieldIfValueNotBlank(element.find("#delegateFullNameEnglish"),jsonData.fullName_EN);
    				setAndDisableFieldIfValueNotBlank(element.find("#delegateGender"),jsonData.gender);
    				setAndDisableFieldIfValueNotBlank(element.find("#delegateExpiryDate"),jsonData.expiryDate);
    			}
    			else
    			{
    				toggleTheNICFieldsEditable(element, "", true);
    				current.data('nicVerified', "false");
    			}
    		}
    		else
    		{
    			toggleTheNICFieldsEditable(element, "", true);
    			current.data('nicVerified', "false");
    		}
    		toggleIdCopeFileSection(element);
    		toggleFieldsOnValue(element);
    		element.find("#verifyDelegateDetails").show();
    	});
    }
    
    
    
    var preShareholderNICVerfication = function(element, current){
    	
    	debugger;
  
    	var idType = element.find("#shareholderIdType").val();
    	var idNumber = element.find("#shareholderIdNumber").val();
    	var delegateDateofBirth = element.find("#shareholderDateofBirth").val();
    	
    	if(!validateNICInput(idType,idNumber,delegateDateofBirth,element))
  		{
    		return false
    	}
    	verifyUserDataWithNIC(idType, idNumber, delegateDateofBirth).done(function(data){
    		if(data)
    		{
    			var jsonData = JSON.parse(data);
    			if( jsonData.result && jsonData.result == "Valid Data")
    			{
    				current.data('nicVerified', "true");
    				setAndDisableFieldIfValueNotBlank(element.find("#shareholderFirstNameArabic"),jsonData.firstName_AR);
    				setAndDisableFieldIfValueNotBlank(element.find("#shareholderLastNameArabic"),jsonData.lastName_AR);
    				setAndDisableFieldIfValueNotBlank(element.find("#shareholderFullNameEnglish"),jsonData.fullName_EN);
    				setAndDisableFieldIfValueNotBlank(element.find("#shareholderGender"),jsonData.gender);
    				setAndDisableFieldIfValueNotBlank(element.find("#shareholderExpiryDate"),jsonData.expiryDate);
    			}
    			else
    			{
    				toggleTheNICFieldsEditable(element, "", true);
    				current.data('nicVerified', "false");
    			}
    		}
    		else
    		{
    			toggleTheNICFieldsEditable(element, "", true);
    			current.data('nicVerified', "false");
    		}
    		toggleIdCopeFileSection(element);
    		toggleFieldsOnValue(element);
    		element.find("#verifyDelegateDetails").show();
    	});
    }
    
    
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
    	}
    }

    var verifyUserDataWithNIC = function(idType, idNumber, delegateDateofBirth){
        return $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/nic", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                //xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            method: 'POST',
            data: {"idType": idType,
            	"idNumber": idNumber,
            	"dob":delegateDateofBirth},
            success: function (data) {
            	//console.log("asd");
            },
            error: function (error) {
            	//console.log("erro");
            }
        });
    }
    
    var validateNICInput = function(idType,idNumber,delegateDateofBirth,element){
    	debugger;
	    if(!idType||!idNumber||!delegateDateofBirth){
	    	var errorMsg = getI18nText("validation.shareholder.delegate.idNumber");
			if(!idType){
				element.find("#idType").parent().addClass("has-error");
				element.find("#idType").parent().siblings(".help-block").text(errorMsg);
			}
			if(!idNumber){
				element.find("#idNumber").parent().addClass("has-error");
				element.find("#idNumber").parent().siblings(".help-block").text(errorMsg);
			}
			if(!delegateDateofBirth){
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
    }
    $("#addShareholderQM1NewPersonSection").find("#idType").change(function(){ 
    	var type = "Person";
    	var element = $("#addShareholderQM1NewPersonSection");
    	onchangeOfIdType(element, type);
    });
    $("#addShareholderQM1NewOrganizationSection").find("#idType").change(function(){ 
    	var type = "Organization";
    	var element = $("#addShareholderQM1NewOrganizationSection");
    	onchangeOfIdType(element, type);
    });
    
    var onchangeOfIdType = function(element, type)
    {
    	toggleUmmAlQuraOrNormalCalInDelegateSection(element);
    	resetDelegateDetails(element, type);
    	
    	element.find("#nicVerifyBtnSection").show();
    	if(element.find("#idType").val()==="4"){
    		//For passport
    		element.find("#nicVerifyBtnSection").hide();
    		element.find("#verifyDelegateDetails").show();
    	}
    	
    	toggleIdCopeFileSection(element);
    	toggleFieldsOnValue(element);
    }
    
    $("#addShareholderQM1NewPersonSection").find("#idNumber").change(function(){ 
    	var element = $("#addShareholderQM1NewPersonSection");
    	resetDelegateDetails(element, "Person");
    });
    $("#addShareholderQM1NewOrganizationSection").find("#idNumber").change(function(){ 
    	var element = $("#addShareholderQM1NewOrganizationSection");
    	resetDelegateDetails(element, "Organization");
    });
    
    $("#addShareholderQM1NewPersonSection").find("#idNumber").on('keypress', function (event) {
    	validateIdNumber($("#addShareholderQM1NewPersonSection"));
    });
    
    $("#addShareholderQM1NewOrganizationSection").find("#idNumber").on('keypress', function (event) {
    	validateIdNumber($("#addShareholderQM1NewOrganizationSection"));
    });
    
    
    var validateIdNumber  = function(element){
    	var idType = element.find("#idType").val();
    	if(idType && idType==="1" || idType==="2")
		{
    		var regex = new RegExp("^[0-9]+$");
    		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    		if (!regex.test(key)) {
    			event.preventDefault();
    			return false;
    		}
		}
    }
    
    $("#addShareholderQM1NewPersonSection").find("#delegateDateofBirth").change(function(){ 
    	var element = $("#addShareholderQM1NewPersonSection");
    	resetDelegateDetails(element, "Person");
    });
    $("#addShareholderQM1NewOrganizationSection").find("#delegateDateofBirth").change(function(){ 
    	var element = $("#addShareholderQM1NewOrganizationSection");
    	resetDelegateDetails(element, "Organization");
    });
    
    var toggleUmmAlQuraOrNormalCalInDelegateSection = function(element){
    	if(element.find("#idType").val()==="1"){
    		bindUmmAlQuraCal(element.find('#delegateDateofBirth'));
    		bindUmmAlQuraCal(element.find('#delegateIssueDate'));
    		bindUmmAlQuraCal(element.find('#delegateExpiryDate'));
        }
    	else if(element.find('#delegateDateofBirth').hasClass("ummAlQura"))
    	{
    		bindNormalCal(element.find('#delegateDateofBirth'));
    		bindNormalCal(element.find('#delegateIssueDate'));
    		bindNormalCal(element.find('#delegateExpiryDate'));
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

    var bindCalendarsToInputs = function (element) {
        var isUmmAlQura = false;

        if(element.find("#idType").val()==="1"){
            isUmmAlQura = true;
        }

        if (isUmmAlQura) {
            bindCalendarsPickerToInput(element.find('#delegateDateofBirth'));
            bindCalendarsPickerToInput(element.find('#delegateIssueDate'));
            bindCalendarsPickerToInput(element.find('#delegateExpiryDate'));
        } else {
            bindCustomFlatpickr(element.find('#delegateDateofBirth'));
            bindCustomFlatpickr(element.find('#delegateIssueDate'));
            bindCustomFlatpickr(element.find('#delegateExpiryDate'));
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
//            altField: $("#delegateDateofBirthUmm"),
//            altFormat: 'yyyy-mm-dd',
            prevText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M15.758.374c.365-.365.955-.365 1.319 0 .364.364.363.954-.001 1.319l-7.789 10.31 7.789 10.305c.364.365.365.955.001 1.319s-.954.364-1.319 0l-9.108-11.624 9.108-11.629z\"/></svg>",
            nextText: "<svg version=\"1.0\" xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path fill=\"currentColor\" d=\"M8.242.374c-.365-.365-.956-.365-1.319 0-.364.364-.363.954 0 1.319l7.79 10.311-7.79 10.305c-.364.365-.364.955 0 1.319s.955.364 1.319 0l9.107-11.624-9.107-11.63z\"/></svg>",
        });
    };

    var bindCustomFlatpickr = function (element) {
        ACC.calendarcommons.bindFlatpickr(element);
    };
    
    
    var toggleIdCopeFileSection = function(element){
    	
    	if(element.find("#idType").val() == "1")
    	{
    		element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.saudiid"));
    	}
    	else if(element.find("#idType").val() == "2")
    	{
    		element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.iqamaid"));
    	}
    	else if(element.find("#idType").val() == "3")
    	{
    		element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.gccid"));
    	}
    	else if(element.find("#idType").val() == "4")
    	{
    		element.find(".idCopyFile").text(getI18nText("license.apply.shareholder.idCopyFile.passport"));
    	}
    	
    	if(element.find("#idType").val() == "4" || element.find("#verifyDetailsShow").data("nicVerified")  == "false"){
    		element.find('#idCopyFileDiv').show();
    	}
		else {
    		element.find('#idCopyFileDiv').hide();
    	}
    };
    
    var toggleFieldsOnValue = function (element) {

		element.find('#postalCodeDiv').hide();
		element.find('#poBoxDiv').hide();
		element.find('#delegateCountryDiv').show();
		element.find('#delegateNationalityDiv').show();
		
		if(element.find("#idType").val() == "1")
		{
			element.find('#delegateCountry').val("SA").trigger("blur").trigger("change");
			element.find('#delegateNationality').val("SA").trigger("blur").trigger("change");
			element.find('#delegateCountryDiv').hide();
			element.find('#delegateNationalityDiv').hide();
		}
		else if(element.find("#idType").val() == "4")
		{
			element.find('#postalCodeDiv').show();
			element.find('#poBoxDiv').show();
		}
	};

    bindCalendarsToInputs($("#addShareholderQM1NewPersonSection"));
    bindCalendarsToInputs($("#addShareholderQM1NewOrganizationSection"));

    var  loadIdAndGenderDropDown = function() {
    	var personDelegateGender = shareholdersQM1.find("#addShareholderQM1NewPersonSection #delegateGender");
        var previousPersonDelegateGender = personDelegateGender.val();
        personDelegateGender.find("option").remove();
        personDelegateGender.append(new Option("", "", false, false));
        
        var personDelegateIdType = shareholdersQM1.find("#addShareholderQM1NewPersonSection #idType");
        var previousPersonDelegateIdType = personDelegateIdType.val();
        personDelegateIdType.find("option").remove();
        personDelegateIdType.append(new Option("", "", false, false));
		
		var organizationDelegateGender = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #delegateGender");
        var previousOrganizationDelegateGender = organizationDelegateGender.val();
        organizationDelegateGender.find("option").remove();
        organizationDelegateGender.append(new Option("", "", false, false));
        
        var organizationDelegateIdType = shareholdersQM1.find("#addShareholderQM1NewOrganizationSection #idType");
        var previousOrganizationDelegateIdType = organizationDelegateIdType.val();
        organizationDelegateIdType.find("option").remove();
        organizationDelegateIdType.append(new Option("", "", false, false));
        
        personDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.male"), "Male", false, false));
        personDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.female"), "Female", false, false));
        organizationDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.male"), "Male", false, false));
        organizationDelegateGender.append(new Option(getI18nText("license.shareholder.delegate.female"), "Female", false, false));
		
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
        personDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.saudiId"), "1", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.iqamaId"), "2", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.gccId"), "3", false, false));
        organizationDelegateIdType.append(new Option(getI18nText("license.shareholder.delegate.passportId"), "4", false, false));
        
        if(previousOrganizationDelegateGender) {
        	organizationDelegateGender.val(previousOrganizationDelegateGender).trigger("blur").trigger('change');
        } else {
        	organizationDelegateGender.val(null);
        }
        
        if(previousPersonDelegateGender) {
        	personDelegateGender.val(previousPersonDelegateGender).trigger("blur").trigger('change');
        } else {
        	personDelegateGender.val(null);
        }
        
        if(previousOrganizationDelegateIdType) {
        	organizationDelegateIdType.val(previousOrganizationDelegateIdType).trigger("blur").trigger('change');
        } else {
        	organizationDelegateIdType.val(null);
        }
        
        if(previousPersonDelegateIdType) {
        	personDelegateIdType.val(previousPersonDelegateIdType).trigger("blur").trigger('change');
        } else {
        	personDelegateIdType.val(null);
        }
    }
    
    var validateRadioButtons = function(element,type){
    	var hasDelegateInfoYES;
    	var hasDelegateInfoNO;
    	var hasDelegateYES;
    	var hasDelegateNO;
    	hasDelegateInfoYES=$(element).find("#"+type+"DelegateYES");
    	hasDelegateInfoNO=$(element).find("#"+type+"DelegateNO");
    	hasDelegateYES=$(element).find("#hasDelegateYES");
    	hasDelegateNO=$(element).find("#hasDelegateNO");
		
        if(hasDelegateInfoYES.is(':checked') === false && hasDelegateInfoNO.is(':checked') === false) {
            makeRedBorderFor($(element).find("#"+type+"DelegateYESLable"));
            makeRedBorderFor($(element).find("#"+type+"DelegateNOLable"));
            return false;
        }
        
        if(hasDelegateYES.is(':checked') === false && hasDelegateNO.is(':checked') === false && type==='Person') {
            makeRedBorderFor($(element).find("#hasDelegateYESLabel"));
            makeRedBorderFor($(element).find("#hasDelegateNOLabel"));
            return false;
        }
		
		return true;
    };
    
    var makeRedBorderFor = function(element){
    	element.css({"border-color": "#FF0000", 
            "border-width":"1px", 
            "border-style":"solid"
         });
    };
    
    $("#addShareholderQM1NewPersonSection").find("#premiumResident").change(function(){ 
    	 //var premiumResident = shareholdersQM1.find("#premiumResident"); //??? academicTitle - education
         var premiumResident = $("#addShareholderQM1NewPersonSection").find("#premiumResident").val();
         //var passportFileNameLabel = $("#addShareholderQM1NewPersonSection").find("#passportFileName").text();
         if(premiumResident === "yes"){
        	 $("label[for='passportFileName']").text(getI18nText("license.apply.premiumcopy"));
         }else{
        	 $("label[for='passportFileName']").text(getI18nText("licence.apply.passport"));
         }
    
    });
    
    $("#companyCountryCodeForMobile").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#companyMobile").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#companyCountryCodeForTelephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#companyTelephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#companyPostalCode").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#companyPOBox").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
  
    $("#countryCodeForTelephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#telephone").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#countryCodeForMobile").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#mobile").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#postalCode").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    $("#poBox").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
           //display error message
           $("#errmsg").html("Digits Only").show().fadeOut("slow");
                  return false;
       }
    });
    
});

$("#email").focusout(function() {
   	var emailValue = $('#email').val();
   	if (!validateShareHolderEmail(emailValue)) {
   	 	$("#shareholderEmailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$("#addShareholderQM1NewPersonSection .addButton").attr("disabled", true);
   	}
   	else {
   		$("#addShareholderQM1NewPersonSection .addButton").attr("disabled", false);	
   	}
   });

   $("#email").focusin(function() {
   	$("#shareholderEmailValidation").text("");
   });
   
   $("#companyEmail").focusout(function() {
   	var emailValue = $('#companyEmail').val();

   	if (!validateShareHolderEmail(emailValue)) {
   	 	$("#companyEmailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$("#addShareholderQM1NewOrganizationSection .addButton").attr("disabled", true);
   	}
   	else {
   		$("#addShareholderQM1NewOrganizationSection .addButton").attr("disabled", false);	
   	}
   });

   $('#companyEmail').focusin(function() {
   	$("#companyEmailValidation").text("");
   });

function validateShareHolderEmail(emailValue) {
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 return filter.test(emailValue);
	}