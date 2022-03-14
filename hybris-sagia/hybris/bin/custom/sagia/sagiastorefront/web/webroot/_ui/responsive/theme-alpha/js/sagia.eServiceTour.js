var SAGIA = SAGIA || {};
SAGIA.eServiceTour = {
	_autoload: [
		["init", '$(".js-eServiceTour").length > 0']
	],
	
    _enquireload: [
        ["screenSmMax", "match", "unmatch"]
    ],
	
	isSmall: null,
	
	match: function(){
		SAGIA.eServiceTour.isSmall = true;
	},
	
	unmatch: function(){
		SAGIA.eServiceTour.isSmall = false;
	},	

	noopMethod: function(){},

	init: function(){
		var self = this;
		if(tutorialJson !== "undefined" && tutorialJson.length !== 0 && tutorialJson.steps.length > 0 && displayTutorial && (window.location.href).indexOf("dash") > -1) {
			console.log('dasboard page..'+(window.location.href).indexOf("dash"));
			$('.header-tutorial-header-btn').show();
			var idx = 0;
			$.each(tutorialJson.steps, function (index, item) {
				if($(item.selector+':visible').length == 1){	
					var template = $(".tutorialTemplate").clone();
					template.find('.eServiceTutorial-headline').html(item.title);
					template.find('.eServiceTutorial-description').html(item.description);
					template.find('.eServiceTutorial-item').attr('id', 'eServiceTutorial-item-' + idx);
					/*if(item.sortOrder == tutorialJson.steps.length - 1){
						template.find('.eServiceTutorial-actions').hide();
					}*/
					$('.eServiceTutorial').append(template.html());
					$(item.selector).attr('data-eServiceTutorial-index', idx++);
					$(item.selector).attr('data-eServiceTutorial-position', item.position);
					$(item.selector).attr('data-eServiceTutorial-offset', item.offset);
					$(item.selector).attr('data-eServiceTutorial-borderradius', item.borderRadius);
					$(item.selector).attr('data-eServiceTutorial-borderradius-sm', item.borderRadiusSmall);
					// $(item.selector).attr('data-eServiceTutorial-beforechange', "$('.js-panelTabs').showAccessibleTabSelector('#passwordTab')");
					$(item.selector).attr('data-eServiceTutorial-beforechange', item.beforeChangeHandler);
				}
			});
			$('ul.eServiceTutorial li:last .eServiceTutorial-actions').hide();
			//SAGIA.eServiceTour.init();
			$('#eServiceTour').find('.modal-title').html(tutorialJson.title);
			$('#eServiceTour').find('.modal-description').html(tutorialJson.description);
			$('#eServiceTour').modal();
		}
		$(document).on("click",".js-eServiceTour-start", function(e) {
			e.preventDefault();
			$('body').width() >= 768 ? SAGIA.eServiceTour.isSmall = false : SAGIA.eServiceTour.isSmall = true;

			$('body').append('<div class="modal-backdrop fade show"></div>')
			setFocusElement();
			setPanelDimensions();
		});
		
		$(document).on("click",".js-eServiceTour-next", function(e) {
			e.preventDefault();
			
			if(tourStep < availableTourSteps-1) {
				tourStep++;
				setFocusElement();
			} else {
				return
			}						
		});
		
		$(document).on("click",".js-eServiceTour-close", function(e) {
			e.preventDefault();
			
			$('.eServiceTutorial-highlight').addClass('eServiceTutorial-highlight_close').one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(e) {
				$(this).remove();
			});
			$('.modal-backdrop').remove();
			tourStep = 0;
		
		});
		
		
		$(document).keyup(function(e) {
			if (e.keyCode == 27) {
				
				$('.eServiceTutorial-highlight').addClass('eServiceTutorial-highlight_close').one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(e) {
					$(this).remove();
				});
			
				tourStep = 0;	
				
			}
		});		
		
		
		$(window).on('scroll resize',  function(e){
			
			if($('.eServiceTutorial-highlight').length) {
				clearTimeout($.data(this, 'scrollTimer'));
				$.data(this, 'scrollTimer', setTimeout(function () {
					focusDimensions = getBoundingBox(focusElement);
					updateHighlight();
				}, 310));
			}

		});

		var tourStep = 0;
		var focusElement;
		var focusDimensions;
		var highLightOffset;
		var borderRadius;
		var panelElement;
		var panelPlacement;
		var direction;
		var availableTourSteps = $('.eServiceTutorial').children('.eServiceTutorial-item').length;
		
		function setFocusElement() {

			focusElement = $('[data-eServiceTutorial-index="'+ tourStep +'"]');
			panelElement = $('#eServiceTutorial-item-' + tourStep).children('.eServiceTutorial-panel').clone(true);
			
			panelPlacement = focusElement.data('eservicetutorial-position');
			if(typeof focusElement.data('eservicetutorial-beforechange') !== 'undefined'){
				eval(focusElement.data('eservicetutorial-beforechange'));
			}
			//set direction swtich in case of rtl
			direction = $('html').attr('dir');
			var tempPanelPlacement;
			
			if(direction === 'rtl' && panelPlacement === 'right') {
				tempPanelPlacement = 'left';
				
			} else if (direction === 'rtl' && panelPlacement === 'left') {
				tempPanelPlacement = 'right'
				
			}			
			if (typeof(tempPanelPlacement) !== 'undefined') {
				panelPlacement = tempPanelPlacement;
			}
			
			
			highLightOffset = typeof focusElement.data('eservicetutorial-offset') === 'undefined' ? 0 : focusElement.data('eservicetutorial-offset');
				
			
			
			focusDimensions = getBoundingBox(focusElement);
			scrollToElement();
			
		};
		
		
		
		function getCenterPos() {
			var viewportHeight = $(window).height();
			return (viewportHeight - focusDimensions.height) / 2
		};
		
		function scrollToElement() {
			var offset = getCenterPos();
			
			$('html, body').animate({
				scrollTop : focusElement.offset().top - offset
			}, 300, function(){
				appendHighlight();
			});			
		};
		
		function appendHighlight() {
			focusDimensions = getBoundingBox(focusElement);
			
			if(!$('.eServiceTutorial-highlight').length) {
				var $appendHighlight = $('<div/>').addClass('eServiceTutorial-highlight').css({
					width: focusDimensions.width + highLightOffset*2,
					height: focusDimensions.height + highLightOffset*2,
					top: focusDimensions.top - highLightOffset,
					left: focusDimensions.left - highLightOffset,
					'z-index':'99999'
				});
				
				var panelArrow = $('<div/>').addClass('eServiceTutorial-arrow');
				
				$('body').append($appendHighlight);
				
				panelArrow.appendTo('.eServiceTutorial-highlight');
				panelElement.appendTo('.eServiceTutorial-highlight');
			} else {
				
				//update panel
				$('.eServiceTutorial-highlight').children('.eServiceTutorial-panel').remove();
				panelElement.appendTo('.eServiceTutorial-highlight');
				
				//update css				
				updateHighlight();
			}
		};
		
		function setPanelDimensions() {
			var bodyWidth = $('body').width();
			var panelTransform;
			var containerPadding = 15;
			var minPanelWidth = 385;
			var maxPanelWidth = 583;
			var panelInnerWidth = maxPanelWidth - containerPadding*2;
			var arrowOffset = 80;
			var arrow = $('.eServiceTutorial-arrow');
			
			arrow.css('left', '');

			//reset position classes
			$('.eServiceTutorial-panel').removeClass (function (index, className) {
				return (className.match (/(^|\s)eServiceTutorial-panel_\S+/g) || []).join(' ');
			});
			
			$('.eServiceTutorial-highlight').removeClass (function (index, className) {
				return (className.match (/(^|\s)eServiceTutorial-highlight_\S+/g) || []).join(' ');
			});

			
			switch(panelPlacement) {
				case 'top':
			
					var panelWidth;
					var leftValue;
					var rightValue = 'auto';
					var arrowPosition = '';
					var moveByPanel = 0;
					
					$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_top');
					$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_top');
						
					//enough space in viewport for full size panel
					if (bodyWidth > maxPanelWidth) {

						if(focusDimensions.left >= (bodyWidth - focusDimensions.right)) {
							//left space > right space
						
							leftValue = '0';

							var moveBy = maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) > 0 ? maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) : containerPadding;
							panelTransform = 'translate(-' + moveBy +'px,-100%)';
							moveByPanel = -moveBy;
							
							//adjust arrow position if focusElement is bigger then panel and arrow is out of panel bounderies
							arrowPosition = focusDimensions.width/2 > panelInnerWidth-arrowOffset ? panelInnerWidth/2+'px' : '';
			

						} else {
							//right space > left space

							leftValue = 'auto';
							rightValue = '0';

							var moveBy = maxPanelWidth - focusDimensions.right > 0 ? maxPanelWidth - focusDimensions.right : containerPadding;
							panelTransform = 'translate(+' + moveBy +'px,-100%)';
							moveByPanel = moveBy;
						}

					} else {
						//not enough space in viewport for full size panel

						panelTransform = 'translate(-' + (focusDimensions.left - highLightOffset) +'px,-100%)';
						leftValue = '';
						rightValue = '';
						moveByPanel = -(focusDimensions.left - highLightOffset);
					}

					panelWidth = bodyWidth+'px';
					panelTransform = 'translate(' + (moveByPanel > 0 ? 100 : -100 ) +'px,-100%)';

					panelElement.css({
						transform: panelTransform,
						width: panelWidth,
						left: leftValue,
						right: rightValue
					});
					
					arrow.css('left', arrowPosition);
					
					break;
					
				case 'right':
					var panelWidth;			
			
					$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_right');
					$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_right');
					
					//not enough space on the right side
					if(bodyWidth - focusDimensions.right <= minPanelWidth){

						$('.eServiceTutorial-panel').removeClass('eServiceTutorial-panel_right');
						$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_top');
						
						$('.eServiceTutorial-panel').parent().removeClass('eServiceTutorial-highlight_right');
						$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_top');
						
						var leftValue = '50%'
						var rightValue = 'auto';
						var moveByPanel = 0;
						
						//not enough space on the right side but enough space in viewport for full panel width. Move if overflowing viewport
						if (bodyWidth > maxPanelWidth) {

							if(focusDimensions.left >= (bodyWidth - focusDimensions.right)) {
								leftValue = '0';
								
								
								var moveBy = maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) > 0 ? maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) : containerPadding;
								panelTransform = 'translate(-' + moveBy +'px,-100%)';
								moveByPanel = -moveBy;
								
							} else {
								
								leftValue = 'auto';
								rightValue = '0';
								
								var moveBy = maxPanelWidth - focusDimensions.right > 0 ? maxPanelWidth - focusDimensions.right : containerPadding;
								panelTransform = 'translate(+' + moveBy +'px,-100%)';
								moveByPanel = moveBy;
							}

						} else {
							
							panelTransform = 'translate(-' + focusDimensions.left +'px,-100%)';
							leftValue = '';
						}
						
						panelWidth = bodyWidth+'px';
						panelTransform = 'translate(' + (moveByPanel > 0 ? -450 : 400 ) +'px,-100%)';
						
						// panelTransform = 'translate(-' + (focusDimensions.left) +'px,-100%)';
						panelElement.css({
							transform: panelTransform,
							width: panelWidth,
							left: leftValue,
							right: rightValue
						});							  
					}else if(bodyWidth - focusDimensions.right > minPanelWidth && bodyWidth - focusDimensions.right <= maxPanelWidth){
						//enough space on right side and panelWidth in minPanelWidth threshold

						panelWidth = bodyWidth - focusDimensions.right +'px';
						
						panelElement.css({
							transform: '',
							width: panelWidth,
							left:'',
							right: ''
						});						
					} else {
						//enough space for full panel size on right side

						panelWidth = maxPanelWidth+'px';
						panelElement.css({
							transform: '',
							width: panelWidth,
							left:'',
							right: ''
						});	
					}					

					break;
					
				case 'bottom':
					var panelWidth;
					var leftValue;
					var rightValue = 'auto';
					var arrowPosition = '';
					var moveByPanel = 0;
					
					$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_bottom');
					$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_bottom');
						
					//enough space in viewport for full size panel
					if (bodyWidth > maxPanelWidth) {

						if(focusDimensions.left >= (bodyWidth - focusDimensions.right) && bodyWidth <= focusDimensions.right) {
							//left space > right space
							leftValue = '0';
							
							var moveBy = maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) > 0 ? maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) : containerPadding;
							panelTransform = 'translate(-' + moveBy +'px,100%)';
							moveByPanel = -moveBy;
							
							//adjust arrow position if focusElement is bigger then panel and arrow is out of panel bounderies
							arrowPosition = focusDimensions.width/2 > panelInnerWidth-arrowOffset ? panelInnerWidth/2+'px' : '';							

						} else {
							//right space > left space
							leftValue = 'auto';
							rightValue = '0';

							var moveBy = maxPanelWidth - focusDimensions.right > 0 ? maxPanelWidth - focusDimensions.right : containerPadding;
							panelTransform = 'translate(+' + moveBy +'px,100%)';
							moveByPanel = moveBy;
						}

					} else {
						//not enough space in viewport for full size panel
						panelTransform = 'translate(-' + (focusDimensions.left - highLightOffset) +'px,100%)';
						leftValue = '';
						rightValue = '';
					}

					panelWidth = bodyWidth+'px';
					panelTransform = 'translate(' + (moveByPanel > 0 ? 125 : -50 ) +'px,100%)';

					panelElement.css({
						transform: panelTransform,
						width: panelWidth,
						left: leftValue,
						right: rightValue
					});
					
					arrow.css('left', arrowPosition);
					
					break;
					
				case 'left':
					var panelWidth;			
			
					$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_left');
					$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_left');
					//not enough space on the left side, move panel top
					if(focusDimensions.left <= minPanelWidth){

						$('.eServiceTutorial-panel').removeClass('eServiceTutorial-panel_left');
						$('.eServiceTutorial-panel').addClass('eServiceTutorial-panel_top');
						
						$('.eServiceTutorial-panel').parent().removeClass('eServiceTutorial-highlight_left');
						$('.eServiceTutorial-panel').parent().addClass('eServiceTutorial-highlight_top');
						
						var leftValue;
						var rightValue = 'auto';
						var moveByPanel = 0;
						
						//not enough space on the left side, but enough space in viewport for full size panel
						if (bodyWidth > maxPanelWidth) {

							if(focusDimensions.left >= (bodyWidth - focusDimensions.right)) {
								//left space > right space
								leftValue = '0';
								
								var moveBy = maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) > 0 ? maxPanelWidth - (focusDimensions.width + (bodyWidth - focusDimensions.right)) : containerPadding;
								panelTransform = 'translate(-' + moveBy +'px,-100%)';
								moveByPanel = -moveBy;
								
							} else {
								//right space > left space
								leftValue = 'auto';
								rightValue = '0';
								
								var moveBy = maxPanelWidth - focusDimensions.right > 0 ? maxPanelWidth - focusDimensions.right : containerPadding;
								panelTransform = 'translate(+' + moveBy +'px,-100%)';
								moveByPanel = moveBy;
							}

						} else {
							//not enough space on the left side, and not enough space in viewport for full size panel
							panelTransform = 'translate(-' + focusDimensions.left +'px,-100%)';
							leftValue = '';
						}
						
						panelWidth = bodyWidth+'px';
						panelTransform = 'translate(' + (moveByPanel > 0 ? -(maxPanelWidth-(containerPadding + 180)) : (maxPanelWidth-(containerPadding + 180)) )  +'px,-100%)';

						panelElement.css({
							transform: panelTransform,
							width: panelWidth,
							left: leftValue,
							right: rightValue
						});							  
					} else if(focusDimensions.left > minPanelWidth && focusDimensions.left <= maxPanelWidth){
						//enough space on the left side and in minPanelWidth treshold
						panelWidth = focusDimensions.left +'px';
						
						panelElement.css({
							transform: '',
							width: panelWidth,
							left:'',
							right: ''
						});						
					} else {
						//enough space on the left side, show full panel size
						panelWidth = maxPanelWidth+'px';
						panelElement.css({
							transform: '',
							width: panelWidth,
							left:'',
							right: ''
						});	
					}

					break;					
				default:

			}
			
		};
		
		function updateHighlight() {
			if((focusDimensions.top - highLightOffset) < 0){
				document.body.scrollTop = document.documentElement.scrollTop = 0;
			}
			$('.eServiceTutorial-highlight').css({
				width: focusDimensions.width + highLightOffset*2,
				height: focusDimensions.height + highLightOffset*2,
				top: focusDimensions.top - highLightOffset,
				left: focusDimensions.left - highLightOffset,
				'z-index':'99999',
				'border-radius': function() {
					if(SAGIA.eServiceTour.isSmall) {
						
						if (typeof focusElement.data('eservicetutorial-borderradius-sm') !== 'undefined') {
							borderRadius = focusElement.data('eservicetutorial-borderradius-sm');
							return borderRadius[0] + 'px ' +  borderRadius[1] + 'px ' + borderRadius[2] + 'px ' + borderRadius[3] + 'px';

						} else {
							//check if border for big screen was defined
							if (typeof focusElement.data('eservicetutorial-borderradius') !== 'undefined') {
								
								borderRadius = focusElement.data('eservicetutorial-borderradius');
								return borderRadius[0] + 'px ' +  borderRadius[1] + 'px ' + borderRadius[2] + 'px ' + borderRadius[3] + 'px';
								
							} else {
								return 0;	
							}
							
						}						
						
					} else {
						
						if (typeof focusElement.data('eservicetutorial-borderradius') !== 'undefined') {
							borderRadius = focusElement.data('eservicetutorial-borderradius');
							return borderRadius[0] + 'px ' +  borderRadius[1] + 'px ' + borderRadius[2] + 'px ' + borderRadius[3] + 'px';
						} else {
							return 0;
						}
						
					}

				}				
			});
			
			setPanelDimensions();
		};
		
		function getBoundingBox(input) {
			var self = $(input);
			return self[0].getBoundingClientRect();
		};
	}
}

function dismissTutorial() {
    $.ajax({
        url: ACC.config.encodedContextPath + "/util/dismiss-tutorial",
        method: 'GET',
        ajaxHideLoadingIndicator: false,
        success: function (data) {
        },
        error: function(data) {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText('general.dismiss.tutorial'));
            errorModal.modal('show');
        }
    });
}


function wait(ms){
	var start = new Date().getTime();
	var end = start;
	while(end < start + ms) {
		end = new Date().getTime();
	}
}
