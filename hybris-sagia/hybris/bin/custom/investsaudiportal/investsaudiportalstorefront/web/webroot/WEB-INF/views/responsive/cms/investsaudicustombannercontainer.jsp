<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="main-content-images mt-4">
    <div class="container">
        <div class="row mt-5 justify-content-center">
            <c:forEach var="currentComponent" items="${components}" varStatus="status">
                    <c:if test="${status.index % 2 eq 0}">
                        <div class="col-lg-2"><!-- --></div>
                    </c:if>
                    <cms:component component="${currentComponent}" element="div" class="col-lg-4 col-md-6 col-sm-12 col-12 my-2 text-center img-ksa"/>
                     <c:if test="${status.index % 2 ne 0}">
                         <div class="col-lg-2"><!-- --></div>
                     </c:if>
            </c:forEach>
        </div>

    </div>
</section>




