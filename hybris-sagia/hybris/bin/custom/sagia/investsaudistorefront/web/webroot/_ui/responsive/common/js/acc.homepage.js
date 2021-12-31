ACC.homepage = {

    _autoload: [
        "bindFixedHeaderWhenScrolling",
        "bindSlickToHomepageMainCarousel",
        "bindOpenCloseSearchArea",
        "bindProductCarousel",
        "bindSlickToCategoryCarousel",
        "bindMyAccountMenuOpen"
    ],

    bindFixedHeaderWhenScrolling: function () {
        var myNav = $('.page-homepage header .navigation__overflow');

        if (screen.width > 1080 && myNav.length > 0) {
            window.onscroll = function () {
                if (window.scrollY >= 200) {
                    myNav.addClass("fixed-header");
                }
                else {
                    myNav.removeClass("fixed-header");
                }
            };
        }
    },

    bindSlickToHomepageMainCarousel: function () {
        $('.ldrgif').remove();
        var prevArrow = "<div class='slick-arrow slick-prev'><img src='"+ACC.config.commonResourcePath+"/images/arrow-left.svg' alt=''></div>";
        var nextArrow = "<div class='slick-arrow slick-next'><img src='"+ACC.config.commonResourcePath+"/images/arrow-right.svg' alt=''></div>";
        $('.main-banner .slick-carousel > ul').slick({
            infinite: false,
            prevArrow: prevArrow,
            nextArrow: nextArrow,
            fade: true,
            cssEase: 'linear',
            autoplay: true
        });
    },

    bindOpenCloseSearchArea: function () {
        $('.header-search-area').on('click', function () {
            if ($('.search-area').hasClass('open')) {
                $('.search-area').removeClass('open-slide');

                setTimeout(function () {
                    $('.search-area').removeClass('open');
                }, 200)
            } else {
                $('.search-area').addClass('open');

                setTimeout(function () {
                    $('.search-area').addClass('open-slide');
                }, 100)
            }
        })
    },

    bindProductCarousel: function () {
        var prevArrow = "<div class='slick-arrow slick-prev'><img src='"+ACC.config.commonResourcePath+"/images/arrow-left-black.png' alt=''></div>";
        var nextArrow = "<div class='slick-arrow slick-next'><img src='"+ACC.config.commonResourcePath+"/images/arrow-right-black.png' alt=''></div>";

        $('.slick-product-carousel').slick({
            infinite: false,
            prevArrow: prevArrow,
            nextArrow: nextArrow,
            slidesToShow: 4,
            slidesToScroll: 1,
            responsive: [
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }
            ]
        })
    },

    bindSlickToCategoryCarousel: function () {
        $('.ldrgif').remove();
        var prevArrow = "<div class='slick-arrow slick-prev'><img src='"+ACC.config.commonResourcePath+"/images/arrow-left-black.png' alt=''></div>";
        var nextArrow = "<div class='slick-arrow slick-next'><img src='"+ACC.config.commonResourcePath+"/images/arrow-right-black.png' alt=''></div>"
        $('.product-category-slide-area .slick-carousel > ul').slick({
            infinite: false,
            prevArrow: prevArrow,
            nextArrow: nextArrow,
            slidesToShow: 4,
            slidesToScroll: 1,
            responsive: [
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }
            ]
        });
    },

    bindMyAccountMenuOpen: function () {
        $(".investsaudi--my-account-menu-wrapper").on('mouseenter', function () {
            var element = $(this).find('.investsaudi--my-account-menu');
            element.removeClass('hidden');
            element.addClass('open');
        });

        $(".investsaudi--my-account-menu-wrapper").on('mouseleave', function () {
            var element = $(this).find('.investsaudi--my-account-menu');
            element.removeClass('open');

            setTimeout(function () {
                element.addClass('hidden');
            }, 200)
        });
    }
};
