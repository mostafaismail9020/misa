var SAGIA = SAGIA || {};

SAGIA.formRangeSlider = {
	_autoload: [
		["init", '$(".js-formRangeSlider").length > 0']
	],

	init: function() {	
		
		$('.js-formRangeSlider input').on('input', function () {
			
			var moveDirection = 'right'
			
			if($("html").attr("dir") == "ltr"){
				moveDirection = 'right';
			}else{
				moveDirection = 'left';
			}			
			
			
			$(this).css('background', 'linear-gradient(to '+moveDirection+', #5cc83b 0%, #5cc83b ' + this.value + '%, #e6e6ed ' + this.value + '%, #e6e6ed 100%)');
			
			if (!$(this).closest('.js-formRangeSlider').hasClass('formRangeSlider_slim')) {
				$(this).closest('.js-formRangeSlider').find('.formRangeSlider-value span').text(this.value);
			}
		});			
		
	}
}
