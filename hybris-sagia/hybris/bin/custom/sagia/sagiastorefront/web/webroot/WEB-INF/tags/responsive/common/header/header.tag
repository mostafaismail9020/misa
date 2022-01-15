<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>

<cms:pageSlot position="TopHeader" var="component" element="div">
    <cms:component component="${component}"/>
</cms:pageSlot>
<c:url value="/dashboard" var="dashboardUrl"/>
<c:url value="/dashboard-without-license" var="dashboardWithoutLicenceUrl"/>

<!-- 
<div class="sagiaHead">
    <div class="investsaudiHeader">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top fixed-header" id="insvest-navbar">
            <div class="container px-0 investNavigation">
                <button class="navbar-toggler sagiaNavigation-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-close">
                        <icon:close_small/>
                    </span>
                </button>
                <button class="sagiaMobileNav-toggler" type="button" id="js-sagiaMobileNav-toggler">
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-close">
                        <icon:close_small/>
                    </span>
                </button>
                <div class="nav-item logo hiddenlarge d-md-none">
                    
                    <c:if test="${currentLanguage.isocode == 'en'}">
                        <a class="nav-link logo-postion-fix" target="_blank" href="https://investsaudi.sa/en/">
                            <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png" />
                        </a>                       
                    </c:if>

                    <c:if test="${currentLanguage.isocode == 'ar'}">
                        <a class="nav-link logo-postion-fix" target="_blank" href="https://investsaudi.sa/ar/">
                            <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png" />
                        </a>                       
                    </c:if>                                           
                    
                </div>
                <ul class="navbar-nav collapse navbar-collapse investNavigation-left" id="navbarResponsive">
                    <li class="nav-item text-center img-roya mynav-logo">

                        <c:if test="${currentLanguage.isocode == 'en'}">
                            <a class="nav-link logo-postion-fix" target="_blank" href="/en/">
                                <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png" />
                            </a>
						</c:if>

                        <c:if test="${currentLanguage.isocode == 'ar'}">
                            <a class="nav-link logo-postion-fix" target="_blank" href="/ar/">
                                <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png" />
                            </a>                      
                        </c:if>                        

                    </li>
                     <c:if test="${currentLanguage.isocode == 'en'}">
                    
						</c:if>
						
						
<!--
                    <%--
                    <c:if test="${currentLanguage.isocode == 'en'}">
                        
                        <li class="nav-item text-center">
                            <a class="nav-link" href="WhySaudiArabia/why-ksa.html"><spring:message code="header.whySaudiArabia.text"/></a>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="SectorAndOpportunities/sectors-and-opp.html"><spring:message code="header.sectorsAndOpportunities.text"/></a>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="investor-services/invest-services.html"><spring:message code="header.investorsServices.text"/></a>
                        </li>
                        <li class="nav-item nav-item_hasSub text-center" tabindex="-1">
                            <a class="nav-link about-us-nav" href="about-us/about-us.html"><spring:message code="header.about.text"/></a>
                            <ul class="dropdown-list">
                                <li><a class="a-nav-item" href="about-us/about-us.html"><spring:message code="header.generalInvestmentAuthority.text"/></a></li>
                                <li><a class="a-nav-item" href="WhySaudiArabia/vision-2030.html"><spring:message code="header.vision.text"/></a></li>
                            </ul>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="contact-us/contact-sagia.html"><spring:message code="header.contact.text"/></a>
                        </li>
                    </c:if>

                    <c:if test="${currentLanguage.isocode == 'ar'}">
                        <li class="nav-item text-center">
                            <a class="nav-link" href="WhySaudiArabia/why-ksa.html"><spring:message code="header.whySaudiArabia.text"/></a>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="SectorAndOpportunities/sectors-and-opp.html"><spring:message code="header.sectorsAndOpportunities.text"/></a>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="investor-services/invest-services.html"><spring:message code="header.investorsServices.text"/></a>
                        </li>
                        <li class="nav-item nav-item_hasSub text-center" tabindex="-1">
                            <a class="nav-link nav-link_hasSub about-us-nav" href="about-us/about-us.html"><spring:message code="header.about.text"/></a>
                            <ul class="dropdown-list">
                                <li><a class="a-nav-item" href="about-us/about-us.html"><spring:message code="header.generalInvestmentAuthority.text"/></a></li>
                                <li><a class="a-nav-item" href="WhySaudiArabia/vision-2030.html"><spring:message code="header.vision.text"/></a></li>
                            </ul>
                        </li>
                        <li class="nav-item text-center">
                            <a class="nav-link" href="contact-us/contact-sagia.html"><spring:message code="header.contact.text"/></a>
                        </li>
                    </c:if>
                    --%>
                    <!-- <c:if test="${currentLanguage.isocode == 'en'}">
                        <header:languageLink currentLanguage="en" newLanguage="ar" text="&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;" additionalCssClass="diff-color arabic"/>
                    </c:if>
                    <c:if test="${currentLanguage.isocode == 'ar'}">
                        <header:languageLink currentLanguage="ar" newLanguage="en" text="ENGLISH"/>
                    </c:if>
                </ul>
                <ul class="navbar-nav img-roya-nav investNavigation-right">
                    <li class="nav-item img-roya">
                        <a class="nav-link" href="http://vision2030.gov.sa/ar/" target="_blank"><img src="${themeResourcePath}/img/roya.svg" class="roya-height" width="97"></a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
        <div class="sagiaNavigation js-sagiaNavigation">
            <div class="container">
                <div class="sagiaNavigation_row">
                    <ul class="sagiaNavigation-list ">
                        <li>
                            <c:if test="${hasLicense}">
                                <a href="${dashboardUrl}"><span><spring:message code="header.mySagia.text"/></span></a>
                            </c:if>
                            <c:if test="${!hasLicense}">
                                <a href="${dashboardWithoutLicenceUrl}"><span><spring:message code="header.mySagia.text"/></span></a>
                            </c:if>
                        </li>
                        <c:if test="${hasLicense}">
                            <header:navigation/>
                        </c:if>
                    </ul>
                    <div class="sagiaNavigation-right">
                        <div class="sagiaNavigation-name">
                            <%--<strong>${user.company}</strong> | ${user.name}--%>
                        </div>
                        <div class="sagiaNavigation-entry header-tutorial-header-btn">
                            <a href="javascript:;" data-toggle="modal" data-target="#eServiceTour" class="sagiaNavigation-btn" title="<spring:message code='dashboard.tutorial.modal.button.text'/>">
                                <icon:first-steps />
                            </a>
                        </div>
                        <c:if test="${hasLicense}">
                            <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>" class="sagiaNavigation-btn sagiaNavigation-cal">
                                <icon:calendarDate_stroke/>
                            </a>
                        </c:if>
                        <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                            <c:if test="${hasLicense or hasAwaitingPayment}">
                                <button class="sagiaNavigation-btn js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                    <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span><icon:mail/>
                                </button>
                            </c:if>
                            <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                            <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible">
                                <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><span class="clr_gld"><spring:message code="header.mostRecent.text"/></span></div>
                                <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                <div class="sagiaNavigation-subPane-actions">
                                    <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                </div>
                            </div>
                        </div>
                        <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>"
                           class="sagiaNavigation-btn sagiaNavigation-user"><icon:person_stroke/></a>
                        <a data-toggle="modal" data-target="#logoutModal" title="<spring:theme code='text.logout'/>" class="sagiaNavigation-btn sagiaNavigation-logout"><icon:logout/></a>
                    </div>
                </div>
            </div>
        </div>
    </sec:authorize>
</div> 

<!-- <div class="sagiaHead-paddingHelper"></div>

<div class="sagiaMobileNav" id="js-sagiaMobileNav">
    <div class="sagiaMobileNav-backdrop"></div>
    <div class="sagiaMobileNav-navigation">
        <div class="sagiaMobileNav-lvl0" id="js-sagiaMobileNav-lvl0">
            <ul> --> 
<!--
<%--
                <li class="sagiaMobileNav-hasSub sagiaMobileNav-hasSub_main js-sagiaMobileNav_moveBack">
                    <a href="#"><spring:message code="header.mainMenu.text"/></a>
                    <div class="sagiaMobileNav-lvl1 sagiaMobileNav-lvl1_main">
                        <div class="sagiaMobileNav-headNav js-sagiaMobileNav_moveForwards">
                            <a class="sagiaMobileNav-headNav-link sagiaMobileNav-headNav-link_dark sagiaMobileNav-headNav-link_forwards" href="#"><spring:message code="header.mainMenu.text"/></a>
                        </div>
                        <div class="sagiaMobileNav-wrapper">
                            <ul>
                                <c:if test="${currentLanguage.isocode == 'en'}">
                                    <li><a href="WhySaudiArabia/why-ksa.html"><spring:message code="header.whySaudiArabia.text"/></a></li>
                                    <li><a href="SectorAndOpportunities/sectors-and-opp.html"><spring:message code="header.sectorsAndOpportunities.text"/></a></li>
                                    <li><a href="investor-services/invest-services.html"><spring:message code="header.investorsServices.text"/></a></li>
                                    <li class="sagiaMobileNav-hasSub js-sagiaMobileNav_moveBack">
                                        <a href="about-us/about-us.html" data-target-submenu="1" data-target-menuID="#sagiaMobileNav-mainMenu" class="sagiaMobileNav-hasSub-link_back"><spring:message code="header.about.text"/></a>
                                    </li>
                                    <li>
                                        <a href="contact-us/contact-sagia.html"><spring:message code="header.contact.text"/></a>
                                    </li>
                                </c:if>

                                <c:if test="${currentLanguage.isocode == 'ar'}">
                                    <li>
                                        <a href="WhySaudiArabia/why-ksa.html"><spring:message code="header.whySaudiArabia.text"/></a>
                                    </li>
                                    <li>
                                        <a href="SectorAndOpportunities/sectors-and-opp.html"><spring:message code="header.sectorsAndOpportunities.text"/></a>
                                    </li>
                                    <li>
                                        <a href="investor-services/invest-services.html"><spring:message code="header.investorsServices.text"/></a>
                                    </li>
                                    <li class="sagiaMobileNav-hasSub js-sagiaMobileNav_moveBack">
                                        <a href="about-us/about-us.html" data-target-submenu="2" data-target-menuID="#sagiaMobileNav-mainMenu"><spring:message code="header.about.text"/></a>
                                    </li>
                                    <li>
                                        <a href="contact-us/contact-sagia.html"><spring:message code="header.contact.text"/></a>
                                    </li>
                                </c:if>
                                <c:if test="${currentLanguage.isocode == 'en'}">
                                    <li><a href="?lang=ar">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</a></li>
                                </c:if>
                                <c:if test="${currentLanguage.isocode == 'ar'}">
                                    <li><a href="?lang=en">English</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="sagiaMobileNav-lvl2 sagiaMobileNav-lvl2_main">
                        <div class="sagiaMobileNav-headNav js-sagiaMobileNav_moveForwards">
                            <a class="sagiaMobileNav-headNav-link sagiaMobileNav-headNav-link_dark sagiaMobileNav-headNav-link_forwards" href="#"><spring:message code="header.about.text"/></a>
                        </div>
                        <div class="sagiaMobileNav-wrapper">
                            <div class="sagiaMobileNav-subRight" id="sagiaMobileNav-mainMenu">
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="1">
                                    <ul>
                                        <li><a href="about-us/about-us.html"><spring:message code="header.generalInvestmentAuthority.text"/></a></li>
                                        <li><a href="WhySaudiArabia/vision-2030.html"><spring:message code="header.vision.text"/></a></li>
                                    </ul>
                                </div>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="2">
                                    <ul>
                                        <li>
                                            <a href="about-us/about-us.html"><spring:message code="header.generalInvestmentAuthority.text"/></a>
                                        </li>
                                        <li>
                                            <a href="WhySaudiArabia/vision-2030.html"><spring:message code="header.vision.text"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
--%>
-->



                <!-- <li><a href="${dashboardUrl}"><span><spring:message code="header.mySagia.text"/></span></a></li>
                services-->
                <!-- <li class="sagiaMobileNav-hasSub js-sagiaMobileNav_moveForwards">
                    <a href="#" class="sagiaMobileNav-hasSub-link_forwards"><spring:message code="header.services.text"/></a>

                    <div class="sagiaMobileNav-lvl1 sagiaMobileNav-lvl1_secondary">
                        <div class="sagiaMobileNav-headNav js-sagiaMobileNav_moveBack">
                            <a class="sagiaMobileNav-headNav-link sagiaMobileNav-headNav-link_back" href="#"><spring:message code="header.services.text"/></a>
                        </div>
                        <div class="sagiaMobileNav-wrapper"> -->
                            <!--services list-->
                            <!-- <ul class="sagiaMobileNav-subNav js-mobileNavRender">
                                <li class="sagiaMobileNav-subNav-title"><a href="${encodedContextPath}/service-search"><icon:your-services-requests-overview/>Overview</a></li>
                                
                                
                                
                                <c:if test="${not empty navcategories['FIRST']}">
                                    <c:forEach items="${navcategories['FIRST']}" var="category">
                                        <li class="js-sagiaMobileNav_moveForwards sagiaMobileNav-subNav-icon">
                                            <a href="#" data-target-submenu="${category.name}" data-target-menuID="#sagiaMobileNav-serviceMenu" class="sagiaMobileNav-hasSub-link_forwards">
                                                <c:if test="${not empty category.menuIcon.url}">
                                                    <img src="${category.menuIcon.url}" width="25" height="25"/>
                                                </c:if>                                                
                                                <c:choose>
                                                    <c:when test="${fn:length(category.name) gt 0}">
                                                        ${category.name}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${category.code}
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                
                                
                                <c:if test="${not empty navcategories}">
                                    <c:forEach items="${navcategories}" var="label">
                                        <c:if test="${label.key ne 'FIRST'}">
                                        <li class="sagiaMobileNav-subNav-subtitle">${label.key}</li>
                                        <c:forEach items="${label.value}" var="category">

                                            <li class="js-sagiaMobileNav_moveForwards sagiaMobileNav-subNav-icon">
                                                <a href="#" data-target-submenu="${category.name}" data-target-menuID="#sagiaMobileNav-serviceMenu" class="sagiaMobileNav-hasSub-link_forwards">
                                                   <c:if test="${not empty category.menuIcon.url}">
                                                        <img src="${category.menuIcon.url}" width="25" height="25"/>
                                                    </c:if>
                                                    <c:choose>
                                                        <c:when test="${fn:length(category.name) gt 0}">
                                                            ${category.name}
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${category.code}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </li>
                                        </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </c:if>                                
                            </ul>
                        </div>
                    </div>
                    <div class="sagiaMobileNav-lvl2 sagiaMobileNav-lvl2_secondary">
                        <div class="sagiaMobileNav-headNav js-sagiaMobileNav_moveBack">
                            <a class="sagiaMobileNav-headNav-link sagiaMobileNav-headNav-link_back" href=""><spring:message code="header.title.text"/></a>
                        </div>
                        <div class="sagiaMobileNav-wrapper">
                           
                           
                           
                           
                           
                            <div class="sagiaMobileNav-subRight" id="sagiaMobileNav-serviceMenu">
                                <c:if test="${not empty navservices}">
                                    <c:forEach items="${navservices}" var="category" >
                                        <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="${category.key}">
                                            <div class="sagiaMobileNav-subRight-detail">
                                                <div class="sagiaMobileNav-subRight-icon"></div>
                                                <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                            </div>                                           
                                            <c:forEach items="${category.value}" var="service" varStatus="serviceLoop">
                                                <a href="${encodedContextPath}/${service.menuUrl}"><span>${service.name}</span></a>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>                           
                           
                           
                           
                           
                           

                            <%--
                            <div class="sagiaMobileNav-subRight" id="sagiaMobileNav-serviceMenu">

                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="1">
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${encodedContextPath}/my-sagia/license/convert"><span><spring:message code="header.convertToNational.text"/></span></a>
                                    <a href="${encodedContextPath}/my-sagia/license/replace"><span><spring:message code="header.replace.text"/></span></a>
                                    <a href="${encodedContextPath}/my-sagia/license/renew"><span><spring:message code="header.renew.text"/></span></a>
                                    <a href="${encodedContextPath}/my-sagia/license/bidding"><span><spring:message code="header.bidding.text"/></span></a>                            <a href="${encodedContextPath}/my-sagia/license/stakeholders"><span><spring:message code="header.stakeholders.text"/></span></a>                                   TODO lancel cancellation should follow the same url pattern 
                                    <a href="${encodedContextPath}/my-sagia/license/cancel"><span><spring:message code="header.cancel.text"/></span></a>
                                </div>
                                
                                
                                
                                
                                
                                
                                
                                <c:if test="${not empty govtCategoriesMap}">
                                    <c:forEach items="${govtCategoriesMap}" var="category" varStatus="categoryLoop">
                                        <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="${categoryLoop.index+3}">
                                            <div class="sagiaMobileNav-subRight-detail">
                                                <div class="sagiaMobileNav-subRight-icon"></div>
                                                <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                            </div>
                                            <c:forEach items="${category.value}" var="service" varStatus="serviceLoop">
                                                <c:url value="/services/government/${category.key.code}/${category.key.categoryUrl}_${service.url}" var="url">
                                                    <c:param name="serviceName" value="${service.name}"/>
                                                </c:url>
                                                <a href="${url}"><span>${service.name}</span></a>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="2">
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${encodedContextPath}/governmentDocuments"><span><spring:message code="header.govDocuments.text"/></span></a>
                                </div>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="10">
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${encodedContextPath}/financial"><span><spring:message code="header.financialStatement.text"/></span></a>
                                </div>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="11">
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${encodedContextPath}/real-estate"><span><spring:message code="header.buyAndSellRealEstate.text"/></span></a>
                                </div>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="12">
                                    <spring:url value="/special-services" var="specialServicesUrl" htmlEscape="false"/>
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${specialServicesUrl}/exit-re-entry-visa"><span><spring:message code="header.exitReentryVisaApplication.text"/></span></a>
                                    <a href="${specialServicesUrl}/renewal-of-iqama"><span><spring:message code="header.renewalOfIqmaApplication.text"/></span></a>
                                    <a href="${specialServicesUrl}/transfer-of-iqama"><span><spring:message code="header.transferOfIqamaApplication.text"/></span></a>
                                    <a href="${specialServicesUrl}/final-exit-visa"><span><spring:message code="header.finalExitVisaApplication.text"/></span></a>
                                </div>
                                <div class="sagiaMobileNav-subRight-pane js-sagiaMobileNav-subRight-pane" data-submenu="13">
                                    <div class="sagiaMobileNav-subRight-detail">
                                        <div class="sagiaMobileNav-subRight-icon"></div>
                                        <div class="sagiaMobileNav-subRight-title"><spring:message code="header.title.text"/></div>
                                    </div>
                                    <a href="${encodedContextPath}/violation-replies"><span><spring:message code="header.violationReplies.text"/></span></a>
                                    <a href="${encodedContextPath}/warning-letters"><span><spring:message code="header.warningLetterExtension.text"/></span></a>
                                    <a href="${encodedContextPath}/support-visits"><span><spring:message code="header.supportVisits.text"/></span></a>
                                    <a href="${encodedContextPath}/facility-reopen"><span><spring:message code="header.reopenClosedFacility.text"/></span></a>
                                </div>
                            </div>
                            --%>
-->
<!--                             
                        </div>
                    </div>
                </li>
                
                language switch-->
                <!-- <c:if test="${currentLanguage.isocode == 'en'}">
                    <li><a href="${fn:replace(requestScope['javax.servlet.forward.request_uri'], '/en/', '/ar/')}"><span>&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</span></a></li>
                </c:if>
                <c:if test="${currentLanguage.isocode == 'ar'}">
                    <li><a  href="${fn:replace(requestScope['javax.servlet.forward.request_uri'], '/ar/', '/en/')}"><span>English</span></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div> --> 

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <form action="" class="js-formInputFileBox">
                <div class="modal-header modal-header_smallPDB">
                    <div class="modal-title"><spring:message code="text.logout.title"/></div>
                    <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                        <icon:close/>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="modal-description modal-description_largeMargin modal-description_smallText">
                        <spring:message code="text.logout.description"/>
                    </div>
                </div>
               <div class="modal-footer modal-footer_spaceBetween">
                    <button type="button" class="btn btn-ctrl btn-warning noButton btn-outline p-0" data-dismiss="modal"><spring:message code="text.logout.no"/></button>
                    <button type="button" class="btn btn-ctrl btn-bg p-0 yesButton"><spring:message code="text.logout.yes"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
