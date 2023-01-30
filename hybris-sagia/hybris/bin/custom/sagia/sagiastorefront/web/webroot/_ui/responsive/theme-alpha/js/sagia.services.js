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
                                '<span class="iconElement iconElement_pdf">'+
                                '<svg id="PDF-Docments" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="59.572" height="78.222" viewBox="0 0 59.572 78.222">'+
                                    '<defs>'+
                                    ' <clipPath id="clip-path">'+
                                    '  <rect id="Rectangle_1212" data-name="Rectangle 1212" width="59.572" height="78.222" fill="none" stroke="#00a6be" stroke-width="2"/>'+
                                    '</clipPath>'+
                                    ' </defs>'+
                                    '<g id="Group_1696" data-name="Group 1696" clip-path="url(#clip-path)">'+
                                    '  <path id="Path_2326" data-name="Path 2326" d="M59.65,16.758V74.667a3.371,3.371,0,0,1-3.089,3.6H3.589A3.371,3.371,0,0,1,.5,74.667V4.127A3.371,3.371,0,0,1,3.589.531H44.222" transform="translate(-0.296 -0.279)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'+
                                    ' <path id="Path_2327" data-name="Path 2327" d="M123.109,16.855,108.186,1.128V17.008Z" transform="translate(-64.06 -0.592)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'+
                                    ' <line id="Line_552" data-name="Line 552" x2="59.15" transform="translate(0.204 54.875)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'+
                                    ' <path id="Path_2328" data-name="Path 2328" d="M52.169,31.25s-2.985-5.777,0-8.08a3.489,3.489,0,0,1,2.273,4.323s-5.4,23.17-11.333,29.17c0,0-2.747,1.53-3.788-.53a2.97,2.97,0,0,1,1.705-3.273s16.349-9.893,26.52-8.964c0,0,2.155,1.511.282,3.313,0,0-1.977.429-5.363-1.312a6.206,6.206,0,0,1-.621-.313C60.991,45.019,58.532,43.632,52.169,31.25Z" transform="translate(-23.258 -12.171)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'+
                                    ' <path id="Path_2329" data-name="Path 2329" d="M33.02,124.42a20.365,20.365,0,0,1,3.537-.289,5.675,5.675,0,0,1,4,1.246,4.129,4.129,0,0,1,1.25,3.137,4.545,4.545,0,0,1-1.1,3.2,5.752,5.752,0,0,1-4.363,1.646,6.033,6.033,0,0,1-1.483-.133v6.006H33.02Zm1.843,7.229a5.9,5.9,0,0,0,1.525.156c2.224,0,3.579-1.135,3.579-3.2,0-1.98-1.334-2.936-3.367-2.936a7.368,7.368,0,0,0-1.737.155Z" transform="translate(-19.552 -65.204)" fill="#00a6be"/>'+
                                    ' <path id="Path_2330" data-name="Path 2330" d="M60.643,124.442a25.216,25.216,0,0,1,3.918-.311c2.647,0,4.532.645,5.782,1.868a7.087,7.087,0,0,1,2.012,5.383,8.159,8.159,0,0,1-2.054,5.828c-1.335,1.4-3.537,2.158-6.311,2.158a28.366,28.366,0,0,1-3.347-.178ZM62.486,137.7a11.111,11.111,0,0,0,1.864.111c3.94,0,6.078-2.313,6.078-6.362.021-3.537-1.885-5.783-5.782-5.783a9.994,9.994,0,0,0-2.16.2Z" transform="translate(-35.909 -65.204)" fill="#00a6be"/>'+
                                    ' <path id="Path_2331" data-name="Path 2331" d="M95.224,124.365h7.688v1.624H97.067v4.983h5.4v1.6h-5.4v6.785H95.224Z" transform="translate(-56.385 -65.327)" fill="#00a6be"/>'+
                                    '</g>'+
                                '</svg>'+
                                '</span>' +
                                element.filename +
                                '</div>' +
                                '<input id="objectId" type="hidden" value=' + element.objectId + '>' +
                                '<input id="documentID" type="hidden" value=' + element.documentID + '>' +
                                '<div class="downloadList-actions">' +
                                '<a href="' + ACC.config.encodedContextPath + '/govtServices/pdf/' + element.objectId + "/" + element.documentID+'" class="link link_nowrap" ' + 
                                	"download='"+ element.fullFileName +  "'>" +
                                '<span class="iconElement iconElement_cloud">'+
                                '<svg xmlns="http://www.w3.org/2000/svg" width="23.265" height="24" viewBox="0 0 23.265 24">'+
                                    ' <g id="Download" transform="translate(-465.44 -1494.127)">'+
                                    ' <path id="Path_1894" data-name="Path 1894" d="M487.935,1509.231a.769.769,0,0,0-.77.77v10.064H466.98V1510a.77.77,0,0,0-1.54,0v11.6H488.7V1510A.769.769,0,0,0,487.935,1509.231Z" transform="translate(0 -3.477)" fill="#00a6be"/>'+
                                    ' <path id="Path_1895" data-name="Path 1895" d="M473.908,1504.419l4.042,4.043V1494.9a.77.77,0,0,1,1.54,0v13.562l4.039-4.04a.77.77,0,1,1,1.088,1.089l-5.9,5.9-5.9-5.9a.77.77,0,0,1,1.089-1.089Z" transform="translate(-1.647 0)" fill="#00a6be"/>'+
                                    ' </g>'+
                                '</svg>'+
                                '</span>' +
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

$('#createIgniteServiceButton').click(function () {
    window.location.href = $('input#createIgniteServUrl').val();
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
