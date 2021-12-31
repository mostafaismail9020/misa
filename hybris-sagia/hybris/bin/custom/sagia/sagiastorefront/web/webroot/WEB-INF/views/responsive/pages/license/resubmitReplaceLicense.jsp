<hr style="margin: 0; padding:0;"/>
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

<c:set var="formName" value="licenseReplacementResubmitFormData"/>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="licenseReplacement.title" /></h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft" data-target-form="${formName}"><spring:theme code="general.savedraft"/>
                    <span class="iconElement iconElement_save"><icon:save/></span></button>

                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if> data-target-form="${formName}"><spring:theme code="general.loaddraft"/>
                    <span class="iconElement iconElement_save"><icon:upload/></span></button>

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
        <form:form action="${encodedContextPath}/my-sagia/license/replace/1/1/resubmit" enctype="multipart/form-data" method="post" modelAttribute="${formName}">
            <input type="hidden" name="csrfToken" value="${_csrf.token}" />
            <div class="panelModule panelModule_halfRadius">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <span class="iconElement iconElement_documents"><icon:documents/></span>
                            <spring:theme code="licenseReplacement.support.documents" /></div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file08" name="files[0]" data-id="0" class="form-control js-inputFile" accept="image/jpeg,application/pdf" type="file" value="">
                                        <input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
                                        <label class="control-label " for=""><spring:theme code="licenseReplacement.investor.letter" /></label>
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
                        <div class="form-item">
                            <input id="checkbox01" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
                            <label class="control-label " for="checkbox01">
                                <span>
                                    <icon:check/>
                                </span><spring:theme code="general.iagreeto"/><a href="#" class="link"><spring:theme code="general.termsandconditions"/></a>
                            </label>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">
                    <spring:theme code="licenseReplacement.reapply" />
                </button>
            </div>
        </form:form>
    </div>
</div>
