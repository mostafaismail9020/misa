<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${classificationUpgrade_list}" var="classificationUpgrade">
        <c:out value="${classificationUpgrade.OBJECT_ID}"/><br/>
        <c:out value="${classificationUpgrade.STATUS}"/><br/>
    </c:forEach>
</div>

<h1>${classificationUpgrade_detail.objectID}</h1>


<div class="container">
    <c:forEach items="${classificationUpgrade_attachment}" var="classificationUpgrade">
        <c:out value="${classificationUpgrade.attachmentName}"/><br/>
    </c:forEach>
</div>

<div class="container">
    <c:forEach items="${classificationUpgrade_appeal}" var="classificationUpgrade">
        <c:out value="${classificationUpgrade.text}"/><br/>
    </c:forEach>
</div>

<h1>${classificationUpgrade_create.returnProperty}</h1>
</html>