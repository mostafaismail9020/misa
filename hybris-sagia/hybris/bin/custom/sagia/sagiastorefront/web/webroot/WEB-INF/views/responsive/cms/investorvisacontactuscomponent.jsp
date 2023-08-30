<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">

<section class="invVisaApplySection invVisaApplyEnquiry" id="investor-visa-contact-form">
  <div class="container-fluid">


      <div class="invVisaApplyEnquirySecTitle">
        <div class="investorVisaTitle">
            <h2 data-wow-duration="1s" ><spring:theme code="portal.investor.visa.contact.expert.name.label"/></h2>
        </div>

        <p class="wow fadeInUp  animated" data-wow-delay="700ms" data-wow-duration="2s"><spring:theme code="portal.investor.visa.contact.us.description"/></p>
        </div>

  <div class="investorVisaContactBtn">
     <c:if test="${language eq 'en'}">
  <a href="https://investsaudi.sa/en/contactUs"><spring:theme code="inv.contact.txt"/></a>
  </c:if>

    <c:if test="${language eq 'ar'}">
  <a href="https://investsaudi.sa/ar/contactUs"><spring:theme code="inv.contact.txt"/></a>
  </c:if>

  </div>
  </div>
</section>

<script src="/_ui/responsive/common/js/wow.js"></script>


<script>
    // Floating label

$('input,textarea').val("");
$('.form-group input, .form-group textarea').focusout(function() {
  var text_val = $(this).val();
  if (text_val === "") {
	console.log("empty!");
	$(this).removeClass('has-value');
  } else {
	$(this).addClass('has-value');
  }
});

new WOW().init();
</script>
</c:if>
