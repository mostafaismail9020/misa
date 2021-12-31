<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${isicDetails_list}" var="isicDetails">
        <c:out value="${isicDetails.sectionDescription}"/><br/>
    </c:forEach>
</div>

</html>
