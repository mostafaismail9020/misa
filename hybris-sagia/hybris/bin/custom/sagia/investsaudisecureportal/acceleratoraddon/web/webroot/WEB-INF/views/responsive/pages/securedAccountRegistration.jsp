<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sptemplate" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/sptemplate"%>
<%@ taglib prefix="spuser" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/spuser"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:htmlEscape defaultHtmlEscape="true" />
<sptemplate:page pageTitle="${pageTitle}">
    <jsp:body>
        <div class="login-logo text-center">
            <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                <cms:component component="${logo}"/>
            </cms:pageSlot>
        </div>

        <div class="tab-controls">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#BDRegistration__tab" aria-controls="home" role="tab" data-toggle="tab">
                        <span>Business Development Representative</span>
                    </a>
                </li>
                <li role="presentation">
                    <a href="#MarcommRegistration__tab" aria-controls="profile" role="tab" data-toggle="tab">
                        <span>Marketing & Communication Representative</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="tab-content bootstrap-tab">
            <div class="register__container tab-pane fade in active" id="BDRegistration__tab">
                <div class="row" data-role="content">
                    <div class="col-md-4">
                        <div class="item_container">
                            <cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="side-content-slot cms_disp-img_slot">
                                <cms:component component="${feature}"/>
                            </cms:pageSlot>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="register__section">
                            <c:url value="/register" var="submitAction" />
                            <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="BDUserGroup" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="register__container tab-pane fade" id="MarcommRegistration__tab">
                <div class="row" data-role="content">
                    <div class="col-md-4">
                        <div class="item_container">
                            <cms:pageSlot position="LeftContentSlot02" var="feature" element="div" class="side-content-slot cms_disp-img_slot">
                                <cms:component component="${feature}"/>
                            </cms:pageSlot>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="register__section">
                            <c:url value="/register" var="submitAction" />
                            <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="MarCommUserGroup"  />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/tags/addons/investsaudisecureportal/responsive/common/termsAndConditionsModal.tag" %>
    </jsp:body>
</sptemplate:page>
