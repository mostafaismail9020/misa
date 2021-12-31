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
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<c:set var="formName" value="formNewLicenseReplacement"/>
<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="licenseReplacement.title" /></h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${formName}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span>
                </button>

                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${formName}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="licenseReplacement.back.dashboard" /></a>
        </div>
    </div>
</div>

<!--site content for second license download page-->
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <form:form action="${encodedContextPath}/my-sagia/license/replace/create"
                   enctype="multipart/form-data"
                   method="post"
                   id = "${formName}"
                   modelAttribute="licenseReplacementFormData">
            <div class="panelModule panelModule_halfRadius">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <span class="iconElement iconElement_documents"><icon:documents/></span>
                            <spring:theme code="licenseReplacement.support.documents" />
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file01" name="files[0]" data-id="0" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value="">
                                        <input id="text01" name="text01" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                        <label class="control-label" for=""><spring:theme code="licenseReplacement.investor.letter" /></label>
                                        <div class="form-icon form-icon_browse">
                                            <icon:upload/>
                                        </div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset">
                                            <icon:cross/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                <button type="button" class="btn btn-secondary">
                    <spring:theme code="licenseReplacement.cancel" />
                </button>
                <div class="formCheckBox formCheckBox_belowPanel">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox path="termsAndConditionsChecked" event="LICENSE_SERVICES" id="checkbox01"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" id="submit">
                    <spring:theme code="licenseReplacement.submit" />
                </button>
            </div>
            <input type="hidden" name="csrfToken" value="${_csrf.token}" />
            <input type="hidden" id="serviceId"/>
        </form:form>
    </div>
</div>
