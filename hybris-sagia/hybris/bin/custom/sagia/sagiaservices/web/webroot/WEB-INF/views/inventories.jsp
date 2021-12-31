<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div class="container">
    <c:forEach items="${inventories}" var="inventory">
        Inventory.id = <c:out value="${inventory.inventoryId}"/><br/>
    </c:forEach>
</div>
</html>