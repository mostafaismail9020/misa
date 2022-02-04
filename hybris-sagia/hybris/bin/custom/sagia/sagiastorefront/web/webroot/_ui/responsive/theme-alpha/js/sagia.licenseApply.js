SAGIA.licenseApply = {
    _autoload: [
        "bindAll"
    ],

    inputWrapperSelectors: ".formInputBox, .formSelectBox, .formInputFile",

    bindAll: function () {
        this.validateEmailOnChange();
        this.validateEmailOnChangeForDelegate();
        this.validateWebsiteOnChange();
    },

    setMobileCode: function (sourceElement, targetElement1, targetElement2) {
        var self = this;

        if (!(sourceElement === '#qm1Country')) {
            $(sourceElement).off('change');
        }
        $(document).on('change', sourceElement, function () {
            var code = $(this).val();

            if(code)
            {
                $.ajax({
                    url : ACC.config.encodedContextPath + "/my-sagia/license/countries",
                    type : "GET",
                    success : function(data) {
                        var countries = JSON.parse(data);

                        var prefix = countries.filter(function (el) {
                            return el.code === code;
                        });

                        if (prefix[0] != null) {
                            var countryCodeInput1 = $(targetElement1);
                            var countryCodeInput2 = $(targetElement2);

                            if (prefix[0].phoneprefix != null) {
                                self.setCountryCodeInputValues(countryCodeInput1, prefix[0].phoneprefix);
                                self.setCountryCodeInputValues(countryCodeInput2, prefix[0].phoneprefix);
                            } else {
                                self.setCountryCodeInputValues(countryCodeInput1, "");
                                self.setCountryCodeInputValues(countryCodeInput2, "");
                            }
                        }
                    }
                });
            }
            else
            {
                self.setCountryCodeInputValues($(targetElement1), "");
                self.setCountryCodeInputValues($(targetElement2), "");
            }
        });
    },

    setCountryCodeInputValues: function (input, value) {
        if (input.attr('data-value')) {
            input.val(input.attr('data-value')).removeAttr('data-value');
        } else {
            input.val(value).trigger("blur").trigger("change");
        }
    },

    parseHtml: function(html) {
        return $('<textarea/>').html(html).val()
    },

    checkSharePercentages: function (element) {
        var sharesPercentageSum = 0;

        $('#shareholderQM1TableSection tr[data-sharespercentage]').each(function () {
            var sharesPercentageValue = parseFloat($(this).attr('data-sharespercentage'));

            if (!(element.parents('form').find('[name=code]').val() === $(this).attr('data-id'))) {
                sharesPercentageSum += sharesPercentageValue;
            }
        });

        element.val(element.val().replace(',','.'));
        var currentPercentage = element.val();
        sharesPercentageSum += parseFloat(currentPercentage);

        if (sharesPercentageSum > 100) {
            var formGroup = element.parent();
            formGroup.addClass('has-error');
            formGroup.siblings('.help-block').text(getI18nText("validation.shareholder.sharesPercentage.amount"));

            return false;
        } else {
            return true;
        }
    },

    validateRadioMandatory: function(element) {
        if (element.filter(':checked').val()) {
            return true;
        } else {
            var helpBlock = element.parents('.formRadioBox').find('.help-block');

            this.appendError(helpBlock,getI18nText("validate.licenseApply.mandatory"));

            element.parents('.form-group').addClass('has-error');

            return false;
        }
    },

    validateMandatory: function (element) {
        if (element.filter('[type=file]').length > 0 && element.parents('form').find('[name=code]').val()) {
            return true;
        } else {
            if (element.val()) {
                return true;
            } else {
                var helpBlock = element.parents(this.inputWrapperSelectors).find('.help-block');

                if (element.filter('[type=file]').length > 0) {
                    this.appendError(helpBlock,getI18nText("validate.licenseApply.mandatoryFile"));
                } else {
                    this.appendError(helpBlock,getI18nText("validate.licenseApply.mandatory"));
                }

                element.parents('.form-group').addClass('has-error');
                return false;
            }
        }

    },

    validateMandatoryAndGetMessage: function (element) {
        var self = this;
        var type = "";

        if (!element.parents('#delegate').length > 0) {
            switch (element.parents('form').attr('id')) {
                case "personShareholderForm":
                    type = "personShareholder"; break;
                case "organizationShareholderForm":
                    type = "organizationShareholder"; break;
                case "existingShareholderForm":
                    type = "existingShareholder"; break;
            }
        }

        if (element.filter('[type=file]').length > 0 && element.parents('form').find('[name=code]').val()) {
            return true;
        } else {
            if (element.val()) {
                return true;
            } else {
                var helpBlock = element.parents(this.inputWrapperSelectors).find('.help-block');

                this.appendError(helpBlock, self.getErrorMessageInfo(element.attr('name'),type));

                element.parents('.form-group').addClass('has-error');
                return false;
            }
        }

    },

    validateFileTypeAndSize: function (element) {
        var valid = true;
        if(element.val()) {
            var validType = element.attr('accept') === element[0].files[0].type;
            var validSize = true;

            if (element[0].hasAttribute('data-maxsize')) {
                var maxSizeInMB = (parseFloat(element.attr('data-maxsize')) * 1024) * 1024;
                validSize = element[0].files[0].size < maxSizeInMB;
            }

            var helpBlock = element.parents(".formInputFile").find('.help-block')

            if (!validType) {
                valid = false;
                this.appendError(helpBlock, getI18nText("validate.licenseApply.fileExtension"));
                element.parents('.form-group').addClass('has-error');
            }

            if (!validSize) {
                valid = false;
                this.appendError(helpBlock,getI18nText("validate.licenseApply.fileSize"));
                element.parents('.form-group').addClass('has-error');
            }
        } else {
            var validElements = false;
            if (element.parents('form').attr('id') === 'entityForm') {
                validElements = (element.parents('[data-existingentity]').length > 0 && !element.siblings('input[type=text]').attr('placeholder')) ||
                    !element.parents('[data-existingentity]').length > 0;

            } else {
                validElements = (element.parents('form').find('[name=code]').val() && !element.siblings('input[type=text]').attr('placeholder')) ||
                    !element.parents('form').find('[name=code]').val();
            }

            if (validElements)
            {
                var helpBlock = element.parents(".formInputFile").find('.help-block');
                valid = false;
                this.appendError(helpBlock,getI18nText("validate.licenseApply.mandatoryFile"));
                element.parents('.form-group').addClass('has-error');
            }

        }

        return valid;
    },

    validateEmailAddresses: function (element) {
    	var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        var valid = true;
        var helpBlock = element.parents(".formInputBox").find('.help-block')

        if (emailRegex.test(element.val().toLowerCase())) {
            valid = true;
        } else {
            valid = false;
            this.appendError(helpBlock, getI18nText("validate.licenseApply.email"));
            element.parents('.form-group').addClass('has-error');
        }

        return valid;
    },

    validateWebsite: function (element) {
        // var websiteRegex = new RegExp('^(https?:\\/\\/)?'+ // protocol
        //     '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
        //     '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
        //     '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
        //     '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
        //     '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
        var websiteRegex = new RegExp('^(https?:\\/\\/)?((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.){2,}[a-z]{2,})$', 'i');
        var valid = true;
        var helpBlock = element.parents(".formInputBox").find('.help-block')

        if (websiteRegex.test(element.val().toLowerCase())) {
            valid = true;
        } else {
            valid = false;
            this.appendError(helpBlock,getI18nText("validate.licenseApply.website"));
            element.parents('.form-group').addClass('has-error');
        }

        return valid;
    },

    validateNumberInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[0-9]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/\D+/g, ""));
            }, 0);
        })
    },

    validateNumberFloatInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[0-9\.]$");
            // var regexMulti = new RegExp("^[\d]+\.?[\d]*$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            } else {
                var hasDot = $(this).val().indexOf('.') >= 0;
                if (key === '.' && hasDot) {
                    event.preventDefault();
                    return false;
                }
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/[^0-9.]+/g, ""));
                var dotCount = $self.val().replace(/[^.]/g, "").length;

                if (dotCount > 1) {
                    while (dotCount > 1) {
                        $self.val($self.val().replace(/[.]/, ""));
                        dotCount--;
                    }
                }
            }, 0);
        })

    },

    validateNumberFloatSharesPercentageInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[0-9\.]$");
            // var regexMulti = new RegExp("^[\d]+\.?[\d]*$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);

            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            } else {
                var hasDot = $(this).val().indexOf('.') >= 0;
                if (key === '.' && hasDot || key === '.' && $(this).val().length === 3) {
                    event.preventDefault();
                    return false;
                }

                var $self = $(this);
                var oldValue = $self.val();

                setTimeout(function () {
                    if (key !== '.' && hasDot) {
                        var value = $self.val().split('.');

                        if (value[0].length > 2 || value[1].length > 7) {
                            $self.val(oldValue);
                        }
                    }

                    if (key !== '.' && !hasDot) {
                        var value = $self.val();

                        if (value.length >= 3 && value > 100) {
                            $self.val(oldValue);
                        }
                    }
                }, 0);
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                if ($self.val().indexOf('.') < 0) {
                    $self.val($self.val().replace(/\D+/, ""));

                    if ($self.val() > 100) {
                        var value = $self.val().match(/...?/g)[0];

                        if (value > 100) {
                            $self.val(value.match(/..?/g)[0]);
                        } else {
                            $self.val(value);
                        }
                    }
                } else {
                    var dotCount = $self.val().replace(/[^.]/g, "").length;

                    if (dotCount > 1) {
                        $self.val("");
                    } else {
                        // var regex = new RegExp("^[\d]{1,2}\.[\d]{1,7}$");
                        if (!/^[\d]{1,2}\.[\d]{1,7}$/.test($self.val())) {
                            $self.val("");
                        }
                    }
                }
            }, 0);
        })
    },

    validateCharacterOnlyInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[a-zA-Z ]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/[^a-zA-Z ]+/g, ""));
            }, 0);
        })
    },

    validateNoSpecialCharsInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[a-zA-Z0-9 ]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/[^a-zA-Z0-9 ]+/g, ""));
            }, 0);
        })
    },

    validateNoSpecialCharsWithArabicInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/[^a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]+/g, ""));
            }, 0);
        })
    },

    validateArabicOnlyInput: function (element) {
        element.on('keypress', function (event) {
            var regex = new RegExp("^[\u0621-\u064A\u0660-\u0669 ]+$");
            var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
            if (!regex.test(key)) {
                event.preventDefault();
                return false;
            }
        });

        element.on('paste', function () {
            var $self = $(this)
            setTimeout(function () {
                $self.val($self.val().replace(/[^\u0621-\u064A\u0660-\u0669 ]+/g, ""));
            }, 0);
        })
    },

    appendError: function (helpBlock, message) {
        helpBlock.text(helpBlock.text() ? helpBlock.text() + ' ' + message : message);

        if (helpBlock.css('display') === "none") {
            helpBlock.show();
        }
    },

    bindInputValidation: function (element) {
        var self = this;

        element.find('.validate__numbers-only').each(function () {
            self.validateNumberInput($(this));
        });

        element.find('.validate__float-numbers-only').each(function () {
            self.validateNumberFloatInput($(this));
        });

        element.find('.validate__characters-only').each(function () {
            self.validateCharacterOnlyInput($(this));
        });

        element.find('.validate__no-special-chars').each(function () {
            self.validateNoSpecialCharsInput($(this));
        });

        element.find('.validate__no-special-chars-with-arabic').each(function () {
            self.validateNoSpecialCharsWithArabicInput($(this));
        });

        element.find('.validate__arabic-only').each(function () {
            self.validateArabicOnlyInput($(this));
        });

        element.find('.validate__float-numbers-only-sharespercentage').each(function () {
            self.validateNumberFloatSharesPercentageInput($(this));
        });
    },

    scrollToErrorMessage: function (element) {
        var topGutter = $('.sagiaHead').outerHeight() + element.outerHeight() + 10;

        $([document.documentElement, document.body]).animate({
            scrollTop: element.offset().top - topGutter
        }, 500);
    },

    globalErrorMessage: function (element) {
        element.show().find('.text').text("Please review the data on your form.");
        SAGIA.licenseApply.scrollToErrorMessage(element);
    },

    removeGlobalErrorMessage: function (element) {
        element.hide();
        element.find('.text').text("");
    },

    validateEmailOnChange: function(element) {
        var self = this;
        $(document).on('change', ".validate__email", function () {
            $(this).parents('.form-group').removeClass('has-error');
            $(this).parents('.formInputBox').find('.help-block').text('');
            return self.validateEmailAddresses($(this))
        })
    },

    validateWebsiteOnChange: function () {
        var self = this;
        $(document).on('change', '.validate__website', function () {
            $(this).parents('.form-group').removeClass('has-error');
            $(this).parents('.formInputBox').find('.help-block').text('');
            return self.validateWebsite($(this));
        })
    },

    validateEmailOnChangeForDelegate: function () {
        $(document).on('change', ".validate__delegate-email", function () {
            $(this).parents('.form-group').removeClass('has-error');
            $(this).parents('.formInputBox').find('.help-block').text('');
            return SAGIA.licenseApply.validateEmailAddresses($(this))
        });
    },

    getErrorMessageInfo: function (fieldName, type) {
        if (type) type+=".";
        return getI18nText("validate.licenseApply."+type+fieldName+".error");
    }
};





$('.plus-icon').hide();
$(".my-button").on('click', function () {
    var obj = $(this).find('.my-icon');
    $(obj[0]).hide();
    $(obj[1]).show();
    $('.my-button-handler').removeClass('my-button');
})

$(document).on('click','.cancelButton', function(){
    $('.add-icon').show();
    $('.plus-icon').hide();
    $('.person-type').removeClass('active');
    $('.organization-type').removeClass('active');
    $('.label-name').removeClass('label-name-active').addClass('label-name-inactive')
});
$('.shareholder-types1').on('click', function(){
    $('.organization-type').removeClass('active');
    $('.person-type').removeClass('active');
    $('.label-name').removeClass('label-name-active').addClass('label-name-inactive');
    if($(this).hasClass('active')){
        var tempObj = $(this).children().eq(1);
        $(tempObj).removeClass('label-name-active').addClass('label-name-inactive')
        $(this).removeClass('active');
    }else{
        var tempObj = $(this).children().eq(1);
        $(tempObj).removeClass('label-name-inactive').addClass('label-name-active')
        $(this).addClass('active');
    }  
});