<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
               
<div class="the_kingdom_glance-container">
    <div class="row m-0">
        <div class="col-md-12 title-area">
            <c:if test="${not empty title}">
                <h1 class="title-heading rhq-glance-heading">${title}</h1>
            </c:if>
        </div>
        
        <c:if test="${not empty headerText}">
            <h1 class="rhq-glance-content">${headerText}</h1>
            <div class="rhq-kingdom-glance-readmore">
                <a href="/en/aboutKingdom" class="rhq-glance-read-more "><spring:theme code="portal.regional.hq.read.more" text=" read more"/> &nbsp;
               <svg xmlns="http://www.w3.org/2000/svg " width="15.835 " height="10.561 " viewBox="0 0 15.835 10.561 ">
                   <path id="Icon_ionic-ios-arrow-round-forward " data-name="Icon ionic-ios-arrow-round-forward "
                       d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z "
                       transform="translate(-7.875 -11.252) " fill="#fff "></path>
               </svg>
           </a>
           </div>
         </c:if>
         
        <div class="container">
            <c:forEach var="currentComponent" items="${components}" >
    <cms:component component="${currentComponent}" element="div"/>
</c:forEach>
        </div>
    </div>
</div>


