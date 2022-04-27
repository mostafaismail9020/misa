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

<div class="panelTabs-head" id="passwordTab"><spring:theme code="profile.security"/></div>
<div class="panelTabs-body" id="passwordTabData">
    <div class="panelModule panelModule_halfRadius">
        <div class="contentModule">
            <div class="contentModule-section">
                <div class="contentModule contentModule-wrap">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                        <span class="contentModule-headline"><spring:theme code="profile.my.password"/></span>
                        <div class="contentModule-headline-border"></div>
                    </div>
                </div>
                <account:updatePwd/>

            </div>

            <div class="contentModule-section">
                <div class="contentModule contentModule-wrap">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                        <span class="contentModule-headline"><spring:theme code="profile.my.username"/></span>
                        <div class="contentModule-headline-border"></div>
                    </div>
                </div>
                <account:updateUsername/>
            </div>
            <div class="contentModule-section">
                <div class="contentModule contentModule-wrap">
                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                        <span class="contentModule-headline"><spring:theme code="profile.my.email"/></span>
                        <div class="contentModule-headline-border"></div>
                    </div>
                </div>
                <account:updateEmail/>
            </div>
        </div>
    </div>
</div>
