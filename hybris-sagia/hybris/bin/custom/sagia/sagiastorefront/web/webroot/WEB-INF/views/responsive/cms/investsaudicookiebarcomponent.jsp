<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="cookie-desclaimer alert alert-secondary d-none" role="alert">
    <div class="fixed-bottom p-4">
        <div class="toast bg-dark text-white w-100 mw-100" role="alert" data-autohide="false">
            <div class="toast-body p-4 d-flex flex-column">
                <h4 class="align-right-when-ar">
                	<spring:theme code="text.cookie.notification.title"/>
                </h4>
                <p class="align-right-when-ar">
                	<spring:theme code="text.cookie.notification.description"/>
                </p>
                <div class="d-flex mt-5">
                    <button type="button" class="btn btn-video text-muted mx-3" id="btnDeny">
                        <spring:theme code="text.cookie.notification.deny"/>
                    </button>
                    <button type="button" class="btn btn-primary-fill btn-video" id="btnAccept">
                        <spring:theme code="text.cookie.notification.accept"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>



