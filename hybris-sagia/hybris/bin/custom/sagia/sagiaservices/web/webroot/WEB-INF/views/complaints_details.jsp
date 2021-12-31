<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${list}" var="complaint">
        Complaint.category1 = <c:out value="${complaint.category1}"/><br/>
    </c:forEach>
</div>
</html>