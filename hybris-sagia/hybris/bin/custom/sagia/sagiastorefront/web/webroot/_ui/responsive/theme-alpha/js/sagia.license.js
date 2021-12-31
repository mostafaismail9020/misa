$(document).ready(function () {
    $(document).on("click", "#reviewTabId", function (e) {
        fillReviewTab();
    });

    $(document).on("click", "#nextFirstTabButtonId, #editDocumentsButtonId", function () {
        $('a[href="#accessibletabscontent0-1"]').click();
    });

    $(document).on("click", "#nextSecondTabButtonId", function () {
        $('a[href="#accessibletabscontent0-2"]').click();
    });

    $(document).on("click", "#editEntityButtonId", function () {
        $('a[href="#accessibletabscontent0-0"]').click();
    });

    $('#governmentEntityId').on("change",removeErrorIfExists);

    $('#countryId').on("change", removeErrorIfExists);

    $('#filesValidationId').hide();

    $('#temporaryBiddingLicenseFormId').ajaxForm({
        beforeSubmit:  beforeSubmit,
        success: showResponse
    });

    $('#countryId').on('change', function(){
        var countryCode = this.value.substr(0, 2);
        if(countryCode) {
            $.ajax(ACC.config.encodedContextPath + controllerUrl + "/bidding-code/" + countryCode, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                success: function (countryPrefix) {
                    if (countryPrefix.length === 0) {
                        $('#noCountryPrefixId').trigger('click');
                    } else {
                        var prefix = countryPrefix;
                        $('#countryPrefixMobileId').val(prefix).trigger("blur").trigger('change');
                        $('#countryPrefixTelephoneId').val(prefix).trigger("blur").trigger('change');
                    }
                }
            });
        }
    });

    $('#temporaryBiddingLicenseFormId').validate({
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
            if(element.closest('.formInputBox_big').length>0){
                error.appendTo(element.closest('.formInputBox_big').find('.help-block')[1]);
            } else if (element.closest('.formInputFile_group').length>0){
                error.appendTo(element.closest('.formInputFile_group').find('.help-block'));
            } else if(element.closest('.form-group').find('.help-block').length>0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn')) {
                error.appendTo(element.closest('.col-md-6').find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            companyNameInEnglish : "required",
            projectNameInArabic : "required",
            projectNameInEnglish : "required",
            capital : "required",
            governmentEntity : "required",
            country : "required",
            city : "required",
            postalCode : "required",
            poBox : "required",
            email : "required",
            mobile : "required",
            telephone : "required",
            termsAndConditionsChecked :"required"

        },
        messages: {
            companyNameInArabic: {
                required: getI18nText("validation.company.name.arabic")
            },
            companyNameInEnglish: {
                required: getI18nText("validation.company.name.english")
            },
            projectNameInArabic: {
                required: getI18nText("validation.project.name.arabic")
            },
            projectNameInEnglish: {
                required: getI18nText("validation.project.name.english")
            },
            capital: {
                required: getI18nText("validation.capital")
            },
            governmentEntity: {
                required: getI18nText("validation.government.entity")
            },
            country: {
                required: getI18nText("validation.country")
            },
            city: {
                required: getI18nText("validation.city")
            },
            postalCode: {
                required: getI18nText("validation.postalcode")
            },
            poBox: {
                required: getI18nText("validation.pobox")
            },
            email: {
                required: getI18nText("validation.email")
            },
            mobile: {
                required: getI18nText("validation.mobileNumber")
            },
            telephone: {
                required: getI18nText("validation.telephoneNumber")
            },
            termsAndConditionsChecked : {
                required: getI18nText("terms.accept.text")
            }
        },
        invalidHandler: function(event, validator) {
            var redirectToDocuments = false;
            for (var key in validator.errorMap) {
                if(validator.errorMap.hasOwnProperty(key) && key.indexOf("temporaryBiddingLicenseMultipartFiles") > -1) {
                    redirectToDocuments = true;
                }
            }

            if(redirectToDocuments) {
                $('a[href="#accessibletabscontent0-1"]').trigger('click');
            } else {
                $('a[href="#accessibletabscontent0-0"]').trigger('click');
            }
        }
    });

    $.validator.addMethod('draftFile', function (value, element) {
        var $element = $(element);
        if (value) return true;

        var mockInput = $element.closest('.formInputFile').find('.js-mock-input');
        return !!mockInput.val();
    }, 'Please upload the indicated document');

    $('input[name^="temporaryBiddingLicenseMultipartFiles"]').each(function () {
        if (!($(this).attr('name').indexOf('2') > -1) && !($(this).attr('name').indexOf('3') > -1) && !($(this).attr('name').indexOf('7')> -1) ) {
            $(this).rules('add', {
                draftFile: true,
                messages: {
                    required: getI18nText("validation.indicated.document")
                }
            })
        }
    });

    $('.js-load-draft').on('click', function() {
        var id = $(this).data("targetForm");
        var serviceId = $(this).data("serviceId");
        $.ajax({
            type: 'get',
            url: ACC.config.encodedContextPath + '/draft?formId=' + serviceId
        }).done(function (response) {
            if (response && response.parameters) {
                response.parameters.forEach(
                    function(parameter) {
                        if (parameter.name !== "CSRFToken") {
                            var domElement = $("[name='" + parameter.name + "']");
                            domElement.attr("selectedValue", parameter.value);
                            domElement.val(parameter.value).trigger("blur").trigger('change');
                        }
                    });
            }

            if (response && response.files) {
                response.files.forEach(
                    function(file, index) {
                        var fileInput = $('[name="' + file.attachmentInputName + '"]');
                        var textInput = fileInput.next('input:text'),
                            rootElement = fileInput.closest('.formInputFile');
                            textInput.attr('placeholder', file.name);
                            var fileIndex = fileInput.data('id');
                            var mockFileInput = '<input id="fileData1" name="draftFileData[' + fileIndex +  ']" file-id="' + fileIndex +'" file-name="' + file.name + '" class="form-control js-mock-input" type="hidden" value="' + file.id + '">';
                            textInput.after(mockFileInput);
                            if (fileInput.length > 0) {
                                rootElement.addClass('active');
                            } else {
                                rootElement.removeClass('active');
                            }
                            fileInput.val('');
                        });
                $("#" + id).trigger('change');
            }
            fillReviewTab();
        });
    })
});

function beforeSubmit(arr, $form, options){
    $('#temporaryBiddingLicenseFormId').valid();

    var removeFromIndex = [];
    $.each(arr, function(index, value) {
        if (value.type === "file" && !value.value){
            removeFromIndex.push(index);
        }
    });

    for (var i = removeFromIndex.length -1; i >= 0; i--) {
        arr.splice(removeFromIndex[i],1);
    }
}

// post-submit callback
function showResponse(responseText, statusText, xhr, $form)  {
    if (responseText.valid) {
        showAndSendFeedback(responseText.entityCreatedId);
        //$('#requestSubmittedId').click();
    } else {
        // remove errors from previous call
        $('div[class*="has-error"]').removeClass("has-error");
        $('div[class="help-block"]').remove();
        $('div[class="formError"]').not('#filesValidationId').remove();
        $('#filesValidationId').hide();

        // add new errors
        var firstTabValid = true;
        var errors = responseText.errors;
        for (var property in errors) {
            if (errors.hasOwnProperty(property)) {
                if (property.indexOf('temporaryBiddingLicenseMultipartFiles') !== -1) {
                    $('#filesValidationId').show();
                } else if((property.indexOf('termsAndConditionsChecked') !== -1)){
                    validateTermsAndConditions("#termsAndConditions");
                } else {
                    firstTabValid = false;
                    $('[name='+ property + ']').parent().addClass("has-error")
                        .append("<div class=\"help-block\"><span>" + errors[property] + "</span></div>");
                }
            }
        }

        if (!firstTabValid) {
            $('a[href="#accessibletabscontent0-0"]').click();
        } else {
            $('a[href="#accessibletabscontent0-1"]').click();
        }
        window.scrollTo(0 ,0);
    }
}

function showAndSendFeedback(serviceId) {
    var ratingModal = $('#requestSubmittedComment');
    ratingModal.find('.modal-description').text(getI18nText("submit.request.message")+" "+getI18nText("submit.request.number") +" "+ serviceId);
    ratingModal.modal('show');
    ratingModal.on('hide.bs.modal', function() {
        window.location.href = ACC.config.encodedContextPath + '/dashboard';
    });

}

function fillReviewTab() {
    $("dd[id*='ReviewId']").each(function () {
        var elementReviewId = this.id;
        var elementId = elementReviewId.replace('Review', '');
        var input = $('#' + elementId);
        if (input) {
            var reviewValue = '-';
            var inputType = input.attr('type');
            if (inputType === 'file') {
                if (input.val()) {
                    reviewValue = input.val().split('\\').pop();
                }
                var mockInput = input.closest('.formInputFile').find('.js-mock-input');
                if (mockInput && mockInput.attr('file-name')) {
                    reviewValue = mockInput.attr('file-name');
                }
            } else if (inputType === 'text' && input.val()) {
                reviewValue = input.val();
            } else if (input.is('select') && input.val()) {
                reviewValue = input.children("option").filter(":selected").text();
            }
            $(this).text(reviewValue);
        }
    })
}

var removeErrorIfExists = function(){
    if ($(this.closest('.form-group')).hasClass('has-error')) {
        $(this.closest('.form-group')).removeClass('has-error');
    }
    if ($(this).nextAll('.help-block:first').length > 0) {
        $(this).nextAll('.help-block').remove();
    }
};
