<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>


<section id="hero" class="d-flex align-items-center">
    
	<img class="home-banner" src="${component.backgroundBannerImage.url}" alt="homepage-banner" loading="lazy">
	<div class="container" >
  		<h1 data-aos="fade-up" data-aos-easing="ease" data-aos-delay="300" class="text-md-left text-center">${component.title}</h1>
    	<div class="d-inline-flex p-2 text-white text-left">
	    	<div class="p-2 header-home-container">
	    		<p>New Investor?</p>
	    		<h5><a href="#">View opportunities</a></h5>
	    	</div>
	    	<div class="p-2 header-home-container">
				<p>Already Investor?</p>
				<h5><a href="#">Log in</a></h5>
			</div>
		</div>
	</div>

</section>

