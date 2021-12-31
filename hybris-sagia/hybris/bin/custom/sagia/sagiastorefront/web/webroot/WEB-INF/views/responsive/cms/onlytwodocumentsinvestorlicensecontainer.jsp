<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

          
<div class="container-fluid invest_lic_outer document_lic_outer text-center py-4">
       <c:if test="${not empty title}">
           <div data-aos="fade-up">
            ${title}
           </div>
                <!-- <h1 class="title-heading"></h1> -->
            </c:if>
       <c:forEach var="currentComponent" items="${components}" >
            <cms:component component="${currentComponent}" element="div"/>
        </c:forEach>  

	

