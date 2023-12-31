<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<div class="container my-auto fixed-auto">
    <div class="row">
        <div class="col-lg-10 mx-auto">
            <h1 class="animated pulse">
                ${descriptionText}
                <br/>
            </h1>
            <c:if test="${not empty buttonText}">
                <a class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" href="${fn:escapeXml(buttonURL.url)}" target="${fn:escapeXml(link)}">${buttonText}</a>
            </c:if>
        </div>
    </div>
</div>



