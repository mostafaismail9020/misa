<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
            <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
                        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



                            <template:portalpage pageTitle="${pageTitle}">



                                <jsp:body>
                                    <header:portalPageTitle/>
                                    <cms:pageSlot position="PortalPageTop" var="slotComponent">
                                        <cms:component component="${slotComponent}" />
                                    </cms:pageSlot>
                                    <main>
                                        <div class="banner-section" style="background-image: url(${provinceDetails.bannerImage.url});">
                                            <div class="banner-container aos-init aos-animate" data-aos="fade-up">
                                                <h1>${provinceDetails.bannerHeader}</h1>
                                                <h2>${provinceDetails.bannerText}</h2>
                                            </div>
                                        </div>

                                        <div class="province-container mt-5 pt-5">
                                            <div class="row h-100 m-0 strategic-sector-sWrapper">
                                                <div class="col-lg-6 pl-0 map-area links map-bg-area">
                                                    <img class="img-fluid mg-background map-bg-area-img" src="${strategicDetails.backgroundImage.url}" alt="" />
                                                </div>
                                                <div class="col-lg-6 content-area pl-5">
                                                    <p class="pt-5 mt-5 pr-5">${strategicDetails.longDescription}</p>
                                                    <c:if test="${not empty strategicDetails.localizedStats}">
                                                        <div class="area">
                                                            <c:forEach var="entry" items="${strategicDetails.localizedStats}" varStatus="loop">
                                                            <c:if test="${!loop.last}">
                                                                <div class="sec-area">
                                                                    <%-- <strong>${entry.key}</strong>
									                                <strong>${entry.value}</strong> --%>
                                                                        <strong>${entry.key}</strong>
                                                                        <p>${entry.value}</p>
                                                                </div>
                                                                </c:if>
                                                                <c:if test="${loop.last}">
                                                                <div class="sec-area border-0 pl-5">
                                                                    <%-- <strong>${entry.key}</strong>
									                                <strong>${entry.value}</strong> --%>
                                                                        <strong>${entry.key}</strong>
                                                                        <p>${entry.value}</p>
                                                                </div>
                                                                </c:if>

                                                            </c:forEach>
                                                        </div>
                                                    </c:if>
                                                    <div class="sub-sectors">
                                                        <h6>${strategicDetails.sectorHeader}</h6>
                                                        <ul>
                                                            <c:forEach var="sector" items="${sectors}">

                                                                <li>
                                                                    <img class="img-fluid" src="${sector.imageIcon.url}" alt="" />
                                                                    <strong>${sector.headerText}</strong>
                                                                </li>


                                                            </c:forEach>
                                                        </ul>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="facts-container">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area">
                                                            <h1 class="title-heading">${provinceDetails.keyFactsHeader}</h1>
                                                        </div>
                                                    </div>
                                                    <div class="key-fact-section pt-5 mt-5">
                                                        <c:forEach var="keyFact" items="${keyFacts}">
                                                            <div class="macro_economic_component">
                                                                <div class="key-fact-box aos-init" data-aos="fade-up" data-aos-delay="0">
                                                                    <div class="macro_economic_icon box-img">
                                                                        <c:if test="${not empty keyFact.imageIcon}">
                                                                            <img class="js-responsive-image achievement_header_icon" src="${keyFact.imageIcon.url}" alt="${keyFact.imageIcon.altText}" title="${keyFact.imageIcon.altText}" style="">
                                                                        </c:if>
                                                                    </div>

                                                                    <c:if test="${not empty keyFact.headerText}">
                                                                        <h3 class="count">${keyFact.headerText}</h3>
                                                                    </c:if>

                                                                    <c:if test="${not empty keyFact.text}">
                                                                        <p class="desc">${keyFact.text}</p>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="key-strength-container">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area  pt-5 mt-5">
                                                            <h1 class="title-heading">${provinceDetails.keyStrengthsHeader}</h1>
                                                        </div>
                                                    </div>
                                                    <div class="key-strength-section pt-5 mt-5">
                                                        <c:forEach var="keyStrength" items="${keyStrengths}">
                                                            <div class="key-strength-box">
                                                                <img class="img-fluid" src="${keyStrength.image.url}" alt="${keyStrength.image.altText}" title="${keyStrength.image.altText}" />
                                                                <c:if test="${not empty keyStrength.text}">
                                                                    <div class="overlay-txt">${keyStrength.text}</div>
                                                                </c:if>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="investment-op-container mt-5 pt-5">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="col-md-12 title-area mt-5 pt-5">
                                                            <h1 class="title-heading mb-5 pb-5">${provinceDetails.opportunitiesHeader}</h1>
                                                            <div class="investment-opportunities">
                                                                <c:forEach var="opportunity" items="${investmentOpportunities}">
                                                                    <div class="macro_economic_component">
                                                                        <div class="panel-box-block d-flex aos-init" data-aos="fade-up" data-aos-delay="0">
                                                                            <div class="macro_economic_icon box-img">
                                                                                <c:if test="${not empty opportunity.iconImage.url}">
                                                                                    <img class="js-responsive-image achievement_header_icon" src="${opportunity.iconImage.url}" alt="${opportunity.iconImage.altText}" title="${opportunity.iconImage.altText}" style="">
                                                                                </c:if>
                                                                            </div>
                                                                            <div class="box-img-right">
                                                                                <c:if test="${not empty opportunity.headerText}">
                                                                                    <h3 class="count">${opportunity.headerText}</h3>
                                                                                </c:if>

                                                                                <c:if test="${not empty opportunity.text}">
                                                                                    <p class="desc">${opportunity.text}</p>
                                                                                </c:if>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>



                                        </div>

                                        <%-- <div class="inc-strategic-details row" style="background-image: url(${strategicDetails.backgroundImage.url});">
<p>${strategicDetails.sectorHeader}</p>
<p>${strategicDetails.longDescription}</p>
<c:forEach var="sector" items="${sectors}">
<div class="row"><img class="img-fluid" src="${sector.imageIcon.url}" alt=""/></div>
<h4>${sector.headerText}</h4>
</c:forEach>
</div>

<div class="inc-facts row" >
<p>${provinceDetails.keyFactsHeader}</p>

<c:forEach var="keyFact" items="${keyFacts}">
<div class="row"><img class="img-fluid" src="${keyFact.imageIcon.url}" alt=""/></div>
<h4>${keyFact.headerText}</h4>
<p>${keyFact.text}</p>
</c:forEach>
</div>



<div class="inc-strengths row" >
<p>${provinceDetails.keyStrengthsHeader}</p>
<c:forEach var="keyStrength" items="${keyStrengths}">
<div class="row"><img class="img-fluid" src="${keyStrength.image.url}" alt=""/></div>
<p>${keyStrength.text}</p>
</c:forEach>
</div>

<div class="inc-opportunities row" >
<p>${provinceDetails.opportunitiesHeader}</p>
<c:forEach var="opportunity" items="${investmentOpportunities}">
<div class="row"><img class="img-fluid" src="${opportunity.iconImage.url}" alt=""/></div>
<p>${keyStrength.headerText}</p>
<p>${keyStrength.text}</p>
</c:forEach>
</div> --%>


                                    </main>

                                </jsp:body>

                            </template:portalpage>