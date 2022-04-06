var SAGIA = SAGIA || {};

$(function() {
    // Scrolling to the selected element within service requests history lists.
    var container = $('#history-list');
    if(container.length){
        var element = container.find('.historyList-item_current');
        if(element.length){
            scrollToSelectedElement(container,element);
        }
    }
    SAGIA.showSuccess = function(message) {
        var successModal = $('#successResponseModal');
        if(message) {
            successModal.find('.modal-description').html(message);
        }
        successModal.modal('show');
    };
    
    SAGIA.showError = function(message) {
        var errorModal = $('#errorResponseModal');
        if(message) {
            errorModal.find('.modal-description').html(message);
        }
        errorModal.modal('show');
    };

    SAGIA.showInfo = function(message) {
        var infoModal = $('#infoResponseModal');
        if(message) {
            infoModal.find('.modal-description').html(message);
        }
        infoModal.modal('show');
    };

    SAGIA.showTermsAndConditions = function(message) {
        var termsAndConditionsModal = $('#termsAndConditionsResponseModal');
        if(message) {
            termsAndConditionsModal.find('.modal-description').load(message, function() {
                termsAndConditionsModal.modal('show');
            });
        } else {
            termsAndConditionsModal.modal('show');
        }
    };

    SAGIA.handleGenericAjaxError = function(jqxhr) {
        if(jqxhr.responseJSON && typeof jqxhr.responseJSON.sagiaExceptionResponse !== 'undefined' && jqxhr.responseJSON.sagiaExceptionResponse.message) {
            SAGIA.showError(jqxhr.responseJSON.sagiaExceptionResponse.message);
        } else {
            if(jqxhr.responseJSON && jqxhr.responseJSON.formErrors) {
                $.each(jqxhr.responseJSON.formErrors, function(formField, errorMessage) {
                    $('input[name="' + formField + '"]').siblings('.help-block').html(errorMessage);
                });
            } else {
                SAGIA.showError(getI18nText("general.error"));
            }
        }
    };

    SAGIA.chat = SAGIA.chat || {};
    SAGIA.chat.feedbackInProgress = false;
    SAGIA.chat.showFeedbackModal = function() {
        if (!SAGIA.chat.feedbackInProgress) {
            var $feedbackModal = $('#requestSubmittedComment');
            $feedbackModal.modal('show');
            $feedbackModal.on('hide.bs.modal', SAGIA.chat.cleanupFeedbackModal);

            $feedbackModal.find('#chatTitleId, #chatMessageId').show();
            $feedbackModal.find('#requestTitleId, #requestMessageId, #feedbackDashboardButtonId').hide();

            SAGIA.chat.feedbackInProgress = true;
        }
    };
    SAGIA.chat.cleanupFeedbackModal = function() {
        var $feedbackModal = $('#requestSubmittedComment');
        $feedbackModal.find('.ratingModule-star.review:last-child').trigger('mouseenter');
        $feedbackModal.find('#extensionReason').val('');
        $feedbackModal.off('hide.bs.modal');

        $feedbackModal.find('#chatTitleId, #chatMessageId').hide();
        $feedbackModal.find('#requestTitleId, #requestMessageId, #feedbackDashboardButtonId').show();

        SAGIA.chat.feedbackInProgress = false;
    };

    var spinnerMainDiv = $("#spinnerMainDiv");
    var lastAjaxCallTimeout = null;
    var redirectToLogoutTimeout = null;
    $(document).ajaxStart(function() {
        _onAjaxCall();
        //spinnerMainDiv.removeClass("hidden");
    }).ajaxSend(function(event, jqXHR, ajaxSettings) {
        _onAjaxCall();
        if(ajaxSettings && !ajaxSettings.ajaxHideLoadingIndicator) {
            spinnerMainDiv.removeClass("hidden");
        }
    }).ajaxStop(function() {
        spinnerMainDiv.addClass("hidden");
    }).ajaxError(function(event, jqxhr, settings, thrownError) {
        if(settings.displayErrorMessageInModal !== undefined && !settings.displayErrorMessageInModal) {
            return;
        }
        SAGIA.handleGenericAjaxError(jqxhr);
    });

    var sessionExpiresModal = $("#sessionExpiresModal");
    var _onAjaxCall = function() {
        if(document.location.href.indexOf("/login") !== -1) {
            return;
        }
        if(lastAjaxCallTimeout != null) {
            clearTimeout(lastAjaxCallTimeout);
        }
        lastAjaxCallTimeout = setTimeout(function() {
            lastAjaxCallTimeout = null;
            if(redirectToLogoutTimeout != null) {
                clearTimeout(redirectToLogoutTimeout);
            }
            redirectToLogoutTimeout = setTimeout(function() {
                redirectToLogoutTimeout = null; //for brevity
                document.location.href = ACC.config.encodedContextPath + "/logout";
            }, 60 * 1000); //1 minute
            sessionExpiresModal.modal("show");
        }, (SAGIA.config.sessionTimeout - 60) * 1000);
    };
    sessionExpiresModal.find(".noButton").on("click", function() {
        document.location.href = ACC.config.encodedContextPath + "/logout";
    });
    sessionExpiresModal.find(".yesButton").on("click", function() {
        sessionExpiresModal.modal("hide");
        clearTimeout(redirectToLogoutTimeout);
        redirectToLogoutTimeout = null;
        $.ajax({
            url: ACC.config.encodedContextPath + "/heartbeat",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function () {}
        });
    });

    if($(document).height() < $(window).height()) {
        $(".investsaudiFooter").css({
            position: 'absolute',
            bottom: 0
        });
    }

//    SAGIA MENU HANDLING START
    var getCategoriesHtml = function(categories) {
        var html = '';
        for(var index in categories) {
            if(categories.hasOwnProperty(index)) {
                var categoryToAdd = categories[index];
                html +=
                    '<li class="sagiaNavigation-subNav-icon sagiaNavigation-subNav-icon">' +
                    '   <div class="serviceModule-icon">' +
                    '       <a href="" data-target-submenu="' + categoryToAdd.name + '">' +
                    (categoryToAdd.menuIcon && categoryToAdd.menuIcon.url ?
                        '       <img src="' + categoryToAdd.menuIcon.url + '" width="25" height="25"/>' : '') +
                    (categoryToAdd.name.length ? categoryToAdd.name : categoryToAdd.code) +
                    '       </a>' +
                    '   </div>' +
                    '</li>';
            }
        }
        return html;
    };
    
    var getMobileCategoriesHtml = function(categories) {
        var html = '';
        for(var index in categories) {
            if(categories.hasOwnProperty(index)) {
                var categoryToAdd = categories[index];
                html +=
                    '<li class="js-sagiaMobileNav_moveForwards sagiaMobileNav-subNav-icon">' +
                    '   <a href="" data-target-submenu="' + categoryToAdd.name + '" data-target-menuID="#sagiaMobileNav-serviceMenu" class="sagiaMobileNav-hasSub-link_forwards">' +
                    (categoryToAdd.menuIcon && categoryToAdd.menuIcon.url ?
                        '   <img src="' + categoryToAdd.menuIcon.url + '" width="25" height="25"/>' : '') +
                    (categoryToAdd.name.length ? categoryToAdd.name : categoryToAdd.code) +
                    '   </a>' +
                    '</li>';
            }
        }
        return html;
    };    

    var handleMenuData = function(menuData) {
        if(menuData && menuData.navcategories && menuData.navservices) {
            //please also add adjustments to mobile navigation below accordingly
            var uiMenu = $(".sagiaNavigation-subNav").empty();            
            if(uiMenu.length) {
                
                if (menuData.navcategories.FIRST && menuData.navcategories.FIRST.length) {
                    uiMenu.append('' +
                        '<li class="sagiaNavigation-subNav-title">' +
                        '   <a href="' + ACC.config.encodedContextPath + '/service-search"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="21px" height="24px" viewBox="0 0 21 24" enable-background="new 0 0 21 24" xml:space="preserve"><path fill="#5CC83B" d="M18.098,13.83c-1.061-1.364-2.716-2.241-4.576-2.241c-3.201,0-5.795,2.594-5.795,5.794c0,2.099,1.116,3.937,2.787,4.954l-1.294,1.451H0V0h19.824v11.894L18.098,13.83z M16.458,4.484H3.163v1.4h13.295V4.484zM16.458,8.475H3.163v1.4h13.295V8.475z M6.8,12.338H3.163v1.4H6.8V12.338z M5.834,16.2H3.163v1.4h2.671V16.2z"></path> <path fill="#1C242C" d="M13.521,21.947c-2.517,0-4.564-2.047-4.564-4.563s2.047-4.563,4.564-4.563c2.516,0,4.562,2.047,4.562,4.563S16.037,21.947,13.521,21.947z M13.521,14.221c-1.744,0-3.164,1.419-3.164,3.163s1.419,3.163,3.164,3.163s3.162-1.419,3.162-3.163S15.266,14.221,13.521,14.221z"></path> <path fill="#1C242C" d="M18.805,23.685l-3.444-3.638l1.017-0.962l3.444,3.638L18.805,23.685z"></path></svg>'+getI18nText('navigation.text.overview')+'</a>' +
                        '</li>');
                    uiMenu.append(getCategoriesHtml(menuData.navcategories.FIRST));
                }

                if (menuData.navcategories["GOVERNMENTAL SERVICES"] && menuData.navcategories["GOVERNMENTAL SERVICES"].length) {
                    uiMenu.append('<li class="sagiaNavigation-subNav-subtitle">'+getI18nText("sagia.governmental.services")+'</li>');
                    uiMenu.append(getCategoriesHtml(menuData.navcategories["GOVERNMENTAL SERVICES"]));
                }

                if (menuData.navcategories["SAGIA SERVICES"] && menuData.navcategories["SAGIA SERVICES"].length) {
                    uiMenu.append('<li class="sagiaNavigation-subNav-subtitle">'+getI18nText("sagia.services")+'</li>');
                    uiMenu.append(getCategoriesHtml(menuData.navcategories["SAGIA SERVICES"]));
                }
            }

            var uiMenuRight = $(".sagiaNavigation-subRight").empty();
            if(uiMenuRight.length) {
                for(var indexNavServices in menuData.navservices) {
                    if(menuData.navservices.hasOwnProperty(indexNavServices)) {
                        var navServices = menuData.navservices[indexNavServices];
                        // console.log('navServices==>',navServices)
                        var htmlContentForNavServices = '<div class="sagiaNavigation-subRight-pane js-sagiaNavigation-subRight-pane" data-submenu="' + indexNavServices + '">';
                        for(var indexNavServiceItem in navServices) {
                            if(navServices.hasOwnProperty(indexNavServiceItem)) {
                                var navServiceItem = navServices[indexNavServiceItem];
                                var urlTokens = navServiceItem.menuUrl.split('?');
                                navServiceItem.menuUrl = urlTokens[0] + (urlTokens.length > 1 ? "?" + encodeURIComponent(urlTokens[1]).replace(/'/g,"%27").replace(/"/g,"%22") : "");

                                var encodedUrl = urlTokens.length > 0 ? urlTokens[0] : "";
                                encodedUrl += urlTokens.length > 1 ? "?" + encodeURIComponent(urlTokens[1]).replace(/'/g,"%27").replace(/"/g,"%22") : "";
                                // console.log(encodedUrl);
                                htmlContentForNavServices += '<a href="' + ACC.config.encodedContextPath + '/' + encodedUrl + '"><span>' + navServiceItem.name + '</span></a>';
                            }
                        }
                        htmlContentForNavServices += '</div>';
                        uiMenuRight.append(htmlContentForNavServices);
                    }
                }
            }

            //Mobile menu
            var uiMenuMobile = $(".js-mobileNavRender").empty();
            if(uiMenuMobile.length) {
                if (menuData.navcategories.FIRST && menuData.navcategories.FIRST.length) {
                    uiMenuMobile.append('' +
                        '<li class="sagiaMobileNav-subNav-title">' +
                        '   <a href="' + ACC.config.encodedContextPath + '/service-search"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="21px" height="24px" viewBox="0 0 21 24" enable-background="new 0 0 21 24" xml:space="preserve"><path fill="#5CC83B" d="M18.098,13.83c-1.061-1.364-2.716-2.241-4.576-2.241c-3.201,0-5.795,2.594-5.795,5.794c0,2.099,1.116,3.937,2.787,4.954l-1.294,1.451H0V0h19.824v11.894L18.098,13.83z M16.458,4.484H3.163v1.4h13.295V4.484zM16.458,8.475H3.163v1.4h13.295V8.475z M6.8,12.338H3.163v1.4H6.8V12.338z M5.834,16.2H3.163v1.4h2.671V16.2z"></path> <path fill="#1C242C" d="M13.521,21.947c-2.517,0-4.564-2.047-4.564-4.563s2.047-4.563,4.564-4.563c2.516,0,4.562,2.047,4.562,4.563S16.037,21.947,13.521,21.947z M13.521,14.221c-1.744,0-3.164,1.419-3.164,3.163s1.419,3.163,3.164,3.163s3.162-1.419,3.162-3.163S15.266,14.221,13.521,14.221z"></path> <path fill="#1C242C" d="M18.805,23.685l-3.444-3.638l1.017-0.962l3.444,3.638L18.805,23.685z"></path></svg>'+getI18nText('navigation.text.overview')+'</a>' +
                        '</li>');
                    uiMenuMobile.append(getMobileCategoriesHtml(menuData.navcategories.FIRST));
                }

                if (menuData.navcategories["GOVERNMENTAL SERVICES"] && menuData.navcategories["GOVERNMENTAL SERVICES"].length) {
                    uiMenuMobile.append('<li class="sagiaMobileNav-subNav-subtitle">'+getI18nText("sagia.governmental.services")+'</li>');
                    uiMenuMobile.append(getMobileCategoriesHtml(menuData.navcategories["GOVERNMENTAL SERVICES"]));
                }

                if (menuData.navcategories["SAGIA SERVICES"] && menuData.navcategories["SAGIA SERVICES"].length) {
                    uiMenuMobile.append('<li class="sagiaMobileNav-subNav-subtitle">'+getI18nText("sagia.services")+'</li>');
                    uiMenuMobile.append(getMobileCategoriesHtml(menuData.navcategories["SAGIA SERVICES"]));
                }
            }          

            var uiMobileMenuRight = $("#sagiaMobileNav-serviceMenu").empty(); 
            if(uiMobileMenuRight.length) {
                for(var indexNavServices in menuData.navservices) {
                    if(menuData.navservices.hasOwnProperty(indexNavServices)) {
                        var navServices = menuData.navservices[indexNavServices];
                        var htmlContentForNavServices = '<div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="' + indexNavServices + '"><div class="sagiaMobileNav-subRight-detail"> <div class="sagiaMobileNav-subRight-icon"></div><div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div></div>';
                        for(var indexNavServiceItem in navServices) {
                            if(navServices.hasOwnProperty(indexNavServiceItem)) {
                                var navServiceItem = navServices[indexNavServiceItem];
                                var urlTokens = navServiceItem.menuUrl.split('?');
                                navServiceItem.menuUrl = urlTokens[0] + (urlTokens.length > 1 ? "?" + encodeURIComponent(urlTokens[1]).replace(/'/g,"%27").replace(/"/g,"%22") : "");

                                var encodedUrl = urlTokens.length > 0 ? urlTokens[0] : "";
                                encodedUrl += urlTokens.length > 1 ? "?" + encodeURIComponent(urlTokens[1]).replace(/'/g,"%27").replace(/"/g,"%22") : "";                                
                                htmlContentForNavServices += '<a href="' + ACC.config.encodedContextPath + '/' + encodedUrl + '"><span>' + navServiceItem.name + '</span></a>';
                            }
                        }
                        htmlContentForNavServices += '</div>';
                        uiMobileMenuRight.append(htmlContentForNavServices);
                    }
                }
            }          

            if(uiMenu.length || uiMenuRight.length) {
                SAGIA.sagiaNavigation.init();
                $('.sagiaNavigation-list-hasSub .sagiaNavigation-subPane .temporaryLoadingMessage').remove();
            }
        }
    };

    $.ajax({
        url: ACC.config.encodedContextPath + "/menu/cached",
        method: 'GET',
        ajaxHideLoadingIndicator: true,
        success: function (menuData) {
            $('.sagiaNavigation-list-hasSub .sagiaNavigation-subPane').prepend(
                "<li class='temporaryLoadingMessage sagiaNavigation-subNav-title'><a href='#'>" + getI18nText("text.loadingMessage") + "</a></li>");
            handleMenuData(menuData);
            $('.sagiaNavigation-list-hasSub .sagiaNavigation-subPane .temporaryLoadingMessage a').html(getI18nText("text.refreshingMessage"));
            $.ajax({
                url: ACC.config.encodedContextPath + "/menu",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (menuData) {
                    handleMenuData(menuData);
                }
            });
        }
    });
    //SAGIA MENU HANDLING END

    $("#logoutModal .yesButton").on("click", function() {
        $.cookie('chatOpen', null, { path: ACC.config.encodedContextPath });
        $("#sap-ecf-client").toggle(false);
        window.location.href = ACC.config.encodedContextPath + "/login";
    });
    $("#logoutModal .noButton").on("click", function() {
        $('#logoutModal').modal('hide');
    });

    //SAGIA NOTIFICATIONS HANDLING START
    SAGIA.notifications = SAGIA.notifications || {};
    SAGIA.notifications.updateNotificationsCount = function(notificationsCount) {
        var unreadNotificationSpan = $('#unreadNotificationSpan');
        // var unreadNotificationSpan = $('.count-notification');
        if (notificationsCount === 0) {
            unreadNotificationSpan.css('display', 'none');
        } else if (notificationsCount > 0) {
            unreadNotificationSpan.css('display', 'block');
        }
        unreadNotificationSpan.text(notificationsCount);
    };

    SAGIA.notifications.handleNotificationData = function(notificationData) {
        if (typeof(notificationData.allNotifsCount) !== "undefined") {
            var notificationsForPopupHtml = '';
            var notificationsCount = notificationData.allNotifsCount.unread;
            var popupNotifications = notificationData.popupNotifs;
            var mandatoryNotificationsCount = 0;
            for (var index in popupNotifications) {
                if (popupNotifications.hasOwnProperty(index)) {
                    var notification = popupNotifications[index];
                    if (notification.notificationPriority === 'M') {
                        mandatoryNotificationsCount++;
                    }
                    if (!notification.readDate) {
                        notificationsForPopupHtml +=
                            '<li class="notificationList-item notificationList-item_unread" data-transaction-id="${notification.transactionId}">' +
                            '   <a href="' + ACC.config.encodedContextPath + '/my-sagia/notifications/' + notification.transactionId + '" class="notificationList-link">' +
                            '       <div class="notificationList-thumb">';
                    }
                    else {
                        notificationsForPopupHtml +=
                            '<li class="notificationList-item notificationList-item_read" data-transaction-id="${notification.transactionId}">' +
                            '   <a href="' + ACC.config.encodedContextPath + '/my-sagia/notifications/' + notification.transactionId + '" class="notificationList-link">' +
                            '       <div class="notificationList-thumb">';
                    }

                    if (notification.notificationType === 'WL' || notification.notificationType === 'NO') {
                        notificationsForPopupHtml +=
                            '           <div class="iconElement iconElement_expertProfile_green">' +
                            '               <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path class="iconElement-colorPrimary_fill" d="M12 0c6.627 0 12 5.373 12 12s-5.373 12-12 12-12-5.373-12-12 5.373-12 12-12z"/><path class="iconElement-colorSecondary_fill" d="M21.086 19.824c-2.201 2.553-5.451 4.176-9.086 4.176-3.567 0-6.761-1.565-8.959-4.036.846-.884 3.309-2.132 6.244-3.323v-1.8c-.166-.118-.343-.292-.512-.533-.39-.555-.63-1.336-.674-2.375-.441-.247-.715-.847-.765-1.503-.051-.676.158-1.267.616-1.538l-.174-.42c-.093-.231-.167-.44-.227-.642-.186-.62-.011-1.034.186-1.436.226-.459.395-.961 1.033-.912 1.205-2.449 6.619-2.044 7.297.671.204.815.776 1.56.44 2.416l-.125.296c.424.312.615.901.56 1.568-.056.656-.329 1.252-.769 1.499-.043 1.04-.284 1.821-.674 2.375-.168.241-.346.415-.512.533v1.8c2.786 1.131 5.143 2.311 6.101 3.184z"/></svg>' +
                            '           </div>';
                        } else if (notification.notificationType === 'SY' || notification.notificationType === 'Rest') {
                            notificationsForPopupHtml +=
                                '           <div class="iconElement">' +
                                '               <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35">' +
                                '                   <g id="Icon_feather-settings" data-name="Icon feather-settings" transform="translate(-0.5 -0.5)">' +
                                '                   <path id="Path_2154" data-name="Path 2154" d="M22.5,18A4.5,4.5,0,1,1,18,13.5,4.5,4.5,0,0,1,22.5,18Z" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>' +
                                '                   <path id="Path_2155" data-name="Path 2155" d="M29.1,22.5a2.475,2.475,0,0,0,.495,2.73l.09.09a3,3,0,1,1-4.245,4.245l-.09-.09a2.5,2.5,0,0,0-4.23,1.77V31.5a3,3,0,1,1-6,0v-.135A2.475,2.475,0,0,0,13.5,29.1a2.475,2.475,0,0,0-2.73.495l-.09.09A3,3,0,1,1,6.435,25.44l.09-.09a2.5,2.5,0,0,0-1.77-4.23H4.5a3,3,0,1,1,0-6h.135A2.475,2.475,0,0,0,6.9,13.5a2.475,2.475,0,0,0-.5-2.73l-.09-.09A3,3,0,1,1,10.56,6.435l.09.09a2.475,2.475,0,0,0,2.73.495h.12A2.475,2.475,0,0,0,15,4.755V4.5a3,3,0,0,1,6,0v.135a2.5,2.5,0,0,0,4.23,1.77l.09-.09a3,3,0,1,1,4.245,4.245l-.09.09a2.475,2.475,0,0,0-.5,2.73v.12A2.475,2.475,0,0,0,31.245,15H31.5a3,3,0,0,1,0,6h-.135A2.475,2.475,0,0,0,29.1,22.5Z" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>' +
                                '                   </g> '+
                                '               </svg>' +
                                '           </div>';
                        } else if (notification.notificationType === 'VI') {
                            notificationsForPopupHtml +=
                                '           <div class="iconElement">' +
                                '               <svg xmlns="http://www.w3.org/2000/svg" width="35.856" height="35.5" viewBox="0 0 35.856 35.5">' +
                                '                   <g id="Appointment_Details" data-name="Appointment Details" transform="translate(0.364 0)">' +
                                '                   <g id="Group_1707" data-name="Group 1707" transform="translate(0.136 0)">' +
                                '                       <path id="Path_2344" data-name="Path 2344" d="M23.51,61.483H.5V31.49H32.051V51.125" transform="translate(-0.5 -28.526)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <path id="Path_2345" data-name="Path 2345" d="M235.872,234.059a6.639,6.639,0,0,1,.309,2.008,6.736,6.736,0,1,1-1.552-4.271" transform="translate(-201.497 -207.772)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <path id="Path_2346" data-name="Path 2346" d="M268.33,238.923l2.429,2.5,7.092-7.21" transform="translate(-242.851 -212.166)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_559" data-name="Line 559" y1="6.04" transform="translate(6.323)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_560" data-name="Line 560" y1="6.04" transform="translate(15.601)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_561" data-name="Line 561" y1="6.04" transform="translate(24.977)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_562" data-name="Line 562" x2="31.551" transform="translate(0 9.333)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_563" data-name="Line 563" x2="5.076" transform="translate(4.672 15.125)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_564" data-name="Line 564" x2="5.076" transform="translate(13.052 15.125)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_565" data-name="Line 565" x2="5.076" transform="translate(21.502 15.125)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_566" data-name="Line 566" x2="5.076" transform="translate(4.672 20.46)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_567" data-name="Line 567" x2="5.076" transform="translate(4.672 25.875)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_568" data-name="Line 568" x2="5.076" transform="translate(13.047 25.875)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_569" data-name="Line 569" x2="5.076" transform="translate(13.052 20.46)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                       <line id="Line_570" data-name="Line 570" x2="5.076" transform="translate(21.502 20.46)" fill="none" stroke="#00a6be" stroke-width="1"/>' +
                                '                   </g>' +
                                '                   </g>' +
                                '               </svg>' +
                                '           </div>';
                        }
                        else if (notification.notificationType === 'PY') {
                            notificationsForPopupHtml +=
                                '           <div class="iconElement">' +
                                '               <svg xmlns="http://www.w3.org/2000/svg" width="36.022" height="36.004" viewBox="0 0 36.022 36.004">'+
                                '                   <g id="My_Payments" data-name="My Payments" transform="translate(0.188 0.191)">'+
                                 '                   <g id="Group_1691" data-name="Group 1691" transform="translate(0.312 0.313)">'+
                                 '                       <rect id="Rectangle_1208" data-name="Rectangle 1208" width="29.558" height="20.789" rx="2" transform="translate(0 14.211)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <rect id="Rectangle_1209" data-name="Rectangle 1209" width="6.487" height="4.639" transform="translate(3.878 22.726)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <line id="Line_537" data-name="Line 537" x2="4.536" transform="translate(2.683 30.909)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <line id="Line_538" data-name="Line 538" x2="4.52" transform="translate(9.154 30.909)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <line id="Line_539" data-name="Line 539" x2="4.487" transform="translate(15.608 30.909)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <line id="Line_540" data-name="Line 540" x2="4.569" transform="translate(22.031 30.909)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <path id="Path_2307" data-name="Path 2307" d="M43.766,15.139,45.718,2.259a1.31,1.31,0,0,1,1.494-1.231L73.647,7.147a1.546,1.546,0,0,1,1.008,1.814l-2.968,17.43a1.3,1.3,0,0,1-1.489,1.2l-.959-.222" transform="translate(-39.681 -1.001)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                       <line id="Line_541" data-name="Line 541" x2="28.823" y2="6.533" transform="translate(5.524 5.109)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="1"/>'+
                                 '                  </g>'+
                                 '              </g>'+
                                 '              </svg> '+
                                '           </div>';
                        }

                    var createdBy = replaceWithEmptyIfNotPresent(notification.createdBy);
                    var createdOn = replaceWithEmptyIfNotPresent(notification.createdOn.dateFormatted);
                    var notificationText;
                    if(notification.notificationType === 'PY'){
                        SAGIA.notifications.payments = SAGIA.notifications.payments || [];
                        SAGIA.notifications.payments.push(notification);
                        $(".dashboardPrintButton").attr("href", ACC.config.encodedContextPath + '/my-sagia/notifications/' + notification.transactionId);
                        notificationText = replaceWithEmptyIfNotPresent(notification.notificationText);
                    }
                    else{
                        notificationText = replaceWithEmptyIfNotPresent(notification.notificationText);
                    }
                    notificationsForPopupHtml +=
                        '       </div>' +
                        '           <div class="notificationList-note">' +
                        '               <div class="notificationList-details">' +
                        '                   <div class="notificationList-name">' + createdBy + '</div>' +
                        '                   <div class="notificationList-date">' + createdOn + '</div>' +
                        '               </div>' +
                        '               <div class="notificationList-details">' +
                        '                   <div class="notificationList-title">' + notificationText + '</div>' +
                        '                   <div class="notificationList-status"></div>' +
                        '               </div>' +
                        '           </div>' +
                        '       </a>' +
                        '</li>';
                }
            }
            $("#popupNotificationHistoryList").empty().append(notificationsForPopupHtml);
            if (mandatoryNotificationsCount > 0) {
                $("#globalMessageDivHeader").css("display", "block");
            }
            SAGIA.notifications.updateNotificationsCount(notificationsCount);
        }
    };
    
    function replaceWithEmptyIfNotPresent(value) {
        return (value == null) ? "" : value;
    }

    var doLogout = function() {
        $.ajax({
            url: ACC.config.encodedContextPath + "/logout",
            async: false,
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function () {}
        });
    };

    SAGIA.notifications.updateNotifications = function() {
        $.ajax({
            url: ACC.config.encodedContextPath + "/my-sagia/notifications/popup",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (notificationData) {
                SAGIA.notifications.handleNotificationData(notificationData);
            }
        });
    };

    console.log(SAGIA.config.hasAwaitingPayment);
    if(SAGIA.config.hasLicense || SAGIA.config.hasAwaitingPayment) {
        SAGIA.notifications.updateNotifications();
        setInterval(function(){
            SAGIA.notifications.updateNotifications();
        }, 1800000);
    }
    //SAGIA NOTIFICATIONS HANDLING END

    jQuery.validator.addMethod("arabic", function(value, element) {
        var isArabic = /^([\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufbc1]|[\ufbd3-\ufd3f]|[\ufd50-\ufd8f]|[\ufd92-\ufdc7]|[\ufe70-\ufefc]|[\ufdf0-\ufdfd]|[ ])*$/g;
        return isArabic.test(value);
    }, "Only arabic text is accepted");

    //security implementation
    //disable back button (because otherwise according to the beforeunload, the user will be logged out as
    // there is no way to determine back is actually going to a different domain or the same
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
   /* $(window).on("beforeunload", function() {
        //doLogout();
        if(window.history.replaceState) {
            window.history.replaceState (null, null, ACC.config.encodedContextPath + "/logout");
        }
    });*/

    SAGIA.attachments = SAGIA.attachments || {};
    SAGIA.attachments.removeBase64Prefix = function(mimeType, content) {
        var prefixStr = 'data:'+mimeType+';base64,';
        var index = content.indexOf(prefixStr);
        if(index !== -1) {
            content = content.substring(index + prefixStr.length);
        }
        return content;
    };

    $(document).on("change", "input[type='text'], input[type='password']", function() { //IE fix
        var inputField = $(this);
        setTimeout(function() {
            if (inputField.val() && inputField.hasClass("placeholder-shown")) {
                inputField.removeClass("placeholder-shown");
            }
        }, 500);
    });
    $("input").on('animationstart', function(event) {
        var inputField = $(this);
        switch(event.originalEvent.animationName) {
            case 'autofill':
            case 'onAutoFillStart':
                inputField.addClass("placeholder-not-shown");
                break;
            case 'onAutoFillCancel':
                break;
        }
    });
    $("input").on('animationstart', function(event) {
        var inputField = $(this);
        switch(event.originalEvent.animationName) {
            case 'autofill':
            case 'onAutoFillStart':
                inputField.removeClass("placeholder-not-shown");
                break;
            case 'onAutoFillCancel':
                break;
        }
    });

    //automatically load draft and handle clear
    var urlLocation = window.location.href;
    if(urlLocation.indexOf("&loadDraft=false") === -1 && urlLocation.indexOf("?loadDraft=false") === -1) {
        var loadDraftButton = $(".js-load-draft");
        if(loadDraftButton.is(":visible")) {
            loadDraftButton.hide();
            setTimeout(function() {
                loadDraftButton.trigger("click");
            }, 500);
        }

        loadDraftButton.parent().append('<button class="btn btn_round btn_slim js-clear-draft">' + getI18nText("general.clearDraft") + ' <span class="iconElement iconElement_closeBack"><svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg></span></button>');
        $(document).on("click", ".js-clear-draft", function() {
            var documentURL = new URL(document.URL);
            documentURL.searchParams.set("loadDraft", "false");
            window.location.href = documentURL.toString();
        });
    }

    $(document).on("click", ".termsAndConditionsLink", function(event) {
        event.preventDefault();
        SAGIA.showTermsAndConditions($(this).attr("href") + " .content");
    });

    //DO NOT remove until we know for sure this doesn't help for IE
    // if (!Object.keys) {
    //     Object.keys = (function () {
    //         'use strict';
    //         var hasOwnProperty = Object.prototype.hasOwnProperty,
    //             hasDontEnumBug = !({toString: null}).propertyIsEnumerable('toString'),
    //             dontEnums = [
    //                 'toString',
    //                 'toLocaleString',
    //                 'valueOf',
    //                 'hasOwnProperty',
    //                 'isPrototypeOf',
    //                 'propertyIsEnumerable',
    //                 'constructor'
    //             ],
    //             dontEnumsLength = dontEnums.length;
    //
    //         return function (obj) {
    //             if (typeof obj !== 'object' && (typeof obj !== 'function' || obj === null)) {
    //                 throw new TypeError('Object.keys called on non-object');
    //             }
    //
    //             var result = [], prop, i;
    //
    //             for (prop in obj) {
    //                 if (hasOwnProperty.call(obj, prop)) {
    //                     result.push(prop);
    //                 }
    //             }
    //
    //             if (hasDontEnumBug) {
    //                 for (i = 0; i < dontEnumsLength; i++) {
    //                     if (hasOwnProperty.call(obj, dontEnums[i])) {
    //                         result.push(dontEnums[i]);
    //                     }
    //                 }
    //             }
    //             return result;
    //         };
    //     }());
    // }
});

  
function expandServiceTab(code){
    if (!$('#service-tab').hasClass('expanded')) {
        $.ajax(ACC.config.encodedContextPath + "/service-search/serviceDetails/"+code, {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                $('#service-tab').empty();
                if (data !== "") {
                    data = JSON.parse(data);
                    if (data.length) {
                        var service = "";
                        data.forEach(function (info) {
                            service += '<div class="serviceModule serviceModule_list mx-5 pt-4">';
                            service += '    <div class="serviceModule-section">';
                            service += '        <div class="serviceModule-content">';
                            service += '            <div class="serviceModule-description">';
                            service += '                <span class="serviceModule-headline"> ' + info.title + ' </span>';
                            if (info.content === "")
                            service += '                        <div cladata.contentss="serviceModule-detail serviceList-description"><div class="w-100"><p>N/A</p></div></div>';
                            else
                            service += '                        <div class="serviceModule-detail serviceList-description"><div class="service-info-anchor"><p>' + info.content + '</p></div></div>';

                            service += '            </div>';
                            service += '        </div>';
                            service += '    </div>';
                            service += '</div>';

                        })

                        $('#service-tab').append(service);
                        $('#service-tab').addClass('expanded');
                        $('#service-tab').show();
                        $(".serviceTab").text('Hide Service Tab')

                    }
                }else {
                    $('#service-tab').empty();
                    $('#service-tab').removeClass('expanded');
                    $('#service-tab').hide();
                    $(".serviceTab").text('Show Service Tab');
                }
            }
        });
    }
    else{
        $('#service-tab').empty();
        $('#service-tab').removeClass('expanded');
        $('#service-tab').hide();
        $(".serviceTab").text('Show Service Tab');
    }
}

$('.btn_show_hide_service').on('click',function(){
    $(this).children('div').each(function(){
        if($(this).is(":visible")) {
            $(this).addClass('hidden').hide();
            $("#expand-03").hide();
        } else {
            $(this).removeClass('hidden').show();
            $("#expand-03").show();
        }				
    });
})

 /**my potential**/ 
function commentTextArea() {
    var x = document.getElementById("comment_box_form");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}





$(document).ready(function () {
    window.onbeforeunload = function () {
        window.scrollTo(0, 0);
    }
    var overflow = "hidden";
    var footerHeight
    $(".float-button").click(function () {
        $(".popup").toggleClass("popup-up");
        $(".float-button").toggleClass("float-button-up");
        $(".full-bg").fadeToggle();
        $("body").css("overflow",overflow);
        // $(this).html($(this).text() == 'Contact Us' ? "<img width='40' src=''>": 'Contact Us');
        overflow = (overflow == "hidden") ? "visible" : "hidden";
    });

    $(window).on('scroll', function () {
        footerHeight = $("#footer").offset().top - 800;
        if ($(this).scrollTop() >= footerHeight) {
            $('.float-button').css({"display":"none","opacity": "0"});
        }
        else {
            $('.float-button').css({"display":"block","opacity": "1"});
        }
    });
});

$("#dashboardNoLicenseHelper").on('click',function(){
    $("#license-application-simulator").show();
})
   
$("#simulator-close").on('click',function(){
    var video=$('#simulator-video').attr("src");
    $('#simulator-video').attr("src",video);
    $("#license-application-simulator").hide();
})
   

function LanguageToggle(lang){
    if(lang === "en"){
        var url = window.location.href.replace('/ar/','/en/');
        window.location.href = url;
    }
    else if(lang === "ar"){
        var url = window.location.href.replace('/en/', '/ar/');
        window.location.href = url;
    }
}

!(function($) {
	"use strict";
  
	// Preloader
	// $(window).on('load', function() {
	//   if ($('#preloader').length) {
	// 	$('#preloader').delay(100).fadeOut('slow', function() {
	// 	  $(this).remove();
	// 	});
	//   }
	// });

	var lang = document.getElementsByTagName("html")[0].getAttribute("lang");
	if(window.location.pathname === "/" || window.location.pathname === ""){
		window.location.href = window.location.href + lang;
	}
	
	if(window.location.href.indexOf('recaptchaChallangeAnswered=false') >= 0){
		 var errorModal = $('#errorResponseModal');
         errorModal.find('.modal-description').text('Invalid Captcha, Please try again');
         errorModal.modal('show');	
	}

	// Smooth scroll for the navigation menu and links with .scrollto classes
	var scrolltoOffset = $('#header').outerHeight() - 1;
	$(document).on('click', '.nav-menu a, .mobile-nav a, .mandar, .scrollto', function(e) {
        console.log('clicked');
	  if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
		var target = $(this.hash);
		if (target.length) {
		  e.preventDefault();
  
		  var scrollto = target.offset().top - scrolltoOffset;
  
		  if ($(this).attr("href") == '#header') {
			scrollto = 0;
		  }
  
		  $('html, body').animate({
			scrollTop: scrollto
		  }, 1500, 'easeInOutExpo');
  
		  if ($(this).parents('.nav-menu, .mobile-nav').length) {
			$('.nav-menu .active, .mobile-nav .active').removeClass('active');
			$(this).closest('li').addClass('active');
		  }
  
		  if ($('body').hasClass('mobile-nav-active')) {
			$('body').removeClass('mobile-nav-active');
			$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
			$('.mobile-nav-overly').fadeOut();
		  }
		  return false;
		}
	  }
	});
  
	// Activate smooth scroll on page load with hash links in the url
	// $(document).ready(function() {
	//   if (window.location.hash) {
	// 	var initial_nav = window.location.hash;
	// 	if ($(initial_nav).length) {
	// 	  var scrollto = $(initial_nav).offset().top - scrolltoOffset;
	// 	  $('html, body').animate({
	// 		scrollTop: scrollto
	// 	  }, 1500, 'easeInOutExpo');
	// 	}
	//   }
	// });
  
	// Mobile Navigation
	if ($('.nav-menu').length) {
	  var $mobile_nav = $('.nav-menu').clone().prop({
		class: 'mobile-nav d-lg-none'
	  });
	  $('body').append($mobile_nav);
	  $('body').prepend('<button type="button" class="mobile-nav-toggle d-lg-none"><i class="icofont-navigation-menu"></i></button>');
	  $('body').append('<div class="mobile-nav-overly"></div>');
  
	  $(document).on('click', '.mobile-nav-toggle', function(e) {
		$('body').toggleClass('mobile-nav-active');
		$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
		$('.mobile-nav-overly').toggle();
	  });
  
	  $(document).on('click', '.mobile-nav .drop-down > a', function(e) {
		e.preventDefault();
		$(this).next().slideToggle(300);
		$(this).parent().toggleClass('active');
	  });
  
	  $(document).click(function(e) {
		var container = $(".mobile-nav, .mobile-nav-toggle");
		if (!container.is(e.target) && container.has(e.target).length === 0) {
		  if ($('body').hasClass('mobile-nav-active')) {
			$('body').removeClass('mobile-nav-active');
			$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
			$('.mobile-nav-overly').fadeOut();
		  }
		}
	  });
	} else if ($(".mobile-nav, .mobile-nav-toggle").length) {
	  $(".mobile-nav, .mobile-nav-toggle").hide();
	}
    
	// Mobile nav link
	
	var twit_lk='/';
	$('.mobile-nav').append('<div class="mobile-nav-logo"></div>');
	$('.mobile-nav-logo').wrap('<a class="mobile-nav-Icon" href="'+twit_lk+'"/>');
  
	// Navigation active state on scroll
	var nav_sections = $('section');
	var main_nav = $('.nav-menu, #mobile-nav');
  
	$(window).on('scroll', function() {
	  var cur_pos = $(this).scrollTop() + 200;
  
	  nav_sections.each(function() {
		var top = $(this).offset().top,
		  bottom = top + $(this).outerHeight();
  
		if (cur_pos >= top && cur_pos <= bottom) {
		  if (cur_pos <= bottom) {
			main_nav.find('li').removeClass('active');
		  }
		  main_nav.find('a[href="#' + $(this).attr('id') + '"]').parent('li').addClass('active');
		}
		if (cur_pos < 300) {
		  $(".nav-menu ul:first li:first").addClass('active');
		}
	  });
	});
  
	// Toggle .header-scrolled class to #header when page is scrolled
	// // Back to top button
	// $(window).scroll(function() {
	//   if ($(this).scrollTop() > 100) {
	// 	$('.back-to-top').fadeIn('slow');
	//   } else {
	// 	$('.back-to-top').fadeOut('slow');
	//   }
	// });
  
	// $('.back-to-top').click(function() {
	//   $('html, body').animate({
	// 	scrollTop: 0
	//   }, 1500, 'easeInOutExpo');
	//   return false;
	// });

	// Init AOS
	function aos_init() {
	  AOS.init({
		duration: 1000,
		easing: "ease-in-out",
		once: true
	  });
	}
	$(window).on('load', function() {
	  aos_init();
	});

  })(jQuery);
//  ------------------------------------------------------------------------------------------------------------------------- 


/* --------- Font-size JS --------*/

var original = 16;
var increment = 0, decrement = 3;

setFont();
function setFont() {
	var inc = getCookie("f_increment");
	// $("#font-decrement").hide();
	if(inc !== null && inc !== ""){
		increaseFontSize(1)
	}
}

function increaseFontSize(isSet = 0) {
  
    var p = document.querySelectorAll('body div,body p,body span, body h1, body h2, body h3, body h4, body h5, body h6');
	var s;
	if (increment < 3) {
		increment = isSet === 0 ? (increment + 1) : parseFloat(getCookie("f_increment"));	
		for (let i = 0; i < p.length; i++) {
			if ($(p[i]).css('font-size')) {
				s = parseFloat($(p[i]).css('font-size'));
			} else {
				s = original;
			}
			original = isSet === 0 ? (s + 0.5) : (s + increment *0.5);
			$(p[i]).css('font-size', original + "px");
		}
		setCookie("f_increment", increment, 30);
	}
	// if(increment >=3){
	// 	$("#font-increment").hide();
	// }
	// if(increment > 0){
	// 	$("#font-decrement").show();
	// }
}
function decreaseFontSize(isSet = 0) {
    var p = document.querySelectorAll('body div,body p,body span, body h1, body h2, body h3, body h4, body h5, body h6');	
	
	if (increment > 0) {
		increment = isSet === 0 ? (increment - 1) : parseFloat(getCookie("f_increment"));
		for (i = 0; i < p.length; i++) {
			if ($(p[i]).css('font-size')) {
				var s = parseFloat($(p[i]).css('font-size'));
			} else {
				var s = original;
			}
			original = isSet === 0 ? (s - 0.5) : (s - increment);
			$(p[i]).css('font-size', original + "px");
		}
		setCookie("f_increment", increment, 30);
	}
	// if(increment <=0){
	// 	$("#font-decrement").hide();
	// }
	// if(increment < 3){
	// 	$("#font-increment").show();
	// }
}
function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}



$(document).ready(function(){
    calheightServiceInfo();
})
window.addEventListener("resize", calheightServiceInfo);
function calheightServiceInfo(){
    var cal = $(".heightServiceInfo").height();
    if(window.matchMedia("(max-width:640px)").matches){
        $('.statusIndicator').css({ paddingTop : cal + 'px' });
    }
}