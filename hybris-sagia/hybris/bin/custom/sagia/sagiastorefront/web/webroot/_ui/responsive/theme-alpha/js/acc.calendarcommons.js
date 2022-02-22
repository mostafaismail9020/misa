ACC.calendarcommons = {
    bindFlatpickr: function (element) {
        element.flatpickr({
            dateFormat: ACC.formatUIDate ? ACC.formatUIDate : "M d, Y",
            disable: ACC.calendarcommons.getFormDatesObject(element.data('dates-disabled')),
            enable: element.data('dates-disabled') ? [] : ACC.calendarcommons.getFormDatesObject(element.data('dates-enabled')),
            disableMobile: "true",
            locale: SAGIA.locale,
            static:true,
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
            onChange: function(selectedDates, dateStr, instance) {
                if(typeof(instance.onChangeBegin) === "function") {
                    instance.onChangeBegin(selectedDates, dateStr, instance);
                } else if(typeof(instance.config.onChangeBegin) === "function") {
                    instance.config.onChangeBegin(selectedDates, dateStr, instance);
                }
                if(typeof(instance.onChangeEnd) === "function") {
                    instance.onChangeEnd(selectedDates, dateStr, instance);
                } else if(typeof(instance.config.onChangeEnd) === "function") {
                    instance.config.onChangeEnd(selectedDates, dateStr, instance);
                }
            },
            onReady: function(selectedDates, dateStr, instance) {
                //instance.monthNav.getElementsByClassName('cur-year')[0].setAttribute("disabled", "disabled");
                ACC.calendarcommons.getFormDatesObject($(instance.input).data('dates-disabled'));
            },
            onOpen : function() {
                //tab index fix for select elements inside modals
                var parentElement = element.get(0).element;

                if($(parentElement).closest('.modal').length > 0) {
                    $(parentElement).closest('.modal').attr('tabindex', '');
                }
            },
            onClose: function() {
                //tab index fix for select elements inside modals
                var parentElement = element.get(0).element;
                if($(parentElement).closest('.modal').length > 0) {
                    $(parentElement).closest('.modal').attr('tabindex', '-1');
                }
            },
            prevArrow: '<svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M15.758.374c.365-.365.955-.365 1.319 0 .364.364.363.954-.001 1.319l-7.789 10.31 7.789 10.305c.364.365.365.955.001 1.319s-.954.364-1.319 0l-9.108-11.624 9.108-11.629z"/></svg>',
            nextArrow: '<svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M8.242.374c-.365-.365-.956-.365-1.319 0-.364.364-.363.954 0 1.319l7.79 10.311-7.79 10.305c-.364.365-.364.955 0 1.319s.955.364 1.319 0l9.107-11.624-9.107-11.63z"/></svg>'
        });
    },

    getFormDatesObject: function (cntnt) {
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
    }
};