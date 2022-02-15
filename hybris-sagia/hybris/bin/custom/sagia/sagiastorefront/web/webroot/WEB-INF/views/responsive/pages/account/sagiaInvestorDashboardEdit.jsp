<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="dashboardBanner" type="java.lang.String"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>
<%--@elvariable id="profilePicture" type="java.lang.String"--%>
<%--@elvariable id="user" type="de.hybris.platform.commercefacades.user.data.CustomerData"--%>

<img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image1.jpg" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                </h1>
            </div>
        </div>
        
<section class="mainSection mainSection_noPadding userDetail-block">
    <div class="container">
        <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder">
            <div class="dashboardUser-wrapper col-md-12 mr-0 pt-3 px-0">
                <div class="dashboardUser-left col-md-6 pr-0">
                    <div class="col">
                        <div class="dashboardUser-image">
                            <div class="dashboardUser-image position-absolute dashboardHeadAdd dashboard-user-add-icon">
                                <button type="button"  id="btnfile" class="dashboardUser-image-add"><icon:plus/><span id="fname"></span></button>
                                
                                <div class="myAccount-profilImage">
                                    <div class="myAccount-profilImage-img">
                                        <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry ml-3">
                                    <h2 class="clr_gld"><c:out value='${user.company}'/></h2>
                                    <span class="last-login"><spring:theme code="dashboard.license.user.lastlogin.title"/> &nbsp;<span class="clr_gld"><fmt:formatDate value="${customerLastLogon}" pattern="dd/MM/yyyy hh:mm a"/></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dashboardUser-right col-md-6 px-0">
                    <div class="col-6">
                        <div class="dashboardUser-col flex-column dashboardUser-col-alignment">
                                <!-- <div class="dashboardUser-label dashboardUser-label-sm"><spring:theme code="general.welcomeback"/></div> -->
                                <div class="dashboardUser-label profile-detail dashboardUser-label-xs"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Profile-name-icon.png"/><span class="dashboardUser-value h5"><c:out value='${user.name}'/></span></div>
                                <div class="dashboardUser-label profile-detail"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-email-icon.png"/><span class="dashboardUser-value"><c:out value='${user.email}'/></span></div>
                                <div class="dashboardUser-label profile-detail"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/profile-mobile-number-icon.png"/><span class="dashboardUser-value"><c:out value='${user.mobileCountryCode}'/>&nbsp;<c:out value='${user.mobileNumber}'/></span></div>
                        </div>
                    </div>
                    <div class="col-6 d-flex justify-content-around">
                        <div class=" user-icon mr-3">
                            <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/> -->
                            <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender-in-active.png"/>
                            </a>
                        </div>
                        <div class=" user-icon mr-3">
                            <!-- <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/> -->
                            <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                                <c:if test="${hasLicense or hasAwaitingPayment}">
                                    <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                        <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                        <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                                    </button>
                                </c:if>
                                <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                            </div>
                        </div>
                        <div class=" user-icon mr-1">
                            <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
                            class="sagiaNavigation-btn sagiaNavigation-user"> <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup">
    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
    <div class="sagiaNavigation-subPane-actions">
        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
    </div>
</div>
<!-- <section class="mainSection mainSection_white mainSection_noPadding">
    <div class="container">
        <div class="dashboardUser dashboardUser_slim dashboardUser_noBorder">
            <div class="dashboardUser-wrapper">
                <div class="dashboardUser-left">
                    <div class="row">
                        <div class="col">
                            <div class="dashboardUser-image">
                                <button class="btn btn_link dashboardUser-image-add">
                                    <icon:plus/>
                                </button>
                                <div class="myAccount-profilImage">
                                    <div class="myAccount-profilImage-img">
                                        <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry">
                                    <div class="dashboardUser-label dashboardUser-label-xs"><spring:theme code="general.company"/></div>
                                    <div class="dashboardUser-value"><c:out value='${user.company}'/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dashboardUser-right">
                    <div class="row">
                        <div class="col">
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry">
                                    <div class="dashboardUser-label dashboardUser-label-sm"><spring:theme code="general.welcomeback"/></div>
                                    <div class="dashboardUser-label dashboardUser-label-xs"><spring:theme code="general.name"/></div>
                                    <div class="dashboardUser-value"><c:out value='${user.name}'/></div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry">
                                    <div class="dashboardUser-label"><spring:theme code="general.email"/></div>
                                    <div class="dashboardUser-value"><c:out value='${user.email}'/></div>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="dashboardUser-col">
                                <div class="dashboardUser-entry">
                                    <div class="dashboardUser-label"><spring:theme code="general.mobilenumber"/></div>
                                    <div class="dashboardUser-value"><c:out value='${user.mobileCountryCode}'/>&nbsp;<c:out value='${user.mobileNumber}'/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section> -->

<div class="globalMessage-holder">
    <div class="container">
        <div class="globalMessage">
            <div class="globalMessage-action">
                <button class="btn btn_round" onclick="location.href='${encodedContextPath}/my-sagia/notifications'">Action</button>
            </div>
            <div class="globalMessage-msg">
                <div class="globalMessage-icon"><img class="Applylicense-icon" src="${commonResourcePath}/images/dashboard-media/Apply-license/Allert-icon.png"/></div>
                <spring:theme code="dashboard.message.text"/>
            </div>
        </div>
    </div>
</div>


<spring:url var="editUrl" value="/dashboard"/>
<section class="mainSection mainSection_grey mainSection_xsmallPaddingTop mainSection_noPaddingBottom">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <a href="${editUrl}" class="btn-outline d-block">
    			<span class="iconElement iconElement_check_green"><icon:check/></span> Done
			</a>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
        <div class="row">
            <ul id="draggableComponentsList" class="dashboardWidgetList dashboardWidgetList_edit">
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="myLicense">
	                <dashboard:myLicense editable="${true}"/>
	            </div>
            </li>
           <!-- <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="dashboardImage">
	                <div class="dashboardWidget dashboardWidget_banner">
	                    <div class="simple-banner banner__component--responsive">
							<a href="#"><picture class="dashboardWidget-pictureSet"></picture></a>
	                    </div>
	                </div>
	            </div>
            </li>-->
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="salaryAndEmployment" <c:if test="${!enableSalaryAndEmployment}">style="display:none;"</c:if>><dashboard:salaryAndEmployment editable="${true}"/></div>
            </li>
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="servicesRequest">
	                <dashboard:servicesRequest editable="${true}" />
	            </div>
            </li>

			<li class="dashboardWidgetListComponent js-component">
				<div class="drag col col-12 p-0" id="savedDrafts">
					<dashboard:savedDrafts editable="${true}" />
				</div>
			</li>

            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="payments">
	                <div class="dashboardWidget js-dashboardWidget">
	                    <dashboard:addAndRemoveComponent checkboxIndex="5"/>
	                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
							<spring:theme code="dashboard.payments"/>
	                        <div class="dashboardWidget-headline-icon">
	                            <icon:payments/>
	                        </div>
	                    </div>
	                    <div class="dashboardWidget-body">
	                        <div class="dashboardWidgetPayments">
	                            <div class="tableModule tableModule_slim tableModule_striped">
	                                <table class="tableModule-table">
	                                    <thead class="tableModule-head">
	                                    <tr>
											<th><spring:theme code="dashboard.payments.name"/></th>
											<th><spring:theme code="dashboard.payments.status"/></th>
	                                        <th class="dashboardWidgetPayments-lastCol">Amount</th>
	                                    </tr>
	                                    </thead>
	                                    <tbody class="tableModule-body" id="paymentsTable"></tbody>
	                                </table>
	                            </div>
								<div class="paginationModule">
                                    <div style="width: 150px; position: absolute">
                                        <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                    </div>
									<div class="paginationModule-wrapper">
										<button class="paginationModule-control paginationModule-control_left" disabled>
											<img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive">
										</button>
										<div class="paginationModule-items"><spring:theme code="text.loadingMessage"/></div>
										<button class="paginationModule-control paginationModule-control_right">
											<img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive" >
										</button>
									</div>
								</div>
	                            <div class="dashboardWidgetPayments-action">
	                                <%--<button class="btn btn_outline btn_bigPadding">--%>
										<%--<spring:theme code="dashboard.viewall"/>--%>
	                                <%--</button>--%>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </li>

            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12 p-0" id="yourTickets">
	                <div class="dashboardWidget js-dashboardWidget" >
	                    <dashboard:addAndRemoveComponent checkboxIndex="6"/>
	                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
							<spring:theme code="dashboard.ticket.yourtickets"/>
	                        <div class="dashboardWidget-headline-icon">
	                            <icon:your-tickets/>
	                        </div>
	                    </div>
	                    <div class="dashboardWidget-body ">
	                        <div class="dashboardWidgetTickets">
	                            <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
	                                <table class="tableModule-table">
	                                    <thead class="tableModule-head">
	                                    <tr>
											<th><spring:theme code="dashboard.ticket.lastupdate"/></th>
											<th><spring:theme code="dashboard.ticket.ticketnumber"/></th>
											<th><spring:theme code="dashboard.ticket.status"/></th>
											<th><spring:theme code="dashboard.ticket.options"/></th>
	                                    </tr>
	                                    </thead>
	                                    <tbody class="tableModule-body" id="ticketsTable"></tbody>
	                                </table>
	                            </div>
								<div class="paginationModule">
									<div class="paginationModule-wrapper">
										<button class="paginationModule-control paginationModule-control_left" disabled>
											<icon:arrow_green_right/>
										</button>
										<div class="paginationModule-items"><spring:theme code="text.loadingMessage"/></div>
										<button class="paginationModule-control paginationModule-control_right">
											<icon:arrow_green_right/>
										</button>
									</div>
								</div>
	                            <%--<div class="dashboardWidgetTickets-action">--%>
	                                <%--<button class="btn btn_outline btn_bigPadding"><spring:theme code="dashboard.viewall"/></button>--%>
	                            <%--</div>--%>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </li>

            <li class="dashboardWidgetListComponent js-component">
                <div class="drag col col-12 p-0" id="support">
            		<dashboard:support editable="${true}"/>
                </div>
            </li>
            </ul>
        </div>
    </div>
</section>

<script type="application/javascript">
    window.variableEditable = true;
</script>
