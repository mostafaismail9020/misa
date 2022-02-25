function bindObjectAttributes(object) {
    for (var attribute in object) {
        if(object.hasOwnProperty(attribute)) {
            var value = object[attribute];
            var span = $("[name='" + attribute + "']");
            if (span !== undefined) {
                if (value !== undefined && value !== null) {
                    span.text(value);
                } else {
                    span.text('-');
                }
            }
        }
    }
}

function removeImage(event){
    $(event).closest('.js-loaded-file').remove();
}

function showAndSendRenewalFeedback(serviceId) {
    $('#serviceId').val(serviceId);
    var infoModal = $('#requestSubmittedComment');
    infoModal.find('.modal-description').text(getI18nText("special.services.wewillreviewmessage"));
    infoModal.modal('show');
    infoModal.on('hide.bs.modal', function () {
        $("#spinnerMainDiv").removeClass("hidden");
        window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/renew-redirect";
    });
}


function showInstantRenewalDuration(type) {
   
    var infoModal = $('#instantRenewal');
    
    
    $('#jqInstantRenewalSubmit').on("click", function () {
    	
    	duration = $('#durationSelectInstant')[0].value;
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/instant/"+type+"?duration="+duration, {
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            cache: false,
            data: {},
            success: function (data) {
                if (!data.valid) {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("text.account.renewlicense.error"));
                    errorModal.modal('show');
                } else {
                    showAndSendRenewalFeedback(data.entityCreatedId);
                }
            }
        });
    });

    
   // infoModal.find('.modal-description').text(getI18nText("special.services.wewillreviewmessage"));
    infoModal.modal('show');
   
}





var removeErrorIfExists = function () {
    if ($(this.closest('.form-group')).hasClass('has-error')) {
        $(this.closest('.form-group')).removeClass('has-error');
    }
    if ($(this).closest('.formSelectBox').find('.help-block').length > 0) {
        $(this).closest('.formSelectBox').find('.help-block').text('');
    }
};

function updateStatus(status) {
    var statusIndicator = $('.statusIndicator');
    statusIndicator.html(getI18nText("renewlicense.status")+'<span>' + status + '</span>');
    statusIndicator.attr('class', 'statusIndicator');

    var in_process_key = getI18nText("service.status.indicator.process");
    var rejected_key = getI18nText("service.status.indicator.reject");
    var accepted_key = getI18nText("service.status.indicator.accept");
    var completed_key = getI18nText("service.status.indicator.complete");

    var statusToLowercase = (status && status.length) ? status.toLowerCase() : null;
    switch (statusToLowercase != null && typeof statusToLowercase !== "undefined") {
        case statusToLowercase.includes(in_process_key):
            statusIndicator.addClass('statusIndicator_inprocess');
            break;
        case statusToLowercase.includes(rejected_key):
            statusIndicator.addClass('statusIndicator_rejected');
            break;
        case statusToLowercase.includes(accepted_key):
            statusIndicator.addClass('statusIndicator_accepted');
            break;
        case statusToLowercase.includes(completed_key):
            statusIndicator.addClass('statusIndicator_completed');
            break;
        default:
            statusIndicator.addClass('statusIndicator_inprocess');
    }
}

function updateExpiryDate(element)  {
	var expDate=$("#expDate").val();
	try { 
		var calendar = $.calendars.instance('gregorian', SAGIA.locale === 'en' ? '' : SAGIA.locale);
		var date = calendar.parseDate('yyyy-mm-dd',expDate );
		var amount = parseInt("353", 10); 
		var yearSelected =  parseInt(element.value, 10);
		var currentDateParsed = calendar.newDate()/*('yyyy-mm-dd',currentDateString );*/
		var date90DaysAfter = currentDateParsed.add(parseInt("90", 10),"d");
		//add one year until until it is more than 90 days from today
		if(date<currentDateParsed){
			do{
				date.add(amount, "d"); 
			}while(date<=date90DaysAfter);
		}
		
		date.add(amount*yearSelected, "d"); 
		date = calendar.formatDate($("#dateUIPattern").val(),date);
		$("#expDateTag").show();
		$("#expDateTag").text(getI18nText("license.apply.licenseYear.expectedExpriyDate")+" "+date);
	}
	catch (e) { 
    	$("#expDateTag").hide();
	        return; 
	} 
}

$(function () {
	var $modal = $('#renewalDisclaimer');
    $modal.modal('toggle');
    
    $('#country').on("change", removeErrorIfExists);
    $('#termsAndConditions').on('change', function (e) {
        if ($(this).is(":checked")) {
            if ($(this).parents('.form-item').find('.help-block').length > 0) {
                $(this).parents('.form-item').find('.help-block').text('');
            }
        }
    });

    if ($('body.page-license-renew-edit, body.page-license-renew').length > 0) {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/check", {
            type: "GET",
            cache: false,
            data: {},
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
            	if(autoRenewal == "true" || autoRenewalClearance == "true"){
            		$('.jqCreateRenewal').hide();
            	}else{
            		$('.jqCreateRenewal').show();
            	}
                $('#renewalButtons').show();
            },
            error: function() {
                $("#renewLicenceId :input").attr("disabled", true);
                $("#draft-buttons :input").attr("disabled", true);
                $('input[type="file"]').css({ 'pointer-events': "none" });

                // variable defined in WEB-INF/views/responsive/pages/license/renewLicense.jsp:23;
                // test for undefined in other jps pages
                if (typeof fromRenewSubmitPage !== 'undefined' && fromRenewSubmitPage) {
                    $('#errorResponseModal').remove();
                }
            }
        });
    }

    $('.jqGovDocsCheck').click(function () {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/checkGovernmentDocuments", {
            type: "GET",
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                if(data && data.autoRenewalEligible) {
                    $('.jqInstantRenewal').show();
                    $('.jqGovDocsCheck').hide();
                    $('.jqCreateRenewal').hide();
                } else {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("licence.renew.instant.notEligible"));
                    errorModal.modal('show');
                }
            }
        });
    });

    $('.jqClearanceCheck').click(function () {
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/checkGovernmentDocuments", {
            type: "GET",
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                if(data && data.autoRenewalForClearanceEligible) {
                    $('.jqInstantClearanceRenewal').show();
                    $('.jqClearanceCheck').hide();
                    $('.jqCreateRenewal').hide();
                } else {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("licence.renew.clearance.notEligible"));
                    errorModal.modal('show');
                }
            }
        });
    });

    $('.jqInstantRenewal').on("click", function () {
    	showInstantRenewalDuration('A');
       
    });

    $('.jqInstantClearanceRenewal').on("click", function () {

    	$.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/instant/R", {    		
    		type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            cache: false,
            data: {},
            success: function (data) {
                if (!data.valid) {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("text.account.renewlicense.error"));
                    errorModal.modal('show');
                } else {
                    showAndSendRenewalFeedback(data.entityCreatedId);
                }
            }
        });
    });

    var renewLicenseId = $('#renewLicenceId');
    renewLicenseId.submit(function (e) {
        e.preventDefault();
        if($('#country')[0].value === 'TF') {
            $('#country').val('').trigger('change');
        }
       

        if ((!validateTermsAndConditions('#termsAndConditions') &&
                validateTermsAndConditions('#termsAndConditions') !== undefined) || !$('#renewLicenceId').valid()) {
            return false;
        }

        var $form = renewLicenseId;
        var fd = new FormData($(this)[0]);

        $.ajax({
            type: 'POST',
            url: $form.attr('action'),
            data: fd,
            cache: false,
            processData: false,
            contentType: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            success: function (data) {
                if (!data.valid) {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("text.account.renewlicense.error"));
                    errorModal.modal('show');
                } else {
                    showAndSendRenewalFeedback(data.entityCreatedId);
                }
            }
        });
    });

    $(".js-load-draft").click(
        function () {
            var id = $(this).data("targetForm");
            var serviceId = $(this).data("serviceId");
            $.ajax({
                type: 'get',
                url: ACC.config.contextPath + '/draft?formId=' + serviceId
            }).done(function (response) {
                var fileCodes = [];
                if(response.parameters !== undefined){
                    response.parameters.forEach(function (parameter) {
                        if (parameter.name === "img" && parameter.type === "hidden") {
                            fileCodes.push(parameter);
                        } else if (parameter.name !== "CSRFToken" && parameter.type !== 'hidden' && parameter.name !== '_termsAndConditionsChecked') {
                            var domElement = $("[name='" + parameter.name + "']");
                            if (domElement.hasClass('realEstate-type')) {
                                domElement.attr("selectedValue", parameter.value);
                                domElement.val(parameter.value).trigger('change', [{files: response.files}])
                            } else {
                                domElement.attr("selectedValue", parameter.value);
                                domElement.val(parameter.value).trigger('change')
                            }
                        }
                    });
                }
                if(response.files !== undefined){
                    response.files.forEach(function (file, index) {
                        var fileInput = $('[name="' + file.attachmentInputName + '"]');
                        var textInput = fileInput.next('input:text'),
                            rootElement = fileInput.closest('.formInputFile');

                        textInput.attr('placeholder', file.name);
                        //var fileIndex = fileInput.data('id');
                        var mockFileInput = fileInput.closest('.formInputFile').find('.js-mock-input');
                        mockFileInput.val(file.id);
                        /* var mockFileInput = '<input name="draftFiles[' + index +  ']" file-id="' + fileIndex +
                                    '" class="form-control js-mock-input" style="display:none;" type="text" value="' + file.id + '">';*/
                        textInput.after(mockFileInput);
                        fileInput.val('');
                        if (fileInput.length > 0) {
                            rootElement.addClass('active');
                        } else {
                            rootElement.removeClass('active');
                        }
                    });
                }

                var fileInput = $('.js-upload-files-list[data-files-name="img"]');
                fileInput.trigger('addFile', [fileCodes, false]);
            })
        }
    );

    renewLicenseId.validate({
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
            } else if (element.hasClass('js-select2-oneColumn')) {
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            street: "required",
            houseNo: "required",
            city: "required",
            additNo: "required",
            country: "required",
            zipCode: "required",
            buildingNo: "required"
        },
        messages: {
            street: {
                required: getI18nText("licence.renew.validation.street")
            },
            houseNo: {
                required: getI18nText("licence.renew.validation.streetNumber")
            },
            city: {
                required: getI18nText("licence.renew.validation.city")
            },
            additNo: {
                required: getI18nText("license.renew.validation.additNo")
            },
            country: {
                required: getI18nText("licence.renew.validation.country")
            },
            zipCode: {
                required: getI18nText("licence.renew.validation.zipcode")
            },
            buildingNo: {
                required: getI18nText("license.renew.validation.buildingNo")
            }
        }
    });

    $('ul#history-list').on('click', 'li', function (e) {
        var srID = $(e.currentTarget).find('.historyList-id').text();
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";

        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/" + srID, {
            type: "GET",
            contentType: "application/json;charset=utf-8",
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            },
            success: function (data) {
                var serviceRequest = JSON.parse(data);
                var address = serviceRequest.address;
                var documents = serviceRequest.attachedDocuments;
                var images = serviceRequest.attachedImages;
                bindObjectAttributes(serviceRequest);
                bindObjectAttributes(address);
                bindObjectAttributes(documents);
                bindObjectAttributes(image);
                $('#srID').html(serviceRequest.srID);
                updateStatus(serviceRequest.statusDescription);

                if(serviceRequest.reApply){
                    $('#resubmitContainer').html("<a href='"+ACC.config.encodedContextPath+"/my-sagia/license/renew/reapply/"+serviceRequest.srID+"' class='btn btn_slim btn_outline'>"+getI18nText("licence.renew.reApply")+"</button>");
                }
                else{
                    $('#resubmitContainer').html("");
                }

                var attachmentsHTML = '';
                for (var document in documents) {
                    if(documents.hasOwnProperty(document)) {
                        var value = documents[document];
                        var attachmentURL = ACC.config.encodedContextPath + "/attachment/pdf/";
                        attachmentsHTML += "<li  style=\"cursor: pointer\" class=\"downloadList-item js-download-service-attachment\">"
                            + " <div class=\"downloadList-description\"> <span class=\"iconElement iconElement_pdf\">"
                            //+ icon 
                            +'<svg id="PDF-Docments" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="59.572" height="78.222" viewBox="0 0 59.572 78.222">'
                            +'<defs>'
                            +'<clipPath id="clip-path">'
                            +'  <rect id="Rectangle_1212" data-name="Rectangle 1212" width="59.572" height="78.222" fill="none" stroke="#00a6be" stroke-width="2"></rect>'
                            +'</clipPath>'
                            +'</defs>'
                            +'<g id="Group_1696" data-name="Group 1696" clip-path="url(#clip-path)">'
                            +' <path id="Path_2326" data-name="Path 2326" d="M59.65,16.758V74.667a3.371,3.371,0,0,1-3.089,3.6H3.589A3.371,3.371,0,0,1,.5,74.667V4.127A3.371,3.371,0,0,1,3.589.531H44.222" transform="translate(-0.296 -0.279)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"></path>'
                            +'<path id="Path_2327" data-name="Path 2327" d="M123.109,16.855,108.186,1.128V17.008Z" transform="translate(-64.06 -0.592)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"></path>'
                            +'<line id="Line_552" data-name="Line 552" x2="59.15" transform="translate(0.204 54.875)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"></line>'
                            +'<path id="Path_2328" data-name="Path 2328" d="M52.169,31.25s-2.985-5.777,0-8.08a3.489,3.489,0,0,1,2.273,4.323s-5.4,23.17-11.333,29.17c0,0-2.747,1.53-3.788-.53a2.97,2.97,0,0,1,1.705-3.273s16.349-9.893,26.52-8.964c0,0,2.155,1.511.282,3.313,0,0-1.977.429-5.363-1.312a6.206,6.206,0,0,1-.621-.313C60.991,45.019,58.532,43.632,52.169,31.25Z" transform="translate(-23.258 -12.171)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"></path>'
                            +'<path id="Path_2329" data-name="Path 2329" d="M33.02,124.42a20.365,20.365,0,0,1,3.537-.289,5.675,5.675,0,0,1,4,1.246,4.129,4.129,0,0,1,1.25,3.137,4.545,4.545,0,0,1-1.1,3.2,5.752,5.752,0,0,1-4.363,1.646,6.033,6.033,0,0,1-1.483-.133v6.006H33.02Zm1.843,7.229a5.9,5.9,0,0,0,1.525.156c2.224,0,3.579-1.135,3.579-3.2,0-1.98-1.334-2.936-3.367-2.936a7.368,7.368,0,0,0-1.737.155Z" transform="translate(-19.552 -65.204)" fill="#00a6be"></path>'
                            +'<path id="Path_2330" data-name="Path 2330" d="M60.643,124.442a25.216,25.216,0,0,1,3.918-.311c2.647,0,4.532.645,5.782,1.868a7.087,7.087,0,0,1,2.012,5.383,8.159,8.159,0,0,1-2.054,5.828c-1.335,1.4-3.537,2.158-6.311,2.158a28.366,28.366,0,0,1-3.347-.178ZM62.486,137.7a11.111,11.111,0,0,0,1.864.111c3.94,0,6.078-2.313,6.078-6.362.021-3.537-1.885-5.783-5.782-5.783a9.994,9.994,0,0,0-2.16.2Z" transform="translate(-35.909 -65.204)" fill="#00a6be"></path>'
                            +'<path id="Path_2331" data-name="Path 2331" d="M95.224,124.365h7.688v1.624H97.067v4.983h5.4v1.6h-5.4v6.785H95.224Z" transform="translate(-56.385 -65.327)" fill="#00a6be"></path>'
                            +'</g>'
                            +'</svg>'
                            + "</span>" + value.fileName + " </div> <div class=\"downloadList-actions\"> <a href='"
                            + attachmentURL + value.objectId + "/" + value.documentID
                            + "' class=\"link link_nowrap\" download='" + value.fullFileName + "'" + "> <span class=\"iconElement iconElement_cloud\">"
                            +'<svg xmlns="http://www.w3.org/2000/svg" width="23.265" height="24" viewBox="0 0 23.265 24">'
                            +'<g id="Download" transform="translate(-465.44 -1494.127)">'
                            +' <path id="Path_1894" data-name="Path 1894" d="M487.935,1509.231a.769.769,0,0,0-.77.77v10.064H466.98V1510a.77.77,0,0,0-1.54,0v11.6H488.7V1510A.769.769,0,0,0,487.935,1509.231Z" transform="translate(0 -3.477)" fill="#00a6be"></path>'
                            +' <path id="Path_1895" data-name="Path 1895" d="M473.908,1504.419l4.042,4.043V1494.9a.77.77,0,0,1,1.54,0v13.562l4.039-4.04a.77.77,0,1,1,1.088,1.089l-5.9,5.9-5.9-5.9a.77.77,0,0,1,1.089-1.089Z" transform="translate(-1.647 0)" fill="#00a6be"></path>'
                            +'</g>'
                            +'</svg>'
                            +'</span>Download </a> </div> </li> ';
                    }
                }
                $('#documents-container').html(attachmentsHTML);

                var imagesHTML = '';
                for (var attribute in images) {
                    if(images.hasOwnProperty(attribute)) {
                        var image = images[attribute];
                        imagesHTML += "<li> <img id=\"image\" src=\" " +
                            ACC.config.encodedContextPath + "/my-sagia/license/image/" + image.objectID + "/" + image.documentID + "/" +
                            "\"/>  </li> ";
                    }
                }
                $('#images-container').html(imagesHTML);
                $('.messageList').html('');
                var hasComments = false;
                for (var commentIdx in serviceRequest.comments) {
                    if(serviceRequest.comments.hasOwnProperty(commentIdx)) {
                        var comment = serviceRequest.comments[commentIdx];
                        if (comment.date != null) {
                            var $messageTemplate = $('.messageTemplateWrapper').clone();
                            $messageTemplate.find('.messageList-name').html(comment.commentBy);
                            var date = new Date(comment.date.date.year + '-' + comment.date.date.month + '-' + comment.date.date.day);
                            var locale = "en";
                            $messageTemplate.find('.messageList-date').html(date.getDay() + ' ' + date.toLocaleString(locale, {month: "short"}) + ', ' + date.getFullYear());
                            $messageTemplate.find('.messageList-message').html(comment.comments);
                            $('.messageList').append($messageTemplate.html());
                            hasComments = true;
                        }
                    }
                }
                if (hasComments) {
                    $('.comments-wrapper').show();
                } else {
                    $('.comments-wrapper').hide();
                }
            }
        });
    });
});
