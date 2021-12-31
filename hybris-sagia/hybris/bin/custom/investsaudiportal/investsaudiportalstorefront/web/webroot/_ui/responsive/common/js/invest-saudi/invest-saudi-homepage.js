ACC.homepage = {

    _autoload: [
        ["homepage", $(".pageLabel-homepage").length >0]
    ],


    homepage: function(){
        var swiper = new Swiper('#HeaderSwiper.swiper-container', {effect: 'fade', parallax: true, pagination: {el: '#HeaderSwiper .swiper-pagination', clickable: true,}, navigation: {nextEl: '#HeaderSwiper .next-header.swiper-button-next', prevEl: '#HeaderSwiper .prev-header.swiper-button-prev',}, autoplay: {delay: 5000}});

        var myNav = document.querySelector("nav.navbar");
        var myLogoImg = document.querySelector(".nav-item.logo img");
        var myarabic = document.querySelector(".diff-color.arabic ");
        if (screen.width > 1080) {
            window.onscroll = function () {
                if (window.scrollY >= 200) {
                    myNav.classList.add("fixed-header");
                    myLogoImg.classList.add("none");
                    //myarabic.classList.remove("d-none");
                }
                else {
                    myNav.classList.remove("fixed-header");
                    myLogoImg.classList.remove("none");
                    //myarabic.classList.add("d-none");
                }
            };
            $(document).ready(function () {
                if (window.scrollY >= 200) {
                    myNav.classList.add("fixed-header");
                    myLogoImg.classList.add("none");
                }
                else {
                    myNav.classList.remove("fixed-header");
                    myLogoImg.classList.remove("none");
                }
            });
        }

        var _whyksaLength = document.querySelectorAll(".whyksa-animate>div").length;
        if (_whyksaLength % 2 !== 0) { document.querySelector(".whyksa-animate>div:last-child").classList.add("last-item"); }
        document.querySelector(".facts-figures div.my-4:last-child").classList.add("col-sm-12");
        document.querySelector(".facts-figures div.my-4:last-child").classList.remove("col-sm-6");

    }


};