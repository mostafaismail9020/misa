<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="editable" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>

<div class="dashboardWidget js-dashboardWidget no-border">
    <c:if test="${editable}"><dashboard:addAndRemoveComponent checkboxIndex="1"/></c:if>
    <div class="dashboardWidget-headline js-dashboardWidget-headline">
        <div class="dashboardWidget-headline-text"><spring:theme code="myLicense.title"/></div>
        <div class="dashboardWidget-headline-icon"><icon:my-license/></div>
        <div class="dashboardWidget-headline-action">
            <!-- <a href="${encodedContextPath}/my-sagia/license/convert" class="dashboardWidget-headline-action-link">
                <spring:theme code="convertlicense.converttonational"/><icon:convert-to-national/>
            </a> -->
            <a href="${encodedContextPath}/my-sagia/license/print" class="btn-outline dashboardWidget-headline-action-link download-payment" download>
                <spring:theme code="text.account.followup.download"/><img class="pl-3" src="${commonResourcePath}/images/dashboard-media/Download.png" alt=""/>
            </a>
           <!-- <a href="${encodedContextPath}/my-sagia/license/cancel" class="dashboardWidget-headline-action-link">
                <spring:theme code="licenseCancellation.text"/><icon:cancel/>
            </a> -->
        </div>
    </div>

    <div class="dashboardWidget-body">
    <%-- start dashboardWidgetLicense --%>
        <div class="dashboardWidget-headline-action">
            <a href="${encodedContextPath}/my-sagia/license/convert" class="dashboardWidget-headline-action-link">
                <spring:theme code="convertlicense.converttonational"/><icon:convert-to-national/>
            </a>
            <a href="${encodedContextPath}/my-sagia/license/download" class="dashboardWidget-headline-action-link">
                <spring:theme code="text.account.followup.download"/><icon:download/>
            </a>
          <%--  <a href="${encodedContextPath}/my-sagia/license/cancel" class="dashboardWidget-headline-action-link">
                <spring:theme code="licenseCancellation.text"/><icon:cancel/>
            </a>--%>
        </div>  
       
        <div class="dashboardWidgetLicense d-license-widget">
            <div class="dashboardTabs js-dashboardTabs">
            <%-- start dashboardTabs 1 --%>
                <div class="dashboardTabs-head"><spring:theme code="dashboard.myLicense.details"/></div>
                <div class="dashboardTabs-body">
                    <div class="dashboardWidgetLicense-list">
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="profile.company.entityId"/></div>
                            <div id="licenseEntityId" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="profile.company.capitalCurrency"/></div>
                            <div id="licenseCapital" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <%-- <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label">
                                <spring:theme code="profile.company.class"/>
                                <a href="${encodedContextPath}/my-sagia/license/classifications"><spring:theme code="classificationupgrade.classificationupgradeplus"/> +</a>
                            </div>
                            <div id="licenseClassLevel" class="dashboardWidgetLicense-list-value"></div>
                        </div> --%>

                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="profile.company.entityStatus"/></div>
                            <div id="licenseEntityStatus" class="dashboardWidgetLicense-list-value dashboardWidgetLicense-list-value_green"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.expiryDate"/></div>
                            <div id="licenseExpiryDate" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.duration"/></div>
                            <div id="licenseDuration" class="dashboardWidgetLicense-list-value"></div>
                        </div>

                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.type"/></div>
                            <div id="licenseType" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="profile.company.ISICSection"/></div>
                            <div id="licenseIsicSection" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="profile.company.yearlyFeesCurrency"/></div>
                            <div id="licenseYearlyFees" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                    </div>
                </div>

                <div class="dashboardTabs-head"><spring:theme code="dashboard.myLicense.branches"/></div>
                <div class="dashboardTabs-body" id="branchesTab">
                    <div class="dashboardWidgetLicense-table">
                        <div class="tableModule tableModule_slim">
                            <table class="tableModule-table" >
                                <thead class="tableModule-head">
                                    <tr>
                                        <th><spring:theme code="text.account.profile.license.branches.type"/><br></th>
                                        <th><spring:theme code="text.account.profile.license.branches.name"/></th>
                                        <th><spring:theme code="text.account.profile.license.branches.city"/></th>
                                    </tr>
                                </thead>
                                <tbody class="tableModule-body" id="branchesTable"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="paginationModule d-license-branch">
                        <div class="paginationModule-wrapper">
                            <!-- <button class="paginationModule-control paginationModule-control_left" disabled><icon:arrow_green_right/></button> -->
                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive" id="firstimg">
                            </button>
                            <div class="paginationModule-items" id="branchesPagesSection">
                                <div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link branch active">1</a></div>
                            </div>
                            <button class="paginationModule-control paginationModule-control_right">
                                <img src="/_ui/responsive/common/images/arrow-left.png" class="img-responsive">
                            </button>
                            <!-- <button class="paginationModule-control paginationModule-control_right">
                                <icon:arrow_green_right/>
                            </button> -->
                        </div>
                    </div>
                </div>

                <div class="dashboardTabs-head"><spring:theme code="text.account.profile.license.shareholders"/></div>
                <div class="dashboardTabs-body">
                    <div class="dashboardWidgetLicense-table">
                        <div class="tableModule tableModule_slim">
                            <table class="tableModule-table">
                                <thead class="tableModule-head">
                                    <tr>
                                        <th><spring:theme code="text.account.profile.license.shareholders.name"/><br></th>
                                        <th><spring:theme code="text.account.profile.license.shareholders.type"/></th>
                                        <th><spring:theme code="text.account.profile.license.shareholders.percentage"/></th>
                                        <th><spring:theme code="text.account.profile.license.shareholders.nationality"/></th>
                                        <th><spring:theme code="text.account.profile.license.shareholders.legalStatus"/></th>
                                    </tr>
                                </thead>
                                <tbody class="tableModule-body" id="shareholdersTable"></tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="dashboardTabs-head"><spring:theme code="text.account.profile.license.contactperson"/></div>
                <div class="dashboardTabs-body">
                    <div class="tableModule tableModule_fixedWidth">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th>Name</th>
                                <th>Mobile number</th>
                                <th>Email</th>
                                <th>National ID</th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body"  id="contactPersonTable"></tbody>
                        </table>
                    </div>
                </div>

                <div class="dashboardTabs-head"><spring:theme code="text.account.profile.license.generalmanager"/></div>
                <div class="dashboardTabs-body">
                    <div class="tableModule tableModule_slim">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th><spring:theme code="text.account.profile.license.generalManager.name"/></th>
                                <th><spring:theme code="text.account.profile.license.generalManager.mobileNumber"/></th>
                                <th><spring:theme code="text.account.profile.license.generalManager.email"/></th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body">
                                <tr>
                                    <td>
                                        <div id="generalManagerName" class="dashboardWidgetLicense-list-value">
                                            <c:out value="${license.generalManager.firstName}"/> <c:out value="${license.generalManager.lastName}"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="generalManagerMobileNumber" class="dashboardWidgetLicense-list-value">
                                            <c:out value="${license.generalManager.firstName}"/> <c:out value="${license.generalManager.lastName}"/>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="generalManagerEmail" class="dashboardWidgetLicense-list-value"></div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.generalManager.name"/></div>
                            <div id="generalManagerName" class="dashboardWidgetLicense-list-value">
                                <c:out value="${license.generalManager.firstName}"/> <c:out value="${license.generalManager.lastName}"/>
                            </div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.generalManager.mobileNumber"/></div>
                            <div id="generalManagerMobileNumber" class="dashboardWidgetLicense-list-value"></div>
                        </div>
                        <div class="dashboardWidgetLicense-list-entry">
                            <div class="dashboardWidgetLicense-list-label"><spring:theme code="text.account.profile.license.generalManager.email"/></div>
                            <div id="generalManagerEmail" class="dashboardWidgetLicense-list-value"></div>
                        </div> -->
                    </div>
                </div>
            </div>

            <div class="dashboardWidgetLicense-action">
                <button type="button" class="btn" onclick="window.location.href='${encodedContextPath}/my-sagia/license/amend'">
                    <spring:theme code='dashboard.myLicense.amend'/><icon:edit/>
                </button>
                <button type="button" class="btn" onclick="window.location.href='${encodedContextPath}/my-sagia/license/renew'">
                    <spring:theme code='dashboard.myLicense.renew'/><icon:renew/>
                </button>
            </div>
        </div>
    </div>
</div>

<table class="branchTemplateWrapper" style="display:none;">
    <tr>
        <td class="branchType"></td>
        <td class="branchName"></td>
        <td class="branchCity"></td>
    </tr>
</table>

<table class="shareholderTemplateWrapper" style="display:none;">
    <tr>
        <td class="shareholderName"></td>
        <td class="shareholderType"></td>
        <td class="shareholderPercentage"></td>
        <td class="shareholderNationality"></td>
        <td class="shareholderLegalStatus"></td>
    </tr>
</table>

<table class="contactPersonTemplateWrapper" style="display:none;">
    <tr>
        <td class="contactPersonName"></td>
        <td class="contactPersonMobileNumber"></td>
        <td class="contactPersonEmail"></td>
        <td class="contactPersonNationalId"></td>
    </tr>
</table>