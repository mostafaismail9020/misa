<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

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
                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link active" id="pills-licensing-tab" data-toggle="pill" href="#licensing-service" role="tab" aria-controls="pills-licensing" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/B2C/Services icon 1.png" alt="LICENSING SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/B2C/Services icon 2.png" alt="LICENSING SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><a href="${encodedContextPath}/service-search/FIRST"><spring:theme code="dashboard.license.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                            <c:if test="${category.key=='Licensing Services'}">
                                <div class="tab-pane fade service-wrapper service_tab_pane_show show active pb-5 m-auto" id="licensing-service1" role="tabpanel" aria-labelledby="pills-licensing-tab">
                                    <div class="p-4 serviceModule-detail">
                                        <p class="INS_letter_set_para pb-3 mb-3">${category.value[0].category.description}</p>
                                        <c:forEach items="${category.value}" var="service">
                                            <div id="serviceModule" class="serviceModule serviceModule_list">
                                                <div class="serviceModule-section">
                                                    <div class="serviceModule-content">
                                                        <div class="serviceModule-description">
                                                            <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                            <div class="serviceModule-detail">
                                                                <div class="">
                                                                    <p>${service.description}</p>
                                                                </div>   
                                                                <div class=" serviceModule-request">
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
                    </div>
                </li>

                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-govt-tab" data-toggle="pill" href="#govt-service" role="tab" aria-controls="pills-govt" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/B2C/Services icon 1.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/B2C/Services icon 2.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><a href="${encodedContextPath}/service-search/GOVERNMENTAL SERVICES"><spring:theme code="dashboard.governmental.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="govt-service1" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                            <div class="p-4 serviceModule-detail">
                                <p class="INS_letter_set_para pb-3 mb-3">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                </p>
                            </div>
                            <div class="accordion pb-1" id="govtServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices1" data-bs-parent="#govtServices1">
                                          <div class="accordion-body serviceModule-detail">
                                            <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                                when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                                remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                                and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                            </p>
                                            <c:forEach items="${category.value}" var="service">
                                                    <div id="serviceModule" class="serviceModule serviceModule_list">
                                                        <div class="serviceModule-section">
                                                            <div class="serviceModule-content">
                                                                <div class="serviceModule-description">
                                                                    <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                    <div class="serviceModule-detail">
                                                                        <div class="">
                                                                            <p>${service.description}</p>
                                                                        </div>
                                                                        <div class=" serviceModule-request">
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
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </li>

                <li class="nav-item col-lg-4 col-md-12">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-misa-tab" data-toggle="pill" href="#misa-service" role="tab" aria-controls="pills-misa" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/B2C/Services icon 1.png" alt="MISA SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/B2C/Services icon 2.png" alt="MISA SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><a href="${encodedContextPath}/service-search/SAGIA SERVICES"><spring:theme code="dashboard.misa.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>  
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="misa-service1" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                            <div class="p-4 serviceModule-detail">
                                <p class="INS_letter_set_para pb-3 mb-3">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                </p>
                            </div>
                            <div class="accordion pb-1" id="misaServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices1" data-bs-parent="#misaServices1">
                                          <div class="accordion-body serviceModule-detail">
                                            <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                                when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                                remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                                and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                            </p>
                                            <c:forEach items="${category.value}" var="service">
                                                    <div id="serviceModule" class="serviceModule serviceModule_list">
                                                        <div class="serviceModule-section">
                                                            <div class="serviceModule-content">
                                                                <div class="serviceModule-description">
                                                                    <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                    <div class="serviceModule-detail">
                                                                        <div class="">
                                                                            <p>${service.description}</p>
                                                                        </div>
                                                                        <div class=" serviceModule-request">
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
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <!--Desktop-->
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
                
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="govt-service" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                        <div class="p-4 serviceModule-detail m-5">
                            <p class="INS_letter_set_para pb-3 mb-3">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <div class="accordion pb-1" id="govtServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices" data-bs-parent="#govtServices">
                                      <div class="accordion-body serviceModule-detail">
                                        <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                            when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                            and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                        </p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
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

                                
                            </c:forEach>
                        </div>
                    </div>
                   

                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="misa-service" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                        <div class="p-4 serviceModule-detail m-5">
                            <p class="INS_letter_set_para pb-3 mb-3">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <div class="accordion pb-1" id="misaServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices" data-bs-parent="#misaServices">
                                      <div class="accordion-body serviceModule-detail">
                                        <p class="">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                            when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, 
                                            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                                            and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                        </p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
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

                                
                            </c:forEach>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>