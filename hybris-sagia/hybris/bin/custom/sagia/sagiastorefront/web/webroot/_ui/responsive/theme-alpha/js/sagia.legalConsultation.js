//updates the status in the detail section based on the selected service instance
function updateStatus(e) {
    var status = $(e.currentTarget).find('.historyList-status').text();
    $('.statusIndicator').html('Status: <span>' + status + '</span>');
    $('.statusIndicator').attr('class', 'statusIndicator');

    switch (status != null && status.length > 0) {
        case status.toLowerCase().includes('process'):
            $('.statusIndicator').addClass('statusIndicator_inprocess');
            break;
        case status.toLowerCase().includes('reject'):
            $('.statusIndicator').addClass('statusIndicator_rejected');
            break;
        case status.toLowerCase().includes('accept'):
            $('.statusIndicator').addClass('statusIndicator_accepted');
            break;
        case status.toLowerCase().includes('complete'):
            $('.statusIndicator').addClass('statusIndicator_completed');
            break;
        default:
            $('.statusIndicator').addClass('statusIndicator_inprocess');
    }
}


$(function () {

    var $historyList = $('#history-list');
    var $currentItem = $historyList.find('.historyList-item_current');
    $historyList.scrollTop(
        $currentItem.offset().top - $historyList.offset().top + $historyList.scrollTop()
    );

    $('#attachmentList')
        .on(
            'click',
            'div[data-view-attachment-target]',
            function (e) {
                var objectId = $(this).data('objectId');
                var documentId = $(this).data('documentId');
                window.open(ACC.config.encodedContextPath + "/attachment/pdf/" +
                    objectId + "/" + documentId,
                    '_blank');
            });

    $('ul#history-list').on('click', 'li',
        function (e) {
            var srId = $(e.currentTarget).find('.historyList-id')
                .text();
            var lis = document.getElementById("history-list")
                .getElementsByTagName("li");
            for (index = 0; index < lis.length; ++index) {
                lis[index].classList.remove('historyList-item_current');
            }
            e.currentTarget.className += " historyList-item_current";

            $.ajax({
                type: 'GET',
                url: ACC.config.encodedContextPath +
                    "/legalconsultations/" + srId +
                    "/details",
                dataType: 'json',
                success: function (res) {
                    var attachedFiles = res.contentHDRSet;
                    var attachedFilesDivContent = $('#attachmentList');
                    attachedFilesDivContent.empty();

                    var messagesList = res.getTextSet;
                    var messagesListDivContent = $('#messagesListUL');
                    messagesListDivContent.empty();

                    var supportedAttachmentsTemplate = document
                        .getElementById("supportedAttachments-template");
                    var supportedAttachmentsTemplateHtml = supportedAttachmentsTemplate.innerHTML;
                    var listHtml = "";

                    var messagesLegalConsultationTemplate = document
                        .getElementById("messagesLegalConsultation-template");
                    var messagesLegalConsultationTemplateHtml = messagesLegalConsultationTemplate.innerHTML;

                    for (var key in attachedFiles) {
                        var fileName = replaceWithEmptyIfNotPresent(attachedFiles[key]["filename"]);
                        var objectId = replaceWithEmptyIfNotPresent(attachedFiles[key]["objectId"]);
                        var documentId = replaceWithEmptyIfNotPresent(attachedFiles[key]["documentId"]);
                			var downloadUrl = ACC.config.encodedContextPath + "/attachment/pdf/" + objectId + "/" + documentId;

                        listHtml += supportedAttachmentsTemplateHtml
                            .replace(
                                /{{filename}}/g, fileName)
                            .replace(
                                /{{objectId}}/g, objectId)
                            .replace(
                                /{{documentId}}/g, documentId)
                             .replace(/{{downloadUrl}}/g,downloadUrl)
                              .replace(/{{fullFileName}}/g, attachedFiles[key]["fullFileName"]);
                    }
                    var expandedLegalConsultationTemplate = document
                        .getElementById("expandedLegalConsultationTemplate");
                    var expandedLegalConsultationTemplateHtml = expandedLegalConsultationTemplate.innerHTML;
                    var expandedDataHtml = expandedLegalConsultationTemplateHtml
                        .replace(/{{srId}}/g, res.srId)

                        .replace(/{{srStDesc}}/g,
                            res.srStDesc);
                    var messagesListHtml = '';
                    for (var key in messagesList) {
                        var commentsBy = replaceWithEmptyIfNotPresent(messagesList[key]["commentsBy"]);
                        var commentDate = replaceWithEmptyIfNotPresent(messagesList[key].commentDate.dateFormatted);
                        var tdline = replaceWithEmptyIfNotPresent(messagesList[key]["tdline"]);

                        messagesListHtml += messagesLegalConsultationTemplateHtml
                            .replace(
                                /{{commentsBy}}/g, commentsBy)
                            .replace(
                                /{{commentDate}}/g, commentDate)
                            .replace(
                                /{{tdline}}/g, tdline);
                    }
                    $('#detailedLegalConsultationContent')
                        .html(expandedDataHtml);
                    $('#attachmentList').html(listHtml);
                    $('#messagesListUL').html(messagesListHtml);
                    updateStatus(e);
                }
            })
        });

    function replaceWithEmptyIfNotPresent(value) {
        return (value == null) ? "" : value;
    }

    if(Boolean($('#requestFeedback').val())){
        $('#requestSubmittedComment').modal().open;
    } else if ($('#legalConsultationErrorMessage').val()) {
        var errorModal = $('#errorResponseModal');
        errorModal.find('.modal-description').text($('#legalConsultationErrorMessage').val());
        errorModal.modal('show');
    }


});
