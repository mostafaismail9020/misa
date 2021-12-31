<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user"%>

<c:url value="/login-second-step/authenticate" var="authenticateActionUrl" />

<div>
    <user:authenticate actionNameKey="authenticate.submit" action="${authenticateActionUrl}" />
</div>