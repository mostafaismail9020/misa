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


<div class="mainSection mainSection potential_opportunity_section">
    <div class="achievement_header">
        <img class="achievement_header_icon page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up"><spring:theme code="my.potential.opportunity.label"/></h1>
            </div>
             <div class="row profile-icons float-right">
				<div class="dashboardUser-right">
					<div class="d-flex">
						<div class=" user-icon ml-1 mr-2">
							<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
							<a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
								<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.svg"/>
							</a>
						</div>
						<div class=" user-icon mr-3 ml-3">
							<!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
							<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
								<c:if test="${hasLicense or hasAwaitingPayment}">
									<button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
										<span id="unreadNotificationSpan" class="notifyCount notifyCount_small potential_opportunity_span"></span>
										<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg"/>
									</button>
								</c:if>
								<div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
								<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible ">
									<div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
									<ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
									<div class="sagiaNavigation-subPane-actions">
										<a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
									</div>
								</div>
							</div>
						</div>
						<div class=" user-icon mr-1">
							<a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
									class="sagiaNavigation-btn sagiaNavigation-user"> 
								<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.svg"/>
							</a>
						</div>
					</div>
				</div>
            </div> 
        </div>
    </div>
</div>
 
<div class="container mb-3 pb-2">
	<div class="row">
		<div class="col-md-6 pull-left">
			<a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service">
				<!-- <span class="iconElement iconElement_closeBack"><icon:close/></span> -->
				<svg class="potential_svg_arrow" xmlns="http://www.w3.org/2000/svg" width="10" height="17.116" viewBox="0 0 10 17.116">
					<path id="Icon_ionic-ios-arrow-back" data-name="Icon ionic-ios-arrow-back" d="M14.265,14.749l6.618-6.471a1.2,1.2,0,0,0,0-1.727,1.275,1.275,0,0,0-1.77,0l-7.5,7.332a1.2,1.2,0,0,0-.036,1.687l7.53,7.383a1.277,1.277,0,0,0,1.77,0,1.2,1.2,0,0,0,0-1.727Z" transform="translate(-11.251 -6.194)" fill="#00a6be"/>
				  </svg>
				<spring:theme code="general.backtodashboard" />
			</a>
		</div>
		<div class="col-md-6  pull-right text-right">
			<c:url value="/potentialOpportunity/${contactTicketDetails.ticketID}/serviceRequest" var="serviceRequestURL"/>
			<a href="${serviceRequestURL}" class="btn-dashboard text-uppercase potentialOpportunityServiceReq" style="float: right;"> 
				<spring:theme code="my.potential.opportunity.service.request.button" />
			</a>
		</div>
	</div>
</div> 

<div class="container">
	<div class="row"> 
		<div class="pull-right col-12 p-0"> 
			<div class="showdowbox_potential col-12 text_potential">
				<div class="stauts_potential">
					<h5 class="h5_posNUMBER">
						<spring:theme code="my.potential.opportunity.number.label"/>&nbsp;
						<span class="goldcolor">${contactTicketDetails.ticketID}</span>
					</h5> 
					<h5 class="status"><span class="goldcolor">
						${contactTicketDetails.sectorCategoryName}
						<c:if test="${not empty opportunityDetails.name}">
							- ${opportunityDetails.name}
						</c:if>
					</span></h5>
					<hr class="mypotentialOpp_hr_hor"></hr>
				</div>
				
				<c:if test="${not empty contactTicketDetails.investorlead and contactTicketDetails.investorlead ne 'NA'}">
					<!-- 
					<div class="stauts_potential">
						<h5 class="h5_status text-center">Status:<span class="yellocolor">In Progress - NDA Needed</span> </h5>					
					</div>
					 -->
					<c:set var="investorlead" value="${contactTicketDetails.investorlead.code}" />
					<div class="circle_potential">
						<div class="tabs">							
							<ul class="nav nav-tabs mb-0" role="tablist">								
								<li class="nav-item active show d-in-table"> 
									<span class="nav-link" href="#Interest" role="tab" aria-selected="false">
										<spring:theme code="my.potential.opportunity.phase.interest"/>
									</span>
									<img src="${commonResourcePath}/images/dashboard-media/potential.png" class="img-responsive img-pot-opp">
									<img src="${commonResourcePath}/images/arrow-round-forward.png" class="img-responsive">
								</li>
								
								<c:if test="${investorlead eq 'Z08' or investorlead eq 'Z02'}">
									<li class="nav-item active d-in-table">
								</c:if>
								<c:if test="${investorlead eq 'Z06'}">
									<li class="nav-item active show d-in-table">
								</c:if>
										<span class="nav-link" href="#NDA" role="tab" aria-selected="false">
											<spring:theme code="my.potential.opportunity.phase.nda"/>
										</span>
										<img src="${commonResourcePath}/images/dashboard-media/potential.png" class="img-responsive img-pot-opp">
										<img src="${commonResourcePath}/images/arrow-round-forward.png" class="img-responsive">
									</li>
									
								<c:if test="${investorlead eq 'Z08' or investorlead eq 'Z02'}">
									<li class="nav-item d-in-table">
								</c:if>
								<c:if test="${investorlead eq 'Z06'}">
									<li class="nav-item active d-in-table">
								</c:if>							
										<span class="nav-link" href="#Letter_of_intent" role="tab" aria-selected="false">
											<spring:theme code="my.potential.opportunity.phase.letter"/>
										</span>
										<img src="${commonResourcePath}/images/dashboard-media/potential.png" class="img-responsive img-pot-opp">
										<img src="${commonResourcePath}/images/arrow-round-forward.png" class="img-responsive">
									</li>
									
								<li class="nav-item d-in-table">
									<span class="nav-link" href="#Business_plan" role="tab" aria-selected="false">
										<spring:theme code="my.potential.opportunity.phase.business"/>
									</span>
									<img src="${commonResourcePath}/images/dashboard-media/potential.png" class="img-responsive img-pot-opp">
									<img src="${commonResourcePath}/images/arrow-round-forward.png" class="img-responsive">
								</li>								
								<li class="nav-item d-in-table">
									<span class="nav-link" href="#Deal" role="tab" aria-selected="false">
										<spring:theme code="my.potential.opportunity.phase.deal"/>
									</span>
									<img src="${commonResourcePath}/images/dashboard-media/potential.png" class="img-responsive img-pot-opp">
								</li>								
							</ul>
						</div>
					</div>
				</c:if>
			</div>
<!--			
<c:set var="ticketId" value="${contactTicketDetails.ticketID}"/>
<form:form action="${encodedContextPath}/potentialOpportunity/${ticketId}/uploadAttachment" modelAttribute="contactTicketForm" method="POST" enctype="multipart/form-data">
<div class="form-group">
<div class="custom-file mb-3">
       <input type="file" accept="application/pdf" class="custom-file-input" id="pdfAttachment" name="pdfAttachment"/>
       <%-- <input type="text" name="comment" class="js-quick-tialoppor_newcomment form-control reply_here" placeholder="<spring:theme code="my.potential.opportunity.enter.reply"/>"/> --%>
       
       <label class="custom-file-label" for="pdfAttachment" id="contact-ticket-upload-file">
                 <spring:theme code="portal.contactus.form.drag.file.label" /> 
           <span class="pvcy-policy">
                 <spring:theme code="portal.contactus.form.browse.label" />
           </span>
       </label>
	
       <button type="submit" id="submit" class="btn btn_submit_whitess" value="Upload" >Upload Documents</button>
</div>
</div> 
</form:form>-->
			
			<%-- 
			<div class="showdowbox_potential col-12 text_potential mt-5 mb-2">
				<!-- Tab panes -->
				<div class="tab-content dashboard-tab-body license">
					<div role="tabpanel" class="tab-pane fade show active" id="Interest"> 
						<div class="container service-wrapper-info  pt-5 pb-5 mb-5">
							<div class="serviceModule serviceModule_list mx-5 pt-4">
								<div class="serviceModule-section">
									<div class="serviceModule-content">
										<div class="serviceModule-description">
											<span class="serviceModule-headline">Documents</span> 
											<div class="serviceModule-detail serviceList-description">
												<div class="w-75"><p>N/A</p></div>
											</div> 
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
					<div role="tabpanel" class="tab-pane fade" id="NDA">
						<div class="container service-wrapper-info  pt-5 pb-5 mb-5">
							<div class="serviceModule serviceModule_list mx-5 pt-4">
								<div class="serviceModule-section">
									<div class="serviceModule-content">
										<div class="serviceModule-description">
											<span class="serviceModule-headline">NDA Documents</span> 
											<div class="serviceModule-detail serviceList-description">
												<div class="w-75"><p>N/A</p></div>
											</div> 
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
					<div role="tabpanel" class="tab-pane fade" id="Letter_of_intent">
						<div class="container service-wrapper-info  pt-5 pb-5 mb-5">
							<div class="serviceModule serviceModule_list mx-5 pt-4">
								<div class="serviceModule-section">
									<div class="serviceModule-content">
										<div class="serviceModule-description">
											<span class="serviceModule-headline">NDA Documents</span> 
											<div class="serviceModule-detail serviceList-description">
												<div class="w-75"><p>N/A</p></div>
											</div> 
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
					<div role="tabpanel" class="tab-pane fade" id="Business_plan">
						<div class="container service-wrapper-info  pt-5 pb-5 mb-5">
							<div class="serviceModule serviceModule_list mx-5 pt-4">
								<div class="serviceModule-section">
									<div class="serviceModule-content">
										<div class="serviceModule-description">
											<span class="serviceModule-headline">Business_plan Documents</span> 
											<div class="serviceModule-detail serviceList-description">
												<div class="w-75"><p>N/A</p></div>
											</div> 
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
					<div role="tabpanel" class="tab-pane fade" id="Deal">
						<div class="container service-wrapper-info  pt-5 pb-5 mb-5">
							<div class="serviceModule serviceModule_list mx-5 pt-4">
								<div class="serviceModule-section">
									<div class="serviceModule-content">
										<div class="serviceModule-description">
											<span class="serviceModule-headline">Deal Documents</span> 
											<div class="serviceModule-detail serviceList-description">
												<div class="w-75"><p>N/A</p></div>
											</div> 
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
				</div>
			</div>
			 --%>
		</div>
	</div>
</div>


<!---Documents start-->
<div class="container  service-wrapper-info pt-5 pb-5">
	<div class="row serviceModule serviceModule_list ">
		<div class="pull-left col-12 showdowbox_potential">
			<div class="mx-5 potentialOpportunityChatBox">
				<div class="serviceModule serviceModule_list pt-4">
					<div class="serviceModule-section">
						<div class="serviceModule-content">
							<div class="serviceModule-description">
								<span class="serviceModule-headline">Documents</span> 
								<div class="serviceModule-detail serviceList-description">
									<c:set var="ticketId" value="${contactTicketDetails.ticketID}"/>
									<form:form action="${encodedContextPath}/potentialOpportunity/${ticketId}/uploadAttachment" modelAttribute="contactTicketForm" method="POST" enctype="multipart/form-data">
										<div class="form-group mt-2 pt-2">
											<div class="formInputFile">
												<div class="form-group">
													<div class="row">
													<div class="col-sm-6">
													<!--<img class="potential_opportunity_images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/>-->
													<input  id="pdfAttachment" name="pdfAttachment" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
													<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
													<label class="control-label document_padd_wid100" for="">Choose File</label>
													<div class="form-icon form-icon_browse">
														<svg xmlns="http://www.w3.org/2000/svg" class="" width="29" height="29" viewBox="0 0 29 29">
														<g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
															<path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
															<path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
															<path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
														</g>
														</svg>
													</div>
													<div class="form-icon form-icon_reset js-inputFile-reset">
														<icon:cross/>
													</div> 
													</div>
													<div class="col-sm-6">
														<button type="submit" id="submit" class="btn btn_Upload_Documents_height" value="Upload">Upload Documents</button>  
													</div>
													</div>
												</div>
											</div>
										<!--<div class="custom-file mb-3">
											<input type="file" accept="application/pdf" class="custom-file-input form-control" id="pdfAttachment" name="pdfAttachment"/>
											  
											<label class="custom-file-label" for="pdfAttachment" id="contact-ticket-upload-file">
														<spring:theme code="portal.contactus.form.drag.file.label" /> 
												<span class="pvcy-policy">
														<spring:theme code="portal.contactus.form.browse.label" />
												</span>
											</label>
											
											<button type="submit" id="submit" class="btn btn_submit_whitess" value="Upload" />
										</div>-->
										</div>
										</form:form>
								</div> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<!---Documents end--> 
<div class="container  service-wrapper-info pt-5 pb-5">
	<div class="row serviceModule serviceModule_list ">
		<div class="pull-left col-12 showdowbox_potential">
			<div class="mx-5 potentialOpportunityChatBox">				
				<!--<input type="submit" value="refresh" id="submit"  onClick="window.location.reload();" class="btn btn_submit_whitess"/>-->
				
				<h5 class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="my.potential.opportunity.comments.label"/></span>
					<button onclick="commentTextArea()" class="btn-dashboard text-uppercase my-3 my-md-0">
						<spring:theme code="my.potential.opportunity.add.comments"/>
					</button>
 				</h5> 
				<c:set var="ticketId" value="${contactTicketDetails.ticketID}"/>
				<div class="comment_box_form" id="comment_box_form" style="display: none;">
					<c:set var="today" value="<%=new java.util.Date()%>" />
					<label class="new_comment"><spring:theme code="my.potential.opportunity.new.comments"/>
						<b> <spring:theme code="my.potential.opportunity.ason.comments"/> <fmt:formatDate type="date" value="${today}" /></b>
					</label>
					<form:form action="${ticketId}" modelAttribute="contactTicketForm" id="js-quick-tialoppor_newcomment"  onsubmit="return validateForm()">
						<div class="form-group">
							<input type="text" name="comment" class="js-quick-tialoppor_newcomment form-control reply_here" placeholder="<spring:theme code="my.potential.opportunity.enter.reply"/>"/>
							<!-- <label class="control-label control-label_mandatory">
								<spring:theme code="my.potential.opportunity.enter.reply"/>
							</label>  -->
							<div class="help-block"></div>
							<div class="success-message-block"></div>
						</div>
						<div class="error-msg"></div>
						<div class="potentialOpportunityNewChat float-right">
							<button type="button" class="btn btn_cancel_whitess"  onclick="commentTextArea()" >
								<spring:theme code="portal.contactus.form.cancel.button"/>
							</button>
							<input type="submit" class="btn btn_submit_whitess" value="<spring:theme code="portal.contactus.form.submit.button"/>" />
						</div>
					</form:form> 
				</div>
				
				<div class="comment_box_message">
					<c:forEach items="${comments}" var="comment" >
						<c:if test="${comment.interventionType eq 'NotesForCustomer'}">
							<div class="misaComment">
								<div class="row"> 
									<div class="col-12 d-inline">
										<div class="comment_misa_message_image">
											<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="53" height="53" viewBox="0 0 53 53">
												<defs>
													<filter id="Ellipse_174" x="0" y="0" width="53" height="53" filterUnits="userSpaceOnUse">
														<feOffset dy="3" input="SourceAlpha"/>
														<feGaussianBlur stdDeviation="3" result="blur"/>
														<feFlood flood-opacity="0.161"/>
														<feComposite operator="in" in2="blur"/>
														<feComposite in="SourceGraphic"/>
													</filter>
												  	<pattern id="pattern" preserveAspectRatio="none" width="100%" height="100%" viewBox="0 0 1115 1797">
														<image width="1115" height="1797" xlink:href="data:image/png;base64,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AoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQGILAAAAQCCxBQAAACCQ2AIAAAAQSGwBAAAACCS2AAAAAAQSWwAAAAACiS0AAAAAgcQWAAAAgEBiCwAAAEAgsQUAAAAgkNgCAAAAEEhsAQAAAAgktgAAAAAEElsAAAAAAoktAAAAAIHEFgAAAIBAYgsAAABAILEFAAAAIJDYAgAAABBIbAEAAAAIJLYAAAAABBJbAAAAAAKJLQAAAACBxBYAAACAQDnFluvlcnm+/rm9/msAAACAsY7w3WsJ2XSEXGJL9wPqfL3+6+9XeAEAAAC4vjrB89UM+oHlK5efTg6x5T20vP/3vkZ+iAAAAEAbbr2hjLGoMtUXDnV2bFnzg+iPB5l2AQAAgLr1O8DXwgGMLILLmbFlzw+gP+0ivAAAAEA9xrYJLXV6cDkrtkT9G+9vM3KoLgAAAJTp9jbFstepweWs2JLi0BqH6gIAAEA5+ofdLt0mtMZpweWM2JL6oFvTLgAAAJCvJYfdRrmeMZBxdGw5+o1CXw7VBQAAgNOlnmKZcvT/v0Njy9mvbvYKaQAAADjWkVMsUw5tAUfFlpwCh1dIAwAAQDpnTrFMOaxNHBFbcj43xbQLAAAAxOi/svnMKZYph6z/U8eWa8Y/4D7TLgAAALDeNfiVzUdI/oailLHl1Hda72DaBQAAAKbdCl87J+0VqWJLqaGlz7QLAAAA/PZ+FkvJknaLFLGlhtDyzrQLAAAArcrljULRkvWLFLGlph/8O9MuAAAAtCDXNwpFSxJcomNLS5Mfpl0AAACoTa1TLFOu0ev6/wb+32o1OnS/lMflcvmfiRcAAAAKcy3obcKp/DSNz9fafreoyRbTHb8/mN0Wo9Z/HgAAAOTt2uAUy5SwthERW8LHbSrw1fvAmnQBAAAgJ6W/tjmlkJ/J3thS45uHIr1PuwAAAMBZ69PvSl7bnNrun8+e2CK0rONAXQAAAI5mimW93b1jT2wRWrbx+mgAAABSen9tM+vtCi5bY4vQEsOBugAAAERx4G2s69YhiS2xxehRvP6Bun62AAAArGGrUDpfW36ma2OLX1xathgBAACwhANvj7O6hayJLV7xfCwH6gIAAPDuZmfEKVb9vJfGFm8eOk9/z51pFwAAgDb1D7wVWc6xeIJoSWwRWvJwfTtQFwAAgLrZKpSXxX1kSWwRWvJjixEAAEC9bBXK16LgMhdbhJa82WIEAABQD1uFyjB7pu1UbFHQymGLEQAAQJlsFSrTZDMZiy03oaVY/ejidwgAAJAnW4XKN/q7G4otVzWtCl++uAAAANmxVagug/3kPbZ481B9+iNpthgBAAAc7/oWWajHYEd5jy1+6XVzrgsAAMBx+i81sd6u11/BpR9bbDdph1dHAwAApOM8lvb88YaiLrb4ALTJq6MBAADiOI+lbf+2lf8seT801fPqaAAAgG28upm+f7YTjb36mXaJLgAAAPOutgox4PHzefjP6z/5uFwun6//HC7OdQEAABjkPBaG3F9d5Z+20p9sefT/G350vKi1AAAAzmNh2P01wHLrt5ShbURddPl4/S/B5W0foi1GAABAC65vkQUur27Sjyx/mTuz5Sa6MMC5LgAAQM36E/4iC53+jqDJ9fDSA3L70cUWIzr96GKMDgAAKJ1jFBiy+tiVtW8jujnXhQFfLkgAAEDBbl4QwoD71hcKbX31s8N0GaICAwAAJXEeC0Mmz2NZYmts6ThMlyEO0wUAAHLl0FvG7I4snb2xpc9hugxxmC4AAJADh94ypD9EErZujYwtHdGFIaILAABwBscdMCTp8SgpYkvntvUgGar25eApAADgADeRhQGHnEGbMrZ0HKbLEHUZAABIoX8ei7UGnc1vFtriiNjSEV0YIroAAAARHHrLkLBDb9c4MrZ0RBeG9KOLc10AAIClRBaGnBJZOmfElo7XRjPk6jBdAABgRvewVmTh3amRpXNmbOnzBiOGiC4AAECfYwgYkuT1zXvkEls6ogtDRBcAAGibyMKQbI8pyS22dEQXhvSjiwssAADUT2RhSPZnweYaWzqiC0O+XHABAKBqN/f8DCjmhTu5x5ZOP7p4gxEdlRsAAOrSf7OQe3w6xb3VuJTY0rl5bTQDRBcAACib1zczpLjI0ikttnSK/YGTlOgCAABlEVkY0r2+udg1f6mxpSO6MER0AQCAvIksDLnn9PrmPUqPLR3RhSH96OK10QAAcD6RhSHVRJZOLbGlI7ow5Pr22mgAAOBYIgtDqossndpiS0d0YYzoAgAAx7iKLIyoNrJ0ao0tHdGFMaILAACk0d/OL7LQXnwMnwAAIABJREFUV31k6dQeWzqiC2NEFwAAiOFFFYxpJrJ0WoktHdGFMaILAABsI7IwprnI0mkttnREF8aILgAAsIzIwphmI0un1djSEV0YI7oAAMAwkYUxzUeWTuuxpSO6MEZ0AQCAX0QWxogsb8SWP4kujBFdAABolcjCGJFlhNgyTHRhjOgCAEArRBbGiCwzxJZpogtjRBcAAGolsjBGZFlIbFlGdGGM6AIAQC1EFsaILCuJLeuILowRXQAAKJXIwpiHyLKN2LKN6MIY0QUAgFKILIzpr3nZQGzZxweQMaILAAC5ElkYY7AgiNgSoxututfwb4ZQogsAALkQWRgjsgQTW2LdRBdGiC4AAJxFZGGMyJKI2JKG6MIY0QUAgKOILIwRWRITW9ISXRgjugAAkNJNZGGAyHIQseUYogtjRBcAACLdXveXX36qvLmLLMcRW4518+FmxJcnDwAA7CCyMOb+evjvAe+BxJbjGdtijD21AACsJbIwRmQ5kdhyHtGFMaILAABzRBbGiCwZEFvOJ7owRnQBAOCdyMKYh8iSD7ElH110cYgu70QXAABEFsb0H+CTCbElP95cxBjRBQCgPd09oMjCO7skMia25Et0YUw/ugAAUCcP2hgjshRAbMlfF118iXh3fY2S2pMJAFAPkYUpd5GlDGJLOZRLxnyJLgAAxRNZmOINQ4URW8piXIwpogsAQHlEFqaILIUSW8rktGmmiC4AAGW4iSyM6NZ87ukLJbaU7eEQXSZ8+eMNAJAlr3FmjN0MlRBb6uDNRYwxlgoAkA+RhTEiS2XElrp4cxFjRBcAgPOILEzxhqEKiS11UkQZ048uAACk1d17iSwMcfhtxcSWehlDY8rVIboAAMmYKmbKQ2Spn9hSvy66OM+FId5cBAAQR2RhirfKNkRsaYdDdJkiugAA7COyMMaugwaJLe1xiC5TvC4aAGCd7vBb908Mcfhto8SWdimrjDH+CgAwzxuGmOLw28aJLW2zZ5ApogsAwN+uIgsTujWWyNI4sYVL7zRs57kwpIsu/mAAAC3rP4iCd85l4Q9iC33Oc2GKQ3QBgBaZ9mWOc1n4i9jCEEWWKaILANCKm8jCBOeyMEpsYYzzXJjjzUWQD3G8Ln6fcD6H3zLFuSzMEluY4zwXphirhTyI43V42M4LpxNZmOJcFhb7eD6fflqsYVHNlIcFH5zq+loguE6Xx/UTzuX6yZy7SRbWMNnCWkouU67Oc4FTPVyji2QyCc5jSpc5zmVhE5Mt7HH16jtmeAIA53GNzt/jdZ0Ux+AcAgtTXKPZxWQLezjPhTkO0YXzmHLJm98PnKc7l8X9CUOcy0IIky1EsqhmiqcDcB7X57y4gYdzmPhjjqlswphsIZICzJTuBscfMDjepynELJhmgXP0z2WBIQ/nshDNZAup3LwyjxmeHMDxPNU9j7cNwfG8YYg5Jq9JRmwhNaPrzPGUF47n2nwscRmO58Efc9yDkpRtRKRmaxFzvG4Rjmdb0XE+hRY4VHf4rdDCmO5VztYnJGWyhSMZX2eOMXs4lutyOq5ncCxbhphjyxCHMtnCkbwqmjnX19MoT4HhGA9P95K4Cy1wmKspWRYwbc/hTLZwFk8fWMIfRTiO8w1iuG7BcVy3mOPMLE4jtnA2I+zMMfIJx3FN3s62ITiOyMIc94+czjYizmZrEXP648FAWg+TGZvYNgTH6O4JhBam2DJEFky2kBNbi1jCOCgcw/kHy7ihh/TcI7KEe0SyIraQI2PsLGGBA+m5Ho8zog7HsGWIOa7HZMk2InJkaxFLePMApGdb0TA/F0jv9npDodDCFFuGyJbJFnJnbJQljI1CeuLmL27qIS33fizh3o/siS2Uwig7S/jDC2m1fC02pg7pibrMcS2mGGILpbFvlzn+CENaLQYXr3WGtNzfsYTJQooitlAi46UsYXEEabXyBNrNPaRjcpklTC5TJAfkUqJuEe0AXaZcXwfr+eMMabRwHRZaII1r76B7GNPd87uXo0gmW6iB/b0sYdEEadT4ZNpkHKRjyxBLmGaheCZbqIFXvrGEV0VDGt3r+mu5Bt+FFkji6lXOLND9TRFaKJ7YQi1sLWKJ7gm8P+AQr4ZrsHF1iGfLEEs8eg9QoQq2EVEjB+iylIkoiFfitiLbhiANE6UsYcsQVTLZQo2UcZaytQjilbatyLYhiHd7bRny95UpDsClaiZbaIHFNEt4sg3xcr/+mm6DWKaLWcr1l+qZbKEFDtBlCa+Khni5ThnWdqgv5MC0KEu4/tIMsYVWOECXpb7cLEKoR2bB2xQbxLJliCVs86c5thHRIiOuLGVRBrHODpmmHCGO+ymWcgAuTRJbaFmJb8zgHG4SIM4Z197H63sstEAME6As4dpL08QWcMPAMm4YIM6RT8RNqEGc2+u7C3NMEtI8sQV+MQrLUhZuECd17HazDzHcJ7GU+yR4cUAu/OIAXZby1iKIk+qwxNwO5YWSecsQSzkAF3rEFvjTzQ06C3lrEcSIfgoqtEAMbxliqbvXOcPfbCOCcfYls5QDdCHG3oApssB+tgyxlPPsYILYAvNML7CUhR7stzV0+/7Bfu55WMqDJpghtsAyXhPNUp7ywH5rrrkOY4T93OewlPscWMiZLbDMw15UFupuWD3tge2WXnPvQgvscu0dgAtzPk0RwnImW2A9T39Yw00J7DO2rch3C/ZxNh1LmSCEDcQW2M5NCku5SYF9+pHb9wn28dCINYRt2EhsgX2c2M8aDpOD7a6vf3yHYDsH4LKUsA07iS0Qw5QLSzlYDoCjuU9hDdMsEEBsgTimXFjDEyMAUnNvwhomcCGQ2ALxPD1iDU+PAEjBliGWMnULCXj1M8S7WUCzwrcbYgAC/fw9efq7wkJ3962QhtgCaXRbRO5+vizQvRnC6C4AW117AR/mdPeq7j0gEduIID37pVnDKC8Aa9nCzBrOZoEDiC1wHDdCrOEAXQDmeKDDGh7owIHEFjiWmyLWso8agCHO+2IN0yxwMLEFznG1p5oVPIkCoOMegjXcQ8BJHJAL5/j5g/fhDx8LOUAXAAfgspY3DcGJTLbA+TyhYg1PqADa49w31nCvABkQWyAf9l6zhgN0AernrDfWcjYLZMI2IsjH5+sPJCzxc+P9dAMOUK2bBzGs0D2EEVogEyZbID+eYrGWKReAethezFqmWSBDJlsgPw9TLqzUTbm40QIomwNwWcM0C2TMZAvkzZQLazkUD6A8pllYyzQLZM5kC+TNlAtreU00QDm8zpm1TLNAIUy2QDlMubCWKReAfHmdM2uZZoGCiC1QHjdnrOXmDCAfHp6wxaeHJ1AWsQXK5EaNLdyoAZzLAxPW8sZBKJTYAmVz08ZabtoAjuchCVt4SAIFE1ugfN5gwBZu4ACO8S2ysJIHI1ABbyOC8v38Qf6wcGYlb78ASOsnsDyFFlb6FFqgDmIL1MMfZ9ayEABIQ9BmLQ/PoDJiC9TFH2q2+DbmDhBCxGaLuwdmUB9ntkC9nOXCFl4TDbCNaM1aj9ffXQ/JoEImW6BeD4egssGXBQPAKqZZ2OLuPg3qZrIF2uAV0WxhygVgmjjNWqZZoBFiC7Tj+goubgpZw00hwN88xGALr3SGhogt0B43iGxhygXAgwu2s2UIGiO2QJscnssWplyAlnlYwRamWaBRYgu0zV5ztjDlArTENAtbmWaBhnkbEbTt09MWNvDGIqAVN9c7NvBGSEBsAdwQsEm3Fc2EC1Cj7hpn2xBreaUz8A/biIA++9HZwlkuQE38LWQrkQX4l9gCvLM3na2c5QKUzN8/tnIILvAXsQUYY486W5hyAUpkmoWtTLMAg5zZAoxxeC5bOMsFKImzWdjKmXfAJJMtwBKmXNjClAuQM9MsbGXbLDBLbAGWclPKVm5KgZw4m4U9TLMAi4gtwBrduDWsZcoFyIEHB2zlEFxgFbEF2MK2IrYy5QKcxd8utjLNAqzmgFxgC4fnstWXBQ9wsJ/rzdN1hw0cggtsJrYAW7kBYStvLAKO8m37Kxvd3ecAe9hGBESwB56tnOUCpOCMMfYQWYDdxBYgihtb9nBjC0SxVZGtHIILhLGNCIjyc4PyYcHMRkb9gb2czcIed6EFiCS2ANE+XzcssJaFErCVYMtW3TSLc8SAULYRAanYVsQeRrmBJfytYQ9/a4BkTLYAqdhWxB6mXIA5N6GFHT6FFiAlsQVIzc0Me9gaALzrplm8BY8tHg5lB44gtgBHcGPDHldvFwFebq4H7OB+BDiMM1uAo7lJZo+7QwyhSdfXJIu/H2wlsgCHEluAMzjQkD0er+jiphna4G8Ge/ibAZzCNiLgDMZ42aNbeJlwgfo5t4k97u43gLOYbAHOZlsRe3htJ9TJNAt7iSzAqUy2AGfztiL28IpoqI9pFvb4CSwfQgtwNrEFyIFtRexlcQblE0/Z6+4BDpAL24iA3NhWxF7CHZTn9nrbEGzl2g9kxWQLkBvbitjL4blQju5sFqGFrWwbArIktgA5sq2Ivb5MSUH2br6n7GTbEJAt24iA3LkRZ6+7SRfIjms7e3koA2TNZAuQO9uK2OvL4bmQDYfgspfpV6AIJluAUlxfi2Y36OzhBh3OY5qFvR4ewAClEFuA0rhZZy8363AssZwIYjlQFNuIgNLYVsReV9EODuMQXPaybQgokskWoFSelBLB4bmQjsjCXiYRgWKJLUDp3Myz1+MVXTw1hRhXh1ITwDQLUDTbiIDSfb4WyrCVbUUQ51toYSfbhoAqmGwBauFJKhGMrMM2tnYSwTUYqIbJFqAWPzdoH56EsdPPQvFpwQirOASXCHehBaiJ2ALUxrYiInw7OBcW+X5NtMAen665QG1sIwJqZVsREYy0wzDXWCK4xgLVMtkC1MoBe0SwrQj+5hBcIggtQNVMtgAtcJYAESwMaJ1DcIniYQhQPZMtQAs+LZIJ4BXRtMwhuEQwdQo0w2QL0BJnDBDl7jBHGiKyEMF0INAUky1AS7wemihfwh0NcGYRUbzWGWiO2AK0yOuhiWAhSs1ugiJBvNYZaJJtREDLbCsiim1F1MIhuESxbQhomtgCtM7CgiiPV3SxTY1SCdBEEVqA5oktAL84AJIo3rRBiVwDieIaCDTv4swWgH85x4Uo36YDKIhXmhNJaAF4MdkC8Cdj9ESxrYjcud4RxbYhgDdiC8DfnONCJE96yZFpFqIILQADxBaAcRYjRLEYIRemWYgkJgOMcGYLwDjnuBDFuRjk4Ca0EEhoAZhgsgVgnifBRLJA4QxiH1FM6gEsYLIFYN7DAplA3lbEkX4Cy1NoIYjQArCQyRaAdTwdJpKIR0q312HfEMH1CmAFky0A6zjHhUjfrwUxRPsWWggktACsZLIFYBvnuBDJaD5RXJuI5NoEsJHJFoBtnONCJOdqEMHbhogktADsYLIFYD/nuBDpbmsRG7gOEcnDBICdTLYA7OccFyJ9mU5gBVNRRBNaAAKILQAxbsatCWQBzRK2DRHpJ7B8CC0AMcQWgDj2txPN24oY421DRPL3CyCY2AIQy5NBotlWRJ+pJ6LdhRaAeGILQBr2vBPJApuLbUMk8Gl6DiANsQUgnU9PCwnmjTPtsm2ISA8PBQDS8upngPSunkYTzPkK7XD9IJrrB8ABTLYApOcJItGuplyaYNsQ0YQWgIOYbAE4lgUy0YS8OrlWEM21AuBAJlsAjvX5evMDRPk2/VAVU0ukILQAHMxkC8A5nMNAtMcr5FlQlct1gWiuCwAnEVsAzmNhRQqeYJfJNAvRnM8CcCLbiADO83Mj/GFhTDDbispi2xApCC0AJzPZApAHiy2iWWzlz3QbKZhuA8iAyRaAPDg4l2g/C/mniJctr3UmBaEFIBMmWwDy4kk3Kdxfi3vyYJKNaA7CBciM2AKQH8GFFGwrOp/vNin4bgNkyDYigPw8jIKTgINYz2XbECkILQCZMtkCkDeLY1IQ847le0wKvscAGTPZApA3N9Ok4PXQxzBNRCr+NgBkzmQLQBmc9UAKtiCk4ztLCg7CBSiE2AJQDos3UvGUPJZpFlIQRwEKYhsRQDncaJPKt1dDhxFaSMH1H6AwYgtAWX5uuD9MIZDAl8mpXX4Cy1NoIYG70AJQHrEFoEy2fZCCA123scWPVD5NnQGUyZktAGWzMCYVQW8Z30FS8R0EKJjJFoCyfRovJxGvh55mCohUHkILQPlMtgDUwTYGUnEw599830jF9w2gEiZbAOrgBp1UHPz6p5vQQiKu4wAVEVsA6uFNRaRky8yvn8FXBv86qI83DgFUxjYigDpZGJNKi0/fr6/I4jtFCs5nAaiQ2AJQL8GFVFoKLs5nISWhBaBSthEB1MubikillXNcnM9CSkILQMVMtgDUz5N5Uqp1wWgyjFQchAvQAJMtAPVzY09K35XFvKvQQkKuxwCNEFsA2vAwsk5CtUxPCS2kJLQANMQ2IoD2WEySUqlR7+a1ziQkdgM0RmwBaJPgQkqlLSx9H0hJaAFokG1EAG1y809KpZzjYtsQqbnWAjTKZAtA27ypiJRyPqPCZ5/UhBaAhplsAWibAxtJ6SdoPDOcHLkJLST0c139EFoA2mayBYCLp/wcIJen/LYNkZKADcA/TLYAcLFA4ABnn+PifBZScx0F4F9iCwAdo++kdtYEldBCanehBYA+sQWAdw51JKWjw4ctcqT2+ToHCAD+5cwWAMaYBCC11GHPZ5jUxGkABplsAWCMRQSpfSecCBBaSM01EoBRJlsAmGMbBqlFHizq88oRhBYAJplsAWCON2yQWlQgEVpIzUHiACwitgCwhOBCaj+h5Llj689NaCEx10EAFrONCIA1TA5whLVbNJzPQmpCCwCrmGwBYA0j9Bzhe2HUO/o10rRJaAFgNbEFgC0cDklqc1NUQgtHuAstAGwhtgCwleBCamNBxXY2jvCZ8NXkAFTOmS0A7GW6gCN0cc/njSOIyQDsIrYAEMECmCM8fM44gNACwG5iCwBRbO0ASie0ABDCmS0ARPHGDqBU3rQGQCixBYBIggtQGtctAMKJLQBEs3ABSuF6BUASYgsAKVjAALlznQIgGbEFgFQeDpsEMiW0AJCU2AJASoILkJu70AJAal79DMBRvl+vhwY4i/gLwCFMtgBwFIsc4EyuQQAcRmwB4EgWO8AZXHsAOJTYAsDRLHqAI7nmAHA4sQWAM3y+DqkESEloAeAUDsgF4EzX18G5ANGEFgBOY7IFgDM9vIIVCPZzXfkQWgA4k9gCwNkEFyCK6wkAWRBbAMiBBRKwl+sIANkQWwDIhYUSsJXrBwBZEVsAyIkFE7CW6wYA2RFbAMiNhROwlOsFAFkSWwDIkbeJAHOEFgCyJbYAkLNPwQUYILQAkDWxBYDcCS5A36fQAkDuxBYASiC4ABfXAgBKIbYAUAqLLGibawAAxRBbACiJxRa0yXcfgKKILQCUxqIL2uI7D0BxxBYASmTxBW3wXQegSGILAKWyCIO6+Y4DUCyxBYCSWYxBnXy3ASia2AJA6X4WZXe/RaiG0AJA8T6ez6ffIgA1uF4ul2+/SSia0AJAFUy2AFCLx2uhBpRJaAGgGmILADURXKBMQgsAVRFbAKiN4ALl+Pm+fggtANRGbAGgRoIL5M/3FIBqiS0A1MpCDvLl+wlA1cQWAGpmQQf58b0EoHpiCwC1s7CDfPg+AtAEsQWAFljgwfl8DwFohtgCQCss9OA8vn8ANEVsAaAlFnxwPN87AJojtgDQGgs/OI7vGwBNElsAaJEFIKTnewZAs8QWAFplIQjp+H4B0DSxBYCWWRBCPN8rAJontgDQOgtDiOP7BEDzLmILAPzDAhH28z0CgBexBQB+sVCE7Xx/AKBHbAGA3ywYYT3fGwB4I7YAwJ8sHGE53xcAGCC2AMDfLCBhnu8JAIwQWwBgmIUkjPP9AIAJYgsAjLOghL/5XgDADLEFAKZZWMJvvg8AsIDYAgDzLDDB9wAAFhNbAGAZC01a5vMPACuILQCwnAUnLfK5B4CVxBYAWMfCk5b4vAPABmILAKxnAUoLfM4BYCOxBQC2sRClZj7fALCD2AIA21mQUiOfawDYSWwBgH0sTKmJzzMABBBbAGA/C1Rq4HMMAEHEFgCIYaFKyXx+ASCQ2AIAcSxYKZHPLQAEE1sAIJaFKyXxeQWABMQWAIhnAUsp7n5TABBPbAGANAQXcvf5+pwCAMHEFgBIR3AhV0ILACQktgBAWg9bNciM0AIAiX08n08/YwBIzx9ccvAT/m5+EwCQlskWAEjv28+YTHxdLperXwYApCW2AEBa3xa3ZMZnEgASE1sAIB2LWnLlswkACYktAJCGxSy58xkFgETEFgCIZxFLKXxWASABsQUAYlm8UppvbygCgFhe/QwAcYQWSva4XC6ffoMAsJ/JFgDY7yq0UIGr15QDQAyTLQCwjwUqtTHhAgA7mWwBgO2EFmrkcw0AO4ktALCNBSk18/kGgB3EFgBYz0KUFvicA8BGYgsArGMBSkt83gFgA7EFAJaz8KRFPvcAsJLYAgDLWHDSMp9/AFhBbAGAeRaa8Ot78Hz9RwBggtgCANOEFvjTt+ACANPEFgAYdxNaYJDgAgATPp7Pp58PAPzNYhLmfV4ul4efEwD8yWQLAPxNaIFlfFcAYIDYAgB/sniEdXxnAOCN2AIAv1k0wja+OwDQI7YAwC8Wi7CP7xAAvIgtAGCRCFF8lwBo3kVsAQCLQwjmOwVA88QWAFpmUQhp+G4B0DSxBYBWWQxCWr5jADRLbAGgRRaBcAzfNQCaJLYA0BqLPziW7xwAzRFbAGiJRR+cw3cPgKaILQC0wmIPzvX9+gcAqie2ANACoQXycBVcAGiB2AJA7YQWyIvgAkD1xBYAaia0QJ4EFwCqJrYAUCuhBfImuABQLbEFgBoJLVAGwQWAKoktANRGaIGyCC4AVEdsAaAWPwu2p9ACRRJcAKiK2AJADSzUoHy+xwBUQ2wBoHQWaFAP32cAqiC2AFAyCzOoj+81AMUTWwAolQUZ1Mv3G4CiiS0AlMhCDOrnew5AscQWAEpjAQbt8H0HoEhiCwAlsfCC9vjeA1AcsQWAUlhwQbt8/wEoitgCQAkstADXAQCKIbYAkDsLLKDjegBAEcQWAHJmYQW8c10AIHtiCwC5sqACxrg+AJA1sQWAXH35zQATBBcAsiW2AJCj79dCCmCK4AJAlsQWAHIjtABrCC4AZEdsASAnQguwheACQFbEFgByIbQAewguAGRDbAEgB0ILEEFwASALYgsAZxNagEiCCwCnE1sAOJPQAqTwc125+ckCcJaP5/Pphw/AGYQWILXPy+Xy8FMG4GgmWwA4g9ACHMG1BoBTiC0AHM3iBziSaw4AhxNbADiSRQ9wBtceAA4ltgBwFIsd4EyuQQAcRmwB4AgWOUAOXIsAOITYAkBqFjdATlyTAEhObAEgJYsaIEeuTQAkJbYAkIrFDJAz1ygAkhFbAEjBIgYogWsVAEmILQBEs3gBSuKaBUA4sQWASBYtQIlcuwAIJbYAEOVqsQIUTHABIIzYAkCE62uhAlAywQWAEGILAHsJLUBNBBcAdhNbANhDaAFq9OW3CsAeYgsAWwktQK1c3wDYRWwBYAsLEaB2rnMAbCa2ALCWBQjQCtc7ADYRWwBYw8IDaI3rHgCriS0ALGXBAbTK9Q+AVcQWAJaw0ABa5zoIwGJiCwBzLDAAfnE9BGARsQWAOV9+QgD/ElwAmCW2ADDl+7WwAOA3wQWASWILAGOEFoBxV9dIAMaILQAMEVoA5rlWAjBIbAHgncUDwHKumQD8RWwBoM+iAWA9104A/iC2ANCxWADYzjUUgH+JLQBcLBIAQriWAvAPsQUAb9QAiCO4ACC2ADTu+loYQAkefksUQnABaJzYAtAuoYWS/ISWT78xCvLllwXQLrEFoE1CCyXphxbBhVK4zgI07OP5fPr9A7TFAoDSfLz96/UZpiSmsgAaZLIFoC0WqZRmaJH6s3i9+01SCNddgAaZbAFohxt+SvM5cyiuQ0gpiQkXgIaYbAFoh8MaKcl9wduH5mIM5ETwBmiI2ALQBhMAlOQnoNwW/uu1nYiSXFd8tgEomG1EAPUTWijJlq0WJgYojaksgMqZbAGom9BCabZMqjgwl9K4NgNUzmQLQL3czFOavU/7feYpjQkXgEqZbAGok0UnpVlyIO4cC1dK41oNUCmxBaA+NzfvFGbNgbhzbCeiNIILQIVsIwKoi4NCKc2WA3Hn+B5Qog+/NYB6mGwBqMf/t3cv2G0bMRRArZ0pKxO1My6tJ42TxrUsUxI/g4d7V9CG1nDmEcA4YFLRFpUoBuZSkfUbIIjKFoAMghYq2nrGivYMqtmi0guAA6hsAahP0EJFawzE/Y6BuVRjPQcIobIFoDYbcyra8+u93wgVqXABKE5lC0BtF8+PYvY+RDq0UpGQEKA4YQtAXeZRUNERg2sNzKWiszUeoC5tRAA1CVqo6OgZKn43VGT2EEBBKlsA6nFgpKI9BuJ+x6GViqz5AAWpbAGoxaabikaam2IWBlUJCwEKEbYA1OGQSFWnwf67/ZaoSuACUIQ2IoAaHA6pasSbgGYHVoryHgAoQtgCMD5BC1WN/BVehQBVeR8AFKCNCGBsghaqGmlOyz02QlRU5fcF0JbKFoBxCVqoqtJB0IGVirwfAAYnbAEY18Wzoahrof9sFQJUJXABGJiwBWBMrnimqoqzUAzMpaqzdwXAmIQtAOMRtFDVtXBoYWAuVXlnAAzIgFyAsdg0U1VCO462DCoTGAIMRGULwDgmQQuFJcw9Mb+FyoT1AANR2QIwBl/UqSzti7pDK5WdPD2A46lsATieoIXKElsXtGNQmfcJwABUtgAcS9BCZeltNzZJVKUlDuBgKlsAjiNoobIOhzmHVaryfgE4mLAF4DgX//YUdm3w8OYm/59kErgAHEgbEcAxDODXo5xsAAAI80lEQVSksm4zTfxeqcwMIoADCFsA9ufgRmVdZ0HYMFGZwAVgZ9qIAPYlaKGyzkM3zW+hMu8egJ0JWwD2M9nsUlznwMHtLlQncAHYkbAFYB9nA3EpTtDwK3DRikFl3kMAOxG2AGzPjRBUdxUy/GH2BZV5HwHsxIBcgG3Z2FKd9pnP/K6pzu8aYGPCFoBtWWSp7uQJ3iRwoTqBC8CGtBEBbMdBjOocxL5mfgvVnQ3MBdiOsAVgG259oDpzWr5nfgvVeVcBbEQbEcD6bF6pTnvBctqJSCA4BFiZsAVgXQ5eJDCn5TF+9yQQuACsSBsRwHocuEigouVx5reQ4OIpAqxH2AKwDkELCcxpeZ6qAKrzHgNYkTYigNfZoJLAnJbXWQtIYC0AWIHKFoDXKb0mgcPV6xxSSfAzNJw8SYDXCFsAXuPmIRIICNZjfgsJLt5tAK8RtgA8T9BCAnNa1md+Cwm84wBeIGwBeM5kE0qAWbvAZq6h/1/0InABeJIBuQCPMwSTFCdPclPWChKYRQTwBJUtAI9xeCKFw9P2zG8hgfcewBOELQDL2XCSwpyW/ZjfQgLvP4AHCVsAlnPFMwnMadmf+S0kcCU0wAPMbAFYxpBAUpjTcoxJYEsI1VoAC6hsAfieoIUU5rQcZ3JAJYR3IsACwhaA+1zxTArDWo8n7CKFwAXgG8IWgK+dlf0TwtWt4/AcSOH9CHCHsAXgNjcvkMSA1nHMngchvCcB7jAgF+A2iyMpDLMckzYMUqicA7hBZQvAZ77UkcKclnE5nJLCldAANwhbAD7ytZkUvjaPz/MhxcW7E+AjYQvAfwQtJDEXZHwqj0jiHQrwF2ELwC9nm0SCXB3iyzBThyTacAHeCVsA3KhAltn8hHJUIZHE+xRo781tRACCFuKcPNKSrEUkMTMKaE9lC9Ddpfs/AFEcbuoyv4UkbigC2lPZAnRmmB9JfEnOYGNGEjOJgLZUtgBdCVpIImjJ4TmSxLsWaEvYAnTk5iHSGLCaY/Y8CWMWEdCSsAXoxhBK0rjmOc/kmRLGexdox8wWoBNBC2m0D+WyXpHGegW0orIF6MTNQ6RxcMnlYEoaNxQBrQhbgC4M6SONg3g+10GT5uJdDHShjQjoQNBCGlUPvdiskcaV0EA8lS1AOjcPkUbQ0o/nTRrziIB4whYgmQGTJHItcD+ugyaR9zMQTRsRkMwCR5qrAZOtaYkkjUo9IJbKFiCVL2akmQUt7aluIY0bioBYKluARL7+kujkqaI9klAG5gJxVLYAaSZBC4GU2fOb66BJ5CMJEEdlC5DEF18SmWnALTZwpLHWAVFUtgApBC0kcvjgK/4uSOM9DkRR2QKksJiRyBwD7tF6QSIhMxBBZQuQwJcwEl0FLXxDGEeisxARSKCyBajOl10S+bLLUlovSCVMBEpT2QJU5usXqa6eLAvN/l4IJUQEShO2AFX5mksq7UM8avI3QyjveaAsbURAVRYvEmkf4lkCaFJZF4GSVLYAFTlQkMqBgmdpJyLV+b16C6AUlS1ANQbiksowSNZgjSSVNRIoRWULUMnkEEGo2SGClaiOIpUgEShFZQtQhXkEJDt5uqzIekkq81uAMlS2ABU4OJDMwYG1qZQilf0AUIawBajg4ikRyqGYrQjxSGVgLlCCsAUYnR5tUimHZ2v+vkh1sTcARidsAUZmIC7JXNPL1lROkczHGGBowhZgVGftQwS7OgSzE9flksw+ARiW24iAERmARzLtQ+zNmkoyayowJJUtwIh8qSKZ9iH2Nvu7I5iBucCQVLYAo9GDTbKrQwEHsr6STMscMBSVLcBIDMQl2Sxo4WCqW0gmTASGImwBRmEgLukcdDmadiLS2UcAwxC2ACMwvJF0bh9iFJO/RYLZTwDDELYAI/AlimTahxiN6haSGZgLDEHYAhxNjzXpHGwZjXYi0l3sLYCjCVuAIxmISzrtQ4xKOxHpfMwBDuXqZ+Ao+qpJN79fRQqjsg6TzjoMHEbYAhzF4kO6kydMAZO5WYQTuACH0EYEHMGXVNLZ2FOFdiLSnbUTAUcQtgB700NNutnhlWIMyyWdvQewO21EwJ7MB6AD7UNUpJ2IDqzPwG5UtgB7EbTQgfYhqtJORAf2IcBuVLYAe7HYkM4QRqoTitOBtRrYhcoWYA8273Rg8051s/ktNGBgLrALYQuwtcmmhgYELaTQTkQHBuYCm9NGBGxJSTodKEknjbWbDqzdwKZUtgBbsVmnC5t10mgnogP7FGBTwhZgK64QpQMHUlJpJ6KD8/vfOsDqtBEBW9ALTQdK0Ennyz9d/BAuAmtT2QKszZR/ulDVQjrtRHQhVARWJ2wB1uQrKF1cfQWlCe1EdGH/AqxKGxGwJgsKHWgfohtBOl1Y34HVqGwB1mIjThfaKuhGOxFdaIUGViNsAdYw2ZzQhPYhutJORBeG/AOr0EYEvEp5OV0oL6c76z1dWO+Bl6lsAV5l400X2ijoTjsRXQgWgZcJW4BX2IjQhfYh+GXy70AT5rcALxG2AM/S00wXswMmfKC9gi7sdYCnmdkCPEN5LZ38UNUCnziE0snJ0wYepbIFeJSghU60D8FtqlvoxL4HeJiwBXjUxb8YjWgfgq8JXOji7H0APErYAjxC2TidOEjCfbPKLxq52AMBjzCzBVhK+xCdzMIWWMxmkk7MbwEWUdkCLCFooRtBCyzn90In9kPAIsIWYAlzWujEwREeo52ITsxvARbRRgR8x5wWOtE+BM9RAUk3P4SMwD0qW4B7JkELzQha4Dnz+1Xp0IVwEbhL2AJ85ax9iGYcFOE1ky/9NCNwAb6kjQj4isWBTrQPwTq0E9GN9wdwk8oW4BYbZbpR1QLr0E5EN2ct18Atwhbg/8xpoZur1gdYlZta6MZlAsAn2oiAvyn/pqOTpw6r8z6hG+1EwAcqW4C/2RjTjY0xbGNWMUYzAkbgA2EL8JsNAt04DMK2hJl0Y34L8IewBXgzp4WmHARhe35ndGN+C/AvYQvwc0Nwaf+vQDcOgLAPFWR0ZF8FCFsA7UO04/AH+3IVNN2c3coFzb29vf0DNBcwLKdP2XMAAAAASUVORK5CYII="/>
												  	</pattern>
												</defs>
												<g id="Green-logo" transform="translate(-1072 -1683)">
												  	<g transform="matrix(1, 0, 0, 1, 1072, 1683)" filter="url(#Ellipse_174)">
														<g id="Ellipse_174-2" data-name="Ellipse 174" transform="translate(9 6)" fill="#006f44" stroke="#006f44" stroke-width="1">
													  		<circle cx="17.5" cy="17.5" r="17.5" stroke="none"/>
													  		<circle cx="17.5" cy="17.5" r="17" fill="none"/>
														</g>
												  	</g>
												  	<rect id="in-logo-w" width="13" height="21" transform="translate(1092 1696)" fill="url(#pattern)"/>
												</g>
											</svg>
										</div> 
										<div class="comment_misa_message_word">
											<h6 class="misateam">
												<span class="green_team"><spring:theme code="my.potential.opportunity.misa.sales"/></span>											
												<b class="pl-2"><spring:theme code="my.potential.opportunity.commented"/>&nbsp;<fmt:formatDate type="date" value="${comment.startDateTime}" /></b>
											</h6>
											<p class="text_message">
												<c:set var="misaComment" value="${comment.text}"/> 
												<!-- Misa Comment ->   -->${misaComment} 
											</p>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						
						<c:if test="${comment.interventionType eq 'TicketMessage'}">
							<div class="customerComment"> 
								<div class="row"> 
									<div class="col-12 d-inline"> 
										<div class="comment_misa_message_word">
											<h6 class="misateam">
												<span class="gold_team"><spring:theme code="my.potential.opportunity.you.commented"/></span>												 
												<b class="pl-2"><fmt:formatDate type="date" value="${comment.startDateTime}" /></b>
											</h6>
											<p class="text_message"> 
												<c:set var="customerComment" value="${comment.text}"/>
												<!-- Customer Comment -> --> ${customerComment}
											</p>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container service-wrapper service-wrapper-info  pt-5 pb-5 mb-5">
	<div class="serviceModule serviceModule_list mx-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="my.potential.opportunity.opportunity.overview"/></span>
					<c:choose>
						<c:when test="${empty opportunityDetails.description}">
							<div class="serviceModule-detail serviceList-description">
								<div class="w-75"><p>NA</p></div></div>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description">
								<div class="w-75"><p>${opportunityDetails.description}</p></div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="my.potential.opportunity.opportunity.highlights"/></span>
					<c:choose>
						<c:when test="${empty opportunityDetails.highlights}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>NA</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description">
								<div class="w-75"><p>${opportunityDetails.highlights}</p></div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div> 
	<div class="serviceModule serviceModule_list mx-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">					  
					<c:forEach var="partnerMap" items="${opportunityDetails.partnerMap}">
						<c:if test="${partnerMap.value.size() gt 0}">							
							<span class="serviceModule-headline">${partnerMap.key}</span>								
							<div class="serviceModule-detail serviceList-description"> 
								<div class="row pt-3">
									<c:forEach var="partnerLogo" items="${partnerMap.value}"> 
										<div class="col-md-3 col-sm-6">
											<div class="mypotentialpp_showdow">
											<img class="sector-item-icon pb-3 img-fluid" src="${fn:escapeXml(partnerLogo.companyLogo.url)}" 
													data-norm="${fn:escapeXml(partnerLogo.companyLogo.url)}" 
													data-alt="${fn:escapeXml(partnerLogo.companyLogo.url)}" alt=""/>
												<a href="${partnerLogo.companyWebsite}" target="_blank">
													<spring:theme code="my.potential.opportunity.know.more"/>
												</a>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>							
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>	
</div>

<script>  
	function validateForm() { 
		var x = document.forms["js-quick-tialoppor_newcomment"]["comment"].value;
		if (x == "") {
            $('.help-block').html("<span class='error'>Please fill out this field</span>"); 
			return false;
		} 
	}  
	$(".js-quick-tialoppor_newcomment").on("keydown", function(e) { 
		$('.help-block').html(""); 
    });
</script>
