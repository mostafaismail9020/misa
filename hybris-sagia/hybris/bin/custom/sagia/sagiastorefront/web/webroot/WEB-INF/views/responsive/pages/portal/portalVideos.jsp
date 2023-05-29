<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage" value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
	<!--
	<script>
    document.querySelector("html").style.cssText = 'background:#000!important';
    document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
    window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
    window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
    window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
    (function(s,e,ss,i,o,n){
    if(s.console && s.console.log) { s.console.log(i);};
    o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
    n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
    })(window,document,'script','SessionForward Loaded.');
    </script>
	-->

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
			                   	<c:url value="/mediaCenter/videos" var="WebinarsUrl"/>
			                   	<c:choose>
									<c:when test="${param.videoType eq 'SUCCESS_STORY'}">
			                     		<a class="nav-link" id="pills-year-tab" href="${WebinarsUrl}" role="tab" aria-controls="pills-year" 
			                     				aria-selected="true"><spring:theme code="portal.media.webinar" text="Webinar"/></a>
			                     	</c:when>
			                     	<c:otherwise>
			                         	<a class="nav-link active" id="pills-year-tab" href="${WebinarsUrl}" role="tab" aria-controls="pills-year" 
			                         			aria-selected="true"><spring:theme code="portal.media.webinar" text="Webinar"/></a>
			                     	</c:otherwise>
			                   	</c:choose>
			           		</li>
		                   	<li class="nav-item">
		                   		<c:url value="/mediaCenter/videos?videoType=SUCCESS_STORY" var="successStoriesUrl"/>
		                   		<c:choose>
		                     		<c:when test="${param.videoType eq 'SUCCESS_STORY'}">
		                         		<a class="nav-link active" id="pills-news-tab" href="${successStoriesUrl}" role="tab" aria-controls="pills-news" 
		                         				aria-selected="false"><spring:theme code="portal.media.success.stories" text="Success Stories"/></a>
		                     		</c:when>
		                     		<c:otherwise>
		                         		<a class="nav-link" id="pills-news-tab" href="${successStoriesUrl}" role="tab" aria-controls="pills-news" 
		                         				aria-selected="false"><spring:theme code="portal.media.success.stories" text="Success Stories"/></a>
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
                         		<c:forEach items="${searchPageData.results}" var="videoComponent">
                                	<div class="col-sm-6 news vedio_outer mb-5">
                                    	<div class="video-player-container">
                                        	<div class="embed-responsive embed-responsive-16by9">
                                            	<iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" 
                                            			src="${fn:escapeXml(videoComponent.embedURL.url)}" frameborder="0" 
                                            			allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                            	</iframe>
                                           	</div>
                                           	<div class="videos-card-inner">
	                                           	<h3>${videoComponent.title}</h3>
	                                           	<c:if test="${not empty videoComponent.companyLogo}">
	                                           		<img class="img-fluid video-companyLogo" src="${fn:escapeXml(videoComponent.companyLogo.url)}" 
	                                           				alt="${videoComponent.title}">
	                                           	</c:if>
                                           	</div>                                         
										</div>
                                   </div>
                               	</c:forEach>
                     		</div>
                 		</div>                
                 		<div class="tab-pane fade" id="pills-news" role="tabpanel" aria-labelledby="pills-news-tab">
                     		<div class="row">
                         		<c:forEach items="${searchPageData.results}" var="videoComponent">
                                	<div class="col-sm-6  news vedio_outer mb-5">
                                    	<div class="video-player-container">
                                        	<div class="embed-responsive embed-responsive-16by9">
                                            	<iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="471" 
                                            			src="${fn:escapeXml(videoComponent.embedURL.url)}" frameborder="0" 
                                            			allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                                            	</iframe>
                                           	</div>
                                     	</div>
                                   	</div>
                               	</c:forEach>
							</div>
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
						                    <c:when test="${param.videoType eq 'SUCCESS_STORY'}">
						                    	<c:if test="${hasPreviousPage}">
						                       		<li class="page-item prev-item">
						                       			<a class="page-link waves-effect waves-light arrow-right" href="${successStoriesUrl}${urlWithPage}${searchPageData.pagination.currentPage-1}">
						                           			<img src="${commonResourcePath}/images/arrow-right.png" class="img-responsive">
						                           		</a>
						                           	</li>
						                    	</c:if>
						                    	<c:set var="count" value="1"/>
						                    	<c:forEach begin="${count}" end="${searchPageData.pagination.numberOfPages}" var="currentPage">
							                        <c:choose>
							                            <c:when test="${currentPage eq param.page + 1}">
							                                <li class="page-item active"><a class="page-link waves-effect waves-light"
							                                        href=${successStoriesUrl}${urlWithPage}${currentPage-1}>${currentPage}</a>
							                            	</li>
							                            </c:when>
							                            <c:otherwise>
							                                <li class="page-item"><a class="page-link waves-effect waves-light"
							                                        href=${successStoriesUrl}${urlWithPage}${currentPage-1}>${currentPage}</a>
							                                </li>
							                            </c:otherwise>
							                        </c:choose>
							                    </c:forEach>
							                    <c:if test="${hasNextPage}">
							                        <li class="page-item next-item">
							                        	<a class="page-link waves-effect waves-light arrow-left" href="${successStoriesUrl}${urlWithPage}${searchPageData.pagination.numberOfPages-1}">
							                            	<img src="${commonResourcePath}/images/arrow-left.png" class="img-responsive" >
							                            </a>
							                        </li>
							                    </c:if>
											</c:when>                    
					                    <c:otherwise>
					                    	<c:if test="${hasPreviousPage}">
					                            <li class="page-item prev prev-item">
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
						                        	<a class="page-link waves-effect waves-light arrow-left" href="${url}${searchPageData.pagination.numberOfPages-1}">
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
			</div>
		</section>
		</main>
	
		<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	    	<cms:component component="${slotComponent}"/>
	    </cms:pageSlot>
	
	 </jsp:body>
 </template:portalpage>
 