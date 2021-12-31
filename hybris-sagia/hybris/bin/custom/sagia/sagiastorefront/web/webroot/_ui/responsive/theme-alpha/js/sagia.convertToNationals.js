 $().ready(function() {
	 var $modal = $('#convertToNationalDisclaimer');
	    $modal.modal('toggle');
    $(document).on("change", '.form-control.js-inputFile', function() {
 		var currentInputFileId = $(this).attr('id');
 		var controlLabelId = "controlLabel-" + currentInputFileId;
 		var fileName = $(this).val().replace(/.*[\/\\]/, '');
 		$('#' + controlLabelId).text(fileName);
   });
     
     if(Boolean($('#requestFeedback').val())){
         $('#requestSubmittedComment').modal().open;
     }

        $('#resubmitButton').click(function(event) {
            event.preventDefault();
            var $form = $("#convNationalResubmitForm");
            var resubmitURI = ACC.config.encodedContextPath + "/convertToNationals/" + $('#currentID').text() + "/" + $('#currentGUID').text() + "/resubmit";
            $('#convNationalResubmitForm').attr('action', resubmitURI);
            $('#convNationalResubmitForm').submit();
        });

        $('#convNationalResubmitForm').submit(function(e) {
            e.preventDefault();
            var $form = $("#convNationalResubmitForm");
            var fd = new FormData($(this)[0]);
            console.info(fd);
            var token = $('input[name="CSRFToken"]').attr('value')

            $.ajax({
                type: 'POST',
                url: $form.attr('action'),
                data: fd,
                cache: false,
                processData: false,
                contentType: false,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('CSRFToken', token);
                    xhr.setRequestHeader('Accept', "application/json");
                },
                success: function(response) {
                $('#errorResponseModal').find('.modal-description').text(getI18nText("general.request.resubmitted"));
                $('#errorResponseModal').modal('show');
                },
                error: function(xhr, status, error) {
                		var errorMessage = xhr.responseJSON.sagiaExceptionResponse.message;
                		$('#errorResponseModal').find('.modal-description').text(errorMessage);
                		$('#errorResponseModal').modal('show');
                }
            });
        });
        

        var modal = $('#uploadFileModal');
        modal.on('shown.bs.modal', function(e) {
            var objectId = $(e.relatedTarget).data('object-id');
            var inputFileItemId = 'formInputListItem' + objectId;
            $('#' + inputFileItemId).show();
        });
        modal.on('hidden.bs.modal', function() {
            $('#inputFilesList li').hide();
        });

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
                    })
            $('ul#history-list')
                .on(
                    'click',
                    'li',
                    function (e) {
                        var srID = $(e.currentTarget).find('.historyList-id').text();
                        var lis = document.getElementById("history-list").getElementsByTagName("li");
                        for (index = 0; index < lis.length; ++index) {
                            lis[index].classList.remove('historyList-item_current');
                        }
                        e.currentTarget.className += " historyList-item_current";

                        $.ajax({
                            type: 'GET',
                            url: ACC.config.encodedContextPath + "/convertToNationals/" + srID + "/details",
                            dataType: 'json',
                            success: function (res) {
                                var attachedFiles = res.convNatToContentNav;
                                var attachedFilesDivContent = $('#attachmentList');
                                attachedFilesDivContent.empty();
                                
                                var messagesList = res.convNatToTextNav;
                                var messagesListDivContent = $('messagesListUL');
                                messagesListDivContent.empty();

                                var supportedAttachmentsTemplate = document.getElementById("supportedAttachments-template");
                                var supportedAttachmentsTemplateHtml = supportedAttachmentsTemplate.innerHTML;
                                var listHtml = "";

                                var uploadFilesModalTemplate = document.getElementById("uploadFilesModal-template");
                                var uploadFilesModalTemplateHtml = uploadFilesModalTemplate.innerHTML;
                                var modalListHtml = "";
                                
                                var messagesConvToNationalsTemplate = document.getElementById("messagesConvToNationals-template");
                                var messagesConvToNationalsTemplateHtml = messagesConvToNationalsTemplate.innerHTML;
                                
                                for (var key in attachedFiles) {
                                		var objectId = attachedFiles[key]["objectId"];
                                		var documentID = attachedFiles[key]["documentID"];
                                		var downloadUrl = ACC.config.encodedContextPath + "/attachment/pdf/" + objectId + "/" + documentID;
                                		listHtml += supportedAttachmentsTemplateHtml
                                        .replace(
                                            /{{filename}}/g,
                                            attachedFiles[key]["filename"])
                                        .replace(
                                            /{{objectId}}/g, objectId)
                                        .replace(
                                            /{{documentID}}/g,documentID)
                                        .replace(
                                            /{{downloadUrl}}/g,downloadUrl)
                                         .replace(
                                        		 /{{fullFileName}}/g, attachedFiles[key]["fullFileName"]);

                                    modalListHtml += uploadFilesModalTemplateHtml
                                        .replace(
                                            /{{documentID}}/g,
                                            attachedFiles[key]["documentID"])
                                        .replace(
                                            /{{index}}/g, Object.keys(attachedFiles).indexOf(key));
                                }
                                
                                var expandedConvTemplate = document.getElementById("expandedConvToNational-template");
                                var expandedConvTemplateHtml = expandedConvTemplate.innerHTML;
                                var expandedDataHtml = expandedConvTemplateHtml
                                    .replace(/{{srID}}/g, res.srID)
                                    .replace(/{{srGuid}}/g, res.srGuid)
                                    .replace(/{{srStDesc}}/g, res.srStDesc)
                                    .replace(/{{srStDescIndicator}}/g, res.srStDesc.replace(/\s+/g, '').toLowerCase())
                                    .replace(/{{longDescr}}/g, res.longDescr);
                                
                                var	messagesListHtml = '';
                                for(var key in messagesList) {
                                	messagesListHtml += messagesConvToNationalsTemplateHtml
                            		.replace(
                            			/{{commentBy}}/g,
                            			messagesList[key]["commentBy"])
                            	 	.replace(
                            			/{{commentDate}}/g,
                            			messagesList[key].sagiaCommentDate.dateFormatted)
                            		.replace(
                            			/{{comments}}/g,
                            			messagesList[key]["comments"]);
                                }

                                var convNationalStatus = res.srStDesc;
                                var isRejected = convNationalStatus.toUpperCase() === 'REJECTED';
                                $('#detailedConvertToNationalsContent').html(expandedDataHtml);
                                $('#attachmentList').html(listHtml);
                                $('#inputFilesList').html(modalListHtml);
                                $('#messagesListUL').html(messagesListHtml);
                                if (isRejected && isReApply) {
                                    document.getElementById("resubmitButton").disabled = false;
                                    document.getElementById("resubmitButton").style.display = "block";

                                    var replaceAnchorTags = $('a[id*="replaceAnchorTag"]');
                                    for (var i = 0; i < replaceAnchorTags.length; ++i) {
                                        var elA = replaceAnchorTags[i];
                                        elA.style.display = "block";
                                    }
                                } else {
                                    document.getElementById("resubmitButton").disabled = true;
                                    document.getElementById("resubmitButton").style.display = "none";
                                    var replaceAnchorTags = $('a[id*="replaceAnchorTag"]');
                                    for (var i = 0; i < replaceAnchorTags.length; ++i) {
                                        var elA = replaceAnchorTags[i];
                                        elA.style.display = "none";
                                    }
                                }
                            }
                        });
                    });

            $("#convertSearchBox").on("keyup", function () {
                var valThis = $(this).val();
                $('#history-list > li').each(function () {
                    var entry = $(this);
                    var text = entry.find('.historyList-id').text();
                    (text.search(valThis) == -1) ? entry.hide() : entry.show();
                });
            });

        });
 

 
