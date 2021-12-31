<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
 
               
<c:if test="${not empty backgroundImage}">
    <div class="bussiness_lic_invest_outer" style="background-image: url(${backgroundImage.url})"> 
</c:if>
   <div class="businesses_investment_license_container"> 
    <div class="container">
        <c:if test="${not empty title}">
           <div data-aos="fade-up">${title}</div>
       </c:if>
       <c:if test="${not empty longDescription}">
           <h5 data-aos="fade-up">${longDescription}</h5>
       </c:if>
    </div>
       <!--<c:if test="${not empty backgroundImage}">
           <img class="img-fluid w-40" src="${backgroundImage.url}" alt='${backgroundImage.altText}' title='${backgroundImage.altText}' style="">
       </c:if>-->
       
       <!-- <c:if test="${not empty title}">
           <div data-aos="fade-up" class="aos-init aos-animate">${title}</div>
           <h2 data-aos="fade-up" class="aos-init aos-animate">${title_1}</h2>
            <h2 data-aos="fade-up" class="aos-init aos-animate"><strong>${title_2}</strong></h2>
            <h2 data-aos="fade-up" class="aos-init aos-animate">${title_3}</h2>
            <h2 data-aos="fade-up" class="aos-init aos-animate"><strong>${title_4}</strong> </h2> 
       </c:if>
       <c:if test="${not empty longDescription}">
           <h5 data-aos="fade-up" class="aos-init aos-animate">${longDescription}</h5>
       </c:if> -->
       
   </div>
       

   <div class="container">
       <div class="d-flex pt-4 bussiness_lic_invest_inner">
           <c:forEach var="currentComponent" items="${components}" >
               <cms:component component="${currentComponent}" element="div"  class="col bussiness_lic_img_inner" data-aos="fade-up"/>
           </c:forEach>  
       </div>
   </div>
   
   <div class="text-center download_btn">
       <button class="aos-init aos-animate" data-aos="fade-up"><a target="_blank" href="https://misa.gov.sa/media/1733/misa-service-manual-8th-edition-en-v18.pdf
           "><spring:theme code="portal.investment.guide.investor.licence.download.latest.version.url" text= "Download the latest version of the Investment Manual"/></a><a></a></button><a>
       </a>
   </div>
   
</div>
	

