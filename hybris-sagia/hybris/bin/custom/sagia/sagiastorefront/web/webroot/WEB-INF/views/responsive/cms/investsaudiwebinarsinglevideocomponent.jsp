<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="mb-5 general-info container">
    <div class="video-player-container">
        <div class="embed-responsive embed-responsive-16by9">
            <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" src="${fn:escapeXml(embedURL.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
        </div>
    </div>
    <div class="mt-2">
        <p>${description}</p>
    </div>
    <div class="mx-auto mt-2 d-flex justify-content-end" style="font-size: 1.5rem;">
        <a title="Share it on Twitter" href="${fn:escapeXml(twitterURL.url)}" target="_blank">
            <i class="fa fa-twitter" aria-hidden="true"></i>
        </a>
        <a title="Post it on LinkedIn" class="ml-2" href="${fn:escapeXml(linkedinURL.url)}" target="_blank">
            <i class="fa fa-linkedin-square" aria-hidden="true"></i>
        </a>
    </div>
</section>
