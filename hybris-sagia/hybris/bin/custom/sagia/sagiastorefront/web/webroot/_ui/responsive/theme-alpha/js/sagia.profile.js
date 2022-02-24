"use strict";

var SAGIA = SAGIA || {};

let limitChar = (element) => {
    const maxChar = 10;
    let ele = document.getElementById(element.id);
    let charLen = ele.value.length;
    if (charLen > maxChar) 
    {
        ele.value = ele.value.substring(0, maxChar);
    }
}
SAGIA.profile = {
    init: function init() {
        $(document).on("click", "#infoModalClose", function (e) {
            e.preventDefault();
            window.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile";
            SAGIA.profile.dismissChanges();
        });

        $(document).on("click", "#updateContactsCancel", function (e) {
            e.preventDefault();
            SAGIA.profile.displayUnsavedChangesModal();
        });

        $(document).on("click", "#dismissChanges", function (e) {
            e.preventDefault();
            SAGIA.profile.dismissChanges();
        });

        $(document).on("click", ".js-contactUpdate-edit", function (e) {
            e.preventDefault();
            SAGIA.profile.toggleContactsUpdateEditMode();
        });

        $.validator.setDefaults({
            highlight: function highlight(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function unhighlight(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function errorPlacement(error, element) {
                if (element.parent('.input-group').length) {
                    error.insertAfter(element.parent());
                } else if (element.nextAll('.js-inputFile-reset').length) {
                    error.insertAfter(element.nextAll('.js-inputFile-reset'));
                } else {
                    error.insertAfter(element);
                }
            }
        });
    },
    companyRepresentativesNo: 0,
    autoContactUpdate: true,
    validationParams: {
        rules: {
            firstName: "required",
            lastName: "required",
            nationalId: "required",
            email: {
                required: true,
                email: true
            },
            fileNationalIdContent: "required",
            applicationSignedFile: "required",
            gosiCertificateIdContent: "required"
        },
        messages: {
            required: getI18nText("validation.empty"),
            email: getI18nText("register.email.invalid")
        }
    },
    documentTypes: {
        nationalId: "NATIONAL",
        gosi: "GOSI"
    },
    contactTypes: {
        generalManager: "GM",
        representative: "R"
    },
    files: {
        filePrefixes: {
            generalManager: "GM",
            representative: "REP"
        },
        fileSufixes: {
            nationalId: "IQAMA",
            gosi: "GOSI"
        },
        separator: "_"
    },
    getFilePromise: function getFilePromise(selector, contactType, docType, index) {
        if (!$(selector)[0].files[0]) {
            return;
        }
        var file = $(selector)[0].files[0];
        var reader = new FileReader();
        var deferred = $.Deferred();

        reader.onload = function (event) {
            var fileName;

            if (contactType === SAGIA.profile.contactTypes.generalManager) {
                fileName = SAGIA.profile.files.filePrefixes.generalManager + SAGIA.profile.files.separator + SAGIA.profile.files.fileSufixes.nationalId;
            } else if (contactType === SAGIA.profile.contactTypes.representative) {
                if (docType === SAGIA.profile.documentTypes.nationalId) {
                    fileName = SAGIA.profile.files.filePrefixes.representative + index + SAGIA.profile.files.separator + SAGIA.profile.files.fileSufixes.nationalId;
                } else if (docType === SAGIA.profile.documentTypes.gosi) {
                    fileName = SAGIA.profile.files.filePrefixes.representative + index + SAGIA.profile.files.separator + SAGIA.profile.files.fileSufixes.gosi;
                }
            } else {
                fileName = file.name.split('.').slice(0, -1).join('.');
            }

            var item = { fileName: fileName, fileType: file.type, fileContent: SAGIA.attachments.removeBase64Prefix(file.type, event.target.result) };
            deferred.resolve(item);
        };

        reader.onerror = function () {
            deferred.reject(this);
        };

        reader.readAsDataURL(file);

        return deferred.promise();
    },
    displayErrorModal: function displayErrorModal(text) {
        var errorModal = $('#errorResponseModal');
        errorModal.find('.modal-description').text(text);
        errorModal.modal('show');
        return false;
    },
    displaySuccessModal: function displaySuccessModal(text) {
        var infoModal = $('#infoResponseModal');
        infoModal.find('.modal-description').text(text);
        infoModal.modal('show');
    },
    displayUnsavedChangesModal: function displayUnsavedChangesModal() {
        $('#unsavedChangesModal').modal('show');
    },
    dismissChanges: function dismissChanges() {
        var toggleElement = $(".js-contactUpdate-edit");
        toggleElement.find('.edit').removeClass('hidden').show();
        toggleElement.find('.close-edit').addClass('hidden').hide();
        SAGIA.myAccount.toggleContent(toggleElement);
        $('#unsavedChangesModal').modal('hide');
    },
    validatePrimaryContact: function validatePrimaryContact() {
        var primaryContactElement = $('#primaryContactId');
        var primaryContact = primaryContactElement.val();

        if (primaryContact === '') {
            primaryContactElement.parent().addClass("has-error");
            primaryContactElement.parent().next(".help-block").text(getI18nText("profile.primaryContact.invalid"));
            return false;
        } else {
            primaryContactElement.parent().removeClass("has-error");
            primaryContactElement.parent().next(".help-block").text('');
            return true;
        }
    },
    validateGMNationalId: function validateGMNationalId(){
        var gMNationalIdElement = $('#sagiaProfileGeneralManagerFormNationalIdInput');
        var gMNationalIdValue = $('#sagiaProfileGeneralManagerFormNationalIdInput').val();
        if(gMNationalIdValue.length > 20){
            gMNationalIdElement.parent().addClass("has-error");
            gMNationalIdElement.after('<span class="help-block">' + getI18nText("profile.nationalId.length") + '</span>');
            return false;
        } else {
            gMNationalIdElement.parent().removeClass("has-error");
            gMNationalIdElement.parent().next(".help-block").text('');
            return true;
        }
    },
    validateCRNationalId: function validateCRNationalId(){
        var validateCRNationalElement = $('#companyRepNationalId');
        var cRNationalIdValue = $('#companyRepNationalId').val();
        if(cRNationalIdValue.length > 20){
            validateCRNationalElement.parent().addClass("has-error");
            validateCRNationalElement.after('<span class="help-block">' + getI18nText("profile.nationalId.length") + '</span>');
            return false;
        } else {
            validateCRNationalElement.parent().removeClass("has-error");
            validateCRNationalElement.parent().next(".help-block").text('');
            return true;
        }
    },
    toggleContactsUpdateEditMode: function toggleContactsUpdateEditMode() {
        var toggleElement = $(".js-contactUpdate-edit");
        if (toggleElement.find('.edit').is(":visible")) {
            $.ajax(ACC.config.encodedContextPath + "/contacts/check", {
                type: "GET",
                cache: false,
                beforeSend: function beforeSend(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function success(response) {
                    toggleElement.find('.edit').addClass('hidden').hide();
                    toggleElement.find('.close-edit').removeClass('hidden').show();

                    SAGIA.myAccount.toggleContent(toggleElement);
                }
            });
        } else {
            SAGIA.profile.displayUnsavedChangesModal();
        }
    },
    updateContacts: function updateContacts() {
        var contactsForm = { termsAndConditionsChecked: false, attachments: [], updatedContacts: [] };
        var contacts = [];
        var attachments = [];
        var promises = [];
        var valid = true;
        var hasAnyFormChanged = false;

        if (SAGIA.profile.hasFormChanged('generalManagerForm')) {
            hasAnyFormChanged = true;
            SAGIA.profile.autoContactUpdate = false;
            var generalManagerForm = $("#generalManagerForm");
            generalManagerForm.validate(SAGIA.profile.validationParams);
            if (!generalManagerForm.valid()) {
                valid = false;
            } else {
                var gm = SAGIA.profile.objectifyForm(generalManagerForm.serializeArray());
                var promise = SAGIA.profile.getFilePromise("#generalManagerForm input[name=fileNationalIdContent]", SAGIA.profile.contactTypes.generalManager, SAGIA.profile.documentTypes.nationalId);
                gm['contactType'] = "GM";
                gm['primaryContact'] = $('#primaryContactId').val();
                var applicationSignedPromise = SAGIA.profile.getFilePromise("#applicationSignedFile", null, null, null);
                promises.push(applicationSignedPromise);
                promises.push(promise);
                var gosiPromise = SAGIA.profile.getFilePromise("#generalManagerForm input[name=gosiCertificateIdContent]", SAGIA.profile.contactTypes.generalManager, SAGIA.profile.documentTypes.gosi);
                if (gosiPromise) {
                    promises.push(gosiPromise);
                }
                contacts.push(gm);
            }
        }

        for (var i = 1; i <= SAGIA.profile.companyRepresentativesNo; i++) {
            if (SAGIA.profile.hasFormChanged('companyRepresentativeForm' + i)) {
                hasAnyFormChanged = true;
                var companyRepresentativeForm = $('#companyRepresentativeForm' + i);
                companyRepresentativeForm.validate(SAGIA.profile.validationParams);
                if (!companyRepresentativeForm.valid()) {
                    valid = false;
                } else {
                    var representative = SAGIA.profile.objectifyForm(companyRepresentativeForm.serializeArray());
                    representative['contactType'] = "R"+i;
                    representative['primaryContact'] = $('#primaryContactId').val();
                    contacts.push(representative);
                }
            }
        }

        if (!hasAnyFormChanged) {
            return SAGIA.profile.displayErrorModal(getI18nText("profile.company.noModification"));
        }

        valid &= SAGIA.profile.validatePrimaryContact();
        valid &= SAGIA.profile.validateGMNationalId();
        valid &= SAGIA.profile.validateCRNationalId();

        contactsForm['termsAndConditionsChecked'] = validateTermsAndConditions('#termsAndConditionsId');
        valid = valid && contactsForm['termsAndConditionsChecked'];

        if (!valid) {
            return SAGIA.profile.displayErrorModal(getI18nText("profile.company.contactUpdate.invalid"));
        }

        Promise.all(promises).then(function (contents) {
            for (i in contents) {
                if (contents.hasOwnProperty(i)) {
                    var attachment = {};
                    attachment['fileName'] = contents[i]['fileName'];
                    attachment['fileType'] = contents[i]['fileType'];
                    attachment['fileContent'] = contents[i]['fileContent'];
                    attachments.push(attachment);
                }
            }

            contactsForm['updatedContacts'] = contacts;
            contactsForm['attachments'] = attachments;

            $.ajax(ACC.config.encodedContextPath + "/contacts/update", {
                type: "POST",
                cache: false,
                data: JSON.stringify(contactsForm),
                beforeSend: function beforeSend(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function success(response) {
                    if(SAGIA.profile.autoContactUpdate){
                        SAGIA.profile.displaySuccessModal(getI18nText("profile.company.instant.success"));
                    }
                    else{
                        SAGIA.profile.displaySuccessModal(getI18nText("profile.company.success"));
                    }

                    SAGIA.myAccount.toggleEditMode("js-contactUpdate-edit");
                },
                error: function error(response) {
                    // SAGIA.myAccount.toggleEditMode("js-contactUpdate-edit");
                }
            });
        });
    },
    objectifyForm: function objectifyForm(formArray) {
        //serialize data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    },
    hasFormChanged: function hasFormChanged(formId) {
        var formIdSelector = $('#' + formId);
        return formIdSelector.serialize() !== formIdSelector.data('serialize');
    },
    updateCompanyData: function updateCompanyData() {
        var companyTabData = $("#companyTabData");
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/companyData",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(companyData) {
                if (companyData) {
                    var sagiaCompanyForm = companyData.sagiaCompanyForm;
                    if (sagiaCompanyForm) {
                        companyTabData.find("#sagiaCompanyFormEntityName").text(sagiaCompanyForm.entityName);
                        companyTabData.find("#sagiaCompanyFormEntityNameArabic").text(sagiaCompanyForm.entityNameArabic);
                        companyTabData.find("#sagiaCompanyFormEmail").text(sagiaCompanyForm.businessEmailAddress);
                        companyTabData.find("#sagiaCompanyFormLegalStatusDisplay").text(sagiaCompanyForm.legalStatus);
                        companyTabData.find("#sagiaCompanyFormCapital").text(sagiaCompanyForm.capital);
                        companyTabData.find("#sagiaCompanyFormRegionDisplay").text(sagiaCompanyForm.region);
                        companyTabData.find("#sagiaCompanyFormCityDisplay").text(sagiaCompanyForm.city);
                        companyTabData.find("#sagiaCompanyFormCountry").text(sagiaCompanyForm.country);
                        companyTabData.find("#sagiaCompanyFormIsicSection").text(sagiaCompanyForm.isicsection);
                        companyTabData.find("#sagiaCompanyFormIsicCode").text(sagiaCompanyForm.isiccode);
                        companyTabData.find("#sagiaCompanyFormIsicDivision").text(sagiaCompanyForm.isicdivision);
                    }

                    var branches = companyData.branches;
                    if (branches) {
                        var branchesTable = companyTabData.find("#branchesTable tbody");
                        var htmlToAppendForBranches = '';
                        //var branchesSection = companyTabData.find("#branchesSection");
                        for (var indexBranches in branches) {
                            if (branches.hasOwnProperty(indexBranches)) {
                                var branch = branches[indexBranches];
                                var name = branch.name || '-';
                                var type = branch.type || '-';
                                var city = branch.city || '-';
                                htmlToAppendForBranches += '' + '<tr>' + '<td>' + name + '</td>' + '<td>' + type + '</td>' + '<td>' + city + '</td>' + '</tr>';
                            }
                        }
                        branchesTable.append(htmlToAppendForBranches);
                    }
                    var contacts = [];
                    var contactsIndex = 1;

                    var sagiaProfileGeneralManagerForm = companyData.sagiaProfileGeneralManagerForm;
                    if (sagiaProfileGeneralManagerForm) {
                        companyTabData.find("#sagiaProfileGeneralManagerFormFirstName").text(sagiaProfileGeneralManagerForm.firstName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormFirstNameInput").val(sagiaProfileGeneralManagerForm.firstName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormMiddleName").text(sagiaProfileGeneralManagerForm.middleName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormMiddleNameInput").val(sagiaProfileGeneralManagerForm.middleName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormLastName").text(sagiaProfileGeneralManagerForm.lastName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormLastNameInput").val(sagiaProfileGeneralManagerForm.lastName);
                        companyTabData.find("#sagiaProfileGeneralManagerFormMobileNumber").text(sagiaProfileGeneralManagerForm.mobileNumber);
                        companyTabData.find("#sagiaProfileGeneralManagerFormMobileNumberInput").val(sagiaProfileGeneralManagerForm.mobileNumber);
                        companyTabData.find("#sagiaProfileGeneralManagerFormEmail").text(sagiaProfileGeneralManagerForm.email);
                        companyTabData.find("#sagiaProfileGeneralManagerFormEmailInput").val(sagiaProfileGeneralManagerForm.email);
                        companyTabData.find("#sagiaProfileGeneralManagerFormNationalId").text(sagiaProfileGeneralManagerForm.nationalId);
                        companyTabData.find("#sagiaProfileGeneralManagerFormNationalIdInput").val(sagiaProfileGeneralManagerForm.nationalId);
                        var attachment = companyTabData.find(".documentSection .documentSection-name");
                        attachment.attr("data-object-id", sagiaProfileGeneralManagerForm.srId);
                        attachment.data("objectId", sagiaProfileGeneralManagerForm.srId);
                        attachment.attr("data-document-id", sagiaProfileGeneralManagerForm.docId);
                        attachment.data("documentId", sagiaProfileGeneralManagerForm.docId);

                        if(sagiaProfileGeneralManagerForm.isPrimaryContact){
                            $('#primaryContactLabel').text(getI18nText("profileCompany.generalManager.title"));
                        }

                        var generalManagerForm = $('#generalManagerForm');
                        generalManagerForm.data('serialize', generalManagerForm.serialize());
                        contacts.push({ index: contactsIndex++, description: getI18nText("profileCompany.generalManager.title") });
                    }

                    var licenseClassification = companyData.licenseClassification;
                    if (licenseClassification) {
                        switch (licenseClassification) {
                            case "A": case "A+": case "I": SAGIA.profile.companyRepresentativesNo = 3; break;
                            case "B": SAGIA.profile.companyRepresentativesNo = 2; break;
                            default: SAGIA.profile.companyRepresentativesNo = 1;
                        }
                    }

                    var sagiaProfileRepresentativesForm = companyData.sagiaProfileRepresentativesForm;
                    if (sagiaProfileRepresentativesForm) {
                        var table = companyTabData.find("#representativeTable tbody");
                        var htmlToAppend = '';
                        var companyRepresentativeSection = companyTabData.find("#companyRepresentativeSection");
                        var count = 0;
                        for (var index in sagiaProfileRepresentativesForm) {
                            if (sagiaProfileRepresentativesForm.hasOwnProperty(index)) {
                                var representative = sagiaProfileRepresentativesForm[index];
                                htmlToAppend += '' + '<tr>' + '<td>' + representative.firstName + '</td>' + '<td>' + representative.lastName + '</td>' + '<td>' + representative.mobileNumber + '</td>' + '<td>' + representative.email + '</td>' + '<td class="tableModule-bodyItem-action">' + '   <button type="button" class="btn btn_link" data-toggle="modal" data-target="#companyRepresentativeModal' + (+index + 1) + '">' + '       <svg version="1.1" id="Ebene_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">\n' + '           <path fill="#5CC83B" d="M8,13.5c-4.786,0-8.133-4.602-8.273-4.798c-0.302-0.419-0.302-0.984,0.001-1.405C-0.135,7.104,3.197,2.5,8,2.5c4.779,0,8.133,4.602,8.273,4.798c0.302,0.419,0.303,0.984,0,1.405C16.132,8.899,12.762,13.5,8,13.5zM8,3.5c-4.293,0-7.428,4.335-7.459,4.379c-0.054,0.075-0.054,0.167-0.001,0.24C0.668,8.299,3.722,12.5,8,12.5c4.256,0,7.333-4.202,7.462-4.381c0.052-0.072,0.052-0.166,0-0.237C15.333,7.702,12.271,3.5,8,3.5z M8,11.5c-1.93,0-3.5-1.57-3.5-3.5S6.07,4.5,8,4.5s3.5,1.57,3.5,3.5S9.93,11.5,8,11.5z M8,5.5C6.622,5.5,5.5,6.622,5.5,8c0,1.379,1.122,2.5,2.5,2.5c1.379,0,2.5-1.121,2.5-2.5C10.5,6.622,9.379,5.5,8,5.5z"></path>\n' + '       </svg>' + '   </button>' + '</td>' + '</tr>';

                                var companyRepresentativeItem = $("#companyRepresentativeTemplate").clone();
                                companyRepresentativeItem.attr("id", "companyRepresentativeForm" + (+index + 1));
                                companyRepresentativeItem.find(".contentModule-headline").text(getI18nText("general.company.representative") + (+index + 1));
                                companyRepresentativeItem.find(".bpId").val(representative.bpId);
                                companyRepresentativeItem.find(".companyRepresentativeFirstName").val(representative.firstName);
                                companyRepresentativeItem.find(".companyRepresentativeMiddleName").val(representative.middleName);
                                companyRepresentativeItem.find(".companyRepresentativeLastName").val(representative.lastName);
                                companyRepresentativeItem.find(".companyRepresentativeMobileNumber").val(representative.mobileNumber);
                                companyRepresentativeItem.find(".companyRepresentativeEmail").val(representative.email);
                                companyRepresentativeItem.find(".companyRepresentativeNationalId").val(representative.nationalId);
                                companyRepresentativeItem.find(".companyRepresentativeContactType").val(representative.contactType);
                                companyRepresentativeItem.show();
                                companyRepresentativeSection.append(companyRepresentativeItem);

                                var companyRepresentativeModal = $("#companyRepresentativeModal").clone();
                                companyRepresentativeModal.attr("id", "companyRepresentativeModal" + (+index + 1));
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormFirstName").text(representative.firstName);
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormMiddleName").text(representative.middleName);
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormLastName").text(representative.lastName);
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormMobileNumber").text(representative.mobileNumber);
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormEmail").text(representative.email);
                                companyRepresentativeModal.find(".sagiaProfileRepresentativeFormNationaliId").text(representative.nationalId);
                                var attachmentDocId = companyRepresentativeModal.find(".documentSection .docId");
                                attachmentDocId.attr("data-object-id", representative.srId);
                                attachmentDocId.data("objectId", representative.srId);
                                attachmentDocId.attr("data-document-id", representative.docId);
                                attachmentDocId.data("documentId", representative.docId);
                                var attachmentDocGovId = companyRepresentativeModal.find(".documentSection .docGovId");
                                attachmentDocGovId.attr("data-object-id", representative.srId);
                                attachmentDocGovId.data("objectId", representative.srId);
                                attachmentDocGovId.attr("data-document-id", representative.docGovId);
                                attachmentDocGovId.data("documentId", representative.docGovId);
                                companyTabData.append(companyRepresentativeModal);

                                if(representative.isPrimaryContact){
                                    $('#primaryContactLabel').text(getI18nText("profileCompany.companyRepresentative.title") +" "+(+index + 1));
                                }
                                count++;
                            }
                        }

                        for (; count < SAGIA.profile.companyRepresentativesNo; count++) {
                            var companyRepresentativeItem = $("#companyRepresentativeTemplate").clone();
                            companyRepresentativeItem.attr("id", "companyRepresentativeForm" + (+count + 1));
                            companyRepresentativeItem.find(".contentModule-headline").text(getI18nText("general.company.representative") + (+count + 1));
                            companyRepresentativeItem.show();
                            companyRepresentativeSection.append(companyRepresentativeItem);

                            var companyRepresentativeModal = $("#companyRepresentativeModal").clone();
                            companyRepresentativeModal.attr("id", "companyRepresentativeModal" + (+count + 1));
                            companyTabData.append(companyRepresentativeModal);
                        }
                        table.append(htmlToAppend);

                        if (!SAGIA.profile.companyRepresentativesNo) {
                            SAGIA.profile.companyRepresentativesNo = sagiaProfileRepresentativesForm.length;
                        }

                        for (var i = 1; i <= SAGIA.profile.companyRepresentativesNo; i++) {
                            var companyRepresentativeForm = $('#companyRepresentativeForm' + i);
                            companyRepresentativeForm.data('serialize', companyRepresentativeForm.serialize());
                            contacts.push({ index: contactsIndex++, description: getI18nText("general.company.representative") + " "+ i });
                        }
                    }

                    var selectOptions = '';
                    for (index in contacts) {
                        selectOptions += '<option value=' + contacts[index]['index'] + '>' + contacts[index]['description'] + '</option>';
                    }
                    $('#primaryContactId').append(selectOptions);

                    $('.js-myAccount.loading').addClass('hidden');
                    $('.js-myAccount-edit-text.edit').removeClass('hidden');
                }
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.company.data"));
                errorModal.modal('show');
            }
        });
    },
    updateMyProfileData: function updateMyProfileData() {
        $("#sagiaProfilePersonalForm").validate({
            rules: {
                firstName: "required",
                lastName: "required",
                country: "required",
                company: "required",
                email: "required",
                mobileNumber: "required",
                mobileCountryCode: "required"
            },
            messages: {
                firstName: {
                    required: getI18nText("validation.firstName")
                },
                lastName: {
                    required: getI18nText("validation.lastName")
                },
                country: {
                    required: getI18nText("validation.country")
                },
                company: {
                    required: getI18nText("validation.company")
                },
                email: {
                    required: getI18nText("validation.email")
                },
                mobileCountryCode: {
                    required: getI18nText("validation.countryCode")
                },
                mobileNumber: {
                    required: getI18nText("validation.mobileNumber")
                }
            },
            invalidHandler: function invalidHandler(event, validator) {}
        });

        var myProfileTabData = $("#myProfileTabData");
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/personalData",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(profileData) {
                if (profileData) {
                    if (profileData.profilePicture) {
//                        myProfileTabData.find(".profilePicture").attr("src", profileData.profilePicture);
                        myProfileTabData.find(".js-profilePicture").css("background-image", 'url(' + profileData.profilePicture + ')');
                        myProfileTabData.find(".myAccount-profilImage-img").css("height", '170px');
                    }
                    else {
                        myProfileTabData.find(".myAccount-profilImage-img").css("height", '0px');
                    }
                    $('#sagia_companyLogo_picture_title').text('Company Logo');

                    if (profileData.companyLogo) {
                         myProfileTabData.find(".js-companyLogo").css("background-image", 'url(' + profileData.companyLogo + ')');
                     }
                    if (profileData.sagiaProfilePersonalForm && profileData.titles && profileData.countries && profileData.sectors) {
                        var sagiaProfilePersonalFormData = profileData.sagiaProfilePersonalForm;
                        myProfileTabData.find("#sagiaProfilePersonalFormTitle").text(sagiaProfilePersonalFormData.title.name);
                        myProfileTabData.find("#sagiaProfilePersonalFormFirstName").text(sagiaProfilePersonalFormData.firstName);
                        myProfileTabData.find("#sagiaProfilePersonalFormCountryName").text(sagiaProfilePersonalFormData.country.name);
                        myProfileTabData.find("#sagiaProfilePersonalFormSectorName").text(sagiaProfilePersonalFormData.sector.name);
                        myProfileTabData.find("#sagiaProfilePersonalFormMobileNumber").text(sagiaProfilePersonalFormData.mobileNumber);
                        myProfileTabData.find("#sagiaProfilePersonalFormLastName").text(sagiaProfilePersonalFormData.lastName);
                        myProfileTabData.find("#sagiaProfilePersonalFormCompany").text(sagiaProfilePersonalFormData.company);
                        myProfileTabData.find("#sagiaProfilePersonalFormMobileCountryCode").text(sagiaProfilePersonalFormData.mobileCountryCode);
						myProfileTabData.find("#sagiaProfilePersonalFormCompanyWebsite").text(sagiaProfilePersonalFormData.companyWebsite);

                        var sagiaProfilePersonalForm = $("#sagiaProfilePersonalForm");
                        var sagiaProfilePersonalFormTitle = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectTitle");
                        for (var index in profileData.titles) {
                            if (profileData.titles.hasOwnProperty(index)) {
                                var title = profileData.titles[index];
                                sagiaProfilePersonalFormTitle.append($("<option></option>").attr("value", title.code).text(title.name));
                            }
                        }
                        sagiaProfilePersonalFormTitle.val(sagiaProfilePersonalFormData.title.code).trigger('change');

                        var sagiaProfilePersonalFormSelectFirstName = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectFirstName");
                        sagiaProfilePersonalFormSelectFirstName.val(sagiaProfilePersonalFormData.firstName);

                        var sagiaProfilePersonalFormSelectLastName = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectLastName");
                        sagiaProfilePersonalFormSelectLastName.val(sagiaProfilePersonalFormData.lastName);

                        var sagiaProfilePersonalFormSelectEmail = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectEmail");
                        sagiaProfilePersonalFormSelectEmail.val(sagiaProfilePersonalFormData.email);

                        var sagiaProfilePersonalFormCountry = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectCountry");
                        for (var indexCountries in profileData.countries) {
                            if (profileData.countries.hasOwnProperty(indexCountries)) {
                                var country = profileData.countries[indexCountries];
                                sagiaProfilePersonalFormCountry.append($("<option></option>").attr("value", country.code).text(country.name));
                            }
                        }
                        sagiaProfilePersonalFormCountry.val(sagiaProfilePersonalFormData.country.code).trigger('change');

                        var sagiaProfilePersonalFormSelectCompany = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectCompany");
                        sagiaProfilePersonalFormSelectCompany.val(sagiaProfilePersonalFormData.company);

                        var sagiaProfilePersonalFormSector = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectSector");
                        for (var indexSectors in profileData.sectors) {
                            if (profileData.sectors.hasOwnProperty(indexSectors)) {
                                var sector = profileData.sectors[indexSectors];
                                sagiaProfilePersonalFormSector.append($("<option></option>").attr("value", sector.code).text(sector.name));
                            }
                        }
                        sagiaProfilePersonalFormSector.val(sagiaProfilePersonalFormData.sector.code).trigger('change');

                        var sagiaProfilePersonalFormSelectMobileCountryCode = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectMobileCountryCode");
                        sagiaProfilePersonalFormSelectMobileCountryCode.val(sagiaProfilePersonalFormData.mobileCountryCode);

                        var sagiaProfilePersonalFormSelectMobileNumber = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectMobileNumber");
                        sagiaProfilePersonalFormSelectMobileNumber.val(sagiaProfilePersonalFormData.mobileNumber);

                          var sagiaProfilePersonalFormSelectCompanyWebsite = sagiaProfilePersonalForm.find("#sagiaProfilePersonalFormSelectCompanyWebsite");
                          sagiaProfilePersonalFormSelectCompanyWebsite.val(sagiaProfilePersonalFormData.companyWebsite);

                        $("#sagiaProfilePersonalFormCancelUpdates").on("click", function () {
                            $(".js-myAccount-edit").trigger("click"); 
                            window.scrollTo({top: 0, behavior: 'smooth' });
                        });
                        $("#sagiaProfilePersonalFormSaveUpdates").on("click", function () {
                            var objectToPost = {
                                title: {
                                    code: sagiaProfilePersonalFormTitle.val(),
                                    text: sagiaProfilePersonalFormTitle.find(":selected").text()
                                },
                                firstName: sagiaProfilePersonalFormSelectFirstName.val(),
                                lastName: sagiaProfilePersonalFormSelectLastName.val(),
                                email: sagiaProfilePersonalFormSelectEmail.val(),
                                country: {
                                    code: sagiaProfilePersonalFormCountry.val(),
                                    name: sagiaProfilePersonalFormCountry.find(":selected").text()
                                },
                                company: sagiaProfilePersonalFormSelectCompany.val(),
                                sector: {
                                    code: sagiaProfilePersonalFormSector.val(),
                                    name: sagiaProfilePersonalFormSector.find(":selected").text()
                                },
                                mobileCountryCode: sagiaProfilePersonalFormSelectMobileCountryCode.val(),
                                mobileNumber: sagiaProfilePersonalFormSelectMobileNumber.val(),
 								companyWebsite : sagiaProfilePersonalFormSelectCompanyWebsite.val()
                            };
                            $.ajax(ACC.config.encodedContextPath + "/my-sagia/update-my-profile", {
                                beforeSend: function beforeSend(xhr) {
                                    xhr.setRequestHeader("Accept", "application/json");
                                    xhr.setRequestHeader("Content-Type", "application/json");
                                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                                },
                                method: 'POST',
                                data: JSON.stringify(objectToPost),
                                success: function success() {
                                    var infoModal = $('#infoResponseModal'); 
                                    infoModal.find('.modal-description').text(getI18nText('general.details.updated'));
                                    infoModal.modal('show');

                                    myProfileTabData.find("#sagiaProfilePersonalFormTitle").text(objectToPost.title.name);
                                    myProfileTabData.find("#sagiaProfilePersonalFormFirstName").text(objectToPost.firstName);
                                    myProfileTabData.find("#sagiaProfilePersonalFormCountryName").text(objectToPost.country.name);
                                    myProfileTabData.find("#sagiaProfilePersonalFormSectorName").text(objectToPost.sector.name);
                                    myProfileTabData.find("#sagiaProfilePersonalFormMobileNumber").text(objectToPost.mobileNumber);
                                    myProfileTabData.find("#sagiaProfilePersonalFormLastName").text(objectToPost.lastName);
                                    myProfileTabData.find("#sagiaProfilePersonalFormCompany").text(objectToPost.company);
                                    myProfileTabData.find("#sagiaProfilePersonalFormMobileCountryCode").text(objectToPost.mobileCountryCode);
									myProfileTabData.find("#sagiaProfilePersonalFormCompanyWebsite").text(objectToPost.companyWebsite);
                                },
                                error: function error() {
                                    var errorModal = $('#errorResponseModal');
                                    errorModal.find('.modal-description').text(getI18nText("general.couldnot.update"));
                                    errorModal.modal('show');
                                }
                            });
                        });
                    }
                }
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.profile.data"));
                errorModal.modal('show');
            }
        });
    },
    updateSecurityData: function updateSecurityData() {
        var securityTabData = $("#passwordTabData");
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/securityData",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(securityData) {
                securityTabData.find("#currentUsername").text(securityData.currentUsername);
                securityTabData.find("#currentEmail").text(securityData.currentEmail);

                $.validator.setDefaults({
                    highlight: function highlight(element) {
                        $(element).closest('.form-group').addClass('has-error');
                    },
                    unhighlight: function unhighlight(element) {
                        $(element).closest('.form-group').removeClass('has-error');
                    },
                    errorElement: 'div',
                    errorClass: 'help-block',
                    errorPlacement: function errorPlacement(error, element) {
                        if (element.parent('.input-group').length) {
                            error.insertAfter(element.parent());
                        } else {
                            error.insertAfter(element);
                        }
                    }
                });

                $.validator.addMethod("regex", function (value, element, regexp) {
                    return new RegExp(regexp).test(value);
                }, "");

                $.validator.addMethod("validateExisting", function (value, element, params) {
                    var validPassword = false;
                    $.ajax({
                        type: "POST",
                        data: JSON.stringify(value),
                        contentType: "application/json; charset=utf-8",
                        url: ACC.config.encodedContextPath + "/my-sagia/validate-password",
                        async: false,
                        beforeSend: function beforeSend(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                        },
                        success: function success(data) {
                            validPassword = false;
                            var responseData = JSON.parse(data);
                            validPassword = responseData.isValid;
                        },
                        error: function error() {
                            validPassword = false;
                        }
                    });
                    return validPassword;
                }, "");

                var updatePasswordForm = $("#updatePwdForm");
                updatePasswordForm.validate({
                    rules: {
                        oldPwd: {
                            required: true,
                            validateExisting: true
                        },
                        pwd: {
                            required: true,
                            // minlength: 8,
                            regex: securityData.backendRegex
                        },
                        checkPwd: {
                            required: true,
                            equalTo: "#pwd"
                        }
                    },
                    messages: {
                        oldPwd: {
                            required: getI18nText("profile.password.enterOld"),
                            validateExisting: getI18nText("profile.password.incorrect")
                        },
                        pwd: {
                            required: getI18nText("profile.password.enterNew"),
                            //minlength: getI18nText("password.length")
                            regex: securityData.backendRegexErrorMessage
                        },
                        checkPwd: {
                            required: getI18nText("profile.password.confirmNew"),
                            equalTo: getI18nText("profile.password.equals")
                        }
                    }
                });
                $("#updatePassword").on("click", function () {
                    updatePasswordForm.validate().form();
                    if (updatePasswordForm.validate().errorList.length === 0) {
                        $.ajax(ACC.config.encodedContextPath + "/my-sagia/update-password", {
                            beforeSend: function beforeSend(xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                            },
                            method: 'POST',
                            data: JSON.stringify({
                                pwd: updatePasswordForm.find("#pwd").val(),
                                checkPwd: updatePasswordForm.find("#checkPwd").val(),
                                oldPwd: updatePasswordForm.find("#oldPwd").val()
                            }),
                            success: function success() {
                                var infoModal = $('#infoResponseModal');
                                infoModal.find('.modal-description').text(getI18nText('general.details.updated'));
                                infoModal.modal('show');
                                updatePasswordForm.find("#pwd").val('');
                                updatePasswordForm.find("#checkPwd").val('');
                                updatePasswordForm.find("#oldPwd").val('');
                            },
                            error: function error() {
                                var errorModal = $('#errorResponseModal');
                                errorModal.find('.modal-description').text(getI18nText("general.couldnot.update"));
                                errorModal.modal('show');
                            }
                        });
                    }
                });

                $(".myAccount-passwordInfo").text(securityData.backendRegexErrorMessage);

                var updateUsernameForm = $("#changeUsername");
                updateUsernameForm.validate({
                    rules: {
                        username: "required",
                        checkUsername: {
                            required: true,
                            equalTo: "#username"
                        },
                        passwordForChangeUsername: {
                            required: true
                        }
                    },
                    messages: {
                        username: {
                            required: getI18nText("validation.new.username")
                        },
                        checkUsername: {
                            required: getI18nText("validation.confirm.username")
                        },
                        passwordForChangeUsername: {
                            required: getI18nText("validation.password")
                        }
                    }
                });
                $('#cancelUpdateUsernameButton').click(function () {
                    $('#changeUsername').trigger("reset");
                });
                $("#updateUsername").on("click", function () {
                    updateUsernameForm.validate().form();
                    if (updateUsernameForm.validate().errorList.length === 0) {
                        $.ajax(ACC.config.encodedContextPath + "/my-sagia/update-username", {
                            beforeSend: function beforeSend(xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                            },
                            method: 'POST',
                            data: JSON.stringify({
                                username: updateUsernameForm.find("#username").val(),
                                checkUsername: updateUsernameForm.find("#checkUsername").val(),
                                password: updateUsernameForm.find("#passwordForChangeUsername").val()
                            }),
                            success: function success() {
                                var infoModal = $('#infoResponseModal');
                                infoModal.find('.modal-description').text(getI18nText('general.details.updated'));
                                infoModal.modal('show');
                                updateUsernameForm.find("#currentUsername").text(updateUsernameForm.find("#username").val());
                                updateUsernameForm.find("#username").val('');
                                updateUsernameForm.find("#checkUsername").val('');
                                updateUsernameForm.find("#passwordForChangeUsername").val('');
                            },
                            error: function error() {
                                var errorModal = $('#errorResponseModal');
                                errorModal.find('.modal-description').text(getI18nText("general.couldnot.update"));
                                errorModal.modal('show');
                            }
                        });
                    }
                });

                var updateEmailForm = $("#changeEmail");
                updateEmailForm.validate({
                    rules: {
                        email: {
                            required: true,
                            regex: /^([A-Za-z0-9._%+-])+@([A-Za-z0-9.-])+\.([A-Za-z]{2,4})$/
                        },
                        chkEmail: {
                            required: true,
                            equalTo: "#email"
                        },
                        passwordForChangeEmail: {
                            required: true
                        }
                    },
                    messages: {
                        email: {
                            required: getI18nText("validation.new.email"),
                            regex: getI18nText("validation.valid.email")
                        },
                        chkEmail: {
                            required: getI18nText("validation.confirm.new.email")
                        },
                        passwordForChangeEmail: {
                            required: getI18nText("validation.password")
                        }
                    }
                });
                $('#cancelUpdateEmailButton').click(function () {
                    $('#changeEmail').trigger("reset");
                });
                $("#updateEmail").on("click", function () {
                    updateEmailForm.validate().form();
                    if (updateEmailForm.validate().errorList.length === 0) {
                        $.ajax(ACC.config.encodedContextPath + "/my-sagia/update-email", {
                            beforeSend: function beforeSend(xhr) {
                                xhr.setRequestHeader("Accept", "application/json");
                                xhr.setRequestHeader("Content-Type", "application/json");
                                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                            },
                            method: 'POST',
                            data: JSON.stringify({
                                email: updateEmailForm.find("#email").val(),
                                chkEmail: updateEmailForm.find("#chkEmail").val(),
                                password: updateEmailForm.find("#passwordForChangeEmail").val()
                            }),
                            success: function success() {
                                var infoModal = $('#infoResponseModal');
                                infoModal.find('.modal-description').text(getI18nText('general.details.updated'));
                                infoModal.modal('show');
                                updateEmailForm.find("#currentEmail").text(updateEmailForm.find("#email").val());
                                updateEmailForm.find("#email").val('');
                                updateEmailForm.find("#chkEmail").val('');
                                updateEmailForm.find("#passwordForChangeEmail").val('');
                            },
                            error: function error() {
                                var errorModal = $('#errorResponseModal');
                                errorModal.find('.modal-description').text(getI18nText("general.couldnot.update"));
                                errorModal.modal('show');
                            }
                        });
                    }
                });
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.security.data"));
                errorModal.modal('show');
            }
        });
    },
    updateEnquiriesData: function updateEnquiriesData() {
        var $modal = $('#enquiryDetail');
        var $modalBody = $modal.find('.modal-dialog');

        $.validator.setDefaults({
            highlight: function highlight(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function unhighlight(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            errorElement: 'div',
            errorClass: 'help-block',
            errorPlacement: function errorPlacement(error, element) {
                if (element.parent('.input-group').length) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

        $modal.on('shown.bs.modal', function (e) {
            var complaintId = $(e.relatedTarget).data('complaint-id');
            $modalBody.load(ACC.config.encodedContextPath + "/complaints/" + complaintId);
        });

        $modal.on('hidden.bs.modal', function () {
            $modalBody.html("");
        });

        var enquiryList = $('#enquiryList');
        enquiryList.val('').trigger('change');

        enquiryList.on("change", function () {
            if ($(this).closest('.form-group').hasClass('has-error')) {
                $(this).closest('.form-group').removeClass('has-error');
            }

            if ($(this).nextAll('.help-block:first').length) {
                $(this).nextAll('.help-block').remove();
            }

            var enquiryType = $(this).val();

            var $categoryOneSelect = $("#categoriesOneList");
            var $categoryTwoSelect = $("#categoriesTwoList");
            $categoryOneSelect.empty();
            $categoryTwoSelect.empty();

            var filteredCategoriesOne = SAGIA.profile.categoryOne.filter(function (el) {
                return el.parentId === enquiryType;
            });

            $.each(filteredCategoriesOne, function (key, value) {
                $categoryOneSelect.append($("<option></option>").attr("value", value.id).text(value.description));
            });
            $categoryOneSelect.val('');
        });

        $('#categoriesOneList').on("change", function () {
            if ($(this).closest('.form-group').hasClass('has-error')) {
                $(this).closest('.form-group').removeClass('has-error');
            }

            if ($(this).nextAll('.help-block:first').length > 0) {
                $(this).nextAll('.help-block').remove();
            }

            var selectedCategoryOne = $(this).val();
            var $categoryTwoSelect = $("#categoriesTwoList");
            $categoryTwoSelect.empty();

            var filteredCategoriesTwo = SAGIA.profile.categoryTwo.filter(function (el) {
                return el.parentId === selectedCategoryOne;
            });
            $.each(filteredCategoriesTwo, function (key, value) {
                $categoryTwoSelect.append($("<option></option>").attr("value", value.id).text(value.description));
            });
            $categoryTwoSelect.val('');
        });

        $('#categoriesTwoList').on("change", function () {
            if ($(this).closest('.form-group').hasClass('has-error')) {
                $(this).closest('.form-group').removeClass('has-error');
            }
            if ($(this).nextAll('.help-block:first').length > 0) {
                $(this).nextAll('.help-block').remove();
            }
        });

        $(".js-attach-file-download").click(function () {
            var element = $(this);
            var objectId = element.data('objectId');
            var documentId = element.data('documentId');
            window.open(ACC.config.encodedContextPath + "/my-sagia/attachment/" + objectId + "/" + documentId, '_blank');
        });

        $('#complaintFormData').validate({
            highlight: function highlight(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function unhighlight(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            focusInvalid: false,
            ignore: "",
            errorElement: 'span',
            errorPlacement: function errorPlacement(error, element) {
                if (element.closest('.form-group').find('.help-block').length) {
                    error.appendTo(element.closest('.form-group').find('.help-block'));
                } else {
                    error.appendTo(element.closest('.formInputBox').find('.help-block'));
                }
            },
            rules: {
                "details.Subject": "required",
                "details.TextMsg": "required",
                "details.EnquiryType": "required",
                "details.Category1": "required",
                "details.Category2": "required",
                "details.Branch": "required"
            },
            messages: {
                "details.Subject": {
                    required: getI18nText("validation.subject")
                },
                "details.TextMsg": {
                    required:getI18nText("validation.message")
                },
                "details.EnquiryType": {
                    required: getI18nText("validation.enquiry.type")
                },
                "details.Category1": {
                    required: getI18nText("validation.topic")
                },
                "details.Category2": {
                    required: getI18nText("validation.subtopic")
                },
                "details.Branch": {
                    required: getI18nText("validation.branch")
                }
            },
            submitHandler: function submitHandler(form) {
                $.ajaxForm({
                    url: form.action,
                    type: form.method,
                    data: $(form).serialize(),
                    enctype: form.enctype
                });
            },
            invalidHandler: function invalidHandler() {
                document.getElementById("details.TextMsg").focus();
            }
        });

        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/complaintData",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(complaintData) {
                if (complaintData) {
                    var processingTime = complaintData.processingTime;
                    if (processingTime) {
                        var serviceTime = $("#serviceTime");
                        serviceTime.show();
                        serviceTime.find(".processingTimeDays").text(processingTime.days);
                        serviceTime.find(".processingTimeHours").text(processingTime.hours);
                    }

                    var complaintList = complaintData.complaintList;
                    var paginationModule = $("#ticketsSection .paginationModule");
                    if (complaintList && complaintData.TicketsPagesNumber) {
                        var profileTicketsTable = $("#profileTicketsTable");
                        for (var key in complaintList) {
                            if (complaintList.hasOwnProperty(key)) {
                                var complaint = complaintList[key];
                                var template = $(".profileTicketsTemplateWrapper").find("tbody").clone();
                                template.find(".lastUpdate").html(complaint.lastUpdateData.dateFormatted);
                                template.find(".ticketNumber").html(complaint.ticketNumber);
                                template.find(".enquiryType").html(complaint.enquiryType);
                                template.find(".status").html(complaint.status);
                                template.find(".details").find("a").attr("data-complaint-id", complaint.ticketNumber);
                                profileTicketsTable.find("tbody").append(template.html());
                            }
                        }
                        var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket active">1</a></div>';
                        for (var i = 2; i <= complaintData.TicketsPagesNumber; i++) {
                            paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket">' + i + '</a></div>';
                        }
                        $("#ticketsSection .paginationModule-items").empty().append(paginationHtml);
                        new CreatePagination($("#ticketsSection .paginationModule"), complaintData.ticketsItemsPerPage);
                        $("#ticketsSection .paginationPicker").append("<option selected='selected' value='" + complaintData.ticketsItemsPerPage + "'>" + getI18nText("dashboard.servicesRequest.viewAll") + "</option>")

                        if(complaintData.showItemsPerPage != null && complaintData.showItemsPerPage.length > 0){
                            var items = complaintData.showItemsPerPage.split(",");
                            for(i=0;i<items.length;i++){
                                $("#ticketsSection .paginationPicker").append("<option value='" +  items[i] + "'>"+ items[i] +"</option>");
                            }
                        }
                    }
                    $('#ticketsSection .loadingModule').addClass('loadingModule_loaded');
                    paginationModule.removeClass('paginationModule_loading');

                    var complaintFormData = complaintData.complaintFormData;
                    if (complaintFormData) {
                        var enquiriesTabData = $("#enquiriesTabData");
                        SAGIA.profile.categoryOne = [];
                        SAGIA.profile.categoryTwo = [];

                        var enquiryList = enquiriesTabData.find("#enquiryList");
                        for (var indexEnquiryTypes in complaintFormData.enquiryTypes) {
                            if (complaintFormData.enquiryTypes.hasOwnProperty(indexEnquiryTypes)) {
                                var enquiryType = complaintFormData.enquiryTypes[indexEnquiryTypes];
                                enquiryList.append($("<option></option>").attr("value", enquiryType.catID).text(enquiryType.catDesc));
                            }
                        }
                        enquiryList.val('').trigger('change');

                        var branches = enquiriesTabData.find("#details\\.Branch");
                        for (var indexBranches in complaintFormData.branches) {
                            if (complaintFormData.branches.hasOwnProperty(indexBranches)) {
                                var branch = complaintFormData.branches[indexBranches];
                                branches.append($("<option></option>").attr("value", branch.fieldkey).text(branch.description));
                            }
                        }
                        branches.val('').trigger('change');

                        var categoriesOneList = enquiriesTabData.find("#categoriesOneList");
                        for (var indexCategoriesOneList in complaintFormData.categoryOne) {
                            if (complaintFormData.categoryOne.hasOwnProperty(indexCategoriesOneList)) {
                                var categoryOne = complaintFormData.categoryOne[indexCategoriesOneList];
                                categoriesOneList.append($("<option></option>").attr("value", categoryOne.catID).text(categoryOne.catDesc));
                                SAGIA.profile.categoryOne.push({
                                    "description": categoryOne.catDesc,
                                    "id": categoryOne.catID,
                                    "parentId": categoryOne.parentID
                                });
                            }
                        }
                        categoriesOneList.val('').trigger('change');

                        var categoriesTwoList = enquiriesTabData.find("#categoriesTwoList");
                        for (var indexCategoriesTwoList in complaintFormData.categoryTwo) {
                            if (complaintFormData.categoryTwo.hasOwnProperty(indexCategoriesTwoList)) {
                                var categoryTwo = complaintFormData.categoryTwo[indexCategoriesTwoList];
                                categoriesTwoList.append($("<option></option>").attr("value", categoryTwo.catID).text(categoryTwo.catDesc));
                                SAGIA.profile.categoryTwo.push({
                                    "description": categoryTwo.catDesc,
                                    "id": categoryTwo.catID,
                                    "parentId": categoryTwo.parentID
                                });
                            }
                        }
                        categoriesTwoList.val('').trigger('change');

                        var counter = 0;
                        for (var indexAttachments in complaintFormData.attachments) {
                            var attachmentsSection = $(".hiddenAttachmentsSection");
                            if (complaintFormData.attachments.hasOwnProperty(indexAttachments)) {
                                var attachment = complaintFormData.attachments[indexAttachments];
                                attachmentsSection.append('<input type="hidden" name="dockeys[' + counter++ + ']" value="' + attachment.dockey_ID + '"/>');
                            }
                        }
                    }
                }
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.enquiry.data"));
                errorModal.modal('show');
                $('#ticketsSection .loadingModule .loadingModule-icon').hide();
                $('#ticketsSection .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>" + getI18nText('general.couldnot.load.enquiry.data') + "</span>");
            }
        });
    },
    updateQuestionnariesData: function updateQuestionnariesData() {
        var newSurveysSection = $("#newSurveysSection");
        var surveysSection = $("#surveysSection");
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/surveyData",
            method: 'GET',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            //ajaxHideLoadingIndicator: true,
            success: function success(surveyData) {
                if (surveyData && surveyData.newSurveyList && surveyData.surveyList) {
                    $("#questionnairesTab #surveyCount").text(surveyData.surveyCount);

                    var newSurveyList = surveyData.newSurveyList;
                    if (newSurveyList.length > 0) {
                        newSurveysSection.show();
                        var htmlContentToAddNewSurvey = "";
                        for (var newSurveyIndex in newSurveyList) {
                            if (newSurveyList.hasOwnProperty(newSurveyIndex)) {
                                var newSurvey = newSurveyList[newSurveyIndex];
                                var newSurveySection = $(".newQuestionnaireTemplate").clone();
                                newSurveySection.removeClass(".newQuestionnaireTemplate");
                                newSurveySection.find(".newSurveyLink").attr("href", newSurvey.isFetchedFromNotificationService ? "questionnaires/" + newSurvey.transactionId : "questionnaires/" + newSurvey.surveyid + "/" + newSurvey.surveyversion);
                                newSurveySection.find(".newSurveyTitle").text(newSurvey.surveytitle);
                                newSurveySection.show();
                                newSurveysSection.append(newSurveySection);
                                htmlContentToAddNewSurvey += newSurveySection.get(0).outerHTML; //adding the element together would be too slow if too many
                            }
                        }
                        newSurveysSection.append(htmlContentToAddNewSurvey);
                    }

                    var surveyList = surveyData.surveyList;
                    if (surveyList.length > 0) {
                        surveysSection.show();
                        var htmlContentToAddSurvey = "";
                        for (var surveyIndex in surveyList) {
                            if (surveyList.hasOwnProperty(surveyIndex)) {
                                var survey = surveyList[surveyIndex];
                                var surveySection = $(".questionnaireTemplate").clone();
                                surveySection.removeClass(".questionnaireTemplate");
                                surveySection.find(".surveyLink").attr("href", survey.isFetchedFromNotificationService ? "questionnaires/" + survey.transactionId : "questionnaires/" + survey.surveyid + "/" + survey.surveyversion);
                                surveySection.find(".surveyTitle").text(survey.surveytitle);
                                surveySection.show();
                                htmlContentToAddSurvey += surveySection.get(0).outerHTML; //adding the element together would be too slow if too many
                            }
                        }
                        surveysSection.append(htmlContentToAddSurvey);
                    }
                }
            }
        });
    }
};

$(function () {
    $('#sagiaProfilePersonalFormSelectCountry').on('change', function () {
        var countryCode = this.value.substr(0, 2);
        if (countryCode) {
            $.ajax(ACC.config.encodedContextPath  + "/my-sagia/license/bidding-code/" + countryCode, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function (countryPrefix) {
                    if (countryPrefix.length > 0) {
                        var prefix = '+' + countryPrefix;
                        $('#sagiaProfilePersonalFormSelectMobileCountryCode').val(prefix).trigger('change');
                    }
                }
            });
        }
    });

    var questionnairesTab = $("#questionnairesTab");
    if (questionnairesTab.is(":visible")) {
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/surveyCount",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(surveyData) {
                if (surveyData && surveyData.surveyCount) {
                    $("#questionnairesTab #surveyCount").text(surveyData.surveyCount);
                }
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText('general.couldnotretrieve.survey'));
                errorModal.modal('show');
            }
        });
    }

    var enquiriesTab = $("#enquiriesTab");
    if (enquiriesTab.is(":visible")) {
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/complaintsCount",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function success(complaintsData) {
                if (complaintsData) {
                    $("#enquiriesTab #complaintsCount").text(complaintsData.complaintsCount);
                }
            },
            error: function error() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText('general.couldnotretrieve.complaints'));
                errorModal.modal('show');
            }
        });
    }

    $("#updateContactsButton").on("click", function () {
        SAGIA.profile.updateContacts();
    });

    var companyTabAlreadyRendered = false;
    if ($("#companyTabData").is(":visible")) {
        SAGIA.profile.updateCompanyData();
        companyTabAlreadyRendered = true;
    }
    $("#companyTab").on("click", function () {
        if (!companyTabAlreadyRendered) {
            SAGIA.profile.updateCompanyData();
            companyTabAlreadyRendered = true;
        }
    });

    var updateMyProfileAlreadyRendered = false;
    if ($("#myProfileTabData").is(":visible")) {
        SAGIA.profile.updateMyProfileData();
        updateMyProfileAlreadyRendered = true;
    }
    $("#myProfileTab").on("click", function () {
        if (!updateMyProfileAlreadyRendered) {
            SAGIA.profile.updateMyProfileData();
            updateMyProfileAlreadyRendered = true;
        }
    });

    var updateSecurityAlreadyRendered = false;
    var passwordTabData = $("#passwordTabData");
    if (passwordTabData.is(":visible")) {
        SAGIA.profile.updateSecurityData();
        updateSecurityAlreadyRendered = true;
    }
    $("#passwordTab").on("click", function () {
        if (!updateSecurityAlreadyRendered) {
            SAGIA.profile.updateSecurityData();
            updateSecurityAlreadyRendered = true;
        }
    });

    var updateEnquiriesAlreadyRendered = false;
    var enquiriesTabData = $("#enquiriesTabData");
    if (enquiriesTabData.is(":visible")) {
        SAGIA.profile.updateEnquiriesData();
        updateEnquiriesAlreadyRendered = true;
    }
    enquiriesTab.on("click", function () {
        if (!updateEnquiriesAlreadyRendered) {
            SAGIA.profile.updateEnquiriesData();
            updateEnquiriesAlreadyRendered = true;
        }
    });

    var updateQuestionnairesAlreadyRendered = false;
    var questionnairesTabData = $("#questionnairesTabData");
    if (questionnairesTabData.is(":visible")) {
        SAGIA.profile.updateQuestionnariesData();
        updateQuestionnairesAlreadyRendered = true;
    }
    questionnairesTab.on("click", function () {
        if (!updateQuestionnairesAlreadyRendered) {
            SAGIA.profile.updateQuestionnariesData();
            updateQuestionnairesAlreadyRendered = true;
        }
    });

    SAGIA.profile.init();
});

$(document).on("click", ".myAccount-profilImage-change", function () {
     var actionUrl = $(this).data('action');
     $('#modalPictureUploadForm').attr('action',actionUrl);
// console.log(actionUrl);
      $('#uploadFilePicture').modal('show');
});


$("#resetPassword").on('click',function(){
    $("#oldPwd-error").remove();
    $("#pwd-error").remove();
    $("#checkPwd-error").remove();
    $("#updatePwdForm .form-group").removeClass('has-error');
})