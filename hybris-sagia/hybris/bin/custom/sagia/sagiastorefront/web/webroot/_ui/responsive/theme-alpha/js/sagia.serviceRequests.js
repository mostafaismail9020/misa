$(function () {

    var serviceRequestsTable = $("#serviceRequestsTable");
    var secriceRequestSection = $("#servicesRequest");
    if(secriceRequestSection.closest('.js-component').css('display') !== 'none') {
        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/serviceRequests/10000",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (serviceRequests) {
                if (serviceRequests && serviceRequests.serviceRequestsData && serviceRequests.serviceRequestPagesNumber) {
                    for(var index in serviceRequests.serviceRequestsData) {
                        if(serviceRequests.serviceRequestsData.hasOwnProperty(index)) {
                            var serviceRequest = serviceRequests.serviceRequestsData[index];
                            var template = $(".serviceTemplateWrapper").find("tbody").clone();

                            if(serviceRequest.urlPath){
                                template.find("#serviceRequestLink").attr('href',ACC.config.encodedContextPath + '/'+serviceRequest.urlPath);
                            }
                            else{
                                $('<div class="tableModule-headline" style="cursor: not-allowed;"></div>').insertAfter(template.find('#serviceRequestLink'));
                                template.find("#serviceRequestLink").remove();
                            }

                            if(!isEmpty(serviceRequest.categoryLevelFour)){
                                template.find(".tableModule-headline").html(serviceRequest.categoryLevelFour);
                                template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelThree);
                                template.find("#description1").html(serviceRequest.categoryLevelTwo);
                                template.find("#description2").html(serviceRequest.categoryLevelOne);
                                template.find("#description3").html(serviceRequest.serviceType);

                            }
                            else if(!isEmpty(serviceRequest.categoryLevelThree)) {
                                template.find(".tableModule-headline").html(serviceRequest.categoryLevelThree);
                                template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelTwo);
                                template.find("#description1").html(serviceRequest.categoryLevelOne);
                                template.find("#description2").html(serviceRequest.serviceType);
                            }
                            else if(!isEmpty(serviceRequest.categoryLevelTwo)) {
                                template.find(".tableModule-headline").html(serviceRequest.categoryLevelTwo);
                                template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelOne);
                                template.find("#description1").html(serviceRequest.serviceType);
                            }
                            else if(!isEmpty(serviceRequest.categoryLevelOne)) {
                                template.find(".tableModule-headline").html(serviceRequest.categoryLevelOne);
                                template.find("#description1").html(serviceRequest.serviceType);
                            }
                            else if(!isEmpty(serviceRequest.serviceType)){
                                template.find(".tableModule-headline").html(serviceRequest.serviceType);
                            }

                            if(serviceRequest.urlPath){
                                template.find("#serviceRequestLink").attr('href',ACC.config.encodedContextPath + '/'+serviceRequest.urlPath);
                            }
                            template.find(".requestNumber").html(serviceRequest.requestNumber);
                            template.find(".requestDate").html(serviceRequest.requestDate.dateFormatted);
                            template.find(".serviceStatus").html(serviceRequest.statusDescription);
                            template.find(".dashboardWidgetRequests-status-icon").html(SAGIA.statusUpdate.initServiceRequest(serviceRequest.status));
                            if(serviceRequest.status==="completed") {
                                template.find("#printLink").attr('href', ACC.config.encodedContextPath + "/dashboard/serviceRequests/print/" + serviceRequest.requestNumber);
                            }
                            else{
                                template.find("#printLink").html('');
                            }
                            serviceRequestsTable.append(template.html());
                        }
                    }
                    var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link service active">1</a></div>';
                    for (var i = 2; i <= serviceRequests.serviceRequestPagesNumber; i++) {
                        paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link service">' + i + '</a></div>';
                    }
                    $(".dashboardWidgetRequests .paginationModule-items").empty().append(paginationHtml);
                    new CreatePagination($(".dashboardWidgetRequests .paginationModule"), serviceRequests.serviceRequestsItemsPerPage);
                    $(".dashboardWidgetRequests .paginationPicker").append("<option selected='selected' value='" + serviceRequests.serviceRequestsItemsPerPage + "'>" + getI18nText("dashboard.servicesRequest.viewAll") + "</option>")
                    if(serviceRequests.showItemsPerPage != null && serviceRequests.showItemsPerPage.length > 0){
                       var items = serviceRequests.showItemsPerPage.split(",");
                        for(i=0;i<items.length;i++){
                            $(".dashboardWidgetRequests .paginationPicker").append("<option value='" +  items[i] + "'>"+ items[i] +"</option>");
                        }
                    }
                }
                $('.dashboardWidgetRequests .loadingModule').addClass('loadingModule_loaded');
                $('.dashboardWidgetRequests .paginationModule').removeClass('paginationModule_loading');
            },
            error: function() {
                serviceRequestsTable.empty();
                $(".dashboardWidgetRequests .paginationModule-items").empty();
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.service.requests"));
                errorModal.modal('show');
                $('.dashboardWidgetRequests .loadingModule .loadingModule-icon').hide();
                $('.dashboardWidgetRequests .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText("general.couldnot.load.service.requests")+"</span>");
            }
        });
    }

    var draftsTable = $("#draftsTable");
    if (draftsTable.closest('.js-component').css('display') !== 'none') {
        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/drafts",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (draftInfo) {
                if (draftInfo) {
                    for(var index in draftInfo) {
                        if(draftInfo.hasOwnProperty(index)) {
                            var draft = draftInfo[index];
                            var template = $(".draftTemplateWrapper").find("tbody").clone();
                            template.find(".js-category-1").html(draft.category1);
                            if (draft.category2)
                                template.find(".js-category-2").html(draft.category2);
                            if (draft.category3)
                                template.find(".js-category-3").html(draft.category3);
                            if (draft.category4)
                                template.find(".js-category-4").html(draft.category4);

                            template.find(".draftTemporaryNumber").html(draft.temporaryNumber);
                            template.find(".draftDate").html(draft.creationDate.dateFormatted);

                            if (draft.url)
                                template.find(".draftContinueBtn").attr("href", draft.url);
                            draftsTable.append(template.html());
                        }
                    }
                }
            },
            error: function() {
                draftsTable.empty();
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.payments"));
                errorModal.modal('show');
            }
        });
    }
});