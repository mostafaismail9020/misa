<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="result" required="true" type="de.hybris.platform.commercefacades.product.data.OpportunityData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="content-box">
      <table class="table table-bordered">
          <tbody>
              <tr>
                  <td>${fn:toLowerCase(result.opportunity.name)}</td>
                  <td>${fn:toLowerCase(result.parentCategory.name)}</td>
                   <td>IRR=15%</td>
              </tr>
          </tbody>
      </table>
</div>