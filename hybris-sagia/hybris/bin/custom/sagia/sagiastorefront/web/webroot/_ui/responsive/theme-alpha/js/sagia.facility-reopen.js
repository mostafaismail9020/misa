$(function(){
    if(typeof createRequestEnabled != "undefined" && !createRequestEnabled){
        $('#reopenFacilityWarning').modal('show');
    }

    $('.historyList-link').on('click', function(){
        loadServiceRequest($(this).data('srid'))
    });

    var investorCrValidationParams = {
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: true,
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
            termsAgree: {
                required: true
            }
        },
        messages: {
            termsAgree: {
                required: getI18nText("acceptTerms.text")
            }
        }
    };

    $(".js-load-draft").click(function () {
        var id = $(this).data("targetForm");
        var serviceId = $(this).data("serviceId");
        $.ajax({
            type: 'get',
            url: ACC.config.contextPath + '/draft?formId=' + serviceId
        }).done(function (response) {
            var fileCodes = [];

            response.parameters.forEach(function(parameter) {
                if (parameter.name === "attachmentIds" && parameter.type === "hidden") {
                    fileCodes.push(parameter);
                } else if (parameter.name !== "CSRFToken" && parameter.type !== 'hidden' && parameter.name !== '_termsAndConditionsChecked') {
                    var domElement = $("[name='" + parameter.name + "']");

                    domElement.attr("selectedValue", parameter.value);
                    domElement.val(parameter.value).trigger('change')
                }
            });

            var fileInput = $('.js-upload-files-list[data-files-name="attachmentIds"]');
            fileInput.trigger('addFile', [fileCodes, false]);

        })
    });

    //$('#reopenFacility').validate(investorCrValidationParams);

    $('#facilityReopen').on("submit", function () {
        $('#facilityReopen button[type="submit"]').attr('disabled', true);
    });

    $('#reopenFacility').ajaxForm({
        beforeSubmit: function(arr, $form, options){
            /*var valid = $form.valid();
            if (!valid) {
                return false;
            }*/
            options.headers = {
                'Accept': 'application/json',
                'CSRFToken': ACC.config.CSRFToken
            };
        },
        headers: {
            Accept: "application/json"
        },
        success: function(data){
            // window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/renew/";
        }
    });
});

function loadServiceRequest(srId) {
    $.ajax(ACC.config.encodedContextPath + "/facility-reopen/get/" + srId,{
        type: "GET",
        responseType: "application/json;charset=utf-8",
        contentType: "application/json;charset=utf-8",
        cache: false,
        success: function(data){
            var jsonData = JSON.parse(data);
            $('.jqSrId').html(jsonData.srId);
            $('.jqText').html(jsonData.text);
            // bindObjectAttributes(jsondata.address);
            if(jsonData.attachments.length > 0 && jsonData.attachments[0].objectId) {
                // ACC.config.encodedContextPath
                $('.jqDownloadList').empty();
                $('.jqDownloadList').append('<ul class="downloadList" id="documents-container"></ul>');
                $.each(jsonData.attachments, function(index, item){
                    var $template = $('.documentDownloadTemplate').clone();
                    $template.find('.downloadList-description').append(item.fileName);
                    $template.find('#downloadFileAnchor').attr('href', ACC.config.encodedContextPath + '/attachment/download/' + item.objectId + '/' + item.fileGuid + '/' + item.fileName);
                    $template.find('#downloadFileAnchor').attr('download', item.fullFileName);
                    $('.downloadList').append($template.html());
                });
            } else {
                $('.jqDownloadList').empty();
                var $template = $('.documentDownloadEmptyTemplate').clone();
                $('.jqDownloadList').append($template.html());
            }
        }
    });
}

function bindObjectAttributes(object) {
    for (var attribute in object) {
        var value = object[attribute];
        var span = $("[name='" + attribute + "']");
        if (span == undefined) {
            continue;
        } else if (value != undefined && value != null) {
            span.text(value);
        } else {
            span.text('-');
        }
    }
}

function renderErrors(errors) {}
