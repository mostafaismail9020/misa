<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

</header>
<c:if test="${fn:length(breadcrumbs) > 0}">
  <div class="breadcrumb-section">
    <div class="account-section">
      <div class="row banner-heading">
        <h1>
          <c:forEach items="${breadcrumbs}" var="breadcrumb" varStatus="status">
            <c:if test="${status.last}">
              ${fn:escapeXml(breadcrumb.name)}
            </c:if>
          </c:forEach>
        </h1>
      </div>
      <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
    </div>
  </div>
</c:if>