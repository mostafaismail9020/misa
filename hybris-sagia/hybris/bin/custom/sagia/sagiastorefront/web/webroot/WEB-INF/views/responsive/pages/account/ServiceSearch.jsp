<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="mainSection mainSection_grey">
    <div class="container">

        <div class="mainSection-header">
            <h1 class="mainSection-headline mb-3"><spring:theme code="services.services"/></h1>
        </div>
        <div class="col-xs-12 col-md-12 services-category-list">
            <ul class="nav nav-pills sagiaNavigation-services" id="pills-tab" role="tablist"></ul> 
            <div class="tab-content desktop_services incentives-container-tabcontent" id="pills-tabContent"></div>
        </div>
    </div>
</div>