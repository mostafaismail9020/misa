<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<td style="width: 49.8998%; height: 64px; text-align: center;">
    ${text}
</td>
<td style="width: 50.1002%; height: 64px;">
    <a href="${fn:escapeXml(pdfLink.url)}">
        <img style="width: 24px; height: 24px;" src="${fn:escapeXml(image.url)}" alt="" data-udi="umb://media/e9069e3b0c184451b87454a86924b84f" />
    </a>
</td>

