<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="contactus" class="contact-us">
	<div class="container-fluid p-0">
		<div class="row contactus-content">
			<div class="col-md-12 p-0">
				<img class="img-fluid w-100" src="${fn:escapeXml(component.backgroundImage.url)}" alt="" />
				<h3 class="aos-init" data-aos="fade-right" data-aos-delay="100">${component.title}</h3>
				<a href="${portal.cmsLinkUrl(component.contactUsLink)}" class="txt-link home-contact-us">${component.contactUsLink.linkName} 
				<img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
			</div>
		</div>
	</div>
</section>