var SAGIA = SAGIA || {};
SAGIA.expandContent = {
	_autoload: [
		["init", '$(".js-expandContent").length > 0']
	],

	init:function(){
		$(document).on("click",".js-expandContent", function(e){
			e.preventDefault();
			var target = $(this).data('expand-target');
			
			if($(this).hasClass('js-expandContent_services')) {
				//service expand
				
				$('#' + target).addClass('expanded');
				$(this).addClass('active');

				//close existing and open section details
				$('.serviceModule-row > .serviceModule-sectionDetail').remove();
				$('.serviceModule-row > .serviceModule-section').unwrap('.serviceModule-row');
				$('.serviceModule-section').removeClass('active');
				var action = $('.serviceModule-action');
				action.find('div:first-child').removeClass('hidden').show();
				action.find('div:last-child').addClass('hidden').hide();
			} else {
				//default
				$('#' + target).toggleClass('expanded');
				$(this).children('div').each(function(){
					if($(this).is(":visible")) {
						$(this).addClass('hidden').hide();
					} else {
						$(this).removeClass('hidden').show();
					}				
				});
			}
		});
		
		$(document).on("click",".js-expandContent-close", function(e){
			e.preventDefault();
			var inputField = $(this).prev();
			var target = inputField.data('expand-target');
			
			$('#' + target).removeClass('expanded');
			inputField.removeClass('active');
			inputField.val('').trigger("input");

			//close existing and open section details
			$('.serviceModule-row > .serviceModule-sectionDetail').remove();
			$('.serviceModule-row > .serviceModule-section').unwrap('.serviceModule-row');
			$('.serviceModule-section').removeClass('active');
			var action = $('.serviceModule-action');
			action.find('div:first-child').removeClass('hidden').show();
			action.find('div:last-child').addClass('hidden').hide();
		});
	}
};


