<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<div class="container">
    
    <!-- Module description-->
    <div class="uiTest">
        <h1 class="uiTest-headline">Tooltip</h1>
        <p class="uiTest-description">Different type, approaches on Tooltip-styling</p>
    </div>
    <!-- End of Module description-->
    <div class="row">
        <div class="col" style="line-height: 40px;">
            content
        </div>  
        <div class="col">
            <button class="btn js-tip" data-tip-title="Tooltip Information to be shown to the user.">Tooltip (text)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-title='<h1>Tooltip</h1><p>Information to be shown to the user.<br>Presented on hover by default.</p>'>Tooltip (html)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-title='<h1>Tooltip</h1><p>Information to be shown to the user.<br>Presented on hover by default. Here triggered by click, removed by click-outside.</p>' data-tip-trigger="click click-outside">Tooltip (html)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-id="demoTooltipListId" data-tip-width="auto" data-trigger="click">Tooltip (html external, list)</button>
        </div>
    </div>
    <br><br>
    <div class="row">
        <div class="col" style="line-height: 40px;">
            position
        </div>  
        <div class="col">
            <button class="btn js-tip" data-tip-id="mySimpleTipId">Tooltip (top, default)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="bottom" data-tip-id="mySimpleTipId">Tooltip (bottom)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="left" data-tip-id="mySimpleTipId">Tooltip (left)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="right" data-tip-id="mySimpleTipId">Tooltip (right)</button>
        </div>
    </div>
    <br><br>
    <div class="row">
        <div class="col" style="line-height: 40px;">
        </div>  
        <div class="col">
            <button class="btn js-tip" data-tip-placement="left-start" data-tip-id="mySimpleTipId">Tooltip (left-start)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="left-end" data-tip-id="mySimpleTipId">Tooltip (left-end)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="right-start" data-tip-id="demoTooltipListId" data-tip-width="auto" data-tip-trigger="click click-outside">Tooltip (right-start)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="right-end" data-tip-id="demoTooltipListId" data-tip-width="auto">Tooltip (right-end)</button>
        </div>
    </div>
    <br><br>
    <div class="row">
        <div class="col" style="line-height: 40px;">
        </div>  
        <div class="col">
            <button class="btn js-tip" data-tip-placement="top-start" data-tip-id="mySimpleTipId">Tooltip (top-start)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="top-end" data-tip-id="mySimpleTipId">Tooltip (top-end)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="bottom-start" data-tip-id="demoTooltipListId" data-tip-width="auto" data-tip-trigger="click click-outside">Tooltip (bottom-start)</button>
        </div>
        <div class="col">
            <button class="btn js-tip" data-tip-placement="bottom-end" data-tip-id="mySimpleTipId">Tooltip (bottom-end)</button>
        </div>
    </div>

    <!-- separated tooltip html content, can be anywhere on the page -->
    <div class="tooltip_content" id="demotooltipHeadlineAndParagraphId">
        <h1>Information Headline</h1>
        <p>No, really this is necessary information. It should be use to acutally really see what is going on, and what really, really matters</p>
    </div>

    <div class="tooltip_content" id="demoTooltipListId">
        <ul class="tooltip-list tooltip-list_collapsible">
            <li class="tooltip-listItem tooltip-listItem_expanded">
                <div class="tooltip-listItem-header">
                    Demo Entry License for service activity
                    <button class="btn btn_link tooltip-listItem-trigger"><icon:collapse/></button>
                </div>
                <div class="tooltip-listItem-body">
    Service activity license comprises of the following activities: (Real Estate, Agriculture, Trading, Administrative Investment, Information Technology, Tourism, Training, Health, Insurance and Re-Insurance, Education, Advertising and Media, Logistic Services, Organizing Exhibitions, Catering and Food Services, Financial Services, Aviation and Handling Services, etc.).
                </div>
            </li>    
            <li class="tooltip-listItem">
                <div class="tooltip-listItem-header">
                    Demo Entry License for industrial activity
                    <button class="btn btn_link tooltip-listItem-trigger"><icon:collapse/></button>
                </div>
                <div class="tooltip-listItem-body">
    Service activity license comprises of the following activities: (Real Estate, Agriculture, Trading, Administrative Investment, Information Technology, Tourism, Training, Health, Insurance and Re-Insurance, Education, Advertising and Media, Logistic Services, Organizing Exhibitions, Catering and Food Services, Financial Services, Aviation and Handling Services, etc.).
                </div>
            </li>
            <li class="tooltip-listItem">
                <div class="tooltip-listItem-header">
                    Demo Entry License for a scientific and technical office
                    <button class="btn btn_link tooltip-listItem-trigger"><icon:collapse/></button>
                </div>
                <div class="tooltip-listItem-body">
                    Test
                </div>
            </li>
        </ul>
    </div>

    <div class="tooltip_content" id="mySimpleTipId">Tooltip Information to be shown to the user. Tooltip Information to be shown to the user. Tooltip Information to be shown to the user. Tooltip Information to be shown to the user.</div>

</div>