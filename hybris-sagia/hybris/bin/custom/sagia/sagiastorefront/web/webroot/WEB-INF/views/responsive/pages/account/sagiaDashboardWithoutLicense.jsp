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

<%--@elvariable id="dashboardBanner" type="java.lang.String"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>
<%--@elvariable id="profilePicture" type="java.lang.String"--%>
<%--@elvariable id="user" type="de.hybris.platform.commercefacades.user.data.CustomerData"--%>

<section class="mainSection mainSection_grey mainSection_noPadding">
    <form:form id="bannerUploadForm" action="dashboard/update-dashboardPic" method="post" enctype="multipart/form-data" >
        <input id="file" name="file" class="form-control js-inputFile dashboardBannerUpload" type="file" value="" accept="image/jpeg,application/pdf" style="display: none;">
            <c:choose>
                <c:when test="${empty dashboardBanner and (not user.shouldDisplaySetCompanyPhotoOption)}">
                    <div class="dashboardHead" id="dashboardHeadId" style="display: none;">
                 </c:when>
                 <c:otherwise>
                    <div class="dashboardHead" id="dashboardHeadId">
                 </c:otherwise>
            </c:choose>
            <label for="file">
                <c:choose>
                    <c:when test="${empty dashboardBanner}">
                        <div class="dashboardHeadImage dashboardHeadImage_defaultBackground"></div>
                        <div class="dashboardHeadAdd">
                            <div class="dashboardHeadAdd-text"><icon:camera/><spring:theme code="dashboard.addcoverphoto"/></div>
                            <a id="setCompanyPhotoAnchor" class="dashboardHeadAdd-link" style="cursor: pointer; display: none">
                                <icon:cross/><spring:theme code="dashboard.message.not.now"/>
                            </a>
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
                                <div class="globalMessage-action">
                                   <a href="${encodedContextPath}/simulator/license-apply" class="btn btn_round btn-warning btn_outline"><spring:theme code="dashboard.withoutlicense.startsimulation"/></a>
                                    <button class="btn btn_round" onclick="applyNewTnC(event,'NewApply');"><spring:theme code="dashboard.withoutlicense.applyfornewlicense"/></button>
                                 </div>

                                <div class="globalMessage-msg">
                                    <div class="globalMessage-icon"><icon:warning/></div>
                                    <spring:theme code="dashboard.withoutlicense.notappliedforlicenseyet"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPadding">
    <div class="container">
        <div class="dashboardUser">
            <div class="dashboardUser-wrapper">
                <div class="dashboardUser-left">
                    <div class="row">
                        <div class="col">
                            <div class="dashboardUser-image">
                                <button type="button" class="btn btn_link dashboardUser-image-add"><icon:plus/></button>
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
                                    <div class="dashboardUser-value"><c:out value='${user.country.phoneprefix}'/><c:out value='${user.mobileNumber}'/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
     <dashboard:opportunityTickets></dashboard:opportunityTickets>
    <dashboard:sectorAndOpportunity sector="${currentCustomerSector}"></dashboard:sectorAndOpportunity>
        <div id="dashboardNoLicenseHelper"></div>
        <div class="row">
            <div class="col-md-8">
                <div class="dashboardWidget js-dashboardWidget dashboardWidget_noRadiusRight">
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
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
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
</div>
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
                    <div class="eServiceTutorial-actions">
                        <a class="btn btn_slim js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                    </div>
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
                    <div class="eServiceTutorial-actions">
                        <a class="btn btn_slim js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                    </div>
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
</script>
