<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="blog--search-area">
    <form class="blog--search-form" action="/simplefix/searchResults" method="GET">
        <h2 class="popularPostHeading"><spring:theme code="blog.searchArea.title"/></h2>
        <div class="form-group">
            <p class="blog--search-area-error"><spring:theme code="blog.searchArea.error"/></p>
            <div class="blog--search-wrapper">
                <spring:theme code="blog.searchArea.placeholder" var="placeholder"/>
                <input type="text" class="form-control blog--search-input" placeholder="${placeholder}" name="searchText">
                <button type="button" class="blog--search-btn btn btn-primary">
                    <span class="icon-search"></span>
                </button>
            </div>
        </div>
    </form>
</div>