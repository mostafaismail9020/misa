<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class="mb-5 general-info mt-4">
    <table border="1" style="border-collapse: collapse; width: 70%; margin-left: auto; margin-right: auto; height: 208px;">
        <tbody>
            <tr style="height: 23px;">
                <td style="width: 49.8998%; height: 24px; text-align: center;"><span style="color: #5bc53b;"><spring:theme code="mc.best.practices.table.title"/></span></td>
                <td style="width: 50.1002%; text-align: center; height: 24px;"><span style="color: #5bc53b;"><spring:theme code="mc.best.practices.table.link"/></span></td>
            </tr>
            <c:forEach var="currentComponent" items="${components}" varStatus="status">
                <cms:component component="${currentComponent}" element="tr" style="text-align: center;"/>
            </c:forEach>
        </tbody>
    </table>
</section>
