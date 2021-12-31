<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<json:object>
    <json:property name="status" value="${saveStatus}"/>
    <c:choose>
        <c:when test="${saveStatus}">
            <json:property name="shareholdersCount" value="${fn:length(shareHoldersDataList)}"/>
            <json:property name="shareholdersTable">
                <license:newLicenseApplyRegisteredShareholdersTable/>
            </json:property>
        </c:when>
        <c:otherwise>
            <json:property name="form">
                <c:choose>
                    <c:when test="${shareholderType eq 'existing'}">
                        <c:if test="${not empty sagiaApplyExistingShareholderForm}">
                            <license:newLicenseApplyExistingShareholderForm-QM1/>
                        </c:if>
                    </c:when>
                    <c:when test="${shareholderType eq 'person'}">
                        <c:if test="${not empty sagiaApplyPersonShareholderForm}">
                            <license:newLicenseApplyPersonShareholderForm-QM1/>
                        </c:if>
                    </c:when>
                    <c:when test="${shareholderType eq 'organization'}">
                        <c:if test="${not empty sagiaApplyOrganizationShareholderForm}">
                            <license:newLicenseApplyOrganizationShareholderForm-QM1/>
                        </c:if>
                    </c:when>
                </c:choose>
            </json:property>
        </c:otherwise>
    </c:choose>
</json:object>