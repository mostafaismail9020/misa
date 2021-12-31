<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<c:set var="demoSelectBoxContent" value="<option>Alpha</option><option>Beta</option><option>Gamma</option><option>Delta</option><option>Epsilon</option><option>Rest</option><option>Test</option>" />

<div class="mainSection mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Supporting Visit</h1>
        </div>
    </div>
</div>
<div class="mainSection mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div>
                <a href="#" class="btn btn_leftIconLink btn_darkLink appointmentControl-backBtn"><span class="iconElement iconElement_closeBack"><icon:close/></span><span class="appointmentControl-backBtn-label">Back to Overview</span></a>
            </div>
            <div>
                <a class="btn btn--primary btn--half-radius btn--small" href="#">Request Supporting Visit</a>
            </div>
        </div>
    </div>
</div>
          
<div class="mainSection mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius appointmentDetails">
            <form:form method="post" modelAttribute="">
            <div class="appointmentDetails appointmentDetails_forms">
                <div class="contentModule-headline"><icon:calendarText/> Visit Details</div>
                <div class="row">
                    <div class="col-xs-8 col-md-6">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn form-control">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="department">Department</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-8 col-md-6">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn form-control">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="branch">Branch</label>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="contentModule-section-delimiter"/>
                <div class="contentModule-headline">Services</div>
                <div class="row service-selection">
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service-type">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="serviceType1">
                                    Service Type</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn" disabled="true">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="ministry1">
                                    Ministry</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service" disabled="true">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="service1">
                                    Enquiry Type</label>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="contentModule-section-rowDelimiter"/>
                <div class="row service-selection">
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service-type">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="serviceType2">
                                    Service Type</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn" disabled="true">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="ministry2">
                                    Ministry</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">

                                <select class="js-select2-oneColumn service" disabled="true">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="service2">
                                    Enquiry Type</label>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="contentModule-section-rowDelimiter"/>
                <div class="row service-selection">
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service-type">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="serviceType3">Service Type</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service-type">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="ministry3">Ministry</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-4">
                        <div class="formSelectBox">
                            <div class="form-group">
                                <select class="js-select2-oneColumn service-type">
                                    <option></option>
                                    ${demoSelectBoxContent}
                                </select>
                                <label class="control-label" for="service1">Enquiry Type</label>
                            </div>
                        </div>
                    </div>
                </div>
                    <%--<div class="appointmentDetails-row_add row text-center">
                        <button class="btn btn_plain">+ Add Row</button>
                    </div>--%>
                <hr class="contentModule-section-delimiter"/>
                <div class="contentModule-headline">Support Visit Appointment</div>
                <div class="row">
                    <div class="col-xs-8 col-md-6">
                        <div class="formInputBox formInputBox_group ">
                            <div class="form-group">
                                <input  id="dateString" name="enquiryType" class="form-control js-form-control_date" placeholder="." value="" type="text" data-dates-enabled="30.03.2018#02.04.2018;28.04.2018#01.05.2018;13.02.2018">
                                <label class="control-label" for="dateString">
                                    Date:
                                </label>
                                <div class="formInputBox-append">
                                    <span class="formInputBox-text"><icon:calendar-gray/></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-8 col-md-6">
                        <div class="formInputBox formInputBox_group ">
                            <div class="form-group">
                                <input  id="timeStart" name="enquiryType" class="form-control js-form-control_timeslot" placeholder="." value="" type="text" data-dates-enabled="30.03.2018#02.04.2018;28.04.2018#01.05.2018;13.02.2018">
                                <label class="control-label" for="timeStart">
                                    Time:
                                </label>
                                <div class="formInputBox-append">
                                    <span class="formInputBox-text"><icon:calendar-gray/></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="contentModule-section-delimiter"/>
                <div class="contentModule-actions contentModule-actions_centered contentModule_spaceTop contentModule-actions_noMargin">
                        <button type="submit" class="btn btn--primary btn--half-radius">Request Supporting Visit</button>
                </div>
                </form:form>
        </div>
    </div>
</div>