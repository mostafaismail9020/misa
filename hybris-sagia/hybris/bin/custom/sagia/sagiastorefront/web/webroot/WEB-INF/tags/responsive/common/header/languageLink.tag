<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="currentLanguage" required="true" %>
<%@ attribute name="newLanguage" required="true" %>
<%@ attribute name="additionalCssClass" required="false" %>
<%@ attribute name="text" required="true" %>

<c:set var="currentLang" value="lang=${currentLanguage}"/>
<c:set var="newLang" value="lang=${newLanguage}"/>
<c:set var="languageURL" value="?${newLang}"/>
<c:set var="indexOfLang" value="${fn:indexOf(requestScope['javax.servlet.forward.query_string'], currentLang)}"/>

<%--<c:choose>--%>
    <%--<c:when test="${indexOfLang >= 0}">--%>
        <%--<c:set var="languageURL" value="?${fn:replace(requestScope['javax.servlet.forward.query_string'],currentLang,newLang)}"/>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
        <%--<c:if test="${not empty requestScope['javax.servlet.forward.query_string']}">--%>
            <%--<c:set var="languageURL" value="?lang=${newLanguage}&${requestScope['javax.servlet.forward.query_string']}"/>--%>
        <%--</c:if>--%>
    <%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--<li class="nav-item text-center ${additionalCssClass}">--%>
    <%--<a class="nav-link" href="${languageURL}">${text}</a>--%>
<%--</li>--%>

<c:set var="currentL" value="/${currentLanguage}/"/>
<c:set var="newL" value="/${newLanguage}/"/>
<li class="nav-item text-center ${additionalCssClass}">
    <a class="nav-link" href="${fn:replace(requestScope['javax.servlet.forward.request_uri'], currentL, newL)}">${text}</a>
</li>