
<%@ attribute name="siteMap" type="java.util.Map"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<c:forEach var="siteMapItem" items="${siteMap}">
   <a href="${siteMapItem.key.url}">${siteMapItem.key.name}</a></br></br>

  <c:if test="${not empty siteMapItem.value}">
    <nav:sagiasitemap siteMap="${siteMapItem.value}"/>
  </c:if>
</c:forEach>