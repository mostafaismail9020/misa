$(function () {
    $("#dashboardNoLicenseHelper").load(ACC.config.encodedContextPath + '/cms/sagia-cms-dashboard-no-license main', function() {
        SAGIA.dashboard.dashboardWidgetNoLicense();
        SAGIA.dashboard.dashboardWidgetBannerTabs();
    });

    $(".dashboardUser-image-add").on("click", function() {
        window.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile#myProfileTab";
    });

    $("#scheduleCallButton, #liveChatButton, #makeAnEnquiry").on("click", function(e) {
        e.preventDefault();
        $(".realTimeOnlineSupport-open").trigger("click");
    });

    /*if(displayTutorial) {
        $('#eServiceTour').modal();
    }*/
});

function dismissDashboardWithoutLicenceTutorial() {
    $.ajax({
        url: ACC.config.encodedContextPath + "/dashboard-without-license/dismiss-tutorial",
        method: 'GET',
        ajaxHideLoadingIndicator: false,
        success: function (data) {
        },
        error: function(data) {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText('general.dismiss.tutorial'));
            errorModal.modal('show');
        }
    });
}


$(document).on("click","#applyButton", function() {
    $.ajax({
        url: ACC.config.encodedContextPath + "/dashboard-without-license/getUnifiedLicenseUrl",
        method: 'GET',
        async : false,
        success: function (data) {
            $("#applyButton").attr("href", data);
        },
        error: function (e) {
        }
    });
});
