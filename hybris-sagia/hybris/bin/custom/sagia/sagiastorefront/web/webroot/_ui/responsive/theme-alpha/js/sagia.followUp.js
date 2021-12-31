$(function () {

    initDownloadAttachments();
    initializeCreateEvent();

    $(".js-show-letters-details, .js-show-replies-details").click(
        function (event) {

            event.preventDefault();

            var $element = $(this);
            if (!$element.hasClass("js-item-current")) {
                $(".js-item-current").each(function () {
                    $(this).removeClass("js-item-current");
                    $(this).parents(".historyList-item_current").removeClass("historyList-item_current");
                });
                $element.addClass("js-item-current");
                $element.parent().addClass("historyList-item_current");
            }

            var dataId = $element.data("id");
            var type = $element.data("type");
            $.get({
                url: ACC.config.encodedContextPath + "/" + type + "/" + dataId
            })
                .done(function (response) {
                    var $details = $(".js-" + type);
                    $details.empty();
                    var $newElement = $(response);
                    $details.append($newElement);
                    $(".js-hidden-element").fadeIn('500');

                    initDownloadAttachments();
                    initializeCreateEvent();
                })
                .fail(function (error) {
                });


        }
    );

    $(".js-supportVisit-select-date").click(
        function (event) {
            var now = new Date();
            var yesterday = new Date(now.setDate(now.getDate() - 1));
            var locale = "en";

            if ($('.js-supportVisit-select-date#dateString').length > 0) {
                $('.js-supportVisit-select-date#dateString').get(0)._flatpickr.set("dateFormat", 'd M, Y');
                $('.js-supportVisit-select-date#dateString').get(0)._flatpickr.set("disable", [{
                    from: "01 Jan, 1970",
                    to: ('0' + yesterday.getDate()).slice(-2) + " " + yesterday.toLocaleString(locale, {month: "short"}) + ", " + yesterday.getFullYear(),
                }, function (date) {
                    // return true to disable
                    return (date.getDay() === 5 || date.getDay() === 6);
                }]);
            }
        }
    );

    $(".js-load-draft").click(function () {
        var id = $(this).data("targetForm");
        var serviceId = $(this).data("serviceId");
        $.ajax(
            {
                type: 'get',
                url: ACC.config.contextPath + '/draft?formId=' + serviceId
            }
        ).done(function (response) {
            var fileCodes = [];

            response.parameters.forEach(
                function(parameter) {
                    if (parameter.name === "docs" && parameter.type === "hidden") {
                        fileCodes.push(parameter);
                    } else if (parameter.name !== "CSRFToken" && parameter.type !== 'hidden' && parameter.name !== '_termsAndConditionsChecked') {
                        var domElement = $("[name='" + parameter.name + "']");

                        if (parameter.type === 'checkbox') {
                            domElement.prop( "checked", true );
                            domElement.trigger('change')
                        } else if (parameter.type === 'radio') {
                            domElement.each(function(index) {
                                var $element = $(this);
                                if ($element.val() === parameter.value) {
                                    domElement.prop( "checked", true );
                                }
                            });
                        } else if (parameter.type === 'range') {
                            domElement.val(parameter.value);
                            domElement.trigger('change');
                            var moveDirection;
                            if($("html").attr("dir") === "ltr") {
                                moveDirection = 'right';
                            } else {
                                moveDirection = 'left';
                            }

                            domElement.css('background', 'linear-gradient(to '+moveDirection+', #5cc83b 0%, #5cc83b ' + parameter.value + '%, #e6e6ed ' + parameter.value + '%, #e6e6ed 100%)');
                            domElement.closest('.js-formRangeSlider').find('.formRangeSlider-value span').text(parameter.value);
                        } else {
                            domElement.attr("selectedValue", parameter.value);
                            domElement.val(parameter.value).trigger('change')
                        }
                    }
                });
            var fileInput = $('.js-upload-files-list[data-files-name="docs"]');
            fileInput.trigger('addFile', [fileCodes, false]);
        })
    });

    $('.js-vr-number').change(function(){
        var $radio = $(this);
        var letterNumber = $radio.val();

        $.get("create/" + letterNumber).done(function(response) {
            if (response) {
                $(".js-violations-list").empty().html(response);
            }
        });
    });

    $('.js-wl-number').change(function(){
        var $radio = $(this);
        var letterNumber = $radio.val();
        $.get("create/" + letterNumber).done(function(response) {
            if (response) {
                var $violationsList = $(".js-warning-letter-extension-time");
                $violationsList.empty();
                $violationsList.html(response);

                SAGIA.formRangeSlider.init();
                initDaysRange();
            }
        });
    });

    function initializeCreateEvent() {
        $(".js-create-warning-letter, .js-create-violation-reply").click(function (event) {
            event.preventDefault();

            var url = window.location.pathname;
            url = url.replace(/(\/)$/,"");

            $.get(url + "/check").done(function (response) {
                if (response.status) {
                    var $uploadModal = $("#failInformationModal");
                    $uploadModal.find(".js-message").text(response.message);
                    $uploadModal.modal('show');
                } else {
                    var url = window.location.pathname;
                    url = url.replace(/(\/)$/,"");
                    window.location = url + "/create";
                }
            }).fail(function () {
                var $uploadModal = $("#failInformationModal");
                $uploadModal.find(".js-message").text(getI18nText("text.account.followup.error"));
                $uploadModal.modal('show');
            })
        });
    }

    // $('.js-terms-agree').change(function() {
    //     var element = $(this);
    //     if(!element.is(":checked")) {
    //         element.closest(".form-group").addClass("has-error");
    //     }
    //     else {
    //         element.closest(".form-group").removeClass("has-error");
    //     }
    // });

    $('.js-form-element').keyup(function() {
        var element = $(this);
        if(element.prop('required')) {
            if (element.val()) {
                element.closest(".form-group").removeClass("has-error");
                element.closest(".form-group").find('.help-block').remove();
            }
        }
    });

    $('#termsAndConditions').on('change', function(e) {
        if ($(this).is(":checked")) {
            if ($(this).parents('.form-item').find('.help-block').length > 0) {
                $(this).parents('.form-item').find('.help-block').text('');
            }
        }
    });

    $(".js-followup-wl-create").click(function(event) {
        event.preventDefault();

        var errors = validateCreateForm();
        if (errors) {
            return;
        }

        var data = createData();

        if (data["docs"] && !Array.isArray(data["docs"])) {
            data["docs"] = [data["docs"]];
        } else if (!data["docs"]) {
            data["docs"] = [];
        }

        sendCreateRequest(data);
    });

    $(".js-followup-vr-create").click(function(event) {
        event.preventDefault();

        var errors = validateCreateForm();
        if (errors) {
            return;
        }

        var data = createData();

        if (data["docs"] && !Array.isArray(data["docs"])) {
            data["docs"] = [data["docs"]];
        } else if (!data["docs"]) {
            data["docs"] = [];
        }

        if (data["violations"] && !Array.isArray(data["violations"])) {
            data["violations"] = [data["violations"]];
        }

        data["violationsTexts"] = [];
        if (data["violations"] && data["violations"].length) {
            data["violations"].forEach(function(violationKey) {
                var commentText = $("[data-violation=" + violationKey + "]").val();
                data["violationsTexts"].push(commentText);
            });
        } else {
            data["violations"] = [];
        }

        sendCreateRequest(data);
    });

    function validateCreateForm() {
        var hasErrors = false;

        if(!validateTermsAndConditions('#termsAndConditions')) {
            hasErrors = true;
        }

        $('.js-form-element').each(function() {
            var element = $(this);
            if(element.prop('required')) {
                if (element.val() === "0" && element.hasClass('js-days-range') || !element.val()) {
                    element.closest(".form-group").addClass("has-error");

                    if (element.closest('textarea').length && element.closest('textarea')[0].id === 'extensionReason') {
                       if($('#commentserrormessage').length) {
                           $('#commentserrormessage')[0].remove();
                       }
                       element.closest(".form-group").append("<div class='help-block' id='reasonerrormessage'><span>" + "Please enter your reason for the warning letter extension" + "</span></div>");
                    } else {
                       if($('#reasonerrormessage').length) {
                          $('#reasonerrormessage')[0].remove();
                       }

                       if($('#commentserrormessage').length) {
                           $('#commentserrormessage')[0].remove();
                       }

                       element.closest(".form-group").append("<div class='help-block' id='commentserrormessage'><span>" + "Please enter the comments" + "</span></div>");
                    }

                    hasErrors = true;
                } else {
                    element.closest(".form-group").removeClass("has-error");

                    if($('#commentserrormessage').length) {
                        $('#commentserrormessage')[0].remove();
                    }

                    if($('#reasonerrormessage').length) {
                        $('#reasonerrormessage')[0].remove();
                    }
                }
            }
        });

        return hasErrors;
    }

    function createData() {
        var data = {};
        jQuery(".js-followup-form").serializeArray().map(function(item) {
            var attributeName;
            if (item.name && item.name.indexOf("[") > 0) {
                attributeName = item.name.substring(0, item.name.indexOf("["))    ;
            } else {
                attributeName = item.name;
            }

            if ( data[attributeName] ) {
                if ( typeof(data[attributeName]) === "string" ) {
                    data[attributeName] = [data[attributeName]];
                }
                data[attributeName].push(item.value);
            } else {
                data[attributeName] = item.value;
            }
        });
        data['termsAndConditionsChecked'] = $('#termsAndConditions').is(':checked');
        return data;
    }

    function sendCreateRequest(data) {
        $.postJsonData("create", data).done(function() {
            showAndSendFeedback();
        }).fail(function(data) {
            if(data && data.responseJSON) {
                var $uploadModal = $("#failInformationModal");

                if (data.responseJSON.status && data.responseJSON.status.name === "VALIDATION_ERROR") {
                    validateTermsAndConditions("#termsAndConditions");
                } else {
                    $uploadModal.find(".js-message").text(data.responseJSON.message);
                    $uploadModal.modal('show');
                }
            }
        })
    }

    initDaysRange();
    function initDaysRange() {
        $(".js-days-range").change(function () {
            var $range = $(this);
            var value = $range.val();
            var date = $range.data('date');

            if ($range.val() === '0') {
                $range.closest(".form-group").addClass("has-error");
            } else {
                $range.closest(".form-group").removeClass("has-error");
            }
            $.get("date-info?date=" + date + "&days=" + value).done(function (response) {
                $(".js-end-date").text(response.dateFormatted);
            }).fail(function (error) {})
        });
    }

    function initDownloadAttachments() {
        $(".js-download-attachments").click(function (event) {
            event.preventDefault();

            var $element = $(this);
            var objectId = $element.data("attachmentsObject");
            var attachmentsFileId = $element.data("attachmentsFile");
            window.open(ACC.config.encodedContextPath + "/attachment/pdf/" + objectId + "/" + attachmentsFileId);
        });
    }
    
  	//get the new created violation reply and send its ID to the feedbackService
    function showAndSendFeedback() {
        $.ajax({
	        type: "GET",
	        dataType: 'json',
	        url: ACC.config.encodedContextPath + "/violation-replies/latest/",
	        success: function (data) {
	        	//set serviceId for feedback to be entity id
	            $('#serviceId').val(data.srId);
	        }
	   });
        //open the modal
        $('#requestSubmittedComment').modal().open;
        //close the modal
        $('#requestSubmittedComment').on('hide.bs.modal', function () {
            window.location.href = './';
        });
    }

    $('#supportVisit').validate({
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
            error.appendTo(element.closest('.formInputBox').find('.help-block'));
        },
        rules: {
            dateString: "required",
            message : "required"

        },
        messages: {
            dateString: {
                required: getI18nText("validation.empty")
            },

            message: {
                required: getI18nText("validation.empty")
            }
        }
    });
});
