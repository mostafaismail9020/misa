<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
<div class="mainSection mainSection potential_opportunity_section">
    <div class="achievement_header">
        <img class="achievement_header_icon page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">New Service Request</h1>
            </div>
            <div class="profile-icons float-right">
				<div class="dashboardUser-right col-md-6">
					<div class="col-6 d-flex">
						<div class=" user-icon mr-5">
							<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
							<a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
								<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/>
							</a>
						</div>
						<div class=" user-icon mr-5">
							<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
							<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
								<c:if test="${hasLicense or hasAwaitingPayment}">
									<button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
										<span id="unreadNotificationSpan" class="notifyCount notifyCount_small potential_opportunity_span"></span>
										<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
									</button>
								</c:if>
								<div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
								<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup-otherpage my-msg-popup">
									<div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
									<ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
									<div class="sagiaNavigation-subPane-actions">
										<a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
									</div>
								</div>
								<!-- <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible ">
									<div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
									<ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
									<div class="sagiaNavigation-subPane-actions">
										<a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
									</div>
								</div> -->
							</div>
						</div>
						<div class=" user-icon mr-1">
							<a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
									class="sagiaNavigation-btn sagiaNavigation-user"> 
								<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/>
							</a>
						</div>
					</div>
				</div>
            </div> 
        </div>
    </div>
</div>

 
<div class="container mb-3 pb-2">
	<div class="row w-75 m-auto">
		<div class="Back_to_Dashboardss">
			<c:url value="/potentialOpportunity/${ticketId}" var="potentialOpportunityURL"/>
			<a href="${potentialOpportunityURL}" class="btn btn_leftIconLink btn_darkLink">
				<!-- <span class="iconElement iconElement_closeBack"><icon:close/></span> -->
				<svg class="potential_svg_arrow4" xmlns="http://www.w3.org/2000/svg" width="10" height="17.116" viewBox="0 0 10 17.116">
					<path id="Icon_ionic-ios-arrow-back" data-name="Icon ionic-ios-arrow-back" d="M14.265,14.749l6.618-6.471a1.2,1.2,0,0,0,0-1.727,1.275,1.275,0,0,0-1.77,0l-7.5,7.332a1.2,1.2,0,0,0-.036,1.687l7.53,7.383a1.277,1.277,0,0,0,1.77,0,1.2,1.2,0,0,0,0-1.727Z" transform="translate(-11.251 -6.194)" fill="#00a6be"/>
				</svg>
				Back to Potential Opportunities
			</a>
		</div>
	</div>
</div> 


<spring:url value="/potentialOpportunity/${ticketId}/serviceRequest" var="submitServiceRequestAction" htmlEscape="false"/>
<div class="container">
	<div class="w-75 m-auto">
		<div class="licensecontactperson_bottomboarder">
			<div class="contentModule-headline">Basic Information</div>
		</div>
		<form:form class="contact-form pt-3" action="${submitServiceRequestAction}" id="js-quick-tialoppor_new" method="post" modelAttribute="sagiaServiceRequestFormData">
			<!-- onsubmit="return validateFormsetting()" id="js-quick-tialoppor_new" -->
			<div class="row pb-5"> 
				
			    <div class="col-md-6 pb-3">
					<div class="formSelectBox">
						<div class="form-group">
							<form:select path="incidentCategory" id="incidentCategory" class="js-select2-oneColumn js-select2-oneColumn form-control select2-hidden-accessible">
								<c:forEach var="incidentCategoryValue" items="${incidentCategories}">
									<form:option value="${incidentCategoryValue}">${incidentCategoryValue}</form:option>
								</c:forEach>
							</form:select> 
							<label class="control-label control-label_mandatory" for="qm1Role">
									Incident Category  
							</label> 
						</div>
					</div> 
			    </div> 
			    <div class="col-md-6 pb-3">
					<div class="formSelectBox">
						<div class="form-group">
							<form:select path="serviceCategory" id="serviceCategory" disabled="true" class="js-select2-oneColumn js-select2-oneColumn form-control select2-hidden-accessible">
								<c:forEach var="serviceCategoryValue" items="${serviceCategories}">
									<form:option value="${serviceCategoryValue}">${serviceCategoryValue}</form:option>
								</c:forEach>
							</form:select> 
							<label class="control-label control-label_mandatory" for="qm1Role">
								Service Category 
							</label> 
						</div>
					</div> 
			    </div> 
				
			    <div class="col-md-6 pb-3">
					<div class="formSelectBox">
						<div class="form-group">
							<form:select path="priority" id="priority" class="js-select2-oneColumn js-select2-oneColumn form-control select2-hidden-accessible">
								<c:forEach var="priorityValue" items="${priorities}">
									<form:option value="${priorityValue}">${priorityValue}</form:option>
								</c:forEach>
							</form:select>
							<label class="control-label control-label_mandatory" for="qm1Role">
								Priority
							</label> 
						</div>
					</div> 
			    </div> 
				<div class="col-md-6 pb-5">
					<div class="formInputBox ">
						<div class="form-group">
							<input type="text" class="js-quick-tialoppor_new text form-control"
							name="subject" id="subject" /> 
							<!-- onkeypress="return onlyAlphabets(event)" -->
							<label class="control-label control-label_mandatory" for="Subject " required>
								Subject 
							</label> 
							<div class="help-block"></div>
						</div> 
					</div> 
			   </div>
			   <div class="col-md-12 pb-5">
				   <div class="formInputBox ">
					   <div class="form-group">
						<textarea type="text" class="js-quick-tialoppor_description text description form-control"
						name="description" id="description" required /></textarea>	
						<!-- onkeypress="return onlyAlphabets(event)"  -->
						   <label class="control-label control-label_mandatory" for="Description">
							Description 
						   </label> 
						   <div class="help-block1"></div>
					   </div> 
				   </div> 
			  </div> 

				<!-- <button class="btn btn-block btn-primary positive adv_search_button w-25 m-auto mb-5" type="submit">
					Submit
				</button> -->
				
				<input type="submit"  id="submit" class="btn btn-block btn-primary positive adv_search_button w-25 m-auto mb-5" value="Submit"   onclick="validateFormsetting()" />
			</div>
		</form:form>
	</div>
</div>


<script>   
	$(".js-quick-tialoppor_new").on("keydown", function(e) {  
		$('.help-block').html("");  
    });
	$(".js-quick-tialoppor_description").on("keydown", function(e) {  
		$('.help-block1').html("");   
    });

	
function validateFormsetting(){
    var subject = $("#subject");
    var description = $("#description");  
	
    subject.removeClass('hasError');
    description.removeClass('hasError');
    var valid = true;

	if (subject.val() === "" ){       
        subject.addClass('hasError'); 
		//submit.addClass('visabless'); 
		$('.help-block').html("<span class='error'>Please fill out this field</span>"); 
		valid = false;
	}
    if(description.val() === "" ){
        description.addClass('hasError'); 
		$('.help-block1').html("<span class='error'>Please fill out this field</span>"); 
		//submit.addClass('visabless'); 
        valid = false;
    }  

    return valid;
}

</script>