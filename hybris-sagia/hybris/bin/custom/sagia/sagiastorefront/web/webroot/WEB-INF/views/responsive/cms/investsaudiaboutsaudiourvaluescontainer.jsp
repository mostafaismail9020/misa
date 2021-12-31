<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="aboutusContainer aboutInvestinsaudi-ourvalues" id="aboutusContainer">
<div class="container our-values">
    <h2 data-aos="fade-right" data-aos-delay="100">${title}</h2>
        <div class="row our-values-column">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                            <cms:component component="${currentComponent}" element="div" class="col-lg-12 d-flex flex-column align-items-lg-center" />
                        </c:forEach>
        </div>
</div>
</section>