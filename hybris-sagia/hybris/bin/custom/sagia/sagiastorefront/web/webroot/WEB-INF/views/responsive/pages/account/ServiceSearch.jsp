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
                    <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                        <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
                            <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                            <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                            <div class="sagiaNavigation-subPane-actions">
                                <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                            </div>
                        </div>
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

<div class="mainSection mainSection_grey  scale-on-resize">
    <div class="container">
        <div class="row services-category-list p-0 p-md-2">
            <div class="service-page-tab col-lg-4">
            <ul class="nav nav-pills sagiaNavigation-services" id="pills-tab" role="tablist">
                <li class="nav-item" onclick="location.href='${encodedContextPath}/service-search/FIRST';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link active" id="pills-licensing-tab" data-toggle="pill" href="#FIRST" role="tab" aria-controls="pills-licensing" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-Yellow-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/License-Services-white-100x100.png" alt="LICENSING SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.license.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>
                
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent0">
                        <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <div class="tab-pane fade service-wrapper service_tab_pane_show show active pb-5 m-auto" id="FIRST1" role="tabpanel" aria-labelledby="pills-licensing-tab">
                                    <div class="p-4 serviceModule-detail border-top-0">
                                        <p class="INS_letter_set_para pb-3 mb-3 text-center">${category.value[0].category.description}</p>
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
                                                                    <a class="text-uppercase request-service" href=${encodedContextPath}/${service.url}><spring:theme code="sagia.services.request.service"/></a>
                                                                </div>                                                         
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                        </c:forEach>
                    </div>
                </li>

                <li class="nav-item" onclick="location.href='${encodedContextPath}/service-search/GOVERNMENTAL SERVICES';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-govt-tab" data-toggle="pill" href="#GOVERNMENTAL SERVICES" role="tab" aria-controls="pills-govt" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-Yellow-100x100.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-white-100x100-2.png" alt="GOVERNMENTAL SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.governmental.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="GOVERNMENTAL SERVICES1" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                            <div class="p-4 serviceModule-detail border-top-0">
                                <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                    <spring:theme code="sagia.governmental.catagory.description"/>
                                </p>
                            </div>
                            <div class="accordion pb-1" id="govtServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                            <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                            <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices1" data-bs-parent="#govtServices1">
                                          <div class="accordion-body serviceModule-detail border-top-0">
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
                                                                            <a class="text-uppercase request-service" href=${encodedContextPath}/services/government/${service.category.code}/${service.code}><spring:theme code="sagia.services.request.service"/></a>
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

                <li class="nav-item"  onclick="location.href='${encodedContextPath}/service-search/SAGIA SERVICES';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-misa-tab" data-toggle="pill" href="#SAGIA SERVICES" role="tab" aria-controls="pills-misa" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA Services.png" alt="MISA SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/MISA-Services-white-100x100-3.png" alt="MISA SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.misa.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>
                
                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="SAGIA SERVICES1" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                            <div class="p-4 serviceModule-detail border-top-0">
                                <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                    <spring:theme code="sagia.misa.services.catagory.description"/>
                                </p>
                            </div>
                            <div class="accordion pb-1" id="misaServices1">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                            <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                            <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices1" data-bs-parent="#misaServices1">
                                          <div class="accordion-body serviceModule-detail border-top-0">
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
                                                                            <a class="text-uppercase request-service" href=${encodedContextPath}/${service.url}><spring:theme code="sagia.services.request.service"/></a>
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
				<li class="nav-item" onclick="location.href='${encodedContextPath}/service-search/IGNITE SERVICES';">
                    <div class="service-wrapper mx-3">
                        <a class="nav-link " id="pills-ignite-tab" data-toggle="pill" href="#IGNITE SERVICES" role="tab" aria-controls="pills-ignite" aria-selected="true">
                            <div class="INS_EPM_border_set text-center">
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-Yellow-100x100.png" alt="IGNITE SERVICES" class="text-center service-icon-1"/>
                                <img src="${commonResourcePath}/images/dashboard-media/services/Government-Documents-white-100x100-2.png" alt="IGNITE SERVICES" class="text-center service-icon-2"/>
                                <span class="licensing-heading"><spring:theme code="dashboard.ignite.service.name"/></span>
                            </div>
                        </a>
                    </div>
                </li>

                <li class="mobile_services mb-5">
                    <div class="tab-content services-container-tabcontent" id="pills-tabContent1">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="IGNITE SERVICES" role="tabpanel" aria-labelledby="pills-ignite-tab" role="tablist">
                            <div class="p-4 serviceModule-detail border-top-0">
                                <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                    <spring:theme code="sagia.ignite.catagory.description"/>
                                </p>
                            </div>
                            <div class="accordion pb-1" id="igniteServices">
                                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                    <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                    <div class="accordion-item">
                                        <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                            <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                            <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                            <h5 class="mb-0">${category.key}</h5>
                                            <div class="plus-minus-icon"></div>
                                          </button>
                                        </h2>
                                        <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#igniteServices" data-bs-parent="#igniteServices">
                                          <div class="accordion-body serviceModule-detail border-top-0">
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
                                                                            <a class="text-uppercase request-service" href=${encodedContextPath}/services/government/${service.category.code}/${service.code}><spring:theme code="sagia.services.request.service"/></a>
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
            </div>

            <!--Desktop-->
            <!-- Licensing Services -->
            <div class=" col-lg-8 tab-content desktop_services services-container-tabcontent" id="pills-tabContent">
                <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                        <div class="tab-pane fade service-wrapper show service_tab_pane_show active pb-5 m-auto" id="FIRST" role="tabpanel" aria-labelledby="pills-licensing-tab">
                            <div class="p-4 serviceModule-detail  mx-2 border-top-0">
                                <p class="INS_letter_set_para pb-3 mb-3 text-center">${category.value[0].category.description}</p>
                                <c:forEach items="${category.value}" var="service">
                                    <div id="serviceModule" class="serviceModule serviceModule_list license-service-list">
                                        <div class="serviceModule-section">
                                            <div class="serviceModule-content">
                                                <div class="serviceModule-description">
                                                    <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                    <div class="serviceModule-detail service-search-module">
                                                        <div class="w-75">
                                                            <p>${service.description}</p>
                                                        </div>
                                                        <div class="w-25 serviceModule-request">
                                                            <a class="text-uppercase request-service" href=${encodedContextPath}/${service.url}><spring:theme code="sagia.services.request.service"/></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                	</c:forEach>

                	<!-- Governmental Services -->
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="GOVERNMENTAL SERVICES" role="tabpanel" aria-labelledby="pills-govt-tab" role="tablist">
                        <div class="p-4 serviceModule-detail mx-2 border-top-0">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                <spring:theme code="sagia.governmental.catagory.description"/>
                            </p>
                        </div>

						<!-- <div class="p-4 serviceModule-detail mx-2 border-top-0">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para text-center" style="color:red">
                                <spring:theme code="services.government.create.disclaimer.title"/>
								<br>
								<spring:theme code="services.government.create.disclaimer.message"/>
								<br>
								<spring:theme code="services.government.create.disclaimer.greet"/>
                            </p>
                        </div> -->

                        <div class="accordion pb-1" id="govtServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                        <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                        <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#govtServices" data-bs-parent="#govtServices">
                                      <div class="accordion-body serviceModule-detail border-top-0">
                                        <p class="service-para"></p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail service-search-module">
                                                                    <div class="w-75">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=${encodedContextPath}/services/government/${service.category.code}/${service.code}><spring:theme code="sagia.services.request.service"/></a>
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

					<!-- MISA Services -->
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="SAGIA SERVICES" role="tabpanel" aria-labelledby="pills-misa-tab" role="tablist">
                        <div class="p-4 serviceModule-detail  mx-2 border-top-0">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                <spring:theme code="sagia.misa.services.catagory.description"/>
                            </p>
                        </div>
                        <div class="accordion pb-1" id="misaServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-m${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-m${loop.index}" aria-expanded="true" aria-controls="collapse-m${loop.index}">
                                        <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                        <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-m${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-m${loop.index}" data-parent="#misaServices" data-bs-parent="#misaServices">
                                      <div class="accordion-body serviceModule-detail border-top-0">
                                        <p class="service-para"></p>
                                        <c:forEach items="${category.value}" var="service">
                                              <div id="serviceModule" class="serviceModule serviceModule_list">
                                                  <div class="serviceModule-section">
                                                      <div class="serviceModule-content">
                                                          <div class="serviceModule-description">
                                                              <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                              <div class="serviceModule-detail service-search-module">
                                                                  <div class="w-75">
                                                                      <p>${service.description}</p>
                                                                  </div>
                                                                  <div class="w-25 serviceModule-request">
                                                                      <a class="text-uppercase request-service" href=${encodedContextPath}/${service.url}><spring:theme code="sagia.services.request.service"/></a>
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

				<!-- IGNITE Services -->
                    <div class="tab-pane fade service-wrapper show service_tab_pane_show pb-5 m-auto" id="IGNITE SERVICES" role="tabpanel" aria-labelledby="pills-ignite-tab" role="tablist">
                        <div class="p-4 serviceModule-detail mx-2 border-top-0">
                            <p class="INS_letter_set_para pb-3 mb-3 service-para text-center">
                                <spring:theme code="sagia.ignite.catagory.description"/>
                            </p>
                        </div>

                        <div class="accordion pb-1" id="igniteServices">
                            <c:forEach items="${SagiaServices}" var="category"  varStatus="loop">
                                <c:set value="${category.value[0].category.code}" var="categoryCode"></c:set>
                                <div class="accordion-item">
                                    <h2 class="accordion-header mb-0" id="heading-g${loop.index}">
                                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-g${loop.index}" aria-expanded="true" aria-controls="collapse-g${loop.index}">
                                        <img class="services-category-img icon-1" src="${commonResourcePath}/images/dashboard-media/services/blue/${categoryCode}.png" alt="" />
                                        <img class="services-category-img icon-2" src="${commonResourcePath}/images/dashboard-media/services/white/${categoryCode}.png" alt="" />
                                        <h5 class="mb-0">${category.key}</h5>
                                        <div class="plus-minus-icon"></div>
                                      </button>
                                    </h2>
                                    <div id="collapse-g${loop.index}" class="accordion-collapse collapse " aria-labelledby="heading-g${loop.index}" data-parent="#igniteServices" data-bs-parent="#igniteServices">
                                      <div class="accordion-body serviceModule-detail border-top-0">
                                        <p class="service-para"></p>
                                        <c:forEach items="${category.value}" var="service">
                                                <div id="serviceModule" class="serviceModule serviceModule_list">
                                                    <div class="serviceModule-section">
                                                        <div class="serviceModule-content">
                                                            <div class="serviceModule-description">
                                                                <span class="serviceModule-headline cursor-pointer">${service.name}</span>
                                                                <div class="serviceModule-detail service-search-module">
                                                                    <div class="w-75">
                                                                        <p>${service.description}</p>
                                                                    </div>
                                                                    <div class="w-25 serviceModule-request">
                                                                        <a class="text-uppercase request-service" href=${encodedContextPath}/services/ignite/${service.category.code}/${service.code}><spring:theme code="sagia.services.request.service"/></a>
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
