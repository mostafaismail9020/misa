$(document).ready(function(){

	var filter = { startDate:null, endDate:null, range:null };
	var startDateStr = document.getElementById("startDateVal") !=null ? document.getElementById("startDateVal").value : null;
	var rangeStr = document.getElementById("rangeVal") != null ? document.getElementById("rangeVal").value : null;
	var startDate = null ;
	var range = 0 ;

	var formAction =  document.getElementById("selectDateForm") != null ? document.getElementById("selectDateForm").action : null;
	if(formAction)
		formAction = formAction.substring(0,formAction.indexOf("?"));

	if(rangeStr != undefined && rangeStr.length>0){
		range = parseInt(rangeStr);
		if(startDateStr != undefined && startDateStr.length>0)
			startDate = parseInt(startDateStr);
	}

	var callbackRangeCalendar =  $("#calendar").rangeCalendar({lang:"en",changeRangeCallback: rangeChanged,weekends:true, maxRangeWidth: 30});

	//select month range if checkbox is clicked
	$('#monthRange').on('change', function () {
		if ($(this).prop('checked') === true) {
			var calendar = $('#calendar').clone();
			calendar.prop('id', 'calendarMonth').find('.calendar').parents('.wrapper').remove();
			calendar.find('.months').css('margin-bottom', 0);
			calendar.find('.months .month-cell').removeClass('current');
			initCustomMonths(calendar);
			initClickEvents(calendar);
			calendar.insertAfter('#calendar');

			$('#calendar').hide();
			$('#calendarMonth').show();
			populateExistingMonthCalendar();
		} else {
			$('#calendarMonth').remove();
			$('#calendar').show();
			callbackRangeCalendar.setStartDate(moment());
			callbackRangeCalendar.setRangeWidth(3);
			goToDate($('.range-calendar .month-cell[month-id='+moment().format('YYYYMM')+']'));
		}
	});

	if (callbackRangeCalendar) {
		if(range>0){
			var startDt = new Date(startDate);
			startDt.setMinutes( startDt.getMinutes() + 20 );
			var selectedDate = moment.utc(startDate);
			// callbackRangeCalendar =  $("#calendar").rangeCalendar({startDate:startDt,lang:"en",changeRangeCallback: rangeChanged,weekends:true, maxRangeWidth: 30});
			callbackRangeCalendar.setStartDate(selectedDate);
			callbackRangeCalendar.setRangeWidth(range);
			goToDate($('.range-calendar .month-cell[month-id='+selectedDate.format('YYYYMM')+']'));
		} else {
			goToDate($('.range-calendar .month-cell[month-id='+moment().format('YYYYMM')+']'));
		}
	}

	//select whole month when doubleclicking it.
	// $(document).on('dblclick', '#calendar div.month-cell.cell', function () {
	// 	var monthId = $(this).attr('month-id');
	// 	if (monthId != undefined || monthId != "") {
	// 		var dates = $(this).parents('#calendar').find('.calendar .cal-cell[month-id='+monthId+']');
	// 		var datesCount = dates.length;
	// 		callbackRangeCalendar.setRangeWidth(datesCount);
	// 		$(dates[0]).trigger('click');
	// 	}
	// });

	function populateExistingMonthCalendar() {
		var $multipleMonth = $("#multipleMonth");
		var multipleMonth = $multipleMonth != null && $multipleMonth.val() === 'true';

		if (startDateStr && range && multipleMonth) {
			var monthArray = [];
			$('#calendar').find('.calendar .cal-cell.selected').each(function () {
				if (monthArray.indexOf($(this).attr('month-id'))) {
					monthArray.push($(this).attr('month-id'));
				}
			});

			var monthCells = $('#calendarMonth').find('.months .month-cell');
			monthCells.removeClass('selected');

			$.each(monthArray, function () {
				monthCells.filter('[month-id='+this+']').addClass('selected');
			})
		}
	}

	function initClickEvents (element) {
		var moved = false;
		element.find('.months .month-cell').on('mousedown', function () {
			moved = false;
		}).on('mousemove', function () {
			moved = true;
		}).on('click', function () {
			if (moved === false) {
				if (!$(this).hasClass('selected')) {
					if (!$(this).next().hasClass('selected') && !$(this).prev().hasClass('selected')) {
						$(this).parent().find('.month-cell').removeClass('selected');
					}

					$(this).addClass('selected');
				} else {
					if (!($(this).next().hasClass('selected') && $(this).prev().hasClass('selected'))) {
						$(this).removeClass('selected');
					}
				}
			}
		})
	}

	function initCustomMonths (element) {
		var obj = {monthsObj: {}};
		obj.monthsObj = element.find('.months');

		obj.monthsObj.draggable({

			axis: "x" ,
			scrollSensitivity: 100,
			scrollSpeed: 100 ,
			cursor: "move",
			stop: function(e, ui) {


				$(this).css({top: 0});
				obj.lastTarget = e.target;

				setTimeout(function(){
					placeElement(obj.monthsObj[0], null);
				},10);
			}
		});

		placeElement = function (el, position) {

			var $el = $(el).parents('.range-calendar');
			var calendarViewWidth = $el.outerWidth();
			var cellWidth = $(el).find(".cell").first().outerWidth();
			var objChildrens = $(el).children().length;
			var objWidth = (objChildrens*cellWidth);

			var elPos =  $(el).position();
			var left = (  !position ? parseInt(elPos.left) :  -position.left);

			if(calendarViewWidth > objWidth )
				left = (calendarViewWidth-objWidth)/2;
			else if (calendarViewWidth < objWidth && left >= 0)
				left = 0 ;
			else if(left < calendarViewWidth-objWidth)
				left = -objWidth+calendarViewWidth;

			$(el).stop().animate({left: left},300,'easeOutCirc');
		}
	}

	function rangeChanged(target,range){
		filter = {	startDate :range.start,
			endDate   :range.end,
			range     :range.width
		};
	};

	function goToDate(currentMonthCell) {
		currentMonthCell.trigger('click');

		// $('.range-calendar .months').stop().animate({left: '-'+currentMonthCell.position().left+'px'},300,'easeOutCirc');

		var lastMonthElement = $('.range-calendar .month-cell:last-child');
		var totalWidth = lastMonthElement.width() + lastMonthElement.position().left;
		var currentToLastWidth =  (totalWidth) - currentMonthCell.position().left;
		var wrapperWidth = $('#calendar').width();

		if (currentToLastWidth < $('#calendar').width()) {
			var position = totalWidth-wrapperWidth;
			$('.range-calendar .months').stop().animate({left: '-'+position+'px'},300,'easeOutCirc');
		} else {
			$('.range-calendar .months').stop().animate({left: '-'+currentMonthCell.position().left+'px'},300,'easeOutCirc');
		}
	}

	filterBlogs = function () {
		var startDate = null, endDate = null;
		var multipleMonths = false;

		if ($('#monthRange').prop('checked') === true) {
			var calendar = $('#calendarMonth');
			var originalCalendar = $('#calendar');
			var monthArray = [];

			calendar.find('.months .month-cell').filter('.selected').each(function () {
				monthArray.push($(this).attr('month-id'));
			});

			var startDateEl = originalCalendar.find('.calendar .cal-cell').filter('[month-id='+monthArray[0]+']').first();
			var startDateElVal = startDateEl.attr('date-id');
			var endDateEl = originalCalendar.find('.calendar .cal-cell').filter('[month-id='+monthArray[monthArray.length - 1]+']').last();
			var endDateElVal = endDateEl.attr('date-id');

			// if (startDateElVal.length === 7) {
			// 	startDateElVal += "0";
			// }
			//
			// if (endDateElVal.length === 7) {
			// 	endDateElVal += "0";
			// }

			startDate = new Date(getMomentFromString(startDateElVal).toString()).getTime();
			endDate = new Date(getMomentFromString(endDateElVal).toString()).getTime();
			multipleMonths = true;
		} else {
			startDate = new Date(filter.startDate).getTime();
			endDate = new Date(filter.endDate).getTime();
		}

		var url = formAction + "?startDate=" + startDate + "&endDate=" + endDate;

		if (multipleMonths) {
			url += '&multipleMonth=true'
		}

		window.location = url;
	};

	resetFilter = function () {
		window.location = formAction;
	};

	getMomentFromString = function (date) {
		var endYear = date.substring(0, 4);
		var endMonth = date.substring(4, 6);
		var endDay = date.substring(6, 8);
		var m = moment.utc([endYear, endMonth, endDay], "YYYY MM DD");

		return m;
	}

	if ($('#monthRange').is(':checked')) {
		setTimeout(function () {
			$('#monthRange').trigger('change');
		}, 10);
	}
});