<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Opening Closed Entity</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div >show History<span>&#x27f6;</span>
                    <%--
                    <spring:theme code="text.account.followup.showServiceHistory"/><span>&#x27f6;</span>
                    --%>
                </div>
                <div class="hidden">hide History<span class="iconElement iconElement_closeBack"><icon:close/></span></span>
                    <%--
                    <spring:theme code="text.account.followup.hideServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span>
                    --%>
                </div>
            </button>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent" id="expand01">
        <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_history"><icon:history/></span>
                                <spring:theme code="text.account.followup.history"/>
                            </div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input class="searchInputBox-input" type="text" placeholder="Search"/>
                            </div>
                               <ul class="historyList">
                                <%-- <c:if test="${fn:}"" --%>
                                    <li class="historyList-item historyList-item_current">
                                            <a href="#" class="historyList-link">
                                                <div class="historyList-id">500009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">06 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_process">In Process</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">200009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">02 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_accepted">Accepted</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">100009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">01 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_rejected">Rejected</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">100009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">01 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_rejected">Rejected</div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                            <%--    </c:if>
                            --%>
                            <%--
                            <c:if test="${fn:}">
                                <div class="emptyIndicator">
                                    <spring:theme code="text.account.followup.noHistory"/>
                                </div>
                            </c:if>
                            --%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="expandableContent-main">
                <form:form action="" method="post" modelAttribute="" enctype="multipart/form-data">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline">
                                    <icon:info/>Entity Information
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList">
                                        <dt data-name="text">Basic Information</dt>
                                        <dd><div name="text">Placeholder Text for an Entity long text information. An entity is something that exists as itself, as a subject or as an object, actually or potentially, concretely or abstractly, physically or not. It need not be of material existence. In particular, abstractions and legal fictions are usually regarded as entities. In general, there is also no presumption that an entity is animate, or present.</div></dd>
                                    </dl>
                                </div>
                                <div class="col-md-3">
                                    <dl class="dlList">
                                        <dt data-name="street">Street / Unit No</dt>
                                        <dd><span name="street">EMAILY</span></dd>
                                        <dt>Country</dt>
                                        <dd><span name="country">SA</span></dd>
                                        <dt>Postal Code / City</dt>
                                        <dd><span name="zipCode">32233</span></dd>
                                    </dl>
                                </div>
                                <div class="col-md-3">
                                    <dl class="dlList">
                                        <dt>Additional No</dt>
                                        <dd><span name="additNo">9377</span></dd>
                                        <dt>Building No</dt>
                                        <dd><span name="building">2853</span></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="contentModule-section">
                            <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_wrap">
                                <div class="contentModule-headline contentModule-headline_smallMargin">
                                    <icon:enquiry2/>Comment
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="formTextArea">
                                        <div class="form-group">
                                            <textarea id="extensionReason" name="extensionReason" class="form-control"
                                                      placeholder="."></textarea>
                                            <label class="control-label" for="">
                                                Add your Comment please*
                                                <%--
                                                <spring:theme code="text.account.followup.warningLetterReason"/>
                                                --%>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                            <div class="contentModule-headline">
                                <icon:documents/>
                                Documents
                                <%-- spring:theme code="text.account.followup.supportDocuments"/--%>
                            </div>


                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input name="supportDocument" class="form-control js-inputFile" type="file" accept="application/pdf">
                                            <input class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label "><spring:theme
                                                    code="text.account.followup.document"/></label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="col-md-6">
                                    <p style="margin-top: 22px;">
                                        We may add an add-button to enable loading up multiple files.
                                    </p>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>


                <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                    <button type="button" class="btn btn-secondary">
                        Cancel
                    </button>
                    <div class="formCheckBox formCheckBox_belowPanel">
                        <div class="form-group">
                            <div class="form-item">
                                <input id="SAH539checkbox01" name="termsAgree" class="form-control" placeholder="."
                                       type="checkbox" value="entity name">
                                <label class="control-label " for="SAH539checkbox01">
                                    <span>
                                    <icon:check/>
                                    </span>I agree to <a href="#" class="link">terms &amp; conditions.</a>
                                </label>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn">
                        <spring:theme code="text.account.followup.submit"/>
                    </button>
                </div>

            </form:form>
        </div>
    </div>
</div>