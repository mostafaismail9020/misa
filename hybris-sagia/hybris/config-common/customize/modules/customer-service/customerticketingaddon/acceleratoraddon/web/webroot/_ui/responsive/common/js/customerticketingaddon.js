ACC.investsaudicustomerticketing = {

    _autoload: [
        "onStatusChange",
        "bindMessageArea",
        "toggleAllMessages",
        "postNewMessage",
        "onFileChosen",
        "bindTicketAddActions",
        "bindTicketUpdateActions",
        "bindBeforeSubmitFunctions",
        "bindQuestionsToSectorChange",
        "bindCloseToQuestionMessages",
        "bindSubSectorsToSectorChange",
		"showConfirmBox"
    ],

    disableMessage: function(_this){
        var currentTicketStatus = $('input[id="currentTicketStatus"]').val();
        var selectedStatus = $(_this).val();

        if((currentTicketStatus === 'COMPLETED' && selectedStatus === 'COMPLETED') || (currentTicketStatus === 'CLOSED' && selectedStatus === 'CLOSED')) {
            $('textarea[id="message"]').attr('disabled','disabled');
            $('button[id="updateTicket"]').attr('disabled','disabled');
        } else {
            $('textarea[id="message"]').removeAttr('disabled');
        }
    },

    onStatusChange: function () {
        $(document).on('change', '.js-add-message-status', function () {
            ACC.investsaudicustomerticketing.disableMessage(this);
        });
    },

    onFileChosen: function () {
        $(document).on('change', '#supportTicketForm input[name=files]', function () {
            ACC.investsaudicustomerticketing.clearAlerts();
            var selectedFile = document.getElementById('attachmentFiles');
            if (!ACC.investsaudicustomerticketing.isSelectedFilesValid(selectedFile))
            {
                var message = "<span style='color:red'>" + $('#file-too-large-message').text() + "</span>";
                $("#supportTicketForm").find(".js-file-upload__file-name").html(message);
            }
        });
    },

    bindMessageArea: function () {
        $(document).on('keyup', '.js-add-message', function () {
            if($(this).val().length > 0) {
                $('button[id="updateTicket"]').removeAttr('disabled');
                $('#NotEmpty-supportTicketForm-message').hide();
            } else {
                $('button[id="updateTicket"]').attr('disabled','disabled');
            }
        });
    },

    toggleAllMessages: function() {
        $('#ct-toggle-all-messages').on('click touchstart', function() {
            $('.cts-msg-history-item:not(.ct-msg-visible)').show();
            $(this).hide();
        });
    },

    postNewMessage: function () {

        var title = $('#ct-overlay-title').html();
        $('.ct-add-new-msg-btn').on('click touchstart', function(e) {
            e.preventDefault();
            $.colorbox({
                href: "#ct-add-new-msg",
                maxWidth:"100%",
                width: 525,
                opacity:0.7,
                title: title,
                inline: true,
                close: '<span class="glyphicon glyphicon-remove"></span>',
                onOpen: function () {
                    $('#ct-add-new-msg').fadeIn();
                },
                onComplete: function () {
                    ACC.investsaudicustomerticketing.disableMessage($('.js-add-message-status'));

                    if (!$.trim($("#message").val())) {
                        $('button[id="updateTicket"]').attr('disabled', 'disabled');
                    }

                    ACC.csvimport.changeFileUploadAppearance();
                },
                onCleanup: function () {
                    $('#ct-add-new-msg').hide();
                }
            });
        })
    },

    isSelectedFilesValid: function (selectedFiles) {
        if (window.File && window.Blob) {
            var fileMaxSize = $('.js-file-upload__input').data('max-upload-size');
            var totalSize = 0;

            for (var i = 0; i < selectedFiles.files.length; ++i){
                totalSize += selectedFiles.files[i].size;
            }

            if ($.isNumeric(fileMaxSize) && totalSize > parseFloat(fileMaxSize)) {
                return false;
            }
        }

        return true;
    },

    displayCustomerTicketingAlert: function (options) {
        var alertTemplateSelector;

        switch (options.type) {
            case 'error':
                alertTemplateSelector = '#global-alert-danger-template';
                break;
            case 'warning':
                alertTemplateSelector = '#global-alert-warning-template';
                break;
            default:
                alertTemplateSelector = '#global-alert-info-template';
        }

        if (typeof options.message !== 'undefined') {
            $('#customer-ticketing-alerts').append($(alertTemplateSelector).tmpl({message: options.message}));
        }

        if (typeof options.messageId !== 'undefined') {
            $('#customer-ticketing-alerts').append($(alertTemplateSelector).tmpl({message: $('#' + options.messageId).text()}));
        }
    },

    displayGlobalAlert: function (options) {
        var alertTemplateSelector;

        switch (options.type) {
            case 'error':
                alertTemplateSelector = '#global-alert-danger-template';
                break;
            case 'warning':
                alertTemplateSelector = '#global-alert-warning-template';
                break;
            default:
                alertTemplateSelector = '#global-alert-info-template';
        }

        if (typeof options.message !== 'undefined') {
            $('#global-alerts').append($(alertTemplateSelector).tmpl({message: options.message}));
        }

        if (typeof options.messageId !== 'undefined') {
            $('#global-alerts').append($(alertTemplateSelector).tmpl({message: $('#' + options.messageId).text()}));
        }

        if (typeof options.errorMessage !== 'undefined') {
            $('#global-alerts').append($(alertTemplateSelector).tmpl({message: options.errorMessage}));
        }
    },

    bindTicketAddActions: function () {
        $(document).on('click', '#addTicket',
            function (event) {
                event.preventDefault();

                ACC.investsaudicustomerticketing.formPostAction("support-tickets?ticketAdded=true");
            });
    },

    bindTicketUpdateActions: function () {
        $(document).on('click', '#updateTicket',
            function (event) {
                event.preventDefault();
                ACC.investsaudicustomerticketing.formPostAction('?ticketUpdated=true');
            });
    },

    formPostAction: function (successRedirectUrl) {

        ACC.investsaudicustomerticketing.clearAlerts();

        if(successRedirectUrl !==  '?ticketUpdated=true'){


            var error = ACC.investsaudicustomerticketing.bindErrorMessagesToOtherFields();

            if ($('.ticket-questions-area').find('textarea').length > 0) {
                error = ACC.investsaudicustomerticketing.bindErrorMessagesToQuestions();

                if (error) {
                    return false;
                }

                ACC.investsaudicustomerticketing.bindQuestionsToJsonInput();
            }

            if (error) {
                return false;
            }

        }

        var form = document.getElementById("supportTicketForm");
        var formData = new window.FormData(form);

        if(successRedirectUrl ===  '?ticketUpdated=true'){
            formData.append("question1", "?");
            formData.append("question2", "?");
            formData.append("question3", "?");
            formData.append("question4", "?");
            formData.append("question5", "?");
            formData.append("question6", "?");
            formData.append("question7", "?");
            formData.append("question8", "?");
            formData.append("question9", "?");
            formData.append("question10", "?");
            formData.append("question11", "?");
            formData.append("question12", "?");
            formData.append("question13", "?");
        }

        var selectedFile = document.getElementById('attachmentFiles');
        if (!ACC.investsaudicustomerticketing.isSelectedFilesValid(selectedFile)) {
            ACC.investsaudicustomerticketing.displayCustomerTicketingAlert({
                type: 'error',
                messageId: 'attachment-file-max-size-exceeded-error-message'
            });
            return;
        }

        $.ajax({
            url: form.action,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                window.location.replace(successRedirectUrl);
            },
            error: function (jqXHR) {
                ACC.investsaudicustomerticketing.processErrorResponse(jqXHR);
            }
        });
    },

    bindQuestionsToJsonInput: function () {
        var questions = {};
        $('.ticket-questions-area').find('textarea').each(function () {
            questions[$(this).prop('name')] = $(this).val();
        });

        $('#questions').val(JSON.stringify(questions));
    },

    bindErrorMessagesToQuestions: function() {
        var questions = $('.ticket-questions-area').find('textarea');
        var error = false;

        $.each(questions, function () {
            if ($(this).val() == "" && $(this).prop('required')) {
                error = true;

                $(this).parent().find('#'+$(this).prop('name')+'-error')
                    .html('Please fill all mandatory fields').show();
            }
        });

        if (error == true) {
            if ($('.ticket-questions-area').find('.alert').length > 0) {
                $('.ticket-questions-area').find('.alert').remove();
            }
            $('.ticket-questions-area').parents('.account-section-content').find('.global-alerts').append('' +
                '<div class="question-messages"><div class="alert alert-danger alert-dismissable getAccAlert">' +
                '<button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">×</button>' +
                'Please fill all required questions.</div></div>');
        }

        return error;
    },

    bindErrorMessagesToOtherFields: function (){
        var error = false;
        var form = $('#supportTicketForm');

        if (form.find('select[name=question2]').val() == "" || form.find('select[name=question2]').val() == null) {
            form.find('#NotEmpty-BDSupportTicketForm-question2')
                .html('Please fill all mandatory fields').show();

            error = true;
        }

        if (form.find('input[name=subject]').val() == "" || form.find('select[name=subject]').val() == null) {
            form.find('#NotEmpty-BDSupportTicketForm-subject')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (form.find('select[name=question4]').val() == "" || form.find('select[name=question4]').val() == null) {
            form.find('#NotEmpty-BDSupportTicketForm-question4')
                .html('Please fill all mandatory fields').show();
            error = true;
        }

        if (error) {
            form.parents('.account-section-content').find('.global-alerts').append('' +
                '<div class="question-messages"><div class="alert alert-danger alert-dismissable getAccAlert">' +
                '<button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">×</button>' +
                'Please fill all required fields.</div></div>');
        }

        return error;
    },

    bindCloseToQuestionMessages: function () {
        $(document).on('click', '.question-messages .alert .close', function () {
            $(this).parents('.alert').remove();
        })
    },

    processErrorResponse: function (jqXHR) {
        ACC.investsaudicustomerticketing.clearAlerts();
        if (jqXHR.status === 400 && jqXHR.responseJSON) {

            $.each(jqXHR.responseJSON, function() {
                $.each(this, function(k, v) {
                    var target = '#' + k;
                    $(target).show();
                    $(target).text(v);

                    if (k === 'NotEmpty-supportTicketForm-subject'
                        || k === 'Size-supportTicketForm-subject'
                        || k === 'NotEmpty-BDSupportTicketForm-message'
                        || k === 'NotEmpty-BDSupportTicketForm-question1'
                        || k === 'NotEmpty-BDSupportTicketForm-question2'
                        || k === 'NotEmpty-BDSupportTicketForm-question3'
                        || k === 'NotEmpty-BDSupportTicketForm-question4'
                        || k === 'NotEmpty-BDSupportTicketForm-question5'
                        || k === 'NotEmpty-BDSupportTicketForm-question6'
                        || k === 'NotEmpty-BDSupportTicketForm-question7'
                        || k === 'NotEmpty-BDSupportTicketForm-question8'
                        || k === 'NotEmpty-BDSupportTicketForm-question9'
                        || k === 'NotEmpty-BDSupportTicketForm-question10'
                        || k === 'NotEmpty-BDSupportTicketForm-question11'
                        || k === 'NotEmpty-BDSupportTicketForm-question12'
                        || k === 'NotEmpty-BDSupportTicketForm-question13') {
                        ACC.investsaudicustomerticketing.addHasErrorClass();
                    }
                    else {
                        ACC.investsaudicustomerticketing.displayGlobalAlert({type: 'error', message: v});
                    }
                });
            });

            return;
        }

        ACC.investsaudicustomerticketing.displayCustomerTicketingAlert({type: 'error', messageId: 'supporttickets-tryLater'});
    },

    addHasErrorClass: function () {
        $('#createTicket-message').parent().addClass('has-error');
    },

    clearAlerts: function () {
        $('#customer-ticketing-alerts').empty();
        $('#global-alerts').empty();
        $('#NotEmpty-supportTicketForm-subject').hide();
        $('#Size-supportTicketForm-message').hide();
        $('#Size-supportTicketForm-subject').hide();
        $('#createTicket-subject').parent().removeClass('has-error');
        $('#NotEmpty-supportTicketForm-message').hide();
        $('#createTicket-message').parent().removeClass('has-error');
    },

    bindBeforeSubmitFunctions: function () {
        var self = this;

        var form = $("#supportTicketForm");

        form.find("#addTicket").on('click', function () {
            self.bindGroupingQuestionsFunction(form);

            // ACC.investsaudicustomerticketing.formPostAction("support-tickets?ticketAdded=true");
            //form.submit();
        });
    },

    bindGroupingQuestionsFunction: function (form) {
        var groupedValue = "";

        form.find(".ticket-questions,#question2,#createTicket-question4").each(function () {
            if (groupedValue === "") {
                var question = "Q: "+$(this).parent().parent().find(".control-label,.label-ticket,.label-ticket-tight").text().trim();
                groupedValue = question + "\n   A: "+$(this).val()+ "\n\n"
            } else {
                var question = "Q: "+$(this).parent().parent().find(".control-label,.label-ticket,.label-ticket-tight").text().trim();
                groupedValue += question+"\n    A: "+$(this).val()+ "\n\n"
            }
        });

        form.find("input[name=message]").val(groupedValue);
    },

    bindQuestionsToSectorChange: function () {
        var sectorSelect = $('#sector');
        var questionsArea = $('.ticket-questions-area');

        sectorSelect.on('change', function () {
            $.ajax({
                method: "GET",
                url: ACC.config.encodedContextPath + "/my-account/questions-per-sector",
                data: {
                    sector: sectorSelect.val()
                }
            }).done(function (data){
                questionsArea.html(data);
            })
        })

        sectorSelect.trigger('change');
    },

    bindSubSectorsToSectorChange: function () {
        var sectorSelect = $('#parentSector');

        sectorSelect.on('change', function () {
            $.ajax({
                method: "GET",
                url: ACC.config.encodedContextPath + "/my-account/getSubSectors",
                data: {
                    sector: sectorSelect.val()
                }
            }).done(function (data){

                var sector = $("#sector");

                sector.find("option").remove();

                var jsonData = JSON.parse(data);

                jsonData.forEach(function (currentValue) {
                    sector.append(new Option(currentValue.name, currentValue.id, false, false));
                });
                sector.trigger('change');


            })
        })

        sectorSelect.trigger('change');
    },
   
    executeOpportunityStatusChangeOps : function (actionUrl, form) {
        $.ajax({
            url: actionUrl,
            type: 'GET',
            data: form.serialize(),
            contentType: false,
            processData: false,
            success: function () {
	            
                 location.assign("../support-tickets");
                   
            },
            error: function(data) {
                ACC.investsaudicustomerticketing.processErrorResponse(data);
            }
        });
    },
	showConfirmBox: function(e) {
        $(document).on('click', '#confirmationModal',function (event) {
            $("#cboxOverlay").css("display", "none");
            $("#colorbox").css("display", "none");
            event.stopPropagation();
            event.preventDefault();
            return false;

        });

         
      	}
};

$(document).on('click', '#launch-approve-confirm',
			  function (event) {
				  event.preventDefault();
				  var form = $("#opportunitySupportTicketForm");
				  var confirmationModal = $('#confirmationModal');
						confirmationModal.find('.modal-title').text("Confirm");
						confirmationModal.find('.modal-description').text("Do you really want approve?");
						confirmationModal.modal('show');
						confirmationModal.find('.yesButton').on('click', function (e) {
						    event.preventDefault();
						    confirmationModal.modal('hide');
						    ACC.investsaudicustomerticketing.executeOpportunityStatusChangeOps(ACC.config.encodedContextPath+"/opportunity/approve", form);
						});
						confirmationModal.find('.noButton').on('click', function (e) {
							event.preventDefault();
							confirmationModal.modal('hide');
						});
			  })

            $(document).on('click', '#launch-reject-confirm',
                      function (event) {
                          event.preventDefault();
                          var confirmationModal = $('#confirmationModal');
                          var form = $("#opportunitySupportTicketForm");
                            confirmationModal.find('.modal-title').text("Confirm");
                            confirmationModal.find('.modal-description').text("Do you really want to reject?");
                            confirmationModal.modal('show');
                            confirmationModal.find('.yesButton').on('click', function (e) {
                                event.preventDefault();
                                confirmationModal.modal('hide');
                                ACC.investsaudicustomerticketing.executeOpportunityStatusChangeOps(ACC.config.encodedContextPath+"/opportunity/reject", form);
                            });
                            confirmationModal.find('.noButton').on('click', function (e) {
                                event.preventDefault();
                                confirmationModal.modal('hide');
                            });
                      })