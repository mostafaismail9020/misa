$(function () {
    var $modal = $('#enquiryDetail');
    var $modalBody = $modal.find('.modal-dialog');

    $modal.on('shown.bs.modal', function (e) {
        var $legalConsultationId = $(e.relatedTarget).data('legalconsultationId');
        $modalBody.load(ACC.config.encodedContextPath +"/legalconsultations/" + $legalConsultationId);
    });

    $modal.on('hidden.bs.modal', function () {
        $modalBody.html("");
    });

    $(".js-attach-file-download").click(function () {
        var element = $(this);
        var objectId = element.data('objectId');
        var documentId = element.data('documentId');
        window.open(ACC.config.encodedContextPath + "/my-sagia/attachment/" + objectId + "/" + documentId, '_blank');
    });

    $('#attachmentList').on('click', 'div', function(e) {
        var objectId = $(this).data('objectId');
        var documentId = $(this).data('documentId');
        window.open(ACC.config.encodedContextPath + "/attachment/pdf/" + objectId + "/" +  documentId, '_blank');
    });

    $("#legalInquiryTypeId").change(function () {
        $("#typeDescriptionId").val($(this).find('option:selected').text());


        if ($("#legalInquiryTypeId").closest('.form-group').hasClass('has-error')) {
            $("#legalInquiryTypeId").closest('.form-group').removeClass('has-error');
        }

        if ($("#legalInquiryTypeId").nextAll('.help-block:first').length>0) {
            $("#legalInquiryTypeId").nextAll('.help-block:first').remove();

         }



    });



    $('.js-legalconsultation-create').validate({
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: false,
        ignore: "",
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.hasClass('js-select2-oneColumn')) {
                error.insertAfter(element.next());
            } else if (element.hasClass('form-control form-control_slim')){
                error.insertAfter($(element).closest('.form-group'));
            } else {
                error.insertAfter(element);
            }
        },
        rules: {
            legEnqTitle: "required",
            textMsg : "required",
            termsAndConditionsChecked : "required"

        },
        messages: {
            legEnqTitle: {
                required: getI18nText("legal.consulation.validation.legEnqTitle")
            },

            textMsg: {
                required: getI18nText("legal.consulation.validation.textMsg")
            },

            termsAndConditionsChecked: {
                required: getI18nText("legal.consulation.validation.termsAndConditions")
            }
        }

    });


    $('.js-legalconsultation-create').on("submit", function () {
        if($(this).valid()) {
            $('.js-submit-legalconsultation').attr('disabled', true);
            return true;
        } else {
            return false;
        }
    });

    $('#termsAndConditions').on('change', function (e) {
        if ($(this).is(":checked")) {
            if ($(this).parents('.form-item').find('#termsAndConditionsChecked-error').length > 0)
                $(this).parents('.form-item').find('#termsAndConditionsChecked-error').remove();
        }
    });

    $(".js-load-draft").click(function () {
        var id = $(this).data("targetForm");
        $.ajax(
            {
                type: 'get',
                url: ACC.config.contextPath + '/draft?formId=' + id
            }
        )
            .done(function (response) {
                response.parameters !== undefined && response.parameters.forEach(
                    function(parameter) {
                        if (parameter.name === "docs" && parameter.type === "hidden") {
                            fileCodes.push(parameter);
                        }
                        else if (parameter.name !== "CSRFToken" && parameter.type !== 'hidden' && parameter.name !== '_termsAndConditionsChecked') {
                            var domElement = $("[name='" + parameter.name + "']");

                            domElement.attr("selectedValue", parameter.value);
                            domElement.val(parameter.value).trigger('change')
                        }
                    }
                );

                if (response && response.files) {
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
            })

    });

});
