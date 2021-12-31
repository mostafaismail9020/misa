<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="sectors-opp sectors-investment pb-5">
    <div class="container succ-search-container">
        <div class="row justify-content-center pos-rel mt-4">
            <div class="col-lg-8 col-md-8">
                <input type="search" placeholder="Search by opportunity name">
                <a class="a-search"><i class="fa fa-search" aria-hidden="true"></i></a>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-lg-12 col-md-12 sectors-list">
                <h5><spring:theme code="text.label.chooseSector"/></h5>
                <a data-sectorid="0"><spring:theme code="text.label.all"/></a>
                <c:forEach var="subCategory" items="${categoryList}">
                    <a data-sectorid="${subCategory.code}">${subCategory.name}</a>
                </c:forEach>
            </div>
            <div class="col-lg-12 col-md-12 text-center mt-4">
                <button type="button" class="button btn org-button btn-green search-btn"><spring:theme code="text.button.applySearch"/> <i class="fa fa-long-arrow-right"></i></button>
                <a class="reset-search small-font"><spring:theme code="text.button.clearSearch"/></a>
            </div>
        </div>
    </div>
</section>