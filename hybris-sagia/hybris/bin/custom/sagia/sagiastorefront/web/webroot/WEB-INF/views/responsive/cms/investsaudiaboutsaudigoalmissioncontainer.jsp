<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="aboutusContainer aboutInvestinsaudi-goalsection" id="aboutusContainer">
<div class="section-goal" style="background-image: url(${backgroundImage.url});">    
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                        <c:forEach var="currentComponent" items="${components}" varStatus="status">
                            <cms:component component="${currentComponent}" element="div" class="goal text-center align-items-center justify-content-center" data-aos="fade-up"/>
                        </c:forEach>
            </div>
        </div>
    </div>
</div>
</section>
