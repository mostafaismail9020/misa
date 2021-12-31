<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<section class='container mb-5 general-info'>
   	<div class='umb-grid'>
		<div class='grid-section'>
			<div class='container'>
            	<div class='row clearfix'>
                	<div class='col-md-12 column'>
                  		<div class="policy-acceptance my-3 text-left">
                        	<input type="checkbox" name="agree-logo" id="agree-logo" onchange="toggleCheckbox(this)" class="" />
                        	<label for="agree-logo">${agreeMessage}</label>
                     	</div>
                     	<div id="brand-policy-logos" class="brand-policy-logos" >
                     		<a download class="button btn-branding pl-5 pr-5 text-uppercase disabled" href="${pdf.url}">${buttonPDF}</a>
                            <a download class="button btn-branding pl-5 pr-5 text-uppercase disabled" href="${jpg.url}">${buttonJPG}</a>
                            <a download class="button btn-branding pl-5 pr-5 text-uppercase disabled" href="${png.url}">${buttonPNG}</a>
                     	</div>                  	
                     	<div id="js-brand-policy-logos" class="d-none">
                        	<a download class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" href="${pdf.url}">${buttonPDF}</a>
                        	<a download class="button btn btn-green pl-5 pr-5 mx-3 text-uppercase" href="${jpg.url}">${buttonJPG}</a>
                        	<a download class="button btn btn-green pl-5 pr-5 m-0 text-uppercase" href="${png.url}">${buttonPNG}</a>
                     	</div>
                  	</div>
               	</div>
			</div>
		</div>
	</div>  
</section>
