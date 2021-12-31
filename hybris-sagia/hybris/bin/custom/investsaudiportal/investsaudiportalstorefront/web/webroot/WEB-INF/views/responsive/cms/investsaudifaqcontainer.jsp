<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="container accordion-content">
    <div class="row">
        <c:forEach var="currentComponent" items="${components}" varStatus="status">
            <div class="tab col-md-12"/>
                <input id="tab-${status.index}" type="checkbox" name="tabs">
                <label for="tab-${status.index}">${currentComponent.text}</label>
                <div class="acc-tab-content">
                    ${currentComponent.descriptionText}
                </div>
            </div>
        </c:forEach>
    </div>
</section>
