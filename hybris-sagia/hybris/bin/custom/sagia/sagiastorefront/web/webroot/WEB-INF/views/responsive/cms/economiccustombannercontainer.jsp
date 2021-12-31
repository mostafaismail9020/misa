<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    
<!-- Breadcurms -->
<!--End of  Breadcurms -->
<div class="eco-banner" style = "background-image:url(${backgroundImage.url})">
    <div class="eco-banner-container" data-aos="fade-up">
        <c:if test="${not empty title}"><h1>${title}</h1></c:if> 
    </div>
</div>

<div class="position-relative">
    <div class="eh-page-breadcrums">
        <div class="toggle-div container-fluid">
            <div class="eh-page-grid">
                <c:forEach var="currentComponent" items="${components}">
                        <cms:component component="${currentComponent}" />
                </c:forEach>
            </div>
        </div>
    </div>
</div>


    

    