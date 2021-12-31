<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="investment-hub-container" id="investment-hub-container">
	<div class="container what-do-section">
    	<h2 data-aos="fade-right" data-aos-delay="100"><span>${title}</span></h2>
        <h5 data-aos="fade-right" data-aos-delay="100">${text}</h5>
    </div>
    <div class="invesment-hub-section-bg">
        <div class="container">
            <div class="row what-we-do">
                <c:forEach var="currentComponent" items="${components}" varStatus="status">
                    <cms:component component="${currentComponent}" element="div" class="col-md-4" />
                </c:forEach>
            </div>
        </div>
    </div>
</section>

