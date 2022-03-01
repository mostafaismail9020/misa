<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<div class="errorPage-div">

    <div class="errorPage">
        <div class="errorPage-heroImage">
            <icon:attention-error/>
        </div>
        <div class="errorPage-description">
            <spring:theme code="payment.failure"/>
        </div>
        <div class="errorPage-actions">
            <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="payments.button.returnToPayment.text"/></button>
        </div>
    </div>
    
    </div>