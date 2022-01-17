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
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<div class="managerchildUser_page"> 
    <div id="global-alerts" class="global-alerts">
        <c:if test="${not empty errorMessageKey}">
            <div class="alert alert-danger alert-dismissable getAccAlert">
                <img src="/investsaudistorefront/_ui/responsive/common/images/danger.png">
                <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
                    <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
                </button><spring:theme code="${errorMessageKey}"/></div>
        </c:if>
        <c:if test="${createSuccess}">
            <div class="alert alert-info alert-dismissable getAccAlert">
                <img src="/investsaudistorefront/_ui/responsive/common/images/success.png">
                <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
                    <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
                </button>User created successfully</div>
        </c:if>
    </div>

<c:url value = "#" var="manageUserForm" />
<div id="global-alerts" class="global-alerts"></div>
<form:form method="post" modelAttribute="manageUserForm" action="${manageUserForm}" >
    <div style="display: none">
        <span id="no-account-selected"><spring:theme code="text.account.childUnits.no.user.selected"/></span>
        <span id="user-active"><spring:theme code="text.account.childUnits.active.status"/></span>
        <span id="user-inactive"><spring:theme code="text.account.childUnits.inactive.status" /></span>
        <span id="user-disabled"><spring:theme code="text.account.childUnits.closed.status" /></span>
    </div>
    <div class="row">
    <div class="col-md-12">
        <%-- this start of manager user page --%>
        <div class="headline header-account"> <spring:theme code="text.account.childUnits.manage.user.title" text="Manage Users" /></div>
        <div class="row">
            <div class="form-group" style = "float:right;">
                <button  class="btn btn-sagia btn-sagia-green lock_button" type="button" id="lockUser">
                    <spring:theme code="text.account.childUnits.lock.button.label" text="Lock User" />
                </button>
                <button class="btn btn-sagia btn-sagia-green unlock_button" type="button" id="unLockUser">
                    <spring:theme code="text.account.childUnits.unlock.button.label" text="Unlock User" />
                </button>
            </div>
        </div>
    </div>
    <table class="manage-user-table responsive-table" id="table"
           data-toggle="table"
           data-toolbar=".toolbar"
           data-sortable="true">
        <thead>
            <tr class="responsive-table-head hidden-xs">
                <th class="th-sm" ><spring:theme code="text.account.childUnits.id" text="" /></th>
                <th class="th-sm" data-field="title" data-sortable="true"><spring:theme code="text.account.childUnits.Title" text="Title" /></th>
                <th class="th-sm" data-field="firstname" data-sortable="true"><spring:theme code="text.account.childUnits.firstName" text="First Name" /></th>
                <th class="th-sm" data-field="lastName" data-sortable="true"><spring:theme code="text.account.childUnits.lastName" text="Last Name" /></th>
                <th class="th-sm" data-field="email" data-sortable="true"><spring:theme code="text.account.childUnits.email" text="Email" /></th>
                <th class="th-sm" data-field="mobileNumber" data-sortable="true"><spring:theme code="text.account.childUnits.mobileNumber" text="Mobile" /></th>
                <th class="th-sm" data-field="role" data-sortable="true"><spring:theme code="text.account.childUnits.role" text="Role" /></th>
                <th class="th-sm" data-field="uniy" data-sortable="true"><spring:theme code="text.account.childUnits.unit" text="Parent Unit" /></th>
                <th class="th-sm" data-field="status" data-sortable="true"><spring:theme code="text.account.childUnits.status" text="User Status" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${childB2BCustomers}" var="childUnit">
                <tr class="responsive-table-item">
                    <td ><input id ="${childUnit.uid}" name="b2bUsers" type = "checkbox" style="width:20px; height:20px"/></td>
                    <td>${childUnit.title}</td>
                    <td>${childUnit.firstName}</td>
                    <td>${childUnit.lastName}</td>
                    <td>${childUnit.email}</td>
                    <td>${childUnit.mobileCountryCode} - ${childUnit.mobileNumber}</td>
                    <td>${childUnit.role}</td>
                    <td>${childUnit.unit.name}</td>
                    <c:choose>
                        <c:when test="${childUnit.active}">
                            <td id="status">
                                <span id="status-${childUnit.uid}">
                                    <spring:theme code="text.account.childUnits.active.status" text="Unlocked" />
                                </span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td id="status">
                                <span id="status-${childUnit.uid}">
                                    <spring:theme code="text.account.childUnits.inactive.status" text="Locked" />
                                </span>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <input type="hidden" value="">
        </tbody>
    </table>
    <%-- End of manager user page --%>
</form:form>
<common:globalMessagesTemplates/>
