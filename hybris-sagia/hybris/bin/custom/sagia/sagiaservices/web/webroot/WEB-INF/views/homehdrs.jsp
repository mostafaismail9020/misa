<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${homehdrs}" var="homehdr">
        HomeHDR.activity = <c:out value="${homehdr.activity}"/><br/>
    </c:forEach>
</div>
</html>