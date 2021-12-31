var SAGIA = SAGIA || {};
SAGIA.serviceMenu = {
	_autoload: [
		["init", '$(".js-serviceMenu").length > 0']
	],

	init:function(){
		var activeItem = $('.serviceMenu-item.active');
		activeItem.children('.serviceMenu-body').css('display', 'block');

		$(document).on("click",".js-serviceMenu", function(e){
			e.preventDefault();		
			e.stopPropagation();
			
			if (!$(this).parent().hasClass('active')) {
				$(this).parent().addClass('active');
				$(this).siblings().slideDown(400);

				$(this).find('.serviceMenu-head-action').children('div:first-child').addClass('hidden');
				$(this).find('.serviceMenu-head-action').children('div:last-child').removeClass('hidden');
			} else {
				$(this).parent().removeClass('active');
				$(this).siblings().slideUp(400);
				
				$(this).find('.serviceMenu-head-action').children('div:first-child').removeClass('hidden');
				$(this).find('.serviceMenu-head-action').children('div:last-child').addClass('hidden');				
			}
		});
	}
};