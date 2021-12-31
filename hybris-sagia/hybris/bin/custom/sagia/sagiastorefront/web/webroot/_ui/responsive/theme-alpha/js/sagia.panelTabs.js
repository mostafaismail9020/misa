var SAGIA = SAGIA || {};
SAGIA.panelTabs = {
	_autoload: [
		["init", '$(".js-panelTabs").length > 0']
	],
	
	_enquireload: [
		["screenSmMax", "match", "unmatch" ]
	],

	match: function() {
		$(document).on("click",".js-panelTabs .tabhead", function(e){
			e.preventDefault();
			
			var self = $(this),
				tabBody = self.next();

			if (self.hasClass('current') || tabBody.css('display') === 'block') {
				self.removeClass('current');
				tabBody.hide();
			} else {
				var id = $(this).attr("id");
				self.parents(".js-panelTabs").find("a[href='#" + id + "']").click();
				
				if(history.pushState) {
					history.pushState(null, null, '#' + id);
				} else {
					location.hash = '#' + id;
				}				
//				window.location.hash = id + "";
			}
		});
	},
	unmatch: function() {
		$(document).off("click",".js-panelTabs .tabhead");
		
		//show hidden tab body on md
		$(".js-panelTabs .panelTabs-navigation li.active a").click();
		$(".js-panelTabs_nested .panelTabs_nested-list li.active-tab a").click();
	},
	init: function() {
		$(".js-panelTabs").accessibleTabs({
			wrapperClass: 'panelTabs-content container',
			currentClass: 'active',
			tabhead: '.panelTabs-head',
			tabbody: '.panelTabs-body',
			autoAnchor: true,
			fx:'fadeIn',
			fxspeed: 'normal',
			currentInfoText: '',
			currentInfoPosition: 'append',
			currentInfoClass: 'panelTabs-currentInfo',
			cssClassAvailable: true
		}).children('.tabs-list').wrap('<div class="panelTabs-navigation"></div>').children('.active').each(function() {
			$(this).parents('.js-panelTabs').first().find('.panelTabs-body').css('display', 'none');
			$($(this).children('a').attr('href')).addClass('current').next('.panelTabs-body').removeAttr('style');
		}).parents('.js-panelTabs').addClass('panelTabs_loaded');

		$(document).on("click",".js-panelTabs .tabs-list a", function(e){
			e.preventDefault();
			$(this).parents(".js-panelTabs").find($(this).attr("href")).addClass("current").siblings().removeClass("current");
//          window.location.hash = $(this).attr("id") + "";
			if(history.pushState) {
				history.pushState(null, null, '#' + $(this).attr("id"));
			} else {
				location.hash = '#' + $(this).attr("id");
			}
        });

		$(".js-panelTabs_nested").accessibleTabs({
			wrapperClass: 'panelTabs_nested-content',
			currentClass: 'active-tab',
			tabhead: '.panelTabs_nested-head',
			tabbody: '.panelTabs_nested-body',
			fx:'fadeIn',
			fxspeed: 'normal',
			tabsListClass:'panelTabs_nested-list',
			currentInfoText: '',
			currentInfoPosition: 'append',
			currentInfoClass: 'panelTabs_nested-currentInfo',
			cssClassAvailable: true
		}).children('.panelTabs_nested-list').children('.active-tab').each(function(){
			$(this).parents('.js-panelTabs_nested').find('.panelTabs_nested-body').css('display','none');
			$($(this).children('a').attr('href')).addClass('current').next('.panelTabs_nested-body').removeAttr('style');
		});

		$(document).on("click",".js-panelTabs_nested .panelTabs_nested-list a", function(e){
			e.preventDefault();
			$(this).parents(".js-panelTabs_nested").find($(this).attr("href")).addClass("current").siblings().removeClass("current");
        });
	}
};