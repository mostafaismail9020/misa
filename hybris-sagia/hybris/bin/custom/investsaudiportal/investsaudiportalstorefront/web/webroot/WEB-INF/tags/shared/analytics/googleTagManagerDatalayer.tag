<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<script>
    $(function () {
        dataLayer.push({
            'event': 'page_loaded',
            'pageName': '${cmsPage.name}'
        });
        $(".fixed-registration,.new-reg").on("click", function () {
            dataLayer.push({
                'event': 'fire_event',
                'category': '${cmsPage.name}' + " Page",
                'action': 'Apply Now Button'
            });
        });
        $(".account-login,.acc-login").on("click", function () {
            dataLayer.push({
                'event': 'fire_event',
                'category': '${cmsPage.name}' + " Page",
                'action': 'Account Login Button'
            });
        });
    })
</script>