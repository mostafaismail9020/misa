<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">License Download</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div>Show Service History<span>&#x27f6;</span></div>
                <div class="hidden">Hide Service History<span class="iconElement iconElement_closeBack"><icon:close/></span></div>
            </button> 
        </div>
    </div>
</div>




<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent" id="expand03">
           
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">

                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                            <span class="iconElement iconElement_history"><icon:history/></span>
                            History</div>
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
                                    <a href="#" class="historyList-link">
                                        <div class="historyList-id">200009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">02 Nov 2017</div>
                                            <div class="historyList-status historyList-status_accepted">Accepted</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="historyList-item">
                                    <a href="#" class="historyList-link">
                                        <div class="historyList-id">100009991</div>
                                        <div class="historyList-info">
                                            <div class="historyList-date">01 Nov 2017</div>
                                            <div class="historyList-status historyList-status_rejected">Rejected</div>
                                        </div>
                                    </a>
                                </li>                     
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">

                        <div class="contentModule-section contentModule-section_noDivider">

                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline"><icon:info/>Service Info: 27000220</div>
                                <button type="submit" class="btn btn_slim">
                                    Replace
                                </button>
                                <div class="statusIndicator statusIndicator_process">
                                    Status: <span>In Process</span>
                                </div>
                            </div>
                            

                            <div class="statusBox">
                                <div class="statusBox-description">
                                    <div class="statusBox-info">Info:<span class="tip" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/></span></div>
                                    <div class="statusBox-details">
                                        This service is for any entity that wants to request/replacement copy of its original license.
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="contentModule-actions contentModule-actions_right">
                            <button type="submit" class="btn btn_outline">
                                Resubmit
                            </button>
                        </div>

                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline contentModule-headline_small">Comments</div>
                            <div class="commentModule">
                                <div class="commentModule-window">
                                    <ul class="messageList">
                                        <li class="messageList-item">
                                            <div class="messageList-img">
                                                <img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="">
                                            </div>
                                            <div class="messageList-content">
                                                <h2 class="messageList-name">Omar Khan (SAGIA advisor)</h2>
                                                <h3 class="messageList-date">05 Jan 2018</h3>
                                                <div class="messageList-message">
                                                    <p>Enim optio, voluptatum possimus officia quas.</p>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis et sit reiciendis temporibus! Quo doloremque quam officia hic molestiae consectetur itaque quod quidem nisi sapiente iste blanditiis, ad nesciunt provident?</p>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>                
                                </div>
                            </div>
                        </div>      

                    </div>
                </div>
                
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                               <icon:documents/>Support Documents</div>
                            <ul class="downloadList">
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Article of Association
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>
                                <li class="downloadList-item">
                                    <div class="downloadList-description">
                                        <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                        Contract of Sale
                                    </div>
                                    <div class="downloadList-actions">
                                        <a href="#" class="link link_nowrap">
                                            <span class="iconElement iconElement_cloud"><icon:download/></span>
                                            Download
                                        </a>
                                    </div>
                                </li>               
                            </ul>
                        </div>
                    </div>
                </div>              
                
            </div>
        </div>
    </div>
</div>