var SAGIA = SAGIA || {};
SAGIA.sagiaNavigation = {
	_autoload: [
		["init", '$(".js-sagiaNavigation").length > 0'],
		["toggle", '$(".js-sagiaNavigationToggle").length > 0'],
		["mobileNav", '$("#js-sagiaMobileNav").length > 0']
	],
	
    _enquireload: [
        ["screenSmMax", "match", "unmatch"]
    ],
	
	unmatch: function(){
		$('.sagiaMobileNav').removeClass('active');
		$('body').removeClass('sagiaMobileNav_isActive').css('top', '');
		
		//close md navigation
		$('.navbar-toggler').removeClass('active');
		$('.sagiaMobileNav-toggler').removeClass('active');
		$('#navbarResponsive').addClass('collapse');
	},
	
	mobileNav: function(){
		var moveAmount = 0;
		var moveDirection = 1;
		var currentScroll;
		var tempCurrentScroll;
				
		var setTransform = function(amount) {
			$('#js-sagiaMobileNav-lvl0').css('transform', 'translateX('+ moveDirection * amount * 100 +'%)');
		};
		
		var showSubmenu = function(value) {
			var self = value;
			var targetMenu = $(self).data('target-menuid');
			var targetPane = $(self).data('target-submenu');
			var title = $(self).text();
			var icon = $(self).children('img').clone(true);
			
			
			$(targetMenu)
				.find('.js-sagiaMobileNav-subRight-pane[data-submenu="' + targetPane + '"]')
				.addClass("active")
				.siblings().removeClass("active");

			//set title in panel
			$(targetMenu)
				.find('.js-sagiaMobileNav-subRight-pane[data-submenu="' + targetPane + '"]')
				.find('.sagiaMobileNav-subRight-title')
				.text(title);
			
			//set icon in panel
			$(targetMenu)
				.find('.js-sagiaMobileNav-subRight-pane[data-submenu="' + targetPane + '"]')
				.find('.sagiaMobileNav-subRight-icon')
				.html(icon);			
			
			//set title on navigation link
			$(targetMenu)
				.parents('.sagiaMobileNav-lvl2')
				.find('.sagiaMobileNav-headNav-link')
				.text(title);
		};
				
		//main navigation toggle button
		$(document).on('click', '#js-sagiaMobileNav-toggler', function() {
			//set scroll position			
			currentScroll = $(window).scrollTop();

			var $body = $('body');
			if(!$body.hasClass('sagiaMobileNav_isActive')) {
                $body.css('top', currentScroll*-1);
                $body.addClass('sagiaMobileNav_isActive');
				tempCurrentScroll = currentScroll;
			} else {
                $body.removeClass('sagiaMobileNav_isActive');
                $body.css('top', '');
				$(window).scrollTop(tempCurrentScroll);				
			}

//			$('body').toggleClass('sagiaMobileNav_isActive');

			$(this).toggleClass('active');
			$('#js-sagiaMobileNav').toggleClass('active');
			moveAmount = 0;
			setTransform(moveAmount);
		});

		$(document).on('click', '.js-sagiaMobileNav_moveForwards > a', function(e){
			e.preventDefault();
			e.stopImmediatePropagation();
			
			if($("html").attr("dir") === "ltr") {
				moveDirection = 1;
			} else {
				moveDirection = -1;
			}			

			moveAmount--;
			setTransform(moveAmount);
			
			if($(this).data('target-menuid')) {
				showSubmenu(this);
			}
		});

		$(document).on('click', '.js-sagiaMobileNav_moveBack > a', function(e){
			e.preventDefault();
			e.stopImmediatePropagation();
			
			if($("html").attr("dir") === "ltr") {
				moveDirection = 1;
			} else {
				moveDirection = -1;
			}			

			moveAmount++;			
			setTransform(moveAmount);
			
			if($(this).data('target-menuid')) {
				showSubmenu(this);
			}
		});
	},

	init:function() {
		var direction;
		if($("html").attr("dir") === "ltr"){
			direction = "right";
		} else {
			direction = "left";
		}

		var $menu = $('.js-sagiaNavigation-subNav .sagiaNavigation-subNav');
		$menu.menuAim({
//			rowSelector: ">ul>li",
			tolerance: 50,  
			submenuDirection: direction,
			activate: function (row) {
				var $row = $(row);
				$row.addClass("active");

				var target = $row.find("a").data("targetSubmenu");
				if(target) {
					$(".js-sagiaNavigation-subRight-pane[data-submenu='" + target + "']").addClass("active");
				}
			},
			deactivate: function (row) {
				var $row = $(row);
				$row.removeClass("active");
				
				var target = $row.find("a").data("targetSubmenu");
				if(target) {
					$(".js-sagiaNavigation-subRight-pane[data-submenu='" + target + "']").removeClass("active");
				}				
			},
			enter: function () {},
			exitMenu: function () {}
		});
		
		//exit menu function
		$(document).on('mouseleave', '.js-sagiaNavigation-subNav', function(){
			$(".js-sagiaNavigation-subNav>ul>li,.js-sagiaNavigation-subRight-pane").removeClass("active");
		});		
		
		$(document).on('click', '.navbar-toggler', function() {
			$(this).toggleClass('active');
		});	
		
		$(document).on('click', '.nav-item_hasSub > a', function(e) {
			e.preventDefault();
			e.stopImmediatePropagation();
			$(this).next().slideToggle(100);
		});
		
		$(document).on('click', '.js-sagiaNavigation-subNav > ul > li.sagiaNavigation-subNav-icon a', function(e){
			e.preventDefault();
			e.stopImmediatePropagation();
		});
		
	},

	toggle:function(){
		$(".js-sagiaNavigationToggle").on('click',function(){
			var target = $(this).attr('data-target') ?  ($(this).attr('data-target') === "self" ? $(this) : $(this).parents('.' + $(this).attr('data-target'))) : $(this).parent();
			target.toggleClass('active');
		});
	}
};