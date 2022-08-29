<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />
    	
        <!-- Banner start -->
        <cms:pageSlot position="PortalPageMain" var="feature">
            <cms:component component="${feature}" element="div" class=""/>
        </cms:pageSlot>
        <!-- Banner end -->

        <!--  Investment Reports start -->
        <div class="container investment-reports-container">
            <div class="row">
                                   		<div class="col-12 col-lg-3 mb-5">
                                            	<div class="news-card">
                                                	<div class="news-date text-center">
                                                    	<div class="day">9</div>
                                                        <div class="month">November</div>
            										</div>
                                                    <img class="img-fluid w-100 news-card-img" src="/medias/news-national-center-for-waste-management-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzU0MTN8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaGQ3L2gyOS84ODEwOTU4MjI1NDM4LmpwZ3w5YjYzYmEwZTEzYTJiOGY0ZjM1MTBmMTc0NmJlODEyNDE1NjYwNjhiOTFmNjZlZWI4MGFiYmEzN2UwNDI0ZWYy" alt="The Ministry of Investment and the National Center for Waste Management sign a cooperation agreement to activate investments in the waste management sector">
                                                    <div class="news-card-inner">
                                                        <h3 title="The Ministry of Investment and the National Center for Waste Management sign a cooperation agreement to activate investments in the waste management sector">The Ministry of Investment and the National Center for Waste Management sign a cooperation agreement to activate investments in the waste management sector</h3>
                                                        <p>Saudi Arabia's Ministry of Investment has inked a cooperation agreement with the kingdom's National Center for Waste Management to boost investment prospects in the waste management sector.</p>
                                                        <a class="btn btn-primary-fill btn-knowmore" href="/en/mediaCenter/news/news-national-center-for-waste-management">Know More&nbsp;
                                                        	<span class="arow-icon"><img class="img-fluid" src="/_ui/responsive/common/images/know-more.png" alt=""></span>
                                                       </a>
            										</div>
            									</div>
            								</div>

            							<div class="col-12 col-lg-3 mb-5">
                                            	<div class="news-card">
                                                	<div class="news-date text-center">
                                                    	<div class="day">3</div>
                                                        <div class="month">May</div>
            										</div>
                                                    <img class="img-fluid w-100 news-card-img" src="/medias/news-ministers-of-Misa-and-Malaysia-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzIxMjV8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDBhL2g1Ni84ODEwOTU3OTk2MDYyLmpwZ3xlOTE0ZjY5ODgyMGE5MDQxOWE4MDAxOTgzNDBhMDNiNzYwMjE3ZGNmMGJlMjkzYTI3MjIzMzEyNDYxYTc4ODU0" alt="Minister of Investment, Malaysian Minister of International Trade Discuss Enhancing Cooperation and Investment Opportunities">
                                                    <div class="news-card-inner">
                                                        <h3 title="Minister of Investment, Malaysian Minister of International Trade Discuss Enhancing Cooperation and Investment Opportunities">Minister of Investment, Malaysian Minister of International Trade Discuss Enhancing Cooperation and Investment Opportunities</h3>
                                                        <p>Minister of Investment Eng. Khalid bin Abdulaziz Al-Falih met here yesterday with Malaysian Minister of International Trade and Industry Mohamed Azmin Ali, and his accompanying delegation, during his official visit to the Kingdom of Saudi Arabia.</p>
                                                        <a class="btn btn-primary-fill btn-knowmore" href="/en/mediaCenter/news/news-ministers-of-Misa-and-Malaysia">Know More&nbsp;
                                                        	<span class="arow-icon"><img class="img-fluid" src="/_ui/responsive/common/images/know-more.png" alt=""></span>
                                                       </a>
            										</div>
            									</div>
            								</div>


            							<div class="col-12 col-lg-3 mb-5">
                                            	<div class="news-card">
                                                	<div class="news-date text-center">
                                                    	<div class="day">8</div>
                                                        <div class="month">February</div>
            										</div>
                                                    <img class="img-fluid w-100 news-card-img" src="/medias/news-Roche-and-misa-partnership-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzM2MTN8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDE5L2hhYS84ODExNzI0NzM0NDk0LmpwZ3xmYTYxNmE5YmRjODQ2MTI1NDZkOGMwZjZhNzJlZjI0ZGFkM2IwNDM5M2VhZTdjOGJhMDQ5YjRjNDhlNzQ5ZTQz" alt="Ministry of Investment and Roche announce new partnership to unlock opportunities in the Saudi life sciences sector">
                                                    <div class="news-card-inner">
                                                        <h3 title="Ministry of Investment and Roche announce new partnership to unlock opportunities in the Saudi life sciences sector">Ministry of Investment and Roche announce new partnership to unlock opportunities in the Saudi life sciences sector</h3>
                                                        <p>The Ministry of Investment of Saudi Arabia (MISA) and Roche Products Saudi Arabia (RPSA) have signed a memorandum of understanding (MoU) to establish a new partnership that will see the two entities collaborate on initiatives to develop the kingdom's healthcare and life sciences sector.</p>
                                                        <a class="btn btn-primary-fill btn-knowmore" href="/en/mediaCenter/news/news-roche-and-misa">Know More&nbsp;
                                                        	<span class="arow-icon"><img class="img-fluid" src="/_ui/responsive/common/images/know-more.png" alt=""></span>
                                                       </a>
            										</div>
            									</div>
            								</div>

            							<div class="col-12 col-lg-3 mb-5">
                                            	<div class="news-card">
                                                	<div class="news-date text-center">
                                                    	<div class="day">23</div>
                                                        <div class="month">January</div>
            										</div>
                                                    <img class="img-fluid w-100 news-card-img" src="/medias/news-saudi-uzbek-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMTMxMzV8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaGJhL2gxYS84ODExMTk5Mzk3OTE4LmpwZ3xkMmJlOTdlNjMwYTMzZmI5OGExMDk4ZDYzMTU1MjliMzBmZGJkNzczODZkMTE2MGNlNzQ2Y2Y3OGZkYWMwYzBj" alt="Saudi, Uzbek officials build ties at investment forum in Bukhara">
                                                    <div class="news-card-inner">
                                                        <h3 title="Saudi, Uzbek officials build ties at investment forum in Bukhara">Saudi, Uzbek officials build ties at investment forum in Bukhara</h3>
                                                        <p>The first Saudi-Uzbek Investment Forum was held recently in the Uzbek city of Bukhara.The forum was held under the co-auspices of Minister of Investment Khalid Al-Falih and the Uzbek deputy Prime Minister and Minister of Investment and Foreign Trade, Sardor Umurzakov.</p>
                                                        <a class="btn btn-primary-fill btn-knowmore" href="/en/mediaCenter/news/news-saudi-uzbek1">Know More&nbsp;
                                                        	<span class="arow-icon"><img class="img-fluid" src="/_ui/responsive/common/images/know-more.png" alt=""></span>
                                                       </a>
            										</div>
            									</div>
            								</div>

            							</div>
        </div>
        <!--  Investment Reports end -->
    </jsp:body>
</template:portalpage>