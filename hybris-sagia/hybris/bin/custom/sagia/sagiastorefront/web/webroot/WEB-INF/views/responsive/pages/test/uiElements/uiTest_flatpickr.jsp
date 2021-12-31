<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">flatpickr</h1>
	        <p class="uiTest-description">Some basic Datepicker examples</p>
    </div>
    <!-- End of Module description-->
	<div class="row">
	    <div class="col">
	        <div class="formInputBox formInputBox_group ">
                <div class="form-group">
                    <input  id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text">
                    <label class="control-label" for="profile.enquiry.type">
                        Date
                    </label>
                    <div class="formInputBox-append">
                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                    </div>              
                </div>
            </div>
	    </div>
	    <div class="col">
	        <div class="formInputBox formInputBox_group ">
                <div class="form-group">
                    <input  id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text" data-dates-disabled="30.03.2018#02.04.2018;28.04.2018#01.05.2018;13.02.2018">
                    <label class="control-label" for="profile.enquiry.type">
                         Disabled Entries
                    </label>
                    <div class="formInputBox-append">
                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                    </div>              
                </div>
            </div>
	    </div>
	    <div class="col">
	    	<div class="formInputBox formInputBox_group ">
                <div class="form-group">
                    <input  id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text" data-dates-enabled="30.03.2018#02.04.2018;28.04.2018#01.05.2018;13.02.2018">
                    <label class="control-label" for="profile.enquiry.type">
                         Enabled Entries
                    </label>
                    <div class="formInputBox-append">
                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                    </div>              
                </div>
            </div>
	    </div>
        <div class="col">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_timeslot"
                           placeholder="." type="text" data-dates-disabled="10:00#12:00;14:00#15:30;16:45">
                    <label class="control-label " for="profile.enquiry.type">
                        Disabled Time Entries</label>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_timeslot"
                           placeholder="." type="text" data-dates-enabled="09:00;11:00#11:45;14:30#15:00;16:15">
                    <label class="control-label " for="profile.enquiry.type">
                        Enabled Time Entries</label>
                </div>
            </div>
        </div>
	</div>
</div>