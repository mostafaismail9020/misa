<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<!-- ======= Mega and Gigya Poject Component Starts ======= -->
<div class="tab-pane fade show <c:if test="${defaultProject}">active</c:if>" id="${projectCSS}" role="tabpanel" aria-labelledby="spc_inc-${projectCSS}-tab">
              <img src="${component.backgroundImage.url}" style="position: absolute; top: 0; left: 0; width: 100%">
              <div class="row justify-content-center">
                <div class="col-8 col-sm-4 col-md-4 col-lg-4 pb-5 top-logo">
                  <a tabindex="-1"><img class="img-fluid" src="${fn:escapeXml(projectLogo.url)}"></a>
                </div>
                <div class="col-12 col-sm-8 col-md-8 col-lg-8 pb-5">
                  <div class="project-info">
                    <h4 class="pb-3 mt-0">${projectTitle}</h4>
                    <p>${longDescription}</p>
                    <br>
                    <!-- <p class="pb-5">Sed ut perspiciatis unde omnis iste natus error sit voluptatem</p> -->
                    ${content}
                  </div>
                </div>
              </div>
            </div>
<!-- ======= Mega and Gigya Poject Component Ends ======= -->