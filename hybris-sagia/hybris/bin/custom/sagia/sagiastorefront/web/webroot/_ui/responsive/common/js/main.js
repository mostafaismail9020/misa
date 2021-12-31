$(document).ready(function () {
    new Imager('.js-responsive-image');

    // add a CSRF request token to POST ajax request if its not available
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        // Modify options, control originalOptions, store jqXHR, etc
        if (options.type === "post" || options.type === "POST") {
            var data = options.data;
            var noData = (typeof data === "undefined");

            if ((data instanceof FormData)) {
                if( !data.has('CSRFToken')) {
                    data.append('CSRFToken', ACC.config.CSRFToken);
                }
                return;

            }
            if (noData || (options && (typeof options.contentType === 'string') && options.contentType.indexOf("application/json") === -1 && options.data.indexOf("CSRFToken") === -1)) {
                options.data = (!noData ? options.data + "&" : "") + "CSRFToken=" + ACC.config.CSRFToken;
            }
        }
    });

    $(document).on("click", "#addressSubmit", function (e) {
        e.preventDefault();
        $('#addressForm').submit();
    });

    $(document).on("click", "#deliveryMethodSubmit", function (e) {
        e.preventDefault();
        $('#selectDeliveryMethodForm').submit();
    });

    //commented to skip validation with pstrength for reset password
    // $('.password-strength').pstrength();

    $(document).on("click", ".js-saved-payments", function (e) {
        e.preventDefault();
        $.colorbox({
            href: "#savedpayments",
            inline: true,
            maxWidth: "100%",
            opacity: 0.7,
            width: "320px",
            close: '<span class="glyphicon glyphicon-remove"></span>',
            title: '<div class="headline"><span class="headline-text">Saved Payment Methods</span></div>',
            onComplete: function () {}
        });
    });

    $('.flex-column').syncHeight({
        'parent': true,
        'updateOnResize': true
    });

    var useMap = $('img[usemap]');
    if (useMap.length > 0) {
        useMap.rwdImageMaps();
    }

    $(document).on("click", ".panel-group .js-next", function (e) {
        e.preventDefault();
        $(this).parents(".panel").next().find(".panel-title a").click();
    });

    $(document).on("click", ".checkout-steps .js-next", function (e) {
        e.preventDefault();
        $(this).parents(".step-body").next().click();
        if ($(this).data("next")) {
            var step = $(this).data("next");
            var href = window.location.href;
            var nhref = href.replace(new RegExp(/step./), "step" + step);
            if (!window.location.hash) {
                nhref = nhref + "#step" + step
            } else {
                nhref = nhref.replace(new RegExp(/#.*/), "#step" + step);
            }
            window.location = nhref;
        }
    });

    addressBookPager();
});


function addressBookPager() {
    $(document).on("change", ".js-address-input", function (e) {
        e.preventDefault();
        var addressDetails = $(this).data("addressDetails");
        $(".js-addressbook-addressFull").html(addressDetails["addressFull"])
    });

    $(document).on("click", ".js-select-store-label", function (e) {
        $(".js-addressbook-component").addClass("show-address")
    });

    $(document).on("click", ".js-back-to-addresslist", function (e) {
        $(".js-addressbook-component").removeClass("show-address")
    });

    // select the first store
    // dirty workaround for firefox
    var addressSelector = $(".js-address-input");
    $(addressSelector[1]).click();
    // select the first store
    $(addressSelector[0]).click();

    var listHeight = $(".js-address-list").height();
    var $listitems = $(".js-address-list > li");
    var listItemHeight = $listitems.height();
    var displayCount = 5;
    var totalCount = $listitems.length;
    var curPos = 0;
    var pageEndPos = ((Math.ceil(totalCount / displayCount) * listHeight) - listHeight) * -1;

    $(".js-address-pager-prev").hide();

    $(".js-address-pager-item-from").html("1");
    $(".js-address-pager-item-to").html(displayCount);
    $(".js-address-pager-item-all").html(totalCount);

    $(document).on("click", ".js-address-pager-prev", function (e) {
        e.preventDefault();
        $listitems.css("transform", "translateY(" + (curPos + listHeight) + "px)");
        curPos = curPos + listHeight;
        checkPosition("prev");
    });


    $(document).on("click", ".js-address-pager-next", function (e) {
        e.preventDefault();
        $listitems.css("transform", "translateY(" + (curPos - listHeight) + "px)");
        curPos = curPos - listHeight;
        checkPosition("next");
    });


    function checkPosition(prevNext) {
        if (curPos < 0) {
            $(".js-address-pager-prev").show()
        }

        if (curPos === 0) {
            $(".js-address-pager-prev").hide()
        }

        if (curPos === pageEndPos) {
            $(".js-address-pager-next").hide()
        }

        if (curPos > pageEndPos) {
            $(".js-address-pager-next").show()
        }

        var curPage = ((curPos / (displayCount * listItemHeight)) * -1) + 1;

        $(".js-address-pager-item-from").html(curPage * displayCount - 4);
        $(".js-address-pager-item-to").html(curPage * displayCount);
    }
}

