<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

<div class="viewchildUser_page"> 

<spring:htmlEscape defaultHtmlEscape="true"/> 

    <div id="global-alerts" class="global-alerts">

    <div class="headline header-account"> Child Unit List</div>

    <c:choose>
        <c:when test="${existingChildUnits.size() eq 0}">
            <div style="text-align:center; margin:20px;">No Child units to display!</div>
        </c:when>
        <c:otherwise>
        <table class="manage-user-table responsive-table " id="table"
                data-toggle="table"
                data-toolbar=".toolbar"
                data-sortable="true">
                    <thead>
                        <tr class="responsive-table-head hidden-xs">
                            <th class="th-sm" data-field="id" data-sortable="true"><spring:theme code="text.account.childUnits.id" text="ID" /></th>
                            <th class="th-sm" data-field="name" data-sortable="true"><spring:theme code="text.account.childUnits.name" text="Name" /></th>
                            <th class="th-sm" data-field="parentUnitId" data-sortable="true"><spring:theme code="text.account.parent.name" text="Parent Unit" /></th>
                        </tr>
                    </thead>
                        <tbody>
                            <c:forEach items="${existingChildUnits}" var="childUnit">
                                <tr class="responsive-table-item">
                                    <td class="hidden-sm hidden-md">${childUnit.id}</td>
                                    <td class="hidden-sm hidden-md">${childUnit.name}</td>
                                    <td class="hidden-sm hidden-md">${childUnit.parentUnitId} [${childUnit.parentUnitName}]</td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                    </table>
        </c:otherwise>
        </c:choose>
    </div>
</div>
