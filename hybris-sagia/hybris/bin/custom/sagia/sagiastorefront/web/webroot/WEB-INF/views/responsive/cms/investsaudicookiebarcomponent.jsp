<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<section class="cookie-desclaimer alert alert-secondary d-none" role="alert">
    <div class="fixed-bottom p-4">
        <div class="toast bg-dark text-white w-100 mw-100" role="alert" data-autohide="false">
            <div class="toast-body p-4 d-flex flex-column">
                <h4>Cookie Warning</h4>
                <p>
                This website stores data such as cookies to enable site functionality including analytics and personalization. By using this website, you automatically accept that we use cookies.
                </p>
                <div class="d-flex mt-5">
                    <button type="button" class="btn btn-video text-muted mx-3" id="btnDeny">
                        Deny
                    </button>
                    <button type="button" class="btn btn-primary-fill btn-video" id="btnAccept">
                        Accept
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>



