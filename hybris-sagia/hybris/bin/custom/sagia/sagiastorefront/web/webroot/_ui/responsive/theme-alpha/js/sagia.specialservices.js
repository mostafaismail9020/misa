//updates the status in the detail section based on the selected service instance

var infoResponseModal = $("#infoResponseModal");

function updateStatus(e) {
    var status = $(e.currentTarget).find('.historyList-status').text();
    var statusIndicator = $('.statusIndicator');
    statusIndicator.html('Status: <span>' + status + '</span>');
    statusIndicator.removeClassExcept("statusIndicator");

    if(!status || !status.length) {
        return;
    }
    var statusToLowerCase = status.trim().toLowerCase();
    switch (statusToLowerCase) {
        case statusToLowerCase.includes('process'):
            statusIndicator.addClass('statusIndicator_process');
            break;
        case statusToLowerCase.includes('reject'):
            statusIndicator.addClass('statusIndicator_rejected');
            break;
        case statusToLowerCase.includes('accept'):
            statusIndicator.addClass('statusIndicator_accepted');
            break;
        case statusToLowerCase.includes('complete'):
            statusIndicator.addClass('statusIndicator_completed');
            break;
        default:
            statusIndicator.addClass('statusIndicator_process');
    }
}

function loadApplicantList(applicantsData) {
    $('.jqApplicantsTable tbody').empty();
    if(applicantsData && applicantsData.length) {
        $.each(applicantsData, function (index, applicantData) {
            var $template = $('.applicantTemplate').clone();
            // $template.find('.jqRemove').data('guid', applicantData.objectGuid);
            $template.find('.jqRemove').attr('data-guid', applicantData.objectGuid);
            $template.find('.jqApplicantName').append(applicantData.applicantName);
            $template.find('.jqIqmaNumber').append(applicantData.iqmaNumber);
            $template.find('.jqIqmaExpiryDate').append(applicantData.iqmaExpiryDate);
            $template.find('.jsNationality').append(applicantData.nationality);
            $template.find('.jqNationalityNote').append(applicantData.nationalityNote);
            $template.find('.jqApplicantProfession').append(applicantData.applicantProfession);
            $template.find('.jqInvestorNumber').append(applicantData.investorNumber);

            $('.jqApplicantsTable').append($template.html());
            $('.jqApplicantsTable tbody').addClass('tableModule-body');
        });
    }
}

function loadSessionApplicantsDraft() {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + '/special-services/' + serviceType + "/get-applicants",
        success: function (applicantsData) {
            loadApplicantList(applicantsData);
        }
    });
}

function loadSessionApplicants() {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + '/special-services/' + serviceType + '/get-applicants',
        success: function (applicantsData) {
            loadApplicantList(applicantsData);
        },
        error: function(error) {
            SAGIA.handleGenericAjaxError(error);
        }
    });
}

function removeApplicant(guid) {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + '/special-services/' + serviceType + '/remove-applicant/' + guid,
        success: function (applicantsData) {
            loadApplicantList(applicantsData);
        },
        error: function(error) {
            SAGIA.handleGenericAjaxError(error);
        }
    });
}

function addDraftAttributes(elements, data, parameters) {
    for(var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if (name && value) {
            var domElement = $("[name='" + name + "']");
            var type = domElement.attr('type') || domElement.prev().prop('nodeName');
            if (type === 'file') {
                if (element.files && element.files[0]) {
                    var attachmentName = domElement.data('attachmentName');
                    data.append('attachments', attachmentName);
                    var inputName = domElement.attr('name');
                    data.append('attachmentsNames', inputName);
                    var file = element.files[0];
                    data.append("files", file)
                }
            } else if (type === 'checkbox') {
                if (element.checked) {
                    parameters.push({
                        name: domElement.attr('name'),
                        type: "checkbox",
                        value: domElement.val()
                    });
                }
            } else if (type === 'radio') {
                if (domElement.is(':checked')) {
                    parameters.push({
                        name: domElement.attr('name'),
                        type: "radio",
                        value: domElement.val()
                    });
                }
            } else if (domElement.hasClass('js-mock-input')) {
                var fileId = domElement.val();
                data.append('draftFiles', fileId);
            } else if (domElement.hasClass('js-file-code-input')) {
                parameters.push({
                    name: name,
                    type: type,
                    value: value,
                    fileName: domElement.attr('file-name')
                });
            } else {
                parameters.push({
                    name: name,
                    type: type,
                    value: value
                });
            }
        }
    }
}

function showAndSendFeedback(serviceType) {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + "/special-services/latest/" + serviceType,
        success: function (data) {
            $('#serviceId').val(data.id);
        },
        error: function(error) {
            SAGIA.handleGenericAjaxError(error);
        }
    });

    var requestSubmittedComment = $('#requestSubmittedComment');
    requestSubmittedComment.modal();
    requestSubmittedComment.on('hide.bs.modal', function () {
        window.location.href = './';
    });
}

function download(filePath) {
    var element = document.createElement('a');
    element.setAttribute('href', filePath);
    element.setAttribute('download', "undertakingLetterSample");
    element.style.display = 'none';
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}

function createDraftData() {
    var parameters = [];
    var data = new FormData();

    var applicantForm = document.getElementById("serviceApplicant");
    var applicantElements = applicantForm.querySelectorAll( "input, select, textarea" );
    addDraftAttributes(applicantElements, data, parameters);

    var contactForm = document.getElementById("specialServiceHeaderId");
    var elements = contactForm.querySelectorAll( "input, select, textarea" );
    addDraftAttributes(elements, data, parameters);


    var applicantList = $('#existingApplicants');
    applicantList.find('input[name="applicant"]').each(function() {
        if($(this).is(":checked")) {
            parameters.push({
                name: "selectedApplicantId",
                type: "selectedApplicantId",
                value:$(this).attr('id')
            });
            return false;
        }
    });

    data.append("paramsCount", parameters.length);
    parameters.forEach(function(parameter, index) {
        data.append("parameters[" + index + "][name]", parameter.name);
        data.append("parameters[" + index + "][type]", parameter.type ? parameter.type : "");
        data.append("parameters[" + index + "][value]", parameter.value ? parameter.value : "");
        if (parameter.fileName) {
            data.append("parameters[" + index + "][fileName]", parameter.fileName);
        }
    });

    return data;
}

$(function () {
    $('.specialServiceItem').on("click", function () {
        var id = $(this).find('.historyList-id').text();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + '/special-services/' + serviceType + "/" + id,
            success: function (data) {
                var service = JSON.parse(data);
                var applicants = service.applicants;
                var comments = service.comments;
                var applicantListHtml, applicantsHtml;
                applicantListHtml = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><g fill="#5CC83B" fill-rule="evenodd"><path d="M11.956 21.702c-5.375 0-9.746-4.371-9.746-9.746s4.37-9.746 9.746-9.746c5.375 0 9.746 4.37 9.746 9.746 0 5.375-4.371 9.746-9.746 9.746zm0-21.702c-6.58 0-11.956 5.375-11.956 11.956 0 6.58 5.375 11.906 11.956 11.906 6.58 0 11.906-5.325 11.906-11.906 0-6.58-5.325-11.956-11.906-11.956zM13.387 16.213l-1.005.452 1.306-6.681c.15-.703-.603-1.457-1.407-1.005l-2.461 1.357c-.452.25-.603.853-.352 1.306.251.452.854.602 1.306.351l.603-.301-1.256 6.38c-.1.552.352 1.155.955 1.155.15 0 .301-.05.402-.1l2.662-1.156c.503-.2.703-.753.503-1.256-.201-.502-.754-.703-1.256-.502m-.662-8.953a1.35 1.35 0 0 0 1.356-1.356 1.35 1.35 0 0 0-1.356-1.357 1.35 1.35 0 0 0-1.357 1.357 1.35 1.35 0 0 0 1.357 1.356"/></g></svg>' +
                    'Applicant List: ' + service.id;

                applicants.forEach(function (element) {
                    applicantsHtml += '<tr>' +
                        '<td class="tableModule-bodyItem-right">' + element.applicantName + '</td>' +
                        '<td>' + element.iqmaNumber + '</td>' +
                        '<td>' + element.iqmaExpiryDate + '</td>' +
                        '<td>' + element.nationality + '</td>' +
                        '<td>' + element.nationalityNote + '</td>' +
                        '<td>' + element.applicantProfession + '</td>' +
                        '<td>' + element.investorNumber + '</td>' +
                        '<td>' + element.applicantCategory + '</td>' +
                        '</tr>'
                });

                if (service.attachedDocuments != null && service.attachedDocuments.length > 0) {
                    $("#specialServiceAttachments").attr('hidden', false);
                    var filesHtml = '';
                    var rootUrl = ACC.config.encodedContextPath + '/special-services/';
                    service.attachedDocuments.forEach(function (element) {
                        if (element.fileName.length > 0) {
                            filesHtml +=
                                '<li class="downloadList-item js-download-service-attachment" style="cursor: pointer" data-attachment-object-id="' + element.objectId + '" data-attachment-document-guid="' + element.documentGuid + '">' +
                                '<div class="downloadList-description">' +
                                '<span class="iconElement iconElement_pdf">' +
                                '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="24" height="32" viewBox="0 0 24 32"><path d="M8.809 25.185c-.095-.019-.286-.026-.572-.026h-.384v1.116h.435c.312 0 .523-.021.627-.062.106-.041.189-.104.249-.192.06-.087.09-.19.09-.306 0-.144-.042-.261-.126-.355-.085-.093-.19-.152-.319-.175zM12.895 25.381c-.097-.092-.22-.152-.368-.185-.11-.026-.328-.038-.652-.038h-.356v2.606h.593c.222 0 .383-.014.48-.038.129-.033.236-.087.32-.164.085-.077.155-.203.209-.379.054-.178.08-.418.08-.722s-.026-.537-.08-.701c-.055-.161-.131-.289-.226-.379zM17.091.539h-16.491c-.276 0-.5.224-.5.5v29.921c0 .277.224.501.5.501h22.8c.276 0 .5-.224.5-.501v-23.612l-6.809-6.809zm-7.149 25.773c-.088.165-.199.294-.334.389-.135.093-.273.156-.413.187-.189.037-.465.056-.824.056h-.518v1.484h-.794v-3.935h1.274c.483 0 .799.02.945.059.225.059.415.188.566.386.152.196.228.452.228.767.001.238-.043.443-.13.607zm3.958 1.023c-.099.286-.238.519-.421.694-.138.135-.323.239-.559.314-.175.056-.409.083-.703.083h-1.494v-3.935h1.451c.327 0 .578.025.749.075.231.068.428.189.594.362.164.174.289.386.376.639.085.251.129.56.129.929 0 .325-.041.604-.122.839zm3.499-2.177h-1.904v.931h1.644v.665h-1.644v1.673h-.794v-3.935h2.698v.666zm5.501-3.006h-21.8v-20.613h15.555v6.06h6.245v14.553z"/></svg>' +
                                '</span>' + element.fileName +
                                '</div>' +
                                '<div class="downloadList-actions">' +
                                '<a href="' + rootUrl + 'pdf/ATTACHMENTDETAILSSET/' + element.objectId + '/' + element.documentGuid 
                                + '" class="link link_nowrap"  download="'+ element.fullFileName 
                                + '"> '
                                +'<span class="iconElement iconElement_cloud02">' +
                                '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
                                '    <path fill="#5CC83B" d="M3.769,12.936c-0.154,0-1.459-0.028-2.381-0.936C0.799,11.421,0.5,10.627,0.5,9.64\n' +
                                '        c0-1.043,0.45-2.001,1.234-2.629c0.568-0.455,1.26-0.683,1.964-0.664C4.094,3.589,6.333,1.5,9,1.5c1.962,0,3.78,1.164,4.713,2.99\n' +
                                '        c0.922,0.045,1.791,0.423,2.479,1.084c0.832,0.8,1.31,1.942,1.31,3.134c0,2.946-2.33,4.05-3.562,4.222l-1.128,0.005\n' +
                                '        c-0.276,0-0.5-0.224-0.5-0.5s0.224-0.5,0.5-0.5h1.059c0.039-0.011,2.631-0.425,2.631-3.227c0-0.921-0.365-1.801-1.003-2.414\n' +
                                '        c-0.572-0.551-1.312-0.835-2.082-0.807c-0.204,0.013-0.394-0.11-0.477-0.297C12.218,3.556,10.672,2.5,9,2.5\n' +
                                '        c-2.305,0-4.219,1.947-4.356,4.432c-0.008,0.146-0.08,0.282-0.197,0.371C4.33,7.392,4.178,7.425,4.036,7.392\n' +
                                '        C3.443,7.259,2.847,7.401,2.36,7.791C1.813,8.228,1.5,8.902,1.5,9.64c0,0.706,0.196,1.259,0.584,1.643\n' +
                                '        c0.66,0.656,1.657,0.646,1.693,0.653h1.392c0.276,0,0.5,0.224,0.5,0.5s-0.224,0.5-0.5,0.5H3.783\n' +
                                '        C3.781,12.936,3.776,12.936,3.769,12.936z M9.849,16.609V8.957c0-0.276-0.224-0.5-0.5-0.5s-0.5,0.224-0.5,0.5v7.652\n' +
                                '        c0,0.276,0.224,0.5,0.5,0.5S9.849,16.886,9.849,16.609z M9.701,16.963l2.088-2.086c0.195-0.195,0.195-0.512,0-0.707\n' +
                                '        s-0.512-0.195-0.707,0l-1.734,1.732L7.615,14.17c-0.195-0.195-0.512-0.195-0.707,0s-0.195,0.512,0,0.707l2.087,2.086\n' +
                                '        c0.098,0.098,0.226,0.146,0.354,0.146S9.604,17.061,9.701,16.963z"></path>\n' +
                                '</svg>' +
                                '</span>' +
                                'Download' +
                                '</a>' +
                                '</div>' +
                                '</li>'
                        }
                    });
                } else{
                    $("#specialServiceAttachments").attr('hidden', true);
                }
                $('.applicantList').html(applicantListHtml);
                $('.tableModule-body').html(applicantsHtml);
                $('#specialServiceRegion').text(service.serviceRegion);
                $('#specialServicesEmail').text(service.email);
                $('#specialServicesPhone').text(service.phoneNumber);
                $('.specialServicesAttachments').html(filesHtml);

                if (comments != null && comments.length > 0) {
                    $('#commentMessage').text(comments);
                    $(".contentModule-commentsSection").css("display","block");
                } else {
                    $(".contentModule-commentsSection").css("display","none");
                }
            },
            error: function(error) {
                SAGIA.handleGenericAjaxError(error);
            }
        });
    });

    var specialServiceCreateBtn = $("#specialServiceCreateBtn");
    specialServiceCreateBtn.show();

    function displayEntityErrorMessage(entityStatusToUpperCase) {
        if (entityStatusToUpperCase.includes("REVOKED")) {
            SAGIA.showError(getI18nText("special.services.validation.entityStatus.revoked"));
        } else if (entityStatusToUpperCase.includes("EXPIRED")) {
            SAGIA.showError(getI18nText("special.services.validation.entityStatus.expired"));
        } else if (entityStatusToUpperCase.includes("ACTIVE") || entityStatusToUpperCase.includes("SUSPENDED")) {
            SAGIA.showError(getI18nText("special.services.validation.entityStatus.error"));
        } else {
            SAGIA.showError("Entity " + entityStatusToUpperCase + " status is not valid");
        }
    }

    if($('#isEntityStatusValid').val() === 'false') {
        var entityStatus = $("#specialServiceCreateBtn").attr('data-entity-status');
        if(!entityStatus) {
            SAGIA.showError("No entity status. Cannot continue");
            return;
        }
        displayEntityErrorMessage(entityStatus.trim().toUpperCase());
    }

    specialServiceCreateBtn.on("click", function(e) {
        e.preventDefault();
        if($('#specialServiceErrorMsg').val()){
            SAGIA.showError($('#specialServiceErrorMsg').val());
            return;
        }
        var entityStatus = $(this).attr('data-entity-status');
        var cancelLetter = $(this).attr('data-cancel-letter');
        if(!entityStatus) {
            SAGIA.showError("No entity status. Cannot continue");
            return;
        }
        var entityStatusToUpperCase = entityStatus.trim().toUpperCase();
        //as required, validation in Hybris is disabled
        // if((
        //     (
        //         !(entityStatusToUpperCase.includes("REVOKED")) &&
        //         !(entityStatusToUpperCase.includes("EXPIRED")) &&
        //         !(entityStatusToUpperCase.includes("ACTIVE")) &&
        //         !(entityStatusToUpperCase.includes("SUSPENDED"))
        //     ) &&
        //     (
        //         (entityStatusToUpperCase.includes("NATIONAL")) ||
        //         (entityStatusToUpperCase.includes("CANCEL"))
        //     )
        //     ) ||
        //     (
        //         entityStatusToUpperCase.includes("REVOKED") && cancelLetter
        //     )
        // ) {
            window.location = this.href;
        // } else {
        //     displayEntityErrorMessage(entityStatusToUpperCase);
        // }
    });

    $.validator.setDefaults({
        highlight: function (element) {
            if (!($(element).attr('id') === 'termsAndConditions')) {
                $(element).closest('.form-group').addClass('has-error');
            }
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        ignore: "",
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.appendTo(element.parent('.form-group')).wrap( "<div class='help-block'></div>" );
            }
        }
    });

    $('.jqApplicant').on("click", function () {
        var $parent = $(this).closest('tr');
        $('#applicantName').val($parent.find('.applicant-applicantName').text().trim());
        $('#iqmaNumber').val($parent.find('.applicant-iqmaNumber').text().trim());
        $('#iqmaExpiryDate').val($parent.find('.applicant-iqmaExpiryDate').text().trim());
        $('#nationality').val($parent.find('.applicant-nationality').text().trim());
        $('#nationalityNote').val($parent.find('.applicant-nationalityNote').text().trim());
        $('#applicantProfession').val($parent.find('.applicant-applicantProfession').text().trim());
        $('#investorNumber').val($parent.find('.applicant-investorNumber').text().trim());
    });

    $('button.transfer-of-iqama, button.final-exit-visa').on('click', function (e) {
        e.preventDefault();
        serviceApplicant.submit();
        return false;
    });
    
    $('#termsAndConditions').on('change', function (e) {
        if ($(this).is(":checked")) {
            if ($(this).parents('.form-item').find('#termsAndConditionsChecked-error').length > 0)
                $(this).parents('.form-item').find('#termsAndConditionsChecked-error').remove();
        }
    });

    var specialServiceHeaderId = $("#specialServiceHeaderId");
    specialServiceHeaderId.on("submit", function (e) {
        e.preventDefault();

        var $form = specialServiceHeaderId;
        var fd = new FormData($(this)[0]);

        var valid = $form.valid();
        if (!valid) {
            return false;
        } else {
           $('#specialServiceHeaderId button[type="submit"]').attr('disabled', true);
        }

        $.ajax({
            type: 'POST',
            url: $form.attr('action'),
            data: fd,
            cache: false,
            processData: false,
            contentType: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Accept', "application/json");
            },  
            success: function (data) {
                showAndSendFeedback(serviceType);
            }
        });
    });

    $('.jqApplicantsTable').on('click', '.jqRemove', function (e) {
        removeApplicant($(this).data('guid'));
        // $(this).closest('.table-item').remove();
    });

    specialServiceHeaderId.validate({
        rules: {
            applicationReason: {
                required: true
            },
            termsAndConditionsChecked: {
                required: true
            }
        },
        messages: {
            applicationReason: {
                required: getI18nText("special.services.validation.application.reason")

            },
            termsAndConditionsChecked: {
                required: getI18nText("acceptTerms.text")
            }
        }
    });

    var serviceApplicant = $("#serviceApplicant");
    serviceApplicant.validate({
        rules: {
            applicantName: {
                required: true
            },
            iqmaNumber: {
                required: true
            },
            applicantProfession: {
                required: true
            }
        },
        messages: {
            applicantName: {
                required: getI18nText("special.services.validation.application.name")
            },
            iqmaNumber: {
                required: getI18nText("special.services.validation.iqama")
            },
            applicantProfession: {
                required: getI18nText("special.services.validation.profession")
            }
        }
    });

    $.validator.addMethod("requiredWithDraft", function (value, element) {
        if (value) {
            return true;
        }

        var draftInput = $(element).closest(".form-group").find(".js-mock-input");
        if (draftInput.val()) {
            return true;
        }
        return false;
    }, getI18nText("special.services.validation.upload.file"));

    $('input[id^="fileToUpload"]').each(function () {
        $(this).rules( "add", {
            requiredWithDraft: true,
            messages: {
                required: getI18nText("special.services.validation.upload.file") }
        });
    });

    serviceApplicant.ajaxForm({
        beforeSubmit: function (arr, $form, options) {
            serviceApplicant.valid();
        },
        success: function (applicantData) {
            // button.transfer-of-iqama, button.final-exit-visa
            if (serviceType === 'transfer-of-iqama' || serviceType === 'final-exit-visa') {
                specialServiceHeaderId.submit();
            } else {
                loadApplicantList(applicantData);
                serviceApplicant.resetForm();
            }
        }
    });

    if (serviceType !== '' && serviceType === 'exit-re-entry-visa') {
        loadSessionApplicants();
    }

    //when an element is no longer selected, remove historyList-item_current class
    $('ul#history-list').on('click', 'li', function (e) {
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";
        updateStatus(e);
    });

    //helper function to remove all classes except one given class
    jQuery.fn.removeClassExcept = function (val) {
        return this.each(function () {
            $(this).removeClass().addClass(val);
        });
    };

    $(".js-save-draft").click(
        function () {
            var serviceId = $(this).data("serviceId");
            var data = createDraftData();
            $.ajax({
                url: ACC.config.encodedContextPath + '/special-services/' + serviceId + "/save-draft",
                data: data,
                enctype: 'multipart/form-data',
                cache: false,
                contentType: false,
                processData: false,
                method: 'POST'
            }).done(function(response) {
                $('.js-load-draft').show();
            }).fail(function(error){})
        }
    );

    $(".js-load-draft").click(function () {
        var serviceId = $(this).data("serviceId");
        $.ajax({
            type: 'get',
            url: ACC.config.encodedContextPath + '/special-services/' + serviceId + "/get-draft"
        }).done(function (response) {
            if (!response) {
                return false;
            }
            if(response.parameters) {
                response.parameters.forEach(
                    function(parameter) {
                        if (parameter.name !== "CSRFToken" && parameter.type !== 'hidden' && parameter.name !== '_termsAndConditionsChecked') {
                            var domElement = $("[name='" + parameter.name + "']");
                            if (parameter.type === 'checkbox') {
                                domElement.prop( "checked", true );
                                domElement.trigger('change')
                            } else if (parameter.name === "selectedApplicantId") {
                                $("#" + parameter.value).prop('checked', true);
                            } else {
                                domElement.attr("selectedValue", parameter.value);
                                domElement.val(parameter.value).trigger('change')
                            }
                        }
                    });
            }

            if (response.files) {
                response.files.forEach(
                    function(file, index) {
                        var fileInput = $('[name="' + file.attachmentInputName + '"]');
                        var textInput = fileInput.next('input:text'),
                            rootElement = fileInput.closest('.formInputFile');

                        textInput.attr('placeholder', file.name);

                        var fileIndex = fileInput.data('id');
                        var mockFileInput = '<input id="fileData1" name="draftFiles[' + fileIndex +  ']" file-id="' + fileIndex +'" class="form-control js-mock-input" style="display:none;" type="text" value="' + file.id + '">';
                        textInput.after(mockFileInput);

                        if (fileInput.length > 0) {
                            rootElement.addClass('active');
                        } else {
                            rootElement.removeClass('active');
                        }
                    }
                );
            }

            if (serviceType !== '' && (serviceType === 'renewal-of-iqama' || serviceType === 'exit-re-entry-visa'))  {
                loadSessionApplicantsDraft();
            }
        })
    });

    $('.downloadUndertakingLetterButton').on("click", function () {
        $.ajax({
            type: 'GET',
            url: ACC.config.encodedContextPath + "/undertakingLetter/",
            dataType: 'json',
            success: function (res) {
                var filePath = res.undertakingLetterUrl;
                download(filePath);
            },
            error: function(error) {
                SAGIA.handleGenericAjaxError(error);
            }
        });
    });

    //when an element is no longer selected, remove historyList-item_current class
    $('ul#history-list').on('click', 'li', function (e) {
        var srID = $(e.currentTarget).find('.historyList-id').text();
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";
    });

    var show = $('#specialServicesWarning').attr('show-modal');
    if (show) {
        $('#specialServicesWarning').modal('show');
        var specialServiceCreateBtn = $("#specialServiceCreateBtn");
        specialServiceCreateBtn.attr('disabled','disabled');
        $("#specialServicesWarningTitle").html(getI18nText("special.services.validation.entityStatus.error"));
    }
});

