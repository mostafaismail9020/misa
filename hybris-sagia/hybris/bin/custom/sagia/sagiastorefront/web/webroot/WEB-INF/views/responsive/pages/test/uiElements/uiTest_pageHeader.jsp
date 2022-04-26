<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<!-- Module description-->
<div class="container">

    <div class="uiTest">
        <h1 class="uiTest-headline">Page Header with expandable panels</h1>
        <p class="uiTest-description">Click on "Show Download History" to toggle animation. Note: on sm breakpoint flex direction changes. Max-height is currently set to be 30vh, might need adjustments for different cases.</p>
    </div>

    
</div>
<!-- End of Module description-->



<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">License Download</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos-arrow"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span class="iconElement iconElement_closeBack"><icon:close/></span></div>
            </button>
        </div>
    </div>
</div>




<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="expandableContent" id="expand02">
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                            <span class="iconElement iconElement_history"><icon:history/></span>                            
                            History</div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input class="searchInputBox-input" type="text" placeholder="Search"/>
                            </div>
                            <ul class="historyList">
                                <li class="historyList-item historyList-item_current">
                                    <a href="#" class="historyList-link">
                                        <div class="historyList-id">500009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">06 Nov 2017</div>
                                            <div class="historyList-status historyList-status_process">In Process</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">200009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">02 Nov 2017</div>
                                            <div class="historyList-status historyList-status_accepted">Accepted</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">100009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">01 Nov 2017</div>
                                            <div class="historyList-status historyList-status_rejected">Rejected</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="" class="historyList-link">
                                        <div class="historyList-id">100009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">01 Nov 2017</div>
                                            <div class="historyList-status historyList-status_rejected">Rejected</div>
                                        </div>
                                    </a>
                                </li>	
                            </ul>
                            <div class="emptyIndicator">
                                No History List
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius">
                    main
                </div>
            </div>
        </div>
    </div>
</div>