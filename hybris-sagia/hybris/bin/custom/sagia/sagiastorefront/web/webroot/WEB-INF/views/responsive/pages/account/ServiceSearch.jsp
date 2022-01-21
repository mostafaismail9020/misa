<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="services.services"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification">
                        <div class="count-notification">123</div>
                        <a href="${encodedContextPath}/my-sagia/notifications">
                            <span></span>
                        </a>
                    </div>
                </c:if>
                <div class="profile">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
                        <span></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_grey">
    <div class="container">

        <!-- <div class="mainSection-header">
            <h1 class="mainSection-headline mb-3"><spring:theme code="services.services"/></h1>
        </div> -->
        <div class="col-xs-12 col-md-12 services-category-list">
            <ul class="nav nav-pills sagiaNavigation-services" id="pills-tab" role="tablist">
                <li class="nav-item col-md-4 col-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link active" id="pills-licensing-tab" data-toggle="pill" href="#licensing-service" role="tab" aria-controls="pills-licensing" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="/_ui/responsive/common/images/B2C/Services icon 1.png" alt="LICENSING SERVICES" class="text-center service-icon-1"/>
                                <img src="/_ui/responsive/common/images/B2C/Services icon 2.png" alt="LICENSING SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading">LICENSING SERVICES</span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="nav-item col-md-4 col-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-govt-tab" data-toggle="pill" href="#govt-service" role="tab" aria-controls="pills-govt" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="/_ui/responsive/common/images/B2C/Services icon 1.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-1"/>
                                <img src="/_ui/responsive/common/images/B2C/Services icon 2.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading">GOVERNMENTAL SERVICES</span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="nav-item col-md-4 col-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-misa-tab" data-toggle="pill" href="#misa-service" role="tab" aria-controls="pills-misa" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="/_ui/responsive/common/images/B2C/Services icon 1.png" alt="MISA SERVICES" class="text-center service-icon-1"/>
                                <img src="/_ui/responsive/common/images/B2C/Services icon 2.png" alt="MISA SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading">MISA SERVICES</span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="mobile_services">
                    <c:forEach items="${SagiaServices}" var="category">
                        <c:forEach items="${category.value}" var="service">
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade service-wrapper show active service_tab_pane_show pb-3 m-auto" id="licensing-service-1" role="tabpanel" aria-labelledby="pills-licensing-tab">
                                        <div class="p-4 serviceModule-detail m-5">
                                            <p class="INS_letter_set_para pb-3 mb-3">${category.value[0].category.description}</p>
                                            <div id="serviceModule" class="serviceModule serviceModule_list mr-5">
                                                <div class="serviceModule-section">
                                                    <div class="serviceModule-content">
                                                        <div class="serviceModule-description">
                                                            <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                            <div class="serviceModule-detail">
                                                                <div class="w-75">
                                                                    <p>${service.description}</p>
                                                                </div>
                                                                <div class="w-25 serviceModule-request">
                                                                    <a class="text-uppercase request-service" href=${service.url}>Request A Service</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:forEach>
                    </li>
            </ul>
            <div class="tab-content desktop_services services-container-tabcontent" id="pills-tabContent">
                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                    <c:if test="${category.key=='Licensing Services'}">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show active pb-5 m-auto" id="licensing-service" role="tabpanel" aria-labelledby="pills-licensing-tab">
                            <div class="p-4 serviceModule-detail m-5">
                                <p class="INS_letter_set_para pb-3 mb-3">${category.value[0].category.description}</p>
                                <c:forEach items="${category.value}" var="service">
                                    <div id="serviceModule" class="serviceModule serviceModule_list mr-5">
                                        <div class="serviceModule-section">
                                            <div class="serviceModule-content">
                                                <div class="serviceModule-description">
                                                    <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                    <div class="serviceModule-detail">
                                                        <div class="w-75">
                                                            <p>${service.description}</p>
                                                        </div>
                                                        <div class="w-25 serviceModule-request">
                                                            <a class="text-uppercase request-service" href=${service.url}>Request A Service</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <!-- <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">                 -->
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show active pb-5 m-auto" id="govt-service" role="tabpanel" aria-labelledby="pills-govt-tab">
                        <div class="p-4 serviceModule-detail m-5">
                            <p class="INS_letter_set_para pb-3 mb-3">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                            <div class="accordion pb-1" id="govtServices">
                                <div class="card collapsed" data-toggle="collapse" data-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                    <div class="card-header" id="service-heading-g${loop.index}">
                                    <h5 class="mb-0">
                                        ${category.key}
                                    </h5>
                                    </div>
                                
                                    <div id="collapse-g${loop.index}" class="collapse" aria-labelledby="service-heading-g${loop.index}" data-parent="#govtServices">
                                        <div class="card-body serviceModule-detail">
                                            <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                                when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                                remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                                and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                            </p>

                                            <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list mr-5">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail">
                                                                    <div class="w-75">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=${service.url}>Request A Service</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show active pb-5 m-auto" id="misa-service" role="tabpanel" aria-labelledby="pills-misa-tab">
                        <div class="p-4 serviceModule-detail m-5">
                            <p class="INS_letter_set_para pb-3 mb-3 ">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                            <div class="accordion pb-1" id="misaServices">
                                <div class="card collapsed" data-toggle="collapse" data-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                    <div class="card-header" id="service-heading-m${loop.index}">
                                    <h5 class="mb-0">
                                        ${category.key}
                                    </h5>
                                    </div>
                                
                                    <div id="collapse-m${loop.index}" class="collapse" aria-labelledby="service-heading-m${loop.index}" data-parent="#misaServices">
                                        <div class="card-body serviceModule-detail">
                                            <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                                when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                                remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                                and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                            </p>

                                            <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list mr-5">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail">
                                                                    <div class="w-75">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=${service.url}>Request A Service</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                <!-- </c:forEach> -->
            </div>
        </div>
    </div>
</div>