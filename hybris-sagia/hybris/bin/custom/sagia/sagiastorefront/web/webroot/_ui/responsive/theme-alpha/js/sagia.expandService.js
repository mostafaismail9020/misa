var SAGIA = SAGIA || {};
SAGIA.expandService = {
	_autoload: [
		["init", '$(".js-expandService").length > 0']
	],
	
	_enquireload: [
		["screenMdMax","match", "unmatch" ]
	],	
	
	match:function(){
		if($('.serviceModule-row')) {
            var itemCount = $('.serviceModule-row').children('.serviceModule-section').length;
            if (itemCount > 2) {
                $('.serviceModule-row > .serviceModule-sectionDetail').remove();
                $('.serviceModule-row > .serviceModule-section').unwrap('.serviceModule-row');
                $('.serviceModule-section').removeClass('active');
                $('.serviceModule-action').find('div:first-child').removeClass('hidden').show();
                $('.serviceModule-action').find('div:last-child').addClass('hidden').hide();
            }
        }
	},
	
	unmatch:function(){
		if($('.serviceModule-row')) {
			var itemCount = $('.serviceModule-row').children('.serviceModule-section').length;
			if (itemCount < 3) {
				$('.serviceModule-row > .serviceModule-sectionDetail').remove();
				$('.serviceModule-row > .serviceModule-section').unwrap('.serviceModule-row');
				$('.serviceModule-section').removeClass('active');
				$('.serviceModule-action').find('div:first-child').removeClass('hidden').show();
				$('.serviceModule-action').find('div:last-child').addClass('hidden').hide();
			}
		}
	},

	init:function() {
		
		$(document).on("click",".js-expandService", function(e){
			e.preventDefault();
			var self = $(this),
				serviceModule = $(this).closest('.serviceModule'),
				serviceSections =  serviceModule.find('.serviceModule-section'),
				parentSection = $(this).closest('.serviceModule-section').length ? $(this).closest('.serviceModule-section') : $(this).closest('.serviceModule-sectionDetail').prev('.serviceModule-section'),
				serviceAction = $(this).closest('.serviceModule-section').find('.serviceModule-action'),
				serviceActions = serviceModule.find('.serviceModule-action'),
				sectionDetail = parentSection.find('.serviceModule-sectionDetail');

			if (parentSection.hasClass('active')) {
				//close sectionDetail if clicked on current active or clicked on button inside section detail
				removeSectionDetail(false);
				parentSection.removeClass('active');
			} 
			else if($('.serviceModule-row').length) {
				//close existing sectionDetail and open current one
				reOpenSectionDetail();
			} 
			else {
				//open sectionDetail if it doesnt exist
				addSectionDetail();
			}

			//toggle buttons
			serviceAction.children('div').each(function(){
				if($(this).is(":visible")){
					$(this).addClass('hidden').hide();
				}else{
					$(this).removeClass('hidden').show();
				}				
			});
			
			function reOpenSectionDetail() {
				serviceModule.find('.serviceModule-section').removeClass('active');
				removeSectionDetail(true);
				serviceActions.find('div:first-child').removeClass('hidden').show();
				serviceActions.find('div:last-child').addClass('hidden').hide();
			}
			
			function addSectionDetail(input) {
				var dealy = input || 0;
				var itemsInRow = [];
				var positionTop = self.closest('.serviceModule-section').position().top;

				//get items in row
				serviceModule.find('.serviceModule-section').each(function(index, element){
					var currentElement = $(element);
					if (currentElement.position().top === positionTop) {
						itemsInRow.push(currentElement.index());
					}
				});

				//add wrapper to current items in one row
				var sectionRow = serviceSections.slice(itemsInRow[0], itemsInRow[itemsInRow.length-1] + 1).wrapAll('<section class="serviceModule-row"></section>').parent();

				//append section detail to row and slide it down
				sectionRow.append(sectionDetail.clone(true));
				var clonedSectionDetail = sectionRow.find('> .serviceModule-sectionDetail').delay(dealy).slideDown(400, 'linear');
				self.closest('.serviceModule-section').addClass('active');
			};
			
			function removeSectionDetail(reOpen) {
				var expandedDetail = serviceModule.find('.serviceModule-row > .serviceModule-sectionDetail').slideUp(400, 'linear', function() {
					$(this).unwrap('.serviceModule-row');
					$(this).remove();

					if(reOpen) {
						addSectionDetail(80);
					}
				});
			};
		})
	}
};