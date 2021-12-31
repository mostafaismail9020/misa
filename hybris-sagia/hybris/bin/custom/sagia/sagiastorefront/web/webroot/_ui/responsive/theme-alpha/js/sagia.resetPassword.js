$(function() {

    clearSuccessValidations();

    $.validator.setDefaults({
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorPlacement: function(error, element) {
            if(element.closest('.formInputBox_big').length>0){
                error.appendTo(element.closest('.formInputBox_big').find('.help-block'));
            } else if (element.closest('.formInputFile_group').length>0){
                error.appendTo(element.closest('.formInputFile_group').find('.help-block'));
            } else if(element.closest('.form-group').find('.help-block').length>0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn')) {
                error.appendTo(element.closest('.col-md-6').find('.help-block'));
            } else if (element.hasClass('form-control')) {
                error.appendTo(element.parent().find('.help-block'));
            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        onkeyup: false
    });

    $.validator.addMethod("regex", function (value, element, regexp) {
        return this.optional(element) || new RegExp(regexp).test(value);
    }, "");

    $.validator.addMethod("sum255", function (value, element, parameter) {
        var relatedElement = $("." + parameter);
        return (value + relatedElement.val()).length < 255;
    }, getI18nText("register.name.invalid"));

    jQuery.extend(jQuery.validator.messages, {
        minlength: jQuery.validator.format(getI18nText("validation.minlength"))
    });

    var resetPswValidationParams = {
        rules: {
            pwd: {
                required: true,
                regex: backendRegex
            },
            checkPwd: {
                required: true,
                equalTo: "#password"
            }
        },
        messages: {
            pwd: {
                required: getI18nText("profile.password.provide"),
                regex: backendRegexErrorMessage
            },
            checkPwd: {
                required: getI18nText("profile.password.provide"),
                equalTo: getI18nText("profile.password.equals")
            }
        },
        invalidHandler: function(event, validator) {
        }
    };

    $("#sagiaResetPwdForm").validate(resetPswValidationParams);

    resetPswValidationParams.rules.checkPwd = {
        required: true,
        equalTo: "#password"
    };

    $(".js-select-required").on("change", function() {
        $(this).valid();
    });

    $(".resetPwdForm").on('focus blur keyup', function () {
        var passwordSuccessDiv = $(this).closest('.formInputBox').find(".success-message-block");
        setTimeout(function() {
            var formValidation = $("#sagiaResetPwdForm").validate();
            var hasNoError = (formValidation.errorMap === undefined) || (formValidation.errorMap.pwd === undefined && !formValidation.invalid.pwd);
            hasNoError = hasNoError && $("#sagiaResetPwdForm").validate().element(".resetPwdForm");
            if (hasNoError) {
                passwordSuccessDiv.text(getI18nText('register.validation.psw.ok'));
            } else {
                passwordSuccessDiv.empty();
            }
        }, 100);
    });

    //used to remove the success message when having an error message because of using backspace key
    $(".resetPwdForm, .resetPwdCheckForm").on('blur').keyup(function(e){
        if(e.keyCode === 8) {
            $(this).closest('.formInputBox').find(".success-message-block").empty();
        }
    });

    $(".resetPwdCheckForm").on('focus blur keyup', function () {
        var passwordSuccessDiv = $(this).closest('.formInputBox').find(".success-message-block");
        var formValidation = $("#sagiaResetPwdForm").validate();
        var hasNoError = (formValidation.errorMap === undefined) || (formValidation.errorMap.pwd === undefined && !formValidation.invalid.checkPwd);
        hasNoError = hasNoError && $("#sagiaResetPwdForm").validate().element(".resetPwdCheckForm");
        if (hasNoError) {
            passwordSuccessDiv.text(getI18nText('register.validation.psw.ok2'));
        } else {
            passwordSuccessDiv.empty();
        }
    });

    function clearSuccessValidations() {
        $(".success-message-block").empty();
    }
});
