<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:portalpage pageTitle="${pageTitle}">
    <jsp:body>
        <!--
        <script>
        document.querySelector("html").style.cssText = 'background:#000!important';
        document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
        window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
        window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
        window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
        (function(s,e,ss,i,o,n){
        if(s.console && s.console.log) { s.console.log(i);};
        o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
        n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
        })(window,document,'script','SessionForward Loaded.');
        </script>
        -->

        <header:portalPageTitle/>

        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <div class="article-details-region-page-banner"
                 style="background-image: url('/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ');">
                <div class="article-details-region-page-banner-container" data-aos="fade-up">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="article-details-region-page-general-title">
                                    Riyadh
                                </h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Article Details Page -->
            <section class="article-details-region-page">
                <div class="container">
                    <!-- Top -->
                    <div class="row">

                        <div class="col-md-3 article-details-region-page-img">
                            <img alt=""
                                 src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                        </div>

                        <div class="col-md-9">
                            <h1 class="article-details-region-page-title">Overview</h1>
                            <p>
                                The Ministry of Investment of Saudi Arabia (MISA) has
                                facilitated four more investment agreements on the sidelines
                                of
                                the Future Investment Initiative (FII6) across the
                                education,
                                entertainment and biotechnology sectors. The agreements
                                signed
                                showcase the government’s commitment to transforming quality
                                of
                                life in the Kingdom, coupled with investor recognition of
                                the
                                vast opportunities available.
                            </p>

                            <div
                                    class="article-details-region-page-info-box-container mt-5 mb-5">
                                <div class="article-details-region-page-info-box">
                                    <div class="article-details-region-page-info-box-item">
                                        <h2>8,872,712</h2>
                                        <p>Population (2020)</p>
                                    </div>

                                    <div class="article-details-region-page-info-box-item">
                                        <h2>380,000</h2>
                                        <p>Area in sq.km</p>
                                    </div>
                                    <div class="article-details-region-page-info-box-item">
                                        <h2>21</h2>
                                        <p>Governorates</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="row mt-5 mb-5">
                        <div class="col-xs-12">
                            <nav>
                                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active key-sector-tabs-link " id="nav-key-sectors-tab" data-toggle="tab"
                                       href="#nav-key-sectors" role="tab" aria-controls="nav-key-sectors"
                                       aria-selected="true">Key Sectors</a>
                                    <a class="nav-item nav-link key-sector-tabs-link " id="nav-demographics-tab" data-toggle="tab"
                                       href="#nav-demographics" role="tab" aria-controls="nav-demographics"
                                       aria-selected="false">Demographics</a>
                                    <a class="nav-item nav-link key-sector-tabs-link " id="nav-policies-tab" data-toggle="tab"
                                       href="#nav-policies" role="tab" aria-controls="nav-policies"
                                       aria-selected="false">Policies & Regulations</a>
                                </div>
                            </nav>
                            <div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
                                <div class="tab-pane fade show active" id="nav-key-sectors" role="tabpanel"
                                     aria-labelledby="nav-home-tab">
                                    <div class="row" data-role="content">
                                        <div class="col-md-4">
                                            <div class="key-sectors-video br-10">
                                                <iframe width="100%" src="https://www.youtube.com/embed/jP-z4m92UAE"
                                                        title="TAP INTO THE UNTAPPED   Invest Saudi  20200722 1000 1  ICT"
                                                        frameborder="0"
                                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                                        allowfullscreen></iframe>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <h1 class="key-sectors-title">
                                                Saudi investment ministry signs four investment agreements
                                            </h1>
                                            <p class="core-components-content">
                                                Lorem ipsum dolor sit amet consectetur. Non ut aliquam vitae suscipit
                                                orci id turpis. Et tincidunt diam nec et at nec porttitor diam. Tempor
                                                lectus vitae nisl ac pulvinar.
                                            </p>
                                            <p class="core-components-content">
                                                Lorem ipsum dolor sit amet consectetur. Non ut aliquam vitae suscipit
                                                orci id turpis. Et tincidunt diam nec et at nec porttitor diam. Tempor
                                                lectus vitae nisl ac pulvinar.
                                            </p>
                                            <div class="key-sectors-title">
                                                <a class="contact-expert-button"
                                                   href="/sectors-opportunities/opportunities">
                                                    <spring:theme code="portal.article.sector.region.view.sector"
                                                                  text="View Sector"/>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="nav-demographics" role="tabpanel"
                                     aria-labelledby="nav-demographics-tab">
                                    Demographics Tab Content
                                </div>
                                <div class="tab-pane fade" id="nav-policies" role="tabpanel"
                                     aria-labelledby="nav-policies-tab">
                                    Policies & Regulations Tab Content
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="key-sectors-container">


                        <div class="boxes">


                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 1</p>

                            </div>
                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 2</p>

                            </div>
                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 3</p>

                            </div>

                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 4</p>

                            </div>
                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 5</p>

                            </div>
                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 6</p>

                            </div>
                            <div class="box">
                                <div class="icon sectors-icon"></div>
                                <p>Sector 7</p>

                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="investment-opportunities-title mt-3 mb-3">
                                Investment Opportunities
                            </div>
                        </div>

                        <div class="investment-opportunities-items">
                            <div class="investment-opportunities-item col-md-4">
                                <div class="investment-opportunities-item-img">
                                    <img alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="investment-opportunities-texts">
                                    <div class="investment-opportunities-header">
                                        Digital Twins For Tourism, Heritage And Events
                                    </div>
                                    <div class="investment-opportunities-content">
                                        Amet minim mollit non deserunt ullamco est sit
                                        aliqua dolor do amet sint.
                                    </div>
                                    <div class="investment-opportunities-footer">
                                        Market CAGR 19-26 +3%
                                    </div>
                                </div>
                            </div>
                            <div class="investment-opportunities-item col-md-4">
                                <div class="investment-opportunities-item-img">
                                    <img alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="investment-opportunities-texts">
                                    <div class="investment-opportunities-header">
                                        Digital Twins For Tourism, Heritage And Events
                                    </div>
                                    <div class="investment-opportunities-content">
                                        Amet minim mollit non deserunt ullamco est sit
                                        aliqua dolor o amet sint.
                                    </div>
                                    <div class="investment-opportunities-footer">
                                        Market CAGR 19-26 +3%
                                    </div>
                                </div>
                            </div>
                            <div class="investment-opportunities-item col-md-4">
                                <div class="investment-opportunities-item-img">
                                    <img alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="investment-opportunities-texts">
                                    <div class="investment-opportunities-header">
                                        Digital Twins For Tourism, Heritage And Events
                                    </div>
                                    <div class="investment-opportunities-content">
                                        Amet minim mollit non deserunt ullamco est sit
                                        aliqua dolor o amet sint.
                                    </div>
                                    <div class="investment-opportunities-footer">
                                        Market CAGR 19-26 +3%
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mt-3">
                            <a class="view-opportunity-button"
                               href="/sectors-opportunities/opportunities">
                                <spring:theme
                                        code="portal.article.sector.details.view.opportunities"
                                        text="View all opportunities"/>
                            </a>
                        </div>
                    </div>

                    <div class="latest-news mt-5 mb-5">
                        <div class="row">
                            <div class="col-md-12 mb-4 latest-news-top">
                                <h1 class="title">Latest News</h1>

                                <div class="latest-news-pagination opportunity-pagination">
                                    <ul
                                            class="pagination pg-darkgrey opportunity-pagination-bottom ">

                                        <li class="page-item prev prev-item">
                                            <a class="page-link waves-effect waves-light"
                                               href="/test">
                                                < </a>
                                        </li>

                                        <li class="page-item active">
                                            <a class="page-link waves-effect waves-light"
                                               href=/test> 1 of 2</a>
                                        </li>
                                        <li class="page-item next-item">
                                            <a class="page-link waves-effect waves-light arrow-left"
                                               href="${url}${param.page+1}">
                                                >
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="date" style="position: absolute;">
                                    <span class="day">30</span>
                                    <span class="month">DEC</span>
                                </div>
                                <div>
                                    <img class="latest-news-card-img" alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="latest-news-time-location">
                                    <span class="event-time">12:00 PM - 01:00 PM</span>
                                    <span class="event-location">Location</span>
                                </div>
                                <div class="latest-news-card-bottom">Saudi investment ministry signs four investment
                                    agreements
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="date" style="position: absolute;">
                                    <span class="day">30</span>
                                    <span class="month">DEC</span>
                                </div>
                                <div>
                                    <img class="latest-news-card-img" alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="latest-news-time-location">
                                    <span class="event-time">12:00 PM - 01:00 PM</span>
                                    <span class="event-location">Location</span>
                                </div>
                                <div class="latest-news-card-bottom">Saudi investment ministry signs four investment
                                    agreements
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="date" style="position: absolute;">
                                    <span class="day">30</span>
                                    <span class="month">DEC</span>
                                </div>
                                <div>
                                    <img class="latest-news-card-img" alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="latest-news-time-location">
                                    <span class="event-time">12:00 PM - 01:00 PM</span>
                                    <span class="event-location">Location</span>
                                </div>
                                <div class="latest-news-card-bottom">Saudi investment ministry signs four investment
                                    agreements
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="date" style="position: absolute;">
                                    <span class="day">30</span>
                                    <span class="month">DEC</span>
                                </div>
                                <div>
                                    <img class="latest-news-card-img" alt=""
                                         src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"/>
                                </div>
                                <div class="latest-news-time-location">
                                    <span class="event-time">12:00 PM - 01:00 PM</span>
                                    <span class="event-location">Location</span>
                                </div>
                                <div class="latest-news-card-bottom">Saudi investment ministry signs four investment
                                    agreements
                                </div>
                            </div>


                        </div>
                    </div>


                </div>
            </section>
            <!-- Article Details Page -->
        </main>
    </jsp:body>
</template:portalpage>