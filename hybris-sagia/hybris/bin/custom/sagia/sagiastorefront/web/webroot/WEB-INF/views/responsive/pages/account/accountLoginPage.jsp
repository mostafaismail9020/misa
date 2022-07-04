<%@ page trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
            <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
                <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

                    <template:page pageTitle="${pageTitle}">
                        <jsp:attribute name="pageScripts">
                            <script>
                                var backendRegex = '${backendRegex}';
                                var backendRegexErrorMessage = '${backendRegexErrorMessage}';
                            </script>
                            <%--<script type="text/javascript" src="${themeResourcePath}/js/sagia.register.js"></script>--%>
                                <%--<script type="text/javascript" src="${themeResourcePath}/js/sagia.login.js"></script>--%>
                        </jsp:attribute>
                        <jsp:body>
                            <div class=" accountLogin accountLogin_outer " style="display: none">
                                <div class="row login-container justify-content-center">
                                    <cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="container-fluid">
                                        <cms:component component="${feature}" element="div" class="panelModule_halfRadius accountLogin-content accountLogin-content_small mt-0" />
                                        <%-- <cms:component component="${feature}" element="div" class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small" /> --%>
                                    </cms:pageSlot>
                                    <cms:pageSlot position="RightContentSlot" var="feature" element="div" class="w-100">
                                        <cms:component component="${feature}" element="div" class="js-panelTabs panelTabs panelTabs_transparent panelTabs_iconsAndLabel accountLogin-content" />
                                    </cms:pageSlot>
                                </div>
                            </div>
                        </jsp:body>
                    </template:page>
                    <%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
                        <%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

                            <div class="particles-js hidden-sm-down">
                            </div>
                            <div class="particles-js-2 hidden-sm-down">
                            </div>