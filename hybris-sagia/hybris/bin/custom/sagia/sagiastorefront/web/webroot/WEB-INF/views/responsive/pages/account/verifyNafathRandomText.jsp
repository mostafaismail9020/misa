<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>



<template:page pageTitle="${pageTitle}">

<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content verification-div-sub" style="max-width: 600px;margin-top: 200px;">
                <h1>${randomNafathText}</h1>
            </div>
        </div>
    </div>
</div>

</template:page>
	
