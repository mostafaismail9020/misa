<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>


<c:forEach items="${pageData.sorts}" var="sort">
    <c:if test="${sort.selected}">
        <c:set var="sortSelected" value="${fn:escapeXml(sort.code)}"/>
    </c:if>
</c:forEach>

<c:forEach items="${pageData.facets}" var="facet">
    <nav:facetNavRefinementFacetDropdown facetData="${facet}" sortCodeSelected="${sortSelected}"/>
</c:forEach>


