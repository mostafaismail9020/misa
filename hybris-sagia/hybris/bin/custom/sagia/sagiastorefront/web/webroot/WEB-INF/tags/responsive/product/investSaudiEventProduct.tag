<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<main>
            <div class="article-details-events-page-banner" style="background-image: url('https://investsaudi.sa/medias/home-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMTEzMTN8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaGM3L2hkOS84OTIzMzU4NjkxMzU4LmpwZ3w1YWJjYTA5NDY1MjM0NGZiYWJhZTMzOGJkZTc3MmVmYmFjYTg0MzBjODczZTVkOGM1MTgzZTJmN2VkZGY3NDhk');">
                <div class="article-details-events-page-banner-container" data-aos="fade-up">
                    <div class="container">
                        <div class="row">

                            <div class="col-md-8">
                                <h1 class="article-details-events-page-general-title">${fn:escapeXml(productData.name)}</h1>
                            </div>

                            <div class="col-md-4" style="position: relative;">
                                <div class="event-top-info-box">
                                    <div class="date" style="position: absolute;">
                                        <span class="day">30</span>
                                        <span class="month">DEC</span>
                                    </div>
                                    <h2 class="event-title">${fn:escapeXml(productData.name)}</h2>
                                    <p class="event-description">${productData.description}</p>
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
                        <a href="${encodedContextPath}/newseventslist">
                            <div class="col-md-12 breadcrumb-container">
                                <span class="breadcrumb-left-icon"></span>
                                <span class="breadcrumb-page-info"> <spring:theme code="dashboard.newsevents" /></span>
                            </div>
                        </a>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="event-time-location">
                                <span class="event-time"> ${productData.eventTiming}</span>
                                <span class="event-location">${productData.eventLocation}</span>
                            </div>
                            <h1 class="article-details-event-title">${fn:escapeXml(productData.name)}</h1>
                            <p>${productData.summary}</p>

                            <div class="event-info-box-container mt-5 mb-5">
                                <div class="event-info-box">

                                     <c:forEach items="${productData.eventDetailGrid}" var="eventDetailGrid">

                                        <div class="event-info-box-item">
                                                <h2>${eventDetailGrid.key}</h2>
                                                <p> ${eventDetailGrid.value}</p>
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
                                <h1 class="title mb-3">Subjects</h1>
                                <ul>

                                   <c:forEach items="${productData.subjects}" var="subjects">

                                   <li>
                                       <p><b>${subjects.key}: </b>  ${subjects.value}
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
                                            <h2>${speakers.key}</h2>
                                            <p>${speakers.value}</p>
                                        </div>

                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-5 mt-5">
                        <div class="sponsors-partners-container">
                            <h1 class="title mb-3">Sponsors & Partners</h1>
                              <div class="boxes">
                             <c:forEach items="${productData.sponsersPartners}" var="sponsorPartner">
                                <div class="box">
                                    <div class="icon" style="background-image: url(${sponsorPartner.url});background-repeat: no-repeat;background-position: center; background-size: cover;"></div>
                                    <p>${sponsorPartner.description}</p>
                                </div>
                                 </c:forEach>

                            </div>
                        </div>
                    </div>


                </div>
            </section>
            <!-- Article Details Events Page -->
        </main>
<cms:pageSlot position="PortalPageBottom" var="slotComponent">
    <cms:component component="${slotComponent}"/>
</cms:pageSlot>
