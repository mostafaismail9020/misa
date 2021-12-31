
var toggleBtn = document.querySelector("button.navbar-toggler");
var navbarList = document.getElementById('navbarResponsive');
var energyTable = document.querySelectorAll(".energy-table ");
var mailingListIds = [];
if (toggleBtn != null) {
    toggleBtn.addEventListener("click", function () {
        if (navbarList.classList.contains("collapse") == true) {
            navbarList.classList.remove("collapse");
            toggleBtn.classList.add("active");
        } else {
            navbarList.classList.add("collapse");
            toggleBtn.classList.remove("active");
        }
    });
}
const mq = window.matchMedia("(max-width: 767px)");
if (mq.matches == true) {
    for (var i = 0; i < energyTable.length; i++) {
        energyTable[i].classList.add("table-responsive");
    }
}

var site = {};
site = {
    utils: {
        language: function () {
            return {
                value: 0,
                langId: 1,
                url: site.utils.isRTL() ? 'ar' : 'en',
                locale: site.utils.isRTL() ? 'Arabic' : 'English',
                dir: site.utils.isRTL() ? 'rtl' : 'ltr'
            };
        },
        isMobile: function () {
            return document.body.clientWidth <= 992 ? true : false;
        },
        checkSize: function (_size) {
            return document.body.clientWidth <= _size ? true : false;
        },
        isRTL: function () {
            return window.location.pathname.split("/")[1] == "en" ? false : true;
        }
    },
    messages: function () {
        if (window.location.pathname.split("/")[1] == "ar") {
            if (window.location.href.indexOf("china") > 0)
                return site.messages_cn;
            return site.messages_ar;
        }
        else
            return site.messages_en;
    },
    messages_cn: {
        required: "必填",
        maxChars: "不超过{0}个字符",
        charLimits: "must be {0} - {1} characters",
        invalidEmail: "邮件地址无效",
        invalidMobile: "手机号码无效",
        searchBoxPlaceholder: "",
        noRecords: "No records",
        noSplChars: "只能输入字母",
        mirRobot: "Please verify that you are not a robot",
        iisMalformed: "The secret parameter is invalid or malformed",
        iirResponse: "The secret parameter is invalid or malformed",
        misSecret: "The secret parameter is missing",
        sending: "提交中...",
        formSubmissionFailed: 'فشل تقديم النموذج. حاول مرة أخرى في وقت لاحق'
    },
    messages_en: {
        required: "required",
        maxChars: "max {0} characters",
        charLimits: "must be {0} - {1} characters",
        invalidEmail: "invalid email",
        invalidMobile: "invalid mobile number",
        searchBoxPlaceholder: "قم بالبحث عن طريق النشاط أو الترخيص أو خدمة إلكترونية",
        noRecords: "No records",
        noSplChars: "Only letters are allowed",
        mirRobot: "Please verify that you are not a robot",
        iisMalformed: "The secret parameter is invalid or malformed",
        iirResponse: "The secret parameter is invalid or malformed",
        misSecret: "The secret parameter is missing",
        sending: "Sending...",
        uploadFileInvalidFormat: 'Invalid Format',
        uploadFilesMaxlimit: 'Max 1 files can be uploaded',
        uploadFileSizeMaxlimit: 'Max. file size is 4 MB',
        formSubmissionFailed: 'Form Submission failed. Try again later'
    },
    messages_ar: {
        required: "مطلوب",
        maxChars: "ماكس {0} حرفا",
        charLimits: "يجب أن يكون {0} - {1} حرفا",
        invalidEmail: "بريد إلكتروني خاطئ",
        invalidMobile: "رقم الجوال غير صالح",
        searchBoxPlaceholder: "قم بالبحث عن طريق النشاط أو الترخيص أو خدمة إلكترونية",
        noRecords: "لا توجد نتائج",
        noSplChars: "الرجاء إدخال أحرف فقط",
        mirRobot: "Please verify that you are not a robot",
        iisMalformed: "The secret parameter is invalid or malformed",
        iirResponse: "The secret parameter is invalid or malformed",
        misSecret: "The secret parameter is missing",
        sending: "إرسال...",
        uploadFileInvalidFormat: 'تنسيق غير صالح',
        uploadFilesMaxlimit: 'أقصى 5 ملفات يمكن تحميلها',
        uploadFileSizeMaxlimit: 'الحد الأقصى لحجم الملف هو 2 ميغابايت',
        formSubmissionFailed: 'فشل تقديم النموذج. حاول مرة أخرى في وقت لاحق'
    }
}

var URL_Array = window.location.pathname.split("/");
var CurrentPage = URL_Array[2];
if ("/" + URL_Array[URL_Array.length - 2] + "/" == "/" + site.utils.language().url + "/") {
    CurrentPage = "default.aspx"
}
if ("/" + URL_Array[URL_Array.length - 2] + "/" + URL_Array[URL_Array.length - 1] == "/" + site.utils.language().url + "/default.aspx") {
    CurrentPage = "default.aspx"
}
if (URL_Array.length > 2) {
    var SubCurrentPage = URL_Array[2].toLowerCase();
    if (URL_Array[URL_Array.length - 1] == "") {
        SubCurrentPage = "default.aspx"
    }
}

$(function () {
    $(".navbar-nav > li > a[href*='" + CurrentPage + "']").parent("li").addClass("active");
    $(".navbar-nav > li.arabic").removeClass("active");
    $(".relatedLinks > a[href*='" + window.location.pathname + "']").addClass("active");
    $("#subList").click(function () {
        $("#divSubscList").show();
    });
    $(".img-swap").hover(function () {
        $(this).find("img").attr("src", $(this).find("img").attr("data-alt"));
    }, function () {
        $(this).find("img").attr("src", $(this).find("img").attr("data-norm"));
    });

    /* Success Stories Search Begins*/
    if (getParameterByName("q") != null) {
        $(".succ-search-container input").val(getParameterByName("q"));
    }
    var sectorIds = [];
    var contractIds = [];

    if (getParameterByName("sectorIds") != null) {
        var _secIds = getParameterByName("sectorIds").split(",");
        $.each(_secIds, function (i, val) {
            sectorIds.push(val);
            $(".succ-search-container a[data-sectorId=" + val + "]").addClass("selected");
        });
    }
    $(".succ-search-container a[data-sectorId]").on("click", function () {
        var _sectorId = $(this).attr("data-sectorId");
        if (_sectorId == 0) {
            sectorIds = [];
            sectorIds.push(0);
            $(".succ-search-container a[data-sectorId]").removeClass("selected");
            $(this).addClass("selected");
        }
        else {
            if ($.inArray(_sectorId, sectorIds) >= 0) {
                sectorIds.splice(sectorIds.indexOf(_sectorId), 1);
                $(this).removeClass("selected");
            }
            else {
                sectorIds.push(_sectorId);
                $(this).addClass("selected");
                window.dataLayer = window.dataLayer || [];
                dataLayer.push({
                    'event': 'fire_event',
                    'category': window.location.href.indexOf("success-stories") > 0 ? 'Success Stories' : 'Opportunities',
                    'action': 'Filters Click',
                    'label': $.trim($(this).text())
                });
            }
        }
    });

    if (getParameterByName("contractIds") != null) {
        var _contractIds = getParameterByName("contractIds").split(",");
        $.each(_contractIds, function (i, val) {
            contractIds.push(val);
            $(".succ-search-container a[data-contractId=" + val + "]").addClass("selected");
        });
    }
    $(".succ-search-container a[data-contractId]").on("click", function () {
        var _contractId = $(this).attr("data-contractId");
        if (_contractId == 0) {
            contractIds = [];
            contractIds.push(0);
            $(".succ-search-container a[data-contractId]").removeClass("selected");
            $(this).addClass("selected");
        }
        else {
            if ($.inArray(_contractId, contractIds) >= 0) {
                contractIds.splice(contractIds.indexOf(_contractId), 1);
                $(this).removeClass("selected");
            }
            else {
                contractIds.push(_contractId);
                $(this).addClass("selected");
                dataLayer.push({
                    'event': 'fire_event',
                    'category': window.location.href.indexOf("success-stories") > 0 ? 'Success Stories' : 'Opportunities',
                    'action': 'Filters Click',
                    'label': $.trim($(this).text())
                });
            }
        }
    });
    $(".succ-search-container input").on("keyup", function (e) {
        if (e.currentTarget.value.length > 0) {
            $(e.currentTarget).next("a").addClass("clearSearch").find(".fa").removeClass("fa-search").addClass("fa-times-circle");
        }
    });
    $(".succ-search-container a.a-search").on("click", function () {
        if ($(this).hasClass("clearSearch")) {
            $(".succ-search-container input").val("");
            $(this).find(".fa").removeClass("fa-times-circle").addClass("fa-search");
        }
    });
    $(".succ-search-container .search-btn").on("click", function () {

        window.dataLayer = window.dataLayer || [];
        dataLayer.push({
            'event': 'fire_event',
            'category': window.location.href.indexOf("success-stories") > 0 ? 'Success Stories' : 'Opportunities',
            'action': 'Apply Search'
        });

        var search = "q=" + $(".succ-search-container input").val();
        if (sectorIds.length > 0 && sectorIds != 0)
            search += "&sectorIds=" + sectorIds;
        if (contractIds.length > 0 && contractIds != 0)
            search += "&contractIds=" + contractIds;

        if (search != "q=") {
            window.location.href = window.location.pathname + "?" + search;
        }
        if (search == "q=" && (sectorIds == 0 || contractIds == 0)) {
            window.location.href = window.location.pathname;
        }
    });
    $("a.reset-search").on("click", function () {
        window.location.href = window.location.pathname;
    });

    /* Success Stories Search Ends*/
    if ($(".ddl-countryCode").length > 0) {
        $(".ddl-countryCode").intlTelInput({
            preferredCountries: ['sa'],
            customPlaceholder: function (selectedCountryPlaceholder, selectedCountryData) {
                return "+" + selectedCountryData.dialCode;
            },
            utilsScript: ACC.config.commonResourcePath + "/intlTelInput/js/utils.js"
        });

        $(".ddl-countryCode").on("countrychange", function (e, countryData) {
           $(this).val("+" + countryData.dialCode);
        });
    }

    //Get Mailing List

    // $.get("/umbraco/api/newsletter/getmailinglist?lang=" + site.utils.language().url)
    //     .done(function (data) {
    //         $.each(data, function (i, e) {
    //             var list = "<li><a data-id='" + e.ID + "'>" + e.Name + "</a></li>";
    //             $("#divSubscList ul").append(list);
    //         });
    //
    //         $("#divSubscList ul li a").on("click", function () {
    //             selectSubsc($(this).attr("data-id"));
    //             if ($(this).hasClass("selected")) {
    //                 $(this).removeClass("selected");
    //             }
    //             else {
    //                 $(this).addClass("selected");
    //             }
    //         });
    //     });

    var isCdd = getCookie("cookieAccept");
    if (isCdd == "" || isCdd != "accepted") {
        setCookie("cookieAccept", "displayed");
    }

    setTimeout(function () {
        if (isCdd == "" || isCdd != "accepted") {
            document.getElementsByClassName("cookie-desclaimer")[0].classList.add("active");
        }
    }, 1);

    $(".cookie-desclaimer .fa,.cookie-desclaimer a").on("click", function () {
        document.getElementsByClassName("cookie-desclaimer")[0].classList.remove("active");
        setCookie("cookieAccept", "accepted", 365);
    });

    if (site.utils.isMobile()) {
        $(".list-about .nav-link").on("click", function () {
            return false;
        });
    }
});

/* newsletter subscription begins*/
// collect subscription list ids

function closeMailingList() {
    $("#divSubscList").hide();
}
function selectSubsc(sid) {
    var _subId = sid;
    if ($.inArray(_subId, mailingListIds) >= 0) {
        mailingListIds.splice(mailingListIds.indexOf(_subId), 1);
    }
    else {
        mailingListIds.push(sid);
    }
    document.getElementById("hfNewsSubsId").value = mailingListIds;
    document.getElementById("subList").innerText = mailingListIds.length;
    if (mailingListIds.length == 0) {
        document.getElementById("subList").innerText = document.getElementById("subList").getAttribute("data-default-text");
    }
}
// form submission
var nlbtn = document.getElementsByClassName('subscribe-btn');
function onNewsletterSub() {
    nlbtn.onclick = validateNL(nlbtn, "#newsletterSubs");
}
function validateNL(event, container) {
    if (validateForm($(event).parents(container)) == true) {
        onNewsletterSubscriptionSubmit();
        return true;
    }
    return false;
}
function onNewsletterSubscriptionSubmit() {

    if ($("#divSubscList:visible").length > 0) {
        $("#divSubscList").hide();
        document.getElementById("subList").innerText = document.getElementById("subList").getAttribute("data-default-text");
    }

    $.ajax({
        url: '/umbraco/surface/newsletter/subscribe',
        async: true,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify({
            Email: $.trim($("#newsEmail").val()),
            SubscriptionIds: $("#hfNewsSubsId").val(),
            Lang: site.utils.language().url
        }),
        success: function (data) {
            if (data) {
                mailingListIds = [];
                document.getElementById("hfNewsSubsId").value = 0;
                document.getElementById("newsEmail").value = null;
                $("#divSubscList ul li a").removeClass("selected");
                $(".subscribe-text").addClass("d-none");
                $(".ns-form-succ").removeClass("d-none");
                //
                dataLayer.push({
                    'event': 'fire_event',
                    'category': 'Newsletter Subscription',
                    'action': 'Successful Submit'
                });
            }
        }
    });
}
/* newsletter subscription ends */
/* general form validation */
function validateForm(ctrl) {
    var valid = true;
    if (ctrl.length == 0) {
        valid = false;
        return valid;
    }
    $.each($('.required', ctrl), function (i, e) {
        $(this).parent().find("label em").remove();
        var _label = $(this).parent().find("label").html();
        if ($.trim($(this).val()) == null || $.trim($(this).val()) == 0) {
            valid = false;
            $(this).addClass('error');
            $(this).parent().addClass('error');
            $(this).parent().find("label").html(_label + " <em><span>" + site.messages().required + "</span></em>");
        }
        else if (!$(this).hasClass("md-textarea") && !$(this).hasClass("validate-email") && ($(this).val().length < 1 || $(this).val().length > 999)) {
            valid = false;
            $(this).addClass('error');
            $(this).parent().addClass('error');
            $(this).parent().find("label").html(_label + " <em><span>" + site.messages().maxChars.replace("{0}", 999) + "</span></em>");
        }
        else {
            $(this).removeClass('error');
            $(this).parent().removeClass('error');
        }
    });
    $.each($('.validate-name', ctrl), function (i, e) {
        var pattern = new RegExp(/^[-\sa-zA-Z,\u0600-\u06FF]+$/);
        var _label = $(this).parent().find("label").html();
        if (pattern.test($.trim($(this).val()))) {
            if (valid) {
                $(this).removeClass('error');
                $(this).parent().find("label em").remove();
                $(this).parents("div." + ctrl.attr("id")).find(".validation-name").hide();
            }
        }
        else {
            $(this).addClass('error');
            if ($(this).parent().find("label").find("em").length == 0) {
                $(this).parent().find("label").html(_label + " <em><span>" + site.messages().noSplChars + "</span></em>");
            }
            $(this).parents("div." + ctrl.attr("id")).find(".validation-name").show();
            valid = false;
        }
    });
    $.each($('.validate-email', ctrl), function (i, e) {
        var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
        var _label = $(this).parent().find("label").html();
        if (pattern.test($.trim($(this).val()))) {
            if (valid) {
                $(this).removeClass('error');
                $(this).parent().removeClass('error');
                $(this).parent().find("label em").remove();
                $(this).parents("div." + ctrl.attr("id")).find(".validation-email").hide();
            }
        }
        else {
            $(this).addClass('error');
            $(this).parent().addClass('error');
            if ($(this).parent().find("label").find("em").length == 0) {
                $(this).parent().find("label").html(_label + " <em><span>" + site.messages().invalidEmail + "</span></em>");
            }
            $(this).parents("div." + ctrl.attr("id")).find(".validation-email").show();
            valid = false;
        }
    });
    $.each($('.validate-mobile', ctrl), function (i, e) {
        var pattern = /^(?!0.)\d+$/;
        var _label = $(this).parent().find("label").html();
        if (pattern.test((parseInt($(this).val())))) {
            if (valid) {
                $(this).removeClass('error');
                $(this).parent().removeClass('error');
                $(this).parent().find("label em").remove();
                $(this).parents("div." + ctrl.attr("id")).find(".validation-mobile").hide();
            }
        }
        else {
            $(this).addClass('error');
            $(this).parent().addClass('error');
            if ($(this).parent().find("label").find("em").length == 0) {
                $(this).parent().find("label").html(_label + " <em><span>" + site.messages().invalidMobile + "</span></em>");
            }
            $(this).parents("div." + ctrl.attr("id")).find(".validation-mobile").show();
            valid = false;
        }
    });
    $.each($('.validate-url', ctrl), function (i, e) {
        if ($.trim($(this).val()) != "" && $.trim($(this).val()) != null) {
            var pattern = new RegExp('https?:\/\/(?:www\.|(?!www))[^\s\.]+\.[^\s]{2,}|www\.[^\s]+\.[^\s]{2,}');

            if (pattern.test($(this).val())) {
                $(this).removeClass('error');
                $(".validation-url").hide();
            }
            else {
                $(this).addClass('error');
                $(".validation-url").show();
                valid = false;
            }
        }
        else {
            $(".validation-url").hide();
        }
    });

    if ($('#crInterestIDs').length > 0) {
        if ($('#crInterestIDs').val().length <= 0) {
            $('.js-interest-required').removeClass('d-none');
            valid = false;
        } else {
            $('.js-interest-required').addClass('d-none');
        }
    }

    if ($('#crSectorIDs').length > 0) {
        if ($('#crSectorIDs').val().length <= 0) {
            $('.js-sector-required').removeClass('d-none');
            valid = false;
        } else {
            $('.js-sector-required').addClass('d-none');
        }
    }

    return valid;
}
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function delete_cookie(name) {
    var d = new Date();
    d.setTime(d.getTime() - (1825 * 24 * 60 * 60 * 1000)); // 5 years ago
    var expires = "expires=" + d.toUTCString() + "; path=/";
    document.cookie = name + "=;" + expires;
};
function setCookie(cname, cvalue, exdays) {
    // 3600000 (1 hour) / 86400000 (24 hours) = 0.041666666666666664 (1 hour)
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString() + "; path=/";
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function formatBytes(bytes, decimals) {
    if (bytes == 0) return '0 Bytes';
    var k = 1024,
        dm = decimals <= 0 ? 0 : decimals || 2,
        sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
}