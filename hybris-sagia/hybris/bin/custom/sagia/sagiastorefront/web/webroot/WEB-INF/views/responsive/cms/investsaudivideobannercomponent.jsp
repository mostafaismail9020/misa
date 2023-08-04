<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section id="hero" class="d-flex align-items-center">
    
	<img class="home-banner" src="${component.backgroundBannerImage.url}" alt="homepage-banner" loading="lazy">
	<div class="container" >
  		<h1 data-aos="fade-up" data-aos-easing="ease" data-aos-delay="300">${component.title}</h1>
    	<div class="header-home-container-wrapper text-white">
	    	<div class="header-home-container">
	    		<p><spring:theme code="text.home.header.newinvestor"/></p>
	    		<h5>
	    			<strong>
	    				<a href="${portal.cmsLinkUrl(component.viewOpportunities)}"><spring:theme code="text.home.header.newinvestor.viewopportunities"/></a>
	    			</strong>
	    		</h5>
	    	</div>
	    	<div class="header-home-container">
				<p><spring:theme code="text.home.header.alreadyinvestor"/></p>
				<h5>
					<strong>
						<a href="${portal.cmsLinkUrl(component.login)}"><spring:theme code="text.home.header.alreadyinvestor.login"/></a>
					</strong>
				</h5>
			</div>
		</div>
	</div>

</section>

