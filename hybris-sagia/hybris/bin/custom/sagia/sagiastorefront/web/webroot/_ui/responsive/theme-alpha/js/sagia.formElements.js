var SAGIA = SAGIA || {};
SAGIA.formElements = {
	_autoload: [
		["select2", '$(".js-select2").length > 0'],
		["select2wide_left", '$(".js-select2-wide_left").length > 0'],
		["select2wide_right", '$(".js-select2-wide_right").length > 0'],
		["select2OneColumn", '$(".js-select2-oneColumn").length > 0'],
		["select2Search", '$(".js-select2-search").length > 0'],
		["select2Multi", '$(".js-select2-multi").length > 0'],
		["select2MultiWide_left", '$(".js-select2-multi-wide_left").length > 0'],
		["select2MultiWide_right", '$(".js-select2-multi-wide_right").length > 0'],
		["select2Event", '$(".js-select2, .js-select2-oneColumn, .js-select2-multi").length > 0'],
		["flatpickr", '$(".js-form-control_date, .js-form-control_timeslot").length > 0'],
		["tip", '$(".js-tip").length > 0'],
		["placeholderPolyfill", '$(".formInputBox, .formTextArea").length > 0']
	],

	select2Event:function() {
		var defaultFocus = $.fn.modal.Constructor.prototype._enforceFocus;
		$.fn.modal.Constructor.prototype._enforceFocus = function() {};	
		
		var elementSelectors = $('.js-select2-wide_left, .js-select2-wide_right, .js-select2, .js-select2-oneColumn, .js-select2-search, .js-select2-multi, .js-select2-mulit-wide_left, .js-select2-multi-wide_right');
        elementSelectors.on('select2:select', function (e) {
			$(this).next().addClass("select2Container_selected");
		});

        elementSelectors.each(function() {
			if(!!$(this).val()) {
				$(this).next().addClass("select2Container_selected select2Container_selected_onLoad");
			}
		});
		//on change event set to enable autofill
        elementSelectors.on('change.select2', function(e) {
			$(e.currentTarget).next().addClass("select2Container_selected");
		});
		
		//background scrollfix for ipad on open modals
		if( navigator.userAgent.match(/iPhone|iPad|iPod/i) ) {
			
			var body = $('body');
			var scrollPos;

			$('.modal').on('show.bs.modal', function() {
				scrollPos = window.scrollY;
				body.css({
					position: 'fixed',
					top: scrollPos*(-1) + 'px',
					width: '100%'
				});
			});

			$('.modal').on('hide.bs.modal', function() {
				body.css({
					position: '',
					top: '',
					width: ''
				});
				body.scrollTop(scrollPos);
			});
		}		

		//close select2 on window resize to prevent positioning bugs
//		$(window).resize(function(){
//			$('.js-select2-wide_left, .js-select2-wide_right, .js-select2, .js-select2-oneColumn, .js-select2-search, .js-select2-multi, .js-select2-mulit-wide_left, .js-select2-multi-wide_right').each(function(){
//				$(this).select2("close");
//			})			
//		});
	},
	
	placeholderPolyfill: function() {
		var supportsPlaceholderShown = function() {
			try {
				var tempDummyInput = $('<input />', {type:'text', class:'placeholder-shown-polyfill', placeholder:'.'});
				$('body').append(tempDummyInput);
				
				var dummyInput = $('.placeholder-shown-polyfill:placeholder-shown');
				dummyInput.css('text-indent','0px');

				return dummyInput.css('text-indent') === '0px';
			} catch (err) {
				return false;
			}
		}();

		if(!supportsPlaceholderShown) {
			var formControl = $('.formTextArea .form-control, .formInputBox .form-control');
			formControl.each(function() {
				if(!$(this).val()) {	
					$(this).addClass('placeholder-shown');
				} else {
					$(this).removeClass('placeholder-shown');
				}
			});

			formControl.on('blur', function() {
				if($(this).val().length > 0) {
					$(this).removeClass('placeholder-shown');
				} else {
					$(this).addClass('placeholder-shown');
				}
			});
		}
	},

	select2:function(){
		$('.js-select2').select2({
			placeholder: "",
//			dropdownAutoWidth: true,
			minimumResultsForSearch: -1
		});
	},
	
	select2wide_left:function(){
		$('.js-select2-wide_left').select2({
			placeholder: "",
//			dropdownAutoWidth: true,
			minimumResultsForSearch: -1,
			dropdownCssClass: 'select2-dropdown-wide_left'
		});
	},
	
	select2wide_right:function(){
		$('.js-select2-wide_right').select2({
			placeholder: "",
//			dropdownAutoWidth: true,
			minimumResultsForSearch: -1,
			dropdownCssClass: 'select2-dropdown-wide_right'
		});
	},	

	select2OneColumn: function(){
		//matcher function to get only words that start with searchterm
		function matchStart(params, data) {
			params.term = params.term || '';
			if (data.text.toUpperCase().indexOf(params.term.toUpperCase()) == 0) {
				return data;
			}
			
			return null;
		}
		
		var defaultMatcher = $.fn.select2.defaults.defaults.matcher;
		
		$('.js-select2-oneColumn').each(function(index, element){
			var _minimumResultsforSearch;
			var _searchBegining;
			var _sortAlphabetically;
			
			//set this class on select to force searchBar
			$(this).hasClass('js-select2-forceSearch') ? _minimumResultsforSearch = 0 : _minimumResultsforSearch = 10;
			
			//set this class on select to change search behaviour to begining of word
			$(this).hasClass('js-select2-searchBegining') ? _searchBegining = true : _searchBegining = false;
			
			//set this class on select to sort alphabetically
			$(this).hasClass('js-select2-sortAlphabetically') ? _sortAlphabetically = true : _sortAlphabetically = false;

			$(this).select2({
				placeholder: "",
				minimumResultsForSearch: _minimumResultsforSearch,
				dropdownCssClass: 'select2-dropdown_oneColumn select2-dropdown_oneColumn_hasFilter',
				matcher: function(params, data) {
					if (_searchBegining) {
						return matchStart(params, data);
					} else {
						return defaultMatcher(params, data);
					}					
				},
				sorter: function(data) {
					if (_sortAlphabetically) {
						return data.sort(function (a, b) {
							a = a.text.toLowerCase();
							b = b.text.toLowerCase();
							if (a > b) {
								return 1;
							} else if (a < b) {
								return -1;
							}
							return 0;
						});
					} else {
						return data;
					}	
				}
			});
		});

		$('.js-select2-oneColumn').on('select2:open', function() {			
			//tab index fix for select elements inside modals
			if($(this).closest('.modal').length > 0) {
				$(this).closest('.modal').attr('tabindex', '');
			} 
		});

        $('.js-select2-oneColumn').on('select2:close', function() {
			//reset tabindex on modal
			if($(this).closest('.modal').length > 0) {
				$(this).closest('.modal').attr('tabindex', '-1');
			}
		});
	},

	select2Search: function() {
		var select2Search = $('.js-select2-search');
		select2Search.select2({
			placeholder: "",
//			dropdownAutoWidth: true,
			minimumResultsForSearch: 0,
			dropdownCssClass: 'select2-dropdown_hasSearch'
		});
		
		//set placeholder for search
        select2Search.on('select2:open', function() {
			var searchPlaceholder = typeof $(this).data('search-placeholder') !== 'undefined' ? $(this).data('search-placeholder') : '';
			$(".select2-search--dropdown .select2-search__field").attr("placeholder", searchPlaceholder);
			
			//tab index fix for select elements inside modals
			if($(this).closest('.modal').length > 0) {
				$(this).closest('.modal').attr('tabindex', '');
			} 
		});

        select2Search.on('select2:close', function() {
			//reset tabindex on modal
			if($(this).closest('.modal').length > 0) {
				$(this).closest('.modal').attr('tabindex', '-1');
			}
		});		
	},

	select2Multi: function() {
		var select2Multi = $('.js-select2-multi');
        select2Multi.select2({
			dropdownAutoWidth: false,
			minimumResultsForSearch: -1,
			multiple:true,
			closeOnSelect: false
		});

        select2Multi.on('select2:select  select2:unselect', function() {
			var $e = $(this).next();
			var count = $e.find(".select2-selection__choice").length;
			if($e.find(".selection_count").length > 0) {
				$e.find(".selection_count").html(count);
			} else {
				$e.append("<div class='selection_count'>" + count + "</div>")
			}

			if(count === 0) {
				$e.find(".selection_count").remove();
				$e.removeClass("select2Container_selected");
			}
		});
	},
	
	select2MultiWide_left: function() {
		var select2Left = $('.js-select2-multi-wide_left');
		select2Left.select2({
//			dropdownAutoWidth: true,
			minimumResultsForSearch: -1,
			multiple:true,
			closeOnSelect: false,
//			dropdownCssClass: 'select2-dropdown_big',
			dropdownCssClass: 'select2-dropdown-wide_left select2-dropdown-wide_multi'
//			dropdownParent: $('.dropdown-wrapper') //set this class to append it to this rather than body. Custom solution on jquery.select2
		});

        select2Left.on('select2:select  select2:unselect', function() {
			var $e = $(this).next();
			var count = $e.find(".select2-selection__choice").length;
			if($e.find(".selection_count").length > 0) {
				$e.find(".selection_count").html(count);
			} else {
				$e.append("<div class='selection_count'>" + count + "</div>")
			}

			if(count === 0) {
				$e.find(".selection_count").remove();
				$e.removeClass("select2Container_selected");
			}
		});
	},
	
	select2MultiWide_right: function() {
		var select2Right = $('.js-select2-multi-wide_right');
        select2Right.select2({
//			dropdownAutoWidth: false,
			minimumResultsForSearch: -1,
			multiple:true,
			closeOnSelect: false,
			dropdownCssClass: 'select2-dropdown-wide_right select2-dropdown-wide_multi'
		});

        select2Right.on('select2:select  select2:unselect', function() {
			var $e = $(this).next();
			var count = $e.find(".select2-selection__choice").length;
			if($e.find(".selection_count").length > 0) {
				$e.find(".selection_count").html(count);
			} else {
				$e.append("<div class='selection_count'>" + count + "</div>")
			}

			if(count === 0) {
				$e.find(".selection_count").remove();
				$e.removeClass("select2Container_selected");
			}
		});
	},

	flatpickr: function(selection) {
		var getFormDatesObject = function(cntnt) {
			var formDataDelimitEntry = ";",
			formDataDelmimtDate = "#",
			formDateObj = [];
			if(cntnt){
				cntnt = cntnt.split(formDataDelimitEntry);
				$(cntnt).each(function(i,e){
					e = e.split(formDataDelmimtDate);
					if(e.length === 2) {
						formDateObj.push({from:e[0], to:e[1]});
					} else {
						formDateObj.push(e[0]);
					}
				});
				return formDateObj;
			}
			return [];
		},
		getHourMin = function (time){
			if(time === "") {
				return "0";
            }
			var _min = time.getMinutes(),
				_hour = time.getHours();
			return ((_hour < 10 ? "0" : "") + _hour + ":" + (_min < 10 ? "0" : "") + _min);
		};

		var _timeSlotSelection = '.js-form-control_timeslot';
		var _self = this;

		var resetPicker = function(picker) {
			picker = $(picker);
			var _instance = picker.get(0)._flatpickr;
			var _resetPosition = false;

			if(_instance.isOpen){
				_resetPosition = {scrollTop: $(_instance.calendarContainer).children('.flatpickr-timeslotContainer').first().scrollTop()};
				_instance.close();
			}
			_instance.destroy();
			picker.removeClass('flatpickr-input').removeClass('active');
			_self.flatpickr(picker);
			if(_resetPosition){
				_instance = picker.get(0)._flatpickr;
				_instance.open();
				window.setTimeout(function(){
					$(_instance.calendarContainer).children('.flatpickr-timeslotContainer').first().scrollTop(_resetPosition.scrollTop);
				}, 320);
			}
		};

		if(selection && $(selection).hasClass(_timeSlotSelection.substring(1, _timeSlotSelection.length))) {
			_timeSlotSelection = selection;
		} else {
			selection = undefined;
		}
		if(!selection) {
			$('.js-form-control_date').each(function(){
				ACC.calendarcommons.bindFlatpickr($(this));
			});
		}

		$(_timeSlotSelection).each(function() {
			if(typeof($(this).get(0)._resetTimeslots) !== "function") {
				//var _me = $(this);
				$(this).get(0)._resetTimeslots = function(){
					resetPicker($(this).get(0));
				};
			}
			$(this).flatpickr({
				enableTime: true,
				noCalendar: true,
				dateFormat: ACC.formatUITime ? ACC.formatUITime : "H:i",
				minDate: "09:00",
				maxDate: "17:00",
				disableMobile: "true",
				time_24hr: true,
				timeslotContainerCls : "flatpickr-timeslotContainer",
				timeslotCls : "flatpickr-timeslot",
				timeslotCurrentCls: "flatpickr-timeslot_current",
				timeslotSuggestCls: "flatpickr-timeslot_current-suggested",
				timeslotDisabledCls: "flatpickr-timeslot_disabled",
				timeDisabled: getFormDatesObject($(this).data('dates-disabled')),
				timeEnabled: $(this).data('dates-disabled') ? false : getFormDatesObject($(this).data('dates-enabled')),
				minuteIncrement: 30,
				getDateObjectFromString: function(inputString) {
					return getFormDatesObject(inputString);
				},
				clearSlot: function (instance) {
					if(instance.originalEvent && instance.originalEvent.keyCode === 8 && SAGIA.formElements._currentTimeslotPicker) {
						instance = SAGIA.formElements._currentTimeslotPicker;
						instance._triggerBackspace = 8;
					}
					if(instance.calendarContainer) {
						$(instance.calendarContainer).find("." + instance.config.timeslotSuggestCls).removeClass(instance.config.timeslotSuggestCls);
						$(instance.calendarContainer).find("." + instance.config.timeslotCurrentCls).removeClass(instance.config.timeslotCurrentCls);
						try {
							if(instance.config) {
								instance.config._inChange = "clear";
							}
							instance.clear();
						} catch(e) {}
					}
				},
				checkTimeEntries : function(selectedDates, dateStr) {
					var _disabled, 
						// _current,
						// _class,
						// _time = new Date(dateStr).getTime(),
						_timeStr = getHourMin(dateStr),
						_getMatch = function(query) {
							var _r = false;
							$(query).each(function(indx, el) {
								if(typeof(el) === "object") {
									if(el.from <= _timeStr && el.to >= _timeStr) {
										_r = true;
										return false;
									}
								} else if(_timeStr === el) {
									_r = true;
									return false;
								}
							});
							return _r;
						};
					if(this.timeEnabled) {
						_disabled = !_getMatch(this.timeEnabled);
					} else {
						_disabled = _getMatch(this.timeDisabled);
					}
					if(_disabled && $(selectedDates).hasClass(this.timeslotCls)) {
						$(selectedDates).addClass(this.timeslotDisabledCls);
					}
				},
				onChangeBegin: function(selectedDates, dateStr, instance) {
					//changeBegin
					//function can be present on instance or (like this one is) on instance.config object
					//thou note that the instance one would be prefered
				},
				onChangeEnd: function(selectedDates, dateStr, instance) {
					//changeEnd
					//function can be present on instance or (like this one is) on instance.config object
					//thou note that the instance one would be prefered
					//there will be no date-string on the event of clearing, also only changeEnd gets the actual clear
					// if(dateStr) {
					// } else {
					// }
				},
				onReady: function(selectedDates, dateStr, instance) {
					$(instance.calendarContainer).addClass('flatpickr-calendar_timeslot')
						.append('<ul class="' + instance.config.timeslotContainerCls + '" tabindex="-1"></ul>');
					var a = $(instance.calendarContainer).children('.'+ instance.config.timeslotContainerCls),
						slot = instance.config.minuteIncrement * 60 *1000,
						slotSize = (new Date(instance.config.maxDate) - new Date(instance.config.minDate))/slot, 
						currentTime = new Date(instance.config.minDate);
					for(var i = 0; i < slotSize; i++) {
						var _data = getHourMin(currentTime),
							_elem = '<li class="' + instance.config.timeslotCls + '" data-time="' + _data + '"><span class="' + instance.config.timeslotCls + '-checkbox"></span><span class="' + instance.config.timeslotCls + '-label">' + _data + '</span></li>';
						a.append(_elem);
						instance.config.checkTimeEntries(a.children().last(), currentTime, a);						
						currentTime.setTime(currentTime.getTime() + slot);
					}
					a.children('.' + instance.config.timeslotCls).filter(':not(.' + instance.config.timeslotDisabledCls + ')').on('click', function(){
						if(!$(this).hasClass(instance.config.timeslotCurrentCls) || $(this).hasClass(instance.config.timeslotCurrentCls + '-suggested')) {
							if($(this).data('time')) {
								instance.setDate($(this).data('time'), true, instance.config.dateFormat);
							}
						}
					});
				},
				onChange: function(selectedDates, dateStr, instance) {
					if(!instance.config._inChange && dateStr.length > 0) {
						instance.config._inChange = true;
						if(typeof(instance.onChangeBegin) === "function") {
							instance.onChangeBegin(selectedDates, dateStr, instance);
						} else if(typeof(instance.config.onChangeBegin) === "function") {
							instance.config.onChangeBegin(selectedDates, dateStr, instance);
						}
					}
					var sels = selectedDates && selectedDates.length > 0 ? selectedDates[0] : "",
						el = $(instance.calendarContainer).children('.'+ instance.config.timeslotContainerCls).find('.flatpickr-timeslot[data-time="' + getHourMin(sels) +'"]'),
						_prev = this.prevSelectedDate && this.prevSelectedDate > getHourMin(new Date(sels)),
						_all = $(instance.calendarContainer).children('.'+ instance.config.timeslotContainerCls).children('.' + instance.config.timeslotCls);
						_all.removeClass(instance.config.timeslotCurrentCls);
					
					if(el.hasClass(instance.config.timeslotDisabledCls)) {
						el = _prev ? el.prev() : el.next();
						if(el.hasClass(instance.config.timeslotDisabledCls)) {
							while(el.hasClass(instance.config.timeslotDisabledCls)) {
								el = _prev ? el.prev() : el.next();
							}
						}
						if(!el.hasClass(instance.config.timeslotDisabledCls)) {
							instance.setDate(el.data('time'), true, instance.config.dateFormat);
							dateStr = el.data('time');
						}
					} else {
						el.addClass(instance.config.timeslotCurrentCls);
						$(instance.timeContainer).find('.flatpickr-minute').trigger('focus');
						if(el.position() !== undefined) {
							if (el.position().top + el.innerHeight() > el.parent().innerHeight()) {
								el.parent().scrollTop(el.parent().scrollTop() + el.position().top + el.innerHeight() - el.parent().innerHeight());
							}
							if (el.position().top < 0) {
								el.parent().scrollTop(el.parent().scrollTop() + el.position().top);
							}
							if(instance._triggerBackspace) {
								instance._triggerBackspace = undefined;
                            }
						} else if (!instance._triggerBackspace && _all.filter(instance.config.timeslotSuggestCls).length < 1 && this.prevSelectedDate) {
                            if(this.prevSelectedDate.toString().indexOf('NaN') < 0) {
                                instance.setDate(this.prevSelectedDate, true, instance.config.dateFormat);
                                dateStr = this.prevSelectedDate;
                                if(instance._triggerBackspace)instance._triggerBackspace = undefined;
                            }
						}
						_all.removeClass(instance.config.timeslotSuggestCls);
						this.prevSelectedDate = getHourMin(new Date(sels));
					}
					
					if(instance.config._inChange) {
						if(instance.config._inChange === "clear") {
							dateStr = false;
						}
						instance.config._inChange = false;
						if(typeof(instance.onChangeEnd) === "function") {
							instance.onChangeEnd(sels, dateStr, instance);
						} else if(typeof(instance.config.onChangeEnd) === "function") {
							instance.config.onChangeEnd(sels, dateStr, instance);
						}
					}
                    this.close();
                },
				onOpen : function(_selectedDates, dateStr, instance){
					//tab index fix for select elements inside modals
					var parentElement = $(this).get(0).element;

					if($(parentElement).closest('.modal').length > 0) {
						$(parentElement).closest('.modal').attr('tabindex', '');
					}
					//end tab index fix for select elements inside modals
					
					var _init;
					if(instance.selectedDates.length < 1){
						_init = true;
					}
					window.setTimeout(function() {
						$(instance.timeContainer).find('.flatpickr-minute').trigger('focus');
						if(_init && instance.selectedDates.length > 0){
							var el = $(instance.calendarContainer).children('.'+ instance.config.timeslotContainerCls).find('.' + instance.config.timeslotCls + '[data-time="' + getHourMin(instance.selectedDates[0]) +'"]');
							if(!el.hasClass(instance.config.timeslotDisabledCls)) {
								el.addClass(instance.config.timeslotCurrentCls);
							} else {
								//MCM - NO, commented, because it would select a value that the user never picked
								//instance.setDate(el.next().data('time'), true, instance.config.dateFormat);
								//el = el.siblings('.' + instance.config.timeslotCurrentCls).first();
							}
							el.addClass(instance.config.timeslotSuggestCls);
						}
						$(document).on('keyup', instance.config.clearSlot);
						SAGIA.formElements._currentTimeslotPicker = instance;
					}, 300);
				},
				onClose : function(selectedDates, dateStr, instance){
					if($(instance.calendarContainer).find("." + instance.config.timeslotSuggestCls).length > 0){
						instance.config.clearSlot(instance);
					}
					$(document).off('keyup', instance.config.clearSlot);
					if(instance._triggerBackspace)instance._triggerBackspace = undefined;
					SAGIA.formElements._currentTimeslotPicker = undefined;

					//tab index fix for select elements inside modals
					var parentElement = $(this).get(0).element;
					var closestModal = $(parentElement).closest('.modal');
					if(closestModal.length > 0) {
						closestModal.attr('tabindex', '-1');
					}					
				}
			});
		});
	},

	tip: function() {
		$('.js-tip').each(function(){
			var tiptrigger = $(this).data('tip-trigger') ? $(this).data('tip-trigger') : 'click click-outside';
			if(tiptrigger && tiptrigger.indexOf('click-outside') > 0) {
				$(this).data('trigger', 'click');
				$(this).data('hide-trigger','click-outside');
			} else {
				tiptrigger = false;
			}
			var htmlValue = $("#" + $(this).data('tip-id')).html();
			var tiptext = $(this).data('tip-title') !== undefined ?
				$(this).data('tip-title') : ($(this).data('tip-id') !== undefined && htmlValue ? htmlValue : false),
				tipPlace = $(this).data('tip-placement') ? $(this).data('tip-placement') : "top";

			var tipAutoSizeFuc = function (el) {
				var _this = $(el.target);
				if(el && !el.target && $(el).attr('aria-describedby')) {
					_this = $(el);
				}

				if(_this.data('tip-width') === "auto") {
					$("#" + _this.attr('aria-describedby')).addClass('tooltip_autosize');
				}
				if(_this.data('tip-class')) {
					$("#" + _this.attr('aria-describedby')).find(".tooltip-inner").addClass(_this.data('tip-class'));

				}
				if(_this.data('hide-trigger') === 'click-outside') {
					var _tipID = _this.attr('aria-describedby'),
						_trigger = _this,
						_shadowID = _tipID + '-shadow';
					if($('.tooltip-clickshadow').length < 1){
						$("#" + _tipID).before('<div class="tooltip-clickshadow"></div>');
						$('.tooltip-clickshadow').click(function(){
							$('.js-tip').tooltip('hide');
							$('body > .tooltip-clickshadow').remove();
						});
					}
					_this.on('hide.bs.tooltip', function () {
						$('body > .tooltip-clickshadow').remove();
					});
					$("#" + _this.attr('aria-describedby') + '-shadow').click(function(){
						_this.tooltip('hide');
					});
					$("#" + _this.attr('aria-describedby')).width('auto');
				}
				$("#" + _this.attr('aria-describedby') + ' .tooltip-list_collapsible .tooltip-listItem-header').click(function(){
					if($(this).parent().hasClass('tooltip-listItem_expanded')) {
						$(this).parent().removeClass('tooltip-listItem_expanded');
						_this.tooltip('update');
						return;
					}
					$(this).parents('.tooltip-list_collapsible').children('.tooltip-listItem_expanded').removeClass('tooltip-listItem_expanded');
					$(this).parent().addClass('tooltip-listItem_expanded');
					_this.tooltip('update');
				});
			};
			$(this).tooltip({
				html: true, 
				title: tiptext, 
				placement: tipPlace
			});

			$(this).on('inserted.bs.tooltip', tipAutoSizeFuc);
			$(this).on('hide.bs.tooltip', function () {
				$('body > .tooltip-clickshadow').remove();
			});
		});
	}
};
