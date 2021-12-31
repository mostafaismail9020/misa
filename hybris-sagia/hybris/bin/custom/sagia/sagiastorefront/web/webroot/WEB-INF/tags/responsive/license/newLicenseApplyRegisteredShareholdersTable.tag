<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<c:forEach items="${shareHoldersDataList}" var="shareholder">
    <c:set var="entityNumber">
        <c:if test="${not empty shareholder.entityNumber}">
            data-entitynumber="${shareholder.entityNumber}"
        </c:if>
    </c:set>
    <tr data-id="${shareholder.code}" data-type="${shareholder.shareHolderType}"
        data-sharespercentage="${shareholder.sharesPercentage}" ${entityNumber}>
        <td title="${shareholder.mainName}">${shareholder.mainName}</td>
        <td style="text-transform: capitalize">${shareholder.shareHolderType}</td>
        <td>${shareholder.sharesPercentage}%</td>
        <td title="${shareholder.nationality}">${shareholder.nationality}</td>
        <td>${not empty shareholder.mainLegalStatus ? shareholder.mainLegalStatus : "-"}</td>
        <td>${not empty shareholder.delegateInfo && not empty shareholder.delegateInfo.delegateFullName ?
         shareholder.delegateInfo.delegateFullName : "-"}</td>
        <td>${not empty shareholder.delegateInfo && not empty shareholder.delegateInfo.delegateIdentityNumber ?
                shareholder.delegateInfo.delegateIdentityNumber : "-"}</td>
        <td class="tableModule-bodyItem-action">
            <button type="button" class="removeButton btn btn_link">
                <icon:trashbin/>
            </button>
            <button type="button" class="editButton btn btn_link">
                <icon:edit/>
            </button>
        </td>
    </tr>
</c:forEach>