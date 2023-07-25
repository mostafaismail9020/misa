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
    	<div class="d-inline-flex p-2 text-white text-left">
	    	<div class="p-2 header-home-container">
	    		<p><spring:theme code="text.home.header.newinvestor"/></p>
	    		<h5><a href="#"><spring:theme code="text.home.header.newinvestor.viewopportunities"/></a></h5>
	    	</div>
	    	<div class="p-2 header-home-container">
				<p><spring:theme code="text.home.header.alreadyinvestor"/></p>
				<h5><a href="#"><spring:theme code="text.home.header.alreadyinvestor.login"/></a></h5>
			</div>
		</div>
	</div>

</section>

