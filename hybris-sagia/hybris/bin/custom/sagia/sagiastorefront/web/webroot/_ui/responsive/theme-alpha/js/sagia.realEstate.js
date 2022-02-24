//updates the status in the detail section based on the selected service instance
function updateStatusClass(realEstate) {
    $('.statusIndicator').html(getI18nText("realEstate.status") + '<span>' + realEstate.status + '</span>');
    $('.statusIndicator').removeClassExcept("statusIndicator");
    switch (realEstate.status != null && realEstate.status.length > 0) {
        case realEstate.status === getI18nText("realEstate.validation.status.inProcess"):
            $('.statusIndicator').addClass('statusIndicator_process');
            $('#realEstateResubmitBtn').attr('hidden', true);
            break;
        case realEstate.status === getI18nText("realEstate.validation.status.rejected"):
            $('.statusIndicator').addClass('statusIndicator_rejected');
            $('#realEstateResubmitBtn').attr('hidden', false);
            break;
        case realEstate.status === getI18nText("realEstate.validation.status.completed"):
            $('.statusIndicator').addClass('statusIndicator_completed');
            $('#realEstateResubmitBtn').attr('hidden', true);
            break;
        default:
            $('.statusIndicator').addClass('statusIndicator_process');
            $('#realEstateResubmitBtn').attr('hidden', true);
    }
}

var getAttachmentList = function (requestType, realEstateType, files) {
    window['uploadedFiles'] = files;
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + "/real-estate/attachmentset/" + requestType + "/" + realEstateType,
        // data: "requestType=" + requestType, // appears as $_GET['id'] @ your backend side
        success: function (data) {
            var attachmentSet = JSON.parse(data),
                filesHtml = '';
            if (attachmentSet != null && attachmentSet.length > 0) {
                $('#realEstateDocuments').removeAttr('hidden');
                attachmentSet.forEach(function (element, index) {
                    var file = findFileData(element.attachmentName, window['uploadedFiles']);
                    if (file) {
                        filesHtml +=
                            '<div class="col-lg-6 col-md-12" data-real-index="' + index + '">' +
                            '<div class="formInputFile">' +
                            '<div class="form-group ">' +
                            '<input id="fileId_[' + index + ']" name="files[' + index + ']" class="form-control js-inputFile" data-attachment-name="' + element.attachmentName + '" value="" type="file" accept="image/jpeg,application/pdf">' +
                            '<input id="fileTextId_' + index + '" name="fileText[' + index + ']" placeholder-mock="' + file.name + '" class="form-control uploadServiceFile" value="" placeholder="" readonly="" tabindex="-1" type="text">' +
                            '<input id="fileData' + index + '" name="fileData[' + index + ']" class="form-control js-mock-input" style="display:none;" value="' + file.id + '" readonly="" tabindex="-1" type="text">' +
                            '<label class="control-label">' + element.attachmentName + '</label>' +
                            '<input id="dockey_' + index + '" name="dockeyID[' + index + ']" type="hidden" value="dockeyID">' +
                            '<div class="form-icon form-icon_browse">' +
                            '<svg width="18" height="18" viewBox="0 0 18 18" xmlns="http://www.w3.org/2000/svg"><g fill="none" stroke="currentColor"><path d="M16.762 13.39v1.348c0 1.117-.906 2.023-2.023 2.023h-11.466c-1.117 0-2.023-.906-2.023-2.023v-1.348"/><path d="M9.006 1.25v12.063"/><path d="M4.07 6.186l4.936-4.936 4.935 4.936"/></g></svg>' +
                            '</div>' +
                            '<div class="form-icon form-icon_reset js-inputFile-reset">' +
                            '\n' +
                            '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
                            '\t<path d="M9,17.5c-4.687,0-8.5-3.813-8.5-8.5c0-4.687,3.813-8.5,8.5-8.5c4.687,0,8.5,3.813,8.5,8.5C17.5,13.687,13.687,17.5,9,17.5z M9,1.5C4.864,1.5,1.5,4.864,1.5,9s3.364,7.5,7.5,7.5s7.5-3.364,7.5-7.5S13.136,1.5,9,1.5z M11.813,12.812c-0.268,0-0.519-0.104-0.707-0.292l-1.949-1.949l-1.95,1.949c-0.378,0.377-1.037,0.377-1.414,0c-0.189-0.188-0.293-0.439-0.293-0.707s0.104-0.519,0.293-0.707l1.949-1.949l-1.95-1.95c-0.39-0.39-0.39-1.024,0-1.414c0.377-0.377,1.037-0.377,1.414,0l1.95,1.95l1.949-1.95c0.377-0.377,1.039-0.377,1.414,0c0.389,0.39,0.389,1.023,0,1.413l-1.95,1.95l1.95,1.949c0.39,0.392,0.389,1.025,0,1.414C12.332,12.709,12.081,12.812,11.813,12.812z"/>\n' +
                            '</svg>\n' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                    } else {
                        filesHtml +=
                            '<div class="col-lg-6 col-md-12">' +
                            '<div class="formInputFile">' +
                            '<div class="form-group ">' +
                            '<input id="fileId_[' + index + ']" name="files[' + index + ']" class="form-control js-inputFile" data-attachment-name="' + element.attachmentName + '" value="" type="file" accept="image/jpeg,application/pdf">' +
                            '<input id="fileTextId_' + index + '" name="fileText[' + index + ']" class="form-control uploadServiceFile" value="" placeholder="" readonly="" tabindex="-1" type="text">' +
                            '<label class="control-label">' + element.attachmentName + '</label>' +
                            '<input id="dockey_' + index + '" name="dockeyID[' + index + ']" type="hidden" value="dockeyID">' +
                            '<div class="form-icon form-icon_browse">' +
                            '<svg width="18" height="18" viewBox="0 0 18 18" xmlns="http://www.w3.org/2000/svg"><g fill="none" stroke="currentColor"><path d="M16.762 13.39v1.348c0 1.117-.906 2.023-2.023 2.023h-11.466c-1.117 0-2.023-.906-2.023-2.023v-1.348"/><path d="M9.006 1.25v12.063"/><path d="M4.07 6.186l4.936-4.936 4.935 4.936"/></g></svg>' +
                            '</div>' +
                            '<div class="form-icon form-icon_reset js-inputFile-reset">' +
                            '\n' +
                            '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
                            '\t<path d="M9,17.5c-4.687,0-8.5-3.813-8.5-8.5c0-4.687,3.813-8.5,8.5-8.5c4.687,0,8.5,3.813,8.5,8.5C17.5,13.687,13.687,17.5,9,17.5z M9,1.5C4.864,1.5,1.5,4.864,1.5,9s3.364,7.5,7.5,7.5s7.5-3.364,7.5-7.5S13.136,1.5,9,1.5z M11.813,12.812c-0.268,0-0.519-0.104-0.707-0.292l-1.949-1.949l-1.95,1.949c-0.378,0.377-1.037,0.377-1.414,0c-0.189-0.188-0.293-0.439-0.293-0.707s0.104-0.519,0.293-0.707l1.949-1.949l-1.95-1.95c-0.39-0.39-0.39-1.024,0-1.414c0.377-0.377,1.037-0.377,1.414,0l1.95,1.95l1.949-1.95c0.377-0.377,1.039-0.377,1.414,0c0.389,0.39,0.389,1.023,0,1.413l-1.95,1.95l1.95,1.949c0.39,0.392,0.389,1.025,0,1.414C12.332,12.709,12.081,12.812,11.813,12.812z"/>\n' +
                            '</svg>\n' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                    }

                    function findFileData(name, files) {
                        var result;
                        if (files && files.length) {
                            files.forEach(function(file) {
                                if (file.attachmentName === name) {
                                    result = file;
                                }
                            })
                        }
                        return result;
                    }
                });

                $("#realEstateupload_placeholder").html(filesHtml);

                $('[placeholder-mock]').each(
                    function() {
                        var element = $(this);
                        element.attr('placeholder', element.attr('placeholder-mock'));
                        var rootElement = element.closest('.formInputFile');
                        rootElement.addClass('active');
                    }
                );
                window['uploadedFiles'] = "";
            } else {
                $('#realEstateDocuments').attr('hidden','hidden');
            }

        }
    });
};

function showErrorModal() {
    var errorModal = $('#errorResponseModal');
    errorModal.find('.modal-description').text(decodeURIComponent(getI18nText("realEstate.validation.entityStatus.error")) + currentEntityStatus);
    errorModal.modal('show');
}

$(function () {
    if($('#isEntityStatusValid').val() === 'false'){
        showErrorModal();
    }

    $('#realEstateCreateBtn, #realEstateResubmitBtn').click(function(e){
        e.preventDefault();
        var entityStatus = $(this).data("entityStatus");
        if(entityStatus === undefined || (entityStatus &&
            ((entityStatus.trim().toUpperCase().includes("EXPIRED")) ||
                (entityStatus.trim().toUpperCase().includes("ACTIVE")) ||
                (entityStatus.trim().toUpperCase().includes("REVOKED")))
        )){
            window.location = this.href;
        } else {
            showErrorModal();
        }
    });

    //enable real estate type and show real estate details if the request type has a value and the real estate
    //has a value; this can only happen after submitting a page that failed the validation process
        $('.js-cancel-create-realEstate').on("click",function () {
            window.location.href = ACC.config.encodedContextPath + "/real-estate";
        });

        var requestType = $(".request-type").val();
        var realEstateType = $(".realEstate-type").val();
        var mojVerified = $('#isMojVerified').val();
        if (requestType && realEstateType) {
            $('.realEstate-type').removeAttr('disabled');
            $("#realEstateDetails").attr("hidden", false);
            $('#identityNumber').removeAttr('disabled');
            $('#deedNumber').removeAttr('disabled');
            //$('#realEstateDocuments').removeAttr('hidden');
            if(requestType === "Sell"){
            	$("#identityType").val("3").change();
        		$("#identityType").attr('disabled', true);
        		//$("#deedNumber").attr('disabled', true);
        		$("#deedNumberDiv").hide();
        		$('#realEstateDocuments').attr('hidden','hidden');
            }else{
            	$("#identityType").attr('disabled', false);
        		$("#deedNumberDiv").show();
            	//$('#realEstateDocuments').removeAttr('hidden');
            }
            if(mojVerified === "TRUE"){
            	$("#mojRegion").attr("hidden", false);
            	$("#region").attr("hidden", true);
            	
            	$("#mojCity").attr("hidden", false);
            	$("#city").attr("hidden", true);
            	
            	$("#mojDeedDate").attr("hidden", false);
            	$("#deedDate").attr("hidden", true);
            	
            	//$("#mojIssuerCourtName").attr("hidden", false);
            	$('#realEstateDocuments').attr('hidden','hidden');
            	
            }else{
            	$("#mojRegion").attr("hidden", true);
            	$("#region").attr("hidden", false);
            	
            	$("#mojCity").attr("hidden", true);
            	$("#city").attr("hidden", false);
            	
            	$("#mojDeedDate").attr("hidden", true);
            	$("#deedDate").attr("hidden", false);
            	
            	//$("#mojIssuerCourtName").attr("hidden", true);
            	$('#realEstateDocuments').removeAttr('hidden');
            }
            
        }

        hideFormFields();

        $('#createRealEstateForm').validate({
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
                if (element.closest('.form-group').find('.help-block').length>0) {
                    error.appendTo(element.closest('.form-group').find('.help-block'));
                } else if (element.hasClass('js-select2-oneColumn')){
                    error.appendTo(element.closest('.formSelectBox').find('.help-block'));

                } else {
                    error.appendTo(element.closest('.formInputBox').find('.help-block'));
                }
            },
            rules: {
                requestType: "required",
                purchaseType : "required",
                termsAndConditionsChecked :"required"
            },
            messages: {
                requestType: {
                    required: getI18nText("realEstate.validation.requestType")
                },
                purchaseType : {
                    required: getI18nText("realEstate.validation.purchaseType")
                },
                termsAndConditionsChecked : {
                    required: getI18nText("terms.accept.text")
                }
            }
    });

    //after selecting a request type, the real estate must be enabled
    $(".request-type").change(function () {

        $('.js-submit-create-realEstate').show();

        $.ajax(ACC.config.encodedContextPath + "/real-estate/checkAllowedRequestType", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {

                if($(".request-type").val() === "Buy" && data.allowedToBuy === false) {

                    $('#errorResponseModal').find('.modal-description').text(getI18nText("text.realEstate.BuyNotAllowed"));
                    $('#errorResponseModal').modal('show');

                    $('.js-submit-create-realEstate').hide();
                }

                if($(".request-type").val() === "Sell" && data.allowedToSell === false) {

                    $('#errorResponseModal').find('.modal-description').text(getI18nText("text.realEstate.SellNotAllowed"));
                    $('#errorResponseModal').modal('show');

                    $('.js-submit-create-realEstate').hide();

                }


            },
            error: function (data) {
               var message = data.responseJSON.sagiaExceptionResponse.message;
                $('#errorResponseModal').find('.modal-description').text(message);
                $('#errorResponseModal').modal('show');

                $('.js-submit-create-realEstate').hide();
            },
            async: false
        });

        if ($(this).val() != '0') {
            $('.realEstate-type').removeAttr('disabled');
        }
        
        $('#realEstateAddInputDetails').attr('hidden','hidden');
        $('#realEstateDetails').attr('hidden','hidden');
    	$("#realEstateDocuments").attr('hidden','hidden');

        if ($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }
    });

    $(".formInputBox").change(function (){
        $(this).find('.help-block').remove();
    });

    $(".js-inputFile").change(function (){
        $(this).find('.help-block').remove();
    });

    //based on the real estate type selection, the form is dynamically adjusted
    /*$(".realEstate-type").change(function (event, data) {
        if (!data ) {
            $('#regionForm').trigger('change');
            data = {};
        }

        $("#realEstateDetails").attr("hidden", false);
        $("#realEstateTypeTitle").html($(".realEstate-type option:selected").text());

        if ($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }

        hideFormFields();
        getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
    });*/

    //when changing the city, save the value in selectedCity attribute, to repopulate it after submit
    //if the page fails the validation process
    $('#selectCity').change(function () {
        $('#selectedCity').val($('#selectCity').val());
    });

    //when selecting a value in the region form, fill the city drop-down with the according relevant values
    $('#regionForm').change(function (e, data) {
        var regionId = $('#regionForm').val();
        if (regionId) {
            $.ajax({
                type: 'GET',
                url: ACC.config.encodedContextPath + "/real-estate/getCity/" + regionId,
                success: function (data) {
                    var selectCity = $('#selectCity');
                    var option = "";
                    selectCity.empty();
                    var optionSelected = $('#selectedCity').val();
                    var selectedCityDraft = selectCity.attr('selectedValue');
                    for (var i = 0; i < data.length; i++) {
                        if (optionSelected == data[i].cityCode) {
                            option = option + "<option value='" + data[i].cityCode + "' selected>" + data[i].cityDesc + "</option>";
                        } else if (selectedCityDraft && selectedCityDraft === data[i].cityCode) {
                            option = option + "<option value='" + data[i].cityCode + "' selected>" + data[i].cityDesc + "</option>";
                            selectCity.val(selectedCityDraft);
                            selectCity.removeAttr('selectedValue');
                        } else {
                            option = option + "<option value='" + data[i].cityCode + "'>" + data[i].cityDesc + "</option>";
                        }
                    }
                    selectCity.append($(option));
                    selectCity.trigger("change");
                },
                error: function () {
                    $('#errorResponseModal').find('.modal-description').text(getI18nText("general.error"));
                    $('#errorResponseModal').modal('show');
                }

            });
        }
    });

    $('#regionForm').change();

    //update the details section of the selected service with data received from CRM
    $('.realEstateItemId').click(function () {
        var id = $(this).attr("id");
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: ACC.config.encodedContextPath + "/real-estate/" + id,
            data: "id=" + id, // appears as $_GET['id'] @ your backend side
            success: function (data) {
                var realEstate = JSON.parse(data);

                var plotNo = realEstate.plotNo;
                if(realEstate.plotNo2 !== null) plotNo += "|"+realEstate.plotNo2;
                if(realEstate.plotNo3 !== null) plotNo += "|"+realEstate.plotNo3;
                if(realEstate.plotNo4 !== null) plotNo += "|"+realEstate.plotNo4;
                if(realEstate.plotNo5 !== null) plotNo += "|"+realEstate.plotNo5;

                $('#reiObjectId').text(" "+realEstate.objectId);
                $("#reiRequestType").text(realEstate.requestType);
                $("#reiPurchaseType").text(realEstate.purchaseType);
                $("#reiPlotNo").text(plotNo);
                $("#reiPlotArea").text(realEstate.plotArea);
                $("#reiDeedNo").text(realEstate.deedNo);
                if(realEstate.sagiaPurchaseDate != null){
                    $("#reiPurchaseDate").text(realEstate.sagiaPurchaseDate.dateFormatted);
                }
                $("#reiOutsideMakkah").text(realEstate.outsideMakkah);
                $("#reiApprovedIndustrial").text(realEstate.approvedIndustrial);
                $("#reiProjectValue").text(realEstate.projectValue);
                $("#reiPrice").text(realEstate.price);
                $("#reiRegion").text(realEstate.region);
                $("#reiCity").text(realEstate.city);
                $("#reiHousingType").text(realEstate.housingType);
                $("#reiDistrict").text(realEstate.district);
                $("#reiUnitNo").text(realEstate.unitNo);
                $("#reiBlockNo").text(realEstate.blockNo);
                $("#reiRemarks").text(realEstate.remarks);
                $("#reiThirtyMore").text(realEstate.thirtyMore);
                $("#realEstateStatus").text(realEstate.status);
                $('#realEstateDocuments').attr('hidden','hidden');
                if (realEstate.attachmentsSet != null && realEstate.attachmentsSet.length > 0) {
                    var filesHtml = '';
                    $('#realEstateDocuments').removeAttr('hidden');
                    realEstate.attachmentsSet.forEach(function (element) {
                        if (element.filename.length > 0) {
                            filesHtml +=
                                '<li class="downloadList-item">' +
                                '<div id="realEstateAttachment_' + element.objectId + '" class="downloadList-description">' +
                                '<span class="iconElement iconElement_pdf">'+
                                '<svg id="PDF-Docments" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="59.572" height="78.222" viewBox="0 0 59.572 78.222">' +
                                '    <defs>' +
                                '    <clipPath id="clip-path">' +
                                '        <rect id="Rectangle_1212" data-name="Rectangle 1212" width="59.572" height="78.222" fill="none" stroke="#00a6be" stroke-width="2"/>' +
                                '    </clipPath>' +
                                '    </defs>' +
                                '    <g id="Group_1696" data-name="Group 1696" clip-path="url(#clip-path)">' +
                                '    <path id="Path_2326" data-name="Path 2326" d="M59.65,16.758V74.667a3.371,3.371,0,0,1-3.089,3.6H3.589A3.371,3.371,0,0,1,.5,74.667V4.127A3.371,3.371,0,0,1,3.589.531H44.222" transform="translate(-0.296 -0.279)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>' +
                                '    <path id="Path_2327" data-name="Path 2327" d="M123.109,16.855,108.186,1.128V17.008Z" transform="translate(-64.06 -0.592)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>' +
                                '    <line id="Line_552" data-name="Line 552" x2="59.15" transform="translate(0.204 54.875)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>' +
                                '    <path id="Path_2328" data-name="Path 2328" d="M52.169,31.25s-2.985-5.777,0-8.08a3.489,3.489,0,0,1,2.273,4.323s-5.4,23.17-11.333,29.17c0,0-2.747,1.53-3.788-.53a2.97,2.97,0,0,1,1.705-3.273s16.349-9.893,26.52-8.964c0,0,2.155,1.511.282,3.313,0,0-1.977.429-5.363-1.312a6.206,6.206,0,0,1-.621-.313C60.991,45.019,58.532,43.632,52.169,31.25Z" transform="translate(-23.258 -12.171)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>' +
                                '    <path id="Path_2329" data-name="Path 2329" d="M33.02,124.42a20.365,20.365,0,0,1,3.537-.289,5.675,5.675,0,0,1,4,1.246,4.129,4.129,0,0,1,1.25,3.137,4.545,4.545,0,0,1-1.1,3.2,5.752,5.752,0,0,1-4.363,1.646,6.033,6.033,0,0,1-1.483-.133v6.006H33.02Zm1.843,7.229a5.9,5.9,0,0,0,1.525.156c2.224,0,3.579-1.135,3.579-3.2,0-1.98-1.334-2.936-3.367-2.936a7.368,7.368,0,0,0-1.737.155Z" transform="translate(-19.552 -65.204)" fill="#00a6be"/>' +
                                '    <path id="Path_2330" data-name="Path 2330" d="M60.643,124.442a25.216,25.216,0,0,1,3.918-.311c2.647,0,4.532.645,5.782,1.868a7.087,7.087,0,0,1,2.012,5.383,8.159,8.159,0,0,1-2.054,5.828c-1.335,1.4-3.537,2.158-6.311,2.158a28.366,28.366,0,0,1-3.347-.178ZM62.486,137.7a11.111,11.111,0,0,0,1.864.111c3.94,0,6.078-2.313,6.078-6.362.021-3.537-1.885-5.783-5.782-5.783a9.994,9.994,0,0,0-2.16.2Z" transform="translate(-35.909 -65.204)" fill="#00a6be"/>' +
                                '    <path id="Path_2331" data-name="Path 2331" d="M95.224,124.365h7.688v1.624H97.067v4.983h5.4v1.6h-5.4v6.785H95.224Z" transform="translate(-56.385 -65.327)" fill="#00a6be"/>' +
                                '    </g>' +
                                '</svg>' +
                                '</span>'+
                                element.filename +
                                '</div>' +
                                '<input id="objectId" type="hidden" value='+element.objectId+'>'+
                                '<input id="docGuid" type="hidden" value='+element.docGuid+'>'+
                                '<div class="downloadList-actions">' +
                                '<a href="' + ACC.config.encodedContextPath + '/real-estate/pdf/' + element.objectId + "/" + element.docGuid +'" class="link link_nowrap" download="'+ element.fullFileName 
                                + '"> '
                                + '<span class="iconElement iconElement_cloud">'
                                +'<svg xmlns="http://www.w3.org/2000/svg" width="23.265" height="24" viewBox="0 0 23.265 24">'
                                +'    <g id="Download" transform="translate(-465.44 -1494.127)">'
                                +'   <path id="Path_1894" data-name="Path 1894" d="M487.935,1509.231a.769.769,0,0,0-.77.77v10.064H466.98V1510a.77.77,0,0,0-1.54,0v11.6H488.7V1510A.769.769,0,0,0,487.935,1509.231Z" transform="translate(0 -3.477)" fill="#00a6be"/>'
                                +'    <path id="Path_1895" data-name="Path 1895" d="M473.908,1504.419l4.042,4.043V1494.9a.77.77,0,0,1,1.54,0v13.562l4.039-4.04a.77.77,0,1,1,1.088,1.089l-5.9,5.9-5.9-5.9a.77.77,0,0,1,1.089-1.089Z" transform="translate(-1.647 0)" fill="#00a6be"/>'
                                +'    </g>'
                                +'</svg>'+
                                '</span>' +
                                getI18nText("realEstate.download") +
                                '</a>' +
                                '</div>' +
                                '</li>';
                        }
                    });
                    $("#realEstateAttachmentList").html(filesHtml);
                    $("#realEstateResubmitBtn").attr("href", ACC.config.encodedContextPath + "/real-estate/resubmit/" + realEstate.objectId);
                }
                updateStatusClass(realEstate);
            }
        });
    });

    //display only relevant form fields for the request type & real estate type combination
    function hideFormFields() {
        $("select option:selected").each(function () {
            if ($(".realEstate-type").val() == "10") {
                $('#projectValue').hide();
                $('#outsideMakkahBox').hide();
                $('#thirtyMoreBox').hide();
                $('#approvedIndustrial').hide();
                $('#housingTypeBox').show();
            } else if ($(".realEstate-type").val() == "20") {
                $('#projectValue').hide();
                $('#outsideMakkahBox').hide();
                $('#thirtyMoreBox').hide();
                $('#housingTypeBox').hide();
                $('#approvedIndustrial').show();
            } else if ($(".realEstate-type").val() == "30") {
                $('#outsideMakkahBox').hide();
                $('#thirtyMoreBox').hide();
                $('#housingTypeBox').hide();
                $('#approvedIndustrial').hide();
                $('#projectValue').show();
            } else if ($(".realEstate-type").val() == "40") {
                $('#projectValue').hide();
                $('#outsideMakkahBox').hide();
                $('#thirtyMoreBox').hide();
                $('#approvedIndustrial').hide();
                $('#housingTypeBox').hide();
            } else if ($(".realEstate-type").val() == "50") {
                $('#approvedIndustrial').hide();
                $('#housingTypeBox').hide();
                $('#outsideMakkahBox').show();
                $('#thirtyMoreBox').show();
                $('#projectValue').show();
            }
        });
    }

    //add another PlotNo input field when pressing Add Plot No
    $('#addPlotNo').click(function(){
        if(!$('#plotNo2').is(":visible")){ $('#plotNo2').attr("hidden", false); $('#plotNo2').show(); return;}
        if(!$('#plotNo3').is(":visible")){ $('#plotNo3').attr("hidden", false); $('#plotNo3').show(); return;}
        if(!$('#plotNo4').is(":visible")){ $('#plotNo4').attr("hidden", false); $('#plotNo4').show(); return;}
        if(!$('#plotNo5').is(":visible")){ $('#plotNo5').attr("hidden", false); $('#plotNo5').show(); return;}
    });

    //when an element is no longer selected, remove historyList-item_current class
    $('ul#history-list').on('click', 'li', function(e) {
        var lis = document.getElementById("history-list").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";
    });

    //helper function to remove all classes except one given class
    jQuery.fn.removeClassExcept = function (val) {
        return this.each(function () {
            $(this).removeClass().addClass(val);
        });
    };

    //  add/remove has-error class based on the selection of the consent box
    $(document).on("click", "#consentBox", function() {
        var isChecked = $('#consentBox').is(':checked');
        if(isChecked){
            $('#consentBox').parent().parent().removeClass('has-error');
        } else {
            $('#consentBox').parent().parent().addClass('has-error');
        }
    });
    
    //after POST successfull submit, the feedback is requested

     if (Boolean($('#requestFeedback').val())) {
        showAndSendFeedback();
        } else {
//continue
       }

  // get the new created real estate
    function showAndSendFeedback() {
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: ACC.config.encodedContextPath + "/real-estate/latest/",
        success: function (data) {
        //set serviceId for feedback to be entity id
          $('#serviceId').val(data.objectId);
        }
   });
    //open the modal 
    $('#requestSubmittedComment').modal().open;
    //close the modal
        $('#requestSubmittedComment').on('hide.bs.modal', function () {
            window.location.href = ACC.config.encodedContextPath + "/real-estate";
        });
    }

    $(".js-load-draft").click(function(){
        loadDraft();
    });
    function loadDraft() {
        $.ajax(
            {
                type: 'get',
                url: ACC.config.contextPath + '/draft?formId=' + serviceCategoryTypeCode
            }
        )
            .done(function (response) {
                if (response && response.parameters) {
                    response.parameters.forEach(
                        function(parameter) {
                            if (parameter.name !== "CSRFToken") {
                                var domElement = $("[name='" + parameter.name + "']");

                                if (domElement.hasClass('realEstate-type')) {
                                    domElement.attr("selectedValue", parameter.value);
                                    domElement.val(parameter.value).trigger('change', [{files: response.files, draft: true}])
                                }
                                else {
                                    domElement.attr("selectedValue", parameter.value);
                                    domElement.val(parameter.value).trigger('change');
                                    domElement.focus();
                                }
                            }
                        }
                    );

                    if($('[name=plotNo2]').val()){ var $element = $('#plotNo2'); $element.attr("hidden", false); $element.show();}
                    if($('[name=plotNo3]').val()){ $('#plotNo3').attr("hidden", false); $('#plotNo3').show();}
                    if($('[name=plotNo4]').val()){ $('#plotNo4').attr("hidden", false); $('#plotNo4').show(); return;}
                    if($('[name=plotNo5]').val()){ $('#plotNo5').attr("hidden", false); $('#plotNo5').show(); return;}
                }
            })
            .fail(function (error) {
            })
    }

    function addRegionCityNamesToForm() {
        //add region Name to the form
        $('#regionName').val($("#regionForm").find(":selected").text());
        $('#cityName').val($("#selectCity").find(":selected").text());
    }

    //validate that all fields to be uploaded contain a file and show error if not
    $('#createRealEstateForm').on("submit", function () {
        //addRegionCityNamesToForm();
    	$('#deedNo input').prop( "disabled", false);
    	$('#mojCity input').prop( "disabled", false);
    	$('#mojDeedDate input').prop( "disabled", false);
    	$('#mojIssuerCourtName input').prop( "disabled", false);
        var formIsValid = true;
        $('*[id*=fileTextId_]:visible').each(function () {
            if (this.placeholder == null || this.placeholder == '') {
                if($(this).parent(".form-group").find(".help-block").length == 0){
                    $(this).parent(".form-group")
                        .append("<div class=\"help-block\"><span id=\"create.realEstate.uploadError\">" +
                            getI18nText("create.realEstate.uploadError") +
                            "</span></div>");
                }
                $(this).parent(".form-group").addClass("has-error");
                formIsValid = false;
            }
        });

       if(!$('#createRealEstateForm').valid()){
           formIsValid = false;
       }

       if(formIsValid) {
           $('.js-submit-create-realEstate').attr('disabled', true);
       }
        return formIsValid;
    });
    
    $(".request-type").change(function (event, data) {
    	var requestType = $(".request-type").val();
    	if(requestType === "Sell"){
    		$("#identityType").val("3").change();
    		$("#identityType").attr('disabled', true);
    		//$("#deedNumber").attr('disabled', true);
    		$("#deedNumberDiv").hide();
    		$('#identityNumber').removeAttr('disabled');
    		$('#realEstateDocuments').attr('hidden','hidden');
    	}else{
    		$("#identityType").val("").change();
    		$("#identityType").attr('disabled', false);
    		$("#deedNumberDiv").show();
    		$('#identityNumber').removeAttr('disabled');
            $('#deedNumber').removeAttr('disabled');
    		if($(".realEstate-type").val()){
    			if (!data ) {
    	            data = {};
    	        }
    			//getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
    		}
    		
    	}
    });
    
    $("#deedSelect").change(function (event, data) {
        if (!data ) {
            $('#regionForm').trigger('change');
            data = {};
        }

        $("#realEstateDetails").attr("hidden", false);
        $("#realEstateTypeTitle").html($(".realEstate-type option:selected").text());

        if ($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }

        hideFormFields();
        //getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
    });
    
    $('#getDeeds').click(function(e){
        
    	e.preventDefault();
    	$("#realEstateDetails").attr("hidden", true);
        var identityNumber = $('#identityNumber').val();
        var identityType = $('#identityType').val();
        var deedNumber = $('#deedNumber').val();
        var requestType = $(".request-type").val();
    	if(requestType === "Sell"){
        	deedNumber = "1";
        }
    	
        $.ajax(ACC.config.encodedContextPath + "/real-estate/deeds/" + identityType + "/" + identityNumber + "/" + deedNumber,{
        	type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
            	if(data){
            		$('#isMojVerified').val("TRUE");
	            	var deeds = JSON.parse(data);
	            	$("#realEstateAddInputDetails").attr("hidden", false);
	            	if(requestType === "Sell" || requestType === "Buy"){
	            		$('#realEstateDocuments').attr('hidden','hidden');
	            	}
                content = '';
            if (deeds != null && deeds.length > 0) {
                deeds.forEach(function (element, index) {
                	content += 
                    '<tr id="' + element.deedNo + '">' +
                    '   <td>' + '<input id="' + element.deedNo + '" class="form-control deedDetail" type="radio" value="'+element.deedNo +'">'+ '</td>' +
                    '   <td class="activityDescription">' + element.deedNo + '</td>' +
                    '   <td class="activityDescription">' + element.mojDeedDate + '</td>' +
                    '   <td class="activityDescription">' + element.mojRegion + '</td>' +  
                    '   <td class="activityDescription">' + element.mojCity + '</td>' +
                    '</tr>';
                });
                var deedTableBody = $("#realEstateAddInputDetails #deedTable tbody");
                deedTableBody.empty().append(content);
            }
            	}else{
            	skipMoj(data);
            }
            },
            error: function (data) {
            	skipMoj(data);
             }
        });
    });
    
    $(document).on("change", ".deedDetail", function(e, data) {
    	e.preventDefault();
    	var form = $('#createRealEstateForm');
    	var formData = $(form).serialize();
    	var identityNumber = $('#identityNumber').val();
        var identityType = $('#identityType').val();
        var deedNumber = $(this).val();
        
        $.ajax(ACC.config.encodedContextPath + "/real-estate/deedDetails/" + identityType + "/" + identityNumber + "/" + deedNumber,{
        	type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
            	if(data){
            	
            	var deedDetails = JSON.parse(data);
            	$("#realEstateDetails").attr("hidden", false);
            	
            	$('#deedNo input').val(deedDetails.deedNo);
            	            	
            	$('#plotNo1 input').val(deedDetails.plotNo);
            	$('#plotArea input').val(deedDetails.plotArea);
            	$('#blockNo input').val(deedDetails.blockNo);
            	$('#price input').val(deedDetails.price);
            	$('#district input').val(deedDetails.district);
            	$('#unitNo input').val(deedDetails.unitNo);
            	$('#district input').val(deedDetails.district);
            	$('#district input').val(deedDetails.district);
            	
            	$('#mojRegion input').val(deedDetails.mojRegion);
            	$('#region input').val(deedDetails.mojRegion);
            	$('#regionName').val(deedDetails.mojRegion);
            	$("#mojRegion").attr("hidden", false);
            	$("#region").attr("hidden", true);
            	
            	$('#mojCity input').val(deedDetails.mojCity);
            	$('#city input').val(deedDetails.mojCity);
            	$('#cityName').val(deedDetails.mojCity);
            	$("#mojCity").attr("hidden", false);
            	$("#city").attr("hidden", true);
            	
            	$('#mojDeedDate input').val(deedDetails.mojDeedDate);
            	$('#deedDate input').val(deedDetails.mojDeedDate);
            	$("#mojDeedDate").attr("hidden", false);
            	$("#deedDate").attr("hidden", true);
            	
            	$('#mojIssuerCourtName input').val(deedDetails.issuerCourtName);
		if(deedDetails.issuerCourtName.length != 0){            		
            		$('#mojIssuerCourtName input').prop( "disabled", true );
            	}
            	
            	
            	$("#addPlotNo").hide();
            	$('#isMojVerified input').val("TRUE");
            	
            	$('#deedNo input').prop( "disabled", true );
            	$('#mojCity input').prop( "disabled", true );
            	$('#mojDeedDate input').prop( "disabled", true );
            	//$('#mojIssuerCourtName input').prop( "disabled", true );
            	
            	hideFormFields();
            	
            	}else{
            		skipMoj(data);
            	}
            },
            error: function (data) {
            	skipMoj(data);
             }
        });
    });
    $(".realEstate-type").change(function (event, data) {
        if (!data) {
            data = {};
        }
        var requestType = $(".request-type").val();
        if(requestType == "Sell"){
    		$("#identityType").val("3").change();
    		$("#identityType").attr('disabled', true);
    		//$("#deedNumber").attr('disabled', true);
    		$("#deedNumberDiv").hide();
    		$('#identityNumber').removeAttr('disabled');
    		//$('#realEstateDocuments').attr('hidden','hidden');
    	}
        $('#realEstateAddInputDetails').attr('hidden','hidden');
        $('#realEstateDetails').attr('hidden','hidden');
    	$("#realEstateDocuments").attr('hidden','hidden');
    	
        $("#realEstateTypeTitle").html($(".realEstate-type option:selected").text());

        if ($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }
        
        hideFormFields();
        //var requestType = $(".request-type").val();
    	if($("#region").is(":visible")){
    		getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
    	}else if(requestType === "Buy"){
            //getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
        }
    });
    
    function skipMoj(data){
    	$("#region").attr("hidden", false);
    	$("#city").attr("hidden", false);
    	$("#mojRegion").attr("hidden", true);
    	$("#mojCity").attr("hidden", true);
    	$("#mojDeedDate").attr("hidden", true);
    	$("#deedDate").attr("hidden", false);
    	//$("#mojIssuerCourtName").attr("hidden", true);
    	$("#realEstateAddInputDetails").attr("hidden", true);
    	$('#isMojVerified input').val("FALSE");
    	data = {};
    	if (!data) {
            $('#regionForm').trigger('change');
            data = {};
        }
        $("#realEstateDetails").attr("hidden", false);
        
        $("#realEstateTypeTitle").html($(".realEstate-type option:selected").text());

        if ($(this).closest('.form-group').hasClass('has-error')) {
            $(this).closest('.form-group').removeClass('has-error');
        }

        if ($(this).closest('.formSelectBox').length>0){
            $(this).closest('.formSelectBox').find('.help-block').text('');
        }
        addRegionCityNamesToForm();
        hideFormFields();
        getAttachmentList($('.request-type').val(), $('.realEstate-type').val(), data.files);
    }
    
});


