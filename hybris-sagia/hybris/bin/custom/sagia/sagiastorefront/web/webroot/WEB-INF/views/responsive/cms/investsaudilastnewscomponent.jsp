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
	            <div class="col-lg-12 pt-4 pt-lg-0 content mx-auto aos-init" data-aos="fade-right" data-aos-delay="100">
	                <h2 class="section-title ">${component.title}</h2>
				</div>
			</div>
			
           	<div class="row contentWrapper">
           		<c:forEach var="currentComponent" items="${lastNews}" varStatus="status">
			      	<c:set var="loopCount" value="${(status.index) * 150}" />
			      	<c:url value="/mediaCenter/news" var="newsUrl"/>
			      	<div class="col-lg-4 col-md-6 card-wrapper " data-aos="fade-up" data-aos-delay="${loopCount}">
			        	<div class="flip-card flip-card-custom row">
			        		<div class="col-md-5 col-4">
			        			<a class="know-more-link" href="${newsUrl}/${currentComponent.uid}">
			        				<img class="img-fluid" src="${fn:escapeXml(currentComponent.newsDetailsImage.url)}" alt="" loading="lazy">
			        			</a>
			        		</div>
			        		<div class="col-md-7 col-8">
			        			<a class="know-more-link" href="${newsUrl}/${currentComponent.uid}">
			        				<p>${currentComponent.newsTitle}</p>
								</a>
								<span class="badge badge-pill badge-pill-custom">
									<fmt:formatDate value="${currentComponent.newsDate}" type="both" dateStyle="long" timeStyle="long" pattern="d MMMM yyyy" />
								</span>
			        		</div>
						</div>
					</div>
				</c:forEach>
        	</div>
			<div class="row explore-keys-btn">
				<a href="${portal.cmsLinkUrl(component.exploreAllUrl)}" class="btn btn-primary-fill btn-video">
					<spring:theme code="portal.seemoreupdates.button.text"/>&nbsp;
				</a>
			</div>
    	</div>
	</section>
</c:if>
