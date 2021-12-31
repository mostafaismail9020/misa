//updates the status in the detail section based on the selected service instance
function updateStatus(e) {
    var status = $(e.currentTarget).find('.historyList-status').text();
    $('.statusIndicator').html('Status: <span>' + status + '</span>');
    $('.statusIndicator').removeClassExcept("statusIndicator");

    switch (status != null && status.length > 0) {
        case status.toLowerCase().includes('process'):
            $('.statusIndicator').addClass('statusIndicator_process');
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
            $('.statusIndicator').addClass('statusIndicator_process');
    }
}

$(function () {

    //when opening the page, make the first element in the list to be selected
    $(document).ready(function () {
        if(srID != 0){
            $('#history-list li[data-param1="' + srID + '"] a.historyList-link').addClass('historyList-item_current').click();
            var $historyList = $('#history-list');
            var $currentItem = $historyList.find('.historyList-item_current');
            $historyList.scrollTop(
                $currentItem.offset().top - $historyList.offset().top + $historyList.scrollTop()
            );
        } else {
            var list = document.getElementById('history-list');
            if (list != null) {
                list.firstElementChild.className += " historyList-item_current";
            }
        }

        //after POST successfull submit, the feedback is requested
		if (Boolean($('#requestFeedback').val())) {
			showAndSendFeedback();
		} else {
			// continue
		}
	});
    
  	// get the new created government service
    function showAndSendFeedback() {
        var categoryUrl = $("[name='categoryUrl']").val();
        var serviceName = $("[name='serviceName']").val();
        var serviceType = $("[name='serviceType']").val();
    		$.ajax({
	        type: "GET",
	        dataType: 'json',
	        url: ACC.config.encodedContextPath + "/services/government/" + categoryUrl + "/" + serviceType + "/latest",
	        success: function (data) {
	        	//set serviceId for feedback to be entity id
	          $('#serviceId').val(data.srID);
	        }
	   });
    		//open the modal 
    		$('#requestSubmittedComment').modal().open;
    		//close the modal
        $('#requestSubmittedComment').on('hide.bs.modal', function () {
        	window.location.href = ACC.config.encodedContextPath 
        				+ '/services/government/'
        				+ categoryUrl + "/" 
        				+ serviceType + '?serviceName=' + encodeURIComponent(serviceName);
        });
    }

    // update the content in the details section, based on the selected item
    $('.serviceItem').click(function () {
        ajaxCallEvent($(this));
    });

    //when selecting a file to be uploaded, remove the has-error class
    $(document).on("change","input[id^='fileId']", function() {
        if($(this).val() != '') {
            var parent = $(this).parent();
            parent.removeClass("has-error");
            parent.find(".help-block").hide();
        }
    });


    $('.js-create-governamentalDocuments').on("submit", function(){
        $('.js-submit-governamentalDocuments').attr('disabled', true);

    });

    //when removing the selected file to be uploaded, add the has-error class
    // $(document).on("click", ".js-inputFile-reset", function() {
    //    $(this).parent().find(".uploadServiceFile").val("");
    //    $(this).parent().addClass("has-error");
    //    $(this).parent().find(".help-block").show();
    // });

    //  add/remove has-error class based on the selection of the consent box
    $(document).on("click", "#consentBox", function() {
        var isChecked = $('#consentBox').is(':checked');
        if(isChecked){
            $('#consentBox').parent().parent().removeClass('has-error');
        } else {
            $('#consentBox').parent().parent().addClass('has-error');
        }
    });

    //update the content in the details section, based on the selected item
    function ajaxCallEvent(object) {
        var id = object.find(".historyList-id").text(),
            category = $('input#category').val(),
            service = $('input#service').val();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + "/services/government/" + category+ "/" + service + "/" + id,
            data: "id=" + id, // appears as $_GET['id'] @ your backend side
            success: function (data) {
                var service = JSON.parse(data);
                $("#currentID").text(service.srID);
                if (service.attachments != null) {
                    var filesHtml = '';
                    var parts = location.pathname.split("/"),
                        serviceUrl = parts[parts.length - 1];
                    service.attachments.forEach(function (element) {
                        if (element.filesize > 0) {
                            if(element.filename.length == 0){
                                element.filename = getI18nText("services.file.noName");
                            }
                            filesHtml +=

                                '<li class="downloadList-item">' +
                                '<div class="downloadList-description">' +
                                '<span class="iconElement iconElement_pdf"><svg version="1" xmlns="http://www.w3.org/2000/svg" width="24" height="32" viewBox="0 0 24 32"><path d="M8.809 25.185c-.095-.019-.286-.026-.572-.026h-.384v1.116h.435c.312 0 .523-.021.627-.062.106-.041.189-.104.249-.192.06-.087.09-.19.09-.306 0-.144-.042-.261-.126-.355-.085-.093-.19-.152-.319-.175zM12.895 25.381c-.097-.092-.22-.152-.368-.185-.11-.026-.328-.038-.652-.038h-.356v2.606h.593c.222 0 .383-.014.48-.038.129-.033.236-.087.32-.164.085-.077.155-.203.209-.379.054-.178.08-.418.08-.722s-.026-.537-.08-.701c-.055-.161-.131-.289-.226-.379zM17.091.539h-16.491c-.276 0-.5.224-.5.5v29.921c0 .277.224.501.5.501h22.8c.276 0 .5-.224.5-.501v-23.612l-6.809-6.809zm-7.149 25.773c-.088.165-.199.294-.334.389-.135.093-.273.156-.413.187-.189.037-.465.056-.824.056h-.518v1.484h-.794v-3.935h1.274c.483 0 .799.02.945.059.225.059.415.188.566.386.152.196.228.452.228.767.001.238-.043.443-.13.607zm3.958 1.023c-.099.286-.238.519-.421.694-.138.135-.323.239-.559.314-.175.056-.409.083-.703.083h-1.494v-3.935h1.451c.327 0 .578.025.749.075.231.068.428.189.594.362.164.174.289.386.376.639.085.251.129.56.129.929 0 .325-.041.604-.122.839zm3.499-2.177h-1.904v.931h1.644v.665h-1.644v1.673h-.794v-3.935h2.698v.666zm5.501-3.006h-21.8v-20.613h15.555v6.06h6.245v14.553z"/></svg></span>' +
                                element.filename +
                                '</div>' +
                                '<input id="objectId" type="hidden" value=' + element.objectId + '>' +
                                '<input id="documentID" type="hidden" value=' + element.documentID + '>' +
                                '<div class="downloadList-actions">' +
                                '<a href="' + ACC.config.encodedContextPath + '/govtServices/pdf/' + element.objectId + "/" + element.documentID+'" class="link link_nowrap" ' + 
                                	"download='"+ element.fullFileName +  "'>" +
                                '<span class="iconElement iconElement_cloud"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
                                '    <path fill="#5CC83B" d="M3.769,12.936c-0.154,0-1.459-0.028-2.381-0.936C0.799,11.421,0.5,10.627,0.5,9.64\n' +
                                '        c0-1.043,0.45-2.001,1.234-2.629c0.568-0.455,1.26-0.683,1.964-0.664C4.094,3.589,6.333,1.5,9,1.5c1.962,0,3.78,1.164,4.713,2.99\n' +
                                '        c0.922,0.045,1.791,0.423,2.479,1.084c0.832,0.8,1.31,1.942,1.31,3.134c0,2.946-2.33,4.05-3.562,4.222l-1.128,0.005\n' +
                                '        c-0.276,0-0.5-0.224-0.5-0.5s0.224-0.5,0.5-0.5h1.059c0.039-0.011,2.631-0.425,2.631-3.227c0-0.921-0.365-1.801-1.003-2.414\n' +
                                '        c-0.572-0.551-1.312-0.835-2.082-0.807c-0.204,0.013-0.394-0.11-0.477-0.297C12.218,3.556,10.672,2.5,9,2.5\n' +
                                '        c-2.305,0-4.219,1.947-4.356,4.432c-0.008,0.146-0.08,0.282-0.197,0.371C4.33,7.392,4.178,7.425,4.036,7.392\n' +
                                '        C3.443,7.259,2.847,7.401,2.36,7.791C1.813,8.228,1.5,8.902,1.5,9.64c0,0.706,0.196,1.259,0.584,1.643\n' +
                                '        c0.66,0.656,1.657,0.646,1.693,0.653h1.392c0.276,0,0.5,0.224,0.5,0.5s-0.224,0.5-0.5,0.5H3.783\n' +
                                '        C3.781,12.936,3.776,12.936,3.769,12.936z M9.849,16.609V8.957c0-0.276-0.224-0.5-0.5-0.5s-0.5,0.224-0.5,0.5v7.652\n' +
                                '        c0,0.276,0.224,0.5,0.5,0.5S9.849,16.886,9.849,16.609z M9.701,16.963l2.088-2.086c0.195-0.195,0.195-0.512,0-0.707\n' +
                                '        s-0.512-0.195-0.707,0l-1.734,1.732L7.615,14.17c-0.195-0.195-0.512-0.195-0.707,0s-0.195,0.512,0,0.707l2.087,2.086\n' +
                                '        c0.098,0.098,0.226,0.146,0.354,0.146S9.604,17.061,9.701,16.963z"></path>\n' +
                                '</svg></span>' +
                                'Download' +
                                '</a>' +
                                '</div>' +
                                '</li>'
                            ;
                        }
                    });
                    $('#govtServicesAttachments').html('<ul class="downloadList downloadList_maxHeight">' + filesHtml + '</ul>');

                    //update messages list
                    //list to be updates
                    var messagesListDivContent = $('messagesListUL');
                    messagesListDivContent.empty();
                    //template messages
                    var messagesGovtServicesTemplate = document.getElementById("messagesSagiaGovtServices-template");
                    var messagesGovtServicesTemplateHtml = messagesGovtServicesTemplate.innerHTML;
                    
                    //messages from response
                    var messagesList = service.govtServicesToTextNav;
                    var	messagesListHtml = '';
                    for(var key in messagesList) {
                    	messagesListHtml += messagesGovtServicesTemplateHtml
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
                    //update list with values from UI
                    messagesListDivContent.html(messagesListHtml);

                    $('#serviceUrl').html(
                        '<button class="btn btn_slim" ' +
                        'onclick="window.location.href=\'' +
                        ACC.config.encodedContextPath + '/services/government/' + category + "/" + serviceUrl + "/create" + '?serviceName=' + encodeURIComponent(serviceName) + '&amp;srID=' + service.srID +'\'\">' +
                        getI18nText("create.govtServices") +
                            '</button>'
                    );
                }
            }
        });
    };

    //when an element is no longer selected, remove historyList-item_current class
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
                updateStatus(e);
            });

    //helper function to remove all classes except one given class
    jQuery.fn.removeClassExcept = function (val) {
        return this.each(function () {
            $(this).removeClass().addClass(val);
        });
    };

});

$('#createGovtServiceButton').click(function () {
    window.location.href = $('input#createGovtServUrl').val();
});

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
