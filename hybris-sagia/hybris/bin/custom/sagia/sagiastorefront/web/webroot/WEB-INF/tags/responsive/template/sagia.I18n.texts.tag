<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>

<script>
    var SAGIA = SAGIA || {};
    SAGIA.i18n = SAGIA.i18n || {};
    SAGIA.locale = '${sagiaJsTextsLocale}';

    <c:forEach items="${sagiaJsTexts}" var="entry">
        SAGIA.i18n['${entry.key}'] = "${entry.value}";
    </c:forEach>
</script>