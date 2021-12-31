<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="container-fluid invest_lic_outer text-center py-4 investment_outer">
    <div class="row m-0">
        <div class="col-md-12 title-area">
            <c:if test="${not empty title}">
                <div data-aos="fade-up">${title}</div>
            </c:if> 
        </div>
        <div class="col-md-12">
            <div class="d-flex investor_lic_invest_inner pt-5"> 
                <div class=" bussiness_lic_invest_inner_box">
                    <c:forEach var="currentComponent" items="${components}" >
                        <cms:component component="${currentComponent}" element="div" class=" inner_box" data-aos="fade-up"/>
                    </c:forEach>   
                </div>
            </div>
        </div>
    </div>
</div>

