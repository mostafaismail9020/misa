<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<div class="container">
    <c:forEach items="${legalConsultation_list}" var="legalConsultation">
        <c:out value="${legalConsultation.srId}"/><br/>
    </c:forEach>

<h1>${legalConsultation_detail.srGuid}</h1>

<h1>${legalConsultation_attachment}</h1>

<h1>${legalConsultation_content}</h1>

<h1>${legalConsultation_gettext}</h1>


</html>