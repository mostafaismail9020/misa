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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var cancellationMessage = '${licenseCancellationMessage}';
</script>

<div class="mainSection mainSection_white">
    <div class="container">
        <div class="mainSection-header">
            <div class="mainSection-headline">

                <spring:theme code="licenseCancellation.title"/>
            </div>
            <div class="mainSection-action">
                <button class="btn btn_round"><spring:theme code="licenseCancellation.save.draft"/>
                    <icon:save/></button>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_noPadding">
    <div class="container">
        <div class="stepModule">
            <div class="stepModule-item done">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step1/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step1_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.request.cancellation.letter"/>
                </div>
            </div>
            <div class="stepModule-item active">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step2/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step2_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.collect.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step3/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step3_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.submit.cancellation.docs"/>
                </div>
            </div>
            <div class="stepModule-item">
                <div class="stepModule-icon stepModule-icon_normal"><icon:licenseCancel-step4/></div>
                <div class="stepModule-icon stepModule-icon_hover"><icon:licenseCancel-step4_hover/></div>
                <div class="stepModule-text">
                    <spring:theme code="licenseCancellation.cancellation.approval"/>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- step2 --%>


<div class="mainSection mainSection_white  mainSection_noPadding">
    <div class="container">
        <form:form action="${encodedContextPath}/my-sagia/license/cancel/stage/next"
                   id="formCancelLicenseStage2"
                   enctype="multipart/form-data"
                   method="post"
                   modelAttribute="licenseCancelFormData">

            <div class="contentModule">
                <div class="contentModule-section">

                    <div class="licenseCancel-text2">
                        <spring:theme code="licenseCancellation.document.stage3.message01"/>
                    </div>

                </div>
                <div class="contentModule-actions contentModule-actions_spaceBetween">
                    <button type="button" class="btn btn-secondary btn-back">
                        <spring:theme code="licenseCancellation.back"/>
                    </button>
                    <div class="formCheckBox formCheckBox_belowPanel">
                        <div class="form-group">
                            <div class="form-item">
                                <input id="lc_a2" name="lc_a2" class="form-control" placeholder="." type="checkbox">
                                <label class="control-label " for="lc_a2">
                                    <span><icon:check/></span>
                                    <spring:theme code="licenseCancellation.document.stage3.checque01"/>
                                </label>
                            </div>
                        </div>
                    </div>                    
                    <button type="submit" class="btn">
                    <spring:theme code="licenseCancellation.next"/>
                    </button>
                </div>
            </div>
        </form:form>

    </div>
</div>
<div class="modal fade" id="blockCancellation"  tabindex="-1" role="dialog" aria-labelledby="blockCancellation" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%-- <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div> --%>
            </div>
            <div class="modal-body">
                <div class="modal-description js-description-text">
                <label class="control-label" for="department">${licenseCancellationMessage}</label>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn js-close-btn btn js-close-btn" href="${encodedContextPath}/dashboard"><spring:theme code="general.close"/></a>
            </div>
        </div>
    </div>
</div>