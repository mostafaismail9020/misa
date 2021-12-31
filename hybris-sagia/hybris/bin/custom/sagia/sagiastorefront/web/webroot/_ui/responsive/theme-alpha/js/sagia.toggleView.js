var SAGIA = SAGIA || {};
SAGIA.toggleView = {
	_autoload: [
		["init", '$(".js-toggleView").length > 0'],
		["dev", '$("*[data-show]").length > 0 && document.location.href.indexOf("#develop") > 0']
	],

	init:function(){
		
		$(document).on("click",".js-toggleView", function(e){
			e.preventDefault();
			
			if('.serviceModule-row') {
				$('.serviceModule-row > .serviceModule-sectionDetail').remove();
				$('.serviceModule-row > .serviceModule-section').unwrap('.serviceModule-row');
				$('.serviceModule-section').removeClass('active');
				$('.serviceModule-action').find('div:first-child').removeClass('hidden').show();
				$('.serviceModule-action').find('div:last-child').addClass('hidden').hide();		
			}
			
			
			if (!$(this).hasClass('active')) {
				
				var target = $(this).closest('.toggleView').data('view-target');
				var isListView = $(this).hasClass('toggleView-action_list');
				
				$('.toggleView-action').removeClass('active');
				$(this).addClass('active');
				
				if (isListView) {
					$('#' + target).removeClass('serviceModule_grid').addClass('serviceModule_list');
				} else {
					$('#' + target).removeClass('serviceModule_list').addClass('serviceModule_grid');
				}				
				
			}		
			
		})
	},

	dev: function(){
		var _id = document.location.href.substring(document.location.href.lastIndexOf("#develop")+1, document.location.href.length);
		if(_id == "develop"){
			$('*[data-show="develop"]').removeAttr('style');
		}
	}

}