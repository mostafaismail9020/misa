<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="mainSection mainSection_grey">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="service.requests.title"/></h1>
            <div>
                <%--<div class="controlBar">
                    <a href="${encodedContextPath}/service-search" class="controlBar-action">
                        <span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
                        <span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
                    </a>
                    <a href="#" class="controlBar-action active">
                        <span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
                        <span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
                    </a>
                </div>--%>
            </div>
        </div>
    </div>
</div>

<section class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
        <div class="row">
            <ul id="draggableComponentsList" class="dashboardWidgetList">
                <li class="dashboardWidgetListComponent js-component">
                    <div class="col col-12" id="savedDrafts">
                        <dashboard:savedDrafts/>
                    </div>
                </li>
                <li class="dashboardWidgetListComponent js-component" >
                    <div class="col col-12" id="servicesRequest">
                        <dashboard:servicesRequest/>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</section>




