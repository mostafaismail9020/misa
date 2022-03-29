<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsApplyModal.tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="dashboardBanner" type="java.lang.String"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>
<%--@elvariable id="profilePicture" type="java.lang.String"--%>
<%--@elvariable id="user" type="de.hybris.platform.commercefacades.user.data.CustomerData"--%>


<div class="mainSection_grey mainSection_noPadding pb-2">
    <div class="container">
        <div class="dashboardUser-wrapper col-12 dashboard-login">
            <div class="dashboardUser-left col-12 col-md-6 ">
                <div class="dashboard-login">
                    <div class="dashboardUser-image position-absolute dashboardHeadAdd dashboard-user-add-icon">
                        <button type="button" id="btnfile" class="dashboardUser-image-add cursor-pointer"><img src="${commonResourcePath}/images/change-profile-icon.png"/><span id="fname"></span></button>                        
                        <div class="myAccount-profilImage">
                            <div class="myAccount-profilImage-img">
                                <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                            </div>
                        </div>
                    </div>
                    <div class="dashboardUser-col">
                        <div class="dashboardUser-entry">
                            <div class="dashboardUser-label d-none"><spring:theme code="general.company"/></div>
                            <h2 class="clr_gld"><c:out value='${user.company}'/></h2>
                            <c:if test="${not empty customerLastLogon}">
                                <span class="last-login"><spring:theme code="dashboard.license.user.lastlogin.title"/>
                                    <span class="clr_gld">&nbsp;<fmt:formatDate value="${customerLastLogon}" pattern="dd/MM/yyyy hh:mm a"/></span>
                                </span>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>            
            <div class="dashboardUser-right col-12 col-md-6  pl-0 user-icon user-icon-without-license">
            	<div class="col-12 col-md-6 d-flex p-0 user-icons-block">
                    <div class=" user-icon mr-1 mr-sm-3">
                        <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                            <c:if test="${hasLicense or hasAwaitingPayment}">
                                <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                    <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                    <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg"/>
                                </button>
                            </c:if>
                            <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                            <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup">
                                <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                                <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                <div class="sagiaNavigation-subPane-actions">
                                    <a class="btn btn_slim btn_round btn_outline" href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class=" user-icon mr-1 mr-sm-3 icon-profile">
                        <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>" class="sagiaNavigation-btn sagiaNavigation-user"> 
                            <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.svg"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="globalMessage-holder" style="background: url(${commonResourcePath}/images/dashboard-media/Apply-license/Apply-license-bg.png) no-repeat center center;  padding: 20px 0; background-size: cover;">
    <div class="container">
        <div class="globalMessage">
        	<a href="${encodedContextPath}/my-sagia/license/entity" id="applyNewLicenseAfterTnC" data-skip-popup="${(applicationStatus != null && not empty applicationStatus.entityId) || hasUserAppliedForLicense}" style="display: none;" class="btn btn_round" >
        		<spring:theme code="dashboard.withoutlicense.applyfornewlicense"/>
        	</a>
            <c:choose>
                <c:when test="${applicationStatus != null && not empty applicationStatus.entityId}">
                    <div class="globalMessage-msg">
                        <div class="globalMessage-icon"><icon:info/></div>
                        <spring:theme code="dashboard.withoutlicense.appliedforlicense"/>&nbsp;
                        <c:if test="${applicationStatus != null && not empty applicationStatus}">
                            <c:out value="${applicationStatus.leadId}"/>&nbsp;
                            <c:out value="${applicationStatus.statusDesc}"/>&nbsp;
                            <c:out value="${applicationStatus.lvDate}"/>&nbsp;
                        </c:if>
                        <%--<c:if test="${entityStatus != null && not empty entityStatus}">--%>
                            <%--<c:out value="${entityStatus}"/>&nbsp;--%>
                        <%--</c:if>--%>
                        <c:if test="${entityStatusDescription != null && not empty entityStatusDescription}">
                            <c:out value="${entityStatusDescription}"/>&nbsp;
                            <c:if test = "${fn:containsIgnoreCase(entityStatusDescription, 'rejected')}">
                               	<div class="globalMessage-action">
                               		<a href="${encodedContextPath}/simulator/license-apply" class="btn btn_round btn-warning btn_outline">
                               			<spring:theme code="dashboard.withoutlicense.startsimulation"/>
                               		</a>
                                 	<button class="btn btn_round" onclick="applyNewTnC(event,'NewApply');"><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></button>
                          		</div>
                            </c:if>
                        </c:if>
                    </div>
                    <c:if test="${hasAwaitingPayment}">
	                    <div class="globalMessage-action">
	                        <a href="#" class="dashboardPrintButton btn btn_outline btn_round btn_slim" style="float: right;">
	                        	<spring:theme code="payment.pay" />
	                        </a>
	                    </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${hasUserAppliedForLicense && !fn:containsIgnoreCase(applicationStatus.statusDesc, 'rejected') }">
                            <div class="globalMessage-msg">
                                <div class="globalMessage-icon"><icon:info/></div>
                                <spring:theme code="dashboard.withoutlicense.appliedforlicense"/>&nbsp;
                                <c:if test="${applicationStatus.statusDesc != null && not empty applicationStatus.statusDesc}">
                                	<c:out value="${applicationStatus.leadId}"/>&nbsp;
                                    <c:out value="${applicationStatus.statusDesc}"/>&nbsp;
                                    <c:out value="${applicationStatus.lvDate}"/>&nbsp;                                     
                                </c:if>
                            </div>
                        </c:when>                                                                                   
                        <c:when test="${hasUserAppliedForLicense && fn:containsIgnoreCase(applicationStatus.statusDesc, 'rejected') }">
                            <div class="globalMessage-action">
                               	<a href="${encodedContextPath}/simulator/license-apply" class="btn btn_round btn-warning btn_outline">
                               		<spring:theme code="dashboard.withoutlicense.startsimulation"/>
                               	</a>
                                <button class="btn btn_round" onclick="applyNewTnC(event,'NewApply');">
                                	<spring:theme code="dashboard.withoutlicense.applyfornewlicense"/>
                                </button>
                             </div>

                            <div class="globalMessage-msg">
                                <div class="globalMessage-icon"><icon:warning/></div>
                                <spring:theme code="dashboard.withoutlicense.appliedforlicense"/>&nbsp;
                                <c:if test="${applicationStatus.statusDesc != null && not empty applicationStatus.statusDesc}">
                                	<c:out value="${applicationStatus.leadId}"/>&nbsp;
                                    <c:out value="${applicationStatus.statusDesc}"/>&nbsp;
                                    <c:out value="${applicationStatus.lvDate}"/>&nbsp;                                      
                                </c:if>
                            </div>
                        </c:when>
                        <c:otherwise>
                        	<div class="globalMessage-action d-flex">
                        		<button data-target="#license-application-simulator" data-toggle="modal" id="dashboardNoLicenseHelper" class="btn-outline text-uppercase mr-5 mr-sm-3 btn-simulator">
                        			<!--<spring:theme code="dashboard.withoutlicense.startsimulation"/>-->
                                    <spring:theme code="dashboard.withoutlicense.investsaudiOverview"/>
                        			<img class="pl-3" src="${commonResourcePath}/images/dashboard-media/Apply-license/Play-icon.png"/>
                        		</button> 
                                <button class="btn-dashboard text-uppercase js-license-apply" onclick="applyNewTnC(event,'NewApply');">
                                	<spring:theme code="dashboard.withoutlicense.applyfornewlicense"/>
                                </button>
                        	</div>
                            <div class="globalMessage-msg">
                            	<img class="Applylicense-icon" src="${commonResourcePath}/images/dashboard-media/Apply-license/Allert-icon.png"/>
                              	<h5 class="pl-3"><spring:theme code="dashboard.withoutlicense.notappliedforlicenseyet"/></h5>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<!-- <div id="license-application-simulator" class="container" style="display:none;">
    <div class="row">
        <div class="col-md-10 ">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe id="simulator-video" class="embed-responsive-item" src="https://www.youtube.com/embed/u3sQ7TDFUWs" allowfullscreen></iframe>
            </div>
        </div>
        <div class="col-md-2"> 
            <button type="button" id="simulator-close" class="license-simulator-modal-close right-close  top-0 end-0">
                <img class="" src="${commonResourcePath}/images/Close.png"/>
            </button>
        </div>
    </div>
</div> -->



<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard scale-on-resize">
    <div class="container">
    	
        <h1 class="section-title text-center clr_gld py-3 py-md-5"><spring:theme code="dashboard.license.my.license.title"/></h1>
        <cms:pageSlot position="MCM_CMS_OTHER" var="component">
        	<cms:component component="${component}"/>
     	</cms:pageSlot>

        </br>
        <dashboard:opportunityTickets></dashboard:opportunityTickets>
    
    	<!-- <div id="dashboardNoLicenseHelper"></div> -->
    	<dashboard:sectorAndOpportunity sector="${currentCustomerSector}"></dashboard:sectorAndOpportunity>

	    <section class="mainSection license mainSection_grey mainSection_noPaddingTop js-dashboard newsSection">
	        <div class="container px-0">
	        <!--News Section Start-->
	            <div id="newsandupdates" class="newsAndUpdateContainer">
	                <div class="dashboard-container">
	                    <div class="row titleContainer">
	                        <div class="col-md-12 title-heading aos-init aos-animate" data-aos="fade-right" data-aos-delay="100">
	                            <h1 class="section-title text-center clr_gld pt-5"><spring:theme code="dashboard.license.news.updates.title"/></h1>
		                        <a href="/${language}/mediaCenter/news" class="btn-dashboard float-right text-uppercase mb-3 mb-md-5  explore-all-btn">
		                        	<spring:theme code="text.dashboard.without.license.exploreAllOpportunities"/>&nbsp;
		                            <img src="/_ui/responsive/common/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive transform-180-degree">
		                    	</a>
	                        </div>
	                    </div>
	                    
	                    <c:if test="${not empty lastNews}">
		                    <div class="row contentWrapper">
			                    <c:url value="/mediaCenter/news" var="newsUrl"/>
			                    <c:forEach var="currentNews" items="${lastNews}" varStatus="status">
			                        <div class="col-lg-4 col-md-6 card-wrapper aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
		                                <div class="flip-card">
		                                    <div class="card-img">
		                                        <img class="img-fluid" src="${fn:escapeXml(currentNews.newsDetailsImage.url)}" alt="">
		                                    </div>
		                                    <div class="card-box p-3 pr-2 home-news-updates-content">
		                                        <!-- <strong><fmt:formatDate value="${currentNews.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="MMMM" /></strong> -->
		                                        <span class="d-news-update-date"><fmt:formatDate value="${currentNews.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="MMMM" />
                                                    <!-- &nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="YY" /> -->
                                                </span>
                                                <h3 class="d-news-update-content my-3">${fn:substring(currentNews.newsTitle,0,25)} ...</h3>
		                                        <p class="home-news-updates-content-p">${currentNews.newsShortInformation}</p>
		                                        <p><a class="know-more-link" href="${newsUrl}/${currentNews.uid}"><spring:theme code="portal.sector.opportunity.know.more.label"/>&nbsp;<img class="ml-3 transform-180-degree" src="${commonResourcePath}/images/btn-sector-outline.png"></a></p>
		                                    </div>
		                                </div>
		                            </div>
								</c:forEach>
							</div>
						</c:if>
	                </div>
	            </div>
	        </div>
	    </section>
    
	    <section class="helpSection">
		    <div class="container">
		        <div class="firstBlock">
		            <div class="firstBlock-widget"> 
		                <h1 class="text-center text-uppercase clr_gld">
		                    <spring:theme code="dashboard.license.letus.help.you.heading.name"/>
		                </h1>
		                <span class="firstBlock-text d-contact-help-text">
		                   <spring:theme code="dashboard.license.letus.help.you.text"/>
		                </span>
		            </div>
		            <div class="firstBlock-contact d-flex row mt-5 mb-3">
                        <div class="firstBlock-contact-local line-after col-md-4">
                            <div>
                                <img  alt="" src="${commonResourcePath}/images/Contact-us/local.png"/>
                            </div>
                            <div>
                                <span class="firstBlock-contact-local-label">
                                    <spring:theme code="dashboard.license.letus.help.you.local"/>
                                </span>
                            </div>
                            <div>
                                <span class="firstBlock-contact-local-number local-connect-number">
                                    <spring:theme code="dashboard.license.letus.help.you.local.number"/>
                                </span>
                            </div>
                        </div>
                        <div class="firstBlock-contact-local line-after col-md-4 mt-3 mt-sm-0">
                            <div>
                                <img  alt="" src="${commonResourcePath}/images/Contact-us/International.png"/>
                            </div>
                            <div>
                                <span class="firstBlock-contact-local-label">
                                    <spring:theme code="dashboard.license.letus.help.you.International"/>
                                </span>
                            </div>
                            <div>
                                <span class="firstBlock-contact-local-number international-connect-number">
                                    <spring:theme code="dashboard.license.letus.help.you.International.number"/>
                                </span>
                            </div>
                        </div>
                        <div class="firstBlock-contact-local col-md-4 mt-3 mt-sm-0">
                            <div>
                                <img  alt="" src="${commonResourcePath}/images/Contact-us/email.png"/>
                            </div>
                            <div class="d-none d-md-block">
                                <span class="firstBlock-contact-local-label">&nbsp;
                                   <!-- <spring:theme code="dashboard.license.letus.help.you.International"/> -->
                                </span>
                            </div>
                            <div>
                                <span class="firstBlock-contact-local-number emailBlock d-email-id">
                                    InvestorCare@misa.gov.sa
                                </span>
                            </div>
                        </div>
		            </div>
                    <div class="row mb-3">
                        <div class="col-12 text-center">                            
                            <button class="btn btn-outline" data-target="#eServiceTour" id="btn-show-me-around" data-toggle="modal" ><spring:theme code="dashboard.license.letus.help.you.emailus.link1.text"></spring:theme></button>
                        </div>
                    </div>
		        </div>
		        <!-- <div class="emailBlock">
		            <div class="firstBlock-widget">
		                <h1 class="text-center text-uppercase clr_gld">
		                    <spring:theme code="dashboard.license.letus.help.you.emailus.heading.name"/>
		                </h1>
		                <span class="firstBlock-text">
		                    <spring:theme code="dashboard.license.letus.help.you.emailus.text"/>
		                </span>
		            </div>
		            <div class="emailBlock-contact">
		                <div class="emailBlock-contact-international">
		                    <span class="firstBlock-contact-local-email">
		                        <spring:theme code="dashboard.license.letus.help.you.emailus.email"/>
		                    </span>
		                    <div class="email-buttons">
		                        <button class="btn-outline mr-5"><spring:theme code="dashboard.license.letus.help.you.emailus.link1.text"/></button>
		                        <button class="btn-dashboard"><spring:theme code="dashboard.license.letus.help.you.emailus.link2.text"/></button>
		                    </div>
		                </div>
		            </div>
		        </div> -->
		    </div>
		</section>
    
    
         <%-- <div class="row mt-5">
            <div class="col-md-8 ">
                <div class="js-dashboardWidget dashboardWidget_noRadiusRight">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="dashboard.support.title"/>
                        <div class="dashboardWidget-headline-icon"><icon:ask-our-expert/></div>
                    </div>

                    <div class="dashboardWidget-body ">
                        <div class="dashboardWidgetAskOurExpert">
                            <div class="row">
                                <div class="col-lg-7">
                                    <div class="dashboardWidgetAskOurExpert-headline"><spring:theme code="dashboard.support.helpQuestion"/></div>
                                    <ul class="dashboardWidgetAskOurExpert-list">
                                        <li>
                                            <a href="${encodedContextPath}/my-sagia/sagia-profile" class="dashboardWidgetAskOurExpert-link">
                                                <icon:account-settings/><spring:theme code="dashboard.support.accountSettings"/>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="" data-toggle="modal" data-target="#eServiceTour" class="dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                <icon:first-steps/><spring:theme code="dashboard.support.firstSteps"/>
                                            </a>
                                        </li>

                                            <li>
                                                <a href="#" class="js-realTimeOnlineSupportCall-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:call/><spring:theme code="realTimeOnlineSupportCall.title"/>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="j-realTimeOnlineSupport-enquiry dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:contactUs-file/><spring:theme code="realTimeOnlineSupport.enquiry"/>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="js-realTimeOnlineSupportChatList-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:chat/><spring:theme code="realTimeOnlineSupportChatList.title"/>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="js-realtimeOnlineSupportEmailUs dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:contactUs-message/><spring:theme code="realtimeOnlineSupportEmailUs.title"/>
                                                </a>
                                            </li>

                                        <li>
                                            <a href="${encodedContextPath}/my-sagia/license/bidding" class="dashboardWidgetAskOurExpert-link">
                                                <icon:bidding-certificates/><spring:theme code="dashboard.support.biddingCertificates"/>
                                            </a>
                                        </li>
                                        <li>
                                           <a href="${encodedContextPath}/service-search" class="dashboardWidgetAskOurExpert-link">
                                                <icon:services/><spring:theme code="dashboard.support.services"/>
                                            </a>
                                        </li>
                                    </ul>                               
                                </div>
                               <div class="col-lg-5 dashboardWidgetAskOurExpert-seperator">
                                    <div class="dashboardWidgetAskOurExpert-headline">
                                        <spring:theme code="dashboard.support.ask"/>
                                    </div>

                                    <ul class="dashboardWidgetAskOurExpert-list dashboardWidgetAskOurExpert-list_oneColumn">
                                        <li><icon:call/><a href="#" id="scheduleCallButton"><spring:theme code="support.schedulecall"/></a></li>
                                        <li><icon:chat/><a href="#" id="liveChatButton"><spring:theme code="support.livechat"/></a></li>
                                        <li><icon:enquiry/><a href="#" id="makeAnEnquiry"><spring:theme code="support.makeenquiry"/></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="dashboardWidget dashboardWidget_bg dashboardWidget_noRadiusLeft">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="dashboard.newsevents"/><div class="dashboardWidget-headline-icon"></div>
                    </div>
                    <div class="dashboardWidget-body ">
                        <div class="dashboardWidgetNews">
                            <div class="dashboardWidgetNews-item">
                                <div class="dashboardWidgetNews-text">
                                    <spring:theme code="dashboard.newsevents.news"/>
                                </div>
                                <div class="dashboardWidgetNews-action">
                                    <a class="btn" target="_new" href="https://investsaudi.sa/${currentLanguage.isocode}/news"><spring:theme code="dashboard.readnews"/></a>
                                </div>
                            </div>
                            <div class="dashboardWidgetNews-item">
                                <div class="dashboardWidgetNews-text">
                                    <spring:theme code="dashboard.newsevents.events"/>
                                </div>
                                <div class="dashboardWidgetNews-action">
                                    <a class="btn" target="_new" href="https://investsaudi.sa/${currentLanguage.isocode}/sectors-opportunities/opportunities/"><spring:theme code="dashboard.showevents"/></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  --%>
    </div>
</section>

<div class="modal fade licenseSimulatorPopup" id="license-application-simulator"  tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true">
    <button type="button" data-dismiss="modal" id="simulator-close" class="license-simulator-modal-close right-close  top-0 end-0">
        X
    </button>
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content dashboard-pop-up">
            <!--<div class="modal-header">
                <button type="button" data-dismiss="modal" id="simulator-close" class="license-simulator-modal-close right-close  top-0 end-0">
                    <img class="" src="${commonResourcePath}/images/Close.png"/>
                </button>
            </div>-->
            <div class="modal-body">
                <div class=" ">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe id="simulator-video" class="embed-responsive-item" src="https://www.youtube.com/embed/u3sQ7TDFUWs" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--Modal: Use (data-toggle="modal" data-target="#eServiceTour") on link or button to call it--%>
<%--<div class="modal fade" id="eServiceTour"  tabindex="-1" role="dialog" aria-labelledby="eServiceTour" aria-hidden="true">
    <!-- <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content dashboard-pop-up">
            <div class="modal-header">
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:tutorial/>
                </div>
                <div class="modal-title modal-title_uppercase"><spring:theme code="dashboard.tutorial.modal.title"/></div>
                <div class="modal-description modal-description_eService">
                    <spring:theme code="dashboard.tutorial.modal.content"/>
                </div>
            </div>
            <div class="modal-footer modal-footer_wrap">
                <button type="button" class="btn btn_slim js-eServiceTour-start" data-dismiss="modal"><spring:theme code="dashboard.tutorial.modal.button.text"/></button>
                <a class="btn btn_slim btn_link btn_inFooterModal js-skipTutorial" data-dismiss="modal" onclick="dismissDashboardWithoutLicenceTutorial();"><spring:theme code="general.dont.show.this.message.again"/></a>
            </div>
        </div>
    </div>
</div> -->
<ul class="eServiceTutorial js-eServiceTour">
    <li class="eServiceTutorial-item" id="eServiceTutorial-item-0">
        <div class="eServiceTutorial-panel">
            <div class="eServiceTutorial-panelInner">
                <div class="eServiceTutorial-header">
                    <div class="eServiceTutorial-headline"><spring:theme code="dashboard.nolicense.tutorial.step0.title"/></div>
                    <button class="eServiceTutorial-close js-eServiceTour-close">
                        <icon:close/>
                    </button>
                </div>
                <div class="eServiceTutorial-body">
                    <div class="eServiceTutorial-description">
                        <spring:theme code="dashboard.nolicense.tutorial.step0.content"/>
                    </div>
                </div>
                <div class="eServiceTutorial-actions">
                    <a class="btn btn_slim js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                </div>
            </div>
        </div>
    </li>
    <li class="eServiceTutorial-item" id="eServiceTutorial-item-1">
        <div class="eServiceTutorial-panel">
            <div class="eServiceTutorial-panelInner">
                <div class="eServiceTutorial-header">
                    <div class="eServiceTutorial-headline"><spring:theme code="dashboard.nolicense.tutorial.step1.title"/></div>
                    <button class="eServiceTutorial-close js-eServiceTour-close">
                        <icon:close/>
                    </button>
                </div>
                <div class="eServiceTutorial-body">
                    <div class="eServiceTutorial-description">
                        <spring:theme code="dashboard.nolicense.tutorial.step1.content"/>
                    </div>
                </div>
                <div class="eServiceTutorial-actions">
                    <a class="btn btn_slim js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                </div>
            </div>
        </div>
    </li>
    <li class="eServiceTutorial-item" id="eServiceTutorial-item-2">
        <div class="eServiceTutorial-panel">
            <div class="eServiceTutorial-panelInner">
                <div class="eServiceTutorial-header">
                    <div class="eServiceTutorial-headline"><spring:theme code="dashboard.nolicense.tutorial.step2.title"/></div>
                    <button class="eServiceTutorial-close js-eServiceTour-close">
                        <icon:close/>
                    </button>
                </div>
                <div class="eServiceTutorial-body">
                    <div class="eServiceTutorial-description">
                        <spring:theme code="dashboard.nolicense.tutorial.step2.content"/>
                    </div>
                    &lt;%&ndash;<div class="eServiceTutorial-actions">
                        <a class="btn btn_slim js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                    </div>&ndash;%&gt;
                </div>
            </div>
        </div>
    </li>
</ul>--%>

<script>
    // var displayTutorial = ${displayTutorial};
    // var displayTutorial = false;
    $(document).ready(function(){
        getAccordion("#tabs",768);
        $(window).resize(function () { getAccordion("#tabs",768);});
    });
</script>

