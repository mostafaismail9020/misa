$(function () {
    var list = document.getElementById('history-list');

    //when an element is no longer selected, remove historyList-item_current class
    $('ul#history-list').on('click', 'li', function (e) {
        var srID = $(e.currentTarget).find('.historyList-id').text();
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";
    });


    // update the content in the details section, based on the selected item
    $('.supportVisitItem').click(function () {
        ajaxCallEvent($(this));
    });

    //update the content in the details section, based on the selected item
    function ajaxCallEvent(object) {
        var id = object.find(".historyList-id").text();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + "/support-visits/" + id,
            data: "id=" + id, // appears as $_GET['id'] @ your backend side
            success: function (data) {
                var supportVisit = JSON.parse(data);

                $('#supportVisitId').html(supportVisit.srId);
                $('#supportVisitDate').html(supportVisit.visitDate.dateFormatted);
                $('#supportVisitMsg').html(supportVisit.textMsg);

                if (supportVisit.supportVstToAttachNav != null && supportVisit.supportVstToAttachNav.length > 0) {
                    var attachmentTemplate = document.getElementById("supportVisitAttachments-template");
                    var attachmentTemplateHtml = attachmentTemplate.innerHTML;
                    var filesHtml = "";
                    $('#supportVisitDocuments').removeAttr('hidden');
                    for (var key in supportVisit.supportVstToAttachNav) {
                        filesHtml += attachmentTemplateHtml
                            .replace(
                                /{{fileName}}/g,
                                supportVisit.supportVstToAttachNav[key]["fileName"])
                            .replace(
                                /{{objectId}}/g,
                                supportVisit.supportVstToAttachNav[key]["objectId"])
                            .replace(
                                /{{fileGuid}}/g,
                                supportVisit.supportVstToAttachNav[key]["fileGuid"]
                            ).replace(
                                /{{mimeType}}/g,
                                supportVisit.supportVstToAttachNav[key]["mimeType"]
                            ).replace(
                                    /{{fullFileName}}/g,
                                    supportVisit.supportVstToAttachNav[key]["fullFileName"]
                            );
                    }
                    $('#supportVisitsAttachmentList').html(filesHtml);
                } else {
                    $('#supportVisitDocuments').attr('hidden', 'hidden');
                }

                if (supportVisit.suppVisitToText != null && supportVisit.suppVisitToText[0].tdline.length > 0) {
                    var commentsTemplate = document.getElementById("supportVisitComments-template");
                    var commentsTemplateHtml = commentsTemplate.innerHTML;
                    var commentsHtml = "";
                    $('#supportVisitsComments').removeAttr('hidden');
                    for (var key in supportVisit.suppVisitToText) {
                        commentsHtml += commentsTemplateHtml
                            .replace(
                                /{{commentsBy}}/g,
                                supportVisit.suppVisitToText[key]["commentsBy"])
                            .replace(
                                /{{dateFormatted}}/g,
                                supportVisit.suppVisitToText[key].commentDate.dateFormatted)
                            .replace(
                                /{{tdline}}/g,
                                supportVisit.suppVisitToText[key]["tdline"]
                            );
                    }
                    $('#supportVisitsCommentsList').html(commentsHtml);
                } else{
                    $('#supportVisitsComments').attr('hidden', 'hidden');
                }
            },
            error: function () {
                $('#errorResponseModal').find('.modal-description').text(getI18nText("general.error"));
                $('#errorResponseModal').modal('show');
            }
        });
    };

    if (Boolean($('#requestFeedback').val())) {
        showAndSendFeedback();
    }

    function showAndSendFeedback() {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + "/support-visits/latest",
            success: function (data) {
                //set serviceId for feedback to be entity id
                $('#serviceId').val(data.srId);
            },
            error: function () {
                $('#errorResponseModal').find('.modal-description').text(getI18nText("general.error"));
                $('#errorResponseModal').modal('show');
            }
        });
        //open the modal
        $('#requestSubmittedComment').modal().open;
        //close the modal
        $('#requestSubmittedComment').on('hide.bs.modal', function () {
            window.location.href = ACC.config.encodedContextPath
                + '/support-visits';
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
                    if (response && response.parameters) {
                        response.parameters.forEach(
                            function(parameter) {
                                if (parameter.name !== "CSRFToken") {
                                    var domElement = $("[name='" + parameter.name + "']");
                                    if(domElement){
                                        domElement.val(parameter.value);
                                    }
                                }
                            }
                        );

                        if(response.files){
                            response.files.forEach(
                                function (file, index) {
                                    var fileInput = $('[name="' + file.attachmentInputName + '"]');
                                    var textInput = fileInput.next('input:text'),
                                        rootElement = fileInput.closest('.formInputFile');

                                    textInput.attr('placeholder', file.name);

                                    var fileIndex = fileInput.data('id');
                                    var mockFileInput = '<input id="fileId_'+fileIndex+'" name="draftFiles['+ fileIndex +']" data-id="' + fileIndex +'" class="form-control js-mock-input" style="display:none;" type="text" value="' + file.id + '">';
                                    textInput.after(mockFileInput);

                                    if (fileInput.length > 0) {
                                        rootElement.addClass('active');
                                    } else {
                                        rootElement.removeClass('active');
                                    }
                                }
                            );
                        }

                        $("#" + id).trigger('change');
                    }
                })
        });


});


