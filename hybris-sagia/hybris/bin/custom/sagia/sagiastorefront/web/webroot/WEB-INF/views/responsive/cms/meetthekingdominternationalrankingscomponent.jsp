<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <div class="container-fluid international-ranking-container">
        <div class="row">
            <div class="col-md-12 col-md-12 title-area">
              <h1 class="title-heading w-100">${component.title}</h1>
            </div>

            <video class="int-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
      <source src="${fn:escapeXml(component.localizedGifImage.url)}" type="${fn:escapeXml(component.localizedGifImage.mime)}">
    </video>
        </div>
    </div>