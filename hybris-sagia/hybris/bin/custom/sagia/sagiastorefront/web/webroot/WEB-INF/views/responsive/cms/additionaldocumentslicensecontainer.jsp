<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

               
<div class="additional_documents_license_container container-fluid invest_lic_outer additional_lic_outer text-center py-4" >
	
	<!--<c:if test="${not empty backgroundImage}">
		<img class="img-fluid w-100" src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
	</c:if>-->
	
	<c:if test="${not empty title}">
		<div data-aos="fade-up">${title}</div>
		<!-- <h1 class="title-heading"></h1> -->
	</c:if> 
		
	<div class="additional_lic_inner"  style="background-image: url(${backgroundImage.url});background-size: 100% 100%;background-repeat: no-repeat;">
		<div class="additional_lic_inner_2d_flex">
			<c:forEach var="currentComponent" items="${components}" >
				<cms:component component="${currentComponent}" element="div" class="additional_lic_inner_box_even"/>
			</c:forEach>
		</div>

		<div class="text-center download_btn">
		<c:url value="/mediaCenter/downloadResoruce/investor-guide-container" var="resourcedownloadURL"/>
			<button class=""><a href="${resourcedownloadURL}" target="_blank"><spring:theme code="portal.investment.guide.investor.licence.download.latest.version.url" text= "Download the latest version of the Investment Manual"/></a></button>
			<button class=""><a href="https://investsaudi.sa/${language}/login#register-apply" target="_blank"><spring:theme code="portal.investment.guide.investor.licence.visit.the.licence.portal.url" text= "Visit the licensing portal to register as an investor today"/></a> </button>
		</div>
		
	</div>
</div>

	

