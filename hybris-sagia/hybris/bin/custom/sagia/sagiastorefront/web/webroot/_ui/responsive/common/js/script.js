!(function($) {
	"use strict";

	// Preloader
	// $(window).on('load', function() {
	//   if ($('#preloader').length) {
	// 	$('#preloader').delay(100).fadeOut('slow', function() {
	// 	  $(this).remove();
	// 	});
	//   }
	// });

	var lang = document.getElementsByTagName("html")[0].getAttribute("lang");
	if(window.location.pathname === "/" || window.location.pathname === ""){
		window.location.href = window.location.href + lang;
	}

	if(window.location.href.indexOf('recaptchaChallangeAnswered=false') >= 0){
		 var errorModal = $('#errorResponseModal');
         errorModal.find('.modal-description').text('Invalid Captcha, Please try again');
         errorModal.modal('show');
	}

	// Smooth scroll for the navigation menu and links with .scrollto classes
	var scrolltoOffset = $('#header').outerHeight() - 1;
	$(document).on('click', '.nav-menu a, .mobile-nav a, .scrollto', function(e) {
	  if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
		var target = $(this.hash);
		if (target.length) {
		  e.preventDefault();

		  var scrollto = target.offset().top - scrolltoOffset;

		  if ($(this).attr("href") == '#header') {
			scrollto = 0;
		  }

		  $('html, body').animate({
			scrollTop: scrollto
		  }, 1500, 'easeInOutExpo');

		  if ($(this).parents('.nav-menu, .mobile-nav').length) {
			$('.nav-menu .active, .mobile-nav .active').removeClass('active');
			$(this).closest('li').addClass('active');
		  }

		  if ($('body').hasClass('mobile-nav-active')) {
			$('body').removeClass('mobile-nav-active');
			$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
			$('.mobile-nav-overly').fadeOut();
		  }
		  return false;
		}
	  }
	});

	// Activate smooth scroll on page load with hash links in the url
	// $(document).ready(function() {
	//   if (window.location.hash) {
	// 	var initial_nav = window.location.hash;
	// 	if ($(initial_nav).length) {
	// 	  var scrollto = $(initial_nav).offset().top - scrolltoOffset;
	// 	  $('html, body').animate({
	// 		scrollTop: scrollto
	// 	  }, 1500, 'easeInOutExpo');
	// 	}
	//   }
	// });

	// Mobile Navigation
	if ($('.nav-menu').length) {
	  var $mobile_nav = $('.nav-menu').clone().prop({
		class: 'mobile-nav d-lg-none'
	  });
	  $('body').append($mobile_nav);
	  $('body').prepend('<button type="button" class="mobile-nav-toggle d-lg-none"><i class="icofont-navigation-menu"></i></button>');
	  $('body').append('<div class="mobile-nav-overly"></div>');

	  $(document).on('click', '.mobile-nav-toggle', function(e) {
		$('body').toggleClass('mobile-nav-active');
		$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
		$('.mobile-nav-overly').toggle();
	  });

	  $(document).on('click', '.mobile-nav .drop-down > a', function(e) {
		e.preventDefault();
		$(this).next().slideToggle(300);
		$(this).parent().toggleClass('active');
	  });

	  $(document).click(function(e) {
		var container = $(".mobile-nav, .mobile-nav-toggle");
		if (!container.is(e.target) && container.has(e.target).length === 0) {
		  if ($('body').hasClass('mobile-nav-active')) {
			$('body').removeClass('mobile-nav-active');
			$('.mobile-nav-toggle i').toggleClass('icofont-navigation-menu icofont-close');
			$('.mobile-nav-overly').fadeOut();
		  }
		}
	  });
	} else if ($(".mobile-nav, .mobile-nav-toggle").length) {
	  $(".mobile-nav, .mobile-nav-toggle").hide();
	}

	// Mobile nav link

	var twit_lk='/';
	$('.mobile-nav').append('<div class="mobile-nav-logo"></div>');
	$('.mobile-nav-logo').wrap('<a class="mobile-nav-Icon" href="'+twit_lk+'"/>');

	// Navigation active state on scroll
	var nav_sections = $('section');
	var main_nav = $('.nav-menu, #mobile-nav');

	$(window).on('scroll', function() {
	  var cur_pos = $(this).scrollTop() + 200;

	  nav_sections.each(function() {
		var top = $(this).offset().top,
		  bottom = top + $(this).outerHeight();

		if (cur_pos >= top && cur_pos <= bottom) {
		  if (cur_pos <= bottom) {
			main_nav.find('li').removeClass('active');
		  }
		  main_nav.find('a[href="#' + $(this).attr('id') + '"]').parent('li').addClass('active');
		}
		if (cur_pos < 300) {
		  $(".nav-menu ul:first li:first").addClass('active');
		}
	  });
	});

	// Toggle .header-scrolled class to #header when page is scrolled
	// // Back to top button
	// $(window).scroll(function() {
	//   if ($(this).scrollTop() > 100) {
	// 	$('.back-to-top').fadeIn('slow');
	//   } else {
	// 	$('.back-to-top').fadeOut('slow');
	//   }
	// });

	// $('.back-to-top').click(function() {
	//   $('html, body').animate({
	// 	scrollTop: 0
	//   }, 1500, 'easeInOutExpo');
	//   return false;
	// });

	// Init AOS
	function aos_init() {
	  AOS.init({
		duration: 1000,
		easing: "ease-in-out",
		once: true
	  });
	}
	$(window).on('load', function() {
	  aos_init();
	});

  })(jQuery);
//  -------------------------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------------------------------

  $(document).ready(function(){
    // Floating Button
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
		  $('#header').addClass('header-scrolled');
		  $('#topbar').addClass('topbar-scrolled');
		  $('#login-Navigation').addClass('login-scrolled');
		} else {
		  $('#header').removeClass('header-scrolled');
		  $('#topbar').removeClass('topbar-scrolled');
		  $('#login-Navigation').removeClass('login-scrolled');
		}
	  });

	  if ($(window).scrollTop() > 100) {
		$('#header').addClass('header-scrolled');
		$('#topbar').addClass('topbar-scrolled');
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
});

$(document).ready(function () {

  $('#spc_inc-pills-home-tab').click(function(){
	$('.mg-slider').removeClass("slider-1");
	$('.mg-slider').removeClass("slider-2 ");
	$('.mg-slider').removeClass("slider-3 ");
	$('.mg-slider').addClass("slider-0 ");
  });

  $('#spc_inc-pills-profile-tab').click(function(){
	$('.mg-slider').removeClass("slider-0 ");
	$('.mg-slider').removeClass("slider-2 ");
	$('.mg-slider').removeClass("slider-3 ");
	$('.mg-slider').addClass("slider-1 ");
  });

  $('#spc_inc-pills-contact-tab').click(function(){
	$('.mg-slider').removeClass("slider-0");
	$('.mg-slider').removeClass("slider-1 ");
	$('.mg-slider').removeClass("slider-3 ");
	$('.mg-slider').addClass("slider-2 ");
  });

  $('#spc_inc-pills-test-tab').click(function(){
	$('.mg-slider').removeClass("slider-0");
	$('.mg-slider').removeClass("slider-1 ");
	$('.mg-slider').removeClass("slider-2 ");
	$('.mg-slider').addClass("slider-3 ");
  });
	//   // when the modal is opened...
	//   $('#videoModal').on('show.bs.modal', function () {
	// 	// call play() on the <video> DOM element
	// 	$('#video-gpro')[0].play()
	//   })

	//   // when the modal is opened...
	//   $('#videoModal').on('hide.bs.modal', function () {
	// 	// call pause() on the <video> DOM element
	// 	$('#video-gpro')[0].pause()
	//   })

  ////////////////////////////////////////////////////////
});

$(document).ready(function() {

	// Contact page input filed
	if($("#crMessage").length > 0){
		$("#crMessage").val('');
	}

	$('.form-control').on('focus blur change', function (e) {
			var $currEl = $(this);
		if($currEl.is('select')) {
			if($currEl.val() === $("select option:first-child", $currEl).val()) {
				$('.control-label', $currEl.parent()).animate({opacity: 0}, 240);
			$currEl.parent().removeClass('focused');
			} else {
				$('.control-label', $currEl.parent()).css({opacity: 1});
				$currEl.parents('.form-group').toggleClass('focused', ((e.type === 'focus' || this.value.length > 0) && ($currEl.val() !== $("option:first", $currEl).val())));
			}
		} else {
			$currEl.parents('.form-group').toggleClass('focused', (e.type === 'focus' || this.value.length > 0));
		}
		}).trigger('blur');

		// Select arrow rotate
		$(".form-normal-item-select").on("click", function() {
			$(this).toggleClass("rotateCaret");
		});
});

$(document).ready(function(){

	  $('.News_press .carousel-item').first().addClass('active');
	  $('.News_press .carousel-indicators > li').first().addClass('active');
	  $('.News_press #carousel').carousel();


	//   ----------------------------------------------------------------------

	$('.slider-carousel .carousel-item').first().addClass('active');
	  $('.slider-carousel .carousel-indicators > li').first().addClass('active');
	  $('.slider-carousel #carousel').carousel();


	// --------------------------------------------------------------------------


	$('.newsDetails-carosel .carousel-item').first().addClass('active');
	$('.newsDetails-carosel .carousel-indicators > li').first().addClass('active');
	$('.newsDetails-carosel #carousel').carousel();

	// --------------------------------------------------------------------------------

	  function Neom() {
		document.getElementById("slider-0").style.display = "block";
		document.getElementById("slider-1").style.display = "none";
		document.getElementById("slider-2").style.display = "none";
		document.getElementById("slider-3").style.display = "none";
	  }
	  function TheRedSea() {
		document.getElementById("slider-0").style.display = "none";
		document.getElementById("slider-1").style.display = "block";
		document.getElementById("slider-2").style.display = "none";
		document.getElementById("slider-3").style.display = "none";
	  }
	  function Quiddiya() {
		document.getElementById("slider-0").style.display = "none";
		document.getElementById("slider-1").style.display = "none";
		document.getElementById("slider-2").style.display = "block";
		document.getElementById("slider-3").style.display = "none";
	  }
	  function Amala() {
		document.getElementById("slider-0").style.display = "none";
		document.getElementById("slider-1").style.display = "none";
		document.getElementById("slider-2").style.display = "none";
		document.getElementById("slider-3").style.display = "block";
	  }

	  (function(){

		$('.reasons .flex-container').mouseenter(function() {
			$(this).addClass("active");
			$('.flex-slide').addClass("blur");
		}).mouseleave(function () {
			$(this).removeClass("active");
			$('.flex-slide').removeClass("blur");
		});
	$(".reasons .flex-slide").each(function(){
			$(this).hover(function(){
			  $(this).addClass("active");

				$(this).find('.flex-title').css({
					top: '56%'
				});
				$(this).find('.flex-about').css({
					opacity: '1'
				});

			}, function(){
			  $(this).removeClass("active");
				$(this).find('.flex-title').css({
					top: '56%'
				});
				 $(this).find('.flex-about').css({
				   opacity: '0'
				});
			})
		});
	  })();

	//    (function(){

	// 	$('.feature .flex-container').mouseenter(function() {
	// 		$(this).addClass("active");
	// 		$('.flex-slide').addClass("blur");
	// 	}).mouseleave(function () {
	// 		$(this).removeClass("active");
	// 		$('.flex-slide').removeClass("blur");
	// 	});
	// 	$(".feature .flex-slide").each(function(){
	// 		$(this).hover(function(){
	// 		  $(this).addClass("active");

	// 			$(this).find('.flex-title').css({
	// 				top: '85%'
	// 			});
	// 			$(this).find('.flex-about').css({
	// 				opacity: '1'
	// 			});

	// 		}, function(){
	// 		  $(this).removeClass("active");
	// 			$(this).find('.flex-title').css({
	// 				top: '85%'
	// 			});
	// 			 $(this).find('.flex-about').css({
	// 			   opacity: '0'
	// 			});
	// 		})
	// 	});
	//   })();

	  function myFunctionrightside(){
		var x = document.getElementById("numberss");
		var y = document.getElementById("right").classList.add('rightsideshow');
		var y = document.getElementById("firstimg").classList.add('showss');
		var y = document.getElementById("secondimg").classList.remove('showss');
		if (x.innerHTML === "01-04") {
			x.innerHTML = "05-07";
		}
	  }
	  function myFunctionleftside(){
		var x = document.getElementById("numberss");
		var y = document.getElementById("right").classList.remove('rightsideshow');
		var y = document.getElementById("secondimg").classList.add('showss');
		var y = document.getElementById("firstimg").classList.remove('showss');
		if (x.innerHTML === "05-07") {
			x.innerHTML = "01-04";
		}
	  }

	  $('.pol-item .poly-item').click(function() {
		$('.pol-item .poly-item.active').removeClass('active');
		$(this).addClass('active');
	});


    $('#poly6').click(function(){
		$('.where-to-invest-main-wrapper').addClass("img-0");
		$('.where-to-invest-main-wrapper').removeClass("img-1");
		$('.where-to-invest-main-wrapper').removeClass("img-2");
		$('.where-to-invest-main-wrapper').removeClass("img-3 ");
		$('.where-to-invest-main-wrapper').removeClass("img-4 ");
		$('.where-to-invest-main-wrapper').removeClass("img-5 ");
		$('.where-to-invest-main-wrapper').removeClass("img-6 ");
		$('.where-to-invest-main-wrapper').removeClass("img-7 ");
		$('.where-to-invest-main-wrapper').removeClass("img-8 ");
		$('.where-to-invest-main-wrapper').removeClass("img-9 ");
		$('.where-to-invest-main-wrapper').removeClass("img-10 ");
		$('.where-to-invest-main-wrapper').removeClass("img-11 ");
		$('.where-to-invest-main-wrapper').removeClass("img-12 ");
	  });

	  $('#poly8').click(function(){
		$('.where-to-invest-main-wrapper').removeClass("img-0");
		$('.where-to-invest-main-wrapper').addClass("img-1");
		$('.where-to-invest-main-wrapper').removeClass("img-2");
		$('.where-to-invest-main-wrapper').removeClass("img-3 ");
		$('.where-to-invest-main-wrapper').removeClass("img-4 ");
		$('.where-to-invest-main-wrapper').removeClass("img-5 ");
		$('.where-to-invest-main-wrapper').removeClass("img-6 ");
		$('.where-to-invest-main-wrapper').removeClass("img-7 ");
		$('.where-to-invest-main-wrapper').removeClass("img-8 ");
		$('.where-to-invest-main-wrapper').removeClass("img-9 ");
		$('.where-to-invest-main-wrapper').removeClass("img-10 ");
		$('.where-to-invest-main-wrapper').removeClass("img-11 ");
		$('.where-to-invest-main-wrapper').removeClass("img-12 ");

	  });

	  $('#poly12').click(function(){
		$('.where-to-invest-main-wrapper').removeClass("img-0");
		$('.where-to-invest-main-wrapper').addClass("img-1");
		$('.where-to-invest-main-wrapper').removeClass("img-2");
		$('.where-to-invest-main-wrapper').removeClass("img-3 ");
		$('.where-to-invest-main-wrapper').removeClass("img-4 ");
		$('.where-to-invest-main-wrapper').removeClass("img-5 ");
		$('.where-to-invest-main-wrapper').removeClass("img-6 ");
		$('.where-to-invest-main-wrapper').removeClass("img-7 ");
		$('.where-to-invest-main-wrapper').removeClass("img-8 ");
		$('.where-to-invest-main-wrapper').removeClass("img-9 ");
		$('.where-to-invest-main-wrapper').removeClass("img-10 ");
		$('.where-to-invest-main-wrapper').removeClass("img-11 ");
		$('.where-to-invest-main-wrapper').removeClass("img-12 ");
	  });


	  $('.Inc-sectordetails-tab> ul>li:nth-child(1)').addClass("active");
	  $('.sector-tab-content> .tab-items:nth-child(1)').addClass("active");

	  $('.Inc-sectordetails-tab> ul>li').click(function() {
		$('.Inc-sectordetails-tab> ul>li').removeClass("active");
		$(this).addClass('active');
	});

	  $('.Inc-sectordetails-tab> ul>li:nth-child(1)').click(function(){
		$('.sector-tab-content> .tab-items:nth-child(1)').addClass("active");
		$('.sector-tab-content> .tab-items:nth-child(2)').removeClass("active");
		$('.sector-tab-content> .tab-items:nth-child(3)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-sectordetails-tab> ul>li:nth-child(2)').click(function(){
		$('.sector-tab-content> .tab-items:nth-child(1)').removeClass("active");
		$('.sector-tab-content> .tab-items:nth-child(2)').addClass("active");
		$('.sector-tab-content> .tab-items:nth-child(3)').removeClass("active");
		event.preventDefault()

	  });

	  $('.Inc-sectordetails-tab> ul>li:nth-child(3)').click(function(){
		$('.sector-tab-content> .tab-items:nth-child(1)').removeClass("active");
		$('.sector-tab-content> .tab-items:nth-child(2)').removeClass("active");
		$('.sector-tab-content> .tab-items:nth-child(3)').addClass("active");
		event.preventDefault()
	  });

    //  ---------------------------------------------------------------------------------

	$('.Inc-tab-panel> ul>li:nth-child(1)> a').addClass("active");
	  $('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').addClass("active");

	  $('.Inc-tab-panel> ul>li> a').click(function() {
		$('.Inc-tab-panel> ul>li> a').removeClass("active");
		$(this).addClass('active');
	});

	$('.Inc-tab-panel> ul>li:nth-child(1)').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(2)').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(3)> a').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(4)> a').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(5)> a').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(6)> a').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').addClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').removeClass("active");
		event.preventDefault()
	  });

	  $('.Inc-tab-panel> ul>li:nth-child(7)> a').click(function(){
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(1)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(2)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(3)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(4)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(5)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(6)').removeClass("active");
		$('.key-reason-invest .tab-content> .tab-pane:nth-child(7)').addClass("active");
		event.preventDefault()
	  });



//---------------------------------
// $('.owl-carousel').owlCarousel({
// 	loop: true,
// 	margin: 10,
// 	responsiveClass: true,
// 	rtl:window.location.href.indexOf('en') > -1 ? false : true,
// 	responsive: {
// 	  0: {
// 		items: 1,
// 		lazyLoad : true,
// 		autoWidth: true,
// 		animateIn: "fadeIn",
//         animateOut: "fadeOut",
// 		nav: true,
// 		loop: false,
// 		margin: 40
// 	  },
// 	  600: {
// 		items: 2,
// 		lazyLoad : true,
// 		autoWidth: true,
// 		animateIn: "fadeIn",
//         animateOut: "fadeOut",
// 		nav: true,
// 		loop: false,
// 		margin: 40

// 	  },
// 	  1000: {
// 		items: 3,
// 		lazyLoad : true,
// 		autoWidth: true,
// 		animateIn: "fadeIn",
//         animateOut: "fadeOut",
// 		nav: true,
// 		loop: false,
// 		margin: 40
// 	  }
// 	}
// });

$(document).on('click', '.dropdown-menu', function (e) {
	//e.stopPropagation();
  });
  if ($(window).width() < 992) {
		$('.dropdown-menu a').click(function(e){
			//e.preventDefault();
		  if($(this).next('.submenu').length){
			  $(this).next('.submenu').toggle();
		  }
		  $('.dropdown').on('hide.bs.dropdown', function () {
			 $(this).find('.submenu').hide();
		  })
		});
  }

});
$(document).ready(function() {
	$(".INS_border_right").last().css( "border-right", "none" );
	$(".INS_EPM_nav .nav-link").click(function(){
		$(".INS_Tab_pane_Show").removeClass("show").removeClass("active");
		$("#"+$(this).attr('href').replace("#","")).addClass("show").addClass("active");
		$("#"+$(this).attr('href').replace("#","")+"1").addClass("show").addClass("active");
		// if($(this).hasClass("active")) {
		// 	$("#"+$(this).attr('href').replace("#","")).removeClass("show").removeClass("active");
		// 	$("#"+$(this).attr('href').replace("#","")+"1").removeClass("show").removeClass("active");
		// 	setTimeout(function(){
		// 		$(this).removeClass("show").removeClass("active");
		// 	},300);

		// }
	});
	$(".dropdown-menu li a").click(function(){
		var selText = $(this).text();
		$(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
	});

});

$(document).ready(function() {

	$("article.item").hover(
		function () {
			$(this).toggleClass("active");
			$(this).siblings().toggleClass('is-disabled');
		}
	  );

	});

	$(document).ready(function() {

		$('article.showinset').click(function() {
			$(this).addClass('active');
			$(this).removeClass('is-disabled');
			$(this).siblings().addClass('is-disabled');
		});

	});


	$(document).ready(function(){

		var i = 0;
		var j = 0;

		$("article.showinset").each( function() {
			   $(this).attr("data-slide-to", +i);
			   $(this).attr("id", '#article-'+i);
			   $(this).attr("class", 'showinset article_fun-'+i);
			i++;
		});
		$(".sector-box").each( function() {
			$(this).attr("data-slide-to", +j);
			$(this).attr( "id", '#hover-' +j);
			$(this).attr( "class", 'sector-box hover-' +j);
			j++;
		});
		$('article.showinset').first().addClass('active');
		$('article.showinset').first().siblings().addClass('is-disabled');
		$('.sector-box').first().addClass('active');
		// $('.Inc-sector-panel').addClass('sectors__backgroundImage_1');

	});

	$(document).ready(function() {
		var elmsall = document.getElementsByClassName("showinset");
		var nall = elmsall.length

		for(var i = 0; i < nall; ++i) {
			var art = ['#article-'+i];

			i = i-1;
			i++;
		}

		$('.showinset').click(function() {
			//console.log(this.id);
			//alert(this.id);
			var nums = this.id.split('-')[1];
			var actives = '.sector-box.hover-'+nums;

			var addone = nums++;

			$('.sector-box').first().removeClass('active');
			$( ".sector-box" ).siblings().removeClass('active');
			//$( ".showinset" ).siblings().removeClass('active');
			//$('.Inc-sector-panel').siblings().removeClass('sectors__backgroundImage_1')
			if(nums === nums){
				$('.sector-box.hover-'+addone).toggleClass("active");
				//$('.Inc-sector-panel').addClass('sectors__backgroundImage_'+addone);
			}
		});

	});

$(document).ready(function(){
	var i = 0;
	$("#carouselExampleControls .carousel-indicators li").each( function() {
	$(this).attr("data-slide-to", +i);
	i++;
	});

	$('#carouselExampleControls .carousel-indicators li').first().addClass('active');
});

// Contact Map SVG

function fun() {
	$('.show').popover({
		html:true
	});
	$('.show').not(this).popover('hide');
}


var $links = $( '.links' ).find( 'a' );

$links.on( 'click', function ( e ) {
	e.preventDefault();

	var group = $( this ).attr( 'href' ).replace( '#', '' );

	$( '.some-class' ).hide();
	$( '.some-class[data-id="' + group + '"]' ).show();
});
// End Contact Map SVG

// Megamneu expand
$(document).ready(function () {
	$('.get_submenus').mouseover(function(){
		$('.dropdown-large').addClass("show_height");
	}).mouseout(function(){
		$('.dropdown-large').removeClass("show_height");
	});
});

//regions map--------------------------------
$('.province_region_container> .province_region_innercontainer> .item:nth-child(1)').css("display","block");
$('.province_region_container> .province_region_innercontainer> .item:nth-child(1)').siblings().css("display","none");

$(document).ready(function() {

	$('.region-map .map-inner').on('click', 'a', function() {
		$('.region-map .map-inner> a.active').removeClass('active');
		$(this).addClass('active');
	});

	$('.mega-jiamap a').click(function() {
		$(this).addClass('active');
		$(this).siblings().removeClass('active');
	});



});

var $links = $( '.meetRegions_links' ).find( 'a' );
$links.on( 'click', function ( e ) {
    e.preventDefault();
    var group = $( this ).attr( 'href' ).replace( '#', '' );
    $( '.meetRegions_some-class' ).hide();
    $( '.meetRegions_item' ).hide();
    $( '.meetRegions_some-class[data-id="' + group + '"]' ).show();
    $( '.meetRegions_item' ).show();
});
// End

// InfraLogistics Map
$('body').on('click', function (e) {
	$('[data-toggle=popover]').each(function () {
		// hide any open popovers when the anywhere else in the body is clicked
		$('.logis_show').popover({
			html:true
		});
		if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
			$(this).popover('hide');
		}
		$('.logis_show').popover({
			html:true
		});

	});
});

// End
/****sector page - sub sectors are left aligned start***/
$(document).ready(function() {
	$('.Inc-sectordetails-tab').addClass(function() {
		return 'Inc_two_width_' + $('.Inc-sectordetails-tab li.nav-item').length;
	});

	// Featured oppetunities
    $('.feature-tab .nav-tabs a').click(function(){
		$('.feature-tab-content .tab-pane').toggleClass("active");
	});
});

/****sector page - sub sectors are left aligned end***/

function myFunctionrightside(){
	var x = document.getElementById("numberss");
	var y = document.getElementById("right").classList.add('rightsideshow');
	var y = document.getElementById("firstimg").classList.add('showss');
	var y = document.getElementById("secondimg").classList.remove('showss');
	if (x.innerHTML === "01-04") {
		x.innerHTML = "05-07";
	}
}
function myFunctionleftside(){
	var x = document.getElementById("numberss");
	var y = document.getElementById("right").classList.remove('rightsideshow');
	var y = document.getElementById("secondimg").classList.add('showss');
	var y = document.getElementById("firstimg").classList.remove('showss');
	if (x.innerHTML === "05-07") {
		x.innerHTML = "01-04";
	}
}

// RHQ

var $RHQ_links = $( '.RHQ_links' ).find( 'a' );

$RHQ_links.on( 'click', function ( e ) {
	//alert('click');
	e.preventDefault();

	var RHQ_group = $( this ).attr( 'href' ).replace( '#', '' );

	$( '.RHQ_class' ).hide();
	$( '.RHQ_class[data-id="' + RHQ_group + '"]' ).show();
});

//

$('body.page-province-region-homepage .province_region_container.container').removeClass('container').addClass('container-fluid p-0');

$('body.page-economic-highlights .eh-page-breadcrums .toggle-div.container-fluid').removeClass('container-fluid').addClass('container');
// -----------------------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------------------------------

$(document).ready(function () {
	am4core.addLicense("CH292550473");
	window.onbeforeunload = function () {
		window.scrollTo(0, 0);
	}
	var overflow = "hidden";
	var footerHeight
	$(".float-button").click(function () {
		$(".popup").toggleClass("popup-up");
		$(".float-button").toggleClass("float-button-up");
		$(".full-bg").fadeToggle();
		$("body").css("overflow", overflow);
		//$(this).html($(this).text() == 'Invest Now' ? "<img width='40' src='./images/close.png'>" : 'Invest Now');
		overflow = (overflow == "hidden") ? "visible" : "hidden";
	})

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

	/* Script for Opportunity page */
	$(".opp-filter-btn").click(function(){
		if($(".sectors-list").attr("style") == "display: none;"){
			$(".sectors-list").attr("style","display: block;");
			$(".opp-search-container").addClass("opp-search-container-full");
			$("#close-filter").attr("style","display: inline-block;");
			$("#open-filter").attr("style","display: none;");
		}
		else {
			$(".sectors-list").attr("style","display: none;");
			$(".opp-search-container").removeClass("opp-search-container-full");
			$("#close-filter").attr("style","display: none;");
			$("#open-filter").attr("style","display: inline-block;;");
		}
	});
	var selectedSectorsList = [];
	$(".sector-btn").click(function(){
		if($(this).attr("data-selected") == "no"){
			$(this).attr("data-selected","yes");
			$(this).addClass("selected");
			$(this).find(".selected-img").removeAttr("style");
			$(this).find(".deselected-img").attr("style", "display: none;");
		}
		else {
			// alert("no");
			$(this).attr("data-selected","no");
			$(this).removeClass("selected");
			$(this).find(".selected-img").attr("style", "display: none;");
			$(this).find(".deselected-img").removeAttr("style");
			// $('.float-button').css("opacity", "1");
		}
		selectedSectorsList = [];
		$(".selected").each(function() {
			selectedSectorsList.push({sectorName:$(this).attr("data-sectorName")});
		});

		console.log("sectorList", selectedSectorsList);
	});
	$(".next-page").hover(function(){
		$(".arrow-left-blue-icon").attr("style", "display: none;");
		$(".arrow-left-white-icon").attr("style", "display: block;");
	}, function(){
		$(".arrow-left-blue-icon").attr("style", "display: block;");
		$(".arrow-left-white-icon").attr("style", "display: none;");
	});
	$(".previous-page").hover(function(){
		$(".previous-left-blue-icon").attr("style", "display: none;");
		$(".previous-left-white-icon").attr("style", "display: block;");
	}, function(){
		$(".previous-left-blue-icon").attr("style", "display: block;");
		$(".previous-left-white-icon").attr("style", "display: none;");
	});
	$(".all-opportunity-description").parent().next().find(".sectors-content").addClass("all-opportunity-description-text");

	/* Success Stories Search Begins*/
	function getParameterByName(name, url) {
		if (!url) url = window.location.href;
		name = name.replace(/[\[\]]/g, "\\$&");
		var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
			results = regex.exec(url);
		if (!results) return null;
		if (!results[2]) return '';
		return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	if (getParameterByName("scrollTo") != null) {
        var scrollDiv = document.getElementById("opp-contact-form").offsetTop - 80;
		window.scrollTo({ top: scrollDiv, behavior: 'smooth'});
    }

    if (getParameterByName("q") != null) {
        $(".opp-search-container input").val(getParameterByName("q"));
    }
    var sectorIds = [];
	var contractIds = [];

    if (getParameterByName("sectorIds") != null) {
        var _secIds = getParameterByName("sectorIds").split(",");
        $.each(_secIds, function (i, val) {
            sectorIds.push(val);
            $(".opp-search-container div[data-sectorId=" + val + "]").addClass("selected").attr("data-selected","yes");
			$(".opp-search-container div[data-sectorid=" + val + "]").find(".selected-img").removeAttr("style");
			$(".opp-search-container div[data-sectorid=" + val + "]").find(".deselected-img").attr("style", "display: none;");
        });
    }
    $(".opp-search-container div[data-sectorId]").on("click", function () {
        var _sectorId = $(this).attr("data-sectorId");
        if (_sectorId == 0) {
            sectorIds = [];
            sectorIds.push(0);
        }
        else {
            if ($.inArray(_sectorId, sectorIds) >= 0) {
                sectorIds.splice(sectorIds.indexOf(_sectorId), 1);
            }
            else {
                sectorIds.push(_sectorId);
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
    $(".opp-search-container input").on("keyup", function (e) {
        if (e.currentTarget.value.length > 0) {
            $(e.currentTarget).next("a").addClass("clearSearch").find(".fa").removeClass("fa-search").addClass("fa-times-circle");
        }
    });
    $(".opp-search-container a.a-search").on("click", function () {
        if ($(this).hasClass("clearSearch")) {
            $(".opp-search-container input").val("");
            $(this).find(".fa").removeClass("fa-times-circle").addClass("fa-search");
        }
    });
	$(".opp-search-container .search-btn").on("click", function () {
		// alert(1);
        window.dataLayer = window.dataLayer || [];
        dataLayer.push({
            'event': 'fire_event',
            'category': window.location.href.indexOf("success-stories") > 0 ? 'Success Stories' : 'Opportunities',
            'action': 'Apply Search'
        });

        var search = "q=" + $(".opp-search-container input").val();
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
	$(".filter-reset-search").on("click", function () {
        window.location.href = window.location.pathname;
    });

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


	/* newsletter subscription ends */
	/* general form validation */
	function validateForm(ctrl) {
		var valid = true;
		if (ctrl.length == 0) {
			valid = false;
			return valid;
		}
		// debugger;
		$.each($('.required', ctrl), function (i, e) {
			$(this).parent().find(".error-msg").text("");
			var value;
			// debugger;
			if($(this).attr("id") == "contactSubjectList"){
				value = $('#contactSubjectList :selected').text();
			}
			else {
				value = $.trim($(this).val());
			}

			if ( value== null || value == 0 || value=="Purpose of Contacting") {
				valid = false;
				$(this).addClass('error');
				$(this).parent().addClass('error');
				$(this).parent().find(".error-msg").html(" <em><span>" + site.messages().required + "</span></em>");
			}
			else if (!$(this).hasClass("textarea") && !$(this).hasClass("validate-email") && ($(this).val().length < 1 || $(this).val().length > 999)) {
				valid = false;
				$(this).addClass('error');
				$(this).parent().addClass('error');
				$(this).parent().find(".error-msg").html(" <em><span>" + site.messages().maxChars.replace("{0}", 999) + "</span></em>");
			}
			else {
				$(this).removeClass('error');
				$(this).parent().removeClass('error');
			}
		});
		$.each($('.validate-name', ctrl), function (i, e) {
			var pattern = new RegExp(/^[-\sa-zA-Z,\u0600-\u06FF]+$/);
			if (pattern.test($.trim($(this).val()))) {
				if (valid) {
					$(this).removeClass('error');
					$(this).parent().find(".error-msg").text("");
					$(this).parents("div." + ctrl.attr("id")).find(".validation-name").hide();
				}
			}
			else {
				$(this).addClass('error');
				if ($(this).parent().find("label").find("em").length == 0) {
					$(this).parent().find(".error-msg").html("<em><span>" + site.messages().noSplChars + "</span></em>");
				}
				$(this).parents("div." + ctrl.attr("id")).find(".validation-name").show();
				valid = false;
			}
		});
		$.each($('.validate-email', ctrl), function (i, e) {
			var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
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
					$(this).parent().find(".error-msg").html(" <em><span>" + site.messages().invalidEmail + "</span></em>");
				}
				$(this).parents("div." + ctrl.attr("id")).find(".validation-email").show();
				valid = false;
			}
		});
		$.each($('.validate-mobile', ctrl), function (i, e) {
			var pattern = /^(?!0.)\d+$/;
			// debugger;
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
					$(this).parent().find(".error-msg").html(" <em><span>" + site.messages().invalidMobile + "</span></em>");
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

		if(ctrl[0].id === "popup-contact-form"){
			var recaptcha = $(".sector-page-download-captcha .g-recaptcha-response").val();
			var lblSectorErrorCaptcha = document.getElementById("lblSectorErrorCaptcha");
			lblSectorErrorCaptcha.innerHTML = "";
			if (recaptcha == "") {
				lblSectorErrorCaptcha.innerHTML = "Please fill reCAPTCHA";
				valid = false;
			}
		}
		else if(ctrl[0].id === "corForm"){
			var recaptcha = $(".sector-page-captcha .g-recaptcha-response").val();
			var lblSectorErrorCaptcha = document.getElementById("lblSectorPageErrorCaptcha");
			lblSectorErrorCaptcha.innerHTML = "";
			if (recaptcha == "") {
				lblSectorErrorCaptcha.innerHTML = "Please fill reCAPTCHA";
				valid = false;
			}
		}

		return valid;
	}
	var element = document.getElementsByClassName('btn-contact');
	var btnText = element[0] && element[0].textContent;
	$("#btn-contact").on("click", function () {
        element.onclick = validateContact(element);
    });
	/* Success Stories Search Ends*/
    if ($(".ddl-countryCode").length > 0) {
        $(".ddl-countryCode").intlTelInput({
            preferredCountries: ['sa'],
			initialCountry: 'sa',
			excludeCountries: ['il'],
            customPlaceholder: function (selectedCountryPlaceholder, selectedCountryData) {
                return "+" + selectedCountryData.dialCode;
            },
            utilsScript: ACC.config.commonResourcePath + "/intlTelInput/js/utils.js"
        });

        $(".ddl-countryCode").on("countrychange", function (e, countryData) {
			// alert(countryData.dialCode);
           $(".ddl-countryCode").val("+" + countryData.dialCode);
        });
    }
	// $(".map-area g").on("click", function () {
	// 	if($(this).attr("id")){
	// 		$(".map-area g").each(function() {
	// 			if($(this).find("path").attr("fill") == "#BF9B2E"){
	// 				$(this).find("path").attr("fill", "#025635");
	// 			}
	// 		});
	// 		$(this).find("path").attr("fill", "#BF9B2E");
	// 	}
    // });
	// $(".map-area ellipse").on("click", function () {
	// 	if($(this).attr("id")){
	// 		var currentId = $(this).attr("id");
	// 		$(".map-area g").each(function() {
	// 			if($(this).find("path").attr("fill") == "#BF9B2E"){
	// 				$(this).find("path").attr("fill", "#025635");
	// 			}
	// 			if($(this).attr("id") === currentId){
	// 				$(this).find("path").attr("fill", "#BF9B2E");
	// 			}
	// 		});

	// 	}
    // });
	function setActiveClientContainer() {
		$(".clientContainer a").each(function() {
			if($(this).hasClass("active")){
				$(this).find(".mg-active").attr("style", "display: block;");
				$(this).find(".mg-inActive").attr("style", "display: none;");
			}
			else {
				$(this).find(".mg-active").attr("style", "display: none;");
				$(this).find(".mg-inActive").attr("style", "display: inline-block;");
			}
		});
	}
	setActiveClientContainer();
	$(".clientContainer a").on("click", function () {
		setTimeout(function(){
			if($(this).hasClass("active")){
				setActiveClientContainer();
			}
			else {
				setActiveClientContainer();
			}
		},500);
    });

	function renderIndicesCharts(chartData){
		// Create chart instance
		// alert("test");
		// console.log(chartData);
		var chart = am4core.create("chartdiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.marginRight = 400;
		chart.data = chartData;
		chart.cursor = new am4charts.XYCursor();
		chart.exporting.menu = new am4core.ExportMenu();
		// chart.dateFormatter.dateFormat = "yyyy";
		chart.numberFormatter.numberFormat = "#";


		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.title.text = "Years";
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		categoryAxis.renderer.labels.template.fontSize = 16;
		categoryAxis.renderer.minGridDistance = 30;

		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		// valueAxis.title.text = "Scores";
		valueAxis.title.text = "Scores / Ranking";
		valueAxis.renderer.grid.template.strokeWidth = 0;

		/* Create series */
		if($('input[name="chartType"]:checked').val() == 'score'){
			var columnSeries = chart.series.push(new am4charts.ColumnSeries());
			columnSeries.name = "Score";
			columnSeries.dataFields.valueY = "scoreValue";
			columnSeries.dataFields.categoryX = "year";

			columnSeries.columns.template.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
			columnSeries.columns.template.propertyFields.fillOpacity = "fillOpacity";
			columnSeries.columns.template.propertyFields.stroke = "stroke";
			columnSeries.columns.template.propertyFields.strokeWidth = "strokeWidth";
			columnSeries.columns.template.propertyFields.strokeDasharray = "columnDash";
			columnSeries.columns.template.fill = am4core.color("#025635");
			columnSeries.tooltip.label.textAlign = "middle";
		}
		else {
			var lineSeries = chart.series.push(new am4charts.LineSeries());
			lineSeries.name = "Ranking";
			lineSeries.dataFields.valueY = "rankValue";
			lineSeries.dataFields.categoryX = "year";

			lineSeries.stroke = am4core.color("#BF9B2E");
			lineSeries.strokeWidth = 3;
			lineSeries.propertyFields.strokeDasharray = "lineDash";
			lineSeries.tooltip.label.textAlign = "middle";

			var bullet = lineSeries.bullets.push(new am4charts.Bullet());
			bullet.fill = am4core.color("#BF9B2E"); // tooltips grab fill from parent by default
			bullet.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
			var circle = bullet.createChild(am4core.Circle);
			circle.radius = 4;
			circle.fill = am4core.color("#fff");
			circle.strokeWidth = 3;
		}




		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}
	if($("#Json-data").text().length > 0){
		var data = JSON.parse($("#Json-data").text());
		renderIndicesCharts(data);
	}
	function renderLogisticsCharts(chartData){
		// console.log(chartData);
		var chart = am4core.create("chartdiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.marginRight = 400;
		chart.data = chartData;
		chart.cursor = new am4charts.XYCursor();
		chart.exporting.menu = new am4core.ExportMenu();

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Length Of Network (Km)";
		// valueAxis.renderer.grid.template.strokeWidth = 0;
		// Create axes
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "networkName";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;
		categoryAxis.renderer.labels.template.fontSize = 10;
		categoryAxis.renderer.minGridDistance = 10;
		categoryAxis.events.on("sizechanged", function(ev) {
		var axis = ev.target;
		var cellWidth = axis.pixelWidth / (axis.endIndex - axis.startIndex);
		const width  = window.innerWidth || document.documentElement.clientWidth ||
		document.body.clientWidth;
		if (width  < 450) {
		axis.renderer.labels.template.rotation = -90;
		axis.renderer.labels.template.horizontalCenter = "right";
		axis.renderer.labels.template.verticalCenter = "middle";
		categoryAxis.renderer.labels.template.fontSize = 9;
		}
		else if (width  < 600) {
			axis.renderer.labels.template.rotation = -55;
			axis.renderer.labels.template.horizontalCenter = "right";
			axis.renderer.labels.template.verticalCenter = "middle";
			categoryAxis.renderer.labels.template.fontSize = 10;
			}
		else if (width  < 1200) {
			axis.renderer.labels.template.rotation = -55;
			axis.renderer.labels.template.horizontalCenter = "right";
			axis.renderer.labels.template.verticalCenter = "middle";
			categoryAxis.renderer.labels.template.fontSize = 10;
			}
		else {
		axis.renderer.labels.template.rotation = 0;
		axis.renderer.labels.template.horizontalCenter = "middle";
		axis.renderer.labels.template.verticalCenter = "top";
		categoryAxis.renderer.labels.template.fontSize = 12;
		}
		});

		// Configure axis label
		var label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 80;

		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "networkValue";
		series.dataFields.categoryX = "networkName";
		series.columns.template.fill = am4core.color("#025635");
		series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/]";
	}
	if($("#logistics-json-data").text().length > 0){
		var data = JSON.parse($("#logistics-json-data").text());
		renderLogisticsCharts(data);
	}

	function renderoverallSaudiPopulationChart(data) {
		// Create chart instance
		if(data.length == 0) {
			$("#renderoverallSaudiPopulationChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#renderoverallSaudiPopulationChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderoverallSaudiPopulationChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#renderoverallSaudiPopulationChartDiv").attr("style", "display: block;");
		}
		console.log(data[0].saudi.replaceAll(",",""));
		console.log(Number(data[0].nonSaudi.replaceAll(",","")));
		$("#population").text((Number(data[0].saudi.replaceAll(",",""))+Number(data[0].nonSaudi.replaceAll(",",""))).toLocaleString());
		var chart = am4core.create("renderoverallSaudiPopulationChartDiv", am4charts.PieChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// Add data
		chart.data = [ {
		"country": "Saudi",
		"population": data[0].saudi,
		"color": am4core.color("#025635")
		}, {
		"country": "Non-Saudi",
		"population": data[0].nonSaudi,
		"color": am4core.color("#BF9B2E")
		}];

		// Set inner radius
		chart.innerRadius = am4core.percent(15);
		chart.radius = am4core.percent(50);
		// Add and configure Series
		var pieSeries = chart.series.push(new am4charts.PieSeries());
		pieSeries.dataFields.value = "population";
		pieSeries.dataFields.category = "country";
		pieSeries.slices.template.stroke = am4core.color("#fff");
		pieSeries.slices.template.strokeWidth = 2;
		pieSeries.slices.template.strokeOpacity = 1;
		pieSeries.slices.template.propertyFields.fill = "color";

		// This creates initial animation
		pieSeries.hiddenState.properties.opacity = 1;
		pieSeries.hiddenState.properties.endAngle = -90;
		pieSeries.hiddenState.properties.startAngle = -90;
		pieSeries.labels.template.fontSize = 11;
		pieSeries.labels.template.maxWidth = 80;
		pieSeries.labels.template.wrap = true;
	}
	function rendergraduatesByDegreeChart(data){
		// Create chart instance
		// alert("test");
		if(data.length == 0) {
			$("#rendergraduatesByDegreeChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#rendergraduatesByDegreeChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#rendergraduatesByDegreeChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#rendergraduatesByDegreeChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("rendergraduatesByDegreeChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// Add data
		chart.data = [
			{
			"degree": "Intermediate Diploma",
			"value": data[0].intermediate
			},
			{
			"degree": "Higher Diploma",
			"value": data[0].diploma
			},
			{
			"degree": "Bachelor",
			"value": data[0].bachelor
			},
			{
			"degree": "Master",
			"value": data[0].master
			},
			{
			"degree": "Phd",
			"value": data[0].phd
			},
			{
			"degree": "Fellowship",
			"value": data[0].fellowship
			}];

		// Create axes

		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "degree";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.labels.template.fontSize = 12;
		categoryAxis.renderer.minGridDistance = 5;
		var label = categoryAxis.renderer.labels.template;
		label.wrap = true;
		label.maxWidth = 90;
		categoryAxis.events.on("sizechanged", function(ev) {
			var axis = ev.target;
			var cellWidth = axis.pixelWidth / (axis.endIndex - axis.startIndex);
			const width  = window.innerWidth || document.documentElement.clientWidth ||
			document.body.clientWidth;
			if (width  < 450) {
			axis.renderer.labels.template.rotation = -90;
			axis.renderer.labels.template.horizontalCenter = "right";
			axis.renderer.labels.template.verticalCenter = "middle";
			categoryAxis.renderer.labels.template.fontSize = 9;
			}
			else if (width  < 600) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else if (width  < 1200) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else {
			axis.renderer.labels.template.rotation = 0;
			axis.renderer.labels.template.horizontalCenter = "middle";
			axis.renderer.labels.template.verticalCenter = "top";
			categoryAxis.renderer.labels.template.fontSize = 12;
			}
			});

		// categoryAxis.renderer.labels.template.adapter.add("dy", function(dy, target) {
		// if (target.dataItem && target.dataItem.index & 2 == 2) {
		// 	return dy + 25;
		// }
		// return dy;
		// });

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;

		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "value";
		series.dataFields.categoryX = "degree";
		series.name = "Graduates By Degree";
		series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/]";
		series.columns.template.fill = am4core.color("#025635");
		// series.columns.template.fillOpacity = .8;
		var valueLabel = series.bullets.push(new am4charts.LabelBullet());
		valueLabel.label.text = "{value}";
		valueLabel.label.fontSize = 12;
		valueLabel.label.dy = -10;
		// valueLabel.label.fill = am4core.color("#025635");
		var columnTemplate = series.columns.template;
		columnTemplate.strokeWi0dth = 0;
		columnTemplate.strokeOpacity = 1;
	}
	function renderpopulationByRegionChart(data){
		// Create chart instance
		// alert("test");
		if(data.length == 0) {
			$("#renderpopulationByRegionDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#renderpopulationByRegionDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderpopulationByRegionDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#renderpopulationByRegionDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("renderpopulationByRegionDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		console.log(data);
		// Add data
		chart.data = [
			{
				"region": "Riyadh",
				"value": data[0].riyadhPercentage,
				"percentage": data[0].riyadhValue
			},
			{
				"region": "Makkah",
				"value": data[0].makkahPercentage,
				"percentage": data[0].makkahValue
			},
			{
				"region": "Madinah",
				"value": data[0].almadinaPercentage,
				"percentage": data[0].almadinahValue
			},
			{
				"region": "Qassim",
				"value": data[0].alqassimPercentage,
				"percentage": data[0].alqassimValue
			},
			{
				"region": "Eastern Region",
				"value": data[0].easternPercentage,
				"percentage": data[0].easternValue
			},
			{
				"region": "Assir",
				"value": data[0].asirPercentage,
				"percentage": data[0].asirValue
			},
			{
				"region": "Tabuk",
				"value": data[0].tabukPercentage,
				"percentage": data[0].tabukValue
			},
			{
				"region": "Hail",
				"value": data[0].hailPercentage,
				"percentage": data[0].hailValue
			},
			{
				"region": "Northern borders",
				"value": data[0].northernPercentage,
				"percentage": data[0].northernValue
			},
			{
				"region": "Jazan",
				"value": data[0].jazanPercentage,
				"percentage": data[0].jazanValue
			},
			{
				"region": "Najran",
				"value": data[0].najranPercentage,
				"percentage": data[0].najranValue
			},
			{
				"region": "Albahah",
				"value": data[0].albahaPercentage,
				"percentage": data[0].albahaValue
			},
			{
				"region": "Aljouf",
				"value": data[0].aljawfPercentage,
				"percentage": data[0].aljawfValue
			}
			];

		// Create axes

		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "region";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.labels.template.fontSize = 12;
		categoryAxis.renderer.minGridDistance = 0;

		// categoryAxis.renderer.labels.template.adapter.add("dy", function(dy, target) {
		// if (target.dataItem && target.dataItem.index & 2 == 2) {
		// 	return dy + 25;
		// }
		// return dy;
		// });

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;

		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "value";
		series.dataFields.categoryX = "region";
		series.name = "Population By Region";
		series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}%[/]";
		series.columns.template.fill = am4core.color("#025635");
		// series.columns.template.fillOpacity = .8;

		var columnTemplate = series.columns.template;
		columnTemplate.strokeWidth = 0;
		columnTemplate.strokeOpacity = 1;

		categoryAxis.events.on("sizechanged", function(ev) {
			var axis = ev.target;
			var cellWidth = axis.pixelWidth / (axis.endIndex - axis.startIndex);
			const width  = window.innerWidth || document.documentElement.clientWidth ||
			document.body.clientWidth;
			if (width  < 450) {
			axis.renderer.labels.template.rotation = -90;
			axis.renderer.labels.template.horizontalCenter = "right";
			axis.renderer.labels.template.verticalCenter = "middle";
			categoryAxis.renderer.labels.template.fontSize = 9;
			}
			else if (width  < 600) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else if (width  < 1200) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else {
			axis.renderer.labels.template.rotation = 0;
			axis.renderer.labels.template.horizontalCenter = "middle";
			axis.renderer.labels.template.verticalCenter = "top";
			categoryAxis.renderer.labels.template.fontSize = 12;
			}
			});
	}
	function renderlabourPrivateSectorChart(data) {
		// Create chart instance
		if(data.length == 0) {
			$("#renderlabourPrivateSectorChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#renderlabourPrivateSectorChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderlabourPrivateSectorChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#renderlabourPrivateSectorChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("renderlabourPrivateSectorChartDiv", am4charts.PieChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// Add data
		chart.data = [ {
		"country": "Saudi",
		"population": data[0].saudiPercentagelabour,
		"color": am4core.color("#025635")
		}, {
		"country": "Non-Saudi",
		"population": data[0].nonSaudiPercentageLabour,
		"color": am4core.color("#BF9B2E")
		}];

		// Set inner radius
		chart.innerRadius = am4core.percent(30);

		// Add and configure Series
		var pieSeries = chart.series.push(new am4charts.PieSeries());
		pieSeries.dataFields.value = "population";
		pieSeries.dataFields.category = "country";
		pieSeries.slices.template.stroke = am4core.color("#fff");
		pieSeries.slices.template.strokeWidth = 2;
		pieSeries.slices.template.strokeOpacity = 1;
		pieSeries.slices.template.propertyFields.fill = "color";


		//responsive
		pieSeries.ticks.template.disabled = true;
		pieSeries.alignLabels = false;
		pieSeries.labels.template.text = "{value.percent.formatNumber('#.0')}%";
		pieSeries.labels.template.radius = am4core.percent(-40);
		pieSeries.labels.template.fill = am4core.color("white");
		pieSeries.slices.template.tooltipText = "{category}: {value.percent.formatNumber('#.0')}%";
		chart.legend = new am4charts.Legend();
		//end responsive

		// This creates initial animation
		pieSeries.hiddenState.properties.opacity = 1;
		pieSeries.hiddenState.properties.endAngle = -90;
		pieSeries.hiddenState.properties.startAngle = -90;
	}
	function renderemploymentByRegionChart(chartData){
		// Create chart instance
		// alert(chartData.length);
		console.log(chartData);
		// console.log(chartData[1]);
		var data, data2;
		if(chartData.length > 1){
			if(chartData[0].length > 0 && chartData[1].length > 0){
				data = chartData[0];
				data2 = chartData[1];
			}
			else {
				data = chartData[0];
			}
		}
		else {
			data = chartData;
		}
		// alert(data.length);
		if(data.length == 0) {
			$("#renderemploymentByRegionChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#renderemploymentByRegionChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderemploymentByRegionChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#renderemploymentByRegionChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("renderemploymentByRegionChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// console.log(data);
		// Add data
		if(chartData.length > 1) {
			if(chartData[0].length > 0 && chartData[1].length > 0){
				chart.data = [
					{
						"region": "Riyadh",
						"saudi": data[0].riyadhPercentage,
						"nonSaudi": data2[0].riyadhPercentage
					},
					{
						"region": "Makkah",
						"saudi": data[0].makkahPercentage,
						"nonSaudi": data2[0].makkahPercentage
					},
					{
						"region": "Madinah",
						"saudi": data[0].almadinaPercentage,
						"nonSaudi": data2[0].almadinaPercentage
					},
					{
						"region": "Qassim",
						"saudi": data[0].alqassimPercentage,
						"nonSaudi": data2[0].alqassimPercentage
					},
					{
						"region": "Eastern Region",
						"saudi": data[0].easternPercentage,
						"nonSaudi": data2[0].easternPercentage
					},
					{
						"region": "Assir",
						"saudi": data[0].asirPercentage,
						"nonSaudi": data2[0].asirPercentage
					},
					{
						"region": "Tabuk",
						"saudi": data[0].tabukPercentage,
						"nonSaudi": data2[0].tabukPercentage
					},
					{
						"region": "Hail",
						"saudi": data[0].hailPercentage,
						"nonSaudi": data2[0].hailPercentage
					},
					{
						"region": "Northern borders",
						"saudi": data[0].northernPercentage,
						"nonSaudi": data2[0].northernPercentage
					},
					{
						"region": "Jazan",
						"saudi": data[0].jazanPercentage,
						"nonSaudi": data2[0].jazanPercentage
					},
					{
						"region": "Najran",
						"saudi": data[0].najranPercentage,
						"nonSaudi": data2[0].najranPercentage
					},
					{
						"region": "Albahah",
						"saudi": data[0].albahaPercentage,
						"nonSaudi": data2[0].albahaPercentage
					},
					{
						"region": "Aljouf",
						"saudi": data[0].aljawfPercentage,
						"nonSaudi": data2[0].aljawfPercentage
					}
					];
			}
			else {
				chart.data = [
					{
						"region": "Riyadh",
						"saudi": data[0].riyadhPercentage
					},
					{
						"region": "Makkah",
						"saudi": data[0].makkahPercentage
					},
					{
						"region": "Madinah",
						"saudi": data[0].almadinaPercentage
					},
					{
						"region": "Qassim",
						"saudi": data[0].alqassimPercentage
					},
					{
						"region": "Eastern Region",
						"saudi": data[0].easternPercentage
					},
					{
						"region": "Assir",
						"saudi": data[0].asirPercentage
					},
					{
						"region": "Tabuk",
						"saudi": data[0].tabukPercentage
					},
					{
						"region": "Hail",
						"saudi": data[0].hailPercentage
					},
					{
						"region": "Northern borders",
						"saudi": data[0].northernPercentage
					},
					{
						"region": "Jazan",
						"saudi": data[0].jazanPercentage
					},
					{
						"region": "Najran",
						"saudi": data[0].najranPercentage
					},
					{
						"region": "Albahah",
						"saudi": data[0].albahaPercentage
					},
					{
						"region": "Aljouf",
						"saudi": data[0].aljawfPercentage
					}
					];
			}
		}
		else {
			chart.data = [
				{
					"region": "Riyadh",
					"saudi": data[0].riyadhPercentage
				},
				{
					"region": "Makkah",
					"saudi": data[0].makkahPercentage
				},
				{
					"region": "Madinah",
					"saudi": data[0].almadinaPercentage
				},
				{
					"region": "Qassim",
					"saudi": data[0].alqassimPercentage
				},
				{
					"region": "Eastern Region",
					"saudi": data[0].easternPercentage
				},
				{
					"region": "Assir",
					"saudi": data[0].asirPercentage
				},
				{
					"region": "Tabuk",
					"saudi": data[0].tabukPercentage
				},
				{
					"region": "Hail",
					"saudi": data[0].hailPercentage
				},
				{
					"region": "Northern borders",
					"saudi": data[0].northernPercentage
				},
				{
					"region": "Jazan",
					"saudi": data[0].jazanPercentage
				},
				{
					"region": "Najran",
					"saudi": data[0].najranPercentage
				},
				{
					"region": "Albahah",
					"saudi": data[0].albahaPercentage
				},
				{
					"region": "Aljouf",
					"saudi": data[0].aljawfPercentage
				}
				];
		}


		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "region";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.labels.template.fontSize = 11;
		categoryAxis.renderer.minGridDistance = 0;


		categoryAxis.events.on("sizechanged", function(ev) {
			var axis = ev.target;
			var cellWidth = axis.pixelWidth / (axis.endIndex - axis.startIndex);
			const width  = window.innerWidth || document.documentElement.clientWidth ||
			document.body.clientWidth;
			if (width  < 450) {
			axis.renderer.labels.template.rotation = -90;
			axis.renderer.labels.template.horizontalCenter = "right";
			axis.renderer.labels.template.verticalCenter = "middle";
			categoryAxis.renderer.labels.template.fontSize = 9;
			}
			else if (width  < 600) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else if (width  < 1200) {
				axis.renderer.labels.template.rotation = -90;
				axis.renderer.labels.template.horizontalCenter = "right";
				axis.renderer.labels.template.verticalCenter = "middle";
				categoryAxis.renderer.labels.template.fontSize = 10;
				}
			else {
			axis.renderer.labels.template.rotation = 0;
			axis.renderer.labels.template.horizontalCenter = "middle";
			axis.renderer.labels.template.verticalCenter = "top";
			categoryAxis.renderer.labels.template.fontSize = 12;
			}
			});


		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;

		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "saudi";
		series.dataFields.categoryX = "region";
		series.name = "Saudi";
		series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}%[/]";
		series.columns.template.fill = am4core.color("#025635");

		if(chartData.length != 1) {
			// Create series
			var series1 = chart.series.push(new am4charts.ColumnSeries());
			series1.dataFields.valueY = "nonSaudi";
			series1.dataFields.categoryX = "region";
			series1.name = "Non-Saudi";
			series1.columns.template.tooltipText = "{categoryX}: [bold]{valueY}%[/]";
			series1.columns.template.fill = am4core.color("#BF9B2E");
		}

		// series.columns.template.fillOpacity = .8;

		var columnTemplate = series.columns.template;
		columnTemplate.strokeWidth = 0;
		columnTemplate.strokeOpacity = 1;

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}
	function isFloat (n) {
		return Number(n) === n && n % 1 !== 0;
	}
	function rendersaudiUnemploymentChart(chartData){
		// Create chart instance
		// console.log(chartData.length);
		var data, data2;
		if(chartData.length > 1){
			data = chartData[0];
			data2 = chartData[1];
		}
		else {
			data = chartData;
			data2 = JSON.parse($("#nonSaudiUnemploymentJson").text());
		}
		if(data.length == 0) {
			$("#rendersaudiUnemploymentChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#rendersaudiUnemploymentChartDiv").attr("style", "display: none;");
			// $(".INL_UEMP_header_number_gold").html("0%");
			$(".INL_UEMP_header_number_green").html("0%");
			return;
		}
		else {
			$("#rendersaudiUnemploymentChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#rendersaudiUnemploymentChartDiv").attr("style", "display: block;");
			// $(".INL_UEMP_header_number_gold").html("0%");
			// $(".INL_UEMP_header_number_green").html("0%");
			var saudiUnEmp = data[data.length-1].total;
			var nonSaudiUnEmp = data2[data2.length-1].total;
			// for(var i=0;i<data.length;i++) {
			// 	// alert(i);
			// 	saudiUnEmp = saudiUnEmp + data[i].total;
			// }
			// for(var i=0;i<data2.length;i++) {
			// 	nonSaudiUnEmp = nonSaudiUnEmp + data2[i].total;
			// }
			// $(".INL_UEMP_header_number_gold").html((!isFloat(nonSaudiUnEmp) ? nonSaudiUnEmp : nonSaudiUnEmp.toFixed(2))+"%");
			$(".INL_UEMP_header_number_green").html((!isFloat(saudiUnEmp) ? saudiUnEmp : saudiUnEmp.toFixed(2))+"%");
		}
		var chart = am4core.create("rendersaudiUnemploymentChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// Create chart instance

		// console.log("tet"+data2);
		// Add data
		chart.data = [
			{
				year: data[0].year,
				saudi: data[0].total,
				nonSaudi: data2[0].total
			},
			{
				year: data[1].year,
				saudi: data[1].total,
				nonSaudi: data2[1].total
			},
			{
				year: data[2].year,
				saudi: data[2].total,
				nonSaudi: data2[2].total
			},
			{
				year: data[3].year,
				saudi: data[3].total,
				nonSaudi: data2[3].total
			},
		];

		// Create category axis
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";

		// Create value axis
		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Unemployment";
		valueAxis.renderer.minLabelPosition = 0.01;
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;

		// Create series
		var series1 = chart.series.push(new am4charts.LineSeries());
		series1.dataFields.valueY = "saudi";
		series1.dataFields.categoryX = "year";
		series1.name = "Saudi";
		var circleBullet1 = series1.bullets.push(new am4charts.CircleBullet());
		circleBullet1.circle.radius = 5;
		circleBullet1.circle.fill = am4core.color("#025635");
		circleBullet1.circle.fillOpacity = 1;
		circleBullet1.circle.stroke = am4core.color("#025635");
		circleBullet1.circle.strokeOpacity = 1;
		series1.tooltipText = "{name} in {categoryX}: {valueY}";
		series1.tooltip.getFillFromObject = false;
		series1.tooltip.background.fill = am4core.color("#025635");
		series1.legendSettings.valueText = "{valueY}";
		series1.stroke = am4core.color("#025635");
		// series1.lines.fill = am4core.color("#025635");

		var series2 = chart.series.push(new am4charts.LineSeries());
		series2.dataFields.valueY = "nonSaudi";
		series2.dataFields.categoryX = "year";
		series2.name = 'Non-Saudi';
		var circleBullet2 = series2.bullets.push(new am4charts.CircleBullet());
		circleBullet2.circle.radius = 5;
		circleBullet2.circle.fill = am4core.color("#BF9B2E");
		circleBullet2.circle.fillOpacity = 1;
		circleBullet2.circle.stroke = am4core.color("#BF9B2E");
		circleBullet2.circle.strokeOpacity = 1;
		series2.tooltipText = "{name} in {categoryX}: {valueY}";
		series2.tooltip.getFillFromObject = false;
		series2.tooltip.background.fill = am4core.color("#BF9B2E");
		series2.legendSettings.valueText = "{valueY}";
		// series2.lines.fill = am4core.color("#BF9B2E");
		series2.stroke = am4core.color("#BF9B2E");

		// Add chart cursor
		chart.cursor = new am4charts.XYCursor();
		chart.cursor.behavior = "zoomY";
		// chart.legend = new am4charts.Legend();
		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}

	function renderSectionCharts(type, data) {
		if(type == 'overallSaudiPopulation') {
			renderoverallSaudiPopulationChart(data);
		} else if(type == 'graduatesByDegree') {
			rendergraduatesByDegreeChart(data);
		}
		else if(type == 'populationByRegion'){
			renderpopulationByRegionChart(data);
		}
		else if(type == 'labourPrivateSector'){
			renderlabourPrivateSectorChart(data);
		}
		else if(type == 'employmentByRegion'){
			renderemploymentByRegionChart(data);
		}
		else if(type == 'saudiUnemployment'){
			rendersaudiUnemploymentChart(data);
		}
	}

	if($("#saudiPopulationJson").length > 0){
		var data = JSON.parse($("#saudiPopulationJson").text());
		renderSectionCharts("overallSaudiPopulation", data);
	}
	if($("#graduatesByDegreeJson").length > 0){
		var data = JSON.parse($("#graduatesByDegreeJson").text());
		renderSectionCharts("graduatesByDegree", data);
	}
	if($("#populationByRegionJson").length > 0){
		var data = JSON.parse($("#populationByRegionJson").text());
		renderSectionCharts("populationByRegion", data);
	}
	if($("#labourPrivateSectorJson").length > 0){
		var data = JSON.parse($("#labourPrivateSectorJson").text());
		renderSectionCharts("labourPrivateSector", data);
	}
	// alert($("#employmentByRegionJson").length);
	if($("#saudiEmploymentByRegionJson").length > 0 || $("#nonSaudiEmploymentByRegionJson").length > 0){
		// alert($("#employmentByRegionJson").children().length);
		// if($("#saudiEmploymentByRegionJson").length > 0 && $("#nonSaudiEmploymentByRegionJson").length > 0) {
		// 	var data = JSON.parse($("#saudiEmploymentByRegionJson").text());
		// 	var data2 = JSON.parse($("#nonSaudiEmploymentByRegionJson").text());
		// 	var array1 = [data,data2];
		// 	renderSectionCharts("employmentByRegion", array1);
		// }
		// else if($("#saudiEmploymentByRegionJson").length > 0) {
		// 	var data = JSON.parse($("#saudiEmploymentByRegionJson").text());
		// 	renderSectionCharts("employmentByRegion", data);
		// }
		// else {
		// 	var data = JSON.parse($("#nonSaudiEmploymentByRegionJson").text());
		// 	renderSectionCharts("employmentByRegion", data);
		// }
		// var data = JSON.parse($("#saudiEmploymentByRegionJson").text());
		// renderSectionCharts("employmentByRegion", data);
	}
	if($("#saudiUnemploymentJson").length > 0){
		var data = JSON.parse($("#saudiUnemploymentJson").text());
		renderSectionCharts("saudiUnemployment", data);
	}
	if($("#avgMonthlyWagesJson").length > 0){
		var data = JSON.parse($("#avgMonthlyWagesJson").text());
		// alert(data[0].total);
		$("h4.INL_EEMP_number_set").html(data[0].total.toLocaleString());
	}
	$("#apply").on("click", function () {
		var form = $("#rankChart").form();
		var url = form.attr('action');
		var data = {
			'startYear': $(".startYear").val(),
			'endYear': $(".endYear").val(),
			'indicator': $('input[name="indicator"]:checked').val() // 4
		};
		// console.log(data);
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: url,
			data: data
			, // serializes the form's elements.
			success: function(data)
			{
				// console.log(data);
				// $("html").html(data);
				var parser = new DOMParser();
                doc = parser.parseFromString(data, "text/html");
                var chartData = doc.getElementById("Json-data").innerHTML;
				// console.log(JSON.parse(chartData));
				renderIndicesCharts(JSON.parse(chartData));
				// console.log(jQuery(data).find('#Json-data').text());
				// alert(data); // show response from the php script.
			}
		});
    });
	function changeYear(year) {
		var form = $("#SecrorChart").form();
		var url = form.attr('action');
		var data = {
			'year': year,
		};
		// alert(date, 'change')
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: url,
			data: data, // serializes the form's elements.
			success: function(data)
			{
				var parser = new DOMParser();
                doc = parser.parseFromString(data, "text/html");
                var chartData = doc.getElementById("saudiPopulationJson").innerHTML;
				// alert(doc.getElementById("employmentByRegionJson") != null);
				// console.log(doc.getElementById("saudiEmploymentByRegionJson").innerHTML);
				// console.log(doc.getElementById("nonSaudiEmploymentByRegionJson").innerHTML);

				if(doc.getElementById("saudiPopulationJson") != null){
					var data = JSON.parse(doc.getElementById("saudiPopulationJson").innerHTML);
					renderSectionCharts("overallSaudiPopulation", data);
				}
				else {
					$("#renderoverallSaudiPopulationChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#renderoverallSaudiPopulationChartDiv").attr("style", "display: none;");
				}
				if(doc.getElementById("graduatesByDegreeJson") != null){
					var data = JSON.parse(doc.getElementById("graduatesByDegreeJson").innerHTML);
					renderSectionCharts("graduatesByDegree", data);
				}
				else {
					$("#rendergraduatesByDegreeChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#rendergraduatesByDegreeChartDiv").attr("style", "display: none;");
				}
				if(doc.getElementById("populationByRegionJson") != null){
					var data = JSON.parse(doc.getElementById("populationByRegionJson").innerHTML);
					renderSectionCharts("populationByRegion", data);
				}
				else {
					$("#renderpopulationByRegionDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#renderpopulationByRegionDiv").attr("style", "display: none;");
				}
				if(doc.getElementById("labourPrivateSectorJson") != null){
					var data = JSON.parse(doc.getElementById("labourPrivateSectorJson").innerHTML);
					renderSectionCharts("labourPrivateSector", data);
				}
				else {
					$("#renderlabourPrivateSectorChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#renderlabourPrivateSectorChartDiv").attr("style", "display: none;");
				}
				if(doc.getElementById("saudiEmploymentByRegionJson") != null){
					// var data = JSON.parse(doc.getElementById("employmentByRegionJson").innerHTML);
					// renderSectionCharts("employmentByRegion", data);
					if(doc.getElementById("saudiEmploymentByRegionJson") != null && doc.getElementById("nonSaudiEmploymentByRegionJson") != null) {
						var data = JSON.parse(doc.getElementById("saudiEmploymentByRegionJson").innerHTML);
						var data2 = JSON.parse(doc.getElementById("nonSaudiEmploymentByRegionJson").innerHTML);
						var array1 = [data,data2];
						// console.log(array1);
						renderSectionCharts("employmentByRegion", array1);
					}
					else if(doc.getElementById("saudiEmploymentByRegionJson") != null) {
						var data = JSON.parse(doc.getElementById("saudiEmploymentByRegionJson").innerHTML);
						renderSectionCharts("employmentByRegion", data);
					}
					else {
						var data2 = JSON.parse(doc.getElementById("nonSaudiEmploymentByRegionJson").innerHTML);
						renderSectionCharts("employmentByRegion", data2);
					}
					// var data = JSON.parse($("#saudiEmploymentByRegionJson").text());
					// renderSectionCharts("employmentByRegion", data);
				}
				else{
					$("#renderemploymentByRegionChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#renderemploymentByRegionChartDiv").attr("style", "display: none;");
				}
				if(doc.getElementById("saudiUnemploymentJson") != null){
					// alert(1);
					var data = JSON.parse(doc.getElementById("saudiUnemploymentJson").innerHTML);
					var data2 = JSON.parse(doc.getElementById("nonSaudiUnemploymentJson").innerHTML);
					var array1 = [data,data2];
					// console.log(array1);
					renderSectionCharts("saudiUnemployment", array1);
				}
				else {
					$("#rendersaudiUnemploymentChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
					$("#rendersaudiUnemploymentChartDiv").attr("style", "display: none;");
					// $(".INL_UEMP_header_number_gold").html("0%");
					$(".INL_UEMP_header_number_green").html("0%");
				}
				if(doc.getElementById("avgMonthlyWagesJson") != null){
					var data = JSON.parse(doc.getElementById("avgMonthlyWagesJson").innerHTML);
					// alert(data[0].total);
					$("h4.INL_EEMP_number_set").html(data[0].total.toLocaleString());
				}
				else {
					$("h4.INL_EEMP_number_set").html("0");
				}
				if(doc.getElementById("overallUnemploymentJson") != null){
					var data = JSON.parse(doc.getElementById("overallUnemploymentJson").innerHTML);
					console.log(data);
					if(data.length !=0) {
						$("h6.INL_UEMP_header_number_gold").html((data[data.length-1].total)+'%');
					}
					else {
						$("h6.INL_UEMP_header_number_gold").html("0%");
					}
				}
				else {
					$("h6.INL_UEMP_header_number_gold").html("0%");
				}
			}
		});
	}
	$('.yearpicker').yearpicker({
		year:2010,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			changeYear(value);
		}
	});

	$('.startYear').yearpicker({
		year:2010,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.endYear').yearpicker({
		year:new Date().getFullYear(),
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.sectorStartYear').yearpicker({
		year:2017,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.sectorEndYear').yearpicker({
		year:new Date().getFullYear(),
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});
	$('.capitalStartYear').yearpicker({
		year:2017,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.capitalEndYear').yearpicker({
		year:new Date().getFullYear(),
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.fundAssetStartYear').yearpicker({
		year:2017,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});
	$('.fundAssetEndYear').yearpicker({
		year:new Date().getFullYear(),
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	$('.commercialStartYear').yearpicker({
		year:2017,
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});
	$('.commercialEndYear').yearpicker({
		year:new Date().getFullYear(),
		startYear:2010,
		endYear: new Date().getFullYear(),
		itemTag:'li',
		selectedClass:'selected',
		disabledClass:'disabled',
		hideClass:'hide',
		template: `<div class="yearpicker-container">
		<div class="yearpicker-header">
						<div class="yearpicker-prev" data-view="yearpicker-prev">&lsaquo;</div>
						<div class="yearpicker-current" data-view="yearpicker-current">SelectedYear</div>
						<div class="yearpicker-next" data-view="yearpicker-next">&rsaquo;</div>
					</div>
					<div class="yearpicker-body">
						<ul class="yearpicker-year" data-view="years">
						</ul>
					</div>
				</div>
		`,
		onChange:function(value){
		//   alert(value);
			// changeYear(value);
		}
	});

	function appendSelectedList(name, id) {
		$("#selectedSectorsList").append('<div class="INID_border_filter_in_line" data-id='+id+'>'+ name +'<div class="INDS_Filter_by_Close">X</div></div>');
		var found = {};
		$('#selectedSectorsList [data-id]').each(function(){
			var $this = $(this);
			if(found[$this.data('id')]){
				$this.remove();
			}
			else{
				found[$this.data('id')] = true;
			}
		});
	}
	function indexOfMax(arr) {
		if (arr.length === 0) {
			return -1;
		}

		var max = arr[0];
		var maxIndex = 0;

		for (var i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				maxIndex = i;
				max = arr[i];
			}
		}

		return maxIndex;
	}
	function renderFDIValueChart(data){
		// Create chart instance
		// alert("test");
		if(data.length == 0) {
			$("#renderFDIValueChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#renderFDIValueChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderFDIValueChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#renderFDIValueChartDiv").attr("style", "display: block;");
		}
		am4core.addLicense("CH292550473");
		var chart = am4core.create("renderFDIValueChartDiv", am4charts.XYChart);
		// am4core.addLicense("CH292550473");
		chart.exporting.menu = new am4core.ExportMenu();
		// Add data
		chart.data = data;
		dataObjectLength = [];
		for(var i=0;i <data.length; i++) {
			console.log(data[i]);
			dataObjectLength.push(Object.keys(data[i]).length);
		}
		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.labels.template.fontSize = $(".sector-period .active").attr("data-id") == 'Annually' ? 12 : 0;
		categoryAxis.renderer.minGridDistance = 30;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Number of Licenses";
		valueAxis.min = 0;
		valueAxis.strictMinMax = true;

		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;

		Object.keys(data[indexOfMax(dataObjectLength)]).forEach(function(keyValue, index, map) {
			if(keyValue != "year"){
				// console.log(keyValue);
				// Create series
				$('.investment-data input[type=checkbox]').each(function(){
					if($(this).attr("id") == keyValue){
						$(this).attr('checked','checked');
						if(keyValue == 'manufacturingLicenses'){
							appendSelectedList("Manufacturing Licenses", 'manufacturingLicenses');
						} else if(keyValue == 'constructionLicenses') {
							appendSelectedList("Construction Licenses", 'constructionLicenses');
						} else if(keyValue == 'wholeSaleRetailTradeMoLicenses') {
							appendSelectedList("Wholesale and retail trade, repair of mo Licenses", 'wholeSaleRetailTradeMoLicenses');
							// series.name = "Wholesale and retail trade, repair of mo Licenses";
						} else if(keyValue == 'informationAndCommunicationLicenses') {
							appendSelectedList("Information and communication Licenses", 'informationAndCommunicationLicenses');
							// series.name = "Information and communication Licenses";
						} else if(keyValue == 'professionalScientificAndTechnicalLicenses') {
							appendSelectedList("Professional, scientific and technical Licenses", 'professionalScientificAndTechnicalLicenses');
							// series.name = "Professional, scientific and technical Licenses";
						} else if(keyValue == 'accommodationFoodServiceActivityLicenses') {
							appendSelectedList("Accommodation and food service actives Licenses", 'accommodationFoodServiceActivityLicenses');
							// series.name = "Accommodation and food service actives Licenses";
						} else if(keyValue == 'administrativeSupportServiceActivityLicenses') {
							appendSelectedList("Administration and storage Licenses", 'administrativeSupportServiceActivityLicenses');
							// series.name = "Administration and storage Licenses";
						} else if(keyValue == 'transportationStorageLicenses') {
							appendSelectedList("Transportation and storage Licenses", 'transportationStorageLicenses');
							// series.name = "Transportation and storage Licenses";
						} else if(keyValue == 'humanHealthSocialworkActivityLicenses') {
							appendSelectedList("Human health and social work activities licenses", 'humanHealthSocialworkActivityLicenses');
							// series.name = "Human health and social work activities licenses";
						} else if(keyValue == 'financialInsuranceActivityLicenses') {
							appendSelectedList("Financial and insurance activities licenses", 'financialInsuranceActivityLicenses');
							// series.name = "Financial and insurance activities licenses";
						} else if(keyValue == 'otherServiceActivityLicenses') {
							appendSelectedList("Other service activities licenses", 'otherServiceActivityLicenses');
							// series.name = "Other service activities licenses";
						} else if(keyValue == 'miningQuarryingLicenses') {
							appendSelectedList("Mining and quarrying licenses", 'miningQuarryingLicenses');
							// series.name = "Mining and quarrying licenses";
						} else if(keyValue == 'waterSupplySewerageWasteManagement') {
							appendSelectedList("Water supply, sewerage, waste management", 'waterSupplySewerageWasteManagement');
							// series.name = "Water supply, sewerage, waste management";
						} else if(keyValue == 'educationLicenses') {
							appendSelectedList("Education licenses", 'educationLicenses');
							// series.name = "Education licenses";
						} else if(keyValue == 'realEstateActivityLicenses') {
							appendSelectedList("Real estate activities licenses", 'realEstateActivityLicenses');
							// series.name = "Real estate activities licenses";
						} else if(keyValue == 'artsEntertainmentRecreationLicenses') {
							appendSelectedList("Arts, entertainment and recreation licenses", 'artsEntertainmentRecreationLicenses');
							// series.name = "Arts, entertainment and recreation licenses";
						} else if(keyValue == 'electricityGasSteamAircondition') {
							appendSelectedList("Electricity, gas, steam and air conditioner", 'electricityGasSteamAircondition');
							// series.name = "Electricity, gas, steam and air conditioner";
						} else if(keyValue == 'agricultureForestryFishing Licenses'){
							appendSelectedList("Agriculture, forestry and fishing licenses", 'agricultureForestryFishingLicenses');
							// series.name = "Agriculture, forestry and fishing licenses";
						} else if(keyValue == 'notAssignedLicenses') {
							appendSelectedList("Not assigned licenses", 'notAssignedLicenses');
							// series.name = "Not assigned licenses";
						} else if(keyValue == 'publicAdministrationDefenceCompuLicenses') {
							appendSelectedList("Public administration and defence compu licenses", 'publicAdministrationDefenceCompuLicenses');
							// series.name = "Public administration and defence; compu licenses";
						} else if(keyValue == 'activitiesOfExtraterritorialOrganizationLicenses')
							appendSelectedList("Activities of extraterritorial organization license", 'activitiesOfExtraterritorialOrganizationLicenses');{
							// series.name = "Activities of extraterritorial organizat license";
						}
					}
				 });
				var series = chart.series.push(new am4charts.ColumnSeries());
				series.dataFields.valueY = keyValue;
				series.dataFields.categoryX = "year";
				if(keyValue == 'manufacturingLicenses'){
					series.name = "Manufacturing Licenses";
					series.columns.template.tooltipText = "{categoryX}: Manufacturing Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'constructionLicenses') {
					series.name = "Construction Licenses";
					series.columns.template.tooltipText = "{categoryX}: Construction Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'wholeSaleRetailTradeMoLicenses') {
					series.name = "Wholesale and retail trade, repair of mo Licenses";
					series.columns.template.tooltipText = "{categoryX}: Wholesale and retail trade, repair of mo Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'informationAndCommunicationLicenses') {
					series.name = "Information and communication Licenses";
					series.columns.template.tooltipText = "{categoryX}: Information and communication Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'professionalScientificAndTechnicalLicenses') {
					series.name = "Professional, scientific and technical Licenses";
					series.columns.template.tooltipText = "{categoryX}: Professional, scientific and technical Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'accommodationFoodServiceActivityLicenses') {
					series.name = "Accommodation and food service actives Licenses";
					series.columns.template.tooltipText = "{categoryX}: Accommodation and food service actives Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'administrativeSupportServiceActivityLicenses') {
					series.name = "Administration and storage Licenses";
					series.columns.template.tooltipText = "{categoryX}: Administration and storage Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'transportationStorageLicenses') {
					series.name = "Transportation and storage Licenses";
					series.columns.template.tooltipText = "{categoryX}: Transportation and storage Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'humanHealthSocialworkActivityLicenses') {
					series.name = "Human health and social work activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Human health and social work activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'financialInsuranceActivityLicenses') {
					series.name = "Financial and insurance activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Financial and insurance activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'otherServiceActivityLicenses') {
					series.name = "Other service activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Other service activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'miningQuarryingLicenses') {
					series.name = "Mining and quarrying licenses";
					series.columns.template.tooltipText = "{categoryX}: Mining and quarrying licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'waterSupplySewerageWasteManagement') {
					series.name = "Water supply, sewerage, waste management";
					series.columns.template.tooltipText = "{categoryX}: Water supply, sewerage, waste management: [bold]{valueY}[/]";
				} else if(keyValue == 'educationLicenses') {
					series.name = "Education licenses";
					series.columns.template.tooltipText = "{categoryX}: Education licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'realEstateActivityLicenses') {
					series.name = "Real estate activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Real estate activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'artsEntertainmentRecreationLicenses') {
					series.name = "Arts, entertainment and recreation licenses";
					series.columns.template.tooltipText = "{categoryX}: Arts, entertainment and recreation licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'electricityGasSteamAircondition') {
					series.name = "Electricity, gas, steam and air conditioner";
					series.columns.template.tooltipText = "{categoryX}: Electricity, gas, steam and air conditioner: [bold]{valueY}[/]";
				} else if(keyValue == 'agricultureForestryFishingLicenses') {
					series.name = "Agriculture, forestry and fishing licenses";
					series.columns.template.tooltipText = "{categoryX}: Agriculture, forestry and fishing licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'notAssignedLicenses') {
					series.name = "Not assigned licenses";
					series.columns.template.tooltipText = "{categoryX}: Not assigned licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'publicAdministrationDefenceCompuLicenses') {
					series.name = "Public administration and defence compu licenses";
					series.columns.template.tooltipText = "{categoryX}: Public administration and defence compu licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'activitiesOfExtraterritorialOrganizationLicenses') {
					series.name = "Activities of extraterritorial organization license";
					series.columns.template.tooltipText = "{categoryX}: Activities of extraterritorial organizat license: [bold]{valueY}[/]";
				}
				series.columns.template.fillOpacity = .8;
				// var valueLabel = series.bullets.push(new am4charts.LabelBullet());
				// valueLabel.label.text = "{valueY}";
				// valueLabel.label.fontSize = 9;
				// valueLabel.label.dy = -10;
			}
		});

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
		// var columnTemplate = series.columns.template;
		// columnTemplate.strokeWi0dth = 0;
		// columnTemplate.strokeOpacity = 1;
	}
	function renderFDIGrowthChart(data){
		// Create chart instance
		// alert("test");
		if(data.length == 0) {
			$("#renderFDIGrowthChartDiv").parent().find("#growthChartDivError").attr("style", "display: flex;");
			$("#renderFDIGrowthChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#renderFDIGrowthChartDiv").parent().find("#growthChartDivError").attr("style", "display: none;");
			$("#renderFDIGrowthChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("renderFDIGrowthChartDiv", am4charts.XYChart);

		chart.exporting.menu = new am4core.ExportMenu();
		// Add data
		chart.data = data;
		dataObjectLength = [];
		for(var i=0;i <data.length; i++) {
			console.log(data[i]);
			dataObjectLength.push(Object.keys(data[i]).length);
		}
		// console.log(indexOfMax(dataObjectLength));
		// Create axes

		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.labels.template.fontSize = $(".sector-period .active").attr("data-id") == 'Annually' ? 12 : 0;
		categoryAxis.renderer.minGridDistance = 30;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Growth Rate";
		// valueAxis.min = 0;
		// valueAxis.strictMinMax = true;

		categoryAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.renderer.grid.template.strokeWidth = 0;
		// alert(Object.keys(data[0]).length);
		// var iterateData = Object.keys(data[0]).length == 0 ? data[data.length-1]: data[0];
		Object.keys(data[indexOfMax(dataObjectLength)]).forEach(function(keyValue, index, map) {
			if(keyValue != "year"){
				// console.log(keyValue);
				// Create series
				var series = chart.series.push(new am4charts.ColumnSeries());
				series.dataFields.valueY = keyValue;
				series.dataFields.categoryX = "year";
				if(keyValue == 'manufacturingLicenses'){
					series.name = "Manufacturing Licenses";
					series.columns.template.tooltipText = "{categoryX}: Manufacturing Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'constructionLicenses') {
					series.name = "Construction Licenses";
					series.columns.template.tooltipText = "{categoryX}: Construction Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'wholeSaleRetailTradeMoLicenses') {
					series.name = "Wholesale and retail trade, repair of mo Licenses";
					series.columns.template.tooltipText = "{categoryX}: Wholesale and retail trade, repair of mo Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'informationAndCommunicationLicenses') {
					series.name = "Information and communication Licenses";
					series.columns.template.tooltipText = "{categoryX}: Information and communication Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'professionalScientificAndTechnicalLicenses') {
					series.name = "Professional, scientific and technical Licenses";
					series.columns.template.tooltipText = "{categoryX}: Professional, scientific and technical Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'accommodationFoodServiceActivityLicenses') {
					series.name = "Accommodation and food service actives Licenses";
					series.columns.template.tooltipText = "{categoryX}: Accommodation and food service actives Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'administrativeSupportServiceActivityLicenses') {
					series.name = "Administration and storage Licenses";
					series.columns.template.tooltipText = "{categoryX}: Administration and storage Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'transportationStorageLicenses') {
					series.name = "Transportation and storage Licenses";
					series.columns.template.tooltipText = "{categoryX}: Transportation and storage Licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'humanHealthSocialworkActivityLicenses') {
					series.name = "Human health and social work activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Human health and social work activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'financialInsuranceActivityLicenses') {
					series.name = "Financial and insurance activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Financial and insurance activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'otherServiceActivityLicenses') {
					series.name = "Other service activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Other service activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'miningQuarryingLicenses') {
					series.name = "Mining and quarrying licenses";
					series.columns.template.tooltipText = "{categoryX}: Mining and quarrying licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'waterSupplySewerageWasteManagement') {
					series.name = "Water supply, sewerage, waste management";
					series.columns.template.tooltipText = "{categoryX}: Water supply, sewerage, waste management: [bold]{valueY}[/]";
				} else if(keyValue == 'educationLicenses') {
					series.name = "Education licenses";
					series.columns.template.tooltipText = "{categoryX}: Education licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'realEstateActivityLicenses') {
					series.name = "Real estate activities licenses";
					series.columns.template.tooltipText = "{categoryX}: Real estate activities licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'artsEntertainmentRecreationLicenses') {
					series.name = "Arts, entertainment and recreation licenses";
					series.columns.template.tooltipText = "{categoryX}: Arts, entertainment and recreation licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'electricityGasSteamAircondition') {
					series.name = "Electricity, gas, steam and air conditioner";
					series.columns.template.tooltipText = "{categoryX}: Electricity, gas, steam and air conditioner: [bold]{valueY}[/]";
				} else if(keyValue == 'agricultureForestryFishingLicenses') {
					series.name = "Agriculture, forestry and fishing licenses";
					series.columns.template.tooltipText = "{categoryX}: Agriculture, forestry and fishing licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'notAssignedLicenses') {
					series.name = "Not assigned licenses";
					series.columns.template.tooltipText = "{categoryX}: Not assigned licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'publicAdministrationDefenceCompuLicenses') {
					series.name = "Public administration and defence compu licenses";
					series.columns.template.tooltipText = "{categoryX}: Public administration and defence compu licenses: [bold]{valueY}[/]";
				} else if(keyValue == 'activitiesOfExtraterritorialOrganizationLicenses') {
					series.name = "Activities of extraterritorial organizat license";
					series.columns.template.tooltipText = "{categoryX}: Activities of extraterritorial organization license: [bold]{valueY}[/]";
				}
				series.columns.template.fillOpacity = .8;
				// var valueLabel = series.bullets.push(new am4charts.LabelBullet());
				// valueLabel.label.text = "{valueY}";
				// valueLabel.label.fontSize = 12;
				// valueLabel.label.dy = -10;
			}
		});

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
		// var columnTemplate = series.columns.template;
		// columnTemplate.strokeWi0dth = 0;
		// columnTemplate.strokeOpacity = 1;
	}

	function rendercapitalInformationChart(chartData){
		// Create chart instance
		// alert("test");
		// console.log(chartData);
		if(chartData.length == 0) {
			$("#capitalInformationChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#capitalInformationChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#capitalInformationChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#capitalInformationChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("capitalInformationChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.marginRight = 400;
		chart.data = chartData;
		chart.cursor = new am4charts.XYCursor();
		chart.exporting.menu = new am4core.ExportMenu();
		// chart.dateFormatter.dateFormat = "yyyy";
		// chart.numberFormatter.numberFormat = "#";

		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.title.text = "Years";
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		categoryAxis.renderer.labels.template.fontSize = 16;
		categoryAxis.renderer.minGridDistance = 30;

		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = "Million SAR";
		valueAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.min = 0;
		valueAxis.strictMinMax = true;

		var valueAxis2 = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis2.title.text = "Percentage";
		valueAxis2.renderer.opposite = true;
		valueAxis2.renderer.grid.template.disabled = true;

		var columnSeries = chart.series.push(new am4charts.ColumnSeries());
		columnSeries.name = "Gross Fixed Capital Information";
		columnSeries.dataFields.valueY = "grossFixed";
		columnSeries.dataFields.categoryX = "year";
		columnSeries.yAxis = valueAxis;
		columnSeries.columns.template.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
		columnSeries.columns.template.propertyFields.fillOpacity = "fillOpacity";
		columnSeries.columns.template.propertyFields.stroke = "stroke";
		columnSeries.columns.template.propertyFields.strokeWidth = "strokeWidth";
		columnSeries.columns.template.propertyFields.strokeDasharray = "columnDash";
		columnSeries.columns.template.fill = am4core.color("#025635");
		columnSeries.tooltip.label.textAlign = "middle";

		var lineSeries = chart.series.push(new am4charts.LineSeries());
		lineSeries.name = "Gross Fixed Capital Formation Growth Rate";
		lineSeries.dataFields.valueY = "growthrateOfGrossFixed";
		lineSeries.dataFields.categoryX = "year";
		lineSeries.yAxis = valueAxis2;
		lineSeries.stroke = am4core.color("#BF9B2E");
		lineSeries.strokeWidth = 3;
		lineSeries.propertyFields.strokeDasharray = "lineDash";
		lineSeries.tooltip.label.textAlign = "middle";

		var bullet = lineSeries.bullets.push(new am4charts.Bullet());
		bullet.fill = am4core.color("#BF9B2E"); // tooltips grab fill from parent by default
		bullet.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}%[/]"
		var circle = bullet.createChild(am4core.Circle);
		circle.radius = 4;
		circle.fill = am4core.color("#fff");
		circle.strokeWidth = 3;

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}
	function renderfundAssetChart(chartData){
		// Create chart instance
		// alert("test");
		console.log(chartData);
		var valueAxisValue = '';
		if(chartData.length == 0) {
			$("#fundAssetChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#fundAssetChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			if(chartData[0].hasOwnProperty('totalNoOfInvestmentFund')) {
				valueAxisValue = 'totalNoOfInvestmentFund';
			}
			if(chartData[0].hasOwnProperty('totalInvestmentFundAssets')) {
				valueAxisValue = 'totalInvestmentFundAssets';
			}
			if(chartData[0].hasOwnProperty('foreignInvestmentFund')) {
				valueAxisValue = 'foreignInvestmentFund';
			}
			if(chartData[0].hasOwnProperty('domesticInvestmentFund')) {
				valueAxisValue = 'domesticInvestmentFund';
			}
			$("#fundAssetChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#fundAssetChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("fundAssetChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.marginRight = 400;
		chart.data = chartData;
		chart.cursor = new am4charts.XYCursor();
		chart.exporting.menu = new am4core.ExportMenu();
		// chart.dateFormatter.dateFormat = "yyyy";
		// chart.numberFormatter.numberFormat = "#";


		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = $(".fund-assets-period .active").attr("data-id") == 'Annually' ? "year" : 'period';
		categoryAxis.title.text = "Years";
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		categoryAxis.renderer.labels.template.fontSize = $(".fund-assets-period .active").attr("data-id") == 'Annually' ? 16 : 0;;
		categoryAxis.renderer.minGridDistance = 30;



		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = valueAxisValue == 'totalNoOfInvestmentFund' ? "Total No of Investment Funds": "Million SAR";
		valueAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.min = 0;
		valueAxis.strictMinMax = true;

		// var valueAxis2 = chart.yAxes.push(new am4charts.ValueAxis());
		// valueAxis2.title.text = "Percentage";
		// valueAxis2.renderer.opposite = true;
		// valueAxis2.renderer.grid.template.disabled = true;

		var columnSeries = chart.series.push(new am4charts.ColumnSeries());
		columnSeries.name = "Number of investment funds";
		columnSeries.dataFields.valueY = valueAxisValue;
		columnSeries.dataFields.categoryX = $(".fund-assets-period .active").attr("data-id") == 'Annually' ? "year" : 'period';;
		columnSeries.yAxis = valueAxis;
		columnSeries.columns.template.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
		columnSeries.columns.template.propertyFields.fillOpacity = "fillOpacity";
		columnSeries.columns.template.propertyFields.stroke = "stroke";
		columnSeries.columns.template.propertyFields.strokeWidth = "strokeWidth";
		columnSeries.columns.template.propertyFields.strokeDasharray = "columnDash";
		columnSeries.columns.template.fill = am4core.color("#025635");
		columnSeries.tooltip.label.textAlign = "middle";

		// var lineSeries = chart.series.push(new am4charts.LineSeries());
		// lineSeries.name = "Gross Fixed Capital Formation Growth Rateate";
		// lineSeries.dataFields.valueY = "growthrateOfGrossFixed";
		// lineSeries.dataFields.categoryX = "year";
		// lineSeries.yAxis = valueAxis2;
		// lineSeries.stroke = am4core.color("#BF9B2E");
		// lineSeries.strokeWidth = 3;
		// lineSeries.propertyFields.strokeDasharray = "lineDash";
		// lineSeries.tooltip.label.textAlign = "middle";

		// var bullet = lineSeries.bullets.push(new am4charts.Bullet());
		// bullet.fill = am4core.color("#BF9B2E"); // tooltips grab fill from parent by default
		// bullet.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
		// var circle = bullet.createChild(am4core.Circle);
		// circle.radius = 4;
		// circle.fill = am4core.color("#fff");
		// circle.strokeWidth = 3;

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}
	function renderCommercialChart(data){
		// Create chart instance
		// alert("test");
		// console.log(data);
		// return;
		if(data.length == 0) {
			$("#CommercialChartDiv").parent().find("#ChartDivError").attr("style", "display: flex;");
			$("#CommercialChartDiv").attr("style", "display: none;");
			return;
		}
		else {
			$("#CommercialChartDiv").parent().find("#ChartDivError").attr("style", "display: none;");
			$("#CommercialChartDiv").attr("style", "display: block;");
		}
		var chart = am4core.create("CommercialChartDiv", am4charts.XYChart);
		am4core.addLicense("CH292550473");
		chart.data = data;
		chart.exporting.menu = new am4core.ExportMenu();
		let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "year";
		categoryAxis.title.text = "Years";
		categoryAxis.renderer.grid.template.strokeWidth = 0;
		categoryAxis.renderer.labels.template.fontSize = 16;
		categoryAxis.renderer.minGridDistance = 30;

		let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis.title.text = $(".commercial-indicator.active").attr("data-id") == "noOfCommercialRegisters" ? "Number of Commercial Registers" : "Capital of Commercial Registers";
		valueAxis.renderer.grid.template.strokeWidth = 0;
		valueAxis.min = 0;
		valueAxis.strictMinMax = true;

		var valueAxis2 = chart.yAxes.push(new am4charts.ValueAxis());
		valueAxis2.title.text = "Growth Rate";
		valueAxis2.renderer.opposite = true;
		valueAxis2.renderer.grid.template.disabled = true;
		// valueAxis2.min = 0;
		// valueAxis2.strictMinMax = true;
		// alert($(".com-mercial-indicator.active").attr("data-id") == "noOfCommercialRegisters" ? "noOfCommercialRegisters" : "capitalOfCommercialRegisters");
		var columnSeries = chart.series.push(new am4charts.ColumnSeries());
		columnSeries.name = $(".commercial-indicator.active").attr("data-id") == "noOfCommercialRegisters" ? "Number of Commercial Registers" : "Capital of Commercial Registers";
		columnSeries.dataFields.valueY = $(".commercial-indicator.active").attr("data-id") == "noOfCommercialRegisters" ? "noOfCommercialRegisters" : "capitalOfCommercialRegisters";
		columnSeries.dataFields.categoryX = "year";
		columnSeries.yAxis = valueAxis;
		columnSeries.columns.template.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}[/]"
		columnSeries.columns.template.propertyFields.fillOpacity = "fillOpacity";
		columnSeries.columns.template.propertyFields.stroke = "stroke";
		columnSeries.columns.template.propertyFields.strokeWidth = "strokeWidth";
		columnSeries.columns.template.propertyFields.strokeDasharray = "columnDash";
		columnSeries.columns.template.fill = am4core.color("#025635");
		columnSeries.tooltip.label.textAlign = "middle";

		var lineSeries = chart.series.push(new am4charts.LineSeries());
		lineSeries.name = "Growth Rate";
		lineSeries.dataFields.valueY = "growthRate";
		lineSeries.dataFields.categoryX = "year";
		lineSeries.yAxis = valueAxis2;
		lineSeries.stroke = am4core.color("#BF9B2E");
		lineSeries.strokeWidth = 3;
		lineSeries.propertyFields.strokeDasharray = "lineDash";
		lineSeries.tooltip.label.textAlign = "middle";

		var bullet = lineSeries.bullets.push(new am4charts.Bullet());
		bullet.fill = am4core.color("#BF9B2E"); // tooltips grab fill from parent by default
		bullet.tooltipText = "[#fff font-size: 15px]{name} in {categoryX}:\n[/][#fff font-size: 20px]{valueY}[/] [#fff]{additional}%[/]"
		var circle = bullet.createChild(am4core.Circle);
		circle.radius = 4;
		circle.fill = am4core.color("#fff");
		circle.strokeWidth = 3;

		chart.legend = new am4charts.Legend();
		var marker = chart.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc");
	}
	$('.investment-data input[type=checkbox]').change(function() {
		// var invesSectorList = [];
		// if($(".INID_border_filter_in_line").length >= 5) {
		// 	alert("Maximum limit is 5");
		// 	$(this).prop('checked', false);
		// 	return;
		// }
        if(this.checked) {
            // alert($(this).attr("data-id"));
			appendSelectedList($(this).attr("data-id"), $(this).attr("id"));
			// invesSectorList.push({sector: $(this).attr("data-id"), sectoId: $(this).attr("id")})
        }
		else {
			$("#selectedSectorsList").find("[data-id='" + $(this).attr("id") + "']").remove();
		}
    });
	$(document).on('click','.INDS_Filter_by_Close',function(){
		// do something
		// alert("[data-id='" + "#"+$(this).parent().attr("data-id") + "']");
		// $("#"+$(this).parent().attr("data-id")).removeAttr("checked");
		$("#"+$(this).parent().attr("data-id")).prop('checked', false);
		$("#selectedSectorsList").find("[data-id='"+$(this).parent().attr("data-id") + "']").remove();
	});
	if($("#annualValueJson").length > 0){
		var data = JSON.parse($("#annualValueJson").text());
		renderFDIValueChart(data);
	}

	if($("#annualGrowthJson").length > 0){
		var data = JSON.parse($("#annualGrowthJson").text());
		renderFDIGrowthChart(data);
	}

	if($("#capitalInformationJson").length > 0){
		var data = JSON.parse($("#capitalInformationJson").text());
		rendercapitalInformationChart(data);
	}
	if($("#numberOfCommercialRegisterJson").length > 0){
		var data = JSON.parse($("#numberOfCommercialRegisterJson").text());
		renderCommercialChart(data);
	}

	$("#sector-filter").on("click", function () {
		var sectorList = [];
		$(".investment-data input[type=checkbox]").each(function() {
			if(this.checked) {
				sectorList.push($(this).attr("id"));
			}
		});
		// console.log(sectorList.join(","));
		// return;
		var form = $("#sector-filter-form").form();
		var url = form.attr('action');
		var data = {
			'startYear': $(".sectorStartYear").val(),
			'endYear': $(".sectorEndYear").val(),
			'period': $(".sector-period .active").attr("data-id"),
			'sector': sectorList.join(",")
		};
		// console.log(data);
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: url,
			data: data, // serializes the form's elements.
			success: function(data)
			{
				// console.log(data);
				// return;
				// $("html").html(data);
				var parser = new DOMParser();
                doc = parser.parseFromString(data, "text/html");
                // var chartData = doc.getElementById("annualValueJson").innerHTML;
				if($(".sector-period .active").attr("data-id") == 'Annually') {
					if(doc.getElementById("annualValueJson") != null){
						var data3 = JSON.parse(doc.getElementById("annualValueJson").innerHTML);
						renderFDIValueChart(data3);
					}
					if(doc.getElementById("annualGrowthJson") != null){
						var data = JSON.parse(doc.getElementById("annualGrowthJson").innerHTML);
						// console.log(data);
						renderFDIGrowthChart(data);
					}
				}
				else {
					if(doc.getElementById("quarterlyValueJson") != null){
						var data4 = JSON.parse(doc.getElementById("quarterlyValueJson").innerHTML);
						console.log(data4);
						renderFDIValueChart(data4);
					}
					if(doc.getElementById("quarterlyGrowthJson") != null){
						var data = JSON.parse(doc.getElementById("quarterlyGrowthJson").innerHTML);
						console.log(data);
						renderFDIGrowthChart(data);
					}
				}

				// console.log(JSON.parse(chartData));
				// renderFDIValueChart(JSON.parse(chartData));
				// console.log(jQuery(data).find('#Json-data').text());
				// alert(data); // show response from the php script.
			}
		});
    });

	$("#capital-data-submit").on("click", function () {
		var form = $("#capital-data-form").form();
		var url = form.attr('action');
		var data = {
			'startYear': $(".capitalStartYear").val(),
			'endYear': $(".capitalEndYear").val(),
			'sector': "grossFixed"
		};
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: url,
			data: data, // serializes the form's elements.
			success: function(data)
			{
				// console.log(data);
				var parser = new DOMParser();
                doc = parser.parseFromString(data, "text/html");
				if(doc.getElementById("capitalInformationJson") != null){
					var data = JSON.parse(doc.getElementById("capitalInformationJson").innerHTML);
					rendercapitalInformationChart(data);
				}
			}
		});
    });
	function getFundAssetData() {
		if($("#fund-asset-form").length > 0) {
			var form = $("#fund-asset-form").form();
			var url = form.attr('action');
			var data = {
				'startYear': $(".fundAssetStartYear").val(),
				'endYear': $(".fundAssetEndYear").val(),
				'sector': $(".fund-asset-indicator.active").attr("data-id"),
				'period': $(".fund-assets-period .active").attr("data-id")
			};
			// console.log(data);
			$.ajax({
				type: "GET",
				contentType: "application/json; charset=utf-8",
				url: url,
				data: data, // serializes the form's elements.
				success: function(data)
				{
					// console.log(data);
					var parser = new DOMParser();
					doc = parser.parseFromString(data, "text/html");
					if(doc.getElementById("annualFundAssetsJson") != null){
						var data = JSON.parse(doc.getElementById("annualFundAssetsJson").innerHTML);
						renderfundAssetChart(data);
					}
					if(doc.getElementById("quarterlyFundAssetsJson") != null){
						var data = JSON.parse(doc.getElementById("quarterlyFundAssetsJson").innerHTML);
						renderfundAssetChart(data);
					}
				}
			});
		}

	}
	$("#commercial-submit").on("click", function () {
		var form = $("#commercial-form").form();
		var url = form.attr('action');
		var data = {
			'startYear': $(".commercialStartYear").val(),
			'endYear': $(".commercialEndYear").val(),
			'sector': $(".commercial-indicator.active").attr("data-id")
		};
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: url,
			data: data, // serializes the form's elements.
			success: function(data)
			{
				var parser = new DOMParser();
                doc = parser.parseFromString(data, "text/html");
				if($(".commercial-indicator.active").attr("data-id") == "noOfCommercialRegisters") {
					if(doc.getElementById("numberOfCommercialRegisterJson") != null){
						console.log(doc.getElementById("numberOfCommercialRegisterJson").innerHTML);
						var data = JSON.parse(doc.getElementById("numberOfCommercialRegisterJson").innerHTML);
						renderCommercialChart(data);
					}
				} else {
					if(doc.getElementById("capitalOfCommercialRegisterJson") != null){
						console.log(doc.getElementById("capitalOfCommercialRegisterJson").innerHTML);
						var data = JSON.parse(doc.getElementById("capitalOfCommercialRegisterJson").innerHTML);
						renderCommercialChart(data);
					}
				}
			}
		});
    });
	$(".fund-asset-indicator").on("click", function () {
		$(".fund-asset-indicator").removeClass("active");
		$(this).addClass("active");
    });
	$(".commercial-indicator").on("click", function () {
		$(".commercial-indicator").removeClass("active");
		$(this).addClass("active");
    });
	$("#fund-asset-submit").on("click", function () {
		// alert("test");
		getFundAssetData();
    });
	getFundAssetData();
	$("#rankChart").submit(function( event ) {
		// alert( "Handler for .submit() called." );
		event.preventDefault();
	  });
	$("#i-am-interested").on("click", function () {
		// alert(1);
		var scrollDiv = document.getElementById("opp-contact-form").offsetTop - 80;
		window.scrollTo({ top: scrollDiv, behavior: 'smooth'});
	});
	$("#contact-clear").on("click", function () {
		// alert(1);
		$("#opp-contact-form input").val('').change();
		$("#opp-contact-form textarea").val('').change();
		$('#opp-contact-form select option:first').prop('selected',true);
		grecaptcha.reset(1);
	});
	// Vidopop up - Meet the kingdom 7 key reasons

	$(".trigger-videoModal").on("click", function () {
		$("#mediaModal iframe").attr("src",$(this).attr("data-url"));
		$('#mediaModal').modal('show');
    });

    // To prevent mobile number text box from typing alphabets
	$(document).on("input", ".mobile-number", function() {
		this.value = this.value.replace(/\D/g,'');
	});
	$(document).on("input", ".validate-mobile", function() {
		this.value = this.value.replace(/\D/g,'');
	});
	function onContactSubmit() {
		// element[0].disabled = true;
		// element[0].textContent = site.messages().sending;
		$.ajax({
			url: ACC.config.contextPath + '/contactus',
			async: true,
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			headers : {"g-recaptcha-response": grecaptcha.getResponse(1)},
			data: JSON.stringify({
				name: $.trim($("#crName").val()),
				email: $.trim($("#crEmail").val()),
				countryCode: $.trim(($(".ddl-countryCode").val() ? $(".ddl-countryCode").val() : "+966")),
				mobile: $.trim($("#crMobile").val()),
				company: $.trim($("#crCompany").val()),
				jobTitle: $.trim($("#crJobTitle").val()),
				message: $.trim($("#crMessage").val()),
				contactSubject: $.trim($("#crSubjectID").val()),
				categoryCode: $("#hfSectorID").length > 0 ? $.trim($("#hfSectorID").val()) : 0,
				opportunity: $("#hfOpportunity").length > 0 ? $.trim($("#hfOpportunity").val()) : 0,
				sectorCode: $("#partnerSectors").length > 0 ? $.trim($("#partnerSectors").val()) : 0,
				productCode: $("#hfProductCode").length > 0 ? $.trim($("#hfProductCode").val()) : 0
			}),
			success: function (data) {
				// console.log(data);
				// return;
				if (data == "mir-robot") {
					$("label.lbError").removeClass("d-none").html("<em><span>" + site.messages().mirRobot + "</span></em>");
				}
				if (data.indexOf("success") != -1){
					// alert(1);
					if (($("#hfSectorID").length > 0 && $("#hfSectorID").val() != "0" && $("#hfSectorID").val() != 0) || $("#hfOpportunityID").length > 0) {
						dataLayer.push({
							'event': 'fire_event',
							'category': 'Contact by Industry',
							'action': $("#hfPageTitle").val(),
							'label': 'Successful  Submit'
						});
					}
					else {
						dataLayer.push({
							'event': 'fire_event',
							'category': 'Contact Us Form',
							'action': 'Successful Submit'
						});
					}
					$(".contactSuccess").removeClass("d-none");
					$("#corForm").addClass("d-none");
					$('.contact-sucess-ticket').text("Your reference number : "+ data.substring(8));
					var scrollDiv = document.getElementById("opp-contact-form").offsetTop - 80;
					window.scrollTo({ top: scrollDiv, behavior: 'smooth'});
				}

				if (data.indexOf('error') >= 0 || data.indexOf('Error') >= 0) {
					$("label.lbError").removeClass("d-none").html("<em><span>" + site.messages().formSubmissionFailed + "</span></em>");
				}

				if (data.indexOf('captcha') >= 0 || data.indexOf('Captcha') >= 0) {
					$('#opp-contact-form').find('#g-recaptcha_incorrect').show();
				}

				// element[0].disabled = false;
				// element[0].textContent = btnText;
			}
		});
	}
	function validateContact(event) {
		// console.log(validateForm($("#corForm")));
		if (validateForm($("#corForm")) == true) {
			onContactSubmit();
			return true;
		}
		return false;
	}


	$(".open-popup-contact-form").on("click", function () {
		var buttonId = $(this).attr('id');
       $(".modal-body #buttonId").val(buttonId);
       $('#downloadModal').modal('show');
       if(buttonId ==='download'){
	     $('#exampleModalLongTitle').text("DOWNLOAD");
		}else{
			$('#exampleModalLongTitle').text("I AM INTERESTED");
		}
    });


	var popUpElement = document.getElementsByClassName('popup-btn-contact');
	var popUpBtnText = popUpElement[0] && popUpElement[0].textContent;
	$("#popup-btn-contact").on("click", function () {
		// alert(1);
        popUpElement.onclick = validatePopupContact(popUpElement);
    });

	$("#popup-btn-contact-cancel").on("click", function () {
		// alert(1);
        $("#popup-contact-form input").val('').change();
		grecaptcha.reset(0);
    });



function onPopupContactSubmit() {
		// element[0].disabled = true;
		// element[0].textContent = site.messages().sending;
		if ($('#invalidCheck').is(":checked")) {
			// it is checked
			$('#invalidCheck').parent().find("label").removeAttr("style");
		}
		else {
			$('#invalidCheck').parent().find("label").attr("style","color: #FF0000");
			return;
		}
		var buttonId = $("#buttonId").val();
		console.log(buttonId);
		console.log(JSON.stringify({
			name: $.trim($("popup_crFirstName").val())+' '+$.trim($("#popup_crLastName").val()),
				email: $.trim($("#popup_crEmail").val()),
				countryCode: $.trim(($(".ddl-countryCode").val() ? $(".ddl-countryCode").val() : "+966")),
				mobile: $.trim($("#popup_crPhone").val()),
				company: $.trim($("#popup_crCompanyName").val()),
				categoryCode: $("#hfSectorID").length > 0 ? $.trim($("#hfSectorID").val()) : 0,
				opportunity: $("#hfOpportunity").length > 0 ? $.trim($("#hfOpportunity").val()) : 0,
				sectorCode: $("#partnerSectors").length > 0 ? $.trim($("#partnerSectors").val()) : 0,
				productCode: $("#hfProductCode").length > 0 ? $.trim($("#hfProductCode").val()) : 0
		}));
		$.ajax({
			url: ACC.config.contextPath + '/contactus',
			async: true,
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			headers : {"g-recaptcha-response": grecaptcha.getResponse()},
			data: JSON.stringify({
				name: $.trim($("#popup_crFirstName").val())+" "+$.trim($("#popup_crLastName").val()),
				email: $.trim($("#popup_crEmail").val()),
				countryCode: $.trim(($(".ddl-countryCode").val() ? $(".ddl-countryCode").val() : "+966")),
				mobile: $.trim($("#popup_crPhone").val()),
				company: $.trim($("#popup_crCompanyName").val()),
				categoryCode: $("#hfSectorID").length > 0 ? $.trim($("#hfSectorID").val()) : 0,
				opportunity: $("#hfOpportunity").length > 0 ? $.trim($("#hfOpportunity").val()) : 0,
				sectorCode: $("#partnerSectors").length > 0 ? $.trim($("#partnerSectors").val()) : 0,
				productCode: $("#hfProductCode").length > 0 ? $.trim($("#hfProductCode").val()) : 0
			}),
			success: function (data) {
				console.log(data);
				if(data.indexOf('captcha')>= 0){
					$("#downloadModal").css("z-index","1300");
					 $('#g-recaptcha_incorrect').show();
				}else{
				$("#downloadModal").modal("toggle");
				$("#popup-contact-form input").val('').change();
				$("#pdfDownloadTrigerrer")[0].click();}
			// if( buttonId === 'download' &&  $("#pdfDownloadTrigerrer").length > 0 ){

			// 	}

			}
		});
	}


function validatePopupContact(event) {
		if (validateForm($("#popup-contact-form")) == true) {
			onPopupContactSubmit();
			return true;
		}
		return false;
	}





});

function updateSubjectId(e) {
	document.getElementById("crSubjectID").value = e.currentTarget.value;
	document.getElementById(e.currentTarget.id).classList.remove("error");
	document.getElementById(e.currentTarget.id).parentElement.classList.remove("error");
}
// Aravind Code End Here---------------------------------------------------------------------------------------------------------->

// Honeycomb Last element issue fix starts

$(window).on('load', function () {
    // code here
	if($(window).width() >= 1100 && $(".hexagon-portal article").length == 15) {
		//console.log('added.');
		$(".hexagon-portal article:nth-child(" + (12) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");
		$(".hexagon-portal article:nth-child(" + (14) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");
		$(".hexagon-portal article:nth-child(" + (16) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");

	}
});

$(document).ready(function () {
    $(window).resize(function () {
        // code here
		if($(window).width() < 1100) {
			document.querySelectorAll(".iAmAplaceHolderArticle").forEach(el => el.remove());
         //console.log('removed.');
        //  $(".hexagon-portal").removeChild('.iAmAplaceHolderArticle');

    }
	else if($(window).width() >= 1100 && $(".hexagon-portal article").length == 15) {
		//console.log('added.');
		$(".hexagon-portal article:nth-child(" + (12) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");
		$(".hexagon-portal article:nth-child(" + (14) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");
		$(".hexagon-portal article:nth-child(" + (16) + ")").after("<article style='visibility: hidden; ' class='iAmAplaceHolderArticle'></article>");

	}
}); });

// Honeycomb Last element issue fix Ends

//Sector details page starts

function myFunctionrightside(){
	var x = document.getElementById("numberss");
	var y = document.getElementById("right").classList.add('rightsideshow');
	var y = document.getElementById("firstimg").classList.add('showss');
	var y = document.getElementById("secondimg").classList.remove('showss');
	if (x.innerHTML === "01-02") {
		x.innerHTML = "02-02";
	}
}
function myFunctionleftside(){
	var x = document.getElementById("numberss");
	var y = document.getElementById("right").classList.remove('rightsideshow');
	var y = document.getElementById("secondimg").classList.add('showss');
	var y = document.getElementById("firstimg").classList.remove('showss');
	if (x.innerHTML === "02-02") {
		x.innerHTML = "01-02";
	}
}

$(document).ready(function() {

	var i = 1;
	var j = 1;

	$('article.item').hover(function() {
		$(this).addClass('active');
		$(this).removeClass('is-disabled');
		$(this).siblings().addClass('is-disabled');

		$('.test-item').addClass('active');

	});
	$("article.item").each( function() {
			$(this).attr("data-slide-to", +i);
			$(this).attr("id", '#article-'+i);
			$(this).attr("class", 'item article_fun-'+i);
		i++;
	});
	$(".test-item").each( function() {
		$(this).attr("data-slide-to", +j);
		$(this).attr( "id", '#hover-' +j);
		$(this).attr( "class", 'test-item hover-' +j);
		j++;
	});

	$('.test-item').siblings().addClass('is-disabled');
	$('.test-item').addClass('active');

	var elmsall = document.getElementsByClassName("item");
	var nall = elmsall.length


	$('.item').hover(function() {
		var nums = this.id.split('-')[1];
		var actives = '.test-item.hover-'+nums;

		var addone = nums++;

		$('.test-item').first().removeClass('active');
		$( ".test-item" ).siblings().removeClass('active');
		if(nums === nums){
			$('.test-item.hover-'+addone).toggleClass("active");
		}
	});

	var totalItems = $('.item-01').length;
	var currentIndex = $('div.carousel-item').index() + 1;
	var down_index;
	$('.num-01').html('0' + currentIndex + '&nbsp;&nbsp;-&nbsp;&nbsp;0' + totalItems + '');
	$(".next-01").click(function() {
		currentIndex_active = $('div.carousel-item.active').index() + 2;
		if (totalItems >= currentIndex_active) {
			down_index = $('div.carousel-item.active').index() + 2;
			$('.num-01').html('0' + currentIndex_active + '&nbsp;&nbsp;-&nbsp;&nbsp;0' + totalItems + '');
		}

		if(down_index == 1) {
			$(".prev-01").attr("style", "pointer-events: none;");
			$("#successstories_firstimg").removeAttr("style");
			$("#successstories_secondimg").attr("style", "display: none;");
			$("#ss_right_arrow").removeClass("opacity_gray_color");

		}
		else {
			$(".prev-01").removeAttr("style");
			$("#successstories_secondimg").removeAttr("style");
			$("#successstories_firstimg").attr("style", "display: none;");
			if(down_index == totalItems) {
				$("#ss_right_arrow").addClass("opacity_gray_color");
			}
			else {
				$("#ss_right_arrow").removeClass("opacity_gray_color");
			}
		}
	});

	$(".prev-01").click(function() {
		down_index = down_index - 1;
		if (down_index >= 1) {
			$('.num-01').html('0' + down_index + '&nbsp;&nbsp;-&nbsp;&nbsp;0' + totalItems + '');
		} else {
		down_index = totalItems; //last slide value
			$('.num-01').html('0' + totalItems + '&nbsp;&nbsp;-&nbsp;&nbsp;0' + totalItems + '');
		}
		if(down_index == 1) {
			$(this).attr("style", "pointer-events: none;");
			$("#successstories_firstimg").removeAttr("style");
			$("#successstories_secondimg").attr("style", "display: none;");
			$("#ss_right_arrow").removeClass("opacity_gray_color");
		}
		else {
			$(this).removeAttr("style");
			$("#successstories_secondimg").removeAttr("style");
			$("#successstories_firstimg").attr("style", "display: none;");
			if(down_index == totalItems) {
				$("#ss_right_arrow").addClass("opacity_gray_color");
			}
			else {
				$("#ss_right_arrow").removeClass("opacity_gray_color");
			}
		}
	});

});

//Sector details page Ends

//about-kingdom-7key-reason-to-invest  ----- code Start --------

(function ($) {
	KeyReasonCarousel();
})(jQuery);

$(document).ready(function () {
	//ManageKeyReasonHeight();
	setTimeout(function () {
		ManageKeyReasonHeight();
	}, 500);
})

$(document).on('click', 'div[id^="heading-"]', function () {
	$('div[id^="heading-"]').removeClass('key-reason-header-color');
	if (!$(this)[0].children[0].classList.contains('collapsed')) {
		$(this).addClass('key-reason-header-color')
	}
	setTimeout(function () {
		ManageKeyReasonHeight();
	}, 500);
})

function ManageKeyReasonHeight() {
	if (window.matchMedia("(min-width:768px)").matches && window.matchMedia("(max-width:990px)").matches) {
		if (document.getElementsByClassName("key-reason-card")[0] !== undefined) {
			var heightTabContent = $("div[id^='pane-0']")[0].clientHeight;
			document.getElementsByClassName("key-reason-card")[0].style.setProperty("min-height", heightTabContent + (-50) + "px", 'important')
		}
	}
	if (window.matchMedia("(max-width:767px)").matches) {
		var Contents = $("div[id^='pane-']");
		var heightTabContent = 0;

		for (var i = 0; i < Contents.length; i++) {
			heightTabContent = heightTabContent + Contents[i].clientHeight;
		}
		if (document.getElementsByClassName("key-reason-card")[0] !== undefined) {
			document.getElementsByClassName("key-reason-card")[0].style.setProperty("min-height", heightTabContent + (-50) + "px", 'important')
			document.getElementsByClassName("key-reason-card")[0].style.setProperty("height", heightTabContent + "px", 'important')
		}
	}
}
function KeyReasonCarousel() {
	if (window.matchMedia("(max-width:1024px)").matches) {
		var head = document.getElementsByTagName('head')[0];
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.href = 'https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css';
		link.media = 'all';
		head.appendChild(link);

		$(".key-reason-card .card .count-outer").addClass(" owl-carousel owl-carousel-key");
		$('.owl-carousel-key').owlCarousel({
			loop: true,
			margin: 10,
			responsiveClass: true,
			rtl:window.location.href.indexOf('en') > -1 ? false : true,
			navText: ['<i class="fa fa-angle-left fa-3x" aria-hidden="true"></i>', '<i class="fa fa-angle-right fa-3x" aria-hidden="true"></i>'],
			responsive: {
				0: {
					items: 1,
					lazyLoad: true,
					nav: true
				},
				600: {
					items: 2,
					lazyLoad: true,
					nav: true
				},
				1000: {
					items: 3,
					lazyLoad: true,
					nav: true
				}
			}
		});

		let prevDivs = document.getElementsByClassName('owl-carousel-key');
		for (var i = 0; i < prevDivs.length; i++) {
			if (prevDivs[i].previousElementSibling.localName == "h2") {
				if (window.matchMedia("(max-width:515.95px)").matches) {
					$(prevDivs[i]).css("margin-top", "10vh")
				}
				else if (window.matchMedia("(max-width: 767px)").matches) {
					$(prevDivs[i]).css("margin-top", "4vh")
				}
				else if (window.matchMedia("(max-width: 992px)").matches) {
					$(prevDivs[i]).css("margin-top", "2vh")
				}
				else if (window.matchMedia("(min-height: 768px)").matches && window.matchMedia("(max-height: 1199px)").matches) {
					$(prevDivs[i]).css("margin-top", "-4vh")
				}
				else if (window.matchMedia("(min-height: 1200px)").matches && window.matchMedia("(max-height: 1400px)").matches) {
					$(prevDivs[i]).css("margin-top", "-2vh")
				}
				else if (window.matchMedia("(max-width: 1024px)").matches) {
					$(prevDivs[i]).css("margin-top", "-4vh")
				}
			}
			else if (prevDivs[i].previousElementSibling.localName == "input") {
				if (window.matchMedia("(max-width: 340px)").matches) {
					$(prevDivs[i]).css("margin-top", "0vh")
				}
				else if (window.matchMedia("(max-width: 767px)").matches) {
					$(prevDivs[i]).css("margin-top", "2vh")
				}
				else if (window.matchMedia("(max-width: 992px)").matches) {
					$(prevDivs[i]).css("margin-top", "-4vh")
				}
				else if (window.matchMedia("(min-height: 768px)").matches && window.matchMedia("(max-height: 1199px)").matches) {
					$(prevDivs[i]).css("margin-top", "-24vh")
				}
				else if (window.matchMedia("(min-height: 1200px)").matches && window.matchMedia("(max-height: 1400px)").matches) {
					$(prevDivs[i]).css("margin-top", "0vh")
				}
				else if (window.matchMedia("(max-width: 1024px)").matches) {
					$(prevDivs[i]).css("margin-top", "-6vh")
				}
			}
		}
	}
	else {
		var owl = $('.owl-carousel.owl-carousel-key');

		owl.trigger('destroy.owl.carousel');
		owl.addClass('off');

		$(".key-reason-card .card .count-outer").removeClass(" owl-carousel owl-carousel-key");
		$(".key-reason-card .count-outer").addClass(" card-desktop");

		let prevDivs = document.getElementsByClassName(' card-desktop');
		for (var i = 0; i < prevDivs.length; i++) {
			if (prevDivs[i].previousElementSibling.localName == "h2") {
				if (prevDivs[i].children.length < 8 && window.matchMedia("(min-width:1920px)").matches) {
					$(prevDivs[i]).css("margin-top", "34vh")
				}
			}
			else if (prevDivs[i].previousElementSibling.localName == "input") {
				if (prevDivs[i].children.length < 8) {
					$(prevDivs[i]).css("margin-top", "24vh")
				}
				else if (window.matchMedia("(min-width:1920px)").matches) {
					for (var j = 0; j < prevDivs[i].children.length; j++) {
						if (j > 6) {
							var className = prevDivs[i].children[j].classList;
							$("<style/>", { text: "." + className + " {margin-bottom: 60px !important;}" }).appendTo('head');
						}
					}
				}
			}
		}
	}
}

// function ScreenResize
window.addEventListener("resize", kingDom);
function kingDom(){
	setTimeout(function () {
		KeyReasonCarousel();
		ManageKeyReasonHeight();
	}, 500);
}

//----code End-----
var script = document.createElement('script');
    script.type = 'application/javascript';
    script.src = 'https://unpkg.com/swiper/swiper-bundle.min.js';
    document.body.appendChild(script);
    $(document).ready(function () {
        setTimeout(function () {
            InitializeSwiper();
        }, 500);
    })

    function InitializeSwiper() {
        var swiper = new Swiper('.swiper-container-invest', {
            slidesPerView: 1,
            centeredSlides: true,
            loop: false,
            spaceBetween: 30,
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
              },
        });
    }



//sectors-opportunities  -----

$(document).ready(function () {
	SectorOppCarousel();
})

function SectorOppCarousel(){
	if (window.matchMedia("(max-width:1024px)").matches) {
		var head = document.getElementsByTagName('head')[0];
		var link = document.createElement('link');
		link.rel = 'stylesheet';
		link.type = 'text/css';
		link.href = 'https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css';
		link.media = 'all';
		head.appendChild(link);

		var header = $(".sector-link-header");
		for (var i = 0; i < header.length; i++) {
			header[i].setAttribute('aria-expanded', 'false')
		}
	}
	else {
		$(".sector-opp-card .count-outer").addClass(" card-desktop");
	}

	setTimeout(() => {
		if (window.matchMedia("(max-width:767px)").matches) {
			var Contents = $(".Inc-sector-explorer");
			var heightTabContent = 0;

			for (var i = 0; i < Contents.length; i++) {
				heightTabContent = heightTabContent + Contents[i].clientHeight;
			}
			if (document.getElementsByClassName("sector-opportunities-title")[0] !== undefined) {
				document.getElementsByClassName("sector-opportunities-title")[0].style.setProperty("min-height", heightTabContent + (-50) + "px", 'important')
				document.getElementsByClassName("sector-opportunities-title")[0].style.setProperty("height", heightTabContent + (-50) + "px", 'important')
			}
		}
	}, 100);
}

$(document).on('click', 'div[id^="heading-sector-"]', function (e) {
	$('div[id^="heading-sector-"]').removeClass('key-reason-header-color');
	if (!$(this)[0].children[0].classList.contains('collapsed')) {
		$(this).addClass('key-reason-header-color')
	}

	var offset = $('.Inc-sector-explorer').offset();
	var scrollto = offset.top;
	$('html, body').animate({scrollTop:scrollto}, 0);
})

window.addEventListener("resize", ScreenResize);
function ScreenResize(){
	setTimeout(function () {
		SectorOppCarousel();
	}, 100);
}
//sectors-opportunities  ----- code END --------


/*Invest saudi brand download component --START*/
$('.policy-acceptance input:checkbox').change(function(){
	if($(this).is(":checked")) {
		$('div.brand-policy-logos a').removeClass("disabled");
	} else {
		$('div.brand-policy-logos a').addClass("disabled");
	}
});
/*Invest saudi brand download component --END*/




/*RHQ --START 15.11.2021 */
$(document).ready(function () {
	if($(".rhq-banner-content").siblings().hasClass("content")){
		$( ".rhq-banner-content" ).next().addClass( "regional-panel container" );
		$( ".rhq-banner-content" ).next().removeClass( "content" );
	}
	$(".rhq-kingdom-glance-readmore").next().addClass('container-fluid rhq-kingdom-video-container')
	$(".rhq-kingdom-glance-readmore").next().removeClass('container');

	$(".support_regional_hq_component").unwrap();
	$(".support_regional_hq_component").parent().addClass('support-setting-view');
	$(".support-setting-view").removeClass('container');
	$(".support-setting-view").wrap('<div class="support-setting-section container"></div>');

	$(".rhq-banner-content .banner-container ").addClass('container')

	//$("body.page-regional-head-quarters .rhq-banner-content .banner-container ").append(' <a href="/en/login" class="enquiry-btn">APPLY NOW <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561"><path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward" d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z" transform="translate(-7.875 -11.252)" fill="#fff"></path>  </svg></a>')

	$($('.province-business-culture_component')[0]).children().slice(5).wrapAll("<div class='col-md-12 RHQ_riyadh_province_detaisl' ></div>");
	$($('.province-business-culture_component')[0]).children().slice(0, 5).wrapAll("<div class='province-map-details'></div>");

	$($(".RHQ_riyadh_province_detaisl")[0]).children().addClass('rhq-events')
	var el = $($(".RHQ_riyadh_province_detaisl")[0]).children().slice(1);
	var count = 0;
	var s1 = 0;
	var s2 = 2;
	for (var j = 0; j < el.length; j++) {
		count++;

		if(count === 2){
			count = 0;
			s1++;
			s2++;
			$($(".RHQ_riyadh_province_detaisl")[0]).children().slice(s1,s2).wrapAll('<div class="col flex-column rhq-events-block"></div>')
		}
		if(j===el.length-1){
			$($(".RHQ_riyadh_province_detaisl")[0]).children().slice(1).wrapAll('<div class="d-flex rhq-map-info-wrapper"></div>')
		}
	}

	var h2_count=0;
	$('.upcoming-special-zone-container .container h2').replaceWith(function() {
		h2_count++;
		var aria_select = h2_count === 1 ? true : false;
		var a_classnames = h2_count === 1 ? "nav-li nav-link active rhq-zone-container-heading" : "nav-li nav-link rhq-zone-container-heading "
		return $(this).replaceWith($('<a class="' + a_classnames + '" id="v-pills-home-tab_' + h2_count + '" data-toggle="pill" href="#v-pills-home_' + h2_count + '" role="tab" aria-controls="v-pills-home_' + h2_count + '" aria-selected="' + aria_select + '">' + this.innerHTML + '</a>'));
	});
	$('.upcoming-special-zone-container .container').addClass('container-fluid RHQ_bg_less_color')

	$('.upcoming-special-zone-container .container .yCmsComponent').replaceWith(function() {
		return $(this).replaceWith($(this).html())
	})
	$('.upcoming-special-zone-container .container .upcoming_special_zone_component').replaceWith(function() {
		return $(this).replaceWith($(this).html())
	})

	$($('.upcoming-special-zone-container .container')[0]).children().wrapAll('<div class="economic-zone"></div>')
	$($('.upcoming-special-zone-container .container .economic-zone')[0]).children().wrapAll('<div class="economic-zone-section"></div>')
	$($('.upcoming-special-zone-container .container .economic-zone-section')[0]).children().wrapAll('<div class="row RHQ_Nav_bar d-flex"></div>')

	var el1 = $($('.upcoming-special-zone-container .container .economic-zone-section')[0]).children();
	el1.find('a').wrapAll('<div class="nav flex-column nav-pills  col-12 col-sm-6 col-md-6 col-lg-3 pr-0 justify-content-between d-order-1" id="v-pills-tab" role="tablist" aria-orientation="vertical"></div>')
	el1.find('img,h4,span').wrapAll('<div class="tab-content col-12 col-sm-6 col-md-6 col-lg-9 p-0 d-order-2" id="rhq-pills-tabContent"></div>')

	var u_count = 0, u_s1 = -1, u_s2 = 2;
	var u_el1 = $($('.upcoming-special-zone-container .container .economic-zone-section .tab-content')[0]).children()
	for (var j = 0; j < u_el1.length; j++) {
		u_count++;

		if(u_count === 2){
			u_count = 0; u_s1++; u_s2++;
			var classnames = u_s1 === 0 ? "tab-pane fade show active" : "tab-pane fade show"
			$($(".upcoming-special-zone-container .container .economic-zone-section .tab-content")[0]).children().slice(u_s1, u_s2).wrapAll('<div class="' + classnames + '" id="v-pills-home_' + (u_s1 + 1) + '" role="tabpanel" aria-labelledby="v-pills-home-tab_' + (u_s1 + 1) + '"></div>')
		}
	}
	var el2 = $('.upcoming-special-zone-container .container .economic-zone-section .tab-content').children();
	for (var j = 0; j < el2.length; j++) {
		$(el2[j]).find('h4,span').wrapAll('<div class="description"></div>')
		var classnames = j === 0 ? "collapse active show" : "collapse";
		$($(el2[j])[0]).children().wrapAll('<div id="collapse-' + (j + 1) + '" class="' + classnames + '" role="tabpanel" data-parent="#rhq-pills-tabContent" aria-labelledby="heading-rhq-' + (j + 1) + '"></div>')
		var classnames1 = j === 0 ? "nav-link rhq-link-header active show" : "nav-link rhq-link-header";
		var aria_expanded = j === 0 ? "true" : "false";
		$(el2[j]).append('<div class="card-header" role="tab" id="heading-rhq-' + (j + 1) + '"><a class="' + classnames1 + '" data-toggle="collapse" href="#collapse-' + (j + 1) + '" aria-expanded="' + aria_expanded + '" aria-controls="collapse-' + (j + 1) + '"> ' + $("#v-pills-home-tab_" + (j + 1) + "")[0].innerHTML + '</a > </div>');
	}


})

/*Menu Toggle between English & Arabic - START */
function LanguageToggle(lang){
	if(lang === "en"){
		if(window.location.href.endsWith('/ar')){
			// handle the home page url ends with /ar
			var url = window.location.href.replace('/ar','/en');
			window.location.href = url;
		}else {
			var url = window.location.href.replace('/ar/','/en/');
			window.location.href = url;
		}
	}
	else if(lang === "ar"){
		if(window.location.href.endsWith('/en')){
			// handle the home page url ends with /en
			var url = window.location.href.replace('/en','/ar');
			window.location.href = url;
		} else {
			var url = window.location.href.replace('/en/', '/ar/');
			window.location.href = url;
		}
	}
}
/*Menu Toggle between English & Arabic - END */

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function onlyAlphabets(evt){
	evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
	if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
		return true;
	else
		return false;
}
function validateEmailReg(evt){
	var email = evt.target.value;
	var lblError = document.getElementById("lblError");
	lblError.innerHTML = "";
	var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (email !== "" && !expr.test(email)) {
		lblError.innerHTML = "Invalid email address.";
	}
}

function refreshPage(){
    window.location.reload();
}


var direction = "left";
const isAR = window.location.href.indexOf('en') > -1 ? false : true;
const gallery = document.querySelector('.page-meet-the-kingdom #carouselExampleControls');

window.addEventListener("resize", SuccessStoryPosition);
function SuccessStoryPosition() {
	if (gallery !== null) {
		if (direction === 'left')
			SuccessStoriesPosLTR(1);
		else
			SuccessStoriesPosRTL(1)
	}
}
$('.page-meet-the-kingdom #carouselExampleControls').on('slid.bs.carousel', function (ev) {
	direction = ev.direction;
	if (ev.direction === 'left')
		SuccessStoriesLTR();
	else
		SuccessStoriesRTL()
})
$('.page-meet-the-kingdom #carouselExampleControls').on('slide.bs.carousel', function (ev) {
	direction = ev.direction;
	if (Math.abs(ev.from - ev.to) <= 1) {
		if (ev.direction === 'left')
			SuccessStoriesPosLTR();
		else
			SuccessStoriesPosRTL()
	}
})

function SuccessStoriesPosLTR(Isresize = 0) {
	const gallery_scroller = gallery.querySelector('.carousel-logos');
	var position = $('.carousel-logos li').filter('.active').offset();
	var scrollBy = isAR ? (position.left > 0 ? (-position.left + 400) : -(position.left - 400)) : position.left;

	if (window.matchMedia("(max-width:640px)").matches) {
		if (Isresize === 0)
			scrollBy = (position.left > 0 ? isAR ? (-position.left + 300) : (position.left + 200) : isAR ? -(position.left - 300) : (position.left + 200));
		else
			scrollBy = position.left > 0 ? isAR ? -(position.left) : position.left : isAR ? -(position.left) : position.left;
	}
	if (isAR)
		gallery_scroller.scrollBy(-scrollBy, 0);
	else
		gallery_scroller.scrollBy(scrollBy, 0);
}
function SuccessStoriesPosRTL(Isresize = 0) {
	const gallery_scroller = gallery.querySelector('.carousel-logos');
	var position = $('.carousel-logos li').filter('.active').offset();
	var scrollBy = isAR ? position.left : (position.left > 0 ? (-position.left + 400) : -(position.left - 400));

	if (window.matchMedia("(max-width:640px)").matches) {
		if (Isresize === 0)
			scrollBy = (position.left > 0 ? isAR ? (position.left + 200) : (-position.left + 300) : isAR ? (position.left + 200) : -(position.left - 300));

		else
			scrollBy = position.left > 0 ? (-position.left) : -(position.left);
	}
	if (isAR)
		gallery_scroller.scrollBy(scrollBy, 0);
	else
		gallery_scroller.scrollBy(-scrollBy, 0);
}
function SuccessStoriesLTR() {
	const gallery_scroller = gallery.querySelector('.carousel-logos');
	if ($('.macro_economic_container.successstory-container .carousel .carousel-logos .carousel-indicators li').first().hasClass('active')) {
		if (gallery_scroller.offsetWidth + Math.abs(gallery_scroller.scrollLeft) >= gallery_scroller.scrollWidth - 1) {
			if (isAR)
				gallery_scroller.scrollBy(gallery_scroller.scrollWidth, 0);
			else
				gallery_scroller.scrollBy(-gallery_scroller.scrollWidth, 0);
		}
	}
}
function SuccessStoriesRTL() {
	const gallery_scroller = gallery.querySelector('.carousel-logos');
	if ($('.macro_economic_container.successstory-container .carousel .carousel-logos .carousel-indicators li').last().hasClass('active')) {
		if (isAR)
			gallery_scroller.scrollBy(-gallery_scroller.scrollWidth, 0);
		else
			gallery_scroller.scrollBy(gallery_scroller.scrollWidth, 0);
	}
}

// Cookie acceptance---->

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function eraseCookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function cookieConsent() {
    if (!getCookie('allowCookies')) {
       $('.cookie-desclaimer').removeClass('d-none');
    }
}

$('#btnDeny').click(()=>{
    eraseCookie('allowCookies')
    $('.toast').hide()
})

$('#btnAccept').click(()=>{
    setCookie('allowCookies','1',7)
    $('.cookie-desclaimer').hide()
})

// load
cookieConsent()





/* --------- Font-size JS --------*/

var original = 16;
var increment = 0, decrement = 3;

setFont();

setFont();
function setFont() {
	var inc = getCookie("f_increment");
	// $("#font-decrement").hide();
	if(inc !== null && inc !== ""){
		increaseFontSize(1)
	}
}

function increaseFontSize(isSet = 0) {
    var p = document.querySelectorAll('body div,body p,body span, body h1, body h2, body h3, body h4, body h5, body h6');
	var s;
	if (increment < 3) {
		increment = isSet === 0 ? (increment + 1) : parseFloat(getCookie("f_increment"));
		for (let i = 0; i < p.length; i++) {
			if ($(p[i]).css('font-size')) {
				s = parseFloat($(p[i]).css('font-size'));
			} else {
				s = original;
			}
			original = isSet === 0 ? (s + 0.5) : (s + increment *0.5);
			$(p[i]).css('font-size', original + "px");
		}
		setCookie("f_increment", increment, 30);
	}
	// if(increment >=3){
	// 	$("#font-increment").hide();
	// }
	// if(increment > 0){
	// 	$("#font-decrement").show();
	// }
}
function decreaseFontSize(isSet = 0) {
    var p = document.querySelectorAll('body div,body p,body span, body h1, body h2, body h3, body h4, body h5, body h6');
	console.log(increment)
	if (increment > 0) {
		increment = isSet === 0 ? (increment - 1) : parseFloat(getCookie("f_increment"));
		for (i = 0; i < p.length; i++) {
			if ($(p[i]).css('font-size')) {
				var s = parseFloat($(p[i]).css('font-size'));
			} else {
				var s = original;
			}
			original = isSet === 0 ? (s - 0.5) : (s - increment);
			$(p[i]).css('font-size', original + "px");
		}
		setCookie("f_increment", increment, 30);
	}
	// if(increment <=0){
	// 	$("#font-decrement").hide();
	// }
	// if(increment < 3){
	// 	$("#font-increment").show();
	// }
}
function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}




var list ; var opt1;
$(document).ready(function(){
	list = $('.contact-us-enquiry-category option');
	opt1 = $(list)[0];
})

function OnChangeEnquiryType() {
	var enquirySelected = $('.contact-us-enquirytype option:selected').val();

	var filtedOptions = list;
	var opts = filtedOptions.filter('[value^=' + '"' + enquirySelected + '"' + ']');
	if(enquirySelected === ''){
		opts = filtedOptions.filter('[value !== "0"]');
	}
	 $('.contact-us-enquiry-category').find('option')
		.remove()
		.end()
		.append(opt1)
		.append(opts);

	$('.contact-us-enquiry-category')[0].selectedIndex = -1;
}


$("#contact-us-form-cancel").on('click',function(e){
	e.preventDefault();
	var lblError = document.getElementById("lblError");
	lblError.innerHTML = "";

  grecaptcha.reset(0);

	$(':input','#contact-us-page-contact-us-form')
  .not(':button, :submit, :reset, :hidden')
  .prop('checked', false)
  .prop('selectedIndex', 0)
  .val('').change()
})




$('#downloadModal').on('hidden.bs.modal', function (e) {
	$("#popup-contact-form input").val('').change();
	$("#popup-contact-form input").prop('checked', false);
	grecaptcha.reset(0);
})


function recaptchaCallback(){
	$(".js-recaptcha-captchaaddon").siblings('span#lblSectorPageErrorCaptcha').text('')
	$(".js-recaptcha-captchaaddon").siblings('span#lblSectorErrorCaptcha').text('');
	$(".js-recaptcha-captchaaddon").siblings('span#lblErrorCaptcha').text('');
}




$('#contactfile').change(function() {
	var filez = $(this).val();
	$('#contact-us-upload-file').text(filez.split("\\").pop());
});

$(document).ready(function(){
	// if($("#CRMResponse").val() === 'true'){
		if ($("#CRMObjectId").val() !== null && $("#CRMObjectId").val() !== "" && $("#CRMObjectId").val() !== undefined) {
			// $("#contact-us-form-success").removeClass("d-none");
			$('.contact-us-form-ticket').text("Your enquiry reference number : " + $("#CRMObjectId").val());
			$("#contactusformModal").modal();
		}
	// }
})


$("#btnContactModalClose").on('hidden.bs.modal',function(){
	$("#CRMObjectId").val('');
	$(this).remove();
})

function validateFormContactUs(){
	var firstName = $("#contact-us-page-contact-us-form #firstName").val();
	var lastName = $("#contact-us-page-contact-us-form #lastName").val();
	var phoneNumber = $("#contact-us-page-contact-us-form #phoneNumber").val();
	var email = $("#contact-us-page-contact-us-form #email").val();
	var selectedEnquiryType = $("#contact-us-page-contact-us-form #selectedEnquiryType").val();
	var selectedCategoryOne = $("#contact-us-page-contact-us-form #selectedCategoryOne").val();
	var invalidCheck = $("#contact-us-page-contact-us-form #invalidCheck");
	var lblError = document.getElementById("lblError");
	lblError.innerHTML = "";
	var isValid = true;

	$("#lblErrorfirstName").text("");
	$("#lblErrorlastName").text("");
	$("#lblErrorPhoneNumber").text("");
	$("#lblErrorselectedEnquiryType").text("");
	$("#lblErrorselectedCategoryOne").text("");
	$("#lblErrorinvalidCheck").text("");
	if(firstName === ""){
		$("#lblErrorfirstName").text("Only Letters Are Allowed");
		isValid = false;
	}
	if(lastName === ""){
		$("#lblErrorlastName").text("Only Letters Are Allowed");
		isValid = false;
	}
	if(phoneNumber === ""){
		$("#lblErrorPhoneNumber").text("Invalid Mobile Number");
		isValid = false;
	}
	if(email === ""){
		$("#lblError").text("Invalid Email Id");
		isValid = false;
	}
	if(selectedEnquiryType === ""){
		$("#lblErrorselectedEnquiryType").text("Required");
		isValid = false;
	}
	if(selectedCategoryOne === ""){
		$("#lblErrorselectedCategoryOne").text("Required");
		isValid = false;
	}
	if(!invalidCheck.is(":checked")){
		$("#lblErrorinvalidCheck").text("Required");
		isValid = false;
	}

	var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (email !== "" && !expr.test(email)) {
		lblError.innerHTML = "Invalid email address.";
		isValid = false;
	}

	var recaptcha = document.forms["contact-us-form"]["g-recaptcha-response"].value;
	$("#lblErrorCaptcha").text("");
    if (recaptcha == "") {
        $("#lblErrorCaptcha").text("Please fill reCAPTCHA");
		isValid = false;
    }
	return isValid;
}


//-----------------------
var role = 0;
$(".login-role-selection .login-role-selection-box").on('click',function(){
	$(".login-role-selection-box.active").removeClass("active");
	$(this).addClass("active");
	$('.img-ivestor-active').addClass('d-none');
	$('.img-ivestor-inactive').removeClass('d-none');

	$('.img-partner-active').addClass('d-none');
	$('.img-partner-inactive').removeClass('d-none');

	$('.login-role').attr('src','');
	role = 0;

	if($(this).hasClass('role-investor active')){
		$('.img-ivestor-active').removeClass('d-none');
		$('.img-ivestor-inactive').addClass('d-none');
		$('.login-role').attr('src',$('.img-ivestor-inactive').attr('src'))
		role = 1;
	}
	if($(this).hasClass('role-partner active')){
		$('.img-partner-active').removeClass('d-none');
		$('.img-partner-inactive').addClass('d-none');
		$('.login-role').attr('src',$('.img-partner-inactive').attr('src'))
		role = 2;
	}
	$('.login-btn-next').addClass('active')
	$(".login-btn-next svg").removeClass('next-hide');
	$(".role-selected-text").removeClass('next-hide');
	$(".register-progress").removeClass('next-hide');
	$(".register-progress span:nth-child(1)").addClass('register-progress-selection')
})


$(".login-cancel").on('click',function(){
	$(".login-role-selection-box").removeClass("active");

	$('.img-ivestor-active').addClass('d-none');
	$('.img-ivestor-inactive').removeClass('d-none');

	$('.img-partner-active').addClass('d-none');
	$('.img-partner-inactive').removeClass('d-none');

	$('.login-role').attr('src','');
	role = 0;

	$('.login-btn-next').removeClass('active');
	$(".login-btn-next svg").addClass('next-hide');
	$(".role-selected-text").addClass('next-hide');
	$(".register-progress").addClass('next-hide');
	$(".register-progress span:nth-child(1)").removeClass('register-progress-selection')
})

$(".login-account-screen .login-btn-next").on('click',function(){
	if ($('.role-investor.active').length > 0) {
		window.location = "/en/login";
	}
	if ($('.role-partner.active').length > 0) {
		window.location = '/investsaudistorefront/investsaudi/en/login';
	}
});

//----------------------------

//SAGIA MENU HANDLING END

$("#logoutModal .yesButton").on("click", function() {
	$.cookie('chatOpen', null, { path: ACC.config.encodedContextPath });
	$("#sap-ecf-client").toggle(false);
	window.location.href = ACC.config.encodedContextPath + "/login";
});
$("#logoutModal .noButton").on("click", function() {
	$('#logoutModal').modal('hide');
});