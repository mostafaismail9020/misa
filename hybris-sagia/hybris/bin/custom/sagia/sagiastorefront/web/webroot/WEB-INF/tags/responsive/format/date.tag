<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="value" required="true" type="java.lang.String" %>


<fmt:parseDate value="${value}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both"/>
<fmt:formatDate value="${parsedDate}" pattern="${localizedDateController}" var="displayDate"/>
${displayDate}