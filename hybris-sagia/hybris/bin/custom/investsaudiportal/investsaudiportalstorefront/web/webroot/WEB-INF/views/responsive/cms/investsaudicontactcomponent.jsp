<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section class="company-mgr-quote mt-0 mb-4">
    <div class="container">
        <div class="umb-grid">
            <div class="grid-section">
                <div >
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div >
                                <h1 class="heading text-left mt-4 my-0">${component.title}</h1>
                                <p> </p>
                                <h5 class="text-left mb-5 col-md-12">${component.descriptionText}</h5>
                            </div>
                        </div>
                    </div>
                </div>
                <div >
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                            <div >
                            </div>
                        </div>
                        <div class="col-md-8 column">
                            <div >
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-4 mb-4">
            <div class="col-lg-5 col-md-12 col-sm-12 contact-section">
                <div>
                    <a href="mailto:${fn:escapeXml(component.emailURL.url)}" target="_top"><p class="my-2 font-bold"><span class="myicon icon-email-filled-closed-envelope"></span>${component.emailURL.linkName} : <span class="font-normal"> ${component.emailURL.url}</span><p></a>
                </div>
                <div>
                    <a href="tel:${fn:escapeXml(component.localURL.url)}"><p class="my-2 font-bold"><span class="myicon icon-call"></span>${component.localURL.linkName} : <span class="font-normal"> ${component.localURL.url} </span><p></a>
                </div>
                <div>
                    <a href="tel:${fn:escapeXml(component.internationalURL.url)}"><p class="my-2 font-bold"><span class="myicon icon-international-calling-service-symbol"></span>${component.internationalURL.linkName} : <span class="font-normal"> ${component.internationalURL.url}</span></p></a>
                </div>
            </div>
        </div>
    </div>
</section>