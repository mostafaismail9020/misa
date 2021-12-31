<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class=" container invest_lic_outer text-center py-4">
    <div class="row m-0">
        <div class="col-md-12 title-area">
            <c:if test="${not empty title}">
                <div data-aos="fade-up">${title}</div>
                <!-- <h1 class="title-heading invest_lic_first_text" >${title}</h1> -->
            </c:if>
            <c:if test="${not empty longDescription}">
                <div class="pt-3" data-aos="fade-up">${longDescription}</div>
            </c:if>
            
        </div>
        <div class="container"> 
            <div class="d-flex">
                <c:forEach var="currentComponent" items="${components}" >
                    <cms:component component="${currentComponent}" element="div" class=" col invest_lic_inner" data-aos="fade-up"/>
                </c:forEach>  
            </div>
        </div>
    </div>
</div>


