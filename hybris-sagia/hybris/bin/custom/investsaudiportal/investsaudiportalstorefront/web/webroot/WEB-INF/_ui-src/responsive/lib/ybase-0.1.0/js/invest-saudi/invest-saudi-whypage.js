ACC.whypage = {

    _autoload: [
        ["whypage", $(".pageLabel--why-saudi-arabia").length > 0]
    ],


    whypage: function () {

        if ($('html').is(':lang(ar)')) {
            $("#swiper-container-div").addClass("swiper-container-rtl");
        }

        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 5,
            centeredSlides: false,
            loop: false,
            spaceBetween: 20,
            breakpoints: {
                499: {
                    slidesPerView: 2,
                    spaceBetweenSlides: 50
                },
                999: {
                    slidesPerView: 3,
                    spaceBetweenSlides: 50
                }
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });

        $(document).ready(function () {
            if (getParameterByName("tab") != null) {
                $("html,body").animate({scrollTop: $("#why-page").offset().top - 50}, 500, function () {
                    $("#tab-" + getParameterByName("tab")).click();
                });
            } else {
                $("#tab-1").click();
            }
        });
    }
};