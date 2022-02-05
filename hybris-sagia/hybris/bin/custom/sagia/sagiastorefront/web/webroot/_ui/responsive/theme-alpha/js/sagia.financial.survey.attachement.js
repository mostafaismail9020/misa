$(function () {
    var $modal = $('#enquiryDetail');
    var $modalBody = $modal.find('.modal-dialog');

    if(Boolean($('#requestFeedback').val())){

        var requestSubmittedCommentModal = $('#requestSubmittedComment');
        requestSubmittedCommentModal.find('.modal-secondaryContent').hide();
        requestSubmittedCommentModal.modal().open;

    } else if ($('#financialStatementErrorMessage').val()) {
        var errorModal = $('#errorResponseModal');
        errorModal.find('.modal-description').text($('#financialStatementErrorMessage').val());
        errorModal.modal('show');
    }

    $(document).on("change", ".js-inputFile", function(e) {
    	var fileInput = $(this),
            textInput = $(this).next('input:text'),
            fileName = $(this).val().replace(/\\/g, '/').replace(/.*\//, ''),
            rootElement = $(this).closest('.formInputFile');
    	var fileSize = e.target.files[0].size;
		 textInput.attr('placeholder', fileName);
		 $(this).parent().removeClass("has-error");
		 rootElement.find(".help-block").hide();
			if (fileInput.length > 0 && fileSize > 0) {
				rootElement.addClass('active');
				if ($('#termsAndConditions').is(":checked")){
					$('.js-submit-financialStatement').attr('disabled', false);
				}else{
	        		$('.js-submit-financialStatement').attr('disabled', true);
	        	}
			} else {
				$('.js-submit-financialStatement').attr('disabled', true);
				rootElement.removeClass('active');
			}
    });

    $modal.on('shown.bs.modal', function (e) {
        var $financialStatementId = $(e.relatedTarget).data('financialStatementId');
        $modalBody.load(ACC.config.encodedContextPath +"/financial-statement/" + $financialStatementId);
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


    $('.js-financialStatement-create').validate({
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
            termsAndConditionsChecked : "required"

        },
        messages: {
            termsAndConditionsChecked: {
                required: getI18nText("legal.consulation.validation.termsAndConditions")
            }
        }

    });


    $('.js-financialStatement-create').on("submit", function () {
        if($(this).valid()) {
            $('.js-submit-financialStatement').attr('disabled', true);
            return true;
        } else {
            return false;
        }
    });

    $('#termsAndConditions').on('change', function (e) {
        if ($(this).is(":checked")) {
        	/*if($('#file0').val() != 0){
        		$('.js-submit-financialStatement').attr('disabled', false);
        	}else{
        		$('.js-submit-financialStatement').attr('disabled', true);
        	}*/
            $('.js-submit-financialStatement').attr('disabled', false);
            if ($(this).parents('.form-item').find('#termsAndConditionsChecked-error').length > 0)
                $(this).parents('.form-item').find('#termsAndConditionsChecked-error').remove();
        }else{
    		$('.js-submit-financialStatement').attr('disabled', true);
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
                response.parameters.forEach(
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
