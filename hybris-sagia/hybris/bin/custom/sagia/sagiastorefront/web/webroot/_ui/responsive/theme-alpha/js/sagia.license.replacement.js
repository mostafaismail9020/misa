$(document).ready(function () {
    $(document).on("change", '.form-control.js-inputFile', function() {
        var currentInputFileId = $(this).attr('id');
        var controlLabelId = "controlLabel-" + currentInputFileId;
        var fileName = $(this).val().replace(/.*[\/\\]/, '');
        $('#' + controlLabelId).text(fileName);
    });

    $('#checkbox01').on('click', function (e) {
        if ($(this).is(":checked")) {
            if ($(this).parents('.form-item').find('.help-block').length > 0) {
                $(this).parents('.form-item').find('.help-block').text('');
            }
        }
    });

    $(document).on("click", ".js-replace-new-validation", function(e){
        e.preventDefault();
        var entityStatus = $(this).attr('data-entity-status');
        if(!entityStatus) {
            SAGIA.showError("No entity status. Cannot continue");
            return;
        }
        var entityStatusToUpperCase = entityStatus.trim().toUpperCase();
        if(entityStatusToUpperCase.includes("ACTIVE")){
            window.location.href= ACC.config.encodedContextPath + '/my-sagia/license/replace/new';
        } else {
            SAGIA.showError(getI18nText("license.replacement.validation.entityStatus.error"));
        }
    });

    function check_validations () {
        var checkBox = document.getElementById('checkbox01');
        var file1 = $("#file01").val() || $("#file01").closest('.formInputFile').find(".js-mock-input").val();
        var allFilesToBeUploaded = new Array();
        allFilesToBeUploaded.push(file1);

        if(!validateTermsAndConditions('#checkbox01'))
           return false;

        var isEmpty = function(inputFile) {
            return inputFile === '';
        };

        if (allFilesToBeUploaded.some(isEmpty)) {
            $('#errorResponseModal').find('.modal-description').text(getI18nText("general.pleaseuploadfiles"));
            $('#errorResponseModal').modal('show');
            event.preventDefault();
            return false;
        }
    }
    $('#resubmitButton').click(function(event) {
        event.preventDefault();
        var resubmitURI = ACC.config.encodedContextPath + "/my-sagia/license/replace/" + $('#currentID').text() + "/" + $('#currentGUID').text() + "/resubmit";
        $('#licenseReplacementResubmitForm').attr('action', resubmitURI);
        $('#licenseReplacementResubmitForm').submit();
    });

    $('#licenseReplacementResubmitForm').submit(function(e) {
        e.preventDefault();
        var $form = $("#licenseReplacementResubmitForm");
        var fd = new FormData($(this)[0]);
        console.info(fd);
        var token = $('input[name="CSRFToken"]').attr('value')

        $.ajax({
            type: 'POST',
            url: $form.attr('action'),
            data: fd,
            cache: false,
            processData: false,
            contentType: false,
            beforeSend: function(xhr) {
                xhr.setRequestHeader('CSRFToken', token);
                xhr.setRequestHeader('Accept', "application/json");
            },
            success: function(response) {
                $('#errorResponseModal').find('.modal-description').text(getI18nText("general.request.resubmitted"));
                $('#errorResponseModal').modal('show');
            },
            error: function(xhr, status, error) {
                var errorMessage = xhr.responseJSON.sagiaExceptionResponse.message;
                $('#errorResponseModal').find('.modal-description').text(errorMessage);
                $('#errorResponseModal').modal('show');
            }
        });
    });

$('#formNewLicenseReplacement').submit(function (e) {
    e.preventDefault();
    var formValidation = check_validations();
    if(formValidation == false) {
    		return false;
    }
    
    var $form = $("#formNewLicenseReplacement");
    var fd = new FormData($(this)[0]);
    var token = $('input[name="CSRFToken"]').attr('value')
    $.ajax({
        type : 'POST',
        url : $form.attr('action'),
        data : fd,
        cache : false,
        processData : false,
        contentType : false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader('CSRFToken', token);
            xhr.setRequestHeader('Accept', "application/json");
        },
        success : function(srId) {
        		showAndSendFeedback(srId);
        },
        error : function(e) {
            $('#errorResponseModal').find('.modal-description').text(JSON.parse(e.responseText));
            $('#errorResponseModal').modal('show');
        		//SagiaExceptionResponse is thrown by backend
        		//It is treated by the global exception handler already, common.js
        	
//            var jsonResponse = xhr.responseJSON;
//            var exceptionResponse = (typeof jsonResponse != 'undefined') ? jsonResponse.sagiaExceptionResponse : undefined;
//            var errorMessageToDisplay = "";
//            var jsonResponse;
//            if (xhr.responseJSON.indexOf('{') >= 0) {
//                jsonResponse = xhr.responseJSON.substring(xhr.responseJSON.indexOf('{'));
//            }
//            if (status === "error") {
//                var jsonErrorResponse = JSON.parse(jsonResponse);
//                if (jsonErrorResponse != null && jsonErrorResponse.error != null && jsonErrorResponse.error.message != null && jsonErrorResponse.error.message.value != null) {
//                    errorMessage = jsonErrorResponse.error.message.value;
//                }
//            }
//
//            if(hasTermsAndConditionsError(xhr.responseText)){
//                validateTermsAndConditions('#checkbox01');
//            }
//            if(exceptionResponse != undefined) {
//            		errorMessageToDisplay = exceptionResponse.description;
//            }
//            else{
//            		errorMessageToDisplay = getI18nText("license.cancellation.error");
//            }
//            if(errorMessageToDisplay != undefined){
//                $('#errorResponseModal').find('.modal-description').text(errorMessageToDisplay);
//                $('#errorResponseModal').modal('show');
//            }
        }
    });
});

    var modal = $('#uploadFileModal');
    modal.on('shown.bs.modal', function(e) {
        var objectId = $(e.relatedTarget).data('object-id');
        var inputFileItemId = 'formInputListItem' + objectId;
        $('#' + inputFileItemId).show();
    });
    modal.on('hidden.bs.modal', function() {
        $('#inputFilesList li').hide();
    });

    $('#attachmentList').on('click', 'div[data-view-attachment-target]', function (e) {
        var objectId = $(this).data('objectId');
        var documentId = $(this).data('documentId');
        window.open(ACC.config.encodedContextPath + "/attachment/pdf/" +
            objectId + "/" + documentId, '_blank');
    });

    $('ul#history-list').on('click', 'li', function (e) {
        var srID = $(e.currentTarget).find('.historyList-id').text();
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";

        $.ajax({
            type: 'GET',
            url: ACC.config.encodedContextPath + "/my-sagia/license/replace/" + srID ,
            dataType: 'json',
            success: function (res) {
                var attachedFiles = res.LicenseReplToUploadNav;
                var attachedFilesDivContent = $('#attachmentList');
                attachedFilesDivContent.empty();

                var messagesList = res.LicenseReplToTextNav;
                var messagesListDivContent = $('messagesListUL');
                messagesListDivContent.empty();
                var supportedAttachmentsTemplate = document.getElementById("supportedAttachments-template");
                var supportedAttachmentsTemplateHtml = supportedAttachmentsTemplate.innerHTML;
                var listHtml = "";

                var uploadFilesModalTemplate = document.getElementById("uploadFilesModal-template");
                var uploadFilesModalTemplateHtml = uploadFilesModalTemplate.innerHTML;
                var modalListHtml = "";

                for (var key in attachedFiles) {
                    var objectID = attachedFiles[key]["objectID"];
                    var documentID = attachedFiles[key]["documentID"];
                    var downloadUrl = ACC.config.encodedContextPath + "/attachment/pdf/" + objectID + "/" + documentID;
                    listHtml += supportedAttachmentsTemplateHtml.replace(/{{filename}}/g, attachedFiles[key]["filename"])
                            .replace(/{{objectID}}/g, objectID)
                            .replace(/{{documentID}}/g,documentID)
                            .replace(/{{downloadUrl}}/g,downloadUrl)
                            .replace(/{{fullFileName}}/g,attachedFiles[key]["fullFileName"]);

                        modalListHtml += uploadFilesModalTemplateHtml.replace(/{{documentID}}/g, attachedFiles[key]["documentID"])
                            .replace(/{{index}}/g, Object.keys(attachedFiles).indexOf(key));
                    }

                    var expandedReplTemplate = document.getElementById("expandedLicenseReplace-template");
                    var expandedReplTemplateHtml = expandedReplTemplate.innerHTML;
                    var expandedDataHtml = expandedReplTemplateHtml
                        .replace(/{{srID}}/g, res.srID)
                        .replace(/{{srGuid}}/g, res.srGuid)
                        .replace(/{{srStDesc}}/g, res.srStDesc)
                        .replace(/{{srStDescIndicator}}/g, res.srStDesc.replace(/\s+/g, '').toLowerCase())
                        .replace(/{{longDescr}}/g, res.longDescr);

                    var	messagesListHtml = '';
                    if(typeof messagesList != "undefined" &&  messagesList!= null && messagesList.length != null && messagesList.length > 0) {
                        var messagesLicenseReplaceTemplate = document.getElementById("messagesLicenseReplace-template");
                        var messagesLicenseReplaceTemplateHtml = messagesLicenseReplaceTemplate.innerHTML;
                        for(var key in messagesList) {
                            messagesListHtml += messagesLicenseReplaceTemplateHtml
                                .replace(/{{commentBy}}/g, messagesList[key]["commentBy"])
                                .replace(/{{commentDate}}/g, messagesList[key].commentDate.dateFormatted)
                                .replace(/{{comments}}/g, messagesList[key]["comments"]);
                        }
                    } else {
                        var messagesLicenseReplaceTemplate = document.getElementById("missingMessages-template");
                        var messagesLicenseReplaceTemplateHtml = messagesLicenseReplaceTemplate.innerHTML;
                        messagesListHtml += messagesLicenseReplaceTemplateHtml
                    }

                    var replNationalStatus = res.srStDesc;
                    var isRejected = replNationalStatus.toUpperCase() === 'REJECTED';
                    $('#detailedLicenseReplacementContent').html(expandedDataHtml);
                    $('#attachmentList').html(listHtml);
                    $('#inputFilesList').html(modalListHtml);
                    $('#messagesListUL').html(messagesListHtml);
                    if (isRejected) {
                        document.getElementById("resubmitButton").disabled = false;
                        document.getElementById("resubmitButton").style.display = "block";

                        var replaceAnchorTags = $('a[id*="replaceAnchorTag"]');
                        for (var i = 0; i < replaceAnchorTags.length; ++i) {
                            var elA = replaceAnchorTags[i];
                            elA.style.display = "block";
                        }
                    } else {
                        document.getElementById("resubmitButton").disabled = true;
                        document.getElementById("resubmitButton").style.display = "none";
                        var replaceAnchorTags = $('a[id*="replaceAnchorTag"]');
                        for (var i = 0; i < replaceAnchorTags.length; ++i) {
                            var elA = replaceAnchorTags[i];
                            elA.style.display = "none";
                        }
                    }
                }
            });
        });

function showAndSendFeedback(serviceId) {
	$('#serviceId').val(serviceId);
	$('#requestSubmittedComment').modal();
	$('#requestSubmittedComment').on('hide.bs.modal', function() {
		window.location.href = './';
	});
}

$(".js-load-draft").click(
    function () {
        var id = $(this).data("targetForm");
        var serviceId = $(this).data("serviceId");
        $.ajax({
            type: 'get',
            url: ACC.config.encodedContextPath + '/draft?formId=' + serviceId
        })
            .done(function (response) {
                if (response && response.files) {
                    response.files.forEach(
                        function(file, index) {
                            var fileInput = $('[name="' + file.attachmentInputName + '"]');
                            var textInput = fileInput.next('input:text'),
                                rootElement = fileInput.closest('.formInputFile');

                            textInput.attr('placeholder', file.name);

                            var fileIndex = fileInput.data('id');
                            var mockFileInput = '<input id="fileData1" name="fileData[' + fileIndex +  ']" file-id="' + fileIndex +'" class="form-control js-mock-input" style="display:none;" type="text" value="' + file.id + '">';
                            textInput.after(mockFileInput);

                            if (fileInput.length > 0) {
                                rootElement.addClass('active');
                            } else {
                                rootElement.removeClass('active');
                            }
                        }
                    );

                    $("#" + id).trigger('change');
                }
            })
    });

    $("#replaceSearchBox").on("keyup", function () {
        var valThis = $(this).val();
        $('#history-list > li').each(function () {
            var entry = $(this);
            var text = entry.find('.historyList-id').text();
            (text.search(valThis) == -1) ? entry.hide() : entry.show();
        });
    });
});