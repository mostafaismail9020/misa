<%@ page trimDirectiveWhitespaces="true" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
				<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
					<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
						<c:if test="${component.visible}">
							<section id="strategyServices" class="strategyServices pt-100">
								<div class="strategicTitle wow zoomIn animated" data-wow-duration="1s">
									<h1>${component.title}</h1>
								</div>
								<div class="serviceSectionV3TabsContents serviceSectionV3Col">
                                            <c:forEach var="currentComponent2" items="${listOfFollowings}" varStatus="loop">
                                                        <div class="serviceSectionV3Description animated zoomIn">
                                                        <p>${currentComponent2.number}</p>
                                                            <p>${currentComponent2.description}</p>
                                                        </div>
                                            </c:forEach>
                                </div>
                                </section>
                            </c:if>