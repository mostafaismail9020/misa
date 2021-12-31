<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/responsive/profile" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<%--@elvariable id="hasLicense" type="java.lang.Boolean"--%>

<div class="mainSection mainSection_dark">
    <div class="container">
        <h1 class="mainSection-headline"><spring:theme code="general.account"/></h1>
    </div>
</div>

<div class="js-panelTabs panelTabs panelTabs_slim panelTabs_account">
    <profile:sagia.profile.company/>
    <profile:sagia.profile.myProfile/>
    <profile:sagia.profile.security/>
    <profile:sagia.profile.enquiries/>
    <profile:sagia.profile.questionnaires/>
</div>
