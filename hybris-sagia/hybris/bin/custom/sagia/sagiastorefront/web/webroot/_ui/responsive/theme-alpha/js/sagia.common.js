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
        window.location.href = ACC.config.encodedContextPath + "/logout?site=sagia";
    });
    $("#logoutModal .noButton").on("click", function() {
        $('#logoutModal').modal('hide');
    });

    //SAGIA NOTIFICATIONS HANDLING START
    SAGIA.notifications = SAGIA.notifications || {};
    SAGIA.notifications.updateNotificationsCount = function(notificationsCount) {
        var unreadNotificationSpan = $('#unreadNotificationSpan');
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
                            '           <span class="iconElement iconElement_expertProfile_green">' +
                            '               <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path class="iconElement-colorPrimary_fill" d="M12 0c6.627 0 12 5.373 12 12s-5.373 12-12 12-12-5.373-12-12 5.373-12 12-12z"/><path class="iconElement-colorSecondary_fill" d="M21.086 19.824c-2.201 2.553-5.451 4.176-9.086 4.176-3.567 0-6.761-1.565-8.959-4.036.846-.884 3.309-2.132 6.244-3.323v-1.8c-.166-.118-.343-.292-.512-.533-.39-.555-.63-1.336-.674-2.375-.441-.247-.715-.847-.765-1.503-.051-.676.158-1.267.616-1.538l-.174-.42c-.093-.231-.167-.44-.227-.642-.186-.62-.011-1.034.186-1.436.226-.459.395-.961 1.033-.912 1.205-2.449 6.619-2.044 7.297.671.204.815.776 1.56.44 2.416l-.125.296c.424.312.615.901.56 1.568-.056.656-.329 1.252-.769 1.499-.043 1.04-.284 1.821-.674 2.375-.168.241-.346.415-.512.533v1.8c2.786 1.131 5.143 2.311 6.101 3.184z"/></svg>' +
                            '           </span>';
                    } else if (notification.notificationType === 'SY' || notification.notificationType === 'Rest') {
                        notificationsForPopupHtml +=
                            '           <span class="iconElement">' +
                            '               <svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="1 1 44 44" enable-background="new 1 1 44 44"><path fill="#5BC83A" d="M22.787 28.315c-2.724.001-4.933-2.207-4.934-4.932s2.208-4.934 4.932-4.934h.001c2.725 0 4.932 2.209 4.932 4.934.001 2.724-2.206 4.931-4.931 4.932zm14.624-3.153v-3.56l-4.085-1.208c-.235-.827-.562-1.613-.974-2.349l2.033-3.745-2.516-2.516-3.745 2.033c-.742-.415-1.531-.741-2.349-.973l-1.21-4.087h-3.558l-1.211 4.086c-.827.234-1.613.562-2.349.972l-3.745-2.032-2.514 2.517 2.033 3.745c-.411.734-.739 1.521-.973 2.349l-4.085 1.211v3.558l4.085 1.211c.235.826.561 1.612.973 2.348l-2.034 3.745 2.517 2.516 3.745-2.033c.734.411 1.522.738 2.349.974l1.211 4.085h3.558l1.21-4.085c.817-.232 1.606-.559 2.349-.974l3.745 2.033 2.516-2.516-2.033-3.745c.411-.734.739-1.521.974-2.348l4.083-1.212z"/></svg>' +
                            '           </span>';
                    } else if (notification.notificationType === 'VI') {
                        notificationsForPopupHtml +=
                            '           <span class="iconElement">' +
                            '               <svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0.5 1 44 44" enable-background="new 0.5 1 44 44"><path stroke="#5CC83B" stroke-linecap="round" stroke-linejoin="round" d="M15.63 12.035c0-1.234 1.014-2.235 2.263-2.235 1.251 0 2.265 1 2.265 2.235 0 1.235-1.014 2.235-2.265 2.235m7.926-2.235c0-1.234 1.014-2.235 2.265-2.235 1.25 0 2.263 1 2.263 2.235 0 1.235-1.013 2.235-2.263 2.235" fill="none"/><path fill="#5CC83B" d="M33.465 11.535h-21.232c-.276 0-.5.224-.5.5v19.468999999999998c0 1.27 1.202 2.264 2.735 2.264h16.763c1.533 0 2.733-.994 2.733-2.264v-19.47c.001-.276-.223-.499-.499-.499zm-9.618 14.795c0 .954-.291 1.658-.871 2.109-.581.453-1.337.679-2.271.679-.987 0-1.735-.235-2.245-.703-.51-.47-.766-.996-.766-1.581 0-.243.061-.411.183-.503.122-.091.333-.138.629-.138.34 0 .571.044.694.131.121.088.183.237.183.446 0 .285.122.508.367.673.245.163.576.244.995.244.489 0 .844-.108 1.067-.321.222-.214.334-.559.334-1.036v-.239c0-.794-.48-1.191-1.44-1.191-.333 0-.498-.215-.498-.642s.169-.641.506-.641c.381 0 .676-.08.884-.243.207-.163.312-.489.312-.98 0-.733-.384-1.1-1.152-1.1-.777 0-1.166.28-1.166.837 0 .417-.271.626-.812.626-.367 0-.604-.049-.714-.145-.108-.095-.163-.28-.163-.556 0-.244.048-.485.144-.723.096-.241.247-.474.452-.702.206-.227.504-.412.896-.554.393-.144.852-.215 1.375-.215 1.85 0 2.774.753 2.774 2.257 0 .5-.09.936-.271 1.307-.181.372-.434.628-.76.77.888.334 1.332.969 1.332 1.896v.238zm4.475 2.199c0 .159-.084.285-.254.377-.184.095-.39.143-.597.137-.233 0-.437-.046-.603-.137-.166-.092-.248-.218-.248-.377v-6.708l-.419.403c-.087.075-.2.115-.314.112-.154.001-.302-.074-.392-.201-.103-.128-.158-.287-.156-.452 0-.26.106-.457.326-.59l1.545-1.131c.1-.081.224-.125.354-.125.191 0 .367.051.522.151.157.101.235.226.235.376v8.165zm4.643-12.524h-20.232v-3.471h20.231v3.471z"/></svg>' +
                            '           </span>';
                    }
                    else if (notification.notificationType === 'PY') {
                        notificationsForPopupHtml +=
                            '           <span class="iconElement">' +
                            '               <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="24px" height="24px" viewBox="0 0 24 24" enable-background="new 0 0 24 24" xml:space="preserve"> <path fill="#5CC83B" d="M24,17.715C24,18.977,23.065,20,21.913,20H2.87C0.935,20,0,18.977,0,17.715V6.286C0,5.023,0.935,4,2.087,4h19.826C23.065,4,24,5.023,24,6.286V17.715z"></path> <path fill="#FFFFFF" d="M5,14.5H2c-0.276,0-0.5-0.224-0.5-0.5s0.224-0.5,0.5-0.5h3c0.276,0,0.5,0.224,0.5,0.5S5.276,14.5,5,14.5zM15.5,14c0-0.276-0.224-0.5-0.5-0.5H7c-0.276,0-0.5,0.224-0.5,0.5s0.224,0.5,0.5,0.5h8C15.276,14.5,15.5,14.276,15.5,14zM12.346,16.666c0-0.276-0.224-0.5-0.5-0.5H2c-0.276,0-0.5,0.224-0.5,0.5s0.224,0.5,0.5,0.5h9.846C12.123,17.166,12.346,16.942,12.346,16.666z"></path> <path fill="#1C242C" d="M0,6h24v4H0V6z"></path></svg>'+
                            '           </span>';
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


