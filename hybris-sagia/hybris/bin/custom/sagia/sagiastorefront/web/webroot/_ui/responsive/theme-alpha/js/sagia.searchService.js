$( document ).ready(function() {
    $("#serviceModule").show();
    $('.serviceList-link, .serviceMenu-body-link').on('click', function(e){
        e.preventDefault();
    });
});

$("#searchInputBox").on('input', function () {
    var value = $(this).val();
    var serviceMenu = $(".serviceMenu");
    var serviceModule = $("#serviceModule");

    setTimeout(function() {
        $.ajax({
            type: "GET",
            url: ACC.config.encodedContextPath + "/service-search/search/" + value,
            cache: false,
            success: function (response) {
                serviceMenu.empty();
                if (response.trim() !== '') {
                    var obj = JSON.parse(response);
                    serviceModule.empty();
                    serviceModule.show();
                    for (var key in obj) {
                        if (obj.hasOwnProperty(key)) {
                            var icon = obj[key][0].category.icon != null ? obj[key][0].category.icon.url : " ";
                            var categoryKey = key.replace(/ /g, "_").replace("&", "_");
                            serviceMenu.append(
                                "<li class=\"serviceMenu-item\">" +
                                "<a href=\"\" class=\"serviceMenu-head js-serviceMenu\">" +
                                "<span class=\"serviceMenu-head-icon\">" +
                                "<img src='" + icon + "' height=\"22\" width=\"22\" >" +
                                "</span>" +
                                "<div class=\"serviceMenu-head-title\">" + key + "</div>" +
                                "<div class=\"serviceMenu-head-action\">" +
                                "<div>&#43;</div>" +
                                "<div class=\"hidden\">&#8722;</div>" +
                                "</div>" +
                                "</a>" +
                                "<ul class=\"serviceMenu-body\" id='" + categoryKey + "'>"
                                + "</ul></li>");

                            serviceModule.append(
                                "<div class=\"serviceModule-section\">" +
                                "<div class=\"serviceModule-content\">" +
                                "<div class=\"serviceModule-icon\">" +
                                "<img src='" + icon + "'>" +
                                "</div>" +
                                "<div class=\"serviceModule-description\">" +
                                "<div class=\"serviceModule-headline\">" + key + "</div>" +
                                "<div class=\"serviceModule-count\">" + obj[key].length + " " + getI18nText("searchservice.services") + " </div>" +
                                "<div class=\"serviceModule-detail\">" +
                                "<p>" + obj[key][0].category.description + "</p>" +
                                "</div>" +
                                "</div>" +
                                "<div class=\"serviceModule-actions\">" +
                                "<button class=\"serviceModule-action js-expandService\">" +
                                "<div>" + getI18nText("searchservice.showallservices") + "<span><icon:arrow_green_down/></span></div>" +
                                "<div class=\"hidden\">" + getI18nText("searchservice.hideallservices") + "<span><icon:arrow_green_up/></span></div>" +
                                "</button>" +
                                "</div>" +
                                "<span class=\"serviceModule-arrow\"></span>" +
                                "</div>" +
                                "<div class=\"serviceModule-sectionDetail\">" +
                                "<ul class=\"serviceList\" id='section_" + categoryKey + "'>" +
                                "</ul>" +
                                "<div class=\"serviceModule-actions\">" +
                                "<button class=\"serviceModule-action js-expandService\">" +
                                "<div>Hide all services<span><icon:arrow_green_up/></span></div>" +
                                "</button>" +
                                "</div>" +
                                "</div>" +
                                "</div>"
                            );
                            for (var item in obj[key]) {
                                if (obj[key].hasOwnProperty(item)) {
                                    $("#" + categoryKey).append(
                                        "<li class=\"serviceMenu-body-item\" data-code='" + obj[key][item].code + "'" +
                                        " data-name='" + obj[key][item].name + "'" +
                                        " data-url='" + obj[key][item].url + "'>" +
                                        "<a href=\"#\" class=\"serviceMenu-body-link\">" + obj[key][item].name + "</a>" +
                                        "</li>"
                                    );
                                    $("#section_" + categoryKey).append(
                                        "<li class=\"serviceList-item\">" +
                                        "<a href=\"#\" class=\"serviceList-link\">" +
                                        "<div class=\"serviceList-headline\" data-code='" + obj[key][item].code + "'" +
                                        " data-name='" + obj[key][item].name + "'" +
                                        " data-url='" + obj[key][item].url + "'>" + obj[key][item].name + "</div>" +
                                        "</a>" +
                                        "<div class=\"serviceList-description\"><p>" + obj[key][item].description + "</p>" +
                                        "</div>" +
                                        "</li>"
                                    );
                                }
                            }
                        }
                    }
                } else {
                    serviceModule.hide();
                }
            },
            error: function () {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
                errorModal.modal('show');
            }
        });
    }, 1000);        


});

$(document).on('click', '.serviceMenu-body-item, .serviceList-headline', function (e) {
    e.stopPropagation();
    e.preventDefault();
    var value = $(this).attr('data-code');
    var name = $(this).attr('data-name');
    var url = $(this).attr('data-url');
    
//    history.pushState(null, null, url.match(/[^/]+$/g)[0]);
//    
//    window.addEventListener('popstate', function(e) {
//        location.reload();
//    });    
    
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/service-search/serviceDetails/" + value,
        cache : false,
        success : function(response) {
            $("#service-details-title").html(name);
            $("#service-request-button").attr("href", url);
            var serviceDetailsTab = $("#service-details-tabs");
            serviceDetailsTab.empty();
            if(response.trim() !== '') {
                var obj = JSON.parse(response);
                for (var key in obj) {
                    if(obj.hasOwnProperty(key)) {
                        serviceDetailsTab.append(
                            "<div class=\"panelTabs-head\">" +
                            "<span class=\"panelTabs-label\">" + obj[key].title + "</span>" +
                            "</div>" +
                            "<div class=\"panelTabs-body\" " +
                            "<div class=\"contentModule-section contentModule-section_noDivider contentModule-section_slimDivider\">" +
                            "<p>" + obj[key].content + "</p>" +
                            "</div>" +
                            "</div>"
                        );
                    }
                }
                $("#contentModule").show();
                $("#toggle-section").hide();
                $("#serviceModule").hide();
                $('html, body').animate({scrollTop: 0}, 400);
            }
            SAGIA.panelTabs.init();
            
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
