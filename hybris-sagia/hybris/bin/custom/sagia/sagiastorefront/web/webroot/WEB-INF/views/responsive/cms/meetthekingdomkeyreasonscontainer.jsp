<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
                    
<!-- Breadcurms -->
<!--End of  Breadcurms -->
<div class="macro_economic_container key-reason-invest">

    <div class="row m-0">
        <div class="col-md-12 title-area">
            <c:if test="${not empty title}">
                <h1 class="title-heading">${title}</h1>
            </c:if>
            <c:if test="${not empty description}">
                <h3>${description}</h3>
            </c:if>
        </div>
    </div>

    <div class="container-fluid Inc-tab-panel key-reason-title">
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <c:forEach var="currentComponent" items="${components}" varStatus="loop">
                <li class="nav-item">
                    <a class="nav-link" href="#pane-${loop.index}" id="pills-home-tab-${loop.index}" data-toggle="pill" aria-selected="true">
                        ${currentComponent.reasonHeader}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="tab-content key-reason-card" id="pills-tabContent" role="tablist">
        <c:forEach var="currentComponent" items="${components}" varStatus="loop">
            <div id="pane-${loop.index}" class="card tab-pane fade show" role="tabpanel" aria-labelledby="pills-home-tab-${loop.index}">
                <div class="card-header" role="tab" id="heading-${loop.index}">
                    <a class="nav-link" data-toggle="collapse" href="#collapse-${loop.index}" aria-expanded="true" aria-controls="collapse-${loop.index}"> ${currentComponent.reasonHeader}</a>
                </div>
                <div id="collapse-${loop.index}" class="collapse" role="tabpanel" data-parent="#pills-tabContent" aria-labelledby="heading-${loop.index}">
                    <cms:component component="${currentComponent}" element="div" />
                </div>

            </div>
        </c:forEach>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="mediaModalTitle" aria-hidden="true">
    <button type="button" class="btn-dismiss close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">x</span>
      </button>
	<div class="modal-dialog modal-dialog-centered mediaModal_section" role="document">
		<div class="modal-content">
			<div class="modal-body">
                <div class="video-player-container">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" 
                            src="${fn:escapeXml(videoLink.url)}" 
                            frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                        </iframe>
                    </div>
                </div>
			</div>
		</div>
	</div>
</div>
<!-- Modal End -->