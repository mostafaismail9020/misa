<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 class="uiTest-headline">Email Testing</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="from" class="form-control" placeholder="." value="" type="text">
                    <label class="control-label" for="from">From</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="to" class="form-control" placeholder="." value="" type="text">
                    <label class="control-label" for="to">To</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="subject" class="form-control" placeholder="." value="" type="text">
                    <label class="control-label" for="subject">Subject</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formTextArea">
                <div class="form-group">
                    <textarea rows="5" id="message" class="form-control form-control_slim" placeholder="."></textarea>
                    <label class="control-label" for="message">Message</label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="contentModule-actions contentModule-actions_centered">
                <button id="sendEmail" class="btn" style="display:none">Send</button>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout(function() {
        $("#sendEmail").show().on("click", function () {
            $.ajax(ACC.config.encodedContextPath + "/ui/sendEmail", {
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                cache: false,
                data: JSON.stringify({
                    from: $("#from").val(),
                    to: $("#to").val(),
                    subject: $("#subject").val(),
                    message: $("#message").val()
                }),
                success: function (data) {
                    alert("email sent successfully");
                },
                error: function(data) {
                    alert("Failed to send email");
                }
            });
        });
    }, 1000);
</script>

<%@ include file="uiElements/uiTest_formErrorTest.jsp" %>
<%@ include file="uiElements/uiTest_urls.jsp" %>
<%@ include file="uiElements/uiTest_buttons.jsp" %>
<%@ include file="uiElements/uiTest_baBreadcrumb.jsp" %>
<%@ include file="uiElements/uiTest_formFieldTest.jsp" %>
<%@ include file="uiElements/uiTest_contentModule.jsp" %>
<%@ include file="uiElements/uiTest_controlBar.jsp" %>
<%@ include file="uiElements/uiTest_dlList.jsp" %>
<%@ include file="uiElements/uiTest_documentSection.jsp" %>
<%@ include file="uiElements/uiTest_dottedList.jsp" %>
<%@ include file="uiElements/uiTest_eServiceTutorial.jsp" %>
<%@ include file="uiElements/uiTest_historyList.jsp" %>
<%@ include file="uiElements/uiTest_messageList.jsp" %>
<%@ include file="uiElements/uiTest_timestampList.jsp" %>
<%@ include file="uiElements/uiTest_downloadList.jsp" %>
<%@ include file="uiElements/uiTest_ynList.jsp" %>
<%@ include file="uiElements/uiTest_baList.jsp" %>
<%@ include file="uiElements/uiTest_statusBox.jsp" %>
<%@ include file="uiElements/uiTest_statusIndicator.jsp" %>
<%@ include file="uiElements/uiTest_pageHeader.jsp" %>
<%@ include file="uiElements/uiTest_flatpickr.jsp" %>
<%@ include file="uiElements/uiTest_acceptTerms.jsp" %>
<%@ include file="uiElements/uiTest_formCheckBox.jsp" %>
<%@ include file="uiElements/uiTest_formError.jsp" %>
<%@ include file="uiElements/uiTest_formInputBox.jsp" %>
<%@ include file="uiElements/uiTest_formInputFile.jsp" %>

<%@ include file="uiElements/uiTest_formInputFileBox.jsp" %>
<%@ include file="uiElements/uiTest_formRadioBox.jsp" %>
<%@ include file="uiElements/uiTest_formRadioPayment.jsp" %>
<%@ include file="uiElements/uiTest_formRadioButton.jsp" %>

<%@ include file="uiElements/uiTest_formSelectBox.jsp" %>
<%@ include file="uiElements/uiTest_formTextArea.jsp" %>



<%@ include file="uiElements/uiTest_searchInputBox.jsp" %>

<%@ include file="uiElements/uiTest_serviceTime.jsp" %>


<%@ include file="uiElements/uiTest_links.jsp" %>
<%@ include file="uiElements/uiTest_notifyCount.jsp" %>
<%@ include file="uiElements/uiTest_paginationModule.jsp" %>
<%@ include file="uiElements/uiTest_panelModule.jsp" %>
<%@ include file="uiElements/uiTest_tabComponent.jsp" %>

<%@ include file="uiElements/uiTest_tableModule.jsp" %>
<%@ include file="uiElements/uiTest_panelTabs.jsp" %>
<%@ include file="uiElements/uiTest_modals.jsp" %>
<%@ include file="uiElements/uiTest_rangeSlider.jsp" %>
<%@ include file="uiElements/uiTest_ratingModule.jsp" %>
<%@ include file="uiElements/uiTest_scrollWrapper.jsp" %>
<%@ include file="uiElements/uiTest_notificationList.jsp" %>
<%@ include file="uiElements/uiTest_appointmentList.jsp" %>
<%@ include file="uiElements/uiTest_appointmentDetails.jsp" %>
<%@ include file="uiElements/uiTest_sagiaNavigation.jsp" %>
<%@ include file="uiElements/uiTest_enumList.jsp" %>

<%--
<%@ include file="uiElements/uiTest_iconElement.jsp" %>
<%@ include file="uiElements/uiTest_iconElements.jsp" %>
<%@ include file="uiElements/uiTest_tooltip.jsp" %>
--%>



<%--
<%@ include file="uiElements/uiTest_SAH-180.jsp" %>
<%@ include file="uiElements/uiTest_SAH-1344.jsp" %>
<%@ include file="uiElements/uiTest_SAH-524.jsp" %>
<%@ include file="uiElements/uiTest_SAH-530.jsp" %>
<%@ include file="uiElements/uiTest_SAH-536.jsp" %>
<%@ include file="uiElements/uiTest_SAH-539.jsp" %>
<%@ include file="uiElements/uiTest_SAH-549.jsp" %>
<%@ include file="uiElements/uiTest_SAH-550.jsp" %>
<%@ include file="uiElements/uiTest_SAH-552.jsp" %>
<%@ include file="uiElements/uiTest_SAH-703.jsp" %>
<%@ include file="uiElements/uiTest_SAH-760.jsp" %>
<%@ include file="uiElements/uiTest_SAH-843.jsp" %>
<%@ include file="uiElements/uiTest_SAH-890.jsp" %>
<%@ include file="uiElements/uiTest_SAH-932.jsp" %>
<%@ include file="uiElements/uiTest_SAH-933.jsp" %>
<%@ include file="uiElements/uiTest_SAH-1290.jsp" %>
<%@ include file="uiElements/uiTest_SAH-1289.jsp" %>
<%@ include file="uiElements/uiTest_SAH-1303.jsp" %>
<%@ include file="uiElements/uiTest_SAH-1344.jsp" %>
<%@ include file="uiElements/uiTest_SAH-933.jsp" %>
--%>
