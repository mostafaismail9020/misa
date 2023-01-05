<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
	<section id="newsandupdates" class="newsAndUpdateContainer">
	    <div class="container">
	        <div class="row titleContainer">
	            <div class="col-md-12 title-heading p-0" data-aos="fade-right" data-aos-delay="100">
	                <h3 class="section-title ">${component.title}</h3>
					<a href="${portal.cmsLinkUrl(component.exploreAllUrl)}" class="btn-primary explore-btn explore-gia-btn">
						<spring:theme code="portal.exploreall.button.text"/>&nbsp;
						<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive" loading="lazy"></a>
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
			                	<img class="img-fluid" src="${fn:escapeXml(currentComponent.newsDetailsImage.url)}" alt="" loading="lazy">
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

	<section id="socialMediaFeedEn" class="socialMediaFeedEn" >
    		<div class="container where-to-invest">
    			<div class="row">
    				<div class="col-md-12">
    					<h2 class="title-heading">SOCIAL MEDIA</h2>
    				 </div>
    			 </div>
    		</div>
    		<div class="tagembed-container" style=" width:100%;height:100%;overflow: auto;"><div class="tagembed-socialwall" data-wall-id="80663"></div> </div>
    	</section>

    	<section id="socialMediaFeedAr" class="socialMediaFeedAr" style="display: none;">
    		<div class="container where-to-invest">
    			<div class="row">
    				<div class="col-md-12">
    					<h2 class="title-heading"> <spring:theme code="feedTitle.arabic"/> </h2>
    				 </div>
    			 </div>
    		</div>
    		<div class="tagembed-container" style=" width:100%;height:100%;overflow: auto;"><div class="tagembed-socialwall" data-wall-id="81404"></div>
    	</div>
    	</section>
    	<script src="//widget.tagembed.com/embed.min.js" type="text/javascript"></script>
</c:if>
