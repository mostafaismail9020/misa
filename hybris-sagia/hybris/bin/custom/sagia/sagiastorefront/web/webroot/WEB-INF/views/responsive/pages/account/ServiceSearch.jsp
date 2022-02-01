<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script type="text/javascript" src="${commonResourcePath}/js/bootstrap-bundle.min.js"></script>

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
        <div class="col-xs-12 col-md-12 services-category-list">
            <ul class="nav nav-pills sagiaNavigation-services" id="pills-tab" role="tablist">
                <li class="nav-item col-lg-4 col-md-12" onclick="location.href='${encodedContextPath}/service-search/FIRST';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link active" id="pills-licensing-tab" data-toggle="pill" href="#FIRST" role="tab" aria-controls="pills-licensing" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-Yellow-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-blue-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.license.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                            <c:if test="${category.key=='Licensing Services'}">
                                <div class="tab-pane fade service-wrapper service_tab_pane_show show active pb-5 m-auto" id="FIRST1" role="tabpanel" aria-labelledby="pills-licensing-tab">
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
                                                                    <a class="text-uppercase request-service" href=/${service.url}>Request A Service</a>
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

                <li class="nav-item col-lg-4 col-md-12" onclick="location.href='${encodedContextPath}/service-search/GOVERNMENTAL SERVICES';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-govt-tab" data-toggle="pill" href="#GOVERNMENTAL SERVICES" role="tab" aria-controls="pills-govt" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-Yellow-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-blue-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.governmental.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="GOVERNMENTAL SERVICES1" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                            <div class="p-4 serviceModule-detail">
                                <p class="INS_letter_set_para pb-3 mb-3 service-para">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                </p>
                            </div>
                            <div class="accordion pb-1" id="govtServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                            <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${category.key}.png" alt="" />
                                            <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${category.key}.png" alt="" />
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices1" data-bs-parent="#govtServices1">
                                          <div class="accordion-body serviceModule-detail">
                                            <p class="service-para">${category.value[0].category.description}</p>
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
                                                                            <a class="text-uppercase request-service" href=${encodedContextPath}/services/government/${service.category.code}/${service.code}>Request A Service</a>
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

                <li class="nav-item col-lg-4 col-md-12"  onclick="location.href='${encodedContextPath}/service-search/SAGIA SERVICES';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-misa-tab" data-toggle="pill" href="#SAGIA SERVICES" role="tab" aria-controls="pills-misa" aria-selected="true">
                            <div class="INS_EPM_border_set text-center p-3">
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services.png" alt="MISA SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services-blue-100x100.png" alt="MISA SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.misa.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>  
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="SAGIA SERVICES1" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                            <div class="p-4 serviceModule-detail">
                                <p class="INS_letter_set_para pb-3 mb-3 service-para">
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                                </p>
                            </div>
                            <div class="accordion pb-1" id="misaServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                            <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${category.key}.png" alt="" />
                                            <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${category.key}.png" alt="" />
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices1" data-bs-parent="#misaServices1">
                                          <div class="accordion-body serviceModule-detail">
                                            <p class="service-para">${category.value[0].category.description}</p>
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
                                                                            <a class="text-uppercase request-service" href=/${service.url}>Request A Service</a>
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
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show active pb-5 m-auto" id="FIRST" role="tabpanel" aria-labelledby="pills-licensing-tab">
                            <div class="p-4 serviceModule-detail mt-5 mx-2">
                                <p class="INS_letter_set_para pb-3 mb-3">${category.value[0].category.description}</p>
                                <c:forEach items="${category.value}" var="service">
                                    <div id="serviceModule" class="serviceModule serviceModule_list mr-5">
                                        <div class="serviceModule-section">
                                            <div class="serviceModule-content">
                                                <div class="serviceModule-description">
                                                    <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                    <div class="serviceModule-detail">
                                                        <div class="w-100">
                                                            <p>${service.description}</p>
                                                        </div>
                                                        <div class="w-25 serviceModule-request">
                                                            <a class="text-uppercase request-service" href=/${service.url}>Request A Service</a>
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
                
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="GOVERNMENTAL SERVICES" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                        <div class="p-4 serviceModule-detail mt-5 mx-2">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <div class="accordion pb-1" id="govtServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                        <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${category.key}.png" alt="" />
                                        <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${category.key}.png" alt="" />
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices" data-bs-parent="#govtServices">
                                      <div class="accordion-body serviceModule-detail">
                                        <p class="service-para">${category.value[0].category.description}</p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail">
                                                                    <div class="w-100">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=${encodedContextPath}/services/government/${service.category.code}/${service.code}>Request A Service</a>
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
                   

                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="SAGIA SERVICES" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                        <div class="p-4 serviceModule-detail mt-5 mx-2">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            </p>
                        </div>
                        <div class="accordion pb-1" id="misaServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                        <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${category.key}.png" alt="" />
                                        <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${category.key}.png" alt="" />
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices" data-bs-parent="#misaServices">
                                      <div class="accordion-body serviceModule-detail">
                                        <p class="service-para">${category.value[0].category.description}</p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail">
                                                                    <div class="w-100">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=/${service.url}>Request A Service</a>
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