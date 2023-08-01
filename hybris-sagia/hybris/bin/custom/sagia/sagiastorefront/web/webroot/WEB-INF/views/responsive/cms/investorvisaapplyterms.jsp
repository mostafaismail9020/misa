<%@ page trimDirectiveWhitespaces="true" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
				<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
					<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
						<c:if test="${component.visible}">
                                <section class="investorVisaApplyTrems invVisa-ptb reveal">
                                    <div class="container-fluid">
                                         <div class="tremsWrapper">
                                       
                                          <div class="tremsWrapperItem">
                                        <div class="investorVisaTitle">
                                        <h2 id="sectionTitle2" class="sectionTitle-animation">${component.title}</h2>
                                      </div>
                                    
                                          <div class="row tremsWrapperItemRow">
                                            <c:forEach var="currentComponent2" items="${listOfTerms}" varStatus="loop">
                                            <div class="col-lg-6 col-xl-5">
                                               <div class="tremsItemPoints wow fadeInUp  animated" data-wow-delay="0.2s" data-wow-duration="1s">
                                             <p><img src="${currentComponent2.investorVisaApplyTermImage.url}"/><span>${currentComponent2.description}</span></p>
                                           </div>
                                            </div>
                                        </c:forEach>

                                          </div>
                                          </div>
                                    
                                       </div>
                                    
                                     
                                    </div>
                                    
                                    </section>
                            </c:if>