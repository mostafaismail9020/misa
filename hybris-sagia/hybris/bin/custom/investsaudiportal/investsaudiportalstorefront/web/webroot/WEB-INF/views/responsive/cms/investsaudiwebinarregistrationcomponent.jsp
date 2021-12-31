<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="my-form my-form2  wow fadeIn" data-wow-delay="0.2s" id="corForm">
    <div class="subscriptionBox p-4">
        <div class="email-cont">
            <h6>${signupTitleText}</h6>
            <div class="md-form mt-2 pos-rel">
                <label for="emailField" class="emailField" data-placeholder="example@gmail.com">
                    ${titleText}
                </label>
                <input id="emailField" class="form-control required validate-email" type="email">
                <input type="hidden" id="webinarCode" value="${webinarCode}"/>
            </div>
            <div class="d-flex justify-content-center">
                <button id="btn-submit" type="button" class="btn btn-contact"
                        onclick="onRegistrationload()">${buttonText}</button>
            </div>
            <div class="my-3 text-center">
                <spring:theme code="text.registration.step"/>
            </div>
        </div>
    </div>
</div>
