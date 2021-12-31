<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage" value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
        <!-- Breadcurms -->
        <section id="page-breadcrums" class="page-breadcrums">
            <div class="container">
                <div class="row">
                    <nav aria-label="breadcrumb">
                    	<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                    </nav>
                </div>
            </div>
        </section>
        <!--End of  Breadcurms --> 
                                
        <div class="container mt-5">
			<div class="row">
            	<div class="col-md-12">
                	<ul class="nav nav-pills  tabs-normal" id="pills-tab" role="tablist">
                  		<li class="nav-item">
                  			<c:url value="/mediaCenter/events" var="upcomingEventsUrl"/>
		                  	<c:choose>
		                        <c:when test="${param.isCurrentYear eq 'false'}">
		                            <a class="nav-link" id="pills-year-tab" href="${upcomingEventsUrl}" role="tab" 
		                            	aria-controls="pills-year" aria-selected="true"><spring:theme code="portal.media.events.upcoming.events" text = "Upcoming Events"/></a>
		                        </c:when>
		                        <c:otherwise>
		                            <a class="nav-link active" id="pills-year-tab" href="${upcomingEventsUrl}" role="tab" 
		                            	aria-controls="pills-year" aria-selected="true"><spring:theme code="portal.media.events.upcoming.events" text = "Upcoming Events"/></a>
		                        </c:otherwise>
		                    </c:choose>
						</li>
                        <li class="nav-item">
							<c:url value="/mediaCenter/events?isCurrentYear=${ycommerce:encodeUrl('false')}" var="pastEventsUrl"/>
                          	<c:choose>
                                <c:when test="${param.isCurrentYear eq 'false'}">
                                    <a class="nav-link active" id="pills-news-tab" href="${pastEventsUrl}" role="tab" 
                                    		aria-controls="pills-news" aria-selected="false"><spring:theme code="portal.media.events.past.events" text = "Past Events"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link" id="pills-news-tab" href="${pastEventsUrl}" role="tab" 
                                    		aria-controls="pills-news" aria-selected="false"><spring:theme code="portal.media.events.past.events" text = "Past Events"/></a>
                                </c:otherwise>
                            </c:choose>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<section class="tab-section">
        	<div class="container">
          		<div class="tab-content d-inline" id="pills-tabContent">
            		<div class="tab-pane fade show active" id="pills-year" role="tabpanel" aria-labelledby="pills-year-tab">
                		<div class="row">
		                    <c:url value="/mediaCenter/events" var="eventsUrl"/>
		                    <c:forEach items="${searchPageData.results}" var="eventComponent">
		                        <div class="col-12 col-lg-4 mb-5">
		                            <div class="news-card">
		                             	<div class="news-date text-center">
		                                	<div class="day"><fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" /></div>
		                                    <div class="month"><fmt:formatDate value="${eventComponent.eventStartDate}" pattern="MMMM" /></div>
		                             	</div>
		                                <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}" 
		                                		alt="${eventComponent.eventName}">
	                                    <div class="news-card-inner">
	                                        <h3 title="${eventComponent.eventName}">${eventComponent.eventName}</h3>
	                                        <p>${eventComponent.eventShortInformation}</p>
	                                        <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventComponent.uid}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
	                                        	<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
	                                        </a>
	                                    </div>
	                                </div>
		                   		</div>
		                    </c:forEach>
                		</div>
                        <div class="container">
                        	<div class="row wow fadeIn" data-wow-delay="0.3s">
                               	<div class="col-lg-12 col-md-12 col-sm-12">
                                	<ul class="pagination pg-darkgrey pagination-bottom mt-4">
                                       	<c:set var="urlWithoutParameter" value="${fn:escapeXml(requestURI)}?page="/>
                                       	<c:set var="urlWithParameter" value="${fn:escapeXml(requestURI)}?${fn:escapeXml(queryString)}&page="/>
                                       	<c:set var="urlWithPage" value="${fn:escapeXml(requestURI)}${fn:escapeXml(queryString)}&page="/>
                                       	<c:set var="url" value="${empty fn:escapeXml(queryString) ? urlWithoutParameter : urlWithParameter}"/>
                                       	<c:choose>
	                        
	                                    <c:when test="${param.isCurrentYear eq 'false'}">
                                       	<c:if test="${hasPreviousPage}">
                                        	<li class="page-item prev-item">
                                        		<a class="page-link waves-effect waves-light" href="${pastEventsUrl}${urlWithPage}${searchPageData.pagination.currentPage-1}">
                                            		<img src="${commonResourcePath}/images/arrow-right.png" class="img-responsive">
                                            	</a>
                                            </li>
										</c:if>
                                   		<c:set var="count" value="1"/>
                                        <c:forEach begin="${count}" end="${searchPageData.pagination.numberOfPages}" var="currentPage">
                                        	<c:choose>
                                            	<c:when test="${currentPage eq param.page + 1}">
                                                	<li class="page-item active">
                                                		<a class="page-link waves-effect waves-light" href=${pastEventsUrl}${urlWithPage}${currentPage-1}>${currentPage}</a>
                                                	</li>
                                                </c:when>
                                                <c:otherwise>
                                                	<li class="page-item">
                                                		<a class="page-link waves-effect waves-light" href=${pastEventsUrl}${urlWithPage}${currentPage-1}>${currentPage}</a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${hasNextPage}">
                                            <li class="page-item next-item">
                                            	<a class="page-link waves-effect waves-light" href="${pastEventsUrl}${urlWithPage}${searchPageData.pagination.numberOfPages-1}">
                                            		<img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive" >
                                            	</a>
                                           </li>
                                        </c:if>
                                        </c:when>
                                        <c:otherwise>
                                        <c:if test="${hasPreviousPage}">
                                        	<li class="page-item prev-item">
                                        		<a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.currentPage-1}">
													<img src="${commonResourcePath}/images/arrow-right.png" class="img-responsive">
                                            	</a>
                                            </li>
										</c:if>
                                   		<c:set var="count" value="1"/>
                                        <c:forEach begin="${count}" end="${searchPageData.pagination.numberOfPages}" var="currentPage">
                                        	<c:choose>
                                            	<c:when test="${currentPage eq param.page + 1}">
                                                	<li class="page-item active">
                                                		<a class="page-link waves-effect waves-light" href=${url}${currentPage-1}>${currentPage}</a>
                                                	</li>
                                                </c:when>
                                                <c:otherwise>
                                                	<li class="page-item">
                                                		<a class="page-link waves-effect waves-light" href=${url}${currentPage-1}>${currentPage}</a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${hasNextPage}">
                                            <li class="page-item next-item">
                                            	<a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.numberOfPages-1}">
                                            		<img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive" >
                                            	</a>
                                           </li>
                                        </c:if>
                                        </c:otherwise>
                                        </c:choose>
                                    </ul>    
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="tab-pane fade" id="pills-news" role="tabpanel" aria-labelledby="pills-news-tab">
                       <div class="row">
                           <c:url value="/mediaCenter/events" var="eventsUrl"/>
                           <c:forEach items="${searchPageData.results}" var="eventComponent">
                               <div class="col-12 col-lg-4 mb-5">
                                   <div class="news-card">
                                       <div class="news-date text-center">
                                           <div class="day"><fmt:formatDate value="${eventComponent.eventStartDate}" pattern="d" /></div>
                                       <div class="month"><fmt:formatDate value="${eventComponent.eventStartDate}" pattern="MMMM" /></div>
                                       </div>
                                       <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(eventComponent.eventThumbnailImage.url)}" 
                                       			alt="${eventComponent.eventName}">
                                       <div class="news-card-inner">
                                           <h3 title="${eventComponent.eventName}">${eventComponent.eventName}</h3>
                                           <p>${eventComponent.eventShortInformation}</p>
                                           <a class="btn btn-primary-fill btn-knowmore" href="${eventsUrl}/${eventComponent.uid}"><spring:theme code="portal.media.know.more" text="Know More"/>&nbsp;
                                           		<span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/know-more.png" alt=""/></span>
                                           </a>
                                       </div>
                                   </div>
                               </div>
							</c:forEach>
                       	</div>
                        <div class="container">
                            <div class="row wow fadeIn" data-wow-delay="0.3s">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <ul class="pagination pg-darkgrey pagination-bottom mt-4">
                                        <c:set var="urlWithoutParameter" value="${fn:escapeXml(requestURI)}?page="/>
                                        <c:set var="urlWithParameter" value="${fn:escapeXml(requestURI)}?${fn:escapeXml(queryString)}&page="/>
                                        <c:set var="url" value="${empty fn:escapeXml(queryString) ? urlWithoutParameter : urlWithParameter}"/>
                                        <c:if test="${hasPreviousPage}">
                                            <li class="page-item prev-item">
                                            	<a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.currentPage-1}">
                                            		<i class="fa blue-text fa-long-arrow-left" aria-hidden="true"></i>
                                            	</a>
                                            </li>
                                        </c:if>
	                                    <c:set var="count" value="1"/>
	                                    <c:forEach begin="${count}" end="${searchPageData.pagination.numberOfPages}" var="currentPage">
	                                        <c:choose>
	                                            <c:when test="${currentPage eq param.page + 1}">
	                                                <li class="page-item active"><a class="page-link waves-effect waves-light"
	                                                        href=${url}${currentPage-1}&isCurrentYear=${param.isCurrentYear}>${currentPage}</a></li>
	                                            </c:when>
	                                            <c:otherwise>
	                                                <li class="page-item"><a class="page-link waves-effect waves-light"
	                                                        href=${url}${currentPage-1}&isCurrentYear=${param.isCurrentYear}>${currentPage}</a></li>
	                                            </c:otherwise>
	                                        </c:choose>
	                                    </c:forEach>
	                                    <c:if test="${hasNextPage}">
	                                        <li class="page-item next-item">
	                                        	<a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.currentPage+1}">
	                                        		<i class="fa blue-text fa-long-arrow-right" aria-hidden="true"></i>
	                                        	</a>
	                                        </li>
	                                    </c:if>
                                	</ul>
                                </div>
                            </div>
                        </div>
                  	</div>
				</div>
			</div>
		</section>

	</main>

    <cms:pageSlot position="PortalPageBottom" var="slotComponent">
        <cms:component component="${slotComponent}"/>
    </cms:pageSlot>

    </jsp:body>
</template:portalpage>
