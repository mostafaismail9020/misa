<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<ul class="eServiceTutorial js-eServiceTour">

</ul>
<ul class="tutorialTemplate" style="display: none;">
    <li class="eServiceTutorial-item" id="eServiceTutorial-item-{index}">
        <div class="eServiceTutorial-panel">
            <div class="eServiceTutorial-panelInner">
                <div class="eServiceTutorial-header">
                    <div class="eServiceTutorial-headline">{{title}}</div>
                    <button class="eServiceTutorial-close js-eServiceTour-close">
                        <icon:close/>
                    </button>
                </div>
                <div class="eServiceTutorial-body flex-column">
                    <div class="eServiceTutorial-description">
                     <c:if test="${not empty description}">
                             {{description}}
                        </c:if>
                    </div>
                    <div class="eServiceTutorial-actions mt-5">
                        <a class="btn-dashboard popup-btn-width js-eServiceTour-next"><spring:theme code="dashboard.tutorial.next"/></a>
                    </div>
                </div>
            </div>
        </div>
    </li>
</ul>
<div class="modal fade" id="eServiceTour"  tabindex="-1" role="dialog" aria-labelledby="eServiceTour" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <img src="${commonResourcePath}/images/dashboard-media/show-me-around.png"/>
                </div>
                <div class="modal-title clr_gld"></div>
                <div class="modal-description modal-description_eService"></div>
            </div>
            <div class="modal-footer modal-footer_wrap flex-column">
                <button type="button" class="btn-outline js-eServiceTour-start" data-dismiss="modal"><spring:theme code="dashboard.tutorial.modal.button.text"/></button>
                <a class=" btn_inFooterModal mt-3 cursor-pointer js-skipTutorial" data-dismiss="modal" onclick="dismissTutorial();"><spring:theme code="general.dont.show.this.message.again"/></a>
            </div>
        </div>
    </div>
</div>
<script>
    var tutorialJson = [];
    <c:if test="${not empty tutorialJson}">
        tutorialJson = ${tutorialJson};
    </c:if>
    var displayTutorial = false;
    <c:if test="${displayTutorial}">
        displayTutorial = ${displayTutorial};
    </c:if>
    $("#btn-show-me-around").on('click',function(){
        displayTutorial = true;
        SAGIA.eServiceTour.init();
    })
</script>
