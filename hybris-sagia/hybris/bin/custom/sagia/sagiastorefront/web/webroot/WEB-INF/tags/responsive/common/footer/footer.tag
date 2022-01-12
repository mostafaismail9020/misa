<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/responsive/modals" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<cms:pageSlot position="SagiaFooter" var="feature">
    <cms:component component="${feature}"/>
</cms:pageSlot>

<!-- <c:if test="${currentLanguage.isocode == 'en'}">
    <div class="investsaudiFooter">
        <footer class="footer">
            <div class="container">
                <div class="row no-gutters">
                    <div class="col-lg-1 col-md-12 investsaudiFooter-logos">
                        <div class="investsaudiFooter-section investsaudiFooter-section_first">
                            <div class="investsaudiFooter-section-item">
                                <a class="nav-link" target="_blank" href="https://investsaudi.sa/en/">
                                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.svg"/>
                                </a>
                            </div>
                            <div class="investsaudiFooter-section-item">
                                <img class="sagia" src="${themeResourcePath}/img/SAGIA.png">
                            </div>
                            <div class="investsaudiFooter-section-item hide_lg_up">
                                <a class="nav-link" href="http://vision2030.gov.sa/ar/" target="_blank"><img src="${themeResourcePath}/img/roya.svg" class="sagia2"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12 border-right">
                        <div class="investsaudiFooter-section investsaudiFooter-section_bdb">
                            <ul class="investsaudiFooter-list01">
                                <li><a href="https://investsaudi.sa/en/why-saudi-arabia/"><spring:message code="footer.whySaudiArabia.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/"><spring:message code="footer.sectorsAndOpportunities.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/investors-services/"><spring:message code="footer.investorsServices.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/about/general-investment-authority/"><spring:message code="footer.aboutUs.text"/></a></li>
                                <li><a class="nav-link" href="https://investsaudi.sa/en/contact/"><spring:message code="footer.contact.text"/></a></li>
                                <li><a class="nav-link" href="https://investsaudi.sa/en/faq/"><spring:message code="footer.faq.text"/></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-5 col-xs-12 border-right">
                        <div class="investsaudiFooter-section investsaudiFooter-section_bdb">
                            <h6 class="investsaudiFooter-headline"><spring:message code="footer.sectorsAndIndustries.text"/></h6>
                            <ul class="investsaudiFooter-list02">                            
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/chemicals/"><spring:message code="footer.chemicals.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/energy-water/"><spring:message code="footer.energyAndWater.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/healthcare-and-life-sciences/"><spring:message code="footer.healthcareAndLifeSciences.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/mining-metals/"><spring:message code="footer.miningAndMetals.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/tourism-culture-entertainment/"><spring:message code="footer.tourismCultureAndEntertainment.text"/></a></li>                                
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/information-technology/"><spring:message code="footer.informationAndTechnology.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/industrial-manufacturing/"><spring:message code="footer.industrialAndManufacturing.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/emerging-sectors/"><spring:message code="footer.emergingSectors.text"/></a></li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/transport-logistics/"><spring:message code="footer.transportAndLogistics.text"/></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="investsaudiFooter-section investsaudiFooter-section_last">
                            <%--<div class="subscribe-text"><spring:theme code="subscribe.text"/> <span class="diff-color"> <spring:theme code="subscribe.all"/></span>  <spring:theme code="subscribe.sector"/></div>--%>
                            <%--<div class="subscribe-area">--%>
                                <%--<input class="input-text" placeholder="Enter your email" type="text"/>--%>
                                <%--<div class="button btn org-button btn-green subscribe-btn"><spring:theme code="subscribe"/></div>--%>
                            <%--</div>--%>
                            <div class="follow-us"><spring:theme code="followUs"/></div>
                            <ul class="social-links">
                                <li><a href="https://www.linkedin.com/company/sagia/" target="_blank"><span class="icon icon-linked-in"></span></a></li>
                                <li><a href="https://twitter.com/SAGIAgov" target="_blank"><span class="icon icon-twitter"></span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <div class="copyright py-4 text-center text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-12 col-12">&copy; <spring:theme code="copyright"/></div>
                    <div class="col-md-6 col-sm-12 col-12 float-right">
                        <ul class="nav-bottom-footer">
                            <li class="nav-item"><a href="https://sagia.gov.sa/en/privacy-policy/"><spring:theme code="privacyPolicy"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if> -->

<!-- <c:if test="${currentLanguage.isocode == 'ar'}">
    <div class="investsaudiFooter">
        <footer class="footer">
            <div class="container">
                <div class="row no-gutters">
                    <div class="col-lg-1 col-md-12 investsaudiFooter-logos">
                        <div class="investsaudiFooter-section investsaudiFooter-section_first">
                            <div class="investsaudiFooter-section-item">
                                <a class="nav-link" target="_blank" href="https://investsaudi.sa/ar/">
                                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.svg" />
                                </a>
                            </div>
                            <div class="investsaudiFooter-section-item">
                                <img class="sagia" src="${themeResourcePath}/img/SAGIA.png">
                            </div>
                            <div class="investsaudiFooter-section-item hide_lg_up">
                                <a class="nav-link" href="#"><img src="${themeResourcePath}/img/roya.svg" class="sagia2"></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12 border-right">
                        <div class="investsaudiFooter-section investsaudiFooter-section_bdb">
                            <ul class="investsaudiFooter-list01">
                                <li><a href="WhySaudiArabia/why-ksa-ar.html"><spring:message code="footer.whySaudiArabia.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/sectors-and-opp-ar.html"><spring:message code="footer.sectorsAndOpportunities.text"/></a>
                                </li>
                                <li>
                                    <a href="investor-services/invest-services-ar.html"><spring:message code="footer.investorsServices.text"/></a>
                                </li>
                                <li>
                                    <a href="about-us/about-us-ar.html"><spring:message code="footer.aboutUs.text"/></a>
                                </li>
                                <li>
                                    <a href="contact-us/contact-sagia-ar.html"><spring:message code="footer.contact.text"/></a>
                                </li>
                                <li>
                                    <a href="WhySaudiArabia/faq-ar.html"><spring:message code="footer.faq.text"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-5 col-xs-12 border-right">
                        <div class="investsaudiFooter-section investsaudiFooter-section_bdb">
                            <h6 class="investsaudiFooter-headline"><spring:message code="footer.sectorsAndIndustries.text"/></h6>
                            <ul class="investsaudiFooter-list02"> 
                                <li>
                                    <a href="SectorAndOpportunities/chemical-ar.html"><spring:message code="footer.chemicals.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/ict-ar.html"><spring:message code="footer.informationAndTechnology.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/machinery-and-equipment-ar.html"><spring:message code="footer.industrialAndManufacturing.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/mentals-ar.html"><spring:message code="footer.miningAndMetals.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/transport-ar.html"><spring:message code="footer.transportAndLogistics.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/tourism-ar.html"><spring:message code="footer.tourismCultureAndEntertainment.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/energy-and-power-ar.html"><spring:message code="footer.energyAndWater.text"/></a>
                                </li>
                                <li>
                                    <a href="SectorAndOpportunities/emerging-sectors-ar.html"><spring:message code="footer.emergingSectors.text"/></a>
                                </li>
                                <li><a href="https://investsaudi.sa/en/sectors-opportunities/healthcare-and-life-sciences/"><spring:message code="footer.healthcareAndLifeSciences.text"/></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12">
                        <div class="investsaudiFooter-section investsaudiFooter-section_last">
                            <%--<div class="subscribe-text mt-4">--%>
                                <%--<spring:theme code="subscribe.text"/>--%>
                                <%--<span class="diff-color"> <spring:theme code="subscribe.all"/></span> --%>
                                <%--<spring:theme code="subscribe.sector"/>--%>
                            <%--</div>--%>
                            <%--<div class="subscribe-area">--%>
                                <%--<input class="input-text my-3"--%>
                                       <%--placeholder="&#x623;&#x62f;&#x62e;&#x644; &#x628;&#x631;&#x64a;&#x62f;&#x643; &#x627;&#x644;&#x625;&#x644;&#x643;&#x62a;&#x631;&#x648;&#x646;&#x64a;"--%>
                                       <%--type="text">--%>
                                <%--<div class="button btn org-button btn-green subscribe-btn"><spring:theme code="subscribe"/></div>--%>
                            <%--</div>--%>
                            <div class="follow-us"><spring:theme code="followUs"/></div>
                            <ul class="social-links">
                                <li>
                                    <a href="https://www.linkedin.com/company/sagia/" target="_blank"><span class="icon icon-linked-in"></span></a>
                                </li>
                                <li>
                                    <a href="https://twitter.com/SAGIAgov" target="_blank"><span class="icon icon-twitter"></span></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <div class="copyright py-4 text-center text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-12 col-12 float-right">&copy; <spring:theme code="copyright"/></div>
                    <div class="col-md-6 col-sm-12 col-12">
                        <ul class="nav-bottom-footer">
                            <li class="nav-item"><a href="https://sagia.gov.sa/ar/privacy-policy/"><spring:theme code="privacyPolicy"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if> -->
<div id="reviewRating">
    <input type="hidden" id="requestFeedback" value="${requestFeedback}"/>
    <modal:ratingWithComments/>
</div>
<div id="emalSending">
    <input type="hidden" class="js-email-sended-message" value="${sendedEmail}"/>
    <modal:emailSendedModal/>
</div>