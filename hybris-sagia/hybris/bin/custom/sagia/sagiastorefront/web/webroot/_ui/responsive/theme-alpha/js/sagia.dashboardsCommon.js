$(document).ready(function() {
	$("#setCompanyPhotoAnchor").css("display", "block");
});

var SAGIA = SAGIA || {};
SAGIA.dashboard = {
    _autoload: [
        ["pie", '$(".js-dashboardWidgetPie-chart").length > 0'],
        ["dashboardTabs", '$(".js-dashboardTabs").length > 0'],
        ["dashboardWidgetNoLicense", '$(".js-dashboardWidgetNoLicense-tabs").length > 0'],
        ["dashboardWidgetBannerTabs", '$(".js-dashboardWidgetBanner-tabs").length > 0'],
        ["dashboardWidgetChartInfo", '$(".js-toggleChartInfo").length > 0'],
        "initRedirects"
    ],

    _enquireload: [
        ["screenSmMax", "match", "unmatch"]
    ],

    match: function () {
        $(document).on("click", ".js-dashboardWidget-headline", function (e) {
            e.preventDefault();
            if (!$(this).parent().hasClass("dashboardWidget_open")) {
                $(this)
                    .parents(".js-dashboard")
                    .find(".js-dashboardWidget.dashboardWidget_open")
                    .removeClass("dashboardWidget_open");
                $(this)
                    .parent()
                    .addClass("dashboardWidget_open");
            } else {
                $(this)
                    .parent()
                    .removeClass("dashboardWidget_open");
            }
        });

        //small match click on tabhead
		$(document).on("click",".js-dashboardWidgetNoLicense-tabs .tabhead", function(e){
			e.preventDefault();
			var self = $(this),
				tabBody = self.next(),
                tabHeads = self.parents('.js-dashboardWidgetNoLicense-tabs').find('.tabhead');

			if (self.hasClass('dashboardWidgetNoLicense-current') || tabBody.css('display') === 'block') {
				self.removeClass('dashboardWidgetNoLicense-current');
				tabBody.hide();
			} else {
                //reset heads
                tabHeads.removeClass('dashboardWidgetNoLicense-current');

				var id = $(this).attr("id");
				self.parents(".js-dashboardWidgetNoLicense-tabs").find("a[href='#" + id + "']").click();
			}
		});

		$(document).on("click",".js-dashboardTabs .tabhead", function(e){
			e.preventDefault();
			var self = $(this),
				tabBody = self.next(),
                tabHeads = self.parents('.js-dashboardTabs').find('.tabhead');
			if (self.hasClass('current') || tabBody.css('display') === 'block') {
				self.removeClass('current');
				tabBody.hide();
			} else {
                //reset heads
                tabHeads.removeClass('current');

				var id = $(this).attr("id");
				self.parents(".js-dashboardTabs").find("a[href='#" + id + "']").click();
			}
		});
    },

    unmatch: function () {
        $(document).off("click", ".js-dashboardWidget-headline");
        $(document).off("click", ".js-dashboardWidgetNoLicense-tabs .tabhead");
        $(document).off("click", ".js-dashboardTabs .tabhead");

        $(".js-dashboardWidgetNoLicense-tabs .tabs-list li.dashboardWidgetNoLicense-current a").click();
        $(".js-dashboardTabs .tabs-list li.dashboardTabs-current a").click();
    },

    dashboardWidgetChartInfo: function(){
        $(document).on('click', '.js-toggleChartInfo', function(e){
			e.preventDefault();

			$(this).closest(".dashboardWidgetCharts-info")
                .toggleClass("dashboardWidgetCharts-info_gender")
                .find(".dashboardWidgetCharts-toggleInfo").each(function() {
				if($(this).is(":visible")) {
					$(this).addClass('hidden');
				} else {
					$(this).removeClass('hidden');
				}
			});

            $(this).closest(".dashboardWidgetCharts-info")
                .find(".dashboardWidgetCharts-info-graphic").each(function() {
                if($(this).is(":visible")) {
                    $(this).addClass('hidden');
                } else {
                    $(this).removeClass('hidden');
                }
            });
        });
    },

    pie: function () {
        new Chart(document.getElementById("dashboardWidgetPie-chart"), {
            type: 'pie',
            data: {
                datasets: [{
                    data: [20, 80],
                    backgroundColor: ["#79c551", "#f3f3f3"]
                }],
                labels: [
                    'Red'
                ]
            },
            options: {
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: false
                },
                elements: {
                    arc: {
                        borderWidth: 0,
                        backgroundColor: "#f3f3f3"
                    }
                }
            }
        });

        new Chart(document.getElementById("dashboardWidgetPie-chart2"), {
            type: 'pie',
            data: {
                datasets: [{
                    data: [20, 80],
                    backgroundColor: ["#79c551", "#f3f3f3"]
                }],
                labels: [
                    'Red'
                ]
            },
            options: {
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: false
                },
                elements: {
                    arc: {
                        borderWidth: 0,
                        backgroundColor: "#f3f3f3"
                    }
                }
            }
        });
    },
    bar: function (dataArray) {
        new Chart(document.getElementById("js-dashboardWidgetCharts-chart"), {
            type: 'bar',
            options: {
                maintainAspectRatio: false,
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: false
                },
                scales: {
                    xAxes: [{
                        gridLines: {
                            display: false,
                            drawBorder: false
                        }
                    }],
                    yAxes: [{
                        gridLines: {
                            drawBorder: false,
                            color: "#c2c9d1",
                            lineWidth: 0.6
                        },
                        ticks: {
                            beginAtZero: true
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'Salary (SAR)'
                        }
                    }]
                }
            },
            data: {
                labels: ['Saudi-Average', 'Expat Average'],
                datasets: [{
                    label: 'My First dataset',
                    data: dataArray,
                    fill: false,
                    lineTension: 0,
                    backgroundColor: ["#7acc61", "#e6e6ed"],
                    hoverBackgroundColor: ["#7acc61", "#e6e6ed"]
                }]
            }
        });
    },

    dashboardTabs: function () {
        $(".js-dashboardTabs").accessibleTabs({
            wrapperClass: 'dashboardTabs-content',
            currentClass: 'dashboardTabs-current',
            tabhead: '.dashboardTabs-head',
            tabbody: '.dashboardTabs-body',
            fx: 'fadeIn',
            fxspeed: 'normal',
            currentInfoText: 'current tab: ',
            currentInfoPosition: 'prepend',
            currentInfoClass: 'dashboardTabs-currentInfo'
        });

        $(document).on("click", ".js-dashboardTabs .tabs-list a", function (e) {
            e.preventDefault();
            var currentID = $(this).attr("href");
            $(this)
                .parents(".js-dashboardTabs")
                .find('.dashboardTabs-content')
                .find(currentID)
                .addClass("current")
                .siblings()
                .removeClass("current")
                .prevAll()
                .removeClass("current");

            //add current state
            $(this).closest('.js-dashboardTabs')
                .find('.dashboardTabs-content')
                .find($(currentID))
                .addClass('current');
        });
    },

    dashboardWidgetNoLicense: function () {
        $(".js-dashboardWidgetNoLicense-tabs").accessibleTabs({
            wrapperClass: 'dashboardWidgetNoLicense-content',
            currentClass: 'dashboardWidgetNoLicense-current',
            tabhead: '.dashboardWidgetNoLicense-head',
            tabbody: '.dashboardWidgetNoLicense-body',
            fx: 'fadeIn',
            fxspeed: 'normal',
            currentInfoText: 'current tab: ',
            currentInfoPosition: 'prepend',
            currentInfoClass: 'dashboardWidgetNoLicense-currentInfo'
        }).children('.tabs-list').children('.dashboardWidgetNoLicense-current').each(function(){
				$(this)
                    .parents('.js-dashboardWidgetNoLicense-tabs')
                    .find('dashboardWidgetNoLicense-body')
                    .css('display','none');

				$($(this).children('a').attr('href'))
                    .addClass('dashboardWidgetNoLicense-current')
                    .next('.dashboardWidgetNoLicense-body')
                    .removeAttr('style');
		});

        //click on list in md view
        $(document).on("click", ".js-dashboardWidgetNoLicense-tabs .tabs-list a", function (e) {
            e.preventDefault();

            //reset current state
            var currentID = $(this).attr('href');
            $(this).closest('.js-dashboardWidgetNoLicense-tabs')
                .find('.dashboardWidgetNoLicense-content')
                .find($(currentID))
                .siblings()
                .removeClass("dashboardWidgetNoLicense-current")
                .prevAll()
                .removeClass("dashboardWidgetNoLicense-current");

            //add current state
            $(this).closest('.js-dashboardWidgetNoLicense-tabs')
                .find('.dashboardWidgetNoLicense-content')
                .find($(currentID))
                .addClass("dashboardWidgetNoLicense-current");
        });
    },

    dashboardWidgetBannerTabs: function () {
        $(".js-dashboardWidgetBanner-tabs").accessibleTabs({
            wrapperClass: 'dashboardWidgetBanner-tabs-content',
            currentClass: 'dashboardWidgetBanner-tabs-current',
            tabhead: '.dashboardWidgetBanner-tabs-head',
            tabbody: '.dashboardWidgetBanner-tabs-body',
            fx: 'fadeIn',
            fxspeed: 'normal',
            currentInfoText: 'current tab: ',
            currentInfoPosition: 'prepend',
            currentInfoClass: 'dashboardWidgetBanner-tabs-currentInfo'
        });

        $(document).on("click", ".js-dashboardWidgetBanner-tabs  .tabhead", function (e) {
            e.preventDefault();
            $(this).parents(".js-dashboardWidgetBanner-tabs").find("a[href='#" + $(this).attr("id") + "']").click();
        });

        $(document).on("click", ".js-dashboardWidgetBanner-tabs .tabs-list a", function (e) {
            e.preventDefault();
            $(this).parents(".js-dashboardWidgetBanner-tabs").find($(this).attr("href")).addClass("current").siblings().removeClass("current");
        });
    },

    initRedirects: function () {
        $(".js-page-redirect").click(function(event) {
            event.preventDefault();
            var redirectUrl = $(this).data("redirect");
            window.location.href = ACC.config.encodedContextPath + "/" + redirectUrl;
        })
    }
};

$(function () {
    var selector = $(".dashboardWidget-setting-switch .form-control");
    if (selector.length > 0) {
        selector.each(function () {
            if ($(this).is(':checked')) {
                return;
            }
            $(this).parents('.dashboardWidgetListComponent').addClass('dashboardWidgetListComponent_invisible');
        });
    }

     /*$(".dashboardBannerUpload").on('change', function () {
        $("#bannerUploadForm").submit();
    });*/

    $(".dashboardUser-image-add").on("click", function() {
        window.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile#myProfileTab";
    });

    $("#scheduleCallButton, #liveChatButton, #makeAnEnquiry").on("click", function(e) {
        e.preventDefault();
        $(".realTimeOnlineSupport-open").trigger("click");
    });

    $(document).on("click", ".dashboardWidgetTickets-btn", function() {
        var $modal = $('#enquiryDetail');
        var $modalBody = $modal.find('.modal-dialog');
        var complaintId = $(this).attr('data-complaint-id');
        $modalBody.load(ACC.config.encodedContextPath + "/complaints/" + complaintId, function() {
            $modal.modal();
        });
    });

    $(document).on("click", ".dashboardWidgetFinancialSurveys-btn", function() {
        var $modal = $('#errorResponseModal');
        var $modalBody = $modal.find('.modal-dialog');
        var complaintId = $(this).attr('data-complaint-id');
        $modalBody.load(ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/messages/" + complaintId, function() {
            $modal.modal();
        });
    });

    /*if(displayTutorial) {
        $('#eServiceTour').modal();
    }*/
});

$(document).on("click", "#setCompanyPhotoAnchor", function () {
    $.ajax({
        type: 'POST',
        url: ACC.config.encodedContextPath + "/dashboard/shouldSetNowCompanyPhoto",
        data: JSON.stringify({setNowCompanyPhoto: false}),
        cache: false,
        processData: false,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
            xhr.setRequestHeader('Accept', "application/json");
        },
        success: function () {
            $("#dashboardHeadId").css({
                display: "none",
                visibility: "hidden"
            });
        },
        error: function () {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.couldnot.update.requested.preference"));
            errorModal.modal('show');
        }
    });
    return false;
});
