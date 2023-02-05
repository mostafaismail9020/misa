<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="serviceUrl" required="true" type="java.lang.String" %>

<div>
	<c:url value="/mediaCenter/downloadServiceApplication/${serviceUrl}" var="resourcedownloadURL"/>
    <spring:theme code="services.serviceApplication.message1" text="To Proceed with the service application, please use the"/>&nbsp
    <a target="_blank" href="${resourcedownloadURL}">
        <spring:theme code="services.serviceApplication.manual" text="Please fill-out the form"/>&nbsp
    </a>
    <spring:theme code="services.serviceApplication.message2"/>
</div>