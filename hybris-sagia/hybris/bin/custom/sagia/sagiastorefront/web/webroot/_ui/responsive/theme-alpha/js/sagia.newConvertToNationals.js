    $('#formNewConvertToNationals').submit(function (e) {
        e.preventDefault();

        var fields = $("form#formNewConvertToNationals input[type=file][data-mandatory]");
        var requiredFieldsCount = fields.length;
        var filledFields = fields.filter(function() {
            if ($(this).val().length > 0) {
                return true;
            }

            var rootElement = $(this).closest('.formInputFile');
            if (rootElement.find('.js-mock-input').length > 0) {
                return true;
            }
        });
        if (filledFields.length < requiredFieldsCount) {
            $('#errorResponseModal').find('.modal-description').text(getI18nText("general.pleaseuploadrequiredfiles"));
            $('#errorResponseModal').modal('show');
            e.preventDefault();
            return false;
        }
        
        //it is false if the terms were not accepted
        var validateResponse = validateTermsAndConditions('.js-terms-agree');
        if(typeof validateResponse === 'boolean' && validateResponse === false) {
        		return false;
        }

        var $form = $("#formNewConvertToNationals");
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
            success : function(response) {
            		showAndSendFeedback();
            },
            error : function(xhr, status, error) {
                var errorMessage;
                if(xhr.responseJSON.sagiaExceptionResponse != undefined){
                    errorMessage = xhr.responseJSON.sagiaExceptionResponse.message;
                }
                else{
                    errorMessage = getI18nText("convertlicense.error");
                }

                if(errorMessage != undefined){
                    $('#errorResponseModal').find('.modal-description').text(errorMessage);
                    $('#errorResponseModal').modal('show');
                }

            }
        });
    });


    $(document).on('resetFormInput', function() {
        validateFilesCount();
    });

    $('#formNewConvertToNationals').change(function() {
    	if(isInstant){
    		$('#convertSubmitButton').removeAttr("disabled");
    	}else{
    		validateFilesCount();
    	}
    });

    function validateFilesCount() {
        var fields = $("form#formNewConvertToNationals input[type=file][data-mandatory]");
        var filledFields = fields.filter(function() {
            if ($(this).val().length > 0) {
                return true;
            }

            var rootElement = $(this).closest('.formInputFile');
            if (rootElement.find('.js-mock-input').length > 0) {
                return true;
            }
        });
        if ((filledFields.length == 3)) {
            $('#convertSubmitButton').removeAttr("disabled");
        } else {
            $('#convertSubmitButton').attr("disabled", "disabled");
        }
    }

    function showAndSendFeedback() {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + "/convertToNationals/latest",
            success: function (data) {
                $('#serviceId').val(data.srID);
            }
        });
        $('#requestSubmittedComment').modal();
        $('#requestSubmittedComment').on('hide.bs.modal', function () {
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
    
    $('#cancelNewConvertToNationalsButton').click(function() {
    		 window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/convert";
    });
