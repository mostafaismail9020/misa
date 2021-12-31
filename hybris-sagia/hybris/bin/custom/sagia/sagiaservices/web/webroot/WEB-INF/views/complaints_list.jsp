<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${complaints_list}" var="complaint">
        Complaint.bpGuid = <c:out value="${complaint.bpGuid}"/><br/>
    </c:forEach>
</div>
</html>