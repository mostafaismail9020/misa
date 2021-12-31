<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${surveyhdrs}" var="surveyhdr">
        SurveyHDR.activity = <c:out value="${surveyhdr.surveyid}"/><br/>
    </c:forEach>
</div>
</html>