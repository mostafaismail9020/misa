<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="aboutusContainer aboutInvestinsaudi-whatWedo" id="aboutusContainer">
        <div class="section-bg">
            <div class="container what-do-section">
            <h2 data-aos="fade-right" data-aos-delay="100">${title}</h2>
                <div class="row what-we-do">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                                    <cms:component component="${currentComponent}" element="div" class="col-md-12" />
                        </c:forEach>
                        
                </div>
        </div>
    </div>
</section>
