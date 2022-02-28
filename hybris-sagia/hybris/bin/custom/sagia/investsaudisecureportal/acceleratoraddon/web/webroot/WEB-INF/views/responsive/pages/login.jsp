<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sptemplate" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/sptemplate" %>
<%@ taglib prefix="spuser" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/spuser" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<sptemplate:page pageTitle="${pageTitle}">
	<jsp:body>
        <div class="main__inner-wrapper" style="margin: 0px;">
           
            <!-- <div class="login-area-wrapper"> -->
            <div class="row container-fluid login-container justify-content-center">
                
                <div class="col-md-6 paragraph-wrapper"> 
                <div class="login-logo" style="margin-top: -10px;">
                    <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                        <cms:component component="${logo}"/>
                    </cms:pageSlot>
                </div>
                <cms:pageSlot position="LeftContentSlot" var="feature">
                    <cms:component component="${feature}" class=""/>
                </cms:pageSlot>
                </div>
                <div class="col-md-6 login-wrapper">
                    <div class="login-section">
                        <c:url value="/j_spring_security_check" var="loginActionUrl"/>
                        <spuser:login actionNameKey="login.login" action="${loginActionUrl}"/>
                    </div>
                </div>
            </div>
        </div>
	</jsp:body>
</sptemplate:page>
