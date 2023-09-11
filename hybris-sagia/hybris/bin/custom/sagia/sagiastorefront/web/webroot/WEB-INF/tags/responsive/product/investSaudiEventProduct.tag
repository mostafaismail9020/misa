<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<main>

            <div class="article-details-events-page-banner" style="background-image: url(${fn:escapeXml(productData.overviewPicture.url)});">
                <div class="article-details-events-page-banner-container" data-aos="fade-up">
                    <div class="container">
                        <div class="row">

                            <div class="col-md-8">
                                <h1 class="article-details-events-page-general-title">${fn:escapeXml(productData.name)}</h1>
                            </div>

                            <div class="col-md-4" style="position: relative;">
                                <div class="event-top-info-box">
                                    <div class="date" style="position: absolute;">
                                     <span class="day"><fmt:formatDate value="${productData.eventDate}" pattern="d" /></span>
                                     <span class="month"><fmt:formatDate value="${productData.eventDate}" pattern="MMMM" /></span>
                                    </div>
                                    <c:choose>
                                        <c:when test="${fn:length(productData.name) gt 20}">
                                             <h2 class="event-title">${fn:substring(productData.name, 0, 20)}...</h2>
                                        </c:when>
                                        <c:otherwise>
                                              <h2 class="event-title">${fn:escapeXml(productData.name)}</h2>
                                        </c:otherwise>
                                    </c:choose>

                                      <c:choose>
                                        <c:when test="${fn:length(productData.description) gt 150}">
                                             <p class="event-description">${fn:substring(productData.description, 0, 150)}...</p>
                                        </c:when>
                                        <c:otherwise>
                                             <p class="event-description">${fn:escapeXml(productData.description)}</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Article Details Events Page -->
            <section class="article-details-events-page">
                <div class="container">
                    <div class="row mt-2 mb-5">
                         <c:if test="${language eq 'en'}">
                             <a href="${encodedContextPath}/newseventslist">
                                <div class="col-md-12 mt-4 breadcrumb-container">
                                    <span class="breadcrumb-left-icon"></span>
                                  <span class="breadcrumb-page-info"> <spring:theme code="dashboard.newsevents" /></span>
                                </div>
                            </a>
                        </c:if>

                        <c:if test="${language eq 'ar'}">
                             <a href="${encodedContextPath}/newseventslist">
                                <div class="col-md-12 mt-4 breadcrumb-container">
                                   <span class="breadcrumb-page-info"> <spring:theme code="dashboard.newsevents" /></span>
                                    <span class="breadcrumb-left-icon"></span>
                                </div>
                            </a>
                        </c:if>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="event-time-location">
                                <span class="event-time"> ${fn:escapeXml(productData.eventTiming)}</span>
                                <span class="event-location">${fn:escapeXml(productData.eventLocation)}</span>
                            </div>
                            <h1 class="article-details-event-title">${fn:escapeXml(productData.name)}</h1>
                            <p>${productData.summary}</p>

                            <div class="event-info-box-container mt-5 mb-5">
                                <div class="event-info-box">

                                     <c:forEach items="${productData.eventDetailGrid}" var="eventDetailGrid">

                                        <div class="event-info-box-item">
                                                <h2> ${fn:escapeXml(eventDetailGrid.key)}</h2>
                                                <p>  ${fn:escapeXml(eventDetailGrid.value)}</p>
                                            </div>


                                       </c:forEach>


                                </div>
                            </div>



                        </div>
                        <div class="col-md-6">
                            <img alt="" class="article-details-events-page-event-img"
                                 src="${productData.imageUrl}"/>
                        </div>
                    </div>

                    <div class="row mt-5 mb-5">
                        <div class="col-md-12">
                            <div class="subjects-container">
                                <h1 class="title mb-4"><spring:theme code="portal.event.details.subjects"/></h1>
                                <ul>

                                   <c:forEach items="${productData.subjects}" var="subjects">

                                   <li>
                                       <p><b>${fn:escapeXml(subjects.key)} : </b>${fn:escapeXml(subjects.value)}
                                       </p>
                                   </li>
                                   </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5 mb-5">
                        <div class="col-md-12">
                            <div class="highlighted-speakers-container">
                                <h1 class="title mb-4"><spring:theme code="portal.event.details.speakers"/></h1>
                                <div class="highlighted-speakers">

                                    <c:forEach items="${productData.speakers}" var="speakers">

                                    <div class="speaker">
                                            <h2>${fn:escapeXml(speakers.key)}</h2>
                                            <p>${fn:escapeXml(speakers.value)}</p>
                                        </div>

                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5" style= "display: none;>
                        <div class="col-md-12">
                            <h1 class="sponsors-partners-container-title mb-4">
                                <spring:theme code="portal.event.details.sponsors"/>
                            </h1>
                            <div class="sponsors-partners-container">

                                <div class="boxes">
                                    <c:forEach items="${productData.sponsersPartners}" var="sponsorPartner">
                                        <div class="box">
                                            <div class="icon"
                                                 style="background-image: url(${sponsorPartner.url});background-repeat: no-repeat;background-position: center; background-size: cover;"></div>
                                            <p>${fn:escapeXml(sponsorPartner.description)}</p>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </section>
            <!-- Article Details Events Page -->

            <!-- Similar Opportunities Component -->
            <cms:pageSlot position="PortalPageBottom" var="slotComponent">
               <cms:component component="${slotComponent}"/>
            </cms:pageSlot>
</main>
