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

<%--@elvariable id="dashboardBanner" type="java.lang.String"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>
<%--@elvariable id="profilePicture" type="java.lang.String"--%>
<%--@elvariable id="user" type="de.hybris.platform.commercefacades.user.data.CustomerData"--%>

<form:form id="bannerUploadForm" action="dashboard/update-dashboardPic" method="post" enctype="multipart/form-data">
    <input id="file" name="file" class="form-control js-inputFile dashboardBannerUpload" type="file" value="" accept="image/jpeg,application/pdf" style="display: none;">
    <div class="dashboardHead">
        <label for="file">
            <c:choose>
                <c:when test="${empty dashboardBanner}">
                    <div class="dashboardHeadImage dashboardHeadImage_defaultBackground"></div>
                    <div class="dashboardHeadAdd">
                        <div class="dashboardHeadAdd-text">
                            <icon:camera/>
							<spring:theme code="dashboard.addcoverphoto"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="dashboardHeadImage" style="background-image: url(${dashboardBanner});"></div>
                </c:otherwise>
            </c:choose>
        </label>
    </div>
</form:form>


<div class="globalMessage-holder">
    <div class="container">
        <div class="globalMessage">
            <div class="globalMessage-action">
                <button class="btn btn_round" onclick="location.href='${encodedContextPath}/my-sagia/notifications'">Action</button>
            </div>
            <div class="globalMessage-msg">
                <div class="globalMessage-icon"><icon:warning/></div>
                <spring:theme code="dashboard.message.text"/>
            </div>
        </div>
    </div>
</div>

<section class="mainSection mainSection_white mainSection_noPadding">
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
</section>

<spring:url var="editUrl" value="/dashboard"/>
<section class="mainSection mainSection_grey mainSection_xsmallPaddingTop mainSection_noPaddingBottom">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <a href="${editUrl}" class="btn btn_link btn_link_slim">
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
	            <div class="drag col col-12" id="myLicense">
	                <dashboard:myLicense editable="${true}"/>
	            </div>
            </li>
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12" id="dashboardImage">
	                <div class="dashboardWidget dashboardWidget_banner">
	                    <div class="simple-banner banner__component--responsive">
							<a href="#"><picture class="dashboardWidget-pictureSet"></picture></a>
	                    </div>
	                </div>
	            </div>
            </li>
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12" id="salaryAndEmployment" <c:if test="${!enableSalaryAndEmployment}">style="display:none;"</c:if>><dashboard:salaryAndEmployment editable="${true}"/></div>
            </li>
            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12" id="servicesRequest">
	                <dashboard:servicesRequest editable="${true}" />
	            </div>
            </li>

			<li class="dashboardWidgetListComponent js-component">
				<div class="drag col col-12" id="savedDrafts">
					<dashboard:savedDrafts editable="${true}" />
				</div>
			</li>

            <li class="dashboardWidgetListComponent js-component">
	            <div class="drag col col-12" id="payments">
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
											<icon:arrow_green_right/>
										</button>
										<div class="paginationModule-items"><spring:theme code="text.loadingMessage"/></div>
										<button class="paginationModule-control paginationModule-control_right">
											<icon:arrow_green_right/>
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
	            <div class="drag col col-12" id="yourTickets">
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
                <div class="drag col col-12" id="support">
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
