$(document).ready(function () {
	
	if(cancellationMessage.length > 0){
		var $modal = $('#blockCancellation');
		$("#blockCancellation").modal({backdrop: "static"});
	    $modal.modal('toggle');
	}

    $('.validate__numbers-only').on('keypress', function (event) {
        var regex = new RegExp("^[0-9]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            event.preventDefault();
            return false;
        }
    });
    
    $('.validate__numbers-only').on('paste', function () {
        var $self = $(this)
        setTimeout(function () {
            $self.val($self.val().replace(/\D+/g, ""));
        }, 0);
    });
	
	$('#formCancelLicenseStage1').validate({
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
            } else if (element.hasClass('js-select2-oneColumn')){
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));

            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            crNo: "required",
            zakathNo : "required",
            termsAndConditionsChecked :"required"
        },
        messages: {
        	crNo: {
                required: getI18nText("validation.licenseCancel.entercrno")
            },
            zakathNo : {
                required: getI18nText("validation.licenseCancel.enterzakathno")
            },
            termsAndConditionsChecked : {
                required: getI18nText("validation.licenseCancel.termscondition")
            }
        }
	});
	
    
    var check_validations_stage1a = function(event) {
        var check_lc_a1 = document.getElementById('lc_a1');
        if (check_lc_a1.checked == false) {
            $('#errorResponseModal').find('.modal-description').text(getI18nText("licenseCancellation.printLetter"));
            $('#errorResponseModal').modal('show');
            event.preventDefault();
            return;
        }
    };
    var form_stage1a = document.getElementById("formCancelLicenseStage1A");

    var check_validations_stage2 = function(event) {
        var check_lc_a2 = document.getElementById('lc_a2');
        if (check_lc_a2.checked == false) {
            $('#errorResponseModal').find('.modal-description').text(getI18nText("license.cancellation.fillMinistry"));
            $('#errorResponseModal').modal('show');
            event.preventDefault();
            return;
        }
    };

    var form_stage2 = document.getElementById("formCancelLicenseStage2");
    var form_stage1 = document.getElementById("formCancelLicenseStage1");
    var form_stage4 = document.getElementById("formCancelLicenseStage4");

    // attach event listener

    if(form_stage1a != null){
        form_stage1a.addEventListener("submit", check_validations_stage1a, true);
    }

    if(form_stage2 != null){
        form_stage2.addEventListener("submit", check_validations_stage2, true);
    }

    $('#attachmentList')
        .on(
            'click',
            'div[data-view-attachment-target]',
            function (e) {
                var objectId = $(this).data('objectId');
                var documentId = $(this).data('documentId');
                window.open(ACC.config.encodedContextPath + "/attachment/pdf/" +
                    objectId + "/" + documentId,
                    '_blank');
            })

$('#formCancelLicenseStage4').submit(function (e) {
    e.preventDefault();
    var $form = $("#formCancelLicenseStage4");
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
        error : function(xhr, status, error) {
            var errorMessage;
            var jsonResponse;
            if (xhr.responseJSON.indexOf('{') >= 0) {
                jsonResponse = xhr.responseJSON.substring(xhr.responseJSON.indexOf('{'));
            }
            if (status === "error") {
                var jsonErrorResponse = JSON.parse(jsonResponse);
                if (jsonErrorResponse != null && jsonErrorResponse.error != null && jsonErrorResponse.error.message != null && jsonErrorResponse.error.message.value != null) {
                    errorMessage = jsonErrorResponse.error.message.value;
                }
            }
                if(hasTermsAndConditionsError(jsonResponse)){
                    validateTermsAndConditions('#termsAndConditions3');
                }
                else if(xhr.responseJSON.sagiaExceptionResponse != undefined){
                    errorMessage = xhr.responseJSON.sagiaExceptionResponse.message;
                }
                else{
                    errorMessage = getI18nText("license.cancellation.error");
                }

            if(errorMessage != undefined){
                $('#errorResponseModal').find('.modal-description').text(errorMessage);
                $('#errorResponseModal').modal('show');
            }
        }
    });
});

function showAndSendFeedback(serviceId) {
	$('#serviceId').val(serviceId);
	$('#requestSubmittedComment').modal();
	$('#requestSubmittedComment').on('hide.bs.modal', function() {
		window.location.href = ACC.config.encodedContextPath + '/dashboard';
	});
}

    $(".js-load-draft").click(
        function () {
            var id = $(this).data("targetForm");
            $.ajax({
                type: 'get',
                url: ACC.config.encodedContextPath + '/draft?formId=' + id
            }).done(function (response) {
                if (response && response.files) {
                    response.files.forEach(function(file, index) {
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
                    });

                    $("#" + id).trigger('change');
                }
            })
        });

    $(".btn-back").click(function () {
        window.location.href = ACC.config.encodedContextPath + '/dashboard';
    });
});
