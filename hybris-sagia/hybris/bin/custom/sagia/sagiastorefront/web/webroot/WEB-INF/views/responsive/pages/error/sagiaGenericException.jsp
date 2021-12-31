<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<!DOCTYPE html>
<html dir="ltr">
<header>
    <link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css"/>
</header>

<body>
<c:url value="/" var="homePageUrl" />
<div class="errorPage">
    <div class="errorPage-heroImage">
        <icon:attention-error/>
    </div>
    <div class="errorPage-description">
        <h1><spring:theme code="error.errorpagemessage" htmlEscape="false"/></h1>
        <br/>
        <br/>
        <h2>${errorMessage}</h2>
    </div>
    <div class="errorPage-actions">
        <button type="button" class="btn btn_wide" onclick="location.href='${homePageUrl}';">Return to dashboard</button>
    </div>
</div>
</body>
</html>