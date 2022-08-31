<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

 
<div class="achievements-section">
     <div class="achievement_header w-100 text-center">
          <h1 data-aos="fade-up">
              <c:if test="${not empty imageIcon}">
              <img class="achievement_header_icon"  src="${imageIcon.url}" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="" loading="lazy">
              </c:if> 
              <c:if test="${not empty headerText}">
             <span >${headerText}</span>
           </c:if> 
          </h1>
          <p>
           <c:if test="${not empty description}">
             <span class="dsp-ar-flex" data-aos="zoom-in">${description}</span>
           </c:if> 
          </p>
      </div> 
          
      <div class="achievement_statastics">
        <div class="row m-0">

       
            <c:if test="${not empty bannerImage && alignmentLeft}">
             <div class="col-12 col-lg-8 achievement_banner_right p-0">
             <img class="img-fluid w-100"  src="${bannerImage.url}" alt='${bannerImage.altText}' title='${bannerImage.altText}' style="" loading="lazy">
             </div>
            </c:if> 
            
            <c:if test="${not empty achievements}">
              <div class="col-12 col-lg-4 list_of_achievements p-0">
                <div class="col-md-7 m-auto achievements_points">
             <c:forEach var="entry" items="${achievements}">
                      <div>
                        <h1 data-aos="fade-up"> ${entry.value.header}</h1>
                        <p class="dsp-ar-flex" data-aos="fade-up"> ${entry.value.description}</p>
                      </div>  
             </c:forEach>
             </div>
            </div>
            </c:if> 
            
            <c:if test="${not empty bannerImage && not alignmentLeft}">
              <div class="col-12 col-lg-8 achievement_banner_left p-0" >
             <img class="img-fluid w-100"  src="${bannerImage.url}" alt='${bannerImage.altText}' title='${bannerImage.altText}' style="" loading="lazy">
              
             </div>
            </c:if> 
          </div>
          
          </div>
          
          
</div>

