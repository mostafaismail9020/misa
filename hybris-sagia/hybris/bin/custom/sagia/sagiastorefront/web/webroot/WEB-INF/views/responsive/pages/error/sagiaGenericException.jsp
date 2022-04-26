<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<!DOCTYPE html>
<html dir="ltr">
<header>
    <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css"/>
</header>

<body>
<c:url value="/" var="homePageUrl" />

<div class="mainSection bg-white">
	<div class="achievement_header">
		<img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
		<div class="container">
			<div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="account.notifications.title"/>
                </h1>
			</div>
			<div class="profile-icons float-right">
				<c:if test="${hasLicense or hasAwaitingPayment}">
						<div class="calendar">
							<a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
								<span></span>
							</a>
						</div>
						<div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
							<c:if test="${hasLicense or hasAwaitingPayment}">
								<button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
									<span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
									<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img" alt="message"/>
								</button>
							</c:if>
							<div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
							<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
								<div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
								<ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
								<div class="sagiaNavigation-subPane-actions">
									<a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
								</div>
							</div>
						</div>
				</c:if>
				<div class="profile">
					<a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
						<span></span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="errorPage">
    <div class="errorPage-heroImage">
        <icon:attention-error/>
    </div>
    <div class="errorPage-description">
        <h1><spring:theme code="error.errorpagemessage" htmlEscape="false"/></h1>
        <br/>
        <br/>
        <h2>${errorMessage}</h2>
    </div>
    <div class="errorPage-actions">
        <button type="button" class="btn btn_wide" onclick="location.href='${homePageUrl}';">Return to dashboard</button>
    </div>
</div>
</body>
</html>