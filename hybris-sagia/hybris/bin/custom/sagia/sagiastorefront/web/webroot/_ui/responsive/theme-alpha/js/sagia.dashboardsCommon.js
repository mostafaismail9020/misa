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
        $(document).on("click", ".js-dashboardWidget-headline-icon", function (e) {
            e.preventDefault();
            let idAttr = $(this).attr("id");
            if (!$(this).parent().hasClass("dashboardWidget_open")) {
                $(this)
                    .parents()
                    // .parents(".js-dashboard")
                    .find(".panel.dashboardWidget_open")
                    .removeClass("dashboardWidget_open");
                    $(this)
                    .parents().find(".panel-collapse.show").removeClass('show');
                    // .parents(".js-dashboard").find(".panel-collapse.show").removeClass('show');
                $(this)
                    .parent()
                    .addClass("dashboardWidget_open");
                    $('.'+idAttr).addClass('show');


            } else {

                $(this)
                    .parent()
                    .removeClass("dashboardWidget_open");
                    $('.'+idAttr).removeClass('show');
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
        $(document).off("click", ".js-dashboardWidget-headline-icon");
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
                .find('#'+$(currentID)[0].id)
                // .find($(currentID))
                .addClass('current');

                $(".dashboardTabs .js-dashboardTabs .dashboardTabs-body").hide();

                $(this).closest('.js-dashboardTabs')
                .find('.dashboardTabs-content')
                .find('#'+$(currentID)[0].id).next().show();
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


/*----dashboard-carousel---*/
$(document).ready(function() {
    $.fn.andSelf = function() {
        return this.addBack.apply(this, arguments);
      }
$('#dashboard-carousel').owlCarousel({
    loop: true,
    margin: 20,
    rtl:$("html").attr('lang') === 'en' ? false : true,
     /*
    animateOut: 'fadeOut',
    animateIn: 'fadeIn',
    */
    responsiveClass: true,
    autoplayTimeout: 7000,
    smartSpeed: 800,
    nav: false,
    dots: true,
    autoplay:true,
    autoplayHoverPause:false,
    // navText:["<img src='/_ui/responsive/common/images/dashboard-media/Banner-icons/Left-arrow.png'/>", "<img src='/_ui/responsive/common/images/dashboard-media/Banner-icons/Right-arrow.png''/>"],
    responsive: {
      0: {
        items: 1
      },

      600: {
        items: 1
      },

      1024: {
        items: 1
      },

      1366: {
        items: 1
      }
    }
  });
  if($("html").attr('lang') === 'en'){
    $('#dashboard-carousel').removeClass('owl-rtl')
}
else{
      $('#dashboard-carousel').addClass('owl-rtl');
  }
});
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
        // $(this).html($(this).text() == 'Contact Us' ? "<img width='40' src=''>": 'Contact Us');
        /*
        $("body").css("overflow", overflow);
        $(this).html($(this).text() == 'Invest Now' ? "<img width='40' src='./images/close.png'>" : 'Invest Now');
        $(this).html($(this).text() == "<img width='40' src='./img/close.png'>" ? 'Invest Now' : 'Invest Now');*/
        overflow = (overflow == "hidden") ? "visible" : "hidden";
    });

    $(window).on('scroll', function () {
		footerHeight = $("#footer").offset().top - 800;
		// console.log($(this).scrollTop())
		if ($(this).scrollTop() >= footerHeight) {
			$('.float-button').css({"display":"none","opacity": "0"});
		}
		else {
			$('.float-button').css({"display":"block","opacity": "1"});
		}
	});
});


// ------------------------------------------------------------------------------------------------------------------------

$(document).ready(function(){
    // Floating Button
	$(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            $('#header').addClass('header-scrolled');
            $('#topbar').addClass('topbar-scrolled');
            $('#user-icons').addClass('d-flex top-scrolled d-sm-none').find('d-none').removeClass('d-none');
            $('#login-Navigation').addClass('login-scrolled');
          } else {
            $('#header').removeClass('header-scrolled');
            $('#topbar').removeClass('topbar-scrolled');
            $('#user-icons').removeClass('d-flex top-scrolled d-sm-none').find('user-icons-header').addClass('d-none');
            $('#login-Navigation').removeClass('login-scrolled');
          }
        });

        if ($(window).scrollTop() > 100) {
          $('#header').addClass('header-scrolled');
          $('#topbar').addClass('topbar-scrolled');
          $('#user-icons').addClass('top-scrolled');
          $('#login-Navigation').addClass('login-scrolled');
        }

   // Video Modal Popup
	var url = $("#cartoonVideo").attr('src');
    $("#videoModal").on('hide.bs.modal', function(){
        $("#cartoonVideo").attr('src', '');
    });
    $("#videoModal").on('show.bs.modal', function(){
        $("#cartoonVideo").attr('src', url);
    });

    //sub-menu show
    $('.get_submenus').mouseover(function(){
		$('.dropdown-large').addClass("show_height");
	}).mouseout(function(){
		$('.dropdown-large').removeClass("show_height");
	});


    var financialSurveysTable = $("#financialSurveysTable");
    var financialSurveysSection = $("#yourFinancialSurveys");
    if(financialSurveysSection.closest('.js-component').css('display') !== 'none') {
        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/financialSurveys",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (financialSurveys) {
                if (financialSurveys && financialSurveys.surveys ) {
                    for (var index in financialSurveys.surveys) {
                        if (financialSurveys.surveys.hasOwnProperty(index)) {
                            var survey = financialSurveys.surveys[index];
                            var template = $(".financialSurveyTemplateWrapper").find("tbody").clone();
                            //template.find(".lastUpdate").html(ticket.lastUpdateData.dateFormatted);
                            template.find(".quarter").html(survey.quarter);
                            template.find(".lastUpdate").html(survey.lastUpdate);
                            template.find(".dashboardWidgetTickets-status-open").html(getI18nText(survey.status));
                            template.find(".dashboardWidgetTickets-status-open").addClass(function () {
                                switch (survey.status) {
                                    case 'PROCESSED':
                                        return 'dashboardWidgetTickets-status_code01';
                                    case 'OPEN':
                                    case 'UPDATED':
                                    case 'IN_PROGRESS':
                                    case 'SUBMITTED':
                                        return 'dashboardWidgetTickets-status_code02';
                                    case 'WAITING_FOR_CUSTOMER_ACTION':
                                    default:
                                        return 'dashboardWidgetTickets-status_code03';
                                }
                            });

                            if (survey.status == 'OPEN' || survey.status == 'WAITING_FOR_CUSTOMER_ACTION' || survey.status == 'IN_PROGRESS' || survey.status == 'UPDATED') {
                                //template.find(".dashboardWidgetFinancialSurvey-btn").find("a").text(getI18nText("financial.survey.button.fill.survey"));
                                template.find(".dashboardWidgetFinancialSurvey-btn").find("a").text(getI18nText("financial.survey.button.fill.survey"));
                                template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr("class","btn btn_slim draftContinueBtn");
                                // template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr("style","float: left;");
                                template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr('href', ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/display/" + survey.quarterCode);
                            }
                            template.find(".dashboardWidgetFinancialSurveys-btn").attr("data-complaint-Id", survey.quarterCode);
                            financialSurveysTable.append(template.html());
                        }
                    }
                }
            },
            error: function () {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').html(getI18nText("general.couldnot.load.tickets"));
                errorModal.modal('show');
                $('.dashboardWidgetTickets .loadingModule .loadingModule-icon').hide();
                $('.dashboardWidgetTickets .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.tickets')+"</span>");
            }
        });
    }

});





$(document).on('click',".services-category-list .nav .nav-link",function(){
    $(".service_tab_pane_show").removeClass("show").removeClass("active");
      $("#"+$(this).attr('href').replace("#","")).addClass("show").addClass("active");
      $("#"+$(this).attr('href').replace("#","")+"1").addClass("show").addClass("active");

      $(".services-category-list .nav .nav-link").removeClass("active");
      $(this).addClass("active");
  });





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



function biddingCertificate(e){
    window.location = ACC.config.encodedContextPath +  "/my-sagia/license/bidding?loadDraft=false";
}


function getAccordion(element_id,screen)
{

	if ($(window).width() < screen)
	{
		var concat = '';
		obj_tabs = $( element_id + " li" ).toArray();
		obj_cont = $( ".tab-content .tab-pane" ).not('.services-container-tabcontent').toArray();

		jQuery.each( obj_tabs, function( n, val )
		{

			concat += '<div id="' + n + '" class="panel panel-default">';
			concat += '<div class="panel-heading dashboardWidget-headline js-dashboardWidget-headline-icon" role="tab" id="heading' + n + '">';
			concat += '<h5 class="panel-title"><img class="imageCertifcate" src="'+$(val).find("img").attr("src")+'" /><a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse' + n + '" aria-expanded="false" aria-controls="collapse' + n + '">' + val.innerText + '</a><h5>';
			concat += '</div>';
			concat += '<div id="collapse' + n + '" class="panel-collapse collapse heading' + n + '" role="tabpanel" aria-labelledby="heading' + n + '">';
			concat += '<div class="panel-body">' + obj_cont[n].innerHTML + '</div>';
			concat += '</div>';
			concat += '</div>';
		});
		$("#accordion").empty().append(concat);
		$("#accordion").find('.panel-collapse:first').addClass("in");
		$("#accordion").find('.panel-title a').attr("aria-expanded","true");
		$("#accordion").find('.panel-title a').removeClass("collapsed");
		$(element_id).not('.services-container-tabcontent').hide();
		$(".tab-content").not('.services-container-tabcontent').hide();
	}
    else{
        $("#accordion").empty();
        $(element_id).show();
		$(".tab-content").not('.services-container-tabcontent').show();
    }
}


function getAccordionWithLicense(element_id,screen)
{
	if ($(window).width() < screen)
	{
        $(element_id).hide();
        $("#accordionDashboard").show();
		$(".tab-content").not('.services-container-tabcontent').hide();
	}
    else{
        $(element_id).show();
        $("#accordionDashboard").hide();
		$(".tab-content").not('.services-container-tabcontent').show();
    }
}

$(".sagiaNavigation-services .nav-link").on('click',function(){
    $(this).toggleClass('show active');
    var tab = $(this)[0].id;
    $(".service_tab_pane_show[aria-labelledby='"+ tab + "']").toggleClass('show active');
})