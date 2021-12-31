<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="mainSection mainSection_grey">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="services.services"/></h1>
            <div>
                <div class="controlBar">
                    <a href="#" class="controlBar-action active">
                        <span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
                        <span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
                    </a>
                    <a href="${encodedContextPath}/service-requests-overview" class="controlBar-action">
                        <span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
                        <span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_grey mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-header">
            <div class="searchInputBox searchInputBox_slim searchInputBox_limited">
                <input id="searchInputBox" class="searchInputBox-input js-expandContent js-expandContent_services" data-expand-target="expand01" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                <button class="searchInputBox-close js-expandContent-close"><icon:close/></button>
            </div>
            <div id="toggle-section">
                <div class="toggleView" data-view-target="serviceModule">
                    <a href="javascript:void(0)" class="js-toggleView toggleView-action toggleView-action_grid">
                        <icon:gridView/>
                    </a>
                    <a href="javascript:void(0)" class="js-toggleView toggleView-action toggleView-action_list active">
                        <icon:listView/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_grey mainSection_noPaddingTop">
    <div class="container" style="min-height:600px !important;">
        <div class="expandableContent expandableContent_wide" id="expand01">
            <div class="expandableContent-aside">
                <ul class="serviceMenu">
                    <c:forEach items="${SagiaServices}" var="category">
                        <li class="serviceMenu-item">
                            <a href="" class="serviceMenu-head js-serviceMenu">
                            <span class="serviceMenu-head-icon">
                                <img src="${category.value[0].category.icon.url}" width="22" height="22">
                            </span>
                                <div class="serviceMenu-head-title">${category.key}</div>
                                <div class="serviceMenu-head-action">
                                    <div>&#43;</div>
                                    <div class="hidden">&#8722;</div>
                                </div>
                            </a>

                            <ul class="serviceMenu-body">
                                <c:forEach items="${category.value}" var="service">
                                    <li class="serviceMenu-body-item" data-code="${service.code}" data-name="${service.name}" data-url="${service.url}">
                                        <a href="#" class="serviceMenu-body-link">${service.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="expandableContent-main" id="expandable-content-div">
                <!-- Service Module -->
                <div id="serviceModule" class="serviceModule serviceModule_list" style="display: none">
                    <c:forEach items="${SagiaServices}" var="category">
                        <div class="serviceModule-section">
                            <div class="serviceModule-content">
                                <div class="serviceModule-icon">
                                        <%--<icon:service-license/>--%>
                                    <img src="${category.value[0].category.icon.url}">
                                </div>
                                <div class="serviceModule-description">
                                    <div class="serviceModule-headline">${category.key}</div>
                                    <div class="serviceModule-count"> ${fn:length(category.value)}&nbsp;<spring:theme code="services.search.services"/> </div>
                                    <div class="serviceModule-detail">
                                        <p>${category.value[0].category.description}</p>
                                    </div>
                                </div>
                                <div class="serviceModule-actions">
                                    <button class="serviceModule-action js-expandService">
                                        <div><spring:theme code="services.showallservices"/>s<span><icon:arrow_green_down/></span></div>
                                        <div class="hidden"><spring:theme code="services.hideallservices"/><span><icon:arrow_green_up/></span></div>
                                    </button>
                                </div>
                                <span class="serviceModule-arrow"></span>
                            </div>

                            <div class="serviceModule-sectionDetail">
                                <ul class="serviceList">
                                    <c:forEach items="${category.value}" var="service">
                                        <li class="serviceList-item">
                                            <a href="#" class="serviceList-link">
                                                <div class="serviceList-headline" data-code="${service.code}" data-name="${service.name}" data-url="${service.url}"
                                                >${service.name}</div>
                                            </a>
                                            <div class="serviceList-description"><p>${service.description}</p>
                                            </div>

                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="serviceModule-actions">
                                    <button class="serviceModule-action js-expandService">
                                        <div><spring:theme code="services.hideallservices"/><span><icon:arrow_green_up/></span></div>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- contentModule -->
                <div class="contentModule" id="contentModule" style="display: none">
                    <div class="contentModule-section">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline" id="service-details-title"><spring:theme code="services.sellingserviceofrealestate"/></div>
                            <div>
                                <a href="#" class="btn btn_slim" id="service-request-button">
                                    <spring:theme code="services.request"/>
                                </a>
                            </div>
                        </div>

                        <div class="js-panelTabs panelTabs panelTabs_simple" id="service-details-tabs">
                            <div class="panelTabs-head" id="tab-head-1">
                                <span class="panelTabs-label"><spring:theme code="services.documents"/></span>
                            </div>

                            <div class="panelTabs-body" id="tab-body-1">
                                <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                                    <p></p>
                                </div>
                                <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                    <div class="contentModule-headline contentModule-headline_small"><spring:theme code="services.howto"/></div>
                                    <!-- video content goes here -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
