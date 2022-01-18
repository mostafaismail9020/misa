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

<section class="mainSection_grey mainSection_noPadding">
    
    <div class="row">
        <div class="col-12 owl-slider">
            <div class="owl-carousel owl-theme" id="dashboard-carousel">
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image1.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image2.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image3.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image4.jpg"></div>
                <div class="item"><img src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image5.jpg"></div>
            </div>
        </div>
    </div>
  

    
    
    
    <div class="mainSection_grey mainSection_noPadding">
        <div class="container">
            <div class="dashboardUser-wrapper col-12 dashboard-login">
                <div class="dashboardUser-left col-6">
                    <div class="dashboard-login">
                        <div class="dashboardUser-image position-absolute dashboardHeadAdd dashboard-user-add-icon">
                            <button type="button"  id="btnfile" class="dashboardUser-image-add"><icon:plus/><span id="fname"></span></button>
                            
                            <div class="myAccount-profilImage">
                                <div class="myAccount-profilImage-img">
                                    <div class="profilePicture js-profilePicture" style="background-image:url(${profilePicture})"></div>
                                </div>
                            </div>
                        </div>
                        <div class="dashboardUser-col">
                            <div class="dashboardUser-entry">
                                <div class="dashboardUser-label dashboardUser-label-xs"><spring:theme code="general.company"/></div>
                                <h2 class="clr_gld"><c:out value='${user.company}'/></h2>
                                <div>Last Login: <span class="clr_gld">${customerLastLogon}</span></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="dashboardUser-right col-6 pl-0 user-icon">
                    <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png"/>
                </div>
            </div>
        </div>
    </div>


    <div class="globalMessage-holder" style="background: url(${commonResourcePath}/images/dashboard-media/Apply-license/Apply-license-bg.png) no-repeat center center;  padding: 20px 0; background-size: cover;">
        <div class="container">
            <div class="globalMessage">
            	<a href="${encodedContextPath}/my-sagia/license/entity" id="applyNewLicenseAfterTnC" data-skip-popup="${(applicationStatus != null && not empty applicationStatus.entityId) || hasUserAppliedForLicense}" style="display: none;" class="btn btn_round" ><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></a>
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
                                         <a href="${encodedContextPath}/simulator/license-apply" class="btn btn_round btn-warning btn_outline"><spring:theme code="dashboard.withoutlicense.startsimulation"/></a>
                                         <button class="btn btn_round" onclick="applyNewTnC(event,'NewApply');"><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></button>
                                          </div>
                                    </c:if>
                            </c:if>
                        </div>
                        <c:if test="${hasAwaitingPayment}">
                        <div class="globalMessage-action">
                            <a href="#" class="dashboardPrintButton btn btn_outline btn_round btn_slim" style="float: right;"><spring:theme code="payment.pay" /></a>
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
                                   <a href="${encodedContextPath}/simulator/license-apply" class="btn btn_round btn-warning btn_outline"><spring:theme code="dashboard.withoutlicense.startsimulation"/></a>
                                    <button class="btn btn_round" onclick="applyNewTnC(event,'NewApply');"><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></button>
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
                                    <a href="${encodedContextPath}/simulator/license-apply" id="dashboardNoLicenseHelper" class="btn-outline text-uppercase mr-5"><spring:theme code="dashboard.withoutlicense.startsimulation"/><img class="pl-3" src="${commonResourcePath}/images/dashboard-media/Apply-license/Play-icon.png"/></a>
                                     <button class="btn-dashboard text-uppercase" onclick="applyNewTnC(event,'NewApply');"><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></button>
                                  </div>

                                <div class="globalMessage-msg">
                                    <img class="Applylicense-icon" src="${commonResourcePath}/images/dashboard-media/Apply-license/Allert-icon.png"/>
                                   <h5 class="pl-3"> <spring:theme code="dashboard.withoutlicense.notappliedforlicenseyet"/></h5>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
     <dashboard:opportunityTickets></dashboard:opportunityTickets>
     <div class="js-dashboardWidget">
        <h1 class="dashboard-headline js-dashboardWidget-headline text-center mt-5 pt-5 mb-5">
            <spring:theme code="myLicense.title"/>
        </h1>
        <div class="card dashboardWidget-body mb-5 p-0 globalMessage">
            <ul  class="nav nav-tabs w-100">
                <li class="col-6 text-center active" ><img class="pr-3" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Apply-for-license.png"/><a  href="#1a" data-toggle="tab">Apply For a Misa License</a>
                </li>
                <li class="col-6 text-center"><img class="pr-3" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Apply-for-certificate.png"/><a href="#2a" data-toggle="tab">Apply for a Bidding Certificate</a>
                </li>
            </ul>
              <div class="process tab-content clearfix">
                  <div class="tab-pane active" id="1a">
                      <div class="text-center">
                        <h4 class="pt-5 dashboard-heading-title-color">How to Apply for a New License</h4>
                        <h5 class="pt-5 dashboard-paragraph-color"> In order to start in saudi arabia, investors must first obtain an investor license, MISA is the autority
                            concerned with providing all seervices to  investors, Types of investment license vary each with its own requirement
                            such as minimum acceptable capital and maximum capital. Investors can apply for a license online on a MISA e-portal
                        </h5>
                      </div>
                      <div class="d-flex pt-5">
                          <div class="process-step col-3 col-xs-12">
                            <div class="process-card">
                                <div class="process-card-step">
                                  <img class="" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Register-icon.png"/>
                                </div>
                            </div>
                            <div  class="text-center">
                                <div class="mt-3">01</div>    
                                <div class="mt-1">Register</div>
                            </div>
                          </div>
                          <div class="process-arrow-icon"><img  src="${commonResourcePath}/images/arrow-round-forward.png"/></div>

                          <div class="process-step col-3 col-xs-12">
                            <div class="process-card">
                                <div class="process-card-step">
                                  <img class="" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Complete-our-application-form.png"/>
                                </div>
                            </div>
                            <div  class="text-center">
                                <div class="mt-3">02</div>    
                                <div class="mt-1">Complete Our Application Form</div>
                            </div>
                          </div>
                          <div class="process-arrow-icon"><img  src="${commonResourcePath}/images/arrow-round-forward.png"/></div>

                          <div class="process-step col-3 col-xs-12">
                            <div class="process-card">
                                <div class="process-card-step">
                                  <img class="" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Waiting-the-approval.png"/>
                                </div>
                            </div>
                            <div  class="text-center">
                                <div class="mt-3">03</div>    
                                <div class="mt-1">Waiting for the Approval</div>
                            </div>
                          </div>
                          <div class="process-arrow-icon"><img  src="${commonResourcePath}/images/arrow-round-forward.png"/></div>

                          <div class="process-step col-3 col-xs-12">
                            <div class="process-card">
                                <div class="process-card-step">
                                  <img class="" src="${commonResourcePath}/images/dashboard-media/MY-LICENSE/Payment.png"/>
                                </div>
                            </div>
                            <div  class="text-center">
                                <div class="mt-3">04</div>    
                                <div class="mt-1">Payment</div>
                            </div>
                          </div>
                      </div>
                  </div>
                  <div class="text-center mt-5"><button  class="btn btn_round btn-primary btn-dashboard text-center text-uppercase">apply<img class="pl-3"  src="${commonResourcePath}/images/arow_btn.png"/></button></div>
              </div>
        </div>
    </div>
     <!-- <div id="dashboardNoLicenseHelper"></div> -->
    <dashboard:sectorAndOpportunity sector="${currentCustomerSector}"></dashboard:sectorAndOpportunity>


    <!--News Section Start-->
    <div id="newsandupdates" class="newsAndUpdateContainer">
	    <div class="dashboard-container">
	        <div class="row titleContainer">
	            <div class="col-md-12 title-heading p-0 aos-init aos-animate" data-aos="fade-right" data-aos-delay="100">
	                <h1 class="section-title text-center clr_gld">NEWS &amp; UPDATES</h1>
					<a href="/en/mediaCenter/news" class="btn-primary explore-btn explore-gia-btn">
						Explore All&nbsp;
						<img src="/_ui/responsive/common/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
					
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
			              	<div class="card-box p-3 pr-5 home-news-updates-content">
			               		<strong><fmt:formatDate value="${currentNews.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentNews.newsDate}" pattern="MMMM" /></strong>
			                  	<h2>${currentNews.newsTitle}</h2>
			                  	<p class="home-news-updates-content-p">${currentNews.newsShortInformation}</p>
	                  			<p><a class="know-more-link" href="${newsUrl}/${currentNews.uid}"><spring:theme code="portal.sector.opportunity.know.more.label"/>&nbsp;<img src=""></a></p>
	                      	</div>
						</div>
					</div>
					</c:forEach>
			
				</div>
				</c:if>
    	</div>
	</div>
         <div class="row mt-5">
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
                                        <%--<li>
                                            <a href="${encodedContextPath}/my-sagia/sagia-profile" class="dashboardWidgetAskOurExpert-link">
                                                <icon:account-settings/><spring:theme code="dashboard.support.accountSettings"/>
                                            </a>
                                        </li>--%>
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

                                            <%-- <li>
                                                <a href="#" class="js-realTimeOnlineSupportChatList-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:chat/><spring:theme code="realTimeOnlineSupportChatList.title"/>
                                                </a>
                                            </li> --%>

                                            <li>
                                                <a href="#" class="js-realtimeOnlineSupportEmailUs dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                                    <icon:contactUs-message/><spring:theme code="realtimeOnlineSupportEmailUs.title"/>
                                                </a>
                                            </li>

                                        <%--<li>
                                            <a href="${encodedContextPath}/my-sagia/license/bidding" class="dashboardWidgetAskOurExpert-link">
                                                <icon:bidding-certificates/><spring:theme code="dashboard.support.biddingCertificates"/>
                                            </a>
                                        </li>--%>
                                        <%--<li>
                                           <a href="${encodedContextPath}/service-search" class="dashboardWidgetAskOurExpert-link">
                                                <icon:services/><spring:theme code="dashboard.support.services"/>
                                            </a>
                                        </li>--%>
                                    </ul>                               
                                </div>
                               <%-- <div class="col-lg-5 dashboardWidgetAskOurExpert-seperator">
                                    <div class="dashboardWidgetAskOurExpert-headline">
                                        <spring:theme code="dashboard.support.ask"/>
                                    </div>

                                    <ul class="dashboardWidgetAskOurExpert-list dashboardWidgetAskOurExpert-list_oneColumn">
                                        <li><icon:call/><a href="#" id="scheduleCallButton"><spring:theme code="support.schedulecall"/></a></li>
                                        <li><icon:chat/><a href="#" id="liveChatButton"><spring:theme code="support.livechat"/></a></li>
                                        <li><icon:enquiry/><a href="#" id="makeAnEnquiry"><spring:theme code="support.makeenquiry"/></a></li>
                                    </ul>
                                </div>--%>
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
        </div> 
    </div>
</section>

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

	<section id="newsandupdates" class="newsAndUpdateContainer">
	    <div class="container">
	        <div class="row titleContainer">
	            <div class="col-md-12 title-heading p-0" data-aos="fade-right" data-aos-delay="100">
	                <h3 class="section-title ">${component.title}</h3>
					<a href="${portal.cmsLinkUrl(component.exploreAllUrl)}" class="btn-primary explore-btn explore-gia-btn">
						<spring:theme code="portal.exploreall.button.text"/>&nbsp;
						<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
					</a>
				</div>
			</div>
			
           	<div class="row contentWrapper">
           		<c:forEach var="currentComponent" items="${lastNews}" varStatus="status">
			      	<c:set var="loopCount" value="${(status.index) * 150}" />
			      	<c:url value="/mediaCenter/news" var="newsUrl"/>
			      	<div class="col-lg-4 col-md-6 card-wrapper " data-aos="fade-up" data-aos-delay="${loopCount}">
			        	<div class="flip-card">
			            	<div class="card-img">
			                	<img class="img-fluid" src="${fn:escapeXml(currentComponent.newsDetailsImage.url)}" alt="">
			              	</div>
			              	<div class="card-box p-3 pr-5 home-news-updates-content">
			               		<strong><fmt:formatDate value="${currentComponent.newsDate}" pattern="d" />&nbsp;<fmt:formatDate value="${currentComponent.newsDate}" pattern="MMMM" /></strong>
			                  	<h2>${currentComponent.newsTitle}</h2>
			                  	<p class="home-news-updates-content-p">${currentComponent.newsShortInformation}</p>
	                  			<p><a class="know-more-link" href="${newsUrl}/${currentComponent.uid}"><spring:theme code="portal.sector.opportunity.know.more.label"/>&nbsp;<img src=""></a></p>
	                      	</div>
						</div>
					</div>
				</c:forEach>
        	</div>
    	</div>
	</section>


<script>
    // var displayTutorial = ${displayTutorial};
    // var displayTutorial = false;
</script>
