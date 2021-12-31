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
                            '<div class="col-md-6" data-real-index="' + index + '">' +
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
                            '<div class="col-md-6">' +
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
                                '<span class="iconElement iconElement_pdf"><svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="24" height="32" viewBox="0 0 24 32"><path d="M8.809 25.185c-.095-.019-.286-.026-.572-.026h-.384v1.116h.435c.312 0 .523-.021.627-.062.106-.041.189-.104.249-.192.06-.087.09-.19.09-.306 0-.144-.042-.261-.126-.355-.085-.093-.19-.152-.319-.175zM12.895 25.381c-.097-.092-.22-.152-.368-.185-.11-.026-.328-.038-.652-.038h-.356v2.606h.593c.222 0 .383-.014.48-.038.129-.033.236-.087.32-.164.085-.077.155-.203.209-.379.054-.178.08-.418.08-.722s-.026-.537-.08-.701c-.055-.161-.131-.289-.226-.379zM17.091.539h-16.491c-.276 0-.5.224-.5.5v29.921c0 .277.224.501.5.501h22.8c.276 0 .5-.224.5-.501v-23.612l-6.809-6.809zm-7.149 25.773c-.088.165-.199.294-.334.389-.135.093-.273.156-.413.187-.189.037-.465.056-.824.056h-.518v1.484h-.794v-3.935h1.274c.483 0 .799.02.945.059.225.059.415.188.566.386.152.196.228.452.228.767.001.238-.043.443-.13.607zm3.958 1.023c-.099.286-.238.519-.421.694-.138.135-.323.239-.559.314-.175.056-.409.083-.703.083h-1.494v-3.935h1.451c.327 0 .578.025.749.075.231.068.428.189.594.362.164.174.289.386.376.639.085.251.129.56.129.929 0 .325-.041.604-.122.839zm3.499-2.177h-1.904v.931h1.644v.665h-1.644v1.673h-.794v-3.935h2.698v.666zm5.501-3.006h-21.8v-20.613h15.555v6.06h6.245v14.553z"/></svg></span>' +
                                element.filename +
                                '</div>' +
                                '<input id="objectId" type="hidden" value='+element.objectId+'>'+
                                '<input id="docGuid" type="hidden" value='+element.docGuid+'>'+
                                '<div class="downloadList-actions">' +
                                '<a href="' + ACC.config.encodedContextPath + '/real-estate/pdf/' + element.objectId + "/" + element.docGuid +'" class="link link_nowrap" download="'+ element.fullFileName 
                                + '"> '
                                + '<span class="iconElement iconElement_cloud"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
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


